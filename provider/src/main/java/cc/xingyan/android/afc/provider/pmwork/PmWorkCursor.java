package cc.xingyan.android.afc.provider.pmwork;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code pm_work} table.
 */
public class PmWorkCursor extends AbstractCursor implements PmWorkModel {
    public PmWorkCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(PmWorkColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code user_id} value.
     * Can be {@code null}.
     */
    public String getUserId() {
        String res = getStringOrNull(PmWorkColumns.USER_ID);
        return res;
    }

    /**
     * Get the {@code guid} value.
     * Can be {@code null}.
     */
    public String getGuid() {
        String res = getStringOrNull(PmWorkColumns.GUID);
        return res;
    }

    /**
     * Get the {@code job_id} value.
     * Can be {@code null}.
     */
    public String getJobId() {
        String res = getStringOrNull(PmWorkColumns.JOB_ID);
        return res;
    }

    /**
     * Get the {@code job_description} value.
     * Can be {@code null}.
     */
    public String getJobDescription() {
        String res = getStringOrNull(PmWorkColumns.JOB_DESCRIPTION);
        return res;
    }

    /**
     * Get the {@code work_id} value.
     * Can be {@code null}.
     */
    public String getWorkId() {
        String res = getStringOrNull(PmWorkColumns.WORK_ID);
        return res;
    }

    /**
     * Get the {@code work_kind} value.
     * Can be {@code null}.
     */
    public String getWorkKind() {
        String res = getStringOrNull(PmWorkColumns.WORK_KIND);
        return res;
    }

    /**
     * Get the {@code device_code} value.
     * Can be {@code null}.
     */
    public String getDeviceCode() {
        String res = getStringOrNull(PmWorkColumns.DEVICE_CODE);
        return res;
    }

    /**
     * Get the {@code device_description} value.
     * Can be {@code null}.
     */
    public String getDeviceDescription() {
        String res = getStringOrNull(PmWorkColumns.DEVICE_DESCRIPTION);
        return res;
    }

    /**
     * Get the {@code executor_id} value.
     * Can be {@code null}.
     */
    public String getExecutorId() {
        String res = getStringOrNull(PmWorkColumns.EXECUTOR_ID);
        return res;
    }

    /**
     * Get the {@code executor_description} value.
     * Can be {@code null}.
     */
    public String getExecutorDescription() {
        String res = getStringOrNull(PmWorkColumns.EXECUTOR_DESCRIPTION);
        return res;
    }

    /**
     * Get the {@code start_time} value.
     * Can be {@code null}.
     */
    public Long getStartTime() {
        Long res = getLongOrNull(PmWorkColumns.START_TIME);
        return res;
    }

    /**
     * Get the {@code end_time} value.
     * Can be {@code null}.
     */
    public Long getEndTime() {
        Long res = getLongOrNull(PmWorkColumns.END_TIME);
        return res;
    }

    /**
     * Get the {@code complete_time} value.
     * Can be {@code null}.
     */
    public Long getCompleteTime() {
        Long res = getLongOrNull(PmWorkColumns.COMPLETE_TIME);
        return res;
    }

    /**
     * Get the {@code last_modified} value.
     * Can be {@code null}.
     */
    public Long getLastModified() {
        Long res = getLongOrNull(PmWorkColumns.LAST_MODIFIED);
        return res;
    }

    /**
     * Get the {@code status} value.
     * Can be {@code null}.
     */
    public PmWorkStatus getStatus() {
        Integer intValue = getIntegerOrNull(PmWorkColumns.STATUS);
        if (intValue == null) return null;
        return PmWorkStatus.values()[intValue];
    }

    /**
     * Get the {@code upload_pending} value.
     * Can be {@code null}.
     */
    public Boolean getUploadPending() {
        Boolean res = getBooleanOrNull(PmWorkColumns.UPLOAD_PENDING);
        return res;
    }
}
