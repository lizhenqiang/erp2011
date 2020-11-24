package cc.xingyan.android.afc.provider.diworkitem;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code di_work_item} table.
 */
public class DiWorkItemCursor extends AbstractCursor implements DiWorkItemModel {
    public DiWorkItemCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(DiWorkItemColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code work_guid} value.
     * Can be {@code null}.
     */
    public String getWorkGuid() {
        String res = getStringOrNull(DiWorkItemColumns.WORK_GUID);
        return res;
    }

    /**
     * Get the {@code station_id} value.
     * Can be {@code null}.
     */
    public String getStationId() {
        String res = getStringOrNull(DiWorkItemColumns.STATION_ID);
        return res;
    }

    /**
     * Get the {@code station_description} value.
     * Can be {@code null}.
     */
    public String getStationDescription() {
        String res = getStringOrNull(DiWorkItemColumns.STATION_DESCRIPTION);
        return res;
    }

    /**
     * Get the {@code device_id} value.
     * Can be {@code null}.
     */
    public String getDeviceId() {
        String res = getStringOrNull(DiWorkItemColumns.DEVICE_ID);
        return res;
    }

    /**
     * Get the {@code device_description} value.
     * Can be {@code null}.
     */
    public String getDeviceDescription() {
        String res = getStringOrNull(DiWorkItemColumns.DEVICE_DESCRIPTION);
        return res;
    }

    /**
     * Get the {@code device_system} value.
     * Can be {@code null}.
     */
    public String getDeviceSystem() {
        String res = getStringOrNull(DiWorkItemColumns.DEVICE_SYSTEM);
        return res;
    }

    /**
     * Get the {@code guid} value.
     * Can be {@code null}.
     */
    public String getGuid() {
        String res = getStringOrNull(DiWorkItemColumns.GUID);
        return res;
    }

    /**
     * Get the {@code result_ordinal} value.
     * Can be {@code null}.
     */
    public Integer getResultOrdinal() {
        Integer res = getIntegerOrNull(DiWorkItemColumns.RESULT_ORDINAL);
        return res;
    }

    /**
     * Get the {@code result_value} value.
     * Can be {@code null}.
     */
    public String getResultValue() {
        String res = getStringOrNull(DiWorkItemColumns.RESULT_VALUE);
        return res;
    }

    /**
     * Get the {@code last_modified} value.
     * Can be {@code null}.
     */
    public Long getLastModified() {
        Long res = getLongOrNull(DiWorkItemColumns.LAST_MODIFIED);
        return res;
    }
}
