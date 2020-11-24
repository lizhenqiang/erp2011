package cc.xingyan.android.afc.provider.cmwork;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code cm_work} table.
 */
public class CmWorkCursor extends AbstractCursor implements CmWorkModel {
    public CmWorkCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(CmWorkColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code user_id} value.
     * Can be {@code null}.
     */
    public String getUserId() {
        String res = getStringOrNull(CmWorkColumns.USER_ID);
        return res;
    }

    /**
     * Get the {@code guid} value.
     * Can be {@code null}.
     */
    public String getGuid() {
        String res = getStringOrNull(CmWorkColumns.GUID);
        return res;
    }

    /**
     * Get the {@code order_id} value.
     * Can be {@code null}.
     */
    public String getOrderId() {
        String res = getStringOrNull(CmWorkColumns.ORDER_ID);
        return res;
    }

    /**
     * Get the {@code priority} value.
     * Can be {@code null}.
     */
    public String getPriority() {
        String res = getStringOrNull(CmWorkColumns.PRIORITY);
        return res;
    }

    /**
     * Get the {@code device_code} value.
     * Can be {@code null}.
     */
    public String getDeviceCode() {
        String res = getStringOrNull(CmWorkColumns.DEVICE_CODE);
        return res;
    }

    /**
     * Get the {@code device_name} value.
     * Can be {@code null}.
     */
    public String getDeviceName() {
        String res = getStringOrNull(CmWorkColumns.DEVICE_NAME);
        return res;
    }

    /**
     * Get the {@code fault_description_text} value.
     * Can be {@code null}.
     */
    public String getFaultDescriptionText() {
        String res = getStringOrNull(CmWorkColumns.FAULT_DESCRIPTION_TEXT);
        return res;
    }

    /**
     * Get the {@code fault_description_code} value.
     * Can be {@code null}.
     */
    public String getFaultDescriptionCode() {
        String res = getStringOrNull(CmWorkColumns.FAULT_DESCRIPTION_CODE);
        return res;
    }

    /**
     * Get the {@code fault_note} value.
     * Can be {@code null}.
     */
    public String getFaultNote() {
        String res = getStringOrNull(CmWorkColumns.FAULT_NOTE);
        return res;
    }

    /**
     * Get the {@code reporter_type_text} value.
     * Can be {@code null}.
     */
    public String getReporterTypeText() {
        String res = getStringOrNull(CmWorkColumns.REPORTER_TYPE_TEXT);
        return res;
    }

    /**
     * Get the {@code reporter_type_code} value.
     * Can be {@code null}.
     */
    public String getReporterTypeCode() {
        String res = getStringOrNull(CmWorkColumns.REPORTER_TYPE_CODE);
        return res;
    }

    /**
     * Get the {@code instruct} value.
     * Can be {@code null}.
     */
    public String getInstruct() {
        String res = getStringOrNull(CmWorkColumns.INSTRUCT);
        return res;
    }

    /**
     * Get the {@code reporter} value.
     * Can be {@code null}.
     */
    public String getReporter() {
        String res = getStringOrNull(CmWorkColumns.REPORTER);
        return res;
    }

    /**
     * Get the {@code prepare_man} value.
     * Can be {@code null}.
     */
    public String getPrepareMan() {
        String res = getStringOrNull(CmWorkColumns.PREPARE_MAN);
        return res;
    }

    /**
     * Get the {@code dispose} value.
     * Can be {@code null}.
     */
    public String getDispose() {
        String res = getStringOrNull(CmWorkColumns.DISPOSE);
        return res;
    }

    /**
     * Get the {@code workarea} value.
     * Can be {@code null}.
     */
    public String getWorkarea() {
        String res = getStringOrNull(CmWorkColumns.WORKAREA);
        return res;
    }

    /**
     * Get the {@code fault_start_time} value.
     * Can be {@code null}.
     */
    public Long getFaultStartTime() {
        Long res = getLongOrNull(CmWorkColumns.FAULT_START_TIME);
        return res;
    }

    /**
     * Get the {@code apply_s_time} value.
     * Can be {@code null}.
     */
    public Long getApplySTime() {
        Long res = getLongOrNull(CmWorkColumns.APPLY_S_TIME);
        return res;
    }

    /**
     * Get the {@code apply_f_time} value.
     * Can be {@code null}.
     */
    public Long getApplyFTime() {
        Long res = getLongOrNull(CmWorkColumns.APPLY_F_TIME);
        return res;
    }

    /**
     * Get the {@code plan_s_time} value.
     * Can be {@code null}.
     */
    public Long getPlanSTime() {
        Long res = getLongOrNull(CmWorkColumns.PLAN_S_TIME);
        return res;
    }

    /**
     * Get the {@code plan_f_time} value.
     * Can be {@code null}.
     */
    public Long getPlanFTime() {
        Long res = getLongOrNull(CmWorkColumns.PLAN_F_TIME);
        return res;
    }

    /**
     * Get the {@code fault_grade_text} value.
     * Can be {@code null}.
     */
    public String getFaultGradeText() {
        String res = getStringOrNull(CmWorkColumns.FAULT_GRADE_TEXT);
        return res;
    }

    /**
     * Get the {@code fault_grade_code} value.
     * Can be {@code null}.
     */
    public String getFaultGradeCode() {
        String res = getStringOrNull(CmWorkColumns.FAULT_GRADE_CODE);
        return res;
    }

    /**
     * Get the {@code fault_type_text} value.
     * Can be {@code null}.
     */
    public String getFaultTypeText() {
        String res = getStringOrNull(CmWorkColumns.FAULT_TYPE_TEXT);
        return res;
    }

