package cc.xingyan.android.afc.provider.cmmaterial;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code cm_material} table.
 */
public class CmMaterialContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return CmMaterialColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  CmMaterialSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  CmMaterialSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public CmMaterialContentValues putCmOrderId(String value) {
        mContentValues.put(CmMaterialColumns.CM_ORDER_ID, value);
        return this;
    }

    public CmMaterialContentValues putCmOrderIdNull() {
        mContentValues.putNull(CmMaterialColumns.CM_ORDER_ID);
        return this;
    }

    public CmMaterialContentValues putMaterialOrderId(String value) {
        mContentValues.put(CmMaterialColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public CmMaterialContentValues putMaterialOrderIdNull() {
        mContentValues.putNull(CmMaterialColumns.MATERIAL_ORDER_ID);
        return this;
    }

    public CmMaterialContentValues putUser(String value) {
        mContentValues.put(CmMaterialColumns.USER, value);
        return this;
    }

    public CmMaterialContentValues putUserNull() {
        mContentValues.putNull(CmMaterialColumns.USER);
        return this;
    }

    public CmMaterialContentValues putDepartment(String value) {
        mContentValues.put(CmMaterialColumns.DEPARTMENT, value);
        return this;
    }

    public CmMaterialContentValues putDepartmentNull() {
        mContentValues.putNull(CmMaterialColumns.DEPARTMENT);
        return this;
    }

    public CmMaterialContentValues putIntCustomerNo(String value) {
        mContentValues.put(CmMaterialColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public CmMaterialContentValues putIntCustomerNoNull() {
        mContentValues.putNull(CmMaterialColumns.INT_CUSTOMER_NO);
        return this;
    }

    public CmMaterialContentValues putEnterDate(Long value) {
        mContentValues.put(CmMaterialColumns.ENTER_DATE, value);
        return this;
    }

    public CmMaterialContentValues putEnterDateNull() {
        mContentValues.putNull(CmMaterialColumns.ENTER_DATE);
        return this;
    }

    public CmMaterialContentValues putDueDate(Long value) {
        mContentValues.put(CmMaterialColumns.DUE_DATE, value);
        return this;
    }

    public CmMaterialContentValues putDueDateNull() {
        mContentValues.putNull(CmMaterialColumns.DUE_DATE);
        return this;
    }

    public CmMaterialContentValues putGuid(String value) {
        mContentValues.put(CmMaterialColumns.GUID, value);
        return this;
    }

    public CmMaterialContentValues putGuidNull() {
        mContentValues.putNull(CmMaterialColumns.GUID);
        return this;
    }
}
