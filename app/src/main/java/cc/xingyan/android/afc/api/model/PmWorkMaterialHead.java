package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.Date;

/**
 * Created by YuJiadeng on 2016/10/31.
 */
public class PmWorkMaterialHead {
    @Json(name = "CMOrderId") String pMOrderId;
    @Json(name = "OrderNumber") String orderNumber;
    @Json(name = "userid") String userid;
    @Json(name = "EnterDate") Date enterDate;
    @Json(name = "Department") String department;
    @Json(name = "IntCustomerNo") String intCustomerNo;
    @Json(name = "DueDate") Date dueDate;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public String getIntCustomerNo() {
        return intCustomerNo;
    }

    public void setIntCustomerNo(String intCustomerNo) {
        this.intCustomerNo = intCustomerNo;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getpMOrderId() {
        return pMOrderId;
    }

    public void setpMOrderId(String pMOrderId) {
        this.pMOrderId = pMOrderId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
