package cc.xingyan.android.afc.provider.yunshuline;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code yunshu_line} table.
 */
public class YunshuLineContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return YunshuLineColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  YunshuLineSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  YunshuLineSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public YunshuLineContentValues putTransportTaskId(String value) {
        mContentValues.put(YunshuLineColumns.TRANSPORT_TASK_ID, value);
        return this;
    }

    public YunshuLineContentValues putTransportTaskIdNull() {
        mContentValues.putNull(YunshuLineColumns.TRANSPORT_TASK_ID);
        return this;
    }

    public YunshuLineContentValues putLineNo(String value) {
        mContentValues.put(YunshuLineColumns.LINE_NO, value);
        return this;
    }

    public YunshuLineContentValues putLineNoNull() {
        mContentValues.putNull(YunshuLineColumns.LINE_NO);
        return this;
    }

    public YunshuLineContentValues putPartNo(String value) {
        mContentValues.put(YunshuLineColumns.PART_NO, value);
        return this;
    }

    public YunshuLineContentValues putPartNoNull() {
        mContentValues.putNull(YunshuLineColumns.PART_NO);
        return this;
    }

    public YunshuLineContentValues putPartName(String value) {
        mContentValues.put(YunshuLineColumns.PART_NAME, value);
        return this;
    }

    public YunshuLineContentValues putPartNameNull() {
        mContentValues.putNull(YunshuLineColumns.PART_NAME);
        return this;
    }

    public YunshuLineContentValues putQuantity(String value) {
        mContentValues.put(YunshuLineColumns.QUANTITY, value);
        return this;
    }

    public YunshuLineContentValues putQuantityNull() {
        mContentValues.putNull(YunshuLineColumns.QUANTITY);
        return this;
    }

    public YunshuLineContentValues putUnit(String value) {
        mContentValues.put(YunshuLineColumns.UNIT, value);
        return this;
    }

    public YunshuLineContentValues putUnitNull() {
        mContentValues.putNull(YunshuLineColumns.UNIT);
        return this;
    }

    public YunshuLineContentValues putLotBatchNo(String value) {
        mContentValues.put(YunshuLineColumns.LOT_BATCH_NO, value);
        return this;
    }

    public YunshuLineContentValues putLotBatchNoNull() {
        mContentValues.putNull(YunshuLineColumns.LOT_BATCH_NO);
        return this;
    }

    public YunshuLineContentValues putSerialNo(String value) {
        mContentValues.put(YunshuLineColumns.SERIAL_NO, value);
        return this;
    }

    public YunshuLineContentValues putSerialNoNull() {
        mContentValues.putNull(YunshuLineColumns.SERIAL_NO);
        return this;
    }

    public YunshuLineContentValues putFormat(String value) {
        mContentValues.put(YunshuLineColumns.FORMAT, value);
        return this;
    }

    public YunshuLineContentValues putFormatNull() {
        mContentValues.putNull(YunshuLineColumns.FORMAT);
        return this;
    }

    public YunshuLineContentValues putLineType(String value) {
        mContentValues.put(YunshuLineColumns.LINE_TYPE, value);
        return this;
    }

    public YunshuLineContentValues putLineTypeNull() {
        mContentValues.putNull(YunshuLineColumns.LINE_TYPE);
        return this;
    }

    public YunshuLineContentValues putLineMark(Long value) {
        mContentValues.put(YunshuLineColumns.LINE_MARK, value);
        return this;
    }

    public YunshuLineContentValues putLineMarkNull() {
        mContentValues.putNull(YunshuLineColumns.LINE_MARK);
        return this;
    }

    public YunshuLineContentValues putKeepCol1(String value) {
        mContentValues.put(YunshuLineColumns.KEEP_COL1, value);
        return this;
    }

    public YunshuLineContentValues putKeepCol1Null() {
        mContentValues.putNull(YunshuLineColumns.KEEP_COL1);
        return this;
    }

    public YunshuLineContentValues putKeepCol2(String value) {
        mContentValues.put(YunshuLineColumns.KEEP_COL2, value);
        return this;
    }

    public YunshuLineContentValues putKeepCol2Null() {
        mContentValues.putNull(YunshuLineColumns.KEEP_COL2);
        return this;
    }
}
