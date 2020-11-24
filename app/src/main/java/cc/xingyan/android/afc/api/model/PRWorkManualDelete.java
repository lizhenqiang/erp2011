package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2017/6/22.
 */
public class PRWorkManualDelete {
    @Json(name = "Userid") String userid;
    @Json(name = "UserIMEI") String userIMEI;
    @Json(name = "UserLat") String userLat;
    @Json(name = "UserLon") String userLon;
    @Json(name = "UserPRNo") String reportNo;
    @Json(name = "UserSEQ") List<String> userSEQ;


    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
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

    public List<String> getUserSEQ() {
        return userSEQ;
    }

    public void setUserSEQ(List<String> userSEQ) {
        this.userSEQ = userSEQ;
    }
}
