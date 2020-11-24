/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.provider.Settings;

import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputLayout;
import com.squareup.moshi.JsonDataException;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import cc.xingyan.android.afc.api.AuthService;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.ChangePassword;
import cc.xingyan.android.afc.api.model.NewVersion;
import cc.xingyan.android.afc.api.model.NewVersions;
import cc.xingyan.android.afc.api.model.ServerTime;
import cc.xingyan.android.afc.app.BaseActivity;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.cmparammaterials.CmParamMaterialsSelection;
import cc.xingyan.android.afc.provider.device.DeviceSelection;
import cc.xingyan.android.afc.provider.paramvalue.ParamValueSelection;
import cc.xingyan.android.afc.provider.picture.PictureCursor;
import cc.xingyan.android.afc.provider.picture.PictureSelection;
import cc.xingyan.android.afc.provider.user.UserCursor;
import cc.xingyan.android.afc.provider.user.UserSelection;
import cc.xingyan.android.afc.service.SyncService;
import cc.xingyan.android.afc.util.IMEIUtil;
import cc.xingyan.android.afc.util.LogUtil;
import cc.xingyan.android.afc.util.NetUtil;
import cc.xingyan.android.afc.util.PermissionsChecker;
import cc.xingyan.android.afc.util.TService;

/**
 * Created by San on 9/21/15.
 */
public class LoginActivity extends BaseActivity {
    private static final String TAG = "Login";
    private static final int REQUEST_SYNC = 0;
    private static final String PREF_LAST_USER = "last_user";
    private static final String PREF_DEVICES_LAST_SYNC_TIME = "devices_last_sync_time";
    private static final String PREF_MATERIALS_LAST_SYNC_TIME = "materials_last_sync_time";
    private static final String PREF_PARAMS_LAST_SYNC_TIME = "params_last_sync_time";
    private static final String[] PERMISSIONS_WRITE_EXTERNAL_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int REQUEST_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 0;


    String updataVersionUrl;

    SharedPreferences chartPreferences;


    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.input_username)
    TextInputLayout mInputUsername;
    @Bind(R.id.input_password) TextInputLayout mInputPassword;
    @Bind(R.id.edit_username) EditText mEditUsername;
    @Bind(R.id.edit_password) EditText mEditPassword;
    @Inject
    AuthService mAuthService;
    @Inject
    Authenticator mAuthenticator;
    @Inject
    ChangePassword mChangePassword;


    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.FLASHLIGHT
    };
    public static final int PERMISSIONS_REQUEST_CAMERA = 10;
    public static final int PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 20;
    public static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 30;
    public static final int PERMISSIONS_REQUEST_FLASHLIGHT = 40;
    public static final int REQUEST_CODE_WRITE_SETTINGS = 50;
    private PermissionsChecker mPermissionsChecker;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);


        setContentView(R.layout.activity_login);
        mPermissionsChecker = new PermissionsChecker(this);
        if ("REAL8833".equals(BuildConfig.FLAVOR)) {
            mToolbar.setTitle(getString(R.string.title_activity_login_real, BuildConfig.VERSION_NAME));
//            mToolbar.setTitle("专项工单维修系统");
        } else if ("TEST8832".equals(BuildConfig.FLAVOR))  {
            mToolbar.setTitle(getString(R.string.title_activity_login_test, BuildConfig.VERSION_NAME));
        } else if ("DEV8831".equals(BuildConfig.FLAVOR))  {
            mToolbar.setTitle(getString(R.string.title_activity_login_dev, BuildConfig.VERSION_NAME));
        } else {
            mToolbar.setTitle(getString(R.string.title_activity_login_dev, BuildConfig.VERSION_NAME));
        }

        mToolbar.setSubtitle(R.string.subtitle_activity_login);


        mEditUsername.setText(getPreferences(MODE_PRIVATE).getString(PREF_LAST_USER, null));

        requestCallPermission();

