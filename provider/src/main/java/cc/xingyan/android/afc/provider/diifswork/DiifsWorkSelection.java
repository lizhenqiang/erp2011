package cc.xingyan.android.afc.provider.diifswork;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code diifs_work} table.
 */
public class DiifsWorkSelection extends AbstractSelection<DiifsWorkSelection> {
    @Override
    protected Uri baseUri() {
        return DiifsWorkColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code DiifsWorkCursor} object, which is positioned before the first entry, or null.
     */
    public DiifsWorkCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new DiifsWorkCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public DiifsWorkCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code DiifsWorkCursor} object, which is positioned before the first entry, or null.
     */
    public DiifsWorkCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new DiifsWorkCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public DiifsWorkCursor query(Context context) {
        return query(context, null);
    }


    public DiifsWorkSelection id(long... value) {
        addEquals("diifs_work." + DiifsWorkColumns._ID, toObjectArray(value));
        return this;
    }

    public DiifsWorkSelection idNot(long... value) {
        addNotEquals("diifs_work." + DiifsWorkColumns._ID, toObjectArray(value));
        return this;
    }

    public DiifsWorkSelection orderById(boolean desc) {
        orderBy("diifs_work." + DiifsWorkColumns._ID, desc);
        return this;
    }

    public DiifsWorkSelection orderById() {
        return orderById(false);
    }

    public DiifsWorkSelection wono(String... value) {
        addEquals(DiifsWorkColumns.WONO, value);
        return this;
    }

    public DiifsWorkSelection wonoNot(String... value) {
        addNotEquals(DiifsWorkColumns.WONO, value);
        return this;
    }

    public DiifsWorkSelection wonoLike(String... value) {
        addLike(DiifsWorkColumns.WONO, value);
        return this;
    }

    public DiifsWorkSelection wonoContains(String... value) {
        addContains(DiifsWorkColumns.WONO, value);
        return this;
    }

    public DiifsWorkSelection wonoStartsWith(String... value) {
        addStartsWith(DiifsWorkColumns.WONO, value);
        return this;
    }

    public DiifsWorkSelection wonoEndsWith(String... value) {
        addEndsWith(DiifsWorkColumns.WONO, value);
        return this;
    }

    public DiifsWorkSelection orderByWono(boolean desc) {
        orderBy(DiifsWorkColumns.WONO, desc);
        return this;
    }

    public DiifsWorkSelection orderByWono() {
        orderBy(DiifsWorkColumns.WONO, false);
        return this;
    }

    public DiifsWorkSelection pmno(String... value) {
        addEquals(DiifsWorkColumns.PMNO, value);
        return this;
    }

    public DiifsWorkSelection pmnoNot(String... value) {
        addNotEquals(DiifsWorkColumns.PMNO, value);
        return this;
    }

    public DiifsWorkSelection pmnoLike(String... value) {
        addLike(DiifsWorkColumns.PMNO, value);
        return this;
    }

    public DiifsWorkSelection pmnoContains(String... value) {
        addContains(DiifsWorkColumns.PMNO, value);
        return this;
    }

    public DiifsWorkSelection pmnoStartsWith(String... value) {
        addStartsWith(DiifsWorkColumns.PMNO, value);
        return this;
    }

    public DiifsWorkSelection pmnoEndsWith(String... value) {
        addEndsWith(DiifsWorkColumns.PMNO, value);
        return this;
    }

    public DiifsWorkSelection orderByPmno(boolean desc) {
        orderBy(DiifsWorkColumns.PMNO, desc);
        return this;
    }

    public DiifsWorkSelection orderByPmno() {
        orderBy(DiifsWorkColumns.PMNO, false);
        return this;
    }

    public DiifsWorkSelection testpointid(String... value) {
        addEquals(DiifsWorkColumns.TESTPOINTID, value);
        return this;
    }

    public DiifsWorkSelection testpointidNot(String... value) {
        addNotEquals(DiifsWorkColumns.TESTPOINTID, value);
        return this;
    }

    public DiifsWorkSelection testpointidLike(String... value) {
        addLike(DiifsWorkColumns.TESTPOINTID, value);
        return this;
    }

    public DiifsWorkSelection testpointidContains(String... value) {
        addContains(DiifsWorkColumns.TESTPOINTID, value);
        return this;
    }

    public DiifsWorkSelection testpointidStartsWith(String... value) {
        addStartsWith(DiifsWorkColumns.TESTPOINTID, value);
        return this;
    }

    public DiifsWorkSelection testpointidEndsWith(String... value) {
        addEndsWith(DiifsWorkColumns.TESTPOINTID, value);
        return this;
    }

    public DiifsWorkSelection orderByTestpointid(boolean desc) {
        orderBy(DiifsWorkColumns.TESTPOINTID, desc);
        return this;
    }

    public DiifsWorkSelection orderByTestpointid() {
        orderBy(DiifsWorkColumns.TESTPOINTID, false);
        return this;
    }

    public DiifsWorkSelection parametercode(String... value) {
        addEquals(DiifsWorkColumns.PARAMETERCODE, value);
        return this;
    }

    public DiifsWorkSelection parametercodeNot(String... value) {
        addNotEquals(DiifsWorkColumns.PARAMETERCODE, value);
        return this;
    }

    public DiifsWorkSelection parametercodeLike(String... value) {
        addLike(DiifsWorkColumns.PARAMETERCODE, value);
        return this;
    }

    public DiifsWorkSelection parametercodeContains(String... value) {
        addContains(DiifsWorkColumns.PARAMETERCODE, value);
        return this;
    }

    public DiifsWorkSelection parametercodeStartsWith(String... value) {
        addStartsWith(DiifsWorkColumns.PARAMETERCODE, value);
        return this;
    }

    public DiifsWorkSelection parametercodeEndsWith(String... value) {
        addEndsWith(DiifsWorkColumns.PARAMETERCODE, value);
        return this;
    }

    public DiifsWorkSelection orderByParametercode(boolean desc) {
        orderBy(DiifsWorkColumns.PARAMETERCODE, desc);
        return this;
    }

    public DiifsWorkSelection orderByParametercode() {
        orderBy(DiifsWorkColumns.PARAMETERCODE, false);
        return this;
    }

    public DiifsWorkSelection parameterdesc(String... value) {
        addEquals(DiifsWorkColumns.PARAMETERDESC, value);
        return this;
    }

    public DiifsWorkSelection parameterdescNot(String... value) {
        addNotEquals(DiifsWorkColumns.PARAMETERDESC, value);
        return this;
    }

    public DiifsWorkSelection parameterdescLike(String... value) {
        addLike(DiifsWorkColumns.PARAMETERDESC, value);
        return this;
    }

    public DiifsWorkSelection parameterdescContains(String... value) {
        addContains(DiifsWorkColumns.PARAMETERDESC, value);
        return this;
    }

    public DiifsWorkSelection parameterdescStartsWith(String... value) {
        addStartsWith(DiifsWorkColumns.PARAMETERDESC, value);
        return this;
    }

    public DiifsWorkSelection parameterdescEndsWith(String... value) {
        addEndsWith(DiifsWorkColumns.PARAMETERDESC, value);
        return this;
    }

    public DiifsWorkSelection orderByParameterdesc(boolean desc) {
        orderBy(DiifsWorkColumns.PARAMETERDESC, desc);
        return this;
    }

    public DiifsWorkSelection orderByParameterdesc() {
        orderBy(DiifsWorkColumns.PARAMETERDESC, false);
        return this;
    }

    public DiifsWorkSelection parametertype(String... value) {
        addEquals(DiifsWorkColumns.PARAMETERTYPE, value);
        return this;
    }

    public DiifsWorkSelection parametertypeNot(String... value) {
        addNotEquals(DiifsWorkColumns.PARAMETERTYPE, value);
        return this;
    }

    public DiifsWorkSelection parametertypeLike(String... value) {
        addLike(DiifsWorkColumns.PARAMETERTYPE, value);
        return this;
    }

    public DiifsWorkSelection parametertypeContains(String... value) {
        addContains(DiifsWorkColumns.PARAMETERTYPE, value);
        return this;
    }

    public DiifsWorkSelection parametertypeStartsWith(String... value) {
        addStartsWith(DiifsWorkColumns.PARAMETERTYPE, value);
        return this;
    }

    public DiifsWorkSelection parametertypeEndsWith(String... value) {
        addEndsWith(DiifsWorkColumns.PARAMETERTYPE, value);
        return this;
    }

    public DiifsWorkSelection orderByParametertype(boolean desc) {
        orderBy(DiifsWorkColumns.PARAMETERTYPE, desc);
        return this;
    }

    public DiifsWorkSelection orderByParametertype() {
        orderBy(DiifsWorkColumns.PARAMETERTYPE, false);
        return this;
    }

    public DiifsWorkSelection unit(String... value) {
        addEquals(DiifsWorkColumns.UNIT, value);
        return this;
    }

    public DiifsWorkSelection unitNot(String... value) {
        addNotEquals(DiifsWorkColumns.UNIT, value);
        return this;
    }

    public DiifsWorkSelection unitLike(String... value) {
        addLike(DiifsWorkColumns.UNIT, value);
        return this;
    }

    public DiifsWorkSelection unitContains(String... value) {
        addContains(DiifsWorkColumns.UNIT, value);
        return this;
    }

    public DiifsWorkSelection unitStartsWith(String... value) {
        addStartsWith(DiifsWorkColumns.UNIT, value);
        return this;
    }

    public DiifsWorkSelection unitEndsWith(String... value) {
        addEndsWith(DiifsWorkColumns.UNIT, value);
        return this;
    }

    public DiifsWorkSelection orderByUnit(boolean desc) {
        orderBy(DiifsWorkColumns.UNIT, desc);
        return this;
    }

    public DiifsWorkSelection orderByUnit() {
        orderBy(DiifsWorkColumns.UNIT, false);
        return this;
    }

    public DiifsWorkSelection minvalue(String... value) {
        addEquals(DiifsWorkColumns.MINVALUE, value);
        return this;
    }

    public DiifsWorkSelection minvalueNot(String... value) {
        addNotEquals(DiifsWorkColumns.MINVALUE, value);
        return this;
    }

    public DiifsWorkSelection minvalueLike(String... value) {
        addLike(DiifsWorkColumns.MINVALUE, value);
        return this;
    }

    public DiifsWorkSelection minvalueContains(String... value) {
        addContains(DiifsWorkColumns.MINVALUE, value);
        return this;
    }

    public DiifsWorkSelection minvalueStartsWith(String... value) {
        addStartsWith(DiifsWorkColumns.MINVALUE, value);
        return this;
    }

    public DiifsWorkSelection minvalueEndsWith(String... value) {
        addEndsWith(DiifsWorkColumns.MINVALUE, value);
        return this;
    }

    public DiifsWorkSelection orderByMinvalue(boolean desc) {
        orderBy(DiifsWorkColumns.MINVALUE, desc);
        return this;
    }

    public DiifsWorkSelection orderByMinvalue() {
        orderBy(DiifsWorkColumns.MINVALUE, false);
        return this;
    }

    public DiifsWorkSelection maxvalue(String... value) {
        addEquals(DiifsWorkColumns.MAXVALUE, value);
        return this;
    }

    public DiifsWorkSelection maxvalueNot(String... value) {
        addNotEquals(DiifsWorkColumns.MAXVALUE, value);
        return this;
    }

    public DiifsWorkSelection maxvalueLike(String... value) {
        addLike(DiifsWorkColumns.MAXVALUE, value);
        return this;
    }

    public DiifsWorkSelection maxvalueContains(String... value) {
        addContains(DiifsWorkColumns.MAXVALUE, value);
        return this;
    }

    public DiifsWorkSelection maxvalueStartsWith(String... value) {
        addStartsWith(DiifsWorkColumns.MAXVALUE, value);
        return this;
    }

    public DiifsWorkSelection maxvalueEndsWith(String... value) {
        addEndsWith(DiifsWorkColumns.MAXVALUE, value);
        return this;
    }

    public DiifsWorkSelection orderByMaxvalue(boolean desc) {
        orderBy(DiifsWorkColumns.MAXVALUE, desc);
        return this;
    }

    public DiifsWorkSelection orderByMaxvalue() {
        orderBy(DiifsWorkColumns.MAXVALUE, false);
        return this;
    }

    public DiifsWorkSelection startvalue(String... value) {
        addEquals(DiifsWorkColumns.STARTVALUE, value);
        return this;
    }

    public DiifsWorkSelection startvalueNot(String... value) {
        addNotEquals(DiifsWorkColumns.STARTVALUE, value);
        return this;
    }

    public DiifsWorkSelection startvalueLike(String... value) {
        addLike(DiifsWorkColumns.STARTVALUE, value);
        return this;
    }

    public DiifsWorkSelection startvalueContains(String... value) {
        addContains(DiifsWorkColumns.STARTVALUE, value);
        return this;
    }

    public DiifsWorkSelection startvalueStartsWith(String... value) {
        addStartsWith(DiifsWorkColumns.STARTVALUE, value);
        return this;
    }

    public DiifsWorkSelection startvalueEndsWith(String... value) {
        addEndsWith(DiifsWorkColumns.STARTVALUE, value);
        return this;
    }

    public DiifsWorkSelection orderByStartvalue(boolean desc) {
        orderBy(DiifsWorkColumns.STARTVALUE, desc);
        return this;
    }

    public DiifsWorkSelection orderByStartvalue() {
        orderBy(DiifsWorkColumns.STARTVALUE, false);
        return this;
    }

    public DiifsWorkSelection interval(String... value) {
        addEquals(DiifsWorkColumns.INTERVAL, value);
        return this;
    }

    public DiifsWorkSelection intervalNot(String... value) {
        addNotEquals(DiifsWorkColumns.INTERVAL, value);
        return this;
    }

    public DiifsWorkSelection intervalLike(String... value) {
        addLike(DiifsWorkColumns.INTERVAL, value);
        return this;
    }

    public DiifsWorkSelection intervalContains(String... value) {
        addContains(DiifsWorkColumns.INTERVAL, value);
        return this;
    }

    public DiifsWorkSelection intervalStartsWith(String... value) {
        addStartsWith(DiifsWorkColumns.INTERVAL, value);
        return this;
    }

    public DiifsWorkSelection intervalEndsWith(String... value) {
        addEndsWith(DiifsWorkColumns.INTERVAL, value);
        return this;
    }

    public DiifsWorkSelection orderByInterval(boolean desc) {
        orderBy(DiifsWorkColumns.INTERVAL, desc);
        return this;
    }

    public DiifsWorkSelection orderByInterval() {
        orderBy(DiifsWorkColumns.INTERVAL, false);
        return this;
    }

    public DiifsWorkSelection lastvalue(String... value) {
        addEquals(DiifsWorkColumns.LASTVALUE, value);
        return this;
    }

    public DiifsWorkSelection lastvalueNot(String... value) {
        addNotEquals(DiifsWorkColumns.LASTVALUE, value);
        return this;
    }

    public DiifsWorkSelection lastvalueLike(String... value) {
        addLike(DiifsWorkColumns.LASTVALUE, value);
        return this;
    }

    public DiifsWorkSelection lastvalueContains(String... value) {
        addContains(DiifsWorkColumns.LASTVALUE, value);
        return this;
    }

    public DiifsWorkSelection lastvalueStartsWith(String... value) {
        addStartsWith(DiifsWorkColumns.LASTVALUE, value);
        return this;
    }

    public DiifsWorkSelection lastvalueEndsWith(String... value) {
        addEndsWith(DiifsWorkColumns.LASTVALUE, value);
        return this;
    }

    public DiifsWorkSelection orderByLastvalue(boolean desc) {
        orderBy(DiifsWorkColumns.LASTVALUE, desc);
        return this;
    }

    public DiifsWorkSelection orderByLastvalue() {
        orderBy(DiifsWorkColumns.LASTVALUE, false);
        return this;
    }

    public DiifsWorkSelection pmgenvalue(String... value) {
        addEquals(DiifsWorkColumns.PMGENVALUE, value);
        return this;
    }

    public DiifsWorkSelection pmgenvalueNot(String... value) {
        addNotEquals(DiifsWorkColumns.PMGENVALUE, value);
        return this;
    }

    public DiifsWorkSelection pmgenvalueLike(String... value) {
        addLike(DiifsWorkColumns.PMGENVALUE, value);
        return this;
    }

    public DiifsWorkSelection pmgenvalueContains(String... value) {
        addContains(DiifsWorkColumns.PMGENVALUE, value);
        return this;
    }

    public DiifsWorkSelection pmgenvalueStartsWith(String... value) {
        addStartsWith(DiifsWorkColumns.PMGENVALUE, value);
        return this;
    }

    public DiifsWorkSelection pmgenvalueEndsWith(String... value) {
        addEndsWith(DiifsWorkColumns.PMGENVALUE, value);
        return this;
    }

    public DiifsWorkSelection orderByPmgenvalue(boolean desc) {
        orderBy(DiifsWorkColumns.PMGENVALUE, desc);
        return this;
    }

    public DiifsWorkSelection orderByPmgenvalue() {
        orderBy(DiifsWorkColumns.PMGENVALUE, false);
        return this;
    }

    public DiifsWorkSelection objid(String... value) {
        addEquals(DiifsWorkColumns.OBJID, value);
        return this;
    }

    public DiifsWorkSelection objidNot(String... value) {
        addNotEquals(DiifsWorkColumns.OBJID, value);
        return this;
    }

    public DiifsWorkSelection objidLike(String... value) {
        addLike(DiifsWorkColumns.OBJID, value);
        return this;
    }

    public DiifsWorkSelection objidContains(String... value) {
        addContains(DiifsWorkColumns.OBJID, value);
        return this;
    }

    public DiifsWorkSelection objidStartsWith(String... value) {
        addStartsWith(DiifsWorkColumns.OBJID, value);
        return this;
    }

    public DiifsWorkSelection objidEndsWith(String... value) {
        addEndsWith(DiifsWorkColumns.OBJID, value);
        return this;
    }

    public DiifsWorkSelection orderByObjid(boolean desc) {
        orderBy(DiifsWorkColumns.OBJID, desc);
        return this;
    }

    public DiifsWorkSelection orderByObjid() {
        orderBy(DiifsWorkColumns.OBJID, false);
        return this;
    }

    public DiifsWorkSelection objversion(String... value) {
        addEquals(DiifsWorkColumns.OBJVERSION, value);
        return this;
    }

    public DiifsWorkSelection objversionNot(String... value) {
        addNotEquals(DiifsWorkColumns.OBJVERSION, value);
        return this;
    }

    public DiifsWorkSelection objversionLike(String... value) {
        addLike(DiifsWorkColumns.OBJVERSION, value);
        return this;
    }

    public DiifsWorkSelection objversionContains(String... value) {
        addContains(DiifsWorkColumns.OBJVERSION, value);
        return this;
    }

    public DiifsWorkSelection objversionStartsWith(String... value) {
        addStartsWith(DiifsWorkColumns.OBJVERSION, value);
        return this;
    }

    public DiifsWorkSelection objversionEndsWith(String... value) {
        addEndsWith(DiifsWorkColumns.OBJVERSION, value);
        return this;
    }

    public DiifsWorkSelection orderByObjversion(boolean desc) {
        orderBy(DiifsWorkColumns.OBJVERSION, desc);
        return this;
    }

    public DiifsWorkSelection orderByObjversion() {
        orderBy(DiifsWorkColumns.OBJVERSION, false);
        return this;
    }

    public DiifsWorkSelection isupdate(Boolean value) {
        addEquals(DiifsWorkColumns.ISUPDATE, toObjectArray(value));
        return this;
    }

    public DiifsWorkSelection orderByIsupdate(boolean desc) {
        orderBy(DiifsWorkColumns.ISUPDATE, desc);
        return this;
    }

    public DiifsWorkSelection orderByIsupdate() {
        orderBy(DiifsWorkColumns.ISUPDATE, false);
        return this;
    }

    public DiifsWorkSelection physiccode(String... value) {
        addEquals(DiifsWorkColumns.PHYSICCODE, value);
        return this;
    }

    public DiifsWorkSelection physiccodeNot(String... value) {
        addNotEquals(DiifsWorkColumns.PHYSICCODE, value);
        return this;
    }

    public DiifsWorkSelection physiccodeLike(String... value) {
        addLike(DiifsWorkColumns.PHYSICCODE, value);
        return this;
    }

    public DiifsWorkSelection physiccodeContains(String... value) {
        addContains(DiifsWorkColumns.PHYSICCODE, value);
        return this;
    }

    public DiifsWorkSelection physiccodeStartsWith(String... value) {
        addStartsWith(DiifsWorkColumns.PHYSICCODE, value);
        return this;
    }

    public DiifsWorkSelection physiccodeEndsWith(String... value) {
        addEndsWith(DiifsWorkColumns.PHYSICCODE, value);
        return this;
    }

    public DiifsWorkSelection orderByPhysiccode(boolean desc) {
        orderBy(DiifsWorkColumns.PHYSICCODE, desc);
        return this;
    }

    public DiifsWorkSelection orderByPhysiccode() {
        orderBy(DiifsWorkColumns.PHYSICCODE, false);
        return this;
    }
}
