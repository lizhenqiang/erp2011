package cc.xingyan.android.afc.adapter;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.Bind;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import cc.xingyan.android.afc.CmWorkActivity;
import cc.xingyan.android.afc.R;
import cc.xingyan.android.afc.provider.cmwork.CmWorkColumns;
import cc.xingyan.android.afc.provider.cmwork.CmWorkCursor;
import cc.xingyan.android.afc.provider.cmwork.CmWorkSelection;
import cc.xingyan.android.afc.provider.cmwork.CmWorkStatus;
import cc.xingyan.android.afc.provider.device.DeviceCursor;
import cc.xingyan.android.afc.provider.device.DeviceSelection;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsCursor;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsSelection;
import cc.xingyan.android.afc.widget.SelectableCursorRecyclerAdapter;

/**
 * Created by 1 on 2015/12/8.
 */
public class CmWorkAdapter extends SelectableCursorRecyclerAdapter<CmWorkCursor, SelectableCursorRecyclerAdapter.SelectableViewHolder<CmWorkCursor>> {

    private Context context;
    private ContentObserver mObserver;
    private final boolean itemSelectable;
    private int TYPE_CM_WORK_NEW = 0;
    private int TYPE_CM_WORK_QUERY = 1;

    public CmWorkAdapter(LayoutInflater layoutInflater, Context context, boolean itemSelectable) {
        super(layoutInflater);
        this.context = context;
        this.itemSelectable = itemSelectable;

    }

    @Override
    public boolean isItemSelectable(int position) {
        return itemSelectable;
    }

    @Override
    protected SelectableViewHolder<CmWorkCursor> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new CmWorkViewHolder(inflater.inflate(R.layout.card_cm, parent, false));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.getContext().getContentResolver().registerContentObserver(CmWorkColumns.CONTENT_URI, true, mObserver = new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange, Uri uri) {
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        recyclerView.getContext().getContentResolver().unregisterContentObserver(mObserver);
    }


    public class CmWorkViewHolder extends SelectableViewHolder<CmWorkCursor> {
        @Bind(R.id.toolbar)
        Toolbar mToolbar;
        @Bind(R.id.cm_card_logic_number_tx) TextView mLogicNumberView;
        @Bind(R.id.cm_card_logic_name_tx) TextView mLogicNameView;
        @Bind(R.id.cm_card_physic_number_tx) TextView mPhysicNumberView;
        @Bind(R.id.cm_card_physic_name_tx) TextView mPhysicNameView;
//        @Bind(R.id.symptom) TextView mSymptomView;
        @Bind(R.id.remaining_hours_cm) TextView mRemainingHoursView;
        @Bind(R.id.overtime_hours_cm) TextView mOvertimeHoursView;
        @Bind(R.id.cm_work_status) TextView mCmWorkStatusView;
        @Bind(R.id.checkbox) CheckBox checkBox;

        private long Cmid;
        private boolean selectable;
        private boolean selectMode;

        public CmWorkViewHolder(View itemView) {
            super(itemView);
        }

        @OnCheckedChanged(R.id.checkbox)
        void onChecked(boolean isChecked) {
            notifySelectChanged(isChecked);
        }

        @OnClick({R.id.card, R.id.toolbar})
        void onCardClick() {
            if (selectable && selectMode) {
                checkBox.toggle();
            } else {
                Bundle bundle = new Bundle();
                final CmWorkCursor cur = new CmWorkSelection().id(Cmid)
                        .query(getContext().getContentResolver());
                int type = TYPE_CM_WORK_QUERY;
                CmWorkStatus statusTemp = CmWorkStatus.FINISH;
                try {
                    if (cur.moveToNext()) {
                        if (cur.getStatus().equals(CmWorkStatus.NEW)) {
                            type = TYPE_CM_WORK_NEW;
                        }
                        statusTemp = cur.getStatus();
                    }
                } finally {
                    cur.close();
                }
                    bundle.putInt("type", type);
                    final Intent intent = new Intent(getContext(), CmWorkActivity.class);
                    intent.putExtra(CmWorkActivity.EXTRA_CM_WORK, ContentUris.withAppendedId(CmWorkColumns.CONTENT_URI, Cmid));
                    intent.setAction(Intent.ACTION_EDIT);
                    intent.putExtras(bundle);
                    getContext().startActivity(intent);
            }
        }


