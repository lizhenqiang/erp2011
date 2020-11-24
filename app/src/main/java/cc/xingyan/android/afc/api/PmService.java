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

import cc.xingyan.android.afc.api.model.PMDeleteLists;
import cc.xingyan.android.afc.api.model.PmReports;
import cc.xingyan.android.afc.api.model.PmWork;
import cc.xingyan.android.afc.api.model.PmWorkExecuteMans;
import cc.xingyan.android.afc.api.model.PmWorkID;
import cc.xingyan.android.afc.api.model.PmWorkIDs;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by San on 9/29/15.
 */
public interface PmService {

    @GET("QueryPMifs/{userId}")
    Observable<List<PmWorkID>> listWorkGuids(@Path("userId") String userId);

    @POST("GetPMifs")
    Observable<List<PmWork>> listWorks(@Body PmWorkIDs ids);

    @POST("UploadPMifs")
    Observable<List<PmWorkID>> postReports(@Body PmReports pmReports);

    @POST("QueryPMifsDelete")
    Observable<List<PmWorkID>> postListDeletePM(@Body PMDeleteLists pmDeleteLists);

    @POST("ChangePMOrderExecute")
    Observable<List<PmWorkID>> postChangeExecuteManPMWorks(@Body PmWorkExecuteMans executeMans);
}
