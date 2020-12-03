package cc.xingyan.android.afc;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.zzj.afc.ui.wheelview.time.ScreenInfo;
import com.zzj.afc.ui.wheelview.time.WheelMain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.CmService;
import cc.xingyan.android.afc.api.model.CmWork;
import cc.xingyan.android.afc.api.model.CmWorkID;
import cc.xingyan.android.afc.api.model.CmWorks;
import cc.xingyan.android.afc.api.model.ParamValue;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
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
import cc.xingyan.android.afc.util.Numbers;
import cc.xingyan.android.afc.widget.FieldValueAdapter;
import cc.xingyan.android.afc.widget.SimpleTextWatcher;
import icepick.State;

/**
 * Created by 1 on 2015/12/14.
 */
public class CmWorkEditerNewFragment extends BaseFragment {
    private static final String ARG_URI = "uri";

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Bind(R.id.priority) AutoCompleteTextView mPriorityView;
    @Bind(R.id.device_code) AutoCompleteTextView mDeviceCodeView;
    @Bind(R.id.device_name)    AutoCompleteTextView mDeviceNameView;
    @Bind(R.id.physic_code_new_cm) AutoCompleteTextView mPhysicCodeView;
    @Bind(R.id.physic_name_new_cm) AutoCompleteTextView mPhysicNameView;
    @Bind(R.id.reporter_type) AutoCompleteTextView mReporterTypeView;
    @Bind(R.id.reporter) EditText mReporterView;
    @Bind(R.id.prepare_man) EditText mPrepareMan;
    @Bind(R.id.dispose) EditText mDisposeView;
    @Bind(R.id.fault_start_time) EditText mFaultStartTimeView;
    @Bind(R.id.apply_start_time) EditText mApplyStartTimeView;
    @Bind(R.id.apply_end_time) EditText mApplyEndTimeView;

    @Inject
    Authenticator mAuthenticator;
    @Inject
    CmService cmService;
    @State
    boolean mEdited;

    private Uri mUri;
    private String mDeviceId;

    public static Fragment newInstance(Uri uri, String deviceId) {
        CmWorkEditerNewFragment fragment = new CmWorkEditerNewFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_URI, uri);
        args.putString("deviceID", deviceId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);

