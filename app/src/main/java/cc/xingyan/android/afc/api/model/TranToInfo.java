package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2018/2/26.
 *
 */
public class TranToInfo {
    @Json(name = "LocationNo") String locationNo;
    @Json(name = "LocationName")  String locationName;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationNo() {
        return locationNo;
    }

    public void setLocationNo(String locationNo) {
        this.locationNo = locationNo;
    }
}
