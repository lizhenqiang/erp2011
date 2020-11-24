package cc.xingyan.android.afc.provider.pmwork;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code pm_work} table.
 */
public class PmWorkSelection extends AbstractSelection<PmWorkSelection> {
    @Override
    protected Uri baseUri() {
        return PmWorkColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PmWorkCursor} object, which is positioned before the first entry, or null.
     */
    public PmWorkCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PmWorkCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public PmWorkCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PmWorkCursor} object, which is positioned before the first entry, or null.
     */
    public PmWorkCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PmWorkCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public PmWorkCursor query(Context context) {
        return query(context, null);
    }


    public PmWorkSelection id(long... value) {
        addEquals("pm_work." + PmWorkColumns._ID, toObjectArray(value));
        return this;
    }

    public PmWorkSelection idNot(long... value) {
        addNotEquals("pm_work." + PmWorkColumns._ID, toObjectArray(value));
        return this;
    }

    public PmWorkSelection orderById(boolean desc) {
        orderBy("pm_work." + PmWorkColumns._ID, desc);
        return this;
    }

    public PmWorkSelection orderById() {
        return orderById(false);
    }

    public PmWorkSelection userId(String... value) {
        addEquals(PmWorkColumns.USER_ID, value);
        return this;
    }

    public PmWorkSelection userIdNot(String... value) {
        addNotEquals(PmWorkColumns.USER_ID, value);
        return this;
    }

    public PmWorkSelection userIdLike(String... value) {
        addLike(PmWorkColumns.USER_ID, value);
        return this;
    }

    public PmWorkSelection userIdContains(String... value) {
        addContains(PmWorkColumns.USER_ID, value);
        return this;
    }

    public PmWorkSelection userIdStartsWith(String... value) {
        addStartsWith(PmWorkColumns.USER_ID, value);
        return this;
    }

    public PmWorkSelection userIdEndsWith(String... value) {
        addEndsWith(PmWorkColumns.USER_ID, value);
        return this;
    }

    public PmWorkSelection orderByUserId(boolean desc) {
        orderBy(PmWorkColumns.USER_ID, desc);
        return this;
    }

    public PmWorkSelection orderByUserId() {
        orderBy(PmWorkColumns.USER_ID, false);
        return this;
    }

    public PmWorkSelection guid(String... value) {
        addEquals(PmWorkColumns.GUID, value);
        return this;
    }

    public PmWorkSelection guidNot(String... value) {
        addNotEquals(PmWorkColumns.GUID, value);
        return this;
    }

    public PmWorkSelection guidLike(String... value) {
        addLike(PmWorkColumns.GUID, value);
        return this;
    }

    public PmWorkSelection guidContains(String... value) {
        addContains(PmWorkColumns.GUID, value);
        return this;
    }

    public PmWorkSelection guidStartsWith(String... value) {
        addStartsWith(PmWorkColumns.GUID, value);
        return this;
    }

    public PmWorkSelection guidEndsWith(String... value) {
        addEndsWith(PmWorkColumns.GUID, value);
        return this;
    }

    public PmWorkSelection orderByGuid(boolean desc) {
        orderBy(PmWorkColumns.GUID, desc);
        return this;
    }

    public PmWorkSelection orderByGuid() {
        orderBy(PmWorkColumns.GUID, false);
        return this;
    }

    public PmWorkSelection jobId(String... value) {
        addEquals(PmWorkColumns.JOB_ID, value);
        return this;
    }

    public PmWorkSelection jobIdNot(String... value) {
        addNotEquals(PmWorkColumns.JOB_ID, value);
        return this;
    }

    public PmWorkSelection jobIdLike(String... value) {
        addLike(PmWorkColumns.JOB_ID, value);
        return this;
    }

    public PmWorkSelection jobIdContains(String... value) {
        addContains(PmWorkColumns.JOB_ID, value);
        return this;
    }

    public PmWorkSelection jobIdStartsWith(String... value) {
        addStartsWith(PmWorkColumns.JOB_ID, value);
        return this;
    }

    public PmWorkSelection jobIdEndsWith(String... value) {
        addEndsWith(PmWorkColumns.JOB_ID, value);
        return this;
    }

    public PmWorkSelection orderByJobId(boolean desc) {
        orderBy(PmWorkColumns.JOB_ID, desc);
        return this;
    }

    public PmWorkSelection orderByJobId() {
        orderBy(PmWorkColumns.JOB_ID, false);
        return this;
    }

    public PmWorkSelection jobDescription(String... value) {
        addEquals(PmWorkColumns.JOB_DESCRIPTION, value);
        return this;
    }

    public PmWorkSelection jobDescriptionNot(String... value) {
        addNotEquals(PmWorkColumns.JOB_DESCRIPTION, value);
        return this;
    }

    public PmWorkSelection jobDescriptionLike(String... value) {
        addLike(PmWorkColumns.JOB_DESCRIPTION, value);
        return this;
    }

    public PmWorkSelection jobDescriptionContains(String... value) {
        addContains(PmWorkColumns.JOB_DESCRIPTION, value);
        return this;
    }

    public PmWorkSelection jobDescriptionStartsWith(String... value) {
        addStartsWith(PmWorkColumns.JOB_DESCRIPTION, value);
        return this;
    }

    public PmWorkSelection jobDescriptionEndsWith(String... value) {
        addEndsWith(PmWorkColumns.JOB_DESCRIPTION, value);
        return this;
    }

    public PmWorkSelection orderByJobDescription(boolean desc) {
        orderBy(PmWorkColumns.JOB_DESCRIPTION, desc);
        return this;
    }

    public PmWorkSelection orderByJobDescription() {
        orderBy(PmWorkColumns.JOB_DESCRIPTION, false);
        return this;
    }

    public PmWorkSelection workId(String... value) {
        addEquals(PmWorkColumns.WORK_ID, value);
        return this;
    }

    public PmWorkSelection workIdNot(String... value) {
        addNotEquals(PmWorkColumns.WORK_ID, value);
        return this;
    }

    public PmWorkSelection workIdLike(String... value) {
        addLike(PmWorkColumns.WORK_ID, value);
        return this;
    }

    public PmWorkSelection workIdContains(String... value) {
        addContains(PmWorkColumns.WORK_ID, value);
        return this;
    }

    public PmWorkSelection workIdStartsWith(String... value) {
        addStartsWith(PmWorkColumns.WORK_ID, value);
        return this;
    }

    public PmWorkSelection workIdEndsWith(String... value) {
        addEndsWith(PmWorkColumns.WORK_ID, value);
        return this;
    }

    public PmWorkSelection orderByWorkId(boolean desc) {
        orderBy(PmWorkColumns.WORK_ID, desc);
        return this;
    }

    public PmWorkSelection orderByWorkId() {
        orderBy(PmWorkColumns.WORK_ID, false);
        return this;
    }

    public PmWorkSelection workKind(String... value) {
        addEquals(PmWorkColumns.WORK_KIND, value);
        return this;
    }

    public PmWorkSelection workKindNot(String... value) {
        addNotEquals(PmWorkColumns.WORK_KIND, value);
        return this;
    }

    public PmWorkSelection workKindLike(String... value) {
        addLike(PmWorkColumns.WORK_KIND, value);
        return this;
    }

    public PmWorkSelection workKindContains(String... value) {
        addContains(PmWorkColumns.WORK_KIND, value);
        return this;
    }

    public PmWorkSelection workKindStartsWith(String... value) {
        addStartsWith(PmWorkColumns.WORK_KIND, value);
        return this;
    }

    public PmWorkSelection workKindEndsWith(String... value) {
        addEndsWith(PmWorkColumns.WORK_KIND, value);
        return this;
    }

    public PmWorkSelection orderByWorkKind(boolean desc) {
        orderBy(PmWorkColumns.WORK_KIND, desc);
        return this;
    }

    public PmWorkSelection orderByWorkKind() {
        orderBy(PmWorkColumns.WORK_KIND, false);
        return this;
    }

    public PmWorkSelection deviceCode(String... value) {
        addEquals(PmWorkColumns.DEVICE_CODE, value);
        return this;
    }

    public PmWorkSelection deviceCodeNot(String... value) {
        addNotEquals(PmWorkColumns.DEVICE_CODE, value);
        return this;
    }

    public PmWorkSelection deviceCodeLike(String... value) {
        addLike(PmWorkColumns.DEVICE_CODE, value);
        return this;
    }

    public PmWorkSelection deviceCodeContains(String... value) {
        addContains(PmWorkColumns.DEVICE_CODE, value);
        return this;
    }

    public PmWorkSelection deviceCodeStartsWith(String... value) {
        addStartsWith(PmWorkColumns.DEVICE_CODE, value);
        return this;
    }

    public PmWorkSelection deviceCodeEndsWith(String... value) {
        addEndsWith(PmWorkColumns.DEVICE_CODE, value);
        return this;
    }

    public PmWorkSelection orderByDeviceCode(boolean desc) {
        orderBy(PmWorkColumns.DEVICE_CODE, desc);
        return this;
    }

    public PmWorkSelection orderByDeviceCode() {
        orderBy(PmWorkColumns.DEVICE_CODE, false);
        return this;
    }

    public PmWorkSelection deviceDescription(String... value) {
        addEquals(PmWorkColumns.DEVICE_DESCRIPTION, value);
        return this;
    }

    public PmWorkSelection deviceDescriptionNot(String... value) {
        addNotEquals(PmWorkColumns.DEVICE_DESCRIPTION, value);
        return this;
    }

    public PmWorkSelection deviceDescriptionLike(String... value) {
        addLike(PmWorkColumns.DEVICE_DESCRIPTION, value);
        return this;
    }

    public PmWorkSelection deviceDescriptionContains(String... value) {
        addContains(PmWorkColumns.DEVICE_DESCRIPTION, value);
        return this;
    }

    public PmWorkSelection deviceDescriptionStartsWith(String... value) {
        addStartsWith(PmWorkColumns.DEVICE_DESCRIPTION, value);
        return this;
    }

    public PmWorkSelection deviceDescriptionEndsWith(String... value) {
        addEndsWith(PmWorkColumns.DEVICE_DESCRIPTION, value);
        return this;
    }

    public PmWorkSelection orderByDeviceDescription(boolean desc) {
        orderBy(PmWorkColumns.DEVICE_DESCRIPTION, desc);
        return this;
    }

    public PmWorkSelection orderByDeviceDescription() {
        orderBy(PmWorkColumns.DEVICE_DESCRIPTION, false);
        return this;
    }

    public PmWorkSelection executorId(String... value) {
        addEquals(PmWorkColumns.EXECUTOR_ID, value);
        return this;
    }

    public PmWorkSelection executorIdNot(String... value) {
        addNotEquals(PmWorkColumns.EXECUTOR_ID, value);
        return this;
    }

    public PmWorkSelection executorIdLike(String... value) {
        addLike(PmWorkColumns.EXECUTOR_ID, value);
        return this;
    }

    public PmWorkSelection executorIdContains(String... value) {
        addContains(PmWorkColumns.EXECUTOR_ID, value);
        return this;
    }

    public PmWorkSelection executorIdStartsWith(String... value) {
        addStartsWith(PmWorkColumns.EXECUTOR_ID, value);
        return this;
    }

    public PmWorkSelection executorIdEndsWith(String... value) {
        addEndsWith(PmWorkColumns.EXECUTOR_ID, value);
        return this;
    }

    public PmWorkSelection orderByExecutorId(boolean desc) {
        orderBy(PmWorkColumns.EXECUTOR_ID, desc);
        return this;
    }

    public PmWorkSelection orderByExecutorId() {
        orderBy(PmWorkColumns.EXECUTOR_ID, false);
        return this;
    }

    public PmWorkSelection executorDescription(String... value) {
        addEquals(PmWorkColumns.EXECUTOR_DESCRIPTION, value);
        return this;
    }

    public PmWorkSelection executorDescriptionNot(String... value) {
        addNotEquals(PmWorkColumns.EXECUTOR_DESCRIPTION, value);
        return this;
    }

    public PmWorkSelection executorDescriptionLike(String... value) {
        addLike(PmWorkColumns.EXECUTOR_DESCRIPTION, value);
        return this;
    }

    public PmWorkSelection executorDescriptionContains(String... value) {
        addContains(PmWorkColumns.EXECUTOR_DESCRIPTION, value);
        return this;
    }

    public PmWorkSelection executorDescriptionStartsWith(String... value) {
        addStartsWith(PmWorkColumns.EXECUTOR_DESCRIPTION, value);
        return this;
    }

    public PmWorkSelection executorDescriptionEndsWith(String... value) {
        addEndsWith(PmWorkColumns.EXECUTOR_DESCRIPTION, value);
        return this;
    }

    public PmWorkSelection orderByExecutorDescription(boolean desc) {
        orderBy(PmWorkColumns.EXECUTOR_DESCRIPTION, desc);
        return this;
    }

    public PmWorkSelection orderByExecutorDescription() {
        orderBy(PmWorkColumns.EXECUTOR_DESCRIPTION, false);
        return this;
    }

    public PmWorkSelection startTime(Long... value) {
        addEquals(PmWorkColumns.START_TIME, value);
        return this;
    }

    public PmWorkSelection startTimeNot(Long... value) {
        addNotEquals(PmWorkColumns.START_TIME, value);
        return this;
    }

    public PmWorkSelection startTimeGt(long value) {
        addGreaterThan(PmWorkColumns.START_TIME, value);
        return this;
    }

    public PmWorkSelection startTimeGtEq(long value) {
        addGreaterThanOrEquals(PmWorkColumns.START_TIME, value);
        return this;
    }

    public PmWorkSelection startTimeLt(long value) {
        addLessThan(PmWorkColumns.START_TIME, value);
        return this;
    }

    public PmWorkSelection startTimeLtEq(long value) {
        addLessThanOrEquals(PmWorkColumns.START_TIME, value);
        return this;
    }

    public PmWorkSelection orderByStartTime(boolean desc) {
        orderBy(PmWorkColumns.START_TIME, desc);
        return this;
    }

    public PmWorkSelection orderByStartTime() {
        orderBy(PmWorkColumns.START_TIME, false);
        return this;
    }

    public PmWorkSelection endTime(Long... value) {
        addEquals(PmWorkColumns.END_TIME, value);
        return this;
    }

    public PmWorkSelection endTimeNot(Long... value) {
        addNotEquals(PmWorkColumns.END_TIME, value);
        return this;
    }

    public PmWorkSelection endTimeGt(long value) {
        addGreaterThan(PmWorkColumns.END_TIME, value);
        return this;
    }

    public PmWorkSelection endTimeGtEq(long value) {
        addGreaterThanOrEquals(PmWorkColumns.END_TIME, value);
        return this;
    }

    public PmWorkSelection endTimeLt(long value) {
        addLessThan(PmWorkColumns.END_TIME, value);
        return this;
    }

    public PmWorkSelection endTimeLtEq(long value) {
        addLessThanOrEquals(PmWorkColumns.END_TIME, value);
        return this;
    }

    public PmWorkSelection orderByEndTime(boolean desc) {
        orderBy(PmWorkColumns.END_TIME, desc);
        return this;
    }

    public PmWorkSelection orderByEndTime() {
        orderBy(PmWorkColumns.END_TIME, false);
        return this;
    }

    public PmWorkSelection completeTime(Long... value) {
        addEquals(PmWorkColumns.COMPLETE_TIME, value);
        return this;
    }

    public PmWorkSelection completeTimeNot(Long... value) {
        addNotEquals(PmWorkColumns.COMPLETE_TIME, value);
        return this;
    }

    public PmWorkSelection completeTimeGt(long value) {
        addGreaterThan(PmWorkColumns.COMPLETE_TIME, value);
        return this;
    }

    public PmWorkSelection completeTimeGtEq(long value) {
        addGreaterThanOrEquals(PmWorkColumns.COMPLETE_TIME, value);
        return this;
    }

    public PmWorkSelection completeTimeLt(long value) {
        addLessThan(PmWorkColumns.COMPLETE_TIME, value);
        return this;
    }

    public PmWorkSelection completeTimeLtEq(long value) {
        addLessThanOrEquals(PmWorkColumns.COMPLETE_TIME, value);
        return this;
    }

    public PmWorkSelection orderByCompleteTime(boolean desc) {
        orderBy(PmWorkColumns.COMPLETE_TIME, desc);
        return this;
    }

    public PmWorkSelection orderByCompleteTime() {
        orderBy(PmWorkColumns.COMPLETE_TIME, false);
        return this;
    }

    public PmWorkSelection lastModified(Long... value) {
        addEquals(PmWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmWorkSelection lastModifiedNot(Long... value) {
        addNotEquals(PmWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmWorkSelection lastModifiedGt(long value) {
        addGreaterThan(PmWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmWorkSelection lastModifiedGtEq(long value) {
        addGreaterThanOrEquals(PmWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmWorkSelection lastModifiedLt(long value) {
        addLessThan(PmWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmWorkSelection lastModifiedLtEq(long value) {
        addLessThanOrEquals(PmWorkColumns.LAST_MODIFIED, value);
        return this;
    }

    public PmWorkSelection orderByLastModified(boolean desc) {
        orderBy(PmWorkColumns.LAST_MODIFIED, desc);
        return this;
    }

    public PmWorkSelection orderByLastModified() {
        orderBy(PmWorkColumns.LAST_MODIFIED, false);
        return this;
    }

    public PmWorkSelection status(PmWorkStatus... value) {
        addEquals(PmWorkColumns.STATUS, value);
        return this;
    }

    public PmWorkSelection statusNot(PmWorkStatus... value) {
        addNotEquals(PmWorkColumns.STATUS, value);
        return this;
    }


    public PmWorkSelection orderByStatus(boolean desc) {
        orderBy(PmWorkColumns.STATUS, desc);
        return this;
    }

    public PmWorkSelection orderByStatus() {
        orderBy(PmWorkColumns.STATUS, false);
        return this;
    }

    public PmWorkSelection uploadPending(Boolean value) {
        addEquals(PmWorkColumns.UPLOAD_PENDING, toObjectArray(value));
        return this;
    }

    public PmWorkSelection orderByUploadPending(boolean desc) {
        orderBy(PmWorkColumns.UPLOAD_PENDING, desc);
        return this;
    }

    public PmWorkSelection orderByUploadPending() {
        orderBy(PmWorkColumns.UPLOAD_PENDING, false);
        return this;
    }
}
