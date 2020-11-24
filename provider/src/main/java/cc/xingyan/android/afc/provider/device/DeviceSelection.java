package cc.xingyan.android.afc.provider.device;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code device} table.
 */
public class DeviceSelection extends AbstractSelection<DeviceSelection> {
    @Override
    protected Uri baseUri() {
        return DeviceColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code DeviceCursor} object, which is positioned before the first entry, or null.
     */
    public DeviceCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new DeviceCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public DeviceCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code DeviceCursor} object, which is positioned before the first entry, or null.
     */
    public DeviceCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new DeviceCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public DeviceCursor query(Context context) {
        return query(context, null);
    }


    public DeviceSelection id(long... value) {
        addEquals("device." + DeviceColumns._ID, toObjectArray(value));
        return this;
    }

    public DeviceSelection idNot(long... value) {
        addNotEquals("device." + DeviceColumns._ID, toObjectArray(value));
        return this;
    }

    public DeviceSelection orderById(boolean desc) {
        orderBy("device." + DeviceColumns._ID, desc);
        return this;
    }

    public DeviceSelection orderById() {
        return orderById(false);
    }

    public DeviceSelection code(String... value) {
        addEquals(DeviceColumns.CODE, value);
        return this;
    }

    public DeviceSelection codeNot(String... value) {
        addNotEquals(DeviceColumns.CODE, value);
        return this;
    }

    public DeviceSelection codeLike(String... value) {
        addLike(DeviceColumns.CODE, value);
        return this;
    }

    public DeviceSelection codeContains(String... value) {
        addContains(DeviceColumns.CODE, value);
        return this;
    }

    public DeviceSelection codeStartsWith(String... value) {
        addStartsWith(DeviceColumns.CODE, value);
        return this;
    }

    public DeviceSelection codeEndsWith(String... value) {
        addEndsWith(DeviceColumns.CODE, value);
        return this;
    }

    public DeviceSelection orderByCode(boolean desc) {
        orderBy(DeviceColumns.CODE, desc);
        return this;
    }

    public DeviceSelection orderByCode() {
        orderBy(DeviceColumns.CODE, false);
        return this;
    }

    public DeviceSelection name(String... value) {
        addEquals(DeviceColumns.NAME, value);
        return this;
    }

    public DeviceSelection nameNot(String... value) {
        addNotEquals(DeviceColumns.NAME, value);
        return this;
    }

    public DeviceSelection nameLike(String... value) {
        addLike(DeviceColumns.NAME, value);
        return this;
    }

    public DeviceSelection nameContains(String... value) {
        addContains(DeviceColumns.NAME, value);
        return this;
    }

    public DeviceSelection nameStartsWith(String... value) {
        addStartsWith(DeviceColumns.NAME, value);
        return this;
    }

    public DeviceSelection nameEndsWith(String... value) {
        addEndsWith(DeviceColumns.NAME, value);
        return this;
    }

    public DeviceSelection orderByName(boolean desc) {
        orderBy(DeviceColumns.NAME, desc);
        return this;
    }

    public DeviceSelection orderByName() {
        orderBy(DeviceColumns.NAME, false);
        return this;
    }

    public DeviceSelection type(String... value) {
        addEquals(DeviceColumns.TYPE, value);
        return this;
    }

    public DeviceSelection typeNot(String... value) {
        addNotEquals(DeviceColumns.TYPE, value);
        return this;
    }

    public DeviceSelection typeLike(String... value) {
        addLike(DeviceColumns.TYPE, value);
        return this;
    }

    public DeviceSelection typeContains(String... value) {
        addContains(DeviceColumns.TYPE, value);
        return this;
    }

    public DeviceSelection typeStartsWith(String... value) {
        addStartsWith(DeviceColumns.TYPE, value);
        return this;
    }

    public DeviceSelection typeEndsWith(String... value) {
        addEndsWith(DeviceColumns.TYPE, value);
        return this;
    }

    public DeviceSelection orderByType(boolean desc) {
        orderBy(DeviceColumns.TYPE, desc);
        return this;
    }

    public DeviceSelection orderByType() {
        orderBy(DeviceColumns.TYPE, false);
        return this;
    }

    public DeviceSelection location(String... value) {
        addEquals(DeviceColumns.LOCATION, value);
        return this;
    }

    public DeviceSelection locationNot(String... value) {
        addNotEquals(DeviceColumns.LOCATION, value);
        return this;
    }

    public DeviceSelection locationLike(String... value) {
        addLike(DeviceColumns.LOCATION, value);
        return this;
    }

    public DeviceSelection locationContains(String... value) {
        addContains(DeviceColumns.LOCATION, value);
        return this;
    }

    public DeviceSelection locationStartsWith(String... value) {
        addStartsWith(DeviceColumns.LOCATION, value);
        return this;
    }

    public DeviceSelection locationEndsWith(String... value) {
        addEndsWith(DeviceColumns.LOCATION, value);
        return this;
    }

    public DeviceSelection orderByLocation(boolean desc) {
        orderBy(DeviceColumns.LOCATION, desc);
        return this;
    }

    public DeviceSelection orderByLocation() {
        orderBy(DeviceColumns.LOCATION, false);
        return this;
    }
}
