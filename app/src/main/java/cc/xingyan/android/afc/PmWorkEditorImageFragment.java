package cc.xingyan.android.afc;

import android.database.DataSetObserver;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;

/**
 * Created by YuJiadeng on 2016/11/17.
 */
public class PmWorkEditorImageFragment extends BaseFragment {
    private static final String ARG_PM_ORDER_ID = "pm_order_id";
    public static String mPMOrderId;

    @Bind(R.id.tab_pm_work_image)
    TabLayout mTabLayout;
    @Bind(R.id.view_pm_work_image)
    ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private List<Page> pages = new ArrayList<>();


    public static Fragment newInstance(String mPMOrderId) {
        PmWorkEditorImageFragment fragment = new PmWorkEditorImageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PM_ORDER_ID, mPMOrderId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);

        mPMOrderId = getArguments().getString(ARG_PM_ORDER_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pm_work_editor_image, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final TabLayout.ViewPagerOnTabSelectedListener l = new TabLayout.ViewPagerOnTabSelectedListener(mViewPager);
        mTabLayout.setOnTabSelectedListener(l);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.setAdapter(mPagerAdapter = new PagerAdapter());
        mPagerAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                // 更新tablayout
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
        pages.add(new Page(Page.TAB_PM_BEFORE_WORK, "处理前图片"));
        pages.add(new Page(Page.TAB_PM_AFTER_WORK, "处理后图片"));
        mPagerAdapter.notifyDataSetChanged();
    }


    private static class Page {
        public static final int TAB_PM_BEFORE_WORK = 0;
        public static final int TAB_PM_AFTER_WORK = 1;
        int type;
        String title;

        public Page(@NonNull int type, @NonNull String title) {
            this.type = type;
            this.title = title;
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
                case Page.TAB_PM_BEFORE_WORK:
                    return PmWorkEditorImageBeforeWorkFragment.newInstance(mPMOrderId);

                case Page.TAB_PM_AFTER_WORK:
                    return PmWorkEditorImageAfterWorkFragment.newInstance(mPMOrderId);

            }
        }

        @Override
        public int getCount() {
            return pages.size();
        }
    }
}
