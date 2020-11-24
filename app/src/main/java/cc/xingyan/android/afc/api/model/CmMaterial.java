package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.Date;

/**
 * Created by 1 on 2015/12/8.
 */
public class CmMaterial {
    @Json(name = "CMOrderId") String CMOrderId;
    @Json(name = "OrderNumber") String OrderNumber;
    @Json(name = "userid") String userid;
    @Json(name = "IntCustomerNo") String intCustomerNo;
    @Json(name = "EnterDate") Date EnterDate;
    @Json(name = "DueDate") Date DueDate;
    @Json(name = "Department") String Department;
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

    public String getuserid() {
        return userid;
    }

    public void setuserid(String userid) {
        this.userid = userid;
    }

    public String getIntCustomerNo() {
        return intCustomerNo;
    }

    public void setIntCustomerNo(String intCustomerNo) {
        this.intCustomerNo = intCustomerNo;
    }

    public Date getEnterDate() {
        return EnterDate;
    }

    public void setEnterDate(Date EnterDate) {
        this.EnterDate = EnterDate;
    }public Date getDueDate() {
        return DueDate;
    }

    public void setDueDate(Date DueDate) {
        this.DueDate = DueDate;
    }
    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public String getGuid() {
        return Guid;
    }

    public void setGuid(String Guid) {
        this.Guid = Guid;
    }
}
