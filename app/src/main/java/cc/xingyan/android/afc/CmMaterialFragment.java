package cc.xingyan.android.afc;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.zzj.afc.ui.wheelview.time.ScreenInfo;
import com.zzj.afc.ui.wheelview.time.WheelMain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.CmService;
import cc.xingyan.android.afc.api.DeviceService;
import cc.xingyan.android.afc.api.model.CmMaterial;
import cc.xingyan.android.afc.api.model.CmMaterialRow;
import cc.xingyan.android.afc.api.model.CmMaterials;
import cc.xingyan.android.afc.api.model.CmMaterialsID;
import cc.xingyan.android.afc.api.model.CmMaterialsInfo;
import cc.xingyan.android.afc.api.model.CmMaterialsInfos;
import cc.xingyan.android.afc.api.model.ParamCmMaterial;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.cmmaterial.CmMaterialColumns;
import cc.xingyan.android.afc.provider.cmmaterial.CmMaterialContentValues;
import cc.xingyan.android.afc.provider.cmmaterial.CmMaterialCursor;
import cc.xingyan.android.afc.provider.cmmaterial.CmMaterialSelection;
import cc.xingyan.android.afc.provider.cmmaterialrow.CmMaterialRowColumns;
import cc.xingyan.android.afc.provider.cmmaterialrow.CmMaterialRowContentValues;
import cc.xingyan.android.afc.provider.cmmaterialrow.CmMaterialRowCursor;
import cc.xingyan.android.afc.provider.cmmaterialrow.CmMaterialRowSelection;
import cc.xingyan.android.afc.provider.cmparammaterials.CmParamMaterialsCursor;
import cc.xingyan.android.afc.provider.cmparammaterials.CmParamMaterialsSelection;
import cc.xingyan.android.afc.provider.cmwork.CmWorkContentValues;
import cc.xingyan.android.afc.provider.cmwork.CmWorkCursor;
import cc.xingyan.android.afc.provider.cmwork.CmWorkSelection;
import cc.xingyan.android.afc.util.LogUtil;
import cc.xingyan.android.afc.util.NetUtil;
import cc.xingyan.android.afc.util.Numbers;
import cc.xingyan.android.afc.util.SyncHelper;
import cc.xingyan.android.afc.util.TService;
import cc.xingyan.android.afc.widget.AppView;
import cc.xingyan.android.afc.widget.FieldValueAdapter;
import cc.xingyan.android.afc.widget.SimpleTextWatcher;

public class CmMaterialFragment extends BaseFragment {
    private static final String ARG_CM_ORDER_ID = "cm_order_id";
    private static final String ARG_URI = "uri";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private ContentObserver mContentObserver;
    private String mCmOrderId;
    private String mDeviceId;
    private String mMaterialOrderId;

    @Inject
    DeviceService deviceService;

    @Inject
    Authenticator mAuthenticator;

    @Bind(R.id.material_container)
    LinearLayout materialContainer;
    @Bind(R.id.add_material)
    AppView addMaterial;
    @Bind(R.id.apply_material_order)
    AppView applyMaterialOrder;
    @Bind(R.id.material_id)
    EditText mMaterialIdView;
    @Bind(R.id.start_time)
    EditText mStartTimeView;
    @Bind(R.id.due_time)
    EditText mDueTimeView;
    @Bind(R.id.material_user)
    TextView mMaterialUserView;

    @Inject
    CmService cmService;

