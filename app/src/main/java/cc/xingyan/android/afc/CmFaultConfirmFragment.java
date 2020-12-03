package cc.xingyan.android.afc;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import cc.xingyan.android.afc.api.CmService;
import cc.xingyan.android.afc.api.model.CmConfirm;
import cc.xingyan.android.afc.api.model.CmConfirms;
import cc.xingyan.android.afc.api.model.CmWorkID;
import cc.xingyan.android.afc.api.model.CmWorkTime;
import cc.xingyan.android.afc.api.model.CmWorkTimes;
import cc.xingyan.android.afc.api.model.ParamValue;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.cmwork.CmWorkColumns;
import cc.xingyan.android.afc.provider.cmwork.CmWorkContentValues;
import cc.xingyan.android.afc.provider.cmwork.CmWorkCursor;
import cc.xingyan.android.afc.provider.cmwork.CmWorkSelection;
import cc.xingyan.android.afc.provider.cmwork.CmWorkStatus;
import cc.xingyan.android.afc.provider.device.DeviceCursor;
import cc.xingyan.android.afc.provider.device.DeviceSelection;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsCursor;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsSelection;
import cc.xingyan.android.afc.provider.paramvalue.ParamValueCursor;
import cc.xingyan.android.afc.provider.paramvalue.ParamValueSelection;
import cc.xingyan.android.afc.provider.user.UserCursor;
import cc.xingyan.android.afc.provider.user.UserSelection;
import cc.xingyan.android.afc.util.LogUtil;
import cc.xingyan.android.afc.util.NetUtil;

/**
 * Created by 1 on 2015/12/15.
 */
public class CmFaultConfirmFragment extends BaseFragment {
    private static final String ARG_CM_ORDER_ID = "cm_order_id";

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    @Inject
    static CmService cmService;
    @Bind(R.id.priority)
    TextView mPriorityView;
    @Bind(R.id.device_code) TextView mDeviceCodeView;
    @Bind(R.id.device_name) TextView mDeviceNameView;

    @Bind(R.id.confirm_device_physic_code) TextView mDevicePhysicCodeView;
    @Bind(R.id.confirm_device_physic_name) TextView mDevicePhysicNameView;


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

    private String mCmOrderId;
    private boolean t;
    private ContentObserver mContentObserver;

    public static Fragment newInstance(String cmOrderId) {
        CmFaultConfirmFragment fragment = new CmFaultConfirmFragment();
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
        return inflater.inflate(R.layout.fragment_cm_fault_report_confirm, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);

        getActivity().setTitle(R.string.title_activity_cm_work);

        queryAndBindData();

        getContext().getContentResolver().registerContentObserver(CmWorkColumns.CONTENT_URI, true, mContentObserver = new ContentObserver(null) {
            @Override
            public void onChange(boolean selfChange) {
                queryAndBindData();
            }
        });
    }

