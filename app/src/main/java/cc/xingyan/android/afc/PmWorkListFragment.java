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
import com.google.common.collect.Collections2;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import cc.xingyan.android.afc.adapter.PmWorkAdapter;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.PmService;
import cc.xingyan.android.afc.api.model.PmReports;
import cc.xingyan.android.afc.app.RecyclerFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkColumns;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkContentValues;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkCursor;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkSelection;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkStatus;
import cc.xingyan.android.afc.util.LogUtil;
import cc.xingyan.android.afc.util.SyncHelper;
import cc.xingyan.android.afc.util.UploadActionModeCallback;
import cc.xingyan.android.afc.widget.SelectableAdapter;

/**
 * Created by San on 10/13/15.
 */
public class PmWorkListFragment extends RecyclerFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final int KIND_DOWNLOAD = 1 << 0;
    public static final int KIND_RELEASED = 1 << 1;
    public static final int KIND_DONE = 1 << 2;
    public static final int KIND_UPLOADED = 1 << 3;


    private static final String ARG_KIND = "kind";

    @Inject
    Authenticator mAuthenticator;
    @Inject
    PmService pmService;

    private PmWorkAdapter mAdapter;
    private int mKind;
    private ActionMode actionMode;
    private ActionMode.Callback actionModeCallback;

    public static Fragment newInstance(int kind) {
        PmWorkListFragment fragment = new PmWorkListFragment();
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
            mKind = args.getInt(ARG_KIND, KIND_DOWNLOAD);
        } else {
            mKind = KIND_DOWNLOAD;
        }
    }

    @Override protected void onSetupRecyclerView(RecyclerView recyclerView) {
        super.onSetupRecyclerView(recyclerView);

        setEmptyText(getString(R.string.no_pm_jobs));

        final LayoutInflater inflater = LayoutInflater.from(getContext());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter = new PmWorkAdapter(inflater,  getContext(), mKind == KIND_DONE));
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
            @Override public boolean requestSelectMode() {
                mAdapter.setSelectMode(true);
                actionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(actionModeCallback);
                return true;
            }

            @Override public void onSelectedItemsChanged() {
                if (mAdapter.getSelectedItemCount() == 0) {
                    exitActionMode();
                }
            }
        });

        final MenuInflater menuInflater = ((AppCompatActivity) getActivity()).getMenuInflater();
        actionModeCallback = new UploadActionModeCallback(menuInflater, mAdapter) {
            @Override protected void onUpload(Set<Long> itemIds) {
                uploadPmWorkReports(itemIds);
            }
        };
    }

    private void uploadPmWorkReports(final Set<Long> itemIds) {
        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage(getString(R.string.message_uploading));
        dialog.show();

        final long[] ids = new long[itemIds.size()];
        int i = 0;
        for (Long id : itemIds) {
            ids[i++] = id;
        }

        final PmReports pmReports = SyncHelper.collectPmReportsWithIds(getContext().getContentResolver(), ids);
        new PmifsWorkContentValues()
                .putUploadPending(true)
                .update(getContext().getContentResolver(), new PmifsWorkSelection().id(ids));
        subscribe(pmService.postReports(pmReports), resp -> {
            dialog.dismiss();
            final List<String> list = new ArrayList(Collections2.transform(resp, g -> g.getOrderId()));
            new PmifsWorkContentValues()
                    .putStatus(PmifsWorkStatus.WORKDONEUPLOAD)
                    .putUploadPending(false)
                    .update(getContext().getContentResolver(), new PmifsWorkSelection().orderId(list.toArray(new String[list.size()])));
            Snackbar.make(mRecyclerView, "上传成功", Snackbar.LENGTH_SHORT).show();
            exitActionMode();
        }, e -> {
            LogUtil.debug(TAG, e.getMessage(), e);
            dialog.dismiss();
            Snackbar.make(mRecyclerView, "上传失败了", Snackbar.LENGTH_SHORT)
                    .setAction(R.string.retry, v -> uploadPmWorkReports(itemIds))
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
        final PmifsWorkSelection sel = new PmifsWorkSelection();
        switch (mKind) {
            case KIND_DOWNLOAD:
                sel.status(PmifsWorkStatus.NEW);
                break;
            case KIND_RELEASED:
                sel.status(PmifsWorkStatus.RELEASED);
                break;
            case KIND_DONE:
                sel.status(PmifsWorkStatus.WORKDONE);
                break;
            case KIND_UPLOADED:
                sel.status(PmifsWorkStatus.WORKDONEUPLOAD);
                break;
        }
        sel.and().userId(mAuthenticator.getAuthenticatedUserId());
        loader.setUri(sel.uri());
        loader.setSelection(sel.sel());
        loader.setSelectionArgs(sel.args());
        loader.setSortOrder(PmifsWorkColumns.OPERATION_END_TIME);
        return loader;
    }

    @Override public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(new PmifsWorkCursor(data));
        setListShown(true, isResumed());
    }

    @Override public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
