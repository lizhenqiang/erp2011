package cc.xingyan.android.afc.provider.diwork;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code di_work} table.
 */
public interface DiWorkModel extends BaseModel {

    /**
     * Get the {@code work_area_id} value.
     * Can be {@code null}.
     */
    String getWorkAreaId();

    /**
     * Get the {@code work_area_description} value.
     * Can be {@code null}.
     */
    String getWorkAreaDescription();

    /**
     * Get the {@code guid} value.
     * Can be {@code null}.
     */
    String getGuid();

    /**
     * Get the {@code work_id} value.
     * Can be {@code null}.
     */
    String getWorkId();

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    Long getDate();

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
    DiWorkStatus getStatus();

    /**
     * Get the {@code upload_pending} value.
     * Can be {@code null}.
     */
    Boolean getUploadPending();

    /**
     * Get the {@code operator} value.
     * Can be {@code null}.
     */
    String getOperator();
}
