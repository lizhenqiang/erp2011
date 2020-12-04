package cc.xingyan.android.afc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.zzj.afc.ui.wheelview.time.ScreenInfo;
import com.zzj.afc.ui.wheelview.time.WheelMain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.Bind;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.picture.PictureCursor;
import cc.xingyan.android.afc.provider.picture.PictureSelection;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkContentValues;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkCursor;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkSelection;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkStatus;
import cc.xingyan.android.afc.provider.pmifsworkitem.PmifsWorkItemContentValues;
import cc.xingyan.android.afc.provider.pmifsworkitem.PmifsWorkItemCursor;
import cc.xingyan.android.afc.provider.pmifsworkitem.PmifsWorkItemSelection;
import cc.xingyan.android.afc.util.Numbers;

/**
 * Created by YuJiadeng on 2016/11/17.
 *
 */
public class PmWorkEditorContentFragment extends BaseFragment {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final String ARG_PM_ORDER_ID = "pm_order_id";
    private static final String ARG_PM_ORDER_ISFORMPMWORKUPLOADEDFRAGMENT = "pm_order_isformpmworkuploadedfragment";
    private String mPMOrderId;
    private boolean isFormPmWorkUploadedFragment;


    @Bind(R.id.pm_work_item_watch_listView) ListView pmWorkItemListView;

