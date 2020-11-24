package cc.xingyan.android.afc.provider.pmmaterial;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code pm_material} table.
 */
public class PmMaterialSelection extends AbstractSelection<PmMaterialSelection> {
    @Override
    protected Uri baseUri() {
        return PmMaterialColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PmMaterialCursor} object, which is positioned before the first entry, or null.
     */
    public PmMaterialCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PmMaterialCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public PmMaterialCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PmMaterialCursor} object, which is positioned before the first entry, or null.
     */
    public PmMaterialCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PmMaterialCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public PmMaterialCursor query(Context context) {
        return query(context, null);
    }


    public PmMaterialSelection id(long... value) {
        addEquals("pm_material." + PmMaterialColumns._ID, toObjectArray(value));
        return this;
    }

    public PmMaterialSelection idNot(long... value) {
        addNotEquals("pm_material." + PmMaterialColumns._ID, toObjectArray(value));
        return this;
    }

    public PmMaterialSelection orderById(boolean desc) {
        orderBy("pm_material." + PmMaterialColumns._ID, desc);
        return this;
    }

    public PmMaterialSelection orderById() {
        return orderById(false);
    }

    public PmMaterialSelection pmOrderId(String... value) {
        addEquals(PmMaterialColumns.PM_ORDER_ID, value);
        return this;
    }

    public PmMaterialSelection pmOrderIdNot(String... value) {
        addNotEquals(PmMaterialColumns.PM_ORDER_ID, value);
        return this;
    }

    public PmMaterialSelection pmOrderIdLike(String... value) {
        addLike(PmMaterialColumns.PM_ORDER_ID, value);
        return this;
    }

    public PmMaterialSelection pmOrderIdContains(String... value) {
        addContains(PmMaterialColumns.PM_ORDER_ID, value);
        return this;
    }

    public PmMaterialSelection pmOrderIdStartsWith(String... value) {
        addStartsWith(PmMaterialColumns.PM_ORDER_ID, value);
        return this;
    }

    public PmMaterialSelection pmOrderIdEndsWith(String... value) {
        addEndsWith(PmMaterialColumns.PM_ORDER_ID, value);
        return this;
    }

    public PmMaterialSelection orderByPmOrderId(boolean desc) {
        orderBy(PmMaterialColumns.PM_ORDER_ID, desc);
        return this;
    }

    public PmMaterialSelection orderByPmOrderId() {
        orderBy(PmMaterialColumns.PM_ORDER_ID, false);
        return this;
    }

    public PmMaterialSelection materialOrderId(String... value) {
        addEquals(PmMaterialColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public PmMaterialSelection materialOrderIdNot(String... value) {
        addNotEquals(PmMaterialColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public PmMaterialSelection materialOrderIdLike(String... value) {
        addLike(PmMaterialColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public PmMaterialSelection materialOrderIdContains(String... value) {
        addContains(PmMaterialColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public PmMaterialSelection materialOrderIdStartsWith(String... value) {
        addStartsWith(PmMaterialColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public PmMaterialSelection materialOrderIdEndsWith(String... value) {
        addEndsWith(PmMaterialColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public PmMaterialSelection orderByMaterialOrderId(boolean desc) {
        orderBy(PmMaterialColumns.MATERIAL_ORDER_ID, desc);
        return this;
    }

    public PmMaterialSelection orderByMaterialOrderId() {
        orderBy(PmMaterialColumns.MATERIAL_ORDER_ID, false);
        return this;
    }

    public PmMaterialSelection user(String... value) {
        addEquals(PmMaterialColumns.USER, value);
        return this;
    }

    public PmMaterialSelection userNot(String... value) {
        addNotEquals(PmMaterialColumns.USER, value);
        return this;
    }

    public PmMaterialSelection userLike(String... value) {
        addLike(PmMaterialColumns.USER, value);
        return this;
    }

    public PmMaterialSelection userContains(String... value) {
        addContains(PmMaterialColumns.USER, value);
        return this;
    }

    public PmMaterialSelection userStartsWith(String... value) {
        addStartsWith(PmMaterialColumns.USER, value);
        return this;
    }

    public PmMaterialSelection userEndsWith(String... value) {
        addEndsWith(PmMaterialColumns.USER, value);
        return this;
    }

    public PmMaterialSelection orderByUser(boolean desc) {
        orderBy(PmMaterialColumns.USER, desc);
        return this;
    }

    public PmMaterialSelection orderByUser() {
        orderBy(PmMaterialColumns.USER, false);
        return this;
    }

    public PmMaterialSelection department(String... value) {
        addEquals(PmMaterialColumns.DEPARTMENT, value);
        return this;
    }

    public PmMaterialSelection departmentNot(String... value) {
        addNotEquals(PmMaterialColumns.DEPARTMENT, value);
        return this;
    }

    public PmMaterialSelection departmentLike(String... value) {
        addLike(PmMaterialColumns.DEPARTMENT, value);
        return this;
    }

    public PmMaterialSelection departmentContains(String... value) {
        addContains(PmMaterialColumns.DEPARTMENT, value);
        return this;
    }

    public PmMaterialSelection departmentStartsWith(String... value) {
        addStartsWith(PmMaterialColumns.DEPARTMENT, value);
        return this;
    }

    public PmMaterialSelection departmentEndsWith(String... value) {
        addEndsWith(PmMaterialColumns.DEPARTMENT, value);
        return this;
    }

    public PmMaterialSelection orderByDepartment(boolean desc) {
        orderBy(PmMaterialColumns.DEPARTMENT, desc);
        return this;
    }

    public PmMaterialSelection orderByDepartment() {
        orderBy(PmMaterialColumns.DEPARTMENT, false);
        return this;
    }

    public PmMaterialSelection intCustomerNo(String... value) {
        addEquals(PmMaterialColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public PmMaterialSelection intCustomerNoNot(String... value) {
        addNotEquals(PmMaterialColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public PmMaterialSelection intCustomerNoLike(String... value) {
        addLike(PmMaterialColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public PmMaterialSelection intCustomerNoContains(String... value) {
        addContains(PmMaterialColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public PmMaterialSelection intCustomerNoStartsWith(String... value) {
        addStartsWith(PmMaterialColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public PmMaterialSelection intCustomerNoEndsWith(String... value) {
        addEndsWith(PmMaterialColumns.INT_CUSTOMER_NO, value);
        return this;
    }

    public PmMaterialSelection orderByIntCustomerNo(boolean desc) {
        orderBy(PmMaterialColumns.INT_CUSTOMER_NO, desc);
        return this;
    }

    public PmMaterialSelection orderByIntCustomerNo() {
        orderBy(PmMaterialColumns.INT_CUSTOMER_NO, false);
        return this;
    }

    public PmMaterialSelection enterDate(Long... value) {
        addEquals(PmMaterialColumns.ENTER_DATE, value);
        return this;
    }

    public PmMaterialSelection enterDateNot(Long... value) {
        addNotEquals(PmMaterialColumns.ENTER_DATE, value);
        return this;
    }

    public PmMaterialSelection enterDateGt(long value) {
        addGreaterThan(PmMaterialColumns.ENTER_DATE, value);
        return this;
    }

    public PmMaterialSelection enterDateGtEq(long value) {
        addGreaterThanOrEquals(PmMaterialColumns.ENTER_DATE, value);
        return this;
    }

    public PmMaterialSelection enterDateLt(long value) {
        addLessThan(PmMaterialColumns.ENTER_DATE, value);
        return this;
    }

    public PmMaterialSelection enterDateLtEq(long value) {
        addLessThanOrEquals(PmMaterialColumns.ENTER_DATE, value);
        return this;
    }

    public PmMaterialSelection orderByEnterDate(boolean desc) {
        orderBy(PmMaterialColumns.ENTER_DATE, desc);
        return this;
    }

    public PmMaterialSelection orderByEnterDate() {
        orderBy(PmMaterialColumns.ENTER_DATE, false);
        return this;
    }

    public PmMaterialSelection dueDate(Long... value) {
        addEquals(PmMaterialColumns.DUE_DATE, value);
        return this;
    }

    public PmMaterialSelection dueDateNot(Long... value) {
        addNotEquals(PmMaterialColumns.DUE_DATE, value);
        return this;
    }

    public PmMaterialSelection dueDateGt(long value) {
        addGreaterThan(PmMaterialColumns.DUE_DATE, value);
        return this;
    }

    public PmMaterialSelection dueDateGtEq(long value) {
        addGreaterThanOrEquals(PmMaterialColumns.DUE_DATE, value);
        return this;
    }

    public PmMaterialSelection dueDateLt(long value) {
        addLessThan(PmMaterialColumns.DUE_DATE, value);
        return this;
    }

    public PmMaterialSelection dueDateLtEq(long value) {
        addLessThanOrEquals(PmMaterialColumns.DUE_DATE, value);
        return this;
    }

    public PmMaterialSelection orderByDueDate(boolean desc) {
        orderBy(PmMaterialColumns.DUE_DATE, desc);
        return this;
    }

    public PmMaterialSelection orderByDueDate() {
        orderBy(PmMaterialColumns.DUE_DATE, false);
        return this;
    }

    public PmMaterialSelection guid(String... value) {
        addEquals(PmMaterialColumns.GUID, value);
        return this;
    }

    public PmMaterialSelection guidNot(String... value) {
        addNotEquals(PmMaterialColumns.GUID, value);
        return this;
    }

    public PmMaterialSelection guidLike(String... value) {
        addLike(PmMaterialColumns.GUID, value);
        return this;
    }

    public PmMaterialSelection guidContains(String... value) {
        addContains(PmMaterialColumns.GUID, value);
        return this;
    }

    public PmMaterialSelection guidStartsWith(String... value) {
        addStartsWith(PmMaterialColumns.GUID, value);
        return this;
    }

    public PmMaterialSelection guidEndsWith(String... value) {
        addEndsWith(PmMaterialColumns.GUID, value);
        return this;
    }

    public PmMaterialSelection orderByGuid(boolean desc) {
        orderBy(PmMaterialColumns.GUID, desc);
        return this;
    }

    public PmMaterialSelection orderByGuid() {
        orderBy(PmMaterialColumns.GUID, false);
        return this;
    }
}
