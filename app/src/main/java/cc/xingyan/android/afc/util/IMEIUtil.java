package cc.xingyan.android.afc.util;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class IMEIUtil {
    public static String getIMEI(Context context) {

        String deviceId="";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q){
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
             deviceId = telephonyManager.getDeviceId();
        }else {
            deviceId = Settings.System.getString(
                    context.getContentResolver(), Settings.Secure.ANDROID_ID);
        }


        return  deviceId;
    }
}
