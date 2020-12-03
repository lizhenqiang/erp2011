package cc.xingyan.android.afc;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.net.Uri;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.CmService;
import cc.xingyan.android.afc.api.model.CmWorkExecuteMan;
import cc.xingyan.android.afc.api.model.CmWorkExecuteMans;
import cc.xingyan.android.afc.api.model.CmWorkID;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.cmmaterial.CmMaterialCursor;
import cc.xingyan.android.afc.provider.cmmaterial.CmMaterialSelection;
import cc.xingyan.android.afc.provider.cmmaterialrow.CmMaterialRowCursor;
import cc.xingyan.android.afc.provider.cmmaterialrow.CmMaterialRowSelection;
import cc.xingyan.android.afc.provider.cmspareparts.CmSparePartsCursor;
import cc.xingyan.android.afc.provider.cmspareparts.CmSparePartsSelection;
import cc.xingyan.android.afc.provider.cmwork.CmWorkContentValues;
import cc.xingyan.android.afc.provider.cmwork.CmWorkCursor;
import cc.xingyan.android.afc.provider.cmwork.CmWorkSelection;
import cc.xingyan.android.afc.provider.cmwork.CmWorkStatus;
import cc.xingyan.android.afc.provider.user.UserCursor;
import cc.xingyan.android.afc.provider.user.UserSelection;
import cc.xingyan.android.afc.util.Action;
import cc.xingyan.android.afc.util.LogUtil;
import cc.xingyan.android.afc.util.NetUtil;

/**
 * Created by 1 on 2015/11/26.
 *
 * CM工单Fragment_yjd_20160307
 */
public class CmWorkEditerFragment extends BaseFragment implements CmReportFragment.Callbacks,  Action.Interceptor  {
    private static final String TAG = "CmEditer";
    private static final String ARG_URI = "uri";

    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    @Inject
    Authenticator mAuthenticator;
    @Inject
    static CmService cmService;
    private Uri mUri;
    private boolean t;
    private String mCMOrderId;
    private PagerAdapter mPagerAdapter;
    private List<Page> pages = new ArrayList<>();


