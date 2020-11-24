package cc.xingyan.android.afc;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

import butterknife.Bind;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.user.UserCursor;
import cc.xingyan.android.afc.provider.user.UserSelection;
import cc.xingyan.android.afc.provider.yunshuhead.YunshuHeadCursor;
import cc.xingyan.android.afc.provider.yunshuhead.YunshuHeadSelection;
import cc.xingyan.android.afc.provider.yunshukuwei.YunshuKuweiCursor;
import cc.xingyan.android.afc.provider.yunshukuwei.YunshuKuweiSelection;

public class PartYunShuUploadedHeadFragment extends BaseFragment {
    private static final String TRANSPORT_TASK_ID_Uploaded = "transport_task_id_uploaded";

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


    public static Fragment newInstance(String tranSportTaskID) {
        PartYunShuUploadedHeadFragment fragment = new PartYunShuUploadedHeadFragment();
        Bundle args = new Bundle();
        args.putString(TRANSPORT_TASK_ID_Uploaded, tranSportTaskID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);

        transportTaskId = getArguments().getString(TRANSPORT_TASK_ID_Uploaded);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.part__yunshu_head, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bindAndShowData();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            PartYunShuUploadedLineFragment.isYunShuLineUploadedFragmentVisible = false;

        }
    }


    private void bindAndShowData(){
        maintenanceTypes = getResources().getStringArray(R.array.maintenance_type);

        String transportState = "";
        String transportPlanDate = "";
        String transportPlanBy = "";
        String taskType = "";
        String packNumber = "";
        String fromNo = "";
        String toNo = "";

        YunshuHeadSelection headSelection = new YunshuHeadSelection().transportTaskId(transportTaskId);
        YunshuHeadCursor headCursor = headSelection.query(getContext().getContentResolver());
        try {
            while (headCursor.moveToNext()){
                transportState = headCursor.getTransportTaskState();

                long createDate = headCursor.getCreateDate();
                SimpleDateFormat sdfCreateDate = new SimpleDateFormat("yyyy/MM/dd");
                transportPlanDate = sdfCreateDate.format(createDate);

                UserSelection userSelection = new UserSelection().userId(headCursor.getPlanBy());
                UserCursor userCursor = userSelection.query(getContext().getContentResolver());
                try {
                    while (userCursor.moveToNext()){
                        transportPlanBy = userCursor.getUserName();
                    }
                } finally {
                    userCursor.close();
                }

                taskType = headCursor.getMaintenanceTypeId();
                packNumber = headCursor.getPackNumber();
                fromNo = headCursor.getSendWarehouseNo();
                toNo = headCursor.getReceiveWarehouseNo();
            }
        } finally {
            headCursor.close();
        }

        transportTaskIdTeV.setText(transportTaskId);
        transportStateTeV.setText(transportState);
        transportPlanDateTeV.setText(transportPlanDate);
        transportPlanByTeV.setText(transportPlanBy);

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


    }

}
