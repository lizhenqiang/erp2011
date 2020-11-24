package cc.xingyan.android.afc.provider.pmifsresultenum;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code pmifs_result_enum} table.
 */
public interface PmifsResultEnumModel extends BaseModel {

    /**
     * Get the {@code item_guid} value.
     * Can be {@code null}.
     */
    String getItemGuid();

    /**
     * Get the {@code sn} value.
     * Can be {@code null}.
     */
    Integer getSn();

    /**
     * Get the {@code value} value.
     * Can be {@code null}.
     */
    String getValue();
}
