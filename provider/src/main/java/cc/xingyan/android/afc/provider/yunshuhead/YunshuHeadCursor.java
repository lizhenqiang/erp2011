package cc.xingyan.android.afc.provider.yunshuhead;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code yunshu_head} table.
 */
public class YunshuHeadCursor extends AbstractCursor implements YunshuHeadModel {
    public YunshuHeadCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(YunshuHeadColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code transport_task_id} value.
     * Can be {@code null}.
     */
    public String getTransportTaskId() {
        String res = getStringOrNull(YunshuHeadColumns.TRANSPORT_TASK_ID);
        return res;
    }

    /**
     * Get the {@code transport_task_state} value.
     * Can be {@code null}.
     */
    public String getTransportTaskState() {
        String res = getStringOrNull(YunshuHeadColumns.TRANSPORT_TASK_STATE);
        return res;
    }

    /**
     * Get the {@code create_date} value.
     * Can be {@code null}.
     */
    public Long getCreateDate() {
        Long res = getLongOrNull(YunshuHeadColumns.CREATE_DATE);
        return res;
    }

    /**
     * Get the {@code maintenance_type_id} value.
     * Can be {@code null}.
     */
    public String getMaintenanceTypeId() {
        String res = getStringOrNull(YunshuHeadColumns.MAINTENANCE_TYPE_ID);
        return res;
    }

    /**
     * Get the {@code plan_by} value.
     * Can be {@code null}.
     */
    public String getPlanBy() {
        String res = getStringOrNull(YunshuHeadColumns.PLAN_BY);
        return res;
    }

    /**
     * Get the {@code pack_number} value.
     * Can be {@code null}.
     */
    public String getPackNumber() {
        String res = getStringOrNull(YunshuHeadColumns.PACK_NUMBER);
        return res;
    }

    /**
     * Get the {@code send_warehouse_no} value.
     * Can be {@code null}.
     */
    public String getSendWarehouseNo() {
        String res = getStringOrNull(YunshuHeadColumns.SEND_WAREHOUSE_NO);
        return res;
    }

    /**
     * Get the {@code receive_warehouse_no} value.
     * Can be {@code null}.
     */
    public String getReceiveWarehouseNo() {
        String res = getStringOrNull(YunshuHeadColumns.RECEIVE_WAREHOUSE_NO);
        return res;
    }

    /**
     * Get the {@code transport_task_type} value.
     * Can be {@code null}.
     */
    public String getTransportTaskType() {
        String res = getStringOrNull(YunshuHeadColumns.TRANSPORT_TASK_TYPE);
        return res;
    }

    /**
     * Get the {@code keep_col1} value.
     * Can be {@code null}.
     */
    public String getKeepCol1() {
        String res = getStringOrNull(YunshuHeadColumns.KEEP_COL1);
        return res;
    }

    /**
     * Get the {@code keep_col2} value.
     * Can be {@code null}.
     */
    public String getKeepCol2() {
        String res = getStringOrNull(YunshuHeadColumns.KEEP_COL2);
        return res;
    }

    /**
     * Get the {@code keep_col3} value.
     * Can be {@code null}.
     */
    public String getKeepCol3() {
        String res = getStringOrNull(YunshuHeadColumns.KEEP_COL3);
        return res;
    }
}
