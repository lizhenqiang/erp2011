package cc.xingyan.android.afc.provider.report;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code report} table.
 */
public class ReportContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ReportColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  ReportSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  ReportSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public ReportContentValues putCode(String value) {
        mContentValues.put(ReportColumns.CODE, value);
        return this;
    }

    public ReportContentValues putCodeNull() {
        mContentValues.putNull(ReportColumns.CODE);
        return this;
    }

    public ReportContentValues putName(String value) {
        mContentValues.put(ReportColumns.NAME, value);
        return this;
    }

    public ReportContentValues putNameNull() {
        mContentValues.putNull(ReportColumns.NAME);
        return this;
    }

    public ReportContentValues putDataStart(Long value) {
        mContentValues.put(ReportColumns.DATA_START, value);
        return this;
    }

    public ReportContentValues putDataStartNull() {
        mContentValues.putNull(ReportColumns.DATA_START);
        return this;
    }

    public ReportContentValues putDataEnd(Long value) {
        mContentValues.put(ReportColumns.DATA_END, value);
        return this;
    }

    public ReportContentValues putDataEndNull() {
        mContentValues.putNull(ReportColumns.DATA_END);
        return this;
    }

    public ReportContentValues putLastReceNum(String value) {
        mContentValues.put(ReportColumns.LAST_RECE_NUM, value);
        return this;
    }

    public ReportContentValues putLastReceNumNull() {
        mContentValues.putNull(ReportColumns.LAST_RECE_NUM);
        return this;
    }

    public ReportContentValues putCurrectReceNum(String value) {
        mContentValues.put(ReportColumns.CURRECT_RECE_NUM, value);
        return this;
    }

    public ReportContentValues putCurrectReceNumNull() {
        mContentValues.putNull(ReportColumns.CURRECT_RECE_NUM);
        return this;
    }

    public ReportContentValues putReceLrr(String value) {
        mContentValues.put(ReportColumns.RECE_LRR, value);
        return this;
    }

    public ReportContentValues putReceLrrNull() {
        mContentValues.putNull(ReportColumns.RECE_LRR);
        return this;
    }

    public ReportContentValues putLastFormNum(String value) {
        mContentValues.put(ReportColumns.LAST_FORM_NUM, value);
        return this;
    }

    public ReportContentValues putLastFormNumNull() {
        mContentValues.putNull(ReportColumns.LAST_FORM_NUM);
        return this;
    }

    public ReportContentValues putCurrectFormNum(String value) {
        mContentValues.put(ReportColumns.CURRECT_FORM_NUM, value);
        return this;
    }

    public ReportContentValues putCurrectFormNumNull() {
        mContentValues.putNull(ReportColumns.CURRECT_FORM_NUM);
        return this;
    }

    public ReportContentValues putFormLrr(String value) {
        mContentValues.put(ReportColumns.FORM_LRR, value);
        return this;
    }

    public ReportContentValues putFormLrrNull() {
        mContentValues.putNull(ReportColumns.FORM_LRR);
        return this;
    }

    public ReportContentValues putLastFormDelay(String value) {
        mContentValues.put(ReportColumns.LAST_FORM_DELAY, value);
        return this;
    }

    public ReportContentValues putLastFormDelayNull() {
        mContentValues.putNull(ReportColumns.LAST_FORM_DELAY);
        return this;
    }

    public ReportContentValues putCurrectFormDelay(String value) {
        mContentValues.put(ReportColumns.CURRECT_FORM_DELAY, value);
        return this;
    }

    public ReportContentValues putCurrectFormDelayNull() {
        mContentValues.putNull(ReportColumns.CURRECT_FORM_DELAY);
        return this;
    }

    public ReportContentValues putFormDelayLrr(String value) {
        mContentValues.put(ReportColumns.FORM_DELAY_LRR, value);
        return this;
    }

    public ReportContentValues putFormDelayLrrNull() {
        mContentValues.putNull(ReportColumns.FORM_DELAY_LRR);
        return this;
    }

    public ReportContentValues putYtdReceNum(String value) {
        mContentValues.put(ReportColumns.YTD_RECE_NUM, value);
        return this;
    }

    public ReportContentValues putYtdReceNumNull() {
        mContentValues.putNull(ReportColumns.YTD_RECE_NUM);
        return this;
    }

    public ReportContentValues putRecePer(String value) {
        mContentValues.put(ReportColumns.RECE_PER, value);
        return this;
    }

    public ReportContentValues putRecePerNull() {
        mContentValues.putNull(ReportColumns.RECE_PER);
        return this;
    }

    public ReportContentValues putFormPer(String value) {
        mContentValues.put(ReportColumns.FORM_PER, value);
        return this;
    }

    public ReportContentValues putFormPerNull() {
        mContentValues.putNull(ReportColumns.FORM_PER);
        return this;
    }

    public ReportContentValues putAgNum(String value) {
        mContentValues.put(ReportColumns.AG_NUM, value);
        return this;
    }

    public ReportContentValues putAgNumNull() {
        mContentValues.putNull(ReportColumns.AG_NUM);
        return this;
    }

    public ReportContentValues putBomNum(String value) {
        mContentValues.put(ReportColumns.BOM_NUM, value);
        return this;
    }

    public ReportContentValues putBomNumNull() {
        mContentValues.putNull(ReportColumns.BOM_NUM);
        return this;
    }

    public ReportContentValues putTvmNum(String value) {
        mContentValues.put(ReportColumns.TVM_NUM, value);
        return this;
    }

    public ReportContentValues putTvmNumNull() {
        mContentValues.putNull(ReportColumns.TVM_NUM);
        return this;
    }

    public ReportContentValues putOtherNum(String value) {
        mContentValues.put(ReportColumns.OTHER_NUM, value);
        return this;
    }

    public ReportContentValues putOtherNumNull() {
        mContentValues.putNull(ReportColumns.OTHER_NUM);
        return this;
    }

    public ReportContentValues putAgPer(String value) {
        mContentValues.put(ReportColumns.AG_PER, value);
        return this;
    }

    public ReportContentValues putAgPerNull() {
        mContentValues.putNull(ReportColumns.AG_PER);
        return this;
    }

    public ReportContentValues putBomPer(String value) {
        mContentValues.put(ReportColumns.BOM_PER, value);
        return this;
    }

    public ReportContentValues putBomPerNull() {
        mContentValues.putNull(ReportColumns.BOM_PER);
        return this;
    }

    public ReportContentValues putTvmPer(String value) {
        mContentValues.put(ReportColumns.TVM_PER, value);
        return this;
    }

    public ReportContentValues putTvmPerNull() {
        mContentValues.putNull(ReportColumns.TVM_PER);
        return this;
    }

    public ReportContentValues putOtherPer(String value) {
        mContentValues.put(ReportColumns.OTHER_PER, value);
        return this;
    }

    public ReportContentValues putOtherPerNull() {
        mContentValues.putNull(ReportColumns.OTHER_PER);
        return this;
    }

    public ReportContentValues putDeviceFaultNum(String value) {
        mContentValues.put(ReportColumns.DEVICE_FAULT_NUM, value);
        return this;
    }

    public ReportContentValues putDeviceFaultNumNull() {
        mContentValues.putNull(ReportColumns.DEVICE_FAULT_NUM);
        return this;
    }

    public ReportContentValues putDeviceFaultPer(String value) {
        mContentValues.put(ReportColumns.DEVICE_FAULT_PER, value);
        return this;
    }

    public ReportContentValues putDeviceFaultPerNull() {
        mContentValues.putNull(ReportColumns.DEVICE_FAULT_PER);
        return this;
    }

    public ReportContentValues putNotDeviceFaultNum(String value) {
        mContentValues.put(ReportColumns.NOT_DEVICE_FAULT_NUM, value);
        return this;
    }

    public ReportContentValues putNotDeviceFaultNumNull() {
        mContentValues.putNull(ReportColumns.NOT_DEVICE_FAULT_NUM);
        return this;
    }

    public ReportContentValues putNotDeviceFaultPer(String value) {
        mContentValues.put(ReportColumns.NOT_DEVICE_FAULT_PER, value);
        return this;
    }

    public ReportContentValues putNotDeviceFaultPerNull() {
        mContentValues.putNull(ReportColumns.NOT_DEVICE_FAULT_PER);
        return this;
    }
}
