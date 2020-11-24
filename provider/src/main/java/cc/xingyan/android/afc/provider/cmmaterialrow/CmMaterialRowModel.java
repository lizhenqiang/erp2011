package cc.xingyan.android.afc.provider.cmmaterialrow;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code cm_material_row} table.
 */
public interface CmMaterialRowModel extends BaseModel {

    /**
     * Get the {@code cm_order_id} value.
     * Can be {@code null}.
     */
    String getCmOrderId();

    /**
     * Get the {@code material_order_id} value.
     * Can be {@code null}.
     */
    String getMaterialOrderId();

    /**
     * Get the {@code material_row} value.
     * Can be {@code null}.
     */
    String getMaterialRow();

    /**
     * Get the {@code material_id} value.
     * Can be {@code null}.
     */
    String getMaterialId();

    /**
     * Get the {@code material_description} value.
     * Can be {@code null}.
     */
    String getMaterialDescription();

    /**
     * Get the {@code due_date} value.
     * Can be {@code null}.
     */
    Long getDueDate();

    /**
     * Get the {@code material_count} value.
     * Can be {@code null}.
     */
    Integer getMaterialCount();

    /**
     * Get the {@code guid} value.
     * Can be {@code null}.
     */
    String getGuid();
}
