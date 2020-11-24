package cc.xingyan.android.afc.provider.diwork;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code di_work} table.
 */
public class DiWorkCursor extends AbstractCursor implements DiWorkModel {
    public DiWorkCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(DiWorkColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code work_area_id} value.
     * Can be {@code null}.
     */
    public String getWorkAreaId() {
        String res = getStringOrNull(DiWorkColumns.WORK_AREA_ID);
        return res;
    }

    /**
     * Get the {@code work_area_description} value.
     * Can be {@code null}.
     */
    public String getWorkAreaDescription() {
        String res = getStringOrNull(DiWorkColumns.WORK_AREA_DESCRIPTION);
        return res;
    }

    /**
     * Get the {@code guid} value.
     * Can be {@code null}.
     */
    public String getGuid() {
        String res = getStringOrNull(DiWorkColumns.GUID);
        return res;
    }

    /**
     * Get the {@code work_id} value.
     * Can be {@code null}.
     */
    public String getWorkId() {
        String res = getStringOrNull(DiWorkColumns.WORK_ID);
        return res;
    }

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    public Long getDate() {
        Long res = getLongOrNull(DiWorkColumns.DATE);
        return res;
    }

    /**
     * Get the {@code start_time} value.
     * Can be {@code null}.
     */
    public Long getStartTime() {
        Long res = getLongOrNull(DiWorkColumns.START_TIME);
        return res;
    }

    /**
     * Get the {@code end_time} value.
     * Can be {@code null}.
     */
    public Long getEndTime() {
        Long res = getLongOrNull(DiWorkColumns.END_TIME);
        return res;
    }

    /**
     * Get the {@code complete_time} value.
     * Can be {@code null}.
     */
    public Long getCompleteTime() {
        Long res = getLongOrNull(DiWorkColumns.COMPLETE_TIME);
        return res;
    }

    /**
     * Get the {@code last_modified} value.
     * Can be {@code null}.
     */
    public Long getLastModified() {
        Long res = getLongOrNull(DiWorkColumns.LAST_MODIFIED);
        return res;
    }

    /**
     * Get the {@code status} value.
     * Can be {@code null}.
     */
    public DiWorkStatus getStatus() {
        Integer intValue = getIntegerOrNull(DiWorkColumns.STATUS);
        if (intValue == null) return null;
        return DiWorkStatus.values()[intValue];
    }

    /**
     * Get the {@code upload_pending} value.
     * Can be {@code null}.
     */
    public Boolean getUploadPending() {
        Boolean res = getBooleanOrNull(DiWorkColumns.UPLOAD_PENDING);
        return res;
    }

    /**
     * Get the {@code operator} value.
     * Can be {@code null}.
     */
    public String getOperator() {
        String res = getStringOrNull(DiWorkColumns.OPERATOR);
        return res;
    }
}
