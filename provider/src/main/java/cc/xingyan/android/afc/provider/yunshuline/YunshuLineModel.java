package cc.xingyan.android.afc.provider.yunshuline;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code yunshu_line} table.
 */
public interface YunshuLineModel extends BaseModel {

    /**
     * Get the {@code transport_task_id} value.
     * Can be {@code null}.
     */
    String getTransportTaskId();

    /**
     * Get the {@code line_no} value.
     * Can be {@code null}.
     */
    String getLineNo();

    /**
     * Get the {@code part_no} value.
     * Can be {@code null}.
     */
    String getPartNo();

    /**
     * Get the {@code part_name} value.
     * Can be {@code null}.
     */
    String getPartName();

    /**
     * Get the {@code quantity} value.
     * Can be {@code null}.
     */
    String getQuantity();

    /**
     * Get the {@code unit} value.
     * Can be {@code null}.
     */
    String getUnit();

    /**
     * Get the {@code lot_batch_no} value.
     * Can be {@code null}.
     */
    String getLotBatchNo();

    /**
     * Get the {@code serial_no} value.
     * Can be {@code null}.
     */
    String getSerialNo();

    /**
     * Get the {@code format} value.
     * Can be {@code null}.
     */
    String getFormat();

    /**
     * Get the {@code line_type} value.
     * Can be {@code null}.
     */
    String getLineType();

    /**
     * Get the {@code line_mark} value.
     * Can be {@code null}.
     */
    Long getLineMark();

    /**
     * Get the {@code keep_col1} value.
     * Can be {@code null}.
     */
    String getKeepCol1();

    /**
     * Get the {@code keep_col2} value.
     * Can be {@code null}.
     */
    String getKeepCol2();
}
