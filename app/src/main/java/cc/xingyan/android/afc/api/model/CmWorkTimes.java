package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by 1 on 2016/1/18.
 */
public class CmWorkTimes {
    @Json(name = "t")
    List<CmWorkTime> cmWorkTimes;

    public List<CmWorkTime> getcmWorkTimes() {
        return cmWorkTimes;
    }

    public void setcmWorkTimes(List<CmWorkTime> cmWorkTimes) {
        this.cmWorkTimes = cmWorkTimes;
    }

}