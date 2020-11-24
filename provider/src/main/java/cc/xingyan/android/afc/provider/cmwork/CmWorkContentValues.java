package cc.xingyan.android.afc.provider.cmwork;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code cm_work} table.
 */
public class CmWorkContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return CmWorkColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  CmWorkSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  CmWorkSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public CmWorkContentValues putUserId(String value) {
        mContentValues.put(CmWorkColumns.USER_ID, value);
        return this;
    }

    public CmWorkContentValues putUserIdNull() {
        mContentValues.putNull(CmWorkColumns.USER_ID);
        return this;
    }

    public CmWorkContentValues putGuid(String value) {
        mContentValues.put(CmWorkColumns.GUID, value);
        return this;
    }

    public CmWorkContentValues putGuidNull() {
        mContentValues.putNull(CmWorkColumns.GUID);
        return this;
    }

    public CmWorkContentValues putOrderId(String value) {
        mContentValues.put(CmWorkColumns.ORDER_ID, value);
        return this;
    }

    public CmWorkContentValues putOrderIdNull() {
        mContentValues.putNull(CmWorkColumns.ORDER_ID);
        return this;
    }

    public CmWorkContentValues putPriority(String value) {
        mContentValues.put(CmWorkColumns.PRIORITY, value);
        return this;
    }

    public CmWorkContentValues putPriorityNull() {
        mContentValues.putNull(CmWorkColumns.PRIORITY);
        return this;
    }

    public CmWorkContentValues putDeviceCode(String value) {
        mContentValues.put(CmWorkColumns.DEVICE_CODE, value);
        return this;
    }

    public CmWorkContentValues putDeviceCodeNull() {
        mContentValues.putNull(CmWorkColumns.DEVICE_CODE);
        return this;
    }

    public CmWorkContentValues putDeviceName(String value) {
        mContentValues.put(CmWorkColumns.DEVICE_NAME, value);
        return this;
    }

    public CmWorkContentValues putDeviceNameNull() {
        mContentValues.putNull(CmWorkColumns.DEVICE_NAME);
        return this;
    }

    public CmWorkContentValues putFaultDescriptionText(String value) {
        mContentValues.put(CmWorkColumns.FAULT_DESCRIPTION_TEXT, value);
        return this;
    }

    public CmWorkContentValues putFaultDescriptionTextNull() {
        mContentValues.putNull(CmWorkColumns.FAULT_DESCRIPTION_TEXT);
        return this;
    }

    public CmWorkContentValues putFaultDescriptionCode(String value) {
        mContentValues.put(CmWorkColumns.FAULT_DESCRIPTION_CODE, value);
        return this;
    }

    public CmWorkContentValues putFaultDescriptionCodeNull() {
        mContentValues.putNull(CmWorkColumns.FAULT_DESCRIPTION_CODE);
        return this;
    }

    public CmWorkContentValues putFaultNote(String value) {
        mContentValues.put(CmWorkColumns.FAULT_NOTE, value);
        return this;
    }

    public CmWorkContentValues putFaultNoteNull() {
        mContentValues.putNull(CmWorkColumns.FAULT_NOTE);
        return this;
    }

    public CmWorkContentValues putReporterTypeText(String value) {
        mContentValues.put(CmWorkColumns.REPORTER_TYPE_TEXT, value);
        return this;
    }

    public CmWorkContentValues putReporterTypeTextNull() {
        mContentValues.putNull(CmWorkColumns.REPORTER_TYPE_TEXT);
        return this;
    }

    public CmWorkContentValues putReporterTypeCode(String value) {
        mContentValues.put(CmWorkColumns.REPORTER_TYPE_CODE, value);
        return this;
    }

    public CmWorkContentValues putReporterTypeCodeNull() {
        mContentValues.putNull(CmWorkColumns.REPORTER_TYPE_CODE);
        return this;
    }

    public CmWorkContentValues putInstruct(String value) {
        mContentValues.put(CmWorkColumns.INSTRUCT, value);
        return this;
    }

    public CmWorkContentValues putInstructNull() {
        mContentValues.putNull(CmWorkColumns.INSTRUCT);
        return this;
    }

    public CmWorkContentValues putReporter(String value) {
        mContentValues.put(CmWorkColumns.REPORTER, value);
        return this;
    }

    public CmWorkContentValues putReporterNull() {
        mContentValues.putNull(CmWorkColumns.REPORTER);
        return this;
    }

    public CmWorkContentValues putPrepareMan(String value) {
        mContentValues.put(CmWorkColumns.PREPARE_MAN, value);
        return this;
    }

    public CmWorkContentValues putPrepareManNull() {
        mContentValues.putNull(CmWorkColumns.PREPARE_MAN);
        return this;
    }

    public CmWorkContentValues putDispose(String value) {
        mContentValues.put(CmWorkColumns.DISPOSE, value);
        return this;
    }

    public CmWorkContentValues putDisposeNull() {
        mContentValues.putNull(CmWorkColumns.DISPOSE);
        return this;
    }

    public CmWorkContentValues putWorkarea(String value) {
        mContentValues.put(CmWorkColumns.WORKAREA, value);
        return this;
    }

    public CmWorkContentValues putWorkareaNull() {
        mContentValues.putNull(CmWorkColumns.WORKAREA);
        return this;
    }

    public CmWorkContentValues putFaultStartTime(Long value) {
        mContentValues.put(CmWorkColumns.FAULT_START_TIME, value);
        return this;
    }

    public CmWorkContentValues putFaultStartTimeNull() {
        mContentValues.putNull(CmWorkColumns.FAULT_START_TIME);
        return this;
    }

    public CmWorkContentValues putApplySTime(Long value) {
        mContentValues.put(CmWorkColumns.APPLY_S_TIME, value);
        return this;
    }

    public CmWorkContentValues putApplySTimeNull() {
        mContentValues.putNull(CmWorkColumns.APPLY_S_TIME);
        return this;
    }

    public CmWorkContentValues putApplyFTime(Long value) {
        mContentValues.put(CmWorkColumns.APPLY_F_TIME, value);
        return this;
    }

    public CmWorkContentValues putApplyFTimeNull() {
        mContentValues.putNull(CmWorkColumns.APPLY_F_TIME);
        return this;
    }

    public CmWorkContentValues putPlanSTime(Long value) {
        mContentValues.put(CmWorkColumns.PLAN_S_TIME, value);
        return this;
    }

    public CmWorkContentValues putPlanSTimeNull() {
        mContentValues.putNull(CmWorkColumns.PLAN_S_TIME);
        return this;
    }

    public CmWorkContentValues putPlanFTime(Long value) {
        mContentValues.put(CmWorkColumns.PLAN_F_TIME, value);
        return this;
    }

    public CmWorkContentValues putPlanFTimeNull() {
        mContentValues.putNull(CmWorkColumns.PLAN_F_TIME);
        return this;
    }

    public CmWorkContentValues putFaultGradeText(String value) {
        mContentValues.put(CmWorkColumns.FAULT_GRADE_TEXT, value);
        return this;
    }

    public CmWorkContentValues putFaultGradeTextNull() {
        mContentValues.putNull(CmWorkColumns.FAULT_GRADE_TEXT);
        return this;
    }

    public CmWorkContentValues putFaultGradeCode(String value) {
        mContentValues.put(CmWorkColumns.FAULT_GRADE_CODE, value);
        return this;
    }

    public CmWorkContentValues putFaultGradeCodeNull() {
        mContentValues.putNull(CmWorkColumns.FAULT_GRADE_CODE);
        return this;
    }

    public CmWorkContentValues putFaultTypeText(String value) {
        mContentValues.put(CmWorkColumns.FAULT_TYPE_TEXT, value);
        return this;
    }

    public CmWorkContentValues putFaultTypeTextNull() {
        mContentValues.putNull(CmWorkColumns.FAULT_TYPE_TEXT);
        return this;
    }

    public CmWorkContentValues putFaultTypeCode(String value) {
        mContentValues.put(CmWorkColumns.FAULT_TYPE_CODE, value);
        return this;
    }

    public CmWorkContentValues putFaultTypeCodeNull() {
        mContentValues.putNull(CmWorkColumns.FAULT_TYPE_CODE);
        return this;
    }

    public CmWorkContentValues putFaultCauseText(String value) {
        mContentValues.put(CmWorkColumns.FAULT_CAUSE_TEXT, value);
        return this;
    }

    public CmWorkContentValues putFaultCauseTextNull() {
        mContentValues.putNull(CmWorkColumns.FAULT_CAUSE_TEXT);
        return this;
    }

    public CmWorkContentValues putFaultCauseCode(String value) {
        mContentValues.put(CmWorkColumns.FAULT_CAUSE_CODE, value);
        return this;
    }

    public CmWorkContentValues putFaultCauseCodeNull() {
        mContentValues.putNull(CmWorkColumns.FAULT_CAUSE_CODE);
        return this;
    }

    public CmWorkContentValues putWorkDetailsText(String value) {
        mContentValues.put(CmWorkColumns.WORK_DETAILS_TEXT, value);
        return this;
    }

    public CmWorkContentValues putWorkDetailsTextNull() {
        mContentValues.putNull(CmWorkColumns.WORK_DETAILS_TEXT);
        return this;
    }

    public CmWorkContentValues putWorkDetailsCode(String value) {
        mContentValues.put(CmWorkColumns.WORK_DETAILS_CODE, value);
        return this;
    }

    public CmWorkContentValues putWorkDetailsCodeNull() {
        mContentValues.putNull(CmWorkColumns.WORK_DETAILS_CODE);
        return this;
    }

    public CmWorkContentValues putWorkDoneText(String value) {
        mContentValues.put(CmWorkColumns.WORK_DONE_TEXT, value);
        return this;
    }

    public CmWorkContentValues putWorkDoneTextNull() {
        mContentValues.putNull(CmWorkColumns.WORK_DONE_TEXT);
        return this;
    }

    public CmWorkContentValues putWorkDoneCode(String value) {
        mContentValues.put(CmWorkColumns.WORK_DONE_CODE, value);
        return this;
    }

    public CmWorkContentValues putWorkDoneCodeNull() {
        mContentValues.putNull(CmWorkColumns.WORK_DONE_CODE);
        return this;
    }

    public CmWorkContentValues putFaultCauseNote(String value) {
        mContentValues.put(CmWorkColumns.FAULT_CAUSE_NOTE, value);
        return this;
    }

    public CmWorkContentValues putFaultCauseNoteNull() {
        mContentValues.putNull(CmWorkColumns.FAULT_CAUSE_NOTE);
        return this;
    }

    public CmWorkContentValues putWorkNote(String value) {
        mContentValues.put(CmWorkColumns.WORK_NOTE, value);
        return this;
    }

    public CmWorkContentValues putWorkNoteNull() {
        mContentValues.putNull(CmWorkColumns.WORK_NOTE);
        return this;
    }

    public CmWorkContentValues putOperatorText(String value) {
        mContentValues.put(CmWorkColumns.OPERATOR_TEXT, value);
        return this;
    }

    public CmWorkContentValues putOperatorTextNull() {
        mContentValues.putNull(CmWorkColumns.OPERATOR_TEXT);
        return this;
    }

    public CmWorkContentValues putOperatorCode(String value) {
        mContentValues.put(CmWorkColumns.OPERATOR_CODE, value);
        return this;
    }

    public CmWorkContentValues putOperatorCodeNull() {
        mContentValues.putNull(CmWorkColumns.OPERATOR_CODE);
        return this;
    }

    public CmWorkContentValues putOperationStartTime(Long value) {
        mContentValues.put(CmWorkColumns.OPERATION_START_TIME, value);
        return this;
    }

    public CmWorkContentValues putOperationStartTimeNull() {
        mContentValues.putNull(CmWorkColumns.OPERATION_START_TIME);
        return this;
    }

    public CmWorkContentValues putOperationEndTime(Long value) {
        mContentValues.put(CmWorkColumns.OPERATION_END_TIME, value);
        return this;
    }

    public CmWorkContentValues putOperationEndTimeNull() {
        mContentValues.putNull(CmWorkColumns.OPERATION_END_TIME);
        return this;
    }

    public CmWorkContentValues putOrderReceiveTime(Long value) {
        mContentValues.put(CmWorkColumns.ORDER_RECEIVE_TIME, value);
        return this;
    }

    public CmWorkContentValues putOrderReceiveTimeNull() {
        mContentValues.putNull(CmWorkColumns.ORDER_RECEIVE_TIME);
        return this;
    }

    public CmWorkContentValues putArriveTime(Long value) {
        mContentValues.put(CmWorkColumns.ARRIVE_TIME, value);
        return this;
    }

    public CmWorkContentValues putArriveTimeNull() {
        mContentValues.putNull(CmWorkColumns.ARRIVE_TIME);
        return this;
    }

    public CmWorkContentValues putIntCustomerNo(String value) {
        mContentValues.put(CmWorkColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public CmWorkContentValues putIntCustomerNoNull() {
        mContentValues.putNull(CmWorkColumns.INT_CUSTOMER_NO);
        return this;
    }

    public CmWorkContentValues putFormFlag(String value) {
        mContentValues.put(CmWorkColumns.FORM_FLAG, value);
        return this;
    }

    public CmWorkContentValues putFormFlagNull() {
        mContentValues.putNull(CmWorkColumns.FORM_FLAG);
        return this;
    }

    public CmWorkContentValues putEquipFault(String value) {
        mContentValues.put(CmWorkColumns.EQUIP_FAULT, value);
        return this;
    }

    public CmWorkContentValues putEquipFaultNull() {
        mContentValues.putNull(CmWorkColumns.EQUIP_FAULT);
        return this;
    }

    public CmWorkContentValues putStatusBackstage(String value) {
        mContentValues.put(CmWorkColumns.STATUS_BACKSTAGE, value);
        return this;
    }

    public CmWorkContentValues putStatusBackstageNull() {
        mContentValues.putNull(CmWorkColumns.STATUS_BACKSTAGE);
        return this;
    }

    public CmWorkContentValues putStatus(CmWorkStatus value) {
        mContentValues.put(CmWorkColumns.STATUS, value == null ? null : value.ordinal());
        return this;
    }

    public CmWorkContentValues putStatusNull() {
        mContentValues.putNull(CmWorkColumns.STATUS);
        return this;
    }

    public CmWorkContentValues putLastModified(Long value) {
        mContentValues.put(CmWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public CmWorkContentValues putLastModifiedNull() {
        mContentValues.putNull(CmWorkColumns.LAST_MODIFIED);
        return this;
    }

    public CmWorkContentValues putDeletePending(Boolean value) {
        mContentValues.put(CmWorkColumns.DELETE_PENDING, value);
        return this;
    }

    public CmWorkContentValues putDeletePendingNull() {
        mContentValues.putNull(CmWorkColumns.DELETE_PENDING);
        return this;
    }

    public CmWorkContentValues putUploadPending(Boolean value) {
        mContentValues.put(CmWorkColumns.UPLOAD_PENDING, value);
        return this;
    }

    public CmWorkContentValues putUploadPendingNull() {
        mContentValues.putNull(CmWorkColumns.UPLOAD_PENDING);
        return this;
    }
}
