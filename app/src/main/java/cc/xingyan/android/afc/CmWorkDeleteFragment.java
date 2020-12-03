package cc.xingyan.android.afc;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

import butterknife.Bind;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.model.ParamValue;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.cmwork.CmWorkCursor;
import cc.xingyan.android.afc.provider.device.DeviceCursor;
import cc.xingyan.android.afc.provider.device.DeviceSelection;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsCursor;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsSelection;
import cc.xingyan.android.afc.provider.paramvalue.ParamValueCursor;
import cc.xingyan.android.afc.provider.paramvalue.ParamValueSelection;
import cc.xingyan.android.afc.provider.user.UserCursor;
import cc.xingyan.android.afc.provider.user.UserSelection;
import icepick.State;

/**
 * Created by 1 on 2016/1/8.
 */
public class CmWorkDeleteFragment extends BaseFragment {
    private static final String ARG_URI = "uri";

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Bind(R.id.priority)
    TextView mPriorityView;
    @Bind(R.id.device_code) TextView mDeviceCodeView;
    @Bind(R.id.device_name) TextView mDeviceNameView;
    @Bind(R.id.not_edit_device_physic_code) TextView mDevicePhysicCodeView;
    @Bind(R.id.not_edit_device_physic_name) TextView mDevicePhysicNameView;
    @Bind(R.id.fault_description)
    TextView mFaultDescriptionView;
    @Bind(R.id.fault_note)
    TextView mFaultNoteView;
    @Bind(R.id.reporter_type)
    TextView mReporterTypeView;
    @Bind(R.id.reporter)
    TextView mReporterView;
    @Bind(R.id.prepare_man)
    TextView mPrepareManView;
    @Bind(R.id.dispose)
    TextView mDisposeView;
    @Bind(R.id.fault_start_time)
    TextView mFaultStartTimeView;
    @Bind(R.id.apply_start_time)
    TextView mApplyStartTimeView;
    @Bind(R.id.apply_end_time)
    TextView mApplyEndTimeView;

    @Inject
    Authenticator mAuthenticator;
    @State
    boolean mEdited;

    private Uri mUri;

    public static Fragment newInstance(Uri uri) {
        CmWorkDeleteFragment fragment = new CmWorkDeleteFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_URI, uri);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);
        mUri = getArguments().getParcelable(ARG_URI);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cm_work_delete, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        //getActivity().setTitle(R.string.title_activity_cm_work_delete);
        loadWorkOrder();
    }

    private void loadWorkOrder() {
        final CmWorkCursor c = new CmWorkCursor(getContext().getContentResolver().query(mUri, null, null, null, null));
        try {
            if (c.moveToFirst()) {
                getActivity().setTitle("[已删除CM工单] # " + c.getOrderId());
                ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(c.getDeviceCode());
                bindData(c);
            } else {
                new AlertDialog.Builder(getContext())
                        .setIcon(R.drawable.ic_info_black_24dp)
                        .setTitle(R.string.error)
                        .setMessage(R.string.error_work_order_not_found)
                        .setPositiveButton(R.string.ok, (button, which) -> {
                            getActivity().finish();
                        }).show();
            }
        } finally {
            c.close();
        }

    }

    private void bindData(CmWorkCursor c) {

        mPriorityView.setText(getParamName(ParamValue.PARAM_PRIORITY,c.getPriority()));

        mDeviceCodeView.setText(getDeviceLogicCode(c.getDeviceCode()));
        mDeviceNameView.setText(getDeviceLogicName(c.getDeviceCode()));
        mDevicePhysicCodeView.setText(c.getDeviceCode());
        mDevicePhysicNameView.setText(c.getDeviceName());

        mFaultDescriptionView.setText(getParamName(ParamValue.PARAM_FAULT_DESCRIPTION, c.getFaultDescriptionCode()));

        mFaultNoteView.setText(c.getFaultNote());

        mReporterTypeView.setText(getParamName(ParamValue.PARAM_REPORTER_TYPE, c.getReporterTypeCode()));

        mReporterView.setText(c.getReporter());
        mPrepareManView.setText(getUserName(c.getPrepareMan()));
        mDisposeView.setText(getUserName(c.getDispose()));

        mFaultStartTimeView.setText(DATE_FORMAT.format(c.getFaultStartTime()));
        mFaultStartTimeView.setTag(c.getFaultStartTime());

        mApplyStartTimeView.setText(DATE_FORMAT.format(c.getApplySTime()));
        mApplyStartTimeView.setTag(c.getApplySTime());

        mApplyEndTimeView.setText(DATE_FORMAT.format(c.getApplyFTime()));
        mApplyEndTimeView.setTag(c.getApplyFTime());
    }

    private String getParamName(String type, String code) {
        String paramName = "";
        if ((type != null) && (code != null)) {
            final ParamValueSelection sel = new ParamValueSelection().type(type);
            final ParamValueCursor cur = sel.query(getContext().getContentResolver());
            try {
                while (cur.moveToNext()) {
                    if(cur.getCode().equals(code)) {
                        paramName = cur.getValue();
                    }
                }
            } finally {
                cur.close();
            }
        }
        return paramName;
    }

    private String getUserName(String userID){
        String userName = "";
        if (userID != null) {
            UserSelection userSelection = new UserSelection().userId(userID);
            UserCursor userCursor = userSelection.query(getContext().getContentResolver());
            try {
                while (userCursor.moveToNext()) {
                    userName = userCursor.getUserName();
                }
            } finally {
                userCursor.close();
            }
        }
        return userName;
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