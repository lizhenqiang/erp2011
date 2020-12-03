package cc.xingyan.android.afc;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.LayoutInflater;
import android.view.MenuInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.common.collect.Collections2;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import cc.xingyan.android.afc.adapter.CmWorkAdapter;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.CmService;
import cc.xingyan.android.afc.api.model.CMStatus;
import cc.xingyan.android.afc.api.model.CMStatusCheck;
import cc.xingyan.android.afc.api.model.CmReports;
import cc.xingyan.android.afc.api.model.CmWorkStatusCheckItem;
import cc.xingyan.android.afc.api.model.CmWorkStatusCheckList;
import cc.xingyan.android.afc.app.RecyclerFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.cmwork.CmWorkColumns;
import cc.xingyan.android.afc.provider.cmwork.CmWorkContentValues;
import cc.xingyan.android.afc.provider.cmwork.CmWorkCursor;
import cc.xingyan.android.afc.provider.cmwork.CmWorkSelection;
import cc.xingyan.android.afc.provider.cmwork.CmWorkStatus;
import cc.xingyan.android.afc.util.LogUtil;
import cc.xingyan.android.afc.util.SyncHelper;
import cc.xingyan.android.afc.util.TService;
import cc.xingyan.android.afc.util.UploadActionModeCallback;
import cc.xingyan.android.afc.widget.SelectableAdapter;

/**
 * Created by 1 on 2015/11/26.
 */
