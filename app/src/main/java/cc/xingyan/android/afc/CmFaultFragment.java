package cc.xingyan.android.afc;

import android.app.AlertDialog;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

import butterknife.Bind;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.model.ParamValue;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.cmwork.CmWorkColumns;
import cc.xingyan.android.afc.provider.cmwork.CmWorkCursor;
import cc.xingyan.android.afc.provider.cmwork.CmWorkSelection;
import cc.xingyan.android.afc.provider.cmwork.SyncStatus;
import cc.xingyan.android.afc.provider.device.DeviceCursor;
import cc.xingyan.android.afc.provider.device.DeviceSelection;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsCursor;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsSelection;
import cc.xingyan.android.afc.provider.paramvalue.ParamValueCursor;
import cc.xingyan.android.afc.provider.paramvalue.ParamValueSelection;
import cc.xingyan.android.afc.provider.user.UserCursor;
import cc.xingyan.android.afc.provider.user.UserSelection;
import cc.xingyan.android.afc.util.LogUtil;

/**
 * Created by San on 12/11/15.
 */
public class CmFaultFragment extends BaseFragment {
    private static final String ARG_CM_ORDER_ID = "cm_order_id";

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Bind(R.id.priority)
    TextView mPriorityView;
    @Bind(R.id.device_code) TextView mDeviceCodeView;
    @Bind(R.id.device_name) TextView mDeviceNameView;
    @Bind(R.id.fault_report_device_physic_code) TextView mDevicePhysicCodeView;
    @Bind(R.id.fault_report_device_physic_name) TextView mDevicePhysicNameView;
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

    private String mCmOrderId;
    private SyncStatus mSyncStatus;
    private ContentObserver mContentObserver;

    public static Fragment newInstance(String cmOrderId) {
        CmFaultFragment fragment = new CmFaultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CM_ORDER_ID, cmOrderId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);

        mCmOrderId = getArguments().getString(ARG_CM_ORDER_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cm_fault_report, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);

        getActivity().setTitle(R.string.title_activity_cm_work);

        try {
            queryAndBindData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        getContext().getContentResolver().registerContentObserver(CmWorkColumns.CONTENT_URI, true, mContentObserver = new ContentObserver(null) {
            @Override
            public void onChange(boolean selfChange) {
                try {
                    queryAndBindData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void queryAndBindData() throws Exception{
        boolean found = false;
        if (mCmOrderId != null) {
            final CmWorkCursor c = new CmWorkSelection().orderId(mCmOrderId).query(getContext());
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

    private void bindData(CmWorkCursor c) {
        final String title = getString(R.string.format_title_activity_cm_work_order, c.getOrderId());
        getActivity().setTitle(title);

        setFieldText(mPriorityView, getParamName(ParamValue.PARAM_PRIORITY, c.getPriority() ));

        setFieldText(mDeviceCodeView, getDeviceLogicCode(c.getDeviceCode()));
        setFieldText(mDeviceNameView, getDeviceLogicName(c.getDeviceCode()));
        setFieldText(mDevicePhysicCodeView, c.getDeviceCode());
        setFieldText(mDevicePhysicNameView, c.getDeviceName());

        setFieldText(mReporterTypeView, getParamName(ParamValue.PARAM_REPORTER_TYPE, c.getReporterTypeCode()));
        setFieldText(mReporterView,c.getReporter());
        setFieldText(mPrepareManView,getUserName(c.getPrepareMan()));
        setFieldText(mDisposeView, getUserName(c.getDispose()));
        setFieldText(mFaultStartTimeView, DATE_FORMAT.format(c.getFaultStartTime()));
        setFieldText(mApplyStartTimeView, DATE_FORMAT.format(c.getApplySTime()));
        setFieldText(mApplyEndTimeView, DATE_FORMAT.format(c.getApplyFTime()));
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
    private String getDeviceName(String code) {
        String deviceName = "";
        if (code != null) {
            final DeviceCursor cur = new DeviceSelection().code(code).query(getContext().getContentResolver());
            try {
                if (cur.moveToNext()) {
                    deviceName = cur.getName();
                }
            } finally {
                cur.close();
            }
        }
        return deviceName;
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

    private void setFieldText(TextView fieldView, String text) {
        try {
            fieldView.setText(text);
            fieldView.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            CmMaterialFragment.isMaterialFragmentVisible = false;
            LogUtil.info("CmMaterialFragment_Scan", "isFragmentVisible_Fault>> " + CmMaterialFragment.isMaterialFragmentVisible);
        }
    }

}
