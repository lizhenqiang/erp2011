package cc.xingyan.android.afc.provider.pmifsresultenum;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code pmifs_result_enum} table.
 */
public class PmifsResultEnumSelection extends AbstractSelection<PmifsResultEnumSelection> {
    @Override
    protected Uri baseUri() {
        return PmifsResultEnumColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PmifsResultEnumCursor} object, which is positioned before the first entry, or null.
     */
    public PmifsResultEnumCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PmifsResultEnumCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public PmifsResultEnumCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PmifsResultEnumCursor} object, which is positioned before the first entry, or null.
     */
    public PmifsResultEnumCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PmifsResultEnumCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public PmifsResultEnumCursor query(Context context) {
        return query(context, null);
    }


    public PmifsResultEnumSelection id(long... value) {
        addEquals("pmifs_result_enum." + PmifsResultEnumColumns._ID, toObjectArray(value));
        return this;
    }

    public PmifsResultEnumSelection idNot(long... value) {
        addNotEquals("pmifs_result_enum." + PmifsResultEnumColumns._ID, toObjectArray(value));
        return this;
    }

    public PmifsResultEnumSelection orderById(boolean desc) {
        orderBy("pmifs_result_enum." + PmifsResultEnumColumns._ID, desc);
        return this;
    }

    public PmifsResultEnumSelection orderById() {
        return orderById(false);
    }

    public PmifsResultEnumSelection itemGuid(String... value) {
        addEquals(PmifsResultEnumColumns.ITEM_GUID, value);
        return this;
    }

    public PmifsResultEnumSelection itemGuidNot(String... value) {
        addNotEquals(PmifsResultEnumColumns.ITEM_GUID, value);
        return this;
    }

    public PmifsResultEnumSelection itemGuidLike(String... value) {
        addLike(PmifsResultEnumColumns.ITEM_GUID, value);
        return this;
    }

    public PmifsResultEnumSelection itemGuidContains(String... value) {
        addContains(PmifsResultEnumColumns.ITEM_GUID, value);
        return this;
    }

    public PmifsResultEnumSelection itemGuidStartsWith(String... value) {
        addStartsWith(PmifsResultEnumColumns.ITEM_GUID, value);
        return this;
    }

    public PmifsResultEnumSelection itemGuidEndsWith(String... value) {
        addEndsWith(PmifsResultEnumColumns.ITEM_GUID, value);
        return this;
    }

    public PmifsResultEnumSelection orderByItemGuid(boolean desc) {
        orderBy(PmifsResultEnumColumns.ITEM_GUID, desc);
        return this;
    }

    public PmifsResultEnumSelection orderByItemGuid() {
        orderBy(PmifsResultEnumColumns.ITEM_GUID, false);
        return this;
    }

    public PmifsResultEnumSelection sn(Integer... value) {
        addEquals(PmifsResultEnumColumns.SN, value);
        return this;
    }

    public PmifsResultEnumSelection snNot(Integer... value) {
        addNotEquals(PmifsResultEnumColumns.SN, value);
        return this;
    }

    public PmifsResultEnumSelection snGt(int value) {
        addGreaterThan(PmifsResultEnumColumns.SN, value);
        return this;
    }

    public PmifsResultEnumSelection snGtEq(int value) {
        addGreaterThanOrEquals(PmifsResultEnumColumns.SN, value);
        return this;
    }

    public PmifsResultEnumSelection snLt(int value) {
        addLessThan(PmifsResultEnumColumns.SN, value);
        return this;
    }

    public PmifsResultEnumSelection snLtEq(int value) {
        addLessThanOrEquals(PmifsResultEnumColumns.SN, value);
        return this;
    }

    public PmifsResultEnumSelection orderBySn(boolean desc) {
        orderBy(PmifsResultEnumColumns.SN, desc);
        return this;
    }

    public PmifsResultEnumSelection orderBySn() {
        orderBy(PmifsResultEnumColumns.SN, false);
        return this;
    }

    public PmifsResultEnumSelection value(String... value) {
        addEquals(PmifsResultEnumColumns.VALUE, value);
        return this;
    }

    public PmifsResultEnumSelection valueNot(String... value) {
        addNotEquals(PmifsResultEnumColumns.VALUE, value);
        return this;
    }

    public PmifsResultEnumSelection valueLike(String... value) {
        addLike(PmifsResultEnumColumns.VALUE, value);
        return this;
    }

    public PmifsResultEnumSelection valueContains(String... value) {
        addContains(PmifsResultEnumColumns.VALUE, value);
        return this;
    }

    public PmifsResultEnumSelection valueStartsWith(String... value) {
        addStartsWith(PmifsResultEnumColumns.VALUE, value);
        return this;
    }

    public PmifsResultEnumSelection valueEndsWith(String... value) {
        addEndsWith(PmifsResultEnumColumns.VALUE, value);
        return this;
    }

    public PmifsResultEnumSelection orderByValue(boolean desc) {
        orderBy(PmifsResultEnumColumns.VALUE, desc);
        return this;
    }

    public PmifsResultEnumSelection orderByValue() {
        orderBy(PmifsResultEnumColumns.VALUE, false);
        return this;
    }
}
