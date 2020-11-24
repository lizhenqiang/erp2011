/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;

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
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.Set;

import javax.inject.Inject;

import cc.xingyan.android.afc.adapter.WorkOrderAdapter;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.WorkOrderService;
import cc.xingyan.android.afc.api.model.WorkOrderNo;
import cc.xingyan.android.afc.api.model.WorkOrders;
import cc.xingyan.android.afc.app.RecyclerFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.workorder.SyncStatus;
import cc.xingyan.android.afc.provider.workorder.WorkOrderContentValues;
import cc.xingyan.android.afc.provider.workorder.WorkOrderCursor;
import cc.xingyan.android.afc.provider.workorder.WorkOrderSelection;
import cc.xingyan.android.afc.util.LogUtil;
import cc.xingyan.android.afc.util.SyncHelper;
import cc.xingyan.android.afc.util.UploadAndDeleteActionModeCallback;
import cc.xingyan.android.afc.widget.SelectableAdapter;

/**
 * Created by San on 9/28/15.
 */
public class WorkOrderListFragment extends RecyclerFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final int KIND_ALL = 0;
    public static final int KIND_LOCAL = 1;
    public static final int KIND_SYNCHRONIZED = 2;

    private static final String ARG_KIND = "kind";

    @Inject
    Authenticator mAuthenticator;
    @Inject
    WorkOrderService workOrderService;

    private WorkOrderAdapter mAdapter;
    private int mKind;
    private ActionMode actionMode;
    private ActionMode.Callback actionModeCallback;

    public static Fragment newInstance(int kind) {
        WorkOrderListFragment fragment = new WorkOrderListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_KIND, kind);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);

        final Bundle args = getArguments();
        if (args != null) {
            mKind = args.getInt(ARG_KIND, KIND_ALL);
        } else {
            mKind = KIND_ALL;
        }
    }

    @Override
    protected void onSetupRecyclerView(RecyclerView recyclerView) {
        super.onSetupRecyclerView(recyclerView);

        switch (mKind) {
            default:
            case KIND_ALL:
                setEmptyText(getString(R.string.no_work_orders));
                break;
            case KIND_LOCAL:
                setEmptyText(getString(R.string.no_local_work_orders));
                break;
            case KIND_SYNCHRONIZED:
                setEmptyText(getString(R.string.no_uploaded_work_orders));
                break;
        }

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final int topPadding = getResources().getDimensionPixelOffset(R.dimen.divider_5dp);
        recyclerView.setPadding(0, topPadding, 0, 0);
        recyclerView.setClipToPadding(false);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).sizeResId(R.dimen.divider_5dp).colorResId(android.R.color.transparent).showLastDivider().build());
        setAdapter(mAdapter = new WorkOrderAdapter(LayoutInflater.from(getContext()), getContext(), mKind == KIND_LOCAL));

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
        actionModeCallback = new UploadAndDeleteActionModeCallback(menuInflater, mAdapter) {
            @Override
            protected void onDelete(Set<Long> selectedItemIds) {
                deleteWorkOrders(selectedItemIds);
                exitActionMode();
            }

            @Override
            protected void onUpload(Set<Long> itemIds) {
                uploadWorkOrders(itemIds);
            }
        };
    }

    private void deleteWorkOrders(Set<Long> selectedItemIds) {
        if (selectedItemIds.size() > 0) {
            final long[] ids = new long[selectedItemIds.size()];
            int i = 0;
            for (Long id : selectedItemIds) {
                ids[i++] = id;
            }
            new WorkOrderSelection().id(ids).delete(getContext());
            Snackbar.make(mRecyclerView, String.format("已删除%1$d条快速工单", ids.length), Snackbar.LENGTH_SHORT).show();
        }
    }

    private void uploadWorkOrders(final Set<Long> itemIds) {
        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage(getString(R.string.message_uploading));
        dialog.show();

        final long[] ids = new long[itemIds.size()];
        int i = 0;
        for (Long id : itemIds) {
            ids[i++] = id;
        }

        final String user = mAuthenticator.getAuthenticatedUserId();
        final WorkOrders workOrders = SyncHelper.collectWorkOrdersWithIds(getContext().getContentResolver(), ids);
        new WorkOrderContentValues()
                .putUploadPending(true)
                .update(getContext(), new WorkOrderSelection().id(ids));
        subscribe(workOrderService.postWorkOrders(workOrders), resp -> {
            dialog.dismiss();
            String string;
            if (resp.size() > 0) {
                for (WorkOrderNo no : resp) {
                    string = no.getWorkOrderNo();
                    if (string != null && string.matches("^[0-9]*$")) {
                        new WorkOrderContentValues()
                                .putNo(no.getWorkOrderNo())
                                .putUploadPending(false)
                                .putSyncStatus(SyncStatus.SYNCHRONIZED)
                                .update(getContext(), new WorkOrderSelection().guid(no.getGuid()));
                        Snackbar.make(mRecyclerView, "上传成功", Snackbar.LENGTH_SHORT).show();
                    } else {
                        Snackbar.make(mRecyclerView, "上传失败", Snackbar.LENGTH_SHORT)
                                .setAction(R.string.retry, v -> uploadWorkOrders(itemIds))
                                .show();
                    }
                }
                exitActionMode();
            } else {
                Snackbar.make(mRecyclerView, "上传失败", Snackbar.LENGTH_SHORT)
                        .setAction(R.string.retry, v -> uploadWorkOrders(itemIds))
                        .show();
            }
        }, e -> {
            LogUtil.debug(TAG, e.getMessage(), e);
            dialog.dismiss();
            Snackbar.make(mRecyclerView, "上传失败", Snackbar.LENGTH_SHORT)
                    .setAction(R.string.retry, v -> uploadWorkOrders(itemIds))
                    .show();
        });
    }

    public void exitActionMode() {
        if (actionMode != null) {
            actionMode.finish();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        final CursorLoader loader = new CursorLoader(getContext());
        final WorkOrderSelection sel = new WorkOrderSelection();
        switch (mKind) {
            default:
            case KIND_ALL:
                // No restriction
                break;
            case KIND_LOCAL:
                sel.syncStatus(SyncStatus.LOCAL);
                break;
            case KIND_SYNCHRONIZED:
                sel.syncStatus(SyncStatus.SYNCHRONIZED);
                break;
        }
        sel.and().userId(mAuthenticator.getAuthenticatedUserId())
                .and().deletePending(false);
        sel.orderById(true);
        loader.setUri(sel.uri());
        loader.setSelection(sel.sel());
        loader.setSelectionArgs(sel.args());
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(new WorkOrderCursor(data));
        setListShown(true, isResumed());
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
        setListShown(true, isResumed());
    }
}
