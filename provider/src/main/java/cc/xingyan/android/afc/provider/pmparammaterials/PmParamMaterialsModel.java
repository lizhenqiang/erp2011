package cc.xingyan.android.afc.provider.pmparammaterials;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code pm_param_materials} table.
 */
public interface PmParamMaterialsModel extends BaseModel {

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
     * Get the {@code line} value.
     * Can be {@code null}.
     */
    String getLine();

    /**
     * Get the {@code device} value.
     * Can be {@code null}.
     */
    String getDevice();
}
