package cc.xingyan.android.afc.provider.cmmaterialrow;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code cm_material_row} table.
 */
public class CmMaterialRowSelection extends AbstractSelection<CmMaterialRowSelection> {
    @Override
    protected Uri baseUri() {
        return CmMaterialRowColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CmMaterialRowCursor} object, which is positioned before the first entry, or null.
     */
    public CmMaterialRowCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CmMaterialRowCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public CmMaterialRowCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CmMaterialRowCursor} object, which is positioned before the first entry, or null.
     */
    public CmMaterialRowCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CmMaterialRowCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public CmMaterialRowCursor query(Context context) {
        return query(context, null);
    }


    public CmMaterialRowSelection id(long... value) {
        addEquals("cm_material_row." + CmMaterialRowColumns._ID, toObjectArray(value));
        return this;
    }

    public CmMaterialRowSelection idNot(long... value) {
        addNotEquals("cm_material_row." + CmMaterialRowColumns._ID, toObjectArray(value));
        return this;
    }

    public CmMaterialRowSelection orderById(boolean desc) {
        orderBy("cm_material_row." + CmMaterialRowColumns._ID, desc);
        return this;
    }

    public CmMaterialRowSelection orderById() {
        return orderById(false);
    }

    public CmMaterialRowSelection cmOrderId(String... value) {
        addEquals(CmMaterialRowColumns.CM_ORDER_ID, value);
        return this;
    }

    public CmMaterialRowSelection cmOrderIdNot(String... value) {
        addNotEquals(CmMaterialRowColumns.CM_ORDER_ID, value);
        return this;
    }

    public CmMaterialRowSelection cmOrderIdLike(String... value) {
        addLike(CmMaterialRowColumns.CM_ORDER_ID, value);
        return this;
    }

    public CmMaterialRowSelection cmOrderIdContains(String... value) {
        addContains(CmMaterialRowColumns.CM_ORDER_ID, value);
        return this;
    }

    public CmMaterialRowSelection cmOrderIdStartsWith(String... value) {
        addStartsWith(CmMaterialRowColumns.CM_ORDER_ID, value);
        return this;
    }

    public CmMaterialRowSelection cmOrderIdEndsWith(String... value) {
        addEndsWith(CmMaterialRowColumns.CM_ORDER_ID, value);
        return this;
    }

    public CmMaterialRowSelection orderByCmOrderId(boolean desc) {
        orderBy(CmMaterialRowColumns.CM_ORDER_ID, desc);
        return this;
    }

    public CmMaterialRowSelection orderByCmOrderId() {
        orderBy(CmMaterialRowColumns.CM_ORDER_ID, false);
        return this;
    }

    public CmMaterialRowSelection materialOrderId(String... value) {
        addEquals(CmMaterialRowColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public CmMaterialRowSelection materialOrderIdNot(String... value) {
        addNotEquals(CmMaterialRowColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public CmMaterialRowSelection materialOrderIdLike(String... value) {
        addLike(CmMaterialRowColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public CmMaterialRowSelection materialOrderIdContains(String... value) {
        addContains(CmMaterialRowColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public CmMaterialRowSelection materialOrderIdStartsWith(String... value) {
        addStartsWith(CmMaterialRowColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public CmMaterialRowSelection materialOrderIdEndsWith(String... value) {
        addEndsWith(CmMaterialRowColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public CmMaterialRowSelection orderByMaterialOrderId(boolean desc) {
        orderBy(CmMaterialRowColumns.MATERIAL_ORDER_ID, desc);
        return this;
    }

    public CmMaterialRowSelection orderByMaterialOrderId() {
        orderBy(CmMaterialRowColumns.MATERIAL_ORDER_ID, false);
        return this;
    }

    public CmMaterialRowSelection materialRow(String... value) {
        addEquals(CmMaterialRowColumns.MATERIAL_ROW, value);
        return this;
    }

    public CmMaterialRowSelection materialRowNot(String... value) {
        addNotEquals(CmMaterialRowColumns.MATERIAL_ROW, value);
        return this;
    }

    public CmMaterialRowSelection materialRowLike(String... value) {
        addLike(CmMaterialRowColumns.MATERIAL_ROW, value);
        return this;
    }

    public CmMaterialRowSelection materialRowContains(String... value) {
        addContains(CmMaterialRowColumns.MATERIAL_ROW, value);
        return this;
    }

    public CmMaterialRowSelection materialRowStartsWith(String... value) {
        addStartsWith(CmMaterialRowColumns.MATERIAL_ROW, value);
        return this;
    }

    public CmMaterialRowSelection materialRowEndsWith(String... value) {
        addEndsWith(CmMaterialRowColumns.MATERIAL_ROW, value);
        return this;
    }

    public CmMaterialRowSelection orderByMaterialRow(boolean desc) {
        orderBy(CmMaterialRowColumns.MATERIAL_ROW, desc);
        return this;
    }

    public CmMaterialRowSelection orderByMaterialRow() {
        orderBy(CmMaterialRowColumns.MATERIAL_ROW, false);
        return this;
    }

    public CmMaterialRowSelection materialId(String... value) {
        addEquals(CmMaterialRowColumns.MATERIAL_ID, value);
        return this;
    }

    public CmMaterialRowSelection materialIdNot(String... value) {
        addNotEquals(CmMaterialRowColumns.MATERIAL_ID, value);
        return this;
    }

    public CmMaterialRowSelection materialIdLike(String... value) {
        addLike(CmMaterialRowColumns.MATERIAL_ID, value);
        return this;
    }

    public CmMaterialRowSelection materialIdContains(String... value) {
        addContains(CmMaterialRowColumns.MATERIAL_ID, value);
        return this;
    }

    public CmMaterialRowSelection materialIdStartsWith(String... value) {
        addStartsWith(CmMaterialRowColumns.MATERIAL_ID, value);
        return this;
    }

    public CmMaterialRowSelection materialIdEndsWith(String... value) {
        addEndsWith(CmMaterialRowColumns.MATERIAL_ID, value);
        return this;
    }

    public CmMaterialRowSelection orderByMaterialId(boolean desc) {
        orderBy(CmMaterialRowColumns.MATERIAL_ID, desc);
        return this;
    }

    public CmMaterialRowSelection orderByMaterialId() {
        orderBy(CmMaterialRowColumns.MATERIAL_ID, false);
        return this;
    }

    public CmMaterialRowSelection materialDescription(String... value) {
        addEquals(CmMaterialRowColumns.MATERIAL_DESCRIPTION, value);
        return this;
    }

    public CmMaterialRowSelection materialDescriptionNot(String... value) {
        addNotEquals(CmMaterialRowColumns.MATERIAL_DESCRIPTION, value);
        return this;
    }

    public CmMaterialRowSelection materialDescriptionLike(String... value) {
        addLike(CmMaterialRowColumns.MATERIAL_DESCRIPTION, value);
        return this;
    }

    public CmMaterialRowSelection materialDescriptionContains(String... value) {
        addContains(CmMaterialRowColumns.MATERIAL_DESCRIPTION, value);
        return this;
    }

    public CmMaterialRowSelection materialDescriptionStartsWith(String... value) {
        addStartsWith(CmMaterialRowColumns.MATERIAL_DESCRIPTION, value);
        return this;
    }

    public CmMaterialRowSelection materialDescriptionEndsWith(String... value) {
        addEndsWith(CmMaterialRowColumns.MATERIAL_DESCRIPTION, value);
        return this;
    }

    public CmMaterialRowSelection orderByMaterialDescription(boolean desc) {
        orderBy(CmMaterialRowColumns.MATERIAL_DESCRIPTION, desc);
        return this;
    }

    public CmMaterialRowSelection orderByMaterialDescription() {
        orderBy(CmMaterialRowColumns.MATERIAL_DESCRIPTION, false);
        return this;
    }

    public CmMaterialRowSelection dueDate(Long... value) {
        addEquals(CmMaterialRowColumns.DUE_DATE, value);
        return this;
    }

    public CmMaterialRowSelection dueDateNot(Long... value) {
        addNotEquals(CmMaterialRowColumns.DUE_DATE, value);
        return this;
    }

    public CmMaterialRowSelection dueDateGt(long value) {
        addGreaterThan(CmMaterialRowColumns.DUE_DATE, value);
        return this;
    }

    public CmMaterialRowSelection dueDateGtEq(long value) {
        addGreaterThanOrEquals(CmMaterialRowColumns.DUE_DATE, value);
        return this;
    }

    public CmMaterialRowSelection dueDateLt(long value) {
        addLessThan(CmMaterialRowColumns.DUE_DATE, value);
        return this;
    }

    public CmMaterialRowSelection dueDateLtEq(long value) {
        addLessThanOrEquals(CmMaterialRowColumns.DUE_DATE, value);
        return this;
    }

    public CmMaterialRowSelection orderByDueDate(boolean desc) {
        orderBy(CmMaterialRowColumns.DUE_DATE, desc);
        return this;
    }

    public CmMaterialRowSelection orderByDueDate() {
        orderBy(CmMaterialRowColumns.DUE_DATE, false);
        return this;
    }

    public CmMaterialRowSelection materialCount(Integer... value) {
        addEquals(CmMaterialRowColumns.MATERIAL_COUNT, value);
        return this;
    }

    public CmMaterialRowSelection materialCountNot(Integer... value) {
        addNotEquals(CmMaterialRowColumns.MATERIAL_COUNT, value);
        return this;
    }

    public CmMaterialRowSelection materialCountGt(int value) {
        addGreaterThan(CmMaterialRowColumns.MATERIAL_COUNT, value);
        return this;
    }

    public CmMaterialRowSelection materialCountGtEq(int value) {
        addGreaterThanOrEquals(CmMaterialRowColumns.MATERIAL_COUNT, value);
        return this;
    }

    public CmMaterialRowSelection materialCountLt(int value) {
        addLessThan(CmMaterialRowColumns.MATERIAL_COUNT, value);
        return this;
    }

    public CmMaterialRowSelection materialCountLtEq(int value) {
        addLessThanOrEquals(CmMaterialRowColumns.MATERIAL_COUNT, value);
        return this;
    }

    public CmMaterialRowSelection orderByMaterialCount(boolean desc) {
        orderBy(CmMaterialRowColumns.MATERIAL_COUNT, desc);
        return this;
    }

    public CmMaterialRowSelection orderByMaterialCount() {
        orderBy(CmMaterialRowColumns.MATERIAL_COUNT, false);
        return this;
    }

    public CmMaterialRowSelection guid(String... value) {
        addEquals(CmMaterialRowColumns.GUID, value);
        return this;
    }

    public CmMaterialRowSelection guidNot(String... value) {
        addNotEquals(CmMaterialRowColumns.GUID, value);
        return this;
    }

    public CmMaterialRowSelection guidLike(String... value) {
        addLike(CmMaterialRowColumns.GUID, value);
        return this;
    }

    public CmMaterialRowSelection guidContains(String... value) {
        addContains(CmMaterialRowColumns.GUID, value);
        return this;
    }

    public CmMaterialRowSelection guidStartsWith(String... value) {
        addStartsWith(CmMaterialRowColumns.GUID, value);
        return this;
    }

    public CmMaterialRowSelection guidEndsWith(String... value) {
        addEndsWith(CmMaterialRowColumns.GUID, value);
        return this;
    }

    public CmMaterialRowSelection orderByGuid(boolean desc) {
        orderBy(CmMaterialRowColumns.GUID, desc);
        return this;
    }

    public CmMaterialRowSelection orderByGuid() {
        orderBy(CmMaterialRowColumns.GUID, false);
        return this;
    }
}
