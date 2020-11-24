package cc.xingyan.android.afc.provider.diwork;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code di_work} table.
 */
public class DiWorkContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return DiWorkColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  DiWorkSelection where) {
        return contentResolver.update(where.uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  DiWorkSelection where) {
        return context.getContentResolver().update(where.uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public DiWorkContentValues putWorkAreaId(String value) {
        mContentValues.put(DiWorkColumns.WORK_AREA_ID, value);
        return this;
    }

    public DiWorkContentValues putWorkAreaIdNull() {
        mContentValues.putNull(DiWorkColumns.WORK_AREA_ID);
        return this;
    }

    public DiWorkContentValues putWorkAreaDescription(String value) {
        mContentValues.put(DiWorkColumns.WORK_AREA_DESCRIPTION, value);
        return this;
    }

    public DiWorkContentValues putWorkAreaDescriptionNull() {
        mContentValues.putNull(DiWorkColumns.WORK_AREA_DESCRIPTION);
        return this;
    }

    public DiWorkContentValues putGuid(String value) {
        mContentValues.put(DiWorkColumns.GUID, value);
        return this;
    }

    public DiWorkContentValues putGuidNull() {
        mContentValues.putNull(DiWorkColumns.GUID);
        return this;
    }

    public DiWorkContentValues putWorkId(String value) {
        mContentValues.put(DiWorkColumns.WORK_ID, value);
        return this;
    }

    public DiWorkContentValues putWorkIdNull() {
        mContentValues.putNull(DiWorkColumns.WORK_ID);
        return this;
    }

    public DiWorkContentValues putDate(Long value) {
        mContentValues.put(DiWorkColumns.DATE, value);
        return this;
    }

    public DiWorkContentValues putDateNull() {
        mContentValues.putNull(DiWorkColumns.DATE);
        return this;
    }

    public DiWorkContentValues putStartTime(Long value) {
        mContentValues.put(DiWorkColumns.START_TIME, value);
        return this;
    }

    public DiWorkContentValues putStartTimeNull() {
        mContentValues.putNull(DiWorkColumns.START_TIME);
        return this;
    }

    public DiWorkContentValues putEndTime(Long value) {
        mContentValues.put(DiWorkColumns.END_TIME, value);
        return this;
    }

    public DiWorkContentValues putEndTimeNull() {
        mContentValues.putNull(DiWorkColumns.END_TIME);
        return this;
    }

    public DiWorkContentValues putCompleteTime(Long value) {
        mContentValues.put(DiWorkColumns.COMPLETE_TIME, value);
        return this;
    }

    public DiWorkContentValues putCompleteTimeNull() {
        mContentValues.putNull(DiWorkColumns.COMPLETE_TIME);
        return this;
    }

    public DiWorkContentValues putLastModified(Long value) {
        mContentValues.put(DiWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public DiWorkContentValues putLastModifiedNull() {
        mContentValues.putNull(DiWorkColumns.LAST_MODIFIED);
        return this;
    }

    public DiWorkContentValues putStatus(DiWorkStatus value) {
        mContentValues.put(DiWorkColumns.STATUS, value == null ? null : value.ordinal());
        return this;
    }

    public DiWorkContentValues putStatusNull() {
        mContentValues.putNull(DiWorkColumns.STATUS);
        return this;
    }

    public DiWorkContentValues putUploadPending(Boolean value) {
        mContentValues.put(DiWorkColumns.UPLOAD_PENDING, value);
        return this;
    }

    public DiWorkContentValues putUploadPendingNull() {
        mContentValues.putNull(DiWorkColumns.UPLOAD_PENDING);
        return this;
    }

    public DiWorkContentValues putOperator(String value) {
        mContentValues.put(DiWorkColumns.OPERATOR, value);
        return this;
    }

    public DiWorkContentValues putOperatorNull() {
        mContentValues.putNull(DiWorkColumns.OPERATOR);
        return this;
    }
}
