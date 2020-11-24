package cc.xingyan.android.afc;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.DeviceService;
import cc.xingyan.android.afc.api.model.HeadAndLineInfo;
import cc.xingyan.android.afc.api.model.HeadAndLineInfos;
import cc.xingyan.android.afc.api.model.PartYunshuGetLineNoReturn;
import cc.xingyan.android.afc.api.model.PartYunshuGetNewReturn;
import cc.xingyan.android.afc.api.model.TranFromInfo;
import cc.xingyan.android.afc.api.model.TranHeadInfo;
import cc.xingyan.android.afc.api.model.TranToInfo;
import cc.xingyan.android.afc.api.model.TranUpdateHeadInfo;
import cc.xingyan.android.afc.api.model.TranUpdateLineInfo;
import cc.xingyan.android.afc.api.model.UserInfo;
import cc.xingyan.android.afc.api.model.UserInfos;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.yunshuhead.YunshuHeadColumns;
import cc.xingyan.android.afc.provider.yunshuhead.YunshuHeadContentValues;
import cc.xingyan.android.afc.provider.yunshuhead.YunshuHeadCursor;
import cc.xingyan.android.afc.provider.yunshuhead.YunshuHeadSelection;
import cc.xingyan.android.afc.provider.yunshukuwei.YunshuKuweiColumns;
import cc.xingyan.android.afc.provider.yunshukuwei.YunshuKuweiContentValues;
import cc.xingyan.android.afc.provider.yunshukuwei.YunshuKuweiSelection;
import cc.xingyan.android.afc.provider.yunshuline.YunshuLineContentValues;
import cc.xingyan.android.afc.provider.yunshuline.YunshuLineCursor;
import cc.xingyan.android.afc.provider.yunshuline.YunshuLineSelection;
import cc.xingyan.android.afc.util.LogUtil;
import cc.xingyan.android.afc.util.NetUtil;
import cc.xingyan.android.afc.util.TService;

/**
 * Created by YuJiadeng on 2017/12/20.
 *
 */
public class PartYunshuNoUploadFragment extends BaseFragment {

    String updateSuccessTaskId = "";

    @Inject
    DeviceService deviceService;

    @Inject
    Authenticator mAuthenticator;

    @Bind(R.id.yunshu_noupload_tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.yunshu_noupload_view_pager)
    ViewPager mViewPager;


