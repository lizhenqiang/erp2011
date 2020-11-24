package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by 1 on 2015/12/28.
 */
public class CmMaterialRow {
    @Json(name = "CmOrderId") String CmOrderId;
    @Json(name = "OrderNumber") String OrderNumber;
    @Json(name = "Row") String Row;
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
    public String getRow() {
        return Row;
    }

    public void setRow(String Row) {
        this.Row = Row;
    }
}
