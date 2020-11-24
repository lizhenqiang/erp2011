package cc.xingyan.android.afc.provider.cmmaterial;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code cm_material} table.
 */
public class CmMaterialSelection extends AbstractSelection<CmMaterialSelection> {
    @Override
    protected Uri baseUri() {
        return CmMaterialColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CmMaterialCursor} object, which is positioned before the first entry, or null.
     */
    public CmMaterialCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CmMaterialCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public CmMaterialCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CmMaterialCursor} object, which is positioned before the first entry, or null.
     */
    public CmMaterialCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CmMaterialCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public CmMaterialCursor query(Context context) {
        return query(context, null);
    }


    public CmMaterialSelection id(long... value) {
        addEquals("cm_material." + CmMaterialColumns._ID, toObjectArray(value));
        return this;
    }

    public CmMaterialSelection idNot(long... value) {
        addNotEquals("cm_material." + CmMaterialColumns._ID, toObjectArray(value));
        return this;
    }

    public CmMaterialSelection orderById(boolean desc) {
        orderBy("cm_material." + CmMaterialColumns._ID, desc);
        return this;
    }

    public CmMaterialSelection orderById() {
        return orderById(false);
    }

    public CmMaterialSelection cmOrderId(String... value) {
        addEquals(CmMaterialColumns.CM_ORDER_ID, value);
        return this;
    }

    public CmMaterialSelection cmOrderIdNot(String... value) {
        addNotEquals(CmMaterialColumns.CM_ORDER_ID, value);
        return this;
    }

    public CmMaterialSelection cmOrderIdLike(String... value) {
        addLike(CmMaterialColumns.CM_ORDER_ID, value);
        return this;
    }

    public CmMaterialSelection cmOrderIdContains(String... value) {
        addContains(CmMaterialColumns.CM_ORDER_ID, value);
        return this;
    }

    public CmMaterialSelection cmOrderIdStartsWith(String... value) {
        addStartsWith(CmMaterialColumns.CM_ORDER_ID, value);
        return this;
    }

    public CmMaterialSelection cmOrderIdEndsWith(String... value) {
        addEndsWith(CmMaterialColumns.CM_ORDER_ID, value);
        return this;
    }

    public CmMaterialSelection orderByCmOrderId(boolean desc) {
        orderBy(CmMaterialColumns.CM_ORDER_ID, desc);
        return this;
    }

    public CmMaterialSelection orderByCmOrderId() {
        orderBy(CmMaterialColumns.CM_ORDER_ID, false);
        return this;
    }

    public CmMaterialSelection materialOrderId(String... value) {
        addEquals(CmMaterialColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public CmMaterialSelection materialOrderIdNot(String... value) {
        addNotEquals(CmMaterialColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public CmMaterialSelection materialOrderIdLike(String... value) {
        addLike(CmMaterialColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public CmMaterialSelection materialOrderIdContains(String... value) {
        addContains(CmMaterialColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public CmMaterialSelection materialOrderIdStartsWith(String... value) {
        addStartsWith(CmMaterialColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public CmMaterialSelection materialOrderIdEndsWith(String... value) {
        addEndsWith(CmMaterialColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public CmMaterialSelection orderByMaterialOrderId(boolean desc) {
        orderBy(CmMaterialColumns.MATERIAL_ORDER_ID, desc);
        return this;
    }

    public CmMaterialSelection orderByMaterialOrderId() {
        orderBy(CmMaterialColumns.MATERIAL_ORDER_ID, false);
        return this;
    }

    public CmMaterialSelection user(String... value) {
        addEquals(CmMaterialColumns.USER, value);
        return this;
    }

    public CmMaterialSelection userNot(String... value) {
        addNotEquals(CmMaterialColumns.USER, value);
        return this;
    }

    public CmMaterialSelection userLike(String... value) {
        addLike(CmMaterialColumns.USER, value);
        return this;
    }

    public CmMaterialSelection userContains(String... value) {
        addContains(CmMaterialColumns.USER, value);
        return this;
    }

    public CmMaterialSelection userStartsWith(String... value) {
        addStartsWith(CmMaterialColumns.USER, value);
        return this;
    }

    public CmMaterialSelection userEndsWith(String... value) {
        addEndsWith(CmMaterialColumns.USER, value);
        return this;
    }

    public CmMaterialSelection orderByUser(boolean desc) {
        orderBy(CmMaterialColumns.USER, desc);
        return this;
    }

    public CmMaterialSelection orderByUser() {
        orderBy(CmMaterialColumns.USER, false);
        return this;
    }

    public CmMaterialSelection department(String... value) {
        addEquals(CmMaterialColumns.DEPARTMENT, value);
        return this;
    }

    public CmMaterialSelection departmentNot(String... value) {
        addNotEquals(CmMaterialColumns.DEPARTMENT, value);
        return this;
    }

    public CmMaterialSelection departmentLike(String... value) {
        addLike(CmMaterialColumns.DEPARTMENT, value);
        return this;
    }

    public CmMaterialSelection departmentContains(String... value) {
        addContains(CmMaterialColumns.DEPARTMENT, value);
        return this;
    }

    public CmMaterialSelection departmentStartsWith(String... value) {
        addStartsWith(CmMaterialColumns.DEPARTMENT, value);
        return this;
    }

    public CmMaterialSelection departmentEndsWith(String... value) {
        addEndsWith(CmMaterialColumns.DEPARTMENT, value);
        return this;
    }

    public CmMaterialSelection orderByDepartment(boolean desc) {
        orderBy(CmMaterialColumns.DEPARTMENT, desc);
        return this;
    }

    public CmMaterialSelection orderByDepartment() {
        orderBy(CmMaterialColumns.DEPARTMENT, false);
        return this;
    }

    public CmMaterialSelection intCustomerNo(String... value) {
        addEquals(CmMaterialColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public CmMaterialSelection intCustomerNoNot(String... value) {
        addNotEquals(CmMaterialColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public CmMaterialSelection intCustomerNoLike(String... value) {
        addLike(CmMaterialColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public CmMaterialSelection intCustomerNoContains(String... value) {
        addContains(CmMaterialColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public CmMaterialSelection intCustomerNoStartsWith(String... value) {
        addStartsWith(CmMaterialColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public CmMaterialSelection intCustomerNoEndsWith(String... value) {
        addEndsWith(CmMaterialColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public CmMaterialSelection orderByIntCustomerNo(boolean desc) {
        orderBy(CmMaterialColumns.INT_CUSTOMER_NO, desc);
        return this;
    }

    public CmMaterialSelection orderByIntCustomerNo() {
        orderBy(CmMaterialColumns.INT_CUSTOMER_NO, false);
        return this;
    }

    public CmMaterialSelection enterDate(Long... value) {
        addEquals(CmMaterialColumns.ENTER_DATE, value);
        return this;
    }

    public CmMaterialSelection enterDateNot(Long... value) {
        addNotEquals(CmMaterialColumns.ENTER_DATE, value);
        return this;
    }

    public CmMaterialSelection enterDateGt(long value) {
        addGreaterThan(CmMaterialColumns.ENTER_DATE, value);
        return this;
    }

    public CmMaterialSelection enterDateGtEq(long value) {
        addGreaterThanOrEquals(CmMaterialColumns.ENTER_DATE, value);
        return this;
    }

    public CmMaterialSelection enterDateLt(long value) {
        addLessThan(CmMaterialColumns.ENTER_DATE, value);
        return this;
    }

    public CmMaterialSelection enterDateLtEq(long value) {
        addLessThanOrEquals(CmMaterialColumns.ENTER_DATE, value);
        return this;
    }

    public CmMaterialSelection orderByEnterDate(boolean desc) {
        orderBy(CmMaterialColumns.ENTER_DATE, desc);
        return this;
    }

    public CmMaterialSelection orderByEnterDate() {
        orderBy(CmMaterialColumns.ENTER_DATE, false);
        return this;
    }

    public CmMaterialSelection dueDate(Long... value) {
        addEquals(CmMaterialColumns.DUE_DATE, value);
        return this;
    }

    public CmMaterialSelection dueDateNot(Long... value) {
        addNotEquals(CmMaterialColumns.DUE_DATE, value);
        return this;
    }

    public CmMaterialSelection dueDateGt(long value) {
        addGreaterThan(CmMaterialColumns.DUE_DATE, value);
        return this;
    }

    public CmMaterialSelection dueDateGtEq(long value) {
        addGreaterThanOrEquals(CmMaterialColumns.DUE_DATE, value);
        return this;
    }

    public CmMaterialSelection dueDateLt(long value) {
        addLessThan(CmMaterialColumns.DUE_DATE, value);
        return this;
    }

    public CmMaterialSelection dueDateLtEq(long value) {
        addLessThanOrEquals(CmMaterialColumns.DUE_DATE, value);
        return this;
    }

    public CmMaterialSelection orderByDueDate(boolean desc) {
        orderBy(CmMaterialColumns.DUE_DATE, desc);
        return this;
    }

    public CmMaterialSelection orderByDueDate() {
        orderBy(CmMaterialColumns.DUE_DATE, false);
        return this;
    }

    public CmMaterialSelection guid(String... value) {
        addEquals(CmMaterialColumns.GUID, value);
        return this;
    }

    public CmMaterialSelection guidNot(String... value) {
        addNotEquals(CmMaterialColumns.GUID, value);
        return this;
    }

    public CmMaterialSelection guidLike(String... value) {
        addLike(CmMaterialColumns.GUID, value);
        return this;
    }

    public CmMaterialSelection guidContains(String... value) {
        addContains(CmMaterialColumns.GUID, value);
        return this;
    }

    public CmMaterialSelection guidStartsWith(String... value) {
        addStartsWith(CmMaterialColumns.GUID, value);
        return this;
    }

    public CmMaterialSelection guidEndsWith(String... value) {
        addEndsWith(CmMaterialColumns.GUID, value);
        return this;
    }

    public CmMaterialSelection orderByGuid(boolean desc) {
        orderBy(CmMaterialColumns.GUID, desc);
        return this;
    }

    public CmMaterialSelection orderByGuid() {
        orderBy(CmMaterialColumns.GUID, false);
        return this;
    }
}
