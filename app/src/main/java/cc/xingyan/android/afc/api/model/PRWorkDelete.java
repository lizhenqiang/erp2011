package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2017/6/16.
 */
public class PRWorkDelete {
    @Json(name = "Userid") String userid;
    @Json(name = "UserIMEI") String userIMEI;
    @Json(name = "UserLat") String userLat;
    @Json(name = "UserLon") String userLon;
    @Json(name = "UserPR") String userPR;

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

    public String getUserPR() {
        return userPR;
    }

    public void setUserPR(String userPR) {
        this.userPR = userPR;
    }
}
