package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2017/3/22.
 */
public class ParamMaterialWarehouseInfo {
    @Json(name = "LotBatchNo") String lotBatchNo;
    @Json(name = "OnHandSum") String OnHandSum;
    @Json(name = "ReservedSum") String ReservedSum;
    @Json(name = "TransitSum") String TransitSum;
    @Json(name = "AvailableSum") String AvailableSum;

    public String getAvailableSum() {
        return AvailableSum;
    }

    public void setAvailableSum(String availableSum) {
        AvailableSum = availableSum;
    }

    public String getLotBatchNo() {
        return lotBatchNo;
    }

    public void setLotBatchNo(String lotBatchNo) {
        this.lotBatchNo = lotBatchNo;
    }

    public String getOnHandSum() {
        return OnHandSum;
    }

    public void setOnHandSum(String onHandSum) {
        OnHandSum = onHandSum;
    }

    public String getReservedSum() {
        return ReservedSum;
    }

    public void setReservedSum(String reservedSum) {
        ReservedSum = reservedSum;
    }

    public String getTransitSum() {
        return TransitSum;
    }

    public void setTransitSum(String transitSum) {
        TransitSum = transitSum;
    }
}
