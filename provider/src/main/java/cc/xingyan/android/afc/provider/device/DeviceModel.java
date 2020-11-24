package cc.xingyan.android.afc.provider.device;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code device} table.
 */
public interface DeviceModel extends BaseModel {

    /**
     * Get the {@code code} value.
     * Can be {@code null}.
     */
    String getCode();

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    String getName();

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    String getType();

    /**
     * Get the {@code location} value.
     * Can be {@code null}.
     */
    String getLocation();
}
