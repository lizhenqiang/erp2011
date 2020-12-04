/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkColumns;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkCursor;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkSelection;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkStatus;
import cc.xingyan.android.afc.util.NetUtil;

/**
 * Created by San on 10/8/15.
 */
public class PmFragment extends BaseFragment {

    private static final int REQUEST_ZBAR_SCANNER = 0;
    private static final int PERMISSION_REQUEST_CAMERA = 1;

    private static final int REQUEST_QUERY_PM = 2;



    private String STOP_SCAN="android.intent.action.FUNCTION_BUTTON_UP";

    private String SCAN_BACK_KEY = "Scan_context";

    @Inject
    Authenticator mAuthenticator;

    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    private CharSequence[] mTabTitles;
    private ContentObserver mPmObserver;
    TabPagerAdapter myTabPagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);


        Intent intent = new Intent("com.android.scanservice.output.foreground");
        intent.putExtra("Scan_output_foreground", false);
        getActivity().sendBroadcast(intent);

        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(NetUtil.RECE_DATA_ACTION);
        getActivity().registerReceiver(receiver, iFilter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pm, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewCompat.setElevation(mTabLayout, getResources().getDimensionPixelOffset(R.dimen.elevation_1dp));
        mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        final CharSequence[] tabs = mTabTitles = getResources().getTextArray(R.array.pm_job_kinds);
        for (CharSequence t : tabs) {
            mTabLayout.addTab(mTabLayout.newTab().setText(t));
        }
        myTabPagerAdapter = new TabPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(myTabPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override public void onPageSelected(int position) {
                for (Fragment f : getChildFragmentManager().getFragments()) {
                    if (f instanceof PmWorkListFragment) {
                        ((PmWorkListFragment) f).exitActionMode();
                    }
                }
            }
        });

        getContext().getContentResolver().registerContentObserver(PmifsWorkColumns.CONTENT_URI, true, mPmObserver = new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
                updateTabTitles();
            }
        });
        updateTabTitles();
    }

    @Override
    public void onResume() {
        super.onResume();
        myTabPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        getContext().getContentResolver().unregisterContentObserver(mPmObserver);
        super.onDestroyView();
    }

    private void updateTabTitles() {
        final CharSequence[] tabs = mTabTitles;
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            mTabLayout.getTabAt(i).setText(tabs[i] +  getTabTitleSuffix(i));
        }
    }

    private String getTabTitleSuffix(int tabPosition) {
        final String userId = mAuthenticator.getAuthenticatedUserId();
        switch (tabPosition) {
            default:
                return "";
            case 0:
                return String.format("(%1$d)", new PmifsWorkSelection().userId(userId).and().status(PmifsWorkStatus.NEW).count(getContext().getContentResolver()));
            case 1:
                return String.format("(%1$d)", new PmifsWorkSelection().userId(userId).and().status(PmifsWorkStatus.RELEASED).count(getContext().getContentResolver()));
            case 2:
                return String.format("(%1$d)", new PmifsWorkSelection().userId(userId).and().status(PmifsWorkStatus.WORKDONE).count(getContext().getContentResolver()));
            case 3:
                return String.format("(%1$d)", new PmifsWorkSelection().userId(userId).and().status(PmifsWorkStatus.WORKDONEUPLOAD).count(getContext().getContentResolver()));
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle(R.string.title_fragment_pm);
    }

    @OnClick(R.id.pm_query)
    void queryPM(){
        Intent intent = null;

        intent = new Intent(getContext(), PmWorkQueryActivity.class);

        startActivityForResult(intent, REQUEST_QUERY_PM);
    }



    @OnClick(R.id.action_scan) void onActionScan() {

        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {

            }
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    PERMISSION_REQUEST_CAMERA);
        } else {
            Intent intent = null;
            String clientBrand = Build.BRAND;
            String clientModel = Build.MODEL;


            intent = new Intent(getContext(), ScanActivity.class);

            startActivityForResult(intent, REQUEST_ZBAR_SCANNER);
        }
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(android.content.Context context,
                              Intent intent) {
            String action = intent.getAction();
            if (action.equals(NetUtil.RECE_DATA_ACTION)) {
                String data = intent.getStringExtra(SCAN_BACK_KEY);

                if(data.contains("#")){
                    String[] strsInfo = data.split("#");
                    if(strsInfo[1].equals("0")){
                        Toast.makeText(getActivity(), "PM—这是物资小码，请扫设备码！", Toast.LENGTH_SHORT).show();
                    }else  if(strsInfo[1].equals("1")){
                        Toast.makeText(getActivity(), "PM—这是物资大码，请扫设备码！", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if(data.contains(":")){
                        String[] temp = data.split("\r\n");
                        dealData(temp[0].split(":")[1]);
                    }else {
                        Toast.makeText(getActivity(), "PM—不合法的编码！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT < 28){
            Intent intent = new Intent();
            intent.setAction(STOP_SCAN);
            getActivity().sendBroadcast(intent);
        }
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_ZBAR_SCANNER){
            if (data != null) {
                String scanResultInfo = (String) data.getExtras().get("result");


                String clientModel = Build.MODEL;

                if(clientModel.equals("C6000")){

                }else{
                    dealData(scanResultInfo);
                }
            }
        }else if(requestCode == REQUEST_QUERY_PM){
            if (data != null) {
                SharedPreferences pmQueryPreferences = getActivity().getSharedPreferences("pm_query_data", Activity.MODE_PRIVATE);
                if(pmQueryPreferences != null) {

                    String queryOrderIDStr = pmQueryPreferences.getString("queryOrderID", "");
                    String logicCodeStr = pmQueryPreferences.getString("logicCode", "");
                    String logicNameStr = pmQueryPreferences.getString("logicName", "");
                    String physicalCodeStr = pmQueryPreferences.getString("physicalCode", "");
                    String physicalNameStr = pmQueryPreferences.getString("physicalName", "");


                    long apply_start_time = pmQueryPreferences.getLong("applyStartTime", 0);
                    long plan_start_time = pmQueryPreferences.getLong("planStartTime", 0);
                    long plan_end_time = pmQueryPreferences.getLong("planEndTime", Long.MAX_VALUE);

                    if(plan_end_time == 0){
                        plan_end_time = Long.MAX_VALUE;
                    }

                    PmifsWorkCursor cursor = new PmifsWorkSelection()
                            .userId(mAuthenticator.getAuthenticatedUserId())
                            .and()
                            .orderIdLike("%" + queryOrderIDStr + "%")
                            .and()
                            .deviceLogicCodeLike("%" + logicCodeStr + "%")
                            .and()
                            .deviceLogicNameLike("%" + logicNameStr + "%")
                            .and()
                            .deviceCodeLike("%" + physicalCodeStr + "%")
                            .and()
                            .deviceNameLike("%" + physicalNameStr + "%")
                            .and()
                            .applySTimeGtEq(apply_start_time)
                            .and()
                            .planSTimeGtEq(plan_start_time)
                            .and()
                            .planFTimeLtEq(plan_end_time)
                            .query(getActivity());

                    List<ShowInfoObj> showInfoObjList = new ArrayList<ShowInfoObj>();
                    while (cursor.moveToNext()) {
                        ShowInfoObj showInfoObj = new ShowInfoObj();
                        showInfoObj.setTitle("[PM工单] #" + cursor.getOrderId());
                        showInfoObj.setmJobId(cursor.getId());
                        showInfoObjList.add(showInfoObj);

                    }

                    String[] showTitles = new String[showInfoObjList.size()];
                    for (int i = 0; i < showTitles.length; i++) {
                        showTitles[i] = showInfoObjList.get(i).getTitle();
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setIcon(R.drawable.ic_info_black_24dp);
                    builder.setTitle("工单列表");
                    ListAdapter myAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, showTitles);
                    builder.setAdapter(myAdapter, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int position) {

                            long seletcMJobId = showInfoObjList.get(position).getmJobId();
                            Intent intent = new Intent(getActivity(), PmWorkActivity.class);
                            intent.putExtra(PmWorkActivity.EXTRA_PM_WORK, ContentUris.withAppendedId(PmifsWorkColumns.CONTENT_URI, seletcMJobId));
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton(R.string.cancel, null);
                    builder.show();
                    cursor.close();
                }

            }

        }

    }

    private void dealData(String scanResultInfo){
        long mJobId = 0;

        PmifsWorkCursor cursor = new PmifsWorkSelection()
                .deviceCode(scanResultInfo)
                .and()
                .status(PmifsWorkStatus.NEW, PmifsWorkStatus.RELEASED)
                .and()
                .userId(mAuthenticator.getAuthenticatedUserId())
                .query(getContext());

        int cursorCount = cursor.getCount();

        if (cursorCount == 1) {
            cursor.moveToFirst();
            mJobId = cursor.getId();
        } else if (cursorCount > 1) {
            List<ShowInfoObj> showInfoObjList = new ArrayList<ShowInfoObj>();

            while (cursor.moveToNext()) {
                ShowInfoObj showInfoObj = new ShowInfoObj();
                showInfoObj.setTitle("[PM工单] #" + cursor.getOrderId());
                showInfoObj.setmJobId(cursor.getId());
                showInfoObjList.add(showInfoObj);

            }

            String[] showTitles = new String[showInfoObjList.size()];
            for (int i = 0; i < showTitles.length; i++) {
                showTitles[i] = showInfoObjList.get(i).getTitle();
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setIcon(R.drawable.ic_info_black_24dp);
            builder.setTitle("工单列表");
            ListAdapter myAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, showTitles);
            builder.setAdapter(myAdapter, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int position) {

                    long seletcMJobId = showInfoObjList.get(position).getmJobId();
                    Intent intent = new Intent(getActivity(), PmWorkActivity.class);
                    intent.putExtra(PmWorkActivity.EXTRA_PM_WORK, ContentUris.withAppendedId(PmifsWorkColumns.CONTENT_URI, seletcMJobId));
                    startActivity(intent);
                }
            });
            builder.setNegativeButton(R.string.cancel, null);
            builder.show();
            cursor.close();
            return;
        }

        if (mJobId != 0) {
            Intent intent = new Intent(getActivity(), PmWorkActivity.class);
            intent.putExtra(PmWorkActivity.EXTRA_PM_WORK, ContentUris.withAppendedId(PmifsWorkColumns.CONTENT_URI, mJobId));
            startActivity(intent);
        } else {
            new AlertDialog.Builder(getContext())
                    .setIcon(R.drawable.ic_info_black_24dp)
                    .setTitle("扫描结果")
                    .setMessage(R.string.message_device_pm_not_found)
                    .setPositiveButton(R.string.ok, null)
                    .setCancelable(false)
                    .show();
        }
        cursor.close();
    }

    class TabPagerAdapter extends FragmentStatePagerAdapter {
        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                default:
                case 0:
                    return PmWorkListFragment.newInstance(PmWorkListFragment.KIND_DOWNLOAD);
                case 1:
                    return PmWorkListFragment.newInstance(PmWorkListFragment.KIND_RELEASED);
                case 2:
                    return PmWorkListFragment.newInstance(PmWorkListFragment.KIND_DONE);
                case 3:
                    return PmWorkListFragment.newInstance(PmWorkListFragment.KIND_UPLOADED);
            }
        }

        @Override
        public int getCount() {
            return mTabTitles.length;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }


    class ShowInfoObj {
        private String title;
        private long mJobId;

        public String getTitle() {
            return title;
        }

        public long getmJobId() {
            return mJobId;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setmJobId(long mJobId) {
            this.mJobId = mJobId;
        }
    }
}
