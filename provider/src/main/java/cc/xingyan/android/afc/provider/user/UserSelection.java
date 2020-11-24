package cc.xingyan.android.afc.provider.user;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code user} table.
 */
public class UserSelection extends AbstractSelection<UserSelection> {
    @Override
    protected Uri baseUri() {
        return UserColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code UserCursor} object, which is positioned before the first entry, or null.
     */
    public UserCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new UserCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public UserCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code UserCursor} object, which is positioned before the first entry, or null.
     */
    public UserCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new UserCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public UserCursor query(Context context) {
        return query(context, null);
    }


    public UserSelection id(long... value) {
        addEquals("user." + UserColumns._ID, toObjectArray(value));
        return this;
    }

    public UserSelection idNot(long... value) {
        addNotEquals("user." + UserColumns._ID, toObjectArray(value));
        return this;
    }

    public UserSelection orderById(boolean desc) {
        orderBy("user." + UserColumns._ID, desc);
        return this;
    }

    public UserSelection orderById() {
        return orderById(false);
    }

    public UserSelection orgCode(String... value) {
        addEquals(UserColumns.ORG_CODE, value);
        return this;
    }

    public UserSelection orgCodeNot(String... value) {
        addNotEquals(UserColumns.ORG_CODE, value);
        return this;
    }

    public UserSelection orgCodeLike(String... value) {
        addLike(UserColumns.ORG_CODE, value);
        return this;
    }

    public UserSelection orgCodeContains(String... value) {
        addContains(UserColumns.ORG_CODE, value);
        return this;
    }

    public UserSelection orgCodeStartsWith(String... value) {
        addStartsWith(UserColumns.ORG_CODE, value);
        return this;
    }

    public UserSelection orgCodeEndsWith(String... value) {
        addEndsWith(UserColumns.ORG_CODE, value);
        return this;
    }

    public UserSelection orderByOrgCode(boolean desc) {
        orderBy(UserColumns.ORG_CODE, desc);
        return this;
    }

    public UserSelection orderByOrgCode() {
        orderBy(UserColumns.ORG_CODE, false);
        return this;
    }

    public UserSelection userId(String... value) {
        addEquals(UserColumns.USER_ID, value);
        return this;
    }

    public UserSelection userIdNot(String... value) {
        addNotEquals(UserColumns.USER_ID, value);
        return this;
    }

    public UserSelection userIdLike(String... value) {
        addLike(UserColumns.USER_ID, value);
        return this;
    }

    public UserSelection userIdContains(String... value) {
        addContains(UserColumns.USER_ID, value);
        return this;
    }

    public UserSelection userIdStartsWith(String... value) {
        addStartsWith(UserColumns.USER_ID, value);
        return this;
    }

    public UserSelection userIdEndsWith(String... value) {
        addEndsWith(UserColumns.USER_ID, value);
        return this;
    }

    public UserSelection orderByUserId(boolean desc) {
        orderBy(UserColumns.USER_ID, desc);
        return this;
    }

    public UserSelection orderByUserId() {
        orderBy(UserColumns.USER_ID, false);
        return this;
    }

    public UserSelection userNo(String... value) {
        addEquals(UserColumns.USER_NO, value);
        return this;
    }

    public UserSelection userNoNot(String... value) {
        addNotEquals(UserColumns.USER_NO, value);
        return this;
    }

    public UserSelection userNoLike(String... value) {
        addLike(UserColumns.USER_NO, value);
        return this;
    }

    public UserSelection userNoContains(String... value) {
        addContains(UserColumns.USER_NO, value);
        return this;
    }

    public UserSelection userNoStartsWith(String... value) {
        addStartsWith(UserColumns.USER_NO, value);
        return this;
    }

    public UserSelection userNoEndsWith(String... value) {
        addEndsWith(UserColumns.USER_NO, value);
        return this;
    }

    public UserSelection orderByUserNo(boolean desc) {
        orderBy(UserColumns.USER_NO, desc);
        return this;
    }

    public UserSelection orderByUserNo() {
        orderBy(UserColumns.USER_NO, false);
        return this;
    }

    public UserSelection userName(String... value) {
        addEquals(UserColumns.USER_NAME, value);
        return this;
    }

    public UserSelection userNameNot(String... value) {
        addNotEquals(UserColumns.USER_NAME, value);
        return this;
    }

    public UserSelection userNameLike(String... value) {
        addLike(UserColumns.USER_NAME, value);
        return this;
    }

    public UserSelection userNameContains(String... value) {
        addContains(UserColumns.USER_NAME, value);
        return this;
    }

    public UserSelection userNameStartsWith(String... value) {
        addStartsWith(UserColumns.USER_NAME, value);
        return this;
    }

    public UserSelection userNameEndsWith(String... value) {
        addEndsWith(UserColumns.USER_NAME, value);
        return this;
    }

    public UserSelection orderByUserName(boolean desc) {
        orderBy(UserColumns.USER_NAME, desc);
        return this;
    }

    public UserSelection orderByUserName() {
        orderBy(UserColumns.USER_NAME, false);
        return this;
    }

    public UserSelection passwordMd5(String... value) {
        addEquals(UserColumns.PASSWORD_MD5, value);
        return this;
    }

    public UserSelection passwordMd5Not(String... value) {
        addNotEquals(UserColumns.PASSWORD_MD5, value);
        return this;
    }

    public UserSelection passwordMd5Like(String... value) {
        addLike(UserColumns.PASSWORD_MD5, value);
        return this;
    }

    public UserSelection passwordMd5Contains(String... value) {
        addContains(UserColumns.PASSWORD_MD5, value);
        return this;
    }

    public UserSelection passwordMd5StartsWith(String... value) {
        addStartsWith(UserColumns.PASSWORD_MD5, value);
        return this;
    }

    public UserSelection passwordMd5EndsWith(String... value) {
        addEndsWith(UserColumns.PASSWORD_MD5, value);
        return this;
    }

    public UserSelection orderByPasswordMd5(boolean desc) {
        orderBy(UserColumns.PASSWORD_MD5, desc);
        return this;
    }

    public UserSelection orderByPasswordMd5() {
        orderBy(UserColumns.PASSWORD_MD5, false);
        return this;
    }
}
