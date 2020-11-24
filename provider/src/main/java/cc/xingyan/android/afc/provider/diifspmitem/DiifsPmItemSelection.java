package cc.xingyan.android.afc.provider.diifspmitem;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code diifs_pm_item} table.
 */
public class DiifsPmItemSelection extends AbstractSelection<DiifsPmItemSelection> {
    @Override
    protected Uri baseUri() {
        return DiifsPmItemColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code DiifsPmItemCursor} object, which is positioned before the first entry, or null.
     */
    public DiifsPmItemCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new DiifsPmItemCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public DiifsPmItemCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code DiifsPmItemCursor} object, which is positioned before the first entry, or null.
     */
    public DiifsPmItemCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new DiifsPmItemCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public DiifsPmItemCursor query(Context context) {
        return query(context, null);
    }


    public DiifsPmItemSelection id(long... value) {
        addEquals("diifs_pm_item." + DiifsPmItemColumns._ID, toObjectArray(value));
        return this;
    }

    public DiifsPmItemSelection idNot(long... value) {
        addNotEquals("diifs_pm_item." + DiifsPmItemColumns._ID, toObjectArray(value));
        return this;
    }

    public DiifsPmItemSelection orderById(boolean desc) {
        orderBy("diifs_pm_item." + DiifsPmItemColumns._ID, desc);
        return this;
    }

    public DiifsPmItemSelection orderById() {
        return orderById(false);
    }

    public DiifsPmItemSelection wono(String... value) {
        addEquals(DiifsPmItemColumns.WONO, value);
        return this;
    }

    public DiifsPmItemSelection wonoNot(String... value) {
        addNotEquals(DiifsPmItemColumns.WONO, value);
        return this;
    }

    public DiifsPmItemSelection wonoLike(String... value) {
        addLike(DiifsPmItemColumns.WONO, value);
        return this;
    }

    public DiifsPmItemSelection wonoContains(String... value) {
        addContains(DiifsPmItemColumns.WONO, value);
        return this;
    }

    public DiifsPmItemSelection wonoStartsWith(String... value) {
        addStartsWith(DiifsPmItemColumns.WONO, value);
        return this;
    }

    public DiifsPmItemSelection wonoEndsWith(String... value) {
        addEndsWith(DiifsPmItemColumns.WONO, value);
        return this;
    }

    public DiifsPmItemSelection orderByWono(boolean desc) {
        orderBy(DiifsPmItemColumns.WONO, desc);
        return this;
    }

    public DiifsPmItemSelection orderByWono() {
        orderBy(DiifsPmItemColumns.WONO, false);
        return this;
    }

    public DiifsPmItemSelection pmno(String... value) {
        addEquals(DiifsPmItemColumns.PMNO, value);
        return this;
    }

    public DiifsPmItemSelection pmnoNot(String... value) {
        addNotEquals(DiifsPmItemColumns.PMNO, value);
        return this;
    }

    public DiifsPmItemSelection pmnoLike(String... value) {
        addLike(DiifsPmItemColumns.PMNO, value);
        return this;
    }

    public DiifsPmItemSelection pmnoContains(String... value) {
        addContains(DiifsPmItemColumns.PMNO, value);
        return this;
    }

    public DiifsPmItemSelection pmnoStartsWith(String... value) {
        addStartsWith(DiifsPmItemColumns.PMNO, value);
        return this;
    }

    public DiifsPmItemSelection pmnoEndsWith(String... value) {
        addEndsWith(DiifsPmItemColumns.PMNO, value);
        return this;
    }

    public DiifsPmItemSelection orderByPmno(boolean desc) {
        orderBy(DiifsPmItemColumns.PMNO, desc);
        return this;
    }

    public DiifsPmItemSelection orderByPmno() {
        orderBy(DiifsPmItemColumns.PMNO, false);
        return this;
    }

    public DiifsPmItemSelection testpointid(String... value) {
        addEquals(DiifsPmItemColumns.TESTPOINTID, value);
        return this;
    }

    public DiifsPmItemSelection testpointidNot(String... value) {
        addNotEquals(DiifsPmItemColumns.TESTPOINTID, value);
        return this;
    }

    public DiifsPmItemSelection testpointidLike(String... value) {
        addLike(DiifsPmItemColumns.TESTPOINTID, value);
        return this;
    }

    public DiifsPmItemSelection testpointidContains(String... value) {
        addContains(DiifsPmItemColumns.TESTPOINTID, value);
        return this;
    }

    public DiifsPmItemSelection testpointidStartsWith(String... value) {
        addStartsWith(DiifsPmItemColumns.TESTPOINTID, value);
        return this;
    }

    public DiifsPmItemSelection testpointidEndsWith(String... value) {
        addEndsWith(DiifsPmItemColumns.TESTPOINTID, value);
        return this;
    }

    public DiifsPmItemSelection orderByTestpointid(boolean desc) {
        orderBy(DiifsPmItemColumns.TESTPOINTID, desc);
        return this;
    }

    public DiifsPmItemSelection orderByTestpointid() {
        orderBy(DiifsPmItemColumns.TESTPOINTID, false);
        return this;
    }

    public DiifsPmItemSelection parametercode(String... value) {
        addEquals(DiifsPmItemColumns.PARAMETERCODE, value);
        return this;
    }

    public DiifsPmItemSelection parametercodeNot(String... value) {
        addNotEquals(DiifsPmItemColumns.PARAMETERCODE, value);
        return this;
    }

    public DiifsPmItemSelection parametercodeLike(String... value) {
        addLike(DiifsPmItemColumns.PARAMETERCODE, value);
        return this;
    }

    public DiifsPmItemSelection parametercodeContains(String... value) {
        addContains(DiifsPmItemColumns.PARAMETERCODE, value);
        return this;
    }

    public DiifsPmItemSelection parametercodeStartsWith(String... value) {
        addStartsWith(DiifsPmItemColumns.PARAMETERCODE, value);
        return this;
    }

    public DiifsPmItemSelection parametercodeEndsWith(String... value) {
        addEndsWith(DiifsPmItemColumns.PARAMETERCODE, value);
        return this;
    }

    public DiifsPmItemSelection orderByParametercode(boolean desc) {
        orderBy(DiifsPmItemColumns.PARAMETERCODE, desc);
        return this;
    }

    public DiifsPmItemSelection orderByParametercode() {
        orderBy(DiifsPmItemColumns.PARAMETERCODE, false);
        return this;
    }

    public DiifsPmItemSelection parameterdesc(String... value) {
        addEquals(DiifsPmItemColumns.PARAMETERDESC, value);
        return this;
    }

    public DiifsPmItemSelection parameterdescNot(String... value) {
        addNotEquals(DiifsPmItemColumns.PARAMETERDESC, value);
        return this;
    }

    public DiifsPmItemSelection parameterdescLike(String... value) {
        addLike(DiifsPmItemColumns.PARAMETERDESC, value);
        return this;
    }

    public DiifsPmItemSelection parameterdescContains(String... value) {
        addContains(DiifsPmItemColumns.PARAMETERDESC, value);
        return this;
    }

    public DiifsPmItemSelection parameterdescStartsWith(String... value) {
        addStartsWith(DiifsPmItemColumns.PARAMETERDESC, value);
        return this;
    }

    public DiifsPmItemSelection parameterdescEndsWith(String... value) {
        addEndsWith(DiifsPmItemColumns.PARAMETERDESC, value);
        return this;
    }

    public DiifsPmItemSelection orderByParameterdesc(boolean desc) {
        orderBy(DiifsPmItemColumns.PARAMETERDESC, desc);
        return this;
    }

    public DiifsPmItemSelection orderByParameterdesc() {
        orderBy(DiifsPmItemColumns.PARAMETERDESC, false);
        return this;
    }

    public DiifsPmItemSelection parametertype(String... value) {
        addEquals(DiifsPmItemColumns.PARAMETERTYPE, value);
        return this;
    }

    public DiifsPmItemSelection parametertypeNot(String... value) {
        addNotEquals(DiifsPmItemColumns.PARAMETERTYPE, value);
        return this;
    }

    public DiifsPmItemSelection parametertypeLike(String... value) {
        addLike(DiifsPmItemColumns.PARAMETERTYPE, value);
        return this;
    }

    public DiifsPmItemSelection parametertypeContains(String... value) {
        addContains(DiifsPmItemColumns.PARAMETERTYPE, value);
        return this;
    }

    public DiifsPmItemSelection parametertypeStartsWith(String... value) {
        addStartsWith(DiifsPmItemColumns.PARAMETERTYPE, value);
        return this;
    }

    public DiifsPmItemSelection parametertypeEndsWith(String... value) {
        addEndsWith(DiifsPmItemColumns.PARAMETERTYPE, value);
        return this;
    }

    public DiifsPmItemSelection orderByParametertype(boolean desc) {
        orderBy(DiifsPmItemColumns.PARAMETERTYPE, desc);
        return this;
    }

    public DiifsPmItemSelection orderByParametertype() {
        orderBy(DiifsPmItemColumns.PARAMETERTYPE, false);
        return this;
    }

    public DiifsPmItemSelection unit(String... value) {
        addEquals(DiifsPmItemColumns.UNIT, value);
        return this;
    }

    public DiifsPmItemSelection unitNot(String... value) {
        addNotEquals(DiifsPmItemColumns.UNIT, value);
        return this;
    }

    public DiifsPmItemSelection unitLike(String... value) {
        addLike(DiifsPmItemColumns.UNIT, value);
        return this;
    }

    public DiifsPmItemSelection unitContains(String... value) {
        addContains(DiifsPmItemColumns.UNIT, value);
        return this;
    }

    public DiifsPmItemSelection unitStartsWith(String... value) {
        addStartsWith(DiifsPmItemColumns.UNIT, value);
        return this;
    }

    public DiifsPmItemSelection unitEndsWith(String... value) {
        addEndsWith(DiifsPmItemColumns.UNIT, value);
        return this;
    }

    public DiifsPmItemSelection orderByUnit(boolean desc) {
        orderBy(DiifsPmItemColumns.UNIT, desc);
        return this;
    }

    public DiifsPmItemSelection orderByUnit() {
        orderBy(DiifsPmItemColumns.UNIT, false);
        return this;
    }

    public DiifsPmItemSelection minvalue(String... value) {
        addEquals(DiifsPmItemColumns.MINVALUE, value);
        return this;
    }

    public DiifsPmItemSelection minvalueNot(String... value) {
        addNotEquals(DiifsPmItemColumns.MINVALUE, value);
        return this;
    }

    public DiifsPmItemSelection minvalueLike(String... value) {
        addLike(DiifsPmItemColumns.MINVALUE, value);
        return this;
    }

    public DiifsPmItemSelection minvalueContains(String... value) {
        addContains(DiifsPmItemColumns.MINVALUE, value);
        return this;
    }

    public DiifsPmItemSelection minvalueStartsWith(String... value) {
        addStartsWith(DiifsPmItemColumns.MINVALUE, value);
        return this;
    }

    public DiifsPmItemSelection minvalueEndsWith(String... value) {
        addEndsWith(DiifsPmItemColumns.MINVALUE, value);
        return this;
    }

    public DiifsPmItemSelection orderByMinvalue(boolean desc) {
        orderBy(DiifsPmItemColumns.MINVALUE, desc);
        return this;
    }

    public DiifsPmItemSelection orderByMinvalue() {
        orderBy(DiifsPmItemColumns.MINVALUE, false);
        return this;
    }

    public DiifsPmItemSelection maxvalue(String... value) {
        addEquals(DiifsPmItemColumns.MAXVALUE, value);
        return this;
    }

    public DiifsPmItemSelection maxvalueNot(String... value) {
        addNotEquals(DiifsPmItemColumns.MAXVALUE, value);
        return this;
    }

    public DiifsPmItemSelection maxvalueLike(String... value) {
        addLike(DiifsPmItemColumns.MAXVALUE, value);
        return this;
    }

    public DiifsPmItemSelection maxvalueContains(String... value) {
        addContains(DiifsPmItemColumns.MAXVALUE, value);
        return this;
    }

    public DiifsPmItemSelection maxvalueStartsWith(String... value) {
        addStartsWith(DiifsPmItemColumns.MAXVALUE, value);
        return this;
    }

    public DiifsPmItemSelection maxvalueEndsWith(String... value) {
        addEndsWith(DiifsPmItemColumns.MAXVALUE, value);
        return this;
    }

    public DiifsPmItemSelection orderByMaxvalue(boolean desc) {
        orderBy(DiifsPmItemColumns.MAXVALUE, desc);
        return this;
    }

    public DiifsPmItemSelection orderByMaxvalue() {
        orderBy(DiifsPmItemColumns.MAXVALUE, false);
        return this;
    }

    public DiifsPmItemSelection startvalue(String... value) {
        addEquals(DiifsPmItemColumns.STARTVALUE, value);
        return this;
    }

    public DiifsPmItemSelection startvalueNot(String... value) {
        addNotEquals(DiifsPmItemColumns.STARTVALUE, value);
        return this;
    }

    public DiifsPmItemSelection startvalueLike(String... value) {
        addLike(DiifsPmItemColumns.STARTVALUE, value);
        return this;
    }

    public DiifsPmItemSelection startvalueContains(String... value) {
        addContains(DiifsPmItemColumns.STARTVALUE, value);
        return this;
    }

    public DiifsPmItemSelection startvalueStartsWith(String... value) {
        addStartsWith(DiifsPmItemColumns.STARTVALUE, value);
        return this;
    }

    public DiifsPmItemSelection startvalueEndsWith(String... value) {
        addEndsWith(DiifsPmItemColumns.STARTVALUE, value);
        return this;
    }

    public DiifsPmItemSelection orderByStartvalue(boolean desc) {
        orderBy(DiifsPmItemColumns.STARTVALUE, desc);
        return this;
    }

    public DiifsPmItemSelection orderByStartvalue() {
        orderBy(DiifsPmItemColumns.STARTVALUE, false);
        return this;
    }

    public DiifsPmItemSelection interval(String... value) {
        addEquals(DiifsPmItemColumns.INTERVAL, value);
        return this;
    }

    public DiifsPmItemSelection intervalNot(String... value) {
        addNotEquals(DiifsPmItemColumns.INTERVAL, value);
        return this;
    }

    public DiifsPmItemSelection intervalLike(String... value) {
        addLike(DiifsPmItemColumns.INTERVAL, value);
        return this;
    }

    public DiifsPmItemSelection intervalContains(String... value) {
        addContains(DiifsPmItemColumns.INTERVAL, value);
        return this;
    }

    public DiifsPmItemSelection intervalStartsWith(String... value) {
        addStartsWith(DiifsPmItemColumns.INTERVAL, value);
        return this;
    }

    public DiifsPmItemSelection intervalEndsWith(String... value) {
        addEndsWith(DiifsPmItemColumns.INTERVAL, value);
        return this;
    }

    public DiifsPmItemSelection orderByInterval(boolean desc) {
        orderBy(DiifsPmItemColumns.INTERVAL, desc);
        return this;
    }

    public DiifsPmItemSelection orderByInterval() {
        orderBy(DiifsPmItemColumns.INTERVAL, false);
        return this;
    }

    public DiifsPmItemSelection lastvalue(String... value) {
        addEquals(DiifsPmItemColumns.LASTVALUE, value);
        return this;
    }

    public DiifsPmItemSelection lastvalueNot(String... value) {
        addNotEquals(DiifsPmItemColumns.LASTVALUE, value);
        return this;
    }

    public DiifsPmItemSelection lastvalueLike(String... value) {
        addLike(DiifsPmItemColumns.LASTVALUE, value);
        return this;
    }

    public DiifsPmItemSelection lastvalueContains(String... value) {
        addContains(DiifsPmItemColumns.LASTVALUE, value);
        return this;
    }

    public DiifsPmItemSelection lastvalueStartsWith(String... value) {
        addStartsWith(DiifsPmItemColumns.LASTVALUE, value);
        return this;
    }

    public DiifsPmItemSelection lastvalueEndsWith(String... value) {
        addEndsWith(DiifsPmItemColumns.LASTVALUE, value);
        return this;
    }

    public DiifsPmItemSelection orderByLastvalue(boolean desc) {
        orderBy(DiifsPmItemColumns.LASTVALUE, desc);
        return this;
    }

    public DiifsPmItemSelection orderByLastvalue() {
        orderBy(DiifsPmItemColumns.LASTVALUE, false);
        return this;
    }

    public DiifsPmItemSelection pmgenvalue(String... value) {
        addEquals(DiifsPmItemColumns.PMGENVALUE, value);
        return this;
    }

    public DiifsPmItemSelection pmgenvalueNot(String... value) {
        addNotEquals(DiifsPmItemColumns.PMGENVALUE, value);
        return this;
    }

    public DiifsPmItemSelection pmgenvalueLike(String... value) {
        addLike(DiifsPmItemColumns.PMGENVALUE, value);
        return this;
    }

    public DiifsPmItemSelection pmgenvalueContains(String... value) {
        addContains(DiifsPmItemColumns.PMGENVALUE, value);
        return this;
    }

    public DiifsPmItemSelection pmgenvalueStartsWith(String... value) {
        addStartsWith(DiifsPmItemColumns.PMGENVALUE, value);
        return this;
    }

    public DiifsPmItemSelection pmgenvalueEndsWith(String... value) {
        addEndsWith(DiifsPmItemColumns.PMGENVALUE, value);
        return this;
    }

    public DiifsPmItemSelection orderByPmgenvalue(boolean desc) {
        orderBy(DiifsPmItemColumns.PMGENVALUE, desc);
        return this;
    }

    public DiifsPmItemSelection orderByPmgenvalue() {
        orderBy(DiifsPmItemColumns.PMGENVALUE, false);
        return this;
    }

    public DiifsPmItemSelection objid(String... value) {
        addEquals(DiifsPmItemColumns.OBJID, value);
        return this;
    }

    public DiifsPmItemSelection objidNot(String... value) {
        addNotEquals(DiifsPmItemColumns.OBJID, value);
        return this;
    }

    public DiifsPmItemSelection objidLike(String... value) {
        addLike(DiifsPmItemColumns.OBJID, value);
        return this;
    }

    public DiifsPmItemSelection objidContains(String... value) {
        addContains(DiifsPmItemColumns.OBJID, value);
        return this;
    }

    public DiifsPmItemSelection objidStartsWith(String... value) {
        addStartsWith(DiifsPmItemColumns.OBJID, value);
        return this;
    }

    public DiifsPmItemSelection objidEndsWith(String... value) {
        addEndsWith(DiifsPmItemColumns.OBJID, value);
        return this;
    }

    public DiifsPmItemSelection orderByObjid(boolean desc) {
        orderBy(DiifsPmItemColumns.OBJID, desc);
        return this;
    }

    public DiifsPmItemSelection orderByObjid() {
        orderBy(DiifsPmItemColumns.OBJID, false);
        return this;
    }

    public DiifsPmItemSelection objversion(String... value) {
        addEquals(DiifsPmItemColumns.OBJVERSION, value);
        return this;
    }

    public DiifsPmItemSelection objversionNot(String... value) {
        addNotEquals(DiifsPmItemColumns.OBJVERSION, value);
        return this;
    }

    public DiifsPmItemSelection objversionLike(String... value) {
        addLike(DiifsPmItemColumns.OBJVERSION, value);
        return this;
    }

    public DiifsPmItemSelection objversionContains(String... value) {
        addContains(DiifsPmItemColumns.OBJVERSION, value);
        return this;
    }

    public DiifsPmItemSelection objversionStartsWith(String... value) {
        addStartsWith(DiifsPmItemColumns.OBJVERSION, value);
        return this;
    }

    public DiifsPmItemSelection objversionEndsWith(String... value) {
        addEndsWith(DiifsPmItemColumns.OBJVERSION, value);
        return this;
    }

    public DiifsPmItemSelection orderByObjversion(boolean desc) {
        orderBy(DiifsPmItemColumns.OBJVERSION, desc);
        return this;
    }

    public DiifsPmItemSelection orderByObjversion() {
        orderBy(DiifsPmItemColumns.OBJVERSION, false);
        return this;
    }
}
