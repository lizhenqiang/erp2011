/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc.api.model.converter;

import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import cc.xingyan.android.afc.api.model.LoginResp;
import retrofit.Converter;

/**
 * Created by San on 10/10/15.
 */
public class LoginRespConverter implements Converter<ResponseBody, LoginResp> {

    @Override public LoginResp convert(ResponseBody value) throws IOException {
        // TODO: Parse userId from response body
        final LoginResp resp = new LoginResp();
        resp.setUserId("100");
        return resp;
    }

}
