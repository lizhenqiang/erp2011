package cc.xingyan.android.afc.provider.pmifswork;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code pmifs_work} table.
 */
public class PmifsWorkCursor extends AbstractCursor implements PmifsWorkModel {
    public PmifsWorkCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(PmifsWorkColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code user_id} value.
     * Can be {@code null}.
     */
    public String getUserId() {
        String res = getStringOrNull(PmifsWorkColumns.USER_ID);
        return res;
    }

    /**
     * Get the {@code guid} value.
     * Can be {@code null}.
     */
    public String getGuid() {
        String res = getStringOrNull(PmifsWorkColumns.GUID);
        return res;
    }

    /**
     * Get the {@code order_id} value.
     * Can be {@code null}.
     */
    public String getOrderId() {
        String res = getStringOrNull(PmifsWorkColumns.ORDER_ID);
        return res;
    }

    /**
     * Get the {@code priority_text} value.
     * Can be {@code null}.
     */
    public String getPriorityText() {
        String res = getStringOrNull(PmifsWorkColumns.PRIORITY_TEXT);
        return res;
    }

    /**
     * Get the {@code priority_code} value.
     * Can be {@code null}.
     */
    public String getPriorityCode() {
        String res = getStringOrNull(PmifsWorkColumns.PRIORITY_CODE);
        return res;
    }

    /**
     * Get the {@code device_code} value.
     * Can be {@code null}.
     */
    public String getDeviceCode() {
        String res = getStringOrNull(PmifsWorkColumns.DEVICE_CODE);
        return res;
    }

    /**
     * Get the {@code device_name} value.
     * Can be {@code null}.
     */
    public String getDeviceName() {
        String res = getStringOrNull(PmifsWorkColumns.DEVICE_NAME);
        return res;
    }

    /**
     * Get the {@code device_logic_code} value.
     * Can be {@code null}.
     */
    public String getDeviceLogicCode() {
        String res = getStringOrNull(PmifsWorkColumns.DEVICE_LOGIC_CODE);
        return res;
    }

    /**
     * Get the {@code device_logic_name} value.
     * Can be {@code null}.
     */
    public String getDeviceLogicName() {
        String res = getStringOrNull(PmifsWorkColumns.DEVICE_LOGIC_NAME);
        return res;
    }

    /**
     * Get the {@code instruct} value.
     * Can be {@code null}.
     */
    public String getInstruct() {
        String res = getStringOrNull(PmifsWorkColumns.INSTRUCT);
        return res;
    }

    /**
     * Get the {@code prepare_man_code} value.
     * Can be {@code null}.
     */
    public String getPrepareManCode() {
        String res = getStringOrNull(PmifsWorkColumns.PREPARE_MAN_CODE);
        return res;
    }

    /**
     * Get the {@code prepare_man_text} value.
     * Can be {@code null}.
     */
    public String getPrepareManText() {
        String res = getStringOrNull(PmifsWorkColumns.PREPARE_MAN_TEXT);
        return res;
    }

    /**
     * Get the {@code workarea_text} value.
     * Can be {@code null}.
     */
    public String getWorkareaText() {
        String res = getStringOrNull(PmifsWorkColumns.WORKAREA_TEXT);
        return res;
    }

    /**
     * Get the {@code workarea_code} value.
     * Can be {@code null}.
     */
    public String getWorkareaCode() {
        String res = getStringOrNull(PmifsWorkColumns.WORKAREA_CODE);
        return res;
    }

    /**
     * Get the {@code apply_s_time} value.
     * Can be {@code null}.
     */
    public Long getApplySTime() {
        Long res = getLongOrNull(PmifsWorkColumns.APPLY_S_TIME);
        return res;
    }

    /**
     * Get the {@code apply_f_time} value.
     * Can be {@code null}.
     */
    public Long getApplyFTime() {
        Long res = getLongOrNull(PmifsWorkColumns.APPLY_F_TIME);
        return res;
    }

    /**
     * Get the {@code plan_s_time} value.
     * Can be {@code null}.
     */
    public Long getPlanSTime() {
        Long res = getLongOrNull(PmifsWorkColumns.PLAN_S_TIME);
        return res;
    }

    /**
     * Get the {@code plan_f_time} value.
     * Can be {@code null}.
     */
    public Long getPlanFTime() {
        Long res = getLongOrNull(PmifsWorkColumns.PLAN_F_TIME);
        return res;
    }

