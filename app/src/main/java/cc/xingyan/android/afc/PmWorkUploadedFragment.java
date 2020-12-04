package cc.xingyan.android.afc;

import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkCursor;

/**
 * Created by YuJiadeng on 2016/11/22.
 */
public class PmWorkUploadedFragment extends BaseFragment {

    private static final String ARG_URI = "uri";

    private Uri mUri;

    private String mPMOrderId;

    @Bind(R.id.tab_pm_work_image)
    TabLayout mTabLayout;
    @Bind(R.id.view_pm_work_image)
    ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private List<Page> pages = new ArrayList<>();

    public static Fragment newInstance(Uri uri) {
        PmWorkUploadedFragment fragment = new PmWorkUploadedFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_URI, uri);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);
        mUri = getArguments().getParcelable(ARG_URI);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pm_work_editor_image, container, false);


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final PmifsWorkCursor work = new PmifsWorkCursor(getContext().getContentResolver().query(mUri, null, null, null, null));

        try {
            if (work.moveToNext()) {
                getActivity().setTitle("[PM工单] #" + work.getOrderId());
                ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(work.getDeviceCode());
                mPMOrderId = work.getOrderId();
            }
        } finally {
            work.close();
        }

        final TabLayout.ViewPagerOnTabSelectedListener l = new TabLayout.ViewPagerOnTabSelectedListener(mViewPager);
        mTabLayout.setOnTabSelectedListener(l);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.setAdapter(mPagerAdapter = new PagerAdapter());


        mPagerAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {

                mTabLayout.setOnTabSelectedListener(null);
                mTabLayout.removeAllTabs();
                for (int i = 0, count = pages.size(); i < count; i++) {
                    mTabLayout.addTab(mTabLayout.newTab().setText(pages.get(i).title));
                }
                mTabLayout.setOnTabSelectedListener(l);
            }
        });
        loadPages();
    }

    private void loadPages() {

        pages.clear();
        pages.add(new Page(Page.TAB_PM_DEVICEINFO, "设备信息", mPMOrderId));
        pages.add(new Page(Page.TAB_PM_CONTENT, "工作内容", mPMOrderId));
        pages.add(new Page(Page.TAB_PM_IMAGE, "工作图片", mPMOrderId));
        pages.add(new Page(Page.TAB_PM_MATERIAL, "工作物料", mPMOrderId));
        mPagerAdapter.notifyDataSetChanged();
    }

    private static class Page {
        public static final int TAB_PM_DEVICEINFO = 0;
        public static final int TAB_PM_CONTENT = 1;
        public static final int TAB_PM_IMAGE = 2;
        public static final int TAB_PM_MATERIAL = 3;

        int type;
        String title;
        Object attachment;

        public Page(@NonNull int type, @NonNull String title, @NonNull Object attachment) {
            this.type = type;
            this.title = title;
            this.attachment = attachment;
        }
    }

    class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter() {
            super(getChildFragmentManager());
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pages.get(position).title;
        }

        @Override
        public Fragment getItem(int position) {
            final Page p = pages.get(position);
            switch (p.type) {
                default:
                case Page.TAB_PM_DEVICEINFO:
                    return PmWorkNewDeviceInfoFragment.newInstance((String) p.attachment);

                case Page.TAB_PM_CONTENT:
                    return PmWorkEditorContentFragment.newInstance((String) p.attachment, true);
                case Page.TAB_PM_IMAGE:
                    return PmWorkUploadImageFragment.newInstance((String) p.attachment);

                case Page.TAB_PM_MATERIAL:
                    return PmWorkNewMaterialFragment.newInstance((String) p.attachment);
            }
        }

        @Override
        public int getCount() {
            return pages.size();
        }
    }
}
