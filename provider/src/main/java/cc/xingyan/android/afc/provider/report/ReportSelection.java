package cc.xingyan.android.afc.provider.report;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code report} table.
 */
public class ReportSelection extends AbstractSelection<ReportSelection> {
    @Override
    protected Uri baseUri() {
        return ReportColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ReportCursor} object, which is positioned before the first entry, or null.
     */
    public ReportCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ReportCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public ReportCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ReportCursor} object, which is positioned before the first entry, or null.
     */
    public ReportCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ReportCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public ReportCursor query(Context context) {
        return query(context, null);
    }


    public ReportSelection id(long... value) {
        addEquals("report." + ReportColumns._ID, toObjectArray(value));
        return this;
    }

    public ReportSelection idNot(long... value) {
        addNotEquals("report." + ReportColumns._ID, toObjectArray(value));
        return this;
    }

    public ReportSelection orderById(boolean desc) {
        orderBy("report." + ReportColumns._ID, desc);
        return this;
    }

    public ReportSelection orderById() {
        return orderById(false);
    }

    public ReportSelection code(String... value) {
        addEquals(ReportColumns.CODE, value);
        return this;
    }

    public ReportSelection codeNot(String... value) {
        addNotEquals(ReportColumns.CODE, value);
        return this;
    }

    public ReportSelection codeLike(String... value) {
        addLike(ReportColumns.CODE, value);
        return this;
    }

    public ReportSelection codeContains(String... value) {
        addContains(ReportColumns.CODE, value);
        return this;
    }

    public ReportSelection codeStartsWith(String... value) {
        addStartsWith(ReportColumns.CODE, value);
        return this;
    }

    public ReportSelection codeEndsWith(String... value) {
        addEndsWith(ReportColumns.CODE, value);
        return this;
    }

    public ReportSelection orderByCode(boolean desc) {
        orderBy(ReportColumns.CODE, desc);
        return this;
    }

    public ReportSelection orderByCode() {
        orderBy(ReportColumns.CODE, false);
        return this;
    }

    public ReportSelection name(String... value) {
        addEquals(ReportColumns.NAME, value);
        return this;
    }

    public ReportSelection nameNot(String... value) {
        addNotEquals(ReportColumns.NAME, value);
        return this;
    }

    public ReportSelection nameLike(String... value) {
        addLike(ReportColumns.NAME, value);
        return this;
    }

    public ReportSelection nameContains(String... value) {
        addContains(ReportColumns.NAME, value);
        return this;
    }

    public ReportSelection nameStartsWith(String... value) {
        addStartsWith(ReportColumns.NAME, value);
        return this;
    }

    public ReportSelection nameEndsWith(String... value) {
        addEndsWith(ReportColumns.NAME, value);
        return this;
    }

    public ReportSelection orderByName(boolean desc) {
        orderBy(ReportColumns.NAME, desc);
        return this;
    }

    public ReportSelection orderByName() {
        orderBy(ReportColumns.NAME, false);
        return this;
    }

    public ReportSelection dataStart(Long... value) {
        addEquals(ReportColumns.DATA_START, value);
        return this;
    }

    public ReportSelection dataStartNot(Long... value) {
        addNotEquals(ReportColumns.DATA_START, value);
        return this;
    }

    public ReportSelection dataStartGt(long value) {
        addGreaterThan(ReportColumns.DATA_START, value);
        return this;
    }

    public ReportSelection dataStartGtEq(long value) {
        addGreaterThanOrEquals(ReportColumns.DATA_START, value);
        return this;
    }

    public ReportSelection dataStartLt(long value) {
        addLessThan(ReportColumns.DATA_START, value);
        return this;
    }

    public ReportSelection dataStartLtEq(long value) {
        addLessThanOrEquals(ReportColumns.DATA_START, value);
        return this;
    }

    public ReportSelection orderByDataStart(boolean desc) {
        orderBy(ReportColumns.DATA_START, desc);
        return this;
    }

    public ReportSelection orderByDataStart() {
        orderBy(ReportColumns.DATA_START, false);
        return this;
    }

    public ReportSelection dataEnd(Long... value) {
        addEquals(ReportColumns.DATA_END, value);
        return this;
    }

    public ReportSelection dataEndNot(Long... value) {
        addNotEquals(ReportColumns.DATA_END, value);
        return this;
    }

    public ReportSelection dataEndGt(long value) {
        addGreaterThan(ReportColumns.DATA_END, value);
        return this;
    }

    public ReportSelection dataEndGtEq(long value) {
        addGreaterThanOrEquals(ReportColumns.DATA_END, value);
        return this;
    }

    public ReportSelection dataEndLt(long value) {
        addLessThan(ReportColumns.DATA_END, value);
        return this;
    }

    public ReportSelection dataEndLtEq(long value) {
        addLessThanOrEquals(ReportColumns.DATA_END, value);
        return this;
    }

    public ReportSelection orderByDataEnd(boolean desc) {
        orderBy(ReportColumns.DATA_END, desc);
        return this;
    }

    public ReportSelection orderByDataEnd() {
        orderBy(ReportColumns.DATA_END, false);
        return this;
    }

    public ReportSelection lastReceNum(String... value) {
        addEquals(ReportColumns.LAST_RECE_NUM, value);
        return this;
    }

    public ReportSelection lastReceNumNot(String... value) {
        addNotEquals(ReportColumns.LAST_RECE_NUM, value);
        return this;
    }

    public ReportSelection lastReceNumLike(String... value) {
        addLike(ReportColumns.LAST_RECE_NUM, value);
        return this;
    }

    public ReportSelection lastReceNumContains(String... value) {
        addContains(ReportColumns.LAST_RECE_NUM, value);
        return this;
    }

    public ReportSelection lastReceNumStartsWith(String... value) {
        addStartsWith(ReportColumns.LAST_RECE_NUM, value);
        return this;
    }

    public ReportSelection lastReceNumEndsWith(String... value) {
        addEndsWith(ReportColumns.LAST_RECE_NUM, value);
        return this;
    }

    public ReportSelection orderByLastReceNum(boolean desc) {
        orderBy(ReportColumns.LAST_RECE_NUM, desc);
        return this;
    }

    public ReportSelection orderByLastReceNum() {
        orderBy(ReportColumns.LAST_RECE_NUM, false);
        return this;
    }

    public ReportSelection currectReceNum(String... value) {
        addEquals(ReportColumns.CURRECT_RECE_NUM, value);
        return this;
    }

    public ReportSelection currectReceNumNot(String... value) {
        addNotEquals(ReportColumns.CURRECT_RECE_NUM, value);
        return this;
    }

    public ReportSelection currectReceNumLike(String... value) {
        addLike(ReportColumns.CURRECT_RECE_NUM, value);
        return this;
    }

    public ReportSelection currectReceNumContains(String... value) {
        addContains(ReportColumns.CURRECT_RECE_NUM, value);
        return this;
    }

    public ReportSelection currectReceNumStartsWith(String... value) {
        addStartsWith(ReportColumns.CURRECT_RECE_NUM, value);
        return this;
    }

    public ReportSelection currectReceNumEndsWith(String... value) {
        addEndsWith(ReportColumns.CURRECT_RECE_NUM, value);
        return this;
    }

    public ReportSelection orderByCurrectReceNum(boolean desc) {
        orderBy(ReportColumns.CURRECT_RECE_NUM, desc);
        return this;
    }

    public ReportSelection orderByCurrectReceNum() {
        orderBy(ReportColumns.CURRECT_RECE_NUM, false);
        return this;
    }

    public ReportSelection receLrr(String... value) {
        addEquals(ReportColumns.RECE_LRR, value);
        return this;
    }

    public ReportSelection receLrrNot(String... value) {
        addNotEquals(ReportColumns.RECE_LRR, value);
        return this;
    }

    public ReportSelection receLrrLike(String... value) {
        addLike(ReportColumns.RECE_LRR, value);
        return this;
    }

    public ReportSelection receLrrContains(String... value) {
        addContains(ReportColumns.RECE_LRR, value);
        return this;
    }

    public ReportSelection receLrrStartsWith(String... value) {
        addStartsWith(ReportColumns.RECE_LRR, value);
        return this;
    }

    public ReportSelection receLrrEndsWith(String... value) {
        addEndsWith(ReportColumns.RECE_LRR, value);
        return this;
    }

    public ReportSelection orderByReceLrr(boolean desc) {
        orderBy(ReportColumns.RECE_LRR, desc);
        return this;
    }

    public ReportSelection orderByReceLrr() {
        orderBy(ReportColumns.RECE_LRR, false);
        return this;
    }

    public ReportSelection lastFormNum(String... value) {
        addEquals(ReportColumns.LAST_FORM_NUM, value);
        return this;
    }

    public ReportSelection lastFormNumNot(String... value) {
        addNotEquals(ReportColumns.LAST_FORM_NUM, value);
        return this;
    }

    public ReportSelection lastFormNumLike(String... value) {
        addLike(ReportColumns.LAST_FORM_NUM, value);
        return this;
    }

    public ReportSelection lastFormNumContains(String... value) {
        addContains(ReportColumns.LAST_FORM_NUM, value);
        return this;
    }

    public ReportSelection lastFormNumStartsWith(String... value) {
        addStartsWith(ReportColumns.LAST_FORM_NUM, value);
        return this;
    }

    public ReportSelection lastFormNumEndsWith(String... value) {
        addEndsWith(ReportColumns.LAST_FORM_NUM, value);
        return this;
    }

    public ReportSelection orderByLastFormNum(boolean desc) {
        orderBy(ReportColumns.LAST_FORM_NUM, desc);
        return this;
    }

    public ReportSelection orderByLastFormNum() {
        orderBy(ReportColumns.LAST_FORM_NUM, false);
        return this;
    }

    public ReportSelection currectFormNum(String... value) {
        addEquals(ReportColumns.CURRECT_FORM_NUM, value);
        return this;
    }

    public ReportSelection currectFormNumNot(String... value) {
        addNotEquals(ReportColumns.CURRECT_FORM_NUM, value);
        return this;
    }

    public ReportSelection currectFormNumLike(String... value) {
        addLike(ReportColumns.CURRECT_FORM_NUM, value);
        return this;
    }

    public ReportSelection currectFormNumContains(String... value) {
        addContains(ReportColumns.CURRECT_FORM_NUM, value);
        return this;
    }

    public ReportSelection currectFormNumStartsWith(String... value) {
        addStartsWith(ReportColumns.CURRECT_FORM_NUM, value);
        return this;
    }

    public ReportSelection currectFormNumEndsWith(String... value) {
        addEndsWith(ReportColumns.CURRECT_FORM_NUM, value);
        return this;
    }

    public ReportSelection orderByCurrectFormNum(boolean desc) {
        orderBy(ReportColumns.CURRECT_FORM_NUM, desc);
        return this;
    }

    public ReportSelection orderByCurrectFormNum() {
        orderBy(ReportColumns.CURRECT_FORM_NUM, false);
        return this;
    }

    public ReportSelection formLrr(String... value) {
        addEquals(ReportColumns.FORM_LRR, value);
        return this;
    }

    public ReportSelection formLrrNot(String... value) {
        addNotEquals(ReportColumns.FORM_LRR, value);
        return this;
    }

    public ReportSelection formLrrLike(String... value) {
        addLike(ReportColumns.FORM_LRR, value);
        return this;
    }

    public ReportSelection formLrrContains(String... value) {
        addContains(ReportColumns.FORM_LRR, value);
        return this;
    }

    public ReportSelection formLrrStartsWith(String... value) {
        addStartsWith(ReportColumns.FORM_LRR, value);
        return this;
    }

    public ReportSelection formLrrEndsWith(String... value) {
        addEndsWith(ReportColumns.FORM_LRR, value);
        return this;
    }

    public ReportSelection orderByFormLrr(boolean desc) {
        orderBy(ReportColumns.FORM_LRR, desc);
        return this;
    }

    public ReportSelection orderByFormLrr() {
        orderBy(ReportColumns.FORM_LRR, false);
        return this;
    }

    public ReportSelection lastFormDelay(String... value) {
        addEquals(ReportColumns.LAST_FORM_DELAY, value);
        return this;
    }

    public ReportSelection lastFormDelayNot(String... value) {
        addNotEquals(ReportColumns.LAST_FORM_DELAY, value);
        return this;
    }

    public ReportSelection lastFormDelayLike(String... value) {
        addLike(ReportColumns.LAST_FORM_DELAY, value);
        return this;
    }

    public ReportSelection lastFormDelayContains(String... value) {
        addContains(ReportColumns.LAST_FORM_DELAY, value);
        return this;
    }

    public ReportSelection lastFormDelayStartsWith(String... value) {
        addStartsWith(ReportColumns.LAST_FORM_DELAY, value);
        return this;
    }

    public ReportSelection lastFormDelayEndsWith(String... value) {
        addEndsWith(ReportColumns.LAST_FORM_DELAY, value);
        return this;
    }

    public ReportSelection orderByLastFormDelay(boolean desc) {
        orderBy(ReportColumns.LAST_FORM_DELAY, desc);
        return this;
    }

    public ReportSelection orderByLastFormDelay() {
        orderBy(ReportColumns.LAST_FORM_DELAY, false);
        return this;
    }

    public ReportSelection currectFormDelay(String... value) {
        addEquals(ReportColumns.CURRECT_FORM_DELAY, value);
        return this;
    }

    public ReportSelection currectFormDelayNot(String... value) {
        addNotEquals(ReportColumns.CURRECT_FORM_DELAY, value);
        return this;
    }

    public ReportSelection currectFormDelayLike(String... value) {
        addLike(ReportColumns.CURRECT_FORM_DELAY, value);
        return this;
    }

    public ReportSelection currectFormDelayContains(String... value) {
        addContains(ReportColumns.CURRECT_FORM_DELAY, value);
        return this;
    }

    public ReportSelection currectFormDelayStartsWith(String... value) {
        addStartsWith(ReportColumns.CURRECT_FORM_DELAY, value);
        return this;
    }

    public ReportSelection currectFormDelayEndsWith(String... value) {
        addEndsWith(ReportColumns.CURRECT_FORM_DELAY, value);
        return this;
    }

    public ReportSelection orderByCurrectFormDelay(boolean desc) {
        orderBy(ReportColumns.CURRECT_FORM_DELAY, desc);
        return this;
    }

    public ReportSelection orderByCurrectFormDelay() {
        orderBy(ReportColumns.CURRECT_FORM_DELAY, false);
        return this;
    }

    public ReportSelection formDelayLrr(String... value) {
        addEquals(ReportColumns.FORM_DELAY_LRR, value);
        return this;
    }

    public ReportSelection formDelayLrrNot(String... value) {
        addNotEquals(ReportColumns.FORM_DELAY_LRR, value);
        return this;
    }

    public ReportSelection formDelayLrrLike(String... value) {
        addLike(ReportColumns.FORM_DELAY_LRR, value);
        return this;
    }

    public ReportSelection formDelayLrrContains(String... value) {
        addContains(ReportColumns.FORM_DELAY_LRR, value);
        return this;
    }

    public ReportSelection formDelayLrrStartsWith(String... value) {
        addStartsWith(ReportColumns.FORM_DELAY_LRR, value);
        return this;
    }

    public ReportSelection formDelayLrrEndsWith(String... value) {
        addEndsWith(ReportColumns.FORM_DELAY_LRR, value);
        return this;
    }

    public ReportSelection orderByFormDelayLrr(boolean desc) {
        orderBy(ReportColumns.FORM_DELAY_LRR, desc);
        return this;
    }

    public ReportSelection orderByFormDelayLrr() {
        orderBy(ReportColumns.FORM_DELAY_LRR, false);
        return this;
    }

    public ReportSelection ytdReceNum(String... value) {
        addEquals(ReportColumns.YTD_RECE_NUM, value);
        return this;
    }

    public ReportSelection ytdReceNumNot(String... value) {
        addNotEquals(ReportColumns.YTD_RECE_NUM, value);
        return this;
    }

    public ReportSelection ytdReceNumLike(String... value) {
        addLike(ReportColumns.YTD_RECE_NUM, value);
        return this;
    }

    public ReportSelection ytdReceNumContains(String... value) {
        addContains(ReportColumns.YTD_RECE_NUM, value);
        return this;
    }

    public ReportSelection ytdReceNumStartsWith(String... value) {
        addStartsWith(ReportColumns.YTD_RECE_NUM, value);
        return this;
    }

    public ReportSelection ytdReceNumEndsWith(String... value) {
        addEndsWith(ReportColumns.YTD_RECE_NUM, value);
        return this;
    }

    public ReportSelection orderByYtdReceNum(boolean desc) {
        orderBy(ReportColumns.YTD_RECE_NUM, desc);
        return this;
    }

    public ReportSelection orderByYtdReceNum() {
        orderBy(ReportColumns.YTD_RECE_NUM, false);
        return this;
    }

    public ReportSelection recePer(String... value) {
        addEquals(ReportColumns.RECE_PER, value);
        return this;
    }

    public ReportSelection recePerNot(String... value) {
        addNotEquals(ReportColumns.RECE_PER, value);
        return this;
    }

    public ReportSelection recePerLike(String... value) {
        addLike(ReportColumns.RECE_PER, value);
        return this;
    }

    public ReportSelection recePerContains(String... value) {
        addContains(ReportColumns.RECE_PER, value);
        return this;
    }

    public ReportSelection recePerStartsWith(String... value) {
        addStartsWith(ReportColumns.RECE_PER, value);
        return this;
    }

    public ReportSelection recePerEndsWith(String... value) {
        addEndsWith(ReportColumns.RECE_PER, value);
        return this;
    }

    public ReportSelection orderByRecePer(boolean desc) {
        orderBy(ReportColumns.RECE_PER, desc);
        return this;
    }

    public ReportSelection orderByRecePer() {
        orderBy(ReportColumns.RECE_PER, false);
        return this;
    }

    public ReportSelection formPer(String... value) {
        addEquals(ReportColumns.FORM_PER, value);
        return this;
    }

    public ReportSelection formPerNot(String... value) {
        addNotEquals(ReportColumns.FORM_PER, value);
        return this;
    }

    public ReportSelection formPerLike(String... value) {
        addLike(ReportColumns.FORM_PER, value);
        return this;
    }

    public ReportSelection formPerContains(String... value) {
        addContains(ReportColumns.FORM_PER, value);
        return this;
    }

    public ReportSelection formPerStartsWith(String... value) {
        addStartsWith(ReportColumns.FORM_PER, value);
        return this;
    }

    public ReportSelection formPerEndsWith(String... value) {
        addEndsWith(ReportColumns.FORM_PER, value);
        return this;
    }

    public ReportSelection orderByFormPer(boolean desc) {
        orderBy(ReportColumns.FORM_PER, desc);
        return this;
    }

    public ReportSelection orderByFormPer() {
        orderBy(ReportColumns.FORM_PER, false);
        return this;
    }

    public ReportSelection agNum(String... value) {
        addEquals(ReportColumns.AG_NUM, value);
        return this;
    }

    public ReportSelection agNumNot(String... value) {
        addNotEquals(ReportColumns.AG_NUM, value);
        return this;
    }

    public ReportSelection agNumLike(String... value) {
        addLike(ReportColumns.AG_NUM, value);
        return this;
    }

    public ReportSelection agNumContains(String... value) {
        addContains(ReportColumns.AG_NUM, value);
        return this;
    }

    public ReportSelection agNumStartsWith(String... value) {
        addStartsWith(ReportColumns.AG_NUM, value);
        return this;
    }

    public ReportSelection agNumEndsWith(String... value) {
        addEndsWith(ReportColumns.AG_NUM, value);
        return this;
    }

    public ReportSelection orderByAgNum(boolean desc) {
        orderBy(ReportColumns.AG_NUM, desc);
        return this;
    }

    public ReportSelection orderByAgNum() {
        orderBy(ReportColumns.AG_NUM, false);
        return this;
    }

    public ReportSelection bomNum(String... value) {
        addEquals(ReportColumns.BOM_NUM, value);
        return this;
    }

    public ReportSelection bomNumNot(String... value) {
        addNotEquals(ReportColumns.BOM_NUM, value);
        return this;
    }

    public ReportSelection bomNumLike(String... value) {
        addLike(ReportColumns.BOM_NUM, value);
        return this;
    }

    public ReportSelection bomNumContains(String... value) {
        addContains(ReportColumns.BOM_NUM, value);
        return this;
    }

    public ReportSelection bomNumStartsWith(String... value) {
        addStartsWith(ReportColumns.BOM_NUM, value);
        return this;
    }

    public ReportSelection bomNumEndsWith(String... value) {
        addEndsWith(ReportColumns.BOM_NUM, value);
        return this;
    }

    public ReportSelection orderByBomNum(boolean desc) {
        orderBy(ReportColumns.BOM_NUM, desc);
        return this;
    }

    public ReportSelection orderByBomNum() {
        orderBy(ReportColumns.BOM_NUM, false);
        return this;
    }

    public ReportSelection tvmNum(String... value) {
        addEquals(ReportColumns.TVM_NUM, value);
        return this;
    }

    public ReportSelection tvmNumNot(String... value) {
        addNotEquals(ReportColumns.TVM_NUM, value);
        return this;
    }

    public ReportSelection tvmNumLike(String... value) {
        addLike(ReportColumns.TVM_NUM, value);
        return this;
    }

    public ReportSelection tvmNumContains(String... value) {
        addContains(ReportColumns.TVM_NUM, value);
        return this;
    }

    public ReportSelection tvmNumStartsWith(String... value) {
        addStartsWith(ReportColumns.TVM_NUM, value);
        return this;
    }

    public ReportSelection tvmNumEndsWith(String... value) {
        addEndsWith(ReportColumns.TVM_NUM, value);
        return this;
    }

    public ReportSelection orderByTvmNum(boolean desc) {
        orderBy(ReportColumns.TVM_NUM, desc);
        return this;
    }

    public ReportSelection orderByTvmNum() {
        orderBy(ReportColumns.TVM_NUM, false);
        return this;
    }

    public ReportSelection otherNum(String... value) {
        addEquals(ReportColumns.OTHER_NUM, value);
        return this;
    }

    public ReportSelection otherNumNot(String... value) {
        addNotEquals(ReportColumns.OTHER_NUM, value);
        return this;
    }

    public ReportSelection otherNumLike(String... value) {
        addLike(ReportColumns.OTHER_NUM, value);
        return this;
    }

    public ReportSelection otherNumContains(String... value) {
        addContains(ReportColumns.OTHER_NUM, value);
        return this;
    }

    public ReportSelection otherNumStartsWith(String... value) {
        addStartsWith(ReportColumns.OTHER_NUM, value);
        return this;
    }

    public ReportSelection otherNumEndsWith(String... value) {
        addEndsWith(ReportColumns.OTHER_NUM, value);
        return this;
    }

    public ReportSelection orderByOtherNum(boolean desc) {
        orderBy(ReportColumns.OTHER_NUM, desc);
        return this;
    }

    public ReportSelection orderByOtherNum() {
        orderBy(ReportColumns.OTHER_NUM, false);
        return this;
    }

    public ReportSelection agPer(String... value) {
        addEquals(ReportColumns.AG_PER, value);
        return this;
    }

    public ReportSelection agPerNot(String... value) {
        addNotEquals(ReportColumns.AG_PER, value);
        return this;
    }

    public ReportSelection agPerLike(String... value) {
        addLike(ReportColumns.AG_PER, value);
        return this;
    }

    public ReportSelection agPerContains(String... value) {
        addContains(ReportColumns.AG_PER, value);
        return this;
    }

    public ReportSelection agPerStartsWith(String... value) {
        addStartsWith(ReportColumns.AG_PER, value);
        return this;
    }

    public ReportSelection agPerEndsWith(String... value) {
        addEndsWith(ReportColumns.AG_PER, value);
        return this;
    }

    public ReportSelection orderByAgPer(boolean desc) {
        orderBy(ReportColumns.AG_PER, desc);
        return this;
    }

    public ReportSelection orderByAgPer() {
        orderBy(ReportColumns.AG_PER, false);
        return this;
    }

    public ReportSelection bomPer(String... value) {
        addEquals(ReportColumns.BOM_PER, value);
        return this;
    }

    public ReportSelection bomPerNot(String... value) {
        addNotEquals(ReportColumns.BOM_PER, value);
        return this;
    }

    public ReportSelection bomPerLike(String... value) {
        addLike(ReportColumns.BOM_PER, value);
        return this;
    }

    public ReportSelection bomPerContains(String... value) {
        addContains(ReportColumns.BOM_PER, value);
        return this;
    }

    public ReportSelection bomPerStartsWith(String... value) {
        addStartsWith(ReportColumns.BOM_PER, value);
        return this;
    }

    public ReportSelection bomPerEndsWith(String... value) {
        addEndsWith(ReportColumns.BOM_PER, value);
        return this;
    }

    public ReportSelection orderByBomPer(boolean desc) {
        orderBy(ReportColumns.BOM_PER, desc);
        return this;
    }

    public ReportSelection orderByBomPer() {
        orderBy(ReportColumns.BOM_PER, false);
        return this;
    }

    public ReportSelection tvmPer(String... value) {
        addEquals(ReportColumns.TVM_PER, value);
        return this;
    }

    public ReportSelection tvmPerNot(String... value) {
        addNotEquals(ReportColumns.TVM_PER, value);
        return this;
    }

    public ReportSelection tvmPerLike(String... value) {
        addLike(ReportColumns.TVM_PER, value);
        return this;
    }

    public ReportSelection tvmPerContains(String... value) {
        addContains(ReportColumns.TVM_PER, value);
        return this;
    }

    public ReportSelection tvmPerStartsWith(String... value) {
        addStartsWith(ReportColumns.TVM_PER, value);
        return this;
    }

    public ReportSelection tvmPerEndsWith(String... value) {
        addEndsWith(ReportColumns.TVM_PER, value);
        return this;
    }

    public ReportSelection orderByTvmPer(boolean desc) {
        orderBy(ReportColumns.TVM_PER, desc);
        return this;
    }

    public ReportSelection orderByTvmPer() {
        orderBy(ReportColumns.TVM_PER, false);
        return this;
    }

    public ReportSelection otherPer(String... value) {
        addEquals(ReportColumns.OTHER_PER, value);
        return this;
    }

    public ReportSelection otherPerNot(String... value) {
        addNotEquals(ReportColumns.OTHER_PER, value);
        return this;
    }

    public ReportSelection otherPerLike(String... value) {
        addLike(ReportColumns.OTHER_PER, value);
        return this;
    }

    public ReportSelection otherPerContains(String... value) {
        addContains(ReportColumns.OTHER_PER, value);
        return this;
    }

    public ReportSelection otherPerStartsWith(String... value) {
        addStartsWith(ReportColumns.OTHER_PER, value);
        return this;
    }

    public ReportSelection otherPerEndsWith(String... value) {
        addEndsWith(ReportColumns.OTHER_PER, value);
        return this;
    }

    public ReportSelection orderByOtherPer(boolean desc) {
        orderBy(ReportColumns.OTHER_PER, desc);
        return this;
    }

    public ReportSelection orderByOtherPer() {
        orderBy(ReportColumns.OTHER_PER, false);
        return this;
    }

    public ReportSelection deviceFaultNum(String... value) {
        addEquals(ReportColumns.DEVICE_FAULT_NUM, value);
        return this;
    }

    public ReportSelection deviceFaultNumNot(String... value) {
        addNotEquals(ReportColumns.DEVICE_FAULT_NUM, value);
        return this;
    }

    public ReportSelection deviceFaultNumLike(String... value) {
        addLike(ReportColumns.DEVICE_FAULT_NUM, value);
        return this;
    }

    public ReportSelection deviceFaultNumContains(String... value) {
        addContains(ReportColumns.DEVICE_FAULT_NUM, value);
        return this;
    }

    public ReportSelection deviceFaultNumStartsWith(String... value) {
        addStartsWith(ReportColumns.DEVICE_FAULT_NUM, value);
        return this;
    }

    public ReportSelection deviceFaultNumEndsWith(String... value) {
        addEndsWith(ReportColumns.DEVICE_FAULT_NUM, value);
        return this;
    }

    public ReportSelection orderByDeviceFaultNum(boolean desc) {
        orderBy(ReportColumns.DEVICE_FAULT_NUM, desc);
        return this;
    }

    public ReportSelection orderByDeviceFaultNum() {
        orderBy(ReportColumns.DEVICE_FAULT_NUM, false);
        return this;
    }

    public ReportSelection deviceFaultPer(String... value) {
        addEquals(ReportColumns.DEVICE_FAULT_PER, value);
        return this;
    }

    public ReportSelection deviceFaultPerNot(String... value) {
        addNotEquals(ReportColumns.DEVICE_FAULT_PER, value);
        return this;
    }

    public ReportSelection deviceFaultPerLike(String... value) {
        addLike(ReportColumns.DEVICE_FAULT_PER, value);
        return this;
    }

    public ReportSelection deviceFaultPerContains(String... value) {
        addContains(ReportColumns.DEVICE_FAULT_PER, value);
        return this;
    }

    public ReportSelection deviceFaultPerStartsWith(String... value) {
        addStartsWith(ReportColumns.DEVICE_FAULT_PER, value);
        return this;
    }

    public ReportSelection deviceFaultPerEndsWith(String... value) {
        addEndsWith(ReportColumns.DEVICE_FAULT_PER, value);
        return this;
    }

    public ReportSelection orderByDeviceFaultPer(boolean desc) {
        orderBy(ReportColumns.DEVICE_FAULT_PER, desc);
        return this;
    }

    public ReportSelection orderByDeviceFaultPer() {
        orderBy(ReportColumns.DEVICE_FAULT_PER, false);
        return this;
    }

    public ReportSelection notDeviceFaultNum(String... value) {
        addEquals(ReportColumns.NOT_DEVICE_FAULT_NUM, value);
        return this;
    }

    public ReportSelection notDeviceFaultNumNot(String... value) {
        addNotEquals(ReportColumns.NOT_DEVICE_FAULT_NUM, value);
        return this;
    }

    public ReportSelection notDeviceFaultNumLike(String... value) {
        addLike(ReportColumns.NOT_DEVICE_FAULT_NUM, value);
        return this;
    }

    public ReportSelection notDeviceFaultNumContains(String... value) {
        addContains(ReportColumns.NOT_DEVICE_FAULT_NUM, value);
        return this;
    }

    public ReportSelection notDeviceFaultNumStartsWith(String... value) {
        addStartsWith(ReportColumns.NOT_DEVICE_FAULT_NUM, value);
        return this;
    }

    public ReportSelection notDeviceFaultNumEndsWith(String... value) {
        addEndsWith(ReportColumns.NOT_DEVICE_FAULT_NUM, value);
        return this;
    }

    public ReportSelection orderByNotDeviceFaultNum(boolean desc) {
        orderBy(ReportColumns.NOT_DEVICE_FAULT_NUM, desc);
        return this;
    }

    public ReportSelection orderByNotDeviceFaultNum() {
        orderBy(ReportColumns.NOT_DEVICE_FAULT_NUM, false);
        return this;
    }

    public ReportSelection notDeviceFaultPer(String... value) {
        addEquals(ReportColumns.NOT_DEVICE_FAULT_PER, value);
        return this;
    }

    public ReportSelection notDeviceFaultPerNot(String... value) {
        addNotEquals(ReportColumns.NOT_DEVICE_FAULT_PER, value);
        return this;
    }

    public ReportSelection notDeviceFaultPerLike(String... value) {
        addLike(ReportColumns.NOT_DEVICE_FAULT_PER, value);
        return this;
    }

    public ReportSelection notDeviceFaultPerContains(String... value) {
        addContains(ReportColumns.NOT_DEVICE_FAULT_PER, value);
        return this;
    }

    public ReportSelection notDeviceFaultPerStartsWith(String... value) {
        addStartsWith(ReportColumns.NOT_DEVICE_FAULT_PER, value);
        return this;
    }

    public ReportSelection notDeviceFaultPerEndsWith(String... value) {
        addEndsWith(ReportColumns.NOT_DEVICE_FAULT_PER, value);
        return this;
    }

    public ReportSelection orderByNotDeviceFaultPer(boolean desc) {
        orderBy(ReportColumns.NOT_DEVICE_FAULT_PER, desc);
        return this;
    }

    public ReportSelection orderByNotDeviceFaultPer() {
        orderBy(ReportColumns.NOT_DEVICE_FAULT_PER, false);
        return this;
    }
}
