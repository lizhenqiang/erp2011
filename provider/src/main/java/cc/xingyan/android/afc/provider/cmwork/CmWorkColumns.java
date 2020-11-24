package cc.xingyan.android.afc.provider.cmwork;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.CmProvider;
import cc.xingyan.android.afc.provider.cmmaterial.CmMaterialColumns;
import cc.xingyan.android.afc.provider.cmmaterialrow.CmMaterialRowColumns;
import cc.xingyan.android.afc.provider.cmparammaterials.CmParamMaterialsColumns;
import cc.xingyan.android.afc.provider.cmspareparts.CmSparePartsColumns;

/**
 * Columns for the {@code cm_work} table.
 */
public class CmWorkColumns implements BaseColumns {
    public static final String TABLE_NAME = "cm_work";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.cm/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String USER_ID = "user_id";

    public static final String GUID = "guid";

    public static final String ORDER_ID = "order_id";

    public static final String PRIORITY = "priority";

    public static final String DEVICE_CODE = "device_code";

    public static final String DEVICE_NAME = "device_name";

    public static final String FAULT_DESCRIPTION_TEXT = "fault_description_text";

    public static final String FAULT_DESCRIPTION_CODE = "fault_description_code";

    public static final String FAULT_NOTE = "fault_note";

    public static final String REPORTER_TYPE_TEXT = "reporter_type_text";

    public static final String REPORTER_TYPE_CODE = "reporter_type_code";

    public static final String INSTRUCT = "instruct";

    public static final String REPORTER = "reporter";

    public static final String PREPARE_MAN = "prepare_man";

    public static final String DISPOSE = "dispose";

    public static final String WORKAREA = "workarea";

    public static final String FAULT_START_TIME = "fault_start_time";

    public static final String APPLY_S_TIME = "apply_s_time";

    public static final String APPLY_F_TIME = "apply_f_time";

    public static final String PLAN_S_TIME = "plan_s_time";

    public static final String PLAN_F_TIME = "plan_f_time";

    public static final String FAULT_GRADE_TEXT = "fault_grade_text";

    public static final String FAULT_GRADE_CODE = "fault_grade_code";

    public static final String FAULT_TYPE_TEXT = "fault_type_text";

    public static final String FAULT_TYPE_CODE = "fault_type_code";

    public static final String FAULT_CAUSE_TEXT = "fault_cause_text";

    public static final String FAULT_CAUSE_CODE = "fault_cause_code";

    public static final String WORK_DETAILS_TEXT = "work_details_text";

    public static final String WORK_DETAILS_CODE = "work_details_code";

    public static final String WORK_DONE_TEXT = "work_done_text";

    public static final String WORK_DONE_CODE = "work_done_code";

    public static final String FAULT_CAUSE_NOTE = "fault_cause_note";

    public static final String WORK_NOTE = "work_note";

    public static final String OPERATOR_TEXT = "operator_text";

    public static final String OPERATOR_CODE = "operator_code";

    public static final String OPERATION_START_TIME = "operation_start_time";

    public static final String OPERATION_END_TIME = "operation_end_time";

    public static final String ORDER_RECEIVE_TIME = "order_receive_time";

    public static final String ARRIVE_TIME = "arrive_time";

    public static final String INT_CUSTOMER_NO = "int_customer_no";

    public static final String FORM_FLAG = "form_flag";

    public static final String EQUIP_FAULT = "equip_fault";

    public static final String STATUS_BACKSTAGE = "status_backstage";

    public static final String STATUS = "status";

    public static final String LAST_MODIFIED = "last_modified";

    public static final String DELETE_PENDING = "delete_pending";

