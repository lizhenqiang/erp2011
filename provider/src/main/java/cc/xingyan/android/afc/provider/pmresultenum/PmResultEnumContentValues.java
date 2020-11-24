package cc.xingyan.android.afc.provider.pmresultenum;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code pm_result_enum} table.
 */
public class PmResultEnumContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return PmResultEnumColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  PmResultEnumSelection where) {
        return contentResolver.update(where.uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  PmResultEnumSelection where) {
        return context.getContentResolver().update(where.uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public PmResultEnumContentValues putItemGuid(String value) {
        mContentValues.put(PmResultEnumColumns.ITEM_GUID, value);
        return this;
    }

    public PmResultEnumContentValues putItemGuidNull() {
        mContentValues.putNull(PmResultEnumColumns.ITEM_GUID);
        return this;
    }

    public PmResultEnumContentValues putOrdinal(Integer value) {
        mContentValues.put(PmResultEnumColumns.ORDINAL, value);
        return this;
    }

    public PmResultEnumContentValues putOrdinalNull() {
        mContentValues.putNull(PmResultEnumColumns.ORDINAL);
        return this;
    }

    public PmResultEnumContentValues putValue(String value) {
        mContentValues.put(PmResultEnumColumns.VALUE, value);
        return this;
    }

    public PmResultEnumContentValues putValueNull() {
        mContentValues.putNull(PmResultEnumColumns.VALUE);
        return this;
    }
}
