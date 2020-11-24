package cc.xingyan.android.afc.api;

import java.util.List;

import cc.xingyan.android.afc.api.model.LocationInfo;
import cc.xingyan.android.afc.api.model.LocationInfos;
import cc.xingyan.android.afc.api.model.User;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by San on 10/24/15.
 */
public interface UserService {

    @GET("GetUser/{userId}")
    Observable<List<User>> listUsers(@Path("userId") String userId);


    @POST("GetLocationInfo")
    Observable<List<LocationInfo>> getLocationInfo(@Body LocationInfos locationInfos);

}
