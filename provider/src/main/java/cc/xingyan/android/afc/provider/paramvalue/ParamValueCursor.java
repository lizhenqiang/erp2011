package cc.xingyan.android.afc.provider.paramvalue;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code param_value} table.
 */
public class ParamValueCursor extends AbstractCursor implements ParamValueModel {
    public ParamValueCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(ParamValueColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code code} value.
     * Can be {@code null}.
     */
    public String getCode() {
        String res = getStringOrNull(ParamValueColumns.CODE);
        return res;
    }

    /**
     * Get the {@code value} value.
     * Can be {@code null}.
     */
    public String getValue() {
        String res = getStringOrNull(ParamValueColumns.VALUE);
        return res;
    }

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    public String getType() {
        String res = getStringOrNull(ParamValueColumns.TYPE);
        return res;
    }

    /**
     * Get the {@code parent_code} value.
     * Can be {@code null}.
     */
    public String getParentCode() {
        String res = getStringOrNull(ParamValueColumns.PARENT_CODE);
        return res;
    }

    /**
     * Get the {@code parent_type} value.
     * Can be {@code null}.
     */
    public String getParentType() {
        String res = getStringOrNull(ParamValueColumns.PARENT_TYPE);
        return res;
    }
}
