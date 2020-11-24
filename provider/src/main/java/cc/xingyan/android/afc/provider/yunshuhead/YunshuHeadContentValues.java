package cc.xingyan.android.afc.provider.yunshuhead;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code yunshu_head} table.
 */
public class YunshuHeadContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return YunshuHeadColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  YunshuHeadSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  YunshuHeadSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public YunshuHeadContentValues putTransportTaskId(String value) {
        mContentValues.put(YunshuHeadColumns.TRANSPORT_TASK_ID, value);
        return this;
    }

    public YunshuHeadContentValues putTransportTaskIdNull() {
        mContentValues.putNull(YunshuHeadColumns.TRANSPORT_TASK_ID);
        return this;
    }

    public YunshuHeadContentValues putTransportTaskState(String value) {
        mContentValues.put(YunshuHeadColumns.TRANSPORT_TASK_STATE, value);
        return this;
    }

    public YunshuHeadContentValues putTransportTaskStateNull() {
        mContentValues.putNull(YunshuHeadColumns.TRANSPORT_TASK_STATE);
        return this;
    }

    public YunshuHeadContentValues putCreateDate(Long value) {
        mContentValues.put(YunshuHeadColumns.CREATE_DATE, value);
        return this;
    }

    public YunshuHeadContentValues putCreateDateNull() {
        mContentValues.putNull(YunshuHeadColumns.CREATE_DATE);
        return this;
    }

    public YunshuHeadContentValues putMaintenanceTypeId(String value) {
        mContentValues.put(YunshuHeadColumns.MAINTENANCE_TYPE_ID, value);
        return this;
    }

    public YunshuHeadContentValues putMaintenanceTypeIdNull() {
        mContentValues.putNull(YunshuHeadColumns.MAINTENANCE_TYPE_ID);
        return this;
    }

    public YunshuHeadContentValues putPlanBy(String value) {
        mContentValues.put(YunshuHeadColumns.PLAN_BY, value);
        return this;
    }

    public YunshuHeadContentValues putPlanByNull() {
        mContentValues.putNull(YunshuHeadColumns.PLAN_BY);
        return this;
    }

    public YunshuHeadContentValues putPackNumber(String value) {
        mContentValues.put(YunshuHeadColumns.PACK_NUMBER, value);
        return this;
    }

    public YunshuHeadContentValues putPackNumberNull() {
        mContentValues.putNull(YunshuHeadColumns.PACK_NUMBER);
        return this;
    }

    public YunshuHeadContentValues putSendWarehouseNo(String value) {
        mContentValues.put(YunshuHeadColumns.SEND_WAREHOUSE_NO, value);
        return this;
    }

    public YunshuHeadContentValues putSendWarehouseNoNull() {
        mContentValues.putNull(YunshuHeadColumns.SEND_WAREHOUSE_NO);
        return this;
    }

    public YunshuHeadContentValues putReceiveWarehouseNo(String value) {
        mContentValues.put(YunshuHeadColumns.RECEIVE_WAREHOUSE_NO, value);
        return this;
    }

    public YunshuHeadContentValues putReceiveWarehouseNoNull() {
        mContentValues.putNull(YunshuHeadColumns.RECEIVE_WAREHOUSE_NO);
        return this;
    }

    public YunshuHeadContentValues putTransportTaskType(String value) {
        mContentValues.put(YunshuHeadColumns.TRANSPORT_TASK_TYPE, value);
        return this;
    }

    public YunshuHeadContentValues putTransportTaskTypeNull() {
        mContentValues.putNull(YunshuHeadColumns.TRANSPORT_TASK_TYPE);
        return this;
    }

    public YunshuHeadContentValues putKeepCol1(String value) {
        mContentValues.put(YunshuHeadColumns.KEEP_COL1, value);
        return this;
    }

    public YunshuHeadContentValues putKeepCol1Null() {
        mContentValues.putNull(YunshuHeadColumns.KEEP_COL1);
        return this;
    }

    public YunshuHeadContentValues putKeepCol2(String value) {
        mContentValues.put(YunshuHeadColumns.KEEP_COL2, value);
        return this;
    }

    public YunshuHeadContentValues putKeepCol2Null() {
        mContentValues.putNull(YunshuHeadColumns.KEEP_COL2);
        return this;
    }

    public YunshuHeadContentValues putKeepCol3(String value) {
        mContentValues.put(YunshuHeadColumns.KEEP_COL3, value);
        return this;
    }

    public YunshuHeadContentValues putKeepCol3Null() {
        mContentValues.putNull(YunshuHeadColumns.KEEP_COL3);
        return this;
    }
}
