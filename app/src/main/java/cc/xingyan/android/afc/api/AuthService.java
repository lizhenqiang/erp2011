/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc.api;

import java.util.List;

import cc.xingyan.android.afc.api.model.LoginResp;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by San on 9/22/15.
 */
public interface AuthService {

    @GET("Login/{username}/{password}")
    Observable<List<LoginResp>> login(@Path("username") String username, @Path("password") String password);

}
