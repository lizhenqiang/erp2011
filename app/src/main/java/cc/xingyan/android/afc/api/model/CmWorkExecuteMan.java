package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by 1 on 2016/1/18.
 */
public class CmWorkExecuteMan {
    @Json(name = "OrderId") String OrderId;
    @Json(name = "ExecuteMan") String ExecuteMan;

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String OrderId) {
        this.OrderId = OrderId;
    }

    public String getExecuteMan() {
        return ExecuteMan;
    }

    public void setExecuteMan(String ExecuteMan) {
        this.ExecuteMan = ExecuteMan;
    }
}
