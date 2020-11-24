/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc;

import android.content.Context;
import android.os.Build;
import androidx.preference.PreferenceManager;


import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.moshi.Moshi;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.TimeUnit;


import cc.xingyan.android.afc.api.model.adapter.DateAdapter;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.util.MyCrashHandler;
import retrofit.Converter;
import retrofit.MoshiConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by San on 9/22/15.
 */
public class AfcApplication extends MultiDexApplication {

//        public static final String DEFAULT_BASE_URL = "http://pda.mtd-bj.com:8832/Service1.svc/json/";    //常用测试库
//    public static final String DEFAULT_BASE_URL = "http://192.168.4.194:2201/Service1.svc/json/";
//    public static final String DEFAULT_BASE_URL = "http://pda.mtd-bj.com:8833/Service1.svc/json/";    //正式库——慎用

    public static final String DEFAULT_BASE_URL = "http://192.168.3.110:2201/Service1.svc/json/";       //本机


//    public static final String DEFAULT_BASE_URL = "http://pda.mtd-bj.com" + BuildConfig.SERVER_PORT + "/Service1.svc/json/";   //发布时使用

    private Date serverDate;

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
        Dagger.initialize(this);

        MyCrashHandler crashHandler = MyCrashHandler.getInstance();
        crashHandler.init(this);

        closeAndroidPDialog();

    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    public void reconfigureApiServices() {
        Dagger.initialize(this);
    }

    @SuppressWarnings("deprecation")
    public Retrofit createRetrofit() throws  Exception{

        final String baseUrl = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("api.base_url", DEFAULT_BASE_URL);
        final OkHttpClient client = new OkHttpClient();

        client.setWriteTimeout(100, TimeUnit.SECONDS);
        client.setReadTimeout(60, TimeUnit.SECONDS);

        client.interceptors().add(chain -> {
            final Response response = chain.proceed(chain.request());

            this.serverDate = new Date(response.header("Date"));

            return response;
        });

        if (BuildConfig.DEBUG) {
            final HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
            logger.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.interceptors().add(logger);
        }
        final Moshi moshi = new Moshi.Builder()
                .add(new DateAdapter())
                .build();
        final Converter.Factory moshiConverterFactory = MoshiConverterFactory.create(moshi);
        final Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(moshiConverterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    public Date getServerDate() {
        return this.serverDate;
    }


    private void closeAndroidPDialog(){
        if (Build.VERSION.SDK_INT < 28)return;
        try {
            Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
