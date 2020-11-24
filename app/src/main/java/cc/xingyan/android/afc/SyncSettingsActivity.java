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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.Nullable;

import butterknife.Bind;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.service.SyncService;

/**
 * Created by San on 10/15/15.
 */
public class SyncSettingsActivity extends ThemeActivity {
    
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content, new SyncSettingsFragment())
                    .commit();
        }
    }

    public static class SyncSettingsFragment extends BaseFragment {
        @Bind(R.id.bd_switch) Switch bdSwitch;
        @Bind(R.id.work_order_switch) Switch workOrderSwitch;
        @Bind(R.id.cm_switch) Switch cmSwitch;
        @Bind(R.id.pm_switch) Switch pmSwitch;
        @Bind(R.id.di_switch) Switch diSwitch;

        int options;

        @Nullable @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            options = SyncService.SYNC_ALL;
            return inflater.inflate(R.layout.fragment_sync_settings, container, false);
        }


        @OnCheckedChanged(R.id.bd_switch) void bdSwitchChanged() {
            if (bdSwitch.isChecked()) {
                options |= SyncService.SYNC_DOWNLOAD_ALL;
            } else {
                options ^= SyncService.SYNC_DOWNLOAD_ALL;
            }
        }

        @OnCheckedChanged(R.id.work_order_switch) void workOrderSwitchChanged() {
            if (workOrderSwitch.isChecked()) {
                options |= SyncService.SYNC_UPLOAD_WORK_ORDERS;
            } else {
                options ^= SyncService.SYNC_UPLOAD_WORK_ORDERS;
            }
        }

        @OnCheckedChanged(R.id.cm_switch) void cmSwitchChanged() {
            if (cmSwitch.isChecked()) {
                options |= SyncService.SYNC_UPLOAD_CM_REPORTS;
            } else {
                options ^= SyncService.SYNC_UPLOAD_CM_REPORTS;
            }
        }

        @OnCheckedChanged(R.id.pm_switch) void pmSwitchChanged() {
            if (pmSwitch.isChecked()) {
                options |= SyncService.SYNC_UPLOAD_PM_REPORTS;
            } else {
                options ^= SyncService.SYNC_UPLOAD_PM_REPORTS;
            }
        }

        @OnCheckedChanged(R.id.di_switch) void diSwitchChanged() {
            if (diSwitch.isChecked()) {
                options |= SyncService.SYNC_UPLOAD_DI_REPORTS;
            } else {
                options ^= SyncService.SYNC_UPLOAD_DI_REPORTS;
            }
        }

        @OnClick(R.id.action_sync) void onActionSync() {
            if (options != 0) {
                Intent intent = new Intent(getContext(), SyncActivity.class);
                intent.putExtra(SyncActivity.EXTRA_SYNC_OPTIONS, options);
                intent.putExtra(SyncActivity.EXTRA_RESET_AUTO_SYNC, false);
                intent.putExtra("source_page","SyncSettingsActivity");
                startActivity(intent);
                getActivity().finish();
            }
        }
    }

}
