package cc.xingyan.android.afc.provider.cmparammaterials;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code cm_param_materials} table.
 */
public class CmParamMaterialsSelection extends AbstractSelection<CmParamMaterialsSelection> {
    @Override
    protected Uri baseUri() {
        return CmParamMaterialsColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CmParamMaterialsCursor} object, which is positioned before the first entry, or null.
     */
    public CmParamMaterialsCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CmParamMaterialsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public CmParamMaterialsCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CmParamMaterialsCursor} object, which is positioned before the first entry, or null.
     */
    public CmParamMaterialsCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CmParamMaterialsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public CmParamMaterialsCursor query(Context context) {
        return query(context, null);
    }


    public CmParamMaterialsSelection id(long... value) {
        addEquals("cm_param_materials." + CmParamMaterialsColumns._ID, toObjectArray(value));
        return this;
    }

    public CmParamMaterialsSelection idNot(long... value) {
        addNotEquals("cm_param_materials." + CmParamMaterialsColumns._ID, toObjectArray(value));
        return this;
    }

    public CmParamMaterialsSelection orderById(boolean desc) {
        orderBy("cm_param_materials." + CmParamMaterialsColumns._ID, desc);
        return this;
    }

    public CmParamMaterialsSelection orderById() {
        return orderById(false);
    }

    public CmParamMaterialsSelection code(String... value) {
        addEquals(CmParamMaterialsColumns.CODE, value);
        return this;
    }

    public CmParamMaterialsSelection codeNot(String... value) {
        addNotEquals(CmParamMaterialsColumns.CODE, value);
        return this;
    }

    public CmParamMaterialsSelection codeLike(String... value) {
        addLike(CmParamMaterialsColumns.CODE, value);
        return this;
    }

    public CmParamMaterialsSelection codeContains(String... value) {
        addContains(CmParamMaterialsColumns.CODE, value);
        return this;
    }

    public CmParamMaterialsSelection codeStartsWith(String... value) {
        addStartsWith(CmParamMaterialsColumns.CODE, value);
        return this;
    }

    public CmParamMaterialsSelection codeEndsWith(String... value) {
        addEndsWith(CmParamMaterialsColumns.CODE, value);
        return this;
    }

    public CmParamMaterialsSelection orderByCode(boolean desc) {
        orderBy(CmParamMaterialsColumns.CODE, desc);
        return this;
    }

    public CmParamMaterialsSelection orderByCode() {
        orderBy(CmParamMaterialsColumns.CODE, false);
        return this;
    }

    public CmParamMaterialsSelection name(String... value) {
        addEquals(CmParamMaterialsColumns.NAME, value);
        return this;
    }

    public CmParamMaterialsSelection nameNot(String... value) {
        addNotEquals(CmParamMaterialsColumns.NAME, value);
        return this;
    }

    public CmParamMaterialsSelection nameLike(String... value) {
        addLike(CmParamMaterialsColumns.NAME, value);
        return this;
    }

    public CmParamMaterialsSelection nameContains(String... value) {
        addContains(CmParamMaterialsColumns.NAME, value);
        return this;
    }

    public CmParamMaterialsSelection nameStartsWith(String... value) {
        addStartsWith(CmParamMaterialsColumns.NAME, value);
        return this;
    }

    public CmParamMaterialsSelection nameEndsWith(String... value) {
        addEndsWith(CmParamMaterialsColumns.NAME, value);
        return this;
    }

    public CmParamMaterialsSelection orderByName(boolean desc) {
        orderBy(CmParamMaterialsColumns.NAME, desc);
        return this;
    }

    public CmParamMaterialsSelection orderByName() {
        orderBy(CmParamMaterialsColumns.NAME, false);
        return this;
    }

    public CmParamMaterialsSelection line(String... value) {
        addEquals(CmParamMaterialsColumns.LINE, value);
        return this;
    }

    public CmParamMaterialsSelection lineNot(String... value) {
        addNotEquals(CmParamMaterialsColumns.LINE, value);
        return this;
    }

    public CmParamMaterialsSelection lineLike(String... value) {
        addLike(CmParamMaterialsColumns.LINE, value);
        return this;
    }

    public CmParamMaterialsSelection lineContains(String... value) {
        addContains(CmParamMaterialsColumns.LINE, value);
        return this;
    }

    public CmParamMaterialsSelection lineStartsWith(String... value) {
        addStartsWith(CmParamMaterialsColumns.LINE, value);
        return this;
    }

    public CmParamMaterialsSelection lineEndsWith(String... value) {
        addEndsWith(CmParamMaterialsColumns.LINE, value);
        return this;
    }

    public CmParamMaterialsSelection orderByLine(boolean desc) {
        orderBy(CmParamMaterialsColumns.LINE, desc);
        return this;
    }

    public CmParamMaterialsSelection orderByLine() {
        orderBy(CmParamMaterialsColumns.LINE, false);
        return this;
    }

    public CmParamMaterialsSelection device(String... value) {
        addEquals(CmParamMaterialsColumns.DEVICE, value);
        return this;
    }

    public CmParamMaterialsSelection deviceNot(String... value) {
        addNotEquals(CmParamMaterialsColumns.DEVICE, value);
        return this;
    }

    public CmParamMaterialsSelection deviceLike(String... value) {
        addLike(CmParamMaterialsColumns.DEVICE, value);
        return this;
    }

    public CmParamMaterialsSelection deviceContains(String... value) {
        addContains(CmParamMaterialsColumns.DEVICE, value);
        return this;
    }

    public CmParamMaterialsSelection deviceStartsWith(String... value) {
        addStartsWith(CmParamMaterialsColumns.DEVICE, value);
        return this;
    }

    public CmParamMaterialsSelection deviceEndsWith(String... value) {
        addEndsWith(CmParamMaterialsColumns.DEVICE, value);
        return this;
    }

    public CmParamMaterialsSelection orderByDevice(boolean desc) {
        orderBy(CmParamMaterialsColumns.DEVICE, desc);
        return this;
    }

    public CmParamMaterialsSelection orderByDevice() {
        orderBy(CmParamMaterialsColumns.DEVICE, false);
        return this;
    }
}
