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
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by San on 9/29/15.
 */
@Module
public class SystemServiceModule {

    private final Context applicationContext;

    public SystemServiceModule(Context context) {
        this.applicationContext = context.getApplicationContext();
    }

    @Provides @Singleton Context provideApplicationContext() {
        return applicationContext;
    }

    @Provides @Singleton SharedPreferences providePreferenceManager() {
        return PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

}
