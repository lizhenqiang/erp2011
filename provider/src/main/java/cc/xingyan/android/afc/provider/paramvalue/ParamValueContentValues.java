package cc.xingyan.android.afc.provider.paramvalue;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code param_value} table.
 */
public class ParamValueContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ParamValueColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  ParamValueSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  ParamValueSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public ParamValueContentValues putCode(String value) {
        mContentValues.put(ParamValueColumns.CODE, value);
        return this;
    }

    public ParamValueContentValues putCodeNull() {
        mContentValues.putNull(ParamValueColumns.CODE);
        return this;
    }

    public ParamValueContentValues putValue(String value) {
        mContentValues.put(ParamValueColumns.VALUE, value);
        return this;
    }

    public ParamValueContentValues putValueNull() {
        mContentValues.putNull(ParamValueColumns.VALUE);
        return this;
    }

    public ParamValueContentValues putType(String value) {
        mContentValues.put(ParamValueColumns.TYPE, value);
        return this;
    }

    public ParamValueContentValues putTypeNull() {
        mContentValues.putNull(ParamValueColumns.TYPE);
        return this;
    }

    public ParamValueContentValues putParentCode(String value) {
        mContentValues.put(ParamValueColumns.PARENT_CODE, value);
        return this;
    }

    public ParamValueContentValues putParentCodeNull() {
        mContentValues.putNull(ParamValueColumns.PARENT_CODE);
        return this;
    }

    public ParamValueContentValues putParentType(String value) {
        mContentValues.put(ParamValueColumns.PARENT_TYPE, value);
        return this;
    }

    public ParamValueContentValues putParentTypeNull() {
        mContentValues.putNull(ParamValueColumns.PARENT_TYPE);
        return this;
    }
}
