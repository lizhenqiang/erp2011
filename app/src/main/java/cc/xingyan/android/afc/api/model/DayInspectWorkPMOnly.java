package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2017/8/11.
 */
public class DayInspectWorkPMOnly {
    @Json(name = "PmNo") String pmNo;
    @Json(name = "WoNo") String woNo;
    @Json(name = "PhysicCode") String physicCode;

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

    public String getPhysicCode() {
        return physicCode;
    }

    public void setPhysicCode(String physicCode) {
        this.physicCode = physicCode;
    }
}
