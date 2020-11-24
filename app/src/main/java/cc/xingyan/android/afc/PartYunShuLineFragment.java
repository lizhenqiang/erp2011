package cc.xingyan.android.afc;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import cc.xingyan.android.afc.api.model.HeadAndLineInfo;
import cc.xingyan.android.afc.api.model.HeadAndLineInfos;
import cc.xingyan.android.afc.api.model.PartYunshuGetLineNoReturn;
import cc.xingyan.android.afc.api.model.TranUpdateHeadInfo;
import cc.xingyan.android.afc.api.model.TranUpdateLineInfo;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.yunshuhead.YunshuHeadCursor;
import cc.xingyan.android.afc.provider.yunshuhead.YunshuHeadSelection;
import cc.xingyan.android.afc.provider.yunshuline.YunshuLineContentValues;
import cc.xingyan.android.afc.provider.yunshuline.YunshuLineCursor;
import cc.xingyan.android.afc.provider.yunshuline.YunshuLineSelection;
import cc.xingyan.android.afc.util.LogUtil;
import cc.xingyan.android.afc.util.NetUtil;
import cc.xingyan.android.afc.util.TService;

/**
 * Created by YuJiadeng on 2017/12/15.
 *
 */
public class PartYunShuLineFragment extends BaseFragment {
    private static final String TRANSPORT_TASK_ID = "transport_task_id";

    public static boolean isYunShuLineFragmentVisible = false;


    private boolean isCurrentShowDialog = false;
    private GoodsAllInfo goodsAllInfo;

    private ProgressDialog progress;

    private String SCAN_BACK_KEY = "Scan_context";

    @Inject
    Authenticator mAuthenticator;

    @Inject
    DeviceService deviceService;

    @Bind(R.id.transport_line_container) LinearLayout transportLine_Container;


    public static Fragment newInstance() {
        PartYunShuLineFragment fragment = new PartYunShuLineFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.part__yunshu_line, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(NetUtil.RECE_DATA_ACTION);
        getActivity().registerReceiver(receiver, iFilter);

        IntentFilter iFilterUpdateYunshuView = new IntentFilter();
        iFilterUpdateYunshuView.addAction("updateYunshuView");
        getActivity().registerReceiver(receiverUpdateYunshuView, iFilterUpdateYunshuView);

        bindAndShowData();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isYunShuLineFragmentVisible = true;
            PartYunShuUploadedLineFragment.isYunShuLineUploadedFragmentVisible = false;
            LogUtil.info("PartYunShuLineFragment_Scan", "isYunShuLineFragmentVisible>> " + isYunShuLineFragmentVisible);

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
        getActivity().unregisterReceiver(receiverUpdateYunshuView);

    }

