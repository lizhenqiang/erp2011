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

import java.io.Serializable;

/**
 * Created by San on 9/29/15.
 */
public class Guid implements Serializable {
    @Json(name = "Guid") String guid;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}
