package cc.xingyan.android.afc.provider.devicephysics;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code device_physics} table.
 */
public class DevicePhysicsCursor extends AbstractCursor implements DevicePhysicsModel {
    public DevicePhysicsCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(DevicePhysicsColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code code} value.
     * Can be {@code null}.
     */
    public String getCode() {
        String res = getStringOrNull(DevicePhysicsColumns.CODE);
        return res;
    }

    /**
     * Get the {@code code_physics} value.
     * Can be {@code null}.
     */
    public String getCodePhysics() {
        String res = getStringOrNull(DevicePhysicsColumns.CODE_PHYSICS);
        return res;
    }

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    public String getName() {
        String res = getStringOrNull(DevicePhysicsColumns.NAME);
        return res;
    }

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    public String getType() {
        String res = getStringOrNull(DevicePhysicsColumns.TYPE);
        return res;
    }
}
