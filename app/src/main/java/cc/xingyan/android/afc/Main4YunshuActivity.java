package cc.xingyan.android.afc;

import android.os.Bundle;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import cc.xingyan.android.afc.widget.AppView4Part;
import cc.xingyan.android.afc.widget.NoScrollViewPager;

/**
 * Created by YuJiadeng on 2017/12/20.
 *
 */
public class Main4YunshuActivity extends FragmentActivity {
    private MyPartPagerAdapter myYunshuPagerAdapter;
    private NoScrollViewPager yunshuViewpager;
    private List<Fragment> fragments;

    private int selectedColor, unSelectedColor;

    AppView4Part noUploadAppView;
    AppView4Part uploadedAppView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yunshu_main);

        init();
    }


    private void init(){
        selectedColor = getResources().getColor(R.color.red);
        unSelectedColor = getResources().getColor(R.color.tv_normal);

        initAppView();

        initViewPager();
    }


    private void initAppView() {
        noUploadAppView = (AppView4Part)findViewById(R.id.yunshu_noupload);
        uploadedAppView = (AppView4Part)findViewById(R.id.yunshu_uploaded);
        noUploadAppView.setNameColor(selectedColor);
        uploadedAppView.setNameColor(unSelectedColor);


        noUploadAppView.setOnClickListener(new MyPartAppViewClickListener(0));
        uploadedAppView.setOnClickListener(new MyPartAppViewClickListener(1));
    }


    private void initViewPager() {
        yunshuViewpager = (NoScrollViewPager)findViewById(R.id.yunshu_viewpager);
        fragments = new ArrayList<Fragment>();
        fragments.add(new PartYunshuNoUploadFragment());
        fragments.add(new PartYunshuUploadedFragment());

        myYunshuPagerAdapter = new MyPartPagerAdapter(getSupportFragmentManager(), fragments);

        yunshuViewpager.setAdapter(myYunshuPagerAdapter);

        yunshuViewpager.setCurrentItem(0);


    }



    private class MyPartAppViewClickListener implements View.OnClickListener {
        private int index = 0;

        public MyPartAppViewClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {

            switch (index) {
                case 0:
                    noUploadAppView.setNameColor(selectedColor);
                    uploadedAppView.setNameColor(unSelectedColor);

                    break;

                case 1:
                    noUploadAppView.setNameColor(unSelectedColor);
                    uploadedAppView.setNameColor(selectedColor);

                    break;

            }
            yunshuViewpager.setCurrentItem(index);
        }

    }



    private class MyPartPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        public MyPartPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int arg0) {
            return (fragmentList == null || fragmentList.size() == 0) ? null
                    : fragmentList.get(arg0);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }


        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
    }
}
