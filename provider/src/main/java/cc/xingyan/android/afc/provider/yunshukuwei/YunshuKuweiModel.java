package cc.xingyan.android.afc.provider.yunshukuwei;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code yunshu_kuwei} table.
 */
public interface YunshuKuweiModel extends BaseModel {

    /**
     * Get the {@code transport_task_id} value.
     * Can be {@code null}.
     */
    String getTransportTaskId();

    /**
     * Get the {@code warehouse_no} value.
     * Can be {@code null}.
     */
    String getWarehouseNo();

    /**
     * Get the {@code warehouse_name} value.
     * Can be {@code null}.
     */
    String getWarehouseName();

    /**
     * Get the {@code warehouse_type} value.
     * Can be {@code null}.
     */
    String getWarehouseType();
}
