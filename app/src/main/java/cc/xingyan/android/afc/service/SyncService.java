package cc.xingyan.android.afc.service;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.preference.PreferenceManager;


import androidx.core.app.NotificationCompat;

import com.google.common.collect.Collections2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import cc.xingyan.android.afc.AfcApplication;
import cc.xingyan.android.afc.LoginActivity;
import cc.xingyan.android.afc.R;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.CmService;
import cc.xingyan.android.afc.api.DayInspectService;
import cc.xingyan.android.afc.api.DeviceService;
import cc.xingyan.android.afc.api.PmService;
import cc.xingyan.android.afc.api.UserService;
import cc.xingyan.android.afc.api.WorkOrderService;
import cc.xingyan.android.afc.api.model.CMDeleteLists;
import cc.xingyan.android.afc.api.model.CmWork;
import cc.xingyan.android.afc.api.model.CmWorkDelete;
import cc.xingyan.android.afc.api.model.CmWorkID;
import cc.xingyan.android.afc.api.model.CmWorkIDs;
import cc.xingyan.android.afc.api.model.CmWorks;
import cc.xingyan.android.afc.api.model.DayInspectWork;
import cc.xingyan.android.afc.api.model.DayInspectWorkInfoList1Level;
import cc.xingyan.android.afc.api.model.DayInspectWorkInfoList2Level;
import cc.xingyan.android.afc.api.model.DayInspectWorkPMOnly;
import cc.xingyan.android.afc.api.model.DayInspectWorkPMOnlys;
import cc.xingyan.android.afc.api.model.DayInspectWorkReport;
import cc.xingyan.android.afc.api.model.DayInspectWorkReportReturn;
import cc.xingyan.android.afc.api.model.DayInspectWorkReports;
import cc.xingyan.android.afc.api.model.DayInspectWorks;
import cc.xingyan.android.afc.api.model.Guid;
import cc.xingyan.android.afc.api.model.LocationInfo;
import cc.xingyan.android.afc.api.model.LocationInfos;
import cc.xingyan.android.afc.api.model.PMDeleteLists;
import cc.xingyan.android.afc.api.model.PRDeleteLists;
import cc.xingyan.android.afc.api.model.PRWorkDelete;
import cc.xingyan.android.afc.api.model.PmReport;
import cc.xingyan.android.afc.api.model.PmReports;
import cc.xingyan.android.afc.api.model.PmWorkDelete;
import cc.xingyan.android.afc.api.model.PmWorkID;
import cc.xingyan.android.afc.api.model.PmWorkIDs;
import cc.xingyan.android.afc.api.model.UserInfo;
import cc.xingyan.android.afc.api.model.WorkOrderNo;
import cc.xingyan.android.afc.api.model.WorkOrders;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.cmmaterial.CmMaterialCursor;
import cc.xingyan.android.afc.provider.cmmaterial.CmMaterialSelection;
import cc.xingyan.android.afc.provider.cmmaterialrow.CmMaterialRowSelection;
import cc.xingyan.android.afc.provider.cmwork.CmWorkContentValues;
import cc.xingyan.android.afc.provider.cmwork.CmWorkCursor;
import cc.xingyan.android.afc.provider.cmwork.CmWorkSelection;
import cc.xingyan.android.afc.provider.cmwork.CmWorkStatus;
import cc.xingyan.android.afc.provider.device.DeviceCursor;
import cc.xingyan.android.afc.provider.device.DeviceSelection;
import cc.xingyan.android.afc.provider.diifsinfo.DiIfsStatus;
import cc.xingyan.android.afc.provider.diifsinfo.DiifsInfoContentValues;
import cc.xingyan.android.afc.provider.diifsinfo.DiifsInfoCursor;
import cc.xingyan.android.afc.provider.diifsinfo.DiifsInfoSelection;
import cc.xingyan.android.afc.provider.diifspm.DiifsPmCursor;
import cc.xingyan.android.afc.provider.diifspm.DiifsPmSelection;
import cc.xingyan.android.afc.provider.diifspmitem.DiifsPmItemCursor;
import cc.xingyan.android.afc.provider.diifspmitem.DiifsPmItemSelection;
import cc.xingyan.android.afc.provider.diifswork.DiifsWorkContentValues;
import cc.xingyan.android.afc.provider.diifswork.DiifsWorkSelection;
import cc.xingyan.android.afc.provider.diwork.DiWorkSelection;
import cc.xingyan.android.afc.provider.pankureport.PankuReportCursor;
import cc.xingyan.android.afc.provider.pankureport.PankuReportSelection;
import cc.xingyan.android.afc.provider.picture.PictureCursor;
import cc.xingyan.android.afc.provider.picture.PictureSelection;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkColumns;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkContentValues;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkCursor;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkSelection;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkStatus;
import cc.xingyan.android.afc.provider.pmifsworkitem.PmifsWorkItemSelection;
import cc.xingyan.android.afc.provider.workorder.SyncStatus;
import cc.xingyan.android.afc.provider.workorder.WorkOrderContentValues;
import cc.xingyan.android.afc.provider.workorder.WorkOrderSelection;
import cc.xingyan.android.afc.util.Counter;
import cc.xingyan.android.afc.util.FileInfoUploadUtil;
import cc.xingyan.android.afc.util.LogConfig;
import cc.xingyan.android.afc.util.LogUtil;
import cc.xingyan.android.afc.util.NetUtil;
import cc.xingyan.android.afc.util.Numbers;
import cc.xingyan.android.afc.util.QueueRunner;
import cc.xingyan.android.afc.util.SyncHelper;
import cc.xingyan.android.afc.util.TService;

/**
 * Created by San on 11/10/15.
 */
public class SyncService extends IntentService implements LogConfig {
    private static final String TAG = "Sync";

    public static final String PREF_CONNECTION_STATUS = "connection_status";
    private static final String PREF_DEVICES_LAST_SYNC_TIME = "devices_last_sync_time";
    private static final String PREF_PHYSIC_LAST_SYNC_TIME = "physics_last_sync_time";
    private static final String PREF_PARAMS_LAST_SYNC_TIME = "params_last_sync_time";
    private static final String PREF_USERS_LAST_SYNC_TIME = "users_last_sync_time";
    private static final String PREF_MATERIALS_LAST_SYNC_TIME = "materials_last_sync_time";

    public static final int STATUS_ONLINE = 0;
    public static final int STATUS_OFFLINE = 1;

    public static final String SYNC_OPTIONS = "options";

