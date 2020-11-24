package cc.xingyan.android.afc.provider.pmresultenum;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.PmProvider;
import cc.xingyan.android.afc.provider.pmwork.PmWorkColumns;
import cc.xingyan.android.afc.provider.pmworkitem.PmWorkItemColumns;

/**
 * Columns for the {@code pm_result_enum} table.
 */
public class PmResultEnumColumns implements BaseColumns {
    public static final String TABLE_NAME = "pm_result_enum";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.pm/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String ITEM_GUID = "item_guid";

    public static final String ORDINAL = "ordinal";

    public static final String VALUE = "value";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            ITEM_GUID,
            ORDINAL,
            VALUE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(ITEM_GUID) || c.contains("." + ITEM_GUID)) return true;
            if (c.equals(ORDINAL) || c.contains("." + ORDINAL)) return true;
            if (c.equals(VALUE) || c.contains("." + VALUE)) return true;
        }
        return false;
    }

}
