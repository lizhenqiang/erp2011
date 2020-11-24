package cc.xingyan.android.afc.provider.diifspm;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code diifs_pm} table.
 */
public class DiifsPmCursor extends AbstractCursor implements DiifsPmModel {
    public DiifsPmCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(DiifsPmColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code wono} value.
     * Can be {@code null}.
     */
    public String getWono() {
        String res = getStringOrNull(DiifsPmColumns.WONO);
        return res;
    }

    /**
     * Get the {@code pmno} value.
     * Can be {@code null}.
     */
    public String getPmno() {
        String res = getStringOrNull(DiifsPmColumns.PMNO);
        return res;
    }

    /**
     * Get the {@code logicname} value.
     * Can be {@code null}.
     */
    public String getLogicname() {
        String res = getStringOrNull(DiifsPmColumns.LOGICNAME);
        return res;
    }

    /**
     * Get the {@code physicname} value.
     * Can be {@code null}.
     */
    public String getPhysicname() {
        String res = getStringOrNull(DiifsPmColumns.PHYSICNAME);
        return res;
    }

    /**
     * Get the {@code physiccode} value.
     * Can be {@code null}.
     */
    public String getPhysiccode() {
        String res = getStringOrNull(DiifsPmColumns.PHYSICCODE);
        return res;
    }

    /**
     * Get the {@code isdone} value.
     * Can be {@code null}.
     */
    public Boolean getIsdone() {
        Boolean res = getBooleanOrNull(DiifsPmColumns.ISDONE);
        return res;
    }
}
