package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2018/5/17.
 */
public class DayInspectWorkObjInfoReturn {
    @Json(name = "WoNo") String woNo;
    @Json(name = "PmNo") String pmNo;
    @Json(name = "TestPointId") String testPointId;
    @Json(name = "ParameterCode") String parameterCode;
    @Json(name = "PhysicCode") String physicCode;
    @Json(name = "ObjId") String objId;
    @Json(name = "ObjVersion") String objVersion;

    public String getWoNo() {
        return woNo;
    }

    public void setWoNo(String woNo) {
        this.woNo = woNo;
    }

    public String getPmNo() {
        return pmNo;
    }

    public void setPmNo(String pmNo) {
        this.pmNo = pmNo;
    }

    public String getTestPointId() {
        return testPointId;
    }

    public void setTestPointId(String testPointId) {
        this.testPointId = testPointId;
    }

    public String getParameterCode() {
        return parameterCode;
    }

    public void setParameterCode(String parameterCode) {
        this.parameterCode = parameterCode;
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
}
