package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by 1 on 2015/12/8.
 */
public class CmWorkID {
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
        CmWorkID c = (CmWorkID) o;

        if(Guid.length() < 1){
            return OrderId.equals(c.OrderId);
        }
        return Guid.equals(c.Guid) || OrderId.equals(c.OrderId);
    }
}
