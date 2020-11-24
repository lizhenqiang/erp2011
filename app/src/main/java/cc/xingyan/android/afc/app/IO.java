/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc.app;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by San on 10/11/15.
 */
public interface IO {
    String TAG = "IO";
    String TAGINFO = "appinfo";

    Subscriber getSubscriber();

    default <T> void subscribe(Observable<T> observable, Action1<T> onNext, Action1<Throwable> onError) {
        getSubscriber().add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError));
    }


}
