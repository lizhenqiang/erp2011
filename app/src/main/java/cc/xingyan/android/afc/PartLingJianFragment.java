package cc.xingyan.android.afc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import javax.inject.Inject;

import butterknife.Bind;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.DeviceService;
import cc.xingyan.android.afc.api.model.ParamMaterialLingJian;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.util.LogUtil;
import cc.xingyan.android.afc.util.NetUtil;
import cc.xingyan.android.afc.util.TService;

/**
 * Created by YuJiadeng on 2017/3/9.
 */
public class PartLingJianFragment extends BaseFragment {

    @Inject
    DeviceService deviceService;

    @Inject
    Authenticator mAuthenticator;

    private ProgressDialog progress;
    private SharedPreferences partInfoPreferences;

    @Bind(R.id.part_contract_lingjian) TextView partContractLingjian;
    @Bind(R.id.part_no_lingjian) TextView partNoLingjian;
    @Bind(R.id.part_description_lingjian) TextView partDescriptionLingjian;
    @Bind(R.id.part_type_designation_lingjian) TextView partTypeDesignationLingjian;
    @Bind(R.id.part_prime_commodity_code_lingjian) TextView partPrimeCommodityCodeLingjian;
    @Bind(R.id.part_prime_commodity_description_lingjian) TextView partPrimeCommodityDescriptionLingjian;
    @Bind(R.id.part_second_commodity_code_lingjian) TextView partSecondCommodityCodeLingjian;
    @Bind(R.id.part_second_commodity_description_lingjian) TextView partSecondCommodityDescriptionLingjian;
    @Bind(R.id.part_unit_meas_code_lingjian) TextView partUnitMeasCodeLingjian;
    @Bind(R.id.part_pihao_lingjian) TextView partPihaoLingjian;
    @Bind(R.id.part_xuhao_lingjian) TextView partXuhaoLingjian;
    @Bind(R.id.part_count_lingjian) TextView partCountLingjian;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.part__lingjian, null);

        return view;
    }


    private String SCAN_BACK_KEY = "Scan_context";
    public static boolean isLingJingFragmentVisible = false;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent = new Intent("com.android.scanservice.output.foreground");
        intent.putExtra("Scan_output_foreground", false);
        getActivity().sendBroadcast(intent);

        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(NetUtil.RECE_DATA_ACTION);
        getActivity().registerReceiver(receiver, iFilter);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);

        if(partInfoPreferences != null){
            SharedPreferences.Editor partInfoEditor = partInfoPreferences.edit();
            partInfoEditor.clear();
            partInfoEditor.commit();
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
        Context context = getContext();
        context.getTheme();
        progress = new ProgressDialog(getContext());
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIcon(R.drawable.ic_settings_white_24dp);
        progress.setTitle("获取信息");
        progress.setMessage("请稍后...");
        progress.setIndeterminate(false);
        progress.setButton(DialogInterface.BUTTON_POSITIVE, "取消",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        progress.dismiss();
                    }

                });


        isLingJingFragmentVisible = true;
        PartKunCunFragment.isKunCunFragmentVisible = false;
        PartPanKuFragment.isPankuFragmentVisible = false;
        PartYunShuLineFragment.isYunShuLineFragmentVisible = false;
        LogUtil.info("CmMaterialFragment_Scan", "isLingJingFragmentVisible>> " + isLingJingFragmentVisible);

        partInfoPreferences = getActivity().getSharedPreferences("part_info", Activity.MODE_PRIVATE);
        String partContract = partInfoPreferences.getString("partContract_lingjian", "0");
        String partNo = partInfoPreferences.getString("partNo_lingjian", "1");
        String partPihao = partInfoPreferences.getString("partPihao_lingjian", "2");
        String partXuhao = partInfoPreferences.getString("partXuhao_lingjian", "3");

        boolean isPartContractEffective = false;
        boolean isPartNoEffective = false;
        boolean isPartPihaoEffective = false;
        boolean isPartXuhaoEffective = false;
        if(partContract != null && partContract.length() > 1){
            isPartContractEffective = true;
        }
        if(partNo != null && partNo.length() > 1){
            isPartNoEffective = true;
        }
        if(partPihao != null && partPihao.length() > 1){
            isPartPihaoEffective = true;
        }
        if(partXuhao != null && partXuhao.length() > 1){
            isPartXuhaoEffective = true;
        }

        if(isPartContractEffective && isPartNoEffective && isPartPihaoEffective && isPartXuhaoEffective){

            partContractLingjian.setText(partContract);
            partNoLingjian.setText(partNo);
            partPihaoLingjian.setText(partPihao);
            partXuhaoLingjian.setText(partXuhao);

            progress.show();
            getLingJingInfo(partNo, partPihao);
        }
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(NetUtil.RECE_DATA_ACTION)) {
                String barcode = "";
                barcode = intent.getStringExtra(SCAN_BACK_KEY);


                if(isLingJingFragmentVisible){
                    if(barcode.contains("#")){
                        String[] strsInfo = barcode.split("#");
                        if(strsInfo[1].equals("0")){
                            String partNo = strsInfo[2].trim();
                            String partPihao = strsInfo[3].trim();
                            String partXuhao = strsInfo[4].trim();
                            String partContract = strsInfo[5].trim();


                            partContractLingjian.setText(partContract);
                            partNoLingjian.setText(partNo);
                            partPihaoLingjian.setText(partPihao);
                            partXuhaoLingjian.setText(partXuhao);


                            progress.show();

                            partInfoPreferences = getActivity().getSharedPreferences("part_info", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor partInfoEditor = partInfoPreferences.edit();
                            partInfoEditor.putString("partContract_lingjian", partContract);
                            partInfoEditor.putString("partNo_lingjian", partNo);
                            partInfoEditor.putString("partPihao_lingjian", partPihao);
                            partInfoEditor.putString("partXuhao_lingjian", partXuhao);
                            partInfoEditor.commit();

                            getLingJingInfo(partNo, partPihao);
                        }else if(strsInfo[1].equals("1")){
                            Toast.makeText(getActivity(), "零件—这是物资大码，请扫物资小码！", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        if(barcode.contains(":")){
                            Toast.makeText(getActivity(), "零件—这是设备码，请扫物资小码！", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "不合法的编码！", Toast.LENGTH_SHORT).show();
                        }
                    }




                }
            }
        }
    };


    private void getLingJingInfo(String partNo, String partPihao){
        String user = mAuthenticator.getAuthenticatedUserId();
        String imei = TService.imei;
        String lat = TService.lat;
        String lon = TService.lon;
        new Thread(new Runnable() {
            @Override
            public void run() {
                deviceService.listMaterialsLingJian(partNo, partPihao, user, NetUtil.getString(imei), NetUtil.getString(lat), NetUtil.getString(lon)).subscribe(resp -> {

                    if(resp != null && resp.size() > 0){

                        ParamMaterialLingJian paramMaterialLingJian = resp.get(0);
                        String description = paramMaterialLingJian.getDescription();
                        String typeDescription = paramMaterialLingJian.getTypeDescription();
                        String primeCommodityCode = paramMaterialLingJian.getPrimeCommodityCode();
                        String primeCommodityName = paramMaterialLingJian.getPrimeCommodityName();
                        String secondCommodityCode = paramMaterialLingJian.getSecondCommodityCode();
                        String secondCommodityName = paramMaterialLingJian.getSecondCommodityName();
                        String unitMeas = paramMaterialLingJian.getUnitMeas();
                        String sumInWarehouse = paramMaterialLingJian.getSumInWarehouse();

                        Bundle bundle = new Bundle();
                        bundle.putString("description", description);
                        bundle.putString("typeDescription", typeDescription);
                        bundle.putString("primeCommodityCode", primeCommodityCode);
                        bundle.putString("primeCommodityName", primeCommodityName);
                        bundle.putString("secondCommodityCode", secondCommodityCode);
                        bundle.putString("secondCommodityName", secondCommodityName);
                        bundle.putString("unitMeas", unitMeas);
                        bundle.putString("sumInWarehouse", sumInWarehouse);

                        Message msg = new Message();
                        msg.setData(bundle);
                        msg.what = 0x001;
                        myHandler.sendMessage(msg);

                        progress.dismiss();
                    }else if(resp != null && resp.size() == 0){
                        Message msg = new Message();
                        msg.what = 0x002;
                        myHandler.sendMessage(msg);
                        progress.dismiss();
                    }else if(resp == null){
                        Message msg = new Message();
                        msg.what = 0x003;
                        myHandler.sendMessage(msg);
                        progress.dismiss();
                    }
                }, e -> {
                    progress.dismiss();
                    LogUtil.info("GetMaterialsByScan", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Fail!" + e);

                });
            }
        }).start();
    }


    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0x001){
                Bundle bundle = msg.getData();
                String description = bundle.getString("description");
                String typeDescription = bundle.getString("typeDescription");
                String primeCommodityCode = bundle.getString("primeCommodityCode");
                String primeCommodityName = bundle.getString("primeCommodityName");
                String secondCommodityCode = bundle.getString("secondCommodityCode");
                String secondCommodityName = bundle.getString("secondCommodityName");
                String unitMeas = bundle.getString("unitMeas");
                String sumInWarehouse = bundle.getString("sumInWarehouse");

                partDescriptionLingjian.setText(description);
                partTypeDesignationLingjian.setText(typeDescription);
                partPrimeCommodityCodeLingjian.setText(primeCommodityCode);
                partPrimeCommodityDescriptionLingjian.setText(primeCommodityName);
                partSecondCommodityCodeLingjian.setText(secondCommodityCode);
                partSecondCommodityDescriptionLingjian.setText(secondCommodityName);
                partUnitMeasCodeLingjian.setText(unitMeas);
                partCountLingjian.setText(sumInWarehouse);


            }else if(msg.what == 0x002){
                Toast.makeText(getActivity(), "没有该物资信息！", Toast.LENGTH_SHORT).show();
            }else if(msg.what == 0x003){
                Toast.makeText(getActivity(), "没有该物资信息，请确认！", Toast.LENGTH_SHORT).show();
            }

        }
    };
}
