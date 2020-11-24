package cc.xingyan.android.afc;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.Bind;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.DeviceService;
import cc.xingyan.android.afc.api.model.GoodsInfo;
import cc.xingyan.android.afc.api.model.GoodsInfos;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.yunshuhead.YunshuHeadContentValues;
import cc.xingyan.android.afc.provider.yunshuhead.YunshuHeadCursor;
import cc.xingyan.android.afc.provider.yunshuhead.YunshuHeadSelection;
import cc.xingyan.android.afc.provider.yunshuline.YunshuLineContentValues;
import cc.xingyan.android.afc.provider.yunshuline.YunshuLineCursor;
import cc.xingyan.android.afc.provider.yunshuline.YunshuLineSelection;
import cc.xingyan.android.afc.util.LogUtil;
import cc.xingyan.android.afc.util.NetUtil;
import cc.xingyan.android.afc.util.TService;

public class PartYunShuUploadedLineFragment extends BaseFragment {
    private static final String TRANSPORT_TASK_ID_UPLOADED = "transport_task_id_uploaded";

    public static boolean isYunShuLineUploadedFragmentVisible = false;

    private boolean isCurrentShowDialog = false;
    private GoodsAllInfo goodsAllInfo;

    private ProgressDialog progress;

    private String tranSportTaskID;
    private String SCAN_BACK_KEY = "Scan_context";

    @Inject
    Authenticator mAuthenticator;

    @Inject
    DeviceService deviceService;

    @Bind(R.id.transport_line_container_uploaded) LinearLayout transportLine_Container;

    @Bind(R.id.switch1) Switch addSwitch;


    public static Fragment newInstance(String tranSportTaskID) {
        PartYunShuUploadedLineFragment fragment = new PartYunShuUploadedLineFragment();
        Bundle args = new Bundle();
        args.putString(TRANSPORT_TASK_ID_UPLOADED, tranSportTaskID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);

        tranSportTaskID = getArguments().getString(TRANSPORT_TASK_ID_UPLOADED);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.part__yunshu_line_uploaded, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(NetUtil.RECE_DATA_ACTION);
        getActivity().registerReceiver(receiver, iFilter);

        bindAndShowData();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {



        }
    }

