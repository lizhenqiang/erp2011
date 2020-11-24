package cc.xingyan.android.afc.provider.cmmaterial;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.CmProvider;
import cc.xingyan.android.afc.provider.cmmaterialrow.CmMaterialRowColumns;
import cc.xingyan.android.afc.provider.cmparammaterials.CmParamMaterialsColumns;
import cc.xingyan.android.afc.provider.cmspareparts.CmSparePartsColumns;
import cc.xingyan.android.afc.provider.cmwork.CmWorkColumns;

/**
 * Columns for the {@code cm_material} table.
 */
public class CmMaterialColumns implements BaseColumns {
    public static final String TABLE_NAME = "cm_material";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.cm/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String CM_ORDER_ID = "cm_order_id";

    public static final String MATERIAL_ORDER_ID = "material_order_id";

    public static final String USER = "user";

    public static final String DEPARTMENT = "department";

    public static final String INT_CUSTOMER_NO = "int_customer_no";

    public static final String ENTER_DATE = "enter_date";

    public static final String DUE_DATE = "due_date";

    public static final String GUID = "guid";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            CM_ORDER_ID,
            MATERIAL_ORDER_ID,
            USER,
            DEPARTMENT,
            INT_CUSTOMER_NO,
            ENTER_DATE,
            DUE_DATE,
            GUID
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(CM_ORDER_ID) || c.contains("." + CM_ORDER_ID)) return true;
            if (c.equals(MATERIAL_ORDER_ID) || c.contains("." + MATERIAL_ORDER_ID)) return true;
            if (c.equals(USER) || c.contains("." + USER)) return true;
            if (c.equals(DEPARTMENT) || c.contains("." + DEPARTMENT)) return true;
            if (c.equals(INT_CUSTOMER_NO) || c.contains("." + INT_CUSTOMER_NO)) return true;
            if (c.equals(ENTER_DATE) || c.contains("." + ENTER_DATE)) return true;
            if (c.equals(DUE_DATE) || c.contains("." + DUE_DATE)) return true;
            if (c.equals(GUID) || c.contains("." + GUID)) return true;
        }
        return false;
    }

}
