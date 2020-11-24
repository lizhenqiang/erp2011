package cc.xingyan.android.afc.provider.pmwork;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code pm_work} table.
 */
public class PmWorkContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return PmWorkColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  PmWorkSelection where) {
        return contentResolver.update(where.uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  PmWorkSelection where) {
        return context.getContentResolver().update(where.uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public PmWorkContentValues putUserId(String value) {
        mContentValues.put(PmWorkColumns.USER_ID, value);
        return this;
    }

    public PmWorkContentValues putUserIdNull() {
        mContentValues.putNull(PmWorkColumns.USER_ID);
        return this;
    }

    public PmWorkContentValues putGuid(String value) {
        mContentValues.put(PmWorkColumns.GUID, value);
        return this;
    }

    public PmWorkContentValues putGuidNull() {
        mContentValues.putNull(PmWorkColumns.GUID);
        return this;
    }

    public PmWorkContentValues putJobId(String value) {
        mContentValues.put(PmWorkColumns.JOB_ID, value);
        return this;
    }

    public PmWorkContentValues putJobIdNull() {
        mContentValues.putNull(PmWorkColumns.JOB_ID);
        return this;
    }

    public PmWorkContentValues putJobDescription(String value) {
        mContentValues.put(PmWorkColumns.JOB_DESCRIPTION, value);
        return this;
    }

    public PmWorkContentValues putJobDescriptionNull() {
        mContentValues.putNull(PmWorkColumns.JOB_DESCRIPTION);
        return this;
    }

    public PmWorkContentValues putWorkId(String value) {
        mContentValues.put(PmWorkColumns.WORK_ID, value);
        return this;
    }

    public PmWorkContentValues putWorkIdNull() {
        mContentValues.putNull(PmWorkColumns.WORK_ID);
        return this;
    }

    public PmWorkContentValues putWorkKind(String value) {
        mContentValues.put(PmWorkColumns.WORK_KIND, value);
        return this;
    }

    public PmWorkContentValues putWorkKindNull() {
        mContentValues.putNull(PmWorkColumns.WORK_KIND);
        return this;
    }

    public PmWorkContentValues putDeviceCode(String value) {
        mContentValues.put(PmWorkColumns.DEVICE_CODE, value);
        return this;
    }

    public PmWorkContentValues putDeviceCodeNull() {
        mContentValues.putNull(PmWorkColumns.DEVICE_CODE);
        return this;
    }

    public PmWorkContentValues putDeviceDescription(String value) {
        mContentValues.put(PmWorkColumns.DEVICE_DESCRIPTION, value);
        return this;
    }

    public PmWorkContentValues putDeviceDescriptionNull() {
        mContentValues.putNull(PmWorkColumns.DEVICE_DESCRIPTION);
        return this;
    }

    public PmWorkContentValues putExecutorId(String value) {
        mContentValues.put(PmWorkColumns.EXECUTOR_ID, value);
        return this;
    }

    public PmWorkContentValues putExecutorIdNull() {
        mContentValues.putNull(PmWorkColumns.EXECUTOR_ID);
        return this;
    }

    public PmWorkContentValues putExecutorDescription(String value) {
        mContentValues.put(PmWorkColumns.EXECUTOR_DESCRIPTION, value);
        return this;
    }

    public PmWorkContentValues putExecutorDescriptionNull() {
        mContentValues.putNull(PmWorkColumns.EXECUTOR_DESCRIPTION);
        return this;
    }

    public PmWorkContentValues putStartTime(Long value) {
        mContentValues.put(PmWorkColumns.START_TIME, value);
        return this;
    }

    public PmWorkContentValues putStartTimeNull() {
        mContentValues.putNull(PmWorkColumns.START_TIME);
        return this;
    }

    public PmWorkContentValues putEndTime(Long value) {
        mContentValues.put(PmWorkColumns.END_TIME, value);
        return this;
    }

    public PmWorkContentValues putEndTimeNull() {
        mContentValues.putNull(PmWorkColumns.END_TIME);
        return this;
    }

    public PmWorkContentValues putCompleteTime(Long value) {
        mContentValues.put(PmWorkColumns.COMPLETE_TIME, value);
        return this;
    }

    public PmWorkContentValues putCompleteTimeNull() {
        mContentValues.putNull(PmWorkColumns.COMPLETE_TIME);
        return this;
    }

    public PmWorkContentValues putLastModified(Long value) {
        mContentValues.put(PmWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmWorkContentValues putLastModifiedNull() {
        mContentValues.putNull(PmWorkColumns.LAST_MODIFIED);
        return this;
    }

    public PmWorkContentValues putStatus(PmWorkStatus value) {
        mContentValues.put(PmWorkColumns.STATUS, value == null ? null : value.ordinal());
        return this;
    }

    public PmWorkContentValues putStatusNull() {
        mContentValues.putNull(PmWorkColumns.STATUS);
        return this;
    }

    public PmWorkContentValues putUploadPending(Boolean value) {
        mContentValues.put(PmWorkColumns.UPLOAD_PENDING, value);
        return this;
    }

    public PmWorkContentValues putUploadPendingNull() {
        mContentValues.putNull(PmWorkColumns.UPLOAD_PENDING);
        return this;
    }
}
