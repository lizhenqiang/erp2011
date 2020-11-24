package cc.xingyan.android.afc.provider.yunshuhead;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code yunshu_head} table.
 */
public interface YunshuHeadModel extends BaseModel {

    /**
     * Get the {@code transport_task_id} value.
     * Can be {@code null}.
     */
    String getTransportTaskId();

    /**
     * Get the {@code transport_task_state} value.
     * Can be {@code null}.
     */
    String getTransportTaskState();

    /**
     * Get the {@code create_date} value.
     * Can be {@code null}.
     */
    Long getCreateDate();

    /**
     * Get the {@code maintenance_type_id} value.
     * Can be {@code null}.
     */
    String getMaintenanceTypeId();

    /**
     * Get the {@code plan_by} value.
     * Can be {@code null}.
     */
    String getPlanBy();

    /**
     * Get the {@code pack_number} value.
     * Can be {@code null}.
     */
    String getPackNumber();

    /**
     * Get the {@code send_warehouse_no} value.
     * Can be {@code null}.
     */
    String getSendWarehouseNo();

    /**
     * Get the {@code receive_warehouse_no} value.
     * Can be {@code null}.
     */
    String getReceiveWarehouseNo();

    /**
     * Get the {@code transport_task_type} value.
     * Can be {@code null}.
     */
    String getTransportTaskType();

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

    /**
     * Get the {@code keep_col3} value.
     * Can be {@code null}.
     */
    String getKeepCol3();
}
