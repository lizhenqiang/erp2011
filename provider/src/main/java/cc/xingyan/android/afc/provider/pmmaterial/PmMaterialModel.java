package cc.xingyan.android.afc.provider.pmmaterial;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code pm_material} table.
 */
public interface PmMaterialModel extends BaseModel {

    /**
     * Get the {@code pm_order_id} value.
     * Can be {@code null}.
     */
    String getPmOrderId();

    /**
     * Get the {@code material_order_id} value.
     * Can be {@code null}.
     */
    String getMaterialOrderId();

    /**
     * Get the {@code user} value.
     * Can be {@code null}.
     */
    String getUser();

    /**
     * Get the {@code department} value.
     * Can be {@code null}.
     */
    String getDepartment();

    /**
     * Get the {@code int_customer_no} value.
     * Can be {@code null}.
     */
    String getIntCustomerNo();

    /**
     * Get the {@code enter_date} value.
     * Can be {@code null}.
     */
    Long getEnterDate();

    /**
     * Get the {@code due_date} value.
     * Can be {@code null}.
     */
    Long getDueDate();

    /**
     * Get the {@code guid} value.
     * Can be {@code null}.
     */
    String getGuid();
}
