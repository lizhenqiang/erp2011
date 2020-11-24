/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc.app;

import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import cc.xingyan.android.afc.service.SyncService;
import icepick.Icepick;
import rx.Subscriber;
import rx.observers.Subscribers;

/**
 * Created by San on 9/21/15.
 */
public class BaseActivity extends AppCompatActivity implements IO {

    private Subscriber mSubscriber = Subscribers.empty();

    private int connectionStatus;
    private SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = (sharedPreferences, key) -> {
        if (SyncService.PREF_CONNECTION_STATUS.equals(key)) {
            connectionStatus = sharedPreferences.getInt(key, SyncService.STATUS_ONLINE);
            onConnectionStatusChanged(connectionStatus, getConnectionStatusText(connectionStatus));
        }
    };

    protected void onConnectionStatusChanged(int connectionStatus, String connectionStatusText) {

    }


    protected String getConnectionStatusText(int connectionStatus) {
        switch (connectionStatus) {
            default:
            case SyncService.STATUS_ONLINE:
                return "在线";
            case SyncService.STATUS_OFFLINE:
                return "离线";
        }
    }


    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        onSharedPreferenceChangeListener.onSharedPreferenceChanged(sharedPreferences, SyncService.PREF_CONNECTION_STATUS);
        clearNotification();
    }

    @Override protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        clearNotification();
    }

    private void clearNotification() {
        final NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.cancel(0);
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override public void setContentView(View view, ViewGroup.LayoutParams params) {
        ButterKnife.bind(this);
        super.setContentView(view, params);
    }

    @Override protected void onDestroy() {
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        ButterKnife.unbind(this);
        mSubscriber.unsubscribe();
        super.onDestroy();
    }

    @Override public Subscriber getSubscriber() {
        return mSubscriber;
    }
}
