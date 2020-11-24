package cc.xingyan.android.afc.provider.cmspareparts;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code cm_spare_parts} table.
 */
public class CmSparePartsContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return CmSparePartsColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  CmSparePartsSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  CmSparePartsSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public CmSparePartsContentValues putCmWorkId(String value) {
        mContentValues.put(CmSparePartsColumns.CM_WORK_ID, value);
        return this;
    }

    public CmSparePartsContentValues putCmWorkIdNull() {
        mContentValues.putNull(CmSparePartsColumns.CM_WORK_ID);
        return this;
    }

    public CmSparePartsContentValues putOrderId(String value) {
        mContentValues.put(CmSparePartsColumns.ORDER_ID, value);
        return this;
    }

    public CmSparePartsContentValues putOrderIdNull() {
        mContentValues.putNull(CmSparePartsColumns.ORDER_ID);
        return this;
    }

    public CmSparePartsContentValues putPartId(String value) {
        mContentValues.put(CmSparePartsColumns.PART_ID, value);
        return this;
    }

    public CmSparePartsContentValues putPartIdNull() {
        mContentValues.putNull(CmSparePartsColumns.PART_ID);
        return this;
    }

    public CmSparePartsContentValues putPartDescription(String value) {
        mContentValues.put(CmSparePartsColumns.PART_DESCRIPTION, value);
        return this;
    }

    public CmSparePartsContentValues putPartDescriptionNull() {
        mContentValues.putNull(CmSparePartsColumns.PART_DESCRIPTION);
        return this;
    }

    public CmSparePartsContentValues putApplyDate(Integer value) {
        mContentValues.put(CmSparePartsColumns.APPLY_DATE, value);
        return this;
    }

    public CmSparePartsContentValues putApplyDateNull() {
        mContentValues.putNull(CmSparePartsColumns.APPLY_DATE);
        return this;
    }

    public CmSparePartsContentValues putInstallDate(Integer value) {
        mContentValues.put(CmSparePartsColumns.INSTALL_DATE, value);
        return this;
    }

    public CmSparePartsContentValues putInstallDateNull() {
        mContentValues.putNull(CmSparePartsColumns.INSTALL_DATE);
        return this;
    }

    public CmSparePartsContentValues putOldPartSn(String value) {
        mContentValues.put(CmSparePartsColumns.OLD_PART_SN, value);
        return this;
    }

    public CmSparePartsContentValues putOldPartSnNull() {
        mContentValues.putNull(CmSparePartsColumns.OLD_PART_SN);
        return this;
    }

    public CmSparePartsContentValues putNewPartSn(String value) {
        mContentValues.put(CmSparePartsColumns.NEW_PART_SN, value);
        return this;
    }

    public CmSparePartsContentValues putNewPartSnNull() {
        mContentValues.putNull(CmSparePartsColumns.NEW_PART_SN);
        return this;
    }

    public CmSparePartsContentValues putSparePartStatus(Integer value) {
        mContentValues.put(CmSparePartsColumns.SPARE_PART_STATUS, value);
        return this;
    }

    public CmSparePartsContentValues putSparePartStatusNull() {
        mContentValues.putNull(CmSparePartsColumns.SPARE_PART_STATUS);
        return this;
    }
}
