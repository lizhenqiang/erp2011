/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import butterknife.Bind;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsCursor;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsSelection;
import cc.xingyan.android.afc.provider.workorder.SyncStatus;
import cc.xingyan.android.afc.provider.workorder.WorkOrderColumns;
import cc.xingyan.android.afc.provider.workorder.WorkOrderCursor;

/**
 * Created by San on 9/23/15.
 */
public class WorkOrderViewerFragment extends BaseFragment {

    private static final String ARG_URI = "uri";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Bind(R.id.logic_code_etx_work_order) TextView mDeviceLogicCodeView;
    @Bind(R.id.logic_name_etx_work_order) TextView mDeviceLogicNameView;
    @Bind(R.id.physic_code_etx_work_order) TextView mDevicePhysicCodeView;
    @Bind(R.id.physic_name_etx_work_order) TextView mDevicePhysicNameView;
    @Bind(R.id.fault_description) TextView mFaultDescriptionView;
    @Bind(R.id.fault_type) TextView mFaultTypeView;
    @Bind(R.id.reporter_type) TextView mReporterTypeView;
    @Bind(R.id.fault_note) TextView mFaultNoteView;
    @Bind(R.id.fault_start_time) TextView mFaultStartTimeView;
    @Bind(R.id.fault_cause) TextView mFaultReasonView;
    @Bind(R.id.operation) TextView mOperationView;
    @Bind(R.id.operation_result) TextView mOperationResultView;
    @Bind(R.id.operation_note) TextView mOperationNoteView;
    @Bind(R.id.operator) TextView mOperatorView;
    @Bind(R.id.operation_start_time) TextView mOperationStartTimeView;
    @Bind(R.id.operation_end_time) TextView mOperationEndTimeView;
    @Bind(R.id.operator_name_tv) TextView mOperatorname;
    @Bind(R.id.form_flag) TextView mFormFlagView;

    private Uri mUri;
    private EditorFacade mEditor;
    private SyncStatus mSyncStatus;
    private ContentObserver mContentObserver;

