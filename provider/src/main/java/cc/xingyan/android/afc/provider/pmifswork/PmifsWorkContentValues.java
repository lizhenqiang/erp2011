package cc.xingyan.android.afc.provider.pmifswork;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code pmifs_work} table.
 */
public class PmifsWorkContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return PmifsWorkColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  PmifsWorkSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  PmifsWorkSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public PmifsWorkContentValues putUserId(String value) {
        mContentValues.put(PmifsWorkColumns.USER_ID, value);
        return this;
    }

    public PmifsWorkContentValues putUserIdNull() {
        mContentValues.putNull(PmifsWorkColumns.USER_ID);
        return this;
    }

    public PmifsWorkContentValues putGuid(String value) {
        mContentValues.put(PmifsWorkColumns.GUID, value);
        return this;
    }

    public PmifsWorkContentValues putGuidNull() {
        mContentValues.putNull(PmifsWorkColumns.GUID);
        return this;
    }

    public PmifsWorkContentValues putOrderId(String value) {
        mContentValues.put(PmifsWorkColumns.ORDER_ID, value);
        return this;
    }

    public PmifsWorkContentValues putOrderIdNull() {
        mContentValues.putNull(PmifsWorkColumns.ORDER_ID);
        return this;
    }

    public PmifsWorkContentValues putPriorityText(String value) {
        mContentValues.put(PmifsWorkColumns.PRIORITY_TEXT, value);
        return this;
    }

    public PmifsWorkContentValues putPriorityTextNull() {
        mContentValues.putNull(PmifsWorkColumns.PRIORITY_TEXT);
        return this;
    }

    public PmifsWorkContentValues putPriorityCode(String value) {
        mContentValues.put(PmifsWorkColumns.PRIORITY_CODE, value);
        return this;
    }

    public PmifsWorkContentValues putPriorityCodeNull() {
        mContentValues.putNull(PmifsWorkColumns.PRIORITY_CODE);
        return this;
    }

    public PmifsWorkContentValues putDeviceCode(String value) {
        mContentValues.put(PmifsWorkColumns.DEVICE_CODE, value);
        return this;
    }

    public PmifsWorkContentValues putDeviceCodeNull() {
        mContentValues.putNull(PmifsWorkColumns.DEVICE_CODE);
        return this;
    }

    public PmifsWorkContentValues putDeviceName(String value) {
        mContentValues.put(PmifsWorkColumns.DEVICE_NAME, value);
        return this;
    }

    public PmifsWorkContentValues putDeviceNameNull() {
        mContentValues.putNull(PmifsWorkColumns.DEVICE_NAME);
        return this;
    }

    public PmifsWorkContentValues putDeviceLogicCode(String value) {
        mContentValues.put(PmifsWorkColumns.DEVICE_LOGIC_CODE, value);
        return this;
    }

    public PmifsWorkContentValues putDeviceLogicCodeNull() {
        mContentValues.putNull(PmifsWorkColumns.DEVICE_LOGIC_CODE);
        return this;
    }

    public PmifsWorkContentValues putDeviceLogicName(String value) {
        mContentValues.put(PmifsWorkColumns.DEVICE_LOGIC_NAME, value);
        return this;
    }

    public PmifsWorkContentValues putDeviceLogicNameNull() {
        mContentValues.putNull(PmifsWorkColumns.DEVICE_LOGIC_NAME);
        return this;
    }

    public PmifsWorkContentValues putInstruct(String value) {
        mContentValues.put(PmifsWorkColumns.INSTRUCT, value);
        return this;
    }

    public PmifsWorkContentValues putInstructNull() {
        mContentValues.putNull(PmifsWorkColumns.INSTRUCT);
        return this;
    }

    public PmifsWorkContentValues putPrepareManCode(String value) {
        mContentValues.put(PmifsWorkColumns.PREPARE_MAN_CODE, value);
        return this;
    }

    public PmifsWorkContentValues putPrepareManCodeNull() {
        mContentValues.putNull(PmifsWorkColumns.PREPARE_MAN_CODE);
        return this;
    }

    public PmifsWorkContentValues putPrepareManText(String value) {
        mContentValues.put(PmifsWorkColumns.PREPARE_MAN_TEXT, value);
        return this;
    }

    public PmifsWorkContentValues putPrepareManTextNull() {
        mContentValues.putNull(PmifsWorkColumns.PREPARE_MAN_TEXT);
        return this;
    }

    public PmifsWorkContentValues putWorkareaText(String value) {
        mContentValues.put(PmifsWorkColumns.WORKAREA_TEXT, value);
        return this;
    }

    public PmifsWorkContentValues putWorkareaTextNull() {
        mContentValues.putNull(PmifsWorkColumns.WORKAREA_TEXT);
        return this;
    }

    public PmifsWorkContentValues putWorkareaCode(String value) {
        mContentValues.put(PmifsWorkColumns.WORKAREA_CODE, value);
        return this;
    }

    public PmifsWorkContentValues putWorkareaCodeNull() {
        mContentValues.putNull(PmifsWorkColumns.WORKAREA_CODE);
        return this;
    }

    public PmifsWorkContentValues putApplySTime(Long value) {
        mContentValues.put(PmifsWorkColumns.APPLY_S_TIME, value);
        return this;
    }

    public PmifsWorkContentValues putApplySTimeNull() {
        mContentValues.putNull(PmifsWorkColumns.APPLY_S_TIME);
        return this;
    }

    public PmifsWorkContentValues putApplyFTime(Long value) {
        mContentValues.put(PmifsWorkColumns.APPLY_F_TIME, value);
        return this;
    }

    public PmifsWorkContentValues putApplyFTimeNull() {
        mContentValues.putNull(PmifsWorkColumns.APPLY_F_TIME);
        return this;
    }

    public PmifsWorkContentValues putPlanSTime(Long value) {
        mContentValues.put(PmifsWorkColumns.PLAN_S_TIME, value);
        return this;
    }

    public PmifsWorkContentValues putPlanSTimeNull() {
        mContentValues.putNull(PmifsWorkColumns.PLAN_S_TIME);
        return this;
    }

    public PmifsWorkContentValues putPlanFTime(Long value) {
        mContentValues.put(PmifsWorkColumns.PLAN_F_TIME, value);
        return this;
    }

    public PmifsWorkContentValues putPlanFTimeNull() {
        mContentValues.putNull(PmifsWorkColumns.PLAN_F_TIME);
        return this;
    }

    public PmifsWorkContentValues putWorkDetailsText(String value) {
        mContentValues.put(PmifsWorkColumns.WORK_DETAILS_TEXT, value);
        return this;
    }

    public PmifsWorkContentValues putWorkDetailsTextNull() {
        mContentValues.putNull(PmifsWorkColumns.WORK_DETAILS_TEXT);
        return this;
    }

    public PmifsWorkContentValues putWorkDetailsCode(String value) {
        mContentValues.put(PmifsWorkColumns.WORK_DETAILS_CODE, value);
        return this;
    }

    public PmifsWorkContentValues putWorkDetailsCodeNull() {
        mContentValues.putNull(PmifsWorkColumns.WORK_DETAILS_CODE);
        return this;
    }

    public PmifsWorkContentValues putWorkDoneText(String value) {
        mContentValues.put(PmifsWorkColumns.WORK_DONE_TEXT, value);
        return this;
    }

    public PmifsWorkContentValues putWorkDoneTextNull() {
        mContentValues.putNull(PmifsWorkColumns.WORK_DONE_TEXT);
        return this;
    }

    public PmifsWorkContentValues putWorkDoneCode(String value) {
        mContentValues.put(PmifsWorkColumns.WORK_DONE_CODE, value);
        return this;
    }

    public PmifsWorkContentValues putWorkDoneCodeNull() {
        mContentValues.putNull(PmifsWorkColumns.WORK_DONE_CODE);
        return this;
    }

    public PmifsWorkContentValues putWorkNote(String value) {
        mContentValues.put(PmifsWorkColumns.WORK_NOTE, value);
        return this;
    }

    public PmifsWorkContentValues putWorkNoteNull() {
        mContentValues.putNull(PmifsWorkColumns.WORK_NOTE);
        return this;
    }

    public PmifsWorkContentValues putOperatorText(String value) {
        mContentValues.put(PmifsWorkColumns.OPERATOR_TEXT, value);
        return this;
    }

    public PmifsWorkContentValues putOperatorTextNull() {
        mContentValues.putNull(PmifsWorkColumns.OPERATOR_TEXT);
        return this;
    }

    public PmifsWorkContentValues putOperatorCode(String value) {
        mContentValues.put(PmifsWorkColumns.OPERATOR_CODE, value);
        return this;
    }

    public PmifsWorkContentValues putOperatorCodeNull() {
        mContentValues.putNull(PmifsWorkColumns.OPERATOR_CODE);
        return this;
    }

    public PmifsWorkContentValues putOperationStartTime(Long value) {
        mContentValues.put(PmifsWorkColumns.OPERATION_START_TIME, value);
        return this;
    }

    public PmifsWorkContentValues putOperationStartTimeNull() {
        mContentValues.putNull(PmifsWorkColumns.OPERATION_START_TIME);
        return this;
    }

    public PmifsWorkContentValues putOperationEndTime(Long value) {
        mContentValues.put(PmifsWorkColumns.OPERATION_END_TIME, value);
        return this;
    }

    public PmifsWorkContentValues putOperationEndTimeNull() {
        mContentValues.putNull(PmifsWorkColumns.OPERATION_END_TIME);
        return this;
    }

    public PmifsWorkContentValues putOrderReceiveTime(Long value) {
        mContentValues.put(PmifsWorkColumns.ORDER_RECEIVE_TIME, value);
        return this;
    }

    public PmifsWorkContentValues putOrderReceiveTimeNull() {
        mContentValues.putNull(PmifsWorkColumns.ORDER_RECEIVE_TIME);
        return this;
    }

    public PmifsWorkContentValues putArriveTime(Long value) {
        mContentValues.put(PmifsWorkColumns.ARRIVE_TIME, value);
        return this;
    }

    public PmifsWorkContentValues putArriveTimeNull() {
        mContentValues.putNull(PmifsWorkColumns.ARRIVE_TIME);
        return this;
    }

    public PmifsWorkContentValues putIntCustomerNo(String value) {
        mContentValues.put(PmifsWorkColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public PmifsWorkContentValues putIntCustomerNoNull() {
        mContentValues.putNull(PmifsWorkColumns.INT_CUSTOMER_NO);
        return this;
    }

    public PmifsWorkContentValues putStatusBackstage(String value) {
        mContentValues.put(PmifsWorkColumns.STATUS_BACKSTAGE, value);
        return this;
    }

    public PmifsWorkContentValues putStatusBackstageNull() {
        mContentValues.putNull(PmifsWorkColumns.STATUS_BACKSTAGE);
        return this;
    }

    public PmifsWorkContentValues putStatus(PmifsWorkStatus value) {
        mContentValues.put(PmifsWorkColumns.STATUS, value == null ? null : value.ordinal());
        return this;
    }

    public PmifsWorkContentValues putStatusNull() {
        mContentValues.putNull(PmifsWorkColumns.STATUS);
        return this;
    }

    public PmifsWorkContentValues putLastModified(Long value) {
        mContentValues.put(PmifsWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmifsWorkContentValues putLastModifiedNull() {
        mContentValues.putNull(PmifsWorkColumns.LAST_MODIFIED);
        return this;
    }

    public PmifsWorkContentValues putDeletePending(Boolean value) {
        mContentValues.put(PmifsWorkColumns.DELETE_PENDING, value);
        return this;
    }

    public PmifsWorkContentValues putDeletePendingNull() {
        mContentValues.putNull(PmifsWorkColumns.DELETE_PENDING);
        return this;
    }

    public PmifsWorkContentValues putUploadPending(Boolean value) {
        mContentValues.put(PmifsWorkColumns.UPLOAD_PENDING, value);
        return this;
    }

    public PmifsWorkContentValues putUploadPendingNull() {
        mContentValues.putNull(PmifsWorkColumns.UPLOAD_PENDING);
        return this;
    }

    public PmifsWorkContentValues putWorkTypeId(String value) {
        mContentValues.put(PmifsWorkColumns.WORK_TYPE_ID, value);
        return this;
    }

    public PmifsWorkContentValues putputWorkTypeIdNull() {
        mContentValues.putNull(PmifsWorkColumns.WORK_TYPE_ID);
        return this;
    }
}