        mUri = getArguments().getParcelable(ARG_URI);
        mDeviceId = getArguments().getString("deviceID");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cm_work_editor_new, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.title_activity_new_cm_work_order);
        if (mUri != null) {
            loadWorkOrder();
        }else {
            long now = System.currentTimeMillis();
            mFaultStartTimeView.setTag(now);
            mFaultStartTimeView.setText(DATE_FORMAT.format(now));
            mApplyStartTimeView.setTag(now);
            mApplyStartTimeView.setText(DATE_FORMAT.format(now));

            now += 1000 * 60 * 60 * 2;
            mApplyEndTimeView.setTag(now);
            mApplyEndTimeView.setText(DATE_FORMAT.format(now));

            mReporterView.setText(mAuthenticator.getAuthenticatedUserName());
            mPrepareMan.setText(mAuthenticator.getAuthenticatedUserName());
            mDisposeView.setText(mAuthenticator.getAuthenticatedUserName());


        }

        setupDropDownFieldView(mPriorityView, ParamValue.PARAM_PRIORITY, null);

        if(mDeviceId == null){
            setupDeviceCodeView();
        }else{
            mPhysicCodeView.setFocusable(false);
            mPhysicNameView.setFocusable(false);
            mDeviceCodeView.setFocusable(false);
            mDeviceNameView.setFocusable(false);
        }

        setupDropDownFieldView(mReporterTypeView, ParamValue.PARAM_REPORTER_TYPE, null);
        setupFieldView(mReporterView);
        setupFieldView(mPrepareMan);
        setupFieldView(mDisposeView);

        final ParamValueCursor curR = new ParamValueSelection()
                .type(ParamValue.PARAM_REPORTER_TYPE)
                .query(getContext().getContentResolver());
        try {
            while (curR.moveToNext()) {
                if(curR.getValue().toString().contains("检修人员")){
                    mReporterTypeView.setText(curR.getValue());
                    mReporterTypeView.setTag(curR.getCode());
                    break;
                }

            }
        } finally {
            curR.close();
        }
        final ParamValueCursor curP = new ParamValueSelection().type(ParamValue.PARAM_PRIORITY).query(getContext().getContentResolver());
        try {
            if(curP.moveToNext()){
                mPriorityView.setText(curP.getValue());
                mPriorityView.setTag(curP.getCode());
            }
        } finally {
            curP.close();
        }

        if (mDeviceId != null) {
            final String deviceCode = mDeviceId;
            mPhysicCodeView.setText(deviceCode);
            mPhysicCodeView.setTag(deviceCode);

            mPhysicNameView.setText(getDevicePhysicName(deviceCode));

            DevicePhysicsSelection devicePhysicsSelection = new DevicePhysicsSelection().codePhysics(deviceCode);
            DevicePhysicsCursor devicePhysicsCursor = devicePhysicsSelection.query(getContext().getContentResolver());
            try {
                if (devicePhysicsCursor.moveToNext()) {
                    mDeviceCodeView.setText(devicePhysicsCursor.getCode());
                    final DeviceCursor cur = new DeviceSelection().code(devicePhysicsCursor.getCode()).query(getContext().getContentResolver());
                    try {
                        if(cur.moveToNext()){
                            mDeviceNameView.setText(cur.getName());
                        }
                    } finally {
                        cur.close();
                    }
                }
            } finally {
                devicePhysicsCursor.close();
            }
        }
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
        mPriorityView.setText(getPriorityValue(c.getPriority()));
        mPriorityView.setTag(c.getPriority());

        mDeviceCodeView.setText(getDeviceLogicCode(c.getDeviceCode()));
        mDeviceNameView.setText(getDeviceLogicName(c.getDeviceCode()));
        mPhysicCodeView.setText(c.getDeviceCode());
        mPhysicNameView.setText(c.getDeviceName());

        mReporterTypeView.setText(c.getReporterTypeText());
        mReporterTypeView.setTag(c.getReporterTypeCode());

        mReporterView.setText(c.getReporter());
        mPrepareMan.setText(getUserName(c.getPrepareMan()));
        mDisposeView.setText(getUserName(c.getDispose()));

        mFaultStartTimeView.setText(DATE_FORMAT.format(c.getFaultStartTime()));
        mFaultStartTimeView.setTag(c.getFaultStartTime());

        mApplyStartTimeView.setText(DATE_FORMAT.format(c.getApplySTime()));
        mApplyStartTimeView.setTag(c.getApplySTime());

        mApplyEndTimeView.setText(DATE_FORMAT.format(c.getApplyFTime()));
        mApplyEndTimeView.setTag(c.getApplyFTime());
    }
    private void openDateTimePickerUpon(final TextView view, final String title) {
        long time = view.getTag() instanceof Long ? ((Long) view.getTag()).longValue() : System.currentTimeMillis();
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(time);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DATE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);

        final View dateTimePickerView = LayoutInflater.from(getContext()).inflate(R.layout.timepicker, null);
        ScreenInfo screenInfo = new ScreenInfo(getActivity());
        final WheelMain wheelMain = new WheelMain(dateTimePickerView, true);
        wheelMain.screenheight = screenInfo.getHeight();
        wheelMain.initDateTimePicker(year, month, date, hour, min);
        new AlertDialog.Builder(getContext())
                .setIcon(R.drawable.ic_info_black_24dp)
                .setTitle(title)
                .setView(dateTimePickerView)
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    view.setText(wheelMain.getTime());
                    cal.clear();
                    cal.set(Calendar.YEAR, wheelMain.getYear());
                    cal.set(Calendar.MONTH, wheelMain.getMonth() - 1);
                    cal.set(Calendar.DATE, wheelMain.getDate());
                    cal.set(Calendar.HOUR_OF_DAY, wheelMain.getHour());
                    cal.set(Calendar.MINUTE, wheelMain.getMinute());
                    view.setTag(cal.getTimeInMillis());
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }
    private void checkAndSave() {
        boolean ok = true;

        for (TextView fieldView : new TextView[]{
                mPriorityView,
                mDeviceCodeView,
                mDeviceNameView,
                mPhysicCodeView,
                mPhysicNameView,
                mReporterTypeView,
                mFaultStartTimeView,
                mApplyStartTimeView,
                mApplyEndTimeView}) {
            if (!checkRequiredFieldView(fieldView)) {
                ok = false;
            }
        }

        if (ok) {
            long faultStartTime = Numbers.longValue((Long) mFaultStartTimeView.getTag());
            long applyStartTime = Numbers.longValue((Long) mApplyStartTimeView.getTag());
            long applyEndTime = Numbers.longValue((Long) mApplyEndTimeView.getTag());
            if(faultStartTime > applyStartTime){
                new AlertDialog.Builder(getActivity())
                        .setTitle("温馨提示")
                        .setMessage("申请开始时间要大于故障开始时间！\n" +
                                "当前申请开始时间为：\n" + DATE_FORMAT.format(applyStartTime) + "\n" +
                                "当前故障开始时间为：\n" + DATE_FORMAT.format(faultStartTime))
                        .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

                            }
                        }).setCancelable(false)
                        .show();
                return;

            }

            if(applyStartTime > applyEndTime){
                new AlertDialog.Builder(getActivity())
                        .setTitle("温馨提示")
                        .setMessage("申请结束时间要大于申请开始时间！\n" +
                                "当前申请开始时间为：\n" + DATE_FORMAT.format(applyStartTime) + "\n" +
                                "当前申请结束时间为：\n" + DATE_FORMAT.format(applyEndTime))
                        .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

                            }
                        }).setCancelable(false)
                        .show();
                return;
            }

            final ContentResolver cr = getContext().getContentResolver();
            try {

                ProgressDialog progressUpload = initProgressDialog("创建CM工单");
                progressSwitch = progressUpload;
                progressSwitch.show();

                long currectTime = System.currentTimeMillis();
                CmWorkContentValues values = new CmWorkContentValues()
                        .putUserId(mAuthenticator.getAuthenticatedUserId())
                        .putDeviceCode(mPhysicCodeView.getText().toString())
                        .putDeviceName(mPhysicNameView.getText().toString())
                        .putReporterTypeText(mReporterTypeView.getText().toString())
                        .putReporterTypeCode(mReporterTypeView.getTag().toString())
                        .putReporter(mAuthenticator.getAuthenticatedUserId())
                        .putPrepareMan(mAuthenticator.getAuthenticatedUserId())
                        .putDispose(mAuthenticator.getAuthenticatedUserId())
                        .putWorkarea(getWorkarea(mAuthenticator.getAuthenticatedUserId()))
                        .putInstruct("CM故障工单")
                        .putOperatorCode(mAuthenticator.getAuthenticatedUserId())
                        .putPriority(mPriorityView.getTag().toString())
                        .putFaultStartTime(Numbers.longValue((Long) mFaultStartTimeView.getTag()))
                        .putApplySTime(Numbers.longValue((Long) mApplyStartTimeView.getTag()))
                        .putApplyFTime(Numbers.longValue((Long) mApplyEndTimeView.getTag()))
                        .putOrderReceiveTime(currectTime)
                        .putArriveTime(currectTime)
                        .putOperatorText(mAuthenticator.getAuthenticatedUserId())
                        .putOperatorCode(mAuthenticator.getAuthenticatedUserId())
                        .putStatus(CmWorkStatus.NEW)
                        .putLastModified(currectTime)
                        .putUploadPending(true);
                if (mUri != null) {
                    cr.update(mUri, values.values(), null, null);
                } else {
                    values.putGuid(UUID.randomUUID().toString());
                    mUri = values.insert(cr);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            mEdited = false;

            final CmWorkCursor cur = new CmWorkCursor(getContext().getContentResolver().query(mUri, null, null, null, null));
            try {
                final List<CmWork> list = new ArrayList<>(cur.getCount());
                while (cur.moveToNext()) {
                    final CmWork cmWork = new CmWork();
                    cmWork.setOrderId(cur.getOrderId());
                    cmWork.setGuid(cur.getGuid());
                    cmWork.setPriority(cur.getPriority());
                    cmWork.setEquitCode(cur.getDeviceCode());
                    cmWork.setEquitName(cur.getDeviceName());
                    cmWork.setSymptomCode("");
                    cmWork.setFaultNote("");
                    cmWork.setDiscovererTypeCode(cur.getReporterTypeCode());
                    cmWork.setInstruct(cur.getInstruct());
                    cmWork.setReportedBy(cur.getReporter());
                    cmWork.setPrepareMan(cur.getPrepareMan());
                    cmWork.setDisposePerson(cur.getDispose());
                    cmWork.setWorkArea(getWorkarea(mAuthenticator.getAuthenticatedUserId()));
                    cmWork.setFaultStartTime(new Date(cur.getFaultStartTime()));
                    cmWork.setApplyStartTime(new Date(cur.getApplySTime()));
                    cmWork.setApplyEndTime(new Date(cur.getApplyFTime()));
                    cmWork.setOrderRecvTime(new Date(cur.getOrderReceiveTime()));
                    cmWork.setArriveTime( new Date(cur.getArriveTime()));
                    cmWork.setSaveDate(new Date(cur.getLastModified()));
                    list.add(cmWork);
                }
                CmWorks cmWorks = new CmWorks();
                cmWorks.setCmWorks(list);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        cmService.listNewCMWorkIDs(cmWorks).subscribe(resp -> {
                            for (CmWorkID no : resp) {
                        new CmWorkContentValues()
                                .putOrderId(no.getOrderId())
                                .putUploadPending(false)
                                .putStatus(CmWorkStatus.RELEASED)
                                .update(getActivity().getContentResolver(), new CmWorkSelection().guid(no.getGuid()));

                                Message msg = new Message();
                                msg.what = 123;
                                myHandler.sendMessage(msg);
                            }
                        }, e -> {
                            LogUtil.info(TAGINFO, "Failed to upload cm work NEW >> " + e);
                            Message msg = new Message();
                            msg.what = 123;
                            myHandler.sendMessage(msg);
                        });
                    }
                }).start();

            }finally {
                cur.close();
            }
        }
    }

    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 123){
                progressSwitch.dismiss();
                getActivity().finish();
            }
        }
    };

    static ProgressDialog progressSwitch;
    private ProgressDialog initProgressDialog(String title){
        ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIcon(R.drawable.ic_settings_white_24dp);
        progress.setTitle(title);
        progress.setMessage("请稍后...");
        progress.setIndeterminate(false);

        return progress;
    }


    private String getWorkarea(String userID){
        String userWorkarea = "";
        if (userID != null) {
            UserSelection userSelection = new UserSelection().userId(userID);
            UserCursor userCursor = userSelection.query(getContext().getContentResolver());
            try {
                while (userCursor.moveToNext()) {
                    userWorkarea = userCursor.getOrgCode();
                }
            } finally {
                userCursor.close();
            }
        }
        return userWorkarea;
    }

    private boolean checkRequiredFieldView(TextView fieldView) {
        if (TextUtils.isEmpty(fieldView.getText())) {
            final TextInputLayout inputLayout = ((TextInputLayout) fieldView.getParent());
            inputLayout.setErrorEnabled(true);
            inputLayout.setError(getString(R.string.error_required));
            return false;
        }
        return true;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.cm_editor_new, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        if (itemId == R.id.action_done) {
            checkAndSave();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    void onDevicePhysicCodeChanged(CharSequence devicePhysicCodeText) {
        final String devicePhysicCode = devicePhysicCodeText.toString();
        mPhysicCodeView.setTag(devicePhysicCode);

        DevicePhysicsCursor devicePhysicsCursor = new DevicePhysicsSelection()
                .codePhysics(devicePhysicCode).query(getContext().getContentResolver());
        try {

            if (devicePhysicsCursor.moveToNext()) {
                mDeviceCodeView.setText(devicePhysicsCursor.getCode());
                mPhysicNameView.setText(devicePhysicsCursor.getName());

                final DeviceCursor cur = new DeviceSelection().code(devicePhysicsCursor.getCode()).query(getContext().getContentResolver());
                try {
                    if(cur.moveToNext()){
                        mDeviceNameView.setText(cur.getName());
                    }
                } finally {
                    cur.close();
                }

            } else {
                mPhysicNameView.setText(null);
                mDeviceCodeView.setText(null);
                mDeviceNameView.setText(null);
            }
            if(mPhysicNameView.getText().toString() != null){
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mPhysicNameView.getWindowToken(), 0);
            }
            if(mDeviceCodeView.getText().toString() != null){
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mDeviceCodeView.getWindowToken(), 0);
            }
            if(mDeviceNameView.getText().toString() != null){
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mDeviceNameView.getWindowToken(), 0);
            }
        } finally {
            devicePhysicsCursor.close();
        }
    }

    void onDevicePhysicNameChanged(CharSequence devicePhysicNameText) {
        String devicePhysicName = devicePhysicNameText.toString();


        DevicePhysicsCursor devicePhysicsCursor = new DevicePhysicsSelection()
                .name(devicePhysicName).query(getContext().getContentResolver());

        try{
            final List<String> listPhysicCode = new ArrayList<String>();
            String deviceLogicName = "";
            String deviceType = "";

            while (devicePhysicsCursor.moveToNext()) {
                listPhysicCode.add(devicePhysicsCursor.getCodePhysics());
                deviceType = devicePhysicsCursor.getType();

                mDeviceCodeView.setText(devicePhysicsCursor.getCode());

                final DeviceCursor cur = new DeviceSelection().code(devicePhysicsCursor.getCode()).query(getContext().getContentResolver());
                try {
                    if (cur.moveToNext()) {
                        deviceLogicName = cur.getName();
                    }
                } finally {
                    cur.close();
                }
            }
            if (listPhysicCode.size() > 0) {
                String devicePhysicsCode = listPhysicCode.get(0);
                mPhysicCodeView.setText(devicePhysicsCode);
                mPhysicCodeView.setTag(devicePhysicsCode);

                mDeviceNameView.setText(deviceLogicName);
                mPhysicNameView.setText(getDevicePhysicName(devicePhysicsCode));


                mPhysicCodeView.setAdapter(new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_dropdown_item_1line, listPhysicCode));

            } else {
                mPhysicCodeView.setText(null);
                mDeviceCodeView.setText(null);
                mDeviceNameView.setText(null);
            }

            if(mPhysicCodeView.getText().toString() != null){
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mPhysicCodeView.getWindowToken(), 0);
            }
            if(mDeviceCodeView.getText().toString() != null){
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mDeviceCodeView.getWindowToken(), 0);
            }
            if(mDeviceNameView.getText().toString() != null){
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mDeviceNameView.getWindowToken(), 0);
            }
        }finally {
            devicePhysicsCursor.close();
        }
    }

    void onDeviceLogicCodeChanged(CharSequence deviceLogicCodeText){
        final String deviceLogicCode = deviceLogicCodeText.toString();

        DevicePhysicsCursor devicePhysicsCursor = new DevicePhysicsSelection()
                .code(deviceLogicCode).query(getContext().getContentResolver());

        try {
            final List<String> listPhysicCode = new ArrayList<String>();
            String deviceName = "";
            String deviceType = "";
            while(devicePhysicsCursor.moveToNext()){
                listPhysicCode.add(devicePhysicsCursor.getCodePhysics());
                deviceType = devicePhysicsCursor.getType();

                if(deviceName.length() < 1){
                    final DeviceCursor cur = new DeviceSelection().code(devicePhysicsCursor.getCode()).query(getContext().getContentResolver());
                    try {
                        if(cur.moveToNext()){
                            deviceName = cur.getName();
                        }
                    } finally {
                        cur.close();
                    }
                }

            }

            if (listPhysicCode.size() > 0) {
                String devicePhysicsCode = listPhysicCode.get(0);
                mPhysicCodeView.setText(devicePhysicsCode);
                mPhysicCodeView.setTag(devicePhysicsCode);

                mDeviceNameView.setText(deviceName);
                mPhysicNameView.setText(getDevicePhysicName(devicePhysicsCode));

                mPhysicCodeView.setAdapter(new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_dropdown_item_1line, listPhysicCode));

            } else {
                mPhysicCodeView.setText(null);
                mPhysicNameView.setText(null);
                mDeviceNameView.setText(null);
            }
            if(mPhysicCodeView.getText().toString() != null){
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mPhysicCodeView.getWindowToken(), 0);
            }
            if(mPhysicNameView.getText().toString() != null){
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mPhysicNameView.getWindowToken(), 0);
            }
            if(mDeviceNameView.getText().toString() != null){
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mDeviceNameView.getWindowToken(), 0);
            }
        } finally {
            devicePhysicsCursor.close();
        }
    }

    void onDeviceNameChanged(CharSequence deviceNameText){
        final String deviceName = deviceNameText.toString();
        String deviceLogicCode = "";

        final DeviceCursor cur = new DeviceSelection().name(deviceName).query(getContext().getContentResolver());
        try {
            if(cur.moveToNext()){
                deviceLogicCode = cur.getCode();
                mDeviceCodeView.setText(deviceLogicCode);
            }
        } finally {
            cur.close();
        }

        final DevicePhysicsCursor devicePhysicsCursor = new DevicePhysicsSelection()
                .code(deviceLogicCode).query(getContext().getContentResolver());

        try {
            final List<String> listPhysicCode = new ArrayList<String>();
            String deviceType = "";
            while(devicePhysicsCursor.moveToNext()){
                listPhysicCode.add(devicePhysicsCursor.getCodePhysics());
                deviceType = devicePhysicsCursor.getType();
            }

            if (listPhysicCode.size() > 0) {
                String devicePhysicsCode = listPhysicCode.get(0);
                mPhysicCodeView.setText(devicePhysicsCode);
                mPhysicCodeView.setTag(devicePhysicsCode);

                mPhysicNameView.setText(getDevicePhysicName(devicePhysicsCode));

                mPhysicCodeView.setAdapter(new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_dropdown_item_1line, listPhysicCode));
            }else {
                mPhysicCodeView.setText(null);
                mPhysicNameView.setText(null);
                mDeviceCodeView.setText(null);
            }

            if(mPhysicCodeView.getText().toString() != null){
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mPhysicCodeView.getWindowToken(), 0);
            }
            if(mPhysicNameView.getText().toString() != null){
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mPhysicNameView.getWindowToken(), 0);
            }
            if(mDeviceCodeView.getText().toString() != null){
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mDeviceCodeView.getWindowToken(), 0);
            }

        }finally {
            devicePhysicsCursor.close();
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


    private void setupDeviceCodeView() {

        setupFieldView(mPhysicCodeView);
        setShowDropdownListeners(mPhysicCodeView);

        setupFieldView(mPhysicNameView);
        setShowDropdownListeners(mPhysicNameView);

        setupFieldView(mDeviceCodeView);
        setShowDropdownListeners(mDeviceCodeView);

        setupFieldView(mDeviceNameView);
        setShowDropdownListeners(mDeviceNameView);

        final DevicePhysicsCursor devicePhysicsCursor = new DevicePhysicsSelection().query(getContext().getContentResolver());
        try {
            final List<String> listPhysicCode = new ArrayList<>();
            final List<String> listPhysicName = new ArrayList<>();
            final List<String> listLogicCode = new ArrayList<>();
            final List<String> listLogicName = new ArrayList<>();
            while (devicePhysicsCursor.moveToNext()) {
                listPhysicCode.add(devicePhysicsCursor.getCodePhysics());
                if(!listLogicCode.contains(devicePhysicsCursor.getCode())){
                    listLogicCode.add(devicePhysicsCursor.getCode());
                }

                if(!listPhysicName.contains(devicePhysicsCursor.getName())){
                    listPhysicName.add(devicePhysicsCursor.getName());
                }
                final DeviceCursor cur = new DeviceSelection().code(devicePhysicsCursor.getCode()).query(getContext().getContentResolver());
                try {
                    if(cur.moveToNext()){
                        if(!listLogicName.contains(cur.getName())){
                            listLogicName.add(cur.getName());
                        }
                    }
                } finally {
                    cur.close();
                }

            }
            mPhysicCodeView.setAdapter(new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_dropdown_item_1line, listPhysicCode));
            mPhysicCodeView.setOnItemClickListener((parent, view, position, id) -> {

                String selectedDeviceCode = (String) parent.getItemAtPosition(position);
                onDevicePhysicCodeChanged(selectedDeviceCode);
            });

            mPhysicNameView.setAdapter(new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_dropdown_item_1line, listPhysicName));
            mPhysicNameView.setOnItemClickListener((parent, view, position, id) -> {
                String selectedDevicePhysicName = (String) parent.getItemAtPosition(position);
                onDevicePhysicNameChanged(selectedDevicePhysicName);
            });



            mDeviceCodeView.setAdapter(new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_dropdown_item_1line, listLogicCode));
            mDeviceCodeView.setOnItemClickListener((parent, view, position, id) -> {
                String selectedDeviceLogicCode = (String) parent.getItemAtPosition(position);
                onDeviceLogicCodeChanged(selectedDeviceLogicCode);
            });

            mDeviceNameView.setAdapter(new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_dropdown_item_1line, listLogicName));
            mDeviceNameView.setOnItemClickListener((parent, view, position, id) -> {
                String selectedDeviceName = (String) parent.getItemAtPosition(position);
                onDeviceNameChanged(selectedDeviceName);

            });
        } finally {
            devicePhysicsCursor.close();
        }
    }

    private void setupDropDownFieldView(final AutoCompleteTextView fieldView, String param, final String parentCode) {
        setupFieldView(fieldView);

        setShowDropdownListeners(fieldView);

        fieldView.setOnItemClickListener((parent, view, position, id) -> {
            final FieldValueAdapter.FieldValue value = (FieldValueAdapter.FieldValue) parent.getItemAtPosition(position);
            fieldView.setTag(value.code);
            hideSoftKeyboard(fieldView);

            final int viewId = fieldView.getId();
            switch (viewId) {
                case R.id.fault_type:
                    break;
            }
            switch (viewId) {
                case R.id.fault_type:
                    break;
            }
        });

        if (param == null)
            return;

        final ParamValueSelection sel = new ParamValueSelection().type(param);
        switch (fieldView.getId()) {
            case R.id.fault_type:
            case R.id.fault_cause:
                sel.and().parentCodeContains(parentCode);
                break;
        }
        final ParamValueCursor cur = sel.query(getContext().getContentResolver());
        try {
            final List<FieldValueAdapter.FieldValue> values = new ArrayList<>(cur.getCount());
            while (cur.moveToNext()) {
                final FieldValueAdapter.FieldValue value = new FieldValueAdapter.FieldValue();
                value.code = cur.getCode();
                value.value = cur.getValue();
                values.add(value);
            }
            fieldView.setAdapter(new FieldValueAdapter(getContext(), values));
        } finally {
            cur.close();
        }
    }

    private void setShowDropdownListeners(AutoCompleteTextView fieldView) {
        final View.OnClickListener onClickListener = v -> {
            if (fieldView.isFocused()) {
                onFieldViewFocused(fieldView);
            } else {
                fieldView.requestFocus();
            }
        };
        fieldView.setOnClickListener(onClickListener);
        if (fieldView.getParent() instanceof TextInputLayout) {
            ((TextInputLayout) fieldView.getParent()).setOnClickListener(onClickListener);
        }

        fieldView.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                onFieldViewFocused((AutoCompleteTextView) view);
            }
        });
    }

    private void onFieldViewFocused(AutoCompleteTextView fieldView) {
        if (fieldView.getAdapter() == null) {
            switch (fieldView.getId()) {
                case R.id.fault_type:
                    toastShort("请先选择故障现象");
                    break;
                case R.id.fault_cause:
                    toastShort("请先选择故障类型");
                    break;
            }
        } else {
            fieldView.showDropDown();
        }
    }

    private void setupFieldView(final TextView fieldView) {
        fieldView.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                mEdited = true;
                final ViewParent parent = fieldView.getParent();
                if (parent instanceof TextInputLayout) {
                    ((TextInputLayout) parent).setError(null);
                    ((TextInputLayout) parent).setErrorEnabled(false);
                }
            }
        });
    }

    @OnFocusChange({R.id.fault_start_time, R.id.apply_start_time, R.id.apply_end_time})
    @OnClick({R.id.input_apply_start_time, R.id.input_apply_end_time, R.id.input_fault_start_time, R.id.apply_start_time, R.id.apply_end_time, R.id.fault_start_time})
    void onDateTimeViewClick(View view) {
        View fieldView = null;
        if (view instanceof TextInputLayout) {
            for (int i = 0, count = ((TextInputLayout) view).getChildCount(); i < count; i++) {
                if (view instanceof EditText) {
                    fieldView = view;
                    break;
                }
            }
        } else if (view.hasFocus()) {
            fieldView = view;
        }

        if (fieldView != null) {
            switch (view.getId()) {
                case R.id.apply_start_time:
                    openDateTimePickerUpon((TextView) view, getString(R.string.hint_apply_start_time));
                    break;
                case R.id.apply_end_time:
                    openDateTimePickerUpon((TextView) view, getString(R.string.hint_apply_end_time));
                    break;
                case R.id.fault_start_time:
                    openDateTimePickerUpon((TextView) view, getString(R.string.hint_fault_start_time));
                    break;
            }
        }
    }

    private void hideSoftKeyboard(View view) {
        if (view != null) {
            final InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private String getPriorityValue(String priorityCode){
        String priorityValue = "";
        final ParamValueCursor paramValueCursor = new ParamValueSelection()
                .code(priorityCode).query(getContext().getContentResolver());
        try {
            while (paramValueCursor.moveToNext()) {
                priorityValue = paramValueCursor.getValue();
            }
        } finally {
            paramValueCursor.close();
        }

        return priorityValue;
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

}