package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.Date;

/**
 * Created by 1 on 2016/1/18.
 */
public class CmWorkTime {
    @Json(name = "OrderId") String OrderId;
    @Json(name = "AcceptTime") Date AcceptTime;
    @Json(name = "ArriveTime") Date ArriveTime;

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String OrderId) {
        this.OrderId = OrderId;
    }

    public Date getAcceptTime() {
        return AcceptTime;
    }

    public void setAcceptTime(Date AcceptTime) {
        this.AcceptTime = AcceptTime;
    }
    public Date getArriveTime() {
        return ArriveTime;
    }

    public void setArriveTime(Date ArriveTime) {
        this.ArriveTime = ArriveTime;
    }
}

