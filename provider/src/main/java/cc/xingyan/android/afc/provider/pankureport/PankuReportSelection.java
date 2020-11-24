package cc.xingyan.android.afc.provider.pankureport;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code panku_report} table.
 */
public class PankuReportSelection extends AbstractSelection<PankuReportSelection> {
    @Override
    protected Uri baseUri() {
        return PankuReportColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PankuReportCursor} object, which is positioned before the first entry, or null.
     */
    public PankuReportCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PankuReportCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public PankuReportCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PankuReportCursor} object, which is positioned before the first entry, or null.
     */
    public PankuReportCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PankuReportCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public PankuReportCursor query(Context context) {
        return query(context, null);
    }


    public PankuReportSelection id(long... value) {
        addEquals("panku_report." + PankuReportColumns._ID, toObjectArray(value));
        return this;
    }

    public PankuReportSelection idNot(long... value) {
        addNotEquals("panku_report." + PankuReportColumns._ID, toObjectArray(value));
        return this;
    }

    public PankuReportSelection orderById(boolean desc) {
        orderBy("panku_report." + PankuReportColumns._ID, desc);
        return this;
    }

    public PankuReportSelection orderById() {
        return orderById(false);
    }

    public PankuReportSelection userId(String... value) {
        addEquals(PankuReportColumns.USER_ID, value);
        return this;
    }

    public PankuReportSelection userIdNot(String... value) {
        addNotEquals(PankuReportColumns.USER_ID, value);
        return this;
    }

    public PankuReportSelection userIdLike(String... value) {
        addLike(PankuReportColumns.USER_ID, value);
        return this;
    }

    public PankuReportSelection userIdContains(String... value) {
        addContains(PankuReportColumns.USER_ID, value);
        return this;
    }

    public PankuReportSelection userIdStartsWith(String... value) {
        addStartsWith(PankuReportColumns.USER_ID, value);
        return this;
    }

    public PankuReportSelection userIdEndsWith(String... value) {
        addEndsWith(PankuReportColumns.USER_ID, value);
        return this;
    }

    public PankuReportSelection orderByUserId(boolean desc) {
        orderBy(PankuReportColumns.USER_ID, desc);
        return this;
    }

    public PankuReportSelection orderByUserId() {
        orderBy(PankuReportColumns.USER_ID, false);
        return this;
    }

    public PankuReportSelection reportNo(String... value) {
        addEquals(PankuReportColumns.REPORT_NO, value);
        return this;
    }

    public PankuReportSelection reportNoNot(String... value) {
        addNotEquals(PankuReportColumns.REPORT_NO, value);
        return this;
    }

    public PankuReportSelection reportNoLike(String... value) {
        addLike(PankuReportColumns.REPORT_NO, value);
        return this;
    }

    public PankuReportSelection reportNoContains(String... value) {
        addContains(PankuReportColumns.REPORT_NO, value);
        return this;
    }

    public PankuReportSelection reportNoStartsWith(String... value) {
        addStartsWith(PankuReportColumns.REPORT_NO, value);
        return this;
    }

    public PankuReportSelection reportNoEndsWith(String... value) {
        addEndsWith(PankuReportColumns.REPORT_NO, value);
        return this;
    }

    public PankuReportSelection orderByReportNo(boolean desc) {
        orderBy(PankuReportColumns.REPORT_NO, desc);
        return this;
    }

    public PankuReportSelection orderByReportNo() {
        orderBy(PankuReportColumns.REPORT_NO, false);
        return this;
    }

    public PankuReportSelection partNo(String... value) {
        addEquals(PankuReportColumns.PART_NO, value);
        return this;
    }

    public PankuReportSelection partNoNot(String... value) {
        addNotEquals(PankuReportColumns.PART_NO, value);
        return this;
    }

    public PankuReportSelection partNoLike(String... value) {
        addLike(PankuReportColumns.PART_NO, value);
        return this;
    }

    public PankuReportSelection partNoContains(String... value) {
        addContains(PankuReportColumns.PART_NO, value);
        return this;
    }

    public PankuReportSelection partNoStartsWith(String... value) {
        addStartsWith(PankuReportColumns.PART_NO, value);
        return this;
    }

    public PankuReportSelection partNoEndsWith(String... value) {
        addEndsWith(PankuReportColumns.PART_NO, value);
        return this;
    }

    public PankuReportSelection orderByPartNo(boolean desc) {
        orderBy(PankuReportColumns.PART_NO, desc);
        return this;
    }

    public PankuReportSelection orderByPartNo() {
        orderBy(PankuReportColumns.PART_NO, false);
        return this;
    }

    public PankuReportSelection partName(String... value) {
        addEquals(PankuReportColumns.PART_NAME, value);
        return this;
    }

    public PankuReportSelection partNameNot(String... value) {
        addNotEquals(PankuReportColumns.PART_NAME, value);
        return this;
    }

    public PankuReportSelection partNameLike(String... value) {
        addLike(PankuReportColumns.PART_NAME, value);
        return this;
    }

    public PankuReportSelection partNameContains(String... value) {
        addContains(PankuReportColumns.PART_NAME, value);
        return this;
    }

    public PankuReportSelection partNameStartsWith(String... value) {
        addStartsWith(PankuReportColumns.PART_NAME, value);
        return this;
    }

    public PankuReportSelection partNameEndsWith(String... value) {
        addEndsWith(PankuReportColumns.PART_NAME, value);
        return this;
    }

    public PankuReportSelection orderByPartName(boolean desc) {
        orderBy(PankuReportColumns.PART_NAME, desc);
        return this;
    }

    public PankuReportSelection orderByPartName() {
        orderBy(PankuReportColumns.PART_NAME, false);
        return this;
    }

    public PankuReportSelection actualAmount(String... value) {
        addEquals(PankuReportColumns.ACTUAL_AMOUNT, value);
        return this;
    }

    public PankuReportSelection actualAmountNot(String... value) {
        addNotEquals(PankuReportColumns.ACTUAL_AMOUNT, value);
        return this;
    }

    public PankuReportSelection actualAmountLike(String... value) {
        addLike(PankuReportColumns.ACTUAL_AMOUNT, value);
        return this;
    }

    public PankuReportSelection actualAmountContains(String... value) {
        addContains(PankuReportColumns.ACTUAL_AMOUNT, value);
        return this;
    }

    public PankuReportSelection actualAmountStartsWith(String... value) {
        addStartsWith(PankuReportColumns.ACTUAL_AMOUNT, value);
        return this;
    }

    public PankuReportSelection actualAmountEndsWith(String... value) {
        addEndsWith(PankuReportColumns.ACTUAL_AMOUNT, value);
        return this;
    }

    public PankuReportSelection orderByActualAmount(boolean desc) {
        orderBy(PankuReportColumns.ACTUAL_AMOUNT, desc);
        return this;
    }

    public PankuReportSelection orderByActualAmount() {
        orderBy(PankuReportColumns.ACTUAL_AMOUNT, false);
        return this;
    }

    public PankuReportSelection lotbatchNo(String... value) {
        addEquals(PankuReportColumns.LOTBATCH_NO, value);
        return this;
    }

    public PankuReportSelection lotbatchNoNot(String... value) {
        addNotEquals(PankuReportColumns.LOTBATCH_NO, value);
        return this;
    }

    public PankuReportSelection lotbatchNoLike(String... value) {
        addLike(PankuReportColumns.LOTBATCH_NO, value);
        return this;
    }

    public PankuReportSelection lotbatchNoContains(String... value) {
        addContains(PankuReportColumns.LOTBATCH_NO, value);
        return this;
    }

    public PankuReportSelection lotbatchNoStartsWith(String... value) {
        addStartsWith(PankuReportColumns.LOTBATCH_NO, value);
        return this;
    }

    public PankuReportSelection lotbatchNoEndsWith(String... value) {
        addEndsWith(PankuReportColumns.LOTBATCH_NO, value);
        return this;
    }

    public PankuReportSelection orderByLotbatchNo(boolean desc) {
        orderBy(PankuReportColumns.LOTBATCH_NO, desc);
        return this;
    }

    public PankuReportSelection orderByLotbatchNo() {
        orderBy(PankuReportColumns.LOTBATCH_NO, false);
        return this;
    }

    public PankuReportSelection lineNo(String... value) {
        addEquals(PankuReportColumns.LINE_NO, value);
        return this;
    }

    public PankuReportSelection lineNoNot(String... value) {
        addNotEquals(PankuReportColumns.LINE_NO, value);
        return this;
    }

    public PankuReportSelection lineNoLike(String... value) {
        addLike(PankuReportColumns.LINE_NO, value);
        return this;
    }

    public PankuReportSelection lineNoContains(String... value) {
        addContains(PankuReportColumns.LINE_NO, value);
        return this;
    }

    public PankuReportSelection lineNoStartsWith(String... value) {
        addStartsWith(PankuReportColumns.LINE_NO, value);
        return this;
    }

    public PankuReportSelection lineNoEndsWith(String... value) {
        addEndsWith(PankuReportColumns.LINE_NO, value);
        return this;
    }

    public PankuReportSelection orderByLineNo(boolean desc) {
        orderBy(PankuReportColumns.LINE_NO, desc);
        return this;
    }

    public PankuReportSelection orderByLineNo() {
        orderBy(PankuReportColumns.LINE_NO, false);
        return this;
    }

    public PankuReportSelection partSeq(String... value) {
        addEquals(PankuReportColumns.PART_SEQ, value);
        return this;
    }

    public PankuReportSelection partSeqNot(String... value) {
        addNotEquals(PankuReportColumns.PART_SEQ, value);
        return this;
    }

    public PankuReportSelection partSeqLike(String... value) {
        addLike(PankuReportColumns.PART_SEQ, value);
        return this;
    }

    public PankuReportSelection partSeqContains(String... value) {
        addContains(PankuReportColumns.PART_SEQ, value);
        return this;
    }

    public PankuReportSelection partSeqStartsWith(String... value) {
        addStartsWith(PankuReportColumns.PART_SEQ, value);
        return this;
    }

    public PankuReportSelection partSeqEndsWith(String... value) {
        addEndsWith(PankuReportColumns.PART_SEQ, value);
        return this;
    }

    public PankuReportSelection orderByPartSeq(boolean desc) {
        orderBy(PankuReportColumns.PART_SEQ, desc);
        return this;
    }

    public PankuReportSelection orderByPartSeq() {
        orderBy(PankuReportColumns.PART_SEQ, false);
        return this;
    }

    public PankuReportSelection warehouseNo(String... value) {
        addEquals(PankuReportColumns.WAREHOUSE_NO, value);
        return this;
    }

    public PankuReportSelection warehouseNoNot(String... value) {
        addNotEquals(PankuReportColumns.WAREHOUSE_NO, value);
        return this;
    }

    public PankuReportSelection warehouseNoLike(String... value) {
        addLike(PankuReportColumns.WAREHOUSE_NO, value);
        return this;
    }

    public PankuReportSelection warehouseNoContains(String... value) {
        addContains(PankuReportColumns.WAREHOUSE_NO, value);
        return this;
    }

    public PankuReportSelection warehouseNoStartsWith(String... value) {
        addStartsWith(PankuReportColumns.WAREHOUSE_NO, value);
        return this;
    }

    public PankuReportSelection warehouseNoEndsWith(String... value) {
        addEndsWith(PankuReportColumns.WAREHOUSE_NO, value);
        return this;
    }

    public PankuReportSelection orderByWarehouseNo(boolean desc) {
        orderBy(PankuReportColumns.WAREHOUSE_NO, desc);
        return this;
    }

    public PankuReportSelection orderByWarehouseNo() {
        orderBy(PankuReportColumns.WAREHOUSE_NO, false);
        return this;
    }

    public PankuReportSelection warehouseName(String... value) {
        addEquals(PankuReportColumns.WAREHOUSE_NAME, value);
        return this;
    }

    public PankuReportSelection warehouseNameNot(String... value) {
        addNotEquals(PankuReportColumns.WAREHOUSE_NAME, value);
        return this;
    }

    public PankuReportSelection warehouseNameLike(String... value) {
        addLike(PankuReportColumns.WAREHOUSE_NAME, value);
        return this;
    }

    public PankuReportSelection warehouseNameContains(String... value) {
        addContains(PankuReportColumns.WAREHOUSE_NAME, value);
        return this;
    }

    public PankuReportSelection warehouseNameStartsWith(String... value) {
        addStartsWith(PankuReportColumns.WAREHOUSE_NAME, value);
        return this;
    }

    public PankuReportSelection warehouseNameEndsWith(String... value) {
        addEndsWith(PankuReportColumns.WAREHOUSE_NAME, value);
        return this;
    }

    public PankuReportSelection orderByWarehouseName(boolean desc) {
        orderBy(PankuReportColumns.WAREHOUSE_NAME, desc);
        return this;
    }

    public PankuReportSelection orderByWarehouseName() {
        orderBy(PankuReportColumns.WAREHOUSE_NAME, false);
        return this;
    }

    public PankuReportSelection pandianMark(String... value) {
        addEquals(PankuReportColumns.PANDIAN_MARK, value);
        return this;
    }

    public PankuReportSelection pandianMarkNot(String... value) {
        addNotEquals(PankuReportColumns.PANDIAN_MARK, value);
        return this;
    }

    public PankuReportSelection pandianMarkLike(String... value) {
        addLike(PankuReportColumns.PANDIAN_MARK, value);
        return this;
    }

    public PankuReportSelection pandianMarkContains(String... value) {
        addContains(PankuReportColumns.PANDIAN_MARK, value);
        return this;
    }

    public PankuReportSelection pandianMarkStartsWith(String... value) {
        addStartsWith(PankuReportColumns.PANDIAN_MARK, value);
        return this;
    }

    public PankuReportSelection pandianMarkEndsWith(String... value) {
        addEndsWith(PankuReportColumns.PANDIAN_MARK, value);
        return this;
    }

    public PankuReportSelection orderByPandianMark(boolean desc) {
        orderBy(PankuReportColumns.PANDIAN_MARK, desc);
        return this;
    }

    public PankuReportSelection orderByPandianMark() {
        orderBy(PankuReportColumns.PANDIAN_MARK, false);
        return this;
    }

    public PankuReportSelection pandianTime(Long... value) {
        addEquals(PankuReportColumns.PANDIAN_TIME, value);
        return this;
    }

    public PankuReportSelection pandianTimeNot(Long... value) {
        addNotEquals(PankuReportColumns.PANDIAN_TIME, value);
        return this;
    }

    public PankuReportSelection pandianTimeGt(long value) {
        addGreaterThan(PankuReportColumns.PANDIAN_TIME, value);
        return this;
    }

    public PankuReportSelection pandianTimeGtEq(long value) {
        addGreaterThanOrEquals(PankuReportColumns.PANDIAN_TIME, value);
        return this;
    }

    public PankuReportSelection pandianTimeLt(long value) {
        addLessThan(PankuReportColumns.PANDIAN_TIME, value);
        return this;
    }

    public PankuReportSelection pandianTimeLtEq(long value) {
        addLessThanOrEquals(PankuReportColumns.PANDIAN_TIME, value);
        return this;
    }

    public PankuReportSelection orderByPandianTime(boolean desc) {
        orderBy(PankuReportColumns.PANDIAN_TIME, desc);
        return this;
    }

    public PankuReportSelection orderByPandianTime() {
        orderBy(PankuReportColumns.PANDIAN_TIME, false);
        return this;
    }
}
