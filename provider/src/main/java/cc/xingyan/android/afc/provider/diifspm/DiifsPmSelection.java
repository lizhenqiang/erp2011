package cc.xingyan.android.afc.provider.diifspm;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code diifs_pm} table.
 */
public class DiifsPmSelection extends AbstractSelection<DiifsPmSelection> {
    @Override
    protected Uri baseUri() {
        return DiifsPmColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code DiifsPmCursor} object, which is positioned before the first entry, or null.
     */
    public DiifsPmCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new DiifsPmCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public DiifsPmCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code DiifsPmCursor} object, which is positioned before the first entry, or null.
     */
    public DiifsPmCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new DiifsPmCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public DiifsPmCursor query(Context context) {
        return query(context, null);
    }


    public DiifsPmSelection id(long... value) {
        addEquals("diifs_pm." + DiifsPmColumns._ID, toObjectArray(value));
        return this;
    }

    public DiifsPmSelection idNot(long... value) {
        addNotEquals("diifs_pm." + DiifsPmColumns._ID, toObjectArray(value));
        return this;
    }

    public DiifsPmSelection orderById(boolean desc) {
        orderBy("diifs_pm." + DiifsPmColumns._ID, desc);
        return this;
    }

    public DiifsPmSelection orderById() {
        return orderById(false);
    }

    public DiifsPmSelection wono(String... value) {
        addEquals(DiifsPmColumns.WONO, value);
        return this;
    }

    public DiifsPmSelection wonoNot(String... value) {
        addNotEquals(DiifsPmColumns.WONO, value);
        return this;
    }

    public DiifsPmSelection wonoLike(String... value) {
        addLike(DiifsPmColumns.WONO, value);
        return this;
    }

    public DiifsPmSelection wonoContains(String... value) {
        addContains(DiifsPmColumns.WONO, value);
        return this;
    }

    public DiifsPmSelection wonoStartsWith(String... value) {
        addStartsWith(DiifsPmColumns.WONO, value);
        return this;
    }

    public DiifsPmSelection wonoEndsWith(String... value) {
        addEndsWith(DiifsPmColumns.WONO, value);
        return this;
    }

    public DiifsPmSelection orderByWono(boolean desc) {
        orderBy(DiifsPmColumns.WONO, desc);
        return this;
    }

    public DiifsPmSelection orderByWono() {
        orderBy(DiifsPmColumns.WONO, false);
        return this;
    }

    public DiifsPmSelection pmno(String... value) {
        addEquals(DiifsPmColumns.PMNO, value);
        return this;
    }

    public DiifsPmSelection pmnoNot(String... value) {
        addNotEquals(DiifsPmColumns.PMNO, value);
        return this;
    }

    public DiifsPmSelection pmnoLike(String... value) {
        addLike(DiifsPmColumns.PMNO, value);
        return this;
    }

    public DiifsPmSelection pmnoContains(String... value) {
        addContains(DiifsPmColumns.PMNO, value);
        return this;
    }

    public DiifsPmSelection pmnoStartsWith(String... value) {
        addStartsWith(DiifsPmColumns.PMNO, value);
        return this;
    }

    public DiifsPmSelection pmnoEndsWith(String... value) {
        addEndsWith(DiifsPmColumns.PMNO, value);
        return this;
    }

    public DiifsPmSelection orderByPmno(boolean desc) {
        orderBy(DiifsPmColumns.PMNO, desc);
        return this;
    }

    public DiifsPmSelection orderByPmno() {
        orderBy(DiifsPmColumns.PMNO, false);
        return this;
    }

    public DiifsPmSelection logicname(String... value) {
        addEquals(DiifsPmColumns.LOGICNAME, value);
        return this;
    }

    public DiifsPmSelection logicnameNot(String... value) {
        addNotEquals(DiifsPmColumns.LOGICNAME, value);
        return this;
    }

    public DiifsPmSelection logicnameLike(String... value) {
        addLike(DiifsPmColumns.LOGICNAME, value);
        return this;
    }

    public DiifsPmSelection logicnameContains(String... value) {
        addContains(DiifsPmColumns.LOGICNAME, value);
        return this;
    }

    public DiifsPmSelection logicnameStartsWith(String... value) {
        addStartsWith(DiifsPmColumns.LOGICNAME, value);
        return this;
    }

    public DiifsPmSelection logicnameEndsWith(String... value) {
        addEndsWith(DiifsPmColumns.LOGICNAME, value);
        return this;
    }

    public DiifsPmSelection orderByLogicname(boolean desc) {
        orderBy(DiifsPmColumns.LOGICNAME, desc);
        return this;
    }

    public DiifsPmSelection orderByLogicname() {
        orderBy(DiifsPmColumns.LOGICNAME, false);
        return this;
    }

    public DiifsPmSelection physicname(String... value) {
        addEquals(DiifsPmColumns.PHYSICNAME, value);
        return this;
    }

    public DiifsPmSelection physicnameNot(String... value) {
        addNotEquals(DiifsPmColumns.PHYSICNAME, value);
        return this;
    }

    public DiifsPmSelection physicnameLike(String... value) {
        addLike(DiifsPmColumns.PHYSICNAME, value);
        return this;
    }

    public DiifsPmSelection physicnameContains(String... value) {
        addContains(DiifsPmColumns.PHYSICNAME, value);
        return this;
    }

    public DiifsPmSelection physicnameStartsWith(String... value) {
        addStartsWith(DiifsPmColumns.PHYSICNAME, value);
        return this;
    }

    public DiifsPmSelection physicnameEndsWith(String... value) {
        addEndsWith(DiifsPmColumns.PHYSICNAME, value);
        return this;
    }

    public DiifsPmSelection orderByPhysicname(boolean desc) {
        orderBy(DiifsPmColumns.PHYSICNAME, desc);
        return this;
    }

    public DiifsPmSelection orderByPhysicname() {
        orderBy(DiifsPmColumns.PHYSICNAME, false);
        return this;
    }

    public DiifsPmSelection physiccode(String... value) {
        addEquals(DiifsPmColumns.PHYSICCODE, value);
        return this;
    }

    public DiifsPmSelection physiccodeNot(String... value) {
        addNotEquals(DiifsPmColumns.PHYSICCODE, value);
        return this;
    }

    public DiifsPmSelection physiccodeLike(String... value) {
        addLike(DiifsPmColumns.PHYSICCODE, value);
        return this;
    }

    public DiifsPmSelection physiccodeContains(String... value) {
        addContains(DiifsPmColumns.PHYSICCODE, value);
        return this;
    }

    public DiifsPmSelection physiccodeStartsWith(String... value) {
        addStartsWith(DiifsPmColumns.PHYSICCODE, value);
        return this;
    }

    public DiifsPmSelection physiccodeEndsWith(String... value) {
        addEndsWith(DiifsPmColumns.PHYSICCODE, value);
        return this;
    }

    public DiifsPmSelection orderByPhysiccode(boolean desc) {
        orderBy(DiifsPmColumns.PHYSICCODE, desc);
        return this;
    }

    public DiifsPmSelection orderByPhysiccode() {
        orderBy(DiifsPmColumns.PHYSICCODE, false);
        return this;
    }

    public DiifsPmSelection isdone(Boolean value) {
        addEquals(DiifsPmColumns.ISDONE, toObjectArray(value));
        return this;
    }

    public DiifsPmSelection orderByIsdone(boolean desc) {
        orderBy(DiifsPmColumns.ISDONE, desc);
        return this;
    }

    public DiifsPmSelection orderByIsdone() {
        orderBy(DiifsPmColumns.ISDONE, false);
        return this;
    }
}