    /**
     * Get the {@code fault_type_code} value.
     * Can be {@code null}.
     */
    public String getFaultTypeCode() {
        String res = getStringOrNull(CmWorkColumns.FAULT_TYPE_CODE);
        return res;
    }

    /**
     * Get the {@code fault_cause_text} value.
     * Can be {@code null}.
     */
    public String getFaultCauseText() {
        String res = getStringOrNull(CmWorkColumns.FAULT_CAUSE_TEXT);
        return res;
    }

    /**
     * Get the {@code fault_cause_code} value.
     * Can be {@code null}.
     */
    public String getFaultCauseCode() {
        String res = getStringOrNull(CmWorkColumns.FAULT_CAUSE_CODE);
        return res;
    }

    /**
     * Get the {@code work_details_text} value.
     * Can be {@code null}.
     */
    public String getWorkDetailsText() {
        String res = getStringOrNull(CmWorkColumns.WORK_DETAILS_TEXT);
        return res;
    }

    /**
     * Get the {@code work_details_code} value.
     * Can be {@code null}.
     */
    public String getWorkDetailsCode() {
        String res = getStringOrNull(CmWorkColumns.WORK_DETAILS_CODE);
        return res;
    }

    /**
     * Get the {@code work_done_text} value.
     * Can be {@code null}.
     */
    public String getWorkDoneText() {
        String res = getStringOrNull(CmWorkColumns.WORK_DONE_TEXT);
        return res;
    }

    /**
     * Get the {@code work_done_code} value.
     * Can be {@code null}.
     */
    public String getWorkDoneCode() {
        String res = getStringOrNull(CmWorkColumns.WORK_DONE_CODE);
        return res;
    }

    /**
     * Get the {@code fault_cause_note} value.
     * Can be {@code null}.
     */
    public String getFaultCauseNote() {
        String res = getStringOrNull(CmWorkColumns.FAULT_CAUSE_NOTE);
        return res;
    }

    /**
     * Get the {@code work_note} value.
     * Can be {@code null}.
     */
    public String getWorkNote() {
        String res = getStringOrNull(CmWorkColumns.WORK_NOTE);
        return res;
    }

    /**
     * Get the {@code operator_text} value.
     * Can be {@code null}.
     */
    public String getOperatorText() {
        String res = getStringOrNull(CmWorkColumns.OPERATOR_TEXT);
        return res;
    }

    /**
     * Get the {@code operator_code} value.
     * Can be {@code null}.
     */
    public String getOperatorCode() {
        String res = getStringOrNull(CmWorkColumns.OPERATOR_CODE);
        return res;
    }

    /**
     * Get the {@code operation_start_time} value.
     * Can be {@code null}.
     */
    public Long getOperationStartTime() {
        Long res = getLongOrNull(CmWorkColumns.OPERATION_START_TIME);
        return res;
    }

    /**
     * Get the {@code operation_end_time} value.
     * Can be {@code null}.
     */
    public Long getOperationEndTime() {
        Long res = getLongOrNull(CmWorkColumns.OPERATION_END_TIME);
        return res;
    }

    /**
     * Get the {@code order_receive_time} value.
     * Can be {@code null}.
     */
    public Long getOrderReceiveTime() {
        Long res = getLongOrNull(CmWorkColumns.ORDER_RECEIVE_TIME);
        return res;
    }

    /**
     * Get the {@code arrive_time} value.
     * Can be {@code null}.
     */
    public Long getArriveTime() {
        Long res = getLongOrNull(CmWorkColumns.ARRIVE_TIME);
        return res;
    }

    /**
     * Get the {@code int_customer_no} value.
     * Can be {@code null}.
     */
    public String getIntCustomerNo() {
        String res = getStringOrNull(CmWorkColumns.INT_CUSTOMER_NO);
        return res;
    }

    /**
     * Get the {@code form_flag} value.
     * Can be {@code null}.
     */
    public String getFormFlag() {
        String res = getStringOrNull(CmWorkColumns.FORM_FLAG);
        return res;
    }

    /**
     * Get the {@code equip_fault} value.
     * Can be {@code null}.
     */
    public String getEquipFault() {
        String res = getStringOrNull(CmWorkColumns.EQUIP_FAULT);
        return res;
    }

    /**
     * Get the {@code status_backstage} value.
     * Can be {@code null}.
     */
    public String getStatusBackstage() {
        String res = getStringOrNull(CmWorkColumns.STATUS_BACKSTAGE);
        return res;
    }

    /**
     * Get the {@code status} value.
     * Can be {@code null}.
     */
    public CmWorkStatus getStatus() {
        Integer intValue = getIntegerOrNull(CmWorkColumns.STATUS);
        if (intValue == null) return null;
        return CmWorkStatus.values()[intValue];
    }

    /**
     * Get the {@code last_modified} value.
     * Can be {@code null}.
     */
    public Long getLastModified() {
        Long res = getLongOrNull(CmWorkColumns.LAST_MODIFIED);
        return res;
    }

    /**
     * Get the {@code delete_pending} value.
     * Can be {@code null}.
     */
    public Boolean getDeletePending() {
        Boolean res = getBooleanOrNull(CmWorkColumns.DELETE_PENDING);
        return res;
    }

    /**
     * Get the {@code upload_pending} value.
     * Can be {@code null}.
     */
    public Boolean getUploadPending() {
        Boolean res = getBooleanOrNull(CmWorkColumns.UPLOAD_PENDING);
        return res;
    }
}
