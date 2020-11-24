package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by 1 on 2015/12/8.
 */
public class CmMaterialsID {
    @Json(name = "CmOrderId") String CmOrderId;
    @Json(name = "OrderNumber") String OrderNumber;
    @Json(name = "Status") String Status;
    public String getCmOrderId() {
        return CmOrderId;
    }

    public void setCmOrderId(String CmOrderId) {
        this.CmOrderId = CmOrderId;
    }
    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String OrderNumber) {
        this.OrderNumber = OrderNumber;
    }
    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
}
