package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.Date;

/**
 * Created by YuJiadeng on 2016/10/31.
 */
public class PmWorkMaterialRows {
    @Json(name = "CMOrderId") String pMOrderId;
    @Json(name = "OrderNumber") String orderNumber;
    @Json(name = "OrderLineNo") String orderLineNo;
    @Json(name = "PartNo") String partNo;
    @Json(name = "PartDescription") String partDescription;
    @Json(name = "QuantityRequired") String quantityRequired;
    @Json(name = "DateRequired") Date dateRequired;

    public Date getDateRequired() {
        return dateRequired;
    }

    public void setDateRequired(Date dateRequired) {
        this.dateRequired = dateRequired;
    }

    public String getOrderLineNo() {
        return orderLineNo;
    }

    public void setOrderLineNo(String orderLineNo) {
        this.orderLineNo = orderLineNo;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public String getpMOrderId() {
        return pMOrderId;
    }

    public void setpMOrderId(String pMOrderId) {
        this.pMOrderId = pMOrderId;
    }

    public String getQuantityRequired() {
        return quantityRequired;
    }

    public void setQuantityRequired(String quantityRequired) {
        this.quantityRequired = quantityRequired;
    }
}
