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

import javax.inject.Inject;

import butterknife.Bind;
import cc.xingyan.android.afc.api.CmService;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.cmwork.CmWorkCursor;

/**
 * Created by 1 on 2015/12/15.
 */
public class CmWorkEditerConfirmFragment extends BaseFragment {
    private static final String TAG = "cm_confirm";
    private static final String ARG_URI = "uri";

    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    @Inject   static CmService cmService;
    private Uri mUri;

    private String mConfirm;
    private String mCMOrderId;
    private PagerAdapter mPagerAdapter;
    private List<Page> pages = new ArrayList<>();

    public static Fragment newInstance(Uri uri) {
        CmWorkEditerConfirmFragment fragment = new CmWorkEditerConfirmFragment();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cm_work_editor_confirm, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        final CmWorkCursor work = new CmWorkCursor(getContext().getContentResolver().query(mUri, null, null, null, null));
        try {
            if (work.moveToNext()) {
                mCMOrderId = work.getOrderId();
                getActivity().setTitle("[CM工单] # " + work.getOrderId());
                ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(work.getDeviceCode());
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
        pages.add(new Page(Page.TAB_FAULT, "故障现象", mCMOrderId));
        mPagerAdapter.notifyDataSetChanged();
    }

    private static class Page {
        public static final int TAB_FAULT = 0;

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
                case Page.TAB_FAULT:
                    return CmFaultConfirmFragment.newInstance((String) p.attachment);
            }
        }

        @Override
        public int getCount() {
            return pages.size();
        }
    }


}