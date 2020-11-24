package cc.xingyan.android.afc.util;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import cc.xingyan.android.afc.AfcApplication;

/**
 * Created by YuJiadeng on 2016/8/11.
 */
public class NetUtil {

    public static String RECE_DATA_ACTION = "com.android.scanservice.scancontext";


    public static boolean isNetworkAvailable(Activity activity) {
//        Context context = activity.getApplicationContext();
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        if (connectivityManager == null){
//            return false;
//        }else {
//            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
//
//            if (networkInfo != null && networkInfo.length > 0) {
//                for (int i = 0; i < networkInfo.length; i++) {
//                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;

        Context context = activity.getApplicationContext();

        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(Build.VERSION.SDK_INT>=23) {
            //获取网络属性
            NetworkCapabilities networkCapabilities = mConnectivityManager.getNetworkCapabilities(mConnectivityManager.getActiveNetwork());
            if (networkCapabilities != null) {

                return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
            }
        }else {
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isConnected();
            }
        }
        return false;
    }



    public static String getUploadFileServerPath(String keyId, String type, String addr, String dateT,String remark){
        String serverPath = AfcApplication.DEFAULT_BASE_URL + "UploadFile/" + keyId + "/" + type + "/"
                + addr + "/" + dateT + "/" + remark;
        return serverPath;
    }


    public static String getUploadFileInfoServerPath(String addr, String dateT){
        String serverPath = AfcApplication.DEFAULT_BASE_URL + "UploadFileInfo/" + addr + "/" + dateT;
        return serverPath;
    }



    public static String getString(String parm){
        if (parm == null) {
            parm = "no_" + parm;
        }
        return parm;
    }
}
