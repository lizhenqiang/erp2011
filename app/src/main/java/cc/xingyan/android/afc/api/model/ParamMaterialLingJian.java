package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2017/3/20.
 */
public class ParamMaterialLingJian {
    @Json(name = "Description") String Description;
    @Json(name = "TypeDescription") String TypeDescription;
    @Json(name = "PrimeCommodityCode") String PrimeCommodityCode;
    @Json(name = "PrimeCommodityName") String PrimeCommodityName;
    @Json(name = "SecondCommodityCode") String SecondCommodityCode;
    @Json(name = "SecondCommodityName") String SecondCommodityName;
    @Json(name = "UnitMeas") String UnitMeas;
    @Json(name = "SumInWarehouse") String SumInWarehouse;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrimeCommodityCode() {
        return PrimeCommodityCode;
    }

    public void setPrimeCommodityCode(String primeCommodityCode) {
        PrimeCommodityCode = primeCommodityCode;
    }

    public String getPrimeCommodityName() {
        return PrimeCommodityName;
    }

    public void setPrimeCommodityName(String primeCommodityName) {
        PrimeCommodityName = primeCommodityName;
    }

    public String getSecondCommodityCode() {
        return SecondCommodityCode;
    }

    public void setSecondCommodityCode(String secondCommodityCode) {
        SecondCommodityCode = secondCommodityCode;
    }

    public String getSecondCommodityName() {
        return SecondCommodityName;
    }

    public void setSecondCommodityName(String secondCommodityName) {
        SecondCommodityName = secondCommodityName;
    }

    public String getSumInWarehouse() {
        return SumInWarehouse;
    }

    public void setSumInWarehouse(String sumInWarehouse) {
        SumInWarehouse = sumInWarehouse;
    }

    public String getTypeDescription() {
        return TypeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        TypeDescription = typeDescription;
    }

    public String getUnitMeas() {
        return UnitMeas;
    }

    public void setUnitMeas(String unitMeas) {
        UnitMeas = unitMeas;
    }
}