    private PagerAdapter mPagerAdapter;
    private List<Page> pages = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.part__yunshu_noupload, null);

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }

    @Override
    public void onResume() {
        super.onResume();
        PartYunshuUploadedFragment.isUploadedFragmentShow = false;
    }

    private ProgressDialog progress;
    @OnClick({R.id.yunshu_noupload_new_transport})
    void onNewtransport(){

        if(isHasNotUploaded()){
            Toast.makeText(getContext(), "已有未上传的货盘运输！", Toast.LENGTH_SHORT).show();
            return;
        }

        progress = new ProgressDialog(getContext());
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIcon(R.drawable.ic_settings_white_24dp);
        progress.setTitle("获取信息");
        progress.setMessage("请稍等...");
        progress.setIndeterminate(false);
        progress.setButton(DialogInterface.BUTTON_POSITIVE, "取消",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        progress.dismiss();
                    }

                });
        progress.show();


        new Thread(new Runnable() {
            @Override
            public void run() {

                UserInfos userInfos = new UserInfos();
                List<UserInfo> userInfoList = new ArrayList<UserInfo>();
                UserInfo userInfo = new UserInfo();

                String user = mAuthenticator.getAuthenticatedUserId();
                String imei = TService.imei;
                String lat = TService.lat;
                String lon = TService.lon;
                userInfo.setUserId(user);
                userInfo.setUserIMEI(NetUtil.getString(imei));
                userInfo.setUserLat(NetUtil.getString(lat));
                userInfo.setUserLon(NetUtil.getString(lon));

                userInfoList.add(userInfo);
                userInfos.setUserInfoList(userInfoList);

                deviceService.postGetNewTransportInfo(userInfos).subscribe(resp -> {
                    if (resp != null && resp.size() > 0) {
                        try {
                            putNew2Data(resp);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Message msg = new Message();
                            msg.what = 0x002;
                            myHandler.sendMessage(msg);
                            progress.dismiss();
                        }



                        Message msg = new Message();
                        msg.what = 0x001;
                        myHandler.sendMessage(msg);

                        progress.dismiss();
                    }else{
                        Message msg = new Message();
                        msg.what = 0x002;
                        myHandler.sendMessage(msg);
                        progress.dismiss();
                    }
                }, e -> {
                    LogUtil.info("GetNewTransportInfo", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Fail!" + e);
                    Message msg = new Message();
                    msg.what = 0x002;
                    myHandler.sendMessage(msg);
                    progress.dismiss();
                });
            }
        }).start();

    }

    @OnClick({R.id.yunshu_toupload})
    void onUploadTransport(){
        String taskId = "";

        YunshuHeadSelection headSelection = new YunshuHeadSelection().transportTaskType("0");
        YunshuHeadCursor headCursor = headSelection.query(getContext().getContentResolver());
        try {
            while (headCursor.moveToNext()){
                taskId = headCursor.getTransportTaskId();
            }
        } finally {
            headCursor.close();
        }


        if (checkItem(taskId)) {
            onUpdateMaterialRows(taskId);
        } else {
            Toast.makeText(getActivity(), taskId + "货盘运输单上传失败，请检查后重试！", Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick({R.id.yunshu_del})
    void onDelTransport(){
        new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_info_black_24dp)
                .setTitle("温馨提示")
                .setMessage("将删除本地所有货盘运输任务，未上传的货盘运输任务将会丢失数据！")
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new YunshuHeadSelection().delete(getContext().getContentResolver());
                        new YunshuKuweiSelection().delete(getContext().getContentResolver());
                        new YunshuLineSelection().delete(getContext().getContentResolver());


                        mTabLayout.setVisibility(View.INVISIBLE);
                        mViewPager.setVisibility(View.INVISIBLE);


                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .setCancelable(false)
                .show();
    }



    private boolean isHasNotUploaded(){
        YunshuHeadSelection headSelection = new YunshuHeadSelection();
        YunshuHeadCursor headCursor = headSelection.query(getContext().getContentResolver());
        try {
            while (headCursor.moveToNext()) {
                String transportTaskType = headCursor.getTransportTaskType();
                if(transportTaskType != null && transportTaskType.equals("0")){
                    return true;
                }
            }
        } finally {
            headCursor.close();
        }
        return false;
    }


    private void putNew2Data(List<PartYunshuGetNewReturn> partYunshuGetNewReturnList) throws  Exception{
        PartYunshuGetNewReturn partYunshuGetNewReturn = partYunshuGetNewReturnList.get(0);

        TranHeadInfo tranHead = partYunshuGetNewReturn.getTranHead();
        List<TranFromInfo> tranFromList = partYunshuGetNewReturn.getTranFromList();
        List<TranToInfo> tranToList = partYunshuGetNewReturn.getTranToList();


        String transportTaskId = tranHead.getNewTranTaskId();
        String transportTaskState = tranHead.getNewTranState();


        Date dateToday = new Date();
        SimpleDateFormat sdfToday = new SimpleDateFormat("yyyy/MM/dd");
        Date dateTarget = sdfToday.parse(sdfToday.format(dateToday));
        long createDate = dateTarget.getTime();

        String planBy = mAuthenticator.getAuthenticatedUserId();

        ContentValues[] valuesHead = new ContentValues[1];
        valuesHead[0] = new YunshuHeadContentValues()
                .putTransportTaskId(transportTaskId)
                .putTransportTaskState(transportTaskState)
                .putCreateDate(createDate)
                .putPlanBy(planBy)
                .putTransportTaskType("0").values();
        getContext().getContentResolver().bulkInsert(YunshuHeadColumns.CONTENT_URI, valuesHead);


        ContentValues[] valuesFrom = new ContentValues[tranFromList.size()];
        for(int i = 0; i < tranFromList.size(); i++){

            TranFromInfo fromInfo = tranFromList.get(i);
            valuesFrom[i] = new YunshuKuweiContentValues()
                    .putTransportTaskId(transportTaskId)
                    .putWarehouseNo(fromInfo.getLocationNo())
                    .putWarehouseName(fromInfo.getLocationName())
                    .putWarehouseType("0")
                    .values();
        }

        getContext().getContentResolver().bulkInsert(YunshuKuweiColumns.CONTENT_URI, valuesFrom);


        ContentValues[] valuesTo = new ContentValues[tranToList.size()];
        for(int i = 0; i < tranToList.size(); i++){
            TranToInfo toInfo = tranToList.get(i);
            valuesTo[i] = new YunshuKuweiContentValues()
                    .putTransportTaskId(transportTaskId)
                    .putWarehouseNo(toInfo.getLocationNo())
                    .putWarehouseName(toInfo.getLocationName())
                    .putWarehouseType("1")
                    .values();
        }
        getContext().getContentResolver().bulkInsert(YunshuKuweiColumns.CONTENT_URI, valuesTo);






    }


    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == 0x001){
                Intent intent = new Intent();
                intent.setAction("updateYunshuView");
                getActivity().sendBroadcast(intent);

                mTabLayout.setVisibility(View.VISIBLE);
                mViewPager.setVisibility(View.VISIBLE);



            }else if(msg.what == 0x002){
                Toast.makeText(getActivity(), "暂时不能新建货盘运输, 请稍后再试！！", Toast.LENGTH_SHORT).show();
            }

        }
    };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent = new Intent("com.android.scanservice.output.foreground");
        intent.putExtra("Scan_output_foreground", false);
        getActivity().sendBroadcast(intent);

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
        queryAndBindData();

    }


    private void loadPages() {
        pages.clear();
        pages.add(new Page(Page.TAB_HEAD, "表头信息"));
        pages.add(new Page(Page.TAB_LINE, "行信息"));

        mPagerAdapter.notifyDataSetChanged();
    }

    private void queryAndBindData() {
        if(isHasNotUploaded()){
            mTabLayout.setVisibility(View.VISIBLE);
            mViewPager.setVisibility(View.VISIBLE);
        }else {
            mTabLayout.setVisibility(View.INVISIBLE);
            mViewPager.setVisibility(View.INVISIBLE);
        }
    }



    private boolean checkItem(String taskId) {
        boolean isLineItemOK = true;
        boolean isHeadItemOK = true;

        String packNumber = "";
        String type = "";
        String from = "";
        String to = "";

        String materialId = "";
        String batchNo = "";
        String serialNo = "";
        String quantity = "";

        YunshuHeadSelection headSelection = new YunshuHeadSelection().transportTaskId(taskId);
        YunshuHeadCursor headCursor = headSelection.query(getContext().getContentResolver());
        try {
            while (headCursor.moveToNext()){
                packNumber = headCursor.getPackNumber();
                if(packNumber == null || packNumber.length() < 1){
                    Toast.makeText(getActivity(), "没有填写装箱单号！", Toast.LENGTH_SHORT).show();
                    isHeadItemOK = false;
                    return isHeadItemOK;
                }

                type = headCursor.getTransportTaskType();
                if(type == null || type.length() < 1){
                    Toast.makeText(getActivity(), "没有选择类型！", Toast.LENGTH_SHORT).show();
                    isHeadItemOK = false;
                    return isHeadItemOK;
                }

                from = headCursor.getSendWarehouseNo();
                if(from == null || from.length() < 1){
                    Toast.makeText(getActivity(), "没有选择发送库位！", Toast.LENGTH_SHORT).show();
                    isHeadItemOK = false;
                    return isHeadItemOK;
                }

                to = headCursor.getReceiveWarehouseNo();
                if(to == null || to.length() < 1){
                    Toast.makeText(getActivity(), "没有选择接收库位！", Toast.LENGTH_SHORT).show();
                    isHeadItemOK = false;
                    return isHeadItemOK;
                }
            }
        } finally {
            headCursor.close();
        }


        YunshuLineSelection lineSelection = new YunshuLineSelection().transportTaskId(taskId);
        YunshuLineCursor lineCursor = lineSelection.query(getContext().getContentResolver());
        try {
            while(lineCursor.moveToNext()){
                taskId = lineCursor.getTransportTaskId();
                if(taskId == null || taskId.length() < 1){
                    Toast.makeText(getActivity(), "没有货盘运输任务标识号！", Toast.LENGTH_SHORT).show();
                    isLineItemOK = false;
                    return isLineItemOK;
                }

                materialId = lineCursor.getPartNo();
                if(materialId == null || materialId.length() < 1){
                    Toast.makeText(getActivity(), "没有物资编码！", Toast.LENGTH_SHORT).show();
                    isLineItemOK = false;
                    return isLineItemOK;
                }

                batchNo = lineCursor.getLotBatchNo();
                if(batchNo == null || batchNo.length() < 1){
                    Toast.makeText(getActivity(), "没有批号！", Toast.LENGTH_SHORT).show();
                    isLineItemOK = false;
                    return isLineItemOK;
                }

                serialNo = lineCursor.getSerialNo();
                if(serialNo == null || serialNo.length() < 1){
                    Toast.makeText(getActivity(), "没有序列号！", Toast.LENGTH_SHORT).show();
                    isLineItemOK = false;
                    return isLineItemOK;
                }

                quantity = lineCursor.getQuantity();
                if(quantity == null || quantity.length() < 1){
                    Toast.makeText(getActivity(), "没有填写数量！", Toast.LENGTH_SHORT).show();
                    isLineItemOK = false;
                    return isLineItemOK;
                }

            }
        } finally {
            lineCursor.close();
        }

        return isLineItemOK && isHeadItemOK;
    }



    private void onUpdateMaterialRows(String taskId){

        String packNumber = "";
        String type = "";
        String from = "";
        String to = "";

        String materialId = "";
        String batchNo = "";
        String serialNo = "";
        String quantity = "";
        long lineId = 0l;

        progress = new ProgressDialog(getContext());
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIcon(R.drawable.ic_settings_white_24dp);
        progress.setTitle("上传货盘运输");
        progress.setMessage("请稍等...");
        progress.setIndeterminate(false);
        progress.setButton(DialogInterface.BUTTON_POSITIVE, "取消",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        progress.dismiss();
                    }

                });
        progress.show();



        HeadAndLineInfos headAndLineInfos = new HeadAndLineInfos();
        HeadAndLineInfo headAndLineInfo = new HeadAndLineInfo();
        TranUpdateHeadInfo headInfo = new TranUpdateHeadInfo();
        List<TranUpdateLineInfo> lineInfoList= new ArrayList<>();

        YunshuHeadSelection headSelection = new YunshuHeadSelection().transportTaskId(taskId);
        YunshuHeadCursor headCursor = headSelection.query(getContext().getContentResolver());
        try {
            while (headCursor.moveToNext()){
                packNumber = headCursor.getPackNumber();
                type = headCursor.getMaintenanceTypeId();
                from = headCursor.getSendWarehouseNo();
                to = headCursor.getReceiveWarehouseNo();
            }
        } finally {
            headCursor.close();
        }

        headInfo.setTaskId(taskId);
        headInfo.setPackNumber(packNumber);
        headInfo.setType(type);
        headInfo.setFrom(from);
        headInfo.setTo(to);


        YunshuLineSelection lineSelection = new YunshuLineSelection().transportTaskId(taskId).and().lineType("0");
        YunshuLineCursor lineCursor = lineSelection.query(getContext().getContentResolver());
        try {
            while(lineCursor.moveToNext()){
                materialId = lineCursor.getPartNo();
                batchNo = lineCursor.getLotBatchNo();
                serialNo = lineCursor.getSerialNo();
                quantity = lineCursor.getQuantity();
                lineId = lineCursor.getId();

                TranUpdateLineInfo lineInfo = new TranUpdateLineInfo();
                lineInfo.setMaterialId(materialId);
                lineInfo.setBatchNo(batchNo);
                lineInfo.setSerialNo(serialNo);
                lineInfo.setQuantity(quantity);
                lineInfo.setLineId(lineId);
                lineInfoList.add(lineInfo);
            }
        } finally {
            lineCursor.close();
        }

        headAndLineInfo.setTranHead(headInfo);
        headAndLineInfo.setLineInfoList(lineInfoList);
        if(lineInfoList.size() < 1){
            progress.dismiss();
            Toast.makeText(getActivity(), "没有未上传的物资行！", Toast.LENGTH_SHORT).show();
            return;
        }

        headAndLineInfos.setHeadAndLineInfo(headAndLineInfo);

        subscribe(deviceService.postUpdateAndGetLineNo(headAndLineInfos), resp -> {
            if (resp != null && resp.size() > 0) {
                try {
                    putLineNo2Data(resp);
                } catch (Exception e) {
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = 0x002;
                    getLineNoHandler.sendMessage(msg);
                    progress.dismiss();
                }

                Message msg = new Message();
                msg.what = 0x001;
                getLineNoHandler.sendMessage(msg);

                progress.dismiss();
            } else {
                Message msg = new Message();
                msg.what = 0x002;
                getLineNoHandler.sendMessage(msg);
                progress.dismiss();
            }
        }, e -> {
            LogUtil.info("postUpdateAndGetLineNos", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Fail!" + e);
            Message msg = new Message();
            msg.what = 0x002;
            getLineNoHandler.sendMessage(msg);
            progress.dismiss();
        });

    }

    private void putLineNo2Data(List<PartYunshuGetLineNoReturn> partYunshuGetLineNoReturnList) throws  Exception{
        for(PartYunshuGetLineNoReturn getLineNoReturn : partYunshuGetLineNoReturnList){
            String taskId = getLineNoReturn.getTaskId();

            updateSuccessTaskId = taskId;

            String materialId = getLineNoReturn.getMaterialId();
            String lineNo = getLineNoReturn.getLineNo();
            long lineId = getLineNoReturn.getLineId();

            new YunshuHeadContentValues()
                    .putTransportTaskType("1")
                    .update(getContext().getContentResolver(), new YunshuHeadSelection()
                            .transportTaskId(taskId));

            new YunshuLineContentValues()
                    .putLineNo(lineNo)
                    .putLineType("1")
                    .update(getContext().getContentResolver(), new YunshuLineSelection()
                            .transportTaskId(taskId)
                            .and()
                            .partNo(materialId)
                            .and()
                            .id(lineId));
        }
    }

    Handler getLineNoHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0x001){
                Toast.makeText(getActivity(), "物资行上传成功！", Toast.LENGTH_SHORT).show();
                mTabLayout.setVisibility(View.INVISIBLE);
                mViewPager.setVisibility(View.INVISIBLE);

                if(updateSuccessTaskId.length() > 0){
                    new YunshuHeadSelection().transportTaskId(updateSuccessTaskId).delete(getContext().getContentResolver());
                    new YunshuKuweiSelection().transportTaskId(updateSuccessTaskId).delete(getContext().getContentResolver());
                    new YunshuLineSelection().transportTaskId(updateSuccessTaskId).delete(getContext().getContentResolver());
                }

            }else if(msg.what == 0x002){
                Toast.makeText(getActivity(), "物资行上传失败, 请稍后再试！！", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private static class Page {
        public static final int TAB_HEAD = 0;
        public static final int TAB_LINE = 1;


        int type;
        String title;

        public Page(@NonNull int type, @NonNull String title) {
            this.type = type;
            this.title = title;
        }
    }

    class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter() {
            super(getChildFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
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
                    return PartYunShuHeadFragment.newInstance();
                case Page.TAB_LINE:
                    return PartYunShuLineFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return pages.size();
        }
    }
}
