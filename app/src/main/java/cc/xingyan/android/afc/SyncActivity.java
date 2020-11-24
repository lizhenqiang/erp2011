/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.app.BaseActivity;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.service.SyncService;
import cc.xingyan.android.afc.widget.SyncItemView;

/**
 * Created by San on 9/30/15.
 */
public class SyncActivity extends BaseActivity {
    private static final int REQUEST_SYNC = 0;
    public static final String EXTRA_SYNC_OPTIONS = "sync_options";
    public static final String EXTRA_AUTO_FINISH = "auto_finish";
    public static final String EXTRA_RESET_AUTO_SYNC = "reset_auto_sync";
    String sourcePage = "";

    @Bind(R.id.sync_items_container) LinearLayout syncItemsContainer;
    @Bind(R.id.ok) TextView okButton;
    @Bind(R.id.failed) TextView failedButton;

    @Inject
    Authenticator mAuthenticator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);

        setContentView(R.layout.activity_sync);
        setFinishOnTouchOutside(false);

        failedButton.setVisibility(View.INVISIBLE);
        okButton.setVisibility(View.GONE);

        final int options = getIntent().getIntExtra(EXTRA_SYNC_OPTIONS, SyncService.SYNC_ALL);
        sourcePage = getIntent().getStringExtra("source_page");

        if (SyncService.hasOption(options, SyncService.SYNC_UPLOAD_WORK_ORDERS)) {
            syncItemsContainer.addView(newSyncItemView(SyncService.SYNC_UPLOAD_WORK_ORDERS, R.drawable.ic_workorder_black_24dp, "上传快速工单"), 0);
        }

        if (SyncService.hasOption(options, SyncService.SYNC_UPLOAD_PM_REPORTS)) {
            syncItemsContainer.addView(newSyncItemView(SyncService.SYNC_UPLOAD_PM_REPORTS, R.drawable.ic_pm_black_24dp, "上传PM工单"), 0);
        }

        if (SyncService.hasOption(options, SyncService.SYNC_UPLOAD_DI_REPORTS)) {
            syncItemsContainer.addView(newSyncItemView(SyncService.SYNC_UPLOAD_DI_REPORTS, R.drawable.ic_dayinspect_black_24dp, "上传日巡检"), 0);
        }

        if (SyncService.hasOption(options, SyncService.SYNC_UPLOAD_CM_REPORTS)) {
            syncItemsContainer.addView(newSyncItemView(SyncService.SYNC_UPLOAD_CM_REPORTS, R.drawable.ic_cm_black_24dp, "上传CM工单"), 0);
        }

        if (SyncService.hasOption(options, SyncService.SYNC_DOWNLOAD_ALL)) {
            syncItemsContainer.addView(newSyncItemView(SyncService.SYNC_DOWNLOAD_ALL, R.drawable.ic_file_download_black_24dp, "下载基础数据和工单任务"), 0);
        }

        final Intent service = new Intent(this, SyncService.class);
        service.putExtra(SyncService.ON_ERROR_STOP, true);
        service.putExtra(SyncService.SYNC_OPTIONS, options);
        try {
            service.putExtra(SyncService.RESULT_RECEIVER, new ResultReceiver(new Handler()) {
                @Override
                protected void onReceiveResult(int resultCode, Bundle resultData) {
                    switch (resultCode) {
                        case 0:
                            setResult(RESULT_OK);
                            if (getIntent().getBooleanExtra(EXTRA_AUTO_FINISH, false)) {
                                finish();
                            } else {
                                if(failedButton != null){

                                    failedButton.setVisibility(View.GONE);
                                }

                                if(okButton != null){
                                    okButton.setVisibility(View.VISIBLE);
                                }

                            }
                            break;

                        case SyncService.SYNC_UPLOAD_WORK_ORDERS:
                            setSynItemStatus(SyncService.SYNC_UPLOAD_WORK_ORDERS, SyncItemView.STATUS_DONE);
                            break;
                        case ~SyncService.SYNC_UPLOAD_WORK_ORDERS:
                            setSynItemStatus(SyncService.SYNC_UPLOAD_WORK_ORDERS, SyncItemView.STATUS_ERROR);
                            failedButton.setVisibility(View.VISIBLE);
                            okButton.setVisibility(View.GONE);
                            break;

                        case SyncService.SYNC_UPLOAD_PM_REPORTS:
                            setSynItemStatus(SyncService.SYNC_UPLOAD_PM_REPORTS, SyncItemView.STATUS_DONE);
                            break;
                        case ~SyncService.SYNC_UPLOAD_PM_REPORTS:
                            setSynItemStatus(SyncService.SYNC_UPLOAD_PM_REPORTS, SyncItemView.STATUS_ERROR);
                            failedButton.setVisibility(View.VISIBLE);
                            okButton.setVisibility(View.GONE);
                            break;

                        case SyncService.SYNC_UPLOAD_DI_REPORTS:
                            setSynItemStatus(SyncService.SYNC_UPLOAD_DI_REPORTS, SyncItemView.STATUS_DONE);
                            break;
                        case ~SyncService.SYNC_UPLOAD_DI_REPORTS:
                            setSynItemStatus(SyncService.SYNC_UPLOAD_DI_REPORTS, SyncItemView.STATUS_ERROR);
                            failedButton.setVisibility(View.VISIBLE);
                            okButton.setVisibility(View.GONE);
                            break;

                        case SyncService.SYNC_UPLOAD_CM_REPORTS:
                            setSynItemStatus(SyncService.SYNC_UPLOAD_CM_REPORTS, SyncItemView.STATUS_DONE);
                            break;
                        case ~SyncService.SYNC_UPLOAD_CM_REPORTS:
                            setSynItemStatus(SyncService.SYNC_UPLOAD_CM_REPORTS, SyncItemView.STATUS_ERROR);
                            failedButton.setVisibility(View.VISIBLE);
                            okButton.setVisibility(View.GONE);
                            break;

                        case SyncService.SYNC_DOWNLOAD_DI_WORKS:
                            setSynItemStatus(SyncService.SYNC_DOWNLOAD_ALL, SyncItemView.STATUS_DONE);
                            break;
                        case ~SyncService.SYNC_DOWNLOAD_BASE_DATA:
                        case ~SyncService.SYNC_DOWNLOAD_CM_WORKS:
                        case ~SyncService.SYNC_DOWNLOAD_PM_WORKS:
                        case ~SyncService.SYNC_DOWNLOAD_DI_WORKS:
                            setSynItemStatus(SyncService.SYNC_DOWNLOAD_ALL, SyncItemView.STATUS_ERROR);
                            try {
                                failedButton.setVisibility(View.VISIBLE);
                                okButton.setVisibility(View.GONE);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (getIntent().getBooleanExtra(EXTRA_RESET_AUTO_SYNC, false)) {
            service.putExtra(SyncService.RESET_ALARM, true);
        }else{
            service.putExtra(SyncService.STOP_ALARM, true);
        }
        startService(service);
    }

    @OnClick({R.id.ok, R.id.failed}) void onOkClick() {
        if ("MainActivity".equals(sourcePage)){
            final Intent intent = new Intent(this, SyncActivity.class);
            intent.putExtra(SyncActivity.EXTRA_SYNC_OPTIONS, SyncService.SYNC_DOWNLOAD_ALL);
            intent.putExtra(SyncActivity.EXTRA_RESET_AUTO_SYNC, true);
            intent.putExtra(SyncActivity.EXTRA_AUTO_FINISH, true);
            startActivityForResult(intent, REQUEST_SYNC);
            finish();
            mAuthenticator.setAuthenticatedUser(null);
            MainActivity.myMMainActivity.finish();
        } else if ("SyncSettingsActivity".equals(sourcePage)) {

            Intent service = new Intent(this, SyncService.class);
            service.putExtra(SyncService.ON_ERROR_STOP, true);
            service.putExtra(SyncService.RESET_ALARM, true);
            startService(service);
            finish();
        }else {
            final Intent intent = new Intent(this, SyncActivity.class);
            intent.putExtra(SyncActivity.EXTRA_SYNC_OPTIONS, SyncService.SYNC_DOWNLOAD_ALL);
            intent.putExtra(SyncActivity.EXTRA_RESET_AUTO_SYNC, true);
            intent.putExtra(SyncActivity.EXTRA_AUTO_FINISH, true);
            startActivityForResult(intent, REQUEST_SYNC);
            finish();
        }
    }

    private void setSynItemStatus(int itemId, int status) {
        if (syncItemsContainer == null)
            return;

        SyncItemView view = (SyncItemView) syncItemsContainer.findViewById(itemId);
        if (view != null) {
            view.setLoadingStatus(status);
        }
    }

    private View newSyncItemView(int itemId, int icon, String text) {
        final SyncItemView view = new SyncItemView(this);
        view.setIcon(icon);
        view.setText(text);
        view.setId(itemId);
        view.setLoadingStatus(SyncItemView.STATUS_LOADING);
        return view;
    }

}
