package cc.xingyan.android.afc.api.model;

/**
 * Created by YuJiadeng on 2017/8/18.
 *
 */
public class DayInspectIfsWork {
    String WoNo;
    String PmNo;
    String TestPointId;
    String ParameterCode;
    String ParameterDesc;
    String ParameterType;
    String Unit;
    String MinValue;
    String MaxValue;
    String StartValue;
    String Interval;
    String LastValue;
    String PmgenValue;
    String ObjId;
    String ObjVersion;
    String PhysicCode;

    public String getInterval() {
        return Interval;
    }

    public void setInterval(String interval) {
        Interval = interval;
    }

    public String getLastValue() {
        return LastValue;
    }

    public void setLastValue(String lastValue) {
        LastValue = lastValue;
    }

    public String getMaxValue() {
        return MaxValue;
    }

    public void setMaxValue(String maxValue) {
        MaxValue = maxValue;
    }

    public String getMinValue() {
        return MinValue;
    }

    public void setMinValue(String minValue) {
        MinValue = minValue;
    }

    public String getParameterCode() {
        return ParameterCode;
    }

    public void setParameterCode(String parameterCode) {
        ParameterCode = parameterCode;
    }

    public String getParameterDesc() {
        return ParameterDesc;
    }

    public void setParameterDesc(String parameterDesc) {
        ParameterDesc = parameterDesc;
    }

    public String getParameterType() {
        return ParameterType;
    }

    public void setParameterType(String parameterType) {
        ParameterType = parameterType;
    }

    public String getPmgenValue() {
        return PmgenValue;
    }

    public void setPmgenValue(String pmgenValue) {
        PmgenValue = pmgenValue;
    }

    public String getPmNo() {
        return PmNo;
    }

    public void setPmNo(String pmNo) {
        PmNo = pmNo;
    }

    public String getStartValue() {
        return StartValue;
    }

    public void setStartValue(String startValue) {
        StartValue = startValue;
    }

    public String getTestPointId() {
        return TestPointId;
    }

    public void setTestPointId(String testPointId) {
        TestPointId = testPointId;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getWoNo() {
        return WoNo;
    }

    public void setWoNo(String woNo) {
        WoNo = woNo;
    }

    public String getObjId() {
        return ObjId;
    }

    public void setObjId(String objId) {
        ObjId = objId;
    }

    public String getObjVersion() {
        return ObjVersion;
    }

    public void setObjVersion(String objVersion) {
        ObjVersion = objVersion;
    }

    public String getPhysicCode() {
        return PhysicCode;
    }

    public void setPhysicCode(String physicCode) {
        PhysicCode = physicCode;
    }
}
