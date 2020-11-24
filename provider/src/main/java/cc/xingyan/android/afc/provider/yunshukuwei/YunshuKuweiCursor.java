package cc.xingyan.android.afc.provider.yunshukuwei;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code yunshu_kuwei} table.
 */
public class YunshuKuweiCursor extends AbstractCursor implements YunshuKuweiModel {
    public YunshuKuweiCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(YunshuKuweiColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code transport_task_id} value.
     * Can be {@code null}.
     */
    public String getTransportTaskId() {
        String res = getStringOrNull(YunshuKuweiColumns.TRANSPORT_TASK_ID);
        return res;
    }

    /**
     * Get the {@code warehouse_no} value.
     * Can be {@code null}.
     */
    public String getWarehouseNo() {
        String res = getStringOrNull(YunshuKuweiColumns.WAREHOUSE_NO);
        return res;
    }

    /**
     * Get the {@code warehouse_name} value.
     * Can be {@code null}.
     */
    public String getWarehouseName() {
        String res = getStringOrNull(YunshuKuweiColumns.WAREHOUSE_NAME);
        return res;
    }

    /**
     * Get the {@code warehouse_type} value.
     * Can be {@code null}.
     */
    public String getWarehouseType() {
        String res = getStringOrNull(YunshuKuweiColumns.WAREHOUSE_TYPE);
        return res;
    }
}
