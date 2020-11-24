package cc.xingyan.android.afc.provider.pmmaterialrow;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code pm_material_row} table.
 */
public class PmMaterialRowContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return PmMaterialRowColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  PmMaterialRowSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  PmMaterialRowSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public PmMaterialRowContentValues putPmOrderId(String value) {
        mContentValues.put(PmMaterialRowColumns.PM_ORDER_ID, value);
        return this;
    }

    public PmMaterialRowContentValues putPmOrderIdNull() {
        mContentValues.putNull(PmMaterialRowColumns.PM_ORDER_ID);
        return this;
    }

    public PmMaterialRowContentValues putMaterialOrderId(String value) {
        mContentValues.put(PmMaterialRowColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public PmMaterialRowContentValues putMaterialOrderIdNull() {
        mContentValues.putNull(PmMaterialRowColumns.MATERIAL_ORDER_ID);
        return this;
    }

    public PmMaterialRowContentValues putMaterialRow(String value) {
        mContentValues.put(PmMaterialRowColumns.MATERIAL_ROW, value);
        return this;
    }

    public PmMaterialRowContentValues putMaterialRowNull() {
        mContentValues.putNull(PmMaterialRowColumns.MATERIAL_ROW);
        return this;
    }

    public PmMaterialRowContentValues putMaterialId(String value) {
        mContentValues.put(PmMaterialRowColumns.MATERIAL_ID, value);
        return this;
    }

    public PmMaterialRowContentValues putMaterialIdNull() {
        mContentValues.putNull(PmMaterialRowColumns.MATERIAL_ID);
        return this;
    }

    public PmMaterialRowContentValues putMaterialDescription(String value) {
        mContentValues.put(PmMaterialRowColumns.MATERIAL_DESCRIPTION, value);
        return this;
    }

    public PmMaterialRowContentValues putMaterialDescriptionNull() {
        mContentValues.putNull(PmMaterialRowColumns.MATERIAL_DESCRIPTION);
        return this;
    }

    public PmMaterialRowContentValues putDueDate(Long value) {
        mContentValues.put(PmMaterialRowColumns.DUE_DATE, value);
        return this;
    }

    public PmMaterialRowContentValues putDueDateNull() {
        mContentValues.putNull(PmMaterialRowColumns.DUE_DATE);
        return this;
    }

    public PmMaterialRowContentValues putMaterialCount(Integer value) {
        mContentValues.put(PmMaterialRowColumns.MATERIAL_COUNT, value);
        return this;
    }

    public PmMaterialRowContentValues putMaterialCountNull() {
        mContentValues.putNull(PmMaterialRowColumns.MATERIAL_COUNT);
        return this;
    }

    public PmMaterialRowContentValues putGuid(String value) {
        mContentValues.put(PmMaterialRowColumns.GUID, value);
        return this;
    }

    public PmMaterialRowContentValues putGuidNull() {
        mContentValues.putNull(PmMaterialRowColumns.GUID);
        return this;
    }
}
