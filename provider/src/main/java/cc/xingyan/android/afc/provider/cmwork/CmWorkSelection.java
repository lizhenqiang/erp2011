package cc.xingyan.android.afc.provider.cmwork;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code cm_work} table.
 */
public class CmWorkSelection extends AbstractSelection<CmWorkSelection> {
    @Override
    protected Uri baseUri() {
        return CmWorkColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CmWorkCursor} object, which is positioned before the first entry, or null.
     */
    public CmWorkCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CmWorkCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public CmWorkCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CmWorkCursor} object, which is positioned before the first entry, or null.
     */
    public CmWorkCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CmWorkCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public CmWorkCursor query(Context context) {
        return query(context, null);
    }


    public CmWorkSelection id(long... value) {
        addEquals("cm_work." + CmWorkColumns._ID, toObjectArray(value));
        return this;
    }

    public CmWorkSelection idNot(long... value) {
        addNotEquals("cm_work." + CmWorkColumns._ID, toObjectArray(value));
        return this;
    }

    public CmWorkSelection orderById(boolean desc) {
        orderBy("cm_work." + CmWorkColumns._ID, desc);
        return this;
    }

    public CmWorkSelection orderById() {
        return orderById(false);
    }

    public CmWorkSelection userId(String... value) {
        addEquals(CmWorkColumns.USER_ID, value);
        return this;
    }

    public CmWorkSelection userIdNot(String... value) {
        addNotEquals(CmWorkColumns.USER_ID, value);
        return this;
    }

    public CmWorkSelection userIdLike(String... value) {
        addLike(CmWorkColumns.USER_ID, value);
        return this;
    }

    public CmWorkSelection userIdContains(String... value) {
        addContains(CmWorkColumns.USER_ID, value);
        return this;
    }

    public CmWorkSelection userIdStartsWith(String... value) {
        addStartsWith(CmWorkColumns.USER_ID, value);
        return this;
    }

    public CmWorkSelection userIdEndsWith(String... value) {
        addEndsWith(CmWorkColumns.USER_ID, value);
        return this;
    }

    public CmWorkSelection orderByUserId(boolean desc) {
        orderBy(CmWorkColumns.USER_ID, desc);
        return this;
    }

    public CmWorkSelection orderByUserId() {
        orderBy(CmWorkColumns.USER_ID, false);
        return this;
    }

    public CmWorkSelection guid(String... value) {
        addEquals(CmWorkColumns.GUID, value);
        return this;
    }

    public CmWorkSelection guidNot(String... value) {
        addNotEquals(CmWorkColumns.GUID, value);
        return this;
    }

    public CmWorkSelection guidLike(String... value) {
        addLike(CmWorkColumns.GUID, value);
        return this;
    }

    public CmWorkSelection guidContains(String... value) {
        addContains(CmWorkColumns.GUID, value);
        return this;
    }

    public CmWorkSelection guidStartsWith(String... value) {
        addStartsWith(CmWorkColumns.GUID, value);
        return this;
    }

    public CmWorkSelection guidEndsWith(String... value) {
        addEndsWith(CmWorkColumns.GUID, value);
        return this;
    }

    public CmWorkSelection orderByGuid(boolean desc) {
        orderBy(CmWorkColumns.GUID, desc);
        return this;
    }

    public CmWorkSelection orderByGuid() {
        orderBy(CmWorkColumns.GUID, false);
        return this;
    }

    public CmWorkSelection orderId(String... value) {
        addEquals(CmWorkColumns.ORDER_ID, value);
        return this;
    }

    public CmWorkSelection orderIdNot(String... value) {
        addNotEquals(CmWorkColumns.ORDER_ID, value);
        return this;
    }

    public CmWorkSelection orderIdLike(String... value) {
        addLike(CmWorkColumns.ORDER_ID, value);
        return this;
    }

    public CmWorkSelection orderIdContains(String... value) {
        addContains(CmWorkColumns.ORDER_ID, value);
        return this;
    }

    public CmWorkSelection orderIdStartsWith(String... value) {
        addStartsWith(CmWorkColumns.ORDER_ID, value);
        return this;
    }

    public CmWorkSelection orderIdEndsWith(String... value) {
        addEndsWith(CmWorkColumns.ORDER_ID, value);
        return this;
    }

    public CmWorkSelection orderByOrderId(boolean desc) {
        orderBy(CmWorkColumns.ORDER_ID, desc);
        return this;
    }

    public CmWorkSelection orderByOrderId() {
        orderBy(CmWorkColumns.ORDER_ID, false);
        return this;
    }

    public CmWorkSelection priority(String... value) {
        addEquals(CmWorkColumns.PRIORITY, value);
        return this;
    }

    public CmWorkSelection priorityNot(String... value) {
        addNotEquals(CmWorkColumns.PRIORITY, value);
        return this;
    }

    public CmWorkSelection priorityLike(String... value) {
        addLike(CmWorkColumns.PRIORITY, value);
        return this;
    }

    public CmWorkSelection priorityContains(String... value) {
        addContains(CmWorkColumns.PRIORITY, value);
        return this;
    }

    public CmWorkSelection priorityStartsWith(String... value) {
        addStartsWith(CmWorkColumns.PRIORITY, value);
        return this;
    }

    public CmWorkSelection priorityEndsWith(String... value) {
        addEndsWith(CmWorkColumns.PRIORITY, value);
        return this;
    }

    public CmWorkSelection orderByPriority(boolean desc) {
        orderBy(CmWorkColumns.PRIORITY, desc);
        return this;
    }

    public CmWorkSelection orderByPriority() {
        orderBy(CmWorkColumns.PRIORITY, false);
        return this;
    }

    public CmWorkSelection deviceCode(String... value) {
        addEquals(CmWorkColumns.DEVICE_CODE, value);
        return this;
    }

    public CmWorkSelection deviceCodeNot(String... value) {
        addNotEquals(CmWorkColumns.DEVICE_CODE, value);
        return this;
    }

    public CmWorkSelection deviceCodeLike(String... value) {
        addLike(CmWorkColumns.DEVICE_CODE, value);
        return this;
    }

    public CmWorkSelection deviceCodeContains(String... value) {
        addContains(CmWorkColumns.DEVICE_CODE, value);
        return this;
    }

    public CmWorkSelection deviceCodeStartsWith(String... value) {
        addStartsWith(CmWorkColumns.DEVICE_CODE, value);
        return this;
    }

    public CmWorkSelection deviceCodeEndsWith(String... value) {
        addEndsWith(CmWorkColumns.DEVICE_CODE, value);
        return this;
    }

    public CmWorkSelection orderByDeviceCode(boolean desc) {
        orderBy(CmWorkColumns.DEVICE_CODE, desc);
        return this;
    }

    public CmWorkSelection orderByDeviceCode() {
        orderBy(CmWorkColumns.DEVICE_CODE, false);
        return this;
    }

    public CmWorkSelection deviceName(String... value) {
        addEquals(CmWorkColumns.DEVICE_NAME, value);
        return this;
    }

    public CmWorkSelection deviceNameNot(String... value) {
        addNotEquals(CmWorkColumns.DEVICE_NAME, value);
        return this;
    }

    public CmWorkSelection deviceNameLike(String... value) {
        addLike(CmWorkColumns.DEVICE_NAME, value);
        return this;
    }

    public CmWorkSelection deviceNameContains(String... value) {
        addContains(CmWorkColumns.DEVICE_NAME, value);
        return this;
    }

    public CmWorkSelection deviceNameStartsWith(String... value) {
        addStartsWith(CmWorkColumns.DEVICE_NAME, value);
        return this;
    }

    public CmWorkSelection deviceNameEndsWith(String... value) {
        addEndsWith(CmWorkColumns.DEVICE_NAME, value);
        return this;
    }

    public CmWorkSelection orderByDeviceName(boolean desc) {
        orderBy(CmWorkColumns.DEVICE_NAME, desc);
        return this;
    }

    public CmWorkSelection orderByDeviceName() {
        orderBy(CmWorkColumns.DEVICE_NAME, false);
        return this;
    }

    public CmWorkSelection faultDescriptionText(String... value) {
        addEquals(CmWorkColumns.FAULT_DESCRIPTION_TEXT, value);
        return this;
    }

    public CmWorkSelection faultDescriptionTextNot(String... value) {
        addNotEquals(CmWorkColumns.FAULT_DESCRIPTION_TEXT, value);
        return this;
    }

    public CmWorkSelection faultDescriptionTextLike(String... value) {
        addLike(CmWorkColumns.FAULT_DESCRIPTION_TEXT, value);
        return this;
    }

    public CmWorkSelection faultDescriptionTextContains(String... value) {
        addContains(CmWorkColumns.FAULT_DESCRIPTION_TEXT, value);
        return this;
    }

    public CmWorkSelection faultDescriptionTextStartsWith(String... value) {
        addStartsWith(CmWorkColumns.FAULT_DESCRIPTION_TEXT, value);
        return this;
    }

    public CmWorkSelection faultDescriptionTextEndsWith(String... value) {
        addEndsWith(CmWorkColumns.FAULT_DESCRIPTION_TEXT, value);
        return this;
    }

    public CmWorkSelection orderByFaultDescriptionText(boolean desc) {
        orderBy(CmWorkColumns.FAULT_DESCRIPTION_TEXT, desc);
        return this;
    }

    public CmWorkSelection orderByFaultDescriptionText() {
        orderBy(CmWorkColumns.FAULT_DESCRIPTION_TEXT, false);
        return this;
    }

    public CmWorkSelection faultDescriptionCode(String... value) {
        addEquals(CmWorkColumns.FAULT_DESCRIPTION_CODE, value);
        return this;
    }

    public CmWorkSelection faultDescriptionCodeNot(String... value) {
        addNotEquals(CmWorkColumns.FAULT_DESCRIPTION_CODE, value);
        return this;
    }

    public CmWorkSelection faultDescriptionCodeLike(String... value) {
        addLike(CmWorkColumns.FAULT_DESCRIPTION_CODE, value);
        return this;
    }

    public CmWorkSelection faultDescriptionCodeContains(String... value) {
        addContains(CmWorkColumns.FAULT_DESCRIPTION_CODE, value);
        return this;
    }

    public CmWorkSelection faultDescriptionCodeStartsWith(String... value) {
        addStartsWith(CmWorkColumns.FAULT_DESCRIPTION_CODE, value);
        return this;
    }

    public CmWorkSelection faultDescriptionCodeEndsWith(String... value) {
        addEndsWith(CmWorkColumns.FAULT_DESCRIPTION_CODE, value);
        return this;
    }

    public CmWorkSelection orderByFaultDescriptionCode(boolean desc) {
        orderBy(CmWorkColumns.FAULT_DESCRIPTION_CODE, desc);
        return this;
    }

    public CmWorkSelection orderByFaultDescriptionCode() {
        orderBy(CmWorkColumns.FAULT_DESCRIPTION_CODE, false);
        return this;
    }

    public CmWorkSelection faultNote(String... value) {
        addEquals(CmWorkColumns.FAULT_NOTE, value);
        return this;
    }

    public CmWorkSelection faultNoteNot(String... value) {
        addNotEquals(CmWorkColumns.FAULT_NOTE, value);
        return this;
    }

    public CmWorkSelection faultNoteLike(String... value) {
        addLike(CmWorkColumns.FAULT_NOTE, value);
        return this;
    }

    public CmWorkSelection faultNoteContains(String... value) {
        addContains(CmWorkColumns.FAULT_NOTE, value);
        return this;
    }

    public CmWorkSelection faultNoteStartsWith(String... value) {
        addStartsWith(CmWorkColumns.FAULT_NOTE, value);
        return this;
    }

    public CmWorkSelection faultNoteEndsWith(String... value) {
        addEndsWith(CmWorkColumns.FAULT_NOTE, value);
        return this;
    }

    public CmWorkSelection orderByFaultNote(boolean desc) {
        orderBy(CmWorkColumns.FAULT_NOTE, desc);
        return this;
    }

    public CmWorkSelection orderByFaultNote() {
        orderBy(CmWorkColumns.FAULT_NOTE, false);
        return this;
    }

    public CmWorkSelection reporterTypeText(String... value) {
        addEquals(CmWorkColumns.REPORTER_TYPE_TEXT, value);
        return this;
    }

    public CmWorkSelection reporterTypeTextNot(String... value) {
        addNotEquals(CmWorkColumns.REPORTER_TYPE_TEXT, value);
        return this;
    }

    public CmWorkSelection reporterTypeTextLike(String... value) {
        addLike(CmWorkColumns.REPORTER_TYPE_TEXT, value);
        return this;
    }

    public CmWorkSelection reporterTypeTextContains(String... value) {
        addContains(CmWorkColumns.REPORTER_TYPE_TEXT, value);
        return this;
    }

    public CmWorkSelection reporterTypeTextStartsWith(String... value) {
        addStartsWith(CmWorkColumns.REPORTER_TYPE_TEXT, value);
        return this;
    }

    public CmWorkSelection reporterTypeTextEndsWith(String... value) {
        addEndsWith(CmWorkColumns.REPORTER_TYPE_TEXT, value);
        return this;
    }

    public CmWorkSelection orderByReporterTypeText(boolean desc) {
        orderBy(CmWorkColumns.REPORTER_TYPE_TEXT, desc);
        return this;
    }

    public CmWorkSelection orderByReporterTypeText() {
        orderBy(CmWorkColumns.REPORTER_TYPE_TEXT, false);
        return this;
    }

    public CmWorkSelection reporterTypeCode(String... value) {
        addEquals(CmWorkColumns.REPORTER_TYPE_CODE, value);
        return this;
    }

    public CmWorkSelection reporterTypeCodeNot(String... value) {
        addNotEquals(CmWorkColumns.REPORTER_TYPE_CODE, value);
        return this;
    }

    public CmWorkSelection reporterTypeCodeLike(String... value) {
        addLike(CmWorkColumns.REPORTER_TYPE_CODE, value);
        return this;
    }

    public CmWorkSelection reporterTypeCodeContains(String... value) {
        addContains(CmWorkColumns.REPORTER_TYPE_CODE, value);
        return this;
    }

    public CmWorkSelection reporterTypeCodeStartsWith(String... value) {
        addStartsWith(CmWorkColumns.REPORTER_TYPE_CODE, value);
        return this;
    }

    public CmWorkSelection reporterTypeCodeEndsWith(String... value) {
        addEndsWith(CmWorkColumns.REPORTER_TYPE_CODE, value);
        return this;
    }

    public CmWorkSelection orderByReporterTypeCode(boolean desc) {
        orderBy(CmWorkColumns.REPORTER_TYPE_CODE, desc);
        return this;
    }

    public CmWorkSelection orderByReporterTypeCode() {
        orderBy(CmWorkColumns.REPORTER_TYPE_CODE, false);
        return this;
    }

    public CmWorkSelection instruct(String... value) {
        addEquals(CmWorkColumns.INSTRUCT, value);
        return this;
    }

    public CmWorkSelection instructNot(String... value) {
        addNotEquals(CmWorkColumns.INSTRUCT, value);
        return this;
    }

    public CmWorkSelection instructLike(String... value) {
        addLike(CmWorkColumns.INSTRUCT, value);
        return this;
    }

    public CmWorkSelection instructContains(String... value) {
        addContains(CmWorkColumns.INSTRUCT, value);
        return this;
    }

    public CmWorkSelection instructStartsWith(String... value) {
        addStartsWith(CmWorkColumns.INSTRUCT, value);
        return this;
    }

    public CmWorkSelection instructEndsWith(String... value) {
        addEndsWith(CmWorkColumns.INSTRUCT, value);
        return this;
    }

    public CmWorkSelection orderByInstruct(boolean desc) {
        orderBy(CmWorkColumns.INSTRUCT, desc);
        return this;
    }

    public CmWorkSelection orderByInstruct() {
        orderBy(CmWorkColumns.INSTRUCT, false);
        return this;
    }

    public CmWorkSelection reporter(String... value) {
        addEquals(CmWorkColumns.REPORTER, value);
        return this;
    }

    public CmWorkSelection reporterNot(String... value) {
        addNotEquals(CmWorkColumns.REPORTER, value);
        return this;
    }

    public CmWorkSelection reporterLike(String... value) {
        addLike(CmWorkColumns.REPORTER, value);
        return this;
    }

    public CmWorkSelection reporterContains(String... value) {
        addContains(CmWorkColumns.REPORTER, value);
        return this;
    }

    public CmWorkSelection reporterStartsWith(String... value) {
        addStartsWith(CmWorkColumns.REPORTER, value);
        return this;
    }

    public CmWorkSelection reporterEndsWith(String... value) {
        addEndsWith(CmWorkColumns.REPORTER, value);
        return this;
    }

    public CmWorkSelection orderByReporter(boolean desc) {
        orderBy(CmWorkColumns.REPORTER, desc);
        return this;
    }

    public CmWorkSelection orderByReporter() {
        orderBy(CmWorkColumns.REPORTER, false);
        return this;
    }

    public CmWorkSelection prepareMan(String... value) {
        addEquals(CmWorkColumns.PREPARE_MAN, value);
        return this;
    }

    public CmWorkSelection prepareManNot(String... value) {
        addNotEquals(CmWorkColumns.PREPARE_MAN, value);
        return this;
    }

    public CmWorkSelection prepareManLike(String... value) {
        addLike(CmWorkColumns.PREPARE_MAN, value);
        return this;
    }

    public CmWorkSelection prepareManContains(String... value) {
        addContains(CmWorkColumns.PREPARE_MAN, value);
        return this;
    }

    public CmWorkSelection prepareManStartsWith(String... value) {
        addStartsWith(CmWorkColumns.PREPARE_MAN, value);
        return this;
    }

    public CmWorkSelection prepareManEndsWith(String... value) {
        addEndsWith(CmWorkColumns.PREPARE_MAN, value);
        return this;
    }

    public CmWorkSelection orderByPrepareMan(boolean desc) {
        orderBy(CmWorkColumns.PREPARE_MAN, desc);
        return this;
    }

    public CmWorkSelection orderByPrepareMan() {
        orderBy(CmWorkColumns.PREPARE_MAN, false);
        return this;
    }

    public CmWorkSelection dispose(String... value) {
        addEquals(CmWorkColumns.DISPOSE, value);
        return this;
    }

    public CmWorkSelection disposeNot(String... value) {
        addNotEquals(CmWorkColumns.DISPOSE, value);
        return this;
    }

    public CmWorkSelection disposeLike(String... value) {
        addLike(CmWorkColumns.DISPOSE, value);
        return this;
    }

    public CmWorkSelection disposeContains(String... value) {
        addContains(CmWorkColumns.DISPOSE, value);
        return this;
    }

    public CmWorkSelection disposeStartsWith(String... value) {
        addStartsWith(CmWorkColumns.DISPOSE, value);
        return this;
    }

    public CmWorkSelection disposeEndsWith(String... value) {
        addEndsWith(CmWorkColumns.DISPOSE, value);
        return this;
    }

    public CmWorkSelection orderByDispose(boolean desc) {
        orderBy(CmWorkColumns.DISPOSE, desc);
        return this;
    }

    public CmWorkSelection orderByDispose() {
        orderBy(CmWorkColumns.DISPOSE, false);
        return this;
    }

    public CmWorkSelection workarea(String... value) {
        addEquals(CmWorkColumns.WORKAREA, value);
        return this;
    }

    public CmWorkSelection workareaNot(String... value) {
        addNotEquals(CmWorkColumns.WORKAREA, value);
        return this;
    }

    public CmWorkSelection workareaLike(String... value) {
        addLike(CmWorkColumns.WORKAREA, value);
        return this;
    }

    public CmWorkSelection workareaContains(String... value) {
        addContains(CmWorkColumns.WORKAREA, value);
        return this;
    }

    public CmWorkSelection workareaStartsWith(String... value) {
        addStartsWith(CmWorkColumns.WORKAREA, value);
        return this;
    }

    public CmWorkSelection workareaEndsWith(String... value) {
        addEndsWith(CmWorkColumns.WORKAREA, value);
        return this;
    }

    public CmWorkSelection orderByWorkarea(boolean desc) {
        orderBy(CmWorkColumns.WORKAREA, desc);
        return this;
    }

    public CmWorkSelection orderByWorkarea() {
        orderBy(CmWorkColumns.WORKAREA, false);
        return this;
    }

    public CmWorkSelection faultStartTime(Long... value) {
        addEquals(CmWorkColumns.FAULT_START_TIME, value);
        return this;
    }

    public CmWorkSelection faultStartTimeNot(Long... value) {
        addNotEquals(CmWorkColumns.FAULT_START_TIME, value);
        return this;
    }

    public CmWorkSelection faultStartTimeGt(long value) {
        addGreaterThan(CmWorkColumns.FAULT_START_TIME, value);
        return this;
    }

    public CmWorkSelection faultStartTimeGtEq(long value) {
        addGreaterThanOrEquals(CmWorkColumns.FAULT_START_TIME, value);
        return this;
    }

    public CmWorkSelection faultStartTimeLt(long value) {
        addLessThan(CmWorkColumns.FAULT_START_TIME, value);
        return this;
    }

    public CmWorkSelection faultStartTimeLtEq(long value) {
        addLessThanOrEquals(CmWorkColumns.FAULT_START_TIME, value);
        return this;
    }

    public CmWorkSelection orderByFaultStartTime(boolean desc) {
        orderBy(CmWorkColumns.FAULT_START_TIME, desc);
        return this;
    }

    public CmWorkSelection orderByFaultStartTime() {
        orderBy(CmWorkColumns.FAULT_START_TIME, false);
        return this;
    }

    public CmWorkSelection applySTime(Long... value) {
        addEquals(CmWorkColumns.APPLY_S_TIME, value);
        return this;
    }

    public CmWorkSelection applySTimeNot(Long... value) {
        addNotEquals(CmWorkColumns.APPLY_S_TIME, value);
        return this;
    }

    public CmWorkSelection applySTimeGt(long value) {
        addGreaterThan(CmWorkColumns.APPLY_S_TIME, value);
        return this;
    }

    public CmWorkSelection applySTimeGtEq(long value) {
        addGreaterThanOrEquals(CmWorkColumns.APPLY_S_TIME, value);
        return this;
    }

    public CmWorkSelection applySTimeLt(long value) {
        addLessThan(CmWorkColumns.APPLY_S_TIME, value);
        return this;
    }

    public CmWorkSelection applySTimeLtEq(long value) {
        addLessThanOrEquals(CmWorkColumns.APPLY_S_TIME, value);
        return this;
    }

    public CmWorkSelection orderByApplySTime(boolean desc) {
        orderBy(CmWorkColumns.APPLY_S_TIME, desc);
        return this;
    }

    public CmWorkSelection orderByApplySTime() {
        orderBy(CmWorkColumns.APPLY_S_TIME, false);
        return this;
    }

    public CmWorkSelection applyFTime(Long... value) {
        addEquals(CmWorkColumns.APPLY_F_TIME, value);
        return this;
    }

    public CmWorkSelection applyFTimeNot(Long... value) {
        addNotEquals(CmWorkColumns.APPLY_F_TIME, value);
        return this;
    }

    public CmWorkSelection applyFTimeGt(long value) {
        addGreaterThan(CmWorkColumns.APPLY_F_TIME, value);
        return this;
    }

    public CmWorkSelection applyFTimeGtEq(long value) {
        addGreaterThanOrEquals(CmWorkColumns.APPLY_F_TIME, value);
        return this;
    }

    public CmWorkSelection applyFTimeLt(long value) {
        addLessThan(CmWorkColumns.APPLY_F_TIME, value);
        return this;
    }

    public CmWorkSelection applyFTimeLtEq(long value) {
        addLessThanOrEquals(CmWorkColumns.APPLY_F_TIME, value);
        return this;
    }

    public CmWorkSelection orderByApplyFTime(boolean desc) {
        orderBy(CmWorkColumns.APPLY_F_TIME, desc);
        return this;
    }

    public CmWorkSelection orderByApplyFTime() {
        orderBy(CmWorkColumns.APPLY_F_TIME, false);
        return this;
    }

    public CmWorkSelection planSTime(Long... value) {
        addEquals(CmWorkColumns.PLAN_S_TIME, value);
        return this;
    }

    public CmWorkSelection planSTimeNot(Long... value) {
        addNotEquals(CmWorkColumns.PLAN_S_TIME, value);
        return this;
    }

    public CmWorkSelection planSTimeGt(long value) {
        addGreaterThan(CmWorkColumns.PLAN_S_TIME, value);
        return this;
    }

    public CmWorkSelection planSTimeGtEq(long value) {
        addGreaterThanOrEquals(CmWorkColumns.PLAN_S_TIME, value);
        return this;
    }

    public CmWorkSelection planSTimeLt(long value) {
        addLessThan(CmWorkColumns.PLAN_S_TIME, value);
        return this;
    }

    public CmWorkSelection planSTimeLtEq(long value) {
        addLessThanOrEquals(CmWorkColumns.PLAN_S_TIME, value);
        return this;
    }

    public CmWorkSelection orderByPlanSTime(boolean desc) {
        orderBy(CmWorkColumns.PLAN_S_TIME, desc);
        return this;
    }

    public CmWorkSelection orderByPlanSTime() {
        orderBy(CmWorkColumns.PLAN_S_TIME, false);
        return this;
    }

    public CmWorkSelection planFTime(Long... value) {
        addEquals(CmWorkColumns.PLAN_F_TIME, value);
        return this;
    }

    public CmWorkSelection planFTimeNot(Long... value) {
        addNotEquals(CmWorkColumns.PLAN_F_TIME, value);
        return this;
    }

    public CmWorkSelection planFTimeGt(long value) {
        addGreaterThan(CmWorkColumns.PLAN_F_TIME, value);
        return this;
    }

    public CmWorkSelection planFTimeGtEq(long value) {
        addGreaterThanOrEquals(CmWorkColumns.PLAN_F_TIME, value);
        return this;
    }

    public CmWorkSelection planFTimeLt(long value) {
        addLessThan(CmWorkColumns.PLAN_F_TIME, value);
        return this;
    }

    public CmWorkSelection planFTimeLtEq(long value) {
        addLessThanOrEquals(CmWorkColumns.PLAN_F_TIME, value);
        return this;
    }

    public CmWorkSelection orderByPlanFTime(boolean desc) {
        orderBy(CmWorkColumns.PLAN_F_TIME, desc);
        return this;
    }

    public CmWorkSelection orderByPlanFTime() {
        orderBy(CmWorkColumns.PLAN_F_TIME, false);
        return this;
    }

    public CmWorkSelection faultGradeText(String... value) {
        addEquals(CmWorkColumns.FAULT_GRADE_TEXT, value);
        return this;
    }

    public CmWorkSelection faultGradeTextNot(String... value) {
        addNotEquals(CmWorkColumns.FAULT_GRADE_TEXT, value);
        return this;
    }

    public CmWorkSelection faultGradeTextLike(String... value) {
        addLike(CmWorkColumns.FAULT_GRADE_TEXT, value);
        return this;
    }

    public CmWorkSelection faultGradeTextContains(String... value) {
        addContains(CmWorkColumns.FAULT_GRADE_TEXT, value);
        return this;
    }

    public CmWorkSelection faultGradeTextStartsWith(String... value) {
        addStartsWith(CmWorkColumns.FAULT_GRADE_TEXT, value);
        return this;
    }

    public CmWorkSelection faultGradeTextEndsWith(String... value) {
        addEndsWith(CmWorkColumns.FAULT_GRADE_TEXT, value);
        return this;
    }

    public CmWorkSelection orderByFaultGradeText(boolean desc) {
        orderBy(CmWorkColumns.FAULT_GRADE_TEXT, desc);
        return this;
    }

    public CmWorkSelection orderByFaultGradeText() {
        orderBy(CmWorkColumns.FAULT_GRADE_TEXT, false);
        return this;
    }

    public CmWorkSelection faultGradeCode(String... value) {
        addEquals(CmWorkColumns.FAULT_GRADE_CODE, value);
        return this;
    }

    public CmWorkSelection faultGradeCodeNot(String... value) {
        addNotEquals(CmWorkColumns.FAULT_GRADE_CODE, value);
        return this;
    }

    public CmWorkSelection faultGradeCodeLike(String... value) {
        addLike(CmWorkColumns.FAULT_GRADE_CODE, value);
        return this;
    }

    public CmWorkSelection faultGradeCodeContains(String... value) {
        addContains(CmWorkColumns.FAULT_GRADE_CODE, value);
        return this;
    }

    public CmWorkSelection faultGradeCodeStartsWith(String... value) {
        addStartsWith(CmWorkColumns.FAULT_GRADE_CODE, value);
        return this;
    }

    public CmWorkSelection faultGradeCodeEndsWith(String... value) {
        addEndsWith(CmWorkColumns.FAULT_GRADE_CODE, value);
        return this;
    }

    public CmWorkSelection orderByFaultGradeCode(boolean desc) {
        orderBy(CmWorkColumns.FAULT_GRADE_CODE, desc);
        return this;
    }

    public CmWorkSelection orderByFaultGradeCode() {
        orderBy(CmWorkColumns.FAULT_GRADE_CODE, false);
        return this;
    }

    public CmWorkSelection faultTypeText(String... value) {
        addEquals(CmWorkColumns.FAULT_TYPE_TEXT, value);
        return this;
    }

    public CmWorkSelection faultTypeTextNot(String... value) {
        addNotEquals(CmWorkColumns.FAULT_TYPE_TEXT, value);
        return this;
    }

    public CmWorkSelection faultTypeTextLike(String... value) {
        addLike(CmWorkColumns.FAULT_TYPE_TEXT, value);
        return this;
    }

    public CmWorkSelection faultTypeTextContains(String... value) {
        addContains(CmWorkColumns.FAULT_TYPE_TEXT, value);
        return this;
    }

    public CmWorkSelection faultTypeTextStartsWith(String... value) {
        addStartsWith(CmWorkColumns.FAULT_TYPE_TEXT, value);
        return this;
    }

    public CmWorkSelection faultTypeTextEndsWith(String... value) {
        addEndsWith(CmWorkColumns.FAULT_TYPE_TEXT, value);
        return this;
    }

    public CmWorkSelection orderByFaultTypeText(boolean desc) {
        orderBy(CmWorkColumns.FAULT_TYPE_TEXT, desc);
        return this;
    }

    public CmWorkSelection orderByFaultTypeText() {
        orderBy(CmWorkColumns.FAULT_TYPE_TEXT, false);
        return this;
    }

    public CmWorkSelection faultTypeCode(String... value) {
        addEquals(CmWorkColumns.FAULT_TYPE_CODE, value);
        return this;
    }

    public CmWorkSelection faultTypeCodeNot(String... value) {
        addNotEquals(CmWorkColumns.FAULT_TYPE_CODE, value);
        return this;
    }

    public CmWorkSelection faultTypeCodeLike(String... value) {
        addLike(CmWorkColumns.FAULT_TYPE_CODE, value);
        return this;
    }

    public CmWorkSelection faultTypeCodeContains(String... value) {
        addContains(CmWorkColumns.FAULT_TYPE_CODE, value);
        return this;
    }

    public CmWorkSelection faultTypeCodeStartsWith(String... value) {
        addStartsWith(CmWorkColumns.FAULT_TYPE_CODE, value);
        return this;
    }

    public CmWorkSelection faultTypeCodeEndsWith(String... value) {
        addEndsWith(CmWorkColumns.FAULT_TYPE_CODE, value);
        return this;
    }

    public CmWorkSelection orderByFaultTypeCode(boolean desc) {
        orderBy(CmWorkColumns.FAULT_TYPE_CODE, desc);
        return this;
    }

    public CmWorkSelection orderByFaultTypeCode() {
        orderBy(CmWorkColumns.FAULT_TYPE_CODE, false);
        return this;
    }

    public CmWorkSelection faultCauseText(String... value) {
        addEquals(CmWorkColumns.FAULT_CAUSE_TEXT, value);
        return this;
    }

    public CmWorkSelection faultCauseTextNot(String... value) {
        addNotEquals(CmWorkColumns.FAULT_CAUSE_TEXT, value);
        return this;
    }

    public CmWorkSelection faultCauseTextLike(String... value) {
        addLike(CmWorkColumns.FAULT_CAUSE_TEXT, value);
        return this;
    }

    public CmWorkSelection faultCauseTextContains(String... value) {
        addContains(CmWorkColumns.FAULT_CAUSE_TEXT, value);
        return this;
    }

    public CmWorkSelection faultCauseTextStartsWith(String... value) {
        addStartsWith(CmWorkColumns.FAULT_CAUSE_TEXT, value);
        return this;
    }

    public CmWorkSelection faultCauseTextEndsWith(String... value) {
        addEndsWith(CmWorkColumns.FAULT_CAUSE_TEXT, value);
        return this;
    }

    public CmWorkSelection orderByFaultCauseText(boolean desc) {
        orderBy(CmWorkColumns.FAULT_CAUSE_TEXT, desc);
        return this;
    }

    public CmWorkSelection orderByFaultCauseText() {
        orderBy(CmWorkColumns.FAULT_CAUSE_TEXT, false);
        return this;
    }

    public CmWorkSelection faultCauseCode(String... value) {
        addEquals(CmWorkColumns.FAULT_CAUSE_CODE, value);
        return this;
    }

    public CmWorkSelection faultCauseCodeNot(String... value) {
        addNotEquals(CmWorkColumns.FAULT_CAUSE_CODE, value);
        return this;
    }

    public CmWorkSelection faultCauseCodeLike(String... value) {
        addLike(CmWorkColumns.FAULT_CAUSE_CODE, value);
        return this;
    }

    public CmWorkSelection faultCauseCodeContains(String... value) {
        addContains(CmWorkColumns.FAULT_CAUSE_CODE, value);
        return this;
    }

    public CmWorkSelection faultCauseCodeStartsWith(String... value) {
        addStartsWith(CmWorkColumns.FAULT_CAUSE_CODE, value);
        return this;
    }

    public CmWorkSelection faultCauseCodeEndsWith(String... value) {
        addEndsWith(CmWorkColumns.FAULT_CAUSE_CODE, value);
        return this;
    }

    public CmWorkSelection orderByFaultCauseCode(boolean desc) {
        orderBy(CmWorkColumns.FAULT_CAUSE_CODE, desc);
        return this;
    }

    public CmWorkSelection orderByFaultCauseCode() {
        orderBy(CmWorkColumns.FAULT_CAUSE_CODE, false);
        return this;
    }

    public CmWorkSelection workDetailsText(String... value) {
        addEquals(CmWorkColumns.WORK_DETAILS_TEXT, value);
        return this;
    }

    public CmWorkSelection workDetailsTextNot(String... value) {
        addNotEquals(CmWorkColumns.WORK_DETAILS_TEXT, value);
        return this;
    }

    public CmWorkSelection workDetailsTextLike(String... value) {
        addLike(CmWorkColumns.WORK_DETAILS_TEXT, value);
        return this;
    }

    public CmWorkSelection workDetailsTextContains(String... value) {
        addContains(CmWorkColumns.WORK_DETAILS_TEXT, value);
        return this;
    }

    public CmWorkSelection workDetailsTextStartsWith(String... value) {
        addStartsWith(CmWorkColumns.WORK_DETAILS_TEXT, value);
        return this;
    }

    public CmWorkSelection workDetailsTextEndsWith(String... value) {
        addEndsWith(CmWorkColumns.WORK_DETAILS_TEXT, value);
        return this;
    }

    public CmWorkSelection orderByWorkDetailsText(boolean desc) {
        orderBy(CmWorkColumns.WORK_DETAILS_TEXT, desc);
        return this;
    }

    public CmWorkSelection orderByWorkDetailsText() {
        orderBy(CmWorkColumns.WORK_DETAILS_TEXT, false);
        return this;
    }

    public CmWorkSelection workDetailsCode(String... value) {
        addEquals(CmWorkColumns.WORK_DETAILS_CODE, value);
        return this;
    }

    public CmWorkSelection workDetailsCodeNot(String... value) {
        addNotEquals(CmWorkColumns.WORK_DETAILS_CODE, value);
        return this;
    }

    public CmWorkSelection workDetailsCodeLike(String... value) {
        addLike(CmWorkColumns.WORK_DETAILS_CODE, value);
        return this;
    }

    public CmWorkSelection workDetailsCodeContains(String... value) {
        addContains(CmWorkColumns.WORK_DETAILS_CODE, value);
        return this;
    }

    public CmWorkSelection workDetailsCodeStartsWith(String... value) {
        addStartsWith(CmWorkColumns.WORK_DETAILS_CODE, value);
        return this;
    }

    public CmWorkSelection workDetailsCodeEndsWith(String... value) {
        addEndsWith(CmWorkColumns.WORK_DETAILS_CODE, value);
        return this;
    }

    public CmWorkSelection orderByWorkDetailsCode(boolean desc) {
        orderBy(CmWorkColumns.WORK_DETAILS_CODE, desc);
        return this;
    }

    public CmWorkSelection orderByWorkDetailsCode() {
        orderBy(CmWorkColumns.WORK_DETAILS_CODE, false);
        return this;
    }

    public CmWorkSelection workDoneText(String... value) {
        addEquals(CmWorkColumns.WORK_DONE_TEXT, value);
        return this;
    }

    public CmWorkSelection workDoneTextNot(String... value) {
        addNotEquals(CmWorkColumns.WORK_DONE_TEXT, value);
        return this;
    }

    public CmWorkSelection workDoneTextLike(String... value) {
        addLike(CmWorkColumns.WORK_DONE_TEXT, value);
        return this;
    }

    public CmWorkSelection workDoneTextContains(String... value) {
        addContains(CmWorkColumns.WORK_DONE_TEXT, value);
        return this;
    }

    public CmWorkSelection workDoneTextStartsWith(String... value) {
        addStartsWith(CmWorkColumns.WORK_DONE_TEXT, value);
        return this;
    }

    public CmWorkSelection workDoneTextEndsWith(String... value) {
        addEndsWith(CmWorkColumns.WORK_DONE_TEXT, value);
        return this;
    }

    public CmWorkSelection orderByWorkDoneText(boolean desc) {
        orderBy(CmWorkColumns.WORK_DONE_TEXT, desc);
        return this;
    }

    public CmWorkSelection orderByWorkDoneText() {
        orderBy(CmWorkColumns.WORK_DONE_TEXT, false);
        return this;
    }

    public CmWorkSelection workDoneCode(String... value) {
        addEquals(CmWorkColumns.WORK_DONE_CODE, value);
        return this;
    }

    public CmWorkSelection workDoneCodeNot(String... value) {
        addNotEquals(CmWorkColumns.WORK_DONE_CODE, value);
        return this;
    }

    public CmWorkSelection workDoneCodeLike(String... value) {
        addLike(CmWorkColumns.WORK_DONE_CODE, value);
        return this;
    }

    public CmWorkSelection workDoneCodeContains(String... value) {
        addContains(CmWorkColumns.WORK_DONE_CODE, value);
        return this;
    }

    public CmWorkSelection workDoneCodeStartsWith(String... value) {
        addStartsWith(CmWorkColumns.WORK_DONE_CODE, value);
        return this;
    }

    public CmWorkSelection workDoneCodeEndsWith(String... value) {
        addEndsWith(CmWorkColumns.WORK_DONE_CODE, value);
        return this;
    }

    public CmWorkSelection orderByWorkDoneCode(boolean desc) {
        orderBy(CmWorkColumns.WORK_DONE_CODE, desc);
        return this;
    }

    public CmWorkSelection orderByWorkDoneCode() {
        orderBy(CmWorkColumns.WORK_DONE_CODE, false);
        return this;
    }

    public CmWorkSelection faultCauseNote(String... value) {
        addEquals(CmWorkColumns.FAULT_CAUSE_NOTE, value);
        return this;
    }

    public CmWorkSelection faultCauseNoteNot(String... value) {
        addNotEquals(CmWorkColumns.FAULT_CAUSE_NOTE, value);
        return this;
    }

    public CmWorkSelection faultCauseNoteLike(String... value) {
        addLike(CmWorkColumns.FAULT_CAUSE_NOTE, value);
        return this;
    }

    public CmWorkSelection faultCauseNoteContains(String... value) {
        addContains(CmWorkColumns.FAULT_CAUSE_NOTE, value);
        return this;
    }

    public CmWorkSelection faultCauseNoteStartsWith(String... value) {
        addStartsWith(CmWorkColumns.FAULT_CAUSE_NOTE, value);
        return this;
    }

    public CmWorkSelection faultCauseNoteEndsWith(String... value) {
        addEndsWith(CmWorkColumns.FAULT_CAUSE_NOTE, value);
        return this;
    }

    public CmWorkSelection orderByFaultCauseNote(boolean desc) {
        orderBy(CmWorkColumns.FAULT_CAUSE_NOTE, desc);
        return this;
    }

    public CmWorkSelection orderByFaultCauseNote() {
        orderBy(CmWorkColumns.FAULT_CAUSE_NOTE, false);
        return this;
    }

    public CmWorkSelection workNote(String... value) {
        addEquals(CmWorkColumns.WORK_NOTE, value);
        return this;
    }

    public CmWorkSelection workNoteNot(String... value) {
        addNotEquals(CmWorkColumns.WORK_NOTE, value);
        return this;
    }

    public CmWorkSelection workNoteLike(String... value) {
        addLike(CmWorkColumns.WORK_NOTE, value);
        return this;
    }

    public CmWorkSelection workNoteContains(String... value) {
        addContains(CmWorkColumns.WORK_NOTE, value);
        return this;
    }

    public CmWorkSelection workNoteStartsWith(String... value) {
        addStartsWith(CmWorkColumns.WORK_NOTE, value);
        return this;
    }

    public CmWorkSelection workNoteEndsWith(String... value) {
        addEndsWith(CmWorkColumns.WORK_NOTE, value);
        return this;
    }

    public CmWorkSelection orderByWorkNote(boolean desc) {
        orderBy(CmWorkColumns.WORK_NOTE, desc);
        return this;
    }

    public CmWorkSelection orderByWorkNote() {
        orderBy(CmWorkColumns.WORK_NOTE, false);
        return this;
    }

    public CmWorkSelection operatorText(String... value) {
        addEquals(CmWorkColumns.OPERATOR_TEXT, value);
        return this;
    }

    public CmWorkSelection operatorTextNot(String... value) {
        addNotEquals(CmWorkColumns.OPERATOR_TEXT, value);
        return this;
    }

    public CmWorkSelection operatorTextLike(String... value) {
        addLike(CmWorkColumns.OPERATOR_TEXT, value);
        return this;
    }

    public CmWorkSelection operatorTextContains(String... value) {
        addContains(CmWorkColumns.OPERATOR_TEXT, value);
        return this;
    }

    public CmWorkSelection operatorTextStartsWith(String... value) {
        addStartsWith(CmWorkColumns.OPERATOR_TEXT, value);
        return this;
    }

    public CmWorkSelection operatorTextEndsWith(String... value) {
        addEndsWith(CmWorkColumns.OPERATOR_TEXT, value);
        return this;
    }

    public CmWorkSelection orderByOperatorText(boolean desc) {
        orderBy(CmWorkColumns.OPERATOR_TEXT, desc);
        return this;
    }

    public CmWorkSelection orderByOperatorText() {
        orderBy(CmWorkColumns.OPERATOR_TEXT, false);
        return this;
    }

    public CmWorkSelection operatorCode(String... value) {
        addEquals(CmWorkColumns.OPERATOR_CODE, value);
        return this;
    }

    public CmWorkSelection operatorCodeNot(String... value) {
        addNotEquals(CmWorkColumns.OPERATOR_CODE, value);
        return this;
    }

    public CmWorkSelection operatorCodeLike(String... value) {
        addLike(CmWorkColumns.OPERATOR_CODE, value);
        return this;
    }

    public CmWorkSelection operatorCodeContains(String... value) {
        addContains(CmWorkColumns.OPERATOR_CODE, value);
        return this;
    }

    public CmWorkSelection operatorCodeStartsWith(String... value) {
        addStartsWith(CmWorkColumns.OPERATOR_CODE, value);
        return this;
    }

    public CmWorkSelection operatorCodeEndsWith(String... value) {
        addEndsWith(CmWorkColumns.OPERATOR_CODE, value);
        return this;
    }

    public CmWorkSelection orderByOperatorCode(boolean desc) {
        orderBy(CmWorkColumns.OPERATOR_CODE, desc);
        return this;
    }

    public CmWorkSelection orderByOperatorCode() {
        orderBy(CmWorkColumns.OPERATOR_CODE, false);
        return this;
    }

    public CmWorkSelection operationStartTime(Long... value) {
        addEquals(CmWorkColumns.OPERATION_START_TIME, value);
        return this;
    }

    public CmWorkSelection operationStartTimeNot(Long... value) {
        addNotEquals(CmWorkColumns.OPERATION_START_TIME, value);
        return this;
    }

    public CmWorkSelection operationStartTimeGt(long value) {
        addGreaterThan(CmWorkColumns.OPERATION_START_TIME, value);
        return this;
    }

    public CmWorkSelection operationStartTimeGtEq(long value) {
        addGreaterThanOrEquals(CmWorkColumns.OPERATION_START_TIME, value);
        return this;
    }

    public CmWorkSelection operationStartTimeLt(long value) {
        addLessThan(CmWorkColumns.OPERATION_START_TIME, value);
        return this;
    }

    public CmWorkSelection operationStartTimeLtEq(long value) {
        addLessThanOrEquals(CmWorkColumns.OPERATION_START_TIME, value);
        return this;
    }

    public CmWorkSelection orderByOperationStartTime(boolean desc) {
        orderBy(CmWorkColumns.OPERATION_START_TIME, desc);
        return this;
    }

    public CmWorkSelection orderByOperationStartTime() {
        orderBy(CmWorkColumns.OPERATION_START_TIME, false);
        return this;
    }

    public CmWorkSelection operationEndTime(Long... value) {
        addEquals(CmWorkColumns.OPERATION_END_TIME, value);
        return this;
    }

    public CmWorkSelection operationEndTimeNot(Long... value) {
        addNotEquals(CmWorkColumns.OPERATION_END_TIME, value);
        return this;
    }

    public CmWorkSelection operationEndTimeGt(long value) {
        addGreaterThan(CmWorkColumns.OPERATION_END_TIME, value);
        return this;
    }

    public CmWorkSelection operationEndTimeGtEq(long value) {
        addGreaterThanOrEquals(CmWorkColumns.OPERATION_END_TIME, value);
        return this;
    }

    public CmWorkSelection operationEndTimeLt(long value) {
        addLessThan(CmWorkColumns.OPERATION_END_TIME, value);
        return this;
    }

    public CmWorkSelection operationEndTimeLtEq(long value) {
        addLessThanOrEquals(CmWorkColumns.OPERATION_END_TIME, value);
        return this;
    }

    public CmWorkSelection orderByOperationEndTime(boolean desc) {
        orderBy(CmWorkColumns.OPERATION_END_TIME, desc);
        return this;
    }

    public CmWorkSelection orderByOperationEndTime() {
        orderBy(CmWorkColumns.OPERATION_END_TIME, false);
        return this;
    }

    public CmWorkSelection orderReceiveTime(Long... value) {
        addEquals(CmWorkColumns.ORDER_RECEIVE_TIME, value);
        return this;
    }

    public CmWorkSelection orderReceiveTimeNot(Long... value) {
        addNotEquals(CmWorkColumns.ORDER_RECEIVE_TIME, value);
        return this;
    }

    public CmWorkSelection orderReceiveTimeGt(long value) {
        addGreaterThan(CmWorkColumns.ORDER_RECEIVE_TIME, value);
        return this;
    }

    public CmWorkSelection orderReceiveTimeGtEq(long value) {
        addGreaterThanOrEquals(CmWorkColumns.ORDER_RECEIVE_TIME, value);
        return this;
    }

    public CmWorkSelection orderReceiveTimeLt(long value) {
        addLessThan(CmWorkColumns.ORDER_RECEIVE_TIME, value);
        return this;
    }

    public CmWorkSelection orderReceiveTimeLtEq(long value) {
        addLessThanOrEquals(CmWorkColumns.ORDER_RECEIVE_TIME, value);
        return this;
    }

    public CmWorkSelection orderByOrderReceiveTime(boolean desc) {
        orderBy(CmWorkColumns.ORDER_RECEIVE_TIME, desc);
        return this;
    }

    public CmWorkSelection orderByOrderReceiveTime() {
        orderBy(CmWorkColumns.ORDER_RECEIVE_TIME, false);
        return this;
    }

    public CmWorkSelection arriveTime(Long... value) {
        addEquals(CmWorkColumns.ARRIVE_TIME, value);
        return this;
    }

    public CmWorkSelection arriveTimeNot(Long... value) {
        addNotEquals(CmWorkColumns.ARRIVE_TIME, value);
        return this;
    }

    public CmWorkSelection arriveTimeGt(long value) {
        addGreaterThan(CmWorkColumns.ARRIVE_TIME, value);
        return this;
    }

    public CmWorkSelection arriveTimeGtEq(long value) {
        addGreaterThanOrEquals(CmWorkColumns.ARRIVE_TIME, value);
        return this;
    }

    public CmWorkSelection arriveTimeLt(long value) {
        addLessThan(CmWorkColumns.ARRIVE_TIME, value);
        return this;
    }

    public CmWorkSelection arriveTimeLtEq(long value) {
        addLessThanOrEquals(CmWorkColumns.ARRIVE_TIME, value);
        return this;
    }

    public CmWorkSelection orderByArriveTime(boolean desc) {
        orderBy(CmWorkColumns.ARRIVE_TIME, desc);
        return this;
    }

    public CmWorkSelection orderByArriveTime() {
        orderBy(CmWorkColumns.ARRIVE_TIME, false);
        return this;
    }

    public CmWorkSelection intCustomerNo(String... value) {
        addEquals(CmWorkColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public CmWorkSelection intCustomerNoNot(String... value) {
        addNotEquals(CmWorkColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public CmWorkSelection intCustomerNoLike(String... value) {
        addLike(CmWorkColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public CmWorkSelection intCustomerNoContains(String... value) {
        addContains(CmWorkColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public CmWorkSelection intCustomerNoStartsWith(String... value) {
        addStartsWith(CmWorkColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public CmWorkSelection intCustomerNoEndsWith(String... value) {
        addEndsWith(CmWorkColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public CmWorkSelection orderByIntCustomerNo(boolean desc) {
        orderBy(CmWorkColumns.INT_CUSTOMER_NO, desc);
        return this;
    }

    public CmWorkSelection orderByIntCustomerNo() {
        orderBy(CmWorkColumns.INT_CUSTOMER_NO, false);
        return this;
    }

    public CmWorkSelection formFlag(String... value) {
        addEquals(CmWorkColumns.FORM_FLAG, value);
        return this;
    }

    public CmWorkSelection formFlagNot(String... value) {
        addNotEquals(CmWorkColumns.FORM_FLAG, value);
        return this;
    }

    public CmWorkSelection formFlagLike(String... value) {
        addLike(CmWorkColumns.FORM_FLAG, value);
        return this;
    }

    public CmWorkSelection formFlagContains(String... value) {
        addContains(CmWorkColumns.FORM_FLAG, value);
        return this;
    }

    public CmWorkSelection formFlagStartsWith(String... value) {
        addStartsWith(CmWorkColumns.FORM_FLAG, value);
        return this;
    }

    public CmWorkSelection formFlagEndsWith(String... value) {
        addEndsWith(CmWorkColumns.FORM_FLAG, value);
        return this;
    }

    public CmWorkSelection orderByFormFlag(boolean desc) {
        orderBy(CmWorkColumns.FORM_FLAG, desc);
        return this;
    }

    public CmWorkSelection orderByFormFlag() {
        orderBy(CmWorkColumns.FORM_FLAG, false);
        return this;
    }

    public CmWorkSelection equipFault(String... value) {
        addEquals(CmWorkColumns.EQUIP_FAULT, value);
        return this;
    }

    public CmWorkSelection equipFaultNot(String... value) {
        addNotEquals(CmWorkColumns.EQUIP_FAULT, value);
        return this;
    }

    public CmWorkSelection equipFaultLike(String... value) {
        addLike(CmWorkColumns.EQUIP_FAULT, value);
        return this;
    }

    public CmWorkSelection equipFaultContains(String... value) {
        addContains(CmWorkColumns.EQUIP_FAULT, value);
        return this;
    }

    public CmWorkSelection equipFaultStartsWith(String... value) {
        addStartsWith(CmWorkColumns.EQUIP_FAULT, value);
        return this;
    }

    public CmWorkSelection equipFaultEndsWith(String... value) {
        addEndsWith(CmWorkColumns.EQUIP_FAULT, value);
        return this;
    }

    public CmWorkSelection orderByEquipFault(boolean desc) {
        orderBy(CmWorkColumns.EQUIP_FAULT, desc);
        return this;
    }

    public CmWorkSelection orderByEquipFault() {
        orderBy(CmWorkColumns.EQUIP_FAULT, false);
        return this;
    }

    public CmWorkSelection statusBackstage(String... value) {
        addEquals(CmWorkColumns.STATUS_BACKSTAGE, value);
        return this;
    }

    public CmWorkSelection statusBackstageNot(String... value) {
        addNotEquals(CmWorkColumns.STATUS_BACKSTAGE, value);
        return this;
    }

    public CmWorkSelection statusBackstageLike(String... value) {
        addLike(CmWorkColumns.STATUS_BACKSTAGE, value);
        return this;
    }

    public CmWorkSelection statusBackstageContains(String... value) {
        addContains(CmWorkColumns.STATUS_BACKSTAGE, value);
        return this;
    }

    public CmWorkSelection statusBackstageStartsWith(String... value) {
        addStartsWith(CmWorkColumns.STATUS_BACKSTAGE, value);
        return this;
    }

    public CmWorkSelection statusBackstageEndsWith(String... value) {
        addEndsWith(CmWorkColumns.STATUS_BACKSTAGE, value);
        return this;
    }

    public CmWorkSelection orderByStatusBackstage(boolean desc) {
        orderBy(CmWorkColumns.STATUS_BACKSTAGE, desc);
        return this;
    }

    public CmWorkSelection orderByStatusBackstage() {
        orderBy(CmWorkColumns.STATUS_BACKSTAGE, false);
        return this;
    }

    public CmWorkSelection status(CmWorkStatus... value) {
        addEquals(CmWorkColumns.STATUS, value);
        return this;
    }

    public CmWorkSelection statusNot(CmWorkStatus... value) {
        addNotEquals(CmWorkColumns.STATUS, value);
        return this;
    }


    public CmWorkSelection orderByStatus(boolean desc) {
        orderBy(CmWorkColumns.STATUS, desc);
        return this;
    }

    public CmWorkSelection orderByStatus() {
        orderBy(CmWorkColumns.STATUS, false);
        return this;
    }

    public CmWorkSelection lastModified(Long... value) {
        addEquals(CmWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public CmWorkSelection lastModifiedNot(Long... value) {
        addNotEquals(CmWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public CmWorkSelection lastModifiedGt(long value) {
        addGreaterThan(CmWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public CmWorkSelection lastModifiedGtEq(long value) {
        addGreaterThanOrEquals(CmWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public CmWorkSelection lastModifiedLt(long value) {
        addLessThan(CmWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public CmWorkSelection lastModifiedLtEq(long value) {
        addLessThanOrEquals(CmWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public CmWorkSelection orderByLastModified(boolean desc) {
        orderBy(CmWorkColumns.LAST_MODIFIED, desc);
        return this;
    }

    public CmWorkSelection orderByLastModified() {
        orderBy(CmWorkColumns.LAST_MODIFIED, false);
        return this;
    }

    public CmWorkSelection deletePending(Boolean value) {
        addEquals(CmWorkColumns.DELETE_PENDING, toObjectArray(value));
        return this;
    }

    public CmWorkSelection orderByDeletePending(boolean desc) {
        orderBy(CmWorkColumns.DELETE_PENDING, desc);
        return this;
    }

    public CmWorkSelection orderByDeletePending() {
        orderBy(CmWorkColumns.DELETE_PENDING, false);
        return this;
    }

    public CmWorkSelection uploadPending(Boolean value) {
        addEquals(CmWorkColumns.UPLOAD_PENDING, toObjectArray(value));
        return this;
    }

    public CmWorkSelection orderByUploadPending(boolean desc) {
        orderBy(CmWorkColumns.UPLOAD_PENDING, desc);
        return this;
    }

    public CmWorkSelection orderByUploadPending() {
        orderBy(CmWorkColumns.UPLOAD_PENDING, false);
        return this;
    }
}
