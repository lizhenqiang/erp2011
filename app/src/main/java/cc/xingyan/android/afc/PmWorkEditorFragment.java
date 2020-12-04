/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.PmService;
import cc.xingyan.android.afc.api.model.PmWorkExecuteMan;
import cc.xingyan.android.afc.api.model.PmWorkExecuteMans;
import cc.xingyan.android.afc.api.model.PmWorkID;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.picture.PictureCursor;
import cc.xingyan.android.afc.provider.picture.PictureSelection;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkContentValues;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkCursor;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkSelection;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkStatus;
import cc.xingyan.android.afc.provider.pmifsworkitem.PmifsWorkItemSelection;
import cc.xingyan.android.afc.provider.user.UserCursor;
import cc.xingyan.android.afc.provider.user.UserSelection;
import cc.xingyan.android.afc.util.LogUtil;
import cc.xingyan.android.afc.util.NetUtil;

/**
 * Created by San on 10/13/15.
 */
public class PmWorkEditorFragment extends BaseFragment {
    private static final String ARG_URI = "uri";

    @Bind(R.id.tab_layout_pm_work_editor)
    TabLayout mTabLayout;
    @Bind(R.id.view_pager_pm_work_editor)
    ViewPager mViewPager;

    private PagerAdapter mPagerAdapter;
    private List<Page> pages = new ArrayList<>();

    @Inject
    Authenticator mAuthenticator;
    @Inject
    PmService pmService;
    private boolean t;
    private Uri mUri;

    private String mWorkGuid;
    private String mPMOrderId;

    private ContentObserver observer;
    private boolean allDone;