    public static Fragment newInstance(Uri uri) {
        WorkOrderViewerFragment fragment = new WorkOrderViewerFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_URI, uri);
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUri = getArguments().getParcelable(ARG_URI);
    }

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof EditorFacade) {
            mEditor = (EditorFacade) getParentFragment();
        } else if (context instanceof EditorFacade) {
            mEditor = (EditorFacade) context;
        }
    }

    @Override public void onDetach() {
        super.onDetach();
        mEditor = null;
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_work_order_viewer, container, false);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);

        getActivity().setTitle(R.string.title_activity_work_order);

        queryAndBindData();

        getContext().getContentResolver().registerContentObserver(WorkOrderColumns.CONTENT_URI, true, mContentObserver = new ContentObserver(null) {
            @Override public void onChange(boolean selfChange) {
                queryAndBindData();
            }
        });
    }

    private void queryAndBindData() {
        boolean found = false;
        if (mUri != null) {
            WorkOrderCursor c = new WorkOrderCursor(getContext().getContentResolver().query(mUri, null, null, null, null));
            if (c.moveToFirst()) {
                bindData(c);
                found = true;
            }
            c.close();
        }

        if (!found) {
            new AlertDialog.Builder(getContext())
                    .setIcon(R.drawable.ic_info_black_24dp)
                    .setTitle(R.string.error)
                    .setMessage(R.string.error_work_order_not_found)
                    .setPositiveButton(R.string.ok, (button, which) -> {
                        getActivity().finish();
                    })
                    .show();
        }
    }

    @Override public void onDestroy() {
        super.onDestroy();
        getContext().getContentResolver().unregisterContentObserver(mContentObserver);
    }

    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (mSyncStatus == SyncStatus.LOCAL) {
            inflater.inflate(R.menu.work_order_viewer, menu);
        } else if (mSyncStatus == SyncStatus.SYNCHRONIZED) {
            inflater.inflate(R.menu.work_order_viewer_synchronized, menu);
        }
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_delete:
                confirmDelete();
                break;
            case R.id.action_upload:
                startActivity(new Intent(getContext(), SyncActivity.class));
                break;
        }
        return true;
    }

    private void confirmDelete() {
        new AlertDialog.Builder(getContext())
                .setIcon(R.drawable.ic_delete_black_24dp)
                .setTitle(R.string.confirm_delete)
                .setMessage(R.string.message_confirm_delete_work_order)
                .setPositiveButton(R.string.delete, (button, which) -> {
                    if (mEditor != null) {
                        mEditor.delete(mUri);
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    private void bindData(WorkOrderCursor c) {
        if (mSyncStatus != c.getSyncStatus()) {
            mSyncStatus = c.getSyncStatus();
            getActivity().supportInvalidateOptionsMenu();
        }
        if (c.getNo() == null) {
            getActivity().setTitle(R.string.local_work_order);
        } else {
            final String title = getString(R.string.format_title_activity_work_order, c.getNo());
            getActivity().setTitle(title);
        }

        setFieldText(mDevicePhysicCodeView, c.getDeviceCode());
        setFieldText(mDevicePhysicNameView, getDevicePhysicName(c.getDeviceCode()));

        setFieldText(mDeviceLogicCodeView, getDeviceLogicCode(c.getDeviceCode()));
        setFieldText(mDeviceLogicNameView, c.getDeviceName());

        setFieldText(mFaultDescriptionView, c.getFaultDescriptionText());
        setFieldText(mFaultTypeView, c.getFaultTypeText());
        setFieldText(mReporterTypeView, c.getReporterTypeText());
        setFieldText(mFaultNoteView, c.getFaultNote());
        setFieldText(mFaultStartTimeView, DATE_FORMAT.format(c.getFaultStartTime()));
        setFieldText(mFaultReasonView, c.getFaultCauseText());
        setFieldText(mOperationView, c.getOperationText());
        setFieldText(mOperationResultView, c.getOperationResultText());
        setFieldText(mOperationNoteView, c.getOperationNote());
        setFieldText(mOperatorView, c.getOperatorText());
        setFieldText(mOperationStartTimeView, DATE_FORMAT.format(c.getOperationStartTime()));
        setFieldText(mOperationEndTimeView, DATE_FORMAT.format(c.getOperationEndTime()));
        setFieldText(mOperatorname, c.getOperatorText());
        setFieldText(mFormFlagView, c.getFormFlagText());
    }

    private void setFieldText(TextView fieldView, String text) {
        fieldView.setText(text);
        if (TextUtils.isEmpty(text)) {
            fieldView.setVisibility(View.GONE);
        } else {
            fieldView.setVisibility(View.VISIBLE);
        }
    }

    public interface EditorFacade {
        void edit(Uri uri);

        void delete(Uri uri);
    }

    private String getDeviceLogicCode(String devicePhysicCode){
        String deviceLogicCode = "";

        if (devicePhysicCode != null) {
            DevicePhysicsSelection devicePhysicsSelection = new DevicePhysicsSelection().codePhysics(devicePhysicCode);
            DevicePhysicsCursor devicePhysicsCursor = devicePhysicsSelection.query(getContext().getContentResolver());
            try {
                while (devicePhysicsCursor.moveToNext()) {
                    deviceLogicCode = devicePhysicsCursor.getCode();
                }
            } finally {
                devicePhysicsCursor.close();
            }
        }

        return deviceLogicCode;
    }



    private String getDevicePhysicName(String devicePhysicCode){
        String devicePhysicName = "";

        if (devicePhysicCode != null) {
            DevicePhysicsSelection devicePhysicsSelection = new DevicePhysicsSelection().codePhysics(devicePhysicCode);
            DevicePhysicsCursor devicePhysicsCursor = devicePhysicsSelection.query(getContext().getContentResolver());
            try {
                while (devicePhysicsCursor.moveToNext()) {
                    devicePhysicName = devicePhysicsCursor.getName();
                }
            } finally {
                devicePhysicsCursor.close();
            }
        }

        return devicePhysicName;
    }
}
