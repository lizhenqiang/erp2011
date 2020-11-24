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

import java.util.Arrays;
import java.util.List;

/**
 * Created by San on 10/9/15.
 */
public class Guids {
    @Json(name = "guids") List<Guid> guids;

    public List<Guid> getGuids() {
        return guids;
    }

    public void setGuids(List<Guid> guids) {
        this.guids = guids;
    }

    public static Guids from(Guid... g) {
        Guids guids = new Guids();
        guids.setGuids(Arrays.asList(g));
        return guids;
    }
}
