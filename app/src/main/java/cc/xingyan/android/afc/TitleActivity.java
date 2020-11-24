package cc.xingyan.android.afc;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;


import androidx.appcompat.widget.Toolbar;

import cc.xingyan.android.afc.app.BaseActivity;
import cc.xingyan.android.afc.service.SyncService;

/**
 * Created by San on 11/17/15.
 */
public abstract class TitleActivity extends BaseActivity {

    private int titleColor;
    private String connectionStatusText;
    private int connectionStatus;

    protected abstract Toolbar getToolbar();

    @Override protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (getToolbar() != null) {
            final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            connectionStatus = prefs.getInt(SyncService.PREF_CONNECTION_STATUS, SyncService.STATUS_ONLINE);
            connectionStatusText = getConnectionStatusText(connectionStatus);
            onConnectionStatusChanged(connectionStatus, connectionStatusText);
        }
    }

    @Override protected void onTitleChanged(CharSequence title, int color) {
        onTitleChanged(title, color, false);
    }

    protected void onTitleChanged(CharSequence title, int color, boolean respectColor) {
        super.onTitleChanged(title, color);
        final Toolbar toolbar = getToolbar();
        if (toolbar != null) {
            toolbar.setTitle(String.format("%1$s", title, connectionStatusText));
            if (respectColor) {
                toolbar.setBackgroundColor(color);
            }
        }
    }

    @Override
    protected void onConnectionStatusChanged(int connectionStatus, String connectionStatusText) {
        final int titleColor, statusBarColor;
        switch (connectionStatus) {
            default:
            case SyncService.STATUS_ONLINE:
                titleColor = getResources().getColor(R.color.primary);
                statusBarColor = getResources().getColor(R.color.primary_dark);
                break;
            case SyncService.STATUS_OFFLINE:
                titleColor = getResources().getColor(R.color.primary_diabled);
                statusBarColor = getResources().getColor(R.color.primary_dark_disabled);
                break;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(statusBarColor);
        }

        this.titleColor = titleColor;
        this.connectionStatusText = connectionStatusText;
        onTitleChanged(getTitle(), titleColor, true);
    }

}
