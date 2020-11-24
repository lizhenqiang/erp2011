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

import cc.xingyan.android.afc.api.model.Device;
import cc.xingyan.android.afc.api.model.GetTrAllInfos;
import cc.xingyan.android.afc.api.model.GoodsInfos;
import cc.xingyan.android.afc.api.model.HeadAndLineInfos;
import cc.xingyan.android.afc.api.model.MEquipPhysic;
import cc.xingyan.android.afc.api.model.PRDeleteLists;
import cc.xingyan.android.afc.api.model.PRManualDeleteLists;
import cc.xingyan.android.afc.api.model.PRWorkID;
import cc.xingyan.android.afc.api.model.PanKuReportLists;
import cc.xingyan.android.afc.api.model.ParamCmMaterial;
import cc.xingyan.android.afc.api.model.ParamMaterialKunCun;
import cc.xingyan.android.afc.api.model.ParamMaterialLingJian;
import cc.xingyan.android.afc.api.model.ParamMaterialPanKuReport;
import cc.xingyan.android.afc.api.model.ParamMaterialPanKuReportReturn;
import cc.xingyan.android.afc.api.model.ParamValue;
import cc.xingyan.android.afc.api.model.PartYunshuGetAllInfoReturn;
import cc.xingyan.android.afc.api.model.PartYunshuGetGoodsInfoReturn;
import cc.xingyan.android.afc.api.model.PartYunshuGetHeadInfoReturn;
import cc.xingyan.android.afc.api.model.PartYunshuGetLineNoReturn;
import cc.xingyan.android.afc.api.model.PartYunshuGetNewReturn;
import cc.xingyan.android.afc.api.model.User;
import cc.xingyan.android.afc.api.model.UserInfos;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by San on 9/29/15.
 */
public interface DeviceService {

    @GET("GetEquip/{userId}/{versionTime}")
    Observable<List<Device>> listDevices(@Path("userId") String userId, @Path("versionTime") String lastSyncTime);

    @GET("GetParam/{versionTime}/{userId}")
    Observable<List<ParamValue>> listParamValues(@Path("versionTime") String lastSyncTime, @Path("userId") String userId);

    @GET("GetUser/{userId}/{versionTime}")
    Observable<List<User>> listUsers(@Path("userId") String userId, @Path("versionTime") String lastSyncTime);


    @GET("GetEquipPhysics/{userId}/{versionTime}")
    Observable<List<MEquipPhysic>> listPhysics(@Path("userId") String userId, @Path("versionTime") String lastSyncTime);

    @GET("GetMaterials/{userId}/{versionTime}")
    Observable<List<ParamCmMaterial>> listMaterials(@Path("userId") String userId, @Path("versionTime") String lastSyncTime);


    @GET("GetMaterialsOnline/{userId}")
    Observable<List<ParamCmMaterial>> listMaterials(@Path("userId") String userId);

    @GET("GetMaterialsByScan/{materialId}/{userId}/{imei}/{lat}/{lon}")
    Observable<List<ParamCmMaterial>> listMaterialsByScan(@Path("materialId") String materialId, @Path("userId") String userId,
                                                          @Path("imei") String imei, @Path("lat") String lat, @Path("lon") String lon);

    @GET("GetMaterialsLingJian/{materialId}/{batchNo}/{userId}/{imei}/{lat}/{lon}")
    Observable<List<ParamMaterialLingJian>> listMaterialsLingJian(@Path("materialId") String materialId, @Path("batchNo") String batchNo, @Path("userId") String userId,
                                                                  @Path("imei") String imei, @Path("lat") String lat, @Path("lon") String lon);

    @GET("GetMaterialsKunCun/{materialId}/{locationNo}/{userId}/{imei}/{lat}/{lon}")
    Observable<List<ParamMaterialKunCun>> listMaterialsKunCun(@Path("materialId") String materialId, @Path("locationNo") String locationNo, @Path("userId") String userId,
                                                              @Path("imei") String imei, @Path("lat") String lat, @Path("lon") String lon);

    @GET("GetMaterialsPanKuReport/{userId}/{imei}/{lat}/{lon}")
    Observable<List<ParamMaterialPanKuReport>> listMaterialsPanKuReport(@Path("userId") String userId, @Path("imei") String imei,
                                                                        @Path("lat") String lat, @Path("lon") String lon);

    @POST("UploadMaterialsPanKuReport")
    Observable<List<ParamMaterialPanKuReportReturn>> panKuReportListWorks(@Body PanKuReportLists ids);

    
    @POST("QueryPRDelete")
    Observable<List<PRWorkID>> postListDeletePR(@Body PRDeleteLists pdDeleteLists);

    @POST("ManualDeletePR")
    Observable<List<String>> postManualDeletePR(@Body PRManualDeleteLists pdManualDeleteLists);


    @POST("GetNewTransportInfo")
    Observable<List<PartYunshuGetNewReturn>> postGetNewTransportInfo(@Body UserInfos userInfoLists);

    @POST("GetTransportGoodsInfo")
    Observable<List<PartYunshuGetGoodsInfoReturn>> postGoodsInfo(@Body GoodsInfos goodsInfoLists);

    @POST("UpdateAndGetLineNo")
    Observable<List<PartYunshuGetLineNoReturn>> postUpdateAndGetLineNo(@Body HeadAndLineInfos headAndLineInfoLists);

    @POST("GetTransportHeadInfo")
    Observable<List<PartYunshuGetHeadInfoReturn>> postGetTransportHeadInfo(@Body UserInfos userInfoLists);

    @POST("GetTransportAllInfo")
    Observable<List<PartYunshuGetAllInfoReturn>> postGetTransportAllInfo(@Body GetTrAllInfos getTrAllInfos);
}
