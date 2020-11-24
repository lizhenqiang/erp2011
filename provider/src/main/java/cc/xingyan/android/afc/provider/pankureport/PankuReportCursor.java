package cc.xingyan.android.afc.provider.pankureport;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code panku_report} table.
 */
public class PankuReportCursor extends AbstractCursor implements PankuReportModel {
    public PankuReportCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(PankuReportColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code user_id} value.
     * Can be {@code null}.
     */
    public String getUserId() {
        String res = getStringOrNull(PankuReportColumns.USER_ID);
        return res;
    }

    /**
     * Get the {@code report_no} value.
     * Can be {@code null}.
     */
    public String getReportNo() {
        String res = getStringOrNull(PankuReportColumns.REPORT_NO);
        return res;
    }

    /**
     * Get the {@code part_no} value.
     * Can be {@code null}.
     */
    public String getPartNo() {
        String res = getStringOrNull(PankuReportColumns.PART_NO);
        return res;
    }

    /**
     * Get the {@code part_name} value.
     * Can be {@code null}.
     */
    public String getPartName() {
        String res = getStringOrNull(PankuReportColumns.PART_NAME);
        return res;
    }

    /**
     * Get the {@code actual_amount} value.
     * Can be {@code null}.
     */
    public String getActualAmount() {
        String res = getStringOrNull(PankuReportColumns.ACTUAL_AMOUNT);
        return res;
    }

    /**
     * Get the {@code lotbatch_no} value.
     * Can be {@code null}.
     */
    public String getLotbatchNo() {
        String res = getStringOrNull(PankuReportColumns.LOTBATCH_NO);
        return res;
    }

    /**
     * Get the {@code line_no} value.
     * Can be {@code null}.
     */
    public String getLineNo() {
        String res = getStringOrNull(PankuReportColumns.LINE_NO);
        return res;
    }

    /**
     * Get the {@code part_seq} value.
     * Can be {@code null}.
     */
    public String getPartSeq() {
        String res = getStringOrNull(PankuReportColumns.PART_SEQ);
        return res;
    }

    /**
     * Get the {@code warehouse_no} value.
     * Can be {@code null}.
     */
    public String getWarehouseNo() {
        String res = getStringOrNull(PankuReportColumns.WAREHOUSE_NO);
        return res;
    }

    /**
     * Get the {@code warehouse_name} value.
     * Can be {@code null}.
     */
    public String getWarehouseName() {
        String res = getStringOrNull(PankuReportColumns.WAREHOUSE_NAME);
        return res;
    }

    /**
     * Get the {@code pandian_mark} value.
     * Can be {@code null}.
     */
    public String getPandianMark() {
        String res = getStringOrNull(PankuReportColumns.PANDIAN_MARK);
        return res;
    }

    /**
     * Get the {@code pandian_time} value.
     * Can be {@code null}.
     */
    public Long getPandianTime() {
        Long res = getLongOrNull(PankuReportColumns.PANDIAN_TIME);
        return res;
    }
}
