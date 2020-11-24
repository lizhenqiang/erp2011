package cc.xingyan.android.afc.provider.diifspmitem;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code diifs_pm_item} table.
 */
public class DiifsPmItemCursor extends AbstractCursor implements DiifsPmItemModel {
    public DiifsPmItemCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(DiifsPmItemColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code wono} value.
     * Can be {@code null}.
     */
    public String getWono() {
        String res = getStringOrNull(DiifsPmItemColumns.WONO);
        return res;
    }

    /**
     * Get the {@code pmno} value.
     * Can be {@code null}.
     */
    public String getPmno() {
        String res = getStringOrNull(DiifsPmItemColumns.PMNO);
        return res;
    }

    /**
     * Get the {@code testpointid} value.
     * Can be {@code null}.
     */
    public String getTestpointid() {
        String res = getStringOrNull(DiifsPmItemColumns.TESTPOINTID);
        return res;
    }

    /**
     * Get the {@code parametercode} value.
     * Can be {@code null}.
     */
    public String getParametercode() {
        String res = getStringOrNull(DiifsPmItemColumns.PARAMETERCODE);
        return res;
    }

    /**
     * Get the {@code parameterdesc} value.
     * Can be {@code null}.
     */
    public String getParameterdesc() {
        String res = getStringOrNull(DiifsPmItemColumns.PARAMETERDESC);
        return res;
    }

    /**
     * Get the {@code parametertype} value.
     * Can be {@code null}.
     */
    public String getParametertype() {
        String res = getStringOrNull(DiifsPmItemColumns.PARAMETERTYPE);
        return res;
    }

    /**
     * Get the {@code unit} value.
     * Can be {@code null}.
     */
    public String getUnit() {
        String res = getStringOrNull(DiifsPmItemColumns.UNIT);
        return res;
    }

    /**
     * Get the {@code minvalue} value.
     * Can be {@code null}.
     */
    public String getMinvalue() {
        String res = getStringOrNull(DiifsPmItemColumns.MINVALUE);
        return res;
    }

    /**
     * Get the {@code maxvalue} value.
     * Can be {@code null}.
     */
    public String getMaxvalue() {
        String res = getStringOrNull(DiifsPmItemColumns.MAXVALUE);
        return res;
    }

    /**
     * Get the {@code startvalue} value.
     * Can be {@code null}.
     */
    public String getStartvalue() {
        String res = getStringOrNull(DiifsPmItemColumns.STARTVALUE);
        return res;
    }

    /**
     * Get the {@code interval} value.
     * Can be {@code null}.
     */
    public String getInterval() {
        String res = getStringOrNull(DiifsPmItemColumns.INTERVAL);
        return res;
    }

    /**
     * Get the {@code lastvalue} value.
     * Can be {@code null}.
     */
    public String getLastvalue() {
        String res = getStringOrNull(DiifsPmItemColumns.LASTVALUE);
        return res;
    }

    /**
     * Get the {@code pmgenvalue} value.
     * Can be {@code null}.
     */
    public String getPmgenvalue() {
        String res = getStringOrNull(DiifsPmItemColumns.PMGENVALUE);
        return res;
    }

    /**
     * Get the {@code objid} value.
     * Can be {@code null}.
     */
    public String getObjid() {
        String res = getStringOrNull(DiifsPmItemColumns.OBJID);
        return res;
    }

    /**
     * Get the {@code objversion} value.
     * Can be {@code null}.
     */
    public String getObjversion() {
        String res = getStringOrNull(DiifsPmItemColumns.OBJVERSION);
        return res;
    }
}
