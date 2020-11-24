package cc.xingyan.android.afc.provider.pankureport;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code panku_report} table.
 */
public interface PankuReportModel extends BaseModel {

    /**
     * Get the {@code user_id} value.
     * Can be {@code null}.
     */
    String getUserId();

    /**
     * Get the {@code report_no} value.
     * Can be {@code null}.
     */
    String getReportNo();

    /**
     * Get the {@code part_no} value.
     * Can be {@code null}.
     */
    String getPartNo();

    /**
     * Get the {@code part_name} value.
     * Can be {@code null}.
     */
    String getPartName();

    /**
     * Get the {@code actual_amount} value.
     * Can be {@code null}.
     */
    String getActualAmount();

    /**
     * Get the {@code lotbatch_no} value.
     * Can be {@code null}.
     */
    String getLotbatchNo();

    /**
     * Get the {@code line_no} value.
     * Can be {@code null}.
     */
    String getLineNo();

    /**
     * Get the {@code part_seq} value.
     * Can be {@code null}.
     */
    String getPartSeq();

    /**
     * Get the {@code warehouse_no} value.
     * Can be {@code null}.
     */
    String getWarehouseNo();

    /**
     * Get the {@code warehouse_name} value.
     * Can be {@code null}.
     */
    String getWarehouseName();

    /**
     * Get the {@code pandian_mark} value.
     * Can be {@code null}.
     */
    String getPandianMark();

    /**
     * Get the {@code pandian_time} value.
     * Can be {@code null}.
     */
    Long getPandianTime();
}
