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

import javax.inject.Singleton;

import cc.xingyan.android.afc.api.AuthService;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.ChangePassword;
import cc.xingyan.android.afc.api.CmService;
import cc.xingyan.android.afc.api.DayInspectService;
import cc.xingyan.android.afc.api.DeviceService;
import cc.xingyan.android.afc.api.PmService;
import cc.xingyan.android.afc.api.UserService;
import cc.xingyan.android.afc.api.WorkOrderService;
import dagger.Module;
import dagger.Provides;
import retrofit.Retrofit;

/**
 * Created by San on 9/29/15.
 */
@Module
public class ApiServiceModule {

    private Retrofit retrofit;

    public ApiServiceModule(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Provides AuthService provideAuthService() {
        return retrofit.create(AuthService.class);
    }

    @Provides ChangePassword provideChangePassword() {
        return retrofit.create(ChangePassword.class);
    }

    @Provides DeviceService provideDeviceService() {
        return retrofit.create(DeviceService.class);
    }

    @Provides WorkOrderService provideWorkOrderService() {
        return retrofit.create(WorkOrderService.class);
    }

    @Provides PmService providePmService() {
        return retrofit.create(PmService.class);
    }

    @Provides
    DayInspectService provideDayInspectService() {
        return retrofit.create(DayInspectService.class);
    }

    @Provides @Singleton
    Authenticator provideAuthenticator(Context context) {
        return new Authenticator(context);
    }

    @Provides UserService provideUserService() {
        return retrofit.create(UserService.class);
    }

    @Provides    CmService provideCmService() {
        return retrofit.create(CmService.class);
    }
}
