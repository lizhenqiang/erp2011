package cc.xingyan.android.afc.provider.yunshuline;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code yunshu_line} table.
 */
public class YunshuLineSelection extends AbstractSelection<YunshuLineSelection> {
    @Override
    protected Uri baseUri() {
        return YunshuLineColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code YunshuLineCursor} object, which is positioned before the first entry, or null.
     */
    public YunshuLineCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new YunshuLineCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public YunshuLineCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code YunshuLineCursor} object, which is positioned before the first entry, or null.
     */
    public YunshuLineCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new YunshuLineCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public YunshuLineCursor query(Context context) {
        return query(context, null);
    }


    public YunshuLineSelection id(long... value) {
        addEquals("yunshu_line." + YunshuLineColumns._ID, toObjectArray(value));
        return this;
    }

    public YunshuLineSelection idNot(long... value) {
        addNotEquals("yunshu_line." + YunshuLineColumns._ID, toObjectArray(value));
        return this;
    }

    public YunshuLineSelection orderById(boolean desc) {
        orderBy("yunshu_line." + YunshuLineColumns._ID, desc);
        return this;
    }

    public YunshuLineSelection orderById() {
        return orderById(false);
    }

    public YunshuLineSelection transportTaskId(String... value) {
        addEquals(YunshuLineColumns.TRANSPORT_TASK_ID, value);
        return this;
    }

    public YunshuLineSelection transportTaskIdNot(String... value) {
        addNotEquals(YunshuLineColumns.TRANSPORT_TASK_ID, value);
        return this;
    }

    public YunshuLineSelection transportTaskIdLike(String... value) {
        addLike(YunshuLineColumns.TRANSPORT_TASK_ID, value);
        return this;
    }

    public YunshuLineSelection transportTaskIdContains(String... value) {
        addContains(YunshuLineColumns.TRANSPORT_TASK_ID, value);
        return this;
    }

    public YunshuLineSelection transportTaskIdStartsWith(String... value) {
        addStartsWith(YunshuLineColumns.TRANSPORT_TASK_ID, value);
        return this;
    }

    public YunshuLineSelection transportTaskIdEndsWith(String... value) {
        addEndsWith(YunshuLineColumns.TRANSPORT_TASK_ID, value);
        return this;
    }

    public YunshuLineSelection orderByTransportTaskId(boolean desc) {
        orderBy(YunshuLineColumns.TRANSPORT_TASK_ID, desc);
        return this;
    }

    public YunshuLineSelection orderByTransportTaskId() {
        orderBy(YunshuLineColumns.TRANSPORT_TASK_ID, false);
        return this;
    }

    public YunshuLineSelection lineNo(String... value) {
        addEquals(YunshuLineColumns.LINE_NO, value);
        return this;
    }

    public YunshuLineSelection lineNoNot(String... value) {
        addNotEquals(YunshuLineColumns.LINE_NO, value);
        return this;
    }

    public YunshuLineSelection lineNoLike(String... value) {
        addLike(YunshuLineColumns.LINE_NO, value);
        return this;
    }

    public YunshuLineSelection lineNoContains(String... value) {
        addContains(YunshuLineColumns.LINE_NO, value);
        return this;
    }

    public YunshuLineSelection lineNoStartsWith(String... value) {
        addStartsWith(YunshuLineColumns.LINE_NO, value);
        return this;
    }

    public YunshuLineSelection lineNoEndsWith(String... value) {
        addEndsWith(YunshuLineColumns.LINE_NO, value);
        return this;
    }

    public YunshuLineSelection orderByLineNo(boolean desc) {
        orderBy(YunshuLineColumns.LINE_NO, desc);
        return this;
    }

    public YunshuLineSelection orderByLineNo() {
        orderBy(YunshuLineColumns.LINE_NO, false);
        return this;
    }

    public YunshuLineSelection partNo(String... value) {
        addEquals(YunshuLineColumns.PART_NO, value);
        return this;
    }

    public YunshuLineSelection partNoNot(String... value) {
        addNotEquals(YunshuLineColumns.PART_NO, value);
        return this;
    }

    public YunshuLineSelection partNoLike(String... value) {
        addLike(YunshuLineColumns.PART_NO, value);
        return this;
    }

    public YunshuLineSelection partNoContains(String... value) {
        addContains(YunshuLineColumns.PART_NO, value);
        return this;
    }

    public YunshuLineSelection partNoStartsWith(String... value) {
        addStartsWith(YunshuLineColumns.PART_NO, value);
        return this;
    }

    public YunshuLineSelection partNoEndsWith(String... value) {
        addEndsWith(YunshuLineColumns.PART_NO, value);
        return this;
    }

    public YunshuLineSelection orderByPartNo(boolean desc) {
        orderBy(YunshuLineColumns.PART_NO, desc);
        return this;
    }

    public YunshuLineSelection orderByPartNo() {
        orderBy(YunshuLineColumns.PART_NO, false);
        return this;
    }

    public YunshuLineSelection partName(String... value) {
        addEquals(YunshuLineColumns.PART_NAME, value);
        return this;
    }

    public YunshuLineSelection partNameNot(String... value) {
        addNotEquals(YunshuLineColumns.PART_NAME, value);
        return this;
    }

    public YunshuLineSelection partNameLike(String... value) {
        addLike(YunshuLineColumns.PART_NAME, value);
        return this;
    }

    public YunshuLineSelection partNameContains(String... value) {
        addContains(YunshuLineColumns.PART_NAME, value);
        return this;
    }

    public YunshuLineSelection partNameStartsWith(String... value) {
        addStartsWith(YunshuLineColumns.PART_NAME, value);
        return this;
    }

    public YunshuLineSelection partNameEndsWith(String... value) {
        addEndsWith(YunshuLineColumns.PART_NAME, value);
        return this;
    }

    public YunshuLineSelection orderByPartName(boolean desc) {
        orderBy(YunshuLineColumns.PART_NAME, desc);
        return this;
    }

    public YunshuLineSelection orderByPartName() {
        orderBy(YunshuLineColumns.PART_NAME, false);
        return this;
    }

    public YunshuLineSelection quantity(String... value) {
        addEquals(YunshuLineColumns.QUANTITY, value);
        return this;
    }

    public YunshuLineSelection quantityNot(String... value) {
        addNotEquals(YunshuLineColumns.QUANTITY, value);
        return this;
    }

    public YunshuLineSelection quantityLike(String... value) {
        addLike(YunshuLineColumns.QUANTITY, value);
        return this;
    }

    public YunshuLineSelection quantityContains(String... value) {
        addContains(YunshuLineColumns.QUANTITY, value);
        return this;
    }

    public YunshuLineSelection quantityStartsWith(String... value) {
        addStartsWith(YunshuLineColumns.QUANTITY, value);
        return this;
    }

    public YunshuLineSelection quantityEndsWith(String... value) {
        addEndsWith(YunshuLineColumns.QUANTITY, value);
        return this;
    }

    public YunshuLineSelection orderByQuantity(boolean desc) {
        orderBy(YunshuLineColumns.QUANTITY, desc);
        return this;
    }

    public YunshuLineSelection orderByQuantity() {
        orderBy(YunshuLineColumns.QUANTITY, false);
        return this;
    }

    public YunshuLineSelection unit(String... value) {
        addEquals(YunshuLineColumns.UNIT, value);
        return this;
    }

    public YunshuLineSelection unitNot(String... value) {
        addNotEquals(YunshuLineColumns.UNIT, value);
        return this;
    }

    public YunshuLineSelection unitLike(String... value) {
        addLike(YunshuLineColumns.UNIT, value);
        return this;
    }

    public YunshuLineSelection unitContains(String... value) {
        addContains(YunshuLineColumns.UNIT, value);
        return this;
    }

    public YunshuLineSelection unitStartsWith(String... value) {
        addStartsWith(YunshuLineColumns.UNIT, value);
        return this;
    }

    public YunshuLineSelection unitEndsWith(String... value) {
        addEndsWith(YunshuLineColumns.UNIT, value);
        return this;
    }

    public YunshuLineSelection orderByUnit(boolean desc) {
        orderBy(YunshuLineColumns.UNIT, desc);
        return this;
    }

    public YunshuLineSelection orderByUnit() {
        orderBy(YunshuLineColumns.UNIT, false);
        return this;
    }

    public YunshuLineSelection lotBatchNo(String... value) {
        addEquals(YunshuLineColumns.LOT_BATCH_NO, value);
        return this;
    }

    public YunshuLineSelection lotBatchNoNot(String... value) {
        addNotEquals(YunshuLineColumns.LOT_BATCH_NO, value);
        return this;
    }

    public YunshuLineSelection lotBatchNoLike(String... value) {
        addLike(YunshuLineColumns.LOT_BATCH_NO, value);
        return this;
    }

    public YunshuLineSelection lotBatchNoContains(String... value) {
        addContains(YunshuLineColumns.LOT_BATCH_NO, value);
        return this;
    }

    public YunshuLineSelection lotBatchNoStartsWith(String... value) {
        addStartsWith(YunshuLineColumns.LOT_BATCH_NO, value);
        return this;
    }

    public YunshuLineSelection lotBatchNoEndsWith(String... value) {
        addEndsWith(YunshuLineColumns.LOT_BATCH_NO, value);
        return this;
    }

    public YunshuLineSelection orderByLotBatchNo(boolean desc) {
        orderBy(YunshuLineColumns.LOT_BATCH_NO, desc);
        return this;
    }

    public YunshuLineSelection orderByLotBatchNo() {
        orderBy(YunshuLineColumns.LOT_BATCH_NO, false);
        return this;
    }

    public YunshuLineSelection serialNo(String... value) {
        addEquals(YunshuLineColumns.SERIAL_NO, value);
        return this;
    }

    public YunshuLineSelection serialNoNot(String... value) {
        addNotEquals(YunshuLineColumns.SERIAL_NO, value);
        return this;
    }

    public YunshuLineSelection serialNoLike(String... value) {
        addLike(YunshuLineColumns.SERIAL_NO, value);
        return this;
    }

    public YunshuLineSelection serialNoContains(String... value) {
        addContains(YunshuLineColumns.SERIAL_NO, value);
        return this;
    }

    public YunshuLineSelection serialNoStartsWith(String... value) {
        addStartsWith(YunshuLineColumns.SERIAL_NO, value);
        return this;
    }

    public YunshuLineSelection serialNoEndsWith(String... value) {
        addEndsWith(YunshuLineColumns.SERIAL_NO, value);
        return this;
    }

    public YunshuLineSelection orderBySerialNo(boolean desc) {
        orderBy(YunshuLineColumns.SERIAL_NO, desc);
        return this;
    }

    public YunshuLineSelection orderBySerialNo() {
        orderBy(YunshuLineColumns.SERIAL_NO, false);
        return this;
    }

    public YunshuLineSelection format(String... value) {
        addEquals(YunshuLineColumns.FORMAT, value);
        return this;
    }

    public YunshuLineSelection formatNot(String... value) {
        addNotEquals(YunshuLineColumns.FORMAT, value);
        return this;
    }

    public YunshuLineSelection formatLike(String... value) {
        addLike(YunshuLineColumns.FORMAT, value);
        return this;
    }

    public YunshuLineSelection formatContains(String... value) {
        addContains(YunshuLineColumns.FORMAT, value);
        return this;
    }

    public YunshuLineSelection formatStartsWith(String... value) {
        addStartsWith(YunshuLineColumns.FORMAT, value);
        return this;
    }

    public YunshuLineSelection formatEndsWith(String... value) {
        addEndsWith(YunshuLineColumns.FORMAT, value);
        return this;
    }

    public YunshuLineSelection orderByFormat(boolean desc) {
        orderBy(YunshuLineColumns.FORMAT, desc);
        return this;
    }

    public YunshuLineSelection orderByFormat() {
        orderBy(YunshuLineColumns.FORMAT, false);
        return this;
    }

    public YunshuLineSelection lineType(String... value) {
        addEquals(YunshuLineColumns.LINE_TYPE, value);
        return this;
    }

    public YunshuLineSelection lineTypeNot(String... value) {
        addNotEquals(YunshuLineColumns.LINE_TYPE, value);
        return this;
    }

    public YunshuLineSelection lineTypeLike(String... value) {
        addLike(YunshuLineColumns.LINE_TYPE, value);
        return this;
    }

    public YunshuLineSelection lineTypeContains(String... value) {
        addContains(YunshuLineColumns.LINE_TYPE, value);
        return this;
    }

    public YunshuLineSelection lineTypeStartsWith(String... value) {
        addStartsWith(YunshuLineColumns.LINE_TYPE, value);
        return this;
    }

    public YunshuLineSelection lineTypeEndsWith(String... value) {
        addEndsWith(YunshuLineColumns.LINE_TYPE, value);
        return this;
    }

    public YunshuLineSelection orderByLineType(boolean desc) {
        orderBy(YunshuLineColumns.LINE_TYPE, desc);
        return this;
    }

    public YunshuLineSelection orderByLineType() {
        orderBy(YunshuLineColumns.LINE_TYPE, false);
        return this;
    }

    public YunshuLineSelection lineMark(Long... value) {
        addEquals(YunshuLineColumns.LINE_MARK, value);
        return this;
    }

    public YunshuLineSelection lineMarkNot(Long... value) {
        addNotEquals(YunshuLineColumns.LINE_MARK, value);
        return this;
    }

    public YunshuLineSelection lineMarkGt(long value) {
        addGreaterThan(YunshuLineColumns.LINE_MARK, value);
        return this;
    }

    public YunshuLineSelection lineMarkGtEq(long value) {
        addGreaterThanOrEquals(YunshuLineColumns.LINE_MARK, value);
        return this;
    }

    public YunshuLineSelection lineMarkLt(long value) {
        addLessThan(YunshuLineColumns.LINE_MARK, value);
        return this;
    }

    public YunshuLineSelection lineMarkLtEq(long value) {
        addLessThanOrEquals(YunshuLineColumns.LINE_MARK, value);
        return this;
    }

    public YunshuLineSelection orderByLineMark(boolean desc) {
        orderBy(YunshuLineColumns.LINE_MARK, desc);
        return this;
    }

    public YunshuLineSelection orderByLineMark() {
        orderBy(YunshuLineColumns.LINE_MARK, false);
        return this;
    }

    public YunshuLineSelection keepCol1(String... value) {
        addEquals(YunshuLineColumns.KEEP_COL1, value);
        return this;
    }

    public YunshuLineSelection keepCol1Not(String... value) {
        addNotEquals(YunshuLineColumns.KEEP_COL1, value);
        return this;
    }

    public YunshuLineSelection keepCol1Like(String... value) {
        addLike(YunshuLineColumns.KEEP_COL1, value);
        return this;
    }

    public YunshuLineSelection keepCol1Contains(String... value) {
        addContains(YunshuLineColumns.KEEP_COL1, value);
        return this;
    }

    public YunshuLineSelection keepCol1StartsWith(String... value) {
        addStartsWith(YunshuLineColumns.KEEP_COL1, value);
        return this;
    }

    public YunshuLineSelection keepCol1EndsWith(String... value) {
        addEndsWith(YunshuLineColumns.KEEP_COL1, value);
        return this;
    }

    public YunshuLineSelection orderByKeepCol1(boolean desc) {
        orderBy(YunshuLineColumns.KEEP_COL1, desc);
        return this;
    }

    public YunshuLineSelection orderByKeepCol1() {
        orderBy(YunshuLineColumns.KEEP_COL1, false);
        return this;
    }

    public YunshuLineSelection keepCol2(String... value) {
        addEquals(YunshuLineColumns.KEEP_COL2, value);
        return this;
    }

    public YunshuLineSelection keepCol2Not(String... value) {
        addNotEquals(YunshuLineColumns.KEEP_COL2, value);
        return this;
    }

    public YunshuLineSelection keepCol2Like(String... value) {
        addLike(YunshuLineColumns.KEEP_COL2, value);
        return this;
    }

    public YunshuLineSelection keepCol2Contains(String... value) {
        addContains(YunshuLineColumns.KEEP_COL2, value);
        return this;
    }

    public YunshuLineSelection keepCol2StartsWith(String... value) {
        addStartsWith(YunshuLineColumns.KEEP_COL2, value);
        return this;
    }

    public YunshuLineSelection keepCol2EndsWith(String... value) {
        addEndsWith(YunshuLineColumns.KEEP_COL2, value);
        return this;
    }

    public YunshuLineSelection orderByKeepCol2(boolean desc) {
        orderBy(YunshuLineColumns.KEEP_COL2, desc);
        return this;
    }

    public YunshuLineSelection orderByKeepCol2() {
        orderBy(YunshuLineColumns.KEEP_COL2, false);
        return this;
    }
}
