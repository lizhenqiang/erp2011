package cc.xingyan.android.afc.service;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.IBinder;
import android.os.SystemClock;


import androidx.core.app.NotificationCompat;

import javax.inject.Inject;

import cc.xingyan.android.afc.R;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.cmwork.CmWorkSelection;
import cc.xingyan.android.afc.provider.cmwork.CmWorkStatus;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkSelection;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkStatus;
import cc.xingyan.android.afc.receiver.WorkNoConfirmAlarmReceiver;
import cc.xingyan.android.afc.util.LogConfig;
import cc.xingyan.android.afc.util.LogUtil;

/**
 * Created by YuJiadeng on 2016/12/6.
 *
 */
public class WorkRemindService  extends IntentService implements LogConfig {

    @Inject
    Authenticator mAuthenticator;
    String userId;


    public WorkRemindService() {
        super("WorkRemindService");
        Dagger.inject(this);
        userId = mAuthenticator.getAuthenticatedUserId();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getNoConfirmPMAndCMCount();
            }
        }).start();

        SharedPreferences workRemindMinPreferences;
        workRemindMinPreferences = getApplicationContext().getSharedPreferences("workRemindMin", Activity.MODE_PRIVATE);

        String workRemindMinStr = workRemindMinPreferences.getString(userId,"1");
        if(workRemindMinStr.length() < 1){
            workRemindMinStr = "1";
        }
        LogUtil.info("LongRunningService>>>>", "workRemindMinStr:" + workRemindMinStr);

        int workRemindMin = 1;

        try {
            workRemindMin = Integer.parseInt(workRemindMinStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }finally {
            AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
            int anHour = workRemindMin * 60 * 1000;
            long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
            Intent i = new Intent(this, WorkNoConfirmAlarmReceiver.class);
            PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
            manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy()
    {
        super.onDestroy();
        stopSelf();

    }


    public void showCzNotify(){

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        Notification mNotification = mBuilder.build();
        mNotification.flags = Notification.FLAG_AUTO_CANCEL;
        mNotification.flags |= Notification.FLAG_ONGOING_EVENT;
        mNotification.flags |= Notification.FLAG_NO_CLEAR;
        mNotification.flags |= Notification.FLAG_SHOW_LIGHTS;

        mNotification.sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sound4);

        mNotification.ledARGB = Color.RED;

        mNotification.defaults |= Notification.DEFAULT_VIBRATE;
        long[] vibrate = {0,100,200,300};
        mNotification.vibrate = vibrate ;

        mNotification.defaults |= Notification.DEFAULT_LIGHTS;
        mNotification.ledARGB = 0xff00ff00;
        mNotification.ledOnMS = 1000;
        mNotification.ledOffMS = 300;
        mNotification.flags |= Notification.FLAG_SHOW_LIGHTS;

        mNotification.tickerText = "通知来了";
        mNotification.when=System.currentTimeMillis();
        mNotificationManager.notify(100, mNotification);
    }


    private void getNoConfirmPMAndCMCount(){
        int pmNoConfirmPMAndCMCount = new PmifsWorkSelection().userId(userId).and().status(PmifsWorkStatus.NEW).count(getApplicationContext().getContentResolver());

        int pmNoConfirmCMAndCMCount = new CmWorkSelection().userId(userId).and().status(CmWorkStatus.NEW, CmWorkStatus.FAULTREPORT).count(getApplicationContext().getContentResolver());


        if(pmNoConfirmCMAndCMCount > 0){
            showCzNotify();
        }
        LogUtil.info("LongRunningService", "未确认工单数量_pmNoConfirmPMAndCMCount: " + pmNoConfirmPMAndCMCount + ",  pmNoConfirmCMAndCMCount:" + pmNoConfirmCMAndCMCount);
    }

}
