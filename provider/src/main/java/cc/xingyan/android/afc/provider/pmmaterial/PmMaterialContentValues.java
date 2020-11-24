package cc.xingyan.android.afc.provider.pmmaterial;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code pm_material} table.
 */
public class PmMaterialContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return PmMaterialColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  PmMaterialSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  PmMaterialSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public PmMaterialContentValues putPmOrderId(String value) {
        mContentValues.put(PmMaterialColumns.PM_ORDER_ID, value);
        return this;
    }

    public PmMaterialContentValues putPmOrderIdNull() {
        mContentValues.putNull(PmMaterialColumns.PM_ORDER_ID);
        return this;
    }

    public PmMaterialContentValues putMaterialOrderId(String value) {
        mContentValues.put(PmMaterialColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public PmMaterialContentValues putMaterialOrderIdNull() {
        mContentValues.putNull(PmMaterialColumns.MATERIAL_ORDER_ID);
        return this;
    }

    public PmMaterialContentValues putUser(String value) {
        mContentValues.put(PmMaterialColumns.USER, value);
        return this;
    }

    public PmMaterialContentValues putUserNull() {
        mContentValues.putNull(PmMaterialColumns.USER);
        return this;
    }

    public PmMaterialContentValues putDepartment(String value) {
        mContentValues.put(PmMaterialColumns.DEPARTMENT, value);
        return this;
    }

    public PmMaterialContentValues putDepartmentNull() {
        mContentValues.putNull(PmMaterialColumns.DEPARTMENT);
        return this;
    }

    public PmMaterialContentValues putIntCustomerNo(String value) {
        mContentValues.put(PmMaterialColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public PmMaterialContentValues putIntCustomerNoNull() {
        mContentValues.putNull(PmMaterialColumns.INT_CUSTOMER_NO);
        return this;
    }

    public PmMaterialContentValues putEnterDate(Long value) {
        mContentValues.put(PmMaterialColumns.ENTER_DATE, value);
        return this;
    }

    public PmMaterialContentValues putEnterDateNull() {
        mContentValues.putNull(PmMaterialColumns.ENTER_DATE);
        return this;
    }

    public PmMaterialContentValues putDueDate(Long value) {
        mContentValues.put(PmMaterialColumns.DUE_DATE, value);
        return this;
    }

    public PmMaterialContentValues putDueDateNull() {
        mContentValues.putNull(PmMaterialColumns.DUE_DATE);
        return this;
    }

    public PmMaterialContentValues putGuid(String value) {
        mContentValues.put(PmMaterialColumns.GUID, value);
        return this;
    }

    public PmMaterialContentValues putGuidNull() {
        mContentValues.putNull(PmMaterialColumns.GUID);
        return this;
    }
}
