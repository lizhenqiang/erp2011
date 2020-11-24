package cc.xingyan.android.afc.provider.pmparammaterials;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code pm_param_materials} table.
 */
public class PmParamMaterialsCursor extends AbstractCursor implements PmParamMaterialsModel {
    public PmParamMaterialsCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(PmParamMaterialsColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code code} value.
     * Can be {@code null}.
     */
    public String getCode() {
        String res = getStringOrNull(PmParamMaterialsColumns.CODE);
        return res;
    }

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    public String getName() {
        String res = getStringOrNull(PmParamMaterialsColumns.NAME);
        return res;
    }

    /**
     * Get the {@code line} value.
     * Can be {@code null}.
     */
    public String getLine() {
        String res = getStringOrNull(PmParamMaterialsColumns.LINE);
        return res;
    }

    /**
     * Get the {@code device} value.
     * Can be {@code null}.
     */
    public String getDevice() {
        String res = getStringOrNull(PmParamMaterialsColumns.DEVICE);
        return res;
    }
}