    public static Fragment newInstance(Uri uri, String info) {
        CmWorkEditerFragment fragment = new CmWorkEditerFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_URI, uri);
        args.putString("deviceID", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);
        mUri = getArguments().getParcelable(ARG_URI);
    }

    @Override
    public boolean onInterceptAction(Action action) {
        int cursorCount = new CmMaterialRowSelection().cmOrderId(mCMOrderId)
                .count(getContext().getContentResolver());
        if(cursorCount > 0){
            CmMaterialRowCursor cmMaterialLineItemCursor =
                    new CmMaterialRowSelection().cmOrderId(mCMOrderId).query(getContext());
            while (cmMaterialLineItemCursor.moveToNext()){
                try {
                    boolean isMaterialRowOk = cmMaterialLineItemCursor.getMaterialRow() != null ? true : false;
                    boolean isMaterialIdOk = cmMaterialLineItemCursor.getMaterialId() != null ? true : false;
                    boolean isMaterialCountOk = cmMaterialLineItemCursor.getDueDate() != null ? true : false;
                    boolean isDueDateOk = cmMaterialLineItemCursor.getDueDate() != null ? true : false;

                    for (boolean isCmMaterialLineItemOk :
                            new boolean[]{isMaterialIdOk, isMaterialCountOk, isDueDateOk}) {
                        if (isCmMaterialLineItemOk == false) {
                            Snackbar.make(mViewPager, "物料行信息不完整！", Snackbar.LENGTH_SHORT).show();
                            return true;
                        }
                    }

                    if(isMaterialRowOk == false){
                        Snackbar.make(mViewPager, "有未上传的物料行！", Snackbar.LENGTH_SHORT).show();
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            cmMaterialLineItemCursor.close();
        }

        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cm_work_editor, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        final CmWorkCursor work = new CmWorkCursor(getContext().getContentResolver().query(mUri, null, null, null, null));
        try {
            if (work.moveToNext()) {
                mCMOrderId = work.getOrderId();
                getActivity().setTitle("[CM工单] # " + work.getOrderId());
                ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(work.getDeviceCode());
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

    private void loadPages() {
        pages.clear();
        pages.add(new Page(Page.TAB_FAULT, "故障现象", mCMOrderId));
        pages.add(new Page(Page.TAB_REPORT, "处理报告", mCMOrderId));

        // Query cm material
        final CmMaterialCursor cmMaterialCursor = new CmMaterialSelection().cmOrderId(mCMOrderId).query(getContext());
        try {
            if (cmMaterialCursor.moveToNext()) {
                pages.add(new Page(Page.TAB_MATERIAL, "物料", mCMOrderId));
            }
        } finally {
            cmMaterialCursor.close();
        }

        final CmSparePartsCursor cmSparePartsCursor = new CmSparePartsSelection().cmWorkId(mCMOrderId).query(getContext());
        try {
            if (cmSparePartsCursor.moveToNext()) {
                pages.add(new Page(Page.TAB_SPARE_PART, "备件", cmSparePartsCursor.getId()));
            }
        } finally {
            cmSparePartsCursor.close();
        }

        mPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onOpenSparePartTab(long cmSparePartId) {
        boolean found = false;
        for (int i = 0; i < pages.size(); i++) {
            final Page p = pages.get(i);
            if (p.type == Page.TAB_SPARE_PART && p.attachment.equals(cmSparePartId)) {
                found = true;
                mTabLayout.getTabAt(i).select();
            }
        }
        if (!found) {
            pages.add(new Page(Page.TAB_SPARE_PART, "备件", CmSparePartFragment.NEW_SPARE_PART));
            mPagerAdapter.notifyDataSetChanged();
            mViewPager.post(() -> {
                mTabLayout.getTabAt(pages.size() - 1).select();
            });
        }
    }


    @Override
    public void onOpenMaterialTab() {
        boolean found = false;
        for (int i = 0; i < pages.size(); i++) {
            final Page p = pages.get(i);
            if (p.type == Page.TAB_MATERIAL) {
                found = true;
                mTabLayout.getTabAt(i).select();
            }
        }
        if (!found) {
            pages.add(2, new Page(Page.TAB_MATERIAL, "物料", mCMOrderId));
            mPagerAdapter.notifyDataSetChanged();
            mViewPager.post(() -> {
                mTabLayout.getTabAt(2).select();
            });
        }
    }


    private static class Page {
        public static final int TAB_FAULT = 0;
        public static final int TAB_REPORT = 1;
        public static final int TAB_MATERIAL = 2;
        public static final int TAB_SPARE_PART = 3;
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
                case Page.TAB_FAULT:
                    return CmFaultFragment.newInstance((String) p.attachment);
                case Page.TAB_REPORT:
                    return CmReportFragment.newInstance((String) p.attachment);
                case Page.TAB_MATERIAL:
                    return CmMaterialFragment.newInstance((String) p.attachment);
                case Page.TAB_SPARE_PART:
                    return CmSparePartFragment.newInstance((Long) p.attachment);
            }
        }

        @Override
        public int getCount() {
            return pages.size();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        final CmWorkCursor cur = new CmWorkSelection().orderId(mCMOrderId).query(getContext().getContentResolver());
        CmWorkStatus cmStatus = null;
        try {
            if (cur.moveToNext()) {
                cmStatus  = cur.getStatus();
            }
            if(cmStatus == CmWorkStatus.RELEASED){
                inflater.inflate(R.menu.cm_editor, menu);
            }else{
                inflater.inflate(R.menu.cm_editor_done, menu);
            }
        } finally {
            cur.close();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
            case R.id.action_done:
                try {
                    checkAndSave();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.new_materiels:

                if(pages.size() >= 3 && pages.get(2).type == Page.TAB_MATERIAL){
                    Toast.makeText(getContext(), "已申请物料订单！", Toast.LENGTH_SHORT).show();
                }else{
                    new AlertDialog.Builder(getContext())
                            .setIcon(R.drawable.ic_cloud_black_24dp)
                            .setTitle("物料申请确认")
                            .setMessage("确定需要物料吗？")
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getContext(), "申请物料订单", Toast.LENGTH_SHORT).show();
                                    onOpenMaterialTab();
                                }
                            })
                            .setNegativeButton(R.string.cancel, null)
                            .show();
                }


                return true;
            case R.id.cm_change:

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

                changeCMWorkOrder();
                return true;

        }
    }

    private void changeCMWorkOrder() {
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
        builder.setTitle("CM工单转单 #" + mCMOrderId);
        ListAdapter myAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, showTitles);
        builder.setAdapter(myAdapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int position) {
                long seletcMJobId = showInfoObjList.get(position).getmJobId();
                t = false;
                uploadChangeCMWork(seletcMJobId, mCMOrderId, () -> {
                    if(t){
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

    private boolean uploadChangeCMWork(long id, String cmOrderid, Runnable next) {
        String orderId = cmOrderid;
        String executeMan = null;
        UserCursor userCursor = new UserSelection().id(id).query(getContext().getContentResolver());
        while (userCursor.moveToNext()) {
            executeMan = userCursor.getUserId();
        }
        userCursor.close();

        if (executeMan == null || orderId == null) {
            t = false;
        }

        CmWorkExecuteMan cmWorkExecuteMan = new CmWorkExecuteMan();
        List<CmWorkExecuteMan> list = Arrays.asList(cmWorkExecuteMan);
        CmWorkExecuteMans listsMan = new CmWorkExecuteMans();
        cmWorkExecuteMan.setOrderId(orderId);
        cmWorkExecuteMan.setExecuteMan(executeMan);
        listsMan.setcmWorkExecuteMans(list);
        subscribe(cmService.postChangeExecuteManCMWorks(listsMan), resp -> {
            final List<ContentValues> workValues = new ArrayList<>(resp.size());
            for (CmWorkID i : resp) {
                new CmWorkContentValues()
                        .putStatus(CmWorkStatus.FINISH)
                        .putUploadPending(false)
                        .update(getContext().getContentResolver(), new CmWorkSelection().orderId(i.getOrderId()));

                deleteCMWork(i);
                deleteCMWorkMaterialOrder(i);
            }
            LogUtil.debug(TAG, "change a cm is ok");
            t = true;
            if (next != null) {
                next.run();
            }
        }, e -> {
            t = false;
            LogUtil.debug(TAG, "change a cm is failed");
            Toast.makeText(getContext(), "CM转单失败", Toast.LENGTH_SHORT).show();
        });
        return  t;
    }
    private void deleteCMWork(CmWorkID i){
        final CmWorkSelection sel = new CmWorkSelection().orderId(i.getOrderId());
        deleteCMWorkMaterialOrder(i);
        sel.delete(getContext().getContentResolver());
    }

    private void deleteCMWorkMaterialOrder(CmWorkID i) {
        final CmMaterialSelection csel = new CmMaterialSelection().cmOrderId(i.getOrderId());
        final CmMaterialCursor ccur = csel.query(getContext().getContentResolver());

        List<String> ids = new ArrayList<>(ccur.getCount());
        while (ccur.moveToNext()) {
            ids.add(ccur.getMaterialOrderId());
        }
        ccur.close();

        if (ids.size() > 0) {
            new CmMaterialRowSelection().cmOrderId(i.getOrderId()).and()
                    .materialOrderId(ids.toArray(new String[ids.size()]))
                    .delete(getContext().getContentResolver());
            csel.delete(getContext().getContentResolver());
        }
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

    int snackbarInfoCode = 0;

    private void checkAndSave() throws Exception{
        boolean ok = isAllInfoFinish();

        if (ok) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            CmWorkCursor cmWorkCursor = new CmWorkSelection().orderId(mCMOrderId).query(getContext());
            long operationStartTime = 0;
            long operationEndTime = 0;
            while (cmWorkCursor.moveToNext()) {
                operationStartTime = cmWorkCursor.getOperationStartTime();
                operationEndTime = cmWorkCursor.getOperationEndTime();
            }

            if(operationStartTime > operationEndTime){
                new AlertDialog.Builder(getActivity())
                        .setTitle("温馨提示")
                        .setMessage("工作结束时间要大于工作开始时间！\n" +
                                "当前工作开始时间为：\n" + sdf.format(operationStartTime) + "\n" +
                                "当前工作结束时间为：\n" + sdf.format(operationEndTime))
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
                                "当前结束时间为：\n" + sdf.format(operationEndTime) + "\n" +
                                "当前系统时间为：\n" + sdf.format(sysTime))
                        .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

                            }
                        }).setCancelable(false)
                        .show();
                return;
            }

            final String dispose = mAuthenticator.getAuthenticatedUserId();
            final CmWorkCursor cur = new CmWorkSelection().orderId(mCMOrderId).query(getContext().getContentResolver());
            try {
                if (cur.moveToNext()) {
                    new CmWorkContentValues()
                            .putDispose(dispose)
                            .putPlanSTime(cur.getApplyFTime())
                            .putPlanFTime(cur.getApplyFTime())
                            .putStatus(CmWorkStatus.WORKDONE)
                            .putLastModified(System.currentTimeMillis())
                            .putUploadPending(false)
                            .update(getContext().getContentResolver(), new CmWorkSelection().orderId(mCMOrderId));
                }
            } finally {
                cur.close();
                getActivity().finish();
            }
        } else {
            String infoShow = "";
            switch (snackbarInfoCode) {

                case 123:
                    infoShow = "没有物料信息！";
                    break;

                case 234:
                    infoShow = "处理报告信息不完整！";
                    break;

                case 456:
                    infoShow = "物料订单信息不完整！";
                    break;

                case 567:
                    infoShow = "物料行信息不完整！";
                    break;

                case 678:
                    infoShow = "有未上传的物料行！";
                    break;

                default:
                    infoShow = getResources().getString(R.string.message_cm_work_not_done);
                    break;
            }
            Toast.makeText(getActivity(), infoShow, Toast.LENGTH_SHORT).show();
        }
    }


    private boolean isAllInfoFinish() {

        CmWorkCursor cmWorkCursor = new CmWorkSelection().orderId(mCMOrderId).query(getContext());
        while (cmWorkCursor.moveToNext()) {
            try {
                boolean isIntCustomerNoTextOk = cmWorkCursor.getIntCustomerNo() != null ? true : false;
                boolean isCmEquipFaultTextOk = cmWorkCursor.getEquipFault() != null ? true : false;
                boolean isCmFaultGradeTextOk = cmWorkCursor.getFaultGradeText() != null ? true : false;
                boolean isCmFaultDescriptionTextOk = cmWorkCursor.getFaultDescriptionCode() != null ? true : false;
                boolean isCmFaultTypeTextOk = cmWorkCursor.getFaultTypeText() != null ? true : false;
                boolean isCmFaultCauseTextOk = cmWorkCursor.getFaultCauseText() != null ? true : false;
                boolean isCmWorkDetailsTextOk = cmWorkCursor.getWorkDetailsText() != null ? true : false;
                boolean isCmWorkDoneTextOk = cmWorkCursor.getWorkDoneText() != null ? true : false;

                boolean isCmFaultDescriptionNoteOk = (cmWorkCursor.getFaultNote() != null && cmWorkCursor.
                        getFaultDescriptionCode().trim().length() >= 1) ? true : false;

                boolean isCmFaultCauseNoteOk = (cmWorkCursor.getFaultCauseNote() != null && cmWorkCursor.
                        getFaultCauseNote().trim().length() >= 1) ? true : false;
                boolean isCmWorkNoteOk = (cmWorkCursor.getWorkNote() != null && cmWorkCursor.
                        getWorkNote().trim().length() >= 1) ? true : false;
                boolean isCmOperationStartTimeOk = cmWorkCursor.getOperationStartTime() != null ? true : false;
                boolean isCmOperationEndTimeOk = cmWorkCursor.getOperationEndTime() != null ? true : false;

                String faultCauseCodeStr = cmWorkCursor.getFaultCauseCode();


                if((faultCauseCodeStr != null) && (faultCauseCodeStr.trim().endsWith("99"))){

                    for (boolean isCmReportItemOk :
                            new boolean[]{isIntCustomerNoTextOk, isCmEquipFaultTextOk, isCmFaultGradeTextOk, isCmFaultTypeTextOk, isCmFaultCauseTextOk,
                                    isCmWorkDetailsTextOk, isCmWorkDoneTextOk, isCmFaultCauseNoteOk
                            }) {
                        if (isCmReportItemOk == false) {
                            snackbarInfoCode = 234;
                            return false;
                        }
                    }
                }else if((faultCauseCodeStr != null) && !(faultCauseCodeStr.trim().endsWith("99"))){

                    for (boolean isCmReportItemOk :
                            new boolean[]{isIntCustomerNoTextOk, isCmEquipFaultTextOk, isCmFaultGradeTextOk, isCmFaultTypeTextOk, isCmFaultCauseTextOk,
                                    isCmWorkDetailsTextOk, isCmWorkDoneTextOk
                            }) {
                        if (isCmReportItemOk == false) {
                            snackbarInfoCode = 234;
                            return false;
                        }
                    }
                }else{
                    for (boolean isCmReportItemOk :
                            new boolean[]{isIntCustomerNoTextOk, isCmEquipFaultTextOk, isCmFaultGradeTextOk, isCmFaultTypeTextOk, isCmFaultCauseTextOk,
                                    isCmWorkDetailsTextOk, isCmWorkDoneTextOk, isCmFaultCauseNoteOk
                            }) {
                        if (isCmReportItemOk == false) {
                            snackbarInfoCode = 234;
                            return false;
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                cmWorkCursor.close();
            }
        }

        if (pages.size() == 3 && pages.get(2).type == Page.TAB_MATERIAL) {

            String mMaterialOrderId = "";
            CmMaterialCursor cmMaterialFormCursor = new CmMaterialSelection().cmOrderId(mCMOrderId).query(getContext());

            int cursorCount = 0;

            while (cmMaterialFormCursor.moveToNext()){
                try {
                    mMaterialOrderId = cmMaterialFormCursor.getMaterialOrderId();

                    cursorCount = new CmMaterialRowSelection().cmOrderId(mCMOrderId).and()
                            .materialOrderId(mMaterialOrderId).count(getContext().getContentResolver());

                    boolean isMaterialOrderIdOk = cmMaterialFormCursor.getMaterialOrderId() != null ? true : false;
                    boolean isUserOk = cmMaterialFormCursor.getUser() != null ? true : false;
                    boolean isEnterDateOk = cmMaterialFormCursor.getEnterDate() != null ? true : false;
                    boolean isDueDateOk = cmMaterialFormCursor.getDueDate() != null ? true : false;

                    for (boolean isCmMaterialFormItemOk :
                            new boolean[]{isMaterialOrderIdOk, isUserOk,
                                    isEnterDateOk, isDueDateOk}) {
                        if (isCmMaterialFormItemOk == false) {
                            snackbarInfoCode = 456;
                            return false;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            cmMaterialFormCursor.close();


            if(cursorCount > 0){
                CmMaterialRowCursor cmMaterialLineItemCursor = new CmMaterialRowSelection().cmOrderId(mCMOrderId).query(getContext());
                while (cmMaterialLineItemCursor.moveToNext()){
                    try {
                        boolean isMaterialRowOk = cmMaterialLineItemCursor.getMaterialRow() != null ? true : false;
                        boolean isMaterialIdOk = cmMaterialLineItemCursor.getMaterialId() != null ? true : false;
                        boolean isMaterialCountOk = cmMaterialLineItemCursor.getDueDate() != null ? true : false;
                        boolean isDueDateOk = cmMaterialLineItemCursor.getDueDate() != null ? true : false;


                        for (boolean isCmMaterialLineItemOk :
                                new boolean[]{isMaterialIdOk, isMaterialCountOk, isDueDateOk}) {
                            if (isCmMaterialLineItemOk == false) {
                                snackbarInfoCode = 567;
                                return false;
                            }
                        }

                        if(isMaterialRowOk == false){
                            snackbarInfoCode = 678;
                            return false;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                cmMaterialLineItemCursor.close();
            }

        }
        return true;
    }
}