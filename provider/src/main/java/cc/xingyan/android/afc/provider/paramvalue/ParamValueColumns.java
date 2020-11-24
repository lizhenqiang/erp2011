package cc.xingyan.android.afc.provider.paramvalue;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.WorkOrderProvider;
import cc.xingyan.android.afc.provider.device.DeviceColumns;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsColumns;
import cc.xingyan.android.afc.provider.user.UserColumns;
import cc.xingyan.android.afc.provider.workorder.WorkOrderColumns;

/**
 * Columns for the {@code param_value} table.
 */
public class ParamValueColumns implements BaseColumns {
    public static final String TABLE_NAME = "param_value";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.work_order/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String CODE = "code";

    public static final String VALUE = "value";

    public static final String TYPE = "type";

    public static final String PARENT_CODE = "parent_code";

    public static final String PARENT_TYPE = "parent_type";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            CODE,
            VALUE,
            TYPE,
            PARENT_CODE,
            PARENT_TYPE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(CODE) || c.contains("." + CODE)) return true;
            if (c.equals(VALUE) || c.contains("." + VALUE)) return true;
            if (c.equals(TYPE) || c.contains("." + TYPE)) return true;
            if (c.equals(PARENT_CODE) || c.contains("." + PARENT_CODE)) return true;
            if (c.equals(PARENT_TYPE) || c.contains("." + PARENT_TYPE)) return true;
        }
        return false;
    }

}
