package cc.xingyan.android.afc;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.Bind;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.DeviceService;
import cc.xingyan.android.afc.api.model.ParamMaterialPanKuReport;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.pankureport.PankuReportContentValues;
import cc.xingyan.android.afc.provider.pankureport.PankuReportCursor;
import cc.xingyan.android.afc.provider.pankureport.PankuReportSelection;
import cc.xingyan.android.afc.util.NetUtil;

/**
 * Created by YuJiadeng on 2017/3/28.
 */
public class PartPanKuFragment extends BaseFragment {

    @Inject
    DeviceService deviceService;

    @Inject
    Authenticator mAuthenticator;

    @Bind(R.id.part_inv_list_no_panku) TextView partNoPanKu;
    @Bind(R.id.part_kuwei_panku) Spinner partKuweiPanKu;
    @Bind(R.id.listview_panku) ListView partPankuListView;



    boolean isActivityCreated = false;
    boolean isUserVisibleHint = false;

    private String SCAN_BACK_KEY = "Scan_context";
    public static boolean isPankuFragmentVisible = false;

    Effectstype[] niftyDialogEffects = {Effectstype.Fadein, Effectstype.Fall, Effectstype.Fliph, Effectstype.Flipv, Effectstype.Newspager,
            Effectstype.RotateBottom, Effectstype.RotateLeft, Effectstype.Shake, Effectstype.Sidefill, Effectstype.SlideBottom,
            Effectstype.Slideleft, Effectstype.Slideright, Effectstype.Slidetop, Effectstype.Slit};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.part__panku, null);


        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        IntentFilter iFilter_ = new IntentFilter();
        iFilter_.addAction("change_pandian_count");
        getActivity().registerReceiver(receiver_, iFilter_);


        Intent intent = new Intent("com.android.scanservice.output.foreground");
        intent.putExtra("Scan_output_foreground", false);
        getActivity().sendBroadcast(intent);


        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(NetUtil.RECE_DATA_ACTION);
        getActivity().registerReceiver(receiver, iFilter);


        isActivityCreated = true;
        if(isActivityCreated&&isUserVisibleHint){
            showView();

        }



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
        isPankuFragmentVisible = true;
        PartLingJianFragment.isLingJingFragmentVisible = false;
        PartKunCunFragment.isKunCunFragmentVisible = false;
        PartYunShuLineFragment.isYunShuLineFragmentVisible = false;
        isUserVisibleHint = true;
        if(isActivityCreated&&isUserVisibleHint){
            showView();

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            getActivity().unregisterReceiver(receiver_);
            getActivity().unregisterReceiver(receiver);

            SharedPreferences kuweiPreferences = getActivity().getSharedPreferences("warehouseNoPF", Activity.MODE_PRIVATE);
            SharedPreferences.Editor kuweiPf = kuweiPreferences.edit();
            kuweiPf.clear();
            kuweiPf.commit();



            SharedPreferences kuweiPreferencesKuweiListSelect = getActivity().getSharedPreferences("warehouseNoKuweiListSelect", Activity.MODE_PRIVATE);
            SharedPreferences.Editor KuweiListSelectPf = kuweiPreferencesKuweiListSelect.edit();
            KuweiListSelectPf.clear();
            KuweiListSelectPf.commit();


            SharedPreferences selectWarehousePreferences = getActivity().getSharedPreferences("PankuReportManagementSelectWarehouse", Activity.MODE_PRIVATE);
            SharedPreferences.Editor selectWarehousePf = selectWarehousePreferences.edit();
            selectWarehousePf.clear();
            selectWarehousePf.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showView(){
        String reportNo = "";
        String user = mAuthenticator.getAuthenticatedUserId();
        PankuReportSelection pankuReportSelection = new PankuReportSelection().userId(user);
        PankuReportCursor pankuReportCursor = pankuReportSelection.query(getContext().getContentResolver());

        try {
            while (pankuReportCursor.moveToNext()) {
                reportNo = pankuReportCursor.getReportNo();
                if(reportNo.length() > 0){
                    partNoPanKu.setText(reportNo);
                    break;
                }
            }

            if(reportNo.length() < 1){
                new AlertDialog.Builder(getActivity())
                        .setIcon(R.drawable.ic_info_black_24dp)
                        .setTitle("温馨提示")
                        .setMessage("当前没有盘点报告，请到“盘库管理”下载盘点报告！")
                        .setPositiveButton(R.string.ok, null)
                        .setCancelable(false)
                        .show();
                return;

            }

            showKuweiSpinner(reportNo);
        } finally {
            pankuReportCursor.close();
        }

    }

    List<Warehouse> warehouseList;
    private void showKuweiSpinner(String reportNo){
        String user = mAuthenticator.getAuthenticatedUserId();
        warehouseList = new ArrayList<>();
        PankuReportSelection pankuReportSelection2 = new PankuReportSelection().userId(user);
        PankuReportCursor pankuReportCursor2 = pankuReportSelection2.query(getContext().getContentResolver());
        try {
            Warehouse warehouse = new Warehouse();

            while (pankuReportCursor2.moveToNext()) {
                warehouse = new Warehouse();
                warehouse.setWarehouseNo(pankuReportCursor2.getWarehouseNo());
                warehouse.setWarehouseName(pankuReportCursor2.getWarehouseName());
                if(!warehouseList.contains(warehouse)){
                    warehouseList.add(warehouse);
                }

            }
        } finally {
            pankuReportCursor2.close();
        }

        MyPartKuweiPanKuAdapter myPartKuweiKunCunAdapter = new MyPartKuweiPanKuAdapter(getContext(), warehouseList);
        partKuweiPanKu.setAdapter(myPartKuweiKunCunAdapter);


        SharedPreferences kuweiPreferencesKuweiListSelect = getActivity().getSharedPreferences("warehouseNoKuweiListSelect", Activity.MODE_PRIVATE);
        SharedPreferences.Editor kuweiListSelectPf = kuweiPreferencesKuweiListSelect.edit();
        kuweiListSelectPf.putString("warehouseSelect", warehouseList.get(0).warehouseNo);
        kuweiListSelectPf.commit();

        SharedPreferences kuweiPreferences = getActivity().getSharedPreferences("PankuReportManagementSelectWarehouse", Activity.MODE_PRIVATE);
        boolean isPankuReportManagementSelectWarehouse = kuweiPreferences.getBoolean("isPankuReportManagementSelectWarehouse",false);
        if(isPankuReportManagementSelectWarehouse){
            String selectWarehouseNo = kuweiPreferences.getString("warehouseNo","all");
            showKuweiByPosition(selectWarehouseNo);
            showPartList(selectWarehouseNo, 0);
        }


        partKuweiPanKu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showPartList(warehouseList.get(position).getWarehouseNo(), 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    int scrollPos, scrollTop;
    List<ParamMaterialPanKuReport> partList;

    private void showPartList(String warehouseNo, int type){
        String user = mAuthenticator.getAuthenticatedUserId();
        partList = new ArrayList<>();

        PankuReportSelection pankuReportSelection4List;

        pankuReportSelection4List = new PankuReportSelection().warehouseNo(warehouseNo).and().userId(user);


        SharedPreferences kuweiPreferencesKuweiListSelect = getActivity().getSharedPreferences("warehouseNoKuweiListSelect", Activity.MODE_PRIVATE);
        SharedPreferences.Editor kuweiListSelectPf = kuweiPreferencesKuweiListSelect.edit();
        kuweiListSelectPf.putString("warehouseSelect", warehouseNo);
        kuweiListSelectPf.commit();
        PankuReportCursor pankuReportCursor4List = pankuReportSelection4List.query(getContext().getContentResolver());


        try {
            while (pankuReportCursor4List.moveToNext()) {
                ParamMaterialPanKuReport paramMaterialPanKuReport = new ParamMaterialPanKuReport();
                paramMaterialPanKuReport.setReportNo(pankuReportCursor4List.getReportNo());
                paramMaterialPanKuReport.setPartNo(pankuReportCursor4List.getPartNo());
                paramMaterialPanKuReport.setPartName(pankuReportCursor4List.getPartName());
                paramMaterialPanKuReport.setActualAmount(pankuReportCursor4List.getActualAmount());
                paramMaterialPanKuReport.setLotBatchNo(pankuReportCursor4List.getLotbatchNo());
                paramMaterialPanKuReport.setLineNo(pankuReportCursor4List.getLineNo());
                paramMaterialPanKuReport.setPartSeq(pankuReportCursor4List.getPartSeq());
                paramMaterialPanKuReport.setWarehouseNo(pankuReportCursor4List.getWarehouseNo());
                paramMaterialPanKuReport.setWarehouseName(pankuReportCursor4List.getWarehouseName());
                paramMaterialPanKuReport.setPandianMark(pankuReportCursor4List.getPandianMark());
                paramMaterialPanKuReport.setPandianTime(pankuReportCursor4List.getPandianTime());

                partList.add(paramMaterialPanKuReport);
            }

        } finally {
            pankuReportCursor4List.close();
        }

        MyPartPanKuListAdapter myPartPanKuListAdapter = new MyPartPanKuListAdapter(getContext(), partList);
        partPankuListView.setAdapter(myPartPanKuListAdapter);

        if(type == 2){
            SharedPreferences kuweiPreferences = getActivity().getSharedPreferences("warehouseNoPF", Activity.MODE_PRIVATE);
            boolean is4PartNo4Scan = kuweiPreferences.getBoolean("is4PartNo4Scan",false);

            if(is4PartNo4Scan){
                String partNo4Scan = kuweiPreferences.getString("partNo4Scan", "all");
                String lotBatchNo4Scan = kuweiPreferences.getString("lotBatchNo4Scan", "all");
                String partSeq4Scan = kuweiPreferences.getString("partSeq4Scan", "all");
                for(int i = 0; i < partList.size(); i++){
                    if(partList.get(i).getPartNo().equals(partNo4Scan) &&
                            partList.get(i).getLotBatchNo().equals(lotBatchNo4Scan) &&
                            partList.get(i).getPartSeq().equals(partSeq4Scan)){
                        partPankuListView.setSelection(i);
                        break;
                    }
                }

            }else{
                partPankuListView.setSelectionFromTop(scrollPos, scrollTop);
            }
        }






        partPankuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    showDialog(partList.get(position));
                    isCurrentShowDialog = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        partPankuListView.setOnScrollListener(scrollListener);

    }


    private AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() {

        @Override
        public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
        }

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                scrollPos = partPankuListView.getFirstVisiblePosition();
            }
            if (partList != null) {
                View v = partPankuListView .getChildAt(0);
                scrollTop = (v==null)?0:v.getTop();
            }
        }
    };


    private void showKuweiByPosition(String warehouseNo){
        for(int i = 0; i < warehouseList.size(); i++){
            if(warehouseList.get(i).getWarehouseNo().equals(warehouseNo)){
                partKuweiPanKu.setSelection(i,true);


                SharedPreferences kuweiPreferencesKuweiListSelect = getActivity().getSharedPreferences("warehouseNoKuweiListSelect", Activity.MODE_PRIVATE);
                SharedPreferences.Editor kuweiListSelectPf = kuweiPreferencesKuweiListSelect.edit();
                kuweiListSelectPf.putString("warehouseSelect", warehouseNo);
                kuweiListSelectPf.commit();

                break;
            }
        }

    }

    public class MyPartKuweiPanKuAdapter extends BaseAdapter {
        private List<Warehouse> warehouseInfoList;
        private Context context;

        public MyPartKuweiPanKuAdapter(Context context, List<Warehouse> warehouseInfoList) {
            this.context = context;
            this.warehouseInfoList = warehouseInfoList;
        }

        @Override
        public int getCount() {
            return warehouseInfoList.size();
        }

        @Override
        public Object getItem(int position) {
            return warehouseInfoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.part__warehouseinfo_item, null);
            if(convertView != null) {
                TextView locNameInfo = (TextView)convertView.findViewById(R.id.locInfoKunCunTV);
                locNameInfo.setText(warehouseInfoList.get(position).getWarehouseName());
            }
            return convertView;
        }
    }


    public class MyPartPanKuListAdapter extends BaseAdapter {
        private List<ParamMaterialPanKuReport> partList;
        private Context context;

        public MyPartPanKuListAdapter(Context context, List<ParamMaterialPanKuReport> partList) {
            this.context = context;
            this.partList = partList;
        }

        @Override
        public int getCount() {
            return partList.size();
        }

        @Override
        public Object getItem(int position) {
            return partList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.part__panku_item, null);
            if(convertView != null) {
                TextView partNoTV = (TextView)convertView.findViewById(R.id.part_no_panku_item);
                TextView partDescriptionTV = (TextView)convertView.findViewById(R.id.part_description_panku_item);
                TextView partPihaoTV = (TextView)convertView.findViewById(R.id.part_pihao_panku_item);
                TextView partPandiancountTV = (TextView)convertView.findViewById(R.id.part_pandian_count_panku_item);
                TextView partPandianTimeTV = (TextView)convertView.findViewById(R.id.part_pandian_time_panku_item);
                ImageView pandianStateImgIV = (ImageView)convertView.findViewById(R.id.pandian_state_img);
                TextView partSeqTV = (TextView)convertView.findViewById(R.id.part_seq_panku_item);

                RelativeLayout pandianTimeLayout = (RelativeLayout) convertView.findViewById(R.id.pandian_time_layout_panku_item);

                partNoTV.setText("(" + (position + 1) + ")");
                partDescriptionTV.setText(partList.get(position).getPartName());
                partPandiancountTV.setText(partList.get(position).getActualAmount());

                String markStr = partList.get(position).getPandianMark();

                if(markStr.equals("1")){
                    pandianStateImgIV.setImageResource(R.drawable.yipan_img);
                }else if(markStr.equals("0")){
                    pandianStateImgIV.setImageResource(R.drawable.weipan_img);
                }

                if(partList.get(position).getPartSeq().length() > 1){
                    partSeqTV.setText("序列件");
                }

                long partPandianTime = partList.get(position).getPandianTime();
                if(partPandianTime > 0){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    pandianTimeLayout.setVisibility(View.VISIBLE);
                    partPandianTimeTV.setText(sdf.format(partPandianTime));
                }


            }
            return convertView;
        }
    }


    private int getRandomNum(int max, int min){
        Random random = new Random();

        int randomNum = random.nextInt(max)%(max-min+1) + min;
        return randomNum;
    }

    EditText actualAmountET = null;

    boolean isCurrentShowDialog = false;

    private void showDialog(ParamMaterialPanKuReport paramMaterialPanKuReport) throws Exception{

        String reportNo = paramMaterialPanKuReport.getReportNo();
        String partNo = paramMaterialPanKuReport.getPartNo();
        String partName = paramMaterialPanKuReport.getPartName();

        String actualAmount = paramMaterialPanKuReport.getActualAmount();
        long pandianTime = paramMaterialPanKuReport.getPandianTime();

        String lotBatchNo = paramMaterialPanKuReport.getLotBatchNo();
        String lineNo = paramMaterialPanKuReport.getLineNo();
        String partSeq = paramMaterialPanKuReport.getPartSeq();
        String warehouseNo = paramMaterialPanKuReport.getWarehouseNo();
        String warehouseName = paramMaterialPanKuReport.getWarehouseName();
        String pandianMark = paramMaterialPanKuReport.getPandianMark();




        NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getContext());



        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.part__panku_dialog_item, null);

        Button confirmBtn = (Button) dialogView.findViewById(R.id.part_inv_list_no_confirm);
        Button cancelBtn = (Button) dialogView.findViewById(R.id.part_inv_list_cancel);

        TextView reportNoTV = (TextView) dialogView.findViewById(R.id.part_inv_list_no_panku_dialog_item);
        TextView partNoTV = (TextView) dialogView.findViewById(R.id.part_no_panku_dialog_item);
        TextView partNameTV = (TextView) dialogView.findViewById(R.id.part_description_panku_dialog_item);

        actualAmountET = (EditText) dialogView.findViewById(R.id.part_pandian_count_panku_dialog_item);

        TextView lotBatchNoTV = (TextView) dialogView.findViewById(R.id.part_pihao_panku_dialog_item);
        TextView seqTV = (TextView) dialogView.findViewById(R.id.part_xuhao_panku_dialog_item);
        TextView warehouseTV = (TextView) dialogView.findViewById(R.id.part_kuwei_no_panku_dialog_item);
        TextView warehouseNameTV = (TextView) dialogView.findViewById(R.id.part_kuwei_name_panku_dialog_item);
        TextView pandianTimeTV = (TextView) dialogView.findViewById(R.id.part_pandian_time_dialog_item);

        RelativeLayout pandianTimeLayout = (RelativeLayout) dialogView.findViewById(R.id.pandian_time_layout_dialog_item);


        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmInfo(partSeq, warehouseNo, partNo, lotBatchNo, dialogBuilder);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCurrentShowDialog = false;
                dialogBuilder.dismiss();
            }
        });

        Drawable drawable = null;
        if(pandianMark.equals("0")){
            drawable = getResources().getDrawable(R.drawable.weipan_img);
        }else{
            drawable = getResources().getDrawable(R.drawable.yipan_img);
            actualAmountET.setText(actualAmount);

            if(pandianTime > 0){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pandianTimeLayout.setVisibility(View.VISIBLE);
                pandianTimeTV.setText(sdf.format(pandianTime));
            }
        }


        reportNoTV.setText(reportNo + "_" + lineNo);
        partNoTV.setText(partNo);
        partNameTV.setText(partName);



        lotBatchNoTV.setText(lotBatchNo);
        seqTV.setText(partSeq);
        warehouseTV.setText(warehouseNo);
        warehouseNameTV.setText(warehouseName);


        dialogBuilder
                .withTitle("物资盘点")
                .withTitleColor("#4682B4")
                .withDividerColor("#11000000")
                .withDialogColor("#FFFFFF")
                .withIcon(drawable)
                .withDuration(700)
                .withEffect(niftyDialogEffects[getRandomNum(niftyDialogEffects.length, 0)])
                .isCancelableOnTouchOutside(false)
                .isCancelable(false)
                .setCustomView(dialogView, getContext())
                .show();


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 111;
                dialogHandle.sendMessage(msg);
            }
        }, 900);
    }

    Handler dialogHandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 111){

                if(actualAmountET != null){
                    actualAmountET.requestFocus();
                    actualAmountET.setSelection(actualAmountET.getText().length());
                    InputMethodManager imm = (InputMethodManager) actualAmountET.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
                }

            }
        }
    };


    private void confirmInfo(String partSeq, String warehouseNo, String partNo, String lotBatchNo, NiftyDialogBuilder dialogBuilder){

        isCurrentShowDialog = false;
        String actualAmountStr = actualAmountET.getText().toString().trim();
        if (actualAmountStr == null) {
            new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.ic_info_black_24dp)
                    .setTitle("温馨提示")
                    .setMessage("请输入实盘数量！")
                    .setPositiveButton(R.string.ok, null)
                    .setCancelable(false)
                    .show();
            return;
        } else if (actualAmountStr != null && actualAmountStr.length() < 1) {
            new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.ic_info_black_24dp)
                    .setTitle("温馨提示")
                    .setMessage("请输入实盘数量！")
                    .setPositiveButton(R.string.ok, null)
                    .setCancelable(false)
                    .show();
            return;
        }

        try {
            if (Integer.parseInt(actualAmountStr) > 9999999) {
                new AlertDialog.Builder(getActivity())
                        .setIcon(R.drawable.ic_info_black_24dp)
                        .setTitle("温馨提示")
                        .setMessage("老兄，实盘数量真的这么多吗？！")
                        .setPositiveButton(R.string.ok, null)
                        .setCancelable(false)
                        .show();
                return;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.ic_info_black_24dp)
                    .setTitle("温馨提示")
                    .setMessage("数值异常，请确认实盘数量是否超过9999999或者关闭PDA“前台输出”！")
                    .setPositiveButton(R.string.ok, null)
                    .setCancelable(false)
                    .show();
            return;
        }

        boolean isAll0 = false;
        for (int i = 0; i < actualAmountStr.length(); i++) {
            char ch = actualAmountStr.charAt(i);
            if (ch == '0') {
                isAll0 = true;
            } else {
                isAll0 = false;
                break;
            }
        }

        if (isAll0) {
            actualAmountStr = "0";
        } else {

            while (actualAmountStr.startsWith("0")) {
                try {
                    actualAmountStr = actualAmountStr.substring(1, actualAmountStr.length());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        try {
            if (partSeq.length() > 1) {
                if (Integer.parseInt(actualAmountStr) > 1) {
                    new AlertDialog.Builder(getActivity())
                            .setIcon(R.drawable.ic_info_black_24dp)
                            .setTitle("温馨提示")
                            .setMessage("该物资是序列键物资，盘点数量只能是0或1！")
                            .setPositiveButton(R.string.ok, null)
                            .setCancelable(false)
                            .show();
                    return;
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String user = mAuthenticator.getAuthenticatedUserId();
        new PankuReportContentValues()
                .putActualAmount(actualAmountStr)
                .putPandianMark("1")
                .putPandianTime(System.currentTimeMillis())
                .update(getContext().getContentResolver(), new PankuReportSelection()
                        .warehouseNo(warehouseNo)
                        .and()
                        .userId(user)
                        .and()
                        .partNo(partNo)
                        .and()
                        .lotbatchNo(lotBatchNo)
                        .and()
                        .partSeq(partSeq));


        Intent intent = new Intent();
        intent.setAction("change_pandian_count");
        getContext().sendBroadcast(intent);

        SharedPreferences kuweiPreferences = getActivity().getSharedPreferences("warehouseNoPF", Activity.MODE_PRIVATE);
        SharedPreferences.Editor kuweiPf = kuweiPreferences.edit();
        kuweiPf.putString("warehouseNo", warehouseNo);
        kuweiPf.commit();

        dialogBuilder.dismiss();
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



    private BroadcastReceiver receiver_ = new BroadcastReceiver() {
        public void onReceive(Context context,
                              Intent intent) {

            SharedPreferences kuweiPreferences = getActivity().getSharedPreferences("warehouseNoPF", Activity.MODE_PRIVATE);
            String warehouseNo = kuweiPreferences.getString("warehouseNo", "all");
            showKuweiByPosition(warehouseNo);

            showPartList(warehouseNo, 2);
        }


    };


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(NetUtil.RECE_DATA_ACTION)) {
                String barcode = "";
                barcode = intent.getStringExtra(SCAN_BACK_KEY);


                if(isPankuFragmentVisible){
                    if(!isCurrentShowDialog){
                        if(barcode.contains("#")){
                            String[] strsInfo = barcode.split("#");
                            if(strsInfo[1].equals("0")){

                                String user = mAuthenticator.getAuthenticatedUserId();
                                String partNo = strsInfo[2].trim();
                                String lotBatchNo = strsInfo[3].trim();
                                String partSeq = strsInfo[4].trim();

                                PankuReportSelection pankuReportSelection4Scan;

                                SharedPreferences kuweiPreferences = getActivity().getSharedPreferences("warehouseNoKuweiListSelect", Activity.MODE_PRIVATE);
                                String warehouseNo = kuweiPreferences.getString("warehouseSelect","all");


                                SharedPreferences partNo4ScanPreferences = getActivity().getSharedPreferences("warehouseNoPF", Activity.MODE_PRIVATE);
                                SharedPreferences.Editor partNo4ScanPf = partNo4ScanPreferences.edit();
                                partNo4ScanPf.putString("partNo4Scan", partNo);
                                partNo4ScanPf.putString("lotBatchNo4Scan", lotBatchNo);
                                partNo4ScanPf.putString("partSeq4Scan", partSeq);
                                partNo4ScanPf.putBoolean("is4PartNo4Scan", true);
                                partNo4ScanPf.commit();

                                pankuReportSelection4Scan = new PankuReportSelection()
                                        .partNo(partNo)
                                        .and()
                                        .userId(user)
                                        .and()
                                        .warehouseNo(warehouseNo)
                                        .and()
                                        .lotbatchNo(lotBatchNo)
                                        .and()
                                        .partSeq(partSeq);
                                PankuReportCursor pankuReportCursor4Scan = pankuReportSelection4Scan.query(getContext().getContentResolver());

                                try {
                                    if (pankuReportCursor4Scan.moveToNext()) {
                                        ParamMaterialPanKuReport paramMaterialPanKuReport = new ParamMaterialPanKuReport();
                                        paramMaterialPanKuReport.setReportNo(pankuReportCursor4Scan.getReportNo());
                                        paramMaterialPanKuReport.setPartNo(pankuReportCursor4Scan.getPartNo());
                                        paramMaterialPanKuReport.setPartName(pankuReportCursor4Scan.getPartName());
                                        paramMaterialPanKuReport.setActualAmount(pankuReportCursor4Scan.getActualAmount());
                                        paramMaterialPanKuReport.setLotBatchNo(pankuReportCursor4Scan.getLotbatchNo());
                                        paramMaterialPanKuReport.setLineNo(pankuReportCursor4Scan.getLineNo());
                                        paramMaterialPanKuReport.setPartSeq(pankuReportCursor4Scan.getPartSeq());
                                        paramMaterialPanKuReport.setWarehouseNo(pankuReportCursor4Scan.getWarehouseNo());
                                        paramMaterialPanKuReport.setWarehouseName(pankuReportCursor4Scan.getWarehouseName());
                                        paramMaterialPanKuReport.setPandianMark(pankuReportCursor4Scan.getPandianMark());
                                        paramMaterialPanKuReport.setPandianTime(pankuReportCursor4Scan.getPandianTime());

                                        try {
                                            showDialog(paramMaterialPanKuReport);
                                            isCurrentShowDialog = true;
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }else {
                                        Toast.makeText(getActivity(), "当前盘点报告中没有该物资！", Toast.LENGTH_SHORT).show();
                                    }

                                } finally {
                                    pankuReportCursor4Scan.close();
                                }

                            }else if(strsInfo[1].equals("1")){
                                Toast.makeText(getActivity(), "盘库—这是物资大码，请扫物资小码！", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            if(barcode.contains(":")){
                                Toast.makeText(getActivity(), "盘库—这是设备码，请扫物资小码！", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getActivity(), "不合法的编码！", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }else{
                        Toast.makeText(getActivity(), "请先确认/取消当前物资盘点工作！", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }
    };

}
