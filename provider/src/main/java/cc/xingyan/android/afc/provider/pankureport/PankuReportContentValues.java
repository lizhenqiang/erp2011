package cc.xingyan.android.afc.provider.pankureport;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code panku_report} table.
 */
public class PankuReportContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return PankuReportColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  PankuReportSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  PankuReportSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public PankuReportContentValues putUserId(String value) {
        mContentValues.put(PankuReportColumns.USER_ID, value);
        return this;
    }

    public PankuReportContentValues putUserIdNull() {
        mContentValues.putNull(PankuReportColumns.USER_ID);
        return this;
    }

    public PankuReportContentValues putReportNo(String value) {
        mContentValues.put(PankuReportColumns.REPORT_NO, value);
        return this;
    }

    public PankuReportContentValues putReportNoNull() {
        mContentValues.putNull(PankuReportColumns.REPORT_NO);
        return this;
    }

    public PankuReportContentValues putPartNo(String value) {
        mContentValues.put(PankuReportColumns.PART_NO, value);
        return this;
    }

    public PankuReportContentValues putPartNoNull() {
        mContentValues.putNull(PankuReportColumns.PART_NO);
        return this;
    }

    public PankuReportContentValues putPartName(String value) {
        mContentValues.put(PankuReportColumns.PART_NAME, value);
        return this;
    }

    public PankuReportContentValues putPartNameNull() {
        mContentValues.putNull(PankuReportColumns.PART_NAME);
        return this;
    }

    public PankuReportContentValues putActualAmount(String value) {
        mContentValues.put(PankuReportColumns.ACTUAL_AMOUNT, value);
        return this;
    }

    public PankuReportContentValues putActualAmountNull() {
        mContentValues.putNull(PankuReportColumns.ACTUAL_AMOUNT);
        return this;
    }

    public PankuReportContentValues putLotbatchNo(String value) {
        mContentValues.put(PankuReportColumns.LOTBATCH_NO, value);
        return this;
    }

    public PankuReportContentValues putLotbatchNoNull() {
        mContentValues.putNull(PankuReportColumns.LOTBATCH_NO);
        return this;
    }

    public PankuReportContentValues putLineNo(String value) {
        mContentValues.put(PankuReportColumns.LINE_NO, value);
        return this;
    }

    public PankuReportContentValues putLineNoNull() {
        mContentValues.putNull(PankuReportColumns.LINE_NO);
        return this;
    }

    public PankuReportContentValues putPartSeq(String value) {
        mContentValues.put(PankuReportColumns.PART_SEQ, value);
        return this;
    }

    public PankuReportContentValues putPartSeqNull() {
        mContentValues.putNull(PankuReportColumns.PART_SEQ);
        return this;
    }

    public PankuReportContentValues putWarehouseNo(String value) {
        mContentValues.put(PankuReportColumns.WAREHOUSE_NO, value);
        return this;
    }

    public PankuReportContentValues putWarehouseNoNull() {
        mContentValues.putNull(PankuReportColumns.WAREHOUSE_NO);
        return this;
    }

    public PankuReportContentValues putWarehouseName(String value) {
        mContentValues.put(PankuReportColumns.WAREHOUSE_NAME, value);
        return this;
    }

    public PankuReportContentValues putWarehouseNameNull() {
        mContentValues.putNull(PankuReportColumns.WAREHOUSE_NAME);
        return this;
    }

    public PankuReportContentValues putPandianMark(String value) {
        mContentValues.put(PankuReportColumns.PANDIAN_MARK, value);
        return this;
    }

    public PankuReportContentValues putPandianMarkNull() {
        mContentValues.putNull(PankuReportColumns.PANDIAN_MARK);
        return this;
    }

    public PankuReportContentValues putPandianTime(Long value) {
        mContentValues.put(PankuReportColumns.PANDIAN_TIME, value);
        return this;
    }

    public PankuReportContentValues putPandianTimeNull() {
        mContentValues.putNull(PankuReportColumns.PANDIAN_TIME);
        return this;
    }
}
