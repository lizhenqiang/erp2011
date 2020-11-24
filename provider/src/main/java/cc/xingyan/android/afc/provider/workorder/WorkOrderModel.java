package cc.xingyan.android.afc.provider.workorder;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code work_order} table.
 */
public interface WorkOrderModel extends BaseModel {

    /**
     * Get the {@code user_id} value.
     * Can be {@code null}.
     */
    String getUserId();

    /**
     * Get the {@code guid} value.
     * Can be {@code null}.
     */
    String getGuid();

    /**
     * Get the {@code no} value.
     * Can be {@code null}.
     */
    String getNo();

    /**
     * Get the {@code device_code} value.
     * Can be {@code null}.
     */
    String getDeviceCode();

    /**
     * Get the {@code device_name} value.
     * Can be {@code null}.
     */
    String getDeviceName();

    /**
     * Get the {@code fault_description_text} value.
     * Can be {@code null}.
     */
    String getFaultDescriptionText();

    /**
     * Get the {@code fault_description_code} value.
     * Can be {@code null}.
     */
    String getFaultDescriptionCode();

    /**
     * Get the {@code fault_type_text} value.
     * Can be {@code null}.
     */
    String getFaultTypeText();

    /**
     * Get the {@code fault_type_code} value.
     * Can be {@code null}.
     */
    String getFaultTypeCode();

    /**
     * Get the {@code reporter_type_text} value.
     * Can be {@code null}.
     */
    String getReporterTypeText();

    /**
     * Get the {@code reporter_type_code} value.
     * Can be {@code null}.
     */
    String getReporterTypeCode();

    /**
     * Get the {@code reporter} value.
     * Can be {@code null}.
     */
    String getReporter();

    /**
     * Get the {@code fault_note} value.
     * Can be {@code null}.
     */
    String getFaultNote();

    /**
     * Get the {@code fault_cause_text} value.
     * Can be {@code null}.
     */
    String getFaultCauseText();

    /**
     * Get the {@code fault_cause_code} value.
     * Can be {@code null}.
     */
    String getFaultCauseCode();

    /**
     * Get the {@code fault_start_time} value.
     * Can be {@code null}.
     */
    Long getFaultStartTime();

    /**
     * Get the {@code operation_text} value.
     * Can be {@code null}.
     */
    String getOperationText();

    /**
     * Get the {@code operation_code} value.
     * Can be {@code null}.
     */
    String getOperationCode();

    /**
     * Get the {@code operation_result_text} value.
     * Can be {@code null}.
     */
    String getOperationResultText();

    /**
     * Get the {@code operation_result_code} value.
     * Can be {@code null}.
     */
    String getOperationResultCode();

    /**
     * Get the {@code operation_note} value.
     * Can be {@code null}.
     */
    String getOperationNote();

    /**
     * Get the {@code operator_text} value.
     * Can be {@code null}.
     */
    String getOperatorText();

    /**
     * Get the {@code operator_code} value.
     * Can be {@code null}.
     */
    String getOperatorCode();

    /**
     * Get the {@code operation_start_time} value.
     * Can be {@code null}.
     */
    Long getOperationStartTime();

    /**
     * Get the {@code operation_end_time} value.
     * Can be {@code null}.
     */
    Long getOperationEndTime();

    /**
     * Get the {@code form_flag_text} value.
     * Can be {@code null}.
     */
    String getFormFlagText();

    /**
     * Get the {@code form_flag_code} value.
     * Can be {@code null}.
     */
    String getFormFlagCode();

    /**
     * Get the {@code sync_status} value.
     * Can be {@code null}.
     */
    SyncStatus getSyncStatus();

    /**
     * Get the {@code last_modified} value.
     * Can be {@code null}.
     */
    Long getLastModified();

    /**
     * Get the {@code delete_pending} value.
     * Can be {@code null}.
     */
    Boolean getDeletePending();

    /**
     * Get the {@code upload_pending} value.
     * Can be {@code null}.
     */
    Boolean getUploadPending();
}