    public static Fragment newInstance(String cmOrderId) {
        CmMaterialFragment fragment = new CmMaterialFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CM_ORDER_ID, cmOrderId);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCmOrderId = getArguments().getString(ARG_CM_ORDER_ID);
        Dagger.inject(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cm_materials, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        Intent intent = new Intent("com.android.scanservice.output.foreground");
        intent.putExtra("Scan_output_foreground", false);
        getActivity().sendBroadcast(intent);


        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(NetUtil.RECE_DATA_ACTION);
        getActivity().registerReceiver(receiver, iFilter);

        boolean found = false;
        final CmMaterialCursor cmMaterialCursor = new CmMaterialSelection().cmOrderId(mCmOrderId).query(getContext());
        if (cmMaterialCursor.moveToFirst()) {
            found = true;
        }
        cmMaterialCursor.close();
        if (!found) {
            final CmWorkCursor c = new CmWorkSelection().orderId(mCmOrderId).query(getContext());
            if (c.moveToNext()) {
                mDeviceId = c.getDeviceCode();
                new CmMaterialContentValues()
                        .putCmOrderId(mCmOrderId)
                        .putEnterDate(System.currentTimeMillis())
                        .putDueDate(System.currentTimeMillis())
                        .putDepartment(mAuthenticator.getAuthenticatedUserWorkArea())
                        .putUser(c.getDispose())
                        .putGuid(UUID.randomUUID().toString())
                        .insert(getContext().getContentResolver());
            }
            c.close();
        }

        queryAndBindData();

        getContext().getContentResolver().registerContentObserver(CmMaterialColumns.CONTENT_URI, true, mContentObserver = new ContentObserver(null) {
            @Override
            public void onChange(boolean selfChange) {
                queryAndBindData();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }

    private void queryAndBindData() {
        final CmMaterialCursor cmMaterialCursor = new CmMaterialSelection().cmOrderId(mCmOrderId).query(getContext());
        if (cmMaterialCursor.moveToFirst()) {
            mMaterialOrderId = cmMaterialCursor.getMaterialOrderId();
            bindData(cmMaterialCursor);
        }
        cmMaterialCursor.close();
    }

    private void bindData(CmMaterialCursor c) {
        if (c.getMaterialOrderId() != null) {
            mMaterialIdView.setText(c.getMaterialOrderId());
            mStartTimeView.setFocusable(false);
            mDueTimeView.setFocusable(false);
        }
        if (c.getUser() != null) {
            mMaterialUserView.setText(c.getUser());
        }
        if (c.getEnterDate() != null) {
            mStartTimeView.setText(DATE_FORMAT.format(c.getEnterDate()));
        }
        if (c.getDueDate() != null) {
            mDueTimeView.setText(DATE_FORMAT.format(c.getDueDate()));
        }

        final CmMaterialRowCursor rows = new CmMaterialRowSelection().materialOrderId(mMaterialOrderId).orderBy(CmMaterialRowColumns._ID).query(getContext());
        try {
            while (rows.moveToNext()) {
                View v = showMateriaRow(rows.getId());
                AutoCompleteTextView materialNameView = (AutoCompleteTextView) v.findViewById(R.id.material_name);
                EditText materialCodeView = (EditText) v.findViewById(R.id.material_code);
                EditText materialNumView = (EditText) v.findViewById(R.id.material_num);
                EditText materialTimeView = (EditText) v.findViewById(R.id.material_apply_time);

                final Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
                final CmMaterialRowCursor cur = new CmMaterialRowSelection().id(rows.getId()).query(getContext());
                try {
                    if (cur.moveToNext()) {
                        toolbar.setTitle("[物料行号] #" + cur.getMaterialRow());
                        if (cur.getMaterialRow() != null) {
                            toolbar.setBackgroundColor(getContext().getResources().getColor(R.color.green));
                            materialCodeView.setFocusable(false);
                            materialNameView.setFocusable(false);
                            materialNumView.setFocusable(false);
                            materialTimeView.setFocusable(false);
                        }
                    }
                } finally {
                    cur.close();
                }

                if (rows.getMaterialDescription() != null) {
                    materialNameView.setText(rows.getMaterialDescription());
                }

                if (rows.getMaterialId() != null) {
                    materialCodeView.setText(rows.getMaterialId());
                }
                if (rows.getMaterialCount() != null) {
                    materialNumView.setText(String.valueOf(rows.getMaterialCount()));
                }
                if (rows.getDueDate() != null) {
                    materialTimeView.setText(DATE_FORMAT.format(rows.getDueDate()));
                }
            }
        } finally {
            rows.close();
        }
    }

    AutoCompleteTextView materialNameView;
    EditText materialCodeView;


    void initMaterialRow(View v, boolean isNewMaterialLine, String partNo, String partName, boolean isFromScan) {
        final CmWorkCursor c = new CmWorkSelection().orderId(mCmOrderId).query(getContext());
        if (c.moveToNext()) {
            mDeviceId = c.getDeviceCode();
        }
        c.close();
        materialNameView = (AutoCompleteTextView) v.findViewById(R.id.material_name);
        materialCodeView = (EditText) v.findViewById(R.id.material_code);
        EditText materialNumView = (EditText) v.findViewById(R.id.material_num);
        EditText materialTimeView = (EditText) v.findViewById(R.id.material_apply_time);

        materialNumView.setText("1");
        updateMaterialCount(materialNumView, true);


        if(isFromScan){
            materialNameView.setText(partName);
            materialNameView.setFocusable(false);

            materialCodeView.setText(partNo);
            materialCodeView.setFocusable(false);

            materialCodeView.setTag(partNo);

            CmMaterialRowContentValues row = new CmMaterialRowContentValues();
            row.putMaterialId(partNo);
            row.putMaterialDescription(partName);

            View card = materialNameView;
            while (!(card instanceof CardView)) {
                card = (View) card.getParent();
            }
            final long rowId = (Long) card.getTag();
            row.update(getContext().getContentResolver(), new CmMaterialRowSelection().id(rowId));

        }else {
            materialNameView.setThreshold(1);
            setupDropDownFieldView(materialNameView, isNewMaterialLine);
            setupFieldView(materialCodeView);
        }

        setupFieldView(materialNumView);




        final CmMaterialCursor cmMaterialCursor = new CmMaterialSelection()
                .materialOrderId(mMaterialOrderId)
                .query(getContext());

        try {
            if (cmMaterialCursor.moveToNext()) {
                materialTimeView.setText(DATE_FORMAT.format(cmMaterialCursor.getDueDate()));
                materialTimeView.setTag(cmMaterialCursor.getDueDate());
                CmMaterialRowContentValues row = new CmMaterialRowContentValues();
                row.putDueDate((long) materialTimeView.getTag());
                row.update(getContext().getContentResolver(), new CmMaterialRowSelection().materialOrderId(mMaterialOrderId));
            }
        } finally {
            cmMaterialCursor.close();
        }

        materialNameView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FieldValueAdapter.FieldValue value = (FieldValueAdapter.FieldValue) parent.getItemAtPosition(0);
                materialCodeView.setTag(value.code);
                materialCodeView.setText(value.code);
                CmMaterialRowContentValues row = new CmMaterialRowContentValues();
                row.putMaterialId(value.code);
                row.putMaterialDescription(value.value);

                View card = materialNameView;
                while (!(card instanceof CardView)) {
                    card = (View) card.getParent();
                }
                final long rowId = (Long) card.getTag();
                row.update(getContext().getContentResolver(), new CmMaterialRowSelection().id(rowId));
            }

        });

    }
    private boolean isAllCmMaterialRowUp(){
        int count = new CmMaterialRowSelection().cmOrderId(mCmOrderId).query(getContext()).getCount();
        if(count == 0){
            return true;
        }

        CmMaterialRowCursor cmMaterialLineItemCursor =
                new CmMaterialRowSelection().cmOrderId(mCmOrderId).query(getContext());

        try {
            while (cmMaterialLineItemCursor.moveToNext()){
                boolean isMaterialRowOk = cmMaterialLineItemCursor.getMaterialRow() != null ? true : false;
                if(isMaterialRowOk == false){
                    return false;
                }
            }
        } finally {
            cmMaterialLineItemCursor.close();
        }

        return true;
    }

