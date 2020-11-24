package cc.xingyan.android.afc.provider.user;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.WorkOrderProvider;
import cc.xingyan.android.afc.provider.device.DeviceColumns;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsColumns;
import cc.xingyan.android.afc.provider.paramvalue.ParamValueColumns;
import cc.xingyan.android.afc.provider.workorder.WorkOrderColumns;

/**
 * Columns for the {@code user} table.
 */
public class UserColumns implements BaseColumns {
    public static final String TABLE_NAME = "user";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.work_order/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String ORG_CODE = "org_code";

    public static final String USER_ID = "user_id";

    public static final String USER_NO = "user_no";

    public static final String USER_NAME = "user_name";

    public static final String PASSWORD_MD5 = "password_md5";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            ORG_CODE,
            USER_ID,
            USER_NO,
            USER_NAME,
            PASSWORD_MD5
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(ORG_CODE) || c.contains("." + ORG_CODE)) return true;
            if (c.equals(USER_ID) || c.contains("." + USER_ID)) return true;
            if (c.equals(USER_NO) || c.contains("." + USER_NO)) return true;
            if (c.equals(USER_NAME) || c.contains("." + USER_NAME)) return true;
            if (c.equals(PASSWORD_MD5) || c.contains("." + PASSWORD_MD5)) return true;
        }
        return false;
    }

}