        @Override
        public void setItem(CmWorkCursor cmWorkCursor, boolean selectable, boolean selected, boolean selectMode) {
            this.selectable = selectable;
            this.selectMode = selectMode;
            this.Cmid = cmWorkCursor.getId();


            mToolbar.setTitle("[CM工单号] # " + cmWorkCursor.getOrderId());

            mLogicNumberView.setText(getDeviceLogicCode(cmWorkCursor.getDeviceCode()));
            mLogicNameView.setText(getDeviceLogicName(cmWorkCursor.getDeviceCode()));
            mPhysicNumberView.setText(cmWorkCursor.getDeviceCode());
            mPhysicNameView.setText(getDevicePhysicName(cmWorkCursor.getDeviceCode()));

            if (cmWorkCursor.getStatus().equals(CmWorkStatus.FINISH)) {
                mRemainingHoursView.setVisibility(View.GONE);
                mOvertimeHoursView.setVisibility(View.GONE);
                mCmWorkStatusView.setVisibility(View.VISIBLE);
                mCmWorkStatusView.setText(R.string.cm_work_status_finish);
            } else if (cmWorkCursor.getStatus().equals(CmWorkStatus.WORKDONEUPLOAD)) {
                mRemainingHoursView.setVisibility(View.GONE);
                mOvertimeHoursView.setVisibility(View.GONE);
                mCmWorkStatusView.setVisibility(View.VISIBLE);
                mCmWorkStatusView.setText(R.string.cm_work_status_upload);
            } else {
                long applyEndTime = cmWorkCursor.getApplyFTime();
                long currentTime = System.currentTimeMillis();

                int days = 0;
                int hours = 0;
                int mins = 0;
                int oneDayMicroSec = 1000 * 3600 * 24;
                int oneHourMicroSec = 1000 * 3600;
                int oneMinMicroSec = 1000 * 60;
                long diffTime = currentTime - applyEndTime;

                if (diffTime < 0) {
                    diffTime = Math.abs(diffTime);
                    days = (int) (diffTime / oneDayMicroSec);
                    hours = (int) ((diffTime % oneDayMicroSec) / oneHourMicroSec);
                    mins = (int) ((diffTime % oneHourMicroSec) / oneMinMicroSec);

                    mRemainingHoursView.setVisibility(View.VISIBLE);
                    mOvertimeHoursView.setVisibility(View.GONE);
                    mRemainingHoursView.setText(getContext().getString(R.string.format_remaining_hours, days, hours, mins));
                    if (days <= 3) {
                        mToolbar.setBackgroundColor(getContext().getResources().getColor(R.color.less3days));
                    } else {
                        mToolbar.setBackgroundColor(getContext().getResources().getColor(R.color.more3days));
                    }

                } else if (diffTime > 0) {
                    days = (int) (diffTime / oneDayMicroSec);
                    hours = (int) ((diffTime % oneDayMicroSec) / oneHourMicroSec);
                    mins = (int) ((diffTime % oneHourMicroSec) / oneMinMicroSec);

                    mRemainingHoursView.setVisibility(View.GONE);
                    mOvertimeHoursView.setVisibility(View.VISIBLE);
                    mOvertimeHoursView.setText(getContext().getString(R.string.format_overtime_hours, days, hours, mins));
                    mToolbar.setBackgroundColor(getContext().getResources().getColor(R.color.pastel_red));
                }
            }
            checkBox.setVisibility(selectable ? View.VISIBLE : View.INVISIBLE);
            checkBox.setChecked(selected);
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
            }catch (Exception e) {
                e.printStackTrace();
            }
            finally {
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