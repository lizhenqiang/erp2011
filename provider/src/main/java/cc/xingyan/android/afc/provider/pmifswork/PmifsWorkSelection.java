package cc.xingyan.android.afc.provider.pmifswork;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code pmifs_work} table.
 */
public class PmifsWorkSelection extends AbstractSelection<PmifsWorkSelection> {
    @Override
    protected Uri baseUri() {
        return PmifsWorkColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PmifsWorkCursor} object, which is positioned before the first entry, or null.
     */
    public PmifsWorkCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PmifsWorkCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public PmifsWorkCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PmifsWorkCursor} object, which is positioned before the first entry, or null.
     */
    public PmifsWorkCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PmifsWorkCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public PmifsWorkCursor query(Context context) {
        return query(context, null);
    }


    public PmifsWorkSelection id(long... value) {
        addEquals("pmifs_work." + PmifsWorkColumns._ID, toObjectArray(value));
        return this;
    }

    public PmifsWorkSelection idNot(long... value) {
        addNotEquals("pmifs_work." + PmifsWorkColumns._ID, toObjectArray(value));
        return this;
    }

    public PmifsWorkSelection orderById(boolean desc) {
        orderBy("pmifs_work." + PmifsWorkColumns._ID, desc);
        return this;
    }

    public PmifsWorkSelection orderById() {
        return orderById(false);
    }

    public PmifsWorkSelection userId(String... value) {
        addEquals(PmifsWorkColumns.USER_ID, value);
        return this;
    }

    public PmifsWorkSelection userIdNot(String... value) {
        addNotEquals(PmifsWorkColumns.USER_ID, value);
        return this;
    }

    public PmifsWorkSelection userIdLike(String... value) {
        addLike(PmifsWorkColumns.USER_ID, value);
        return this;
    }

    public PmifsWorkSelection userIdContains(String... value) {
        addContains(PmifsWorkColumns.USER_ID, value);
        return this;
    }

    public PmifsWorkSelection userIdStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.USER_ID, value);
        return this;
    }

    public PmifsWorkSelection userIdEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.USER_ID, value);
        return this;
    }

    public PmifsWorkSelection orderByUserId(boolean desc) {
        orderBy(PmifsWorkColumns.USER_ID, desc);
        return this;
    }

    public PmifsWorkSelection orderByUserId() {
        orderBy(PmifsWorkColumns.USER_ID, false);
        return this;
    }

    public PmifsWorkSelection guid(String... value) {
        addEquals(PmifsWorkColumns.GUID, value);
        return this;
    }

    public PmifsWorkSelection guidNot(String... value) {
        addNotEquals(PmifsWorkColumns.GUID, value);
        return this;
    }

    public PmifsWorkSelection guidLike(String... value) {
        addLike(PmifsWorkColumns.GUID, value);
        return this;
    }

    public PmifsWorkSelection guidContains(String... value) {
        addContains(PmifsWorkColumns.GUID, value);
        return this;
    }

    public PmifsWorkSelection guidStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.GUID, value);
        return this;
    }

    public PmifsWorkSelection guidEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.GUID, value);
        return this;
    }

    public PmifsWorkSelection orderByGuid(boolean desc) {
        orderBy(PmifsWorkColumns.GUID, desc);
        return this;
    }

    public PmifsWorkSelection orderByGuid() {
        orderBy(PmifsWorkColumns.GUID, false);
        return this;
    }

    public PmifsWorkSelection orderId(String... value) {
        addEquals(PmifsWorkColumns.ORDER_ID, value);
        return this;
    }

    public PmifsWorkSelection orderIdNot(String... value) {
        addNotEquals(PmifsWorkColumns.ORDER_ID, value);
        return this;
    }

    public PmifsWorkSelection orderIdLike(String... value) {
        addLike(PmifsWorkColumns.ORDER_ID, value);
        return this;
    }

    public PmifsWorkSelection orderIdContains(String... value) {
        addContains(PmifsWorkColumns.ORDER_ID, value);
        return this;
    }

    public PmifsWorkSelection orderIdStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.ORDER_ID, value);
        return this;
    }

    public PmifsWorkSelection orderIdEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.ORDER_ID, value);
        return this;
    }

    public PmifsWorkSelection orderByOrderId(boolean desc) {
        orderBy(PmifsWorkColumns.ORDER_ID, desc);
        return this;
    }

    public PmifsWorkSelection orderByOrderId() {
        orderBy(PmifsWorkColumns.ORDER_ID, false);
        return this;
    }

    public PmifsWorkSelection priorityText(String... value) {
        addEquals(PmifsWorkColumns.PRIORITY_TEXT, value);
        return this;
    }

    public PmifsWorkSelection priorityTextNot(String... value) {
        addNotEquals(PmifsWorkColumns.PRIORITY_TEXT, value);
        return this;
    }

    public PmifsWorkSelection priorityTextLike(String... value) {
        addLike(PmifsWorkColumns.PRIORITY_TEXT, value);
        return this;
    }

    public PmifsWorkSelection priorityTextContains(String... value) {
        addContains(PmifsWorkColumns.PRIORITY_TEXT, value);
        return this;
    }

    public PmifsWorkSelection priorityTextStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.PRIORITY_TEXT, value);
        return this;
    }

    public PmifsWorkSelection priorityTextEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.PRIORITY_TEXT, value);
        return this;
    }

    public PmifsWorkSelection orderByPriorityText(boolean desc) {
        orderBy(PmifsWorkColumns.PRIORITY_TEXT, desc);
        return this;
    }

    public PmifsWorkSelection orderByPriorityText() {
        orderBy(PmifsWorkColumns.PRIORITY_TEXT, false);
        return this;
    }

    public PmifsWorkSelection priorityCode(String... value) {
        addEquals(PmifsWorkColumns.PRIORITY_CODE, value);
        return this;
    }

    public PmifsWorkSelection priorityCodeNot(String... value) {
        addNotEquals(PmifsWorkColumns.PRIORITY_CODE, value);
        return this;
    }

    public PmifsWorkSelection priorityCodeLike(String... value) {
        addLike(PmifsWorkColumns.PRIORITY_CODE, value);
        return this;
    }

    public PmifsWorkSelection priorityCodeContains(String... value) {
        addContains(PmifsWorkColumns.PRIORITY_CODE, value);
        return this;
    }

    public PmifsWorkSelection priorityCodeStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.PRIORITY_CODE, value);
        return this;
    }

    public PmifsWorkSelection priorityCodeEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.PRIORITY_CODE, value);
        return this;
    }

    public PmifsWorkSelection orderByPriorityCode(boolean desc) {
        orderBy(PmifsWorkColumns.PRIORITY_CODE, desc);
        return this;
    }

    public PmifsWorkSelection orderByPriorityCode() {
        orderBy(PmifsWorkColumns.PRIORITY_CODE, false);
        return this;
    }

    public PmifsWorkSelection deviceCode(String... value) {
        addEquals(PmifsWorkColumns.DEVICE_CODE, value);
        return this;
    }

    public PmifsWorkSelection deviceCodeNot(String... value) {
        addNotEquals(PmifsWorkColumns.DEVICE_CODE, value);
        return this;
    }

    public PmifsWorkSelection deviceCodeLike(String... value) {
        addLike(PmifsWorkColumns.DEVICE_CODE, value);
        return this;
    }

    public PmifsWorkSelection deviceCodeContains(String... value) {
        addContains(PmifsWorkColumns.DEVICE_CODE, value);
        return this;
    }

    public PmifsWorkSelection deviceCodeStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.DEVICE_CODE, value);
        return this;
    }

    public PmifsWorkSelection deviceCodeEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.DEVICE_CODE, value);
        return this;
    }

    public PmifsWorkSelection orderByDeviceCode(boolean desc) {
        orderBy(PmifsWorkColumns.DEVICE_CODE, desc);
        return this;
    }

    public PmifsWorkSelection orderByDeviceCode() {
        orderBy(PmifsWorkColumns.DEVICE_CODE, false);
        return this;
    }

    public PmifsWorkSelection deviceName(String... value) {
        addEquals(PmifsWorkColumns.DEVICE_NAME, value);
        return this;
    }

    public PmifsWorkSelection deviceNameNot(String... value) {
        addNotEquals(PmifsWorkColumns.DEVICE_NAME, value);
        return this;
    }

    public PmifsWorkSelection deviceNameLike(String... value) {
        addLike(PmifsWorkColumns.DEVICE_NAME, value);
        return this;
    }

    public PmifsWorkSelection deviceNameContains(String... value) {
        addContains(PmifsWorkColumns.DEVICE_NAME, value);
        return this;
    }

    public PmifsWorkSelection deviceNameStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.DEVICE_NAME, value);
        return this;
    }

    public PmifsWorkSelection deviceNameEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.DEVICE_NAME, value);
        return this;
    }

    public PmifsWorkSelection orderByDeviceName(boolean desc) {
        orderBy(PmifsWorkColumns.DEVICE_NAME, desc);
        return this;
    }

    public PmifsWorkSelection orderByDeviceName() {
        orderBy(PmifsWorkColumns.DEVICE_NAME, false);
        return this;
    }

    public PmifsWorkSelection deviceLogicCode(String... value) {
        addEquals(PmifsWorkColumns.DEVICE_LOGIC_CODE, value);
        return this;
    }

    public PmifsWorkSelection deviceLogicCodeNot(String... value) {
        addNotEquals(PmifsWorkColumns.DEVICE_LOGIC_CODE, value);
        return this;
    }

    public PmifsWorkSelection deviceLogicCodeLike(String... value) {
        addLike(PmifsWorkColumns.DEVICE_LOGIC_CODE, value);
        return this;
    }

    public PmifsWorkSelection deviceLogicCodeContains(String... value) {
        addContains(PmifsWorkColumns.DEVICE_LOGIC_CODE, value);
        return this;
    }

    public PmifsWorkSelection deviceLogicCodeStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.DEVICE_LOGIC_CODE, value);
        return this;
    }

    public PmifsWorkSelection deviceLogicCodeEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.DEVICE_LOGIC_CODE, value);
        return this;
    }

    public PmifsWorkSelection orderByDeviceLogicCode(boolean desc) {
        orderBy(PmifsWorkColumns.DEVICE_LOGIC_CODE, desc);
        return this;
    }

    public PmifsWorkSelection orderByDeviceLogicCode() {
        orderBy(PmifsWorkColumns.DEVICE_LOGIC_CODE, false);
        return this;
    }

    public PmifsWorkSelection deviceLogicName(String... value) {
        addEquals(PmifsWorkColumns.DEVICE_LOGIC_NAME, value);
        return this;
    }

    public PmifsWorkSelection deviceLogicNameNot(String... value) {
        addNotEquals(PmifsWorkColumns.DEVICE_LOGIC_NAME, value);
        return this;
    }

    public PmifsWorkSelection deviceLogicNameLike(String... value) {
        addLike(PmifsWorkColumns.DEVICE_LOGIC_NAME, value);
        return this;
    }

    public PmifsWorkSelection deviceLogicNameContains(String... value) {
        addContains(PmifsWorkColumns.DEVICE_LOGIC_NAME, value);
        return this;
    }

    public PmifsWorkSelection deviceLogicNameStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.DEVICE_LOGIC_NAME, value);
        return this;
    }

    public PmifsWorkSelection deviceLogicNameEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.DEVICE_LOGIC_NAME, value);
        return this;
    }

    public PmifsWorkSelection orderByDeviceLogicName(boolean desc) {
        orderBy(PmifsWorkColumns.DEVICE_LOGIC_NAME, desc);
        return this;
    }

    public PmifsWorkSelection orderByDeviceLogicName() {
        orderBy(PmifsWorkColumns.DEVICE_LOGIC_NAME, false);
        return this;
    }

    public PmifsWorkSelection instruct(String... value) {
        addEquals(PmifsWorkColumns.INSTRUCT, value);
        return this;
    }

    public PmifsWorkSelection instructNot(String... value) {
        addNotEquals(PmifsWorkColumns.INSTRUCT, value);
        return this;
    }

    public PmifsWorkSelection instructLike(String... value) {
        addLike(PmifsWorkColumns.INSTRUCT, value);
        return this;
    }

    public PmifsWorkSelection instructContains(String... value) {
        addContains(PmifsWorkColumns.INSTRUCT, value);
        return this;
    }

    public PmifsWorkSelection instructStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.INSTRUCT, value);
        return this;
    }

    public PmifsWorkSelection instructEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.INSTRUCT, value);
        return this;
    }

    public PmifsWorkSelection orderByInstruct(boolean desc) {
        orderBy(PmifsWorkColumns.INSTRUCT, desc);
        return this;
    }

    public PmifsWorkSelection orderByInstruct() {
        orderBy(PmifsWorkColumns.INSTRUCT, false);
        return this;
    }

    public PmifsWorkSelection prepareManCode(String... value) {
        addEquals(PmifsWorkColumns.PREPARE_MAN_CODE, value);
        return this;
    }

    public PmifsWorkSelection prepareManCodeNot(String... value) {
        addNotEquals(PmifsWorkColumns.PREPARE_MAN_CODE, value);
        return this;
    }

    public PmifsWorkSelection prepareManCodeLike(String... value) {
        addLike(PmifsWorkColumns.PREPARE_MAN_CODE, value);
        return this;
    }

    public PmifsWorkSelection prepareManCodeContains(String... value) {
        addContains(PmifsWorkColumns.PREPARE_MAN_CODE, value);
        return this;
    }

    public PmifsWorkSelection prepareManCodeStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.PREPARE_MAN_CODE, value);
        return this;
    }

    public PmifsWorkSelection prepareManCodeEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.PREPARE_MAN_CODE, value);
        return this;
    }

    public PmifsWorkSelection orderByPrepareManCode(boolean desc) {
        orderBy(PmifsWorkColumns.PREPARE_MAN_CODE, desc);
        return this;
    }

    public PmifsWorkSelection orderByPrepareManCode() {
        orderBy(PmifsWorkColumns.PREPARE_MAN_CODE, false);
        return this;
    }

    public PmifsWorkSelection prepareManText(String... value) {
        addEquals(PmifsWorkColumns.PREPARE_MAN_TEXT, value);
        return this;
    }

    public PmifsWorkSelection prepareManTextNot(String... value) {
        addNotEquals(PmifsWorkColumns.PREPARE_MAN_TEXT, value);
        return this;
    }

    public PmifsWorkSelection prepareManTextLike(String... value) {
        addLike(PmifsWorkColumns.PREPARE_MAN_TEXT, value);
        return this;
    }

    public PmifsWorkSelection prepareManTextContains(String... value) {
        addContains(PmifsWorkColumns.PREPARE_MAN_TEXT, value);
        return this;
    }

    public PmifsWorkSelection prepareManTextStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.PREPARE_MAN_TEXT, value);
        return this;
    }

    public PmifsWorkSelection prepareManTextEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.PREPARE_MAN_TEXT, value);
        return this;
    }

    public PmifsWorkSelection orderByPrepareManText(boolean desc) {
        orderBy(PmifsWorkColumns.PREPARE_MAN_TEXT, desc);
        return this;
    }

    public PmifsWorkSelection orderByPrepareManText() {
        orderBy(PmifsWorkColumns.PREPARE_MAN_TEXT, false);
        return this;
    }

    public PmifsWorkSelection workareaText(String... value) {
        addEquals(PmifsWorkColumns.WORKAREA_TEXT, value);
        return this;
    }

    public PmifsWorkSelection workareaTextNot(String... value) {
        addNotEquals(PmifsWorkColumns.WORKAREA_TEXT, value);
        return this;
    }

    public PmifsWorkSelection workareaTextLike(String... value) {
        addLike(PmifsWorkColumns.WORKAREA_TEXT, value);
        return this;
    }

    public PmifsWorkSelection workareaTextContains(String... value) {
        addContains(PmifsWorkColumns.WORKAREA_TEXT, value);
        return this;
    }

    public PmifsWorkSelection workareaTextStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.WORKAREA_TEXT, value);
        return this;
    }

    public PmifsWorkSelection workareaTextEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.WORKAREA_TEXT, value);
        return this;
    }

    public PmifsWorkSelection orderByWorkareaText(boolean desc) {
        orderBy(PmifsWorkColumns.WORKAREA_TEXT, desc);
        return this;
    }

    public PmifsWorkSelection orderByWorkareaText() {
        orderBy(PmifsWorkColumns.WORKAREA_TEXT, false);
        return this;
    }

    public PmifsWorkSelection workareaCode(String... value) {
        addEquals(PmifsWorkColumns.WORKAREA_CODE, value);
        return this;
    }

    public PmifsWorkSelection workareaCodeNot(String... value) {
        addNotEquals(PmifsWorkColumns.WORKAREA_CODE, value);
        return this;
    }

    public PmifsWorkSelection workareaCodeLike(String... value) {
        addLike(PmifsWorkColumns.WORKAREA_CODE, value);
        return this;
    }

    public PmifsWorkSelection workareaCodeContains(String... value) {
        addContains(PmifsWorkColumns.WORKAREA_CODE, value);
        return this;
    }

    public PmifsWorkSelection workareaCodeStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.WORKAREA_CODE, value);
        return this;
    }

    public PmifsWorkSelection workareaCodeEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.WORKAREA_CODE, value);
        return this;
    }

    public PmifsWorkSelection orderByWorkareaCode(boolean desc) {
        orderBy(PmifsWorkColumns.WORKAREA_CODE, desc);
        return this;
    }

    public PmifsWorkSelection orderByWorkareaCode() {
        orderBy(PmifsWorkColumns.WORKAREA_CODE, false);
        return this;
    }

    public PmifsWorkSelection applySTime(Long... value) {
        addEquals(PmifsWorkColumns.APPLY_S_TIME, value);
        return this;
    }

    public PmifsWorkSelection applySTimeNot(Long... value) {
        addNotEquals(PmifsWorkColumns.APPLY_S_TIME, value);
        return this;
    }

    public PmifsWorkSelection applySTimeGt(long value) {
        addGreaterThan(PmifsWorkColumns.APPLY_S_TIME, value);
        return this;
    }

    public PmifsWorkSelection applySTimeGtEq(long value) {
        addGreaterThanOrEquals(PmifsWorkColumns.APPLY_S_TIME, value);
        return this;
    }

    public PmifsWorkSelection applySTimeLt(long value) {
        addLessThan(PmifsWorkColumns.APPLY_S_TIME, value);
        return this;
    }

    public PmifsWorkSelection applySTimeLtEq(long value) {
        addLessThanOrEquals(PmifsWorkColumns.APPLY_S_TIME, value);
        return this;
    }

    public PmifsWorkSelection orderByApplySTime(boolean desc) {
        orderBy(PmifsWorkColumns.APPLY_S_TIME, desc);
        return this;
    }

    public PmifsWorkSelection orderByApplySTime() {
        orderBy(PmifsWorkColumns.APPLY_S_TIME, false);
        return this;
    }

    public PmifsWorkSelection applyFTime(Long... value) {
        addEquals(PmifsWorkColumns.APPLY_F_TIME, value);
        return this;
    }

    public PmifsWorkSelection applyFTimeNot(Long... value) {
        addNotEquals(PmifsWorkColumns.APPLY_F_TIME, value);
        return this;
    }

    public PmifsWorkSelection applyFTimeGt(long value) {
        addGreaterThan(PmifsWorkColumns.APPLY_F_TIME, value);
        return this;
    }

    public PmifsWorkSelection applyFTimeGtEq(long value) {
        addGreaterThanOrEquals(PmifsWorkColumns.APPLY_F_TIME, value);
        return this;
    }

    public PmifsWorkSelection applyFTimeLt(long value) {
        addLessThan(PmifsWorkColumns.APPLY_F_TIME, value);
        return this;
    }

    public PmifsWorkSelection applyFTimeLtEq(long value) {
        addLessThanOrEquals(PmifsWorkColumns.APPLY_F_TIME, value);
        return this;
    }

    public PmifsWorkSelection orderByApplyFTime(boolean desc) {
        orderBy(PmifsWorkColumns.APPLY_F_TIME, desc);
        return this;
    }

    public PmifsWorkSelection orderByApplyFTime() {
        orderBy(PmifsWorkColumns.APPLY_F_TIME, false);
        return this;
    }

    public PmifsWorkSelection planSTime(Long... value) {
        addEquals(PmifsWorkColumns.PLAN_S_TIME, value);
        return this;
    }

    public PmifsWorkSelection planSTimeNot(Long... value) {
        addNotEquals(PmifsWorkColumns.PLAN_S_TIME, value);
        return this;
    }

    public PmifsWorkSelection planSTimeGt(long value) {
        addGreaterThan(PmifsWorkColumns.PLAN_S_TIME, value);
        return this;
    }

    public PmifsWorkSelection planSTimeGtEq(long value) {
        addGreaterThanOrEquals(PmifsWorkColumns.PLAN_S_TIME, value);
        return this;
    }

    public PmifsWorkSelection planSTimeLt(long value) {
        addLessThan(PmifsWorkColumns.PLAN_S_TIME, value);
        return this;
    }

    public PmifsWorkSelection planSTimeLtEq(long value) {
        addLessThanOrEquals(PmifsWorkColumns.PLAN_S_TIME, value);
        return this;
    }

    public PmifsWorkSelection orderByPlanSTime(boolean desc) {
        orderBy(PmifsWorkColumns.PLAN_S_TIME, desc);
        return this;
    }

    public PmifsWorkSelection orderByPlanSTime() {
        orderBy(PmifsWorkColumns.PLAN_S_TIME, false);
        return this;
    }

    public PmifsWorkSelection planFTime(Long... value) {
        addEquals(PmifsWorkColumns.PLAN_F_TIME, value);
        return this;
    }

    public PmifsWorkSelection planFTimeNot(Long... value) {
        addNotEquals(PmifsWorkColumns.PLAN_F_TIME, value);
        return this;
    }

    public PmifsWorkSelection planFTimeGt(long value) {
        addGreaterThan(PmifsWorkColumns.PLAN_F_TIME, value);
        return this;
    }

    public PmifsWorkSelection planFTimeGtEq(long value) {
        addGreaterThanOrEquals(PmifsWorkColumns.PLAN_F_TIME, value);
        return this;
    }

    public PmifsWorkSelection planFTimeLt(long value) {
        addLessThan(PmifsWorkColumns.PLAN_F_TIME, value);
        return this;
    }

    public PmifsWorkSelection planFTimeLtEq(long value) {
        addLessThanOrEquals(PmifsWorkColumns.PLAN_F_TIME, value);
        return this;
    }

    public PmifsWorkSelection orderByPlanFTime(boolean desc) {
        orderBy(PmifsWorkColumns.PLAN_F_TIME, desc);
        return this;
    }

    public PmifsWorkSelection orderByPlanFTime() {
        orderBy(PmifsWorkColumns.PLAN_F_TIME, false);
        return this;
    }

    public PmifsWorkSelection workDetailsText(String... value) {
        addEquals(PmifsWorkColumns.WORK_DETAILS_TEXT, value);
        return this;
    }

    public PmifsWorkSelection workDetailsTextNot(String... value) {
        addNotEquals(PmifsWorkColumns.WORK_DETAILS_TEXT, value);
        return this;
    }

    public PmifsWorkSelection workDetailsTextLike(String... value) {
        addLike(PmifsWorkColumns.WORK_DETAILS_TEXT, value);
        return this;
    }

    public PmifsWorkSelection workDetailsTextContains(String... value) {
        addContains(PmifsWorkColumns.WORK_DETAILS_TEXT, value);
        return this;
    }

    public PmifsWorkSelection workDetailsTextStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.WORK_DETAILS_TEXT, value);
        return this;
    }

    public PmifsWorkSelection workDetailsTextEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.WORK_DETAILS_TEXT, value);
        return this;
    }

    public PmifsWorkSelection orderByWorkDetailsText(boolean desc) {
        orderBy(PmifsWorkColumns.WORK_DETAILS_TEXT, desc);
        return this;
    }

    public PmifsWorkSelection orderByWorkDetailsText() {
        orderBy(PmifsWorkColumns.WORK_DETAILS_TEXT, false);
        return this;
    }

    public PmifsWorkSelection workDetailsCode(String... value) {
        addEquals(PmifsWorkColumns.WORK_DETAILS_CODE, value);
        return this;
    }

    public PmifsWorkSelection workDetailsCodeNot(String... value) {
        addNotEquals(PmifsWorkColumns.WORK_DETAILS_CODE, value);
        return this;
    }

    public PmifsWorkSelection workDetailsCodeLike(String... value) {
        addLike(PmifsWorkColumns.WORK_DETAILS_CODE, value);
        return this;
    }

    public PmifsWorkSelection workDetailsCodeContains(String... value) {
        addContains(PmifsWorkColumns.WORK_DETAILS_CODE, value);
        return this;
    }

    public PmifsWorkSelection workDetailsCodeStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.WORK_DETAILS_CODE, value);
        return this;
    }

    public PmifsWorkSelection workDetailsCodeEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.WORK_DETAILS_CODE, value);
        return this;
    }

    public PmifsWorkSelection orderByWorkDetailsCode(boolean desc) {
        orderBy(PmifsWorkColumns.WORK_DETAILS_CODE, desc);
        return this;
    }

    public PmifsWorkSelection orderByWorkDetailsCode() {
        orderBy(PmifsWorkColumns.WORK_DETAILS_CODE, false);
        return this;
    }

    public PmifsWorkSelection workDoneText(String... value) {
        addEquals(PmifsWorkColumns.WORK_DONE_TEXT, value);
        return this;
    }

    public PmifsWorkSelection workDoneTextNot(String... value) {
        addNotEquals(PmifsWorkColumns.WORK_DONE_TEXT, value);
        return this;
    }

    public PmifsWorkSelection workDoneTextLike(String... value) {
        addLike(PmifsWorkColumns.WORK_DONE_TEXT, value);
        return this;
    }

    public PmifsWorkSelection workDoneTextContains(String... value) {
        addContains(PmifsWorkColumns.WORK_DONE_TEXT, value);
        return this;
    }

    public PmifsWorkSelection workDoneTextStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.WORK_DONE_TEXT, value);
        return this;
    }

    public PmifsWorkSelection workDoneTextEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.WORK_DONE_TEXT, value);
        return this;
    }

    public PmifsWorkSelection orderByWorkDoneText(boolean desc) {
        orderBy(PmifsWorkColumns.WORK_DONE_TEXT, desc);
        return this;
    }

    public PmifsWorkSelection orderByWorkDoneText() {
        orderBy(PmifsWorkColumns.WORK_DONE_TEXT, false);
        return this;
    }

    public PmifsWorkSelection workDoneCode(String... value) {
        addEquals(PmifsWorkColumns.WORK_DONE_CODE, value);
        return this;
    }

    public PmifsWorkSelection workDoneCodeNot(String... value) {
        addNotEquals(PmifsWorkColumns.WORK_DONE_CODE, value);
        return this;
    }

    public PmifsWorkSelection workDoneCodeLike(String... value) {
        addLike(PmifsWorkColumns.WORK_DONE_CODE, value);
        return this;
    }

    public PmifsWorkSelection workDoneCodeContains(String... value) {
        addContains(PmifsWorkColumns.WORK_DONE_CODE, value);
        return this;
    }

    public PmifsWorkSelection workDoneCodeStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.WORK_DONE_CODE, value);
        return this;
    }

    public PmifsWorkSelection workDoneCodeEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.WORK_DONE_CODE, value);
        return this;
    }

    public PmifsWorkSelection orderByWorkDoneCode(boolean desc) {
        orderBy(PmifsWorkColumns.WORK_DONE_CODE, desc);
        return this;
    }

    public PmifsWorkSelection orderByWorkDoneCode() {
        orderBy(PmifsWorkColumns.WORK_DONE_CODE, false);
        return this;
    }

    public PmifsWorkSelection workNote(String... value) {
        addEquals(PmifsWorkColumns.WORK_NOTE, value);
        return this;
    }

    public PmifsWorkSelection workNoteNot(String... value) {
        addNotEquals(PmifsWorkColumns.WORK_NOTE, value);
        return this;
    }

    public PmifsWorkSelection workNoteLike(String... value) {
        addLike(PmifsWorkColumns.WORK_NOTE, value);
        return this;
    }

    public PmifsWorkSelection workNoteContains(String... value) {
        addContains(PmifsWorkColumns.WORK_NOTE, value);
        return this;
    }

    public PmifsWorkSelection workNoteStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.WORK_NOTE, value);
        return this;
    }

    public PmifsWorkSelection workNoteEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.WORK_NOTE, value);
        return this;
    }

    public PmifsWorkSelection orderByWorkNote(boolean desc) {
        orderBy(PmifsWorkColumns.WORK_NOTE, desc);
        return this;
    }

    public PmifsWorkSelection orderByWorkNote() {
        orderBy(PmifsWorkColumns.WORK_NOTE, false);
        return this;
    }

    public PmifsWorkSelection operatorText(String... value) {
        addEquals(PmifsWorkColumns.OPERATOR_TEXT, value);
        return this;
    }

    public PmifsWorkSelection operatorTextNot(String... value) {
        addNotEquals(PmifsWorkColumns.OPERATOR_TEXT, value);
        return this;
    }

    public PmifsWorkSelection operatorTextLike(String... value) {
        addLike(PmifsWorkColumns.OPERATOR_TEXT, value);
        return this;
    }

    public PmifsWorkSelection operatorTextContains(String... value) {
        addContains(PmifsWorkColumns.OPERATOR_TEXT, value);
        return this;
    }

    public PmifsWorkSelection operatorTextStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.OPERATOR_TEXT, value);
        return this;
    }

    public PmifsWorkSelection operatorTextEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.OPERATOR_TEXT, value);
        return this;
    }

    public PmifsWorkSelection orderByOperatorText(boolean desc) {
        orderBy(PmifsWorkColumns.OPERATOR_TEXT, desc);
        return this;
    }

    public PmifsWorkSelection orderByOperatorText() {
        orderBy(PmifsWorkColumns.OPERATOR_TEXT, false);
        return this;
    }

    public PmifsWorkSelection operatorCode(String... value) {
        addEquals(PmifsWorkColumns.OPERATOR_CODE, value);
        return this;
    }

    public PmifsWorkSelection operatorCodeNot(String... value) {
        addNotEquals(PmifsWorkColumns.OPERATOR_CODE, value);
        return this;
    }

    public PmifsWorkSelection operatorCodeLike(String... value) {
        addLike(PmifsWorkColumns.OPERATOR_CODE, value);
        return this;
    }

    public PmifsWorkSelection operatorCodeContains(String... value) {
        addContains(PmifsWorkColumns.OPERATOR_CODE, value);
        return this;
    }

    public PmifsWorkSelection operatorCodeStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.OPERATOR_CODE, value);
        return this;
    }

    public PmifsWorkSelection operatorCodeEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.OPERATOR_CODE, value);
        return this;
    }

    public PmifsWorkSelection orderByOperatorCode(boolean desc) {
        orderBy(PmifsWorkColumns.OPERATOR_CODE, desc);
        return this;
    }

    public PmifsWorkSelection orderByOperatorCode() {
        orderBy(PmifsWorkColumns.OPERATOR_CODE, false);
        return this;
    }

    public PmifsWorkSelection operationStartTime(Long... value) {
        addEquals(PmifsWorkColumns.OPERATION_START_TIME, value);
        return this;
    }

    public PmifsWorkSelection operationStartTimeNot(Long... value) {
        addNotEquals(PmifsWorkColumns.OPERATION_START_TIME, value);
        return this;
    }

    public PmifsWorkSelection operationStartTimeGt(long value) {
        addGreaterThan(PmifsWorkColumns.OPERATION_START_TIME, value);
        return this;
    }

    public PmifsWorkSelection operationStartTimeGtEq(long value) {
        addGreaterThanOrEquals(PmifsWorkColumns.OPERATION_START_TIME, value);
        return this;
    }

    public PmifsWorkSelection operationStartTimeLt(long value) {
        addLessThan(PmifsWorkColumns.OPERATION_START_TIME, value);
        return this;
    }

    public PmifsWorkSelection operationStartTimeLtEq(long value) {
        addLessThanOrEquals(PmifsWorkColumns.OPERATION_START_TIME, value);
        return this;
    }

    public PmifsWorkSelection orderByOperationStartTime(boolean desc) {
        orderBy(PmifsWorkColumns.OPERATION_START_TIME, desc);
        return this;
    }

    public PmifsWorkSelection orderByOperationStartTime() {
        orderBy(PmifsWorkColumns.OPERATION_START_TIME, false);
        return this;
    }

    public PmifsWorkSelection operationEndTime(Long... value) {
        addEquals(PmifsWorkColumns.OPERATION_END_TIME, value);
        return this;
    }

    public PmifsWorkSelection operationEndTimeNot(Long... value) {
        addNotEquals(PmifsWorkColumns.OPERATION_END_TIME, value);
        return this;
    }

    public PmifsWorkSelection operationEndTimeGt(long value) {
        addGreaterThan(PmifsWorkColumns.OPERATION_END_TIME, value);
        return this;
    }

    public PmifsWorkSelection operationEndTimeGtEq(long value) {
        addGreaterThanOrEquals(PmifsWorkColumns.OPERATION_END_TIME, value);
        return this;
    }

    public PmifsWorkSelection operationEndTimeLt(long value) {
        addLessThan(PmifsWorkColumns.OPERATION_END_TIME, value);
        return this;
    }

    public PmifsWorkSelection operationEndTimeLtEq(long value) {
        addLessThanOrEquals(PmifsWorkColumns.OPERATION_END_TIME, value);
        return this;
    }

    public PmifsWorkSelection orderByOperationEndTime(boolean desc) {
        orderBy(PmifsWorkColumns.OPERATION_END_TIME, desc);
        return this;
    }

    public PmifsWorkSelection orderByOperationEndTime() {
        orderBy(PmifsWorkColumns.OPERATION_END_TIME, false);
        return this;
    }

    public PmifsWorkSelection orderReceiveTime(Long... value) {
        addEquals(PmifsWorkColumns.ORDER_RECEIVE_TIME, value);
        return this;
    }

    public PmifsWorkSelection orderReceiveTimeNot(Long... value) {
        addNotEquals(PmifsWorkColumns.ORDER_RECEIVE_TIME, value);
        return this;
    }

    public PmifsWorkSelection orderReceiveTimeGt(long value) {
        addGreaterThan(PmifsWorkColumns.ORDER_RECEIVE_TIME, value);
        return this;
    }

    public PmifsWorkSelection orderReceiveTimeGtEq(long value) {
        addGreaterThanOrEquals(PmifsWorkColumns.ORDER_RECEIVE_TIME, value);
        return this;
    }

    public PmifsWorkSelection orderReceiveTimeLt(long value) {
        addLessThan(PmifsWorkColumns.ORDER_RECEIVE_TIME, value);
        return this;
    }

    public PmifsWorkSelection orderReceiveTimeLtEq(long value) {
        addLessThanOrEquals(PmifsWorkColumns.ORDER_RECEIVE_TIME, value);
        return this;
    }

    public PmifsWorkSelection orderByOrderReceiveTime(boolean desc) {
        orderBy(PmifsWorkColumns.ORDER_RECEIVE_TIME, desc);
        return this;
    }

    public PmifsWorkSelection orderByOrderReceiveTime() {
        orderBy(PmifsWorkColumns.ORDER_RECEIVE_TIME, false);
        return this;
    }

    public PmifsWorkSelection arriveTime(Long... value) {
        addEquals(PmifsWorkColumns.ARRIVE_TIME, value);
        return this;
    }

    public PmifsWorkSelection arriveTimeNot(Long... value) {
        addNotEquals(PmifsWorkColumns.ARRIVE_TIME, value);
        return this;
    }

    public PmifsWorkSelection arriveTimeGt(long value) {
        addGreaterThan(PmifsWorkColumns.ARRIVE_TIME, value);
        return this;
    }

    public PmifsWorkSelection arriveTimeGtEq(long value) {
        addGreaterThanOrEquals(PmifsWorkColumns.ARRIVE_TIME, value);
        return this;
    }

    public PmifsWorkSelection arriveTimeLt(long value) {
        addLessThan(PmifsWorkColumns.ARRIVE_TIME, value);
        return this;
    }

    public PmifsWorkSelection arriveTimeLtEq(long value) {
        addLessThanOrEquals(PmifsWorkColumns.ARRIVE_TIME, value);
        return this;
    }

    public PmifsWorkSelection orderByArriveTime(boolean desc) {
        orderBy(PmifsWorkColumns.ARRIVE_TIME, desc);
        return this;
    }

    public PmifsWorkSelection orderByArriveTime() {
        orderBy(PmifsWorkColumns.ARRIVE_TIME, false);
        return this;
    }

    public PmifsWorkSelection intCustomerNo(String... value) {
        addEquals(PmifsWorkColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public PmifsWorkSelection intCustomerNoNot(String... value) {
        addNotEquals(PmifsWorkColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public PmifsWorkSelection intCustomerNoLike(String... value) {
        addLike(PmifsWorkColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public PmifsWorkSelection intCustomerNoContains(String... value) {
        addContains(PmifsWorkColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public PmifsWorkSelection intCustomerNoStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public PmifsWorkSelection intCustomerNoEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public PmifsWorkSelection orderByIntCustomerNo(boolean desc) {
        orderBy(PmifsWorkColumns.INT_CUSTOMER_NO, desc);
        return this;
    }

    public PmifsWorkSelection orderByIntCustomerNo() {
        orderBy(PmifsWorkColumns.INT_CUSTOMER_NO, false);
        return this;
    }

    public PmifsWorkSelection statusBackstage(String... value) {
        addEquals(PmifsWorkColumns.STATUS_BACKSTAGE, value);
        return this;
    }

    public PmifsWorkSelection statusBackstageNot(String... value) {
        addNotEquals(PmifsWorkColumns.STATUS_BACKSTAGE, value);
        return this;
    }

    public PmifsWorkSelection statusBackstageLike(String... value) {
        addLike(PmifsWorkColumns.STATUS_BACKSTAGE, value);
        return this;
    }

    public PmifsWorkSelection statusBackstageContains(String... value) {
        addContains(PmifsWorkColumns.STATUS_BACKSTAGE, value);
        return this;
    }

    public PmifsWorkSelection statusBackstageStartsWith(String... value) {
        addStartsWith(PmifsWorkColumns.STATUS_BACKSTAGE, value);
        return this;
    }

    public PmifsWorkSelection statusBackstageEndsWith(String... value) {
        addEndsWith(PmifsWorkColumns.STATUS_BACKSTAGE, value);
        return this;
    }

    public PmifsWorkSelection orderByStatusBackstage(boolean desc) {
        orderBy(PmifsWorkColumns.STATUS_BACKSTAGE, desc);
        return this;
    }

    public PmifsWorkSelection orderByStatusBackstage() {
        orderBy(PmifsWorkColumns.STATUS_BACKSTAGE, false);
        return this;
    }

    public PmifsWorkSelection status(PmifsWorkStatus... value) {
        addEquals(PmifsWorkColumns.STATUS, value);
        return this;
    }

    public PmifsWorkSelection statusNot(PmifsWorkStatus... value) {
        addNotEquals(PmifsWorkColumns.STATUS, value);
        return this;
    }


    public PmifsWorkSelection orderByStatus(boolean desc) {
        orderBy(PmifsWorkColumns.STATUS, desc);
        return this;
    }

    public PmifsWorkSelection orderByStatus() {
        orderBy(PmifsWorkColumns.STATUS, false);
        return this;
    }

    public PmifsWorkSelection lastModified(Long... value) {
        addEquals(PmifsWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmifsWorkSelection lastModifiedNot(Long... value) {
        addNotEquals(PmifsWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmifsWorkSelection lastModifiedGt(long value) {
        addGreaterThan(PmifsWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmifsWorkSelection lastModifiedGtEq(long value) {
        addGreaterThanOrEquals(PmifsWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmifsWorkSelection lastModifiedLt(long value) {
        addLessThan(PmifsWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmifsWorkSelection lastModifiedLtEq(long value) {
        addLessThanOrEquals(PmifsWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmifsWorkSelection orderByLastModified(boolean desc) {
        orderBy(PmifsWorkColumns.LAST_MODIFIED, desc);
        return this;
    }

    public PmifsWorkSelection orderByLastModified() {
        orderBy(PmifsWorkColumns.LAST_MODIFIED, false);
        return this;
    }

    public PmifsWorkSelection deletePending(Boolean value) {
        addEquals(PmifsWorkColumns.DELETE_PENDING, toObjectArray(value));
        return this;
    }

    public PmifsWorkSelection orderByDeletePending(boolean desc) {
        orderBy(PmifsWorkColumns.DELETE_PENDING, desc);
        return this;
    }

    public PmifsWorkSelection orderByDeletePending() {
        orderBy(PmifsWorkColumns.DELETE_PENDING, false);
        return this;
    }

    public PmifsWorkSelection uploadPending(Boolean value) {
        addEquals(PmifsWorkColumns.UPLOAD_PENDING, toObjectArray(value));
        return this;
    }

    public PmifsWorkSelection orderByUploadPending(boolean desc) {
        orderBy(PmifsWorkColumns.UPLOAD_PENDING, desc);
        return this;
    }

    public PmifsWorkSelection orderByUploadPending() {
        orderBy(PmifsWorkColumns.UPLOAD_PENDING, false);
        return this;
    }
}
