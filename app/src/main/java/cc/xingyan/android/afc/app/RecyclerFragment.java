/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc.app;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.Bind;
import cc.xingyan.android.afc.R;

/**
 * Created by San on 9/28/15.
 */
public class RecyclerFragment extends BaseFragment {

    @Bind(R.id.recycler) protected RecyclerView mRecyclerView;
    @Bind(R.id.empty) TextView mEmptyView;
    @Bind(R.id.progress) ProgressBar mProgressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onSetupRecyclerView(mRecyclerView);
        setListShown(false, false);
    }

    protected void onSetupRecyclerView(RecyclerView recyclerView) {
        // Let implementations decide how to use the recycler view.
    }

    protected void setAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    public void setEmptyText(CharSequence text) {
        mEmptyView.setText(text);
    }

    public void setListShown(boolean shown, boolean animate) {
        if (shown) {
            mProgressBar.setVisibility(View.GONE);
            final RecyclerView.Adapter adapter = mRecyclerView.getAdapter();
            View showingView;
            if (adapter != null && adapter.getItemCount() > 0) {
                showingView = mRecyclerView;
                mEmptyView.setVisibility(View.GONE);
            } else {
                showingView = mEmptyView;
                mRecyclerView.setVisibility(View.GONE);
            }
            if (animate && showingView.getVisibility() != View.VISIBLE) {
                showingView.setVisibility(View.VISIBLE);
                showingView.setAlpha(0);
                showingView.animate().alpha(1).start();
            }
        } else {
            final ProgressBar progressBar = mProgressBar;
            mEmptyView.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.GONE);
            if (animate && progressBar.getVisibility() != View.VISIBLE) {
                progressBar.setVisibility(View.VISIBLE);
                mProgressBar.setAlpha(0);
                mProgressBar.animate().alpha(1).start();
            }
        }
    }
}
