package cc.xingyan.android.afc;

import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.net.Uri;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.CmService;
import cc.xingyan.android.afc.api.model.UpLocationInfo;
import cc.xingyan.android.afc.api.model.UpLocationInfos;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.cmwork.CmWorkColumns;
import cc.xingyan.android.afc.provider.cmwork.CmWorkContentValues;
import cc.xingyan.android.afc.provider.cmwork.CmWorkCursor;
import cc.xingyan.android.afc.provider.cmwork.CmWorkSelection;
import cc.xingyan.android.afc.provider.cmwork.CmWorkStatus;
import cc.xingyan.android.afc.util.LogUtil;
import cc.xingyan.android.afc.util.NetUtil;
import cc.xingyan.android.afc.util.TService;

/**
 * Created by 1 on 2015/11/26.
 *
 */
public class CmFragment extends BaseFragment {

    private static final int REQUEST_ZBAR_SCANNER = 0;
    private static final int PERMISSION_REQUEST_CAMERA = 1;
    private int TYPE_CM_WORK_NEW = 0;
    private int TYPE_CM_WORK_QUERY = 1;

    public static boolean isCMFragmentVisible = false;



    private String STOP_SCAN="android.intent.action.FUNCTION_BUTTON_UP";

    private String SCAN_BACK_KEY = "Scan_context";

    @Inject
    Authenticator mAuthenticator;

    @Inject
    CmService cmService;

    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    @Bind(R.id.floating_action_menu)
    FloatingActionMenu mFloatingActionMenu;
    @Bind(R.id.menu_shade)
    View mMenuShadeView;

    private CharSequence[] mTabTitles;
    private ContentObserver mCmObserver;
    TabPagerAdapter myTabPagerAdapter;


