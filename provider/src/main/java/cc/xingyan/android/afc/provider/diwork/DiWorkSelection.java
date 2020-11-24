package cc.xingyan.android.afc.provider.diwork;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code di_work} table.
 */
public class DiWorkSelection extends AbstractSelection<DiWorkSelection> {
    @Override
    protected Uri baseUri() {
        return DiWorkColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code DiWorkCursor} object, which is positioned before the first entry, or null.
     */
    public DiWorkCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new DiWorkCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public DiWorkCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code DiWorkCursor} object, which is positioned before the first entry, or null.
     */
    public DiWorkCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new DiWorkCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public DiWorkCursor query(Context context) {
        return query(context, null);
    }


    public DiWorkSelection id(long... value) {
        addEquals("di_work." + DiWorkColumns._ID, toObjectArray(value));
        return this;
    }

    public DiWorkSelection idNot(long... value) {
        addNotEquals("di_work." + DiWorkColumns._ID, toObjectArray(value));
        return this;
    }

    public DiWorkSelection orderById(boolean desc) {
        orderBy("di_work." + DiWorkColumns._ID, desc);
        return this;
    }

    public DiWorkSelection orderById() {
        return orderById(false);
    }

    public DiWorkSelection workAreaId(String... value) {
        addEquals(DiWorkColumns.WORK_AREA_ID, value);
        return this;
    }

    public DiWorkSelection workAreaIdNot(String... value) {
        addNotEquals(DiWorkColumns.WORK_AREA_ID, value);
        return this;
    }

    public DiWorkSelection workAreaIdLike(String... value) {
        addLike(DiWorkColumns.WORK_AREA_ID, value);
        return this;
    }

    public DiWorkSelection workAreaIdContains(String... value) {
        addContains(DiWorkColumns.WORK_AREA_ID, value);
        return this;
    }

    public DiWorkSelection workAreaIdStartsWith(String... value) {
        addStartsWith(DiWorkColumns.WORK_AREA_ID, value);
        return this;
    }

    public DiWorkSelection workAreaIdEndsWith(String... value) {
        addEndsWith(DiWorkColumns.WORK_AREA_ID, value);
        return this;
    }

    public DiWorkSelection orderByWorkAreaId(boolean desc) {
        orderBy(DiWorkColumns.WORK_AREA_ID, desc);
        return this;
    }

    public DiWorkSelection orderByWorkAreaId() {
        orderBy(DiWorkColumns.WORK_AREA_ID, false);
        return this;
    }

    public DiWorkSelection workAreaDescription(String... value) {
        addEquals(DiWorkColumns.WORK_AREA_DESCRIPTION, value);
        return this;
    }

    public DiWorkSelection workAreaDescriptionNot(String... value) {
        addNotEquals(DiWorkColumns.WORK_AREA_DESCRIPTION, value);
        return this;
    }

    public DiWorkSelection workAreaDescriptionLike(String... value) {
        addLike(DiWorkColumns.WORK_AREA_DESCRIPTION, value);
        return this;
    }

    public DiWorkSelection workAreaDescriptionContains(String... value) {
        addContains(DiWorkColumns.WORK_AREA_DESCRIPTION, value);
        return this;
    }

    public DiWorkSelection workAreaDescriptionStartsWith(String... value) {
        addStartsWith(DiWorkColumns.WORK_AREA_DESCRIPTION, value);
        return this;
    }

    public DiWorkSelection workAreaDescriptionEndsWith(String... value) {
        addEndsWith(DiWorkColumns.WORK_AREA_DESCRIPTION, value);
        return this;
    }

    public DiWorkSelection orderByWorkAreaDescription(boolean desc) {
        orderBy(DiWorkColumns.WORK_AREA_DESCRIPTION, desc);
        return this;
    }

    public DiWorkSelection orderByWorkAreaDescription() {
        orderBy(DiWorkColumns.WORK_AREA_DESCRIPTION, false);
        return this;
    }

    public DiWorkSelection guid(String... value) {
        addEquals(DiWorkColumns.GUID, value);
        return this;
    }

    public DiWorkSelection guidNot(String... value) {
        addNotEquals(DiWorkColumns.GUID, value);
        return this;
    }

    public DiWorkSelection guidLike(String... value) {
        addLike(DiWorkColumns.GUID, value);
        return this;
    }

    public DiWorkSelection guidContains(String... value) {
        addContains(DiWorkColumns.GUID, value);
        return this;
    }

    public DiWorkSelection guidStartsWith(String... value) {
        addStartsWith(DiWorkColumns.GUID, value);
        return this;
    }

    public DiWorkSelection guidEndsWith(String... value) {
        addEndsWith(DiWorkColumns.GUID, value);
        return this;
    }

    public DiWorkSelection orderByGuid(boolean desc) {
        orderBy(DiWorkColumns.GUID, desc);
        return this;
    }

    public DiWorkSelection orderByGuid() {
        orderBy(DiWorkColumns.GUID, false);
        return this;
    }

    public DiWorkSelection workId(String... value) {
        addEquals(DiWorkColumns.WORK_ID, value);
        return this;
    }

    public DiWorkSelection workIdNot(String... value) {
        addNotEquals(DiWorkColumns.WORK_ID, value);
        return this;
    }

    public DiWorkSelection workIdLike(String... value) {
        addLike(DiWorkColumns.WORK_ID, value);
        return this;
    }

    public DiWorkSelection workIdContains(String... value) {
        addContains(DiWorkColumns.WORK_ID, value);
        return this;
    }

    public DiWorkSelection workIdStartsWith(String... value) {
        addStartsWith(DiWorkColumns.WORK_ID, value);
        return this;
    }

    public DiWorkSelection workIdEndsWith(String... value) {
        addEndsWith(DiWorkColumns.WORK_ID, value);
        return this;
    }

    public DiWorkSelection orderByWorkId(boolean desc) {
        orderBy(DiWorkColumns.WORK_ID, desc);
        return this;
    }

    public DiWorkSelection orderByWorkId() {
        orderBy(DiWorkColumns.WORK_ID, false);
        return this;
    }

    public DiWorkSelection date(Long... value) {
        addEquals(DiWorkColumns.DATE, value);
        return this;
    }

    public DiWorkSelection dateNot(Long... value) {
        addNotEquals(DiWorkColumns.DATE, value);
        return this;
    }

    public DiWorkSelection dateGt(long value) {
        addGreaterThan(DiWorkColumns.DATE, value);
        return this;
    }

    public DiWorkSelection dateGtEq(long value) {
        addGreaterThanOrEquals(DiWorkColumns.DATE, value);
        return this;
    }

    public DiWorkSelection dateLt(long value) {
        addLessThan(DiWorkColumns.DATE, value);
        return this;
    }

    public DiWorkSelection dateLtEq(long value) {
        addLessThanOrEquals(DiWorkColumns.DATE, value);
        return this;
    }

    public DiWorkSelection orderByDate(boolean desc) {
        orderBy(DiWorkColumns.DATE, desc);
        return this;
    }

    public DiWorkSelection orderByDate() {
        orderBy(DiWorkColumns.DATE, false);
        return this;
    }

    public DiWorkSelection startTime(Long... value) {
        addEquals(DiWorkColumns.START_TIME, value);
        return this;
    }

    public DiWorkSelection startTimeNot(Long... value) {
        addNotEquals(DiWorkColumns.START_TIME, value);
        return this;
    }

    public DiWorkSelection startTimeGt(long value) {
        addGreaterThan(DiWorkColumns.START_TIME, value);
        return this;
    }

    public DiWorkSelection startTimeGtEq(long value) {
        addGreaterThanOrEquals(DiWorkColumns.START_TIME, value);
        return this;
    }

    public DiWorkSelection startTimeLt(long value) {
        addLessThan(DiWorkColumns.START_TIME, value);
        return this;
    }

    public DiWorkSelection startTimeLtEq(long value) {
        addLessThanOrEquals(DiWorkColumns.START_TIME, value);
        return this;
    }

    public DiWorkSelection orderByStartTime(boolean desc) {
        orderBy(DiWorkColumns.START_TIME, desc);
        return this;
    }

    public DiWorkSelection orderByStartTime() {
        orderBy(DiWorkColumns.START_TIME, false);
        return this;
    }

    public DiWorkSelection endTime(Long... value) {
        addEquals(DiWorkColumns.END_TIME, value);
        return this;
    }

    public DiWorkSelection endTimeNot(Long... value) {
        addNotEquals(DiWorkColumns.END_TIME, value);
        return this;
    }

    public DiWorkSelection endTimeGt(long value) {
        addGreaterThan(DiWorkColumns.END_TIME, value);
        return this;
    }

    public DiWorkSelection endTimeGtEq(long value) {
        addGreaterThanOrEquals(DiWorkColumns.END_TIME, value);
        return this;
    }

    public DiWorkSelection endTimeLt(long value) {
        addLessThan(DiWorkColumns.END_TIME, value);
        return this;
    }

    public DiWorkSelection endTimeLtEq(long value) {
        addLessThanOrEquals(DiWorkColumns.END_TIME, value);
        return this;
    }

    public DiWorkSelection orderByEndTime(boolean desc) {
        orderBy(DiWorkColumns.END_TIME, desc);
        return this;
    }

    public DiWorkSelection orderByEndTime() {
        orderBy(DiWorkColumns.END_TIME, false);
        return this;
    }

    public DiWorkSelection completeTime(Long... value) {
        addEquals(DiWorkColumns.COMPLETE_TIME, value);
        return this;
    }

    public DiWorkSelection completeTimeNot(Long... value) {
        addNotEquals(DiWorkColumns.COMPLETE_TIME, value);
        return this;
    }

    public DiWorkSelection completeTimeGt(long value) {
        addGreaterThan(DiWorkColumns.COMPLETE_TIME, value);
        return this;
    }

    public DiWorkSelection completeTimeGtEq(long value) {
        addGreaterThanOrEquals(DiWorkColumns.COMPLETE_TIME, value);
        return this;
    }

    public DiWorkSelection completeTimeLt(long value) {
        addLessThan(DiWorkColumns.COMPLETE_TIME, value);
        return this;
    }

    public DiWorkSelection completeTimeLtEq(long value) {
        addLessThanOrEquals(DiWorkColumns.COMPLETE_TIME, value);
        return this;
    }

    public DiWorkSelection orderByCompleteTime(boolean desc) {
        orderBy(DiWorkColumns.COMPLETE_TIME, desc);
        return this;
    }

    public DiWorkSelection orderByCompleteTime() {
        orderBy(DiWorkColumns.COMPLETE_TIME, false);
        return this;
    }

    public DiWorkSelection lastModified(Long... value) {
        addEquals(DiWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public DiWorkSelection lastModifiedNot(Long... value) {
        addNotEquals(DiWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public DiWorkSelection lastModifiedGt(long value) {
        addGreaterThan(DiWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public DiWorkSelection lastModifiedGtEq(long value) {
        addGreaterThanOrEquals(DiWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public DiWorkSelection lastModifiedLt(long value) {
        addLessThan(DiWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public DiWorkSelection lastModifiedLtEq(long value) {
        addLessThanOrEquals(DiWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public DiWorkSelection orderByLastModified(boolean desc) {
        orderBy(DiWorkColumns.LAST_MODIFIED, desc);
        return this;
    }

    public DiWorkSelection orderByLastModified() {
        orderBy(DiWorkColumns.LAST_MODIFIED, false);
        return this;
    }

    public DiWorkSelection status(DiWorkStatus... value) {
        addEquals(DiWorkColumns.STATUS, value);
        return this;
    }

    public DiWorkSelection statusNot(DiWorkStatus... value) {
        addNotEquals(DiWorkColumns.STATUS, value);
        return this;
    }


    public DiWorkSelection orderByStatus(boolean desc) {
        orderBy(DiWorkColumns.STATUS, desc);
        return this;
    }

    public DiWorkSelection orderByStatus() {
        orderBy(DiWorkColumns.STATUS, false);
        return this;
    }

    public DiWorkSelection uploadPending(Boolean value) {
        addEquals(DiWorkColumns.UPLOAD_PENDING, toObjectArray(value));
        return this;
    }

    public DiWorkSelection orderByUploadPending(boolean desc) {
        orderBy(DiWorkColumns.UPLOAD_PENDING, desc);
        return this;
    }

    public DiWorkSelection orderByUploadPending() {
        orderBy(DiWorkColumns.UPLOAD_PENDING, false);
        return this;
    }

    public DiWorkSelection operator(String... value) {
        addEquals(DiWorkColumns.OPERATOR, value);
        return this;
    }

    public DiWorkSelection operatorNot(String... value) {
        addNotEquals(DiWorkColumns.OPERATOR, value);
        return this;
    }

    public DiWorkSelection operatorLike(String... value) {
        addLike(DiWorkColumns.OPERATOR, value);
        return this;
    }

    public DiWorkSelection operatorContains(String... value) {
        addContains(DiWorkColumns.OPERATOR, value);
        return this;
    }

    public DiWorkSelection operatorStartsWith(String... value) {
        addStartsWith(DiWorkColumns.OPERATOR, value);
        return this;
    }

    public DiWorkSelection operatorEndsWith(String... value) {
        addEndsWith(DiWorkColumns.OPERATOR, value);
        return this;
    }

    public DiWorkSelection orderByOperator(boolean desc) {
        orderBy(DiWorkColumns.OPERATOR, desc);
        return this;
    }

    public DiWorkSelection orderByOperator() {
        orderBy(DiWorkColumns.OPERATOR, false);
        return this;
    }
}