public class CmWorkListFragment extends RecyclerFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final int KIND_NEW = 1 << 0;
    public static final int KIND_FAULT_REPORT = 1 << 1;
    public static final int KIND_RELEASED = 1 << 2;
    public static final int KIND_WORK_DONE = 1 << 3;
    public static final int KIND_WORK_DONE_UPLOAD = 1 << 4;
    public static final int KIND_FINISH = 1 << 5;


    private static final String ARG_KIND = "kind";

    @Inject
    Authenticator mAuthenticator;
    @Inject
    CmService cmService;

    private CmWorkAdapter mAdapter;
    private int mKind;
    private ActionMode actionMode;
    private ActionMode.Callback actionModeCallback;

    private List<CMStatus> cmStatusList;
    private Set<Long> itemIdSet;

    public static Fragment newInstance(int kind) {
        CmWorkListFragment fragment = new CmWorkListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_KIND, kind);
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);

        final Bundle args = getArguments();
        if (args != null) {
            mKind = args.getInt(ARG_KIND, KIND_RELEASED);
        } else {
            mKind = KIND_RELEASED;
        }
    }

    @Override protected void onSetupRecyclerView(RecyclerView recyclerView) {
        super.onSetupRecyclerView(recyclerView);

        setEmptyText(getString(R.string.no_pm_jobs));

        final LayoutInflater inflater = LayoutInflater.from(getContext());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter = new CmWorkAdapter(inflater, getContext(), mKind == KIND_WORK_DONE));
        final int topPadding = getResources().getDimensionPixelOffset(R.dimen.divider_5dp);
        recyclerView.setPadding(0, topPadding, 0, 0);
        recyclerView.setClipToPadding(false);
        recyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getContext()).sizeResId(R.dimen.divider_5dp)
                        .colorResId(android.R.color.transparent)
                        .showLastDivider()
                        .build()
        );

        mAdapter.setSelectModeListener(new SelectableAdapter.SelectModeListener() {
            @Override
            public boolean requestSelectMode() {
                mAdapter.setSelectMode(true);
                actionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(actionModeCallback);
                return true;
            }

            @Override
            public void onSelectedItemsChanged() {
                if (mAdapter.getSelectedItemCount() == 0) {
                    exitActionMode();
                }
            }
        });

        final MenuInflater menuInflater = ((AppCompatActivity) getActivity()).getMenuInflater();


        actionModeCallback = new UploadActionModeCallback(menuInflater, mAdapter) {
            @Override protected void onUpload(Set<Long> itemIds) {
                itemIdSet = itemIds;

                List<CmWorkStatusCheckItem> cmWorkStatusCheckItems = new ArrayList<>();
                Iterator<Long> iterator = itemIds.iterator();
                    while(iterator.hasNext()) {
                        long cmWorkIdIterator = iterator.next();


                        CmWorkCursor cmWorkCursor = new CmWorkSelection().id(cmWorkIdIterator).query(getContext());
                        while (cmWorkCursor.moveToNext()) {
                            try {
                                String cmWorkOrderId = cmWorkCursor.getOrderId();
                                CmWorkStatusCheckItem cmWorkStatusCheckItem = new CmWorkStatusCheckItem();
                                cmWorkStatusCheckItem.setOrderId(cmWorkOrderId);
                                cmWorkStatusCheckItems.add(cmWorkStatusCheckItem);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }finally {
                                cmWorkCursor.close();
                            }

                        }
                    }


                if(cmWorkStatusCheckItems.size() > 0){
                    CmWorkStatusCheckList cmWorkStatusCheckList = new CmWorkStatusCheckList();
                    String user = mAuthenticator.getAuthenticatedUserId();
                    String imei = TService.imei;
                    String lat = TService.lat;
                    String lon = TService.lon;

                    cmWorkStatusCheckList.setUserid(user);
                    cmWorkStatusCheckList.setUserIMEI(imei);
                    cmWorkStatusCheckList.setUserLat(lat);
                    cmWorkStatusCheckList.setUserLon(lon);
                    cmWorkStatusCheckList.setCmWorkStatusCheckItems(cmWorkStatusCheckItems);

                    List<CmWorkStatusCheckList> cmWorkStatusCheckLists = new ArrayList<>();
                    cmWorkStatusCheckLists.add(cmWorkStatusCheckList);

                    CMStatusCheck cmStatusCheck = new CMStatusCheck();
                    cmStatusCheck.setCmWorkStatusChecks(cmWorkStatusCheckLists);

                    ProgressDialog progressUpload = initProgressDialog("检查CM工单");
                    progressSwitch = progressUpload;
                    progressSwitch.show();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            cmService.postListStatusCM(cmStatusCheck).subscribe(resp -> {
                                cmStatusList = new ArrayList<>();
                                for (CMStatus cmStatus : resp) {
                                    if(cmStatus.getStatus().equals("FINISHED") ||
                                       cmStatus.getStatus().equals("CANCELED") ||
                                       cmStatus.getStatus().equals("REPORTED")){
                                        cmStatusList.add(cmStatus);
                                    }
                                }
                                Message msg = new Message();
                                msg.what = 123;
                                myHandler.sendMessage(msg);
                            }, e -> {
                                LogUtil.error(TAGINFO, "Failed to check cm work status", e);
                                progressSwitch.dismiss();
                            });
                        }
                    }).start();
                }
            }
        };
    }

    static ProgressDialog progressSwitch;
    private ProgressDialog initProgressDialog(String title){
        ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setCancelable(true);
        progress.setCanceledOnTouchOutside(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIcon(R.drawable.ic_settings_white_24dp);
        progress.setTitle(title);
        progress.setMessage("请稍后...");
        progress.setIndeterminate(false);

        return progress;
    }

    private void showCMStatusDialog(String string){
        new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_info_black_24dp)
                .setTitle("温馨提示")
                .setMessage(string)
                .setPositiveButton(R.string.ok, null)
                .setCancelable(false)
                .show();
    }

        Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 123){

                if(cmStatusList != null && cmStatusList.size() > 0){
                    StringBuffer stringBuffer = new StringBuffer();
                    int count = 0;
                    for(CMStatus cmStatus : cmStatusList){
                        count++;
                        stringBuffer.append(cmStatus.getOrderId())
                                .append(getCMStatus(cmStatus.getStatus()))
                                .append("\n");
                        if(count >= 5 ){
                            stringBuffer.append("...\n");
                            break;
                        }

                    }
                    stringBuffer.append("不能上传，数据同步后将清除这些工单！");
                    progressSwitch.dismiss();
                    showCMStatusDialog(stringBuffer.toString());
                }else {
                    progressSwitch.dismiss();
                    if(itemIdSet != null){
                        uploadCmWorkReports(itemIdSet);
                    }
                }
            }
        }
    };


    private String getCMStatus(String smStatus){
        switch (smStatus){
            case "FINISHED":
                return "已关闭";

            case "CANCELED":
                return "已取消";

            case "REPORTED":
                return "是报告";
        }
        return "";
    }

    private void uploadCmWorkReports(final Set<Long> itemIds) {
        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage(getString(R.string.message_uploading));
        dialog.show();

        final long[] ids = new long[itemIds.size()];
        int i = 0;
        for (Long id : itemIds) {
            ids[i++] = id;
        }

        final CmReports cmReports = SyncHelper.collectCmWorkReportsWithIds(getContext().getContentResolver(), ids);
        new CmWorkContentValues()
                .putUploadPending(true)
                .update(getContext().getContentResolver(), new CmWorkSelection().id(ids));

        subscribe(cmService.postCMWorkReports(cmReports), resp -> {
            dialog.dismiss();
            final List<String> list = new ArrayList(Collections2.transform(resp, g -> g.getOrderId()));
            new CmWorkContentValues()
                    .putStatus(CmWorkStatus.WORKDONEUPLOAD)
                    .putUploadPending(false)
                    .update(getContext().getContentResolver(), new CmWorkSelection().orderId(list.toArray(new String[list.size()])));
            Snackbar.make(mRecyclerView, "上传成功", Snackbar.LENGTH_SHORT).show();
            exitActionMode();
        },e -> {
            LogUtil.info("UploadCMR", e.getMessage() + " <<>> " + e);
            dialog.dismiss();
            Snackbar.make(mRecyclerView, "上传失败了", Snackbar.LENGTH_SHORT)
                    .setAction(R.string.retry, v -> uploadCmWorkReports(itemIds))
                    .show();
        });
    }

    void exitActionMode() {
        if (actionMode != null) {
            actionMode.finish();
        }
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        final CursorLoader loader = new CursorLoader(getContext());
        final CmWorkSelection sel = new CmWorkSelection();
        switch (mKind) {
            case KIND_NEW:
                sel.status(CmWorkStatus.NEW);
                break;
            case KIND_FAULT_REPORT:
                sel.status(CmWorkStatus.FAULTREPORT);
                break;
            case KIND_RELEASED:
                sel.status(CmWorkStatus.RELEASED);
                break;
            case KIND_WORK_DONE:
                sel.status(CmWorkStatus.WORKDONE);
                break;
            case KIND_WORK_DONE_UPLOAD:
                sel.status(CmWorkStatus.WORKDONEUPLOAD);
                break;
            case KIND_FINISH:
                sel.status(CmWorkStatus.FINISH);
                break;
        }
        sel.and().userId(mAuthenticator.getAuthenticatedUserId());
        loader.setUri(sel.uri());
        loader.setSelection(sel.sel());
        loader.setSelectionArgs(sel.args());
        loader.setSortOrder(CmWorkColumns.ORDER_ID);
        return loader;
    }

    @Override public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(new CmWorkCursor(data));
        setListShown(true, isResumed());
    }

    @Override public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
