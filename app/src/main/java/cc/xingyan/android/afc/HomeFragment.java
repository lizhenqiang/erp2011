/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.base.AbstractSelection;
import cc.xingyan.android.afc.provider.cmwork.CmWorkSelection;
import cc.xingyan.android.afc.provider.cmwork.CmWorkStatus;
import cc.xingyan.android.afc.provider.diifsinfo.DiIfsStatus;
import cc.xingyan.android.afc.provider.diifsinfo.DiifsInfoSelection;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkSelection;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkStatus;
import cc.xingyan.android.afc.provider.workorder.SyncStatus;
import cc.xingyan.android.afc.provider.workorder.WorkOrderSelection;
import cc.xingyan.android.afc.widget.AppView;

/**
 * Created by San on 9/22/15.
 */
public class HomeFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int LD_LOCAL_WORK_ORDERS = 0;
    private static final int LD_LOCAL_PM_JOBS = 1;
    private static final int LD_LOCAL_DAY_INSPECT = 2;
    private static final int LD_LOCAL_CM_WORK = 3;

    @Bind(R.id.work_order)
    AppView mWorkOrderAppView;
    @Bind(R.id.cm)
    AppView mCmAppView;
    @Bind(R.id.pm)
    AppView mPmAppView;
    @Bind(R.id.di)
    AppView mDpAppView;
    @Bind(R.id.chart)
    AppView mChartAppView;
    @Bind(R.id.part)
    AppView mPartAppView;

    @Inject
    Authenticator mAuthenticator;

    private SharedPreferences loginPreferences;

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle(R.string.title_fragment_home);


        SharedPreferences chartPreferences = getActivity().getSharedPreferences("chart", Activity.MODE_PRIVATE);

        String userId = mAuthenticator.getAuthenticatedUserId();

        String chartStr = chartPreferences.getString(userId, "noinfo");
        if(!chartStr.equals("chart")){
            mChartAppView.setVisibility(View.GONE);
        }

        initBadges();
    }


    private void initBadges() {
        getLoaderManager().initLoader(LD_LOCAL_WORK_ORDERS, null, this);
        getLoaderManager().initLoader(LD_LOCAL_PM_JOBS, null, this);
        getLoaderManager().initLoader(LD_LOCAL_DAY_INSPECT, null, this);
        getLoaderManager().initLoader(LD_LOCAL_CM_WORK, null, this);
    }


    @OnClick(R.id.work_order) void onWorkOrderClick() {
        ((MainActivity) getActivity()).performNavigationAction(R.id.nav_workorder);
    }

    @OnClick(R.id.cm) void onCmClick() {
        ((MainActivity) getActivity()).performNavigationAction(R.id.nav_cm);
    }

    @OnClick(R.id.pm) void onPmClick() {
        ((MainActivity) getActivity()).performNavigationAction(R.id.nav_pm);
    }

    @OnClick(R.id.di) void onDpClick() {
        ((MainActivity) getActivity()).performNavigationAction(R.id.nav_di);
    }

    @OnClick(R.id.sync) void onSyncClick() {
        ((MainActivity) getActivity()).performNavigationAction(R.id.nav_sync);
    }
    @OnClick(R.id.chart) void onChartClick() {
        ((MainActivity) getActivity()).performNavigationAction(R.id.nav_chart);
    }
    @OnClick(R.id.part) void onPartClick() {
        ((MainActivity) getActivity()).performNavigationAction(R.id.nav_part);
    }

    @OnClick(R.id.panku_report_management) void onReportManagementClick() {
        startActivity(new Intent(getActivity(), Main4PankuReportManagementActivity.class));
    }

    @OnClick(R.id.yunshu) void onYunshuClick() {
        startActivity(new Intent(getActivity(), Main4YunshuActivity.class));
    }


    @Override public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        final String userId = mAuthenticator.getAuthenticatedUserId();
        final  String userWorkAreaId = mAuthenticator.getAuthenticatedUserWorkArea();

        final CursorLoader loader = new CursorLoader(getContext());
        AbstractSelection sel;
        switch (id) {
            default:
            case LD_LOCAL_WORK_ORDERS:
                sel = new WorkOrderSelection().userId(userId).and().syncStatusNot(SyncStatus.SYNCHRONIZED);
                break;
            case LD_LOCAL_PM_JOBS:
                sel = new PmifsWorkSelection().userId(userId).and().statusNot(PmifsWorkStatus.WORKDONEUPLOAD);
                break;
            case LD_LOCAL_DAY_INSPECT:
                sel = new DiifsInfoSelection().sign(userId).and().statusNot(DiIfsStatus.UPLOADED);
                break;
            case LD_LOCAL_CM_WORK:
                sel = new CmWorkSelection().userId(userId).and()
                        .status(CmWorkStatus.RELEASED , CmWorkStatus.FAULTREPORT, CmWorkStatus.NEW);
                break;
        }
        loader.setUri(sel.uri());
        loader.setSelection(sel.sel());
        loader.setSelectionArgs(sel.args());
        return loader;
    }

    @Override public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case LD_LOCAL_WORK_ORDERS:
                mWorkOrderAppView.setBadgeNumber(data.getCount());
                break;
            case LD_LOCAL_PM_JOBS:
                mPmAppView.setBadgeNumber(data.getCount());
                break;
            case LD_LOCAL_DAY_INSPECT:
                mDpAppView.setBadgeNumber(data.getCount());
                break;
            case LD_LOCAL_CM_WORK:
                mCmAppView.setBadgeNumber(data.getCount());
                break;
        }
    }

    @Override public void onLoaderReset(Loader<Cursor> loader) {
    }
}
