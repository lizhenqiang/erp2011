package cc.xingyan.android.afc;

import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.textfield.TextInputLayout;
import com.zzj.afc.ui.wheelview.time.ScreenInfo;
import com.zzj.afc.ui.wheelview.time.WheelMain;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.model.ParamValue;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.photoutil.FileRecorderUtil;
import cc.xingyan.android.afc.photoutil.album.ImageFileActivity;
import cc.xingyan.android.afc.provider.cmwork.CmWorkColumns;
import cc.xingyan.android.afc.provider.cmwork.CmWorkContentValues;
import cc.xingyan.android.afc.provider.cmwork.CmWorkCursor;
import cc.xingyan.android.afc.provider.cmwork.CmWorkSelection;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsCursor;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsSelection;
import cc.xingyan.android.afc.provider.paramvalue.ParamValueCursor;
import cc.xingyan.android.afc.provider.paramvalue.ParamValueSelection;
import cc.xingyan.android.afc.provider.picture.PictureCursor;
import cc.xingyan.android.afc.provider.picture.PictureSelection;
import cc.xingyan.android.afc.provider.user.UserCursor;
import cc.xingyan.android.afc.provider.user.UserSelection;
import cc.xingyan.android.afc.util.LogUtil;
import cc.xingyan.android.afc.util.Numbers;
import cc.xingyan.android.afc.widget.FieldValueAdapter;
import cc.xingyan.android.afc.widget.SimpleTextWatcher;

/**
 * Created by San on 12/11/15.
 *
 *
 */
public class CmReportFragment extends BaseFragment {

    private static final String ARG_URI = "uri";
    private static final String ARG_CM_ORDER_ID = "cm_order_id";
    private Callbacks callbacks;
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    @Bind(R.id.intcustomer_no)
    AutoCompleteTextView intCustomerNoView;


    @Bind(R.id.cm_equipfault)
    AutoCompleteTextView cmEquipfault;

    @Bind(R.id.fault_grade)
    AutoCompleteTextView mFaultGradeView;

    @Bind(R.id.fault_description_report) AutoCompleteTextView mFaultDescriptionView;


    @Bind(R.id.fault_type)
    AutoCompleteTextView mFaultTypeView;
    @Bind(R.id.fault_cause)
    AutoCompleteTextView mFaultCauseView;

    @Bind(R.id.operation)
    AutoCompleteTextView mOperationView;
    @Bind(R.id.work_done)
    AutoCompleteTextView mWorkDoneView;
    @Bind(R.id.fault_reason_note)
    EditText mFaultReasonNoteView;
    @Bind(R.id.work_note)
    EditText mWorkNoteView;
    @Bind(R.id.dispose)
    EditText mDisposeView;
    @Bind(R.id.operation_start_time)
    EditText mOperationStartTimeView;
    @Bind(R.id.operation_end_time)
    EditText mOperationEndTimeView;

    @Bind(R.id.to_upload_picture)
    Button toUploadPictureBtn;

    public static Uri pictureUri;
    public static String mCmOrderId;

    FileRecorderUtil recorderUtil = new FileRecorderUtil();

    @Bind(R.id.noScrollgridview)
    GridView noScrollgridview;

    @Bind(R.id.uploaded_count)
    TextView uploadedCountTextView;
    @Bind(R.id.up_pic_layout)
    RelativeLayout upPicLayout;

    private GridAdapter adapter;

    private ArrayList<String> uploadedPicAddrs;

    public static final int MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 10 ;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 20 ;
	
    @Inject
    Authenticator mAuthenticator;

    private ContentObserver mContentObserver;

    public static Fragment newInstance(String cmOrderId) {
        CmReportFragment fragment = new CmReportFragment();
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.callbacks = (Callbacks) getParentFragment();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.callbacks = null;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction("update_ok");
        getContext().getApplicationContext().registerReceiver(receiver, iFilter);

        return inflater.inflate(R.layout.fragment_cm_report, container, false);
    }


