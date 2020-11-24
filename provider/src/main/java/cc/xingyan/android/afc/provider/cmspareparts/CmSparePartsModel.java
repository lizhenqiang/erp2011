package cc.xingyan.android.afc.provider.cmspareparts;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code cm_spare_parts} table.
 */
public interface CmSparePartsModel extends BaseModel {

    /**
     * Get the {@code cm_work_id} value.
     * Can be {@code null}.
     */
    String getCmWorkId();

    /**
     * Get the {@code order_id} value.
     * Can be {@code null}.
     */
    String getOrderId();

    /**
     * Get the {@code part_id} value.
     * Can be {@code null}.
     */
    String getPartId();

    /**
     * Get the {@code part_description} value.
     * Can be {@code null}.
     */
    String getPartDescription();

    /**
     * Get the {@code apply_date} value.
     * Can be {@code null}.
     */
    Integer getApplyDate();

    /**
     * Get the {@code install_date} value.
     * Can be {@code null}.
     */
    Integer getInstallDate();

    /**
     * Get the {@code old_part_sn} value.
     * Can be {@code null}.
     */
    String getOldPartSn();

    /**
     * Get the {@code new_part_sn} value.
     * Can be {@code null}.
     */
    String getNewPartSn();

    /**
     * Get the {@code spare_part_status} value.
     * Can be {@code null}.
     */
    Integer getSparePartStatus();
}
