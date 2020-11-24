package cc.xingyan.android.afc.provider.pmifswork;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Columns for the {@code pmifs_work} table.
 */
public class PmifsWorkColumns implements BaseColumns {
    public static final String TABLE_NAME = "pmifs_work";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.pmifs/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String USER_ID = "user_id";

    public static final String GUID = "guid";

    public static final String ORDER_ID = "order_id";

    public static final String PRIORITY_TEXT = "priority_text";

    public static final String PRIORITY_CODE = "priority_code";

    public static final String DEVICE_CODE = "device_code";

    public static final String DEVICE_NAME = "device_name";

    public static final String DEVICE_LOGIC_CODE = "device_logic_code";

    public static final String DEVICE_LOGIC_NAME = "device_logic_name";

    public static final String INSTRUCT = "instruct";

    public static final String PREPARE_MAN_CODE = "prepare_man_code";

    public static final String PREPARE_MAN_TEXT = "prepare_man_text";

    public static final String WORKAREA_TEXT = "workarea_text";

    public static final String WORKAREA_CODE = "workarea_code";

    public static final String APPLY_S_TIME = "apply_s_time";

    public static final String APPLY_F_TIME = "apply_f_time";

    public static final String PLAN_S_TIME = "plan_s_time";

    public static final String PLAN_F_TIME = "plan_f_time";

    public static final String WORK_DETAILS_TEXT = "work_details_text";

    public static final String WORK_DETAILS_CODE = "work_details_code";

    public static final String WORK_DONE_TEXT = "work_done_text";

    public static final String WORK_DONE_CODE = "work_done_code";

    public static final String WORK_NOTE = "work_note";

    public static final String OPERATOR_TEXT = "operator_text";

    public static final String OPERATOR_CODE = "operator_code";

    public static final String OPERATION_START_TIME = "operation_start_time";

    public static final String OPERATION_END_TIME = "operation_end_time";

    public static final String ORDER_RECEIVE_TIME = "order_receive_time";

    public static final String ARRIVE_TIME = "arrive_time";

    public static final String INT_CUSTOMER_NO = "int_customer_no";

    public static final String STATUS_BACKSTAGE = "status_backstage";

    public static final String STATUS = "status";

    public static final String LAST_MODIFIED = "last_modified";

    public static final String DELETE_PENDING = "delete_pending";

    public static final String UPLOAD_PENDING = "upload_pending";
    public static final String WORK_TYPE_ID = "work_type_id";

    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            USER_ID,
            GUID,
            ORDER_ID,
            PRIORITY_TEXT,
            PRIORITY_CODE,
            DEVICE_CODE,
            DEVICE_NAME,
            DEVICE_LOGIC_CODE,
            DEVICE_LOGIC_NAME,
            INSTRUCT,
            PREPARE_MAN_CODE,
            PREPARE_MAN_TEXT,
            WORKAREA_TEXT,
            WORKAREA_CODE,
            APPLY_S_TIME,
            APPLY_F_TIME,
            PLAN_S_TIME,
            PLAN_F_TIME,
            WORK_DETAILS_TEXT,
            WORK_DETAILS_CODE,
            WORK_DONE_TEXT,
            WORK_DONE_CODE,
            WORK_NOTE,
            OPERATOR_TEXT,
            OPERATOR_CODE,
            OPERATION_START_TIME,
            OPERATION_END_TIME,
            ORDER_RECEIVE_TIME,
            ARRIVE_TIME,
            INT_CUSTOMER_NO,
            STATUS_BACKSTAGE,
            STATUS,
            LAST_MODIFIED,
            DELETE_PENDING,
            WORK_TYPE_ID,
            UPLOAD_PENDING
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(USER_ID) || c.contains("." + USER_ID)) return true;
            if (c.equals(GUID) || c.contains("." + GUID)) return true;
            if (c.equals(ORDER_ID) || c.contains("." + ORDER_ID)) return true;
            if (c.equals(PRIORITY_TEXT) || c.contains("." + PRIORITY_TEXT)) return true;
            if (c.equals(PRIORITY_CODE) || c.contains("." + PRIORITY_CODE)) return true;
            if (c.equals(DEVICE_CODE) || c.contains("." + DEVICE_CODE)) return true;
            if (c.equals(DEVICE_NAME) || c.contains("." + DEVICE_NAME)) return true;
            if (c.equals(DEVICE_LOGIC_CODE) || c.contains("." + DEVICE_LOGIC_CODE)) return true;
            if (c.equals(DEVICE_LOGIC_NAME) || c.contains("." + DEVICE_LOGIC_NAME)) return true;
            if (c.equals(INSTRUCT) || c.contains("." + INSTRUCT)) return true;
            if (c.equals(PREPARE_MAN_CODE) || c.contains("." + PREPARE_MAN_CODE)) return true;
            if (c.equals(PREPARE_MAN_TEXT) || c.contains("." + PREPARE_MAN_TEXT)) return true;
            if (c.equals(WORKAREA_TEXT) || c.contains("." + WORKAREA_TEXT)) return true;
            if (c.equals(WORKAREA_CODE) || c.contains("." + WORKAREA_CODE)) return true;
            if (c.equals(APPLY_S_TIME) || c.contains("." + APPLY_S_TIME)) return true;
            if (c.equals(APPLY_F_TIME) || c.contains("." + APPLY_F_TIME)) return true;
            if (c.equals(PLAN_S_TIME) || c.contains("." + PLAN_S_TIME)) return true;
            if (c.equals(PLAN_F_TIME) || c.contains("." + PLAN_F_TIME)) return true;
            if (c.equals(WORK_DETAILS_TEXT) || c.contains("." + WORK_DETAILS_TEXT)) return true;
            if (c.equals(WORK_DETAILS_CODE) || c.contains("." + WORK_DETAILS_CODE)) return true;
            if (c.equals(WORK_DONE_TEXT) || c.contains("." + WORK_DONE_TEXT)) return true;
            if (c.equals(WORK_DONE_CODE) || c.contains("." + WORK_DONE_CODE)) return true;
            if (c.equals(WORK_NOTE) || c.contains("." + WORK_NOTE)) return true;
            if (c.equals(OPERATOR_TEXT) || c.contains("." + OPERATOR_TEXT)) return true;
            if (c.equals(OPERATOR_CODE) || c.contains("." + OPERATOR_CODE)) return true;
            if (c.equals(OPERATION_START_TIME) || c.contains("." + OPERATION_START_TIME)) return true;
            if (c.equals(OPERATION_END_TIME) || c.contains("." + OPERATION_END_TIME)) return true;
            if (c.equals(ORDER_RECEIVE_TIME) || c.contains("." + ORDER_RECEIVE_TIME)) return true;
            if (c.equals(ARRIVE_TIME) || c.contains("." + ARRIVE_TIME)) return true;
            if (c.equals(INT_CUSTOMER_NO) || c.contains("." + INT_CUSTOMER_NO)) return true;
            if (c.equals(STATUS_BACKSTAGE) || c.contains("." + STATUS_BACKSTAGE)) return true;
            if (c.equals(STATUS) || c.contains("." + STATUS)) return true;
            if (c.equals(LAST_MODIFIED) || c.contains("." + LAST_MODIFIED)) return true;
            if (c.equals(DELETE_PENDING) || c.contains("." + DELETE_PENDING)) return true;
            if (c.equals(UPLOAD_PENDING) || c.contains("." + UPLOAD_PENDING)) return true;
            if (c.equals(WORK_TYPE_ID) || c.contains("." + WORK_TYPE_ID)) return true;
        }
        return false;
    }

}
