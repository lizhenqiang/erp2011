package cc.xingyan.android.afc.provider.workorder;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code work_order} table.
 */
public class WorkOrderCursor extends AbstractCursor implements WorkOrderModel {
    public WorkOrderCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(WorkOrderColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code user_id} value.
     * Can be {@code null}.
     */
    public String getUserId() {
        String res = getStringOrNull(WorkOrderColumns.USER_ID);
        return res;
    }

    /**
     * Get the {@code guid} value.
     * Can be {@code null}.
     */
    public String getGuid() {
        String res = getStringOrNull(WorkOrderColumns.GUID);
        return res;
    }

    /**
     * Get the {@code no} value.
     * Can be {@code null}.
     */
    public String getNo() {
        String res = getStringOrNull(WorkOrderColumns.NO);
        return res;
    }

    /**
     * Get the {@code device_code} value.
     * Can be {@code null}.
     */
    public String getDeviceCode() {
        String res = getStringOrNull(WorkOrderColumns.DEVICE_CODE);
        return res;
    }

    /**
     * Get the {@code device_name} value.
     * Can be {@code null}.
     */
    public String getDeviceName() {
        String res = getStringOrNull(WorkOrderColumns.DEVICE_NAME);
        return res;
    }

    /**
     * Get the {@code fault_description_text} value.
     * Can be {@code null}.
     */
    public String getFaultDescriptionText() {
        String res = getStringOrNull(WorkOrderColumns.FAULT_DESCRIPTION_TEXT);
        return res;
    }

    /**
     * Get the {@code fault_description_code} value.
     * Can be {@code null}.
     */
    public String getFaultDescriptionCode() {
        String res = getStringOrNull(WorkOrderColumns.FAULT_DESCRIPTION_CODE);
        return res;
    }

    /**
     * Get the {@code fault_type_text} value.
     * Can be {@code null}.
     */
    public String getFaultTypeText() {
        String res = getStringOrNull(WorkOrderColumns.FAULT_TYPE_TEXT);
        return res;
    }

    /**
     * Get the {@code fault_type_code} value.
     * Can be {@code null}.
     */
    public String getFaultTypeCode() {
        String res = getStringOrNull(WorkOrderColumns.FAULT_TYPE_CODE);
        return res;
    }

    /**
     * Get the {@code reporter_type_text} value.
     * Can be {@code null}.
     */
    public String getReporterTypeText() {
        String res = getStringOrNull(WorkOrderColumns.REPORTER_TYPE_TEXT);
        return res;
    }

    /**
     * Get the {@code reporter_type_code} value.
     * Can be {@code null}.
     */
    public String getReporterTypeCode() {
        String res = getStringOrNull(WorkOrderColumns.REPORTER_TYPE_CODE);
        return res;
    }

    /**
     * Get the {@code reporter} value.
     * Can be {@code null}.
     */
    public String getReporter() {
        String res = getStringOrNull(WorkOrderColumns.REPORTER);
        return res;
    }

    /**
     * Get the {@code fault_note} value.
     * Can be {@code null}.
     */
    public String getFaultNote() {
        String res = getStringOrNull(WorkOrderColumns.FAULT_NOTE);
        return res;
    }

    /**
     * Get the {@code fault_cause_text} value.
     * Can be {@code null}.
     */
    public String getFaultCauseText() {
        String res = getStringOrNull(WorkOrderColumns.FAULT_CAUSE_TEXT);
        return res;
    }

    /**
     * Get the {@code fault_cause_code} value.
     * Can be {@code null}.
     */
    public String getFaultCauseCode() {
        String res = getStringOrNull(WorkOrderColumns.FAULT_CAUSE_CODE);
        return res;
    }

    /**
     * Get the {@code fault_start_time} value.
     * Can be {@code null}.
     */
    public Long getFaultStartTime() {
        Long res = getLongOrNull(WorkOrderColumns.FAULT_START_TIME);
        return res;
    }

    /**
     * Get the {@code operation_text} value.
     * Can be {@code null}.
     */
    public String getOperationText() {
        String res = getStringOrNull(WorkOrderColumns.OPERATION_TEXT);
        return res;
    }

    /**
     * Get the {@code operation_code} value.
     * Can be {@code null}.
     */
    public String getOperationCode() {
        String res = getStringOrNull(WorkOrderColumns.OPERATION_CODE);
        return res;
    }

    /**
     * Get the {@code operation_result_text} value.
     * Can be {@code null}.
     */
    public String getOperationResultText() {
        String res = getStringOrNull(WorkOrderColumns.OPERATION_RESULT_TEXT);
        return res;
    }

    /**
     * Get the {@code operation_result_code} value.
     * Can be {@code null}.
     */
    public String getOperationResultCode() {
        String res = getStringOrNull(WorkOrderColumns.OPERATION_RESULT_CODE);
        return res;
    }

    /**
     * Get the {@code operation_note} value.
     * Can be {@code null}.
     */
    public String getOperationNote() {
        String res = getStringOrNull(WorkOrderColumns.OPERATION_NOTE);
        return res;
    }

    /**
     * Get the {@code operator_text} value.
     * Can be {@code null}.
     */
    public String getOperatorText() {
        String res = getStringOrNull(WorkOrderColumns.OPERATOR_TEXT);
        return res;
    }

    /**
     * Get the {@code operator_code} value.
     * Can be {@code null}.
     */
    public String getOperatorCode() {
        String res = getStringOrNull(WorkOrderColumns.OPERATOR_CODE);
        return res;
    }

    /**
     * Get the {@code operation_start_time} value.
     * Can be {@code null}.
     */
    public Long getOperationStartTime() {
        Long res = getLongOrNull(WorkOrderColumns.OPERATION_START_TIME);
        return res;
    }

    /**
     * Get the {@code operation_end_time} value.
     * Can be {@code null}.
     */
    public Long getOperationEndTime() {
        Long res = getLongOrNull(WorkOrderColumns.OPERATION_END_TIME);
        return res;
    }

    /**
     * Get the {@code form_flag_text} value.
     * Can be {@code null}.
     */
    public String getFormFlagText() {
        String res = getStringOrNull(WorkOrderColumns.FORM_FLAG_TEXT);
        return res;
    }

    /**
     * Get the {@code form_flag_code} value.
     * Can be {@code null}.
     */
    public String getFormFlagCode() {
        String res = getStringOrNull(WorkOrderColumns.FORM_FLAG_CODE);
        return res;
    }

    /**
     * Get the {@code sync_status} value.
     * Can be {@code null}.
     */
    public SyncStatus getSyncStatus() {
        Integer intValue = getIntegerOrNull(WorkOrderColumns.SYNC_STATUS);
        if (intValue == null) return null;
        return SyncStatus.values()[intValue];
    }

    /**
     * Get the {@code last_modified} value.
     * Can be {@code null}.
     */
    public Long getLastModified() {
        Long res = getLongOrNull(WorkOrderColumns.LAST_MODIFIED);
        return res;
    }

    /**
     * Get the {@code delete_pending} value.
     * Can be {@code null}.
     */
    public Boolean getDeletePending() {
        Boolean res = getBooleanOrNull(WorkOrderColumns.DELETE_PENDING);
        return res;
    }

    /**
     * Get the {@code upload_pending} value.
     * Can be {@code null}.
     */
    public Boolean getUploadPending() {
        Boolean res = getBooleanOrNull(WorkOrderColumns.UPLOAD_PENDING);
        return res;
    }
}
