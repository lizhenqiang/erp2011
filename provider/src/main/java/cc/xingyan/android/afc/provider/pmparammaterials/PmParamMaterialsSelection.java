package cc.xingyan.android.afc.provider.pmparammaterials;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code pm_param_materials} table.
 */
public class PmParamMaterialsSelection extends AbstractSelection<PmParamMaterialsSelection> {
    @Override
    protected Uri baseUri() {
        return PmParamMaterialsColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PmParamMaterialsCursor} object, which is positioned before the first entry, or null.
     */
    public PmParamMaterialsCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PmParamMaterialsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public PmParamMaterialsCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PmParamMaterialsCursor} object, which is positioned before the first entry, or null.
     */
    public PmParamMaterialsCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PmParamMaterialsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public PmParamMaterialsCursor query(Context context) {
        return query(context, null);
    }


    public PmParamMaterialsSelection id(long... value) {
        addEquals("pm_param_materials." + PmParamMaterialsColumns._ID, toObjectArray(value));
        return this;
    }

    public PmParamMaterialsSelection idNot(long... value) {
        addNotEquals("pm_param_materials." + PmParamMaterialsColumns._ID, toObjectArray(value));
        return this;
    }

    public PmParamMaterialsSelection orderById(boolean desc) {
        orderBy("pm_param_materials." + PmParamMaterialsColumns._ID, desc);
        return this;
    }

    public PmParamMaterialsSelection orderById() {
        return orderById(false);
    }

    public PmParamMaterialsSelection code(String... value) {
        addEquals(PmParamMaterialsColumns.CODE, value);
        return this;
    }

    public PmParamMaterialsSelection codeNot(String... value) {
        addNotEquals(PmParamMaterialsColumns.CODE, value);
        return this;
    }

    public PmParamMaterialsSelection codeLike(String... value) {
        addLike(PmParamMaterialsColumns.CODE, value);
        return this;
    }

    public PmParamMaterialsSelection codeContains(String... value) {
        addContains(PmParamMaterialsColumns.CODE, value);
        return this;
    }

    public PmParamMaterialsSelection codeStartsWith(String... value) {
        addStartsWith(PmParamMaterialsColumns.CODE, value);
        return this;
    }

    public PmParamMaterialsSelection codeEndsWith(String... value) {
        addEndsWith(PmParamMaterialsColumns.CODE, value);
        return this;
    }

    public PmParamMaterialsSelection orderByCode(boolean desc) {
        orderBy(PmParamMaterialsColumns.CODE, desc);
        return this;
    }

    public PmParamMaterialsSelection orderByCode() {
        orderBy(PmParamMaterialsColumns.CODE, false);
        return this;
    }

    public PmParamMaterialsSelection name(String... value) {
        addEquals(PmParamMaterialsColumns.NAME, value);
        return this;
    }

    public PmParamMaterialsSelection nameNot(String... value) {
        addNotEquals(PmParamMaterialsColumns.NAME, value);
        return this;
    }

    public PmParamMaterialsSelection nameLike(String... value) {
        addLike(PmParamMaterialsColumns.NAME, value);
        return this;
    }

    public PmParamMaterialsSelection nameContains(String... value) {
        addContains(PmParamMaterialsColumns.NAME, value);
        return this;
    }

    public PmParamMaterialsSelection nameStartsWith(String... value) {
        addStartsWith(PmParamMaterialsColumns.NAME, value);
        return this;
    }

    public PmParamMaterialsSelection nameEndsWith(String... value) {
        addEndsWith(PmParamMaterialsColumns.NAME, value);
        return this;
    }

    public PmParamMaterialsSelection orderByName(boolean desc) {
        orderBy(PmParamMaterialsColumns.NAME, desc);
        return this;
    }

    public PmParamMaterialsSelection orderByName() {
        orderBy(PmParamMaterialsColumns.NAME, false);
        return this;
    }

    public PmParamMaterialsSelection line(String... value) {
        addEquals(PmParamMaterialsColumns.LINE, value);
        return this;
    }

    public PmParamMaterialsSelection lineNot(String... value) {
        addNotEquals(PmParamMaterialsColumns.LINE, value);
        return this;
    }

    public PmParamMaterialsSelection lineLike(String... value) {
        addLike(PmParamMaterialsColumns.LINE, value);
        return this;
    }

    public PmParamMaterialsSelection lineContains(String... value) {
        addContains(PmParamMaterialsColumns.LINE, value);
        return this;
    }

    public PmParamMaterialsSelection lineStartsWith(String... value) {
        addStartsWith(PmParamMaterialsColumns.LINE, value);
        return this;
    }

    public PmParamMaterialsSelection lineEndsWith(String... value) {
        addEndsWith(PmParamMaterialsColumns.LINE, value);
        return this;
    }

    public PmParamMaterialsSelection orderByLine(boolean desc) {
        orderBy(PmParamMaterialsColumns.LINE, desc);
        return this;
    }

    public PmParamMaterialsSelection orderByLine() {
        orderBy(PmParamMaterialsColumns.LINE, false);
        return this;
    }

    public PmParamMaterialsSelection device(String... value) {
        addEquals(PmParamMaterialsColumns.DEVICE, value);
        return this;
    }

    public PmParamMaterialsSelection deviceNot(String... value) {
        addNotEquals(PmParamMaterialsColumns.DEVICE, value);
        return this;
    }

    public PmParamMaterialsSelection deviceLike(String... value) {
        addLike(PmParamMaterialsColumns.DEVICE, value);
        return this;
    }

    public PmParamMaterialsSelection deviceContains(String... value) {
        addContains(PmParamMaterialsColumns.DEVICE, value);
        return this;
    }

    public PmParamMaterialsSelection deviceStartsWith(String... value) {
        addStartsWith(PmParamMaterialsColumns.DEVICE, value);
        return this;
    }

    public PmParamMaterialsSelection deviceEndsWith(String... value) {
        addEndsWith(PmParamMaterialsColumns.DEVICE, value);
        return this;
    }

    public PmParamMaterialsSelection orderByDevice(boolean desc) {
        orderBy(PmParamMaterialsColumns.DEVICE, desc);
        return this;
    }

    public PmParamMaterialsSelection orderByDevice() {
        orderBy(PmParamMaterialsColumns.DEVICE, false);
        return this;
    }
}
