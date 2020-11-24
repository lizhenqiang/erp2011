package cc.xingyan.android.afc.provider.pmmaterialrow;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.PmifsProvider;
import cc.xingyan.android.afc.provider.pmmaterial.PmMaterialColumns;
import cc.xingyan.android.afc.provider.pmparammaterials.PmParamMaterialsColumns;
import cc.xingyan.android.afc.provider.pmifsresultenum.PmifsResultEnumColumns;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkColumns;
import cc.xingyan.android.afc.provider.pmifsworkitem.PmifsWorkItemColumns;

/**
 * Columns for the {@code pm_material_row} table.
 */
public class PmMaterialRowColumns implements BaseColumns {
    public static final String TABLE_NAME = "pm_material_row";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.pmifs/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String PM_ORDER_ID = "pm_order_id";

    public static final String MATERIAL_ORDER_ID = "material_order_id";

    public static final String MATERIAL_ROW = "material_row";

    public static final String MATERIAL_ID = "material_id";

    public static final String MATERIAL_DESCRIPTION = "material_description";

    public static final String DUE_DATE = "due_date";

    public static final String MATERIAL_COUNT = "material_count";

    public static final String GUID = "guid";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            PM_ORDER_ID,
            MATERIAL_ORDER_ID,
            MATERIAL_ROW,
            MATERIAL_ID,
            MATERIAL_DESCRIPTION,
            DUE_DATE,
            MATERIAL_COUNT,
            GUID
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(PM_ORDER_ID) || c.contains("." + PM_ORDER_ID)) return true;
            if (c.equals(MATERIAL_ORDER_ID) || c.contains("." + MATERIAL_ORDER_ID)) return true;
            if (c.equals(MATERIAL_ROW) || c.contains("." + MATERIAL_ROW)) return true;
            if (c.equals(MATERIAL_ID) || c.contains("." + MATERIAL_ID)) return true;
            if (c.equals(MATERIAL_DESCRIPTION) || c.contains("." + MATERIAL_DESCRIPTION)) return true;
            if (c.equals(DUE_DATE) || c.contains("." + DUE_DATE)) return true;
            if (c.equals(MATERIAL_COUNT) || c.contains("." + MATERIAL_COUNT)) return true;
            if (c.equals(GUID) || c.contains("." + GUID)) return true;
        }
        return false;
    }

}
