package cc.xingyan.android.afc.provider.cmspareparts;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.CmProvider;
import cc.xingyan.android.afc.provider.cmmaterial.CmMaterialColumns;
import cc.xingyan.android.afc.provider.cmmaterialrow.CmMaterialRowColumns;
import cc.xingyan.android.afc.provider.cmparammaterials.CmParamMaterialsColumns;
import cc.xingyan.android.afc.provider.cmwork.CmWorkColumns;

/**
 * Columns for the {@code cm_spare_parts} table.
 */
public class CmSparePartsColumns implements BaseColumns {
    public static final String TABLE_NAME = "cm_spare_parts";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.cm/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String CM_WORK_ID = "cm_work_id";

    public static final String ORDER_ID = "order_id";

    public static final String PART_ID = "part_id";

    public static final String PART_DESCRIPTION = "part_description";

    public static final String APPLY_DATE = "apply_date";

    public static final String INSTALL_DATE = "install_date";

    public static final String OLD_PART_SN = "old_part_sn";

    public static final String NEW_PART_SN = "new_part_sn";

    public static final String SPARE_PART_STATUS = "spare_part_status";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            CM_WORK_ID,
            ORDER_ID,
            PART_ID,
            PART_DESCRIPTION,
            APPLY_DATE,
            INSTALL_DATE,
            OLD_PART_SN,
            NEW_PART_SN,
            SPARE_PART_STATUS
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(CM_WORK_ID) || c.contains("." + CM_WORK_ID)) return true;
            if (c.equals(ORDER_ID) || c.contains("." + ORDER_ID)) return true;
            if (c.equals(PART_ID) || c.contains("." + PART_ID)) return true;
            if (c.equals(PART_DESCRIPTION) || c.contains("." + PART_DESCRIPTION)) return true;
            if (c.equals(APPLY_DATE) || c.contains("." + APPLY_DATE)) return true;
            if (c.equals(INSTALL_DATE) || c.contains("." + INSTALL_DATE)) return true;
            if (c.equals(OLD_PART_SN) || c.contains("." + OLD_PART_SN)) return true;
            if (c.equals(NEW_PART_SN) || c.contains("." + NEW_PART_SN)) return true;
            if (c.equals(SPARE_PART_STATUS) || c.contains("." + SPARE_PART_STATUS)) return true;
        }
        return false;
    }

}
