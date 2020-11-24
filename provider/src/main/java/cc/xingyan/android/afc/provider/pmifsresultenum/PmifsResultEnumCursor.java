package cc.xingyan.android.afc.provider.pmifsresultenum;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code pmifs_result_enum} table.
 */
public class PmifsResultEnumCursor extends AbstractCursor implements PmifsResultEnumModel {
    public PmifsResultEnumCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(PmifsResultEnumColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code item_guid} value.
     * Can be {@code null}.
     */
    public String getItemGuid() {
        String res = getStringOrNull(PmifsResultEnumColumns.ITEM_GUID);
        return res;
    }

    /**
     * Get the {@code sn} value.
     * Can be {@code null}.
     */
    public Integer getSn() {
        Integer res = getIntegerOrNull(PmifsResultEnumColumns.SN);
        return res;
    }

    /**
     * Get the {@code value} value.
     * Can be {@code null}.
     */
    public String getValue() {
        String res = getStringOrNull(PmifsResultEnumColumns.VALUE);
        return res;
    }
}
