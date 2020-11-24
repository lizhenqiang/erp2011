package cc.xingyan.android.afc.provider.pmworkitem;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code pm_work_item} table.
 */
public class PmWorkItemContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return PmWorkItemColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  PmWorkItemSelection where) {
        return contentResolver.update(where.uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  PmWorkItemSelection where) {
        return context.getContentResolver().update(where.uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public PmWorkItemContentValues putWorkGuid(String value) {
        mContentValues.put(PmWorkItemColumns.WORK_GUID, value);
        return this;
    }

    public PmWorkItemContentValues putWorkGuidNull() {
        mContentValues.putNull(PmWorkItemColumns.WORK_GUID);
        return this;
    }

    public PmWorkItemContentValues putPackageId(String value) {
        mContentValues.put(PmWorkItemColumns.PACKAGE_ID, value);
        return this;
    }

    public PmWorkItemContentValues putPackageIdNull() {
        mContentValues.putNull(PmWorkItemColumns.PACKAGE_ID);
        return this;
    }

    public PmWorkItemContentValues putPackageDescription(String value) {
        mContentValues.put(PmWorkItemColumns.PACKAGE_DESCRIPTION, value);
        return this;
    }

    public PmWorkItemContentValues putPackageDescriptionNull() {
        mContentValues.putNull(PmWorkItemColumns.PACKAGE_DESCRIPTION);
        return this;
    }

    public PmWorkItemContentValues putGuid(String value) {
        mContentValues.put(PmWorkItemColumns.GUID, value);
        return this;
    }

    public PmWorkItemContentValues putGuidNull() {
        mContentValues.putNull(PmWorkItemColumns.GUID);
        return this;
    }

    public PmWorkItemContentValues putItemId(String value) {
        mContentValues.put(PmWorkItemColumns.ITEM_ID, value);
        return this;
    }

    public PmWorkItemContentValues putItemIdNull() {
        mContentValues.putNull(PmWorkItemColumns.ITEM_ID);
        return this;
    }

    public PmWorkItemContentValues putDescription(String value) {
        mContentValues.put(PmWorkItemColumns.DESCRIPTION, value);
        return this;
    }

    public PmWorkItemContentValues putDescriptionNull() {
        mContentValues.putNull(PmWorkItemColumns.DESCRIPTION);
        return this;
    }

    public PmWorkItemContentValues putOrdinal(Integer value) {
        mContentValues.put(PmWorkItemColumns.ORDINAL, value);
        return this;
    }

    public PmWorkItemContentValues putOrdinalNull() {
        mContentValues.putNull(PmWorkItemColumns.ORDINAL);
        return this;
    }

    public PmWorkItemContentValues putResultType(String value) {
        mContentValues.put(PmWorkItemColumns.RESULT_TYPE, value);
        return this;
    }

    public PmWorkItemContentValues putResultTypeNull() {
        mContentValues.putNull(PmWorkItemColumns.RESULT_TYPE);
        return this;
    }

    public PmWorkItemContentValues putResultMinValue(Integer value) {
        mContentValues.put(PmWorkItemColumns.RESULT_MIN_VALUE, value);
        return this;
    }

    public PmWorkItemContentValues putResultMinValueNull() {
        mContentValues.putNull(PmWorkItemColumns.RESULT_MIN_VALUE);
        return this;
    }

    public PmWorkItemContentValues putResultMaxValue(Integer value) {
        mContentValues.put(PmWorkItemColumns.RESULT_MAX_VALUE, value);
        return this;
    }

    public PmWorkItemContentValues putResultMaxValueNull() {
        mContentValues.putNull(PmWorkItemColumns.RESULT_MAX_VALUE);
        return this;
    }

    public PmWorkItemContentValues putResultDefaultValue(String value) {
        mContentValues.put(PmWorkItemColumns.RESULT_DEFAULT_VALUE, value);
        return this;
    }

    public PmWorkItemContentValues putResultDefaultValueNull() {
        mContentValues.putNull(PmWorkItemColumns.RESULT_DEFAULT_VALUE);
        return this;
    }

    public PmWorkItemContentValues putResultValueUnit(String value) {
        mContentValues.put(PmWorkItemColumns.RESULT_VALUE_UNIT, value);
        return this;
    }

    public PmWorkItemContentValues putResultValueUnitNull() {
        mContentValues.putNull(PmWorkItemColumns.RESULT_VALUE_UNIT);
        return this;
    }

    public PmWorkItemContentValues putResultEnumOrdinal(Integer value) {
        mContentValues.put(PmWorkItemColumns.RESULT_ENUM_ORDINAL, value);
        return this;
    }

    public PmWorkItemContentValues putResultEnumOrdinalNull() {
        mContentValues.putNull(PmWorkItemColumns.RESULT_ENUM_ORDINAL);
        return this;
    }

    public PmWorkItemContentValues putResultValue(String value) {
        mContentValues.put(PmWorkItemColumns.RESULT_VALUE, value);
        return this;
    }

    public PmWorkItemContentValues putResultValueNull() {
        mContentValues.putNull(PmWorkItemColumns.RESULT_VALUE);
        return this;
    }

    public PmWorkItemContentValues putLastModified(Long value) {
        mContentValues.put(PmWorkItemColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmWorkItemContentValues putLastModifiedNull() {
        mContentValues.putNull(PmWorkItemColumns.LAST_MODIFIED);
        return this;
    }

    public PmWorkItemContentValues putRequired(Boolean value) {
        mContentValues.put(PmWorkItemColumns.REQUIRED, value);
        return this;
    }

    public PmWorkItemContentValues putRequiredNull() {
        mContentValues.putNull(PmWorkItemColumns.REQUIRED);
        return this;
    }
}
