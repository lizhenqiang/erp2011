package cc.xingyan.android.afc.provider.pmifsworkitem;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.PmifsProvider;
import cc.xingyan.android.afc.provider.pmmaterial.PmMaterialColumns;
import cc.xingyan.android.afc.provider.pmmaterialrow.PmMaterialRowColumns;
import cc.xingyan.android.afc.provider.pmparammaterials.PmParamMaterialsColumns;
import cc.xingyan.android.afc.provider.pmifsresultenum.PmifsResultEnumColumns;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkColumns;

/**
 * Columns for the {@code pmifs_work_item} table.
 */
public class PmifsWorkItemColumns implements BaseColumns {
    public static final String TABLE_NAME = "pmifs_work_item";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.pmifs/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String ORDER_ID = "order_id";

    public static final String WORK_GUID = "work_guid";

    public static final String PACKAGE_ID = "package_id";

    public static final String PACKAGE_DES = "package_des";

    public static final String GUID = "guid";

    public static final String ITEM_ID = "item_id";

    public static final String ITEM_DES = "item_des";

    public static final String WORK_SN = "work_sn";

    public static final String RESULT_TYPE = "result_type";

    public static final String RESULT_MIN_VALUE = "result_min_value";

    public static final String RESULT_MAX_VALUE = "result_max_value";

    public static final String RESULT_DEFAULT_VALUE = "result_default_value";

    public static final String RESULT_VALUE_UNIT = "result_value_unit";

    public static final String RESULT_ENUM_ORDINAL = "result_enum_ordinal";

    public static final String RESULT_VALUE = "result_value";

    public static final String LAST_MODIFIED = "last_modified";

    public static final String REQUIRED = "required";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            ORDER_ID,
            WORK_GUID,
            PACKAGE_ID,
            PACKAGE_DES,
            GUID,
            ITEM_ID,
            ITEM_DES,
            WORK_SN,
            RESULT_TYPE,
            RESULT_MIN_VALUE,
            RESULT_MAX_VALUE,
            RESULT_DEFAULT_VALUE,
            RESULT_VALUE_UNIT,
            RESULT_ENUM_ORDINAL,
            RESULT_VALUE,
            LAST_MODIFIED,
            REQUIRED
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(ORDER_ID) || c.contains("." + ORDER_ID)) return true;
            if (c.equals(WORK_GUID) || c.contains("." + WORK_GUID)) return true;
            if (c.equals(PACKAGE_ID) || c.contains("." + PACKAGE_ID)) return true;
            if (c.equals(PACKAGE_DES) || c.contains("." + PACKAGE_DES)) return true;
            if (c.equals(GUID) || c.contains("." + GUID)) return true;
            if (c.equals(ITEM_ID) || c.contains("." + ITEM_ID)) return true;
            if (c.equals(ITEM_DES) || c.contains("." + ITEM_DES)) return true;
            if (c.equals(WORK_SN) || c.contains("." + WORK_SN)) return true;
            if (c.equals(RESULT_TYPE) || c.contains("." + RESULT_TYPE)) return true;
            if (c.equals(RESULT_MIN_VALUE) || c.contains("." + RESULT_MIN_VALUE)) return true;
            if (c.equals(RESULT_MAX_VALUE) || c.contains("." + RESULT_MAX_VALUE)) return true;
            if (c.equals(RESULT_DEFAULT_VALUE) || c.contains("." + RESULT_DEFAULT_VALUE)) return true;
            if (c.equals(RESULT_VALUE_UNIT) || c.contains("." + RESULT_VALUE_UNIT)) return true;
            if (c.equals(RESULT_ENUM_ORDINAL) || c.contains("." + RESULT_ENUM_ORDINAL)) return true;
            if (c.equals(RESULT_VALUE) || c.contains("." + RESULT_VALUE)) return true;
            if (c.equals(LAST_MODIFIED) || c.contains("." + LAST_MODIFIED)) return true;
            if (c.equals(REQUIRED) || c.contains("." + REQUIRED)) return true;
        }
        return false;
    }

}
