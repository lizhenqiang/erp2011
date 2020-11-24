package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2017/8/11.
 *
 * 废弃——2017年8月11日15:57:29
 */
public class DayInspectWorkPMItemInfo {
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

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
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

    public String getPmgenValue() {
        return pmgenValue;
    }

    public void setPmgenValue(String pmgenValue) {
        this.pmgenValue = pmgenValue;
    }

    public String getPmNo() {
        return pmNo;
    }

    public void setPmNo(String pmNo) {
        this.pmNo = pmNo;
    }

    public String getStartValue() {
        return startValue;
    }

    public void setStartValue(String startValue) {
        this.startValue = startValue;
    }

    public String getTestPointId() {
        return testPointId;
    }

    public void setTestPointId(String testPointId) {
        this.testPointId = testPointId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
