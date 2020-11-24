package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2017/5/4.
 * 盘点报告要素(上传用到)
 */
public class ParamMaterialPanKuReportUpload {
    @Json(name = "ReportNo") String reportNo;
    @Json(name = "LineNo") String lineNo;
    @Json(name = "ActualAmount") String actualAmount;
    @Json(name = "PandianTime") long pandianTime;

    @Json(name = "userId") String userId;
    @Json(name = "IMEI") String IMEI;
    @Json(name = "lat") String lat;
    @Json(name = "lon") String lon;

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public long getPandianTime() {
        return pandianTime;
    }

    public void setPandianTime(long pandianTime) {
        this.pandianTime = pandianTime;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
