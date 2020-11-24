package cc.xingyan.android.afc.api;

import java.util.List;

import cc.xingyan.android.afc.api.model.DayInspectWork;
import cc.xingyan.android.afc.api.model.DayInspectWorkInfo;
import cc.xingyan.android.afc.api.model.DayInspectWorkInfoList1Level;
import cc.xingyan.android.afc.api.model.DayInspectWorkObjInfoReturn;
import cc.xingyan.android.afc.api.model.DayInspectWorkPM;
import cc.xingyan.android.afc.api.model.DayInspectWorkPMItem;
import cc.xingyan.android.afc.api.model.DayInspectWorkPMOnlys;
import cc.xingyan.android.afc.api.model.DayInspectWorkReportReturn;
import cc.xingyan.android.afc.api.model.DayInspectWorkReports;
import cc.xingyan.android.afc.api.model.DayInspectWorks;
import cc.xingyan.android.afc.api.model.PmWorkExecuteMans;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by 1 on 2015/10/27.
 */
public interface DayInspectService {


    @GET("QueryDayInspectWorkList/{userid}")Observable<List<DayInspectWork>> queryDayInspectWorkList(@Path("userid") String userId);

    @POST("GetDayInspectWorkInfoList")
    Observable<List<DayInspectWorkInfo>> getDayInspectWorkList(@Body DayInspectWorkInfoList1Level getdi);

    @POST("GetDayInspectWorkPMList")
    Observable<List<DayInspectWorkPM>> getDayInspectWorkPMList(@Body DayInspectWorks diws);

    @POST("GetDayInspectWorkPMItemList")
    Observable<List<DayInspectWorkPMItem>> getDayInspectWorkPMItemList(@Body DayInspectWorkPMOnlys diwPMs);

    @POST("UploadDayInspectWorkList")
    Observable<List<DayInspectWorkReportReturn>> uploadDayInspectWorkList(@Body DayInspectWorkReports dayInspectWorkReports);

    @POST("GetDayInspectWorkObjInfo")
    Observable<List<DayInspectWorkObjInfoReturn>> getDayInspectWorkObjInfo(@Body DayInspectWorkPMOnlys diwPMs);

    @POST("ChangeDIWorkExecute")
    Observable<List<DayInspectWorkReportReturn>> postChangeExecuteManDIWorks(@Body PmWorkExecuteMans executeMans);


}
