package cc.xingyan.android.afc.provider.yunshuhead;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code yunshu_head} table.
 */
public class YunshuHeadSelection extends AbstractSelection<YunshuHeadSelection> {
    @Override
    protected Uri baseUri() {
        return YunshuHeadColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code YunshuHeadCursor} object, which is positioned before the first entry, or null.
     */
    public YunshuHeadCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new YunshuHeadCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public YunshuHeadCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code YunshuHeadCursor} object, which is positioned before the first entry, or null.
     */
    public YunshuHeadCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new YunshuHeadCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public YunshuHeadCursor query(Context context) {
        return query(context, null);
    }


    public YunshuHeadSelection id(long... value) {
        addEquals("yunshu_head." + YunshuHeadColumns._ID, toObjectArray(value));
        return this;
    }

    public YunshuHeadSelection idNot(long... value) {
        addNotEquals("yunshu_head." + YunshuHeadColumns._ID, toObjectArray(value));
        return this;
    }

    public YunshuHeadSelection orderById(boolean desc) {
        orderBy("yunshu_head." + YunshuHeadColumns._ID, desc);
        return this;
    }

    public YunshuHeadSelection orderById() {
        return orderById(false);
    }

    public YunshuHeadSelection transportTaskId(String... value) {
        addEquals(YunshuHeadColumns.TRANSPORT_TASK_ID, value);
        return this;
    }

    public YunshuHeadSelection transportTaskIdNot(String... value) {
        addNotEquals(YunshuHeadColumns.TRANSPORT_TASK_ID, value);
        return this;
    }

    public YunshuHeadSelection transportTaskIdLike(String... value) {
        addLike(YunshuHeadColumns.TRANSPORT_TASK_ID, value);
        return this;
    }

    public YunshuHeadSelection transportTaskIdContains(String... value) {
        addContains(YunshuHeadColumns.TRANSPORT_TASK_ID, value);
        return this;
    }

    public YunshuHeadSelection transportTaskIdStartsWith(String... value) {
        addStartsWith(YunshuHeadColumns.TRANSPORT_TASK_ID, value);
        return this;
    }

    public YunshuHeadSelection transportTaskIdEndsWith(String... value) {
        addEndsWith(YunshuHeadColumns.TRANSPORT_TASK_ID, value);
        return this;
    }

    public YunshuHeadSelection orderByTransportTaskId(boolean desc) {
        orderBy(YunshuHeadColumns.TRANSPORT_TASK_ID, desc);
        return this;
    }

    public YunshuHeadSelection orderByTransportTaskId() {
        orderBy(YunshuHeadColumns.TRANSPORT_TASK_ID, false);
        return this;
    }

    public YunshuHeadSelection transportTaskState(String... value) {
        addEquals(YunshuHeadColumns.TRANSPORT_TASK_STATE, value);
        return this;
    }

    public YunshuHeadSelection transportTaskStateNot(String... value) {
        addNotEquals(YunshuHeadColumns.TRANSPORT_TASK_STATE, value);
        return this;
    }

    public YunshuHeadSelection transportTaskStateLike(String... value) {
        addLike(YunshuHeadColumns.TRANSPORT_TASK_STATE, value);
        return this;
    }

    public YunshuHeadSelection transportTaskStateContains(String... value) {
        addContains(YunshuHeadColumns.TRANSPORT_TASK_STATE, value);
        return this;
    }

    public YunshuHeadSelection transportTaskStateStartsWith(String... value) {
        addStartsWith(YunshuHeadColumns.TRANSPORT_TASK_STATE, value);
        return this;
    }

    public YunshuHeadSelection transportTaskStateEndsWith(String... value) {
        addEndsWith(YunshuHeadColumns.TRANSPORT_TASK_STATE, value);
        return this;
    }

    public YunshuHeadSelection orderByTransportTaskState(boolean desc) {
        orderBy(YunshuHeadColumns.TRANSPORT_TASK_STATE, desc);
        return this;
    }

    public YunshuHeadSelection orderByTransportTaskState() {
        orderBy(YunshuHeadColumns.TRANSPORT_TASK_STATE, false);
        return this;
    }

    public YunshuHeadSelection createDate(Long... value) {
        addEquals(YunshuHeadColumns.CREATE_DATE, value);
        return this;
    }

    public YunshuHeadSelection createDateNot(Long... value) {
        addNotEquals(YunshuHeadColumns.CREATE_DATE, value);
        return this;
    }

    public YunshuHeadSelection createDateGt(long value) {
        addGreaterThan(YunshuHeadColumns.CREATE_DATE, value);
        return this;
    }

    public YunshuHeadSelection createDateGtEq(long value) {
        addGreaterThanOrEquals(YunshuHeadColumns.CREATE_DATE, value);
        return this;
    }

    public YunshuHeadSelection createDateLt(long value) {
        addLessThan(YunshuHeadColumns.CREATE_DATE, value);
        return this;
    }

    public YunshuHeadSelection createDateLtEq(long value) {
        addLessThanOrEquals(YunshuHeadColumns.CREATE_DATE, value);
        return this;
    }

    public YunshuHeadSelection orderByCreateDate(boolean desc) {
        orderBy(YunshuHeadColumns.CREATE_DATE, desc);
        return this;
    }

    public YunshuHeadSelection orderByCreateDate() {
        orderBy(YunshuHeadColumns.CREATE_DATE, false);
        return this;
    }

    public YunshuHeadSelection maintenanceTypeId(String... value) {
        addEquals(YunshuHeadColumns.MAINTENANCE_TYPE_ID, value);
        return this;
    }

    public YunshuHeadSelection maintenanceTypeIdNot(String... value) {
        addNotEquals(YunshuHeadColumns.MAINTENANCE_TYPE_ID, value);
        return this;
    }

    public YunshuHeadSelection maintenanceTypeIdLike(String... value) {
        addLike(YunshuHeadColumns.MAINTENANCE_TYPE_ID, value);
        return this;
    }

    public YunshuHeadSelection maintenanceTypeIdContains(String... value) {
        addContains(YunshuHeadColumns.MAINTENANCE_TYPE_ID, value);
        return this;
    }

    public YunshuHeadSelection maintenanceTypeIdStartsWith(String... value) {
        addStartsWith(YunshuHeadColumns.MAINTENANCE_TYPE_ID, value);
        return this;
    }

    public YunshuHeadSelection maintenanceTypeIdEndsWith(String... value) {
        addEndsWith(YunshuHeadColumns.MAINTENANCE_TYPE_ID, value);
        return this;
    }

    public YunshuHeadSelection orderByMaintenanceTypeId(boolean desc) {
        orderBy(YunshuHeadColumns.MAINTENANCE_TYPE_ID, desc);
        return this;
    }

    public YunshuHeadSelection orderByMaintenanceTypeId() {
        orderBy(YunshuHeadColumns.MAINTENANCE_TYPE_ID, false);
        return this;
    }

    public YunshuHeadSelection planBy(String... value) {
        addEquals(YunshuHeadColumns.PLAN_BY, value);
        return this;
    }

    public YunshuHeadSelection planByNot(String... value) {
        addNotEquals(YunshuHeadColumns.PLAN_BY, value);
        return this;
    }

    public YunshuHeadSelection planByLike(String... value) {
        addLike(YunshuHeadColumns.PLAN_BY, value);
        return this;
    }

    public YunshuHeadSelection planByContains(String... value) {
        addContains(YunshuHeadColumns.PLAN_BY, value);
        return this;
    }

    public YunshuHeadSelection planByStartsWith(String... value) {
        addStartsWith(YunshuHeadColumns.PLAN_BY, value);
        return this;
    }

    public YunshuHeadSelection planByEndsWith(String... value) {
        addEndsWith(YunshuHeadColumns.PLAN_BY, value);
        return this;
    }

    public YunshuHeadSelection orderByPlanBy(boolean desc) {
        orderBy(YunshuHeadColumns.PLAN_BY, desc);
        return this;
    }

    public YunshuHeadSelection orderByPlanBy() {
        orderBy(YunshuHeadColumns.PLAN_BY, false);
        return this;
    }

    public YunshuHeadSelection packNumber(String... value) {
        addEquals(YunshuHeadColumns.PACK_NUMBER, value);
        return this;
    }

    public YunshuHeadSelection packNumberNot(String... value) {
        addNotEquals(YunshuHeadColumns.PACK_NUMBER, value);
        return this;
    }

    public YunshuHeadSelection packNumberLike(String... value) {
        addLike(YunshuHeadColumns.PACK_NUMBER, value);
        return this;
    }

    public YunshuHeadSelection packNumberContains(String... value) {
        addContains(YunshuHeadColumns.PACK_NUMBER, value);
        return this;
    }

    public YunshuHeadSelection packNumberStartsWith(String... value) {
        addStartsWith(YunshuHeadColumns.PACK_NUMBER, value);
        return this;
    }

    public YunshuHeadSelection packNumberEndsWith(String... value) {
        addEndsWith(YunshuHeadColumns.PACK_NUMBER, value);
        return this;
    }

    public YunshuHeadSelection orderByPackNumber(boolean desc) {
        orderBy(YunshuHeadColumns.PACK_NUMBER, desc);
        return this;
    }

    public YunshuHeadSelection orderByPackNumber() {
        orderBy(YunshuHeadColumns.PACK_NUMBER, false);
        return this;
    }

    public YunshuHeadSelection sendWarehouseNo(String... value) {
        addEquals(YunshuHeadColumns.SEND_WAREHOUSE_NO, value);
        return this;
    }

    public YunshuHeadSelection sendWarehouseNoNot(String... value) {
        addNotEquals(YunshuHeadColumns.SEND_WAREHOUSE_NO, value);
        return this;
    }

    public YunshuHeadSelection sendWarehouseNoLike(String... value) {
        addLike(YunshuHeadColumns.SEND_WAREHOUSE_NO, value);
        return this;
    }

    public YunshuHeadSelection sendWarehouseNoContains(String... value) {
        addContains(YunshuHeadColumns.SEND_WAREHOUSE_NO, value);
        return this;
    }

    public YunshuHeadSelection sendWarehouseNoStartsWith(String... value) {
        addStartsWith(YunshuHeadColumns.SEND_WAREHOUSE_NO, value);
        return this;
    }

    public YunshuHeadSelection sendWarehouseNoEndsWith(String... value) {
        addEndsWith(YunshuHeadColumns.SEND_WAREHOUSE_NO, value);
        return this;
    }

    public YunshuHeadSelection orderBySendWarehouseNo(boolean desc) {
        orderBy(YunshuHeadColumns.SEND_WAREHOUSE_NO, desc);
        return this;
    }

    public YunshuHeadSelection orderBySendWarehouseNo() {
        orderBy(YunshuHeadColumns.SEND_WAREHOUSE_NO, false);
        return this;
    }

    public YunshuHeadSelection receiveWarehouseNo(String... value) {
        addEquals(YunshuHeadColumns.RECEIVE_WAREHOUSE_NO, value);
        return this;
    }

    public YunshuHeadSelection receiveWarehouseNoNot(String... value) {
        addNotEquals(YunshuHeadColumns.RECEIVE_WAREHOUSE_NO, value);
        return this;
    }

    public YunshuHeadSelection receiveWarehouseNoLike(String... value) {
        addLike(YunshuHeadColumns.RECEIVE_WAREHOUSE_NO, value);
        return this;
    }

    public YunshuHeadSelection receiveWarehouseNoContains(String... value) {
        addContains(YunshuHeadColumns.RECEIVE_WAREHOUSE_NO, value);
        return this;
    }

    public YunshuHeadSelection receiveWarehouseNoStartsWith(String... value) {
        addStartsWith(YunshuHeadColumns.RECEIVE_WAREHOUSE_NO, value);
        return this;
    }

    public YunshuHeadSelection receiveWarehouseNoEndsWith(String... value) {
        addEndsWith(YunshuHeadColumns.RECEIVE_WAREHOUSE_NO, value);
        return this;
    }

    public YunshuHeadSelection orderByReceiveWarehouseNo(boolean desc) {
        orderBy(YunshuHeadColumns.RECEIVE_WAREHOUSE_NO, desc);
        return this;
    }

    public YunshuHeadSelection orderByReceiveWarehouseNo() {
        orderBy(YunshuHeadColumns.RECEIVE_WAREHOUSE_NO, false);
        return this;
    }

    public YunshuHeadSelection transportTaskType(String... value) {
        addEquals(YunshuHeadColumns.TRANSPORT_TASK_TYPE, value);
        return this;
    }

    public YunshuHeadSelection transportTaskTypeNot(String... value) {
        addNotEquals(YunshuHeadColumns.TRANSPORT_TASK_TYPE, value);
        return this;
    }

    public YunshuHeadSelection transportTaskTypeLike(String... value) {
        addLike(YunshuHeadColumns.TRANSPORT_TASK_TYPE, value);
        return this;
    }

    public YunshuHeadSelection transportTaskTypeContains(String... value) {
        addContains(YunshuHeadColumns.TRANSPORT_TASK_TYPE, value);
        return this;
    }

    public YunshuHeadSelection transportTaskTypeStartsWith(String... value) {
        addStartsWith(YunshuHeadColumns.TRANSPORT_TASK_TYPE, value);
        return this;
    }

    public YunshuHeadSelection transportTaskTypeEndsWith(String... value) {
        addEndsWith(YunshuHeadColumns.TRANSPORT_TASK_TYPE, value);
        return this;
    }

    public YunshuHeadSelection orderByTransportTaskType(boolean desc) {
        orderBy(YunshuHeadColumns.TRANSPORT_TASK_TYPE, desc);
        return this;
    }

    public YunshuHeadSelection orderByTransportTaskType() {
        orderBy(YunshuHeadColumns.TRANSPORT_TASK_TYPE, false);
        return this;
    }

    public YunshuHeadSelection keepCol1(String... value) {
        addEquals(YunshuHeadColumns.KEEP_COL1, value);
        return this;
    }

    public YunshuHeadSelection keepCol1Not(String... value) {
        addNotEquals(YunshuHeadColumns.KEEP_COL1, value);
        return this;
    }

    public YunshuHeadSelection keepCol1Like(String... value) {
        addLike(YunshuHeadColumns.KEEP_COL1, value);
        return this;
    }

    public YunshuHeadSelection keepCol1Contains(String... value) {
        addContains(YunshuHeadColumns.KEEP_COL1, value);
        return this;
    }

    public YunshuHeadSelection keepCol1StartsWith(String... value) {
        addStartsWith(YunshuHeadColumns.KEEP_COL1, value);
        return this;
    }

    public YunshuHeadSelection keepCol1EndsWith(String... value) {
        addEndsWith(YunshuHeadColumns.KEEP_COL1, value);
        return this;
    }

    public YunshuHeadSelection orderByKeepCol1(boolean desc) {
        orderBy(YunshuHeadColumns.KEEP_COL1, desc);
        return this;
    }

    public YunshuHeadSelection orderByKeepCol1() {
        orderBy(YunshuHeadColumns.KEEP_COL1, false);
        return this;
    }

    public YunshuHeadSelection keepCol2(String... value) {
        addEquals(YunshuHeadColumns.KEEP_COL2, value);
        return this;
    }

    public YunshuHeadSelection keepCol2Not(String... value) {
        addNotEquals(YunshuHeadColumns.KEEP_COL2, value);
        return this;
    }

    public YunshuHeadSelection keepCol2Like(String... value) {
        addLike(YunshuHeadColumns.KEEP_COL2, value);
        return this;
    }

    public YunshuHeadSelection keepCol2Contains(String... value) {
        addContains(YunshuHeadColumns.KEEP_COL2, value);
        return this;
    }

    public YunshuHeadSelection keepCol2StartsWith(String... value) {
        addStartsWith(YunshuHeadColumns.KEEP_COL2, value);
        return this;
    }

    public YunshuHeadSelection keepCol2EndsWith(String... value) {
        addEndsWith(YunshuHeadColumns.KEEP_COL2, value);
        return this;
    }

    public YunshuHeadSelection orderByKeepCol2(boolean desc) {
        orderBy(YunshuHeadColumns.KEEP_COL2, desc);
        return this;
    }

    public YunshuHeadSelection orderByKeepCol2() {
        orderBy(YunshuHeadColumns.KEEP_COL2, false);
        return this;
    }

    public YunshuHeadSelection keepCol3(String... value) {
        addEquals(YunshuHeadColumns.KEEP_COL3, value);
        return this;
    }

    public YunshuHeadSelection keepCol3Not(String... value) {
        addNotEquals(YunshuHeadColumns.KEEP_COL3, value);
        return this;
    }

    public YunshuHeadSelection keepCol3Like(String... value) {
        addLike(YunshuHeadColumns.KEEP_COL3, value);
        return this;
    }

    public YunshuHeadSelection keepCol3Contains(String... value) {
        addContains(YunshuHeadColumns.KEEP_COL3, value);
        return this;
    }

    public YunshuHeadSelection keepCol3StartsWith(String... value) {
        addStartsWith(YunshuHeadColumns.KEEP_COL3, value);
        return this;
    }

    public YunshuHeadSelection keepCol3EndsWith(String... value) {
        addEndsWith(YunshuHeadColumns.KEEP_COL3, value);
        return this;
    }

    public YunshuHeadSelection orderByKeepCol3(boolean desc) {
        orderBy(YunshuHeadColumns.KEEP_COL3, desc);
        return this;
    }

    public YunshuHeadSelection orderByKeepCol3() {
        orderBy(YunshuHeadColumns.KEEP_COL3, false);
        return this;
    }
}
