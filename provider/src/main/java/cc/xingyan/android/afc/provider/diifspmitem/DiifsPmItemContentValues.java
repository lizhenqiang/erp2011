package cc.xingyan.android.afc.provider.diifspmitem;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code diifs_pm_item} table.
 */
public class DiifsPmItemContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return DiifsPmItemColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  DiifsPmItemSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  DiifsPmItemSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public DiifsPmItemContentValues putWono(String value) {
        mContentValues.put(DiifsPmItemColumns.WONO, value);
        return this;
    }

    public DiifsPmItemContentValues putWonoNull() {
        mContentValues.putNull(DiifsPmItemColumns.WONO);
        return this;
    }

    public DiifsPmItemContentValues putPmno(String value) {
        mContentValues.put(DiifsPmItemColumns.PMNO, value);
        return this;
    }

    public DiifsPmItemContentValues putPmnoNull() {
        mContentValues.putNull(DiifsPmItemColumns.PMNO);
        return this;
    }

    public DiifsPmItemContentValues putTestpointid(String value) {
        mContentValues.put(DiifsPmItemColumns.TESTPOINTID, value);
        return this;
    }

    public DiifsPmItemContentValues putTestpointidNull() {
        mContentValues.putNull(DiifsPmItemColumns.TESTPOINTID);
        return this;
    }

    public DiifsPmItemContentValues putParametercode(String value) {
        mContentValues.put(DiifsPmItemColumns.PARAMETERCODE, value);
        return this;
    }

    public DiifsPmItemContentValues putParametercodeNull() {
        mContentValues.putNull(DiifsPmItemColumns.PARAMETERCODE);
        return this;
    }

    public DiifsPmItemContentValues putParameterdesc(String value) {
        mContentValues.put(DiifsPmItemColumns.PARAMETERDESC, value);
        return this;
    }

    public DiifsPmItemContentValues putParameterdescNull() {
        mContentValues.putNull(DiifsPmItemColumns.PARAMETERDESC);
        return this;
    }

    public DiifsPmItemContentValues putParametertype(String value) {
        mContentValues.put(DiifsPmItemColumns.PARAMETERTYPE, value);
        return this;
    }

    public DiifsPmItemContentValues putParametertypeNull() {
        mContentValues.putNull(DiifsPmItemColumns.PARAMETERTYPE);
        return this;
    }

    public DiifsPmItemContentValues putUnit(String value) {
        mContentValues.put(DiifsPmItemColumns.UNIT, value);
        return this;
    }

    public DiifsPmItemContentValues putUnitNull() {
        mContentValues.putNull(DiifsPmItemColumns.UNIT);
        return this;
    }

    public DiifsPmItemContentValues putMinvalue(String value) {
        mContentValues.put(DiifsPmItemColumns.MINVALUE, value);
        return this;
    }

    public DiifsPmItemContentValues putMinvalueNull() {
        mContentValues.putNull(DiifsPmItemColumns.MINVALUE);
        return this;
    }

    public DiifsPmItemContentValues putMaxvalue(String value) {
        mContentValues.put(DiifsPmItemColumns.MAXVALUE, value);
        return this;
    }

    public DiifsPmItemContentValues putMaxvalueNull() {
        mContentValues.putNull(DiifsPmItemColumns.MAXVALUE);
        return this;
    }

    public DiifsPmItemContentValues putStartvalue(String value) {
        mContentValues.put(DiifsPmItemColumns.STARTVALUE, value);
        return this;
    }

    public DiifsPmItemContentValues putStartvalueNull() {
        mContentValues.putNull(DiifsPmItemColumns.STARTVALUE);
        return this;
    }

    public DiifsPmItemContentValues putInterval(String value) {
        mContentValues.put(DiifsPmItemColumns.INTERVAL, value);
        return this;
    }

    public DiifsPmItemContentValues putIntervalNull() {
        mContentValues.putNull(DiifsPmItemColumns.INTERVAL);
        return this;
    }

    public DiifsPmItemContentValues putLastvalue(String value) {
        mContentValues.put(DiifsPmItemColumns.LASTVALUE, value);
        return this;
    }

    public DiifsPmItemContentValues putLastvalueNull() {
        mContentValues.putNull(DiifsPmItemColumns.LASTVALUE);
        return this;
    }

    public DiifsPmItemContentValues putPmgenvalue(String value) {
        mContentValues.put(DiifsPmItemColumns.PMGENVALUE, value);
        return this;
    }

    public DiifsPmItemContentValues putPmgenvalueNull() {
        mContentValues.putNull(DiifsPmItemColumns.PMGENVALUE);
        return this;
    }

    public DiifsPmItemContentValues putObjid(String value) {
        mContentValues.put(DiifsPmItemColumns.OBJID, value);
        return this;
    }

    public DiifsPmItemContentValues putObjidNull() {
        mContentValues.putNull(DiifsPmItemColumns.OBJID);
        return this;
    }

    public DiifsPmItemContentValues putObjversion(String value) {
        mContentValues.put(DiifsPmItemColumns.OBJVERSION, value);
        return this;
    }

    public DiifsPmItemContentValues putObjversionNull() {
        mContentValues.putNull(DiifsPmItemColumns.OBJVERSION);
        return this;
    }
}
