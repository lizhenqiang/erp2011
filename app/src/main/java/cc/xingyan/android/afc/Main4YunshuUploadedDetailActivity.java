package cc.xingyan.android.afc;

import android.database.DataSetObserver;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuJiadeng on 2017/12/25.
 *
 */
public class Main4YunshuUploadedDetailActivity extends FragmentActivity {
    TabLayout mTabLayout;
    ViewPager mViewPager;

    String taskId = "";

    private PagerAdapter mPagerAdapter;
    private List<Page> pages = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yunshu_uploadeddetail);
        mTabLayout = (TabLayout)findViewById(R.id.act_yunshu_uploadeddetal_tab_layout);
        mViewPager = (ViewPager)findViewById(R.id.act_yunshu_uploadeddetal_view_pager);

        Bundle bundle = getIntent().getExtras();
        taskId = bundle.getString("transport_task_id");

    }

    @Override
    protected void onStart() {
        super.onStart();
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
        pages.add(new Page(Page.TAB_HEAD, "表头信息", taskId));
        pages.add(new Page(Page.TAB_LINE, "行信息", taskId));

        mPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private static class Page {
        public static final int TAB_HEAD = 0;
        public static final int TAB_LINE = 1;

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
            super(getSupportFragmentManager());
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
                case Page.TAB_HEAD:
                    return PartYunShuUploadedHeadFragment.newInstance((String) p.attachment);
                case Page.TAB_LINE:
                    return PartYunShuUploadedLineFragment.newInstance((String) p.attachment);
            }
        }

        @Override
        public int getCount() {
            return pages.size();
        }
    }

}
