package cc.xingyan.android.afc.provider.pmresultenum;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code pm_result_enum} table.
 */
public class PmResultEnumSelection extends AbstractSelection<PmResultEnumSelection> {
    @Override
    protected Uri baseUri() {
        return PmResultEnumColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PmResultEnumCursor} object, which is positioned before the first entry, or null.
     */
    public PmResultEnumCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PmResultEnumCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public PmResultEnumCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PmResultEnumCursor} object, which is positioned before the first entry, or null.
     */
    public PmResultEnumCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PmResultEnumCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public PmResultEnumCursor query(Context context) {
        return query(context, null);
    }


    public PmResultEnumSelection id(long... value) {
        addEquals("pm_result_enum." + PmResultEnumColumns._ID, toObjectArray(value));
        return this;
    }

    public PmResultEnumSelection idNot(long... value) {
        addNotEquals("pm_result_enum." + PmResultEnumColumns._ID, toObjectArray(value));
        return this;
    }

    public PmResultEnumSelection orderById(boolean desc) {
        orderBy("pm_result_enum." + PmResultEnumColumns._ID, desc);
        return this;
    }

    public PmResultEnumSelection orderById() {
        return orderById(false);
    }

    public PmResultEnumSelection itemGuid(String... value) {
        addEquals(PmResultEnumColumns.ITEM_GUID, value);
        return this;
    }

    public PmResultEnumSelection itemGuidNot(String... value) {
        addNotEquals(PmResultEnumColumns.ITEM_GUID, value);
        return this;
    }

    public PmResultEnumSelection itemGuidLike(String... value) {
        addLike(PmResultEnumColumns.ITEM_GUID, value);
        return this;
    }

    public PmResultEnumSelection itemGuidContains(String... value) {
        addContains(PmResultEnumColumns.ITEM_GUID, value);
        return this;
    }

    public PmResultEnumSelection itemGuidStartsWith(String... value) {
        addStartsWith(PmResultEnumColumns.ITEM_GUID, value);
        return this;
    }

    public PmResultEnumSelection itemGuidEndsWith(String... value) {
        addEndsWith(PmResultEnumColumns.ITEM_GUID, value);
        return this;
    }

    public PmResultEnumSelection orderByItemGuid(boolean desc) {
        orderBy(PmResultEnumColumns.ITEM_GUID, desc);
        return this;
    }

    public PmResultEnumSelection orderByItemGuid() {
        orderBy(PmResultEnumColumns.ITEM_GUID, false);
        return this;
    }

    public PmResultEnumSelection ordinal(Integer... value) {
        addEquals(PmResultEnumColumns.ORDINAL, value);
        return this;
    }

    public PmResultEnumSelection ordinalNot(Integer... value) {
        addNotEquals(PmResultEnumColumns.ORDINAL, value);
        return this;
    }

    public PmResultEnumSelection ordinalGt(int value) {
        addGreaterThan(PmResultEnumColumns.ORDINAL, value);
        return this;
    }

    public PmResultEnumSelection ordinalGtEq(int value) {
        addGreaterThanOrEquals(PmResultEnumColumns.ORDINAL, value);
        return this;
    }

    public PmResultEnumSelection ordinalLt(int value) {
        addLessThan(PmResultEnumColumns.ORDINAL, value);
        return this;
    }

    public PmResultEnumSelection ordinalLtEq(int value) {
        addLessThanOrEquals(PmResultEnumColumns.ORDINAL, value);
        return this;
    }

    public PmResultEnumSelection orderByOrdinal(boolean desc) {
        orderBy(PmResultEnumColumns.ORDINAL, desc);
        return this;
    }

    public PmResultEnumSelection orderByOrdinal() {
        orderBy(PmResultEnumColumns.ORDINAL, false);
        return this;
    }

    public PmResultEnumSelection value(String... value) {
        addEquals(PmResultEnumColumns.VALUE, value);
        return this;
    }

    public PmResultEnumSelection valueNot(String... value) {
        addNotEquals(PmResultEnumColumns.VALUE, value);
        return this;
    }

    public PmResultEnumSelection valueLike(String... value) {
        addLike(PmResultEnumColumns.VALUE, value);
        return this;
    }

    public PmResultEnumSelection valueContains(String... value) {
        addContains(PmResultEnumColumns.VALUE, value);
        return this;
    }

    public PmResultEnumSelection valueStartsWith(String... value) {
        addStartsWith(PmResultEnumColumns.VALUE, value);
        return this;
    }

    public PmResultEnumSelection valueEndsWith(String... value) {
        addEndsWith(PmResultEnumColumns.VALUE, value);
        return this;
    }

    public PmResultEnumSelection orderByValue(boolean desc) {
        orderBy(PmResultEnumColumns.VALUE, desc);
        return this;
    }

    public PmResultEnumSelection orderByValue() {
        orderBy(PmResultEnumColumns.VALUE, false);
        return this;
    }
}
