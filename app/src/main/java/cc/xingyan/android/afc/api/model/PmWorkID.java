package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2016/10/30.
 */
public class PmWorkID {
    @Json(name = "Guid") String Guid;
    @Json(name = "OrderId") String OrderId;
    public String getGuid() {
        return Guid;
    }

    public void setGuid(String Guid) {
        this.Guid = Guid;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String OrderId) {
        this.OrderId = OrderId;
    }

    @Override
    public boolean equals(Object o) {
        PmWorkID c = (PmWorkID) o;

        if(Guid.length() < 1){
            return OrderId.equals(c.OrderId);
        }
        return Guid.equals(c.Guid) || OrderId.equals(c.OrderId);
    }
}
