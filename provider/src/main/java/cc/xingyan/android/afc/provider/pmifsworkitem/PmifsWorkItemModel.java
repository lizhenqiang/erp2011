package cc.xingyan.android.afc.provider.pmifsworkitem;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code pmifs_work_item} table.
 */
public interface PmifsWorkItemModel extends BaseModel {

    /**
     * Get the {@code order_id} value.
     * Can be {@code null}.
     */
    String getOrderId();

    /**
     * Get the {@code work_guid} value.
     * Can be {@code null}.
     */
    String getWorkGuid();

    /**
     * Get the {@code package_id} value.
     * Can be {@code null}.
     */
    String getPackageId();

    /**
     * Get the {@code package_des} value.
     * Can be {@code null}.
     */
    String getPackageDes();

    /**
     * Get the {@code guid} value.
     * Can be {@code null}.
     */
    String getGuid();

    /**
     * Get the {@code item_id} value.
     * Can be {@code null}.
     */
    String getItemId();

    /**
     * Get the {@code item_des} value.
     * Can be {@code null}.
     */
    String getItemDes();

    /**
     * Get the {@code work_sn} value.
     * Can be {@code null}.
     */
    Integer getWorkSn();

    /**
     * Get the {@code result_type} value.
     * Can be {@code null}.
     */
    String getResultType();

    /**
     * Get the {@code result_min_value} value.
     * Can be {@code null}.
     */
    Integer getResultMinValue();

    /**
     * Get the {@code result_max_value} value.
     * Can be {@code null}.
     */
    Integer getResultMaxValue();

    /**
     * Get the {@code result_default_value} value.
     * Can be {@code null}.
     */
    String getResultDefaultValue();

    /**
     * Get the {@code result_value_unit} value.
     * Can be {@code null}.
     */
    String getResultValueUnit();

    /**
     * Get the {@code result_enum_ordinal} value.
     * Can be {@code null}.
     */
    Integer getResultEnumOrdinal();

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

    /**
     * Get the {@code required} value.
     * Can be {@code null}.
     */
    Boolean getRequired();
}
