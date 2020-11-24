package cc.xingyan.android.afc.provider.paramvalue;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code param_value} table.
 */
public class ParamValueSelection extends AbstractSelection<ParamValueSelection> {
    @Override
    protected Uri baseUri() {
        return ParamValueColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ParamValueCursor} object, which is positioned before the first entry, or null.
     */
    public ParamValueCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ParamValueCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public ParamValueCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ParamValueCursor} object, which is positioned before the first entry, or null.
     */
    public ParamValueCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ParamValueCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public ParamValueCursor query(Context context) {
        return query(context, null);
    }


    public ParamValueSelection id(long... value) {
        addEquals("param_value." + ParamValueColumns._ID, toObjectArray(value));
        return this;
    }

    public ParamValueSelection idNot(long... value) {
        addNotEquals("param_value." + ParamValueColumns._ID, toObjectArray(value));
        return this;
    }

    public ParamValueSelection orderById(boolean desc) {
        orderBy("param_value." + ParamValueColumns._ID, desc);
        return this;
    }

    public ParamValueSelection orderById() {
        return orderById(false);
    }

    public ParamValueSelection code(String... value) {
        addEquals(ParamValueColumns.CODE, value);
        return this;
    }

    public ParamValueSelection codeNot(String... value) {
        addNotEquals(ParamValueColumns.CODE, value);
        return this;
    }

    public ParamValueSelection codeLike(String... value) {
        addLike(ParamValueColumns.CODE, value);
        return this;
    }

    public ParamValueSelection codeContains(String... value) {
        addContains(ParamValueColumns.CODE, value);
        return this;
    }

    public ParamValueSelection codeStartsWith(String... value) {
        addStartsWith(ParamValueColumns.CODE, value);
        return this;
    }

    public ParamValueSelection codeEndsWith(String... value) {
        addEndsWith(ParamValueColumns.CODE, value);
        return this;
    }

    public ParamValueSelection orderByCode(boolean desc) {
        orderBy(ParamValueColumns.CODE, desc);
        return this;
    }

    public ParamValueSelection orderByCode() {
        orderBy(ParamValueColumns.CODE, false);
        return this;
    }

    public ParamValueSelection value(String... value) {
        addEquals(ParamValueColumns.VALUE, value);
        return this;
    }

    public ParamValueSelection valueNot(String... value) {
        addNotEquals(ParamValueColumns.VALUE, value);
        return this;
    }

    public ParamValueSelection valueLike(String... value) {
        addLike(ParamValueColumns.VALUE, value);
        return this;
    }

    public ParamValueSelection valueContains(String... value) {
        addContains(ParamValueColumns.VALUE, value);
        return this;
    }

    public ParamValueSelection valueStartsWith(String... value) {
        addStartsWith(ParamValueColumns.VALUE, value);
        return this;
    }

    public ParamValueSelection valueEndsWith(String... value) {
        addEndsWith(ParamValueColumns.VALUE, value);
        return this;
    }

    public ParamValueSelection orderByValue(boolean desc) {
        orderBy(ParamValueColumns.VALUE, desc);
        return this;
    }

    public ParamValueSelection orderByValue() {
        orderBy(ParamValueColumns.VALUE, false);
        return this;
    }

    public ParamValueSelection type(String... value) {
        addEquals(ParamValueColumns.TYPE, value);
        return this;
    }

    public ParamValueSelection typeNot(String... value) {
        addNotEquals(ParamValueColumns.TYPE, value);
        return this;
    }

    public ParamValueSelection typeLike(String... value) {
        addLike(ParamValueColumns.TYPE, value);
        return this;
    }

    public ParamValueSelection typeContains(String... value) {
        addContains(ParamValueColumns.TYPE, value);
        return this;
    }

    public ParamValueSelection typeStartsWith(String... value) {
        addStartsWith(ParamValueColumns.TYPE, value);
        return this;
    }

    public ParamValueSelection typeEndsWith(String... value) {
        addEndsWith(ParamValueColumns.TYPE, value);
        return this;
    }

    public ParamValueSelection orderByType(boolean desc) {
        orderBy(ParamValueColumns.TYPE, desc);
        return this;
    }

    public ParamValueSelection orderByType() {
        orderBy(ParamValueColumns.TYPE, false);
        return this;
    }

    public ParamValueSelection parentCode(String... value) {
        addEquals(ParamValueColumns.PARENT_CODE, value);
        return this;
    }

    public ParamValueSelection parentCodeNot(String... value) {
        addNotEquals(ParamValueColumns.PARENT_CODE, value);
        return this;
    }

    public ParamValueSelection parentCodeLike(String... value) {
        addLike(ParamValueColumns.PARENT_CODE, value);
        return this;
    }

    public ParamValueSelection parentCodeContains(String... value) {
        addContains(ParamValueColumns.PARENT_CODE, value);
        return this;
    }

    public ParamValueSelection parentCodeStartsWith(String... value) {
        addStartsWith(ParamValueColumns.PARENT_CODE, value);
        return this;
    }

    public ParamValueSelection parentCodeEndsWith(String... value) {
        addEndsWith(ParamValueColumns.PARENT_CODE, value);
        return this;
    }

    public ParamValueSelection orderByParentCode(boolean desc) {
        orderBy(ParamValueColumns.PARENT_CODE, desc);
        return this;
    }

    public ParamValueSelection orderByParentCode() {
        orderBy(ParamValueColumns.PARENT_CODE, false);
        return this;
    }

    public ParamValueSelection parentType(String... value) {
        addEquals(ParamValueColumns.PARENT_TYPE, value);
        return this;
    }

    public ParamValueSelection parentTypeNot(String... value) {
        addNotEquals(ParamValueColumns.PARENT_TYPE, value);
        return this;
    }

    public ParamValueSelection parentTypeLike(String... value) {
        addLike(ParamValueColumns.PARENT_TYPE, value);
        return this;
    }

    public ParamValueSelection parentTypeContains(String... value) {
        addContains(ParamValueColumns.PARENT_TYPE, value);
        return this;
    }

    public ParamValueSelection parentTypeStartsWith(String... value) {
        addStartsWith(ParamValueColumns.PARENT_TYPE, value);
        return this;
    }

    public ParamValueSelection parentTypeEndsWith(String... value) {
        addEndsWith(ParamValueColumns.PARENT_TYPE, value);
        return this;
    }

    public ParamValueSelection orderByParentType(boolean desc) {
        orderBy(ParamValueColumns.PARENT_TYPE, desc);
        return this;
    }

    public ParamValueSelection orderByParentType() {
        orderBy(ParamValueColumns.PARENT_TYPE, false);
        return this;
    }
}
