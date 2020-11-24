package cc.xingyan.android.afc.provider.report;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code report} table.
 */
public interface ReportModel extends BaseModel {

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
     * Get the {@code data_start} value.
     * Can be {@code null}.
     */
    Long getDataStart();

    /**
     * Get the {@code data_end} value.
     * Can be {@code null}.
     */
    Long getDataEnd();

    /**
     * Get the {@code last_rece_num} value.
     * Can be {@code null}.
     */
    String getLastReceNum();

    /**
     * Get the {@code currect_rece_num} value.
     * Can be {@code null}.
     */
    String getCurrectReceNum();

    /**
     * Get the {@code rece_lrr} value.
     * Can be {@code null}.
     */
    String getReceLrr();

    /**
     * Get the {@code last_form_num} value.
     * Can be {@code null}.
     */
    String getLastFormNum();

    /**
     * Get the {@code currect_form_num} value.
     * Can be {@code null}.
     */
    String getCurrectFormNum();

    /**
     * Get the {@code form_lrr} value.
     * Can be {@code null}.
     */
    String getFormLrr();

    /**
     * Get the {@code last_form_delay} value.
     * Can be {@code null}.
     */
    String getLastFormDelay();

    /**
     * Get the {@code currect_form_delay} value.
     * Can be {@code null}.
     */
    String getCurrectFormDelay();

    /**
     * Get the {@code form_delay_lrr} value.
     * Can be {@code null}.
     */
    String getFormDelayLrr();

    /**
     * Get the {@code ytd_rece_num} value.
     * Can be {@code null}.
     */
    String getYtdReceNum();

    /**
     * Get the {@code rece_per} value.
     * Can be {@code null}.
     */
    String getRecePer();

    /**
     * Get the {@code form_per} value.
     * Can be {@code null}.
     */
    String getFormPer();

    /**
     * Get the {@code ag_num} value.
     * Can be {@code null}.
     */
    String getAgNum();

    /**
     * Get the {@code bom_num} value.
     * Can be {@code null}.
     */
    String getBomNum();

    /**
     * Get the {@code tvm_num} value.
     * Can be {@code null}.
     */
    String getTvmNum();

    /**
     * Get the {@code other_num} value.
     * Can be {@code null}.
     */
    String getOtherNum();

    /**
     * Get the {@code ag_per} value.
     * Can be {@code null}.
     */
    String getAgPer();

    /**
     * Get the {@code bom_per} value.
     * Can be {@code null}.
     */
    String getBomPer();

    /**
     * Get the {@code tvm_per} value.
     * Can be {@code null}.
     */
    String getTvmPer();

    /**
     * Get the {@code other_per} value.
     * Can be {@code null}.
     */
    String getOtherPer();

    /**
     * Get the {@code device_fault_num} value.
     * Can be {@code null}.
     */
    String getDeviceFaultNum();

    /**
     * Get the {@code device_fault_per} value.
     * Can be {@code null}.
     */
    String getDeviceFaultPer();

    /**
     * Get the {@code not_device_fault_num} value.
     * Can be {@code null}.
     */
    String getNotDeviceFaultNum();

    /**
     * Get the {@code not_device_fault_per} value.
     * Can be {@code null}.
     */
    String getNotDeviceFaultPer();
}