    public static Fragment newInstance(Uri uri) {
        PmWorkEditorFragment fragment = new PmWorkEditorFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_URI, uri);
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);
        mUri = getArguments().getParcelable(ARG_URI);
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        IntentFilter iFilter_ = new IntentFilter();
        iFilter_.addAction("changePmWokItemCount");
        getContext().getApplicationContext().registerReceiver(receiver_, iFilter_);

        return inflater.inflate(R.layout.fragment_pm_work_editor, container, false);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        final PmifsWorkCursor work = new PmifsWorkCursor(getContext().getContentResolver().query(mUri, null, null, null, null));

        try {
            if (work.moveToNext()) {
                getActivity().setTitle("["+work.getWorkTypeId()+"工单] #" + work.getOrderId());
                ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(work.getDeviceCode());
                mPMOrderId = work.getOrderId();
            }
        } finally {
            work.close();
        }

        final TabLayout.ViewPagerOnTabSelectedListener l = new TabLayout.ViewPagerOnTabSelectedListener(mViewPager);
        mTabLayout.setOnTabSelectedListener(l);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.setAdapter(mPagerAdapter = new PagerAdapter());
        mPagerAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                // 更新tablayout
                mTabLayout.setOnTabSelectedListener(null);
                mTabLayout.removeAllTabs();
                for (int i = 0, count = pages.size(); i < count; i++) {
                    mTabLayout.addTab(mTabLayout.newTab().setText(pages.get(i).title));
                }
                mTabLayout.setOnTabSelectedListener(l);
            }
        });
        loadPages();
    }

    @Override public void onDestroyView() {

        super.onDestroyView();
    }

    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.pm_job_editor, menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
            case R.id.pm_work_item_action_done_:
                try {
                    markDone();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;

            case R.id.pm_change:

                boolean isNetworkAvailable =  NetUtil.isNetworkAvailable(getActivity());
                if(!isNetworkAvailable){
                    new AlertDialog.Builder(getActivity())
                            .setIcon(R.drawable.ic_info_black_24dp)
                            .setTitle("温馨提示")
                            .setMessage("没有网络！")
                            .setPositiveButton(R.string.ok, null)
                            .show();
                    return true;
                }

                changePMWorkOrder();
                return true;
        }
    }


    int changedPMIndex = 0;
    private void changePMWorkOrder() {
        long mJobId = 0;
        UserCursor userCursor = new UserSelection().query(getContext().getContentResolver());
        List<ShowInfoObj> showInfoObjList = new ArrayList<ShowInfoObj>();
        while (userCursor.moveToNext()) {
            if(!userCursor.getUserId().equals(mAuthenticator.getAuthenticatedUserId())) {
                ShowInfoObj showInfoObj = new ShowInfoObj();
                showInfoObj.setTitle("[转给执行人] #");
                showInfoObj.setUserName(userCursor.getUserName());
                showInfoObj.setmJobId(userCursor.getId());
                showInfoObjList.add(showInfoObj);
            }
        }

        String[] showTitles = new String[showInfoObjList.size()];
        for (int i = 0; i < showTitles.length; i++) {
            showTitles[i] = showInfoObjList.get(i).getTitle() + showInfoObjList.get(i).getUserName();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.ic_info_black_24dp);
        builder.setTitle("PM工单转单 #" + mPMOrderId);
        ListAdapter myAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, showTitles);
        builder.setAdapter(myAdapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int position) {
                long seletpMJobId = showInfoObjList.get(position).getmJobId();
                t = false;
                uploadChangePMWork(seletpMJobId, mPMOrderId, () -> {
                    if(t){
                        if(changedPMIndex == 0){
                            Toast.makeText(getContext(), "不允许转单！", Toast.LENGTH_SHORT).show();
                        }
                        getActivity().finish();
                    } else {
                        Toast.makeText(getContext(), "转单失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
        builder.show();
        userCursor.close();
        return;

    }


    private boolean uploadChangePMWork(long id, String pmOrderid, Runnable next) {
        String orderId = pmOrderid;
        String executeMan = null;
        UserCursor userCursor = new UserSelection().id(id).query(getContext().getContentResolver());
        while (userCursor.moveToNext()) {
            executeMan = userCursor.getUserId();
        }
        userCursor.close();

        if (executeMan == null || orderId == null) {
            t = false;
        }

        PmWorkExecuteMan pmWorkExecuteMan = new PmWorkExecuteMan();
        List<PmWorkExecuteMan> list = Arrays.asList(pmWorkExecuteMan);
        PmWorkExecuteMans listsMan = new PmWorkExecuteMans();
        pmWorkExecuteMan.setOrderId(orderId);
        pmWorkExecuteMan.setExecuteMan(executeMan);
        listsMan.setPmWorkExecuteMans(list);
        subscribe(pmService.postChangeExecuteManPMWorks(listsMan), resp -> {
            final List<ContentValues> workValues = new ArrayList<>(resp.size());
            for (PmWorkID i : resp) {
                new PmifsWorkContentValues()
                        .putStatus(PmifsWorkStatus.RELEASED)
                        .putUploadPending(false)
                        .update(getContext().getContentResolver(), new PmifsWorkSelection().orderId(i.getOrderId()));

                deletePMWork(i);
            }
            LogUtil.debug(TAG, "change a pm is ok");
            t = true;
            if (next != null) {
                next.run();
            }
        }, e -> {
            t = false;
            LogUtil.debug(TAG, "change a pm is failed");
            Toast.makeText(getContext(), "PM转单失败", Toast.LENGTH_SHORT).show();
        });
        return  t;
    }


    private void deletePMWork(PmWorkID pmWorkID){
        final PmifsWorkSelection sel = new PmifsWorkSelection().orderId(pmWorkID.getOrderId());
        changedPMIndex = sel.delete(getContext().getContentResolver());
    }


    class ShowInfoObj {
        private String title;
        private long mJobId;
        private String UserName;

        public String getTitle() {
            return title;
        }

        public long getmJobId() {
            return mJobId;
        }
        public String getUserName() {
            return UserName;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setmJobId(long mJobId) {
            this.mJobId = mJobId;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

    }


    private void markDone() throws Exception{


        int resultCode = validateReports();
        if(resultCode == 0){
            new PmifsWorkContentValues()
                    .putStatus(PmifsWorkStatus.WORKDONE)
                    .update(getContext().getContentResolver(), new PmifsWorkSelection().orderId(mPMOrderId));
            getActivity().finish();
        }else if(resultCode == 1){
            Snackbar.make(mViewPager, R.string.message_pm_work_not_done, Snackbar.LENGTH_SHORT).show();
        }else if(resultCode == 2){
            Snackbar.make(mViewPager, "开始时间没写!", Snackbar.LENGTH_SHORT).show();
        }else if(resultCode == 3){
            Snackbar.make(mViewPager, "结束时间没写!", Snackbar.LENGTH_SHORT).show();
        }else if(resultCode == 4){
            Snackbar.make(mViewPager, "开始时间大于当前时间!", Snackbar.LENGTH_SHORT).show();
        }else if(resultCode == 5){
            Snackbar.make(mViewPager, "结束时间大于当前时间!", Snackbar.LENGTH_SHORT).show();
        }else if(resultCode == 6){
            Snackbar.make(mViewPager, "开始时间大于结束时间!", Snackbar.LENGTH_SHORT).show();
        }else if (resultCode == 7){
            Snackbar.make(mViewPager, "照片未上传", Snackbar.LENGTH_SHORT).show();
        }
    }


    private int validateReports(){
        boolean isOperationStartTimeNotNull = false;
        boolean isOperationendTimeNotNull = false;
        long operationStartTime = 0;
        long operationEndTime = 0;

        PmifsWorkCursor pmifsWorkCursor = new PmifsWorkSelection().orderId(mPMOrderId).query(getContext());
        try {
            if (pmifsWorkCursor.moveToFirst()) {
                if(pmifsWorkCursor.getOperationStartTime() != null){
                    isOperationStartTimeNotNull = true;
                    operationStartTime = pmifsWorkCursor.getOperationStartTime();
                }else{
                    return 2;
                }

                if(pmifsWorkCursor.getOperationEndTime() != null){
                    isOperationendTimeNotNull = true;
                    operationEndTime = pmifsWorkCursor.getOperationEndTime();
                }else {
                    return 3;
                }
            }
        } finally {
            pmifsWorkCursor.close();
        }


        if(isOperationStartTimeNotNull){
            if(operationStartTime > System.currentTimeMillis()){
                return 4;
            }
        }


        if(isOperationendTimeNotNull){
            if(operationEndTime > System.currentTimeMillis()){
                return 5;
            }
        }


        if(isOperationStartTimeNotNull && isOperationendTimeNotNull){
            if(operationStartTime > operationEndTime){
                return 6;
            }
        }

        int pmRemainingJobItemCount = new PmifsWorkItemSelection()
                .orderId(mPMOrderId)
                .and()
                .resultEnumOrdinal((Integer) null)
                .count(getContext().getContentResolver());
        if (pmRemainingJobItemCount > 0){
            return 1;
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

        //老款pda不受此限制
        if (!Build.MODEL.equals("C6000L")&&pictureCursor0.getCount()+pictureCursor1.getCount()==0){

            return  7;

        }
        return 0;
    }




    private void loadPages() {


            int pmRemainingJobItemCount = new PmifsWorkItemSelection()
                    .orderId(mPMOrderId)
                    .and()
                    .resultEnumOrdinal((Integer) null)
                    .count(getContext().getContentResolver());

            String tempStr = String.format(pmRemainingJobItemCount > 0 ? "%1$s" : "√",
                    pmRemainingJobItemCount);

            if(!tempStr.equals("√")){
                int countTemp = Integer.parseInt(tempStr);
                if(countTemp < 10){
                    tempStr = "0" + tempStr;
                }
            }

            pages.clear();
            pages.add(new Page(Page.TAB_PM_DEVICEINFO, "设备信息", mPMOrderId));
            pages.add(new Page(Page.TAB_PM_CONTENT, "工作内容(" + tempStr + ")", mPMOrderId));
            pages.add(new Page(Page.TAB_PM_IMAGE, "工作图片", mPMOrderId));
            pages.add(new Page(Page.TAB_PM_MATERIAL, "工作物料", mPMOrderId));
            mPagerAdapter.notifyDataSetChanged();
    }

    private static class Page {
        public static final int TAB_PM_DEVICEINFO = 0;
        public static final int TAB_PM_CONTENT = 1;
        public static final int TAB_PM_IMAGE = 2;
        public static final int TAB_PM_MATERIAL = 3;

        int type;
        String title;
        Object attachment;

        public Page(@NonNull int type, @NonNull String title, @NonNull Object attachment) {
            this.type = type;
            this.title = title;
            this.attachment = attachment;
        }
    }

    class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter() {
            super(getChildFragmentManager());
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pages.get(position).title;
        }

        @Override
        public Fragment getItem(int position) {
            final Page p = pages.get(position);
            switch (p.type) {
                default:
                case Page.TAB_PM_DEVICEINFO:
                    return PmWorkNewDeviceInfoFragment.newInstance((String) p.attachment);

                case Page.TAB_PM_CONTENT:
                    return PmWorkEditorContentFragment.newInstance((String) p.attachment, false);

                case Page.TAB_PM_IMAGE:
                    return PmWorkEditorImageFragment.newInstance((String) p.attachment);

                case Page.TAB_PM_MATERIAL:
                    return PmWorkNewMaterialFragment.newInstance((String) p.attachment);
            }
        }

        @Override
        public int getCount() {
            return pages.size();
        }
    }

    private BroadcastReceiver receiver_ = new BroadcastReceiver() {
        public void onReceive(android.content.Context context,
                              android.content.Intent intent) {

            try {


                int pmRemainingJobItemCount = new PmifsWorkItemSelection()
                        .orderId(mPMOrderId)
                        .and()
                        .resultEnumOrdinal((Integer) null)
                        .count(getContext().getContentResolver());

                String tempStr = String.format(pmRemainingJobItemCount > 0 ? "%1$s" : "√",
                        pmRemainingJobItemCount);
                if(!tempStr.equals("√")){
                    int countTemp = Integer.parseInt(tempStr);
                    if(countTemp < 10){
                        tempStr = "0" + tempStr;
                    }
                }
                mTabLayout.getTabAt(1).setText("工作内容(" + tempStr + ")");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    };

}
