/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc.api;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;

import cc.xingyan.android.afc.provider.user.UserCursor;
import cc.xingyan.android.afc.provider.user.UserSelection;

/**
 * Created by San on 9/23/15.
 */
public class Authenticator {
    private static final String PREF_USER_ID = "auth.user_id";

    private final Context applicationContext;
    private final SharedPreferences sharedPreferences;

    private String userId;
    private String username;
    private String userWorkArea;

    public Authenticator(Context context) {
        this.applicationContext = context.getApplicationContext();
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

    public void setAuthenticatedUser(String userId) {
        this.userId = userId;
        this.username = null;
        if (userId == null) {
            sharedPreferences.edit().remove(PREF_USER_ID).commit();
        } else {
            sharedPreferences.edit().putString(PREF_USER_ID, userId).commit();
        }
    }

    public boolean offlineAuth(String userId, String password) {
        // TODO: Uncomment this line to enable md5 hashing
        // password = Hashing.md5().hashString(password, Charset.defaultCharset()).toString();
        final UserCursor cur = new UserSelection()
                .userId(userId)
                .and().passwordMd5(password).query(applicationContext);
        try {
            if (cur.getCount() > 0) {
                setAuthenticatedUser(userId);
                return true;
            } else {
                return false;
            }
        } finally {
            cur.close();
        }
    }

    public String getAuthenticatedUserId() {
        if (userId == null) {
            userId = sharedPreferences.getString(PREF_USER_ID, null);
        }
        return userId;
    }

    public String getAuthenticatedUserName() {
//        System.out.println(">>>>>>>>>>>>>>4" + username + ", time:" + System.currentTimeMillis());
        if (username == null) {
            loadUser();
        }
        return username;
    }


    public String getAuthenticatedUserWorkArea() {
        if (userWorkArea == null) {
            loadUser();
        }
        return userWorkArea;
    }

    private void loadUser() {
        final String userId = getAuthenticatedUserId();
//        System.out.println(">>>>>>>>>>>>>>5" + userId + ", time:" + System.currentTimeMillis());
        if (userId == null)
            return;

        final UserCursor cur = new UserSelection().userId(userId).query(applicationContext);
        try {
            if (cur.moveToFirst()) {
                this.username = cur.getUserName();
                this.userWorkArea = cur.getOrgCode();
//                System.out.println(">>>>>>>>>>>>>>6" + username + ", time:" + System.currentTimeMillis());
            }
        } finally {
            cur.close();
        }
    }
}
