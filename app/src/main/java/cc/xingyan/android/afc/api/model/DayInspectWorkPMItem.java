package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2017/8/11.
 */
public class DayInspectWorkPMItem {

    @Json(name = "WoNo") String woNo;
    @Json(name = "PmNo") String pmNo;
    @Json(name = "TestPointId") String testPointId;
    @Json(name = "ParameterCode") String parameterCode;
    @Json(name = "ParameterDesc") String parameterDesc;
    @Json(name = "ParameterType") String parameterType;
    @Json(name = "Unit") String unit;
    @Json(name = "MinValue") String minValue;
    @Json(name = "MaxValue") String maxValue;
    @Json(name = "StartValue") String startValue;
    @Json(name = "Interval") String interval;
    @Json(name = "LastValue") String lastValue;
    @Json(name = "PmgenValue") String pmgenValue;
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

    public String getParameterDesc() {
        return parameterDesc;
    }

    public void setParameterDesc(String parameterDesc) {
        this.parameterDesc = parameterDesc;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getStartValue() {
        return startValue;
    }

    public void setStartValue(String startValue) {
        this.startValue = startValue;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getLastValue() {
        return lastValue;
    }

    public void setLastValue(String lastValue) {
        this.lastValue = lastValue;
    }

    public String getPmgenValue() {
        return pmgenValue;
    }

    public void setPmgenValue(String pmgenValue) {
        this.pmgenValue = pmgenValue;
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
