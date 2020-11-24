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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.DeviceService;
import cc.xingyan.android.afc.api.model.ParamMaterialKunCun;
import cc.xingyan.android.afc.api.model.ParamMaterialWarehouseInfo;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.util.LogUtil;
import cc.xingyan.android.afc.util.NetUtil;
import cc.xingyan.android.afc.util.TService;

/**
 * Created by YuJiadeng on 2017/3/9.
 */
public class PartKunCunFragment extends BaseFragment {

    @Inject
    DeviceService deviceService;

    @Inject
    Authenticator mAuthenticator;

    private ProgressDialog progress;
    private SharedPreferences partInfoPreferences;

    @Bind(R.id.part_contract_kuncun) TextView partContractKunCun;
    @Bind(R.id.part_no_kuncun) TextView partNoKunCun;
    @Bind(R.id.part_description_kuncun) TextView partDescriptionKunCun;
    @Bind(R.id.part_kuwei_kuncun) TextView partKuweiKunCun;
    @Bind(R.id.part_listview_kuncun) ListView partListViewKunCun;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.part__kuncun, null);

        return view;
    }


    private String SCAN_BACK_KEY = "Scan_context";
    public static boolean isKunCunFragmentVisible = false;
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



        isKunCunFragmentVisible = true;
        PartLingJianFragment.isLingJingFragmentVisible = false;
        PartPanKuFragment.isPankuFragmentVisible = false;
        PartYunShuLineFragment.isYunShuLineFragmentVisible = false;
        LogUtil.info("CmMaterialFragment_Scan", "isKunCunFragmentVisible>> " + isKunCunFragmentVisible);

        partInfoPreferences = getActivity().getSharedPreferences("part_info", Activity.MODE_PRIVATE);
        String partContract = partInfoPreferences.getString("partContract_kuncun", "0");
        String partNo = partInfoPreferences.getString("partNo_kuncun", "1");
        String kuweiNo = partInfoPreferences.getString("kuweiNo_kuncun", "2");

        boolean isPartContractEffective = false;
        boolean isPartNoEffective = false;
        boolean isKuweiNoEffective = false;
        if(partContract != null && partContract.length() > 1){
            isPartContractEffective = true;
        }
        if(partNo != null && partNo.length() > 1){
            isPartNoEffective = true;
        }
        if(kuweiNo != null && kuweiNo.length() > 1){
            isKuweiNoEffective = true;
        }
        if(isPartContractEffective && isPartNoEffective && isKuweiNoEffective){

            partContractKunCun.setText(partContract);
            partNoKunCun.setText(partNo);

            progress.show();
            getKunCunInfo(partNo, kuweiNo);
        }

    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(NetUtil.RECE_DATA_ACTION)) {
                String barcode = "";
                barcode = intent.getStringExtra(SCAN_BACK_KEY);

                if(isKunCunFragmentVisible){
                    if(barcode.contains("#")){
                        String[] strsInfo = barcode.split("#");
                        if(strsInfo[1].equals("1")){
                            String partNo = strsInfo[2].trim();
                            String partName = strsInfo[3].trim();
                            String kuweiNo = strsInfo[4].trim();
                            String kuweiName = strsInfo[5].trim();
                            String partContract = strsInfo[6].trim();

                            partContractKunCun.setText(partContract);
                            partNoKunCun.setText(partNo);
                            partDescriptionKunCun.setText(partName);
                            partKuweiKunCun.setText(kuweiName);



                            progress.show();

                            partInfoPreferences = getActivity().getSharedPreferences("part_info", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor partInfoPf = partInfoPreferences.edit();
                            partInfoPf.putString("partContract_kuncun", partContract);
                            partInfoPf.putString("partNo_kuncun", partNo);
                            partInfoPf.putString("kuweiNo_kuncun", kuweiNo);
                            partInfoPf.commit();

                            getKunCunInfo(partNo, kuweiNo);

                        }else if(strsInfo[1].equals("0")){
                            Toast.makeText(getActivity(), "库存—这是物资小码，请扫物资大码！", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        if(barcode.contains(":")){
                            Toast.makeText(getActivity(), "库存—这是设备码，请扫物资大码！", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "不合法的编码！", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        }
    };


    private List<ParamMaterialWarehouseInfo> WarehouseInfos;


    private void getKunCunInfo(String partNo, String kuweiNo){
        String user = mAuthenticator.getAuthenticatedUserId();
        String imei = TService.imei;
        String lat = TService.lat;
        String lon = TService.lon;

        new Thread(new Runnable() {
            @Override
            public void run() {
                deviceService.listMaterialsKunCun(partNo, kuweiNo, user, NetUtil.getString(imei), NetUtil.getString(lat), NetUtil.getString(lon)).subscribe(resp -> {

                    if(resp != null && resp.size() > 0){

                        ParamMaterialKunCun paramMaterialKunCun = resp.get(0);
                        String description = paramMaterialKunCun.getDescription();
                        WarehouseInfos = paramMaterialKunCun.getWarehouseInfos();

                        Bundle bundle = new Bundle();
                        bundle.putString("description", description);

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

                partDescriptionKunCun.setText(description);

                if(WarehouseInfos != null){
                    MyPartKunCunAdapter myPartKuweiKunCunAdapter = new MyPartKunCunAdapter(getContext(), WarehouseInfos);
                    partListViewKunCun.setAdapter(myPartKuweiKunCunAdapter);
                }

            }else if(msg.what == 0x002){
                refreshView();
                Toast.makeText(getActivity(), "没有该物资信息！", Toast.LENGTH_SHORT).show();
            }else if(msg.what == 0x003){
                refreshView();
                Toast.makeText(getActivity(), "没有该物资信息，请确认！", Toast.LENGTH_SHORT).show();
            }

        }
    };


    private void refreshView(){
        WarehouseInfos = new ArrayList<>();
        MyPartKunCunAdapter myPartKuweiKunCunAdapter = new MyPartKunCunAdapter(getContext(), WarehouseInfos);
        partListViewKunCun.setAdapter(myPartKuweiKunCunAdapter);
    }

    public class MyPartKunCunAdapter extends BaseAdapter {
        private List<ParamMaterialWarehouseInfo> warehouseInfoList;
        private Context context;
        LayoutInflater inflater;

        public MyPartKunCunAdapter(Context context, List<ParamMaterialWarehouseInfo> warehouseInfoList) {
            this.context = context;
            this.warehouseInfoList = warehouseInfoList;
            inflater = LayoutInflater.from(context);
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
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.part__kuncun_item, parent, false);
                holder = new ViewHolder();
                holder.partIndexKuncunItem = (TextView) convertView.findViewById(R.id.part_index);
                holder.partPihaoKuncunItem = (TextView) convertView.findViewById(R.id.part_pihao_kuncun_item);
                holder.partXianyouKuncunItem = (TextView) convertView.findViewById(R.id.part_xianyou_kuncun_item);
                holder.partYuliuKuncunItem = (TextView) convertView.findViewById(R.id.part_yuliu_kuncun_item);
                holder.partZaituKuncunItem = (TextView) convertView.findViewById(R.id.part_zaitu_kuncun_item);
                holder.partKeyongKuncunItem = (TextView) convertView.findViewById(R.id.part_keyong_kuncun_item);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            holder.partPihaoKuncunItem.setText("(" + (position + 1) + ") " + "物资批号：" + warehouseInfoList.get(position).getLotBatchNo());
            holder.partXianyouKuncunItem.setText(warehouseInfoList.get(position).getOnHandSum());
            holder.partYuliuKuncunItem.setText(warehouseInfoList.get(position).getReservedSum());
            holder.partZaituKuncunItem.setText(warehouseInfoList.get(position).getTransitSum());
            holder.partKeyongKuncunItem.setText(warehouseInfoList.get(position).getAvailableSum());

            return convertView;
        }
    }


    public class ViewHolder {
        public TextView partPihaoKuncunItem;
        public TextView partIndexKuncunItem;
        public TextView partXianyouKuncunItem;
        public TextView partYuliuKuncunItem;
        public TextView partZaituKuncunItem;
        public TextView partKeyongKuncunItem;
    }
}
