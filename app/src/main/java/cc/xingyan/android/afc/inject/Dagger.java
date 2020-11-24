/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc.inject;

import android.content.Context;

import cc.xingyan.android.afc.AfcApplication;

/**
 * Created by San on 9/29/15.
 */
public class Dagger {
    private static BulletComponentInjector componentInjector;

    public static void initialize(Context context) {
        try {
            final AfcApplication app = (AfcApplication) context.getApplicationContext();
            final ApiServiceModule apiServiceModule = new ApiServiceModule(app.createRetrofit());
            final SystemServiceModule systemServiceModule = new SystemServiceModule(app);
            componentInjector = new BulletComponentInjector(DaggerComponentInjector.builder()
                    .apiServiceModule(apiServiceModule).systemServiceModule(systemServiceModule).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> void inject(T t) {
        componentInjector.inject(t);
    }
}
