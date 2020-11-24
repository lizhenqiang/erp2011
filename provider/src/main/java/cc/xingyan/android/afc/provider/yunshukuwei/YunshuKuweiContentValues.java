package cc.xingyan.android.afc.provider.yunshukuwei;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code yunshu_kuwei} table.
 */
public class YunshuKuweiContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return YunshuKuweiColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  YunshuKuweiSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  YunshuKuweiSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public YunshuKuweiContentValues putTransportTaskId(String value) {
        mContentValues.put(YunshuKuweiColumns.TRANSPORT_TASK_ID, value);
        return this;
    }

    public YunshuKuweiContentValues putTransportTaskIdNull() {
        mContentValues.putNull(YunshuKuweiColumns.TRANSPORT_TASK_ID);
        return this;
    }

    public YunshuKuweiContentValues putWarehouseNo(String value) {
        mContentValues.put(YunshuKuweiColumns.WAREHOUSE_NO, value);
        return this;
    }

    public YunshuKuweiContentValues putWarehouseNoNull() {
        mContentValues.putNull(YunshuKuweiColumns.WAREHOUSE_NO);
        return this;
    }

    public YunshuKuweiContentValues putWarehouseName(String value) {
        mContentValues.put(YunshuKuweiColumns.WAREHOUSE_NAME, value);
        return this;
    }

    public YunshuKuweiContentValues putWarehouseNameNull() {
        mContentValues.putNull(YunshuKuweiColumns.WAREHOUSE_NAME);
        return this;
    }

    public YunshuKuweiContentValues putWarehouseType(String value) {
        mContentValues.put(YunshuKuweiColumns.WAREHOUSE_TYPE, value);
        return this;
    }

    public YunshuKuweiContentValues putWarehouseTypeNull() {
        mContentValues.putNull(YunshuKuweiColumns.WAREHOUSE_TYPE);
        return this;
    }
}
