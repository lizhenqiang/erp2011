package cc.xingyan.android.afc.provider.pmifswork;

import cc.xingyan.android.afc.provider.base.BaseModel;

/**
 * Data model for the {@code pmifs_work} table.
 */
public interface PmifsWorkModel extends BaseModel {

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
     * Get the {@code priority_text} value.
     * Can be {@code null}.
     */
    String getPriorityText();

    /**
     * Get the {@code priority_code} value.
     * Can be {@code null}.
     */
    String getPriorityCode();

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
     * Get the {@code device_logic_code} value.
     * Can be {@code null}.
     */
    String getDeviceLogicCode();

    /**
     * Get the {@code device_logic_name} value.
     * Can be {@code null}.
     */
    String getDeviceLogicName();

    /**
     * Get the {@code instruct} value.
     * Can be {@code null}.
     */
    String getInstruct();

    /**
     * Get the {@code prepare_man_code} value.
     * Can be {@code null}.
     */
    String getPrepareManCode();

    /**
     * Get the {@code prepare_man_text} value.
     * Can be {@code null}.
     */
    String getPrepareManText();

    /**
     * Get the {@code workarea_text} value.
     * Can be {@code null}.
     */
    String getWorkareaText();

    /**
     * Get the {@code workarea_code} value.
     * Can be {@code null}.
     */
    String getWorkareaCode();

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
     * Get the {@code status_backstage} value.
     * Can be {@code null}.
     */
    String getStatusBackstage();

    /**
     * Get the {@code status} value.
     * Can be {@code null}.
     */
    PmifsWorkStatus getStatus();

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

    String getWorkTypeId();
}
