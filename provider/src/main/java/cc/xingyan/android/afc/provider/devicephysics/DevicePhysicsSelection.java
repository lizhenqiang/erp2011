package cc.xingyan.android.afc.provider.devicephysics;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code device_physics} table.
 */
public class DevicePhysicsSelection extends AbstractSelection<DevicePhysicsSelection> {
    @Override
    protected Uri baseUri() {
        return DevicePhysicsColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code DevicePhysicsCursor} object, which is positioned before the first entry, or null.
     */
    public DevicePhysicsCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new DevicePhysicsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public DevicePhysicsCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code DevicePhysicsCursor} object, which is positioned before the first entry, or null.
     */
    public DevicePhysicsCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new DevicePhysicsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public DevicePhysicsCursor query(Context context) {
        return query(context, null);
    }


    public DevicePhysicsSelection id(long... value) {
        addEquals("device_physics." + DevicePhysicsColumns._ID, toObjectArray(value));
        return this;
    }

    public DevicePhysicsSelection idNot(long... value) {
        addNotEquals("device_physics." + DevicePhysicsColumns._ID, toObjectArray(value));
        return this;
    }

    public DevicePhysicsSelection orderById(boolean desc) {
        orderBy("device_physics." + DevicePhysicsColumns._ID, desc);
        return this;
    }

    public DevicePhysicsSelection orderById() {
        return orderById(false);
    }

    public DevicePhysicsSelection code(String... value) {
        addEquals(DevicePhysicsColumns.CODE, value);
        return this;
    }

    public DevicePhysicsSelection codeNot(String... value) {
        addNotEquals(DevicePhysicsColumns.CODE, value);
        return this;
    }

    public DevicePhysicsSelection codeLike(String... value) {
        addLike(DevicePhysicsColumns.CODE, value);
        return this;
    }

    public DevicePhysicsSelection codeContains(String... value) {
        addContains(DevicePhysicsColumns.CODE, value);
        return this;
    }

    public DevicePhysicsSelection codeStartsWith(String... value) {
        addStartsWith(DevicePhysicsColumns.CODE, value);
        return this;
    }

    public DevicePhysicsSelection codeEndsWith(String... value) {
        addEndsWith(DevicePhysicsColumns.CODE, value);
        return this;
    }

    public DevicePhysicsSelection orderByCode(boolean desc) {
        orderBy(DevicePhysicsColumns.CODE, desc);
        return this;
    }

    public DevicePhysicsSelection orderByCode() {
        orderBy(DevicePhysicsColumns.CODE, false);
        return this;
    }

    public DevicePhysicsSelection codePhysics(String... value) {
        addEquals(DevicePhysicsColumns.CODE_PHYSICS, value);
        return this;
    }

    public DevicePhysicsSelection codePhysicsNot(String... value) {
        addNotEquals(DevicePhysicsColumns.CODE_PHYSICS, value);
        return this;
    }

    public DevicePhysicsSelection codePhysicsLike(String... value) {
        addLike(DevicePhysicsColumns.CODE_PHYSICS, value);
        return this;
    }

    public DevicePhysicsSelection codePhysicsContains(String... value) {
        addContains(DevicePhysicsColumns.CODE_PHYSICS, value);
        return this;
    }

    public DevicePhysicsSelection codePhysicsStartsWith(String... value) {
        addStartsWith(DevicePhysicsColumns.CODE_PHYSICS, value);
        return this;
    }

    public DevicePhysicsSelection codePhysicsEndsWith(String... value) {
        addEndsWith(DevicePhysicsColumns.CODE_PHYSICS, value);
        return this;
    }

    public DevicePhysicsSelection orderByCodePhysics(boolean desc) {
        orderBy(DevicePhysicsColumns.CODE_PHYSICS, desc);
        return this;
    }

    public DevicePhysicsSelection orderByCodePhysics() {
        orderBy(DevicePhysicsColumns.CODE_PHYSICS, false);
        return this;
    }

    public DevicePhysicsSelection name(String... value) {
        addEquals(DevicePhysicsColumns.NAME, value);
        return this;
    }

    public DevicePhysicsSelection nameNot(String... value) {
        addNotEquals(DevicePhysicsColumns.NAME, value);
        return this;
    }

    public DevicePhysicsSelection nameLike(String... value) {
        addLike(DevicePhysicsColumns.NAME, value);
        return this;
    }

    public DevicePhysicsSelection nameContains(String... value) {
        addContains(DevicePhysicsColumns.NAME, value);
        return this;
    }

    public DevicePhysicsSelection nameStartsWith(String... value) {
        addStartsWith(DevicePhysicsColumns.NAME, value);
        return this;
    }

    public DevicePhysicsSelection nameEndsWith(String... value) {
        addEndsWith(DevicePhysicsColumns.NAME, value);
        return this;
    }

    public DevicePhysicsSelection orderByName(boolean desc) {
        orderBy(DevicePhysicsColumns.NAME, desc);
        return this;
    }

    public DevicePhysicsSelection orderByName() {
        orderBy(DevicePhysicsColumns.NAME, false);
        return this;
    }

    public DevicePhysicsSelection type(String... value) {
        addEquals(DevicePhysicsColumns.TYPE, value);
        return this;
    }

    public DevicePhysicsSelection typeNot(String... value) {
        addNotEquals(DevicePhysicsColumns.TYPE, value);
        return this;
    }

    public DevicePhysicsSelection typeLike(String... value) {
        addLike(DevicePhysicsColumns.TYPE, value);
        return this;
    }

    public DevicePhysicsSelection typeContains(String... value) {
        addContains(DevicePhysicsColumns.TYPE, value);
        return this;
    }

    public DevicePhysicsSelection typeStartsWith(String... value) {
        addStartsWith(DevicePhysicsColumns.TYPE, value);
        return this;
    }

    public DevicePhysicsSelection typeEndsWith(String... value) {
        addEndsWith(DevicePhysicsColumns.TYPE, value);
        return this;
    }

    public DevicePhysicsSelection orderByType(boolean desc) {
        orderBy(DevicePhysicsColumns.TYPE, desc);
        return this;
    }

    public DevicePhysicsSelection orderByType() {
        orderBy(DevicePhysicsColumns.TYPE, false);
        return this;
    }
}
