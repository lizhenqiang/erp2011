package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2016/3/22.
 */
public class UpLocationInfos {
    @Json(name = "locations")
    List<UpLocationInfo> upLocationInfoList;

    public List<UpLocationInfo> getUpLocationInfoList() {
        return upLocationInfoList;
    }

    public void setUpLocationInfoList(List<UpLocationInfo> upLocationInfoList) {
        this.upLocationInfoList = upLocationInfoList;
    }
}
