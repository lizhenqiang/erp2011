package cc.xingyan.android.afc.provider.diworkitem;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code di_work_item} table.
 */
public class DiWorkItemContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return DiWorkItemColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  DiWorkItemSelection where) {
        return contentResolver.update(where.uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  DiWorkItemSelection where) {
        return context.getContentResolver().update(where.uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public DiWorkItemContentValues putWorkGuid(String value) {
        mContentValues.put(DiWorkItemColumns.WORK_GUID, value);
        return this;
    }

    public DiWorkItemContentValues putWorkGuidNull() {
        mContentValues.putNull(DiWorkItemColumns.WORK_GUID);
        return this;
    }

    public DiWorkItemContentValues putStationId(String value) {
        mContentValues.put(DiWorkItemColumns.STATION_ID, value);
        return this;
    }

    public DiWorkItemContentValues putStationIdNull() {
        mContentValues.putNull(DiWorkItemColumns.STATION_ID);
        return this;
    }

    public DiWorkItemContentValues putStationDescription(String value) {
        mContentValues.put(DiWorkItemColumns.STATION_DESCRIPTION, value);
        return this;
    }

    public DiWorkItemContentValues putStationDescriptionNull() {
        mContentValues.putNull(DiWorkItemColumns.STATION_DESCRIPTION);
        return this;
    }

    public DiWorkItemContentValues putDeviceId(String value) {
        mContentValues.put(DiWorkItemColumns.DEVICE_ID, value);
        return this;
    }

    public DiWorkItemContentValues putDeviceIdNull() {
        mContentValues.putNull(DiWorkItemColumns.DEVICE_ID);
        return this;
    }

    public DiWorkItemContentValues putDeviceDescription(String value) {
        mContentValues.put(DiWorkItemColumns.DEVICE_DESCRIPTION, value);
        return this;
    }

    public DiWorkItemContentValues putDeviceDescriptionNull() {
        mContentValues.putNull(DiWorkItemColumns.DEVICE_DESCRIPTION);
        return this;
    }

    public DiWorkItemContentValues putDeviceSystem(String value) {
        mContentValues.put(DiWorkItemColumns.DEVICE_SYSTEM, value);
        return this;
    }

    public DiWorkItemContentValues putDeviceSystemNull() {
        mContentValues.putNull(DiWorkItemColumns.DEVICE_SYSTEM);
        return this;
    }

    public DiWorkItemContentValues putGuid(String value) {
        mContentValues.put(DiWorkItemColumns.GUID, value);
        return this;
    }

    public DiWorkItemContentValues putGuidNull() {
        mContentValues.putNull(DiWorkItemColumns.GUID);
        return this;
    }

    public DiWorkItemContentValues putResultOrdinal(Integer value) {
        mContentValues.put(DiWorkItemColumns.RESULT_ORDINAL, value);
        return this;
    }

    public DiWorkItemContentValues putResultOrdinalNull() {
        mContentValues.putNull(DiWorkItemColumns.RESULT_ORDINAL);
        return this;
    }

    public DiWorkItemContentValues putResultValue(String value) {
        mContentValues.put(DiWorkItemColumns.RESULT_VALUE, value);
        return this;
    }

    public DiWorkItemContentValues putResultValueNull() {
        mContentValues.putNull(DiWorkItemColumns.RESULT_VALUE);
        return this;
    }

    public DiWorkItemContentValues putLastModified(Long value) {
        mContentValues.put(DiWorkItemColumns.LAST_MODIFIED, value);
        return this;
    }

    public DiWorkItemContentValues putLastModifiedNull() {
        mContentValues.putNull(DiWorkItemColumns.LAST_MODIFIED);
        return this;
    }
}