    private void bindAndShowData(){
        String notUploadTaskId = "";

        YunshuHeadSelection headSelection = new YunshuHeadSelection().transportTaskType("0");
        YunshuHeadCursor headCursor = headSelection.query(getContext().getContentResolver());

        try {
            while(headCursor.moveToNext()){
                notUploadTaskId = headCursor.getTransportTaskId();
            }
        } finally {
            headCursor.close();
        }

        YunshuLineSelection lineSelection = new YunshuLineSelection().transportTaskId(notUploadTaskId);
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

                if(isYunShuLineFragmentVisible){
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
                                String taskId = "";
                                YunshuHeadSelection headSelection0 = new YunshuHeadSelection().transportTaskType("0");
                                YunshuHeadCursor headCursor0 = headSelection0.query(getContext().getContentResolver());

                                try {
                                    while(headCursor0.moveToNext()){

                                        taskId = headCursor0.getTransportTaskId();
                                    }
                                } finally {
                                    headCursor0.close();
                                }


                                if(isHasNotUpdatePart(taskId)){
                                    Toast.makeText(getActivity(), "有未上传物资！", Toast.LENGTH_SHORT).show();
                                    return;
                                }



                                if(!isCurrentShowDialog){
                                    String locationNo = "";
                                    YunshuHeadSelection headSelection = new YunshuHeadSelection();
                                    YunshuHeadCursor headCursor = headSelection.query(getContext().getContentResolver());
                                    try {
                                        while(headCursor.moveToNext()){
                                            locationNo = headCursor.getSendWarehouseNo();
                                        }
                                    } finally {
                                        headCursor.close();
                                    }

                                    if(locationNo == null || locationNo.length() < 1){
                                        Toast.makeText(getActivity(), "没有选择发送库位！", Toast.LENGTH_SHORT).show();
                                        return;
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





    private BroadcastReceiver receiverUpdateYunshuView = new BroadcastReceiver() {
        public void onReceive(Context context,
                              Intent intent) {

            try {


                transportLine_Container.removeAllViews();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };



    private void showDialog(boolean isNewMaterialRow, GoodsAllInfo goodsAllInfo, View view, Long id) throws Exception{


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


                dialogBuilder.dismiss();
                isCurrentShowDialog = false;
                if(isNewMaterialRow){
                    goodsAllInfo.setPartQuantity(Integer.parseInt(partQuantity) + "");

                    addMaterialRow(goodsAllInfo);

                }else{

                    new YunshuLineContentValues()
                            .putQuantity(Integer.parseInt(partQuantity) + "")
                            .update(getContext().getContentResolver(), new YunshuLineSelection()
                                    .transportTaskId(goodsAllInfo.getTransportTaskId())
                                    .and()
                                    .partNo(goodsAllInfo.getPartId())
                                    .and()
                                    .id(id));


                    TextView materialCountTV = (TextView) view.findViewById(R.id.part_count_yunshu_dialog_item);
                    materialCountTV.setText(Integer.parseInt(partQuantity) + "");
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


    View addMaterialRow(GoodsAllInfo goodsAllInfo) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.part__yunshu_line__view, transportLine_Container, false);
        transportLine_Container.addView(v);

        final long id = newMaterialRow(goodsAllInfo);
        if(id > 0){
            v.setTag(id);
            setMenu(v, id);
        }

        return v;
    }


    View addMaterialRow(long id) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.part__yunshu_line__view, transportLine_Container, false);
        transportLine_Container.addView(v);
        v.setTag(id);
        setMenu(v, id);

        return v;
    }

    private long newMaterialRow(GoodsAllInfo goodsAllInfo) {
        Uri uri = new YunshuLineContentValues()
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

        return Long.parseLong(uri.getLastPathSegment());

    }

    private boolean isHasNotUpdatePart(String taskId){
        boolean isHasNotUpdatePart = false;

        YunshuLineSelection lineSelection = new YunshuLineSelection().transportTaskId(taskId);
        YunshuLineCursor lineCursor = lineSelection.query(getContext().getContentResolver());
        try {
            while(lineCursor.moveToNext()){
                if(lineCursor.getLineType().equals("0")){
                    isHasNotUpdatePart = true;
                    break;
                }
            }
        } finally {
            lineCursor.close();
        }

        return isHasNotUpdatePart;
    }


    Toolbar toolbar;
    private void setMenu(View v, Long id) {
        toolbar = (Toolbar) v.findViewById(R.id.yunshu_line__view_toolbar);
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

                        if (checkItem(id)) {
                            onUpdateMaterialRow(id);
                        } else {
                            String partName = "";
                            YunshuLineSelection lineSelection = new YunshuLineSelection().id(id);
                            YunshuLineCursor lineCursor = lineSelection.query(getContext().getContentResolver());
                            try {
                                while (lineCursor.moveToNext()){
                                    partName = lineCursor.getPartName();
                                }
                            } finally {
                                lineCursor.close();

                            }
                            Toast.makeText(getActivity(), partName + " 上传失败，请检查后重试！", Toast.LENGTH_SHORT).show();
                        }

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


                            showDialog(false, goodsAllInfo, v, id);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }finally {
                            lineCursor.close();
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



    private boolean checkItem(long id) {
        boolean isLineItemOK = true;
        boolean isHeadItemOK = true;

        String taskId = "";
        String packNumber;
        String type;
        String from;
        String to;

        String materialId;
        String batchNo;
        String serialNo;
        String quantity;

        YunshuLineSelection lineSelection = new YunshuLineSelection().id(id);
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

        return isLineItemOK && isHeadItemOK;
    }



    private void onUpdateMaterialRow(long id){
        progress = new ProgressDialog(getContext());
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIcon(R.drawable.ic_settings_white_24dp);
        progress.setTitle("上传物资行");
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


        String taskId = "";
        String packNumber = "";
        String type = "";
        String from = "";
        String to = "";

        String materialId = "";
        String batchNo = "";
        String serialNo = "";
        String quantity = "";


        YunshuLineSelection lineSelection = new YunshuLineSelection().id(id);
        YunshuLineCursor lineCursor = lineSelection.query(getContext().getContentResolver());
        try {
            while(lineCursor.moveToNext()){
                taskId = lineCursor.getTransportTaskId();
                materialId = lineCursor.getPartNo();
                batchNo = lineCursor.getLotBatchNo();
                serialNo = lineCursor.getSerialNo();
                quantity = lineCursor.getQuantity();
            }
        } finally {
            lineCursor.close();
        }


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

        HeadAndLineInfos headAndLineInfos = new HeadAndLineInfos();
        HeadAndLineInfo headAndLineInfo = new HeadAndLineInfo();
        TranUpdateHeadInfo headInfo = new TranUpdateHeadInfo();
        List<TranUpdateLineInfo> lineInfoList= new ArrayList<>();
        TranUpdateLineInfo lineInfo = new TranUpdateLineInfo();

        lineInfo.setMaterialId(materialId);
        lineInfo.setBatchNo(batchNo);
        lineInfo.setSerialNo(serialNo);
        lineInfo.setQuantity(quantity);
        lineInfo.setLineId(id);
        lineInfoList.add(lineInfo);

        headInfo.setTaskId(taskId);
        headInfo.setPackNumber(packNumber);
        headInfo.setType(type);
        headInfo.setFrom(from);
        headInfo.setTo(to);

        headAndLineInfo.setTranHead(headInfo);
        headAndLineInfo.setLineInfoList(lineInfoList);

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
                    }else{
                        Message msg = new Message();
                        msg.what = 0x002;
                        getLineNoHandler.sendMessage(msg);
                        progress.dismiss();
                    }
        }, e -> {
            LogUtil.info("postUpdateAndGetLineNo", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Fail!" + e);
            Message msg = new Message();
            msg.what = 0x002;
            getLineNoHandler.sendMessage(msg);
            progress.dismiss();
        });

    }


    void deleteMaterialRow(View fieldView, Long id) {

        new YunshuLineSelection().id(id).delete(getContext());
        transportLine_Container.removeView(fieldView);
        Toast.makeText(getContext(), "已删除一行！", Toast.LENGTH_SHORT).show();

    }

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
                        // TODO Auto-generated method stub
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
                YunshuHeadSelection headSelection = new YunshuHeadSelection().transportTaskType("0");
                YunshuHeadCursor headCursor = headSelection.query(getContext().getContentResolver());

                try {
                    while(headCursor.moveToNext()){
                        if(headCursor.getTransportTaskId() != null){
                            goodsAllInfo.setTransportTaskId(headCursor.getTransportTaskId());
                        }
                    }
                } finally {
                    headCursor.close();
                }

                try {
                    if(!isCurrentShowDialog){
                        showDialog(true, goodsAllInfo, null, null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }else if(msg.what == 0x002){
                Toast.makeText(getActivity(), "扫码失败, 请稍后再试！！", Toast.LENGTH_SHORT).show();
            }

        }
    };


    Handler getLineNoHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0x001){
                Toast.makeText(getActivity(), "物资行上传成功！", Toast.LENGTH_SHORT).show();
            }else if(msg.what == 0x002){
                Toast.makeText(getActivity(), "物资行上传失败, 请稍后再试！！", Toast.LENGTH_SHORT).show();
            }
        }
    };



    private void putLineNo2Data(List<PartYunshuGetLineNoReturn> partYunshuGetLineNoReturnList) throws  Exception{
        PartYunshuGetLineNoReturn getLineNoReturn = partYunshuGetLineNoReturnList.get(0);
        String taskId = getLineNoReturn.getTaskId();
        String materialId = getLineNoReturn.getMaterialId();
        String lineNo = getLineNoReturn.getLineNo();
        long lineId = getLineNoReturn.getLineId();

        new YunshuLineContentValues()
                .putLineNo(lineNo)
                .putLineType("1")
                .update(getContext().getContentResolver(), new YunshuLineSelection()
                        .transportTaskId(taskId)
                        .and()
                        .partNo(materialId)
                        .and()
                        .id(lineId));

        toolbar.setTitle("[物资行号] #" + lineNo);
        toolbar.getMenu().findItem(R.id.yunshu_line_view_menu_action_sync).setVisible(false);
        toolbar.getMenu().findItem(R.id.yunshu_line_view_menu_action_delete).setVisible(false);
        toolbar.setBackgroundColor(getContext().getResources().getColor(R.color.green));


        Intent intent = new Intent();
        intent.setAction("line_ok");
        getActivity().sendBroadcast(intent);

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
