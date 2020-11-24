package cc.xingyan.android.afc.provider.devicephysics;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code device_physics} table.
 */
public class DevicePhysicsContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return DevicePhysicsColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  DevicePhysicsSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  DevicePhysicsSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public DevicePhysicsContentValues putCode(String value) {
        mContentValues.put(DevicePhysicsColumns.CODE, value);
        return this;
    }

    public DevicePhysicsContentValues putCodeNull() {
        mContentValues.putNull(DevicePhysicsColumns.CODE);
        return this;
    }

    public DevicePhysicsContentValues putCodePhysics(String value) {
        mContentValues.put(DevicePhysicsColumns.CODE_PHYSICS, value);
        return this;
    }

    public DevicePhysicsContentValues putCodePhysicsNull() {
        mContentValues.putNull(DevicePhysicsColumns.CODE_PHYSICS);
        return this;
    }

    public DevicePhysicsContentValues putName(String value) {
        mContentValues.put(DevicePhysicsColumns.NAME, value);
        return this;
    }

    public DevicePhysicsContentValues putNameNull() {
        mContentValues.putNull(DevicePhysicsColumns.NAME);
        return this;
    }

    public DevicePhysicsContentValues putType(String value) {
        mContentValues.put(DevicePhysicsColumns.TYPE, value);
        return this;
    }

    public DevicePhysicsContentValues putTypeNull() {
        mContentValues.putNull(DevicePhysicsColumns.TYPE);
        return this;
    }
}