    /**
     *
     */
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context,
                              Intent intent) {

            try {
                updateGridview();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        try {
            queryAndBindData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        getContext().getContentResolver().registerContentObserver(CmWorkColumns.CONTENT_URI, true, mContentObserver = new ContentObserver(null) {
            @Override
            public void onChange(boolean selfChange) {
//          android10会卡死，所以这个地方注释掉了
//                try {
//                    queryAndBindData();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        });


        noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String picPath = uploadedPicAddrs.get(position);


                
                String columns[] = new String[] { MediaStore.Images.Media.DATA};

                ContentResolver cr = getContext().getContentResolver();
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, null);


                ArrayList<String> albumPathList = new ArrayList<String>();
                while(cur.moveToNext()){
                    String albumPath = cur.getString(cur.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                    albumPathList.add(albumPath);
                }

                if (!picPath.contains("default_pic")) {
                    if(albumPathList.contains(picPath)){
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.parse("file://" + picPath), "image/*");
                        startActivity(intent);
                    }else{
                        new android.app.AlertDialog.Builder(getActivity())
                                .setTitle("温馨提示")
                                .setMessage("该图片在本地已经删除！")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).setCancelable(false)
                                .show();
                    }
                }

            }
        });

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

            CmMaterialFragment.isMaterialFragmentVisible = false;
            LogUtil.info("CmMaterialFragment_Scan", "isFragmentVisible_Report>> " + CmMaterialFragment.isMaterialFragmentVisible);

            toUploadPictureBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    if(uploadedCount >= 9){
                        Toast.makeText(getActivity(), "上传图片已达最大数！", Toast.LENGTH_SHORT).show();
                    }else{
                        if (Build.VERSION.SDK_INT >= 23){

                            int checkCallPhonePermission = ContextCompat.checkSelfPermission(getActivity(),
                                    Manifest.permission.CAMERA);

                            int checkCallPhonePermission2 = ContextCompat.checkSelfPermission(getActivity(),
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
                            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {


                                new AlertDialog.Builder(getActivity())
                                            .setIcon(R.mipmap.ic_launcher)
                                            .setTitle("温馨提示")
                                            .setMessage("需要使用相机权限，请赋予该权限！")
                                            .setPositiveButton("赋予权限", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                    ActivityCompat.requestPermissions(getActivity(),
                                                            new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                                                }
                                            }).setNegativeButton("取消", null)
                                            .show();

                                return;
                            } else if (checkCallPhonePermission2 != PackageManager.PERMISSION_GRANTED) {
                                    new AlertDialog.Builder(getActivity())
                                            .setIcon(R.mipmap.ic_launcher)
                                            .setTitle("温馨提示")
                                            .setMessage("需要使用存储设备权限，请赋予该权限！")
                                            .setPositiveButton("赋予权限", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                    ActivityCompat.requestPermissions(getActivity(),
                                                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE);
                                                }
                                            }).setNegativeButton("取消", null)
                                            .show();

                                    return;
                            } else {
                                try {
                                    uploadPic();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        }else{
                            try {
                                uploadPic();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }

                }
            });

            try {
                updateGridview();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static int uploadedCount;
    private void updateGridview() throws Exception{
        PictureCursor pictureCursor = new PictureSelection()
                .keyId(mCmOrderId)
                .and()
                .type("C")
                .query(getContext());
        uploadedPicAddrs = new ArrayList<String>();

        uploadedCount = pictureCursor.getCount();

        if(uploadedCount > 0){
            uploadedCountTextView.setText(uploadedCount + "");
        }

        while(pictureCursor.moveToNext()){
            uploadedPicAddrs.add(pictureCursor.getPictureId());
        }

        if(uploadedPicAddrs.size() > 0 && uploadedPicAddrs.size() < 9){
            for(int i = uploadedPicAddrs.size(); i < 9; i++){
                uploadedPicAddrs.add("default_pic");
            }
        }

        if(uploadedPicAddrs != null && uploadedPicAddrs.size() > 0){

            noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
            adapter = new GridAdapter(uploadedPicAddrs, getActivity());
            noScrollgridview.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            upPicLayout.setVisibility(View.VISIBLE);
        }else{
            upPicLayout.setVisibility(View.GONE);
        }
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
    private void bindData(CmWorkCursor c) throws  Exception{
        final String title = getString(R.string.format_title_activity_cm_work_order, c.getOrderId());
        getActivity().setTitle(title);

        if (c.getIntCustomerNo() != null) {
            intCustomerNoView.setTag(c.getIntCustomerNo());
            intCustomerNoView.setText(getIntCustomerValueByNo(c.getIntCustomerNo()));
        }


        if (c.getEquipFault() != null) {
            String cmEquipfaultCode =  c.getEquipFault();
            cmEquipfault.setTag(c.getEquipFault());
            if(cmEquipfaultCode.equals("TRUE")){
                cmEquipfault.setText("设备类故障");
            }else{
                cmEquipfault.setText("非设备类故障");
            }

        }

        if (c.getFaultGradeCode() != null && c.getFaultGradeText() != null) {
            mFaultGradeView.setTag(c.getFaultGradeCode());
            mFaultGradeView.setText(c.getFaultGradeText());
        }

        if (c.getFaultDescriptionCode() != null && c.getFaultDescriptionText() != null){
            mFaultDescriptionView.setTag(c.getFaultDescriptionCode());
            mFaultDescriptionView.setText(c.getFaultDescriptionText());
            setupDropDownFieldView(mFaultTypeView, ParamValue.PARAM_FAULT_TYPE, c.getFaultDescriptionCode(), false);
        }

        if (c.getFaultTypeCode() != null && c.getFaultTypeText() != null){
            mFaultTypeView.setTag(c.getFaultTypeCode());
            mFaultTypeView.setText(c.getFaultTypeText());
            setupDropDownFieldView(mFaultCauseView, ParamValue.PARAM_FAULT_CAUSE, c.getFaultTypeCode(), false);
        }

        if (c.getFaultCauseCode() != null && c.getFaultCauseText() != null) {
            mFaultCauseView.setTag(c.getFaultCauseCode());
            mFaultCauseView.setText(c.getFaultCauseText());
        }

        if (c.getWorkDetailsCode() != null && c.getWorkDetailsText() != null) {
            mOperationView.setTag(c.getWorkDetailsCode());
            mOperationView.setText(c.getWorkDetailsText());
        }

        if (c.getWorkDoneCode() != null && c.getWorkDoneText() != null) {
            mWorkDoneView.setTag(c.getWorkDoneCode());
            mWorkDoneView.setText(c.getWorkDoneText());
        }



        if (c.getFaultCauseNote() != null) {
            mFaultReasonNoteView.setText(c.getFaultCauseNote());
        }

        if (c.getWorkNote() != null) {
            mWorkNoteView.setText(c.getWorkNote());
        }

        if(c.getDispose() != null){

            mDisposeView.setText(getUserName(c.getDispose()));
        }
        long now = System.currentTimeMillis();



        if(c.getOperationStartTime() != null){
            mOperationStartTimeView.setText(DATE_FORMAT.format(c.getOperationStartTime()));
            mOperationStartTimeView.setTag(c.getOperationStartTime());
        }
        else{

            if(c.getArriveTime() != null){
                mOperationStartTimeView.setText(DATE_FORMAT.format(c.getArriveTime()));
                mOperationStartTimeView.setTag(c.getArriveTime());

                new CmWorkContentValues()
                        .putOperationStartTime(Numbers.longValue((Long) mOperationStartTimeView.getTag()))
                        .putLastModified(System.currentTimeMillis())
                        .update(getContext().getContentResolver(), new CmWorkSelection().orderId(mCmOrderId));
            }else{
                mOperationStartTimeView.setTag(now);
                mOperationStartTimeView.setText(DATE_FORMAT.format(now));
            }
        }

        if(c.getOperationEndTime() != null){
            mOperationEndTimeView.setText(DATE_FORMAT.format(c.getOperationEndTime()));
            mOperationEndTimeView.setTag(c.getOperationEndTime());
        } else{

            mOperationEndTimeView.setTag(now);
            mOperationEndTimeView.setText(DATE_FORMAT.format(now));
            new CmWorkContentValues()
                    .putOperationEndTime(Numbers.longValue((Long) mOperationEndTimeView.getTag()))
                    .putLastModified(System.currentTimeMillis())
                    .update(getContext().getContentResolver(), new CmWorkSelection().orderId(mCmOrderId));
        }

        setupDropDownFieldView(intCustomerNoView, ParamValue.PARAM_INTERNALCUSTOMER);
        setupDropDownFieldView(cmEquipfault, ParamValue.PARAM_CM_EQUIPFAULT);
        setupDropDownFieldView(mFaultGradeView, ParamValue.PARAM_FAULT_GRADE, null, false);

        DevicePhysicsCursor devicePhysicsCursor = new DevicePhysicsSelection()
                .codePhysics(c.getDeviceCode()).query(getContext().getContentResolver());
        String typeTemp = "";
        try {
            if (devicePhysicsCursor.moveToNext()) {
                typeTemp = devicePhysicsCursor.getType();
            }
        } finally {
            devicePhysicsCursor.close();
        }

        setupDropDownFieldView(mFaultDescriptionView, ParamValue.PARAM_FAULT_DESCRIPTION, typeTemp, true);

        setupDropDownFieldView(mOperationView, ParamValue.PARAM_OPERATION, null, false);
        setupDropDownFieldView(mWorkDoneView, ParamValue.PARAM_OPERATION_RESULT, null, false);


        setShowDropdownListeners(mFaultDescriptionView);
        setShowDropdownListeners(mFaultTypeView);
        setShowDropdownListeners(mFaultCauseView);


        setupFieldView(mFaultReasonNoteView);
        setupFieldView(mWorkNoteView);

    }

    @OnFocusChange({R.id.operation_start_time, R.id.operation_end_time})
    @OnClick({R.id.input_operation_end_time, R.id.input_operation_start_time, R.id.operation_start_time, R.id.operation_end_time})
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
            }
        }
    }

    String noChildNoteFirstLetter = "";
    Map<String, String> type_causeLetterMap;


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

    Map<String, String> cmEquipfaultMap = null;

    private void setupDropDownFieldView(final AutoCompleteTextView fieldView, String param){
        if(param.equals(ParamValue.PARAM_INTERNALCUSTOMER)){
            final ParamValueSelection sel = new ParamValueSelection().type(param);
            final ParamValueCursor cur = sel.query(getContext().getContentResolver());
            try {
                final List<FieldValueAdapter.FieldValue> values = new ArrayList<>(cur.getCount());
                while (cur.moveToNext()) {
                    final FieldValueAdapter.FieldValue value = new FieldValueAdapter.FieldValue();
                    value.code = cur.getCode();
                    value.value = cur.getValue();
                    values.add(value);
                }
                if(values.size() == 1){
                    intCustomerNoView.setTag(values.get(0).code);
                    intCustomerNoView.setText(values.get(0).value);

                    new CmWorkContentValues()
                            .putIntCustomerNo(values.get(0).code)
                            .update(getContext().getContentResolver(), new CmWorkSelection().orderId(mCmOrderId));

                    intCustomerNoView.setFocusable(false);
                    cur.close();
                    return;
                }
                fieldView.setAdapter(new FieldValueAdapter(getContext(), values));
            } finally {
                cur.close();
            }
        }else if(param.equals(ParamValue.PARAM_CM_EQUIPFAULT)){
            cmEquipfaultMap = new HashMap<String, String>();
            List<FieldValueAdapter.FieldValue> values = new ArrayList<>(2);

            FieldValueAdapter.FieldValue value = new FieldValueAdapter.FieldValue();
            value.code = "TRUE";
            value.value = "设备类故障";
            cmEquipfaultMap.put(value.value, value.code);
            values.add(value);

            value = new FieldValueAdapter.FieldValue();
            value.code = "FALSE";
            value.value = "非设备类故障";
            cmEquipfaultMap.put(value.value, value.code);
            values.add(value);

            fieldView.setAdapter(new FieldValueAdapter(getContext(), values));
        }


        setupFieldView(fieldView);
        setShowDropdownListeners(fieldView);
    }


    private void setupDropDownFieldView(final AutoCompleteTextView fieldView, String param, final String parentCode, boolean isSymptom) {
        setupFieldView(fieldView);
        setShowDropdownListeners(fieldView);
        fieldView.setOnItemClickListener((parent, view, position, id) -> {
            final FieldValueAdapter.FieldValue value = (FieldValueAdapter.FieldValue) parent.getItemAtPosition(position);
            fieldView.setTag(value.code);
            hideSoftKeyboard(fieldView);

            final int viewId = fieldView.getId();
            switch (viewId) {
                case R.id.fault_grade:
                    new CmWorkContentValues()
                            .putFaultGradeCode(mFaultGradeView.getTag().toString())
                            .putFaultGradeText(mFaultGradeView.getText().toString())
                            .putLastModified(System.currentTimeMillis())
                            .update(getContext().getContentResolver(), new CmWorkSelection().orderId(mCmOrderId));
                    break;

                case R.id.fault_description_report:
                    mFaultTypeView.setAdapter(null);
                    mFaultTypeView.setTag(null);
                    mFaultTypeView.setText(null);

                    mFaultCauseView.setAdapter(null);
                    mFaultCauseView.setTag(null);
                    mFaultCauseView.setText(null);

                    setupDropDownFieldView(mFaultTypeView, ParamValue.PARAM_FAULT_TYPE, value.code, false);
                    new CmWorkContentValues()
                            .putFaultDescriptionCode(mFaultDescriptionView.getTag().toString())
                            .putFaultDescriptionText(mFaultDescriptionView.getText().toString())
                            .putLastModified(System.currentTimeMillis())
                            .update(getContext().getContentResolver(), new CmWorkSelection().orderId(mCmOrderId));

                    break;
                case R.id.fault_type:
                    mFaultCauseView.setAdapter(null);
                    mFaultCauseView.setTag(null);
                    mFaultCauseView.setText(null);
                    setupDropDownFieldView(mFaultCauseView, ParamValue.PARAM_FAULT_CAUSE, value.code, false);
                    new CmWorkContentValues()
                            .putFaultTypeCode(mFaultTypeView.getTag().toString())
                            .putFaultTypeText(mFaultTypeView.getText().toString())
                            .putLastModified(System.currentTimeMillis())
                            .update(getContext().getContentResolver(), new CmWorkSelection().orderId(mCmOrderId));
                    break;
                case R.id.fault_cause:
                    new CmWorkContentValues()
                            .putFaultCauseCode(mFaultCauseView.getTag().toString())
                            .putFaultCauseText(mFaultCauseView.getText().toString())
                            .putLastModified(System.currentTimeMillis())
                            .update(getContext().getContentResolver(), new CmWorkSelection().orderId(mCmOrderId));
                    break;
                case R.id.operation:
                    new CmWorkContentValues()
                            .putWorkDetailsCode(mOperationView.getTag().toString())
                            .putWorkDetailsText(mOperationView.getText().toString())
                            .putLastModified(System.currentTimeMillis())
                            .update(getContext().getContentResolver(), new CmWorkSelection().orderId(mCmOrderId));
                    break;

                case R.id.work_done:
                    new CmWorkContentValues()
                            .putWorkDoneCode(mWorkDoneView.getTag().toString())
                            .putWorkDoneText(mWorkDoneView.getText().toString())
                            .putLastModified(System.currentTimeMillis())
                            .update(getContext().getContentResolver(), new CmWorkSelection().orderId(mCmOrderId));
                    break;
            }
        });

        if (param == null)
            return;

        final ParamValueSelection sel = new ParamValueSelection().type(param);
        if(isSymptom){
            sel.and().parentCodeContains(parentCode);
        }
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
                    noChildNoteFirstLetter = typeFirstLetter;
                    isgetFirstLette = true;
                }

            }


            if(parentCode != null) {
                FieldValueAdapter.FieldValue value = new FieldValueAdapter.FieldValue();
                if(parentCode.matches("^[A-Z][0-9]*$")){
                    if(!(parentCode.endsWith("99"))){


                        int firstLetterSize = typeFirstLetter.length();
                        if(firstLetterSize < 1){
                            //case1
                            type_causeLetterMap = new HashMap<String, String>();
                            type_causeLetterMap.put("G", "Z");
                            type_causeLetterMap.put("V", "M");
                            type_causeLetterMap.put("T", "T");
                            type_causeLetterMap.put("S", "P");
                            type_causeLetterMap.put("Q", "C");
                            type_causeLetterMap.put("D", "X");


                            type_causeLetterMap.put("A", "A");
                            type_causeLetterMap.put("B", "B");
                            type_causeLetterMap.put("C", "D");
                            type_causeLetterMap.put("E", "E");
                            type_causeLetterMap.put("H", "H");
                            type_causeLetterMap.put("I", "I");
                            type_causeLetterMap.put("K", "J");
                            type_causeLetterMap.put("L", "K");
                            type_causeLetterMap.put("N", "L");
                            type_causeLetterMap.put("O", "N");
                            type_causeLetterMap.put("P", "G");
                            type_causeLetterMap.put("Y", "Y");

                            try {
                                noChildNoteFirstLetter = parentCode.substring(0, 1);
                                typeFirstLetter = type_causeLetterMap.get(noChildNoteFirstLetter);
                            } catch (Exception e) {
                                e.printStackTrace();
                                typeFirstLetter = "Q";
                            }
                        }

                        value.code = typeFirstLetter +"99";
                        value.value = "其他的";
                        boolean valueIsExist = false;
                        for(FieldValueAdapter.FieldValue valueTemp : values){
                            if(valueTemp.code.equals(value.code)){
                                valueIsExist = true;
                                break;
                            }
                        }

                        if(!valueIsExist){
                            values.add(value);
                        }
                    }
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

                final ViewParent parent = fieldView.getParent();
                if (parent instanceof TextInputLayout) {
                    ((TextInputLayout) parent).setError(null);
                    ((TextInputLayout) parent).setErrorEnabled(false);
                }
                final int viewId = fieldView.getId();
                switch (viewId) {

                    case R.id.fault_reason_note:
                        new CmWorkContentValues()
                                .putFaultCauseNote(mFaultReasonNoteView.getText().toString())
                                .putLastModified(System.currentTimeMillis())
                                .update(getContext().getContentResolver(), new CmWorkSelection().orderId(mCmOrderId));
                        break;
                    case R.id.work_note:
                        new CmWorkContentValues()
                                .putWorkNote(mWorkNoteView.getText().toString())
                                .putLastModified(System.currentTimeMillis())
                                .update(getContext().getContentResolver(), new CmWorkSelection().orderId(mCmOrderId));
                        break;

                    case R.id.intcustomer_no:

                        String intCustomerNo = "";
                        String intCustomerNoStr = fieldView.getText().toString();
                        ParamValueSelection sel = new ParamValueSelection().value(intCustomerNoStr);
                        final ParamValueCursor cur = sel.query(getContext().getContentResolver());
                        try {
                            while (cur.moveToNext()) {
                                intCustomerNo = cur.getCode();
                            }
                        } finally {
                            cur.close();
                        }

                        new CmWorkContentValues()
                                .putIntCustomerNo(intCustomerNo)
                                .update(getContext().getContentResolver(), new CmWorkSelection().orderId(mCmOrderId));

                        break;


                    case R.id.cm_equipfault:
                        String cmEquipfaultCode = "";
                        String cmEquipfaultStr = fieldView.getText().toString();
                        cmEquipfaultCode = cmEquipfaultMap.get(cmEquipfaultStr);
                        new CmWorkContentValues().putEquipFault(cmEquipfaultCode)
                                .update(getContext().getContentResolver(), new CmWorkSelection().orderId(mCmOrderId));
                        break;
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

                    final int viewId = view.getId();
                    switch (viewId) {
                        case R.id.operation_start_time:
                            new CmWorkContentValues()
                                    .putOperationStartTime(Numbers.longValue((Long) mOperationStartTimeView.getTag()))
                                    .putLastModified(System.currentTimeMillis())
                                    .update(getContext().getContentResolver(), new CmWorkSelection().orderId(mCmOrderId));
                            break;
                        case R.id.operation_end_time:
                            new CmWorkContentValues()
                                    .putOperationEndTime(Numbers.longValue((Long) mOperationEndTimeView.getTag()))
                                    .putLastModified(System.currentTimeMillis())
                                    .update(getContext().getContentResolver(), new CmWorkSelection().orderId(mCmOrderId));
                            break;
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }
    public interface Callbacks {

        void onOpenSparePartTab(long cmSparePartId);

        void onOpenMaterialTab();
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


    public class GridAdapter extends BaseAdapter {

        Context context;
        ArrayList<String>uploadedPicAddrs;
        LayoutInflater inflater;

        public GridAdapter(ArrayList<String>uploadedPicAddrs, Context context) {
            this.uploadedPicAddrs = uploadedPicAddrs;
            this.context = context;
            inflater = LayoutInflater.from(context);
        }


        public int getCount() {
            return uploadedPicAddrs.size();
        }

        public Object getItem(int position) {
            return uploadedPicAddrs.get(position);
        }

        public long getItemId(int position) {
            return position;
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.fragment_cm_report_photo_item,
                        parent, false);
                holder = new ViewHolder();
                holder.image = (SimpleDraweeView) convertView
                        .findViewById(R.id.item_grida_image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            String picUri = uploadedPicAddrs.get(position);

            String columns[] = new String[] { MediaStore.Images.Media.DATA};

            ContentResolver cr = context.getContentResolver();
            Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, null);


            ArrayList<String> albumPathList = new ArrayList<String>();
            while(cur.moveToNext()){
                String albumPath = cur.getString(cur.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));

                albumPathList.add(albumPath);
            }

            if(picUri.contains("default_pic")){
                Uri uri = Uri.parse("res://cc.xingyan.android.afc/" + R.drawable.icon_addpic_unfocused);
                holder.image.setImageURI(uri);
            }else {
                if(albumPathList.contains(picUri)){
                    Uri uri = Uri.parse("file://" + picUri);
                    holder.image.setImageURI(uri);
                }else{
                    Uri uri = Uri.parse("res://cc.xingyan.android.afc/" + R.drawable.plugin_camera_no_pictures);
                    holder.image.setImageURI(uri);
                }
            }


            return convertView;
        }
    }

        public class ViewHolder {
            public SimpleDraweeView image;
        }


    private void uploadPic() throws Exception{
        String[] ps = { "拍照上传", "本地照片"};
        new AlertDialog.Builder(getActivity()).setTitle("照片上传")
                .setItems(ps, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                                try {
                                    pictureUri = recorderUtil.cameraTakePic(getActivity(), mCmOrderId, 2);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(getActivity(), "没有SD卡！", Toast.LENGTH_LONG).show();
                            }
                        } else if (which == 1) {


                            Intent intent = new Intent(getActivity(), ImageFileActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("keyID", mCmOrderId);
                            bundle.putString("type", "C");
                            bundle.putString("remark", "no_remark_info_local");
                            intent.putExtra("getKeyID", bundle);
                            startActivity(intent);
                        }
                    }
                }).show();
    }


}
