/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc.adapter;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import cc.xingyan.android.afc.PmWorkActivity;
import cc.xingyan.android.afc.R;
import cc.xingyan.android.afc.provider.device.DeviceCursor;
import cc.xingyan.android.afc.provider.device.DeviceSelection;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsCursor;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsSelection;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkColumns;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkCursor;
import cc.xingyan.android.afc.provider.pmifsworkitem.PmifsWorkItemSelection;
import cc.xingyan.android.afc.widget.SelectableCursorRecyclerAdapter;

/**
 * Created by San on 10/13/15.
 */
public class PmWorkAdapter extends SelectableCursorRecyclerAdapter<PmifsWorkCursor, SelectableCursorRecyclerAdapter.SelectableViewHolder<PmifsWorkCursor>> {

    private Context context;
    private ContentObserver mObserver;
    private final boolean itemSelectable;

    public PmWorkAdapter(LayoutInflater layoutInflater, Context context, boolean itemSelectable) {
        super(layoutInflater);
        this.context = context;
        this.itemSelectable = itemSelectable;
    }

    @Override public boolean isItemSelectable(int position) {
        return itemSelectable;
    }

    @Override
    protected SelectableViewHolder<PmifsWorkCursor> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new PmWorkViewHolder(inflater.inflate(R.layout.card_pm_job, parent, false));
    }

    @Override public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.getContext().getContentResolver().registerContentObserver(PmifsWorkColumns.CONTENT_URI, true, mObserver = new ContentObserver(new Handler()) {
            @Override public void onChange(boolean selfChange, Uri uri) {
                notifyDataSetChanged();
            }
        });
    }

    @Override public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        recyclerView.getContext().getContentResolver().unregisterContentObserver(mObserver);
    }


    public class PmWorkViewHolder extends SelectableViewHolder<PmifsWorkCursor> {
        @Bind(R.id.toolbar_pm_card)
        Toolbar mToolbar;
        @Bind(R.id.device_code_pm_card) TextView mDeviceNumberView;
        @Bind(R.id.device_name_pm_card) TextView mDeviceNameView;
        @Bind(R.id.physic_number_pm_card) TextView mPhysicNumberView;
        @Bind(R.id.physic_name_pm_card) TextView mPhysicNameView;

        @Bind(R.id.remaining_job_items_pm_card) TextView mRemainingJobItemsView;
        @Bind(R.id.remaining_days_pm_card) TextView mRemainingDaysView;
        @Bind(R.id.checkbox_pm_card) CheckBox checkBox;

        @Bind(R.id.out_tx_1) TextView outTx_1;
        @Bind(R.id.out_days_pm_card) TextView outDays;
        @Bind(R.id.out_tx_2) TextView outTx_2;


        @Bind(R.id.pm_apply_start_time) TextView pmApplyStartTime;
        @Bind(R.id.pm_apply_end_time) TextView pmApplyEndTime;
        @Bind(R.id.pm_plan_start_time) TextView pmPlanStartTime;
        @Bind(R.id.pm_plan_end_time) TextView pmPlanEndTime;

        private long workId;
        private boolean selectable;
        private boolean selectMode;

        public PmWorkViewHolder(View itemView) {
            super(itemView);
        }

        @OnCheckedChanged(R.id.checkbox_pm_card) void onChecked(boolean isChecked) {
            notifySelectChanged(isChecked);
        }

        @OnClick({R.id.card, R.id.toolbar_pm_card}) void onCardClick() {
            if (selectable && selectMode) {
                checkBox.toggle();
            } else {
                final Intent intent = new Intent(getContext(), PmWorkActivity.class);
                intent.putExtra(PmWorkActivity.EXTRA_PM_WORK, ContentUris.withAppendedId(PmifsWorkColumns.CONTENT_URI, workId));
                getContext().startActivity(intent);
            }
        }

        @Override
        public void setItem(PmifsWorkCursor pmWorkCursor, boolean selectable, boolean selected, boolean selectMode) {
            this.selectable = selectable;
            this.selectMode = selectMode;
            this.workId = pmWorkCursor.getId();

            checkBox.setVisibility(selectable ? View.VISIBLE : View.INVISIBLE);
            checkBox.setChecked(selected);

            mToolbar.setTitle("["+pmWorkCursor.getWorkTypeId()+"工单] #" + pmWorkCursor.getOrderId());

            mDeviceNumberView.setText(pmWorkCursor.getDeviceLogicCode());

            String deviceLogicName = getDeviceLogicName(pmWorkCursor.getDeviceCode());
            mDeviceNameView.setText(deviceLogicName);


            mPhysicNumberView.setText(pmWorkCursor.getDeviceCode());
            mPhysicNameView.setText(pmWorkCursor.getDeviceName());

            DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            pmApplyStartTime.setText(DATE_FORMAT.format(pmWorkCursor.getApplySTime()));
            pmApplyEndTime.setText(DATE_FORMAT.format(pmWorkCursor.getApplyFTime()));
            pmPlanStartTime.setText(DATE_FORMAT.format(pmWorkCursor.getPlanSTime()));
            pmPlanEndTime.setText(DATE_FORMAT.format(pmWorkCursor.getPlanFTime()));

            final int remainingJobItems = countRemainingJobItems(pmWorkCursor);
            if (remainingJobItems > 0) {
                mRemainingJobItemsView.setText(getContext().getString(R.string.format_remaining_job_items, countRemainingJobItems(pmWorkCursor)));
            } else {
                mToolbar.setBackgroundColor(getContext().getResources().getColor(R.color.green));
                mRemainingJobItemsView.setText(R.string.pm_job_done);
            }
        }

        private int countRemainingJobItems(final PmifsWorkCursor job) {



            String orderId = job.getOrderId();

            int pmRemainingJobItemCount = new PmifsWorkItemSelection()
                    .orderId(orderId)
                    .and()
                    .resultEnumOrdinal((Integer) null)
                    .count(getContext().getContentResolver());

            return pmRemainingJobItemCount;
        }
    }

    private String getDeviceLogicCode(String devicePhysicCode){
        String deviceLogicCode = "";

        if (devicePhysicCode != null) {
            DevicePhysicsSelection devicePhysicsSelection = new DevicePhysicsSelection().codePhysics(devicePhysicCode);
            DevicePhysicsCursor devicePhysicsCursor = devicePhysicsSelection.query(context.getContentResolver());
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

    private String getDeviceLogicName(String devicePhysicCode){
        String deviceLogicCode = getDeviceLogicCode(devicePhysicCode);
        String deviceLogicName = "";

        if (deviceLogicCode != null) {
            DeviceSelection deviceSelection = new DeviceSelection().code(deviceLogicCode);
            DeviceCursor deviceCursor = deviceSelection.query(context.getContentResolver());
            try {
                while (deviceCursor.moveToNext()) {
                    deviceLogicName = deviceCursor.getName();
                }
            } finally {
                deviceCursor.close();
            }
        }

        return deviceLogicName;
    }

}

