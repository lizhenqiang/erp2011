package cc.xingyan.android.afc.provider.yunshukuwei;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.YunshuProvider;
import cc.xingyan.android.afc.provider.yunshuhead.YunshuHeadColumns;
import cc.xingyan.android.afc.provider.yunshuline.YunshuLineColumns;

/**
 * Columns for the {@code yunshu_kuwei} table.
 */
public class YunshuKuweiColumns implements BaseColumns {
    public static final String TABLE_NAME = "yunshu_kuwei";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.yunshu/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String TRANSPORT_TASK_ID = "transport_task_id";

    public static final String WAREHOUSE_NO = "warehouse_no";

    public static final String WAREHOUSE_NAME = "warehouse_name";

    public static final String WAREHOUSE_TYPE = "warehouse_type";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            TRANSPORT_TASK_ID,
            WAREHOUSE_NO,
            WAREHOUSE_NAME,
            WAREHOUSE_TYPE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(TRANSPORT_TASK_ID) || c.contains("." + TRANSPORT_TASK_ID)) return true;
            if (c.equals(WAREHOUSE_NO) || c.contains("." + WAREHOUSE_NO)) return true;
            if (c.equals(WAREHOUSE_NAME) || c.contains("." + WAREHOUSE_NAME)) return true;
            if (c.equals(WAREHOUSE_TYPE) || c.contains("." + WAREHOUSE_TYPE)) return true;
        }
        return false;
    }

}