    private void setMenu(View v, Long id) {
        final Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);

        toolbar.inflateMenu(R.menu.card_material_row);

        final CmMaterialRowCursor cur = new CmMaterialRowSelection().id(id).query(getContext());
        try {
            if (cur.moveToNext()) {
                toolbar.setTitle("[物料行号] #" + cur.getMaterialRow());
                if (cur.getMaterialRow() != null) {
                    toolbar.setBackgroundColor(getContext().getResources().getColor(R.color.green));
                    toolbar.getMenu().clear();
                }
            }
        } finally {
            cur.close();
        }

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_sync:
                        if (checkItem(v, id)) {
                            onUpdateMaterialRow(v, id);
                        } else {

                        }
                        break;
                    case R.id.action_delete:
                        deleteMaterialRow(v, id);
                        break;
                }
                return true;
            }
        });
    }

    private boolean checkItem(View v, long id) {
        boolean ok = true;
        AutoCompleteTextView materialNameView = (AutoCompleteTextView) v.findViewById(R.id.material_name);
        EditText materialCodeView = (EditText) v.findViewById(R.id.material_code);
        EditText materialNumView = (EditText) v.findViewById(R.id.material_num);
        EditText materialTimeView = (EditText) v.findViewById(R.id.material_apply_time);

        for (TextView fieldView : new TextView[]{
                materialCodeView,
                materialNameView,
                materialNumView,
                materialTimeView
        }) {
            if (!checkRequiredFieldView(fieldView)) {
                ok = false;
            }
        }
        return ok;
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

    void deleteMaterialRow(View fieldView, Long id) {

        new CmParamMaterialsSelection().delete(getContext().getContentResolver());

        new CmMaterialRowSelection().id(id).delete(getContext());
        materialContainer.removeView(fieldView);
        LogUtil.debug(TAG, "new cm materials row is ok");
        Toast.makeText(getContext(), "删除物料行", Toast.LENGTH_SHORT).show();
    }


    void onUpdateMaterialRow(View fieldView, Long id) {
        final Toolbar toolbar = (Toolbar) fieldView.findViewById(R.id.toolbar);
        CmMaterialsInfo c = new CmMaterialsInfo();
        List<CmMaterialsInfo> list = Arrays.asList(c);
        CmMaterialsInfos list1 = new CmMaterialsInfos();
        final CmMaterialRowCursor cur = new CmMaterialRowSelection().id(id).query(getContext());
        try {
            if (cur.moveToNext()) {
                final CmMaterialCursor cmMaterialCursor = new CmMaterialSelection().materialOrderId(mMaterialOrderId).query(getContext());
                try {
                    if (cmMaterialCursor.moveToNext()) {
                        c.setCMOrderId(cmMaterialCursor.getCmOrderId());
                    }
                } finally {
                    cmMaterialCursor.close();
                }
                c.setOrderNumber(cur.getMaterialOrderId());
                c.setPartNo(cur.getMaterialId());
                c.setQuantityRequired(cur.getMaterialCount().toString());
                c.setDateRequired(new Date(cur.getDueDate()));
                c.setOrderLineNo("");
                c.setPartDescription("");
                c.setGuid(cur.getGuid());
            }
        } finally {
            cur.close();
        }
        list1.setCmMaterialsInfos(list);
        subscribe(cmService.postMaterials(list1), resp -> {
            final List<ContentValues> workValues = new ArrayList<>(resp.size());
            if (resp.size() > 0) {
                CmMaterialRow m = resp.get(0);
                if (m.getRow() != null && m.getRow().matches("^[0-9]*$")) {
                    new CmMaterialRowContentValues()
                            .putMaterialRow(m.getRow())
                            .update(getContext().getContentResolver(), new CmMaterialRowSelection().id(id));
                    toolbar.setTitle("[物料行号] #" + m.getRow());
                    toolbar.getMenu().clear();
                    toolbar.setBackgroundColor(getContext().getResources().getColor(R.color.green));
                    AutoCompleteTextView materialNameView = (AutoCompleteTextView) fieldView.findViewById(R.id.material_name);
                    EditText materialCodeView = (EditText) fieldView.findViewById(R.id.material_code);
                    EditText materialNumView = (EditText) fieldView.findViewById(R.id.material_num);
                    EditText materialTimeView = (EditText) fieldView.findViewById(R.id.material_apply_time);
                    materialCodeView.setFocusable(false);
                    materialNameView.setFocusable(false);
                    materialNumView.setFocusable(false);
                    materialTimeView.setFocusable(false);

                    new CmParamMaterialsSelection().delete(getContext().getContentResolver());

                    LogUtil.debug(TAG, "new cm materials row is ok");
                } else if (m.getRow() != null && !m.getRow().matches("^[0-9]*$")) {
                    LogUtil.debug(TAG, "new cm materials row  is failed");
                    LogUtil.debug(TAG, m.getRow());
                    Toast.makeText(getContext(), "新建cm物料行失败", Toast.LENGTH_SHORT).show();
                }
            } else {
                LogUtil.debug(TAG, "new cm materials row  is failed  resp.size() <= 0");
                Toast.makeText(getContext(), "新建cm物料行失败", Toast.LENGTH_SHORT).show();
            }
        }, e -> {
            LogUtil.debug(TAG, "new cm materials row is failed connect");
            Toast.makeText(getContext(), "新建cm物料行失败", Toast.LENGTH_SHORT).show();
        });
    }

    View addMaterialRow() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.materials_view, materialContainer, false);
        materialContainer.addView(v);
        final long id = newMaterialRow();
        v.setTag(id);

        setMenu(v, id);
        return v;
    }

    View showMateriaRow(long id) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.materials_view, materialContainer, false);
        materialContainer.addView(v);
        v.setTag(id);

        setMenu(v, id);

        return v;
    }

    void onAddMaterial(String partNo, String partName, boolean isFromScan){
        initMaterialRow(addMaterialRow(), true, partNo, partName,  isFromScan);
    }


    @OnClick({R.id.add_material})
    void onAddMaterial() {

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

        final CmMaterialCursor work = new CmMaterialSelection().cmOrderId(mCmOrderId).query(getContext());
        try {
            if (work.moveToNext()) {
                if (work.getMaterialOrderId() != null)
                {
                    if(isAllCmMaterialRowUp() == false){
                        Snackbar.make(getView(), "有未上传的物料行！", Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    initMaterialRow(addMaterialRow(), true, "", "", false);
                } else {
                    Toast.makeText(getContext(), "无领料单号不能新建行", Toast.LENGTH_SHORT).show();
                }
            }
        } finally {
            work.close();
        }
    }

    private long newMaterialRow() {
        final Uri uri = new CmMaterialRowContentValues()
                .putCmOrderId(mCmOrderId)
                .putMaterialOrderId(mMaterialOrderId)
                .putGuid(UUID.randomUUID().toString())
                .insert(getContext());
        return Long.parseLong(uri.getLastPathSegment());
    }


    @OnClick({R.id.apply_material_order})
    void onApplyMaterialOrder() {

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

        String userView = mMaterialUserView.getText().toString();
        String mStartTimeStr = mStartTimeView.getText().toString();
        String mDueTimeStr = mDueTimeView.getText().toString();

        long mStartTime = 0;
        long mDueTime = 0;
        final CmMaterialCursor work = new CmMaterialSelection().cmOrderId(mCmOrderId).query(getContext());
        try {
            if (work.moveToNext()) {
                mStartTime = work.getEnterDate();
                mDueTime = work.getDueDate();
            }
        } finally {
            work.close();
        }

        if (userView == null || userView.length() < 1) {
            Toast.makeText(getActivity(), "领用人信息不完整！", Toast.LENGTH_SHORT).show();
            return;
        }else if (mStartTimeStr == null || mStartTimeStr.length() < 1) {
            Toast.makeText(getActivity(), "输入日期信息不完整！", Toast.LENGTH_SHORT).show();
            return;
        } else if (mDueTimeStr == null || mDueTimeStr.length() < 1) {
            Toast.makeText(getActivity(), "要求日期信息不完整！", Toast.LENGTH_SHORT).show();
            return;
        } else if(mStartTime > mDueTime){
            new AlertDialog.Builder(getActivity()).setTitle("温馨提示").setMessage("到期日时间要大于输入的时间！")
                    .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {

                        }
                    }).setCancelable(false)
                    .show();
            return;
        }else{
            newMaterial();
        }


    }


    public boolean newMaterial() {
        CmMaterial c = new CmMaterial();
        List<CmMaterial> list = Arrays.asList(c);
        CmMaterials list1 = new CmMaterials();
        String MaterialOrderId = "";
        final CmMaterialCursor work = new CmMaterialSelection().cmOrderId(mCmOrderId).query(getContext());
        try {
            if (work.moveToNext()) {
                MaterialOrderId = work.getMaterialOrderId();
                c.setCMOrderId(work.getCmOrderId());

                c.setOrderNumber("no_need_for_request");

                c.setuserid(work.getUser());
                c.setEnterDate(new Date(work.getEnterDate()));
                c.setDepartment(work.getDepartment());
                c.setDueDate(new Date(work.getDueDate()));
                c.setGuid(work.getGuid());
            }
        } finally {
            work.close();
        }

        if (MaterialOrderId != null) {
            Pattern p = Pattern.compile("[0-9]*");
            Matcher m = p.matcher(MaterialOrderId);
            if (m.matches()) {
                Toast.makeText(getContext(), "物料领料单已存在，不用申请", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        list1.setCmMaterials(list);
        subscribe(cmService.listMaterials(list1), resp -> {
            final List<ContentValues> workValues = new ArrayList<>(resp.size());
            if (resp.size() > 0) {
                CmMaterialsID m = resp.get(0);
                mMaterialOrderId = m.getOrderNumber();

                if (mMaterialOrderId != null && mMaterialOrderId.matches("^[0-9]*$")) {
                    new CmMaterialContentValues()
                            .putMaterialOrderId(mMaterialOrderId)
                            .update(getContext().getContentResolver(), new CmMaterialSelection().cmOrderId(m.getCmOrderId()));

                    mMaterialIdView.setText(mMaterialOrderId);
                    mStartTimeView.setFocusable(false);
                    mDueTimeView.setFocusable(false);
                    LogUtil.debug(TAG, "new cm materials is ok");
                } else {
                    LogUtil.debug(TAG, "new cm materials is failed");
                    Toast.makeText(getContext(), "新建cm物料订单失败", Toast.LENGTH_SHORT).show();
                }
            } else {
                LogUtil.debug(TAG, "new cm materials is failed");
                Toast.makeText(getContext(), "新建cm物料订单失败", Toast.LENGTH_SHORT).show();
            }
        }, e -> {
            LogUtil.debug(TAG, "new cm materials is failed");
            Toast.makeText(getContext(), "新建cm物料订单失败", Toast.LENGTH_SHORT).show();
        });

        return true;
    }

    @OnFocusChange({R.id.start_time, R.id.due_time})
    @OnClick({R.id.input_start_time, R.id.input_due_time, R.id.start_time, R.id.due_time})
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
                case R.id.start_time:
                    openDateTimePickerUpon((TextView) view, getString(R.string.hint_start_time));
                    break;
                case R.id.due_time:
                    openDateTimePickerUpon((TextView) view, getString(R.string.hint_due_time));
                    break;
            }
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
                        case R.id.start_time:
                            long startTime = Numbers.longValue((Long) mStartTimeView.getTag());
                            new CmWorkContentValues()
                                    .putOperationStartTime(startTime)
                                    .putLastModified(System.currentTimeMillis())
                                    .update(getContext().getContentResolver(), new CmWorkSelection().orderId(mCmOrderId));


                            new CmMaterialContentValues()
                                    .putEnterDate(startTime)
                                    .update(getContext().getContentResolver(), new CmMaterialSelection().cmOrderId(mCmOrderId));

                            break;
                        case R.id.due_time:
                            long dueTime = Numbers.longValue((Long) mDueTimeView.getTag());
                            new CmWorkContentValues()
                                    .putOperationEndTime(dueTime)
                                    .putLastModified(System.currentTimeMillis())
                                    .update(getContext().getContentResolver(), new CmWorkSelection().orderId(mCmOrderId));

                            new CmMaterialContentValues()
                                    .putDueDate(dueTime)
                                    .update(getContext().getContentResolver(), new CmMaterialSelection().cmOrderId(mCmOrderId));

                            break;
                        case R.id.material_apply_time:
                            final ViewParent parent = view.getParent();
                            if (parent instanceof TextInputLayout) {
                                ((TextInputLayout) parent).setError(null);
                                ((TextInputLayout) parent).setErrorEnabled(false);
                            }

                            EditText materialTimeView = (EditText) view.findViewById(R.id.material_apply_time);
                            CmMaterialRowContentValues row = new CmMaterialRowContentValues();
                            row.putDueDate(Numbers.longValue((long) materialTimeView.getTag()));
                            View card = view;
                            while (!(card instanceof CardView)) {
                                card = (View) card.getParent();
                            }
                            final long rowId = (Long) card.getTag();
                            row.update(getContext().getContentResolver(), new CmMaterialRowSelection().id(rowId));
                            break;
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    };


    private ProgressDialog progress;
    private void setupDropDownFieldView(AutoCompleteTextView fieldView, boolean isNewMaterialLine){
        setupFieldView(fieldView);
        setShowDropdownListeners(fieldView);

        if(isNewMaterialLine){
            progress = new ProgressDialog(getContext());
            progress.setCancelable(false);
            progress.setCanceledOnTouchOutside(false);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIcon(R.drawable.ic_settings_white_24dp);
            progress.setTitle("获取物料");
            progress.setMessage("正在下载...");
            progress.setIndeterminate(false);
            progress.setButton(DialogInterface.BUTTON_POSITIVE, "取消",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            progress.dismiss();
                        }

                    });
            progress.show();

            final String user = mAuthenticator.getAuthenticatedUserId();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    deviceService.listMaterials(user).subscribe(resp -> {

                        if(resp != null && resp.size() > 0){
                            SyncHelper.saveMaterials(getContext().getContentResolver(), resp);
                            progress.dismiss();
                        }else if(resp != null && resp.size() == 0){
                            progress.dismiss();
                            Toast.makeText(getActivity(), "没有物料信息！", Toast.LENGTH_SHORT).show();
                        }else if(resp == null){
                            progress.dismiss();
                            Toast.makeText(getActivity(), "没有物料信息！", Toast.LENGTH_SHORT).show();
                        }

                        values = new ArrayList<>();
                        final CmParamMaterialsCursor cur = new CmParamMaterialsSelection().query(getContext().getContentResolver());
                        try {
                            while (cur.moveToNext()) {
                                final FieldValueAdapter.FieldValue value = new FieldValueAdapter.FieldValue();
                                value.code = cur.getCode();
                                value.value = cur.getName();
                                values.add(value);
                            }
                            Message msg = new Message();
                            msg.what = 0x001;
                            myHandler.sendMessage(msg);
                        } finally {
                            cur.close();
                        }
                    }, e -> {
                        progress.dismiss();
                        LogUtil.info("GetMaterialsOnline", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Fail!" + e);

                    });
                }
            }).start();
        }

    }

    List<FieldValueAdapter.FieldValue> values;

    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0x001){
                materialNameView.setAdapter(new FieldValueAdapter(getContext(), values));
            }else if(msg.what == 0x002){
                Bundle bundle = msg.getData();
                String partNo = bundle.getString("partNo");
                String partName = bundle.getString("partName");

                onAddMaterial(partNo, partName, true);
            }else if(msg.what == 0x003){
                Toast.makeText(getActivity(), "该物资不允许出库！", Toast.LENGTH_SHORT).show();
            }else if(msg.what == 0x004){
                Toast.makeText(getActivity(), "该物资不允许出库, 请确认！！", Toast.LENGTH_SHORT).show();
            }

        }
    };


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
        if (fieldView.getAdapter() != null) {
            fieldView.showDropDown();
        }
    }


    private void updateMaterialCount(TextView fieldView, boolean isDefault){
        CmMaterialRowContentValues row = new CmMaterialRowContentValues();
        String materialCountStr = "";
        if(isDefault){
            materialCountStr = "1";
        }else {
            materialCountStr = fieldView.getText().toString();
        }
        int materialCount = materialCountStr.length() > 0 ? Integer.parseInt(materialCountStr) : 0;
        row.putMaterialCount(materialCount);
        View card = fieldView;
        while (!(card instanceof CardView)) {
            card = (View) card.getParent();
        }
        row.update(getContext().getContentResolver(), new CmMaterialRowSelection().id((Long) card.getTag()));
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
                    case R.id.material_num:
                        updateMaterialCount(fieldView, false);
                        break;
                    case R.id.material_name:
                        String materialNameStr = fieldView.getText().toString();
                        if(materialNameStr != null && materialNameStr.length() > 0){
                            values = new ArrayList<>();
                            final CmParamMaterialsCursor cur = new CmParamMaterialsSelection()
                                    .nameLike("%" + materialNameStr + "%").query(getContext().getContentResolver());
                            try {
                                while (cur.moveToNext()) {
                                    final FieldValueAdapter.FieldValue value = new FieldValueAdapter.FieldValue();
                                    value.code = cur.getCode();
                                    value.value = cur.getName();
                                    values.add(value);
                                }
                            } finally {
                                cur.close();
                            }
                        }else if(materialNameStr != null && materialNameStr.length() == 0){
                            values = new ArrayList<>();
                            final CmParamMaterialsCursor cur = new CmParamMaterialsSelection().query(getContext().getContentResolver());
                            try {
                                while (cur.moveToNext()) {
                                    final FieldValueAdapter.FieldValue value = new FieldValueAdapter.FieldValue();
                                    value.code = cur.getCode();
                                    value.value = cur.getName();
                                    values.add(value);
                                }
                            } finally {
                                cur.close();
                            }
                        }
                        if(values.size() > 0){
                            materialNameView.setAdapter(new FieldValueAdapter(getContext(), values));
                        }else{
                        }
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

    void onMaterialNameChanged(View fieldView, String materialCode) {
        EditText materialCodeView = (EditText) fieldView.findViewById(R.id.material_code);
        materialCodeView.setText(materialCode);
    }

    private String SCAN_BACK_KEY = "Scan_context";
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(NetUtil.RECE_DATA_ACTION)) {
                String barcode = "";
                barcode = intent.getStringExtra(SCAN_BACK_KEY);
                LogUtil.info("CmMaterialFragment_Scan", "isFragmentVisible_OUT" + barcode);


                if(isMaterialFragmentVisible){

                    final CmMaterialCursor work = new CmMaterialSelection().cmOrderId(mCmOrderId).query(getContext());
                    try {
                        if (work.moveToNext()) {
                            if (work.getMaterialOrderId() != null)
                            {
                                if(isAllCmMaterialRowUp() == false){
                                    Snackbar.make(getView(), "有未上传的物料行！", Snackbar.LENGTH_SHORT).show();
                                    return;
                                }

                            } else {
                                Toast.makeText(getContext(), "无领料单号不能新建行", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    } finally {
                        work.close();
                    }

                    LogUtil.info("CmMaterialFragment_Scan", "isFragmentVisible_TRUE" + barcode);

                    if(barcode.contains("#")){
                        String[] strsInfo = barcode.split("#");
                        if(strsInfo[1].equals("0")){
                            String partNo = strsInfo[2].trim();
                            getMaterialByScan(partNo);
                        }else  if(strsInfo[1].equals("1")){
                            Toast.makeText(getActivity(), "出库—这是物资大码，请扫物资小码！", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        if(barcode.contains(":")){
                            Toast.makeText(getActivity(), "出库—这是设备码，请扫物资小码！", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "出库—不合法的编码！", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        }
    };


    public static boolean isMaterialFragmentVisible = false;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isMaterialFragmentVisible = true;
            CmFragment.isCMFragmentVisible = false;
            LogUtil.info("CmMaterialFragment_Scan", "isFragmentVisible_Material>> " + isMaterialFragmentVisible);
        }
    }


    private void getMaterialByScan(String materialId){

        String imei = TService.imei;
        String lat = TService.lat;
        String lon = TService.lon;

        progress = new ProgressDialog(getContext());
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIcon(R.drawable.ic_settings_white_24dp);
        progress.setTitle("获取物料");
        progress.setMessage("正在下载...");
        progress.setIndeterminate(false);
        progress.setButton(DialogInterface.BUTTON_POSITIVE, "取消",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        progress.dismiss();
                    }

                });
        progress.show();

        final String user = mAuthenticator.getAuthenticatedUserId();
        new Thread(new Runnable() {
            @Override
            public void run() {
                deviceService.listMaterialsByScan(materialId, user, NetUtil.getString(imei), NetUtil.getString(lat), NetUtil.getString(lon)).subscribe(resp -> {

                    if(resp != null && resp.size() > 0){

                        ParamCmMaterial paramCmMaterial = resp.get(0);
                        String partNo = paramCmMaterial.getCode();
                        String partName = paramCmMaterial.getName();

                        Bundle bundle = new Bundle();
                        bundle.putString("partNo", partNo);
                        bundle.putString("partName", partName);

                        Message msg = new Message();
                        msg.setData(bundle);
                        msg.what = 0x002;
                        myHandler.sendMessage(msg);

                        progress.dismiss();
                    }else if(resp != null && resp.size() == 0){
                        Message msg = new Message();
                        msg.what = 0x003;
                        myHandler.sendMessage(msg);
                        progress.dismiss();
                    }else if(resp == null){
                        Message msg = new Message();
                        msg.what = 0x004;
                        myHandler.sendMessage(msg);
                        progress.dismiss();
                    }

                }, e -> {
                    progress.dismiss();
                    LogUtil.info("GetMaterialsByScan", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Fail!" + e);

                });
            }
        }).start();
    }

}
