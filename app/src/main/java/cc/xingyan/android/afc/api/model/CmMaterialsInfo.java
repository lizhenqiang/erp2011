package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.Date;

/**
 * Created by 1 on 2015/12/8.
 */
public class CmMaterialsInfo {
    @Json(name = "CMOrderId") String CMOrderId;
    @Json(name = "OrderNumber") String OrderNumber;
    @Json(name = "OrderLineNo") String OrderLineNo;
    @Json(name = "PartNo") String PartNo;
    @Json(name = "PartDescription") String PartDescription;
    @Json(name = "QuantityRequired") String QuantityRequired;
    @Json(name = "DateRequired") Date DateRequired;
    @Json(name = "Guid") String Guid;
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
    public String getOrderLineNo() {
        return OrderLineNo;
    }

    public void setOrderLineNo(String OrderLineNo) {
        this.OrderLineNo = OrderLineNo;
    }
    public String getPartNo() {
        return PartNo;
    }

    public void setPartNo(String PartNo) {
        this.PartNo = PartNo;
    }
    public String getPartDescription() {
        return PartDescription;
    }

    public void setPartDescription(String PartDescription) {
        this.PartDescription = PartDescription;
    }
    public String getQuantityRequired() {
        return QuantityRequired;
    }

    public void setQuantityRequired(String QuantityRequired) {
        this.QuantityRequired = QuantityRequired;
    }
    public Date getDateRequired() {
        return DateRequired;
    }

    public void setDateRequired(Date DateRequired) {
        this.DateRequired = DateRequired;
    }

    public String getGuid() {
        return Guid;
    }

    public void setGuid(String Guid) {
        this.Guid = Guid;
    }

}
