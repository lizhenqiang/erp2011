package cc.xingyan.android.afc.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.inject.Dagger;

import static android.os.Process.killProcess;
import static android.os.Process.myPid;

/**
 * Created by YuJiadeng on 2017/4/2.
 */

public class MyCrashHandler implements Thread.UncaughtExceptionHandler {

    @Inject Authenticator mAuthenticator;

    private static final String TAG = "CrashHandler";
    private static final boolean DEBUG = true;

    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/AFC_Log_Crash/";
    private static final String FILE_NAME = "";

    private static final String FILE_NAME_SUFFIX = ".trace";

    private static MyCrashHandler sInstance = new MyCrashHandler();

    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;

    private Context mContext;

    private MyCrashHandler() {
        Dagger.inject(this);
    }

    public static MyCrashHandler getInstance() {
        return sInstance;
    }


    public void init(Context context) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        try {
            dumpExceptionToSDCard(ex);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ex.printStackTrace();

        if (mDefaultCrashHandler != null) {
            mDefaultCrashHandler.uncaughtException(thread, ex);
        } else {
            killProcess(myPid());
        }

    }

    private void dumpExceptionToSDCard(Throwable ex) throws IOException {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (DEBUG) {
                LogUtil.info(TAG, "sdcard unmounted,skip dump exception");
                return;
            }
        }

        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyyMMdd_HH_mm_ss").format(new Date(current));
        String userID = mAuthenticator.getAuthenticatedUserId();

        File file = new File(PATH + FILE_NAME + time + "_" + userID + FILE_NAME_SUFFIX);
        if(!file.exists()){
            file.createNewFile();
        }

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println(time);
            dumpPhoneInfo(pw);
            pw.println();
            ex.printStackTrace(pw);
            pw.close();
            toUpLoadFile(file);

        } catch (Exception e) {
            LogUtil.info(TAG, "dump crash info failed");
        }
    }

    private void dumpPhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException {
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        pw.print("App Version: ");
        pw.print(pi.versionName);
        pw.print('_');
        pw.println(pi.versionCode);
        pw.print("OS Version: ");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);
        pw.print("Vendor: ");
        pw.println(Build.MANUFACTURER);
        pw.print("Model: ");
        pw.println(Build.MODEL);
        pw.print("CPU ABI: ");
        pw.println(Build.CPU_ABI);
        pw.print("IMEI: ");
        pw.println(getIMEI(mContext));
        pw.print("UerID: ");
        pw.println(mAuthenticator.getAuthenticatedUserId());
    }

    public static String getIMEI(Context context) {
        String imei = "";
        TelephonyManager manager = (TelephonyManager) context
                .getSystemService(Activity.TELEPHONY_SERVICE);
        if (PackageManager.PERMISSION_GRANTED == context.getPackageManager()
                .checkPermission(Manifest.permission.READ_PHONE_STATE,
                        context.getPackageName())) {
            imei = manager.getDeviceId();
        }

        return imei == null || "".equals(imei) ? "isFake" : imei;
    }


    private void toUpLoadFile(File file){
        String lat = TService.lat;
        String lon = TService.lon;

        String upFileAdd = null;
        long current = System.currentTimeMillis();

        if(lat != null && lon != null){
            upFileAdd = lat + "_" + lon;
        }else{
            upFileAdd = "no_add_info";
        }

        String dateT = current + "";

        String serverPath = NetUtil.getUploadFileInfoServerPath(upFileAdd, dateT) + File.separator + file.getName();


        FileInfoUploadUtil fileInfoUploadUtil = new FileInfoUploadUtil(file.getAbsolutePath(), serverPath, mContext);
        fileInfoUploadUtil.uploadFile();
    }
}
