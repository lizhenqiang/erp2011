package cc.xingyan.android.afc.api;

import java.util.List;

import cc.xingyan.android.afc.api.model.CMDeleteLists;
import cc.xingyan.android.afc.api.model.CMStatus;
import cc.xingyan.android.afc.api.model.CMStatusCheck;
import cc.xingyan.android.afc.api.model.CmConfirms;
import cc.xingyan.android.afc.api.model.CmMaterialRow;
import cc.xingyan.android.afc.api.model.CmMaterials;
import cc.xingyan.android.afc.api.model.CmMaterialsID;
import cc.xingyan.android.afc.api.model.CmMaterialsInfos;
import cc.xingyan.android.afc.api.model.CmReports;
import cc.xingyan.android.afc.api.model.CmSparePartID;
import cc.xingyan.android.afc.api.model.CmSparePartStatuss;
import cc.xingyan.android.afc.api.model.CmSpareParts;
import cc.xingyan.android.afc.api.model.CmWork;
import cc.xingyan.android.afc.api.model.CmWorkExecuteMans;
import cc.xingyan.android.afc.api.model.CmWorkID;
import cc.xingyan.android.afc.api.model.CmWorkIDs;
import cc.xingyan.android.afc.api.model.CmWorkTimes;
import cc.xingyan.android.afc.api.model.CmWorks;
import cc.xingyan.android.afc.api.model.ReportReqDatas;
import cc.xingyan.android.afc.api.model.ReportRespData;
import cc.xingyan.android.afc.api.model.UpLocationInfo;
import cc.xingyan.android.afc.api.model.UpLocationInfos;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by 1 on 2015/11/26.
 */
public interface CmService {
    @POST("NewCMR")
    Observable<List<CmWorkID>> listNewCMWorkIDs(@Body CmWorks cmrnews);

    @GET("QueryCMR/{userId}")
    Observable<List<CmWorkID>> listQueryCMWorkIDs(@Path("userId") String userId);

    @POST("GetCMR")
    Observable<List<CmWork>> postCMWorks(@Body CmWorkIDs ids);

    @POST("ConfirmCMR")
    Observable<List<CmWorkID>> postConfirmCMWorks(@Body CmConfirms icds);

    @POST("UploadArriveTimeAcceptTime")
    Observable<List<CmWorkID>> postUploadTimeCMWorks(@Body CmWorkTimes times);

    @POST("ChangeCMOrderExecute")
    Observable<List<CmWorkID>> postChangeExecuteManCMWorks(@Body CmWorkExecuteMans executeMans);

    @POST("UploadCMR")
    Observable<List<CmWorkID>> postCMWorkReports(@Body CmReports cmrs);

    @POST("NewMaterials")
    Observable<List<CmMaterialsID>> listMaterials(@Body CmMaterials ms);

    @POST("UploadMaterialInfos")
    Observable<List<CmMaterialRow>> postMaterials(@Body CmMaterialsInfos mis);

    @POST("NewSparePart")
    Observable<List<CmSparePartID>> listSpareParts(@Body CmSpareParts sps);

    @POST("ModifySparePartStatus")
    Observable<List<CmSparePartID>> postSpareParts(@Body CmSparePartStatuss spss);

    @POST("UploadLocation")
    Observable<List<UpLocationInfo>> upLocInfo(@Body UpLocationInfos upLocaInfos);

    @POST("GetReportInfo")
    Observable<List<ReportRespData>> reportInfo(@Body ReportReqDatas reportReqDatas);

    @POST("QueryCMifsDelete")
    Observable<List<CmWorkID>> postListDeleteCM(@Body CMDeleteLists cmDeleteLists);

    @POST("QueryCMifsStatus")
    Observable<List<CMStatus>> postListStatusCM(@Body CMStatusCheck cmStatusReturn);

}