    public static final int SYNC_UPLOAD_PENDING = 1 << 0;
    public static final int SYNC_DOWNLOAD_BASE_DATA = 1 << 1;
    public static final int SYNC_DOWNLOAD_PM_WORKS = 1 << 2;
    public static final int SYNC_DOWNLOAD_PM_WORKS_DEL = 1 << 9;
    public static final int SYNC_DOWNLOAD_DI_WORKS = 1 << 3;
    public static final int SYNC_DOWNLOAD_CM_WORKS = 1 << 4;
    public static final int SYNC_DOWNLOAD_CM_WORKS_DEL = 2 << 9;
    public static final int SYNC_DOWNLOAD_PR_WORKS_DEL = 3 << 9;
    public static final int SYNC_DOWNLOAD_DI_WORKS_DEL = 4 << 9;
    public static final int SYNC_UPLOAD_WORK_ORDERS = 1 << 5;
    public static final int SYNC_UPLOAD_PM_REPORTS = 1 << 6;
    public static final int SYNC_UPLOAD_DI_REPORTS = 1 << 7;
    public static final int SYNC_UPLOAD_CM_REPORTS = 1 << 8;
    public static final int SYNC_UPLOAD_LOC = 5 << 9;

    public static final int SYNC_UPLOAD_ALL = SYNC_UPLOAD_PENDING
            | SYNC_UPLOAD_WORK_ORDERS
            | SYNC_UPLOAD_PM_REPORTS
            | SYNC_UPLOAD_DI_REPORTS
            | SYNC_UPLOAD_CM_REPORTS
            | SYNC_UPLOAD_LOC;

    public static final int SYNC_DOWNLOAD_ALL = SYNC_DOWNLOAD_BASE_DATA
            | SYNC_DOWNLOAD_PM_WORKS
            | SYNC_DOWNLOAD_PM_WORKS_DEL
            | SYNC_DOWNLOAD_CM_WORKS_DEL
            | SYNC_DOWNLOAD_PR_WORKS_DEL
            | SYNC_DOWNLOAD_DI_WORKS_DEL
            | SYNC_DOWNLOAD_DI_WORKS
            | SYNC_DOWNLOAD_CM_WORKS;

    public static final int SYNC_DOWNLOAD_AUTO = SYNC_DOWNLOAD_PM_WORKS |  SYNC_DOWNLOAD_CM_WORKS
            | SYNC_DOWNLOAD_PM_WORKS_DEL | SYNC_DOWNLOAD_CM_WORKS_DEL | SYNC_DOWNLOAD_PR_WORKS_DEL | SYNC_DOWNLOAD_DI_WORKS_DEL | SYNC_DOWNLOAD_DI_WORKS;

    public static final int SYNC_STOP = 0;

    public static final int SYNC_ALL = SYNC_UPLOAD_ALL | SYNC_DOWNLOAD_ALL;      

    public static final int SYNC_AUTO = SYNC_UPLOAD_ALL | SYNC_DOWNLOAD_AUTO;


    
    private static final int DEFAULT_SYNC_OPTIONS = SYNC_AUTO | SYNC_UPLOAD_PENDING;
    private static final int DEFAULT_SYNC_OPTIONS_CHART = SYNC_DOWNLOAD_BASE_DATA |SYNC_DOWNLOAD_PR_WORKS_DEL;  


    public static final String RESULT_RECEIVER = "result_receiver";
    public static final String RESET_ALARM = "reset_alarm";
    public static final String STOP_ALARM = "stop_alarm";
    public static final String ON_ERROR_STOP = "on_error_stop";

    @Inject
    Authenticator mAuthenticator;


    @Inject
    DeviceService deviceService;
    @Inject
    WorkOrderService workOrderService;
    @Inject
    PmService pmService;
    @Inject
    DayInspectService dayInspectService;
    @Inject
    CmService cmService;

    @Inject
    UserService userService;

