package cc.xingyan.android.afc.provider.diworkitem;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.DiProvider;
import cc.xingyan.android.afc.provider.diwork.DiWorkColumns;

/**
 * Columns for the {@code di_work_item} table.
 */
public class DiWorkItemColumns implements BaseColumns {
    public static final String TABLE_NAME = "di_work_item";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.di/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String WORK_GUID = "work_guid";

    public static final String STATION_ID = "station_id";

    public static final String STATION_DESCRIPTION = "station_description";

    public static final String DEVICE_ID = "device_id";

    public static final String DEVICE_DESCRIPTION = "device_description";

    public static final String DEVICE_SYSTEM = "device_system";

    public static final String GUID = "guid";

    public static final String RESULT_ORDINAL = "result_ordinal";

    public static final String RESULT_VALUE = "result_value";

    public static final String LAST_MODIFIED = "last_modified";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            WORK_GUID,
            STATION_ID,
            STATION_DESCRIPTION,
            DEVICE_ID,
            DEVICE_DESCRIPTION,
            DEVICE_SYSTEM,
            GUID,
            RESULT_ORDINAL,
            RESULT_VALUE,
            LAST_MODIFIED
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(WORK_GUID) || c.contains("." + WORK_GUID)) return true;
            if (c.equals(STATION_ID) || c.contains("." + STATION_ID)) return true;
            if (c.equals(STATION_DESCRIPTION) || c.contains("." + STATION_DESCRIPTION)) return true;
            if (c.equals(DEVICE_ID) || c.contains("." + DEVICE_ID)) return true;
            if (c.equals(DEVICE_DESCRIPTION) || c.contains("." + DEVICE_DESCRIPTION)) return true;
            if (c.equals(DEVICE_SYSTEM) || c.contains("." + DEVICE_SYSTEM)) return true;
            if (c.equals(GUID) || c.contains("." + GUID)) return true;
            if (c.equals(RESULT_ORDINAL) || c.contains("." + RESULT_ORDINAL)) return true;
            if (c.equals(RESULT_VALUE) || c.contains("." + RESULT_VALUE)) return true;
            if (c.equals(LAST_MODIFIED) || c.contains("." + LAST_MODIFIED)) return true;
        }
        return false;
    }

}
