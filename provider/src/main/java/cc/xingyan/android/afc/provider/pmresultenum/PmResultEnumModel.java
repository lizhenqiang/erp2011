package cc.xingyan.android.afc.provider.pmresultenum;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code pm_result_enum} table.
 */
public interface PmResultEnumModel extends BaseModel {

    /**
     * Get the {@code item_guid} value.
     * Can be {@code null}.
     */
    String getItemGuid();

    /**
     * Get the {@code ordinal} value.
     * Can be {@code null}.
     */
    Integer getOrdinal();

    /**
     * Get the {@code value} value.
     * Can be {@code null}.
     */
    String getValue();
}
