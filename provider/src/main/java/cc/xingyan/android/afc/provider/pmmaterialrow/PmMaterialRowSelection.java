package cc.xingyan.android.afc.provider.pmmaterialrow;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code pm_material_row} table.
 */
public class PmMaterialRowSelection extends AbstractSelection<PmMaterialRowSelection> {
    @Override
    protected Uri baseUri() {
        return PmMaterialRowColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PmMaterialRowCursor} object, which is positioned before the first entry, or null.
     */
    public PmMaterialRowCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PmMaterialRowCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public PmMaterialRowCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PmMaterialRowCursor} object, which is positioned before the first entry, or null.
     */
    public PmMaterialRowCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PmMaterialRowCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public PmMaterialRowCursor query(Context context) {
        return query(context, null);
    }


    public PmMaterialRowSelection id(long... value) {
        addEquals("pm_material_row." + PmMaterialRowColumns._ID, toObjectArray(value));
        return this;
    }

    public PmMaterialRowSelection idNot(long... value) {
        addNotEquals("pm_material_row." + PmMaterialRowColumns._ID, toObjectArray(value));
        return this;
    }

    public PmMaterialRowSelection orderById(boolean desc) {
        orderBy("pm_material_row." + PmMaterialRowColumns._ID, desc);
        return this;
    }

    public PmMaterialRowSelection orderById() {
        return orderById(false);
    }

    public PmMaterialRowSelection pmOrderId(String... value) {
        addEquals(PmMaterialRowColumns.PM_ORDER_ID, value);
        return this;
    }

    public PmMaterialRowSelection pmOrderIdNot(String... value) {
        addNotEquals(PmMaterialRowColumns.PM_ORDER_ID, value);
        return this;
    }

    public PmMaterialRowSelection pmOrderIdLike(String... value) {
        addLike(PmMaterialRowColumns.PM_ORDER_ID, value);
        return this;
    }

    public PmMaterialRowSelection pmOrderIdContains(String... value) {
        addContains(PmMaterialRowColumns.PM_ORDER_ID, value);
        return this;
    }

    public PmMaterialRowSelection pmOrderIdStartsWith(String... value) {
        addStartsWith(PmMaterialRowColumns.PM_ORDER_ID, value);
        return this;
    }

    public PmMaterialRowSelection pmOrderIdEndsWith(String... value) {
        addEndsWith(PmMaterialRowColumns.PM_ORDER_ID, value);
        return this;
    }

    public PmMaterialRowSelection orderByPmOrderId(boolean desc) {
        orderBy(PmMaterialRowColumns.PM_ORDER_ID, desc);
        return this;
    }

    public PmMaterialRowSelection orderByPmOrderId() {
        orderBy(PmMaterialRowColumns.PM_ORDER_ID, false);
        return this;
    }

    public PmMaterialRowSelection materialOrderId(String... value) {
        addEquals(PmMaterialRowColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public PmMaterialRowSelection materialOrderIdNot(String... value) {
        addNotEquals(PmMaterialRowColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public PmMaterialRowSelection materialOrderIdLike(String... value) {
        addLike(PmMaterialRowColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public PmMaterialRowSelection materialOrderIdContains(String... value) {
        addContains(PmMaterialRowColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public PmMaterialRowSelection materialOrderIdStartsWith(String... value) {
        addStartsWith(PmMaterialRowColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public PmMaterialRowSelection materialOrderIdEndsWith(String... value) {
        addEndsWith(PmMaterialRowColumns.MATERIAL_ORDER_ID, value);
        return this;
    }

    public PmMaterialRowSelection orderByMaterialOrderId(boolean desc) {
        orderBy(PmMaterialRowColumns.MATERIAL_ORDER_ID, desc);
        return this;
    }

    public PmMaterialRowSelection orderByMaterialOrderId() {
        orderBy(PmMaterialRowColumns.MATERIAL_ORDER_ID, false);
        return this;
    }

    public PmMaterialRowSelection materialRow(String... value) {
        addEquals(PmMaterialRowColumns.MATERIAL_ROW, value);
        return this;
    }

    public PmMaterialRowSelection materialRowNot(String... value) {
        addNotEquals(PmMaterialRowColumns.MATERIAL_ROW, value);
        return this;
    }

    public PmMaterialRowSelection materialRowLike(String... value) {
        addLike(PmMaterialRowColumns.MATERIAL_ROW, value);
        return this;
    }

    public PmMaterialRowSelection materialRowContains(String... value) {
        addContains(PmMaterialRowColumns.MATERIAL_ROW, value);
        return this;
    }

    public PmMaterialRowSelection materialRowStartsWith(String... value) {
        addStartsWith(PmMaterialRowColumns.MATERIAL_ROW, value);
        return this;
    }

    public PmMaterialRowSelection materialRowEndsWith(String... value) {
        addEndsWith(PmMaterialRowColumns.MATERIAL_ROW, value);
        return this;
    }

    public PmMaterialRowSelection orderByMaterialRow(boolean desc) {
        orderBy(PmMaterialRowColumns.MATERIAL_ROW, desc);
        return this;
    }

    public PmMaterialRowSelection orderByMaterialRow() {
        orderBy(PmMaterialRowColumns.MATERIAL_ROW, false);
        return this;
    }

    public PmMaterialRowSelection materialId(String... value) {
        addEquals(PmMaterialRowColumns.MATERIAL_ID, value);
        return this;
    }

    public PmMaterialRowSelection materialIdNot(String... value) {
        addNotEquals(PmMaterialRowColumns.MATERIAL_ID, value);
        return this;
    }

    public PmMaterialRowSelection materialIdLike(String... value) {
        addLike(PmMaterialRowColumns.MATERIAL_ID, value);
        return this;
    }

    public PmMaterialRowSelection materialIdContains(String... value) {
        addContains(PmMaterialRowColumns.MATERIAL_ID, value);
        return this;
    }

    public PmMaterialRowSelection materialIdStartsWith(String... value) {
        addStartsWith(PmMaterialRowColumns.MATERIAL_ID, value);
        return this;
    }

    public PmMaterialRowSelection materialIdEndsWith(String... value) {
        addEndsWith(PmMaterialRowColumns.MATERIAL_ID, value);
        return this;
    }

    public PmMaterialRowSelection orderByMaterialId(boolean desc) {
        orderBy(PmMaterialRowColumns.MATERIAL_ID, desc);
        return this;
    }

    public PmMaterialRowSelection orderByMaterialId() {
        orderBy(PmMaterialRowColumns.MATERIAL_ID, false);
        return this;
    }

    public PmMaterialRowSelection materialDescription(String... value) {
        addEquals(PmMaterialRowColumns.MATERIAL_DESCRIPTION, value);
        return this;
    }

    public PmMaterialRowSelection materialDescriptionNot(String... value) {
        addNotEquals(PmMaterialRowColumns.MATERIAL_DESCRIPTION, value);
        return this;
    }

    public PmMaterialRowSelection materialDescriptionLike(String... value) {
        addLike(PmMaterialRowColumns.MATERIAL_DESCRIPTION, value);
        return this;
    }

    public PmMaterialRowSelection materialDescriptionContains(String... value) {
        addContains(PmMaterialRowColumns.MATERIAL_DESCRIPTION, value);
        return this;
    }

    public PmMaterialRowSelection materialDescriptionStartsWith(String... value) {
        addStartsWith(PmMaterialRowColumns.MATERIAL_DESCRIPTION, value);
        return this;
    }

    public PmMaterialRowSelection materialDescriptionEndsWith(String... value) {
        addEndsWith(PmMaterialRowColumns.MATERIAL_DESCRIPTION, value);
        return this;
    }

    public PmMaterialRowSelection orderByMaterialDescription(boolean desc) {
        orderBy(PmMaterialRowColumns.MATERIAL_DESCRIPTION, desc);
        return this;
    }

    public PmMaterialRowSelection orderByMaterialDescription() {
        orderBy(PmMaterialRowColumns.MATERIAL_DESCRIPTION, false);
        return this;
    }

    public PmMaterialRowSelection dueDate(Long... value) {
        addEquals(PmMaterialRowColumns.DUE_DATE, value);
        return this;
    }

    public PmMaterialRowSelection dueDateNot(Long... value) {
        addNotEquals(PmMaterialRowColumns.DUE_DATE, value);
        return this;
    }

    public PmMaterialRowSelection dueDateGt(long value) {
        addGreaterThan(PmMaterialRowColumns.DUE_DATE, value);
        return this;
    }

    public PmMaterialRowSelection dueDateGtEq(long value) {
        addGreaterThanOrEquals(PmMaterialRowColumns.DUE_DATE, value);
        return this;
    }

    public PmMaterialRowSelection dueDateLt(long value) {
        addLessThan(PmMaterialRowColumns.DUE_DATE, value);
        return this;
    }

    public PmMaterialRowSelection dueDateLtEq(long value) {
        addLessThanOrEquals(PmMaterialRowColumns.DUE_DATE, value);
        return this;
    }

    public PmMaterialRowSelection orderByDueDate(boolean desc) {
        orderBy(PmMaterialRowColumns.DUE_DATE, desc);
        return this;
    }

    public PmMaterialRowSelection orderByDueDate() {
        orderBy(PmMaterialRowColumns.DUE_DATE, false);
        return this;
    }

    public PmMaterialRowSelection materialCount(Integer... value) {
        addEquals(PmMaterialRowColumns.MATERIAL_COUNT, value);
        return this;
    }

    public PmMaterialRowSelection materialCountNot(Integer... value) {
        addNotEquals(PmMaterialRowColumns.MATERIAL_COUNT, value);
        return this;
    }

    public PmMaterialRowSelection materialCountGt(int value) {
        addGreaterThan(PmMaterialRowColumns.MATERIAL_COUNT, value);
        return this;
    }

    public PmMaterialRowSelection materialCountGtEq(int value) {
        addGreaterThanOrEquals(PmMaterialRowColumns.MATERIAL_COUNT, value);
        return this;
    }

    public PmMaterialRowSelection materialCountLt(int value) {
        addLessThan(PmMaterialRowColumns.MATERIAL_COUNT, value);
        return this;
    }

    public PmMaterialRowSelection materialCountLtEq(int value) {
        addLessThanOrEquals(PmMaterialRowColumns.MATERIAL_COUNT, value);
        return this;
    }

    public PmMaterialRowSelection orderByMaterialCount(boolean desc) {
        orderBy(PmMaterialRowColumns.MATERIAL_COUNT, desc);
        return this;
    }

    public PmMaterialRowSelection orderByMaterialCount() {
        orderBy(PmMaterialRowColumns.MATERIAL_COUNT, false);
        return this;
    }

    public PmMaterialRowSelection guid(String... value) {
        addEquals(PmMaterialRowColumns.GUID, value);
        return this;
    }

    public PmMaterialRowSelection guidNot(String... value) {
        addNotEquals(PmMaterialRowColumns.GUID, value);
        return this;
    }

    public PmMaterialRowSelection guidLike(String... value) {
        addLike(PmMaterialRowColumns.GUID, value);
        return this;
    }

    public PmMaterialRowSelection guidContains(String... value) {
        addContains(PmMaterialRowColumns.GUID, value);
        return this;
    }

    public PmMaterialRowSelection guidStartsWith(String... value) {
        addStartsWith(PmMaterialRowColumns.GUID, value);
        return this;
    }

    public PmMaterialRowSelection guidEndsWith(String... value) {
        addEndsWith(PmMaterialRowColumns.GUID, value);
        return this;
    }

    public PmMaterialRowSelection orderByGuid(boolean desc) {
        orderBy(PmMaterialRowColumns.GUID, desc);
        return this;
    }

    public PmMaterialRowSelection orderByGuid() {
        orderBy(PmMaterialRowColumns.GUID, false);
        return this;
    }
}
