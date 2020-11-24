package cc.xingyan.android.afc.provider.pmmaterial;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code pm_material} table.
 */
public class PmMaterialCursor extends AbstractCursor implements PmMaterialModel {
    public PmMaterialCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(PmMaterialColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code pm_order_id} value.
     * Can be {@code null}.
     */
    public String getPmOrderId() {
        String res = getStringOrNull(PmMaterialColumns.PM_ORDER_ID);
        return res;
    }

    /**
     * Get the {@code material_order_id} value.
     * Can be {@code null}.
     */
    public String getMaterialOrderId() {
        String res = getStringOrNull(PmMaterialColumns.MATERIAL_ORDER_ID);
        return res;
    }

    /**
     * Get the {@code user} value.
     * Can be {@code null}.
     */
    public String getUser() {
        String res = getStringOrNull(PmMaterialColumns.USER);
        return res;
    }

    /**
     * Get the {@code department} value.
     * Can be {@code null}.
     */
    public String getDepartment() {
        String res = getStringOrNull(PmMaterialColumns.DEPARTMENT);
        return res;
    }

    /**
     * Get the {@code int_customer_no} value.
     * Can be {@code null}.
     */
    public String getIntCustomerNo() {
        String res = getStringOrNull(PmMaterialColumns.INT_CUSTOMER_NO);
        return res;
    }

    /**
     * Get the {@code enter_date} value.
     * Can be {@code null}.
     */
    public Long getEnterDate() {
        Long res = getLongOrNull(PmMaterialColumns.ENTER_DATE);
        return res;
    }

    /**
     * Get the {@code due_date} value.
     * Can be {@code null}.
     */
    public Long getDueDate() {
        Long res = getLongOrNull(PmMaterialColumns.DUE_DATE);
        return res;
    }

    /**
     * Get the {@code guid} value.
     * Can be {@code null}.
     */
    public String getGuid() {
        String res = getStringOrNull(PmMaterialColumns.GUID);
        return res;
    }
}
