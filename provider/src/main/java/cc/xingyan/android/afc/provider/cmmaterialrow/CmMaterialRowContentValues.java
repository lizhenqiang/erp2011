package cc.xingyan.android.afc.provider.cmmaterialrow;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code cm_material_row} table.
 */
public class CmMaterialRowContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return CmMaterialRowColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  CmMaterialRowSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  CmMaterialRowSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public CmMaterialRowContentValues putCmOrderId(String value) {
        mContentValues.put(CmMaterialRowColumns.CM_ORDER_ID, value);
        return this;
    }

    public CmMaterialRowContentValues putCmOrderIdNull() {
        mContentValues.putNull(CmMaterialRowColumns.CM_ORDER_ID);
        return this;
    }

    public CmMaterialRowContentValues putMaterialOrderId(String value) {
        mContentValues.put(CmMaterialRowColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public CmMaterialRowContentValues putMaterialOrderIdNull() {
        mContentValues.putNull(CmMaterialRowColumns.MATERIAL_ORDER_ID);
        return this;
    }

    public CmMaterialRowContentValues putMaterialRow(String value) {
        mContentValues.put(CmMaterialRowColumns.MATERIAL_ROW, value);
        return this;
    }

    public CmMaterialRowContentValues putMaterialRowNull() {
        mContentValues.putNull(CmMaterialRowColumns.MATERIAL_ROW);
        return this;
    }

    public CmMaterialRowContentValues putMaterialId(String value) {
        mContentValues.put(CmMaterialRowColumns.MATERIAL_ID, value);
        return this;
    }

    public CmMaterialRowContentValues putMaterialIdNull() {
        mContentValues.putNull(CmMaterialRowColumns.MATERIAL_ID);
        return this;
    }

    public CmMaterialRowContentValues putMaterialDescription(String value) {
        mContentValues.put(CmMaterialRowColumns.MATERIAL_DESCRIPTION, value);
        return this;
    }

    public CmMaterialRowContentValues putMaterialDescriptionNull() {
        mContentValues.putNull(CmMaterialRowColumns.MATERIAL_DESCRIPTION);
        return this;
    }

    public CmMaterialRowContentValues putDueDate(Long value) {
        mContentValues.put(CmMaterialRowColumns.DUE_DATE, value);
        return this;
    }

    public CmMaterialRowContentValues putDueDateNull() {
        mContentValues.putNull(CmMaterialRowColumns.DUE_DATE);
        return this;
    }

    public CmMaterialRowContentValues putMaterialCount(Integer value) {
        mContentValues.put(CmMaterialRowColumns.MATERIAL_COUNT, value);
        return this;
    }

    public CmMaterialRowContentValues putMaterialCountNull() {
        mContentValues.putNull(CmMaterialRowColumns.MATERIAL_COUNT);
        return this;
    }

    public CmMaterialRowContentValues putGuid(String value) {
        mContentValues.put(CmMaterialRowColumns.GUID, value);
        return this;
    }

    public CmMaterialRowContentValues putGuidNull() {
        mContentValues.putNull(CmMaterialRowColumns.GUID);
        return this;
    }
}
