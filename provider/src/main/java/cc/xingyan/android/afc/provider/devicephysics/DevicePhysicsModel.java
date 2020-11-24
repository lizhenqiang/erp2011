package cc.xingyan.android.afc.provider.devicephysics;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code device_physics} table.
 */
public interface DevicePhysicsModel extends BaseModel {

    /**
     * Get the {@code code} value.
     * Can be {@code null}.
     */
    String getCode();

    /**
     * Get the {@code code_physics} value.
     * Can be {@code null}.
     */
    String getCodePhysics();

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
}
