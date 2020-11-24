package cc.xingyan.android.afc.provider.yunshukuwei;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code yunshu_kuwei} table.
 */
public class YunshuKuweiSelection extends AbstractSelection<YunshuKuweiSelection> {
    @Override
    protected Uri baseUri() {
        return YunshuKuweiColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code YunshuKuweiCursor} object, which is positioned before the first entry, or null.
     */
    public YunshuKuweiCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new YunshuKuweiCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public YunshuKuweiCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code YunshuKuweiCursor} object, which is positioned before the first entry, or null.
     */
    public YunshuKuweiCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new YunshuKuweiCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public YunshuKuweiCursor query(Context context) {
        return query(context, null);
    }


    public YunshuKuweiSelection id(long... value) {
        addEquals("yunshu_kuwei." + YunshuKuweiColumns._ID, toObjectArray(value));
        return this;
    }

    public YunshuKuweiSelection idNot(long... value) {
        addNotEquals("yunshu_kuwei." + YunshuKuweiColumns._ID, toObjectArray(value));
        return this;
    }

    public YunshuKuweiSelection orderById(boolean desc) {
        orderBy("yunshu_kuwei." + YunshuKuweiColumns._ID, desc);
        return this;
    }

    public YunshuKuweiSelection orderById() {
        return orderById(false);
    }

    public YunshuKuweiSelection transportTaskId(String... value) {
        addEquals(YunshuKuweiColumns.TRANSPORT_TASK_ID, value);
        return this;
    }

    public YunshuKuweiSelection transportTaskIdNot(String... value) {
        addNotEquals(YunshuKuweiColumns.TRANSPORT_TASK_ID, value);
        return this;
    }

    public YunshuKuweiSelection transportTaskIdLike(String... value) {
        addLike(YunshuKuweiColumns.TRANSPORT_TASK_ID, value);
        return this;
    }

    public YunshuKuweiSelection transportTaskIdContains(String... value) {
        addContains(YunshuKuweiColumns.TRANSPORT_TASK_ID, value);
        return this;
    }

    public YunshuKuweiSelection transportTaskIdStartsWith(String... value) {
        addStartsWith(YunshuKuweiColumns.TRANSPORT_TASK_ID, value);
        return this;
    }

    public YunshuKuweiSelection transportTaskIdEndsWith(String... value) {
        addEndsWith(YunshuKuweiColumns.TRANSPORT_TASK_ID, value);
        return this;
    }

    public YunshuKuweiSelection orderByTransportTaskId(boolean desc) {
        orderBy(YunshuKuweiColumns.TRANSPORT_TASK_ID, desc);
        return this;
    }

    public YunshuKuweiSelection orderByTransportTaskId() {
        orderBy(YunshuKuweiColumns.TRANSPORT_TASK_ID, false);
        return this;
    }

    public YunshuKuweiSelection warehouseNo(String... value) {
        addEquals(YunshuKuweiColumns.WAREHOUSE_NO, value);
        return this;
    }

    public YunshuKuweiSelection warehouseNoNot(String... value) {
        addNotEquals(YunshuKuweiColumns.WAREHOUSE_NO, value);
        return this;
    }

    public YunshuKuweiSelection warehouseNoLike(String... value) {
        addLike(YunshuKuweiColumns.WAREHOUSE_NO, value);
        return this;
    }

    public YunshuKuweiSelection warehouseNoContains(String... value) {
        addContains(YunshuKuweiColumns.WAREHOUSE_NO, value);
        return this;
    }

    public YunshuKuweiSelection warehouseNoStartsWith(String... value) {
        addStartsWith(YunshuKuweiColumns.WAREHOUSE_NO, value);
        return this;
    }

    public YunshuKuweiSelection warehouseNoEndsWith(String... value) {
        addEndsWith(YunshuKuweiColumns.WAREHOUSE_NO, value);
        return this;
    }

    public YunshuKuweiSelection orderByWarehouseNo(boolean desc) {
        orderBy(YunshuKuweiColumns.WAREHOUSE_NO, desc);
        return this;
    }

    public YunshuKuweiSelection orderByWarehouseNo() {
        orderBy(YunshuKuweiColumns.WAREHOUSE_NO, false);
        return this;
    }

    public YunshuKuweiSelection warehouseName(String... value) {
        addEquals(YunshuKuweiColumns.WAREHOUSE_NAME, value);
        return this;
    }

    public YunshuKuweiSelection warehouseNameNot(String... value) {
        addNotEquals(YunshuKuweiColumns.WAREHOUSE_NAME, value);
        return this;
    }

    public YunshuKuweiSelection warehouseNameLike(String... value) {
        addLike(YunshuKuweiColumns.WAREHOUSE_NAME, value);
        return this;
    }

    public YunshuKuweiSelection warehouseNameContains(String... value) {
        addContains(YunshuKuweiColumns.WAREHOUSE_NAME, value);
        return this;
    }

    public YunshuKuweiSelection warehouseNameStartsWith(String... value) {
        addStartsWith(YunshuKuweiColumns.WAREHOUSE_NAME, value);
        return this;
    }

    public YunshuKuweiSelection warehouseNameEndsWith(String... value) {
        addEndsWith(YunshuKuweiColumns.WAREHOUSE_NAME, value);
        return this;
    }

    public YunshuKuweiSelection orderByWarehouseName(boolean desc) {
        orderBy(YunshuKuweiColumns.WAREHOUSE_NAME, desc);
        return this;
    }

    public YunshuKuweiSelection orderByWarehouseName() {
        orderBy(YunshuKuweiColumns.WAREHOUSE_NAME, false);
        return this;
    }

    public YunshuKuweiSelection warehouseType(String... value) {
        addEquals(YunshuKuweiColumns.WAREHOUSE_TYPE, value);
        return this;
    }

    public YunshuKuweiSelection warehouseTypeNot(String... value) {
        addNotEquals(YunshuKuweiColumns.WAREHOUSE_TYPE, value);
        return this;
    }

    public YunshuKuweiSelection warehouseTypeLike(String... value) {
        addLike(YunshuKuweiColumns.WAREHOUSE_TYPE, value);
        return this;
    }

    public YunshuKuweiSelection warehouseTypeContains(String... value) {
        addContains(YunshuKuweiColumns.WAREHOUSE_TYPE, value);
        return this;
    }

    public YunshuKuweiSelection warehouseTypeStartsWith(String... value) {
        addStartsWith(YunshuKuweiColumns.WAREHOUSE_TYPE, value);
        return this;
    }

    public YunshuKuweiSelection warehouseTypeEndsWith(String... value) {
        addEndsWith(YunshuKuweiColumns.WAREHOUSE_TYPE, value);
        return this;
    }

    public YunshuKuweiSelection orderByWarehouseType(boolean desc) {
        orderBy(YunshuKuweiColumns.WAREHOUSE_TYPE, desc);
        return this;
    }

    public YunshuKuweiSelection orderByWarehouseType() {
        orderBy(YunshuKuweiColumns.WAREHOUSE_TYPE, false);
        return this;
    }
}
