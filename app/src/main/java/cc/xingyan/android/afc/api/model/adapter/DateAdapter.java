/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc.api.model.adapter;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by San on 10/10/15.
 */
public class DateAdapter {
    final Pattern pattern = Pattern.compile("/Date\\(([0-9]+)([+-][0-9]{4})?\\)/");

    @ToJson String toJson(Date date) {
        return String.format("/Date(%1$s)/", date.getTime());
    }

    @FromJson Date fromJson(String json) {
        final Matcher m = pattern.matcher(json);
        if (m.find()) {
            return new Date(Long.parseLong(m.group(1)));
        }
        return new Date();
    }
}
