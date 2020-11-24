package cc.xingyan.android.afc.provider.user;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code user} table.
 */
public interface UserModel extends BaseModel {

    /**
     * Get the {@code org_code} value.
     * Can be {@code null}.
     */
    String getOrgCode();

    /**
     * Get the {@code user_id} value.
     * Can be {@code null}.
     */
    String getUserId();

    /**
     * Get the {@code user_no} value.
     * Can be {@code null}.
     */
    String getUserNo();

    /**
     * Get the {@code user_name} value.
     * Can be {@code null}.
     */
    String getUserName();

    /**
     * Get the {@code password_md5} value.
     * Can be {@code null}.
     */
    String getPasswordMd5();
}
