package cc.xingyan.android.afc.provider.report;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code report} table.
 */
public class ReportCursor extends AbstractCursor implements ReportModel {
    public ReportCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(ReportColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code code} value.
     * Can be {@code null}.
     */
    public String getCode() {
        String res = getStringOrNull(ReportColumns.CODE);
        return res;
    }

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    public String getName() {
        String res = getStringOrNull(ReportColumns.NAME);
        return res;
    }

    /**
     * Get the {@code data_start} value.
     * Can be {@code null}.
     */
    public Long getDataStart() {
        Long res = getLongOrNull(ReportColumns.DATA_START);
        return res;
    }

    /**
     * Get the {@code data_end} value.
     * Can be {@code null}.
     */
    public Long getDataEnd() {
        Long res = getLongOrNull(ReportColumns.DATA_END);
        return res;
    }

    /**
     * Get the {@code last_rece_num} value.
     * Can be {@code null}.
     */
    public String getLastReceNum() {
        String res = getStringOrNull(ReportColumns.LAST_RECE_NUM);
        return res;
    }

    /**
     * Get the {@code currect_rece_num} value.
     * Can be {@code null}.
     */
    public String getCurrectReceNum() {
        String res = getStringOrNull(ReportColumns.CURRECT_RECE_NUM);
        return res;
    }

    /**
     * Get the {@code rece_lrr} value.
     * Can be {@code null}.
     */
    public String getReceLrr() {
        String res = getStringOrNull(ReportColumns.RECE_LRR);
        return res;
    }

    /**
     * Get the {@code last_form_num} value.
     * Can be {@code null}.
     */
    public String getLastFormNum() {
        String res = getStringOrNull(ReportColumns.LAST_FORM_NUM);
        return res;
    }

    /**
     * Get the {@code currect_form_num} value.
     * Can be {@code null}.
     */
    public String getCurrectFormNum() {
        String res = getStringOrNull(ReportColumns.CURRECT_FORM_NUM);
        return res;
    }

    /**
     * Get the {@code form_lrr} value.
     * Can be {@code null}.
     */
    public String getFormLrr() {
        String res = getStringOrNull(ReportColumns.FORM_LRR);
        return res;
    }

    /**
     * Get the {@code last_form_delay} value.
     * Can be {@code null}.
     */
    public String getLastFormDelay() {
        String res = getStringOrNull(ReportColumns.LAST_FORM_DELAY);
        return res;
    }

    /**
     * Get the {@code currect_form_delay} value.
     * Can be {@code null}.
     */
    public String getCurrectFormDelay() {
        String res = getStringOrNull(ReportColumns.CURRECT_FORM_DELAY);
        return res;
    }

    /**
     * Get the {@code form_delay_lrr} value.
     * Can be {@code null}.
     */
    public String getFormDelayLrr() {
        String res = getStringOrNull(ReportColumns.FORM_DELAY_LRR);
        return res;
    }

    /**
     * Get the {@code ytd_rece_num} value.
     * Can be {@code null}.
     */
    public String getYtdReceNum() {
        String res = getStringOrNull(ReportColumns.YTD_RECE_NUM);
        return res;
    }

    /**
     * Get the {@code rece_per} value.
     * Can be {@code null}.
     */
    public String getRecePer() {
        String res = getStringOrNull(ReportColumns.RECE_PER);
        return res;
    }

    /**
     * Get the {@code form_per} value.
     * Can be {@code null}.
     */
    public String getFormPer() {
        String res = getStringOrNull(ReportColumns.FORM_PER);
        return res;
    }

    /**
     * Get the {@code ag_num} value.
     * Can be {@code null}.
     */
    public String getAgNum() {
        String res = getStringOrNull(ReportColumns.AG_NUM);
        return res;
    }

    /**
     * Get the {@code bom_num} value.
     * Can be {@code null}.
     */
    public String getBomNum() {
        String res = getStringOrNull(ReportColumns.BOM_NUM);
        return res;
    }

    /**
     * Get the {@code tvm_num} value.
     * Can be {@code null}.
     */
    public String getTvmNum() {
        String res = getStringOrNull(ReportColumns.TVM_NUM);
        return res;
    }

    /**
     * Get the {@code other_num} value.
     * Can be {@code null}.
     */
    public String getOtherNum() {
        String res = getStringOrNull(ReportColumns.OTHER_NUM);
        return res;
    }

    /**
     * Get the {@code ag_per} value.
     * Can be {@code null}.
     */
    public String getAgPer() {
        String res = getStringOrNull(ReportColumns.AG_PER);
        return res;
    }

    /**
     * Get the {@code bom_per} value.
     * Can be {@code null}.
     */
    public String getBomPer() {
        String res = getStringOrNull(ReportColumns.BOM_PER);
        return res;
    }

    /**
     * Get the {@code tvm_per} value.
     * Can be {@code null}.
     */
    public String getTvmPer() {
        String res = getStringOrNull(ReportColumns.TVM_PER);
        return res;
    }

    /**
     * Get the {@code other_per} value.
     * Can be {@code null}.
     */
    public String getOtherPer() {
        String res = getStringOrNull(ReportColumns.OTHER_PER);
        return res;
    }

    /**
     * Get the {@code device_fault_num} value.
     * Can be {@code null}.
     */
    public String getDeviceFaultNum() {
        String res = getStringOrNull(ReportColumns.DEVICE_FAULT_NUM);
        return res;
    }

    /**
     * Get the {@code device_fault_per} value.
     * Can be {@code null}.
     */
    public String getDeviceFaultPer() {
        String res = getStringOrNull(ReportColumns.DEVICE_FAULT_PER);
        return res;
    }

    /**
     * Get the {@code not_device_fault_num} value.
     * Can be {@code null}.
     */
    public String getNotDeviceFaultNum() {
        String res = getStringOrNull(ReportColumns.NOT_DEVICE_FAULT_NUM);
        return res;
    }

    /**
     * Get the {@code not_device_fault_per} value.
     * Can be {@code null}.
     */
    public String getNotDeviceFaultPer() {
        String res = getStringOrNull(ReportColumns.NOT_DEVICE_FAULT_PER);
        return res;
    }
}
