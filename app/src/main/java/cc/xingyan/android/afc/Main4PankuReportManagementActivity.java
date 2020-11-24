package cc.xingyan.android.afc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.DeviceService;
import cc.xingyan.android.afc.api.model.PRManualDeleteLists;
import cc.xingyan.android.afc.api.model.PRWorkManualDelete;
import cc.xingyan.android.afc.api.model.PanKuReportLists;
import cc.xingyan.android.afc.api.model.ParamMaterialPanKuReport;
import cc.xingyan.android.afc.api.model.ParamMaterialPanKuReportReturn;
import cc.xingyan.android.afc.api.model.ParamMaterialPanKuReportUpload;
import cc.xingyan.android.afc.app.BaseActivity;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.pankureport.PankuReportColumns;
import cc.xingyan.android.afc.provider.pankureport.PankuReportContentValues;
import cc.xingyan.android.afc.provider.pankureport.PankuReportCursor;
import cc.xingyan.android.afc.provider.pankureport.PankuReportSelection;
import cc.xingyan.android.afc.util.LogUtil;
import cc.xingyan.android.afc.util.NetUtil;
import cc.xingyan.android.afc.util.TService;

/**
 * Created by YuJiadeng on 2017/4/24.
 */
public class Main4PankuReportManagementActivity extends BaseActivity {

    @Inject
    DeviceService deviceService;

    @Inject
    Authenticator mAuthenticator;

    static ProgressDialog progressSwitch;
    boolean isNeedDownloadPankuReport = true;