    /**
     * Get the {@code work_details_text} value.
     * Can be {@code null}.
     */
    public String getWorkDetailsText() {
        String res = getStringOrNull(PmifsWorkColumns.WORK_DETAILS_TEXT);
        return res;
    }

    /**
     * Get the {@code work_details_code} value.
     * Can be {@code null}.
     */
    public String getWorkDetailsCode() {
        String res = getStringOrNull(PmifsWorkColumns.WORK_DETAILS_CODE);
        return res;
    }

    /**
     * Get the {@code work_done_text} value.
     * Can be {@code null}.
     */
    public String getWorkDoneText() {
        String res = getStringOrNull(PmifsWorkColumns.WORK_DONE_TEXT);
        return res;
    }

    /**
     * Get the {@code work_done_code} value.
     * Can be {@code null}.
     */
    public String getWorkDoneCode() {
        String res = getStringOrNull(PmifsWorkColumns.WORK_DONE_CODE);
        return res;
    }

    /**
     * Get the {@code work_note} value.
     * Can be {@code null}.
     */
    public String getWorkNote() {
        String res = getStringOrNull(PmifsWorkColumns.WORK_NOTE);
        return res;
    }

    /**
     * Get the {@code operator_text} value.
     * Can be {@code null}.
     */
    public String getOperatorText() {
        String res = getStringOrNull(PmifsWorkColumns.OPERATOR_TEXT);
        return res;
    }

    /**
     * Get the {@code operator_code} value.
     * Can be {@code null}.
     */
    public String getOperatorCode() {
        String res = getStringOrNull(PmifsWorkColumns.OPERATOR_CODE);
        return res;
    }

    /**
     * Get the {@code operation_start_time} value.
     * Can be {@code null}.
     */
    public Long getOperationStartTime() {
        Long res = getLongOrNull(PmifsWorkColumns.OPERATION_START_TIME);
        return res;
    }

    /**
     * Get the {@code operation_end_time} value.
     * Can be {@code null}.
     */
    public Long getOperationEndTime() {
        Long res = getLongOrNull(PmifsWorkColumns.OPERATION_END_TIME);
        return res;
    }

    /**
     * Get the {@code order_receive_time} value.
     * Can be {@code null}.
     */
    public Long getOrderReceiveTime() {
        Long res = getLongOrNull(PmifsWorkColumns.ORDER_RECEIVE_TIME);
        return res;
    }

    /**
     * Get the {@code arrive_time} value.
     * Can be {@code null}.
     */
    public Long getArriveTime() {
        Long res = getLongOrNull(PmifsWorkColumns.ARRIVE_TIME);
        return res;
    }

    /**
     * Get the {@code int_customer_no} value.
     * Can be {@code null}.
     */
    public String getIntCustomerNo() {
        String res = getStringOrNull(PmifsWorkColumns.INT_CUSTOMER_NO);
        return res;
    }

    /**
     * Get the {@code status_backstage} value.
     * Can be {@code null}.
     */
    public String getStatusBackstage() {
        String res = getStringOrNull(PmifsWorkColumns.STATUS_BACKSTAGE);
        return res;
    }

    /**
     * Get the {@code status} value.
     * Can be {@code null}.
     */
    public PmifsWorkStatus getStatus() {
        Integer intValue = getIntegerOrNull(PmifsWorkColumns.STATUS);
        if (intValue == null) return null;
        return PmifsWorkStatus.values()[intValue];
    }

    /**
     * Get the {@code last_modified} value.
     * Can be {@code null}.
     */
    public Long getLastModified() {
        Long res = getLongOrNull(PmifsWorkColumns.LAST_MODIFIED);
        return res;
    }

    /**
     * Get the {@code delete_pending} value.
     * Can be {@code null}.
     */
    public Boolean getDeletePending() {
        Boolean res = getBooleanOrNull(PmifsWorkColumns.DELETE_PENDING);
        return res;
    }

    /**
     * Get the {@code upload_pending} value.
     * Can be {@code null}.
     */
    public Boolean getUploadPending() {
        Boolean res = getBooleanOrNull(PmifsWorkColumns.UPLOAD_PENDING);
        return res;
    }
    public String getWorkTypeId() {
        String res = getStringOrNull(PmifsWorkColumns.WORK_TYPE_ID);
        return res;
    }

}