    public SyncService() {
        super("SyncService");
        Dagger.inject(this);
    }
    @Override public int onStartCommand(Intent intent, int flags, int startId) {
        final boolean shouldStopAlarm = intent.getBooleanExtra(STOP_ALARM, false);
        if (shouldStopAlarm) {
            stopAlarm();
        } else {
            final boolean shouldResetAlarm = intent.getBooleanExtra(RESET_ALARM, false);
            if (shouldResetAlarm) {
                resetAlarm();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void stopAlarm() {
        final AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        final Intent intent = new Intent(this, SyncService.class);
        final PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        am.cancel(pendingIntent);
    }

    private void resetAlarm() {
        final AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        final Intent intent = new Intent(this, SyncService.class);
        intent.putExtra(RESULT_RECEIVER, new ResultReceiver(null) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                switch (resultCode) {
                    case ~SYNC_DOWNLOAD_BASE_DATA:
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit()
                                .putInt(PREF_CONNECTION_STATUS, STATUS_OFFLINE)
                                .commit();
                        break;
                    case SYNC_DOWNLOAD_BASE_DATA:
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit()
                                .putInt(PREF_CONNECTION_STATUS, STATUS_ONLINE)
                                .commit();
                        break;
                    case SYNC_DOWNLOAD_CM_WORKS:
                    case SYNC_DOWNLOAD_DI_WORKS:
                    case SYNC_DOWNLOAD_PM_WORKS_DEL:
                    case SYNC_DOWNLOAD_CM_WORKS_DEL:
                    case SYNC_DOWNLOAD_PR_WORKS_DEL:
                    case SYNC_DOWNLOAD_DI_WORKS_DEL:
                    case SYNC_DOWNLOAD_PM_WORKS:
                        if (resultData != null && resultData.getInt("count", 0) > 0) {
                            raiseNotification();
                        }
                        break;
                }
            }

            private void raiseNotification() {
                final Intent activityIntent = new Intent(getApplicationContext(), LoginActivity.class);
                activityIntent.setAction(Intent.ACTION_MAIN);
                activityIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                PendingIntent notificationIntent = PendingIntent.getActivity(getApplicationContext(), 0, activityIntent, 0);
                final Notification n = new NotificationCompat.Builder(getApplicationContext())
                        .setContentTitle("有新的工单任务了")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(notificationIntent)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .build();
                NotificationManager nm = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
                nm.notify(0, n);
            }
        });
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        am.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), AlarmManager.INTERVAL_FIFTEEN_MINUTES /5, pendingIntent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        deleteOutdatedData();

        final boolean onErrorStop = intent.getBooleanExtra(ON_ERROR_STOP, true);


        SharedPreferences chartPreferences = getSharedPreferences("chart", Activity.MODE_PRIVATE);
        String userId = mAuthenticator.getAuthenticatedUserId();
        String chartStr = chartPreferences.getString(userId, "noinfo");
        int defaultOption = 0;
        if(chartStr.equals("chart")){
            defaultOption = DEFAULT_SYNC_OPTIONS_CHART;
        }else{
            defaultOption = DEFAULT_SYNC_OPTIONS;
        }

        final int options = intent.getIntExtra(SYNC_OPTIONS, defaultOption);
        if (willLog(TAG)) {
            LogUtil.debug(TAG, "Sync started");
        }

        final ResultReceiver receiver = intent.getParcelableExtra(RESULT_RECEIVER);
        final QueueRunner q = new QueueRunner();
        final String user = mAuthenticator.getAuthenticatedUserId();

        final String userName = mAuthenticator.getAuthenticatedUserName();;


        final WorkOrders workOrders;
        if (hasOption(options, SYNC_UPLOAD_WORK_ORDERS) && user != null) {
            workOrders = SyncHelper.collectLocalWorkOrders(getContentResolver(), user);
        } else if (hasOption(options, SYNC_UPLOAD_PENDING)) {
            workOrders = SyncHelper.collectUploadPendingWorkOrders(getContentResolver());
        } else {
            workOrders = null;
        }
        if (workOrders != null && workOrders.getWorkOrders() != null && workOrders.getWorkOrders().size() > 0) {
            q.add(() -> {
                final List<String> guids = new ArrayList();
                for (Guid g : workOrders.getWorkOrders()) {
                    guids.add(g.getGuid());
                }
                new WorkOrderContentValues()
                        .putUploadPending(true)
                        .update(getContentResolver(), new WorkOrderSelection().guid(guids.toArray(new String[guids.size()])));
                try {
                    workOrderService.postWorkOrders(workOrders).subscribe(resp -> {
                        for (WorkOrderNo no : resp) {
                            new WorkOrderContentValues()
                                    .putNo(no.getWorkOrderNo())
                                    .putUploadPending(false)
                                    .putSyncStatus(SyncStatus.SYNCHRONIZED)
                                    .update(getContentResolver(), new WorkOrderSelection().guid(no.getGuid()));
                        }
                        if (receiver != null) {
                            final Bundle result = new Bundle();
                            result.putInt("count", resp.size());
                            receiver.send(SYNC_UPLOAD_WORK_ORDERS, result);
                        }
                        q.next();
                    }, e -> {
                        if (receiver != null) {
                            receiver.send(~SYNC_UPLOAD_WORK_ORDERS, null);
                        }
                         LogUtil.error(TAG, "Failed to upload work orders", e);
                        if (!onErrorStop) {
                            q.next();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    q.next();
                }
            });
        } else {
            if (receiver != null) {
                receiver.send(SYNC_UPLOAD_WORK_ORDERS, null);
            }
        }


        final PmReports pmReports;
        if (hasOption(options, SYNC_UPLOAD_PM_REPORTS) && user != null) {
            pmReports = SyncHelper.collectDonePmReports(getContentResolver(), user);
        } else if (hasOption(options, SYNC_UPLOAD_PENDING)) {
            pmReports = SyncHelper.collectUploadPendingPmReports(getContentResolver());
        } else {
            pmReports = null;
        }
        if (pmReports != null && pmReports.getPmReports() != null && pmReports.getPmReports().size() > 0) {
            q.add(() -> {
                final List<String> pmOrderIds = new ArrayList();
                for (PmReport g : pmReports.getPmReports()) {
                    pmOrderIds.add(g.getOrderId());
                }
                new PmifsWorkContentValues()
                        .putUploadPending(true)
                        .update(getContentResolver(), new PmifsWorkSelection().guid(pmOrderIds.toArray(new String[pmOrderIds.size()])));
                try {
                    pmService.postReports(pmReports).subscribe(resp -> {
                        final List<String> list = new ArrayList(Collections2.transform(resp, g -> g.getOrderId()));
                        new PmifsWorkContentValues()
                                .putStatus(PmifsWorkStatus.WORKDONEUPLOAD)
                                .putUploadPending(false)
                                .update(getContentResolver(), new PmifsWorkSelection().orderId(list.toArray(new String[list.size()])));
                        if (receiver != null) {
                            final Bundle result = new Bundle();
                            result.putInt("count", resp.size());
                            receiver.send(SYNC_UPLOAD_PM_REPORTS, result);
                        }
                        q.next();
                    }, e -> {
                        if (receiver != null) {
                            receiver.send(~SYNC_UPLOAD_PM_REPORTS, null);
                        }
                         LogUtil.error(TAG, "Failed to upload pm reports", e);
                        if (!onErrorStop) {
                            q.next();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    q.next();
                }
            });
        } else {
            if (receiver != null) {
                receiver.send(SYNC_UPLOAD_PM_REPORTS, null);
            }
        }


        final DayInspectWorkReports dayInspectWorkReports;
        if (hasOption(options, SYNC_UPLOAD_DI_REPORTS)) {
            dayInspectWorkReports = SyncHelper.collectDoneDayInspectWorkReports(getContentResolver(), user);
        } else if (hasOption(options, SYNC_UPLOAD_PENDING)) {
            dayInspectWorkReports = SyncHelper.collectUploadPendingDayInspectWorkReports(getContentResolver(), user);
        } else {
            dayInspectWorkReports = null;
        }
        if (dayInspectWorkReports != null && dayInspectWorkReports.getDayInspectWorkReports() != null && dayInspectWorkReports.getDayInspectWorkReports().size() > 0) {


            q.add(() -> {
                final List<String> diwrWoNos = new ArrayList();
                for (DayInspectWorkReport g : dayInspectWorkReports.getDayInspectWorkReports()) {
                    diwrWoNos.add(g.getWoNo());
                }


                new DiifsInfoContentValues()
                        .putUploadPending(true)
                        .update(getContentResolver(), new DiifsInfoSelection().wono(diwrWoNos.toArray(new String[diwrWoNos.size()])));

                try {
                    dayInspectService.uploadDayInspectWorkList(dayInspectWorkReports).subscribe(resp -> {

                        boolean isAllUpdate = true;
                        for(DayInspectWorkReportReturn reportReturn : resp){
                            if(reportReturn.getObjMark().equals("0")){
                                new DiifsWorkContentValues()
                                        .putIsupdate(true)
                                        .update(getContentResolver(), new DiifsWorkSelection()
                                                .wono(reportReturn.getWoNo())
                                                .and()
                                                .objid(reportReturn.getObjId())
                                                .and().
                                                        objversion(reportReturn.getObjVersion()));
                            }else{
                                new DiifsWorkContentValues()
                                        .putIsupdate(false)
                                        .update(getContentResolver(), new DiifsWorkSelection()
                                                .wono(reportReturn.getWoNo())
                                                .and()
                                                .objid(reportReturn.getObjId())
                                                .and().
                                                        objversion(reportReturn.getObjVersion()));
                                isAllUpdate = false;
                            }
                        }

                        if(isAllUpdate){
                            final List<String> list = new ArrayList<String>(Collections2.transform(resp, g -> g.getWoNo()));
                            new DiifsInfoContentValues()
                                    .putStatus(DiIfsStatus.UPLOADED)
                                    .putUploadPending(false)
                                    .update(getContentResolver(), new DiifsInfoSelection()
                                            .wono(new ArrayList<>(Collections2.transform(resp, g -> g.getWoNo())).toArray(new String[list.size()])));
                        }

                        if (receiver != null) {
                            final Bundle result = new Bundle();
                            result.putInt("count", resp.size());
                            receiver.send(~SYNC_UPLOAD_DI_REPORTS, result);
                        }
                        q.next();
                    }, e -> {
                        if (receiver != null) {
                            receiver.send(~SYNC_UPLOAD_DI_REPORTS, null);
                        }
                        LogUtil.error(TAG, "Failed to upload day inspect reports", e);
                        if (!onErrorStop) {
                            q.next();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    q.next();
                }
            });


        }else {
            if (receiver != null) {
                receiver.send(SYNC_UPLOAD_DI_REPORTS, null);
            }
        }


        final CmWorks cmWorks;

        if (hasOption(options, SYNC_UPLOAD_PENDING) && user != null) {
            cmWorks = SyncHelper.collectUploadPendingCmWorksNew(getContentResolver());
        } else {
            cmWorks = null;
        }
        if (cmWorks != null && cmWorks.getCmWorks() != null && cmWorks.getCmWorks().size() > 0) {
            q.add(() -> {
                final List<String> guids = new ArrayList();
                for (CmWork g : cmWorks.getCmWorks()) {
                    guids.add(g.getGuid());
                }

                new CmWorkContentValues()
                        .putUploadPending(true)
                        .update(getContentResolver(), new CmWorkSelection().guid(guids.toArray(new String[guids.size()])));
                try {
                    cmService.listNewCMWorkIDs(cmWorks).subscribe(resp -> {
                        for (CmWorkID no : resp) {


                            new CmWorkContentValues()
                                    .putOrderId(no.getOrderId())
                                    .putUploadPending(false)
                                    .putStatus(CmWorkStatus.RELEASED)
                                    .update(getContentResolver(), new CmWorkSelection().guid(no.getGuid()));
                        }
                        if (receiver != null) {
                            final Bundle result = new Bundle();
                            result.putInt("count", resp.size());
                            receiver.send(SYNC_UPLOAD_PENDING, result);
                        }
                        q.next();
                    }, e -> {
                        if (receiver != null) {
                            receiver.send(~SYNC_UPLOAD_PENDING, null);
                        }
                         LogUtil.error(TAG, "Failed to upload cm work NEW", e);
                        if (!onErrorStop) {
                            q.next();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    q.next();
                }
            });
        } else {
            if (receiver != null) {
                receiver.send(SYNC_UPLOAD_CM_REPORTS, null);
            }
        }


        if (hasOption(options, SYNC_UPLOAD_LOC) && user != null  && userName != null /*&& userName.contains("XL")*/) {
            q.add(() -> {
                if (willLog(TAG)) {
                    LogUtil.debug(TAG, "upLoad Location");
                }

                LocationInfos locationInfos = new LocationInfos();
                List<LocationInfo> locationInfoList = new ArrayList<LocationInfo>();

                LocationInfo locationInfo = new LocationInfo();
                locationInfo.setUserId(user);
                locationInfo.setUserName(userName);

                String time = new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date(System.currentTimeMillis()));
                locationInfo.setTime(time);

                String imei = TService.imei;
                String lat = TService.lat;
                String lon = TService.lon;

                locationInfo.setImei(imei);
                locationInfo.setLat(lat);
                locationInfo.setLon(lon);

                locationInfoList.add(locationInfo);
                locationInfos.setLocationInfos(locationInfoList);

                try {
                    userService.getLocationInfo(locationInfos).subscribe(resp -> {
                        if (willLog(TAG)) {
                            LogUtil.debug(TAG, "Success to upload location");
                        }
                        q.next();
                    }, e -> {
                        if (receiver != null) {
                            receiver.send(SYNC_UPLOAD_LOC, null);
                        }
                        LogUtil.error(TAG, "Failed to upload location ", e);
                        if (!onErrorStop) {
                            q.next();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    q.next();
                }
            });
        }

        if (hasOption(options, SYNC_DOWNLOAD_BASE_DATA) && user != null) {
            final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


            q.add(() ->{

                List<String> uploadedFile = new ArrayList<String>();
                PictureCursor pictureCursor = new PictureSelection()
                        .keyId("110")
                        .and()
                        .type("crash")
                        .query(getApplicationContext());
                while(pictureCursor.moveToNext()){
                    uploadedFile.add(pictureCursor.getPictureId());
                }

                String lat = TService.lat;
                String lon = TService.lon;

                String upFileAdd = null;
                long current = System.currentTimeMillis();

                if(lat != null && lon != null){
                    upFileAdd = lat + "_" + lon;
                }else{
                    upFileAdd = "no_add_info";
                }
                String dateT = current + "";
                String serverPath = NetUtil.getUploadFileInfoServerPath(upFileAdd, dateT) + File.separator;
                String crashLogDir = Environment.getExternalStorageDirectory().getPath() + "/AFC_Log_Crash/";

                File crashFile = new File(crashLogDir);
                File[] crashLogs = crashFile.listFiles();

                if(crashLogs != null){
                    for(File file : crashLogs){
                        if(!uploadedFile.contains(file.getAbsolutePath())){
                            FileInfoUploadUtil fileInfoUploadUtil = new FileInfoUploadUtil(file.getAbsolutePath(), serverPath + file.getName(), getApplicationContext());
                            fileInfoUploadUtil.uploadFile();
                        }
                    }
                }
                q.next();
            });


            q.add(() -> {
                if (willLog(TAG)) {
                    LogUtil.debug(TAG, "Downloading users");
                }

                final String lastSyncTime = String.valueOf(prefs.getLong(PREF_USERS_LAST_SYNC_TIME, 0));
                try {
                    deviceService.listUsers(user, lastSyncTime).subscribe(resp -> {
                        LogUtil.debug(TAG, String.format("Downloaded %1$d users", resp.size()));

                        SyncHelper.saveUsers(getContentResolver(), resp);
                        if (receiver != null) {
                            receiver.send(SYNC_DOWNLOAD_BASE_DATA, null);
                        }
                        prefs.edit().putLong(PREF_USERS_LAST_SYNC_TIME, ((AfcApplication) getApplication()).getServerDate().getTime()).commit();
                        q.next();
                    }, e -> {
                        if (receiver != null) {
                            receiver.send(~SYNC_DOWNLOAD_BASE_DATA, null);
                        }
                         LogUtil.error(TAG, "Failed to download users", e);
                        if (!onErrorStop) {
                            q.next();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    q.next();
                }
            });


            q.add(() -> {
                if (willLog(TAG)) {
                    LogUtil.debug(TAG, "Downloading devices");
                }

                final String lastSyncTime = String.valueOf(prefs.getLong(PREF_DEVICES_LAST_SYNC_TIME, 0));
                try {
                    deviceService.listDevices(user, lastSyncTime).subscribe(resp -> {
                        if (willLog(TAG)) {
                            LogUtil.debug(TAG, String.format("Downloaded %1$d devices", resp.size()));
                        }
                        SyncHelper.saveDevices(getContentResolver(), resp);
                        prefs.edit().putLong(PREF_DEVICES_LAST_SYNC_TIME, ((AfcApplication) getApplication()).getServerDate().getTime()).commit();
                        q.next();
                    }, e -> {
                        if (receiver != null) {
                            receiver.send(~SYNC_DOWNLOAD_BASE_DATA, null);
                        }
                         LogUtil.error(TAG, "Failed to download devices", e);
                        if (!onErrorStop) {
                            q.next();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    q.next();
                }
            });


            q.add(() -> {
                if (willLog(TAG)) {
                    LogUtil.debug(TAG, "Downloading Physics");
                }
                final String lastSyncTime = String.valueOf(prefs.getLong(PREF_DEVICES_LAST_SYNC_TIME, 0));
                try {
                    deviceService.listPhysics(user, lastSyncTime).subscribe(resp -> {
                        if (willLog(TAG)) {
                            LogUtil.debug(TAG, String.format("Downloaded %1$d Physics", resp.size()));
                        }
                        SyncHelper.savePhysics(getContentResolver(), resp);
                        prefs.edit().putLong(PREF_PHYSIC_LAST_SYNC_TIME, ((AfcApplication) getApplication()).getServerDate().getTime()).commit();
                        q.next();
                    }, e -> {
                        if (receiver != null) {
                            receiver.send(~SYNC_DOWNLOAD_BASE_DATA, null);
                        }
                         LogUtil.error(TAG, "Failed to download Physics", e);
                        if (!onErrorStop) {
                            q.next();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    q.next();
                }
            });


            q.add(() -> {
                if (willLog(TAG)) {
                    LogUtil.debug(TAG, "Downloading params");
                }

                final String lastSyncTime = String.valueOf(prefs.getLong(PREF_PARAMS_LAST_SYNC_TIME, 0));
                try {
                    deviceService.listParamValues(lastSyncTime, user).subscribe(resp -> {
                        if (willLog(TAG)) {
                            LogUtil.debug(TAG, String.format("Downloaded %1$d params", resp.size()));
                        }
                        SyncHelper.saveParams(getContentResolver(), resp);
                        Date date = ((AfcApplication) getApplication()).getServerDate();
                        prefs.edit().putLong(PREF_PARAMS_LAST_SYNC_TIME, ((AfcApplication) getApplication()).getServerDate().getTime()).commit();
                        q.next();
                    }, e -> {
                        if (receiver != null) {
                            receiver.send(~SYNC_DOWNLOAD_BASE_DATA, null);
                        }
                         LogUtil.error(TAG, "Failed to download params", e);
                        if (!onErrorStop) {
                            q.next();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    q.next();
                }
            });

        }

        if (hasOption(options, SYNC_DOWNLOAD_CM_WORKS) && user != null) {
            q.add(() -> {
                if (willLog(TAG)) {
                    LogUtil.debug(TAG, "Downloading cm works");
                }

                cmService.listQueryCMWorkIDs(user).subscribe(resp -> {
                    if (resp.size() == 0) {
                        if (receiver != null) {
                            receiver.send(SYNC_DOWNLOAD_CM_WORKS, null);
                        }
                        q.next();
                        return;
                    }

                    final List<CmWorkID> downloaded = new ArrayList<>();
                    final CmWorkCursor cur = new CmWorkSelection().orderId(mapCmWorkID(resp)).query(getContentResolver());
                    try {
                        while (cur.moveToNext()) {
                            CmWorkID localCmWorkID = new CmWorkID();
                            localCmWorkID.setGuid(cur.getGuid());
                            localCmWorkID.setOrderId(cur.getOrderId());

                            downloaded.add(localCmWorkID);
                        }
                    } finally {
                        cur.close();
                    }

                    final List<CmWorkID> cmworkids = collectionMinusByCMOrderId(resp, downloaded);
                    if (cmworkids.size() > 0) {
                        final Counter<Integer> counter = new Counter<Integer>() {
                            private int works;

                            @Override
                            public void onStep(int count, Integer data) {
                                works += Numbers.intValue(data);
                                if (count == cmworkids.size()) {
                                    if (receiver != null) {
                                        final Bundle bundle = new Bundle();
                                        bundle.putInt("count", works);
                                        receiver.send(SYNC_DOWNLOAD_CM_WORKS, bundle);
                                    }
                                    q.next();
                                }
                            }
                        };

                        for (CmWorkID c : cmworkids) {
                            List<CmWorkID> list = Arrays.asList(c);
                            CmWorkIDs list1 = new CmWorkIDs();
                            list1.setCmWorkIDs(list);
                            cmService.postCMWorks(list1).subscribe(works -> {
                                if (willLog(TAG)) {
                                    LogUtil.debug(TAG, String.format("Download %1$d cm works", works.size()));
                                }
                                SyncHelper.saveCmWorks(getContentResolver(), user, works);
                                SyncHelper.saveCmWorksMaterialInfos(getContentResolver(), user, works);
                                counter.step(works.size());
                            }, e -> {
                                LogUtil.debug(TAG, "Failed to download a cm");
                                counter.step(0);
                            });
                        }
                    } else {
                        q.next();
                    }
                }, e -> {
                    if (receiver != null) {
                        receiver.send(~SYNC_DOWNLOAD_CM_WORKS, null);
                    }
                    LogUtil.debug(TAG, "Failed to download cm works", e);
                    if (!onErrorStop) {
                        q.next();
                    }
                });
            });
        }


        if (hasOption(options, SYNC_DOWNLOAD_PM_WORKS) && user != null) {
            q.add(() -> {
                if (willLog(TAG)) {
                    LogUtil.debug(TAG, "Downloading pm works");
                }

                pmService.listWorkGuids(user).subscribe(resp -> {
                    if (resp.size() == 0) {
                        if (receiver != null) {
                            receiver.send(SYNC_DOWNLOAD_PM_WORKS, null);
                        }
                        q.next();
                        return;
                    }


                    final List<PmWorkID> downloaded = new ArrayList<PmWorkID>();
                    final PmifsWorkCursor cur = new PmifsWorkSelection().orderId(mapPmWorkID(resp)).query(getContentResolver());
                    try {
                        while (cur.moveToNext()) {
                            PmWorkID localPmWorkID = new PmWorkID();
                            localPmWorkID.setGuid(cur.getGuid());
                            localPmWorkID.setOrderId(cur.getOrderId());

                            downloaded.add(localPmWorkID);
                        }
                    } finally {
                        cur.close();
                    }

                    final List<PmWorkID> pmworkids = collectionMinusByPMOrderId(resp, downloaded);
                    if (pmworkids.size() > 0) {
                        final Counter<Integer> counter = new Counter<Integer>() {
                            private int works;

                            @Override
                            public void onStep(int count, Integer data) {
                                works += Numbers.intValue(data);
                                if (count == pmworkids.size()) {
                                    if (receiver != null) {
                                        final Bundle bundle = new Bundle();
                                        bundle.putInt("count", works);
                                        receiver.send(SYNC_DOWNLOAD_PM_WORKS, bundle);
                                    }
                                    q.next();
                                }
                            }
                        };
                        for (PmWorkID c : pmworkids) {
                            List<PmWorkID> list = Arrays.asList(c);
                            PmWorkIDs list1 = new PmWorkIDs();
                            list1.setPmWorkIDs(list);
                            pmService.listWorks(list1).subscribe(works -> {
                                if (willLog(TAG)) {
                                    LogUtil.debug(TAG, String.format("Downloaded %1$d pm works", works.size()));
                                }
                                SyncHelper.savePmWorks(getContentResolver(), user, works);
                                SyncHelper.savePmWorksMaterialInfos(getContentResolver(), user, works);
                                counter.step(works.size());
                            }, e -> {
                                LogUtil.debug(TAG, "Failed to download a pm");
                                counter.step(0);
                            });
                        }
                    } else {
                        q.next();
                    }
                }, e -> {
                    if (receiver != null) {
                        receiver.send(~SYNC_DOWNLOAD_PM_WORKS, null);
                    }
                     LogUtil.error(TAG, "Failed to download pm works", e);
                    if (!onErrorStop) {
                        q.next();
                    }
                });
            });
        }


        if (hasOption(options, SYNC_DOWNLOAD_PM_WORKS_DEL) && user != null) {
            q.add(() -> {
                if (willLog(TAG)) {
                    LogUtil.debug(TAG, "Deleteing PM");
                }

                PMDeleteLists pmDeleteListObj = new PMDeleteLists();
                List<PmWorkDelete> pmWorkDeleteList = new ArrayList<PmWorkDelete>();

                PmWorkDelete pmWorkDelete = new PmWorkDelete();
                pmWorkDelete.setUserid(user);

                String imei = TService.imei;
                String lat = TService.lat;
                String lon = TService.lon;

                pmWorkDelete.setUserIMEI(imei);
                pmWorkDelete.setUserLat(lat);
                pmWorkDelete.setUserLon(lon);

                List<PmWorkID> localPM = new ArrayList<PmWorkID>();


                final PmifsWorkCursor cur = new PmifsWorkSelection().statusNot(PmifsWorkStatus.WORKDONEUPLOAD).query(getContentResolver());
                try {
                    while (cur.moveToNext()) {
                        localPM.add(getLocalNotUploadPM(cur));
                    }
                } finally {
                    cur.close();
                }


                pmWorkDelete.setPmWorkIDs(localPM);
                pmWorkDeleteList.add(pmWorkDelete);
                pmDeleteListObj.setPmWorkDeletes(pmWorkDeleteList);

                try {
                    pmService.postListDeletePM(pmDeleteListObj).subscribe(resp -> {
                        if (willLog(TAG)) {
                            LogUtil.debug(TAG, String.format("Delete %1$d pm", resp.size()));
                        }
                        SyncHelper.deletePM(getContentResolver(), resp);
                        q.next();
                    }, e -> {
                        if (receiver != null) {
                            receiver.send(SYNC_DOWNLOAD_PM_WORKS_DEL, null);
                        }
                         LogUtil.error(TAG, "Failed to Delete pm or Not PM Must be Delete ", e);
                        if (!onErrorStop) {
                            q.next();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    q.next();
                }
            });
        }


        
        if (hasOption(options, SYNC_DOWNLOAD_CM_WORKS_DEL) && user != null) {
            q.add(() -> {
                if (willLog(TAG)) {
                    LogUtil.debug(TAG, "Deleteing CM");
                }

                CMDeleteLists cmDeleteListObj = new CMDeleteLists();
                List<CmWorkDelete> cmWorkDeleteList = new ArrayList<CmWorkDelete>();

                CmWorkDelete cmWorkDelete = new CmWorkDelete();
                cmWorkDelete.setUserid(user);

                String imei = TService.imei;
                String lat = TService.lat;
                String lon = TService.lon;

                cmWorkDelete.setUserIMEI(imei);
                cmWorkDelete.setUserLat(lat);
                cmWorkDelete.setUserLon(lon);

                List<CmWorkID> localCM = new ArrayList<CmWorkID>();


                final CmWorkCursor cur = new CmWorkSelection().statusNot(CmWorkStatus.WORKDONEUPLOAD).query(getContentResolver());
                try {
                    while (cur.moveToNext()) {
                        localCM.add(getLocalNotUploadCM(cur));
                    }
                } finally {
                    cur.close();
                }


                cmWorkDelete.setCmWorkIDs(localCM);
                cmWorkDeleteList.add(cmWorkDelete);
                cmDeleteListObj.setCmWorkDeletes(cmWorkDeleteList);

                try {
                    cmService.postListDeleteCM(cmDeleteListObj).subscribe(resp -> {
                        if (willLog(TAG)) {
                            LogUtil.debug(TAG, String.format("Delete %1$d cm", resp.size()));
                        }
                        SyncHelper.deleteCM(getContentResolver(), resp);
                        q.next();
                    }, e -> {
                        if (receiver != null) {
                            receiver.send(SYNC_DOWNLOAD_CM_WORKS_DEL, null);
                        }
                        LogUtil.error(TAG, "Failed to Delete pm or Not CM Must be Delete ", e);
                        if (!onErrorStop) {
                            q.next();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    q.next();
                }
            });
        }


        
        if (hasOption(options, SYNC_DOWNLOAD_PR_WORKS_DEL) && user != null) {
            q.add(() -> {
                if (willLog(TAG)) {
                    LogUtil.debug(TAG, "Deleteing PR");
                }

                PRDeleteLists prDeleteListObj = new PRDeleteLists();
                List<PRWorkDelete> prWorkDeleteList = new ArrayList<PRWorkDelete>();

                PRWorkDelete prWorkDelete = new PRWorkDelete();
                prWorkDelete.setUserid(user);

                String imei = TService.imei;
                String lat = TService.lat;
                String lon = TService.lon;

                prWorkDelete.setUserIMEI(imei);
                prWorkDelete.setUserLat(lat);
                prWorkDelete.setUserLon(lon);

                String localReportNo = "";


                PankuReportSelection pankuReportSelection = new PankuReportSelection().userId(user);
                PankuReportCursor pankuReportCursor = pankuReportSelection.query(getContentResolver());

                try {
                    while (pankuReportCursor.moveToNext()) {
                        localReportNo = pankuReportCursor.getReportNo();
                    }
                } finally {
                    pankuReportCursor.close();
                }


                prWorkDelete.setUserPR(localReportNo);
                prWorkDeleteList.add(prWorkDelete);
                prDeleteListObj.setPrWorkDeletes(prWorkDeleteList);

                try {
                    deviceService.postListDeletePR(prDeleteListObj).subscribe(resp -> {
                        if (willLog(TAG)) {
                            LogUtil.debug(TAG, String.format("Delete %1$d pr", resp.size()));
                        }
                        SyncHelper.deletePR(getContentResolver(), getApplicationContext(), resp);
                        q.next();
                    }, e -> {
                        if (receiver != null) {
                            receiver.send(SYNC_DOWNLOAD_PR_WORKS_DEL, null);
                        }
                        LogUtil.error(TAG, "Failed to Delete pm or Not PR Must be Delete ", e);
                        if (!onErrorStop) {
                            q.next();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    q.next();
                }
            });
        }



        if (hasOption(options, SYNC_DOWNLOAD_DI_WORKS) && user != null) {
            q.add(() -> {
                if (willLog(TAG)) {
                    LogUtil.debug(TAG, "Downloading di works");
                }



                try {
                    SyncHelper.delNotCurrentDateDiifis(getContentResolver());
                } catch (Exception e) {
                    e.printStackTrace();
                }


                dayInspectService.queryDayInspectWorkList(user).subscribe(resp -> {
                    if (resp.size() == 0) {
                        if (receiver != null) {
                            receiver.send(SYNC_DOWNLOAD_DI_WORKS, null);
                        }
                        q.next();
                        return;
                    }

                    final List<DayInspectWork> downloaded = new ArrayList<>();
                    final DiifsInfoCursor cur = new DiifsInfoSelection().sign(user).and().statusNot(DiIfsStatus.UPLOADED).query(getContentResolver());
                    try {
                        while (cur.moveToNext()) {
                            DayInspectWork localDiWork = new DayInspectWork();
                            localDiWork.setWoNo(cur.getWono());
                            downloaded.add(localDiWork);
                        }
                    } finally {
                        cur.close();
                    }


                    final List<DayInspectWork> needDownloadDayInspectWorks = collectionMinusByDiOrderId(resp, downloaded);
                    if (needDownloadDayInspectWorks.size() > 0) {
                        final Counter<Integer> counter = new Counter<Integer>() {
                            private int works;

                            @Override
                            public void onStep(int count, Integer data) {
                                works += Numbers.intValue(data);
                                if (count == needDownloadDayInspectWorks.size()) {
                                    if (receiver != null) {
                                        final Bundle result = new Bundle();
                                        result.putInt("count", works);
                                        receiver.send(SYNC_DOWNLOAD_DI_WORKS, result);
                                    }
                                    q.next();
                                }
                            }
                        };

                        DayInspectWorkInfoList2Level dayInspectWorkInfoList2Level = new DayInspectWorkInfoList2Level();

                        UserInfo userInfo = new UserInfo();
                        String imei = TService.imei;
                        String lat = TService.lat;
                        String lon = TService.lon;

                        userInfo.setUserId(userId);
                        userInfo.setUserIMEI(imei);
                        userInfo.setUserLat(lat);
                        userInfo.setUserLon(lon);
                        dayInspectWorkInfoList2Level.setUserInfo(userInfo);


                        dayInspectWorkInfoList2Level.setDiws(needDownloadDayInspectWorks);

                        DayInspectWorkInfoList1Level dayInspectWorkInfoList1Level = new DayInspectWorkInfoList1Level();
                        dayInspectWorkInfoList1Level.setDayInspectWorkInfoList2Level(dayInspectWorkInfoList2Level);

                        dayInspectService.getDayInspectWorkList(dayInspectWorkInfoList1Level).subscribe(works -> {
                            if (willLog(TAG)) {
                                LogUtil.debug(TAG, String.format("Downloaded %1$d day inspect works", works.size()));
                            }

                            SyncHelper.saveDiWorkInfos(getContentResolver(), works, user);
                            counter.step(works.size());
                            q.next();
                        }, e -> {
                            LogUtil.debug(TAG, "Failed to download a day inspect");
                            counter.step(0);
                            q.next();
                        });

                        DayInspectWorks dayInspectWorks = new DayInspectWorks();
                        dayInspectWorks.setDiws(needDownloadDayInspectWorks);
                        dayInspectService.getDayInspectWorkPMList(dayInspectWorks).subscribe(works -> {
                            if (willLog(TAG)) {
                                LogUtil.debug(TAG, String.format("Downloaded %1$d day inspect PM works", works.size()));
                            }

                            SyncHelper.saveDiWorkPMs(getContentResolver(), works);
                            counter.step(works.size());
                            q.next();
                        }, e -> {
                            LogUtil.debug(TAG, "Failed to download day inspect  PM");
                            counter.step(0);
                            q.next();
                        });

                        Set<String> pmNoInTab_diifs_pm = new HashSet<String>();
                        Set<String> pmNoInTab_pm_item = new HashSet<String>();

                        final DiifsPmCursor curInDiIfsPM = new DiifsPmSelection().query(getContentResolver());
                        try {
                            while (curInDiIfsPM.moveToNext()) {
                                pmNoInTab_diifs_pm.add(curInDiIfsPM.getPmno());
                            }
                        } finally {
                            cur.close();
                        }


                        final DiifsPmItemCursor curInPMItem = new DiifsPmItemSelection().query(getContentResolver());
                        try {
                            while (curInPMItem.moveToNext()) {
                                pmNoInTab_pm_item.add(curInPMItem.getPmno());
                            }
                        } finally {
                            cur.close();
                        }



                        final List<DayInspectWorkPMOnly> needDownloadPmItems = collectionMinusByPMNo(pmNoInTab_diifs_pm, pmNoInTab_pm_item);
                        if (needDownloadPmItems.size() > 0) {
                            DayInspectWorkPMOnlys dayInspectWorkPMOnlys = new DayInspectWorkPMOnlys();
                            dayInspectWorkPMOnlys.setDiwPMs(needDownloadPmItems);

                            dayInspectService.getDayInspectWorkPMItemList(dayInspectWorkPMOnlys).subscribe(works -> {
                                if (willLog(TAG)) {
                                    LogUtil.debug(TAG, String.format("Downloaded %1$d day inspect PM Item works", works.size()));
                                }

                                SyncHelper.saveDiWorkPMItems(getContentResolver(), works);
                                counter.step(works.size());
                                q.next();
                            }, e -> {
                                LogUtil.debug(TAG, "Failed to download day inspect  PM Item");
                                counter.step(0);
                                q.next();
                            });
                        }


                        SyncHelper.composeDiWorkPMItems(getContentResolver());

                    } else {
                        q.next();
                    }
                }, e -> {
                    if (receiver != null) {
                        receiver.send(~SYNC_DOWNLOAD_DI_WORKS, null);
                    }
                    LogUtil.error(TAG, "Failed to download day inspect works", e);
                    if (!onErrorStop) {
                        q.next();
                    }
                });


            });
        }

        if (receiver != null) {
            q.add(() -> {
                LogUtil.debug(TAG, "Sync finished");
                setLogicName2Table();
                receiver.send(0, null);
            });
        }
        q.next();

    }

    private PmWorkID getLocalNotUploadPM(PmifsWorkCursor cur) {
        PmWorkID pmWorkID = new PmWorkID();
        pmWorkID.setOrderId(cur.getOrderId());
        pmWorkID.setGuid(cur.getGuid());
        return pmWorkID;
    }

    private CmWorkID getLocalNotUploadCM(CmWorkCursor cur) {
        CmWorkID cmWorkID = new CmWorkID();
        cmWorkID.setOrderId(cur.getOrderId());
        cmWorkID.setGuid(cur.getGuid());
        return cmWorkID;
    }

    private List<CmWorkID> collectionMinusByCMOrderId(final List<CmWorkID> serverCMTask, final List<CmWorkID> localCMTask){

        List<CmWorkID> list = new ArrayList<>();
        if(localCMTask != null && localCMTask.size() < 1){
            list = serverCMTask;

        }else{
            try {
                boolean isCMNotRepeat =  serverCMTask.removeAll(localCMTask);

                if(isCMNotRepeat){
                    list = serverCMTask;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    private List<PmWorkID> collectionMinusByPMOrderId(final List<PmWorkID> serverPMTask, final List<PmWorkID> localPMTask) {
        List<PmWorkID> list = new ArrayList<>();

        if(localPMTask != null && localPMTask.size() < 1){
            list = serverPMTask;

        }else{
            try {
                boolean isPMNotRepeat =  serverPMTask.removeAll(localPMTask);

                if(isPMNotRepeat){
                    list = serverPMTask;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return list;
    }


    private List<DayInspectWork> collectionMinusByDiOrderId(final List<DayInspectWork> serverDiTask, final List<DayInspectWork> localDiTask) {
        List<DayInspectWork> list = new ArrayList<>();


        if(localDiTask != null && localDiTask.size() < 1){
            list = serverDiTask;

        }else{
            try {


                boolean isPMNotRepeat =  serverDiTask.removeAll(localDiTask);

                if(isPMNotRepeat){
                    list = serverDiTask;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    private List<DayInspectWorkPMOnly> collectionMinusByPMNo(final Set<String> pmNoInTab_diifs_pm, final Set<String> pmNoInTab_pm_item) {
        Set<String> list = new HashSet<>();
        List<DayInspectWorkPMOnly> listDiPm = new ArrayList<>();


        if(pmNoInTab_pm_item != null && pmNoInTab_pm_item.size() < 1){
            list = pmNoInTab_diifs_pm;

        }else{
            try {


                boolean isPMNotRepeat =  pmNoInTab_diifs_pm.removeAll(pmNoInTab_pm_item);

                if(isPMNotRepeat){
                    list = pmNoInTab_diifs_pm;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for(String s : list){
            DayInspectWorkPMOnly dayInspectWorkPMOnly = new DayInspectWorkPMOnly();
            dayInspectWorkPMOnly.setPmNo(s);
            dayInspectWorkPMOnly.setWoNo(getInfoInDiifsPm(s, 0));
            dayInspectWorkPMOnly.setPhysicCode(getInfoInDiifsPm(s, 1));
            listDiPm.add(dayInspectWorkPMOnly);
        }

        return listDiPm;
    }



    private String getInfoInDiifsPm(String pmNo, int infoType){
        String backInfo = "";

        DiifsPmSelection diifsPmSelection = new DiifsPmSelection().pmno(pmNo);
        DiifsPmCursor diifsPmCursor = diifsPmSelection.query(getContentResolver());
        try {
            while (diifsPmCursor.moveToNext()) {
                if(infoType == 0){
                    backInfo = diifsPmCursor.getWono();
                }else if(infoType == 1){
                    backInfo = diifsPmCursor.getPhysiccode();
                }
            }
        } finally {
            diifsPmCursor.close();
        }

        return backInfo;
    }



    private String[] mapCmWorkID(List<CmWorkID> cmWorkIDs) {
        final List<String> list = new ArrayList<>(cmWorkIDs.size());
        for (CmWorkID g : cmWorkIDs) {
            list.add(g.getOrderId());
        }
        return list.toArray(new String[list.size()]);
    }

    private String[] mapPmWorkID(List<PmWorkID> pmWorkIDs) {
        final List<String> list = new ArrayList<>(pmWorkIDs.size());
        for (PmWorkID g : pmWorkIDs) {
            list.add(g.getOrderId());
        }
        return list.toArray(new String[list.size()]);
    }

    public static boolean hasOption(int options, int option) {
        return (options & option) == option;
    }


    private void deleteOutdatedData() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -2);
        cal.set(Calendar.DATE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long time = cal.getTimeInMillis();

        deleteCM(time);
        deletePmWorksBefore(time);
        new PmifsWorkSelection().operationEndTimeLt(time).delete(getContentResolver());
        new WorkOrderSelection().lastModifiedLt(time).delete(getContentResolver());
        new DiWorkSelection().endTimeLt(time).delete(getContentResolver());
    }

    private void deleteCM(long time) {
        final CmWorkSelection sel = new CmWorkSelection().applyFTimeLt(time);
        final CmWorkCursor cur = sel.query(getContentResolver());

        List<CmWorkID> ids = new ArrayList<>(cur.getCount());
        CmWorkID t = new CmWorkID();
        while (cur.moveToNext()) {
            t.setOrderId(cur.getOrderId());
            t.setGuid(cur.getGuid());
            ids.add(t);
        }
        cur.close();

        for (CmWorkID i : ids) {
            deleteCMWorkMaterialOrder(i);
        }
        sel.delete(getContentResolver());
    }

    private void deleteCMWorkMaterialOrder(CmWorkID i) {
        final CmMaterialSelection csel = new CmMaterialSelection().cmOrderId(i.getOrderId());
        final CmMaterialCursor ccur = csel.query(getContentResolver());

        List<String> ids = new ArrayList<>(ccur.getCount());
        while (ccur.moveToNext()) {
            ids.add(ccur.getMaterialOrderId());
        }
        ccur.close();

        if (ids.size() > 0) {
            new CmMaterialRowSelection().cmOrderId(i.getOrderId()).and()
                    .materialOrderId(ids.toArray(new String[ids.size()]))
                    .delete(getContentResolver());
            csel.delete(getContentResolver());
        }
    }

    private void deletePmWorksBefore(long time) {
        final PmifsWorkSelection sel = new PmifsWorkSelection().operationEndTimeLt(time);
        final PmifsWorkCursor cur = sel.query(getContentResolver());
        List<String> guids = new ArrayList<>(cur.getCount());
        while (cur.moveToNext()) {
            guids.add(cur.getGuid());
        }
        cur.close();
        if (guids.size() > 0) {
            new PmifsWorkItemSelection().workGuid(guids.toArray(new String[guids.size()])).delete(getContentResolver());
            sel.delete(getContentResolver());
        }
    }

    private void setLogicName2Table(){
        PmifsWorkSelection pmifsWorkSelection = new PmifsWorkSelection();
        PmifsWorkCursor cursor = pmifsWorkSelection.query(getContentResolver());
        while(cursor.moveToNext()){
            String logicCode = cursor.getDeviceLogicCode();

            DeviceSelection deviceSelection = new DeviceSelection().code(logicCode);
            DeviceCursor deviceCursor = deviceSelection.query(getContentResolver());
            while(deviceCursor.moveToNext()){
                String logicName = deviceCursor.getName();
                PmifsWorkContentValues pcvLogicName = new PmifsWorkContentValues();
                pcvLogicName.putDeviceLogicName(logicName);
                getContentResolver().update(PmifsWorkColumns.CONTENT_URI, pcvLogicName.values(), "device_logic_code = ?", new String[] {logicCode});
            }
        }
    }

}
