package cc.xingyan.android.afc.provider.pmifsworkitem;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code pmifs_work_item} table.
 */
public class PmifsWorkItemContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return PmifsWorkItemColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  PmifsWorkItemSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  PmifsWorkItemSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public PmifsWorkItemContentValues putOrderId(String value) {
        mContentValues.put(PmifsWorkItemColumns.ORDER_ID, value);
        return this;
    }

    public PmifsWorkItemContentValues putOrderIdNull() {
        mContentValues.putNull(PmifsWorkItemColumns.ORDER_ID);
        return this;
    }

    public PmifsWorkItemContentValues putWorkGuid(String value) {
        mContentValues.put(PmifsWorkItemColumns.WORK_GUID, value);
        return this;
    }

    public PmifsWorkItemContentValues putWorkGuidNull() {
        mContentValues.putNull(PmifsWorkItemColumns.WORK_GUID);
        return this;
    }

    public PmifsWorkItemContentValues putPackageId(String value) {
        mContentValues.put(PmifsWorkItemColumns.PACKAGE_ID, value);
        return this;
    }

    public PmifsWorkItemContentValues putPackageIdNull() {
        mContentValues.putNull(PmifsWorkItemColumns.PACKAGE_ID);
        return this;
    }

    public PmifsWorkItemContentValues putPackageDes(String value) {
        mContentValues.put(PmifsWorkItemColumns.PACKAGE_DES, value);
        return this;
    }

    public PmifsWorkItemContentValues putPackageDesNull() {
        mContentValues.putNull(PmifsWorkItemColumns.PACKAGE_DES);
        return this;
    }

    public PmifsWorkItemContentValues putGuid(String value) {
        mContentValues.put(PmifsWorkItemColumns.GUID, value);
        return this;
    }

    public PmifsWorkItemContentValues putGuidNull() {
        mContentValues.putNull(PmifsWorkItemColumns.GUID);
        return this;
    }

    public PmifsWorkItemContentValues putItemId(String value) {
        mContentValues.put(PmifsWorkItemColumns.ITEM_ID, value);
        return this;
    }

    public PmifsWorkItemContentValues putItemIdNull() {
        mContentValues.putNull(PmifsWorkItemColumns.ITEM_ID);
        return this;
    }

    public PmifsWorkItemContentValues putItemDes(String value) {
        mContentValues.put(PmifsWorkItemColumns.ITEM_DES, value);
        return this;
    }

    public PmifsWorkItemContentValues putItemDesNull() {
        mContentValues.putNull(PmifsWorkItemColumns.ITEM_DES);
        return this;
    }

    public PmifsWorkItemContentValues putWorkSn(Integer value) {
        mContentValues.put(PmifsWorkItemColumns.WORK_SN, value);
        return this;
    }

    public PmifsWorkItemContentValues putWorkSnNull() {
        mContentValues.putNull(PmifsWorkItemColumns.WORK_SN);
        return this;
    }

    public PmifsWorkItemContentValues putResultType(String value) {
        mContentValues.put(PmifsWorkItemColumns.RESULT_TYPE, value);
        return this;
    }

    public PmifsWorkItemContentValues putResultTypeNull() {
        mContentValues.putNull(PmifsWorkItemColumns.RESULT_TYPE);
        return this;
    }

    public PmifsWorkItemContentValues putResultMinValue(Integer value) {
        mContentValues.put(PmifsWorkItemColumns.RESULT_MIN_VALUE, value);
        return this;
    }

    public PmifsWorkItemContentValues putResultMinValueNull() {
        mContentValues.putNull(PmifsWorkItemColumns.RESULT_MIN_VALUE);
        return this;
    }

    public PmifsWorkItemContentValues putResultMaxValue(Integer value) {
        mContentValues.put(PmifsWorkItemColumns.RESULT_MAX_VALUE, value);
        return this;
    }

    public PmifsWorkItemContentValues putResultMaxValueNull() {
        mContentValues.putNull(PmifsWorkItemColumns.RESULT_MAX_VALUE);
        return this;
    }

    public PmifsWorkItemContentValues putResultDefaultValue(String value) {
        mContentValues.put(PmifsWorkItemColumns.RESULT_DEFAULT_VALUE, value);
        return this;
    }

    public PmifsWorkItemContentValues putResultDefaultValueNull() {
        mContentValues.putNull(PmifsWorkItemColumns.RESULT_DEFAULT_VALUE);
        return this;
    }

    public PmifsWorkItemContentValues putResultValueUnit(String value) {
        mContentValues.put(PmifsWorkItemColumns.RESULT_VALUE_UNIT, value);
        return this;
    }

    public PmifsWorkItemContentValues putResultValueUnitNull() {
        mContentValues.putNull(PmifsWorkItemColumns.RESULT_VALUE_UNIT);
        return this;
    }

    public PmifsWorkItemContentValues putResultEnumOrdinal(Integer value) {
        mContentValues.put(PmifsWorkItemColumns.RESULT_ENUM_ORDINAL, value);
        return this;
    }

    public PmifsWorkItemContentValues putResultEnumOrdinalNull() {
        mContentValues.putNull(PmifsWorkItemColumns.RESULT_ENUM_ORDINAL);
        return this;
    }

    public PmifsWorkItemContentValues putResultValue(String value) {
        mContentValues.put(PmifsWorkItemColumns.RESULT_VALUE, value);
        return this;
    }

    public PmifsWorkItemContentValues putResultValueNull() {
        mContentValues.putNull(PmifsWorkItemColumns.RESULT_VALUE);
        return this;
    }

    public PmifsWorkItemContentValues putLastModified(Long value) {
        mContentValues.put(PmifsWorkItemColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmifsWorkItemContentValues putLastModifiedNull() {
        mContentValues.putNull(PmifsWorkItemColumns.LAST_MODIFIED);
        return this;
    }

    public PmifsWorkItemContentValues putRequired(Boolean value) {
        mContentValues.put(PmifsWorkItemColumns.REQUIRED, value);
        return this;
    }

    public PmifsWorkItemContentValues putRequiredNull() {
        mContentValues.putNull(PmifsWorkItemColumns.REQUIRED);
        return this;
    }
}
