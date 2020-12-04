package cc.xingyan.android.afc;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import butterknife.Bind;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.device.DeviceCursor;
import cc.xingyan.android.afc.provider.device.DeviceSelection;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsCursor;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsSelection;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkCursor;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkSelection;

/**
 * Created by YuJiadeng on 2016/11/13.
 */
public class PmWorkNewDeviceInfoFragment extends BaseFragment {
    private static final String ARG_PM_ORDER_ID = "pm_order_id";


    private String mPMOrderId;

    @Bind(R.id.pm_fault_report_confirm_device_code) TextView mDeviceCodeView;
    @Bind(R.id.pm_fault_report_confirm_device_name) TextView mDeviceNameView;
    @Bind(R.id.pm_fault_report_confirm_device_physic_code) TextView mDevicePhysicCodeView;
    @Bind(R.id.pm_fault_report_confirm_device_physic_name) TextView mDevicePhysicNameView;
    @Bind(R.id.pm_fault_report_confirm_plan_start_time) TextView mplanStartTime;
    @Bind(R.id.pm_fault_report_confirm_plan_end_time) TextView mplanEndTime;
    @Bind(R.id.pm_fault_report_confirm_prepareman) TextView preparemanNameView;

    public static Fragment newInstance(String mPMOrderId) {
        PmWorkNewDeviceInfoFragment fragment = new PmWorkNewDeviceInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PM_ORDER_ID, mPMOrderId);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);

        mPMOrderId = getArguments().getString(ARG_PM_ORDER_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pm_work_new_device_info, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);

        queryAndBindData();

    }

    private void queryAndBindData() {
        if (mPMOrderId != null) {
            final PmifsWorkCursor c = new PmifsWorkSelection().orderId(mPMOrderId).query(getContext());
            if (c.moveToFirst()) {
                bindData(c);
            }
            c.close();
        }
    }

    private void bindData(PmifsWorkCursor c) {
        mDeviceCodeView.setText(getDeviceLogicCode(c.getDeviceCode()));
        mDeviceNameView.setText(getDeviceLogicName(c.getDeviceCode()));
        mDevicePhysicCodeView.setText(c.getDeviceCode());
        mDevicePhysicNameView.setText(c.getDeviceName());

        DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        mplanStartTime.setText(DATE_FORMAT.format(c.getPlanSTime()));
        mplanEndTime.setText(DATE_FORMAT.format(c.getPlanFTime()));

        preparemanNameView.setText(c.getPrepareManCode());

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


    private String getDeviceLogicName(String devicePhysicCode){
        String deviceLogicCode = getDeviceLogicCode(devicePhysicCode);
        String deviceLogicName = "";

        if (deviceLogicCode != null) {
            DeviceSelection deviceSelection = new DeviceSelection().code(deviceLogicCode);
            DeviceCursor deviceCursor = deviceSelection.query(getContext().getContentResolver());
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
