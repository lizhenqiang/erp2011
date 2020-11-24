package cc.xingyan.android.afc.provider.pmifsresultenum;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code pmifs_result_enum} table.
 */
public class PmifsResultEnumContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return PmifsResultEnumColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  PmifsResultEnumSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  PmifsResultEnumSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public PmifsResultEnumContentValues putItemGuid(String value) {
        mContentValues.put(PmifsResultEnumColumns.ITEM_GUID, value);
        return this;
    }

    public PmifsResultEnumContentValues putItemGuidNull() {
        mContentValues.putNull(PmifsResultEnumColumns.ITEM_GUID);
        return this;
    }

    public PmifsResultEnumContentValues putSn(Integer value) {
        mContentValues.put(PmifsResultEnumColumns.SN, value);
        return this;
    }

    public PmifsResultEnumContentValues putSnNull() {
        mContentValues.putNull(PmifsResultEnumColumns.SN);
        return this;
    }

    public PmifsResultEnumContentValues putValue(String value) {
        mContentValues.put(PmifsResultEnumColumns.VALUE, value);
        return this;
    }

    public PmifsResultEnumContentValues putValueNull() {
        mContentValues.putNull(PmifsResultEnumColumns.VALUE);
        return this;
    }
}
