package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by 1 on 2016/1/26.
 */
public class PMDeleteList {
    @Json(name = "OrderId") String orderId;
    @Json(name = "executionerID") String executionerID;
    @Json(name = "Guid") String guid;

    public String getExecutionerID() {
        return executionerID;
    }

    public void setExecutionerID(String executionerID) {
        this.executionerID = executionerID;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
