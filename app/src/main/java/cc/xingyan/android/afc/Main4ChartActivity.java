package cc.xingyan.android.afc;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.zzj.afc.ui.wheelview.time.ScreenInfo;
import com.zzj.afc.ui.wheelview.time.WheelMain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.CmService;
import cc.xingyan.android.afc.api.model.ReportReqData;
import cc.xingyan.android.afc.api.model.ReportReqDatas;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.user.UserCursor;
import cc.xingyan.android.afc.provider.user.UserSelection;
import cc.xingyan.android.afc.util.LogConfig;
import cc.xingyan.android.afc.util.LogUtil;
import cc.xingyan.android.afc.util.NetUtil;
import cc.xingyan.android.afc.util.Numbers;
import cc.xingyan.android.afc.util.SyncHelper;

/**
 * Created by YuJiadeng on 2016/8/18.
 */
public class Main4ChartActivity extends FragmentActivity implements LogConfig {
    private static final String TAG = "Chart";
    public static int netStatus = 0;

    private MyPagerAdapter myPagerAdapter;
    private ViewPager viewPager;
    private ImageView imageView;
    private TextView receiveText, sendText, proportionText;
    private TextView chartStartText,chartEndText;
    private Button chartSubmitBtn;
    private List<Fragment> fragments;
    private int offset = 0;
    private int currIndex = 0;
    private int bmpW;
    private int selectedColor, unSelectedColor;

    @Inject
    Authenticator mAuthenticator;

    @Inject
    CmService cmService;


    private static final int pageSize = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Dagger.inject(this);

        setContentView(R.layout.chart_main);