    private void queryAndBindData() {
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

    private void bindData(CmWorkCursor c) {
        final String title = getString(R.string.format_title_activity_cm_work_order, c.getOrderId());
        getActivity().setTitle(title);

        setFieldText(mPriorityView, getParamName(ParamValue.PARAM_PRIORITY, c.getPriority()));
        setFieldText(mDeviceCodeView, getDeviceLogicCode(c.getDeviceCode()));
        setFieldText(mDeviceNameView, getDeviceLogicName(c.getDeviceCode()));
        setFieldText(mDevicePhysicCodeView, c.getDeviceCode());
        setFieldText(mDevicePhysicNameView, c.getDeviceName());

        setFieldText(mReporterTypeView, getParamName(ParamValue.PARAM_REPORTER_TYPE, c.getReporterTypeCode()));
        setFieldText(mReporterView, c.getReporter());
        setFieldText(mPrepareManView, getUserName(c.getPrepareMan()));
        setFieldText(mDisposeView, getUserName(c.getDispose()));
        setFieldText(mFaultStartTimeView, DATE_FORMAT.format(c.getFaultStartTime()));
        setFieldText(mApplyStartTimeView, DATE_FORMAT.format(c.getApplySTime()));
        setFieldText(mApplyEndTimeView, DATE_FORMAT.format(c.getApplyFTime()));
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
                    if (cur.getCode().equals(code)) {
                        paramName = cur.getValue();
                    }
                }
            } finally {
                cur.close();
            }
        }
        return paramName;
    }


    /**根据用户id获取用户姓名
     * @param userID 用户id
     * @return 用户姓名
     * @author yjd
     */
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

    private void setFieldText(TextView fieldView, String text) {
        fieldView.setText(text);
        fieldView.setVisibility(View.VISIBLE);
    }

    public interface EditorFacade {
        void edit(Uri uri);

        void delete(Uri uri);
    }

    CmConfirm c = new CmConfirm();
    List<CmConfirm> list = Arrays.asList(c);
    CmConfirms list1 = new CmConfirms();

    @OnClick(R.id.confirm_cm) void cmConfirm() {

        boolean isNetworkAvailable =  NetUtil.isNetworkAvailable(getActivity());
        if(!isNetworkAvailable){
            new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.ic_info_black_24dp)
                    .setTitle("温馨提示")
                    .setMessage("没有网络！")
                    .setPositiveButton(R.string.ok, null)
                    .show();
            return;
        }

        t = false;
        uploadTime(() -> {
            confirm(c, mCmOrderId, list1, list, getActivity(), () -> {
                if (t) {
                    getActivity().finish();
                } else {
                    Toast.makeText(getContext(), "通讯失败", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private boolean uploadTime(final Runnable next) {
        Long acceptTime = null;
        Long arriveTime = null;

        arriveTime = System.currentTimeMillis();
        new CmWorkContentValues()
                .putArriveTime(arriveTime)
                .update(getContext().getContentResolver(), new CmWorkSelection().orderId(mCmOrderId));

        final CmWorkCursor cmWorkCursor = new CmWorkSelection().orderId(mCmOrderId).query(getContext());
        if (cmWorkCursor.moveToFirst()) {
            acceptTime = cmWorkCursor.getOrderReceiveTime();
        }
        cmWorkCursor.close();

        if (acceptTime == null) {
            Toast.makeText(getContext(), "接单时间为空，不能确认或取消", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (arriveTime == null) {
            Toast.makeText(getContext(), "到达现场时间为空，不能确认或取消。请扫描设备二维码打开CM工单", Toast.LENGTH_SHORT).show();
            return false;
        }
        CmWorkTime c = new CmWorkTime();
        List<CmWorkTime> list = Arrays.asList(c);
        CmWorkTimes list1 = new CmWorkTimes();
        c.setOrderId(mCmOrderId);
        c.setAcceptTime(new Date(acceptTime));
        c.setArriveTime(new Date(arriveTime));
        list1.setcmWorkTimes(list);
        subscribe(cmService.postUploadTimeCMWorks(list1), resp -> {
            if (resp.size() > 0) {
                t = true;
                if (next != null) {
                    next.run();
                }
                LogUtil.debug(TAG, "confirm a cm is ok");
            } else {
                Toast.makeText(getContext(), "上传接单时间失败", Toast.LENGTH_SHORT).show();
            }
        }, e -> {
            LogUtil.debug(TAG, "confirm a cm is failed");
            Toast.makeText(getContext(), "上传接单时间失败", Toast.LENGTH_SHORT).show();
        });
        return t;
    }

    @OnClick(R.id.cancel_cm) void cmCancel() {

        boolean isNetworkAvailable =  NetUtil.isNetworkAvailable(getActivity());
        if(!isNetworkAvailable){
            new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.ic_info_black_24dp)
                    .setTitle("温馨提示")
                    .setMessage("没有网络！")
                    .setPositiveButton(R.string.ok, null)
                    .show();
            return;
        }

        new AlertDialog.Builder(getActivity()).setTitle("温馨提示").setMessage("确定取消？")
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        t = false;
                        uploadTime(() -> {
                            cancel(c, mCmOrderId, list1, list, getActivity(), () -> {
                                if (t) {
                                    getActivity().finish();
                                } else {
                                    Toast.makeText(getContext(), "通讯失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        });

                    }
                }).setNegativeButton(getString(R.string.later), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        }).setCancelable(false)
                .show();

    }


    private void confirm(CmConfirm c, String mCmOrderId, CmConfirms list1, List<CmConfirm> list, FragmentActivity fa, Runnable next) {
        c.setOrderId(mCmOrderId);
        c.setconfirm("RELEASED");
        list1.setCmConfirms(list);
        subscribe(cmService.postConfirmCMWorks(list1), resp -> {
            final List<ContentValues> workValues = new ArrayList<>(resp.size());
            for (CmWorkID i : resp) {
                new CmWorkContentValues()
                        .putStatus(CmWorkStatus.RELEASED)
                        .putUploadPending(false)
                        .update(fa.getContentResolver(), new CmWorkSelection().orderId(i.getOrderId()));
            }
            t = true;
            LogUtil.debug(TAG, "confirm a cm is ok");

            if (next != null) {
                next.run();
            }
        }, e -> {
            LogUtil.debug(TAG, "confirm a cm is failed");
            Toast.makeText(fa, "确认CM工单失败", Toast.LENGTH_SHORT).show();
        });
    }

    private void cancel(CmConfirm c, String mCmOrderId, CmConfirms list1, List<CmConfirm> list, FragmentActivity fa, Runnable next) {

        c.setOrderId(mCmOrderId);
        c.setconfirm("FINISHED");
        list1.setCmConfirms(list);
        subscribe(cmService.postConfirmCMWorks(list1), resp -> {
            final List<ContentValues> workValues = new ArrayList<>(resp.size());
            for (CmWorkID i : resp) {
                new CmWorkContentValues()
                        .putStatus(CmWorkStatus.FINISH)
                        .putUploadPending(false)
                        .update(fa.getContentResolver(), new CmWorkSelection().orderId(i.getOrderId()));
            }
            t = true;
            LogUtil.debug(TAG, "cancel a cm is ok");

            if (next != null) {
                next.run();
            }
        }, e -> {
            LogUtil.debug(TAG, "cancel a cm is failed");
            Toast.makeText(fa, "拒绝CM工单失败", Toast.LENGTH_SHORT).show();
        });
    }
}