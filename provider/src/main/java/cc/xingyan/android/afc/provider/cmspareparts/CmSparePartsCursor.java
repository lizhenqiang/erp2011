package cc.xingyan.android.afc.provider.cmspareparts;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code cm_spare_parts} table.
 */
public class CmSparePartsCursor extends AbstractCursor implements CmSparePartsModel {
    public CmSparePartsCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(CmSparePartsColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code cm_work_id} value.
     * Can be {@code null}.
     */
    public String getCmWorkId() {
        String res = getStringOrNull(CmSparePartsColumns.CM_WORK_ID);
        return res;
    }

    /**
     * Get the {@code order_id} value.
     * Can be {@code null}.
     */
    public String getOrderId() {
        String res = getStringOrNull(CmSparePartsColumns.ORDER_ID);
        return res;
    }

    /**
     * Get the {@code part_id} value.
     * Can be {@code null}.
     */
    public String getPartId() {
        String res = getStringOrNull(CmSparePartsColumns.PART_ID);
        return res;
    }

    /**
     * Get the {@code part_description} value.
     * Can be {@code null}.
     */
    public String getPartDescription() {
        String res = getStringOrNull(CmSparePartsColumns.PART_DESCRIPTION);
        return res;
    }

    /**
     * Get the {@code apply_date} value.
     * Can be {@code null}.
     */
    public Integer getApplyDate() {
        Integer res = getIntegerOrNull(CmSparePartsColumns.APPLY_DATE);
        return res;
    }

    /**
     * Get the {@code install_date} value.
     * Can be {@code null}.
     */
    public Integer getInstallDate() {
        Integer res = getIntegerOrNull(CmSparePartsColumns.INSTALL_DATE);
        return res;
    }

    /**
     * Get the {@code old_part_sn} value.
     * Can be {@code null}.
     */
    public String getOldPartSn() {
        String res = getStringOrNull(CmSparePartsColumns.OLD_PART_SN);
        return res;
    }

    /**
     * Get the {@code new_part_sn} value.
     * Can be {@code null}.
     */
    public String getNewPartSn() {
        String res = getStringOrNull(CmSparePartsColumns.NEW_PART_SN);
        return res;
    }

    /**
     * Get the {@code spare_part_status} value.
     * Can be {@code null}.
     */
    public Integer getSparePartStatus() {
        Integer res = getIntegerOrNull(CmSparePartsColumns.SPARE_PART_STATUS);
        return res;
    }
}
