/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc.widget;

import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import butterknife.ButterKnife;

/**
 * Created by San on 9/23/15.
 */
public abstract class CursorRecyclerAdapter<C extends Cursor, VH extends CursorRecyclerAdapter.ViewHolder<C>> extends RecyclerView.Adapter<VH> {

    private final LayoutInflater mLayoutInflater;
    protected C mCursor;
    private int mIdColumnIndex;

    protected CursorRecyclerAdapter(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
    }

    public void swapCursor(C c) {
        this.mCursor = c;
        if (c != null) {
            mIdColumnIndex = c.getColumnIndex(BaseColumns._ID);
        }
        notifyDataSetChanged();
    }

    @Override public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    public final C getItem(int position) {
        mCursor.moveToPosition(position);
        return mCursor;
    }

    @Override public long getItemId(int position) {
        mCursor.moveToPosition(position);
        return mCursor.getLong(mIdColumnIndex);
    }

    @Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateViewHolder(mLayoutInflater, parent, viewType);
    }

    protected abstract VH onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType);

    @Override public void onBindViewHolder(VH holder, int position) {
        onBindViewHolder(holder, getItem(position), position);
    }

    protected void onBindViewHolder(VH holder, C c, int position) {
        holder.setItem(c);
    }

    public static abstract class ViewHolder<C> extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            onViewInject();
        }

        protected void onViewInject() {
            ButterKnife.bind(this, itemView);
        }

        public Context getContext() {
            return itemView.getContext();
        }

        public abstract void setItem(C c);
    }
}
