package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2018/3/20.
 */
public class GoodsInfo {
    @Json(name = "materialId") String materialId;
    @Json(name = "fromContract") String fromContract;
    @Json(name = "batchNo") String batchNo;
    @Json(name = "locationNo") String locationNo;
    @Json(name = "userID") String userId;
    @Json(name = "userIMEI") String userIMEI;
    @Json(name = "userLat") String userLat;
    @Json(name = "userLon") String userLon;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getFromContract() {
        return fromContract;
    }

    public void setFromContract(String fromContract) {
        this.fromContract = fromContract;
    }

    public String getLocationNo() {
        return locationNo;
    }

    public void setLocationNo(String locationNo) {
        this.locationNo = locationNo;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIMEI() {
        return userIMEI;
    }

    public void setUserIMEI(String userIMEI) {
        this.userIMEI = userIMEI;
    }

    public String getUserLat() {
        return userLat;
    }

    public void setUserLat(String userLat) {
        this.userLat = userLat;
    }

    public String getUserLon() {
        return userLon;
    }

    public void setUserLon(String userLon) {
        this.userLon = userLon;
    }
}
