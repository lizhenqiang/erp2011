package cc.xingyan.android.afc.provider.diifswork;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code diifs_work} table.
 */
public class DiifsWorkContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return DiifsWorkColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  DiifsWorkSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  DiifsWorkSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public DiifsWorkContentValues putWono(String value) {
        mContentValues.put(DiifsWorkColumns.WONO, value);
        return this;
    }

    public DiifsWorkContentValues putWonoNull() {
        mContentValues.putNull(DiifsWorkColumns.WONO);
        return this;
    }

    public DiifsWorkContentValues putPmno(String value) {
        mContentValues.put(DiifsWorkColumns.PMNO, value);
        return this;
    }

    public DiifsWorkContentValues putPmnoNull() {
        mContentValues.putNull(DiifsWorkColumns.PMNO);
        return this;
    }

    public DiifsWorkContentValues putTestpointid(String value) {
        mContentValues.put(DiifsWorkColumns.TESTPOINTID, value);
        return this;
    }

    public DiifsWorkContentValues putTestpointidNull() {
        mContentValues.putNull(DiifsWorkColumns.TESTPOINTID);
        return this;
    }

    public DiifsWorkContentValues putParametercode(String value) {
        mContentValues.put(DiifsWorkColumns.PARAMETERCODE, value);
        return this;
    }

    public DiifsWorkContentValues putParametercodeNull() {
        mContentValues.putNull(DiifsWorkColumns.PARAMETERCODE);
        return this;
    }

    public DiifsWorkContentValues putParameterdesc(String value) {
        mContentValues.put(DiifsWorkColumns.PARAMETERDESC, value);
        return this;
    }

    public DiifsWorkContentValues putParameterdescNull() {
        mContentValues.putNull(DiifsWorkColumns.PARAMETERDESC);
        return this;
    }

    public DiifsWorkContentValues putParametertype(String value) {
        mContentValues.put(DiifsWorkColumns.PARAMETERTYPE, value);
        return this;
    }

    public DiifsWorkContentValues putParametertypeNull() {
        mContentValues.putNull(DiifsWorkColumns.PARAMETERTYPE);
        return this;
    }

    public DiifsWorkContentValues putUnit(String value) {
        mContentValues.put(DiifsWorkColumns.UNIT, value);
        return this;
    }

    public DiifsWorkContentValues putUnitNull() {
        mContentValues.putNull(DiifsWorkColumns.UNIT);
        return this;
    }

    public DiifsWorkContentValues putMinvalue(String value) {
        mContentValues.put(DiifsWorkColumns.MINVALUE, value);
        return this;
    }

    public DiifsWorkContentValues putMinvalueNull() {
        mContentValues.putNull(DiifsWorkColumns.MINVALUE);
        return this;
    }

    public DiifsWorkContentValues putMaxvalue(String value) {
        mContentValues.put(DiifsWorkColumns.MAXVALUE, value);
        return this;
    }

    public DiifsWorkContentValues putMaxvalueNull() {
        mContentValues.putNull(DiifsWorkColumns.MAXVALUE);
        return this;
    }

    public DiifsWorkContentValues putStartvalue(String value) {
        mContentValues.put(DiifsWorkColumns.STARTVALUE, value);
        return this;
    }

    public DiifsWorkContentValues putStartvalueNull() {
        mContentValues.putNull(DiifsWorkColumns.STARTVALUE);
        return this;
    }

    public DiifsWorkContentValues putInterval(String value) {
        mContentValues.put(DiifsWorkColumns.INTERVAL, value);
        return this;
    }

    public DiifsWorkContentValues putIntervalNull() {
        mContentValues.putNull(DiifsWorkColumns.INTERVAL);
        return this;
    }

    public DiifsWorkContentValues putLastvalue(String value) {
        mContentValues.put(DiifsWorkColumns.LASTVALUE, value);
        return this;
    }

    public DiifsWorkContentValues putLastvalueNull() {
        mContentValues.putNull(DiifsWorkColumns.LASTVALUE);
        return this;
    }

    public DiifsWorkContentValues putPmgenvalue(String value) {
        mContentValues.put(DiifsWorkColumns.PMGENVALUE, value);
        return this;
    }

    public DiifsWorkContentValues putPmgenvalueNull() {
        mContentValues.putNull(DiifsWorkColumns.PMGENVALUE);
        return this;
    }

    public DiifsWorkContentValues putObjid(String value) {
        mContentValues.put(DiifsWorkColumns.OBJID, value);
        return this;
    }

    public DiifsWorkContentValues putObjidNull() {
        mContentValues.putNull(DiifsWorkColumns.OBJID);
        return this;
    }

    public DiifsWorkContentValues putObjversion(String value) {
        mContentValues.put(DiifsWorkColumns.OBJVERSION, value);
        return this;
    }

    public DiifsWorkContentValues putObjversionNull() {
        mContentValues.putNull(DiifsWorkColumns.OBJVERSION);
        return this;
    }

    public DiifsWorkContentValues putIsupdate(Boolean value) {
        mContentValues.put(DiifsWorkColumns.ISUPDATE, value);
        return this;
    }

    public DiifsWorkContentValues putIsupdateNull() {
        mContentValues.putNull(DiifsWorkColumns.ISUPDATE);
        return this;
    }

    public DiifsWorkContentValues putPhysiccode(String value) {
        mContentValues.put(DiifsWorkColumns.PHYSICCODE, value);
        return this;
    }

    public DiifsWorkContentValues putPhysiccodeNull() {
        mContentValues.putNull(DiifsWorkColumns.PHYSICCODE);
        return this;
    }
}
