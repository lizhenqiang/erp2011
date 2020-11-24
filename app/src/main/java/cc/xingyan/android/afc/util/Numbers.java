/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc.util;

/**
 * Created by San on 9/23/15.
 */
public class Numbers {
    public static long longValue(Number number) {
        return longValue(number, 0L);
    }

    public static long longValue(Number number, long defaultValue) {
        return number == null ? defaultValue : number.longValue();
    }

    public static long intValue(Number number) {
        return intValue(number, 0);
    }

    public static long intValue(Number number, int defaultValue) {
        return number == null ? defaultValue : number.intValue();
    }
}
