package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2017/8/21.
 */
public class DayInspectWorkReport {
    @Json(name = "WoNo") String woNo;
    @Json(name = "PhysicCode") String physicCode;
    @Json(name = "ObjId") String objId;
    @Json(name = "ObjVersion") String objVersion;
    @Json(name = "PmgenValue") String pmgenValue;
    @Json(name = "Sign") String sign;

    public String getWoNo() {
        return woNo;
    }

    public void setWoNo(String woNo) {
        this.woNo = woNo;
    }

    public String getPhysicCode() {
        return physicCode;
    }

    public void setPhysicCode(String physicCode) {
        this.physicCode = physicCode;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getObjVersion() {
        return objVersion;
    }

    public void setObjVersion(String objVersion) {
        this.objVersion = objVersion;
    }

    public String getPmgenValue() {
        return pmgenValue;
    }

    public void setPmgenValue(String pmgenValue) {
        this.pmgenValue = pmgenValue;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