    @Bind(R.id.relativeLayout_report_managerment) RelativeLayout relativeLayout_report_managerment;
    @Bind(R.id.part_inv_list_no_panku_report_managerment) TextView part_inv_list_no;
    @Bind(R.id.listview_panku_report_managerment) ListView kuweiListInPankuReport;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);


        IntentFilter iFilter_ = new IntentFilter();
        iFilter_.addAction("delPR");
        registerReceiver(receiver_, iFilter_);

        setContentView(R.layout.activity_panku_report_managerment);

    }


    private BroadcastReceiver receiver_ = new BroadcastReceiver() {
        public void onReceive(Context context,
                              Intent intent) {
            upLoadReportSuccess();

        }


    };

    @Override
    protected void onResume() {
        super.onResume();
        String reportNo = "";
        String user = mAuthenticator.getAuthenticatedUserId();
        PankuReportSelection pankuReportSelection = new PankuReportSelection().userId(user);
        PankuReportCursor pankuReportCursor = pankuReportSelection.query(getContentResolver());

        try {
            while (pankuReportCursor.moveToNext()) {
                reportNo = pankuReportCursor.getReportNo();
                if(reportNo.length() > 0){
                    isNeedDownloadPankuReport = false;
                    break;
                }
            }
        } finally {
            pankuReportCursor.close();
        }

        if (isNeedDownloadPankuReport){
            ProgressDialog progressDownload = initProgressDialog("获取盘点报告");
            progressSwitch = progressDownload;
            progressSwitch.show();
            downloadPankuReport();
            isNeedDownloadPankuReport = false;
        }else {
            try {
                showReportView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        kuweiListInPankuReport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    if (warehouseList != null) {
                        String warehouseNo = warehouseList.get(position).getWarehouseNo();


                        SharedPreferences selectWarehousePreferences = getSharedPreferences("PankuReportManagementSelectWarehouse", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor selectWarehousePf = selectWarehousePreferences.edit();
                        selectWarehousePf.putBoolean("isPankuReportManagementSelectWarehouse", true);
                        selectWarehousePf.putString("warehouseNo", warehouseNo);
                        selectWarehousePf.commit();
                    }


                    Intent intent = new Intent(Main4PankuReportManagementActivity.this, Main4PartActivity.class);
                    intent.putExtra("isFromPankuReportManagement", true);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    new AlertDialog.Builder(Main4PankuReportManagementActivity.this)
                            .setIcon(R.drawable.ic_info_black_24dp)
                            .setTitle("温馨提示")
                            .setMessage("盘点报告异常，请删除后重新下载或者确认后台是否已取消！")
                            .setPositiveButton(R.string.ok, null)
                            .setCancelable(false)
                            .show();
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver_);

        SharedPreferences selectWarehousePreferences = getSharedPreferences("PankuReportManagementSelectWarehouse", Activity.MODE_PRIVATE);
        SharedPreferences.Editor selectWarehousePf = selectWarehousePreferences.edit();
        selectWarehousePf.clear();
        selectWarehousePf.commit();
    }


    @OnClick(R.id.download_panku_report) void onClickDownloadPankuReport() {


        if(isNeedDownloadPankuReport){
            ProgressDialog progressDownload = initProgressDialog("获取盘点报告");
            progressSwitch = progressDownload;
            progressSwitch.show();
            downloadPankuReport();
        }else{
            new AlertDialog.Builder(Main4PankuReportManagementActivity.this)
                    .setIcon(R.drawable.ic_info_black_24dp)
                    .setTitle("温馨提示")
                    .setMessage("有尚未上传的盘点报告！")
                    .setPositiveButton(R.string.ok, null)
                    .setCancelable(false)
                    .show();
            return;

        }


    }



    @OnClick(R.id.upload_panku_report) void onClickUploadPankuReport() {


        if(isNeedDownloadPankuReport){
            new AlertDialog.Builder(Main4PankuReportManagementActivity.this)
                    .setIcon(R.drawable.ic_info_black_24dp)
                    .setTitle("温馨提示")
                    .setMessage("没有可上传的盘点报告，请确认！")
                    .setPositiveButton(R.string.ok, null)
                    .setCancelable(false)
                    .show();
        }else {


            String user = mAuthenticator.getAuthenticatedUserId();
            PankuReportSelection pankuReportSelection = new PankuReportSelection().pandianMark("0").and().userId(user);
            int weipancount = pankuReportSelection.query(getContentResolver()).getCount();
            if(weipancount > 0){
                new AlertDialog.Builder(Main4PankuReportManagementActivity.this)
                        .setIcon(R.drawable.ic_info_black_24dp)
                        .setTitle("温馨提示")
                        .setMessage("还有尚未盘点的物资！")
                        .setPositiveButton(R.string.ok, null)
                        .setCancelable(false)
                        .show();
                return;
            }

            ProgressDialog progressUpload = initProgressDialog("上传盘点报告");
            progressSwitch = progressUpload;
            progressSwitch.show();
            uploadPankuReport();
        }
    }



    @OnClick(R.id.delete_panku_report) void onClickDeletePankuReport() {
        new AlertDialog.Builder(Main4PankuReportManagementActivity.this)
                .setIcon(R.drawable.ic_info_black_24dp)
                .setTitle("温馨提示")
                .setMessage("您将删除本账户下的全部盘点报告！")
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ProgressDialog progressUpload = initProgressDialog("正在删除...");
                        progressSwitch = progressUpload;
                        progressSwitch.show();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String user = mAuthenticator.getAuthenticatedUserId();
                                String imei = TService.imei;
                                String lat = TService.lat;
                                String lon = TService.lon;

                               String reportNo = "";

                                List<String> userSEQ = new ArrayList<String>();


                                PankuReportSelection pankuReportSelection = new PankuReportSelection().userId(user);
                                PankuReportCursor pankuReportCursor = pankuReportSelection.query(getContentResolver());

                                try {
                                    while (pankuReportCursor.moveToNext()) {
                                        String  seqStr = pankuReportCursor.getLineNo();
                                        reportNo = pankuReportCursor.getReportNo();
                                        userSEQ.add(seqStr);
                                    }
                                } finally {
                                    pankuReportCursor.close();
                                }


                                PRWorkManualDelete prWorkManualDelete = new PRWorkManualDelete();

                                prWorkManualDelete.setUserid(user);
                                prWorkManualDelete.setUserIMEI(imei);
                                prWorkManualDelete.setUserLat(lat);
                                prWorkManualDelete.setUserLon(lon);
                                prWorkManualDelete.setReportNo(reportNo);
                                prWorkManualDelete.setUserSEQ(userSEQ);

                                List<PRWorkManualDelete> prWorkManualDeletes = new ArrayList<PRWorkManualDelete>();
                                prWorkManualDeletes.add(prWorkManualDelete);


                                PRManualDeleteLists prManualDeleteLists = new PRManualDeleteLists();
                                prManualDeleteLists.setPrWorkManualDeletes(prWorkManualDeletes);

                                deviceService.postManualDeletePR(prManualDeleteLists).subscribe(resp -> {
                                    if (resp != null && resp.size() > 0) {
                                        Message msg = new Message();
                                        msg.what = 567;
                                        myHandler.sendMessage(msg);
                                    }
                                }, e -> {
                                    LogUtil.info("ManualDeletePR", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Fail!" + e);
                                    Message msg = new Message();
                                    msg.what = 678;
                                    myHandler.sendMessage(msg);

                                });
                            }
                        }).start();
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .setCancelable(false)
                .show();

    }


    private ProgressDialog initProgressDialog(String title){
        ProgressDialog progress = new ProgressDialog(Main4PankuReportManagementActivity.this);
        progress.setCancelable(true);
        progress.setCanceledOnTouchOutside(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIcon(R.drawable.ic_settings_white_24dp);
        progress.setTitle(title);
        progress.setMessage("请稍后...");
        progress.setIndeterminate(false);




        return progress;
    }

    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 123){
                try {
                    showReportView();
                    isNeedDownloadPankuReport = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressSwitch.dismiss();
            }else if(msg.what == 122){
                Toast.makeText(Main4PankuReportManagementActivity.this, "没有盘点报告，请确认!", Toast.LENGTH_SHORT).show();
                isNeedDownloadPankuReport = true;
                progressSwitch.dismiss();
            }else if(msg.what == 234){
                Toast.makeText(Main4PankuReportManagementActivity.this, "下载盘点报告失败，请稍后重试!", Toast.LENGTH_SHORT).show();
                isNeedDownloadPankuReport = true;
                progressSwitch.dismiss();
            }else if(msg.what == 345){
                upLoadReportSuccess();

                progressSwitch.dismiss();
            }else if(msg.what == 456){
                Toast.makeText(Main4PankuReportManagementActivity.this, "上传盘点报告失败，请稍后重试!", Toast.LENGTH_SHORT).show();
                progressSwitch.dismiss();
            }else if(msg.what == 567){
                upLoadReportSuccess();
                Toast.makeText(Main4PankuReportManagementActivity.this, "盘点报告已删除!", Toast.LENGTH_SHORT).show();
                progressSwitch.dismiss();
            }else if(msg.what == 678){
                Toast.makeText(Main4PankuReportManagementActivity.this, "删除盘点报告失败，请稍后重试!", Toast.LENGTH_SHORT).show();
                progressSwitch.dismiss();
            }
        }
    };



    List<Warehouse> warehouseList;
    private void showReportView() throws Exception{
        String reportNo = "";
        String user = mAuthenticator.getAuthenticatedUserId();
        PankuReportSelection pankuReportSelection = new PankuReportSelection().userId(user);
        PankuReportCursor pankuReportCursor = pankuReportSelection.query(getContentResolver());

        try {
            while (pankuReportCursor.moveToNext()) {
                reportNo = pankuReportCursor.getReportNo();
                if(reportNo.length() > 0){
                    part_inv_list_no.setText(reportNo);
                    relativeLayout_report_managerment.setVisibility(View.VISIBLE);
                    break;
                }
            }
        } finally {
            pankuReportCursor.close();
        }



        warehouseList = new ArrayList<>();
        PankuReportSelection pankuReportSelection2 = new PankuReportSelection().userId(user);
        PankuReportCursor pankuReportCursor2 = pankuReportSelection2.query(getContentResolver());
        try {
            while (pankuReportCursor2.moveToNext()) {
                Warehouse warehouse = new Warehouse();
                warehouse.setWarehouseNo(pankuReportCursor2.getWarehouseNo());
                warehouse.setWarehouseName(pankuReportCursor2.getWarehouseName());
                if(!warehouseList.contains(warehouse)){
                    warehouseList.add(warehouse);
                }

            }
        } finally {
            pankuReportCursor2.close();
        }

        if(warehouseList.size() > 0){
            Map<String, Integer> allPartsCountMap = new HashMap<>();
            Map<String, Integer> weipanCountMap = new HashMap<>();
            Map<String, Integer> yipanCountMap = new HashMap<>();
            for(int i = 0; i < warehouseList.size(); i++){

                int allPartsCount = 0;
                int weipanCount = 0;
                int yipanCount = 0;

                String warehouseNo = warehouseList.get(i).getWarehouseNo();
                PankuReportSelection pankuReportSelection4Map = new PankuReportSelection().warehouseNo(warehouseNo).and().userId(user);
                PankuReportCursor pankuReportCursor4Map = pankuReportSelection4Map.query(getContentResolver());
                try {
                    while (pankuReportCursor4Map.moveToNext()) {
                        allPartsCount++;
                        if(pankuReportCursor4Map.getPandianMark().equals("0")){
                            weipanCount++;
                        }else if(pankuReportCursor4Map.getPandianMark().equals("1")){
                            yipanCount++;
                        }
                    }
                    allPartsCountMap.put(warehouseNo, allPartsCount);
                    weipanCountMap.put(warehouseNo, weipanCount);
                    yipanCountMap.put(warehouseNo, yipanCount);

                    PankuReportManagermentAdapter pankuReportManagermentAdapter = new PankuReportManagermentAdapter(Main4PankuReportManagementActivity.this, warehouseList,
                                                                                    allPartsCountMap, weipanCountMap, yipanCountMap);

                    kuweiListInPankuReport.setAdapter(pankuReportManagermentAdapter);
                } finally {
                    pankuReportCursor4Map.close();
                }
            }

        }


    }




    private void upLoadReportSuccess(){


        String user = mAuthenticator.getAuthenticatedUserId();
        new PankuReportSelection().userId(user).delete(getContentResolver());
        isNeedDownloadPankuReport = true;
        relativeLayout_report_managerment.setVisibility(View.INVISIBLE);

        List<Warehouse> warehouseList = new ArrayList<>();
        Map<String, Integer> allPartsCountMap = new HashMap<>();
        Map<String, Integer> weipanCountMap = new HashMap<>();
        Map<String, Integer> yipanCountMap = new HashMap<>();

        PankuReportManagermentAdapter pankuReportManagermentAdapter =
                new PankuReportManagermentAdapter(Main4PankuReportManagementActivity.this, warehouseList,
                        allPartsCountMap, weipanCountMap, yipanCountMap);
        kuweiListInPankuReport.setAdapter(pankuReportManagermentAdapter);
    }



    private void downloadPankuReport(){
        String user = mAuthenticator.getAuthenticatedUserId();
        String imei = TService.imei;
        String lat = TService.lat;
        String lon = TService.lon;
        new Thread(new Runnable() {
            @Override
            public void run() {

                deviceService.listMaterialsPanKuReport(user, NetUtil.getString(imei), NetUtil.getString(lat), NetUtil.getString(lon)).subscribe(resp -> {

                    if(resp != null && resp.size() > 0){

                        List<ParamMaterialPanKuReport> pankuReportList = (List<ParamMaterialPanKuReport>) resp;



                        final ContentValues[] values = new ContentValues[resp.size()];
                        for(int i = 0; i < resp.size(); i++){
                            ParamMaterialPanKuReport pankuReportItem = resp.get(i);
                            values[i] = new PankuReportContentValues()
                                    .putUserId(user)
                                    .putReportNo(pankuReportItem.getReportNo() + "")
                                    .putPartNo(pankuReportItem.getPartNo() + "")
                                    .putPartName(pankuReportItem.getPartName() + "")
                                    .putActualAmount(pankuReportItem.getActualAmount() + "")
                                    .putLotbatchNo(pankuReportItem.getLotBatchNo() + "")
                                    .putLineNo(pankuReportItem.getLineNo() + "")
                                    .putPartSeq(pankuReportItem.getPartSeq() + "")
                                    .putWarehouseNo(pankuReportItem.getWarehouseNo() + "")
                                    .putWarehouseName(pankuReportItem.getWarehouseName() + "")
                                    .putPandianMark(pankuReportItem.getPandianMark())
                                    .putPandianTime(pankuReportItem.getPandianTime())
                                    .values();
                        }


                        getApplicationContext().getContentResolver().bulkInsert(PankuReportColumns.CONTENT_URI, values);

                        Message msg = new Message();
                        msg.what = 123;
                        myHandler.sendMessage(msg);
                    }else if(resp != null && resp.size() == 0){
                        Message msg = new Message();
                        msg.what = 122;
                        myHandler.sendMessage(msg);
                    }else if(resp == null){
                        Message msg = new Message();
                        msg.what = 234;
                        myHandler.sendMessage(msg);
                    }
                }, e -> {
                    Message msg = new Message();
                    msg.what = 234;
                    myHandler.sendMessage(msg);
                    LogUtil.info("GetMaterialsPanKuReport", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Fail!" + e);

                });
            }
        }).start();
    }



    String upLoadReportNo;
    private void uploadPankuReport(){

        String user = mAuthenticator.getAuthenticatedUserId();
        String imei = TService.imei;
        String lat = TService.lat;
        String lon = TService.lon;


        PanKuReportLists panKuReportLists = new PanKuReportLists();
        List<ParamMaterialPanKuReportUpload>  panKuReportList = new ArrayList<>();
        PankuReportSelection pankuReportSelection = new PankuReportSelection().userId(user);
        PankuReportCursor pankuReportCursor = pankuReportSelection.query(getContentResolver());


        try {
            while (pankuReportCursor.moveToNext()) {
                ParamMaterialPanKuReportUpload panKuReportUpload = new ParamMaterialPanKuReportUpload();
                upLoadReportNo = pankuReportCursor.getReportNo();
                panKuReportUpload.setReportNo(upLoadReportNo);
                panKuReportUpload.setLineNo(pankuReportCursor.getLineNo());
                panKuReportUpload.setActualAmount(pankuReportCursor.getActualAmount());
                panKuReportUpload.setPandianTime(pankuReportCursor.getPandianTime());

                panKuReportUpload.setUserId(user);
                panKuReportUpload.setIMEI(NetUtil.getString(imei));
                panKuReportUpload.setLat(NetUtil.getString(lat));
                panKuReportUpload.setLon(NetUtil.getString(lon));

                panKuReportList.add(panKuReportUpload);
            }

            panKuReportLists.setPanKuReportList(panKuReportList);
        } finally {
            pankuReportCursor.close();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                deviceService.panKuReportListWorks(panKuReportLists).subscribe(resp -> {
                    if (resp != null && resp.size() > 0) {

                        ParamMaterialPanKuReportReturn panKuReportReturn = new ParamMaterialPanKuReportReturn();
                        if (upLoadReportNo.equals(resp.get(0).getReportNo())) {
                            String uploadSatae = resp.get(0).getUploadSatae();
                            if (uploadSatae.equals("1")) {
                                Message msg = new Message();
                                msg.what = 345;
                                myHandler.sendMessage(msg);
                            } else {
                                Message msg = new Message();
                                msg.what = 456;
                                myHandler.sendMessage(msg);
                            }
                        } else {
                            Message msg = new Message();
                            msg.what = 456;
                            myHandler.sendMessage(msg);
                        }


                    } else if (resp != null && resp.size() == 0) {
                        Message msg = new Message();
                        msg.what = 456;
                        myHandler.sendMessage(msg);
                    } else if (resp == null) {
                        Message msg = new Message();
                        msg.what = 456;
                        myHandler.sendMessage(msg);
                    }
                }, e -> {
                    Message msg = new Message();
                    msg.what = 456;
                    myHandler.sendMessage(msg);
                    LogUtil.info("UploadMaterialsPanKuReport", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Fail!" + e);

                });
            }
        }).start();


    }



    private class Warehouse{
        String warehouseNo;
        String warehouseName;

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        public String getWarehouseNo() {
            return warehouseNo;
        }

        public void setWarehouseNo(String warehouseNo) {
            this.warehouseNo = warehouseNo;
        }

        @Override
        public boolean equals(Object o) {

            Warehouse warehouse = (Warehouse)o;

            return warehouseNo.equals(warehouse.warehouseNo);
        }
    }

    class PankuReportManagermentAdapter extends BaseAdapter{

        Context context;
        List<Warehouse> warehouseList;
        LayoutInflater inflater;
        Map<String, Integer> allPartsCountMap;
        Map<String, Integer> weipanCountMap;
        Map<String, Integer> yipanCountMap;

        public PankuReportManagermentAdapter( Context context, List<Warehouse> warehouseList, Map<String, Integer> allPartsCountMap,
                                              Map<String, Integer> weipanCountMap, Map<String, Integer> yipanCountMap) {
            this.context = context;
            this.warehouseList = warehouseList;
            this.allPartsCountMap = allPartsCountMap;
            this.weipanCountMap = weipanCountMap;
            this.yipanCountMap = yipanCountMap;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return warehouseList.size();
        }

        @Override
        public Object getItem(int position) {
            return warehouseList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            WarehouseViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.part_panku_report_management_item, parent, false);
                holder = new WarehouseViewHolder();
                holder.warehouseNameTV = (TextView) convertView .findViewById(R.id.warehouse_name);
                holder.allPartsCountTV = (TextView) convertView .findViewById(R.id.all_parts_count);
                holder.weipanCountTV = (TextView) convertView .findViewById(R.id.weipan_count);
                holder.yipanCountMapTV = (TextView) convertView .findViewById(R.id.yipan_count);
                convertView.setTag(holder);
            } else {
                holder = (WarehouseViewHolder) convertView.getTag();
            }

            String warehouseNo = warehouseList.get(position).getWarehouseNo();
            String warehouseName = warehouseList.get(position).getWarehouseName();

            holder.warehouseNameTV.setText(warehouseName);
            holder.allPartsCountTV.setText(allPartsCountMap.get(warehouseNo) + "");
            holder.weipanCountTV.setText(weipanCountMap.get(warehouseNo) + "");
            holder.yipanCountMapTV.setText(yipanCountMap.get(warehouseNo) + "");

            return convertView;
        }
    }

    public class WarehouseViewHolder {
        public TextView warehouseNameTV;
        public TextView allPartsCountTV;
        public TextView weipanCountTV;
        public TextView yipanCountMapTV;
    }
}
