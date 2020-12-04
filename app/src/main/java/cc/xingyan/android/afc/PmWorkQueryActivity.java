package cc.xingyan.android.afc;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.zzj.afc.ui.wheelview.time.ScreenInfo;
import com.zzj.afc.ui.wheelview.time.WheelMain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cc.xingyan.android.afc.util.Numbers;

/**
 * Created by YuJiadeng on 2017/7/24.
 */
public class PmWorkQueryActivity extends Activity {
    EditText logicCode;
    EditText queryOrderID;
    EditText logicName;
    EditText physicalCode;
    EditText physicalName;

    EditText applyStartTime;
    EditText planStartTime;
    EditText planEndTime;

    Button okBtn;
    Button cancelBtn;

    SharedPreferences pmQueryPreferences;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(0xff000000));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setFinishOnTouchOutside(false);
        setContentView(R.layout.activity_pm_query);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        init();

    }

    @Override
    protected void onStart() {
        super.onStart();


        applyStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDateTimePickerUpon(applyStartTime, "申请开始时间");
            }
        });
        planStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDateTimePickerUpon(planStartTime, "计划开始时间");
            }
        });
        planEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDateTimePickerUpon(planEndTime, "计划结束时间");
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndBack();
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void init(){
        queryOrderID = (EditText) findViewById(R.id.pm_query_order_id);
        logicCode = (EditText) findViewById(R.id.pm_query_logic_code);
        logicName = (EditText) findViewById(R.id.pm_query_logic_name);
        physicalCode = (EditText) findViewById(R.id.pm_query_physical_code);
        physicalName = (EditText) findViewById(R.id.pm_query_physical_name);

        applyStartTime = (EditText) findViewById(R.id.pm_query_apply_start_time);
        planStartTime = (EditText) findViewById(R.id.pm_query_plan_start_time);
        planEndTime = (EditText) findViewById(R.id.pm_query_plan_end_time);

        okBtn = (Button) findViewById(R.id.pm_query_ok);
        cancelBtn = (Button) findViewById(R.id.pm_query_cancel);

        pmQueryPreferences = getSharedPreferences("pm_query_data", Activity.MODE_PRIVATE);
        if(pmQueryPreferences != null){

            String queryOrderIDStr = pmQueryPreferences.getString("queryOrderID", "");
            String logicCodeStr = pmQueryPreferences.getString("logicCode", "");
            String logicNameStr = pmQueryPreferences.getString("logicName", "");
            String physicalCodeStr = pmQueryPreferences.getString("physicalCode", "");
            String physicalNameStr = pmQueryPreferences.getString("physicalName", "");

            long apply_start_time = pmQueryPreferences.getLong("applyStartTime", 0);
            long plan_start_time = pmQueryPreferences.getLong("planStartTime", 0);
            long plan_end_time = pmQueryPreferences.getLong("planEndTime", 0);


            queryOrderID.setText(queryOrderIDStr);
            queryOrderID.setSelection(queryOrderID.getText().length());

            logicCode.setText(logicCodeStr);
            logicName.setText(logicNameStr);
            physicalCode.setText(physicalCodeStr);
            physicalName.setText(physicalNameStr);

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            if(apply_start_time > 0){
                applyStartTime.setText(dateFormat.format(apply_start_time));
            }

            if(plan_start_time > 0){
                planStartTime.setText(dateFormat.format(plan_start_time));
            }

            if(plan_end_time > 0){
                planEndTime.setText(dateFormat.format(plan_end_time));
            }

        }
    }

    private void saveAndBack(){
        SharedPreferences.Editor pmQueryEditor = pmQueryPreferences.edit();

        String queryOrderIDStr = queryOrderID.getText().toString();
        String logicCodeStr = logicCode.getText().toString();
        String logicNameStr = logicName.getText().toString();
        String physicalCodeStr = physicalCode.getText().toString();
        String physicalNameStr = physicalName.getText().toString();

        long apply_start_time = Numbers.longValue((Long) applyStartTime.getTag());
        long plan_start_time = Numbers.longValue((Long) planStartTime.getTag());
        long plan_end_time = Numbers.longValue((Long) planEndTime.getTag());

        pmQueryEditor.putString("queryOrderID", queryOrderIDStr);
        pmQueryEditor.putString("logicCode", logicCodeStr);
        pmQueryEditor.putString("logicName", logicNameStr);
        pmQueryEditor.putString("physicalCode", physicalCodeStr);
        pmQueryEditor.putString("physicalName", physicalNameStr);

        pmQueryEditor.putLong("applyStartTime", apply_start_time);
        pmQueryEditor.putLong("planStartTime", plan_start_time);
        pmQueryEditor.putLong("planEndTime", plan_end_time);

        pmQueryEditor.commit();

        Intent intent = new Intent();
        intent.putExtra("pm_quety_condition", "OK");
        setResult(RESULT_OK, intent);

    }

    private void openDateTimePickerUpon(final TextView view, final String title) {
        long time = view.getTag() instanceof Long ? ((Long) view.getTag()).longValue() : System.currentTimeMillis();
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(time);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DATE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);

        final View dateTimePickerView = LayoutInflater.from(this).inflate(R.layout.timepicker, null);
        ScreenInfo screenInfo = new ScreenInfo(PmWorkQueryActivity.this);
        final WheelMain wheelMain = new WheelMain(dateTimePickerView, true);
        wheelMain.screenheight = screenInfo.getHeight();
        wheelMain.initDateTimePicker(year, month, date, hour, min);
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_info_black_24dp)
                .setTitle(title)
                .setView(dateTimePickerView)
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    view.setText(wheelMain.getTime());
                    cal.clear();
                    cal.set(Calendar.YEAR, wheelMain.getYear());
                    cal.set(Calendar.MONTH, wheelMain.getMonth() - 1);
                    cal.set(Calendar.DATE, wheelMain.getDate());
                    cal.set(Calendar.HOUR_OF_DAY, wheelMain.getHour());
                    cal.set(Calendar.MINUTE, wheelMain.getMinute());
                    view.setTag(cal.getTimeInMillis());

                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

}