    public static final String UPLOAD_PENDING = "upload_pending";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            USER_ID,
            GUID,
            ORDER_ID,
            PRIORITY,
            DEVICE_CODE,
            DEVICE_NAME,
            FAULT_DESCRIPTION_TEXT,
            FAULT_DESCRIPTION_CODE,
            FAULT_NOTE,
            REPORTER_TYPE_TEXT,
            REPORTER_TYPE_CODE,
            INSTRUCT,
            REPORTER,
            PREPARE_MAN,
            DISPOSE,
            WORKAREA,
            FAULT_START_TIME,
            APPLY_S_TIME,
            APPLY_F_TIME,
            PLAN_S_TIME,
            PLAN_F_TIME,
            FAULT_GRADE_TEXT,
            FAULT_GRADE_CODE,
            FAULT_TYPE_TEXT,
            FAULT_TYPE_CODE,
            FAULT_CAUSE_TEXT,
            FAULT_CAUSE_CODE,
            WORK_DETAILS_TEXT,
            WORK_DETAILS_CODE,
            WORK_DONE_TEXT,
            WORK_DONE_CODE,
            FAULT_CAUSE_NOTE,
            WORK_NOTE,
            OPERATOR_TEXT,
            OPERATOR_CODE,
            OPERATION_START_TIME,
            OPERATION_END_TIME,
            ORDER_RECEIVE_TIME,
            ARRIVE_TIME,
            INT_CUSTOMER_NO,
            FORM_FLAG,
            EQUIP_FAULT,
            STATUS_BACKSTAGE,
            STATUS,
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
            if (c.equals(ORDER_ID) || c.contains("." + ORDER_ID)) return true;
            if (c.equals(PRIORITY) || c.contains("." + PRIORITY)) return true;
            if (c.equals(DEVICE_CODE) || c.contains("." + DEVICE_CODE)) return true;
            if (c.equals(DEVICE_NAME) || c.contains("." + DEVICE_NAME)) return true;
            if (c.equals(FAULT_DESCRIPTION_TEXT) || c.contains("." + FAULT_DESCRIPTION_TEXT)) return true;
            if (c.equals(FAULT_DESCRIPTION_CODE) || c.contains("." + FAULT_DESCRIPTION_CODE)) return true;
            if (c.equals(FAULT_NOTE) || c.contains("." + FAULT_NOTE)) return true;
            if (c.equals(REPORTER_TYPE_TEXT) || c.contains("." + REPORTER_TYPE_TEXT)) return true;
            if (c.equals(REPORTER_TYPE_CODE) || c.contains("." + REPORTER_TYPE_CODE)) return true;
            if (c.equals(INSTRUCT) || c.contains("." + INSTRUCT)) return true;
            if (c.equals(REPORTER) || c.contains("." + REPORTER)) return true;
            if (c.equals(PREPARE_MAN) || c.contains("." + PREPARE_MAN)) return true;
            if (c.equals(DISPOSE) || c.contains("." + DISPOSE)) return true;
            if (c.equals(WORKAREA) || c.contains("." + WORKAREA)) return true;
            if (c.equals(FAULT_START_TIME) || c.contains("." + FAULT_START_TIME)) return true;
            if (c.equals(APPLY_S_TIME) || c.contains("." + APPLY_S_TIME)) return true;
            if (c.equals(APPLY_F_TIME) || c.contains("." + APPLY_F_TIME)) return true;
            if (c.equals(PLAN_S_TIME) || c.contains("." + PLAN_S_TIME)) return true;
            if (c.equals(PLAN_F_TIME) || c.contains("." + PLAN_F_TIME)) return true;
            if (c.equals(FAULT_GRADE_TEXT) || c.contains("." + FAULT_GRADE_TEXT)) return true;
            if (c.equals(FAULT_GRADE_CODE) || c.contains("." + FAULT_GRADE_CODE)) return true;
            if (c.equals(FAULT_TYPE_TEXT) || c.contains("." + FAULT_TYPE_TEXT)) return true;
            if (c.equals(FAULT_TYPE_CODE) || c.contains("." + FAULT_TYPE_CODE)) return true;
            if (c.equals(FAULT_CAUSE_TEXT) || c.contains("." + FAULT_CAUSE_TEXT)) return true;
            if (c.equals(FAULT_CAUSE_CODE) || c.contains("." + FAULT_CAUSE_CODE)) return true;
            if (c.equals(WORK_DETAILS_TEXT) || c.contains("." + WORK_DETAILS_TEXT)) return true;
            if (c.equals(WORK_DETAILS_CODE) || c.contains("." + WORK_DETAILS_CODE)) return true;
            if (c.equals(WORK_DONE_TEXT) || c.contains("." + WORK_DONE_TEXT)) return true;
            if (c.equals(WORK_DONE_CODE) || c.contains("." + WORK_DONE_CODE)) return true;
            if (c.equals(FAULT_CAUSE_NOTE) || c.contains("." + FAULT_CAUSE_NOTE)) return true;
            if (c.equals(WORK_NOTE) || c.contains("." + WORK_NOTE)) return true;
            if (c.equals(OPERATOR_TEXT) || c.contains("." + OPERATOR_TEXT)) return true;
            if (c.equals(OPERATOR_CODE) || c.contains("." + OPERATOR_CODE)) return true;
            if (c.equals(OPERATION_START_TIME) || c.contains("." + OPERATION_START_TIME)) return true;
            if (c.equals(OPERATION_END_TIME) || c.contains("." + OPERATION_END_TIME)) return true;
            if (c.equals(ORDER_RECEIVE_TIME) || c.contains("." + ORDER_RECEIVE_TIME)) return true;
            if (c.equals(ARRIVE_TIME) || c.contains("." + ARRIVE_TIME)) return true;
            if (c.equals(INT_CUSTOMER_NO) || c.contains("." + INT_CUSTOMER_NO)) return true;
            if (c.equals(FORM_FLAG) || c.contains("." + FORM_FLAG)) return true;
            if (c.equals(EQUIP_FAULT) || c.contains("." + EQUIP_FAULT)) return true;
            if (c.equals(STATUS_BACKSTAGE) || c.contains("." + STATUS_BACKSTAGE)) return true;
            if (c.equals(STATUS) || c.contains("." + STATUS)) return true;
            if (c.equals(LAST_MODIFIED) || c.contains("." + LAST_MODIFIED)) return true;
            if (c.equals(DELETE_PENDING) || c.contains("." + DELETE_PENDING)) return true;
            if (c.equals(UPLOAD_PENDING) || c.contains("." + UPLOAD_PENDING)) return true;
        }
        return false;
    }

}
