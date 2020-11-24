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
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

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
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.model.ParamValue;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.device.DeviceCursor;
import cc.xingyan.android.afc.provider.device.DeviceSelection;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsCursor;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsSelection;
import cc.xingyan.android.afc.provider.paramvalue.ParamValueCursor;
import cc.xingyan.android.afc.provider.paramvalue.ParamValueSelection;
import cc.xingyan.android.afc.provider.workorder.SyncStatus;
import cc.xingyan.android.afc.provider.workorder.WorkOrderContentValues;
import cc.xingyan.android.afc.provider.workorder.WorkOrderCursor;
import cc.xingyan.android.afc.util.Action;
import cc.xingyan.android.afc.util.Numbers;
import cc.xingyan.android.afc.widget.FieldValueAdapter;
import cc.xingyan.android.afc.widget.SimpleTextWatcher;
import icepick.State;

/**
 * Created by San on 9/23/15.
 */
public class WorkOrderEditorFragment extends BaseFragment implements Action.Interceptor {

    private static final String ARG_URI = "uri";

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Bind(R.id.physic_code_word_order) AutoCompleteTextView mPhysicCodeView;
    @Bind(R.id.physic_name_word_order) AutoCompleteTextView mPhysicNameView;
    @Bind(R.id.device_code) AutoCompleteTextView mLogicCodeView;
    @Bind(R.id.device_name) AutoCompleteTextView mLogicNameView;
    @Bind(R.id.fault_description)
    AutoCompleteTextView mFaultDescriptionView;
    @Bind(R.id.fault_type)
    AutoCompleteTextView mFaultTypeView;
    @Bind(R.id.reporter_type)
    AutoCompleteTextView mReporterTypeView;
    @Bind(R.id.fault_note)
    EditText mFaultNoteView;
    @Bind(R.id.fault_start_time)
    EditText mFaultStartTimeView;
    @Bind(R.id.fault_cause)
    AutoCompleteTextView mFaultCauseView;
    @Bind(R.id.operation)
    AutoCompleteTextView mOperationView;
    @Bind(R.id.operation_result)
    AutoCompleteTextView mOperationResultView;
    @Bind(R.id.operation_note)
    EditText mOperationNoteView;
    @Bind(R.id.operation_start_time)
    EditText mOperationStartTimeView;
    @Bind(R.id.operation_end_time)
    EditText mOperationEndTimeView;

    @Bind(R.id.operator_name_tv)
    TextView operatorNameTv;

    @Inject
    Authenticator mAuthenticator;

    @State
    boolean mEdited;

    private Uri mUri;
    private String mDeviceId;

    public static Fragment newInstance(Uri uri, String deviceId) {
        WorkOrderEditorFragment fragment = new WorkOrderEditorFragment();
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
        return inflater.inflate(R.layout.fragment_work_order_editor, container, false);
    }



    void onDevicePhysicCodeChanged(CharSequence devicePhysicCodeText) {
        final String devicePhysicCode = devicePhysicCodeText.toString();
        mPhysicCodeView.setTag(devicePhysicCode);
        mFaultDescriptionView.setAdapter(null);
        mFaultDescriptionView.setTag(null);
        mFaultDescriptionView.setText(null);
        mFaultNoteView.setText(null);


        DevicePhysicsCursor devicePhysicsCursor = new DevicePhysicsSelection()
                .codePhysics(devicePhysicCode).query(getContext().getContentResolver());
        try {

            if (devicePhysicsCursor.moveToNext()) {
                mLogicCodeView.setText(devicePhysicsCursor.getCode());
                mPhysicNameView.setText(devicePhysicsCursor.getName());

                final DeviceCursor cur = new DeviceSelection().code(devicePhysicsCursor.getCode()).query(getContext().getContentResolver());
                try {
                    if(cur.moveToNext()){
                        mLogicNameView.setText(cur.getName());
                    }
                } finally {
                    cur.close();
                }
                setupDropDownFieldView(mFaultDescriptionView, ParamValue.PARAM_FAULT_DESCRIPTION, devicePhysicsCursor.getType());
            } else {
                mPhysicNameView.setText(null);
                mLogicCodeView.setText(null);
                mLogicNameView.setText(null);
            }
            if(mPhysicNameView.getText().toString() != null){
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mPhysicNameView.getWindowToken(), 0);
            }
            if(mLogicCodeView.getText().toString() != null){
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mLogicCodeView.getWindowToken(), 0);
            }
            if(mLogicNameView.getText().toString() != null){
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mLogicNameView.getWindowToken(), 0);
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

