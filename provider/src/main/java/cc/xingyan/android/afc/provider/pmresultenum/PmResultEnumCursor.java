package cc.xingyan.android.afc.provider.pmresultenum;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code pm_result_enum} table.
 */
public class PmResultEnumCursor extends AbstractCursor implements PmResultEnumModel {
    public PmResultEnumCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(PmResultEnumColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code item_guid} value.
     * Can be {@code null}.
     */
    public String getItemGuid() {
        String res = getStringOrNull(PmResultEnumColumns.ITEM_GUID);
        return res;
    }

    /**
     * Get the {@code ordinal} value.
     * Can be {@code null}.
     */
    public Integer getOrdinal() {
        Integer res = getIntegerOrNull(PmResultEnumColumns.ORDINAL);
        return res;
    }

    /**
     * Get the {@code value} value.
     * Can be {@code null}.
     */
    public String getValue() {
        String res = getStringOrNull(PmResultEnumColumns.VALUE);
        return res;
    }
}