        chartStartText = (TextView)findViewById(R.id.chart_start_time);
        chartEndText = (TextView)findViewById(R.id.chart_end_time);
        chartSubmitBtn = (Button)findViewById(R.id.chart_submit);
        initView();
    }


    @Override
    protected void onStart() {
        super.onStart();
        chartStartText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDateTimePickerUpon((TextView) v, "开始时间");
            }
        });

        chartEndText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDateTimePickerUpon((TextView)v, "截止时间");
            }
        });

        chartSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean isNetworkAvailable =  NetUtil.isNetworkAvailable(Main4ChartActivity.this);
                    if(!isNetworkAvailable){
                        new AlertDialog.Builder(Main4ChartActivity.this)
                                .setIcon(R.drawable.ic_info_black_24dp)
                                .setTitle("温馨提示")
                                .setMessage("没有网络！")
                                .setPositiveButton(R.string.ok, null)
                                .show();
                        return;
                    }else{
                        checkChartTimeData();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initView() {
        selectedColor = getResources().getColor(R.color.tv_pre);
        unSelectedColor = getResources().getColor(R.color.tv_normal);

        InitImageView();
        InitTextView();
        InitViewPager();
    }

    private void InitViewPager() {
        viewPager = (ViewPager) findViewById(R.id.id_viewpager);
        fragments = new ArrayList<Fragment>();
        fragments.add(new ChartReceiveFragment());
        fragments.add(new ChartSendFragment());
        fragments.add(new ChartProportionFragment());

        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);

        viewPager.setAdapter(myPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    private void InitTextView() {
        receiveText = (TextView) findViewById(R.id.id_receive);
        sendText = (TextView) findViewById(R.id.id_send);
        proportionText = (TextView) findViewById(R.id.id_proportion);

        receiveText.setTextColor(selectedColor);
        sendText.setTextColor(unSelectedColor);
        proportionText.setTextColor(unSelectedColor);

        receiveText.setText("接报故障");
        sendText.setText("上表故障");
        proportionText.setText("故障占比");

        receiveText.setOnClickListener(new MyOnClickListener(0));
        sendText.setOnClickListener(new MyOnClickListener(1));
        proportionText.setOnClickListener(new MyOnClickListener(2));
    }

    private void InitImageView() {
        imageView = (ImageView) findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(),
                R.drawable.tab_selected_bg).getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        offset = (screenW / pageSize - bmpW) / 2;

        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        imageView.setImageMatrix(matrix);
    }


    private class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {

            switch (index) {
                case 0:
                    receiveText.setTextColor(selectedColor);
                    sendText.setTextColor(unSelectedColor);
                    proportionText.setTextColor(unSelectedColor);

                    break;
                case 1:
                    sendText.setTextColor(selectedColor);
                    receiveText.setTextColor(unSelectedColor);
                    proportionText.setTextColor(unSelectedColor);

                    break;
                case 2:
                    proportionText.setTextColor(selectedColor);
                    receiveText.setTextColor(unSelectedColor);
                    sendText.setTextColor(unSelectedColor);

                    break;
            }
            viewPager.setCurrentItem(index);
        }

    }


    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        int one = offset * 2 + bmpW;
        int two = one * 2;

        public void onPageScrollStateChanged(int index) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageSelected(int index) {
            Animation animation = new TranslateAnimation(one * currIndex, one * index, 0, 0);
            currIndex = index;
            animation.setFillAfter(true);
            animation.setDuration(300);
            imageView.startAnimation(animation);

            switch (index) {
                case 0:
                    receiveText.setTextColor(selectedColor);
                    sendText.setTextColor(unSelectedColor);
                    proportionText.setTextColor(unSelectedColor);

                    Intent intent0 = new Intent();
                    intent0.setAction("page0");
                    sendBroadcast(intent0);

                    break;
                case 1:
                    sendText.setTextColor(selectedColor);
                    receiveText.setTextColor(unSelectedColor);
                    proportionText.setTextColor(unSelectedColor);

                    Intent intent1 = new Intent();
                    intent1.setAction("page1");
                    sendBroadcast(intent1);
                    break;
                case 2:
                    proportionText.setTextColor(selectedColor);
                    receiveText.setTextColor(unSelectedColor);
                    sendText.setTextColor(unSelectedColor);


                    Intent intent2 = new Intent();
                    intent2.setAction("page2");
                    sendBroadcast(intent2);
                    break;
            }
        }
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        public MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }


        @Override
        public Fragment getItem(int arg0) {
            return (fragmentList == null || fragmentList.size() == 0) ? null
                    : fragmentList.get(arg0);
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }


        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
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
        ScreenInfo screenInfo = new ScreenInfo(this);
        final WheelMain wheelMain = new WheelMain(dateTimePickerView, true, false);
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

                    final int viewId = view.getId();
                    switch (viewId) {
                        case R.id.chart_start_time:


                            break;
                        case R.id.chart_end_time:


                            break;

                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }


    private void checkChartTimeData() throws  Exception{
        String chartStartStr = chartStartText.getText().toString();
        String chartEndStr = chartEndText.getText().toString();
        if(chartStartStr == null || chartStartStr.length() < 1){
            Toast.makeText(this,"请选择开始时间！", Toast.LENGTH_SHORT).show();
            return;
        }
        if(chartEndStr == null || chartEndStr.length() < 1){
            Toast.makeText(this,"请选择截止时间！", Toast.LENGTH_SHORT).show();
            return;
        }
        long startTime = Numbers.longValue((Long) chartStartText.getTag());
        long endTime = Numbers.longValue((Long) chartEndText.getTag());

        if(startTime >= endTime){
            new AlertDialog.Builder(this).setTitle("温馨提示").setMessage("截止时间要大于开始时间！")
                    .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {

                        }
                    }).setCancelable(false)
                    .show();
            return;
        }


        getChartData(startTime, endTime);

    }

    private ProgressDialog progress;

    private void getChartData(long startTime, long endTime) throws  Exception{

        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIcon(R.drawable.ic_settings_white_24dp);
        progress.setTitle("获取报表数据");
        progress.setMessage("正在下载...");
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
                String user = mAuthenticator.getAuthenticatedUserId();;
                String userPassword = "";
                ReportReqData reportReqData = new ReportReqData();
                ReportReqDatas reportReqDatas = new ReportReqDatas();
                ArrayList<ReportReqData> reportReqDataList = new ArrayList<ReportReqData>();

                UserCursor userCursor = new UserSelection().userId(user).query(getApplicationContext());
                while(userCursor.moveToNext()){
                    userPassword = userCursor.getPasswordMd5();
                }

                reportReqData.setUserId(user);
                reportReqData.setUserPwd(userPassword);
                reportReqData.setEncryptFlag("");
                reportReqData.setDateStart(new Date(startTime));
                reportReqData.setDateEnd(new Date(endTime));

                reportReqDataList.add(reportReqData);
                reportReqDatas.setReportReqDataList(reportReqDataList);

                cmService.reportInfo(reportReqDatas).subscribe(resp -> {
                    if (willLog(TAG)) {
                        LogUtil.debug(TAG, String.format("Downloaded %1$d ChartDatas", resp.size()));
                    }
                    if(resp != null && resp.size() > 0){
                        int saveLineCount = SyncHelper.saveChartData(getApplicationContext().getContentResolver(), resp);
                        if(netStatus == 1){
                            myPagerAdapter.notifyDataSetChanged();
                            netStatus = 0;
                        }
                        if(saveLineCount > 0){
                            Intent intent = new Intent();
                            intent.setAction("showAndUpDateChart");
                            sendBroadcast(intent);

                        }
                        progress.dismiss();
                    }else{
                        progress.dismiss();
                        Message msg = new Message();
                        msg.what = 0x001;
                        myHandler.sendMessage(msg);
                    }
                }, e -> {
                    progress.dismiss();
                    LogUtil.info("GetChartData", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Fail!" + e);

                });
            }
        }).start();
    }

    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0x001){
                Toast.makeText(Main4ChartActivity.this, "没有获取到报表数据！", Toast.LENGTH_SHORT).show();
            }

        }
    };

}
