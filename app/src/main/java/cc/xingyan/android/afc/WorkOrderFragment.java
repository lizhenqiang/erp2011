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
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnTouch;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.workorder.SyncStatus;
import cc.xingyan.android.afc.provider.workorder.WorkOrderColumns;
import cc.xingyan.android.afc.provider.workorder.WorkOrderCursor;
import cc.xingyan.android.afc.provider.workorder.WorkOrderSelection;
import cc.xingyan.android.afc.util.NetUtil;
import icepick.State;

/**
 * Created by San on 9/22/15.
 */
public class WorkOrderFragment extends BaseFragment {
    private static final int REQUEST_ZBAR_SCANNER = 0;
    private static final int PERMISSION_REQUEST_CAMERA = 1;



    private String STOP_SCAN="android.intent.action.FUNCTION_BUTTON_UP";

    private String SCAN_BACK_KEY = "Scan_context";

    @Inject
    Authenticator mAuthenticator;

    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    @Bind(R.id.floating_action_menu)
    FloatingActionMenu mFloatingActionMenu;
    @Bind(R.id.menu_shade)
    View mMenuShadeView;

    @State
    int mSelectedTabPosition;

    private CharSequence[] mTabTitles;
    private ContentObserver mWorkOrderObserver;
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_work_order, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewCompat.setElevation(mTabLayout, getResources().getDimensionPixelOffset(R.dimen.elevation_1dp));
        mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        final CharSequence[] tabs = mTabTitles = getResources().getTextArray(R.array.work_order_kinds);
        for (CharSequence t : tabs) {
            mTabLayout.addTab(mTabLayout.newTab().setText(t));
        }
        myTabPagerAdapter = new TabPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(myTabPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                for (Fragment f : getChildFragmentManager().getFragments()) {
                    if (f instanceof WorkOrderListFragment) {
                        ((WorkOrderListFragment) f).exitActionMode();
                    }
                }
            }
        });

        mFloatingActionMenu.setClosedOnTouchOutside(true);
        mFloatingActionMenu.setOnMenuToggleListener((opened) -> {
            mMenuShadeView.setVisibility(opened ? View.VISIBLE : View.GONE);
        });

        getContext().getContentResolver().registerContentObserver(WorkOrderColumns.CONTENT_URI, true, mWorkOrderObserver = new ContentObserver(new Handler()) {
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
        getContext().getContentResolver().unregisterContentObserver(mWorkOrderObserver);
        super.onDestroyView();
    }

    private void updateTabTitles() {
        final CharSequence[] tabs = mTabTitles;
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            try {
                mTabLayout.getTabAt(i).setText(tabs[i] + getTabTitleSuffix(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getTabTitleSuffix(int tabPosition) {
        final String userId = mAuthenticator.getAuthenticatedUserId();
        switch (tabPosition) {
            default:
                return "";
            case 0:
                return String.format("(%1$d)", new WorkOrderSelection().userId(userId).and().syncStatus(SyncStatus.LOCAL).and().deletePending(false).count(getContext().getContentResolver()));
        }
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle(R.string.title_fragment_work_order);
        try {
            mTabLayout.getTabAt(mSelectedTabPosition).select();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnTouch(R.id.main_content)
    boolean onTouchOutsideFloatingActionMenu(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_UP) {
            mFloatingActionMenu.close(true);
            return true;
        }
        return false;
    }

    @OnClick(R.id.action_new_work_order)
    void onActionNewWorkOrder(View view) {
        mFloatingActionMenu.close(true);
        if (checkTopNum()) {
            Toast.makeText(getContext(), "快速工单总数超过1000条，先删除部分已同步工单，腾出空间", Toast.LENGTH_SHORT).show();
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("deviceID", null);
            final Intent intent = new Intent(getContext(), WorkOrderActivity.class);
            intent.setAction(Intent.ACTION_EDIT);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    private boolean checkTopNum() {
        boolean t=false;
        WorkOrderCursor cursor = new WorkOrderSelection().query(getContext().getContentResolver());
        if(cursor.getCount() > 1000){
            t = true;
        }
        cursor.close();
        return t;
    }

    @OnClick(R.id.action_scan)
    void onActionScan(View view) {
        if (checkTopNum()) {
            Toast.makeText(getContext(), "快速工单总数超过1000条，先删除部分已同步工单，腾出空间", Toast.LENGTH_SHORT).show();
        } else {
            if (Build.VERSION.SDK_INT >= 23 && getContext().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            } else {

                Intent intent;
                String clientBrand = Build.BRAND;
                String clientModel = Build.MODEL;


                intent = new Intent(getContext(), ScanActivity.class);
                startActivityForResult(intent, REQUEST_ZBAR_SCANNER);
            }
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
                        Toast.makeText(getActivity(), "快单—这是物资小码，请扫设备码！", Toast.LENGTH_SHORT).show();
                    }else  if(strsInfo[1].equals("1")){
                        Toast.makeText(getActivity(), "快单—这是物资大码，请扫设备码！", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if(data.contains(":")){
                        String[] temp = data.split("\r\n");
                        dealData(temp[0].split(":")[1]);
                    }else {
                        Toast.makeText(getActivity(), "快单—不合法的编码！", Toast.LENGTH_SHORT).show();
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

        if (data != null) {
            String scanResultInfo = (String) data.getExtras().get("result");

            mFloatingActionMenu.close(true);

            String clientModel = Build.MODEL;

            if(clientModel.equals("C6000")){
                dealData(scanResultInfo);


            }else{
                dealData(scanResultInfo);
            }

        }
    }


    private void dealData(String scanResultInfo){
        Bundle bundle = new Bundle();
        bundle.putString("deviceID", getDeviceId(scanResultInfo));
        final Intent intent = new Intent(getContext(), WorkOrderActivity.class);
        intent.setAction(Intent.ACTION_EDIT);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private String getDeviceId(String deviceid) {
        String deviceId;

        deviceId = deviceid;
        return deviceId;
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
                    return WorkOrderListFragment.newInstance(WorkOrderListFragment.KIND_LOCAL);
                case 1:
                    return WorkOrderListFragment.newInstance(WorkOrderListFragment.KIND_SYNCHRONIZED);
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

}
