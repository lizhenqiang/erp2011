package cc.xingyan.android.afc;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

import butterknife.Bind;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.CmService;
import cc.xingyan.android.afc.api.model.ParamValue;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.cmmaterial.CmMaterialCursor;
import cc.xingyan.android.afc.provider.cmmaterial.CmMaterialSelection;
import cc.xingyan.android.afc.provider.cmmaterialrow.CmMaterialRowColumns;
import cc.xingyan.android.afc.provider.cmmaterialrow.CmMaterialRowCursor;
import cc.xingyan.android.afc.provider.cmmaterialrow.CmMaterialRowSelection;
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

public class CmWorkReportFragment extends BaseFragment {
    private static final String ARG_URI = "uri";

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Bind(R.id.intcustomer_no_uploaded)
    TextView intcustomerNo;
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
    TextView mPrepareView;
    @Bind(R.id.dispose)
    TextView mDisposeView;
    @Bind(R.id.fault_start_time)
    TextView mFaultStartTimeView;
    @Bind(R.id.apply_start_time)
    TextView mApplyStartTimeView;
    @Bind(R.id.apply_end_time)
    TextView mApplyEndTimeView;


    @Bind(R.id.fault_grade)
    TextView mFaultGradeView;
    @Bind(R.id.fault_type)
    TextView mFaultTypeView;
    @Bind(R.id.fault_cause)
    TextView mFaultCauseView;
    @Bind(R.id.operation)
    TextView mOperationView;
    @Bind(R.id.work_done)
    TextView mWorkDoneView;
    @Bind(R.id.fault_reason_note)
    TextView mFaultReasonNoteView;
    @Bind(R.id.work_note)
    TextView mWorkNoteView;
    @Bind(R.id.operation_start_time)
    TextView mOperationStartTimeView;
    @Bind(R.id.operation_end_time)
    TextView mOperationEndTimeView;


    @Bind(R.id.material_container)
    LinearLayout materialContainer;
    @Bind(R.id.material_id)
    TextView mMaterialIdView;
    @Bind(R.id.start_time)
    TextView mStartTimeView;
    @Bind(R.id.due_time)
    TextView mDueTimeView;
    @Bind(R.id.material_user)
    TextView mMaterialUserView;

    @Inject
    Authenticator mAuthenticator;
    @Inject
    CmService cmService;
    @State
    boolean mEdited;

    private Uri mUri;
    private String mDeviceId;
    private String mCmOrderId;

    public static Fragment newInstance(Uri uri) {
        CmWorkReportFragment fragment = new CmWorkReportFragment();
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
        return inflater.inflate(R.layout.fragment_cm_work_report, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.title_activity_cm_work_report);
        loadWorkOrder();
    }

