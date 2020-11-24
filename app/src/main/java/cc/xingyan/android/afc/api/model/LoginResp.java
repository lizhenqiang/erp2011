/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by San on 9/29/15.
 */
public class LoginResp {
    @Json(name = "errorInfo") String errorInfo;
    @Json(name = "userID") String userID;
    @Json(name = "WorkArea") String WorkArea;

    public String getUserId() {
        return userID;
    }

    public void setUserId(String userID) {
        this.userID = userID;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getWorkArea() {
        return WorkArea;
    }

    public void setWorkArea(String WorkArea) {
        this.WorkArea = WorkArea;
    }

}
