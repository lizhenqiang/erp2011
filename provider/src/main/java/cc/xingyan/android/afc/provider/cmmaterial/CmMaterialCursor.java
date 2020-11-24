package cc.xingyan.android.afc.provider.cmmaterial;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code cm_material} table.
 */
public class CmMaterialCursor extends AbstractCursor implements CmMaterialModel {
    public CmMaterialCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(CmMaterialColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code cm_order_id} value.
     * Can be {@code null}.
     */
    public String getCmOrderId() {
        String res = getStringOrNull(CmMaterialColumns.CM_ORDER_ID);
        return res;
    }

    /**
     * Get the {@code material_order_id} value.
     * Can be {@code null}.
     */
    public String getMaterialOrderId() {
        String res = getStringOrNull(CmMaterialColumns.MATERIAL_ORDER_ID);
        return res;
    }

    /**
     * Get the {@code user} value.
     * Can be {@code null}.
     */
    public String getUser() {
        String res = getStringOrNull(CmMaterialColumns.USER);
        return res;
    }

    /**
     * Get the {@code department} value.
     * Can be {@code null}.
     */
    public String getDepartment() {
        String res = getStringOrNull(CmMaterialColumns.DEPARTMENT);
        return res;
    }

    /**
     * Get the {@code int_customer_no} value.
     * Can be {@code null}.
     */
    public String getIntCustomerNo() {
        String res = getStringOrNull(CmMaterialColumns.INT_CUSTOMER_NO);
        return res;
    }

    /**
     * Get the {@code enter_date} value.
     * Can be {@code null}.
     */
    public Long getEnterDate() {
        Long res = getLongOrNull(CmMaterialColumns.ENTER_DATE);
        return res;
    }

    /**
     * Get the {@code due_date} value.
     * Can be {@code null}.
     */
    public Long getDueDate() {
        Long res = getLongOrNull(CmMaterialColumns.DUE_DATE);
        return res;
    }

    /**
     * Get the {@code guid} value.
     * Can be {@code null}.
     */
    public String getGuid() {
        String res = getStringOrNull(CmMaterialColumns.GUID);
        return res;
    }
}
