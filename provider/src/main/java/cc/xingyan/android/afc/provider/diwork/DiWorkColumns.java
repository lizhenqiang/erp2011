package cc.xingyan.android.afc.provider.diwork;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.DiProvider;
import cc.xingyan.android.afc.provider.diworkitem.DiWorkItemColumns;

/**
 * Columns for the {@code di_work} table.
 */
public class DiWorkColumns implements BaseColumns {
    public static final String TABLE_NAME = "di_work";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.di/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String WORK_AREA_ID = "work_area_id";

    public static final String WORK_AREA_DESCRIPTION = "work_area_description";

    public static final String GUID = "guid";

    public static final String WORK_ID = "work_id";

    public static final String DATE = "date";

    public static final String START_TIME = "start_time";

    public static final String END_TIME = "end_time";

    public static final String COMPLETE_TIME = "complete_time";

    public static final String LAST_MODIFIED = "last_modified";

    public static final String STATUS = "status";

    public static final String UPLOAD_PENDING = "upload_pending";

    public static final String OPERATOR = "operator";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            WORK_AREA_ID,
            WORK_AREA_DESCRIPTION,
            GUID,
            WORK_ID,
            DATE,
            START_TIME,
            END_TIME,
            COMPLETE_TIME,
            LAST_MODIFIED,
            STATUS,
            UPLOAD_PENDING,
            OPERATOR
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(WORK_AREA_ID) || c.contains("." + WORK_AREA_ID)) return true;
            if (c.equals(WORK_AREA_DESCRIPTION) || c.contains("." + WORK_AREA_DESCRIPTION)) return true;
            if (c.equals(GUID) || c.contains("." + GUID)) return true;
            if (c.equals(WORK_ID) || c.contains("." + WORK_ID)) return true;
            if (c.equals(DATE) || c.contains("." + DATE)) return true;
            if (c.equals(START_TIME) || c.contains("." + START_TIME)) return true;
            if (c.equals(END_TIME) || c.contains("." + END_TIME)) return true;
            if (c.equals(COMPLETE_TIME) || c.contains("." + COMPLETE_TIME)) return true;
            if (c.equals(LAST_MODIFIED) || c.contains("." + LAST_MODIFIED)) return true;
            if (c.equals(STATUS) || c.contains("." + STATUS)) return true;
            if (c.equals(UPLOAD_PENDING) || c.contains("." + UPLOAD_PENDING)) return true;
            if (c.equals(OPERATOR) || c.contains("." + OPERATOR)) return true;
        }
        return false;
    }

}
