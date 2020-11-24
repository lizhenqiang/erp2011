/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc.util;

import java.util.Date;

/**
 * Created by San on 10/12/15.
 */
public class Dates {
    public static Long getMillis(Date date) {
        return date == null ? null : Long.valueOf(date.getTime());
    }
}