//        requstWritePermission();


        try {
            String logRecordFilePath = Environment.
                    getExternalStorageDirectory().getPath() +
                    File.separator + "AFC_Log" + File.separator;
            delAFCLog(logRecordFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }





        Intent intent4Scan = new Intent("com.android.scanservice.output.foreground");
        intent4Scan.putExtra("Scan_output_foreground", false);
        sendBroadcast(intent4Scan);
    }

    private void requstWritePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            //大于等于23 请求权限
            if ( !Settings.System.canWrite(getApplicationContext()))
            {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_CODE_WRITE_SETTINGS );
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
//            startPermissionsActivity();
//        }
    }

//    private void startPermissionsActivity() {
//
//        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.CAMERA)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
//                    Manifest.permission.CAMERA)) {
//
//            }
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.CAMERA},
//                    PERMISSIONS_REQUEST_CAMERA);
//        }
//
//        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//
//            }
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                    PERMISSIONS_REQUEST_EXTERNAL_STORAGE);
//
//        }
//
//        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_PHONE_STATE)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
//                    Manifest.permission.READ_PHONE_STATE)) {
//
//            }
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.READ_PHONE_STATE},
//                    PERMISSIONS_REQUEST_READ_PHONE_STATE);
//        }
//
//        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.FLASHLIGHT)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
//                    Manifest.permission.FLASHLIGHT)) {
//
//            }
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.FLASHLIGHT},
//                    PERMISSIONS_REQUEST_FLASHLIGHT);
//        }
//    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intentStopLoc = new Intent(LoginActivity.this, TService.class);
            stopService(intentStopLoc);
            finish();
        }

        return false;
    }


    private void checkLocalTime(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String userID = mEditUsername.getText().toString();
                    if (TextUtils.isEmpty(userID)) {
                        userID = "login";
                    }
                    String imei = TService.imei;
                    String lat = TService.lat;
                    String lon = TService.lon;

                    LogUtil.info("serverTime", "imei: " + imei + ", lat: " + lat + ", lon: " + lon);

                    mChangePassword.getServerTime(userID, NetUtil.getString(imei), NetUtil.getString(lat), NetUtil.getString(lon)).subscribe(resp -> {
                        LogUtil.info("serverTime", ">>>>>>>>>>>>>>>>>>>>>>>OK!");
                        LogUtil.info("serverTime", "resp>>" + resp);
                        if(resp.size() > 0){
                            ServerTime serverTime = resp.get(0);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("getServerTimeInfo", serverTime);

                            Message msg = new Message();
                            msg.setData(bundle);
                            myServerTimeHandler.sendMessage(msg);
                        }
                    }, e -> {
                        LogUtil.info("serverTime", ">>>>>>>>>>>>>>>>>>>>>>>Fail!" + e);

                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }

    private void checkSoftwareUpdates() {

        PictureCursor pictureCursor = new PictureSelection()
                .keyId("110")
                .and()
                .type("crash")
                .query(LoginActivity.this);
        while(pictureCursor.moveToNext()){
            String crashLogPath = pictureCursor.getPictureId();
            File file = new File(crashLogPath);
            if(file.exists()){
                file.delete();
            }
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                int versionCode = BuildConfig.VERSION_CODE;

                String versionType = "";
                if ("REAL8833".equals(BuildConfig.FLAVOR)) {
                    versionType = "REAL";
                } else if ("TEST8832".equals(BuildConfig.FLAVOR))  {
                    versionType = "TEST";
                } else if ("DEV8831".equals(BuildConfig.FLAVOR))  {
                    versionType = "DEV";
                } else {
                    versionType = "DEV";
                }

//                TelephonyManager tm = (TelephonyManager)getApplicationContext().
//                        getSystemService(getApplicationContext().TELEPHONY_SERVICE);
//                String imei = tm.getDeviceId();
                String    imei = IMEIUtil.getIMEI(getApplicationContext());
                Log.e("TAG","imei=="+imei);
                NewVersion newVersion = new NewVersion();
                newVersion.setCurrectVersion(Integer.toString(versionCode));
                newVersion.setCurrentVersionType(versionType);
                newVersion.setImei(imei);

                NewVersions newVersions = new NewVersions();
                ArrayList<NewVersion> newVersionList = new ArrayList<NewVersion>();
                newVersionList.add(newVersion);
                newVersions.setNewVersionList(newVersionList);

                mChangePassword.getNewVersion(newVersions).subscribe(resp -> {
                    LogUtil.info("NewVersions", ">>>>>>>>>>>>>>>>>>>>>>>OK!");
                    if(resp.size() > 0){
                        NewVersion newVersionBack = resp.get(0);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("newVersionInfo", newVersionBack);

                        Message msg = new Message();
                        msg.setData(bundle);
                        myHandler.sendMessage(msg);
                    }
                }, e -> {
                    LogUtil.info("NewVersions", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Fail!" + e);
                });
            }
        }).start();

    }

    private void delAFCLog(String logRecordFilePath) throws Exception{
        File dirFile = new File(logRecordFilePath);

        if(dirFile.exists()){

            File[] files = dirFile.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    if(files[i].exists()){
                        files[i].delete();
                    }
                }else {
                    delAFCLog(files[i].getAbsolutePath());
                }
            }
            dirFile.delete();
        }

    }

    private Runnable showUpdate = new Runnable() {
        public void run() {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(updataVersionUrl));
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(LoginActivity.this, "没有找到浏览器！", Toast.LENGTH_SHORT).show();
            }
        }
    };


    Handler myServerTimeHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            ServerTime sTime = (ServerTime) bundle.get("getServerTimeInfo");
            long serverTime = sTime.getServerTime().getTime();
            long localTime = System.currentTimeMillis();
            long maxDiff = 5 * 60 * 1000;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            if(Math.abs(serverTime - localTime) > maxDiff){
                new AlertDialog.Builder(LoginActivity.this)
                        .setIcon(R.drawable.ic_info_black_24dp)
                        .setTitle("温馨提示")
                        .setMessage("当前手持机的时间与服务的时间相差超过5分钟，请进行修改！\n" +
                                "当前服务器时间为：\n" + sdf.format(serverTime) + "\n" +
                                "当前手持机时间为：\n" + sdf.format(localTime))
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_DATE_SETTINGS);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .setCancelable(false)
                        .show();

            }else{
                requestPermission(1);
            }


            LogUtil.info("serverTime", "serverTime>> " + serverTime);
            LogUtil.info("serverTime", "localTime>> " + localTime);
        }
    };


    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            NewVersion newVersionBack = (NewVersion) bundle.get("newVersionInfo");

            String updateFlag = newVersionBack.getUpdateFlag();
            String url = newVersionBack.getUrl();
            String updateContent = newVersionBack.getUpdateContent();


            if(url != null && url.equals("htt")){
                return;
            }
            if(updateFlag.contains("2")){
                new AlertDialog.Builder(LoginActivity.this).setTitle("更新提示").setMessage(updateContent)
                        .setPositiveButton(getString(R.string.update_now), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

                                Handler versionHandler = new Handler();
                                updataVersionUrl = url;
                                versionHandler.post(showUpdate);
                                finish();

                            }
                        }).setNegativeButton(getString(R.string.later), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                }).setCancelable(false)
                        .show();
            }else if(updateFlag.contains("3")){
                new AlertDialog.Builder(LoginActivity.this).setTitle("重要更新").setMessage(updateContent)
                        .setPositiveButton(getString(R.string.update_now), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

                                Handler versionHandler = new Handler();
                                updataVersionUrl = url;
                                versionHandler.post(showUpdate);
                                finish();
                            }
                        }).setCancelable(false)
                        .show();
            }
        }
    };

    @OnTextChanged(value = R.id.edit_username, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void onUsernameChanged() {
        mInputUsername.setError(null);
        mInputUsername.setErrorEnabled(false);
    }

    @OnTextChanged(value = R.id.edit_password, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void onPasswordChanged() {
        mInputPassword.setError(null);
        mInputPassword.setErrorEnabled(false);
    }

    @OnClick({R.id.action_login_online}) void onLoginOffline(View v) {

        SharedPreferences partInfoPreferences;
        partInfoPreferences = getSharedPreferences("part_info", Activity.MODE_PRIVATE);
        if(partInfoPreferences != null){
            SharedPreferences.Editor partInfoEditor = partInfoPreferences.edit();
            partInfoEditor.clear();
            partInfoEditor.commit();
        }

        SharedPreferences pmQueryPreferences;
        pmQueryPreferences = getSharedPreferences("pm_query_data", Activity.MODE_PRIVATE);
        if(pmQueryPreferences != null){
            SharedPreferences.Editor pmQueryEditor = pmQueryPreferences.edit();
            pmQueryEditor.clear();
            pmQueryEditor.commit();
        }

        boolean isNetworkAvailable =  NetUtil.isNetworkAvailable(LoginActivity.this);
        if(isNetworkAvailable){

            checkLocalTime();
        }else {
            requestPermission(1);
        }

    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SYNC) {
            if (resultCode == RESULT_OK) {
                startActivity(new Intent(this, MainActivity.class));

                finish();
            }
        }

        if (requestCode == REQUEST_CODE_WRITE_SETTINGS)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                //Settings.System.canWrite方法检测授权结果
                if (Settings.System.canWrite(getApplicationContext()))
                {
                    Toast.makeText(this, "获取到了权限", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "您拒绝了权限", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    private ProgressDialog progress;

    private void onlineAuth(String username, String password) {

        subscribe(mAuthService.login(username, password), loginr -> {
            progress.dismiss();
            if (loginr.size() == 1) {
                if (loginr.get(0).getErrorInfo().toString().equals("登录成功")) {

                    final UserCursor cur = new UserSelection().query(getContentResolver());
                    try {
                        if (cur.moveToNext()) {
                            if (!cur.getOrgCode().equals(loginr.get(0).getWorkArea())) {
                                new DeviceSelection().delete(getContentResolver());
                                new ParamValueSelection().delete(getContentResolver());
                                new CmParamMaterialsSelection().delete(getContentResolver());
                                clearShowMSGSharedPreference();
                            }
                        }
                    } finally {
                        cur.close();
                    }

                    mAuthenticator.setAuthenticatedUser(username);
                    final Intent intent = new Intent(this, SyncActivity.class);
                    intent.putExtra(SyncActivity.EXTRA_SYNC_OPTIONS, SyncService.SYNC_DOWNLOAD_ALL);
                    intent.putExtra(SyncActivity.EXTRA_RESET_AUTO_SYNC, true);
                    intent.putExtra(SyncActivity.EXTRA_AUTO_FINISH, true);
                    startActivityForResult(intent, REQUEST_SYNC);
                } else if (loginr.get(0).getErrorInfo().toString().contains("查看报表")) {


                    new UserSelection().delete(getContentResolver());
                    mAuthenticator.setAuthenticatedUser(username);

                    chartPreferences = getSharedPreferences("chart", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor chartPf = chartPreferences.edit();

                    chartPf.putString(username, "chart");
                    chartPf.commit();

                    final Intent service = new Intent(this, SyncService.class);
                    service.putExtra(SyncService.STOP_ALARM, true);
                    startService(service);

                    final Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    new AlertDialog.Builder(this)
                            .setIcon(R.drawable.ic_lock_black_24dp)
                            .setTitle(R.string.error_login_failed)
                            .setMessage(loginr.get(0).getErrorInfo())
                            .setPositiveButton(R.string.ok, null).show();
                }
            } else if (loginr.size() < 1) {
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.ic_lock_black_24dp)
                        .setTitle(R.string.error_login_failed)
                        .setMessage("用户名或密码错误")
                        .setPositiveButton(R.string.ok, null).show();
            } else {
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.ic_lock_black_24dp)
                        .setTitle(R.string.error_login_failed)
                        .setMessage("用户名或密码错误")
                        .setPositiveButton(R.string.ok, null).show();
            }

        }, (err) -> {
            progress.dismiss();
            LogUtil.error(TAG, err.getMessage(), err);
            if (err instanceof JsonDataException) {
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.ic_lock_black_24dp)
                        .setTitle(R.string.error_login_failed)
                        .setMessage(R.string.error_invalidate_authentication)
                        .setPositiveButton(R.string.ok, null).show();
            } else {
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.ic_info_black_24dp)
                        .setTitle(R.string.error_login_failed)
                        .setMessage(R.string.error_service_online)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                offlineAuth(username, password);
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .show();
            }
        });
    }

    private void offlineAuth(String username, String password) {
        if (mAuthenticator.offlineAuth(username, password)) {

            final UserCursor cur = new UserSelection()
                    .userName("查看报表").query(getApplicationContext());

            if(cur.getCount() > 0){
                final Intent intent = new Intent(this, Main4ChartForWebActivity.class);
                startActivity(intent);
                finish();
            }else{
                final Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                finish();
            }

        } else {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_lock_black_24dp)
                    .setTitle(R.string.error_login_failed)
                    .setMessage(R.string.error_invalidate_authentication)
                    .setPositiveButton(R.string.ok, null)
                    .show();
        }
    }


    private void clearShowMSGSharedPreference() {
        SharedPreferences loginPreferences = getSharedPreferences("show_msg_sp", Activity.MODE_PRIVATE);
        SharedPreferences.Editor show_msg_ed = loginPreferences.edit();
        show_msg_ed.clear();
        show_msg_ed.commit();
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs.edit().putLong(PREF_DEVICES_LAST_SYNC_TIME, 0).commit();
        prefs.edit().putLong(PREF_PARAMS_LAST_SYNC_TIME, 0).commit();
        prefs.edit().putLong(PREF_MATERIALS_LAST_SYNC_TIME, 0).commit();
    }

    @TargetApi(Build.VERSION_CODES.M) @Override
      public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
            case 1:
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    try {
                        login(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

            case 2:
                if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                    try {
                        Intent intent = new Intent(LoginActivity.this, TService.class);
                        startService(intent);
                        checkSoftwareUpdates();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    finish();
                }
                break;
        }
    }



    public void requestPermission(int type) {

            if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) && (type == 1)) {

                    new AlertDialog.Builder(this)
                            .setIcon(R.mipmap.ic_launcher)
                            .setTitle("温馨提示")
                            .setMessage("需要使用存储设备权限，请赋予该权限！")
                            .setPositiveButton("赋予权限", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    ActivityCompat.requestPermissions(LoginActivity.this,
                                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                            type);
                                }
                            }).setNegativeButton("取消", null)
                            .show();


                } else {

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            type);
                }
            }else{
                try {
                    login(type);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    public void requestCallPermission() {

        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                    Manifest.permission.READ_PHONE_STATE) ) {

                new AlertDialog.Builder(this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle("温馨提示")
                        .setMessage("需要使用拨打电话权限，请赋予该权限！")
                        .setPositiveButton("赋予权限", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ActivityCompat.requestPermissions(LoginActivity.this,
                                        new String[]{Manifest.permission.READ_PHONE_STATE},2);
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                        .show();


            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        2);
            }
        }else{
            try {
                Intent intent = new Intent(LoginActivity.this, TService.class);
                startService(intent);

                checkSoftwareUpdates();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }

    private void login(int type) throws Exception{
        switch (type){
            case 1:

                final String username = mEditUsername.getText().toString();
                final String password = mEditPassword.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    mInputUsername.setErrorEnabled(true);
                    mInputUsername.setError(getString(R.string.error_required));
                } else if (TextUtils.isEmpty(password)) {
                    mInputPassword.setErrorEnabled(true);
                    mInputPassword.setError(getString(R.string.error_required));
                } else {

                    progress = new ProgressDialog(LoginActivity.this);
                    progress.setCancelable(false);
                    progress.setCanceledOnTouchOutside(false);
                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progress.setMessage(getString(R.string.signing_in));
                    progress.setIndeterminate(true);
                    progress.show();

                    getPreferences(MODE_PRIVATE).edit()
                            .putString(PREF_LAST_USER, username)
                            .apply();
                    onlineAuth(username, password);
                }
                break;
        }
    }

}
