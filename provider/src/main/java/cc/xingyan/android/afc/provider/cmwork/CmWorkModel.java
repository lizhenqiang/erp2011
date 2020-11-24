package cc.xingyan.android.afc.provider.cmwork;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code cm_work} table.
 */
public interface CmWorkModel extends BaseModel {

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
     * Get the {@code order_id} value.
     * Can be {@code null}.
     */
    String getOrderId();

    /**
     * Get the {@code priority} value.
     * Can be {@code null}.
     */
    String getPriority();

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
     * Get the {@code fault_note} value.
     * Can be {@code null}.
     */
    String getFaultNote();

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
     * Get the {@code instruct} value.
     * Can be {@code null}.
     */
    String getInstruct();

    /**
     * Get the {@code reporter} value.
     * Can be {@code null}.
     */
    String getReporter();

    /**
     * Get the {@code prepare_man} value.
     * Can be {@code null}.
     */
    String getPrepareMan();

    /**
     * Get the {@code dispose} value.
     * Can be {@code null}.
     */
    String getDispose();

    /**
     * Get the {@code workarea} value.
     * Can be {@code null}.
     */
    String getWorkarea();

    /**
     * Get the {@code fault_start_time} value.
     * Can be {@code null}.
     */
    Long getFaultStartTime();

    /**
     * Get the {@code apply_s_time} value.
     * Can be {@code null}.
     */
    Long getApplySTime();

    /**
     * Get the {@code apply_f_time} value.
     * Can be {@code null}.
     */
    Long getApplyFTime();

    /**
     * Get the {@code plan_s_time} value.
     * Can be {@code null}.
     */
    Long getPlanSTime();

    /**
     * Get the {@code plan_f_time} value.
     * Can be {@code null}.
     */
    Long getPlanFTime();

    /**
     * Get the {@code fault_grade_text} value.
     * Can be {@code null}.
     */
    String getFaultGradeText();

    /**
     * Get the {@code fault_grade_code} value.
     * Can be {@code null}.
     */
    String getFaultGradeCode();

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
     * Get the {@code work_details_text} value.
     * Can be {@code null}.
     */
    String getWorkDetailsText();

    /**
     * Get the {@code work_details_code} value.
     * Can be {@code null}.
     */
    String getWorkDetailsCode();

    /**
     * Get the {@code work_done_text} value.
     * Can be {@code null}.
     */
    String getWorkDoneText();

    /**
     * Get the {@code work_done_code} value.
     * Can be {@code null}.
     */
    String getWorkDoneCode();

    /**
     * Get the {@code fault_cause_note} value.
     * Can be {@code null}.
     */
    String getFaultCauseNote();

    /**
     * Get the {@code work_note} value.
     * Can be {@code null}.
     */
    String getWorkNote();

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
     * Get the {@code order_receive_time} value.
     * Can be {@code null}.
     */
    Long getOrderReceiveTime();

    /**
     * Get the {@code arrive_time} value.
     * Can be {@code null}.
     */
    Long getArriveTime();

    /**
     * Get the {@code int_customer_no} value.
     * Can be {@code null}.
     */
    String getIntCustomerNo();

    /**
     * Get the {@code form_flag} value.
     * Can be {@code null}.
     */
    String getFormFlag();

    /**
     * Get the {@code equip_fault} value.
     * Can be {@code null}.
     */
    String getEquipFault();

    /**
     * Get the {@code status_backstage} value.
     * Can be {@code null}.
     */
    String getStatusBackstage();

    /**
     * Get the {@code status} value.
     * Can be {@code null}.
     */
    CmWorkStatus getStatus();

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
