package cc.xingyan.android.afc;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

import cc.xingyan.android.afc.adapter.LvAdapter;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.DeviceService;
import cc.xingyan.android.afc.api.model.GetTrAllInfo;
import cc.xingyan.android.afc.api.model.GetTrAllInfos;
import cc.xingyan.android.afc.api.model.PartYunshuGetAllInfoReturn;
import cc.xingyan.android.afc.api.model.PartYunshuGetHeadInfoReturn;
import cc.xingyan.android.afc.api.model.UploadedTranHeadInfo;
import cc.xingyan.android.afc.api.model.UploadedTranLineInfo;
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
import cc.xingyan.android.afc.provider.yunshuline.YunshuLineColumns;
import cc.xingyan.android.afc.provider.yunshuline.YunshuLineContentValues;
import cc.xingyan.android.afc.util.LogUtil;
import cc.xingyan.android.afc.util.NetUtil;
import cc.xingyan.android.afc.util.PullToRefreshListView;
import cc.xingyan.android.afc.util.TService;

/**
 * Created by YuJiadeng on 2017/12/20.
 *
 */
public class PartYunshuUploadedFragment extends BaseFragment {

    public static boolean isUploadedFragmentShow = true;
    private boolean isFirstShow = true;
    private List<PartYunshuGetHeadInfoReturn> listHeadInfoReturn;
    private List<PartYunshuGetAllInfoReturn> listAllInfoReturn;
    private LvAdapter adapter;

    private ProgressDialog progress;

    @Inject
    DeviceService deviceService;

    @Inject
    Authenticator mAuthenticator;

