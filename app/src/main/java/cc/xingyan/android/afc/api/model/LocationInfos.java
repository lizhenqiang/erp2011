package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2019/6/24.
 */
public class LocationInfos {
    @Json(name = "locInfos")
    List<LocationInfo> locationInfos;

    public List<LocationInfo> getLocationInfos() {
        return locationInfos;
    }

    public void setLocationInfos(List<LocationInfo> locationInfos) {
        this.locationInfos = locationInfos;
    }
}
