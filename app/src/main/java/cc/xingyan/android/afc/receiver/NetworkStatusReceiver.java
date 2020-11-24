package cc.xingyan.android.afc.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import javax.inject.Inject;

import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.service.SyncService;

/**
 * Created by San on 11/10/15.
 */
public class NetworkStatusReceiver extends BroadcastReceiver {

    @Inject
    Authenticator authenticator;

    public NetworkStatusReceiver() {
        super();
        Dagger.inject(this);
    }

    @Override public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            final ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo network = manager.getActiveNetworkInfo();
            if (network == null || !network.isConnected()) {
                PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit()
                        .putInt(SyncService.PREF_CONNECTION_STATUS, SyncService.STATUS_OFFLINE)
                        .commit();

                // Stop sync task
                final AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                final Intent service = new Intent(context, SyncService.class);
                final PendingIntent pendingIntent = PendingIntent.getService(context, 0, service, PendingIntent.FLAG_NO_CREATE);
                if (pendingIntent != null) {
                    am.cancel(pendingIntent);
                }
            } else {
                PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit()
                        .putInt(SyncService.PREF_CONNECTION_STATUS, SyncService.STATUS_ONLINE)
                        .commit();
                final Intent service = new Intent(context, SyncService.class);
                service.putExtra(SyncService.RESET_ALARM, true);
                if (authenticator.getAuthenticatedUserId() == null) {
                    service.putExtra(SyncService.SYNC_OPTIONS, SyncService.SYNC_UPLOAD_PENDING);
                }
                context.startService(service);
            }
        }
    }

}
