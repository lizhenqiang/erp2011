package cc.xingyan.android.afc.provider.device;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code device} table.
 */
public class DeviceContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return DeviceColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  DeviceSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  DeviceSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public DeviceContentValues putCode(String value) {
        mContentValues.put(DeviceColumns.CODE, value);
        return this;
    }

    public DeviceContentValues putCodeNull() {
        mContentValues.putNull(DeviceColumns.CODE);
        return this;
    }

    public DeviceContentValues putName(String value) {
        mContentValues.put(DeviceColumns.NAME, value);
        return this;
    }

    public DeviceContentValues putNameNull() {
        mContentValues.putNull(DeviceColumns.NAME);
        return this;
    }

    public DeviceContentValues putType(String value) {
        mContentValues.put(DeviceColumns.TYPE, value);
        return this;
    }

    public DeviceContentValues putTypeNull() {
        mContentValues.putNull(DeviceColumns.TYPE);
        return this;
    }

    public DeviceContentValues putLocation(String value) {
        mContentValues.put(DeviceColumns.LOCATION, value);
        return this;
    }

    public DeviceContentValues putLocationNull() {
        mContentValues.putNull(DeviceColumns.LOCATION);
        return this;
    }
}
