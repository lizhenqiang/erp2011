package cc.xingyan.android.afc.provider.pmwork;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.PmProvider;
import cc.xingyan.android.afc.provider.pmresultenum.PmResultEnumColumns;
import cc.xingyan.android.afc.provider.pmworkitem.PmWorkItemColumns;

/**
 * Columns for the {@code pm_work} table.
 */
public class PmWorkColumns implements BaseColumns {
    public static final String TABLE_NAME = "pm_work";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.pm/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String USER_ID = "user_id";

    public static final String GUID = "guid";

    public static final String JOB_ID = "job_id";

    public static final String JOB_DESCRIPTION = "job_description";

    public static final String WORK_ID = "work_id";

    public static final String WORK_KIND = "work_kind";

    public static final String DEVICE_CODE = "device_code";

    public static final String DEVICE_DESCRIPTION = "device_description";

    public static final String EXECUTOR_ID = "executor_id";

    public static final String EXECUTOR_DESCRIPTION = "executor_description";

    public static final String START_TIME = "start_time";

    public static final String END_TIME = "end_time";

    public static final String COMPLETE_TIME = "complete_time";

    public static final String LAST_MODIFIED = "last_modified";

    public static final String STATUS = "status";

    public static final String UPLOAD_PENDING = "upload_pending";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            USER_ID,
            GUID,
            JOB_ID,
            JOB_DESCRIPTION,
            WORK_ID,
            WORK_KIND,
            DEVICE_CODE,
            DEVICE_DESCRIPTION,
            EXECUTOR_ID,
            EXECUTOR_DESCRIPTION,
            START_TIME,
            END_TIME,
            COMPLETE_TIME,
            LAST_MODIFIED,
            STATUS,
            UPLOAD_PENDING
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(USER_ID) || c.contains("." + USER_ID)) return true;
            if (c.equals(GUID) || c.contains("." + GUID)) return true;
            if (c.equals(JOB_ID) || c.contains("." + JOB_ID)) return true;
            if (c.equals(JOB_DESCRIPTION) || c.contains("." + JOB_DESCRIPTION)) return true;
            if (c.equals(WORK_ID) || c.contains("." + WORK_ID)) return true;
            if (c.equals(WORK_KIND) || c.contains("." + WORK_KIND)) return true;
            if (c.equals(DEVICE_CODE) || c.contains("." + DEVICE_CODE)) return true;
            if (c.equals(DEVICE_DESCRIPTION) || c.contains("." + DEVICE_DESCRIPTION)) return true;
            if (c.equals(EXECUTOR_ID) || c.contains("." + EXECUTOR_ID)) return true;
            if (c.equals(EXECUTOR_DESCRIPTION) || c.contains("." + EXECUTOR_DESCRIPTION)) return true;
            if (c.equals(START_TIME) || c.contains("." + START_TIME)) return true;
            if (c.equals(END_TIME) || c.contains("." + END_TIME)) return true;
            if (c.equals(COMPLETE_TIME) || c.contains("." + COMPLETE_TIME)) return true;
            if (c.equals(LAST_MODIFIED) || c.contains("." + LAST_MODIFIED)) return true;
            if (c.equals(STATUS) || c.contains("." + STATUS)) return true;
            if (c.equals(UPLOAD_PENDING) || c.contains("." + UPLOAD_PENDING)) return true;
        }
        return false;
    }

}
