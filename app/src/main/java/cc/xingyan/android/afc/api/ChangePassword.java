package cc.xingyan.android.afc.api;

/**
 * Created by 1 on 2015/10/22.
 */


import java.util.List;

import cc.xingyan.android.afc.api.model.ChangePwdResp;
import cc.xingyan.android.afc.api.model.NewVersion;
import cc.xingyan.android.afc.api.model.NewVersions;
import cc.xingyan.android.afc.api.model.ServerTime;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

public interface ChangePassword {
    @GET("changePassword/{UName}/{UPassWordOld}/{UPassWordNew}")
    Observable<List<ChangePwdResp>> changPassword(@Path("UName") String UName, @Path("UPassWordOld") String UPassWordOld, @Path("UPassWordNew") String UPassWordNew);

    @POST("GetNewVersion")
    Observable<List<NewVersion>> getNewVersion(@Body NewVersions newVersions);

    @GET("GetSerTime/{userId}/{imei}/{lat}/{lon}")
    Observable<List<ServerTime>> getServerTime(@Path("userId") String userId, @Path("imei") String imei,
                                               @Path("lat") String lat, @Path("lon") String lon);
}
