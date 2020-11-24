package cc.xingyan.android.afc.provider.diworkitem;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code di_work_item} table.
 */
public class DiWorkItemSelection extends AbstractSelection<DiWorkItemSelection> {
    @Override
    protected Uri baseUri() {
        return DiWorkItemColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code DiWorkItemCursor} object, which is positioned before the first entry, or null.
     */
    public DiWorkItemCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new DiWorkItemCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public DiWorkItemCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code DiWorkItemCursor} object, which is positioned before the first entry, or null.
     */
    public DiWorkItemCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new DiWorkItemCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public DiWorkItemCursor query(Context context) {
        return query(context, null);
    }


    public DiWorkItemSelection id(long... value) {
        addEquals("di_work_item." + DiWorkItemColumns._ID, toObjectArray(value));
        return this;
    }

    public DiWorkItemSelection idNot(long... value) {
        addNotEquals("di_work_item." + DiWorkItemColumns._ID, toObjectArray(value));
        return this;
    }

    public DiWorkItemSelection orderById(boolean desc) {
        orderBy("di_work_item." + DiWorkItemColumns._ID, desc);
        return this;
    }

    public DiWorkItemSelection orderById() {
        return orderById(false);
    }

    public DiWorkItemSelection workGuid(String... value) {
        addEquals(DiWorkItemColumns.WORK_GUID, value);
        return this;
    }

    public DiWorkItemSelection workGuidNot(String... value) {
        addNotEquals(DiWorkItemColumns.WORK_GUID, value);
        return this;
    }

    public DiWorkItemSelection workGuidLike(String... value) {
        addLike(DiWorkItemColumns.WORK_GUID, value);
        return this;
    }

    public DiWorkItemSelection workGuidContains(String... value) {
        addContains(DiWorkItemColumns.WORK_GUID, value);
        return this;
    }

    public DiWorkItemSelection workGuidStartsWith(String... value) {
        addStartsWith(DiWorkItemColumns.WORK_GUID, value);
        return this;
    }

    public DiWorkItemSelection workGuidEndsWith(String... value) {
        addEndsWith(DiWorkItemColumns.WORK_GUID, value);
        return this;
    }

    public DiWorkItemSelection orderByWorkGuid(boolean desc) {
        orderBy(DiWorkItemColumns.WORK_GUID, desc);
        return this;
    }

    public DiWorkItemSelection orderByWorkGuid() {
        orderBy(DiWorkItemColumns.WORK_GUID, false);
        return this;
    }

    public DiWorkItemSelection stationId(String... value) {
        addEquals(DiWorkItemColumns.STATION_ID, value);
        return this;
    }

    public DiWorkItemSelection stationIdNot(String... value) {
        addNotEquals(DiWorkItemColumns.STATION_ID, value);
        return this;
    }

    public DiWorkItemSelection stationIdLike(String... value) {
        addLike(DiWorkItemColumns.STATION_ID, value);
        return this;
    }

    public DiWorkItemSelection stationIdContains(String... value) {
        addContains(DiWorkItemColumns.STATION_ID, value);
        return this;
    }

    public DiWorkItemSelection stationIdStartsWith(String... value) {
        addStartsWith(DiWorkItemColumns.STATION_ID, value);
        return this;
    }

    public DiWorkItemSelection stationIdEndsWith(String... value) {
        addEndsWith(DiWorkItemColumns.STATION_ID, value);
        return this;
    }

    public DiWorkItemSelection orderByStationId(boolean desc) {
        orderBy(DiWorkItemColumns.STATION_ID, desc);
        return this;
    }

    public DiWorkItemSelection orderByStationId() {
        orderBy(DiWorkItemColumns.STATION_ID, false);
        return this;
    }

    public DiWorkItemSelection stationDescription(String... value) {
        addEquals(DiWorkItemColumns.STATION_DESCRIPTION, value);
        return this;
    }

    public DiWorkItemSelection stationDescriptionNot(String... value) {
        addNotEquals(DiWorkItemColumns.STATION_DESCRIPTION, value);
        return this;
    }

    public DiWorkItemSelection stationDescriptionLike(String... value) {
        addLike(DiWorkItemColumns.STATION_DESCRIPTION, value);
        return this;
    }

    public DiWorkItemSelection stationDescriptionContains(String... value) {
        addContains(DiWorkItemColumns.STATION_DESCRIPTION, value);
        return this;
    }

    public DiWorkItemSelection stationDescriptionStartsWith(String... value) {
        addStartsWith(DiWorkItemColumns.STATION_DESCRIPTION, value);
        return this;
    }

    public DiWorkItemSelection stationDescriptionEndsWith(String... value) {
        addEndsWith(DiWorkItemColumns.STATION_DESCRIPTION, value);
        return this;
    }

    public DiWorkItemSelection orderByStationDescription(boolean desc) {
        orderBy(DiWorkItemColumns.STATION_DESCRIPTION, desc);
        return this;
    }

    public DiWorkItemSelection orderByStationDescription() {
        orderBy(DiWorkItemColumns.STATION_DESCRIPTION, false);
        return this;
    }

    public DiWorkItemSelection deviceId(String... value) {
        addEquals(DiWorkItemColumns.DEVICE_ID, value);
        return this;
    }

    public DiWorkItemSelection deviceIdNot(String... value) {
        addNotEquals(DiWorkItemColumns.DEVICE_ID, value);
        return this;
    }

    public DiWorkItemSelection deviceIdLike(String... value) {
        addLike(DiWorkItemColumns.DEVICE_ID, value);
        return this;
    }

    public DiWorkItemSelection deviceIdContains(String... value) {
        addContains(DiWorkItemColumns.DEVICE_ID, value);
        return this;
    }

    public DiWorkItemSelection deviceIdStartsWith(String... value) {
        addStartsWith(DiWorkItemColumns.DEVICE_ID, value);
        return this;
    }

    public DiWorkItemSelection deviceIdEndsWith(String... value) {
        addEndsWith(DiWorkItemColumns.DEVICE_ID, value);
        return this;
    }

    public DiWorkItemSelection orderByDeviceId(boolean desc) {
        orderBy(DiWorkItemColumns.DEVICE_ID, desc);
        return this;
    }

    public DiWorkItemSelection orderByDeviceId() {
        orderBy(DiWorkItemColumns.DEVICE_ID, false);
        return this;
    }

    public DiWorkItemSelection deviceDescription(String... value) {
        addEquals(DiWorkItemColumns.DEVICE_DESCRIPTION, value);
        return this;
    }

    public DiWorkItemSelection deviceDescriptionNot(String... value) {
        addNotEquals(DiWorkItemColumns.DEVICE_DESCRIPTION, value);
        return this;
    }

    public DiWorkItemSelection deviceDescriptionLike(String... value) {
        addLike(DiWorkItemColumns.DEVICE_DESCRIPTION, value);
        return this;
    }

    public DiWorkItemSelection deviceDescriptionContains(String... value) {
        addContains(DiWorkItemColumns.DEVICE_DESCRIPTION, value);
        return this;
    }

    public DiWorkItemSelection deviceDescriptionStartsWith(String... value) {
        addStartsWith(DiWorkItemColumns.DEVICE_DESCRIPTION, value);
        return this;
    }

    public DiWorkItemSelection deviceDescriptionEndsWith(String... value) {
        addEndsWith(DiWorkItemColumns.DEVICE_DESCRIPTION, value);
        return this;
    }

    public DiWorkItemSelection orderByDeviceDescription(boolean desc) {
        orderBy(DiWorkItemColumns.DEVICE_DESCRIPTION, desc);
        return this;
    }

    public DiWorkItemSelection orderByDeviceDescription() {
        orderBy(DiWorkItemColumns.DEVICE_DESCRIPTION, false);
        return this;
    }

    public DiWorkItemSelection deviceSystem(String... value) {
        addEquals(DiWorkItemColumns.DEVICE_SYSTEM, value);
        return this;
    }

    public DiWorkItemSelection deviceSystemNot(String... value) {
        addNotEquals(DiWorkItemColumns.DEVICE_SYSTEM, value);
        return this;
    }

    public DiWorkItemSelection deviceSystemLike(String... value) {
        addLike(DiWorkItemColumns.DEVICE_SYSTEM, value);
        return this;
    }

    public DiWorkItemSelection deviceSystemContains(String... value) {
        addContains(DiWorkItemColumns.DEVICE_SYSTEM, value);
        return this;
    }

    public DiWorkItemSelection deviceSystemStartsWith(String... value) {
        addStartsWith(DiWorkItemColumns.DEVICE_SYSTEM, value);
        return this;
    }

    public DiWorkItemSelection deviceSystemEndsWith(String... value) {
        addEndsWith(DiWorkItemColumns.DEVICE_SYSTEM, value);
        return this;
    }

    public DiWorkItemSelection orderByDeviceSystem(boolean desc) {
        orderBy(DiWorkItemColumns.DEVICE_SYSTEM, desc);
        return this;
    }

    public DiWorkItemSelection orderByDeviceSystem() {
        orderBy(DiWorkItemColumns.DEVICE_SYSTEM, false);
        return this;
    }

    public DiWorkItemSelection guid(String... value) {
        addEquals(DiWorkItemColumns.GUID, value);
        return this;
    }

    public DiWorkItemSelection guidNot(String... value) {
        addNotEquals(DiWorkItemColumns.GUID, value);
        return this;
    }

    public DiWorkItemSelection guidLike(String... value) {
        addLike(DiWorkItemColumns.GUID, value);
        return this;
    }

    public DiWorkItemSelection guidContains(String... value) {
        addContains(DiWorkItemColumns.GUID, value);
        return this;
    }

    public DiWorkItemSelection guidStartsWith(String... value) {
        addStartsWith(DiWorkItemColumns.GUID, value);
        return this;
    }

    public DiWorkItemSelection guidEndsWith(String... value) {
        addEndsWith(DiWorkItemColumns.GUID, value);
        return this;
    }

    public DiWorkItemSelection orderByGuid(boolean desc) {
        orderBy(DiWorkItemColumns.GUID, desc);
        return this;
    }

    public DiWorkItemSelection orderByGuid() {
        orderBy(DiWorkItemColumns.GUID, false);
        return this;
    }

    public DiWorkItemSelection resultOrdinal(Integer... value) {
        addEquals(DiWorkItemColumns.RESULT_ORDINAL, value);
        return this;
    }

    public DiWorkItemSelection resultOrdinalNot(Integer... value) {
        addNotEquals(DiWorkItemColumns.RESULT_ORDINAL, value);
        return this;
    }

    public DiWorkItemSelection resultOrdinalGt(int value) {
        addGreaterThan(DiWorkItemColumns.RESULT_ORDINAL, value);
        return this;
    }

    public DiWorkItemSelection resultOrdinalGtEq(int value) {
        addGreaterThanOrEquals(DiWorkItemColumns.RESULT_ORDINAL, value);
        return this;
    }

    public DiWorkItemSelection resultOrdinalLt(int value) {
        addLessThan(DiWorkItemColumns.RESULT_ORDINAL, value);
        return this;
    }

    public DiWorkItemSelection resultOrdinalLtEq(int value) {
        addLessThanOrEquals(DiWorkItemColumns.RESULT_ORDINAL, value);
        return this;
    }

    public DiWorkItemSelection orderByResultOrdinal(boolean desc) {
        orderBy(DiWorkItemColumns.RESULT_ORDINAL, desc);
        return this;
    }

    public DiWorkItemSelection orderByResultOrdinal() {
        orderBy(DiWorkItemColumns.RESULT_ORDINAL, false);
        return this;
    }

    public DiWorkItemSelection resultValue(String... value) {
        addEquals(DiWorkItemColumns.RESULT_VALUE, value);
        return this;
    }

    public DiWorkItemSelection resultValueNot(String... value) {
        addNotEquals(DiWorkItemColumns.RESULT_VALUE, value);
        return this;
    }

    public DiWorkItemSelection resultValueLike(String... value) {
        addLike(DiWorkItemColumns.RESULT_VALUE, value);
        return this;
    }

    public DiWorkItemSelection resultValueContains(String... value) {
        addContains(DiWorkItemColumns.RESULT_VALUE, value);
        return this;
    }

    public DiWorkItemSelection resultValueStartsWith(String... value) {
        addStartsWith(DiWorkItemColumns.RESULT_VALUE, value);
        return this;
    }

    public DiWorkItemSelection resultValueEndsWith(String... value) {
        addEndsWith(DiWorkItemColumns.RESULT_VALUE, value);
        return this;
    }

    public DiWorkItemSelection orderByResultValue(boolean desc) {
        orderBy(DiWorkItemColumns.RESULT_VALUE, desc);
        return this;
    }

    public DiWorkItemSelection orderByResultValue() {
        orderBy(DiWorkItemColumns.RESULT_VALUE, false);
        return this;
    }

    public DiWorkItemSelection lastModified(Long... value) {
        addEquals(DiWorkItemColumns.LAST_MODIFIED, value);
        return this;
    }

    public DiWorkItemSelection lastModifiedNot(Long... value) {
        addNotEquals(DiWorkItemColumns.LAST_MODIFIED, value);
        return this;
    }

    public DiWorkItemSelection lastModifiedGt(long value) {
        addGreaterThan(DiWorkItemColumns.LAST_MODIFIED, value);
        return this;
    }

    public DiWorkItemSelection lastModifiedGtEq(long value) {
        addGreaterThanOrEquals(DiWorkItemColumns.LAST_MODIFIED, value);
        return this;
    }

    public DiWorkItemSelection lastModifiedLt(long value) {
        addLessThan(DiWorkItemColumns.LAST_MODIFIED, value);
        return this;
    }

    public DiWorkItemSelection lastModifiedLtEq(long value) {
        addLessThanOrEquals(DiWorkItemColumns.LAST_MODIFIED, value);
        return this;
    }

    public DiWorkItemSelection orderByLastModified(boolean desc) {
        orderBy(DiWorkItemColumns.LAST_MODIFIED, desc);
        return this;
    }

    public DiWorkItemSelection orderByLastModified() {
        orderBy(DiWorkItemColumns.LAST_MODIFIED, false);
        return this;
    }
}
