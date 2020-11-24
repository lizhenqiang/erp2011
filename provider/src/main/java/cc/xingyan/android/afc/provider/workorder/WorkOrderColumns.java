package cc.xingyan.android.afc.provider.workorder;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.WorkOrderProvider;
import cc.xingyan.android.afc.provider.device.DeviceColumns;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsColumns;
import cc.xingyan.android.afc.provider.paramvalue.ParamValueColumns;
import cc.xingyan.android.afc.provider.user.UserColumns;

/**
 * Columns for the {@code work_order} table.
 */
public class WorkOrderColumns implements BaseColumns {
    public static final String TABLE_NAME = "work_order";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.work_order/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String USER_ID = "user_id";

    public static final String GUID = "guid";

    public static final String NO = "no";

    public static final String DEVICE_CODE = "device_code";

    public static final String DEVICE_NAME = "device_name";

    public static final String FAULT_DESCRIPTION_TEXT = "fault_description_text";

    public static final String FAULT_DESCRIPTION_CODE = "fault_description_code";

    public static final String FAULT_TYPE_TEXT = "fault_type_text";

    public static final String FAULT_TYPE_CODE = "fault_type_code";

    public static final String REPORTER_TYPE_TEXT = "reporter_type_text";

    public static final String REPORTER_TYPE_CODE = "reporter_type_code";

    public static final String REPORTER = "reporter";

    public static final String FAULT_NOTE = "fault_note";

    public static final String FAULT_CAUSE_TEXT = "fault_cause_text";

    public static final String FAULT_CAUSE_CODE = "fault_cause_code";

    public static final String FAULT_START_TIME = "fault_start_time";

    public static final String OPERATION_TEXT = "operation_text";

    public static final String OPERATION_CODE = "operation_code";

    public static final String OPERATION_RESULT_TEXT = "operation_result_text";

    public static final String OPERATION_RESULT_CODE = "operation_result_code";

    public static final String OPERATION_NOTE = "operation_note";

    public static final String OPERATOR_TEXT = "operator_text";

    public static final String OPERATOR_CODE = "operator_code";

    public static final String OPERATION_START_TIME = "operation_start_time";

    public static final String OPERATION_END_TIME = "operation_end_time";

    public static final String FORM_FLAG_TEXT = "form_flag_text";

    public static final String FORM_FLAG_CODE = "form_flag_code";

    public static final String SYNC_STATUS = "sync_status";

    public static final String LAST_MODIFIED = "last_modified";

    public static final String DELETE_PENDING = "delete_pending";

    public static final String UPLOAD_PENDING = "upload_pending";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            USER_ID,
            GUID,
            NO,
            DEVICE_CODE,
            DEVICE_NAME,
            FAULT_DESCRIPTION_TEXT,
            FAULT_DESCRIPTION_CODE,
            FAULT_TYPE_TEXT,
            FAULT_TYPE_CODE,
            REPORTER_TYPE_TEXT,
            REPORTER_TYPE_CODE,
            REPORTER,
            FAULT_NOTE,
            FAULT_CAUSE_TEXT,
            FAULT_CAUSE_CODE,
            FAULT_START_TIME,
            OPERATION_TEXT,
            OPERATION_CODE,
            OPERATION_RESULT_TEXT,
            OPERATION_RESULT_CODE,
            OPERATION_NOTE,
            OPERATOR_TEXT,
            OPERATOR_CODE,
            OPERATION_START_TIME,
            OPERATION_END_TIME,
            FORM_FLAG_TEXT,
            FORM_FLAG_CODE,
            SYNC_STATUS,
            LAST_MODIFIED,
            DELETE_PENDING,
            UPLOAD_PENDING
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(USER_ID) || c.contains("." + USER_ID)) return true;
            if (c.equals(GUID) || c.contains("." + GUID)) return true;
            if (c.equals(NO) || c.contains("." + NO)) return true;
            if (c.equals(DEVICE_CODE) || c.contains("." + DEVICE_CODE)) return true;
            if (c.equals(DEVICE_NAME) || c.contains("." + DEVICE_NAME)) return true;
            if (c.equals(FAULT_DESCRIPTION_TEXT) || c.contains("." + FAULT_DESCRIPTION_TEXT)) return true;
            if (c.equals(FAULT_DESCRIPTION_CODE) || c.contains("." + FAULT_DESCRIPTION_CODE)) return true;
            if (c.equals(FAULT_TYPE_TEXT) || c.contains("." + FAULT_TYPE_TEXT)) return true;
            if (c.equals(FAULT_TYPE_CODE) || c.contains("." + FAULT_TYPE_CODE)) return true;
            if (c.equals(REPORTER_TYPE_TEXT) || c.contains("." + REPORTER_TYPE_TEXT)) return true;
            if (c.equals(REPORTER_TYPE_CODE) || c.contains("." + REPORTER_TYPE_CODE)) return true;
            if (c.equals(REPORTER) || c.contains("." + REPORTER)) return true;
            if (c.equals(FAULT_NOTE) || c.contains("." + FAULT_NOTE)) return true;
            if (c.equals(FAULT_CAUSE_TEXT) || c.contains("." + FAULT_CAUSE_TEXT)) return true;
            if (c.equals(FAULT_CAUSE_CODE) || c.contains("." + FAULT_CAUSE_CODE)) return true;
            if (c.equals(FAULT_START_TIME) || c.contains("." + FAULT_START_TIME)) return true;
            if (c.equals(OPERATION_TEXT) || c.contains("." + OPERATION_TEXT)) return true;
            if (c.equals(OPERATION_CODE) || c.contains("." + OPERATION_CODE)) return true;
            if (c.equals(OPERATION_RESULT_TEXT) || c.contains("." + OPERATION_RESULT_TEXT)) return true;
            if (c.equals(OPERATION_RESULT_CODE) || c.contains("." + OPERATION_RESULT_CODE)) return true;
            if (c.equals(OPERATION_NOTE) || c.contains("." + OPERATION_NOTE)) return true;
            if (c.equals(OPERATOR_TEXT) || c.contains("." + OPERATOR_TEXT)) return true;
            if (c.equals(OPERATOR_CODE) || c.contains("." + OPERATOR_CODE)) return true;
            if (c.equals(OPERATION_START_TIME) || c.contains("." + OPERATION_START_TIME)) return true;
            if (c.equals(OPERATION_END_TIME) || c.contains("." + OPERATION_END_TIME)) return true;
            if (c.equals(FORM_FLAG_TEXT) || c.contains("." + FORM_FLAG_TEXT)) return true;
            if (c.equals(FORM_FLAG_CODE) || c.contains("." + FORM_FLAG_CODE)) return true;
            if (c.equals(SYNC_STATUS) || c.contains("." + SYNC_STATUS)) return true;
            if (c.equals(LAST_MODIFIED) || c.contains("." + LAST_MODIFIED)) return true;
            if (c.equals(DELETE_PENDING) || c.contains("." + DELETE_PENDING)) return true;
            if (c.equals(UPLOAD_PENDING) || c.contains("." + UPLOAD_PENDING)) return true;
        }
        return false;
    }

}
