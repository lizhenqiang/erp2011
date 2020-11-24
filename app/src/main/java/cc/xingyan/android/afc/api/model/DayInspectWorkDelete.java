package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2018/4/17.
 */
public class DayInspectWorkDelete {
    @Json(name = "Userid") String userid;
    @Json(name = "UserIMEI") String userIMEI;
    @Json(name = "UserLat") String userLat;
    @Json(name = "UserLon") String userLon;
    @Json(name = "UserDI") List<DayInspectWork> dayInspectWorks;

    public List<DayInspectWork> getDayInspectWorks() {
        return dayInspectWorks;
    }

    public void setDayInspectWorks(List<DayInspectWork> dayInspectWorks) {
        this.dayInspectWorks = dayInspectWorks;
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
