package cc.xingyan.android.afc;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.user.UserCursor;
import cc.xingyan.android.afc.provider.user.UserSelection;
import cc.xingyan.android.afc.provider.yunshuhead.YunshuHeadContentValues;
import cc.xingyan.android.afc.provider.yunshuhead.YunshuHeadCursor;
import cc.xingyan.android.afc.provider.yunshuhead.YunshuHeadSelection;
import cc.xingyan.android.afc.provider.yunshukuwei.YunshuKuweiCursor;
import cc.xingyan.android.afc.provider.yunshukuwei.YunshuKuweiSelection;
import cc.xingyan.android.afc.provider.yunshuline.YunshuLineCursor;
import cc.xingyan.android.afc.provider.yunshuline.YunshuLineSelection;
import cc.xingyan.android.afc.widget.SimpleTextWatcher;

/**
 * Created by YuJiadeng on 2018/3/17.
 *
 */
public class PartYunShuHeadFragment extends BaseFragment {

    private String transportTaskId;

    private String[] maintenanceTypes;

    @Inject
    Authenticator mAuthenticator;

    @Bind(R.id.transport_task_id) TextView transportTaskIdTeV;
    @Bind(R.id.transport_state) TextView transportStateTeV;
    @Bind(R.id.transport_plan_date) TextView transportPlanDateTeV;
    @Bind(R.id.transport_plan_by) TextView transportPlanByTeV;

    @Bind(R.id.transport_maintenance_type) Spinner transportTypeSP;
    @Bind(R.id.transport_maintenance_type_show) TextView transportTypeShow;

    @Bind(R.id.transport_pack_number) EditText transportPackNumberET;
    @Bind(R.id.transport_pack_number_show) TextView transportPackNumberShow;

    @Bind(R.id.transport_from_location_name) AutoCompleteTextView fromLocationATV;
    @Bind(R.id.transport_from_location_name_show) TextView fromLocationShow;

    @Bind(R.id.transport_to_location_name) AutoCompleteTextView toLocationATV;
    @Bind(R.id.transport_to_location_name_show) TextView toLocationShow;

    public static Fragment newInstance() {
        PartYunShuHeadFragment fragment = new PartYunShuHeadFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.part__yunshu_head, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction("updateYunshuView");
        getActivity().registerReceiver(receiver, iFilter);


        IntentFilter iFilterLineOk = new IntentFilter();
        iFilterLineOk.addAction("line_ok");
        getActivity().registerReceiver(receiverLineOK, iFilterLineOk);


        bindAndShowData();


        transportTypeSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new YunshuHeadContentValues()
                        .putMaintenanceTypeId(position + "")
                        .update(getContext().getContentResolver(), new YunshuHeadSelection().transportTaskId(transportTaskId));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        initWarehouse(fromLocationATV, "0");

        initWarehouse(toLocationATV, "1");

        transportPackNumberET.addTextChangedListener(textWatcher);

    }

    private void initWarehouse(AutoCompleteTextView locationATV, String warehouseType){
        List<String> warehouseNameList = new ArrayList<>();
        YunshuKuweiSelection kuweiToSelection = new YunshuKuweiSelection()
                .transportTaskId(transportTaskId)
                .and()
                .warehouseType(warehouseType);
        YunshuKuweiCursor kuweiCursor = kuweiToSelection.query(getContext().getContentResolver());
        while(kuweiCursor.moveToNext()){
            warehouseNameList.add(kuweiCursor.getWarehouseName());
        }

        ArrayAdapter warehouseAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,warehouseNameList);
        warehouseAdapter.notifyDataSetChanged();
        locationATV.setAdapter(warehouseAdapter);

        setupFieldView(locationATV);
        setShowDropdownListeners(locationATV);
        locationATV.setThreshold(1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);

        getActivity().unregisterReceiver(receiverLineOK);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

