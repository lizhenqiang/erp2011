package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.Date;

/**
 * Created by YuJiadeng on 2016/8/22.
 */
public class ReportReqData {
    @Json(name = "UserId") String userId;
    @Json(name = "UserPwd") String userPwd;
    @Json(name = "EncryptFlag") String encryptFlag;
    @Json(name = "DateStart")Date dateStart;
    @Json(name = "DateEnd") Date dateEnd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getEncryptFlag() {
        return encryptFlag;
    }

    public void setEncryptFlag(String encryptFlag) {
        this.encryptFlag = encryptFlag;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
}
