package cc.xingyan.android.afc.provider.cmparammaterials;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code cm_param_materials} table.
 */
public interface CmParamMaterialsModel extends BaseModel {

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
