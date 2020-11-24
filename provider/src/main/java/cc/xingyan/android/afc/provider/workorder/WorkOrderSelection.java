package cc.xingyan.android.afc.provider.workorder;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code work_order} table.
 */
public class WorkOrderSelection extends AbstractSelection<WorkOrderSelection> {
    @Override
    protected Uri baseUri() {
        return WorkOrderColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code WorkOrderCursor} object, which is positioned before the first entry, or null.
     */
    public WorkOrderCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new WorkOrderCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public WorkOrderCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code WorkOrderCursor} object, which is positioned before the first entry, or null.
     */
    public WorkOrderCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new WorkOrderCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public WorkOrderCursor query(Context context) {
        return query(context, null);
    }


    public WorkOrderSelection id(long... value) {
        addEquals("work_order." + WorkOrderColumns._ID, toObjectArray(value));
        return this;
    }

    public WorkOrderSelection idNot(long... value) {
        addNotEquals("work_order." + WorkOrderColumns._ID, toObjectArray(value));
        return this;
    }

    public WorkOrderSelection orderById(boolean desc) {
        orderBy("work_order." + WorkOrderColumns._ID, desc);
        return this;
    }

    public WorkOrderSelection orderById() {
        return orderById(false);
    }

    public WorkOrderSelection userId(String... value) {
        addEquals(WorkOrderColumns.USER_ID, value);
        return this;
    }

    public WorkOrderSelection userIdNot(String... value) {
        addNotEquals(WorkOrderColumns.USER_ID, value);
        return this;
    }

    public WorkOrderSelection userIdLike(String... value) {
        addLike(WorkOrderColumns.USER_ID, value);
        return this;
    }

    public WorkOrderSelection userIdContains(String... value) {
        addContains(WorkOrderColumns.USER_ID, value);
        return this;
    }

    public WorkOrderSelection userIdStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.USER_ID, value);
        return this;
    }

    public WorkOrderSelection userIdEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.USER_ID, value);
        return this;
    }

    public WorkOrderSelection orderByUserId(boolean desc) {
        orderBy(WorkOrderColumns.USER_ID, desc);
        return this;
    }

    public WorkOrderSelection orderByUserId() {
        orderBy(WorkOrderColumns.USER_ID, false);
        return this;
    }

    public WorkOrderSelection guid(String... value) {
        addEquals(WorkOrderColumns.GUID, value);
        return this;
    }

    public WorkOrderSelection guidNot(String... value) {
        addNotEquals(WorkOrderColumns.GUID, value);
        return this;
    }

    public WorkOrderSelection guidLike(String... value) {
        addLike(WorkOrderColumns.GUID, value);
        return this;
    }

    public WorkOrderSelection guidContains(String... value) {
        addContains(WorkOrderColumns.GUID, value);
        return this;
    }

    public WorkOrderSelection guidStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.GUID, value);
        return this;
    }

    public WorkOrderSelection guidEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.GUID, value);
        return this;
    }

    public WorkOrderSelection orderByGuid(boolean desc) {
        orderBy(WorkOrderColumns.GUID, desc);
        return this;
    }

    public WorkOrderSelection orderByGuid() {
        orderBy(WorkOrderColumns.GUID, false);
        return this;
    }

    public WorkOrderSelection no(String... value) {
        addEquals(WorkOrderColumns.NO, value);
        return this;
    }

    public WorkOrderSelection noNot(String... value) {
        addNotEquals(WorkOrderColumns.NO, value);
        return this;
    }

    public WorkOrderSelection noLike(String... value) {
        addLike(WorkOrderColumns.NO, value);
        return this;
    }

    public WorkOrderSelection noContains(String... value) {
        addContains(WorkOrderColumns.NO, value);
        return this;
    }

    public WorkOrderSelection noStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.NO, value);
        return this;
    }

    public WorkOrderSelection noEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.NO, value);
        return this;
    }

    public WorkOrderSelection orderByNo(boolean desc) {
        orderBy(WorkOrderColumns.NO, desc);
        return this;
    }

    public WorkOrderSelection orderByNo() {
        orderBy(WorkOrderColumns.NO, false);
        return this;
    }

    public WorkOrderSelection deviceCode(String... value) {
        addEquals(WorkOrderColumns.DEVICE_CODE, value);
        return this;
    }

    public WorkOrderSelection deviceCodeNot(String... value) {
        addNotEquals(WorkOrderColumns.DEVICE_CODE, value);
        return this;
    }

    public WorkOrderSelection deviceCodeLike(String... value) {
        addLike(WorkOrderColumns.DEVICE_CODE, value);
        return this;
    }

    public WorkOrderSelection deviceCodeContains(String... value) {
        addContains(WorkOrderColumns.DEVICE_CODE, value);
        return this;
    }

    public WorkOrderSelection deviceCodeStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.DEVICE_CODE, value);
        return this;
    }

    public WorkOrderSelection deviceCodeEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.DEVICE_CODE, value);
        return this;
    }

    public WorkOrderSelection orderByDeviceCode(boolean desc) {
        orderBy(WorkOrderColumns.DEVICE_CODE, desc);
        return this;
    }

    public WorkOrderSelection orderByDeviceCode() {
        orderBy(WorkOrderColumns.DEVICE_CODE, false);
        return this;
    }

    public WorkOrderSelection deviceName(String... value) {
        addEquals(WorkOrderColumns.DEVICE_NAME, value);
        return this;
    }

    public WorkOrderSelection deviceNameNot(String... value) {
        addNotEquals(WorkOrderColumns.DEVICE_NAME, value);
        return this;
    }

    public WorkOrderSelection deviceNameLike(String... value) {
        addLike(WorkOrderColumns.DEVICE_NAME, value);
        return this;
    }

    public WorkOrderSelection deviceNameContains(String... value) {
        addContains(WorkOrderColumns.DEVICE_NAME, value);
        return this;
    }

    public WorkOrderSelection deviceNameStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.DEVICE_NAME, value);
        return this;
    }

    public WorkOrderSelection deviceNameEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.DEVICE_NAME, value);
        return this;
    }

    public WorkOrderSelection orderByDeviceName(boolean desc) {
        orderBy(WorkOrderColumns.DEVICE_NAME, desc);
        return this;
    }

    public WorkOrderSelection orderByDeviceName() {
        orderBy(WorkOrderColumns.DEVICE_NAME, false);
        return this;
    }

    public WorkOrderSelection faultDescriptionText(String... value) {
        addEquals(WorkOrderColumns.FAULT_DESCRIPTION_TEXT, value);
        return this;
    }

    public WorkOrderSelection faultDescriptionTextNot(String... value) {
        addNotEquals(WorkOrderColumns.FAULT_DESCRIPTION_TEXT, value);
        return this;
    }

    public WorkOrderSelection faultDescriptionTextLike(String... value) {
        addLike(WorkOrderColumns.FAULT_DESCRIPTION_TEXT, value);
        return this;
    }

    public WorkOrderSelection faultDescriptionTextContains(String... value) {
        addContains(WorkOrderColumns.FAULT_DESCRIPTION_TEXT, value);
        return this;
    }

    public WorkOrderSelection faultDescriptionTextStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.FAULT_DESCRIPTION_TEXT, value);
        return this;
    }

    public WorkOrderSelection faultDescriptionTextEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.FAULT_DESCRIPTION_TEXT, value);
        return this;
    }

    public WorkOrderSelection orderByFaultDescriptionText(boolean desc) {
        orderBy(WorkOrderColumns.FAULT_DESCRIPTION_TEXT, desc);
        return this;
    }

    public WorkOrderSelection orderByFaultDescriptionText() {
        orderBy(WorkOrderColumns.FAULT_DESCRIPTION_TEXT, false);
        return this;
    }

    public WorkOrderSelection faultDescriptionCode(String... value) {
        addEquals(WorkOrderColumns.FAULT_DESCRIPTION_CODE, value);
        return this;
    }

    public WorkOrderSelection faultDescriptionCodeNot(String... value) {
        addNotEquals(WorkOrderColumns.FAULT_DESCRIPTION_CODE, value);
        return this;
    }

    public WorkOrderSelection faultDescriptionCodeLike(String... value) {
        addLike(WorkOrderColumns.FAULT_DESCRIPTION_CODE, value);
        return this;
    }

    public WorkOrderSelection faultDescriptionCodeContains(String... value) {
        addContains(WorkOrderColumns.FAULT_DESCRIPTION_CODE, value);
        return this;
    }

    public WorkOrderSelection faultDescriptionCodeStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.FAULT_DESCRIPTION_CODE, value);
        return this;
    }

    public WorkOrderSelection faultDescriptionCodeEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.FAULT_DESCRIPTION_CODE, value);
        return this;
    }

    public WorkOrderSelection orderByFaultDescriptionCode(boolean desc) {
        orderBy(WorkOrderColumns.FAULT_DESCRIPTION_CODE, desc);
        return this;
    }

    public WorkOrderSelection orderByFaultDescriptionCode() {
        orderBy(WorkOrderColumns.FAULT_DESCRIPTION_CODE, false);
        return this;
    }

    public WorkOrderSelection faultTypeText(String... value) {
        addEquals(WorkOrderColumns.FAULT_TYPE_TEXT, value);
        return this;
    }

    public WorkOrderSelection faultTypeTextNot(String... value) {
        addNotEquals(WorkOrderColumns.FAULT_TYPE_TEXT, value);
        return this;
    }

    public WorkOrderSelection faultTypeTextLike(String... value) {
        addLike(WorkOrderColumns.FAULT_TYPE_TEXT, value);
        return this;
    }

    public WorkOrderSelection faultTypeTextContains(String... value) {
        addContains(WorkOrderColumns.FAULT_TYPE_TEXT, value);
        return this;
    }

    public WorkOrderSelection faultTypeTextStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.FAULT_TYPE_TEXT, value);
        return this;
    }

    public WorkOrderSelection faultTypeTextEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.FAULT_TYPE_TEXT, value);
        return this;
    }

    public WorkOrderSelection orderByFaultTypeText(boolean desc) {
        orderBy(WorkOrderColumns.FAULT_TYPE_TEXT, desc);
        return this;
    }

    public WorkOrderSelection orderByFaultTypeText() {
        orderBy(WorkOrderColumns.FAULT_TYPE_TEXT, false);
        return this;
    }

    public WorkOrderSelection faultTypeCode(String... value) {
        addEquals(WorkOrderColumns.FAULT_TYPE_CODE, value);
        return this;
    }

    public WorkOrderSelection faultTypeCodeNot(String... value) {
        addNotEquals(WorkOrderColumns.FAULT_TYPE_CODE, value);
        return this;
    }

    public WorkOrderSelection faultTypeCodeLike(String... value) {
        addLike(WorkOrderColumns.FAULT_TYPE_CODE, value);
        return this;
    }

    public WorkOrderSelection faultTypeCodeContains(String... value) {
        addContains(WorkOrderColumns.FAULT_TYPE_CODE, value);
        return this;
    }

    public WorkOrderSelection faultTypeCodeStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.FAULT_TYPE_CODE, value);
        return this;
    }

    public WorkOrderSelection faultTypeCodeEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.FAULT_TYPE_CODE, value);
        return this;
    }

    public WorkOrderSelection orderByFaultTypeCode(boolean desc) {
        orderBy(WorkOrderColumns.FAULT_TYPE_CODE, desc);
        return this;
    }

    public WorkOrderSelection orderByFaultTypeCode() {
        orderBy(WorkOrderColumns.FAULT_TYPE_CODE, false);
        return this;
    }

    public WorkOrderSelection reporterTypeText(String... value) {
        addEquals(WorkOrderColumns.REPORTER_TYPE_TEXT, value);
        return this;
    }

    public WorkOrderSelection reporterTypeTextNot(String... value) {
        addNotEquals(WorkOrderColumns.REPORTER_TYPE_TEXT, value);
        return this;
    }

    public WorkOrderSelection reporterTypeTextLike(String... value) {
        addLike(WorkOrderColumns.REPORTER_TYPE_TEXT, value);
        return this;
    }

    public WorkOrderSelection reporterTypeTextContains(String... value) {
        addContains(WorkOrderColumns.REPORTER_TYPE_TEXT, value);
        return this;
    }

    public WorkOrderSelection reporterTypeTextStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.REPORTER_TYPE_TEXT, value);
        return this;
    }

    public WorkOrderSelection reporterTypeTextEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.REPORTER_TYPE_TEXT, value);
        return this;
    }

    public WorkOrderSelection orderByReporterTypeText(boolean desc) {
        orderBy(WorkOrderColumns.REPORTER_TYPE_TEXT, desc);
        return this;
    }

    public WorkOrderSelection orderByReporterTypeText() {
        orderBy(WorkOrderColumns.REPORTER_TYPE_TEXT, false);
        return this;
    }

    public WorkOrderSelection reporterTypeCode(String... value) {
        addEquals(WorkOrderColumns.REPORTER_TYPE_CODE, value);
        return this;
    }

    public WorkOrderSelection reporterTypeCodeNot(String... value) {
        addNotEquals(WorkOrderColumns.REPORTER_TYPE_CODE, value);
        return this;
    }

    public WorkOrderSelection reporterTypeCodeLike(String... value) {
        addLike(WorkOrderColumns.REPORTER_TYPE_CODE, value);
        return this;
    }

    public WorkOrderSelection reporterTypeCodeContains(String... value) {
        addContains(WorkOrderColumns.REPORTER_TYPE_CODE, value);
        return this;
    }

    public WorkOrderSelection reporterTypeCodeStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.REPORTER_TYPE_CODE, value);
        return this;
    }

    public WorkOrderSelection reporterTypeCodeEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.REPORTER_TYPE_CODE, value);
        return this;
    }

    public WorkOrderSelection orderByReporterTypeCode(boolean desc) {
        orderBy(WorkOrderColumns.REPORTER_TYPE_CODE, desc);
        return this;
    }

    public WorkOrderSelection orderByReporterTypeCode() {
        orderBy(WorkOrderColumns.REPORTER_TYPE_CODE, false);
        return this;
    }

    public WorkOrderSelection reporter(String... value) {
        addEquals(WorkOrderColumns.REPORTER, value);
        return this;
    }

    public WorkOrderSelection reporterNot(String... value) {
        addNotEquals(WorkOrderColumns.REPORTER, value);
        return this;
    }

    public WorkOrderSelection reporterLike(String... value) {
        addLike(WorkOrderColumns.REPORTER, value);
        return this;
    }

    public WorkOrderSelection reporterContains(String... value) {
        addContains(WorkOrderColumns.REPORTER, value);
        return this;
    }

    public WorkOrderSelection reporterStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.REPORTER, value);
        return this;
    }

    public WorkOrderSelection reporterEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.REPORTER, value);
        return this;
    }

    public WorkOrderSelection orderByReporter(boolean desc) {
        orderBy(WorkOrderColumns.REPORTER, desc);
        return this;
    }

    public WorkOrderSelection orderByReporter() {
        orderBy(WorkOrderColumns.REPORTER, false);
        return this;
    }

    public WorkOrderSelection faultNote(String... value) {
        addEquals(WorkOrderColumns.FAULT_NOTE, value);
        return this;
    }

    public WorkOrderSelection faultNoteNot(String... value) {
        addNotEquals(WorkOrderColumns.FAULT_NOTE, value);
        return this;
    }

    public WorkOrderSelection faultNoteLike(String... value) {
        addLike(WorkOrderColumns.FAULT_NOTE, value);
        return this;
    }

    public WorkOrderSelection faultNoteContains(String... value) {
        addContains(WorkOrderColumns.FAULT_NOTE, value);
        return this;
    }

    public WorkOrderSelection faultNoteStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.FAULT_NOTE, value);
        return this;
    }

    public WorkOrderSelection faultNoteEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.FAULT_NOTE, value);
        return this;
    }

    public WorkOrderSelection orderByFaultNote(boolean desc) {
        orderBy(WorkOrderColumns.FAULT_NOTE, desc);
        return this;
    }

    public WorkOrderSelection orderByFaultNote() {
        orderBy(WorkOrderColumns.FAULT_NOTE, false);
        return this;
    }

    public WorkOrderSelection faultCauseText(String... value) {
        addEquals(WorkOrderColumns.FAULT_CAUSE_TEXT, value);
        return this;
    }

    public WorkOrderSelection faultCauseTextNot(String... value) {
        addNotEquals(WorkOrderColumns.FAULT_CAUSE_TEXT, value);
        return this;
    }

    public WorkOrderSelection faultCauseTextLike(String... value) {
        addLike(WorkOrderColumns.FAULT_CAUSE_TEXT, value);
        return this;
    }

    public WorkOrderSelection faultCauseTextContains(String... value) {
        addContains(WorkOrderColumns.FAULT_CAUSE_TEXT, value);
        return this;
    }

    public WorkOrderSelection faultCauseTextStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.FAULT_CAUSE_TEXT, value);
        return this;
    }

    public WorkOrderSelection faultCauseTextEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.FAULT_CAUSE_TEXT, value);
        return this;
    }

    public WorkOrderSelection orderByFaultCauseText(boolean desc) {
        orderBy(WorkOrderColumns.FAULT_CAUSE_TEXT, desc);
        return this;
    }

    public WorkOrderSelection orderByFaultCauseText() {
        orderBy(WorkOrderColumns.FAULT_CAUSE_TEXT, false);
        return this;
    }

    public WorkOrderSelection faultCauseCode(String... value) {
        addEquals(WorkOrderColumns.FAULT_CAUSE_CODE, value);
        return this;
    }

    public WorkOrderSelection faultCauseCodeNot(String... value) {
        addNotEquals(WorkOrderColumns.FAULT_CAUSE_CODE, value);
        return this;
    }

    public WorkOrderSelection faultCauseCodeLike(String... value) {
        addLike(WorkOrderColumns.FAULT_CAUSE_CODE, value);
        return this;
    }

    public WorkOrderSelection faultCauseCodeContains(String... value) {
        addContains(WorkOrderColumns.FAULT_CAUSE_CODE, value);
        return this;
    }

    public WorkOrderSelection faultCauseCodeStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.FAULT_CAUSE_CODE, value);
        return this;
    }

    public WorkOrderSelection faultCauseCodeEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.FAULT_CAUSE_CODE, value);
        return this;
    }

    public WorkOrderSelection orderByFaultCauseCode(boolean desc) {
        orderBy(WorkOrderColumns.FAULT_CAUSE_CODE, desc);
        return this;
    }

    public WorkOrderSelection orderByFaultCauseCode() {
        orderBy(WorkOrderColumns.FAULT_CAUSE_CODE, false);
        return this;
    }

    public WorkOrderSelection faultStartTime(Long... value) {
        addEquals(WorkOrderColumns.FAULT_START_TIME, value);
        return this;
    }

    public WorkOrderSelection faultStartTimeNot(Long... value) {
        addNotEquals(WorkOrderColumns.FAULT_START_TIME, value);
        return this;
    }

    public WorkOrderSelection faultStartTimeGt(long value) {
        addGreaterThan(WorkOrderColumns.FAULT_START_TIME, value);
        return this;
    }

    public WorkOrderSelection faultStartTimeGtEq(long value) {
        addGreaterThanOrEquals(WorkOrderColumns.FAULT_START_TIME, value);
        return this;
    }

    public WorkOrderSelection faultStartTimeLt(long value) {
        addLessThan(WorkOrderColumns.FAULT_START_TIME, value);
        return this;
    }

    public WorkOrderSelection faultStartTimeLtEq(long value) {
        addLessThanOrEquals(WorkOrderColumns.FAULT_START_TIME, value);
        return this;
    }

    public WorkOrderSelection orderByFaultStartTime(boolean desc) {
        orderBy(WorkOrderColumns.FAULT_START_TIME, desc);
        return this;
    }

    public WorkOrderSelection orderByFaultStartTime() {
        orderBy(WorkOrderColumns.FAULT_START_TIME, false);
        return this;
    }

    public WorkOrderSelection operationText(String... value) {
        addEquals(WorkOrderColumns.OPERATION_TEXT, value);
        return this;
    }

    public WorkOrderSelection operationTextNot(String... value) {
        addNotEquals(WorkOrderColumns.OPERATION_TEXT, value);
        return this;
    }

    public WorkOrderSelection operationTextLike(String... value) {
        addLike(WorkOrderColumns.OPERATION_TEXT, value);
        return this;
    }

    public WorkOrderSelection operationTextContains(String... value) {
        addContains(WorkOrderColumns.OPERATION_TEXT, value);
        return this;
    }

    public WorkOrderSelection operationTextStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.OPERATION_TEXT, value);
        return this;
    }

    public WorkOrderSelection operationTextEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.OPERATION_TEXT, value);
        return this;
    }

    public WorkOrderSelection orderByOperationText(boolean desc) {
        orderBy(WorkOrderColumns.OPERATION_TEXT, desc);
        return this;
    }

    public WorkOrderSelection orderByOperationText() {
        orderBy(WorkOrderColumns.OPERATION_TEXT, false);
        return this;
    }

    public WorkOrderSelection operationCode(String... value) {
        addEquals(WorkOrderColumns.OPERATION_CODE, value);
        return this;
    }

    public WorkOrderSelection operationCodeNot(String... value) {
        addNotEquals(WorkOrderColumns.OPERATION_CODE, value);
        return this;
    }

    public WorkOrderSelection operationCodeLike(String... value) {
        addLike(WorkOrderColumns.OPERATION_CODE, value);
        return this;
    }

    public WorkOrderSelection operationCodeContains(String... value) {
        addContains(WorkOrderColumns.OPERATION_CODE, value);
        return this;
    }

    public WorkOrderSelection operationCodeStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.OPERATION_CODE, value);
        return this;
    }

    public WorkOrderSelection operationCodeEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.OPERATION_CODE, value);
        return this;
    }

    public WorkOrderSelection orderByOperationCode(boolean desc) {
        orderBy(WorkOrderColumns.OPERATION_CODE, desc);
        return this;
    }

    public WorkOrderSelection orderByOperationCode() {
        orderBy(WorkOrderColumns.OPERATION_CODE, false);
        return this;
    }

    public WorkOrderSelection operationResultText(String... value) {
        addEquals(WorkOrderColumns.OPERATION_RESULT_TEXT, value);
        return this;
    }

    public WorkOrderSelection operationResultTextNot(String... value) {
        addNotEquals(WorkOrderColumns.OPERATION_RESULT_TEXT, value);
        return this;
    }

    public WorkOrderSelection operationResultTextLike(String... value) {
        addLike(WorkOrderColumns.OPERATION_RESULT_TEXT, value);
        return this;
    }

    public WorkOrderSelection operationResultTextContains(String... value) {
        addContains(WorkOrderColumns.OPERATION_RESULT_TEXT, value);
        return this;
    }

    public WorkOrderSelection operationResultTextStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.OPERATION_RESULT_TEXT, value);
        return this;
    }

    public WorkOrderSelection operationResultTextEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.OPERATION_RESULT_TEXT, value);
        return this;
    }

    public WorkOrderSelection orderByOperationResultText(boolean desc) {
        orderBy(WorkOrderColumns.OPERATION_RESULT_TEXT, desc);
        return this;
    }

    public WorkOrderSelection orderByOperationResultText() {
        orderBy(WorkOrderColumns.OPERATION_RESULT_TEXT, false);
        return this;
    }

    public WorkOrderSelection operationResultCode(String... value) {
        addEquals(WorkOrderColumns.OPERATION_RESULT_CODE, value);
        return this;
    }

    public WorkOrderSelection operationResultCodeNot(String... value) {
        addNotEquals(WorkOrderColumns.OPERATION_RESULT_CODE, value);
        return this;
    }

    public WorkOrderSelection operationResultCodeLike(String... value) {
        addLike(WorkOrderColumns.OPERATION_RESULT_CODE, value);
        return this;
    }

    public WorkOrderSelection operationResultCodeContains(String... value) {
        addContains(WorkOrderColumns.OPERATION_RESULT_CODE, value);
        return this;
    }

    public WorkOrderSelection operationResultCodeStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.OPERATION_RESULT_CODE, value);
        return this;
    }

    public WorkOrderSelection operationResultCodeEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.OPERATION_RESULT_CODE, value);
        return this;
    }

    public WorkOrderSelection orderByOperationResultCode(boolean desc) {
        orderBy(WorkOrderColumns.OPERATION_RESULT_CODE, desc);
        return this;
    }

    public WorkOrderSelection orderByOperationResultCode() {
        orderBy(WorkOrderColumns.OPERATION_RESULT_CODE, false);
        return this;
    }

    public WorkOrderSelection operationNote(String... value) {
        addEquals(WorkOrderColumns.OPERATION_NOTE, value);
        return this;
    }

    public WorkOrderSelection operationNoteNot(String... value) {
        addNotEquals(WorkOrderColumns.OPERATION_NOTE, value);
        return this;
    }

    public WorkOrderSelection operationNoteLike(String... value) {
        addLike(WorkOrderColumns.OPERATION_NOTE, value);
        return this;
    }

    public WorkOrderSelection operationNoteContains(String... value) {
        addContains(WorkOrderColumns.OPERATION_NOTE, value);
        return this;
    }

    public WorkOrderSelection operationNoteStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.OPERATION_NOTE, value);
        return this;
    }

    public WorkOrderSelection operationNoteEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.OPERATION_NOTE, value);
        return this;
    }

    public WorkOrderSelection orderByOperationNote(boolean desc) {
        orderBy(WorkOrderColumns.OPERATION_NOTE, desc);
        return this;
    }

    public WorkOrderSelection orderByOperationNote() {
        orderBy(WorkOrderColumns.OPERATION_NOTE, false);
        return this;
    }

    public WorkOrderSelection operatorText(String... value) {
        addEquals(WorkOrderColumns.OPERATOR_TEXT, value);
        return this;
    }

    public WorkOrderSelection operatorTextNot(String... value) {
        addNotEquals(WorkOrderColumns.OPERATOR_TEXT, value);
        return this;
    }

    public WorkOrderSelection operatorTextLike(String... value) {
        addLike(WorkOrderColumns.OPERATOR_TEXT, value);
        return this;
    }

    public WorkOrderSelection operatorTextContains(String... value) {
        addContains(WorkOrderColumns.OPERATOR_TEXT, value);
        return this;
    }

    public WorkOrderSelection operatorTextStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.OPERATOR_TEXT, value);
        return this;
    }

    public WorkOrderSelection operatorTextEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.OPERATOR_TEXT, value);
        return this;
    }

    public WorkOrderSelection orderByOperatorText(boolean desc) {
        orderBy(WorkOrderColumns.OPERATOR_TEXT, desc);
        return this;
    }

    public WorkOrderSelection orderByOperatorText() {
        orderBy(WorkOrderColumns.OPERATOR_TEXT, false);
        return this;
    }

    public WorkOrderSelection operatorCode(String... value) {
        addEquals(WorkOrderColumns.OPERATOR_CODE, value);
        return this;
    }

    public WorkOrderSelection operatorCodeNot(String... value) {
        addNotEquals(WorkOrderColumns.OPERATOR_CODE, value);
        return this;
    }

    public WorkOrderSelection operatorCodeLike(String... value) {
        addLike(WorkOrderColumns.OPERATOR_CODE, value);
        return this;
    }

    public WorkOrderSelection operatorCodeContains(String... value) {
        addContains(WorkOrderColumns.OPERATOR_CODE, value);
        return this;
    }

    public WorkOrderSelection operatorCodeStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.OPERATOR_CODE, value);
        return this;
    }

    public WorkOrderSelection operatorCodeEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.OPERATOR_CODE, value);
        return this;
    }

    public WorkOrderSelection orderByOperatorCode(boolean desc) {
        orderBy(WorkOrderColumns.OPERATOR_CODE, desc);
        return this;
    }

    public WorkOrderSelection orderByOperatorCode() {
        orderBy(WorkOrderColumns.OPERATOR_CODE, false);
        return this;
    }

    public WorkOrderSelection operationStartTime(Long... value) {
        addEquals(WorkOrderColumns.OPERATION_START_TIME, value);
        return this;
    }

    public WorkOrderSelection operationStartTimeNot(Long... value) {
        addNotEquals(WorkOrderColumns.OPERATION_START_TIME, value);
        return this;
    }

    public WorkOrderSelection operationStartTimeGt(long value) {
        addGreaterThan(WorkOrderColumns.OPERATION_START_TIME, value);
        return this;
    }

    public WorkOrderSelection operationStartTimeGtEq(long value) {
        addGreaterThanOrEquals(WorkOrderColumns.OPERATION_START_TIME, value);
        return this;
    }

    public WorkOrderSelection operationStartTimeLt(long value) {
        addLessThan(WorkOrderColumns.OPERATION_START_TIME, value);
        return this;
    }

    public WorkOrderSelection operationStartTimeLtEq(long value) {
        addLessThanOrEquals(WorkOrderColumns.OPERATION_START_TIME, value);
        return this;
    }

    public WorkOrderSelection orderByOperationStartTime(boolean desc) {
        orderBy(WorkOrderColumns.OPERATION_START_TIME, desc);
        return this;
    }

    public WorkOrderSelection orderByOperationStartTime() {
        orderBy(WorkOrderColumns.OPERATION_START_TIME, false);
        return this;
    }

    public WorkOrderSelection operationEndTime(Long... value) {
        addEquals(WorkOrderColumns.OPERATION_END_TIME, value);
        return this;
    }

    public WorkOrderSelection operationEndTimeNot(Long... value) {
        addNotEquals(WorkOrderColumns.OPERATION_END_TIME, value);
        return this;
    }

    public WorkOrderSelection operationEndTimeGt(long value) {
        addGreaterThan(WorkOrderColumns.OPERATION_END_TIME, value);
        return this;
    }

    public WorkOrderSelection operationEndTimeGtEq(long value) {
        addGreaterThanOrEquals(WorkOrderColumns.OPERATION_END_TIME, value);
        return this;
    }

    public WorkOrderSelection operationEndTimeLt(long value) {
        addLessThan(WorkOrderColumns.OPERATION_END_TIME, value);
        return this;
    }

    public WorkOrderSelection operationEndTimeLtEq(long value) {
        addLessThanOrEquals(WorkOrderColumns.OPERATION_END_TIME, value);
        return this;
    }

    public WorkOrderSelection orderByOperationEndTime(boolean desc) {
        orderBy(WorkOrderColumns.OPERATION_END_TIME, desc);
        return this;
    }

    public WorkOrderSelection orderByOperationEndTime() {
        orderBy(WorkOrderColumns.OPERATION_END_TIME, false);
        return this;
    }

    public WorkOrderSelection formFlagText(String... value) {
        addEquals(WorkOrderColumns.FORM_FLAG_TEXT, value);
        return this;
    }

    public WorkOrderSelection formFlagTextNot(String... value) {
        addNotEquals(WorkOrderColumns.FORM_FLAG_TEXT, value);
        return this;
    }

    public WorkOrderSelection formFlagTextLike(String... value) {
        addLike(WorkOrderColumns.FORM_FLAG_TEXT, value);
        return this;
    }

    public WorkOrderSelection formFlagTextContains(String... value) {
        addContains(WorkOrderColumns.FORM_FLAG_TEXT, value);
        return this;
    }

    public WorkOrderSelection formFlagTextStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.FORM_FLAG_TEXT, value);
        return this;
    }

    public WorkOrderSelection formFlagTextEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.FORM_FLAG_TEXT, value);
        return this;
    }

    public WorkOrderSelection orderByFormFlagText(boolean desc) {
        orderBy(WorkOrderColumns.FORM_FLAG_TEXT, desc);
        return this;
    }

    public WorkOrderSelection orderByFormFlagText() {
        orderBy(WorkOrderColumns.FORM_FLAG_TEXT, false);
        return this;
    }

    public WorkOrderSelection formFlagCode(String... value) {
        addEquals(WorkOrderColumns.FORM_FLAG_CODE, value);
        return this;
    }

    public WorkOrderSelection formFlagCodeNot(String... value) {
        addNotEquals(WorkOrderColumns.FORM_FLAG_CODE, value);
        return this;
    }

    public WorkOrderSelection formFlagCodeLike(String... value) {
        addLike(WorkOrderColumns.FORM_FLAG_CODE, value);
        return this;
    }

    public WorkOrderSelection formFlagCodeContains(String... value) {
        addContains(WorkOrderColumns.FORM_FLAG_CODE, value);
        return this;
    }

    public WorkOrderSelection formFlagCodeStartsWith(String... value) {
        addStartsWith(WorkOrderColumns.FORM_FLAG_CODE, value);
        return this;
    }

    public WorkOrderSelection formFlagCodeEndsWith(String... value) {
        addEndsWith(WorkOrderColumns.FORM_FLAG_CODE, value);
        return this;
    }

    public WorkOrderSelection orderByFormFlagCode(boolean desc) {
        orderBy(WorkOrderColumns.FORM_FLAG_CODE, desc);
        return this;
    }

    public WorkOrderSelection orderByFormFlagCode() {
        orderBy(WorkOrderColumns.FORM_FLAG_CODE, false);
        return this;
    }

    public WorkOrderSelection syncStatus(SyncStatus... value) {
        addEquals(WorkOrderColumns.SYNC_STATUS, value);
        return this;
    }

    public WorkOrderSelection syncStatusNot(SyncStatus... value) {
        addNotEquals(WorkOrderColumns.SYNC_STATUS, value);
        return this;
    }


    public WorkOrderSelection orderBySyncStatus(boolean desc) {
        orderBy(WorkOrderColumns.SYNC_STATUS, desc);
        return this;
    }

    public WorkOrderSelection orderBySyncStatus() {
        orderBy(WorkOrderColumns.SYNC_STATUS, false);
        return this;
    }

    public WorkOrderSelection lastModified(Long... value) {
        addEquals(WorkOrderColumns.LAST_MODIFIED, value);
        return this;
    }

    public WorkOrderSelection lastModifiedNot(Long... value) {
        addNotEquals(WorkOrderColumns.LAST_MODIFIED, value);
        return this;
    }

    public WorkOrderSelection lastModifiedGt(long value) {
        addGreaterThan(WorkOrderColumns.LAST_MODIFIED, value);
        return this;
    }

    public WorkOrderSelection lastModifiedGtEq(long value) {
        addGreaterThanOrEquals(WorkOrderColumns.LAST_MODIFIED, value);
        return this;
    }

    public WorkOrderSelection lastModifiedLt(long value) {
        addLessThan(WorkOrderColumns.LAST_MODIFIED, value);
        return this;
    }

    public WorkOrderSelection lastModifiedLtEq(long value) {
        addLessThanOrEquals(WorkOrderColumns.LAST_MODIFIED, value);
        return this;
    }

    public WorkOrderSelection orderByLastModified(boolean desc) {
        orderBy(WorkOrderColumns.LAST_MODIFIED, desc);
        return this;
    }

    public WorkOrderSelection orderByLastModified() {
        orderBy(WorkOrderColumns.LAST_MODIFIED, false);
        return this;
    }

    public WorkOrderSelection deletePending(Boolean value) {
        addEquals(WorkOrderColumns.DELETE_PENDING, toObjectArray(value));
        return this;
    }

    public WorkOrderSelection orderByDeletePending(boolean desc) {
        orderBy(WorkOrderColumns.DELETE_PENDING, desc);
        return this;
    }

    public WorkOrderSelection orderByDeletePending() {
        orderBy(WorkOrderColumns.DELETE_PENDING, false);
        return this;
    }

    public WorkOrderSelection uploadPending(Boolean value) {
        addEquals(WorkOrderColumns.UPLOAD_PENDING, toObjectArray(value));
        return this;
    }

    public WorkOrderSelection orderByUploadPending(boolean desc) {
        orderBy(WorkOrderColumns.UPLOAD_PENDING, desc);
        return this;
    }

    public WorkOrderSelection orderByUploadPending() {
        orderBy(WorkOrderColumns.UPLOAD_PENDING, false);
        return this;
    }
}
