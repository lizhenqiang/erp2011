package cc.xingyan.android.afc.provider.yunshuhead;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.YunshuProvider;
import cc.xingyan.android.afc.provider.yunshukuwei.YunshuKuweiColumns;
import cc.xingyan.android.afc.provider.yunshuline.YunshuLineColumns;

/**
 * Columns for the {@code yunshu_head} table.
 */
public class YunshuHeadColumns implements BaseColumns {
    public static final String TABLE_NAME = "yunshu_head";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.yunshu/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String TRANSPORT_TASK_ID = "transport_task_id";

    public static final String TRANSPORT_TASK_STATE = "transport_task_state";

    public static final String CREATE_DATE = "create_date";

    public static final String MAINTENANCE_TYPE_ID = "maintenance_type_id";

    public static final String PLAN_BY = "plan_by";

    public static final String PACK_NUMBER = "pack_number";

    public static final String SEND_WAREHOUSE_NO = "send_warehouse_no";

    public static final String RECEIVE_WAREHOUSE_NO = "receive_warehouse_no";

    public static final String TRANSPORT_TASK_TYPE = "transport_task_type";

    public static final String KEEP_COL1 = "keep_col1";

    public static final String KEEP_COL2 = "keep_col2";

    public static final String KEEP_COL3 = "keep_col3";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            TRANSPORT_TASK_ID,
            TRANSPORT_TASK_STATE,
            CREATE_DATE,
            MAINTENANCE_TYPE_ID,
            PLAN_BY,
            PACK_NUMBER,
            SEND_WAREHOUSE_NO,
            RECEIVE_WAREHOUSE_NO,
            TRANSPORT_TASK_TYPE,
            KEEP_COL1,
            KEEP_COL2,
            KEEP_COL3
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(TRANSPORT_TASK_ID) || c.contains("." + TRANSPORT_TASK_ID)) return true;
            if (c.equals(TRANSPORT_TASK_STATE) || c.contains("." + TRANSPORT_TASK_STATE)) return true;
            if (c.equals(CREATE_DATE) || c.contains("." + CREATE_DATE)) return true;
            if (c.equals(MAINTENANCE_TYPE_ID) || c.contains("." + MAINTENANCE_TYPE_ID)) return true;
            if (c.equals(PLAN_BY) || c.contains("." + PLAN_BY)) return true;
            if (c.equals(PACK_NUMBER) || c.contains("." + PACK_NUMBER)) return true;
            if (c.equals(SEND_WAREHOUSE_NO) || c.contains("." + SEND_WAREHOUSE_NO)) return true;
            if (c.equals(RECEIVE_WAREHOUSE_NO) || c.contains("." + RECEIVE_WAREHOUSE_NO)) return true;
            if (c.equals(TRANSPORT_TASK_TYPE) || c.contains("." + TRANSPORT_TASK_TYPE)) return true;
            if (c.equals(KEEP_COL1) || c.contains("." + KEEP_COL1)) return true;
            if (c.equals(KEEP_COL2) || c.contains("." + KEEP_COL2)) return true;
            if (c.equals(KEEP_COL3) || c.contains("." + KEEP_COL3)) return true;
        }
        return false;
    }

}
