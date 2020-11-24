package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2017/8/21.
 */
public class DayInspectWorkReportReturn {
    @Json(name = "WoNo") String woNo;
    @Json(name = "ObjId") String objId;
    @Json(name = "ObjVersion") String objVersion;
    @Json(name = "ObjMark") String objMark;

    public String getWoNo() {
        return woNo;
    }

    public void setWoNo(String woNo) {
        this.woNo = woNo;
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

    public String getObjMark() {
        return objMark;
    }

    public void setObjMark(String objMark) {
        this.objMark = objMark;
    }
}
