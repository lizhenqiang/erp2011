package cc.xingyan.android.afc.provider.user;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code user} table.
 */
public class UserContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return UserColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  UserSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  UserSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public UserContentValues putOrgCode(String value) {
        mContentValues.put(UserColumns.ORG_CODE, value);
        return this;
    }

    public UserContentValues putOrgCodeNull() {
        mContentValues.putNull(UserColumns.ORG_CODE);
        return this;
    }

    public UserContentValues putUserId(String value) {
        mContentValues.put(UserColumns.USER_ID, value);
        return this;
    }

    public UserContentValues putUserIdNull() {
        mContentValues.putNull(UserColumns.USER_ID);
        return this;
    }

    public UserContentValues putUserNo(String value) {
        mContentValues.put(UserColumns.USER_NO, value);
        return this;
    }

    public UserContentValues putUserNoNull() {
        mContentValues.putNull(UserColumns.USER_NO);
        return this;
    }

    public UserContentValues putUserName(String value) {
        mContentValues.put(UserColumns.USER_NAME, value);
        return this;
    }

    public UserContentValues putUserNameNull() {
        mContentValues.putNull(UserColumns.USER_NAME);
        return this;
    }

    public UserContentValues putPasswordMd5(String value) {
        mContentValues.put(UserColumns.PASSWORD_MD5, value);
        return this;
    }

    public UserContentValues putPasswordMd5Null() {
        mContentValues.putNull(UserColumns.PASSWORD_MD5);
        return this;
    }
}
