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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import butterknife.Bind;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import cc.xingyan.android.afc.R;
import cc.xingyan.android.afc.WorkOrderActivity;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsCursor;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsSelection;
import cc.xingyan.android.afc.provider.workorder.SyncStatus;
import cc.xingyan.android.afc.provider.workorder.WorkOrderColumns;
import cc.xingyan.android.afc.provider.workorder.WorkOrderCursor;
import cc.xingyan.android.afc.widget.SelectableCursorRecyclerAdapter;

/**
 * Created by San on 9/23/15.
 */
public class WorkOrderAdapter extends SelectableCursorRecyclerAdapter<WorkOrderCursor, SelectableCursorRecyclerAdapter.SelectableViewHolder<WorkOrderCursor>> {

    private Context context;
    private boolean itemSelectable;

    public WorkOrderAdapter(LayoutInflater layoutInflater, Context context, boolean itemSelectable) {
        super(layoutInflater);
        this.context = context;
        this.itemSelectable = itemSelectable;
    }

    @Override
    protected SelectableViewHolder<WorkOrderCursor> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new WorkOrderViewHolder(inflater.inflate(R.layout.card_work_order, parent, false));
    }

    @Override public boolean isItemSelectable(int position) {
        return itemSelectable;
    }

    public class WorkOrderViewHolder extends SelectableViewHolder<WorkOrderCursor> {
        @Bind(R.id.toolbar) Toolbar mToolbar;
        @Bind(R.id.work_order_card_logic_number_tx) TextView mLogicNumberView;
        @Bind(R.id.work_order_card_logic_name_tx) TextView mLogicNameView;
        @Bind(R.id.work_order_card_physic_number_tx) TextView mPhysicNumberView;
        @Bind(R.id.work_order_card_physic_name_tx) TextView mPhysicNameView;
        @Bind(R.id.fault_description) TextView mFaultDescriptionView;
        @Bind(R.id.operation_result) TextView mOperationResultView;
        @Bind(R.id.checkbox) CheckBox checkBox;

        private long mWorkOrderId;
        private boolean mSynchronized;
        private boolean selectable;
        private boolean selectMode;

        public WorkOrderViewHolder(View itemView) {
            super(itemView);
        }

        @OnCheckedChanged(R.id.checkbox) void onChecked(boolean isChecked) {
            notifySelectChanged(isChecked);
        }


        @OnClick({R.id.card, R.id.toolbar}) void onCardClick() {
            if (selectable && selectMode) {
                checkBox.toggle();
            } else {
                final Intent intent = new Intent(getContext(), WorkOrderActivity.class);
                intent.setAction(mSynchronized ? Intent.ACTION_VIEW : Intent.ACTION_EDIT);
                intent.putExtra(WorkOrderActivity.EXTRA_WORK_ORDER, ContentUris.withAppendedId(WorkOrderColumns.CONTENT_URI, mWorkOrderId));
                getContext().startActivity(intent);
            }
        }

        @Override
        public void setItem(WorkOrderCursor c, boolean selectable, boolean selected, boolean selectMode) {
            this.selectable = selectable;
            this.selectMode = selectMode;
            mWorkOrderId = c.getId();

            checkBox.setVisibility(selectable ? View.VISIBLE : View.INVISIBLE);
            checkBox.setChecked(selected);

            mSynchronized = c.getSyncStatus() == SyncStatus.SYNCHRONIZED;
            if (c.getNo() == null) {
                mToolbar.setTitle(R.string.local_work_order);
                mToolbar.setBackgroundColor(getContext().getResources().getColor(R.color.card_primary));
            } else {
                mToolbar.setTitle(getContext().getString(R.string.format_title_activity_work_order, c.getNo()));
                mToolbar.setBackgroundColor(getContext().getResources().getColor(R.color.green));
            }

            mLogicNumberView.setText(getDeviceLogicCode(c.getDeviceCode()));
            mLogicNameView.setText(c.getDeviceName());
            mPhysicNumberView.setText(c.getDeviceCode());
            mPhysicNameView.setText(getDevicePhysicName(c.getDeviceCode()));
            mFaultDescriptionView.setText(c.getFaultDescriptionText());
            mOperationResultView.setText(c.getOperationResultText());
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
    private String getDevicePhysicName(String devicePhysicCode){
        String devicePhysicName = "";

        if (devicePhysicCode != null) {
            DevicePhysicsSelection devicePhysicsSelection = new DevicePhysicsSelection().codePhysics(devicePhysicCode);
            DevicePhysicsCursor devicePhysicsCursor = devicePhysicsSelection.query(context.getContentResolver());
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
