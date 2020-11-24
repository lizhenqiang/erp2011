package cc.xingyan.android.afc.provider.pankureport;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.KucunjianProvider;

/**
 * Columns for the {@code panku_report} table.
 */
public class PankuReportColumns implements BaseColumns {
    public static final String TABLE_NAME = "panku_report";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.kucunjian/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String USER_ID = "user_id";

    public static final String REPORT_NO = "report_no";

    public static final String PART_NO = "part_no";

    public static final String PART_NAME = "part_name";

    public static final String ACTUAL_AMOUNT = "actual_amount";

    public static final String LOTBATCH_NO = "lotBatch_no";

    public static final String LINE_NO = "line_no";

    public static final String PART_SEQ = "part_seq";

    public static final String WAREHOUSE_NO = "warehouse_no";

    public static final String WAREHOUSE_NAME = "warehouse_name";

    public static final String PANDIAN_MARK = "pandian_mark";

    public static final String PANDIAN_TIME = "pandian_time";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            USER_ID,
            REPORT_NO,
            PART_NO,
            PART_NAME,
            ACTUAL_AMOUNT,
            LOTBATCH_NO,
            LINE_NO,
            PART_SEQ,
            WAREHOUSE_NO,
            WAREHOUSE_NAME,
            PANDIAN_MARK,
            PANDIAN_TIME
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(USER_ID) || c.contains("." + USER_ID)) return true;
            if (c.equals(REPORT_NO) || c.contains("." + REPORT_NO)) return true;
            if (c.equals(PART_NO) || c.contains("." + PART_NO)) return true;
            if (c.equals(PART_NAME) || c.contains("." + PART_NAME)) return true;
            if (c.equals(ACTUAL_AMOUNT) || c.contains("." + ACTUAL_AMOUNT)) return true;
            if (c.equals(LOTBATCH_NO) || c.contains("." + LOTBATCH_NO)) return true;
            if (c.equals(LINE_NO) || c.contains("." + LINE_NO)) return true;
            if (c.equals(PART_SEQ) || c.contains("." + PART_SEQ)) return true;
            if (c.equals(WAREHOUSE_NO) || c.contains("." + WAREHOUSE_NO)) return true;
            if (c.equals(WAREHOUSE_NAME) || c.contains("." + WAREHOUSE_NAME)) return true;
            if (c.equals(PANDIAN_MARK) || c.contains("." + PANDIAN_MARK)) return true;
            if (c.equals(PANDIAN_TIME) || c.contains("." + PANDIAN_TIME)) return true;
        }
        return false;
    }

}
