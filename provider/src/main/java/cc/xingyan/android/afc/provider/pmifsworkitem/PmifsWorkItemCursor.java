package cc.xingyan.android.afc.provider.pmifsworkitem;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code pmifs_work_item} table.
 */
public class PmifsWorkItemCursor extends AbstractCursor implements PmifsWorkItemModel {
    public PmifsWorkItemCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(PmifsWorkItemColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code order_id} value.
     * Can be {@code null}.
     */
    public String getOrderId() {
        String res = getStringOrNull(PmifsWorkItemColumns.ORDER_ID);
        return res;
    }

    /**
     * Get the {@code work_guid} value.
     * Can be {@code null}.
     */
    public String getWorkGuid() {
        String res = getStringOrNull(PmifsWorkItemColumns.WORK_GUID);
        return res;
    }

    /**
     * Get the {@code package_id} value.
     * Can be {@code null}.
     */
    public String getPackageId() {
        String res = getStringOrNull(PmifsWorkItemColumns.PACKAGE_ID);
        return res;
    }

    /**
     * Get the {@code package_des} value.
     * Can be {@code null}.
     */
    public String getPackageDes() {
        String res = getStringOrNull(PmifsWorkItemColumns.PACKAGE_DES);
        return res;
    }

    /**
     * Get the {@code guid} value.
     * Can be {@code null}.
     */
    public String getGuid() {
        String res = getStringOrNull(PmifsWorkItemColumns.GUID);
        return res;
    }

    /**
     * Get the {@code item_id} value.
     * Can be {@code null}.
     */
    public String getItemId() {
        String res = getStringOrNull(PmifsWorkItemColumns.ITEM_ID);
        return res;
    }

    /**
     * Get the {@code item_des} value.
     * Can be {@code null}.
     */
    public String getItemDes() {
        String res = getStringOrNull(PmifsWorkItemColumns.ITEM_DES);
        return res;
    }

    /**
     * Get the {@code work_sn} value.
     * Can be {@code null}.
     */
    public Integer getWorkSn() {
        Integer res = getIntegerOrNull(PmifsWorkItemColumns.WORK_SN);
        return res;
    }

    /**
     * Get the {@code result_type} value.
     * Can be {@code null}.
     */
    public String getResultType() {
        String res = getStringOrNull(PmifsWorkItemColumns.RESULT_TYPE);
        return res;
    }

    /**
     * Get the {@code result_min_value} value.
     * Can be {@code null}.
     */
    public Integer getResultMinValue() {
        Integer res = getIntegerOrNull(PmifsWorkItemColumns.RESULT_MIN_VALUE);
        return res;
    }

    /**
     * Get the {@code result_max_value} value.
     * Can be {@code null}.
     */
    public Integer getResultMaxValue() {
        Integer res = getIntegerOrNull(PmifsWorkItemColumns.RESULT_MAX_VALUE);
        return res;
    }

    /**
     * Get the {@code result_default_value} value.
     * Can be {@code null}.
     */
    public String getResultDefaultValue() {
        String res = getStringOrNull(PmifsWorkItemColumns.RESULT_DEFAULT_VALUE);
        return res;
    }

    /**
     * Get the {@code result_value_unit} value.
     * Can be {@code null}.
     */
    public String getResultValueUnit() {
        String res = getStringOrNull(PmifsWorkItemColumns.RESULT_VALUE_UNIT);
        return res;
    }

    /**
     * Get the {@code result_enum_ordinal} value.
     * Can be {@code null}.
     */
    public Integer getResultEnumOrdinal() {
        Integer res = getIntegerOrNull(PmifsWorkItemColumns.RESULT_ENUM_ORDINAL);
        return res;
    }

    /**
     * Get the {@code result_value} value.
     * Can be {@code null}.
     */
    public String getResultValue() {
        String res = getStringOrNull(PmifsWorkItemColumns.RESULT_VALUE);
        return res;
    }

    /**
     * Get the {@code last_modified} value.
     * Can be {@code null}.
     */
    public Long getLastModified() {
        Long res = getLongOrNull(PmifsWorkItemColumns.LAST_MODIFIED);
        return res;
    }

    /**
     * Get the {@code required} value.
     * Can be {@code null}.
     */
    public Boolean getRequired() {
        Boolean res = getBooleanOrNull(PmifsWorkItemColumns.REQUIRED);
        return res;
    }
}
