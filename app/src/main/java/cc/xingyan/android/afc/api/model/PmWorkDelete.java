package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2017/3/29.
 */
public class PmWorkDelete {
    @Json(name = "Userid") String userid;
    @Json(name = "UserIMEI") String userIMEI;
    @Json(name = "UserLat") String userLat;
    @Json(name = "UserLon") String userLon;
    @Json(name = "UserPM") List<PmWorkID> PmWorkIDs;

    public List<PmWorkID> getPmWorkIDs() {
        return PmWorkIDs;
    }

    public void setPmWorkIDs(List<PmWorkID> pmWorkIDs) {
        PmWorkIDs = pmWorkIDs;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserIMEI() {
        return userIMEI;
    }

    public void setUserIMEI(String userIMEI) {
        this.userIMEI = userIMEI;
    }

    public String getUserLat() {
        return userLat;
    }

    public void setUserLat(String userLat) {
        this.userLat = userLat;
    }

    public String getUserLon() {
        return userLon;
    }

    public void setUserLon(String userLon) {
        this.userLon = userLon;
    }
}