    @Override
    public void onResume() {
        super.onResume();
        addSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String taskType = "2";
                boolean isHasPartYunshuNoUpload = false;
                YunshuHeadSelection headSelection = new YunshuHeadSelection();
                YunshuHeadCursor headCursor = headSelection.query(getContext().getContentResolver());
                try {
                    while(headCursor.moveToNext()){
                        taskType = headCursor.getTransportTaskType();
                        if(taskType.equals("0")){
                            isHasPartYunshuNoUpload = true;
                            break;
                        }
                    }
                } finally {
                    headCursor.close();
                }

                if (isChecked) {
                    if(isHasPartYunshuNoUpload){
                        Toast.makeText(getContext(), "已经有未上传的货盘运输，请先上传完毕再添加！", Toast.LENGTH_SHORT).show();
                        addSwitch.setChecked(false);
                        return;
                    }
                    isYunShuLineUploadedFragmentVisible = true;
                } else {
                    isYunShuLineUploadedFragmentVisible = false;
                }
            }
        });

        PartPanKuFragment.isPankuFragmentVisible = false;
        LogUtil.info("PartYunShuLineFragment_Scan", "isYunShuLineUploadedFragmentVisible>> " + isYunShuLineUploadedFragmentVisible);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);

    }

    private void bindAndShowData(){
        YunshuLineSelection lineSelection = new YunshuLineSelection().transportTaskId(tranSportTaskID);
        YunshuLineCursor lineCursor = lineSelection.query(getContext().getContentResolver());
        try {
            while (lineCursor.moveToNext()){
                addMaterialRow(lineCursor.getId());
            }
        } finally {
            lineCursor.close();
        }
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(NetUtil.RECE_DATA_ACTION)) {
                String barcode = "";
                barcode = intent.getStringExtra(SCAN_BACK_KEY);


                if(isYunShuLineUploadedFragmentVisible){
                    if(barcode.contains("#")){
                        String[] strsInfo = barcode.split("#");
                        if(strsInfo[1].equals("0")){
                            String partNo = strsInfo[2].trim();
                            String partPihao = strsInfo[3].trim();
                            String partXuhao = strsInfo[4].trim();
                            String partContract = strsInfo[5].trim();

                            goodsAllInfo = new GoodsAllInfo();

                            goodsAllInfo.setPartId(partNo);
                            goodsAllInfo.setPartBatchNo(partPihao);
                            goodsAllInfo.setPartSerialNo(partXuhao);


                            try {
                                if(!isCurrentShowDialog){
                                    String locationNo = "";
                                    YunshuHeadSelection headSelection = new YunshuHeadSelection().transportTaskId(tranSportTaskID);
                                    YunshuHeadCursor headCursor = headSelection.query(getContext().getContentResolver());
                                    try {
                                        while(headCursor.moveToNext()){
                                            locationNo = headCursor.getSendWarehouseNo();
                                        }
                                    } finally {
                                        headCursor.close();
                                    }

                                    getTransportGoodsInfo(partContract, partPihao, locationNo);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }else if(strsInfo[1].equals("1")){
                            Toast.makeText(getActivity(), "运输—这是物资大码，请扫物资小码！", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        if(barcode.contains(":")){
                            Toast.makeText(getActivity(), "运输—这是设备码，请扫物资小码！", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "不合法的编码！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    };



    private void getTransportGoodsInfo(String fromContract, String batchNo, String locationNo) throws Exception{

        String materialId = goodsAllInfo.getPartId();

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


        String user = mAuthenticator.getAuthenticatedUserId();
        String imei = TService.imei;
        String lat = TService.lat;
        String lon = TService.lon;

        GoodsInfos goodsInfos = new GoodsInfos();
        GoodsInfo goodsInfo = new GoodsInfo();

        goodsInfo.setMaterialId(materialId);
        goodsInfo.setFromContract(fromContract);
        goodsInfo.setBatchNo(batchNo);
        goodsInfo.setLocationNo(locationNo);
        goodsInfo.setUserId(user);
        goodsInfo.setUserIMEI(imei);
        goodsInfo.setUserLat(lat);
        goodsInfo.setUserLon(lon);

        List<GoodsInfo> goodsInfoList = new ArrayList<>();
        goodsInfoList.add(goodsInfo);

        goodsInfos.setGoodsInfoList(goodsInfoList);


        new Thread(new Runnable() {
            @Override
            public void run() {

                deviceService.postGoodsInfo(goodsInfos).subscribe(resp -> {
                    if (resp != null && resp.size() > 0) {
                        try {

                            String materialName = resp.get(0).getMaterialName();
                            String materialType = resp.get(0).getMaterialType();
                            String materialUnit = resp.get(0).getMaterialUnit();
                            String availableSum = resp.get(0).getAvailableSum();

                            if(availableSum == null || availableSum.length() < 1){
                                availableSum = "0";
                            }

                            goodsAllInfo.setPartName(materialName);
                            goodsAllInfo.setPartFormat(materialType);
                            goodsAllInfo.setPartUnit(materialUnit);
                            goodsAllInfo.setAvailableSum(availableSum);

                            Message msg = new Message();
                            msg.what = 0x001;
                            myHandler.sendMessage(msg);
                            progress.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                            Message msg = new Message();
                            msg.what = 0x002;
                            myHandler.sendMessage(msg);
                            progress.dismiss();
                        }
                    }else{
                        Message msg = new Message();
                        msg.what = 0x002;
                        myHandler.sendMessage(msg);
                        progress.dismiss();
                    }
                }, e -> {
                    LogUtil.info("postGoodsInfo", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Fail!" + e);
                    Message msg = new Message();
                    msg.what = 0x002;
                    myHandler.sendMessage(msg);
                    progress.dismiss();
                });

            }
        }).start();

    }


    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == 0x001){
                goodsAllInfo.setTransportTaskId(tranSportTaskID);
                try {
                    if(!isCurrentShowDialog){
                        showDialog(true, goodsAllInfo);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }else if(msg.what == 0x002){
                Toast.makeText(getActivity(), "扫码失败, 请稍后再试！！", Toast.LENGTH_SHORT).show();
            }

        }
    };


    private void showDialog(boolean isNewMaterialRow, GoodsAllInfo goodsAllInfo) throws Exception{


        NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getContext());



        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.part__yunshu_line_dialog, null);

        Button confirmBtn = (Button) dialogView.findViewById(R.id.yunshu_dialog_confirm);
        Button cancelBtn = (Button) dialogView.findViewById(R.id.yunshu_dialog_cancel);

        TextView transportTaskIdTV = (TextView) dialogView.findViewById(R.id.yunshu_dialog_task_id);
        TextView lineNoTV = (TextView) dialogView.findViewById(R.id.yunshu_dialog__line_no);
        TextView partIdTV = (TextView) dialogView.findViewById(R.id.yunshu_dialog_part_id);
        TextView partNameTV = (TextView) dialogView.findViewById(R.id.yunshu_dialog_part_name);

        EditText partQuantityET = (EditText) dialogView.findViewById(R.id.yunshu_dialog_part_quantity);

        TextView partUnitTV = (TextView) dialogView.findViewById(R.id.yunshu_dialog_part_unit);
        TextView partBatchNoTV = (TextView) dialogView.findViewById(R.id.yunshu_dialog_part_batch_no);
        TextView partSerialNoTV = (TextView) dialogView.findViewById(R.id.yunshu_dialog_part_serial_no);
        TextView partFormatTV = (TextView) dialogView.findViewById(R.id.yunshu_dialog_part_format);


        transportTaskIdTV.setText(goodsAllInfo.getTransportTaskId());
        lineNoTV.setText(goodsAllInfo.getLineNo());
        partIdTV.setText(goodsAllInfo.getPartId());
        partNameTV.setText(goodsAllInfo.getPartName());

        if(isNewMaterialRow){

        }else{
            partQuantityET.setText(goodsAllInfo.getPartQuantity());


            String lineNo = goodsAllInfo.getLineNo();
            if(lineNo != null && lineNo.length() > 0){
                partQuantityET.setFocusable(false);
                partQuantityET.setFocusableInTouchMode(false);

                confirmBtn.setVisibility(View.GONE);
            }
        }


        partUnitTV.setText(goodsAllInfo.getPartUnit());
        partBatchNoTV.setText(goodsAllInfo.getPartBatchNo());
        partSerialNoTV.setText(goodsAllInfo.getPartSerialNo());
        partFormatTV.setText(goodsAllInfo.getPartFormat());

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String partQuantity = partQuantityET.getText().toString();

                if(partQuantity == null || partQuantity.length() < 1){
                    Toast.makeText(getActivity(), "请输入物资数量！", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(Integer.parseInt(partQuantity) == 0){
                    Toast.makeText(getActivity(), "物资数量怎么会是0呢？", Toast.LENGTH_SHORT).show();
                    return;
                }

                String availableSum = goodsAllInfo.getAvailableSum();
                try {
                    if(Integer.parseInt(partQuantity) > Integer.parseInt(availableSum)){
                        Toast.makeText(getActivity(), "该物资库存可用数为" + availableSum + ", 所输数量已超过库存可用数，请核实！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }


                if(isNewMaterialRow){
                    goodsAllInfo.setPartQuantity(Integer.parseInt(partQuantity) + "");

                    new YunshuLineContentValues()
                            .putTransportTaskId(goodsAllInfo.getTransportTaskId())
                            .putPartNo(goodsAllInfo.getPartId())
                            .putPartName(goodsAllInfo.getPartName())
                            .putQuantity(goodsAllInfo.getPartQuantity())
                            .putUnit(goodsAllInfo.getPartUnit())
                            .putLotBatchNo(goodsAllInfo.getPartBatchNo())
                            .putSerialNo(goodsAllInfo.getPartSerialNo())
                            .putFormat(goodsAllInfo.getPartFormat())
                            .putLineType("0")
                            .putKeepCol1(goodsAllInfo.getAvailableSum())
                            .insert(getContext());

                    new YunshuHeadContentValues()
                            .putTransportTaskType("0")
                            .update(getContext().getContentResolver(), new YunshuHeadSelection().transportTaskId(goodsAllInfo.getTransportTaskId()));

                    dialogBuilder.dismiss();
                    isCurrentShowDialog = false;

                    startActivity(new Intent(getActivity(), Main4YunshuActivity.class));
                    getActivity().finish();


                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
                isCurrentShowDialog = false;
            }
        });


        dialogBuilder
                .withTitle("货盘运输")
                .withTitleColor("#4682B4")
                .withDividerColor("#11000000")
                .withDialogColor("#FFFFFF")
                .isCancelableOnTouchOutside(false)
                .isCancelable(false)
                .setCustomView(dialogView, getContext())
                .show();
        isCurrentShowDialog = true;


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 111;
            }
        }, 900);
    }



    View addMaterialRow(long id) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.part__yunshu_line__view, transportLine_Container, false);
        transportLine_Container.addView(v);
        v.setTag(id);
        setMenu(v, id);

        return v;
    }


    private void setMenu(View v, Long id) {
        final Toolbar toolbar = (Toolbar) v.findViewById(R.id.yunshu_line__view_toolbar);

        toolbar.inflateMenu(R.menu.yunshu_line_view_menu);

        TextView materialNameTV = (TextView) v.findViewById(R.id.part_description_yunshu_dialog_item);
        TextView materialIdTV = (TextView) v.findViewById(R.id.part_no_yunshu_dialog_item);
        TextView materialCountTV = (TextView) v.findViewById(R.id.part_count_yunshu_dialog_item);
        TextView availableSumTV = (TextView) v.findViewById(R.id.part_availablesum_yunshu_dialog_item);

        String materialName = "";
        String materialId = "";
        String materialCount = "";
        String availableSum = "";

        YunshuLineSelection lineSelection = new YunshuLineSelection().id(id);
        YunshuLineCursor lineCursor = lineSelection.query(getContext().getContentResolver());
        try {
            while(lineCursor.moveToNext()){
                materialName = lineCursor.getPartName();
                materialId = lineCursor.getPartNo();
                materialCount = lineCursor.getQuantity();
                availableSum = lineCursor.getKeepCol1();

                if (lineCursor.getLineNo() != null) {
                    toolbar.setTitle("[物资行号] #" + lineCursor.getLineNo());
                    toolbar.getMenu().findItem(R.id.yunshu_line_view_menu_action_sync).setVisible(false);
                    toolbar.getMenu().findItem(R.id.yunshu_line_view_menu_action_delete).setVisible(false);
                    toolbar.setBackgroundColor(getContext().getResources().getColor(R.color.green));
                }

            }
        } finally {
            lineCursor.close();
        }

        materialNameTV.setText(materialName);
        materialIdTV.setText(materialId);
        materialCountTV.setText(materialCount);
        availableSumTV.setText(availableSum);




        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.yunshu_line_view_menu_action_sync:

                        break;

                    case R.id.yunshu_line_view_menu_action_edit:
                        try {
                            GoodsAllInfo goodsAllInfo = new GoodsAllInfo();
                            YunshuLineSelection lineSelection = new YunshuLineSelection().id(id);
                            YunshuLineCursor lineCursor = lineSelection.query(getContext().getContentResolver());
                            while (lineCursor.moveToNext()){
                                goodsAllInfo.setTransportTaskId(lineCursor.getTransportTaskId());
                                goodsAllInfo.setLineNo(lineCursor.getLineNo());
                                goodsAllInfo.setPartId(lineCursor.getPartNo());
                                goodsAllInfo.setPartName(lineCursor.getPartName());
                                goodsAllInfo.setPartQuantity(lineCursor.getQuantity());
                                goodsAllInfo.setPartUnit(lineCursor.getUnit());
                                goodsAllInfo.setPartBatchNo(lineCursor.getLotBatchNo());
                                goodsAllInfo.setPartSerialNo(lineCursor.getSerialNo());
                                goodsAllInfo.setPartFormat(lineCursor.getFormat());
                                goodsAllInfo.setAvailableSum(lineCursor.getKeepCol1());
                            }


                            showDialog(false, goodsAllInfo);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;

                    case R.id.yunshu_line_view_menu_action_delete:
                        new AlertDialog.Builder(getActivity())
                                .setIcon(R.drawable.ic_info_black_24dp)
                                .setTitle("温馨提示")
                                .setMessage("确定删除该物资行？")
                                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        deleteMaterialRow(v, id);
                                    }
                                })
                                .setNegativeButton(R.string.cancel, null)
                                .setCancelable(false)
                                .show();

                        break;
                }
                return true;
            }
        });
    }


    void deleteMaterialRow(View fieldView, Long id) {
        transportLine_Container.removeView(fieldView);
        Toast.makeText(getContext(), "已删除一行！", Toast.LENGTH_SHORT).show();
    }


    class GoodsAllInfo{
        String transportTaskId;
        String lineNo;
        String partId;
        String partName;
        String partQuantity;
        String partUnit;
        String partBatchNo;
        String partSerialNo;
        String partFormat;
        String availableSum;

        public String getAvailableSum() {
            return availableSum;
        }

        public void setAvailableSum(String availableSum) {
            this.availableSum = availableSum;
        }

        public String getLineNo() {
            return lineNo;
        }

        public void setLineNo(String lineNo) {
            this.lineNo = lineNo;
        }

        public String getPartBatchNo() {
            return partBatchNo;
        }

        public void setPartBatchNo(String partBatchNo) {
            this.partBatchNo = partBatchNo;
        }

        public String getPartFormat() {
            return partFormat;
        }

        public void setPartFormat(String partFormat) {
            this.partFormat = partFormat;
        }

        public String getPartId() {
            return partId;
        }

        public void setPartId(String partId) {
            this.partId = partId;
        }

        public String getPartName() {
            return partName;
        }

        public void setPartName(String partName) {
            this.partName = partName;
        }

        public String getPartQuantity() {
            return partQuantity;
        }

        public void setPartQuantity(String partQuantity) {
            this.partQuantity = partQuantity;
        }

        public String getPartSerialNo() {
            return partSerialNo;
        }

        public void setPartSerialNo(String partSerialNo) {
            this.partSerialNo = partSerialNo;
        }

        public String getPartUnit() {
            return partUnit;
        }

        public void setPartUnit(String partUnit) {
            this.partUnit = partUnit;
        }

        public String getTransportTaskId() {
            return transportTaskId;
        }

        public void setTransportTaskId(String transportTaskId) {
            this.transportTaskId = transportTaskId;
        }
    }

}
