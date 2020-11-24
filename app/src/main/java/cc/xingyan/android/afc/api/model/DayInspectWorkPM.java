package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2017/8/11.
 */
public class DayInspectWorkPM {
    @Json(name = "WoNo") String woNo;
    @Json(name = "PmNo") String pmNo;
    @Json(name = "LogicName") String logicName;
    @Json(name = "PhysicName") String physicName;
    @Json(name = "PhysicCode") String physicCode;

    public String getLogicName() {
        return logicName;
    }

    public void setLogicName(String logicName) {
        this.logicName = logicName;
    }

    public String getPhysicCode() {
        return physicCode;
    }

    public void setPhysicCode(String physicCode) {
        this.physicCode = physicCode;
    }

    public String getPhysicName() {
        return physicName;
    }

    public void setPhysicName(String physicName) {
        this.physicName = physicName;
    }

    public String getPmNo() {
        return pmNo;
    }

    public void setPmNo(String pmNo) {
        this.pmNo = pmNo;
    }

    public String getWoNo() {
        return woNo;
    }

    public void setWoNo(String woNo) {
        this.woNo = woNo;
    }
}
