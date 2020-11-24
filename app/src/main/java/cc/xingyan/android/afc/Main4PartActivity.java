package cc.xingyan.android.afc;

import android.content.Intent;
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
 * Created by YuJiadeng on 2017/3/8.
 */
public class Main4PartActivity extends FragmentActivity {

    private MyPartPagerAdapter myPartPagerAdapter;
    private NoScrollViewPager partViewpager;
    private List<Fragment> fragments;


    private int selectedColor, unSelectedColor;

    AppView4Part lingJianAppView;
    AppView4Part kunCunAppView;
    AppView4Part panKuAppView;
    AppView4Part yunShuAppView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_main);

        Intent intent = getIntent();
        boolean isFromPankuReportManagement = false;
        if(intent != null){
            isFromPankuReportManagement  =  intent.getBooleanExtra("isFromPankuReportManagement", false);
        }
        init(isFromPankuReportManagement);
    }


    private void init(boolean isFromPankuReportManagement){
        selectedColor = getResources().getColor(R.color.red);
        unSelectedColor = getResources().getColor(R.color.tv_normal);

        initAppView(isFromPankuReportManagement);

        initViewPager(isFromPankuReportManagement);
    }


    private void initAppView(boolean isFromPankuReportManagement) {
        lingJianAppView = (AppView4Part)findViewById(R.id.lingjian);
        kunCunAppView = (AppView4Part)findViewById(R.id.kuncun);
        panKuAppView = (AppView4Part)findViewById(R.id.panku);
        yunShuAppView = (AppView4Part)findViewById(R.id.yunshu);

        if(isFromPankuReportManagement){
            lingJianAppView.setNameColor(unSelectedColor);
            kunCunAppView.setNameColor(unSelectedColor);
            yunShuAppView.setNameColor(unSelectedColor);
            panKuAppView.setNameColor(selectedColor);
        }else {
            lingJianAppView.setNameColor(selectedColor);
            kunCunAppView.setNameColor(unSelectedColor);
            panKuAppView.setNameColor(unSelectedColor);
            yunShuAppView.setNameColor(unSelectedColor);
        }


        lingJianAppView.setOnClickListener(new MyPartAppViewClickListener(0));
        kunCunAppView.setOnClickListener(new MyPartAppViewClickListener(1));
        panKuAppView.setOnClickListener(new MyPartAppViewClickListener(2));
        yunShuAppView.setOnClickListener(new MyPartAppViewClickListener(3));
    }


    private void initViewPager(boolean isFromPankuReportManagement) {
        partViewpager = (NoScrollViewPager)findViewById(R.id.part_viewpager);
        fragments = new ArrayList<Fragment>();
        fragments.add(new PartLingJianFragment());
        fragments.add(new PartKunCunFragment());
        fragments.add(new PartPanKuFragment());

        myPartPagerAdapter = new MyPartPagerAdapter(getSupportFragmentManager(), fragments);

        partViewpager.setAdapter(myPartPagerAdapter);

        if(isFromPankuReportManagement){
            partViewpager.setCurrentItem(2);
        }else{
            partViewpager.setCurrentItem(0);
        }

    }



    private class MyPartAppViewClickListener implements View.OnClickListener {
        private int index = 0;

        public MyPartAppViewClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {

            switch (index) {
                case 0:
                    lingJianAppView.setNameColor(selectedColor);
                    kunCunAppView.setNameColor(unSelectedColor);
                    panKuAppView.setNameColor(unSelectedColor);
                    yunShuAppView.setNameColor(unSelectedColor);

                    break;

                case 1:
                    lingJianAppView.setNameColor(unSelectedColor);
                    kunCunAppView.setNameColor(selectedColor);
                    panKuAppView.setNameColor(unSelectedColor);
                    yunShuAppView.setNameColor(unSelectedColor);

                    break;

                case 2:
                    lingJianAppView.setNameColor(unSelectedColor);
                    kunCunAppView.setNameColor(unSelectedColor);
                    panKuAppView.setNameColor(selectedColor);
                    yunShuAppView.setNameColor(unSelectedColor);

                    break;

                case 3:
                    lingJianAppView.setNameColor(unSelectedColor);
                    kunCunAppView.setNameColor(unSelectedColor);
                    panKuAppView.setNameColor(unSelectedColor);
                    yunShuAppView.setNameColor(selectedColor);

                    break;

            }
            partViewpager.setCurrentItem(index);
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
