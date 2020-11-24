package cc.xingyan.android.afc.provider.pmifsworkitem;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code pmifs_work_item} table.
 */
public class PmifsWorkItemSelection extends AbstractSelection<PmifsWorkItemSelection> {
    @Override
    protected Uri baseUri() {
        return PmifsWorkItemColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PmifsWorkItemCursor} object, which is positioned before the first entry, or null.
     */
    public PmifsWorkItemCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PmifsWorkItemCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public PmifsWorkItemCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PmifsWorkItemCursor} object, which is positioned before the first entry, or null.
     */
    public PmifsWorkItemCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PmifsWorkItemCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public PmifsWorkItemCursor query(Context context) {
        return query(context, null);
    }


    public PmifsWorkItemSelection id(long... value) {
        addEquals("pmifs_work_item." + PmifsWorkItemColumns._ID, toObjectArray(value));
        return this;
    }

    public PmifsWorkItemSelection idNot(long... value) {
        addNotEquals("pmifs_work_item." + PmifsWorkItemColumns._ID, toObjectArray(value));
        return this;
    }

    public PmifsWorkItemSelection orderById(boolean desc) {
        orderBy("pmifs_work_item." + PmifsWorkItemColumns._ID, desc);
        return this;
    }

    public PmifsWorkItemSelection orderById() {
        return orderById(false);
    }

    public PmifsWorkItemSelection orderId(String... value) {
        addEquals(PmifsWorkItemColumns.ORDER_ID, value);
        return this;
    }

    public PmifsWorkItemSelection orderIdNot(String... value) {
        addNotEquals(PmifsWorkItemColumns.ORDER_ID, value);
        return this;
    }

    public PmifsWorkItemSelection orderIdLike(String... value) {
        addLike(PmifsWorkItemColumns.ORDER_ID, value);
        return this;
    }

    public PmifsWorkItemSelection orderIdContains(String... value) {
        addContains(PmifsWorkItemColumns.ORDER_ID, value);
        return this;
    }

    public PmifsWorkItemSelection orderIdStartsWith(String... value) {
        addStartsWith(PmifsWorkItemColumns.ORDER_ID, value);
        return this;
    }

    public PmifsWorkItemSelection orderIdEndsWith(String... value) {
        addEndsWith(PmifsWorkItemColumns.ORDER_ID, value);
        return this;
    }

    public PmifsWorkItemSelection orderByOrderId(boolean desc) {
        orderBy(PmifsWorkItemColumns.ORDER_ID, desc);
        return this;
    }

    public PmifsWorkItemSelection orderByOrderId() {
        orderBy(PmifsWorkItemColumns.ORDER_ID, false);
        return this;
    }

    public PmifsWorkItemSelection workGuid(String... value) {
        addEquals(PmifsWorkItemColumns.WORK_GUID, value);
        return this;
    }

    public PmifsWorkItemSelection workGuidNot(String... value) {
        addNotEquals(PmifsWorkItemColumns.WORK_GUID, value);
        return this;
    }

    public PmifsWorkItemSelection workGuidLike(String... value) {
        addLike(PmifsWorkItemColumns.WORK_GUID, value);
        return this;
    }

    public PmifsWorkItemSelection workGuidContains(String... value) {
        addContains(PmifsWorkItemColumns.WORK_GUID, value);
        return this;
    }

    public PmifsWorkItemSelection workGuidStartsWith(String... value) {
        addStartsWith(PmifsWorkItemColumns.WORK_GUID, value);
        return this;
    }

    public PmifsWorkItemSelection workGuidEndsWith(String... value) {
        addEndsWith(PmifsWorkItemColumns.WORK_GUID, value);
        return this;
    }

    public PmifsWorkItemSelection orderByWorkGuid(boolean desc) {
        orderBy(PmifsWorkItemColumns.WORK_GUID, desc);
        return this;
    }

    public PmifsWorkItemSelection orderByWorkGuid() {
        orderBy(PmifsWorkItemColumns.WORK_GUID, false);
        return this;
    }

    public PmifsWorkItemSelection packageId(String... value) {
        addEquals(PmifsWorkItemColumns.PACKAGE_ID, value);
        return this;
    }

    public PmifsWorkItemSelection packageIdNot(String... value) {
        addNotEquals(PmifsWorkItemColumns.PACKAGE_ID, value);
        return this;
    }

    public PmifsWorkItemSelection packageIdLike(String... value) {
        addLike(PmifsWorkItemColumns.PACKAGE_ID, value);
        return this;
    }

    public PmifsWorkItemSelection packageIdContains(String... value) {
        addContains(PmifsWorkItemColumns.PACKAGE_ID, value);
        return this;
    }

    public PmifsWorkItemSelection packageIdStartsWith(String... value) {
        addStartsWith(PmifsWorkItemColumns.PACKAGE_ID, value);
        return this;
    }

    public PmifsWorkItemSelection packageIdEndsWith(String... value) {
        addEndsWith(PmifsWorkItemColumns.PACKAGE_ID, value);
        return this;
    }

    public PmifsWorkItemSelection orderByPackageId(boolean desc) {
        orderBy(PmifsWorkItemColumns.PACKAGE_ID, desc);
        return this;
    }

    public PmifsWorkItemSelection orderByPackageId() {
        orderBy(PmifsWorkItemColumns.PACKAGE_ID, false);
        return this;
    }

    public PmifsWorkItemSelection packageDes(String... value) {
        addEquals(PmifsWorkItemColumns.PACKAGE_DES, value);
        return this;
    }

    public PmifsWorkItemSelection packageDesNot(String... value) {
        addNotEquals(PmifsWorkItemColumns.PACKAGE_DES, value);
        return this;
    }

    public PmifsWorkItemSelection packageDesLike(String... value) {
        addLike(PmifsWorkItemColumns.PACKAGE_DES, value);
        return this;
    }

    public PmifsWorkItemSelection packageDesContains(String... value) {
        addContains(PmifsWorkItemColumns.PACKAGE_DES, value);
        return this;
    }

    public PmifsWorkItemSelection packageDesStartsWith(String... value) {
        addStartsWith(PmifsWorkItemColumns.PACKAGE_DES, value);
        return this;
    }

    public PmifsWorkItemSelection packageDesEndsWith(String... value) {
        addEndsWith(PmifsWorkItemColumns.PACKAGE_DES, value);
        return this;
    }

    public PmifsWorkItemSelection orderByPackageDes(boolean desc) {
        orderBy(PmifsWorkItemColumns.PACKAGE_DES, desc);
        return this;
    }

    public PmifsWorkItemSelection orderByPackageDes() {
        orderBy(PmifsWorkItemColumns.PACKAGE_DES, false);
        return this;
    }

    public PmifsWorkItemSelection guid(String... value) {
        addEquals(PmifsWorkItemColumns.GUID, value);
        return this;
    }

    public PmifsWorkItemSelection guidNot(String... value) {
        addNotEquals(PmifsWorkItemColumns.GUID, value);
        return this;
    }

    public PmifsWorkItemSelection guidLike(String... value) {
        addLike(PmifsWorkItemColumns.GUID, value);
        return this;
    }

    public PmifsWorkItemSelection guidContains(String... value) {
        addContains(PmifsWorkItemColumns.GUID, value);
        return this;
    }

    public PmifsWorkItemSelection guidStartsWith(String... value) {
        addStartsWith(PmifsWorkItemColumns.GUID, value);
        return this;
    }

    public PmifsWorkItemSelection guidEndsWith(String... value) {
        addEndsWith(PmifsWorkItemColumns.GUID, value);
        return this;
    }

    public PmifsWorkItemSelection orderByGuid(boolean desc) {
        orderBy(PmifsWorkItemColumns.GUID, desc);
        return this;
    }

    public PmifsWorkItemSelection orderByGuid() {
        orderBy(PmifsWorkItemColumns.GUID, false);
        return this;
    }

    public PmifsWorkItemSelection itemId(String... value) {
        addEquals(PmifsWorkItemColumns.ITEM_ID, value);
        return this;
    }

    public PmifsWorkItemSelection itemIdNot(String... value) {
        addNotEquals(PmifsWorkItemColumns.ITEM_ID, value);
        return this;
    }

    public PmifsWorkItemSelection itemIdLike(String... value) {
        addLike(PmifsWorkItemColumns.ITEM_ID, value);
        return this;
    }

    public PmifsWorkItemSelection itemIdContains(String... value) {
        addContains(PmifsWorkItemColumns.ITEM_ID, value);
        return this;
    }

    public PmifsWorkItemSelection itemIdStartsWith(String... value) {
        addStartsWith(PmifsWorkItemColumns.ITEM_ID, value);
        return this;
    }

    public PmifsWorkItemSelection itemIdEndsWith(String... value) {
        addEndsWith(PmifsWorkItemColumns.ITEM_ID, value);
        return this;
    }

    public PmifsWorkItemSelection orderByItemId(boolean desc) {
        orderBy(PmifsWorkItemColumns.ITEM_ID, desc);
        return this;
    }

    public PmifsWorkItemSelection orderByItemId() {
        orderBy(PmifsWorkItemColumns.ITEM_ID, false);
        return this;
    }

    public PmifsWorkItemSelection itemDes(String... value) {
        addEquals(PmifsWorkItemColumns.ITEM_DES, value);
        return this;
    }

    public PmifsWorkItemSelection itemDesNot(String... value) {
        addNotEquals(PmifsWorkItemColumns.ITEM_DES, value);
        return this;
    }

    public PmifsWorkItemSelection itemDesLike(String... value) {
        addLike(PmifsWorkItemColumns.ITEM_DES, value);
        return this;
    }

    public PmifsWorkItemSelection itemDesContains(String... value) {
        addContains(PmifsWorkItemColumns.ITEM_DES, value);
        return this;
    }

    public PmifsWorkItemSelection itemDesStartsWith(String... value) {
        addStartsWith(PmifsWorkItemColumns.ITEM_DES, value);
        return this;
    }

    public PmifsWorkItemSelection itemDesEndsWith(String... value) {
        addEndsWith(PmifsWorkItemColumns.ITEM_DES, value);
        return this;
    }

    public PmifsWorkItemSelection orderByItemDes(boolean desc) {
        orderBy(PmifsWorkItemColumns.ITEM_DES, desc);
        return this;
    }

    public PmifsWorkItemSelection orderByItemDes() {
        orderBy(PmifsWorkItemColumns.ITEM_DES, false);
        return this;
    }

    public PmifsWorkItemSelection workSn(Integer... value) {
        addEquals(PmifsWorkItemColumns.WORK_SN, value);
        return this;
    }

    public PmifsWorkItemSelection workSnNot(Integer... value) {
        addNotEquals(PmifsWorkItemColumns.WORK_SN, value);
        return this;
    }

    public PmifsWorkItemSelection workSnGt(int value) {
        addGreaterThan(PmifsWorkItemColumns.WORK_SN, value);
        return this;
    }

    public PmifsWorkItemSelection workSnGtEq(int value) {
        addGreaterThanOrEquals(PmifsWorkItemColumns.WORK_SN, value);
        return this;
    }

    public PmifsWorkItemSelection workSnLt(int value) {
        addLessThan(PmifsWorkItemColumns.WORK_SN, value);
        return this;
    }

    public PmifsWorkItemSelection workSnLtEq(int value) {
        addLessThanOrEquals(PmifsWorkItemColumns.WORK_SN, value);
        return this;
    }

    public PmifsWorkItemSelection orderByWorkSn(boolean desc) {
        orderBy(PmifsWorkItemColumns.WORK_SN, desc);
        return this;
    }

    public PmifsWorkItemSelection orderByWorkSn() {
        orderBy(PmifsWorkItemColumns.WORK_SN, false);
        return this;
    }

    public PmifsWorkItemSelection resultType(String... value) {
        addEquals(PmifsWorkItemColumns.RESULT_TYPE, value);
        return this;
    }

    public PmifsWorkItemSelection resultTypeNot(String... value) {
        addNotEquals(PmifsWorkItemColumns.RESULT_TYPE, value);
        return this;
    }

    public PmifsWorkItemSelection resultTypeLike(String... value) {
        addLike(PmifsWorkItemColumns.RESULT_TYPE, value);
        return this;
    }

    public PmifsWorkItemSelection resultTypeContains(String... value) {
        addContains(PmifsWorkItemColumns.RESULT_TYPE, value);
        return this;
    }

    public PmifsWorkItemSelection resultTypeStartsWith(String... value) {
        addStartsWith(PmifsWorkItemColumns.RESULT_TYPE, value);
        return this;
    }

    public PmifsWorkItemSelection resultTypeEndsWith(String... value) {
        addEndsWith(PmifsWorkItemColumns.RESULT_TYPE, value);
        return this;
    }

    public PmifsWorkItemSelection orderByResultType(boolean desc) {
        orderBy(PmifsWorkItemColumns.RESULT_TYPE, desc);
        return this;
    }

    public PmifsWorkItemSelection orderByResultType() {
        orderBy(PmifsWorkItemColumns.RESULT_TYPE, false);
        return this;
    }

    public PmifsWorkItemSelection resultMinValue(Integer... value) {
        addEquals(PmifsWorkItemColumns.RESULT_MIN_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection resultMinValueNot(Integer... value) {
        addNotEquals(PmifsWorkItemColumns.RESULT_MIN_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection resultMinValueGt(int value) {
        addGreaterThan(PmifsWorkItemColumns.RESULT_MIN_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection resultMinValueGtEq(int value) {
        addGreaterThanOrEquals(PmifsWorkItemColumns.RESULT_MIN_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection resultMinValueLt(int value) {
        addLessThan(PmifsWorkItemColumns.RESULT_MIN_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection resultMinValueLtEq(int value) {
        addLessThanOrEquals(PmifsWorkItemColumns.RESULT_MIN_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection orderByResultMinValue(boolean desc) {
        orderBy(PmifsWorkItemColumns.RESULT_MIN_VALUE, desc);
        return this;
    }

    public PmifsWorkItemSelection orderByResultMinValue() {
        orderBy(PmifsWorkItemColumns.RESULT_MIN_VALUE, false);
        return this;
    }

    public PmifsWorkItemSelection resultMaxValue(Integer... value) {
        addEquals(PmifsWorkItemColumns.RESULT_MAX_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection resultMaxValueNot(Integer... value) {
        addNotEquals(PmifsWorkItemColumns.RESULT_MAX_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection resultMaxValueGt(int value) {
        addGreaterThan(PmifsWorkItemColumns.RESULT_MAX_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection resultMaxValueGtEq(int value) {
        addGreaterThanOrEquals(PmifsWorkItemColumns.RESULT_MAX_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection resultMaxValueLt(int value) {
        addLessThan(PmifsWorkItemColumns.RESULT_MAX_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection resultMaxValueLtEq(int value) {
        addLessThanOrEquals(PmifsWorkItemColumns.RESULT_MAX_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection orderByResultMaxValue(boolean desc) {
        orderBy(PmifsWorkItemColumns.RESULT_MAX_VALUE, desc);
        return this;
    }

    public PmifsWorkItemSelection orderByResultMaxValue() {
        orderBy(PmifsWorkItemColumns.RESULT_MAX_VALUE, false);
        return this;
    }

    public PmifsWorkItemSelection resultDefaultValue(String... value) {
        addEquals(PmifsWorkItemColumns.RESULT_DEFAULT_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection resultDefaultValueNot(String... value) {
        addNotEquals(PmifsWorkItemColumns.RESULT_DEFAULT_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection resultDefaultValueLike(String... value) {
        addLike(PmifsWorkItemColumns.RESULT_DEFAULT_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection resultDefaultValueContains(String... value) {
        addContains(PmifsWorkItemColumns.RESULT_DEFAULT_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection resultDefaultValueStartsWith(String... value) {
        addStartsWith(PmifsWorkItemColumns.RESULT_DEFAULT_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection resultDefaultValueEndsWith(String... value) {
        addEndsWith(PmifsWorkItemColumns.RESULT_DEFAULT_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection orderByResultDefaultValue(boolean desc) {
        orderBy(PmifsWorkItemColumns.RESULT_DEFAULT_VALUE, desc);
        return this;
    }

    public PmifsWorkItemSelection orderByResultDefaultValue() {
        orderBy(PmifsWorkItemColumns.RESULT_DEFAULT_VALUE, false);
        return this;
    }

    public PmifsWorkItemSelection resultValueUnit(String... value) {
        addEquals(PmifsWorkItemColumns.RESULT_VALUE_UNIT, value);
        return this;
    }

    public PmifsWorkItemSelection resultValueUnitNot(String... value) {
        addNotEquals(PmifsWorkItemColumns.RESULT_VALUE_UNIT, value);
        return this;
    }

    public PmifsWorkItemSelection resultValueUnitLike(String... value) {
        addLike(PmifsWorkItemColumns.RESULT_VALUE_UNIT, value);
        return this;
    }

    public PmifsWorkItemSelection resultValueUnitContains(String... value) {
        addContains(PmifsWorkItemColumns.RESULT_VALUE_UNIT, value);
        return this;
    }

    public PmifsWorkItemSelection resultValueUnitStartsWith(String... value) {
        addStartsWith(PmifsWorkItemColumns.RESULT_VALUE_UNIT, value);
        return this;
    }

    public PmifsWorkItemSelection resultValueUnitEndsWith(String... value) {
        addEndsWith(PmifsWorkItemColumns.RESULT_VALUE_UNIT, value);
        return this;
    }

    public PmifsWorkItemSelection orderByResultValueUnit(boolean desc) {
        orderBy(PmifsWorkItemColumns.RESULT_VALUE_UNIT, desc);
        return this;
    }

    public PmifsWorkItemSelection orderByResultValueUnit() {
        orderBy(PmifsWorkItemColumns.RESULT_VALUE_UNIT, false);
        return this;
    }

    public PmifsWorkItemSelection resultEnumOrdinal(Integer... value) {
        addEquals(PmifsWorkItemColumns.RESULT_ENUM_ORDINAL, value);
        return this;
    }

    public PmifsWorkItemSelection resultEnumOrdinalNot(Integer... value) {
        addNotEquals(PmifsWorkItemColumns.RESULT_ENUM_ORDINAL, value);
        return this;
    }

    public PmifsWorkItemSelection resultEnumOrdinalGt(int value) {
        addGreaterThan(PmifsWorkItemColumns.RESULT_ENUM_ORDINAL, value);
        return this;
    }

    public PmifsWorkItemSelection resultEnumOrdinalGtEq(int value) {
        addGreaterThanOrEquals(PmifsWorkItemColumns.RESULT_ENUM_ORDINAL, value);
        return this;
    }

    public PmifsWorkItemSelection resultEnumOrdinalLt(int value) {
        addLessThan(PmifsWorkItemColumns.RESULT_ENUM_ORDINAL, value);
        return this;
    }

    public PmifsWorkItemSelection resultEnumOrdinalLtEq(int value) {
        addLessThanOrEquals(PmifsWorkItemColumns.RESULT_ENUM_ORDINAL, value);
        return this;
    }

    public PmifsWorkItemSelection orderByResultEnumOrdinal(boolean desc) {
        orderBy(PmifsWorkItemColumns.RESULT_ENUM_ORDINAL, desc);
        return this;
    }

    public PmifsWorkItemSelection orderByResultEnumOrdinal() {
        orderBy(PmifsWorkItemColumns.RESULT_ENUM_ORDINAL, false);
        return this;
    }

    public PmifsWorkItemSelection resultValue(String... value) {
        addEquals(PmifsWorkItemColumns.RESULT_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection resultValueNot(String... value) {
        addNotEquals(PmifsWorkItemColumns.RESULT_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection resultValueLike(String... value) {
        addLike(PmifsWorkItemColumns.RESULT_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection resultValueContains(String... value) {
        addContains(PmifsWorkItemColumns.RESULT_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection resultValueStartsWith(String... value) {
        addStartsWith(PmifsWorkItemColumns.RESULT_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection resultValueEndsWith(String... value) {
        addEndsWith(PmifsWorkItemColumns.RESULT_VALUE, value);
        return this;
    }

    public PmifsWorkItemSelection orderByResultValue(boolean desc) {
        orderBy(PmifsWorkItemColumns.RESULT_VALUE, desc);
        return this;
    }

    public PmifsWorkItemSelection orderByResultValue() {
        orderBy(PmifsWorkItemColumns.RESULT_VALUE, false);
        return this;
    }

    public PmifsWorkItemSelection lastModified(Long... value) {
        addEquals(PmifsWorkItemColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmifsWorkItemSelection lastModifiedNot(Long... value) {
        addNotEquals(PmifsWorkItemColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmifsWorkItemSelection lastModifiedGt(long value) {
        addGreaterThan(PmifsWorkItemColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmifsWorkItemSelection lastModifiedGtEq(long value) {
        addGreaterThanOrEquals(PmifsWorkItemColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmifsWorkItemSelection lastModifiedLt(long value) {
        addLessThan(PmifsWorkItemColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmifsWorkItemSelection lastModifiedLtEq(long value) {
        addLessThanOrEquals(PmifsWorkItemColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmifsWorkItemSelection orderByLastModified(boolean desc) {
        orderBy(PmifsWorkItemColumns.LAST_MODIFIED, desc);
        return this;
    }

    public PmifsWorkItemSelection orderByLastModified() {
        orderBy(PmifsWorkItemColumns.LAST_MODIFIED, false);
        return this;
    }

    public PmifsWorkItemSelection required(Boolean value) {
        addEquals(PmifsWorkItemColumns.REQUIRED, toObjectArray(value));
        return this;
    }

    public PmifsWorkItemSelection orderByRequired(boolean desc) {
        orderBy(PmifsWorkItemColumns.REQUIRED, desc);
        return this;
    }

    public PmifsWorkItemSelection orderByRequired() {
        orderBy(PmifsWorkItemColumns.REQUIRED, false);
        return this;
    }
}
