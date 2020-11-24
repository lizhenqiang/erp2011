package cc.xingyan.android.afc.provider.pmworkitem;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code pm_work_item} table.
 */
public class PmWorkItemSelection extends AbstractSelection<PmWorkItemSelection> {
    @Override
    protected Uri baseUri() {
        return PmWorkItemColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PmWorkItemCursor} object, which is positioned before the first entry, or null.
     */
    public PmWorkItemCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PmWorkItemCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public PmWorkItemCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PmWorkItemCursor} object, which is positioned before the first entry, or null.
     */
    public PmWorkItemCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PmWorkItemCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public PmWorkItemCursor query(Context context) {
        return query(context, null);
    }


    public PmWorkItemSelection id(long... value) {
        addEquals("pm_work_item." + PmWorkItemColumns._ID, toObjectArray(value));
        return this;
    }

    public PmWorkItemSelection idNot(long... value) {
        addNotEquals("pm_work_item." + PmWorkItemColumns._ID, toObjectArray(value));
        return this;
    }

    public PmWorkItemSelection orderById(boolean desc) {
        orderBy("pm_work_item." + PmWorkItemColumns._ID, desc);
        return this;
    }

    public PmWorkItemSelection orderById() {
        return orderById(false);
    }

    public PmWorkItemSelection workGuid(String... value) {
        addEquals(PmWorkItemColumns.WORK_GUID, value);
        return this;
    }

    public PmWorkItemSelection workGuidNot(String... value) {
        addNotEquals(PmWorkItemColumns.WORK_GUID, value);
        return this;
    }

    public PmWorkItemSelection workGuidLike(String... value) {
        addLike(PmWorkItemColumns.WORK_GUID, value);
        return this;
    }

    public PmWorkItemSelection workGuidContains(String... value) {
        addContains(PmWorkItemColumns.WORK_GUID, value);
        return this;
    }

    public PmWorkItemSelection workGuidStartsWith(String... value) {
        addStartsWith(PmWorkItemColumns.WORK_GUID, value);
        return this;
    }

    public PmWorkItemSelection workGuidEndsWith(String... value) {
        addEndsWith(PmWorkItemColumns.WORK_GUID, value);
        return this;
    }

    public PmWorkItemSelection orderByWorkGuid(boolean desc) {
        orderBy(PmWorkItemColumns.WORK_GUID, desc);
        return this;
    }

    public PmWorkItemSelection orderByWorkGuid() {
        orderBy(PmWorkItemColumns.WORK_GUID, false);
        return this;
    }

    public PmWorkItemSelection packageId(String... value) {
        addEquals(PmWorkItemColumns.PACKAGE_ID, value);
        return this;
    }

    public PmWorkItemSelection packageIdNot(String... value) {
        addNotEquals(PmWorkItemColumns.PACKAGE_ID, value);
        return this;
    }

    public PmWorkItemSelection packageIdLike(String... value) {
        addLike(PmWorkItemColumns.PACKAGE_ID, value);
        return this;
    }

    public PmWorkItemSelection packageIdContains(String... value) {
        addContains(PmWorkItemColumns.PACKAGE_ID, value);
        return this;
    }

    public PmWorkItemSelection packageIdStartsWith(String... value) {
        addStartsWith(PmWorkItemColumns.PACKAGE_ID, value);
        return this;
    }

    public PmWorkItemSelection packageIdEndsWith(String... value) {
        addEndsWith(PmWorkItemColumns.PACKAGE_ID, value);
        return this;
    }

    public PmWorkItemSelection orderByPackageId(boolean desc) {
        orderBy(PmWorkItemColumns.PACKAGE_ID, desc);
        return this;
    }

    public PmWorkItemSelection orderByPackageId() {
        orderBy(PmWorkItemColumns.PACKAGE_ID, false);
        return this;
    }

    public PmWorkItemSelection packageDescription(String... value) {
        addEquals(PmWorkItemColumns.PACKAGE_DESCRIPTION, value);
        return this;
    }

    public PmWorkItemSelection packageDescriptionNot(String... value) {
        addNotEquals(PmWorkItemColumns.PACKAGE_DESCRIPTION, value);
        return this;
    }

    public PmWorkItemSelection packageDescriptionLike(String... value) {
        addLike(PmWorkItemColumns.PACKAGE_DESCRIPTION, value);
        return this;
    }

    public PmWorkItemSelection packageDescriptionContains(String... value) {
        addContains(PmWorkItemColumns.PACKAGE_DESCRIPTION, value);
        return this;
    }

    public PmWorkItemSelection packageDescriptionStartsWith(String... value) {
        addStartsWith(PmWorkItemColumns.PACKAGE_DESCRIPTION, value);
        return this;
    }

    public PmWorkItemSelection packageDescriptionEndsWith(String... value) {
        addEndsWith(PmWorkItemColumns.PACKAGE_DESCRIPTION, value);
        return this;
    }

    public PmWorkItemSelection orderByPackageDescription(boolean desc) {
        orderBy(PmWorkItemColumns.PACKAGE_DESCRIPTION, desc);
        return this;
    }

    public PmWorkItemSelection orderByPackageDescription() {
        orderBy(PmWorkItemColumns.PACKAGE_DESCRIPTION, false);
        return this;
    }

    public PmWorkItemSelection guid(String... value) {
        addEquals(PmWorkItemColumns.GUID, value);
        return this;
    }

    public PmWorkItemSelection guidNot(String... value) {
        addNotEquals(PmWorkItemColumns.GUID, value);
        return this;
    }

    public PmWorkItemSelection guidLike(String... value) {
        addLike(PmWorkItemColumns.GUID, value);
        return this;
    }

    public PmWorkItemSelection guidContains(String... value) {
        addContains(PmWorkItemColumns.GUID, value);
        return this;
    }

    public PmWorkItemSelection guidStartsWith(String... value) {
        addStartsWith(PmWorkItemColumns.GUID, value);
        return this;
    }

    public PmWorkItemSelection guidEndsWith(String... value) {
        addEndsWith(PmWorkItemColumns.GUID, value);
        return this;
    }

    public PmWorkItemSelection orderByGuid(boolean desc) {
        orderBy(PmWorkItemColumns.GUID, desc);
        return this;
    }

    public PmWorkItemSelection orderByGuid() {
        orderBy(PmWorkItemColumns.GUID, false);
        return this;
    }

    public PmWorkItemSelection itemId(String... value) {
        addEquals(PmWorkItemColumns.ITEM_ID, value);
        return this;
    }

    public PmWorkItemSelection itemIdNot(String... value) {
        addNotEquals(PmWorkItemColumns.ITEM_ID, value);
        return this;
    }

    public PmWorkItemSelection itemIdLike(String... value) {
        addLike(PmWorkItemColumns.ITEM_ID, value);
        return this;
    }

    public PmWorkItemSelection itemIdContains(String... value) {
        addContains(PmWorkItemColumns.ITEM_ID, value);
        return this;
    }

    public PmWorkItemSelection itemIdStartsWith(String... value) {
        addStartsWith(PmWorkItemColumns.ITEM_ID, value);
        return this;
    }

    public PmWorkItemSelection itemIdEndsWith(String... value) {
        addEndsWith(PmWorkItemColumns.ITEM_ID, value);
        return this;
    }

    public PmWorkItemSelection orderByItemId(boolean desc) {
        orderBy(PmWorkItemColumns.ITEM_ID, desc);
        return this;
    }

    public PmWorkItemSelection orderByItemId() {
        orderBy(PmWorkItemColumns.ITEM_ID, false);
        return this;
    }

    public PmWorkItemSelection description(String... value) {
        addEquals(PmWorkItemColumns.DESCRIPTION, value);
        return this;
    }

    public PmWorkItemSelection descriptionNot(String... value) {
        addNotEquals(PmWorkItemColumns.DESCRIPTION, value);
        return this;
    }

    public PmWorkItemSelection descriptionLike(String... value) {
        addLike(PmWorkItemColumns.DESCRIPTION, value);
        return this;
    }

    public PmWorkItemSelection descriptionContains(String... value) {
        addContains(PmWorkItemColumns.DESCRIPTION, value);
        return this;
    }

    public PmWorkItemSelection descriptionStartsWith(String... value) {
        addStartsWith(PmWorkItemColumns.DESCRIPTION, value);
        return this;
    }

    public PmWorkItemSelection descriptionEndsWith(String... value) {
        addEndsWith(PmWorkItemColumns.DESCRIPTION, value);
        return this;
    }

    public PmWorkItemSelection orderByDescription(boolean desc) {
        orderBy(PmWorkItemColumns.DESCRIPTION, desc);
        return this;
    }

    public PmWorkItemSelection orderByDescription() {
        orderBy(PmWorkItemColumns.DESCRIPTION, false);
        return this;
    }

    public PmWorkItemSelection ordinal(Integer... value) {
        addEquals(PmWorkItemColumns.ORDINAL, value);
        return this;
    }

    public PmWorkItemSelection ordinalNot(Integer... value) {
        addNotEquals(PmWorkItemColumns.ORDINAL, value);
        return this;
    }

    public PmWorkItemSelection ordinalGt(int value) {
        addGreaterThan(PmWorkItemColumns.ORDINAL, value);
        return this;
    }

    public PmWorkItemSelection ordinalGtEq(int value) {
        addGreaterThanOrEquals(PmWorkItemColumns.ORDINAL, value);
        return this;
    }

    public PmWorkItemSelection ordinalLt(int value) {
        addLessThan(PmWorkItemColumns.ORDINAL, value);
        return this;
    }

    public PmWorkItemSelection ordinalLtEq(int value) {
        addLessThanOrEquals(PmWorkItemColumns.ORDINAL, value);
        return this;
    }

    public PmWorkItemSelection orderByOrdinal(boolean desc) {
        orderBy(PmWorkItemColumns.ORDINAL, desc);
        return this;
    }

    public PmWorkItemSelection orderByOrdinal() {
        orderBy(PmWorkItemColumns.ORDINAL, false);
        return this;
    }

    public PmWorkItemSelection resultType(String... value) {
        addEquals(PmWorkItemColumns.RESULT_TYPE, value);
        return this;
    }

    public PmWorkItemSelection resultTypeNot(String... value) {
        addNotEquals(PmWorkItemColumns.RESULT_TYPE, value);
        return this;
    }

    public PmWorkItemSelection resultTypeLike(String... value) {
        addLike(PmWorkItemColumns.RESULT_TYPE, value);
        return this;
    }

    public PmWorkItemSelection resultTypeContains(String... value) {
        addContains(PmWorkItemColumns.RESULT_TYPE, value);
        return this;
    }

    public PmWorkItemSelection resultTypeStartsWith(String... value) {
        addStartsWith(PmWorkItemColumns.RESULT_TYPE, value);
        return this;
    }

    public PmWorkItemSelection resultTypeEndsWith(String... value) {
        addEndsWith(PmWorkItemColumns.RESULT_TYPE, value);
        return this;
    }

    public PmWorkItemSelection orderByResultType(boolean desc) {
        orderBy(PmWorkItemColumns.RESULT_TYPE, desc);
        return this;
    }

    public PmWorkItemSelection orderByResultType() {
        orderBy(PmWorkItemColumns.RESULT_TYPE, false);
        return this;
    }

    public PmWorkItemSelection resultMinValue(Integer... value) {
        addEquals(PmWorkItemColumns.RESULT_MIN_VALUE, value);
        return this;
    }

    public PmWorkItemSelection resultMinValueNot(Integer... value) {
        addNotEquals(PmWorkItemColumns.RESULT_MIN_VALUE, value);
        return this;
    }

    public PmWorkItemSelection resultMinValueGt(int value) {
        addGreaterThan(PmWorkItemColumns.RESULT_MIN_VALUE, value);
        return this;
    }

    public PmWorkItemSelection resultMinValueGtEq(int value) {
        addGreaterThanOrEquals(PmWorkItemColumns.RESULT_MIN_VALUE, value);
        return this;
    }

    public PmWorkItemSelection resultMinValueLt(int value) {
        addLessThan(PmWorkItemColumns.RESULT_MIN_VALUE, value);
        return this;
    }

    public PmWorkItemSelection resultMinValueLtEq(int value) {
        addLessThanOrEquals(PmWorkItemColumns.RESULT_MIN_VALUE, value);
        return this;
    }

    public PmWorkItemSelection orderByResultMinValue(boolean desc) {
        orderBy(PmWorkItemColumns.RESULT_MIN_VALUE, desc);
        return this;
    }

    public PmWorkItemSelection orderByResultMinValue() {
        orderBy(PmWorkItemColumns.RESULT_MIN_VALUE, false);
        return this;
    }

    public PmWorkItemSelection resultMaxValue(Integer... value) {
        addEquals(PmWorkItemColumns.RESULT_MAX_VALUE, value);
        return this;
    }

    public PmWorkItemSelection resultMaxValueNot(Integer... value) {
        addNotEquals(PmWorkItemColumns.RESULT_MAX_VALUE, value);
        return this;
    }

    public PmWorkItemSelection resultMaxValueGt(int value) {
        addGreaterThan(PmWorkItemColumns.RESULT_MAX_VALUE, value);
        return this;
    }

    public PmWorkItemSelection resultMaxValueGtEq(int value) {
        addGreaterThanOrEquals(PmWorkItemColumns.RESULT_MAX_VALUE, value);
        return this;
    }

    public PmWorkItemSelection resultMaxValueLt(int value) {
        addLessThan(PmWorkItemColumns.RESULT_MAX_VALUE, value);
        return this;
    }

    public PmWorkItemSelection resultMaxValueLtEq(int value) {
        addLessThanOrEquals(PmWorkItemColumns.RESULT_MAX_VALUE, value);
        return this;
    }

    public PmWorkItemSelection orderByResultMaxValue(boolean desc) {
        orderBy(PmWorkItemColumns.RESULT_MAX_VALUE, desc);
        return this;
    }

    public PmWorkItemSelection orderByResultMaxValue() {
        orderBy(PmWorkItemColumns.RESULT_MAX_VALUE, false);
        return this;
    }

    public PmWorkItemSelection resultDefaultValue(String... value) {
        addEquals(PmWorkItemColumns.RESULT_DEFAULT_VALUE, value);
        return this;
    }

    public PmWorkItemSelection resultDefaultValueNot(String... value) {
        addNotEquals(PmWorkItemColumns.RESULT_DEFAULT_VALUE, value);
        return this;
    }

    public PmWorkItemSelection resultDefaultValueLike(String... value) {
        addLike(PmWorkItemColumns.RESULT_DEFAULT_VALUE, value);
        return this;
    }

    public PmWorkItemSelection resultDefaultValueContains(String... value) {
        addContains(PmWorkItemColumns.RESULT_DEFAULT_VALUE, value);
        return this;
    }

    public PmWorkItemSelection resultDefaultValueStartsWith(String... value) {
        addStartsWith(PmWorkItemColumns.RESULT_DEFAULT_VALUE, value);
        return this;
    }

    public PmWorkItemSelection resultDefaultValueEndsWith(String... value) {
        addEndsWith(PmWorkItemColumns.RESULT_DEFAULT_VALUE, value);
        return this;
    }

    public PmWorkItemSelection orderByResultDefaultValue(boolean desc) {
        orderBy(PmWorkItemColumns.RESULT_DEFAULT_VALUE, desc);
        return this;
    }

    public PmWorkItemSelection orderByResultDefaultValue() {
        orderBy(PmWorkItemColumns.RESULT_DEFAULT_VALUE, false);
        return this;
    }

    public PmWorkItemSelection resultValueUnit(String... value) {
        addEquals(PmWorkItemColumns.RESULT_VALUE_UNIT, value);
        return this;
    }

    public PmWorkItemSelection resultValueUnitNot(String... value) {
        addNotEquals(PmWorkItemColumns.RESULT_VALUE_UNIT, value);
        return this;
    }

    public PmWorkItemSelection resultValueUnitLike(String... value) {
        addLike(PmWorkItemColumns.RESULT_VALUE_UNIT, value);
        return this;
    }

    public PmWorkItemSelection resultValueUnitContains(String... value) {
        addContains(PmWorkItemColumns.RESULT_VALUE_UNIT, value);
        return this;
    }

    public PmWorkItemSelection resultValueUnitStartsWith(String... value) {
        addStartsWith(PmWorkItemColumns.RESULT_VALUE_UNIT, value);
        return this;
    }

    public PmWorkItemSelection resultValueUnitEndsWith(String... value) {
        addEndsWith(PmWorkItemColumns.RESULT_VALUE_UNIT, value);
        return this;
    }

    public PmWorkItemSelection orderByResultValueUnit(boolean desc) {
        orderBy(PmWorkItemColumns.RESULT_VALUE_UNIT, desc);
        return this;
    }

    public PmWorkItemSelection orderByResultValueUnit() {
        orderBy(PmWorkItemColumns.RESULT_VALUE_UNIT, false);
        return this;
    }

    public PmWorkItemSelection resultEnumOrdinal(Integer... value) {
        addEquals(PmWorkItemColumns.RESULT_ENUM_ORDINAL, value);
        return this;
    }

    public PmWorkItemSelection resultEnumOrdinalNot(Integer... value) {
        addNotEquals(PmWorkItemColumns.RESULT_ENUM_ORDINAL, value);
        return this;
    }

    public PmWorkItemSelection resultEnumOrdinalGt(int value) {
        addGreaterThan(PmWorkItemColumns.RESULT_ENUM_ORDINAL, value);
        return this;
    }

    public PmWorkItemSelection resultEnumOrdinalGtEq(int value) {
        addGreaterThanOrEquals(PmWorkItemColumns.RESULT_ENUM_ORDINAL, value);
        return this;
    }

    public PmWorkItemSelection resultEnumOrdinalLt(int value) {
        addLessThan(PmWorkItemColumns.RESULT_ENUM_ORDINAL, value);
        return this;
    }

    public PmWorkItemSelection resultEnumOrdinalLtEq(int value) {
        addLessThanOrEquals(PmWorkItemColumns.RESULT_ENUM_ORDINAL, value);
        return this;
    }

    public PmWorkItemSelection orderByResultEnumOrdinal(boolean desc) {
        orderBy(PmWorkItemColumns.RESULT_ENUM_ORDINAL, desc);
        return this;
    }

    public PmWorkItemSelection orderByResultEnumOrdinal() {
        orderBy(PmWorkItemColumns.RESULT_ENUM_ORDINAL, false);
        return this;
    }

    public PmWorkItemSelection resultValue(String... value) {
        addEquals(PmWorkItemColumns.RESULT_VALUE, value);
        return this;
    }

    public PmWorkItemSelection resultValueNot(String... value) {
        addNotEquals(PmWorkItemColumns.RESULT_VALUE, value);
        return this;
    }

    public PmWorkItemSelection resultValueLike(String... value) {
        addLike(PmWorkItemColumns.RESULT_VALUE, value);
        return this;
    }

    public PmWorkItemSelection resultValueContains(String... value) {
        addContains(PmWorkItemColumns.RESULT_VALUE, value);
        return this;
    }

    public PmWorkItemSelection resultValueStartsWith(String... value) {
        addStartsWith(PmWorkItemColumns.RESULT_VALUE, value);
        return this;
    }

    public PmWorkItemSelection resultValueEndsWith(String... value) {
        addEndsWith(PmWorkItemColumns.RESULT_VALUE, value);
        return this;
    }

    public PmWorkItemSelection orderByResultValue(boolean desc) {
        orderBy(PmWorkItemColumns.RESULT_VALUE, desc);
        return this;
    }

    public PmWorkItemSelection orderByResultValue() {
        orderBy(PmWorkItemColumns.RESULT_VALUE, false);
        return this;
    }

    public PmWorkItemSelection lastModified(Long... value) {
        addEquals(PmWorkItemColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmWorkItemSelection lastModifiedNot(Long... value) {
        addNotEquals(PmWorkItemColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmWorkItemSelection lastModifiedGt(long value) {
        addGreaterThan(PmWorkItemColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmWorkItemSelection lastModifiedGtEq(long value) {
        addGreaterThanOrEquals(PmWorkItemColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmWorkItemSelection lastModifiedLt(long value) {
        addLessThan(PmWorkItemColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmWorkItemSelection lastModifiedLtEq(long value) {
        addLessThanOrEquals(PmWorkItemColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmWorkItemSelection orderByLastModified(boolean desc) {
        orderBy(PmWorkItemColumns.LAST_MODIFIED, desc);
        return this;
    }

    public PmWorkItemSelection orderByLastModified() {
        orderBy(PmWorkItemColumns.LAST_MODIFIED, false);
        return this;
    }

    public PmWorkItemSelection required(Boolean value) {
        addEquals(PmWorkItemColumns.REQUIRED, toObjectArray(value));
        return this;
    }

    public PmWorkItemSelection orderByRequired(boolean desc) {
        orderBy(PmWorkItemColumns.REQUIRED, desc);
        return this;
    }

    public PmWorkItemSelection orderByRequired() {
        orderBy(PmWorkItemColumns.REQUIRED, false);
        return this;
    }
}
