package cc.xingyan.android.afc.provider.yunshuline;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.YunshuProvider;
import cc.xingyan.android.afc.provider.yunshuhead.YunshuHeadColumns;
import cc.xingyan.android.afc.provider.yunshukuwei.YunshuKuweiColumns;

/**
 * Columns for the {@code yunshu_line} table.
 */
public class YunshuLineColumns implements BaseColumns {
    public static final String TABLE_NAME = "yunshu_line";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.yunshu/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String TRANSPORT_TASK_ID = "transport_task_id";

    public static final String LINE_NO = "line_no";

    public static final String PART_NO = "part_no";

    public static final String PART_NAME = "part_name";

    public static final String QUANTITY = "quantity";

    public static final String UNIT = "unit";

    public static final String LOT_BATCH_NO = "lot_batch_no";

    public static final String SERIAL_NO = "serial_no";

    public static final String FORMAT = "format";

    public static final String LINE_TYPE = "line_type";

    public static final String LINE_MARK = "line_mark";

    public static final String KEEP_COL1 = "keep_col1";

    public static final String KEEP_COL2 = "keep_col2";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            TRANSPORT_TASK_ID,
            LINE_NO,
            PART_NO,
            PART_NAME,
            QUANTITY,
            UNIT,
            LOT_BATCH_NO,
            SERIAL_NO,
            FORMAT,
            LINE_TYPE,
            LINE_MARK,
            KEEP_COL1,
            KEEP_COL2
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(TRANSPORT_TASK_ID) || c.contains("." + TRANSPORT_TASK_ID)) return true;
            if (c.equals(LINE_NO) || c.contains("." + LINE_NO)) return true;
            if (c.equals(PART_NO) || c.contains("." + PART_NO)) return true;
            if (c.equals(PART_NAME) || c.contains("." + PART_NAME)) return true;
            if (c.equals(QUANTITY) || c.contains("." + QUANTITY)) return true;
            if (c.equals(UNIT) || c.contains("." + UNIT)) return true;
            if (c.equals(LOT_BATCH_NO) || c.contains("." + LOT_BATCH_NO)) return true;
            if (c.equals(SERIAL_NO) || c.contains("." + SERIAL_NO)) return true;
            if (c.equals(FORMAT) || c.contains("." + FORMAT)) return true;
            if (c.equals(LINE_TYPE) || c.contains("." + LINE_TYPE)) return true;
            if (c.equals(LINE_MARK) || c.contains("." + LINE_MARK)) return true;
            if (c.equals(KEEP_COL1) || c.contains("." + KEEP_COL1)) return true;
            if (c.equals(KEEP_COL2) || c.contains("." + KEEP_COL2)) return true;
        }
        return false;
    }

}
