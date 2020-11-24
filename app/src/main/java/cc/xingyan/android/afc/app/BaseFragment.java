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

import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import icepick.Icepick;
import rx.Subscriber;
import rx.observers.Subscribers;

/**
 * Created by San on 9/22/15.
 */
public class BaseFragment extends Fragment implements IO {

    private Subscriber mSubscriber = Subscribers.empty();
    private Toast toast;

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
    }

    @Override public Subscriber getSubscriber() {
        return mSubscriber;
    }

    @Override public void onDestroyView() {
        mSubscriber.unsubscribe();
        super.onDestroyView();
    }


    public void toastShort(String message) {
        toast(Toast.LENGTH_SHORT, message);
    }

    public void toastLong(String message) {
        toast(Toast.LENGTH_LONG, message);
    }

    private void toast(int duration, String message) {
        ensureToast();
        toast.setDuration(duration);
        toast.setText(message);
        toast.show();
    }

    private void ensureToast() {
        if (toast == null) {
            toast = Toast.makeText(getContext(), "", Toast.LENGTH_SHORT);
        }
    }

}