                mLogicCodeView.setText(devicePhysicsCursor.getCode());

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

                mLogicNameView.setText(deviceLogicName);
                mPhysicNameView.setText(getDevicePhysicName(devicePhysicsCode));


                setupDropDownFieldView(mFaultDescriptionView, ParamValue.PARAM_FAULT_DESCRIPTION, deviceType);

                mPhysicCodeView.setAdapter(new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_dropdown_item_1line, listPhysicCode));

            } else {
                mPhysicCodeView.setText(null);
                mLogicCodeView.setText(null);
                mLogicNameView.setText(null);
            }

            if(mPhysicCodeView.getText().toString() != null){
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mPhysicCodeView.getWindowToken(), 0);
            }
            if(mLogicCodeView.getText().toString() != null){
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mLogicCodeView.getWindowToken(), 0);
            }
            if(mLogicNameView.getText().toString() != null){
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mLogicNameView.getWindowToken(), 0);
            }
        }finally {
            devicePhysicsCursor.close();
        }

    }



    void onDeviceLogicCodeChanged(CharSequence deviceLogicCodeText){
        final String deviceLogicCode = deviceLogicCodeText.toString();


        mFaultDescriptionView.setAdapter(null);
        mFaultDescriptionView.setTag(null);
        mFaultDescriptionView.setText(null);
        mFaultNoteView.setText(null);

        DevicePhysicsCursor devicePhysicsCursor = new DevicePhysicsSelection()
                .code(deviceLogicCode).query(getContext().getContentResolver());

        try {
            final List<String> listPhysicCode = new ArrayList<String>();
            String deviceLogicName = "";
            String deviceType = "";
            while(devicePhysicsCursor.moveToNext()){
                listPhysicCode.add(devicePhysicsCursor.getCodePhysics());
                deviceType = devicePhysicsCursor.getType();


                final DeviceCursor cur = new DeviceSelection().code(devicePhysicsCursor.getCode()).query(getContext().getContentResolver());
                try {
                    if(cur.moveToNext()){
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

                mLogicNameView.setText(deviceLogicName);
                mPhysicNameView.setText(getDevicePhysicName(devicePhysicsCode));


                setupDropDownFieldView(mFaultDescriptionView, ParamValue.PARAM_FAULT_DESCRIPTION, deviceType);

                mPhysicCodeView.setAdapter(new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_dropdown_item_1line, listPhysicCode));

            } else {
                mPhysicCodeView.setText(null);
                mPhysicNameView.setText(null);
                mLogicNameView.setText(null);
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
            if(mLogicNameView.getText().toString() != null){
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mLogicNameView.getWindowToken(), 0);
            }

        } finally {
            devicePhysicsCursor.close();
        }
    }



    void onDeviceLogicNameChanged(CharSequence deviceLogicNameText){
        final String deviceName = deviceLogicNameText.toString();
        String deviceLogicCode = "";

        mFaultDescriptionView.setAdapter(null);
        mFaultDescriptionView.setTag(null);
        mFaultDescriptionView.setText(null);
        mFaultNoteView.setText(null);



        final DeviceCursor cur = new DeviceSelection().name(deviceName).query(getContext().getContentResolver());
        try {
            if(cur.moveToNext()){
                deviceLogicCode = cur.getCode();
                mLogicCodeView.setText(deviceLogicCode);
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

                setupDropDownFieldView(mFaultDescriptionView,
                        ParamValue.PARAM_FAULT_DESCRIPTION, deviceType);

                mPhysicCodeView.setAdapter(new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_dropdown_item_1line, listPhysicCode));
            }else {
                mPhysicCodeView.setText(null);
                mPhysicNameView.setText(null);
                mLogicCodeView.setText(null);
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
            if(mLogicCodeView.getText().toString() != null){
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mLogicCodeView.getWindowToken(), 0);
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


    @OnFocusChange({R.id.operation_start_time, R.id.operation_end_time, R.id.fault_start_time})
    @OnClick({R.id.input_operation_end_time, R.id.input_operation_start_time, R.id.input_fault_start_time, R.id.operation_start_time, R.id.operation_end_time, R.id.fault_start_time})
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
                case R.id.operation_start_time:
                    openDateTimePickerUpon((TextView) view, getString(R.string.hint_operation_start_time));
                    break;
                case R.id.operation_end_time:
                    openDateTimePickerUpon((TextView) view, getString(R.string.hint_operation_end_time));
                    break;
                case R.id.fault_start_time:
                    openDateTimePickerUpon((TextView) view, getString(R.string.hint_fault_start_time));
                    break;
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        operatorNameTv.setText(mAuthenticator.getAuthenticatedUserName());

        setHasOptionsMenu(true);

        if (mUri != null) {
            getActivity().setTitle(R.string.title_activity_edit_work_order);

            loadWorkOrder();
        } else {
            getActivity().setTitle(R.string.title_activity_new_work_order);

            long now = System.currentTimeMillis();
            mOperationStartTimeView.setTag(now);
            mOperationStartTimeView.setText(DATE_FORMAT.format(now));
            mOperationEndTimeView.setTag(now);
            mOperationEndTimeView.setText(DATE_FORMAT.format(now));
            mFaultStartTimeView.setTag(now);
            mFaultStartTimeView.setText(DATE_FORMAT.format(now));

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
        }

        if(mDeviceId == null){
            setupDeviceCodeView();
        }else{
            mPhysicCodeView.setFocusable(false);
            mPhysicNameView.setFocusable(false);
            mLogicCodeView.setFocusable(false);
            mLogicNameView.setFocusable(false);
        }
        setupDropDownFieldView(mReporterTypeView, ParamValue.PARAM_REPORTER_TYPE, null);
        setupDropDownFieldView(mOperationView, ParamValue.PARAM_OPERATION, null);
        setupDropDownFieldView(mOperationResultView, ParamValue.PARAM_OPERATION_RESULT, null);
        setShowDropdownListeners(mFaultDescriptionView);
        setShowDropdownListeners(mFaultTypeView);
        setShowDropdownListeners(mFaultCauseView);
        setupFieldView(mFaultNoteView);
        setupFieldView(mOperationNoteView);
        if (mDeviceId != null) {
            final String deviceCode = mDeviceId;
            mPhysicCodeView.setText(deviceCode);
            mPhysicCodeView.setTag(deviceCode);
            mFaultDescriptionView.setAdapter(null);
            mFaultDescriptionView.setTag(null);
            mFaultDescriptionView.setText(null);
            mFaultNoteView.setText(null);
            mFaultTypeView.setAdapter(null);
            mFaultTypeView.setTag(null);
            mFaultTypeView.setText(null);
            mFaultCauseView.setAdapter(null);
            mFaultCauseView.setTag(null);
            mFaultCauseView.setText(null);

            DevicePhysicsSelection devicePhysicsSelection = new DevicePhysicsSelection().codePhysics(deviceCode);
            DevicePhysicsCursor devicePhysicsCursor = devicePhysicsSelection.query(getContext().getContentResolver());
            try {
                if (devicePhysicsCursor.moveToNext()) {
                    mPhysicNameView.setText(devicePhysicsCursor.getName());
                    mLogicCodeView.setText(devicePhysicsCursor.getCode());

                    final DeviceCursor cur = new DeviceSelection().code(devicePhysicsCursor.getCode()).query(getContext().getContentResolver());
                    try {
                        if(cur.moveToNext()){
                            mLogicNameView.setText(cur.getName());

                        }
                    } finally {
                        cur.close();
                    }
                    setupDropDownFieldView(mFaultDescriptionView, ParamValue.PARAM_FAULT_DESCRIPTION, devicePhysicsCursor.getType());
                }
            } finally {
                devicePhysicsCursor.close();
            }
        }
    }

    private void loadWorkOrder() {
        WorkOrderCursor c = new WorkOrderCursor(getContext().getContentResolver().query(mUri, null, null, null, null));
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

    private void setupDeviceCodeView() {
        setupFieldView(mPhysicCodeView);
        setShowDropdownListeners(mPhysicCodeView);

        setupFieldView(mPhysicNameView);
        setShowDropdownListeners(mPhysicNameView);

        setupFieldView(mLogicCodeView);
        setShowDropdownListeners(mLogicCodeView);

        setupFieldView(mLogicNameView);
        setShowDropdownListeners(mLogicNameView);

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



            mLogicCodeView.setAdapter(new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_dropdown_item_1line, listLogicCode));
            mLogicCodeView.setOnItemClickListener((parent, view, position, id) -> {
                String selectedDeviceLogicCode = (String) parent.getItemAtPosition(position);
                onDeviceLogicCodeChanged(selectedDeviceLogicCode);
            });

            mLogicNameView.setAdapter(new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_dropdown_item_1line, listLogicName));
            mLogicNameView.setOnItemClickListener((parent, view, position, id) -> {
                String selectedDeviceLogicName = (String) parent.getItemAtPosition(position);
                onDeviceLogicNameChanged(selectedDeviceLogicName);

            });
        } finally {
            devicePhysicsCursor.close();
        }
    }

    static boolean isCheckFaultType = false;
    static boolean isCheckFaultDescription = false;
    private void setupDropDownFieldView(final AutoCompleteTextView fieldView, String param, final String parentCode) {



        setupFieldView(fieldView);

        setShowDropdownListeners(fieldView);

        fieldView.setOnItemClickListener((parent, view, position, id) -> {
            final FieldValueAdapter.FieldValue value = (FieldValueAdapter.FieldValue) parent.getItemAtPosition(position);
            fieldView.setTag(value.code);
            hideSoftKeyboard(fieldView);

            final int viewId = fieldView.getId();
            switch (viewId) {
                case R.id.fault_description:
                    isCheckFaultDescription = false;
                    mFaultNoteView.setText(null);
                    mFaultTypeView.setAdapter(null);
                    mFaultTypeView.setTag(null);
                    mFaultTypeView.setText(null);

                case R.id.fault_type:
                    isCheckFaultType = false;
                    isCheckFaultDescription = false;
                    mFaultCauseView.setAdapter(null);
                    mFaultCauseView.setTag(null);
                    mFaultCauseView.setText(null);
                    break;
            }
            switch (viewId) {
                case R.id.fault_description:
                    isCheckFaultDescription = true;
                    setupDropDownFieldView(mFaultTypeView, ParamValue.PARAM_FAULT_TYPE, value.code);
                    if (!value.code.endsWith("99")) {
                        isCheckFaultDescription = false;
                    }
                    break;
                case R.id.fault_type:
                    if (!value.code.endsWith("99") || !isCheckFaultDescription) {
                        isCheckFaultType = true;
                    }
                    setupDropDownFieldView(mFaultCauseView, ParamValue.PARAM_FAULT_CAUSE, value.code);
                    break;
            }
        });

        if (param == null)
            return;

        final ParamValueSelection sel = new ParamValueSelection().type(param);
        switch (fieldView.getId()) {
            case R.id.fault_description:
            case R.id.fault_type:
            case R.id.fault_cause:
                sel.and().parentCodeContains(parentCode);
                break;
        }
        final ParamValueCursor cur = sel.query(getContext().getContentResolver());
        String typeFirstLetter = "";
        boolean isgetFirstLette = false;
        try {
            final List<FieldValueAdapter.FieldValue> values = new ArrayList<>(cur.getCount());
            while (cur.moveToNext()) {
                final FieldValueAdapter.FieldValue value = new FieldValueAdapter.FieldValue();
                value.code = cur.getCode();
                value.value = cur.getValue();
                values.add(value);

                if(!isgetFirstLette && cur.getCode().matches("^[A-Z][0-9]*$")){
                    typeFirstLetter = cur.getCode().substring(0, 1);
                    isgetFirstLette = true;
                }
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
        if(fieldView.getAdapter() == null) {
            switch (fieldView.getId()) {
                case R.id.fault_description:
                    if(mPhysicCodeView.getText() == null || mPhysicCodeView.getText().toString().trim().length() < 1){
                        toastShort("请先选择设备");
                    }
                    break;
                case R.id.fault_type:
                    if(mFaultDescriptionView.getText() == null || mFaultDescriptionView.getText().toString().trim().length() < 1){
                        toastShort("请先选择故障现象");
                    }
                    break;
                case R.id.fault_cause:
                    if(mFaultTypeView.getText() == null || mFaultTypeView.getText().toString().trim().length() < 1){
                        toastShort("请先选择故障类型");
                    }
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


                if (fieldView.getId() == R.id.fault_description) {
                    mFaultNoteView
                            .setVisibility("其他".equals(s.toString()) || "其它".equals(s.toString()) ? View.VISIBLE : View.GONE);

                }
            }
        });
    }

    private void hideSoftKeyboard(View view) {
        if (view != null) {
            final InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    private void bindData(WorkOrderCursor c) {
        if (c.getNo() != null) {
            final String title = getString(R.string.format_title_activity_edit_work_order, c.getNo());
            getActivity().setTitle(title);
        }

        mPhysicCodeView.setText(c.getDeviceCode());
        mPhysicNameView.setText(getDevicePhysicName(c.getDeviceCode()));
        mLogicCodeView.setText(getDeviceLogicCode(c.getDeviceCode()));
        mLogicNameView.setText(c.getDeviceName());
        mFaultDescriptionView.setText(c.getFaultDescriptionText());
        mFaultDescriptionView.setTag(c.getFaultDescriptionCode());

        if(c.getFaultDescriptionCode() != null && c.getFaultDescriptionCode().contains("99")){
            mFaultNoteView.setVisibility(View.VISIBLE);
        }else if(c.getFaultDescriptionCode() != null && !c.getFaultDescriptionCode().contains("99")){
            mFaultNoteView.setVisibility(View.GONE);
        }

        mFaultTypeView.setText(c.getFaultTypeText());
        mFaultTypeView.setTag(c.getFaultTypeCode());
        mReporterTypeView.setText(c.getReporterTypeText());
        mReporterTypeView.setTag(c.getReporterTypeCode());
        mFaultNoteView.setText(c.getFaultNote());
        mFaultCauseView.setText(c.getFaultCauseText());
        mFaultCauseView.setTag(c.getFaultCauseCode());
        mOperationView.setText(c.getOperationText());
        mOperationView.setTag(c.getOperationCode());
        mOperationResultView.setText(c.getOperationResultText());
        mOperationResultView.setTag(c.getOperationResultCode());
        mOperationNoteView.setText(c.getOperationNote());
        mOperationStartTimeView.setText(DATE_FORMAT.format(c.getOperationStartTime()));
        mOperationStartTimeView.setTag(c.getOperationStartTime());
        mOperationEndTimeView.setText(DATE_FORMAT.format(c.getOperationEndTime()));
        mOperationEndTimeView.setTag(c.getOperationEndTime());
        mFaultStartTimeView.setText(DATE_FORMAT.format(c.getFaultStartTime()));
        mFaultStartTimeView.setTag(c.getFaultStartTime());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.work_order_editor, menu);
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

    private void checkAndSave() {
        boolean ok = true;

        if (mFaultNoteView.getVisibility() == View.VISIBLE && !checkRequiredFieldView(mFaultNoteView)) {
            ok = false;
        }

        for (TextView fieldView : new TextView[]{
                mPhysicCodeView,
                mPhysicNameView,
                mLogicCodeView,
                mLogicNameView,
                mFaultDescriptionView,
                mFaultTypeView,
                mReporterTypeView,
                mFaultCauseView,
                mOperationView,
                mOperationResultView,
                mOperationStartTimeView,
                mOperationEndTimeView,
                mFaultStartTimeView}) {
            if (!checkRequiredFieldView(fieldView)) {
                ok = false;
            }
        }

        if (ok) {
            long operationStartTime = Numbers.longValue((Long) mOperationStartTimeView.getTag());
            long operationEndTime = Numbers.longValue((Long) mOperationEndTimeView.getTag());

            if(operationStartTime > operationEndTime){
                new AlertDialog.Builder(getActivity())
                        .setTitle("温馨提示")
                        .setMessage("结束时间要大于开始时间！\n" +
                                "当前开始时间为：\n" + DATE_FORMAT.format(operationStartTime) + "\n" +
                                "当前结束时间为：\n" + DATE_FORMAT.format(operationEndTime))
                        .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

                            }
                        }).setCancelable(false)
                        .show();
                return;
            }

            long sysTime = System.currentTimeMillis();
            if(operationEndTime > sysTime){
                    new AlertDialog.Builder(getActivity())
                            .setTitle("温馨提示")
                            .setMessage("结束时间要小于当前系统时间！\n" +
                                    "当前结束时间为：\n" + DATE_FORMAT.format(operationEndTime) + "\n" +
                                    "当前系统时间为：\n" + DATE_FORMAT.format(sysTime))
                            .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {

                                }
                            }).setCancelable(false)
                            .show();
                    return;
            }

            final ContentResolver cr = getContext().getContentResolver();
            WorkOrderContentValues values = new WorkOrderContentValues()
                    .putUserId(mAuthenticator.getAuthenticatedUserId())
                    .putDeviceCode(mPhysicCodeView.getText().toString())
                    .putDeviceName(mLogicNameView.getText().toString())
                    .putFaultDescriptionText(mFaultDescriptionView.getText().toString())
                    .putFaultDescriptionCode(mFaultDescriptionView.getTag().toString())
                    .putFaultTypeText(mFaultTypeView.getText().toString())
                    .putFaultTypeCode(mFaultTypeView.getTag().toString())
                    .putReporterTypeText(mReporterTypeView.getText().toString())
                    .putReporterTypeCode(mReporterTypeView.getTag().toString())
                    .putFaultNote(mFaultNoteView.getText().toString())
                    .putFaultCauseText(mFaultCauseView.getText().toString())
                    .putFaultCauseCode(mFaultCauseView.getTag().toString())
                    .putOperationText(mOperationView.getText().toString())
                    .putOperationCode(mOperationView.getTag().toString())
                    .putOperationResultText(mOperationResultView.getText().toString())
                    .putOperationResultCode(mOperationResultView.getTag().toString())
                    .putOperationNote(mOperationNoteView.getText().toString())
                    .putOperationStartTime(Numbers.longValue((Long) mOperationStartTimeView.getTag()))
                    .putOperationEndTime(Numbers.longValue((Long) mOperationEndTimeView.getTag()))
                    .putFaultStartTime(Numbers.longValue((Long) mFaultStartTimeView.getTag()))
                    .putOperatorText(mAuthenticator.getAuthenticatedUserId())
                    .putOperatorCode(mAuthenticator.getAuthenticatedUserId())
                    .putFormFlagCode("FALSE")
                    .putSyncStatus(SyncStatus.LOCAL)
                    .putLastModified(System.currentTimeMillis());
            if (mUri != null) {
                cr.update(mUri, values.values(), null, null);
            } else {
                values.putGuid(UUID.randomUUID().toString());
                mUri = values.insert(cr);
            }
            mEdited = false;
            getActivity().finish();
        }
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
    public boolean onInterceptAction(final Action action) {
        if ("finish".equals(action.name()) && mEdited) {
            new AlertDialog.Builder(getContext())
                    .setIcon(R.drawable.ic_info_black_24dp)
                    .setTitle(" ")
                    .setMessage(R.string.message_confirm_exit_work_order_editor)
                    .setPositiveButton(R.string.yes, (dialog, which) -> {
                        action.proceed();
                    })
                    .setNegativeButton(R.string.no, null)
                    .show()
                    .setCanceledOnTouchOutside(true);
            return true;
        }
        return false;
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

}