            PartYunShuLineFragment.isYunShuLineFragmentVisible = false;
            PartYunShuUploadedLineFragment.isYunShuLineUploadedFragmentVisible = false;

        }
    }



    private void bindAndShowData(){

        String transportState = "";
        String transportPlanDate = "";
        String transportPlanBy = "";
        String transportTypeId = "0";
        String transportPackNumber = "";
        String fromWarehouseNo = "";
        String toWarehouseNo = "";

        YunshuHeadSelection headSelection = new YunshuHeadSelection().transportTaskType("0");
        YunshuHeadCursor headCursor = headSelection.query(getContext().getContentResolver());
        try {
            while(headCursor.moveToNext()){


                if(headCursor.getTransportTaskId() != null){
                    transportTaskId = headCursor.getTransportTaskId();
                }


                if(headCursor.getTransportTaskState() != null){
                    transportState = headCursor.getTransportTaskState();
                }


                if(headCursor.getCreateDate() != null){
                    long createDate = headCursor.getCreateDate();
                    SimpleDateFormat sdfCreateDate = new SimpleDateFormat("yyyy/MM/dd");
                    transportPlanDate = sdfCreateDate.format(createDate);

                }


                if(headCursor.getPlanBy() != null){
                    UserSelection userSelection = new UserSelection().userId(headCursor.getPlanBy());
                    UserCursor userCursor = userSelection.query(getContext().getContentResolver());
                    try {
                        while (userCursor.moveToNext()){
                            transportPlanBy = userCursor.getUserName();
                        }
                    } finally {
                        userCursor.close();
                    }

                }


                if(headCursor.getMaintenanceTypeId() != null){
                    transportTypeId = headCursor.getMaintenanceTypeId();
                }



                if(headCursor.getPackNumber() != null){
                    transportPackNumber = headCursor.getPackNumber();
                }


                if(headCursor.getSendWarehouseNo() != null){
                    fromWarehouseNo = headCursor.getSendWarehouseNo();
                }


                if(headCursor.getReceiveWarehouseNo() != null){
                    toWarehouseNo = headCursor.getReceiveWarehouseNo();
                }

            }
        } finally {
            headCursor.close();
        }
        transportTaskIdTeV.setText(transportTaskId);
        transportStateTeV.setText(transportState);
        transportPlanDateTeV.setText(transportPlanDate);
        transportPlanByTeV.setText(transportPlanBy);



        maintenanceTypes = getResources().getStringArray(R.array.maintenance_type);
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, maintenanceTypes);
        transportTypeSP.setAdapter(adapter);
        transportTypeSP.setSelection(Integer.parseInt(transportTypeId));

        transportPackNumberET.setText(transportPackNumber);


        String fromWarehouseName = "";
        YunshuKuweiCursor kuweiFromCursor = new YunshuKuweiSelection()
                .transportTaskId(transportTaskId)
                .and()
                .warehouseNo(fromWarehouseNo)
                .and()
                .warehouseType("0")
                .query(getContext().getContentResolver());
        try {
            while (kuweiFromCursor.moveToNext()){
                fromWarehouseName = kuweiFromCursor.getWarehouseName();
            }
        } finally {
            kuweiFromCursor.close();
        }

        if(fromWarehouseName.length() > 0){
            fromLocationATV.setText(fromWarehouseName);
        }else{
            initWarehouse(fromLocationATV, "0");
        }



        String toWarehouseName = "";
        YunshuKuweiCursor kuweiToCursor = new YunshuKuweiSelection()
                .transportTaskId(transportTaskId)
                .and()
                .warehouseNo(toWarehouseNo)
                .and()
                .warehouseType("1")
                .query(getContext().getContentResolver());
        try {
            while (kuweiToCursor.moveToNext()){
                toWarehouseName = kuweiToCursor.getWarehouseName();
            }
        } finally {
            kuweiToCursor.close();
        }

        if(toWarehouseName.length() > 0){
            toLocationATV.setText(toWarehouseName);
        }else{
            initWarehouse(toLocationATV, "1");
        }

        lockOrNotHeadContent();

    }



    private BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(android.content.Context context,
                              android.content.Intent intent) {

            try {


                fromLocationATV.setText("");
                toLocationATV.setText("");
                lockOrNotHeadContent();


                bindAndShowData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };



    private BroadcastReceiver receiverLineOK = new BroadcastReceiver() {
        public void onReceive(android.content.Context context,
                              android.content.Intent intent) {

            lockOrNotHeadContent();
        }
    };


    private void lockOrNotHeadContent(){
        try {


            boolean isLineUploadMark = false;

            YunshuLineSelection lineSelection = new YunshuLineSelection().transportTaskId(transportTaskId);
            YunshuLineCursor lineCursor = lineSelection.query(getContext().getContentResolver());
            try {
                while(lineCursor.moveToNext()){
                    if(lineCursor.getLineType().equals("1")){
                        isLineUploadMark = true;
                        break;
                    }
                }
            } finally {
                lineCursor.close();
            }

            if(isLineUploadMark){

                String taskType = "";
                String packNumber = "";
                String fromNo = "";
                String toNo = "";

                YunshuHeadSelection headSelection = new YunshuHeadSelection().transportTaskId(transportTaskId);
                YunshuHeadCursor headCursor = headSelection.query(getContext().getContentResolver());
                try {
                    while (headCursor.moveToNext()){
                        taskType = headCursor.getMaintenanceTypeId();
                        packNumber = headCursor.getPackNumber();
                        fromNo = headCursor.getSendWarehouseNo();
                        toNo = headCursor.getReceiveWarehouseNo();
                    }
                } finally {
                    headCursor.close();
                }


                transportTypeSP.setVisibility(View.GONE);
                transportTypeShow.setVisibility(View.VISIBLE);
                if(taskType.equals("0")){
                    transportTypeShow.setText(maintenanceTypes[0]);
                }else if(taskType.equals("1")){
                    transportTypeShow.setText(maintenanceTypes[1]);
                }else{
                    transportTypeShow.setText(maintenanceTypes[2]);
                }

                transportPackNumberET.setVisibility(View.GONE);
                transportPackNumberShow.setVisibility(View.VISIBLE);
                transportPackNumberShow.setText(packNumber);

                String fromWarehouseName = "";
                YunshuKuweiCursor kuweiFromCursor = new YunshuKuweiSelection()
                        .transportTaskId(transportTaskId)
                        .and()
                        .warehouseNo(fromNo)
                        .and()
                        .warehouseType("0")
                        .query(getContext().getContentResolver());
                try {
                    while (kuweiFromCursor.moveToNext()){
                        fromWarehouseName = kuweiFromCursor.getWarehouseName();
                    }
                } finally {
                    kuweiFromCursor.close();
                }
                fromLocationATV.setVisibility(View.GONE);
                fromLocationShow.setVisibility(View.VISIBLE);
                fromLocationShow.setText(fromWarehouseName);

                String toWarehouseName = "";
                YunshuKuweiCursor kuweiToCursor = new YunshuKuweiSelection()
                        .transportTaskId(transportTaskId)
                        .and()
                        .warehouseNo(toNo)
                        .and()
                        .warehouseType("1")
                        .query(getContext().getContentResolver());
                try {
                    while (kuweiToCursor.moveToNext()){
                        toWarehouseName = kuweiToCursor.getWarehouseName();
                    }
                } finally {
                    kuweiToCursor.close();
                }
                toLocationATV.setVisibility(View.GONE);
                toLocationShow.setVisibility(View.VISIBLE);
                toLocationShow.setText(toWarehouseName);

            }else {

                transportTypeSP.setVisibility(View.VISIBLE);
                transportTypeShow.setVisibility(View.GONE);

                transportPackNumberET.setVisibility(View.VISIBLE);
                transportPackNumberShow.setVisibility(View.GONE);

                fromLocationATV.setVisibility(View.VISIBLE);
                fromLocationShow.setVisibility(View.GONE);

                toLocationATV.setVisibility(View.VISIBLE);
                toLocationShow.setVisibility(View.GONE);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TextWatcher textWatcher = new SimpleTextWatcher() {
        @Override public void afterTextChanged(Editable s) {

            new YunshuHeadContentValues()
                    .putPackNumber(s.toString())
                    .update(getContext().getContentResolver(), new YunshuHeadSelection().transportTaskId(transportTaskId));

        }
    };


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
                    case R.id.transport_from_location_name:
                        String tempFromNameStr = fieldView.getText().toString();

                        List<String> fromWarehouseNoList = new ArrayList<>();
                        List<String> fromWarehouseNameList = new ArrayList<>();

                        YunshuKuweiSelection kuweiFromSelection = new YunshuKuweiSelection()
                                .transportTaskId(transportTaskId)
                                .and()
                                .warehouseType("0")
                                .and()
                                .warehouseNameLike("%" + tempFromNameStr + "%");

                        YunshuKuweiCursor kuweiCursor = kuweiFromSelection.query(getContext().getContentResolver());
                        while(kuweiCursor.moveToNext()){
                            fromWarehouseNoList.add(kuweiCursor.getWarehouseNo());
                            fromWarehouseNameList.add(kuweiCursor.getWarehouseName());
                        }

                        ArrayAdapter fromWarehouseAdapter = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_list_item_1,fromWarehouseNameList);
                        fromLocationATV.setAdapter(fromWarehouseAdapter);

                        for(int i = 0; i < fromWarehouseNameList.size(); i++){
                                if(tempFromNameStr.equals(fromWarehouseNameList.get(i))){
                                    new YunshuHeadContentValues()
                                            .putSendWarehouseNo(fromWarehouseNoList.get(i))
                                            .update(getContext().getContentResolver(), new YunshuHeadSelection().transportTaskId(transportTaskId));
                                }
                        }

                        break;

                    case R.id.transport_to_location_name:
                        String tempToNameStr = fieldView.getText().toString();

                        List<String> toWarehouseNoList = new ArrayList<>();
                        List<String> toWarehouseNameList = new ArrayList<>();

                        YunshuKuweiSelection kuweiSelection = new YunshuKuweiSelection()
                                .transportTaskId(transportTaskId)
                                .and()
                                .warehouseType("1")
                                .and()
                                .warehouseNameLike("%" + tempToNameStr + "%");

                        YunshuKuweiCursor kuweiToCursor = kuweiSelection.query(getContext().getContentResolver());
                        while(kuweiToCursor.moveToNext()){
                            toWarehouseNoList.add(kuweiToCursor.getWarehouseNo());
                            toWarehouseNameList.add(kuweiToCursor.getWarehouseName());
                        }

                        ArrayAdapter toWarehouseAdapter = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_list_item_1,toWarehouseNameList);
                        toLocationATV.setAdapter(toWarehouseAdapter);

                        for(int i = 0; i < toWarehouseNameList.size(); i++){
                            if(tempToNameStr.equals(toWarehouseNameList.get(i))){
                                new YunshuHeadContentValues()
                                        .putReceiveWarehouseNo(toWarehouseNoList.get(i))
                                        .update(getContext().getContentResolver(), new YunshuHeadSelection().transportTaskId(transportTaskId));
                            }
                        }


                        break;
                }
            }
        });
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
        if (fieldView.getAdapter() != null) {
            fieldView.showDropDown();
        }
    }

}
