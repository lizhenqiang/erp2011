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

import cc.xingyan.android.afc.api.model.WorkOrderNo;
import cc.xingyan.android.afc.api.model.WorkOrders;
import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by San on 10/9/15.
 */
public interface WorkOrderService {

    @POST("UpdateCM")
    Observable<List<WorkOrderNo>> postWorkOrders(@Body WorkOrders workOrders);

}
