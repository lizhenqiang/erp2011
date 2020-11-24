package cc.xingyan.android.afc.provider.pmwork;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code pm_work} table.
 */
public interface PmWorkModel extends BaseModel {

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
     * Get the {@code job_id} value.
     * Can be {@code null}.
     */
    String getJobId();

    /**
     * Get the {@code job_description} value.
     * Can be {@code null}.
     */
    String getJobDescription();

    /**
     * Get the {@code work_id} value.
     * Can be {@code null}.
     */
    String getWorkId();

    /**
     * Get the {@code work_kind} value.
     * Can be {@code null}.
     */
    String getWorkKind();

    /**
     * Get the {@code device_code} value.
     * Can be {@code null}.
     */
    String getDeviceCode();

    /**
     * Get the {@code device_description} value.
     * Can be {@code null}.
     */
    String getDeviceDescription();

    /**
     * Get the {@code executor_id} value.
     * Can be {@code null}.
     */
    String getExecutorId();

    /**
     * Get the {@code executor_description} value.
     * Can be {@code null}.
     */
    String getExecutorDescription();

    /**
     * Get the {@code start_time} value.
     * Can be {@code null}.
     */
    Long getStartTime();

    /**
     * Get the {@code end_time} value.
     * Can be {@code null}.
     */
    Long getEndTime();

    /**
     * Get the {@code complete_time} value.
     * Can be {@code null}.
     */
    Long getCompleteTime();

    /**
     * Get the {@code last_modified} value.
     * Can be {@code null}.
     */
    Long getLastModified();

    /**
     * Get the {@code status} value.
     * Can be {@code null}.
     */
    PmWorkStatus getStatus();

    /**
     * Get the {@code upload_pending} value.
     * Can be {@code null}.
     */
    Boolean getUploadPending();
}
