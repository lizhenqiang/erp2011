package cc.xingyan.android.afc.provider.paramvalue;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code param_value} table.
 */
public interface ParamValueModel extends BaseModel {

    /**
     * Get the {@code code} value.
     * Can be {@code null}.
     */
    String getCode();

    /**
     * Get the {@code value} value.
     * Can be {@code null}.
     */
    String getValue();

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    String getType();

    /**
     * Get the {@code parent_code} value.
     * Can be {@code null}.
     */
    String getParentCode();

    /**
     * Get the {@code parent_type} value.
     * Can be {@code null}.
     */
    String getParentType();
}
