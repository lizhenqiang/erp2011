package cc.xingyan.android.afc.provider.cmmaterialrow;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code cm_material_row} table.
 */
public class CmMaterialRowCursor extends AbstractCursor implements CmMaterialRowModel {
    public CmMaterialRowCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(CmMaterialRowColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code cm_order_id} value.
     * Can be {@code null}.
     */
    public String getCmOrderId() {
        String res = getStringOrNull(CmMaterialRowColumns.CM_ORDER_ID);
        return res;
    }

    /**
     * Get the {@code material_order_id} value.
     * Can be {@code null}.
     */
    public String getMaterialOrderId() {
        String res = getStringOrNull(CmMaterialRowColumns.MATERIAL_ORDER_ID);
        return res;
    }

    /**
     * Get the {@code material_row} value.
     * Can be {@code null}.
     */
    public String getMaterialRow() {
        String res = getStringOrNull(CmMaterialRowColumns.MATERIAL_ROW);
        return res;
    }

    /**
     * Get the {@code material_id} value.
     * Can be {@code null}.
     */
    public String getMaterialId() {
        String res = getStringOrNull(CmMaterialRowColumns.MATERIAL_ID);
        return res;
    }

    /**
     * Get the {@code material_description} value.
     * Can be {@code null}.
     */
    public String getMaterialDescription() {
        String res = getStringOrNull(CmMaterialRowColumns.MATERIAL_DESCRIPTION);
        return res;
    }

    /**
     * Get the {@code due_date} value.
     * Can be {@code null}.
     */
    public Long getDueDate() {
        Long res = getLongOrNull(CmMaterialRowColumns.DUE_DATE);
        return res;
    }

    /**
     * Get the {@code material_count} value.
     * Can be {@code null}.
     */
    public Integer getMaterialCount() {
        Integer res = getIntegerOrNull(CmMaterialRowColumns.MATERIAL_COUNT);
        return res;
    }

    /**
     * Get the {@code guid} value.
     * Can be {@code null}.
     */
    public String getGuid() {
        String res = getStringOrNull(CmMaterialRowColumns.GUID);
        return res;
    }
}
