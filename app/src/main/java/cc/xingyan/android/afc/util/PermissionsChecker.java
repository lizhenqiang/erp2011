package cc.xingyan.android.afc.util;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;


/**
 * Created by YuJiadeng on 2018/6/11.
 * 检查权限的工具类
 * 作者：SpikeKing
 */
public class PermissionsChecker {
    private final Context mContext;

    public PermissionsChecker(Context context) {
        mContext = context.getApplicationContext();
    }

    public boolean lacksPermissions(String... permissions) {
        for (String permission : permissions) {
            if (lacksPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    private boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission) ==
                PackageManager.PERMISSION_DENIED;
    }


}
