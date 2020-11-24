/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc.api.model.converter;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import cc.xingyan.android.afc.api.model.LoginResp;
import retrofit.Converter;

/**
 * Created by San on 10/10/15.
 */
public class KsonConverterFactory extends Converter.Factory {

    private Converter.Factory genericConvertFactory;

    public static KsonConverterFactory create(Converter.Factory genericConvertFactory) {
        final KsonConverterFactory factory = new KsonConverterFactory();
        factory.genericConvertFactory = genericConvertFactory;
        return factory;
    }

    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        if (type == LoginResp.class) {
            return new LoginRespConverter();
        } else {
            return genericConvertFactory.fromResponseBody(type, annotations);
        }
    }

    @Override
    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        return genericConvertFactory.toRequestBody(type, annotations);
    }
}
