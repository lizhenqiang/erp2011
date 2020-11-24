package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.Date;

/**
 * Created by YuJiadeng on 2017/8/11.
 */
public class DayInspectWorkInfo {
    @Json(name = "WoNo") String woNo;
    @Json(name = "RounddefId") String rounddefId;
    @Json(name = "Descr") String descr;
    @Json(name = "RequiredStartDate") Date RequiredStartDate;
    @Json(name = "PlanStartDate") Date PlanStartDate;
    @Json(name = "PlanFinishDate") Date PlanFinishDate;
    @Json(name = "Signature") String Signature;
    @Json(name = "Sign") String Sign;
    @Json(name = "DeviceCount") String DeviceCount;

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getDeviceCount() {
        return DeviceCount;
    }

    public void setDeviceCount(String deviceCount) {
        DeviceCount = deviceCount;
    }

    public Date getPlanFinishDate() {
        return PlanFinishDate;
    }

    public void setPlanFinishDate(Date planFinishDate) {
        PlanFinishDate = planFinishDate;
    }

    public Date getPlanStartDate() {
        return PlanStartDate;
    }

    public void setPlanStartDate(Date planStartDate) {
        PlanStartDate = planStartDate;
    }

    public Date getRequiredStartDate() {
        return RequiredStartDate;
    }

    public void setRequiredStartDate(Date requiredStartDate) {
        RequiredStartDate = requiredStartDate;
    }

    public String getRounddefId() {
        return rounddefId;
    }

    public void setRounddefId(String rounddefId) {
        this.rounddefId = rounddefId;
    }

    public String getSign() {
        return Sign;
    }

    public void setSign(String sign) {
        Sign = sign;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public String getWoNo() {
        return woNo;
    }

    public void setWoNo(String woNo) {
        this.woNo = woNo;
    }
}
