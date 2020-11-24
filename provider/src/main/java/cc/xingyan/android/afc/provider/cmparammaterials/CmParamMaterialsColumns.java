package cc.xingyan.android.afc.provider.cmparammaterials;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.CmProvider;
import cc.xingyan.android.afc.provider.cmmaterial.CmMaterialColumns;
import cc.xingyan.android.afc.provider.cmmaterialrow.CmMaterialRowColumns;
import cc.xingyan.android.afc.provider.cmspareparts.CmSparePartsColumns;
import cc.xingyan.android.afc.provider.cmwork.CmWorkColumns;

/**
 * Columns for the {@code cm_param_materials} table.
 */
public class CmParamMaterialsColumns implements BaseColumns {
    public static final String TABLE_NAME = "cm_param_materials";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.cm/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String CODE = "code";

    public static final String NAME = "name";

    public static final String LINE = "line";

    public static final String DEVICE = "device";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            CODE,
            NAME,
            LINE,
            DEVICE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(CODE) || c.contains("." + CODE)) return true;
            if (c.equals(NAME) || c.contains("." + NAME)) return true;
            if (c.equals(LINE) || c.contains("." + LINE)) return true;
            if (c.equals(DEVICE) || c.contains("." + DEVICE)) return true;
        }
        return false;
    }

}
