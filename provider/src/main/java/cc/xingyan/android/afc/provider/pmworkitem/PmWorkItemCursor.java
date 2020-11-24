package cc.xingyan.android.afc.provider.pmworkitem;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code pm_work_item} table.
 */
public class PmWorkItemCursor extends AbstractCursor implements PmWorkItemModel {
    public PmWorkItemCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(PmWorkItemColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code work_guid} value.
     * Can be {@code null}.
     */
    public String getWorkGuid() {
        String res = getStringOrNull(PmWorkItemColumns.WORK_GUID);
        return res;
    }

    /**
     * Get the {@code package_id} value.
     * Can be {@code null}.
     */
    public String getPackageId() {
        String res = getStringOrNull(PmWorkItemColumns.PACKAGE_ID);
        return res;
    }

    /**
     * Get the {@code package_description} value.
     * Can be {@code null}.
     */
    public String getPackageDescription() {
        String res = getStringOrNull(PmWorkItemColumns.PACKAGE_DESCRIPTION);
        return res;
    }

    /**
     * Get the {@code guid} value.
     * Can be {@code null}.
     */
    public String getGuid() {
        String res = getStringOrNull(PmWorkItemColumns.GUID);
        return res;
    }

    /**
     * Get the {@code item_id} value.
     * Can be {@code null}.
     */
    public String getItemId() {
        String res = getStringOrNull(PmWorkItemColumns.ITEM_ID);
        return res;
    }

    /**
     * Get the {@code description} value.
     * Can be {@code null}.
     */
    public String getDescription() {
        String res = getStringOrNull(PmWorkItemColumns.DESCRIPTION);
        return res;
    }

    /**
     * Get the {@code ordinal} value.
     * Can be {@code null}.
     */
    public Integer getOrdinal() {
        Integer res = getIntegerOrNull(PmWorkItemColumns.ORDINAL);
        return res;
    }

    /**
     * Get the {@code result_type} value.
     * Can be {@code null}.
     */
    public String getResultType() {
        String res = getStringOrNull(PmWorkItemColumns.RESULT_TYPE);
        return res;
    }

    /**
     * Get the {@code result_min_value} value.
     * Can be {@code null}.
     */
    public Integer getResultMinValue() {
        Integer res = getIntegerOrNull(PmWorkItemColumns.RESULT_MIN_VALUE);
        return res;
    }

    /**
     * Get the {@code result_max_value} value.
     * Can be {@code null}.
     */
    public Integer getResultMaxValue() {
        Integer res = getIntegerOrNull(PmWorkItemColumns.RESULT_MAX_VALUE);
        return res;
    }

    /**
     * Get the {@code result_default_value} value.
     * Can be {@code null}.
     */
    public String getResultDefaultValue() {
        String res = getStringOrNull(PmWorkItemColumns.RESULT_DEFAULT_VALUE);
        return res;
    }

    /**
     * Get the {@code result_value_unit} value.
     * Can be {@code null}.
     */
    public String getResultValueUnit() {
        String res = getStringOrNull(PmWorkItemColumns.RESULT_VALUE_UNIT);
        return res;
    }

    /**
     * Get the {@code result_enum_ordinal} value.
     * Can be {@code null}.
     */
    public Integer getResultEnumOrdinal() {
        Integer res = getIntegerOrNull(PmWorkItemColumns.RESULT_ENUM_ORDINAL);
        return res;
    }

    /**
     * Get the {@code result_value} value.
     * Can be {@code null}.
     */
    public String getResultValue() {
        String res = getStringOrNull(PmWorkItemColumns.RESULT_VALUE);
        return res;
    }

    /**
     * Get the {@code last_modified} value.
     * Can be {@code null}.
     */
    public Long getLastModified() {
        Long res = getLongOrNull(PmWorkItemColumns.LAST_MODIFIED);
        return res;
    }

    /**
     * Get the {@code required} value.
     * Can be {@code null}.
     */
    public Boolean getRequired() {
        Boolean res = getBooleanOrNull(PmWorkItemColumns.REQUIRED);
        return res;
    }
}
