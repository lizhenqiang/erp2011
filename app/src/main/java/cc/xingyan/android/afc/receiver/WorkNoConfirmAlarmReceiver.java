package cc.xingyan.android.afc.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cc.xingyan.android.afc.service.WorkRemindService;

/**
 * Created by YuJiadeng on 2016/12/7.
 *
 */
public class WorkNoConfirmAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentTemp = new Intent(context, WorkRemindService.class);
        context.startService(intentTemp);
    }
}