    public static Fragment newInstance(String mPMOrderId, boolean isFormPmWorkUploadedFragment) {
        PmWorkEditorContentFragment fragment = new PmWorkEditorContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PM_ORDER_ID, mPMOrderId);
        args.putBoolean(ARG_PM_ORDER_ISFORMPMWORKUPLOADEDFRAGMENT, isFormPmWorkUploadedFragment);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);

        mPMOrderId = getArguments().getString(ARG_PM_ORDER_ID);
        isFormPmWorkUploadedFragment = getArguments().getBoolean(ARG_PM_ORDER_ISFORMPMWORKUPLOADEDFRAGMENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pm_work_item_watch_public, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);
        queryAndBindData();
    }

    private void queryAndBindData() {


        ArrayList<PmWorkEditorItem> pmifsWorkItemList = new ArrayList<PmWorkEditorItem>();
        if (mPMOrderId != null) {
            PmifsWorkItemCursor pmifsWorkItemCursor =
                    new PmifsWorkItemSelection()
                            .orderId(mPMOrderId)
                            .query(getContext());

            try {
                while (pmifsWorkItemCursor.moveToNext()) {
                    PmWorkEditorItem pmWorkEditorItem = new PmWorkEditorItem();

                    int resultEnumOrdinal = (pmifsWorkItemCursor.getResultEnumOrdinal() != null)
                            ? pmifsWorkItemCursor.getResultEnumOrdinal() : -1;


                    pmWorkEditorItem.setWorkItemDes(pmifsWorkItemCursor.getItemDes());
                    pmWorkEditorItem.setResultEnumOrdinal(resultEnumOrdinal);
                    pmWorkEditorItem.setStartTime(1);
                    pmWorkEditorItem.setFinishTime(1);

                    pmifsWorkItemList.add(pmWorkEditorItem);

                }


                PmifsWorkCursor pmifsWorkCursor = new PmifsWorkSelection().orderId(mPMOrderId).query(getContext());
                try {


                    PmWorkEditorItem pmWorkEditorItem = new PmWorkEditorItem();
                    pmWorkEditorItem.setWorkItemDes("noinfo");
                    pmWorkEditorItem.setResultEnumOrdinal(-1);

                    if (pmifsWorkCursor.moveToFirst()) {
                        if(pmifsWorkCursor.getOperationStartTime() != null){
                            pmWorkEditorItem.setStartTime(pmifsWorkCursor.getOperationStartTime());
                        }

                        if(pmifsWorkCursor.getOperationEndTime() != null){
                            pmWorkEditorItem.setFinishTime(pmifsWorkCursor.getOperationEndTime());
                        }
                    }
                    pmifsWorkItemList.add(pmWorkEditorItem);
                } finally {
                    pmifsWorkCursor.close();
                }

            } finally {
                pmifsWorkItemCursor.close();
            }

            if (pmifsWorkItemList.size() > 0) {
                PmWorkItemListViewAdapter pmWorkItemListViewAdapter =
                        new PmWorkItemListViewAdapter(getActivity(), mPMOrderId, isFormPmWorkUploadedFragment, pmifsWorkItemList);
                pmWorkItemListView.setAdapter(pmWorkItemListViewAdapter);
            }
        }

    }

    class PmWorkItemListViewAdapter extends BaseAdapter {

        final int TYPE_WORK_CONTENT = 0;
        final int TYPE_WORK_TIME  = 1;

        Context context;
        String mPMOrderId;
        boolean isFormPmWorkUploadedFragment;
        ArrayList<PmWorkEditorItem> pmWorkItemList;
        LayoutInflater inflater;

        public PmWorkItemListViewAdapter(Context context, String mPMOrderId, boolean isFormPmWorkUploadedFragment,
                                         ArrayList<PmWorkEditorItem> pmWorkItemList){
            inflater = LayoutInflater.from(context);
            this.context = context;
            this.mPMOrderId = mPMOrderId;
            this.isFormPmWorkUploadedFragment = isFormPmWorkUploadedFragment;
            this.pmWorkItemList = pmWorkItemList;

        }

        @Override
        public int getItemViewType(int position) {
            String itemInfo = pmWorkItemList.get(position).getWorkItemDes();
            if("noinfo".equals(itemInfo)){
                return TYPE_WORK_TIME;
            }else{
                return  TYPE_WORK_CONTENT;
            }

        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getCount() {
            return pmWorkItemList.size();
        }

        @Override
        public Object getItem(int position) {
            return pmWorkItemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolderContent holder = null;
            ViewHolderTime holderTime = null;
            int type = getItemViewType(position);

            if (convertView == null) {

                switch(type){
                    case TYPE_WORK_CONTENT:

                        convertView = inflater.inflate(R.layout.fragment_pm_work_released_content_item, null);
                        holder = new ViewHolderContent();
                        holder.pmWorkReleasedItemcount = (TextView) convertView.findViewById(R.id.pm_work_released_item_count);
                        holder.pmWorkReleasedItemDescription = (TextView) convertView.findViewById(R.id.pm_work_released_item_description);
                        holder.rgPmWorkReleasedItem = (RadioGroup) convertView.findViewById(R.id.pm_work_released_item_result);
                        holder.rbFinish = (RadioButton) convertView.findViewById(R.id.pm_work_released_result_finished);
                        holder.rbUnfinish = (RadioButton) convertView.findViewById(R.id.pm_work_released_result_unfinished);
                        convertView.setTag(holder);

                        break;

                    case TYPE_WORK_TIME:

                        convertView = inflater.inflate(R.layout.fragment_pm_work_released_content_item_time, null);
                        holderTime = new ViewHolderTime();
                        holderTime.pmWorkItemOperationStartTimeView = (EditText)convertView.findViewById(R.id.pm_work_item_time_operation_start_time);
                        holderTime.pmWorkItemOperationEndTimeView = (EditText)convertView.findViewById(R.id.pm_work_item_time_operation_end_time);
                        convertView.setTag(holderTime);

                        break;
                }

            } else {
                switch(type) {
                    case TYPE_WORK_CONTENT:
                        holder = (ViewHolderContent) convertView.getTag();
                        break;

                    case TYPE_WORK_TIME:
                        holderTime = (ViewHolderTime) convertView.getTag();
                        break;
                }
            }

            switch (type){
                case TYPE_WORK_CONTENT:

                    if(isFormPmWorkUploadedFragment){
                        holder.rbFinish.setClickable(false);
                        holder.rbUnfinish.setClickable(false);
                    }else{
                        holder.rbFinish.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String pmWorkReleasedItem = "";
                                int resultEnumOrdinal = -1;
                                PmifsWorkItemCursor pmifsWorkItemCursor =
                                        new PmifsWorkItemSelection()
                                                .orderId(mPMOrderId)
                                                .query(getContext());
                                try {
                                    if (pmifsWorkItemCursor.moveToPosition(position)) {
                                        pmWorkReleasedItem = pmifsWorkItemCursor.getWorkGuid();
                                        resultEnumOrdinal = (pmifsWorkItemCursor.getResultEnumOrdinal() != null)
                                                ? pmifsWorkItemCursor.getResultEnumOrdinal() : -1;
                                    }
                                } finally {
                                    pmifsWorkItemCursor.close();
                                }

                                new PmifsWorkItemContentValues()
                                        .putResultEnumOrdinal(1)
                                        .putLastModified(System.currentTimeMillis())
                                        .update(getContext(), new PmifsWorkItemSelection().workGuid(pmWorkReleasedItem));

                                boolean isSendBroadcast = resultEnumOrdinal*1 < 0 ? true :false;

                                if(isSendBroadcast){
                                    Intent intent = new Intent();
                                    intent.setAction("changePmWokItemCount");
                                    context.sendBroadcast(intent);

                                    finishAndRemind();
                                }

                            }
                        });


                        holder.rbUnfinish.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String pmWorkReleasedItem = "";
                                int resultEnumOrdinal = -1;

                                PmifsWorkItemCursor pmifsWorkItemCursor =
                                        new PmifsWorkItemSelection()
                                                .orderId(mPMOrderId)
                                                .query(getContext());
                                try {
                                    if (pmifsWorkItemCursor.moveToPosition(position)) {
                                        pmWorkReleasedItem = pmifsWorkItemCursor.getWorkGuid();
                                        resultEnumOrdinal = (pmifsWorkItemCursor.getResultEnumOrdinal() != null)
                                                ? pmifsWorkItemCursor.getResultEnumOrdinal() : -1;
                                    }
                                } finally {
                                    pmifsWorkItemCursor.close();
                                }

                                new PmifsWorkItemContentValues()
                                        .putResultEnumOrdinal(2)
                                        .putLastModified(System.currentTimeMillis())
                                        .update(getContext(), new PmifsWorkItemSelection().workGuid(pmWorkReleasedItem));

                                boolean isSendBroadcast = resultEnumOrdinal*2 < 0 ? true :false;
                                if(isSendBroadcast){
                                    Intent intent = new Intent();
                                    intent.setAction("changePmWokItemCount");
                                    context.sendBroadcast(intent);

                                    finishAndRemind();
                                }
                            }
                        });
                    }



                    holder.pmWorkReleasedItemDescription.setText((position + 1) + ". " + pmWorkItemList.get(position).getWorkItemDes());

                    holder.rgPmWorkReleasedItem.setTag(position);

                    int resultEnumOrdinal = -1;
                    PmifsWorkItemCursor pmifsWorkItemCursor =
                            new PmifsWorkItemSelection()
                                    .orderId(mPMOrderId)
                                    .query(getContext());
                    try {
                        if (pmifsWorkItemCursor.moveToPosition(position)) {
                            resultEnumOrdinal = (pmifsWorkItemCursor.getResultEnumOrdinal() != null)
                                    ? pmifsWorkItemCursor.getResultEnumOrdinal() : -1;
                        }
                    } finally {
                        pmifsWorkItemCursor.close();
                    }

                    if(resultEnumOrdinal == 1){
                        holder.rbFinish.setChecked(true);
                    }else if(resultEnumOrdinal == 2){
                        holder.rbUnfinish.setChecked(true);
                    }else {
                        holder.rgPmWorkReleasedItem.clearCheck();
                    }


                    break;

                case TYPE_WORK_TIME:
                    long startTime = pmWorkItemList.get(position).getStartTime();
                    long finishTime = pmWorkItemList.get(position).getFinishTime();

                    if(startTime > 1){
                        holderTime.pmWorkItemOperationStartTimeView.setText(DATE_FORMAT.format(startTime));
                    }

                    if(finishTime > 1){
                        holderTime.pmWorkItemOperationEndTimeView.setText(DATE_FORMAT.format(finishTime));
                    }


                    if(isFormPmWorkUploadedFragment){
                        holderTime.pmWorkItemOperationStartTimeView.setClickable(false);
                        holderTime.pmWorkItemOperationEndTimeView.setClickable(false);
                    }else{

                        final EditText editTextStartTimeTemp = holderTime.pmWorkItemOperationStartTimeView;
                        final EditText editTextEndTimeTemp = holderTime.pmWorkItemOperationEndTimeView;
                        holderTime.pmWorkItemOperationStartTimeView.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                openDateTimePickerUpon((TextView) v,
                                        getString(R.string.hint_operation_start_time), editTextStartTimeTemp);
                            }
                        });

                        holderTime.pmWorkItemOperationEndTimeView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                openDateTimePickerUpon((TextView) v,
                                        getString(R.string.hint_operation_end_time),  editTextEndTimeTemp);
                            }
                        });
                    }

                    break;

            }
            return convertView;
        }
    }


    private class ViewHolderContent {
        TextView pmWorkReleasedItemcount;
        TextView pmWorkReleasedItemDescription;
        RadioGroup rgPmWorkReleasedItem;
        RadioButton rbFinish;
        RadioButton rbUnfinish;
    }

    private class ViewHolderTime {
        EditText pmWorkItemOperationStartTimeView;
        EditText pmWorkItemOperationEndTimeView;
    }


    private class PmWorkEditorItem{

        String workItemDes;
        int resultEnumOrdinal;

        long startTime;
        long finishTime;

        public String getWorkItemDes() {
            return workItemDes;
        }

        public void setWorkItemDes(String workItemDes) {
            this.workItemDes = workItemDes;
        }

        public int getResultEnumOrdinal() {
            return resultEnumOrdinal;
        }

        public void setResultEnumOrdinal(int resultEnumOrdinal) {
            this.resultEnumOrdinal = resultEnumOrdinal;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(long finishTime) {
            this.finishTime = finishTime;
        }
    }


    private void openDateTimePickerUpon(final TextView view, final String title, EditText editText) {
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
                        case R.id.pm_work_item_time_operation_start_time:
                            long startTime = Numbers.longValue((Long) editText.getTag());
                            new PmifsWorkContentValues()
                                    .putOperationStartTime(startTime)
                                    .update(getContext().getContentResolver(), new PmifsWorkSelection().orderId(mPMOrderId));

                            finishAndRemind();


                            break;
                        case R.id.pm_work_item_time_operation_end_time:
                            long endTime = Numbers.longValue((Long) editText.getTag());
                            new PmifsWorkContentValues()
                                    .putOperationEndTime(endTime)
                                    .update(getContext().getContentResolver(), new PmifsWorkSelection().orderId(mPMOrderId));
                            finishAndRemind();
                            break;
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }




    private void finishAndRemind(){
        int pmRemainingJobItemCount = new PmifsWorkItemSelection()
                .orderId(mPMOrderId)
                .and()
                .resultEnumOrdinal((Integer) null)
                .count(getContext().getContentResolver());

        boolean isStartTimeAndEndTimeOK = false;
        boolean isOperationStartTimeNotNull = false;
        boolean isOperationStartTimeValid = false;
        boolean isOperationendTimeNotNull = false;
        boolean isOperationendTimeValid = false;
        boolean isUploadPicOk = false;
        long operationStartTime = 0;
        long operationEndTime = 0;


        PmifsWorkCursor pmifsWorkCursor = new PmifsWorkSelection().orderId(mPMOrderId).query(getContext());
        try {
            if (pmifsWorkCursor.moveToFirst()) {
                if(pmifsWorkCursor.getOperationStartTime() != null){
                    isOperationStartTimeNotNull = true;
                    operationStartTime = pmifsWorkCursor.getOperationStartTime();
                }

                if(pmifsWorkCursor.getOperationEndTime() != null){
                    isOperationendTimeNotNull = true;
                    operationEndTime = pmifsWorkCursor.getOperationEndTime();
                }
            }
        } finally {
            pmifsWorkCursor.close();
        }


        if(isOperationStartTimeNotNull){
            if(operationStartTime <= System.currentTimeMillis()){
                isOperationStartTimeValid = true;
            }
        }


        if(isOperationendTimeNotNull){
            if(operationEndTime <= System.currentTimeMillis()){
                isOperationendTimeValid = true;
            }
        }


        if(isOperationStartTimeValid && isOperationendTimeValid){
            if(operationStartTime <= operationEndTime){
                isStartTimeAndEndTimeOK = true;
            }
        }


        PictureCursor pictureCursor1 = new PictureSelection()
                .keyId(mPMOrderId)
                .and()
                .type("P")
                .and()
                .remark("1")
                .query(getContext());

        PictureCursor pictureCursor0 = new PictureSelection()
                .keyId(mPMOrderId)
                .and()
                .type("P")
                .and()
                .remark("0")
                .query(getContext());

        if (pictureCursor0.getCount()+pictureCursor1.getCount()>0){
          isUploadPicOk = true;
        }
        if(pmRemainingJobItemCount == 0 && isStartTimeAndEndTimeOK&&isUploadPicOk){
            new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.ic_info_black_24dp)
                    .setTitle("温馨提示")
                    .setMessage("该工单已选择全部工作项，是否将该工单转为“已完成”？")
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new PmifsWorkContentValues().putStatus(PmifsWorkStatus.WORKDONE)
                                    .update(getActivity().getContentResolver(), new PmifsWorkSelection().orderId(mPMOrderId));
                            getActivity().finish();
                        }
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .setCancelable(false)
                    .show();
        }
    }

}
