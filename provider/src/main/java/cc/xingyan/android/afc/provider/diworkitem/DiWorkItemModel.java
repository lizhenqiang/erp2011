package cc.xingyan.android.afc.provider.diworkitem;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code di_work_item} table.
 */
public interface DiWorkItemModel extends BaseModel {

    /**
     * Get the {@code work_guid} value.
     * Can be {@code null}.
     */
    String getWorkGuid();

    /**
     * Get the {@code station_id} value.
     * Can be {@code null}.
     */
    String getStationId();

    /**
     * Get the {@code station_description} value.
     * Can be {@code null}.
     */
    String getStationDescription();

    /**
     * Get the {@code device_id} value.
     * Can be {@code null}.
     */
    String getDeviceId();

    /**
     * Get the {@code device_description} value.
     * Can be {@code null}.
     */
    String getDeviceDescription();

    /**
     * Get the {@code device_system} value.
     * Can be {@code null}.
     */
    String getDeviceSystem();

    /**
     * Get the {@code guid} value.
     * Can be {@code null}.
     */
    String getGuid();

    /**
     * Get the {@code result_ordinal} value.
     * Can be {@code null}.
     */
    Integer getResultOrdinal();

    /**
     * Get the {@code result_value} value.
     * Can be {@code null}.
     */
    String getResultValue();

    /**
     * Get the {@code last_modified} value.
     * Can be {@code null}.
     */
    Long getLastModified();
}
