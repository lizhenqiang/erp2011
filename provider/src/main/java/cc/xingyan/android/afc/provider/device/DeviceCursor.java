package cc.xingyan.android.afc.provider.device;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code device} table.
 */
public class DeviceCursor extends AbstractCursor implements DeviceModel {
    public DeviceCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(DeviceColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code code} value.
     * Can be {@code null}.
     */
    public String getCode() {
        String res = getStringOrNull(DeviceColumns.CODE);
        return res;
    }

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    public String getName() {
        String res = getStringOrNull(DeviceColumns.NAME);
        return res;
    }

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    public String getType() {
        String res = getStringOrNull(DeviceColumns.TYPE);
        return res;
    }

    /**
     * Get the {@code location} value.
     * Can be {@code null}.
     */
    public String getLocation() {
        String res = getStringOrNull(DeviceColumns.LOCATION);
        return res;
    }
}