    @Bind(R.id.head_info_lv) PullToRefreshListView headInfoLv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.part__yunshu_uploaded, null);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent = new Intent("com.android.scanservice.output.foreground");
        intent.putExtra("Scan_output_foreground", false);
        getActivity().sendBroadcast(intent);


        headInfoLv.setonRefreshListener(new PullToRefreshListView.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            new AsyncTask<Void, Void, Void>() {
                                protected Void doInBackground(Void... params) {
                                    try {
                                        getTransportHeadInfoFromServer();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    return null;
                                }

                    @Override
                    protected void onPostExecute(Void result) {
                        if(adapter != null){
                            adapter.notifyDataSetChanged();
                        }
                        headInfoLv.onRefreshComplete();
                    }
                }.execute(null, null, null);
            }
        });


        headInfoLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String taskId = "";
                try {
                    taskId = listHeadInfoReturn.get(position - 1).getTaskId();
                }catch (Exception e){
                    e.printStackTrace();
                }


                String taskType = "2";
                YunshuHeadSelection headSelection = new YunshuHeadSelection().transportTaskId(taskId);
                YunshuHeadCursor headCursor = headSelection.query(getContext().getContentResolver());
                try {
                    while(headCursor.moveToNext()){
                        taskType = headCursor.getTransportTaskType();
                    }
                } finally {
                    headCursor.close();
                }

                if(taskType.equals("0")){
                    startActivity(new Intent(getActivity(), Main4YunshuActivity.class));
                }else if(taskType.equals("1")){

                    Intent intent = new Intent(getActivity(), Main4YunshuUploadedDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("transport_task_id", taskId);
                    intent.putExtras(bundle);
                    getActivity().startActivity(intent);

                }else{
                    getTransportAllInfo(taskId);
                }
            }
        });

}


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){

        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if(isFirstShow){
            isUploadedFragmentShow = true;
            getTransportHeadInfo();
            isFirstShow = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    void getTransportHeadInfo(){
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

                        progress.dismiss();
                    }

                });
        progress.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                getTransportHeadInfoFromServer();
            }
        }).start();


    }

    void getTransportAllInfo(String taskId){
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
                        progress.dismiss();
                    }

                });
        progress.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                getTransportAllInfoFromServer(taskId);
            }
        }).start();
    }

    private void getTransportHeadInfoFromServer(){

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

        listHeadInfoReturn = new ArrayList<PartYunshuGetHeadInfoReturn>();
        deviceService.postGetTransportHeadInfo(userInfos).subscribe(resp -> {

            if (resp != null && resp.size() > 0) {
                try {
                    for (PartYunshuGetHeadInfoReturn gHeadInfo : resp) {
                        listHeadInfoReturn.add(gHeadInfo);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = 0x002;

                    myHandler.sendMessage(msg);

                }
                Message msg = new Message();
                msg.what = 0x001;

                myHandler.sendMessage(msg);

            } else {
                Message msg = new Message();
                msg.what = 0x003;
                myHandler.sendMessage(msg);
            }
        }, e -> {
            LogUtil.info("GetTransportHeadInfo", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Fail!" + e);
            Message msg = new Message();
            msg.what = 0x004;
            myHandler.sendMessage(msg);
        });

    }



    private void getTransportAllInfoFromServer(String taskId){
        GetTrAllInfos getTrAllInfos = new GetTrAllInfos();
        List<GetTrAllInfo> getTrAllInfoList = new ArrayList<GetTrAllInfo>();
        GetTrAllInfo getTrAllInfo = new GetTrAllInfo();


        String user = mAuthenticator.getAuthenticatedUserId();
        String imei = TService.imei;
        String lat = TService.lat;
        String lon = TService.lon;

        getTrAllInfo.setTaskId(taskId);
        getTrAllInfo.setUserId(user);
        getTrAllInfo.setUserIMEI(NetUtil.getString(imei));
        getTrAllInfo.setUserLat(NetUtil.getString(lat));
        getTrAllInfo.setUserLon(NetUtil.getString(lon));

        getTrAllInfoList.add(getTrAllInfo);
        getTrAllInfos.setGetTrAllInfos(getTrAllInfoList);


        listAllInfoReturn = new ArrayList<PartYunshuGetAllInfoReturn>();
        deviceService.postGetTransportAllInfo(getTrAllInfos).subscribe(resp -> {

            if (resp != null && resp.size() > 0) {
                try {
                    for (PartYunshuGetAllInfoReturn gAllInfo : resp) {
                        listAllInfoReturn.add(gAllInfo);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = 0x002;

                    myAllInfoHandler.sendMessage(msg);

                }
                Message msg = new Message();
                msg.what = 0x001;

                myAllInfoHandler.sendMessage(msg);
            } else {
                Message msg = new Message();
                msg.what = 0x003;
                myAllInfoHandler.sendMessage(msg);
            }
        }, e -> {
            LogUtil.info("GetTransportHeadInfo", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Fail!" + e);
            Message msg = new Message();
            msg.what = 0x004;
            myAllInfoHandler.sendMessage(msg);
        });


    }

    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == 0x001){
                if(listHeadInfoReturn != null){
                    adapter = new LvAdapter(listHeadInfoReturn, getActivity());
                    headInfoLv.setAdapter(adapter);
                    progress.dismiss();

                }

            }else if(msg.what == 0x002){
                adapter = new LvAdapter(listHeadInfoReturn, getActivity());
                headInfoLv.setAdapter(adapter);
                progress.dismiss();
                Toast.makeText(getActivity(), "暂时不能获取数据, 请稍后再试！", Toast.LENGTH_SHORT).show();
            }else if(msg.what == 0x003){
                adapter = new LvAdapter(listHeadInfoReturn, getActivity());
                headInfoLv.setAdapter(adapter);
                progress.dismiss();
                Toast.makeText(getActivity(), "未查找到货盘运输工单！！", Toast.LENGTH_SHORT).show();
            }else if(msg.what == 0x004){
                adapter = new LvAdapter(listHeadInfoReturn, getActivity());
                headInfoLv.setAdapter(adapter);
                progress.dismiss();
                Toast.makeText(getActivity(), "暂时不能获取数据, 请稍后再试！！", Toast.LENGTH_SHORT).show();
            }

        }
    };

    Handler myAllInfoHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0x001){

                UploadedTranHeadInfo headInfo =  listAllInfoReturn.get(0).getHeadInfo();
                List<UploadedTranLineInfo> lineInfoList =  listAllInfoReturn.get(0).getLineInfoList();

                String maintenanceTypeId = "";
                if(headInfo.getType().equals("故障修")){
                    maintenanceTypeId = "0";
                }else if(headInfo.getType().equals("计划修")){
                    maintenanceTypeId = "1";
                }else if(headInfo.getType().equals("<Null>")){
                    maintenanceTypeId = "2";
                }

                ContentValues[] valuesHead = new ContentValues[1];
                valuesHead[0] = new YunshuHeadContentValues()
                        .putTransportTaskId(headInfo.getTaskId())
                        .putTransportTaskState(headInfo.getState())
                        .putCreateDate(headInfo.getCreateDate().getTime())
                        .putMaintenanceTypeId(maintenanceTypeId)
                        .putPlanBy(headInfo.getPlanBy())
                        .putPackNumber(headInfo.getPackNumber())
                        .putSendWarehouseNo(headInfo.getFromLocationNo())
                        .putReceiveWarehouseNo(headInfo.getToLocationNo())
                        .putTransportTaskType("1").values();
                getContext().getContentResolver().bulkInsert(YunshuHeadColumns.CONTENT_URI, valuesHead);

                ContentValues[] valuesFrom = new ContentValues[1];
                valuesFrom[0] = new YunshuKuweiContentValues()
                        .putTransportTaskId(headInfo.getTaskId())
                        .putWarehouseNo(headInfo.getFromLocationNo())
                        .putWarehouseName(headInfo.getFromLocationName())
                        .putWarehouseType("0").values();
                getContext().getContentResolver().bulkInsert(YunshuKuweiColumns.CONTENT_URI, valuesFrom);

                ContentValues[] valuesTo = new ContentValues[1];
                valuesTo[0] = new YunshuKuweiContentValues()
                        .putTransportTaskId(headInfo.getTaskId())
                        .putWarehouseNo(headInfo.getToLocationNo())
                        .putWarehouseName(headInfo.getToLocationName())
                        .putWarehouseType("1").values();
                getContext().getContentResolver().bulkInsert(YunshuKuweiColumns.CONTENT_URI, valuesTo);

                for(UploadedTranLineInfo lineInfo : lineInfoList){
                    ContentValues[] valuesLine = new ContentValues[1];
                    valuesLine[0] = new YunshuLineContentValues()
                            .putTransportTaskId(lineInfo.getTaskId())
                            .putLineNo(lineInfo.getLineNo())
                            .putPartNo(lineInfo.getMaterialId())
                            .putPartName(lineInfo.getMaterialName())
                            .putQuantity(lineInfo.getQuantity())
                            .putUnit(lineInfo.getMaterialUnit())
                            .putLotBatchNo(lineInfo.getBatchNo())
                            .putSerialNo(lineInfo.getSerialNo())
                            .putFormat(lineInfo.getMaterialType())
                            .putLineType("1").values();
                    getContext().getContentResolver().bulkInsert(YunshuLineColumns.CONTENT_URI, valuesLine);
                }

                progress.dismiss();

                Intent intent = new Intent(getActivity(), Main4YunshuUploadedDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("transport_task_id", headInfo.getTaskId());
                intent.putExtras(bundle);
                getActivity().startActivity(intent);

            }else if(msg.what == 0x002){
                progress.dismiss();
                Toast.makeText(getActivity(), "暂时不能获取数据, 请稍后再试！", Toast.LENGTH_SHORT).show();
            }else if(msg.what == 0x003){
                progress.dismiss();
                Toast.makeText(getActivity(), "未查找到货盘运输工单！！", Toast.LENGTH_SHORT).show();
            }else if(msg.what == 0x004){
                progress.dismiss();
                Toast.makeText(getActivity(), "暂时不能获取数据, 请稍后再试！！", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