    String userCode;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);
        initLocation();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cm, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewCompat.setElevation(mTabLayout, getResources().getDimensionPixelOffset(R.dimen.elevation_1dp));
        mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        final CharSequence[] tabs = mTabTitles = getResources().getTextArray(R.array.cm_kinds);
        for (CharSequence t : tabs) {
            mTabLayout.addTab(mTabLayout.newTab().setText(t));
        }
        myTabPagerAdapter = new TabPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(myTabPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                for (Fragment f : getChildFragmentManager().getFragments()) {
                    if (f instanceof CmWorkListFragment) {
                        ((CmWorkListFragment) f).exitActionMode();
                    }
                }
            }
        });

        getContext().getContentResolver().registerContentObserver(CmWorkColumns.CONTENT_URI, true, mCmObserver = new ContentObserver(new Handler()) {
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

        isCMFragmentVisible = true;
    }

    @Override
    public void onDestroyView() {
        getContext().getContentResolver().unregisterContentObserver(mCmObserver);
        super.onDestroyView();
    }

    private void updateTabTitles() {
        final CharSequence[] tabs = mTabTitles;
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            mTabLayout.getTabAt(i).setText(tabs[i] + getTabTitleSuffix(i));
        }
    }

    private String getTabTitleSuffix(int tabPosition) {
        final String userId = mAuthenticator.getAuthenticatedUserId();
        switch (tabPosition) {
            default:
                return "";
            case 0:
                return String.format("(%1$d)", new CmWorkSelection().userId(userId).and().status(CmWorkStatus.NEW).count(getContext().getContentResolver()));
            case 1:
                return String.format("(%1$d)", new CmWorkSelection().userId(userId).and().status(CmWorkStatus.FAULTREPORT).count(getContext().getContentResolver()));
            case 2:
                return String.format("(%1$d)", new CmWorkSelection().userId(userId).and().status(CmWorkStatus.RELEASED).count(getContext().getContentResolver()));
            case 3:
                return String.format("(%1$d)", new CmWorkSelection().userId(userId).and().status(CmWorkStatus.WORKDONE).count(getContext().getContentResolver()));
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle(R.string.nav_cm);
    }

    @OnClick(R.id.action_new_cm)
    void onActionNewCM(View view) {
        mFloatingActionMenu.close(true);
        if (checkTopNum()) {
            Toast.makeText(getContext(), "CM工单总数超过1000条，先删除部分完工或关闭的CM工单，腾出空间", Toast.LENGTH_SHORT).show();
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("deviceID", null);
            bundle.putInt("type", TYPE_CM_WORK_NEW);
            final Intent intent = new Intent(getContext(), CmWorkActivity.class);
            intent.setAction(Intent.ACTION_EDIT);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    private boolean checkTopNum() {
        boolean t = false;
        CmWorkCursor cursor = new CmWorkSelection().query(getContext().getContentResolver());
        if (cursor.getCount() > 1000) {
            t = true;
        }
        cursor.close();
        return t;
    }

    @OnClick(R.id.action_scan)
    void onActionScan(View view) {

        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {

            }
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    PERMISSION_REQUEST_CAMERA);
        } else {
            Intent intent = null;
            String clientModel = Build.MODEL;


            intent = new Intent(getContext(), ScanActivity.class);
            startActivityForResult(intent, REQUEST_ZBAR_SCANNER);
        }

    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(android.content.Context context,
                              Intent intent) {
            if(isCMFragmentVisible){
                String action = intent.getAction();
                if (action.equals(NetUtil.RECE_DATA_ACTION)) {
                    String data = intent.getStringExtra(SCAN_BACK_KEY);
                    if(data.contains("#")){
                        String[] strsInfo = data.split("#");
                        if(strsInfo[1].equals("0")){
                            Toast.makeText(getActivity(), "CM—这是物资小码，请扫设备码！", Toast.LENGTH_SHORT).show();
                        }else  if(strsInfo[1].equals("1")){
                            Toast.makeText(getActivity(), "CM—这是物资大码，请扫设备码！", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        if(data.contains(":")){
                            String[] temp = data.split("\r\n");
                            dealData(temp[0].split(":")[1]);
                        }else {
                            Toast.makeText(getActivity(), "CM—不合法的编码！", Toast.LENGTH_SHORT).show();
                        }
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



    private void initLocation(){

        Intent intent = new Intent("com.android.scanservice.output.foreground");
        intent.putExtra("Scan_output_foreground", false);
        getActivity().sendBroadcast(intent);


        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(NetUtil.RECE_DATA_ACTION);
        getActivity().registerReceiver(receiver, iFilter);

        userCode = mAuthenticator.getAuthenticatedUserId();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            String DeviceID = getDeviceId((String) data.getExtras().get("result"));


            mFloatingActionMenu.close(true);

            String clientModel = Build.MODEL;

            if(clientModel.equals("C60000000")){

            }else{
                dealData(DeviceID);
            }



        }

    }

    private void dealData(String DeviceID){


        String imei = TService.imei;
        String locErrorCode = TService.locErrorCode;
        String lat = TService.lat;
        String lon = TService.lon;
        String addr = TService.addr;

        LogUtil.debug("Scan_erp", "imei———— :" + imei);
        LogUtil.debug("Scan_erp", "dealData_locErrorCode———— :" + locErrorCode);
        LogUtil.debug("Scan_erp", "dealData_lat———— :" + lat);
        LogUtil.debug("Scan_erp", "dealData_lon———— :" + lon);

        boolean isLatAndLonNotNull = (lat == null) && (lon == null) ? false : true;
        boolean isLocErrorCodeNotNull = (locErrorCode != null) && (locErrorCode.trim().equals("161") || locErrorCode.trim().equals("66"));

        if(isLatAndLonNotNull || isLocErrorCodeNotNull){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String userCode = mAuthenticator.getAuthenticatedUserId();


                    UpLocationInfo upLocationInfo = new UpLocationInfo();
                    upLocationInfo.setUserCode(userCode);
                    upLocationInfo.setImei(NetUtil.getString(imei));
                    upLocationInfo.setLat(NetUtil.getString(lat));
                    upLocationInfo.setLon(NetUtil.getString(lon));
                    upLocationInfo.setAddr(NetUtil.getString(addr));

                    UpLocationInfos upLocationInfos = new UpLocationInfos();
                    ArrayList<UpLocationInfo> upLocationInfoList = new ArrayList<UpLocationInfo>();
                    upLocationInfoList.add(upLocationInfo);

                    upLocationInfos.setUpLocationInfoList(upLocationInfoList);
                    cmService.upLocInfo(upLocationInfos).subscribe(resp -> {
                        LogUtil.info("Scan_erp", ">>>>>>>>>>>>>>>>>>>>>>>OK!");
                    }, e -> {
                        LogUtil.info("Scan_erp", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Fail!" + e);
                    });


                }
            }).start();
        }


        CmWorkCursor cursor = new CmWorkSelection().deviceCode(DeviceID).and()
                .status(CmWorkStatus.FAULTREPORT, CmWorkStatus.RELEASED).and()
                .userId(mAuthenticator.getAuthenticatedUserId())
                .query(getContext());
        int cursorCount = cursor.getCount();
        if (cursorCount >= 1) {
            List<ShowInfoObj> showInfoObjList = new ArrayList<ShowInfoObj>();

            while (cursor.moveToNext()) {
                ShowInfoObj showInfoObj = new ShowInfoObj();
                showInfoObj.setTitle("[CM工单] #" + cursor.getOrderId());
                showInfoObj.setmCMWorkId(cursor.getId());
                showInfoObjList.add(showInfoObj);
            }

            String[] showTitles = new String[showInfoObjList.size()];
            for (int i = 0; i < showTitles.length; i++) {
                showTitles[i] = showInfoObjList.get(i).getTitle();
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setIcon(R.drawable.ic_info_black_24dp);
            builder.setTitle("工单列表");
            ListAdapter myAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, showTitles);
            builder.setAdapter(myAdapter, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int position) {
                    mFloatingActionMenu.close(true);
                    long selectCMWorkId = showInfoObjList.get(position).getmCMWorkId();

                    final Uri uri = ContentUris.withAppendedId(CmWorkColumns.CONTENT_URI, selectCMWorkId);
                    final CmWorkCursor work = new CmWorkCursor(getContext().getContentResolver().query(uri, null, null, null, null));
                    try {
                        if (work.moveToNext()) {
                            if (work.getArriveTime() == null) {
                                CmWorkContentValues values = new CmWorkContentValues()
                                        .putArriveTime(System.currentTimeMillis())
                                        .putLastModified(System.currentTimeMillis());
                                getContext().getContentResolver().update(uri, values.values(), null, null);
                            }
                        }
                    } finally {
                        work.close();
                    }

                    Bundle bundle = new Bundle();
                    bundle.putString("deviceID", DeviceID);
                    bundle.putInt("type", TYPE_CM_WORK_QUERY);
                    Intent intent = new Intent(getActivity(), CmWorkActivity.class);
                    intent.putExtra(CmWorkActivity.EXTRA_CM_WORK, ContentUris.withAppendedId(CmWorkColumns.CONTENT_URI, selectCMWorkId));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton(R.string.cancel, null);
            builder.setNeutralButton(R.string.new_cm_work, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mFloatingActionMenu.close(true);
                    if (checkTopNum()) {
                        Toast.makeText(getContext(), "CM工单总数超过1000条，先删除部分完工或关闭的CM工单，腾出空间", Toast.LENGTH_SHORT).show();
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putString("deviceID", DeviceID);
                        bundle.putInt("type", TYPE_CM_WORK_NEW);
                        final Intent intent = new Intent(getContext(), CmWorkActivity.class);
                        intent.setAction(Intent.ACTION_EDIT);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
            });
            builder.show();
            cursor.close();
            return;
        } else {
            new AlertDialog.Builder(getContext())
                    .setIcon(R.drawable.ic_info_black_24dp)
                    .setTitle("扫描结果")
                    .setMessage(R.string.message_device_cm_not_found)
                    .setPositiveButton(R.string.ok, null)
                    .setNeutralButton(R.string.new_cm_work, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mFloatingActionMenu.close(true);
                            if (checkTopNum()) {
                                Toast.makeText(getContext(), "CM工单总数超过1000条，先删除部分完工或关闭的CM工单，腾出空间", Toast.LENGTH_SHORT).show();
                            } else {
                                Bundle bundle = new Bundle();
                                bundle.putString("deviceID", DeviceID);
                                bundle.putInt("type", TYPE_CM_WORK_NEW);
                                final Intent intent = new Intent(getContext(), CmWorkActivity.class);
                                intent.setAction(Intent.ACTION_EDIT);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        }
                    })
                    .setCancelable(false)
                    .show();
        }
        cursor.close();
    }

    private String getDeviceId(String scanInfo) {
        String deviceId = null;

        deviceId = scanInfo;
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
                    return CmWorkListFragment.newInstance(CmWorkListFragment.KIND_NEW);
                case 1:
                    return CmWorkListFragment.newInstance(CmWorkListFragment.KIND_FAULT_REPORT);
                case 2:
                    return CmWorkListFragment.newInstance(CmWorkListFragment.KIND_RELEASED);
                case 3:
                    return CmWorkListFragment.newInstance(CmWorkListFragment.KIND_WORK_DONE);
                case 4:
                    return CmWorkListFragment.newInstance(CmWorkListFragment.KIND_WORK_DONE_UPLOAD);
                case 5:
                    return CmWorkListFragment.newInstance(CmWorkListFragment.KIND_FINISH);
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
        private long mCMWorkId;

        public String getTitle() {
            return title;
        }

        public long getmCMWorkId() {
            return mCMWorkId;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setmCMWorkId(long mCMWorkId) {
            this.mCMWorkId = mCMWorkId;
        }
    }

}
