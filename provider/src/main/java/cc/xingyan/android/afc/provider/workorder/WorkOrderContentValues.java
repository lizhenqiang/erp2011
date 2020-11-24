package cc.xingyan.android.afc.provider.workorder;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code work_order} table.
 */
public class WorkOrderContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return WorkOrderColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  WorkOrderSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  WorkOrderSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public WorkOrderContentValues putUserId(String value) {
        mContentValues.put(WorkOrderColumns.USER_ID, value);
        return this;
    }

    public WorkOrderContentValues putUserIdNull() {
        mContentValues.putNull(WorkOrderColumns.USER_ID);
        return this;
    }

    public WorkOrderContentValues putGuid(String value) {
        mContentValues.put(WorkOrderColumns.GUID, value);
        return this;
    }

    public WorkOrderContentValues putGuidNull() {
        mContentValues.putNull(WorkOrderColumns.GUID);
        return this;
    }

    public WorkOrderContentValues putNo(String value) {
        mContentValues.put(WorkOrderColumns.NO, value);
        return this;
    }

    public WorkOrderContentValues putNoNull() {
        mContentValues.putNull(WorkOrderColumns.NO);
        return this;
    }

    public WorkOrderContentValues putDeviceCode(String value) {
        mContentValues.put(WorkOrderColumns.DEVICE_CODE, value);
        return this;
    }

    public WorkOrderContentValues putDeviceCodeNull() {
        mContentValues.putNull(WorkOrderColumns.DEVICE_CODE);
        return this;
    }

    public WorkOrderContentValues putDeviceName(String value) {
        mContentValues.put(WorkOrderColumns.DEVICE_NAME, value);
        return this;
    }

    public WorkOrderContentValues putDeviceNameNull() {
        mContentValues.putNull(WorkOrderColumns.DEVICE_NAME);
        return this;
    }

    public WorkOrderContentValues putFaultDescriptionText(String value) {
        mContentValues.put(WorkOrderColumns.FAULT_DESCRIPTION_TEXT, value);
        return this;
    }

    public WorkOrderContentValues putFaultDescriptionTextNull() {
        mContentValues.putNull(WorkOrderColumns.FAULT_DESCRIPTION_TEXT);
        return this;
    }

    public WorkOrderContentValues putFaultDescriptionCode(String value) {
        mContentValues.put(WorkOrderColumns.FAULT_DESCRIPTION_CODE, value);
        return this;
    }

    public WorkOrderContentValues putFaultDescriptionCodeNull() {
        mContentValues.putNull(WorkOrderColumns.FAULT_DESCRIPTION_CODE);
        return this;
    }

    public WorkOrderContentValues putFaultTypeText(String value) {
        mContentValues.put(WorkOrderColumns.FAULT_TYPE_TEXT, value);
        return this;
    }

    public WorkOrderContentValues putFaultTypeTextNull() {
        mContentValues.putNull(WorkOrderColumns.FAULT_TYPE_TEXT);
        return this;
    }

    public WorkOrderContentValues putFaultTypeCode(String value) {
        mContentValues.put(WorkOrderColumns.FAULT_TYPE_CODE, value);
        return this;
    }

    public WorkOrderContentValues putFaultTypeCodeNull() {
        mContentValues.putNull(WorkOrderColumns.FAULT_TYPE_CODE);
        return this;
    }

    public WorkOrderContentValues putReporterTypeText(String value) {
        mContentValues.put(WorkOrderColumns.REPORTER_TYPE_TEXT, value);
        return this;
    }

    public WorkOrderContentValues putReporterTypeTextNull() {
        mContentValues.putNull(WorkOrderColumns.REPORTER_TYPE_TEXT);
        return this;
    }

    public WorkOrderContentValues putReporterTypeCode(String value) {
        mContentValues.put(WorkOrderColumns.REPORTER_TYPE_CODE, value);
        return this;
    }

    public WorkOrderContentValues putReporterTypeCodeNull() {
        mContentValues.putNull(WorkOrderColumns.REPORTER_TYPE_CODE);
        return this;
    }

    public WorkOrderContentValues putReporter(String value) {
        mContentValues.put(WorkOrderColumns.REPORTER, value);
        return this;
    }

    public WorkOrderContentValues putReporterNull() {
        mContentValues.putNull(WorkOrderColumns.REPORTER);
        return this;
    }

    public WorkOrderContentValues putFaultNote(String value) {
        mContentValues.put(WorkOrderColumns.FAULT_NOTE, value);
        return this;
    }

    public WorkOrderContentValues putFaultNoteNull() {
        mContentValues.putNull(WorkOrderColumns.FAULT_NOTE);
        return this;
    }

    public WorkOrderContentValues putFaultCauseText(String value) {
        mContentValues.put(WorkOrderColumns.FAULT_CAUSE_TEXT, value);
        return this;
    }

    public WorkOrderContentValues putFaultCauseTextNull() {
        mContentValues.putNull(WorkOrderColumns.FAULT_CAUSE_TEXT);
        return this;
    }

    public WorkOrderContentValues putFaultCauseCode(String value) {
        mContentValues.put(WorkOrderColumns.FAULT_CAUSE_CODE, value);
        return this;
    }

    public WorkOrderContentValues putFaultCauseCodeNull() {
        mContentValues.putNull(WorkOrderColumns.FAULT_CAUSE_CODE);
        return this;
    }

    public WorkOrderContentValues putFaultStartTime(Long value) {
        mContentValues.put(WorkOrderColumns.FAULT_START_TIME, value);
        return this;
    }

    public WorkOrderContentValues putFaultStartTimeNull() {
        mContentValues.putNull(WorkOrderColumns.FAULT_START_TIME);
        return this;
    }

    public WorkOrderContentValues putOperationText(String value) {
        mContentValues.put(WorkOrderColumns.OPERATION_TEXT, value);
        return this;
    }

    public WorkOrderContentValues putOperationTextNull() {
        mContentValues.putNull(WorkOrderColumns.OPERATION_TEXT);
        return this;
    }

    public WorkOrderContentValues putOperationCode(String value) {
        mContentValues.put(WorkOrderColumns.OPERATION_CODE, value);
        return this;
    }

    public WorkOrderContentValues putOperationCodeNull() {
        mContentValues.putNull(WorkOrderColumns.OPERATION_CODE);
        return this;
    }

    public WorkOrderContentValues putOperationResultText(String value) {
        mContentValues.put(WorkOrderColumns.OPERATION_RESULT_TEXT, value);
        return this;
    }

    public WorkOrderContentValues putOperationResultTextNull() {
        mContentValues.putNull(WorkOrderColumns.OPERATION_RESULT_TEXT);
        return this;
    }

    public WorkOrderContentValues putOperationResultCode(String value) {
        mContentValues.put(WorkOrderColumns.OPERATION_RESULT_CODE, value);
        return this;
    }

    public WorkOrderContentValues putOperationResultCodeNull() {
        mContentValues.putNull(WorkOrderColumns.OPERATION_RESULT_CODE);
        return this;
    }

    public WorkOrderContentValues putOperationNote(String value) {
        mContentValues.put(WorkOrderColumns.OPERATION_NOTE, value);
        return this;
    }

    public WorkOrderContentValues putOperationNoteNull() {
        mContentValues.putNull(WorkOrderColumns.OPERATION_NOTE);
        return this;
    }

    public WorkOrderContentValues putOperatorText(String value) {
        mContentValues.put(WorkOrderColumns.OPERATOR_TEXT, value);
        return this;
    }

    public WorkOrderContentValues putOperatorTextNull() {
        mContentValues.putNull(WorkOrderColumns.OPERATOR_TEXT);
        return this;
    }

    public WorkOrderContentValues putOperatorCode(String value) {
        mContentValues.put(WorkOrderColumns.OPERATOR_CODE, value);
        return this;
    }

    public WorkOrderContentValues putOperatorCodeNull() {
        mContentValues.putNull(WorkOrderColumns.OPERATOR_CODE);
        return this;
    }

    public WorkOrderContentValues putOperationStartTime(Long value) {
        mContentValues.put(WorkOrderColumns.OPERATION_START_TIME, value);
        return this;
    }

    public WorkOrderContentValues putOperationStartTimeNull() {
        mContentValues.putNull(WorkOrderColumns.OPERATION_START_TIME);
        return this;
    }

    public WorkOrderContentValues putOperationEndTime(Long value) {
        mContentValues.put(WorkOrderColumns.OPERATION_END_TIME, value);
        return this;
    }

    public WorkOrderContentValues putOperationEndTimeNull() {
        mContentValues.putNull(WorkOrderColumns.OPERATION_END_TIME);
        return this;
    }

    public WorkOrderContentValues putFormFlagText(String value) {
        mContentValues.put(WorkOrderColumns.FORM_FLAG_TEXT, value);
        return this;
    }

    public WorkOrderContentValues putFormFlagTextNull() {
        mContentValues.putNull(WorkOrderColumns.FORM_FLAG_TEXT);
        return this;
    }

    public WorkOrderContentValues putFormFlagCode(String value) {
        mContentValues.put(WorkOrderColumns.FORM_FLAG_CODE, value);
        return this;
    }

    public WorkOrderContentValues putFormFlagCodeNull() {
        mContentValues.putNull(WorkOrderColumns.FORM_FLAG_CODE);
        return this;
    }

    public WorkOrderContentValues putSyncStatus(SyncStatus value) {
        mContentValues.put(WorkOrderColumns.SYNC_STATUS, value == null ? null : value.ordinal());
        return this;
    }

    public WorkOrderContentValues putSyncStatusNull() {
        mContentValues.putNull(WorkOrderColumns.SYNC_STATUS);
        return this;
    }

    public WorkOrderContentValues putLastModified(Long value) {
        mContentValues.put(WorkOrderColumns.LAST_MODIFIED, value);
        return this;
    }

    public WorkOrderContentValues putLastModifiedNull() {
        mContentValues.putNull(WorkOrderColumns.LAST_MODIFIED);
        return this;
    }

    public WorkOrderContentValues putDeletePending(Boolean value) {
        mContentValues.put(WorkOrderColumns.DELETE_PENDING, value);
        return this;
    }

    public WorkOrderContentValues putDeletePendingNull() {
        mContentValues.putNull(WorkOrderColumns.DELETE_PENDING);
        return this;
    }

    public WorkOrderContentValues putUploadPending(Boolean value) {
        mContentValues.put(WorkOrderColumns.UPLOAD_PENDING, value);
        return this;
    }

    public WorkOrderContentValues putUploadPendingNull() {
        mContentValues.putNull(WorkOrderColumns.UPLOAD_PENDING);
        return this;
    }
}
