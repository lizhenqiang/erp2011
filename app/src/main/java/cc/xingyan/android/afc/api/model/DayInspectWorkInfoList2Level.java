package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2017/8/31.
 */
public class DayInspectWorkInfoList2Level {
    @Json(name = "UserInfo") UserInfo userInfo;
    @Json(name = "WoNos") List<DayInspectWork> diws;

    public List<DayInspectWork> getDiws() {
        return diws;
    }

    public void setDiws(List<DayInspectWork> diws) {
        this.diws = diws;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
