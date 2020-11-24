package cc.xingyan.android.afc.provider.diifswork;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code diifs_work} table.
 */
public class DiifsWorkCursor extends AbstractCursor implements DiifsWorkModel {
    public DiifsWorkCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(DiifsWorkColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code wono} value.
     * Can be {@code null}.
     */
    public String getWono() {
        String res = getStringOrNull(DiifsWorkColumns.WONO);
        return res;
    }

    /**
     * Get the {@code pmno} value.
     * Can be {@code null}.
     */
    public String getPmno() {
        String res = getStringOrNull(DiifsWorkColumns.PMNO);
        return res;
    }

    /**
     * Get the {@code testpointid} value.
     * Can be {@code null}.
     */
    public String getTestpointid() {
        String res = getStringOrNull(DiifsWorkColumns.TESTPOINTID);
        return res;
    }

    /**
     * Get the {@code parametercode} value.
     * Can be {@code null}.
     */
    public String getParametercode() {
        String res = getStringOrNull(DiifsWorkColumns.PARAMETERCODE);
        return res;
    }

    /**
     * Get the {@code parameterdesc} value.
     * Can be {@code null}.
     */
    public String getParameterdesc() {
        String res = getStringOrNull(DiifsWorkColumns.PARAMETERDESC);
        return res;
    }

    /**
     * Get the {@code parametertype} value.
     * Can be {@code null}.
     */
    public String getParametertype() {
        String res = getStringOrNull(DiifsWorkColumns.PARAMETERTYPE);
        return res;
    }

    /**
     * Get the {@code unit} value.
     * Can be {@code null}.
     */
    public String getUnit() {
        String res = getStringOrNull(DiifsWorkColumns.UNIT);
        return res;
    }

    /**
     * Get the {@code minvalue} value.
     * Can be {@code null}.
     */
    public String getMinvalue() {
        String res = getStringOrNull(DiifsWorkColumns.MINVALUE);
        return res;
    }

    /**
     * Get the {@code maxvalue} value.
     * Can be {@code null}.
     */
    public String getMaxvalue() {
        String res = getStringOrNull(DiifsWorkColumns.MAXVALUE);
        return res;
    }

    /**
     * Get the {@code startvalue} value.
     * Can be {@code null}.
     */
    public String getStartvalue() {
        String res = getStringOrNull(DiifsWorkColumns.STARTVALUE);
        return res;
    }

    /**
     * Get the {@code interval} value.
     * Can be {@code null}.
     */
    public String getInterval() {
        String res = getStringOrNull(DiifsWorkColumns.INTERVAL);
        return res;
    }

    /**
     * Get the {@code lastvalue} value.
     * Can be {@code null}.
     */
    public String getLastvalue() {
        String res = getStringOrNull(DiifsWorkColumns.LASTVALUE);
        return res;
    }

    /**
     * Get the {@code pmgenvalue} value.
     * Can be {@code null}.
     */
    public String getPmgenvalue() {
        String res = getStringOrNull(DiifsWorkColumns.PMGENVALUE);
        return res;
    }

    /**
     * Get the {@code objid} value.
     * Can be {@code null}.
     */
    public String getObjid() {
        String res = getStringOrNull(DiifsWorkColumns.OBJID);
        return res;
    }

    /**
     * Get the {@code objversion} value.
     * Can be {@code null}.
     */
    public String getObjversion() {
        String res = getStringOrNull(DiifsWorkColumns.OBJVERSION);
        return res;
    }

    /**
     * Get the {@code isupdate} value.
     * Can be {@code null}.
     */
    public Boolean getIsupdate() {
        Boolean res = getBooleanOrNull(DiifsWorkColumns.ISUPDATE);
        return res;
    }

    /**
     * Get the {@code physiccode} value.
     * Can be {@code null}.
     */
    public String getPhysiccode() {
        String res = getStringOrNull(DiifsWorkColumns.PHYSICCODE);
        return res;
    }
}