    private void loadWorkOrder() {
        CmWorkCursor c = new CmWorkCursor(getContext().getContentResolver().query(mUri, null, null, null, null));
        if (c.moveToFirst()) {
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
        c.close();
    }

    private void bindData(CmWorkCursor c) {

        getActivity().setTitle("[已上传CM工单] # " + c.getOrderId());

        intcustomerNo.setText(getIntCustomerValueByNo(c.getIntCustomerNo()));
        mPriorityView.setText(getParamName(ParamValue.PARAM_PRIORITY, c.getPriority()));

        mDeviceCodeView.setText(getDeviceLogicCode(c.getDeviceCode()));
        mDeviceNameView.setText(getDeviceLogicName(c.getDeviceCode()));
        mDevicePhysicCodeView.setText(c.getDeviceCode());
        mDevicePhysicNameView.setText(c.getDeviceName());

        mFaultDescriptionView.setText(getParamName(ParamValue.PARAM_FAULT_DESCRIPTION, c.getFaultDescriptionCode()));
        mFaultDescriptionView.setTag(c.getFaultDescriptionCode());

        mFaultNoteView.setText(c.getFaultNote());

        mReporterTypeView.setText(getParamName(ParamValue.PARAM_REPORTER_TYPE, c.getReporterTypeCode()));
        mReporterTypeView.setTag(c.getReporterTypeCode());


        mFaultStartTimeView.setText(DATE_FORMAT.format(c.getFaultStartTime()));
        mFaultStartTimeView.setTag(c.getFaultStartTime());

        mApplyStartTimeView.setText(DATE_FORMAT.format(c.getApplySTime()));
        mApplyStartTimeView.setTag(c.getApplySTime());

        mApplyEndTimeView.setText(DATE_FORMAT.format(c.getApplyFTime()));
        mApplyEndTimeView.setTag(c.getApplyFTime());

        mFaultGradeView.setTag(c.getFaultGradeCode());
        mFaultGradeView.setText(c.getFaultGradeText());

        mFaultTypeView.setTag(c.getFaultTypeCode());
        mFaultTypeView.setText(c.getFaultTypeText());

        mFaultCauseView.setTag(c.getFaultCauseCode());
        mFaultCauseView.setText(c.getFaultCauseText());

        mOperationView.setTag(c.getWorkDetailsCode());
        mOperationView.setText(c.getWorkDetailsText());

        mWorkDoneView.setTag(c.getWorkDoneCode());
        mWorkDoneView.setText(c.getWorkDoneText());

        mFaultReasonNoteView.setText(c.getFaultCauseNote());

        mWorkNoteView.setText(c.getWorkNote());

        mReporterView.setText(c.getReporter());
        mPrepareView.setText(getUserName(c.getPrepareMan()));
        mDisposeView.setText(getUserName(c.getDispose()));


        mOperationStartTimeView.setText(DATE_FORMAT.format(c.getOperationStartTime()));
        mOperationStartTimeView.setTag(c.getOperationStartTime());

        mOperationEndTimeView.setText(DATE_FORMAT.format(c.getOperationEndTime()));
        mOperationEndTimeView.setTag(c.getOperationEndTime());

        final CmMaterialCursor cmMaterialCursor = new CmMaterialSelection().cmOrderId(c.getOrderId()).query(getContext());
        try {
            while (cmMaterialCursor.moveToNext()) {
                mMaterialIdView.setText(cmMaterialCursor.getMaterialOrderId());
                mMaterialUserView.setText(cmMaterialCursor.getUser());
                mStartTimeView.setText(DATE_FORMAT.format(cmMaterialCursor.getEnterDate()));
                mDueTimeView.setText(DATE_FORMAT.format(cmMaterialCursor.getDueDate()));
                final CmMaterialRowCursor rows = new CmMaterialRowSelection().materialOrderId(cmMaterialCursor.getMaterialOrderId()).orderBy(CmMaterialRowColumns._ID).query(getContext());
                try {
                    while (rows.moveToNext()) {
                        View v = showMateriaRow(rows.getId());
                        TextView materialNameView = (TextView) v.findViewById(R.id.material_name);
                        TextView materialCodeView = (TextView) v.findViewById(R.id.material_code);
                        TextView materialNumView = (TextView) v.findViewById(R.id.material_num);
                        TextView materialTimeView = (TextView) v.findViewById(R.id.material_apply_time);

                        materialNameView.setFocusable(false);
                        materialNumView.setFocusable(false);

                        final Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
                        final CmMaterialRowCursor cur = new CmMaterialRowSelection().id(rows.getId()).query(getContext());
                        try {
                            if (cur.moveToNext()) {
                                toolbar.setTitle("[物料行号] #" + cur.getMaterialRow());
                                if (cur.getMaterialRow() != null) {
                                    toolbar.setBackgroundColor(getContext().getResources().getColor(R.color.green));
                                }
                            }
                        } finally {
                            cur.close();
                        }

                        if (rows.getMaterialId() != null) {
                            materialCodeView.setText(rows.getMaterialId());
                        }
                        if (rows.getMaterialDescription() != null) {
                            materialNameView.setText(rows.getMaterialDescription());
                        }
                        if (rows.getMaterialCount() != null) {
                            materialNumView.setText(String.valueOf(rows.getMaterialCount()));
                        }
                        if (rows.getDueDate() != null) {
                            materialTimeView.setText(DATE_FORMAT.format(rows.getDueDate()));
                        }
                        initMaterialRow(v);
                    }
                } finally {
                    rows.close();
                }

            }
        } finally {
            cmMaterialCursor.close();
        }


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

    private String getIntCustomerValueByNo(String intCustomerNo){
        String intCustomerValue = "";

        final ParamValueSelection sel = new ParamValueSelection().code(intCustomerNo);
        final ParamValueCursor cur = sel.query(getContext().getContentResolver());
        try {

            while (cur.moveToNext()) {
                intCustomerValue = cur.getValue();
            }
        } finally {
            cur.close();
        }
        return  intCustomerValue;
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
    void initMaterialRow(View v) {
        final CmWorkCursor c = new CmWorkCursor(getContext().getContentResolver().query(mUri, null, null, null, null));
        if (c.moveToNext()) {
            mDeviceId = c.getDeviceCode();
        }
        c.close();
    }

    View showMateriaRow(long id) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.materials_view, materialContainer, false);
        materialContainer.addView(v);
        v.setTag(id);
        return v;
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