package cc.xingyan.android.afc.provider.user;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code user} table.
 */
public class UserCursor extends AbstractCursor implements UserModel {
    public UserCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(UserColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code org_code} value.
     * Can be {@code null}.
     */
    public String getOrgCode() {
        String res = getStringOrNull(UserColumns.ORG_CODE);
        return res;
    }

    /**
     * Get the {@code user_id} value.
     * Can be {@code null}.
     */
    public String getUserId() {
        String res = getStringOrNull(UserColumns.USER_ID);
        return res;
    }

    /**
     * Get the {@code user_no} value.
     * Can be {@code null}.
     */
    public String getUserNo() {
        String res = getStringOrNull(UserColumns.USER_NO);
        return res;
    }

    /**
     * Get the {@code user_name} value.
     * Can be {@code null}.
     */
    public String getUserName() {
        String res = getStringOrNull(UserColumns.USER_NAME);
        return res;
    }

    /**
     * Get the {@code password_md5} value.
     * Can be {@code null}.
     */
    public String getPasswordMd5() {
        String res = getStringOrNull(UserColumns.PASSWORD_MD5);
        return res;
    }
}
