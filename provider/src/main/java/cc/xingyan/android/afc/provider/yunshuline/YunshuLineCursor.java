package cc.xingyan.android.afc.provider.yunshuline;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code yunshu_line} table.
 */
public class YunshuLineCursor extends AbstractCursor implements YunshuLineModel {
    public YunshuLineCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(YunshuLineColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code transport_task_id} value.
     * Can be {@code null}.
     */
    public String getTransportTaskId() {
        String res = getStringOrNull(YunshuLineColumns.TRANSPORT_TASK_ID);
        return res;
    }

    /**
     * Get the {@code line_no} value.
     * Can be {@code null}.
     */
    public String getLineNo() {
        String res = getStringOrNull(YunshuLineColumns.LINE_NO);
        return res;
    }

    /**
     * Get the {@code part_no} value.
     * Can be {@code null}.
     */
    public String getPartNo() {
        String res = getStringOrNull(YunshuLineColumns.PART_NO);
        return res;
    }

    /**
     * Get the {@code part_name} value.
     * Can be {@code null}.
     */
    public String getPartName() {
        String res = getStringOrNull(YunshuLineColumns.PART_NAME);
        return res;
    }

    /**
     * Get the {@code quantity} value.
     * Can be {@code null}.
     */
    public String getQuantity() {
        String res = getStringOrNull(YunshuLineColumns.QUANTITY);
        return res;
    }

    /**
     * Get the {@code unit} value.
     * Can be {@code null}.
     */
    public String getUnit() {
        String res = getStringOrNull(YunshuLineColumns.UNIT);
        return res;
    }

    /**
     * Get the {@code lot_batch_no} value.
     * Can be {@code null}.
     */
    public String getLotBatchNo() {
        String res = getStringOrNull(YunshuLineColumns.LOT_BATCH_NO);
        return res;
    }

    /**
     * Get the {@code serial_no} value.
     * Can be {@code null}.
     */
    public String getSerialNo() {
        String res = getStringOrNull(YunshuLineColumns.SERIAL_NO);
        return res;
    }

    /**
     * Get the {@code format} value.
     * Can be {@code null}.
     */
    public String getFormat() {
        String res = getStringOrNull(YunshuLineColumns.FORMAT);
        return res;
    }

    /**
     * Get the {@code line_type} value.
     * Can be {@code null}.
     */
    public String getLineType() {
        String res = getStringOrNull(YunshuLineColumns.LINE_TYPE);
        return res;
    }

    /**
     * Get the {@code line_mark} value.
     * Can be {@code null}.
     */
    public Long getLineMark() {
        Long res = getLongOrNull(YunshuLineColumns.LINE_MARK);
        return res;
    }

    /**
     * Get the {@code keep_col1} value.
     * Can be {@code null}.
     */
    public String getKeepCol1() {
        String res = getStringOrNull(YunshuLineColumns.KEEP_COL1);
        return res;
    }

    /**
     * Get the {@code keep_col2} value.
     * Can be {@code null}.
     */
    public String getKeepCol2() {
        String res = getStringOrNull(YunshuLineColumns.KEEP_COL2);
        return res;
    }
}
