/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import cc.xingyan.android.afc.util.LogUtil;
import cc.xingyan.android.afc.util.NetUtil;

/**
 * Created by San on 9/24/15.
 */
public class ScanActivity extends Activity {
    public static final String RESULT = "result";
    Button backBtn;


    private String START_SCAN_ACTION = "android.intent.action.FUNCTION_BUTTON_DOWN";



    private String STOP_SCAN = "android.intent.action.FUNCTION_BUTTON_UP";



    private String SCAN_BACK_KEY = "Scan_context";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(0xff000000));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setFinishOnTouchOutside(false);
        setContentView(R.layout.activity_scan_laser);
        backBtn = (Button) findViewById(R.id.scan_back_btn_laser);

        startScan();

    }

    @Override
    public void onResume() {
        super.onResume();

        Intent intent = new Intent("com.android.scanservice.output.foreground");
        intent.putExtra("Scan_output_foreground", false);
        sendBroadcast(intent);

        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(NetUtil.RECE_DATA_ACTION);
        registerReceiver(receiver, iFilter);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void startScan() {

        Intent scanIntent = new Intent("com.android.scanservice.scan.on");
        sendBroadcast(scanIntent);

        Intent intent = new Intent();
        intent.setAction(START_SCAN_ACTION);
        sendBroadcast(intent, null);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(android.content.Context context,
                              Intent intent) {
            String action = intent.getAction();
            if (action.equals(NetUtil.RECE_DATA_ACTION)) {
                String data = intent.getStringExtra(SCAN_BACK_KEY);

                 if(data.contains("#")){
                    String[] strsInfo = data.split("#");
                    if(strsInfo[1].equals("0")){
                        finish();
                    }else  if(strsInfo[1].equals("1")){
                        finish();
                    }else{

                        //快速创建工单
                        String deviceId = getDevieceId(strsInfo);
//                        if (strsInfo.length ==8){
//                            String [] id = strsInfo[4].split("\r\n");
//                            handleResult((id[0]));
//                        }else if (strsInfo.length==9){
//                            String [] id = strsInfo[5].split("\r\n");
//                            handleResult((id[0]));
//                        }else if (strsInfo.length ==10){
//                            String [] id = strsInfo[6].split("\r\n");
//                            handleResult((id[0]));
//                        }
                        
                        if (TextUtils.isEmpty(deviceId)){
                            Toast.makeText(context, "无法获取物理编码", Toast.LENGTH_SHORT).show();
                        }else {
                            handleResult(deviceId);
                        }


                    }
                }else{
                    if(data.contains(":")){
                        String[] temp = data.split("\r\n");
                        handleResult(temp[0].split(":")[1]);
                    }else {
                        finish();
                    }
                }
            }
        }

    };

    private String getDevieceId(String[] strsInfo) {
        for (String info: strsInfo) {
            String [] id = info.split("\r\n");
            if (id[0].length() == 21){
                if (isDeviceId(id[0])){
                    return id[0];
                }
            }
        }
        return "";
    }

    private boolean isDeviceId(String info) {
        char[] charArray =  info.toCharArray();
        for (char ch: charArray) {
            if (ch!='-'&& !Character.isDigit(ch)){
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT < 28){
            Intent intent = new Intent();
            intent.setAction(STOP_SCAN);
            sendBroadcast(intent);
        }
        unregisterReceiver(receiver);
    }


    public void handleResult(String scanInfo) {
        LogUtil.debug("Scan", "Sacn_SacnResult>>>> :" + scanInfo);
        final Intent data = new Intent();
        data.putExtra(RESULT, scanInfo);
        setResult(RESULT_OK, data);
        finish();
    }

}
