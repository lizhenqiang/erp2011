package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2017/6/21.
 */
public class PmWorkExecuteMan {
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
