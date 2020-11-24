package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by 1 on 2015/12/8.
 */
public class CmSparePartStatus {
    @Json(name = "CMOrderId") String CMOrderId;
    @Json(name = "OrderNumber") String OrderNumber;
    @Json(name = "SparePartStatus") String SparePartStatus;
    public String getCMOrderId() {
        return CMOrderId;
    }

    public void setCMOrderId(String CMOrderId) {
        this.CMOrderId = CMOrderId;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String OrderNumber) {
        this.OrderNumber = OrderNumber;
    }

    public String getSparePartStatus() {
        return SparePartStatus;
    }

    public void setSparePartStatus(String SparePartStatus) {
        this.SparePartStatus = SparePartStatus;
    }
}
