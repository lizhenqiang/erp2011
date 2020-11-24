package cc.xingyan.android.afc.provider.devicephysics;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.WorkOrderProvider;
import cc.xingyan.android.afc.provider.device.DeviceColumns;
import cc.xingyan.android.afc.provider.paramvalue.ParamValueColumns;
import cc.xingyan.android.afc.provider.user.UserColumns;
import cc.xingyan.android.afc.provider.workorder.WorkOrderColumns;

/**
 * Columns for the {@code device_physics} table.
 */
public class DevicePhysicsColumns implements BaseColumns {
    public static final String TABLE_NAME = "device_physics";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.work_order/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String CODE = "code";

    public static final String CODE_PHYSICS = "code_physics";

    public static final String NAME = "name";

    public static final String TYPE = "type";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            CODE,
            CODE_PHYSICS,
            NAME,
            TYPE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(CODE) || c.contains("." + CODE)) return true;
            if (c.equals(CODE_PHYSICS) || c.contains("." + CODE_PHYSICS)) return true;
            if (c.equals(NAME) || c.contains("." + NAME)) return true;
            if (c.equals(TYPE) || c.contains("." + TYPE)) return true;
        }
        return false;
    }

}
