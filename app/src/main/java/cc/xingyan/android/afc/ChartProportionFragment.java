package cc.xingyan.android.afc;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.xclcharts.common.DensityUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import cc.xingyan.android.afc.chartview.PieChartView;
import cc.xingyan.android.afc.provider.report.ReportCursor;
import cc.xingyan.android.afc.provider.report.ReportSelection;
import cc.xingyan.android.afc.util.NetUtil;

/**
 * Created by YuJiadeng on 2016/8/19.
 */
public class ChartProportionFragment extends Fragment {

    private LinearLayout mLaychart;
    private Switch unitSwitch;
    private TextView starTimeReceiveTextView;
    private TextView endTimeReceiveTextView;
    String unitStr;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.chart__receive, null);
        mLaychart = (LinearLayout)view.findViewById(R.id.chart_receive);
        unitSwitch = (Switch)view.findViewById(R.id.unit_switch);
        unitSwitch.setChecked(true);
        unitSwitch.setVisibility(View.GONE);

        starTimeReceiveTextView = (TextView) view.findViewById(R.id.star_time_receive);
        endTimeReceiveTextView = (TextView) view.findViewById(R.id.end_time_receive);

        if(unitSwitch.isChecked()){
            unitStr = "线";
        }else{
            unitStr = "部";
        }

        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction("showAndUpDateChart");
        getContext().getApplicationContext().registerReceiver(receiver, iFilter);

        IntentFilter iFilter_ = new IntentFilter();
        iFilter_.addAction("page2");
        getContext().getApplicationContext().registerReceiver(receiver_, iFilter_);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        unitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    unitStr = "线";
                    try {
                        dealChartData(unitStr,"");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    unitStr = "部";
                    try {
                        dealChartData(unitStr, "");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getShowData();
        }
    }


    private void getShowData(){
        boolean isNetworkAvailable =  NetUtil.isNetworkAvailable(getActivity());
        if(!isNetworkAvailable){
            try {
                dealChartData(unitStr, "无网状态！");
                Main4ChartActivity.netStatus = 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(android.content.Context context,
                              android.content.Intent intent) {

            try {
                dealChartData(unitStr, "广播");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private BroadcastReceiver receiver_ = new BroadcastReceiver() {
        public void onReceive(android.content.Context context,
                              android.content.Intent intent) {

            try {
                dealChartData(unitStr, "滑动");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try {
            getContext().getApplicationContext().unregisterReceiver(receiver);
            getContext().getApplicationContext().unregisterReceiver(receiver_);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void dealChartData(String unit, String status) throws Exception{

        long starTime = 0;
        long endTime = 0;

        ReportCursor cursor = new ReportSelection().nameLike("%" + "部").query(getContext().getContentResolver());
        Map<String, String> datasMap = new HashMap<String, String>();
        while(cursor.moveToNext()){

            datasMap.put("AGNum", cursor.getAgNum());
            datasMap.put("BomNum", cursor.getBomNum());
            datasMap.put("TvmNum", cursor.getTvmNum());
            datasMap.put("OtherNum", cursor.getOtherNum());

            datasMap.put("AGPer", cursor.getAgPer());
            datasMap.put("BomPer", cursor.getBomPer());
            datasMap.put("TvmPer", cursor.getTvmPer());
            datasMap.put("OtherPer", cursor.getOtherPer());

            starTime = cursor.getDataStart();
            endTime = cursor.getDataEnd();

        }

        if(starTime == 0 || endTime == 0){
            starTimeReceiveTextView.setVisibility(View.INVISIBLE);
            endTimeReceiveTextView.setVisibility(View.INVISIBLE);
        }else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            starTimeReceiveTextView.setText(sdf.format(starTime));
            endTimeReceiveTextView.setText(sdf.format(endTime));
        }

        renderChart(datasMap);
    }



    private void renderChart(Map<String, String> datasMap) throws  Exception{

        int width = DensityUtil.dip2px(getContext().getApplicationContext(), 300);
        int height = DensityUtil.dip2px(getContext().getApplicationContext(), 400);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);


        mLaychart.removeAllViews();
        PieChartView pieChartView= new PieChartView(getContext(), datasMap);
        mLaychart.addView(pieChartView, layoutParams);
    }
}
