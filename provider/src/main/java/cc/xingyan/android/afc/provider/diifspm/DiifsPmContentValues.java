package cc.xingyan.android.afc.provider.diifspm;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code diifs_pm} table.
 */
public class DiifsPmContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return DiifsPmColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  DiifsPmSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  DiifsPmSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public DiifsPmContentValues putWono(String value) {
        mContentValues.put(DiifsPmColumns.WONO, value);
        return this;
    }

    public DiifsPmContentValues putWonoNull() {
        mContentValues.putNull(DiifsPmColumns.WONO);
        return this;
    }

    public DiifsPmContentValues putPmno(String value) {
        mContentValues.put(DiifsPmColumns.PMNO, value);
        return this;
    }

    public DiifsPmContentValues putPmnoNull() {
        mContentValues.putNull(DiifsPmColumns.PMNO);
        return this;
    }

    public DiifsPmContentValues putLogicname(String value) {
        mContentValues.put(DiifsPmColumns.LOGICNAME, value);
        return this;
    }

    public DiifsPmContentValues putLogicnameNull() {
        mContentValues.putNull(DiifsPmColumns.LOGICNAME);
        return this;
    }

    public DiifsPmContentValues putPhysicname(String value) {
        mContentValues.put(DiifsPmColumns.PHYSICNAME, value);
        return this;
    }

    public DiifsPmContentValues putPhysicnameNull() {
        mContentValues.putNull(DiifsPmColumns.PHYSICNAME);
        return this;
    }

    public DiifsPmContentValues putPhysiccode(String value) {
        mContentValues.put(DiifsPmColumns.PHYSICCODE, value);
        return this;
    }

    public DiifsPmContentValues putPhysiccodeNull() {
        mContentValues.putNull(DiifsPmColumns.PHYSICCODE);
        return this;
    }

    public DiifsPmContentValues putIsdone(Boolean value) {
        mContentValues.put(DiifsPmColumns.ISDONE, value);
        return this;
    }

    public DiifsPmContentValues putIsdoneNull() {
        mContentValues.putNull(DiifsPmColumns.ISDONE);
        return this;
    }
}
