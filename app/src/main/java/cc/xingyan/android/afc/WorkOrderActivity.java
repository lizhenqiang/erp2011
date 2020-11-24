/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import androidx.fragment.app.Fragment;

import cc.xingyan.android.afc.util.Action;

/**
 * Created by San on 9/23/15.
 */
public class WorkOrderActivity extends ThemeActivity {
    public static final String EXTRA_WORK_ORDER = "work_order_uri";

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            final Intent intent = getIntent();
            final Uri uri = intent.getParcelableExtra(EXTRA_WORK_ORDER);
            Bundle bundle = intent.getExtras();
            String info = bundle.getString("deviceID");
            Fragment fragment;
            if (Intent.ACTION_EDIT.equals(intent.getAction())) {
                fragment = WorkOrderEditorFragment.newInstance(uri,info);
            } else {
                fragment = WorkOrderViewerFragment.newInstance(uri);
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
        }
    }

    @Override public void finish() {
        final Action finish = new Action() {
            @Override public String name() {
                return "finish";
            }

            @Override public void proceed() {
                WorkOrderActivity.super.finish();
            }
        };

        for (Fragment f : getSupportFragmentManager().getFragments()) {
            if (f instanceof Action.Interceptor && ((Action.Interceptor) f).onInterceptAction(finish)) {
                return;
            }
        }

        finish.proceed();
    }

}
