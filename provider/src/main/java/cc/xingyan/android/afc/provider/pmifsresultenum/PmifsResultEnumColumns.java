package cc.xingyan.android.afc.provider.pmifsresultenum;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.PmifsProvider;
import cc.xingyan.android.afc.provider.pmmaterial.PmMaterialColumns;
import cc.xingyan.android.afc.provider.pmmaterialrow.PmMaterialRowColumns;
import cc.xingyan.android.afc.provider.pmparammaterials.PmParamMaterialsColumns;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkColumns;
import cc.xingyan.android.afc.provider.pmifsworkitem.PmifsWorkItemColumns;

/**
 * Columns for the {@code pmifs_result_enum} table.
 */
public class PmifsResultEnumColumns implements BaseColumns {
    public static final String TABLE_NAME = "pmifs_result_enum";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.pmifs/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String ITEM_GUID = "item_guid";

    public static final String SN = "sn";

    public static final String VALUE = "value";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            ITEM_GUID,
            SN,
            VALUE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(ITEM_GUID) || c.contains("." + ITEM_GUID)) return true;
            if (c.equals(SN) || c.contains("." + SN)) return true;
            if (c.equals(VALUE) || c.contains("." + VALUE)) return true;
        }
        return false;
    }

}
