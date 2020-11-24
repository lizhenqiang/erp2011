package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.Date;

/**
 * Created by 1 on 2015/12/8.
 */
public class CmWork {
    @Json(name = "userid") String userid;
    @Json(name = "Guid") String Guid;
    @Json(name = "OrderId") String OrderId;
    @Json(name = "Priority")    String Priority;
    @Json(name = "EquitCode") String EquitCode;
    @Json(name = "EquitName") String EquitName;
    @Json(name = "SymptomCode") String SymptomCode;
    @Json(name = "FaultNote") String FaultNote;
    @Json(name = "DiscovererTypeCode")    String DiscovererTypeCode;
    @Json(name = "Instruct")    String Instruct;
    @Json(name = "ReportedBy")    String ReportedBy;
    @Json(name = "PrepareMan")    String PrepareMan;
    @Json(name = "DisposePerson")    String DisposePerson;
    @Json(name = "WorkArea")    String WorkArea;
    @Json(name = "FaultStartTime") Date FaultStartTime;
    @Json(name = "ApplyStartTime")    Date ApplyStartTime;
    @Json(name = "ApplyEndTime")    Date ApplyEndTime;
    @Json(name = "RecvTime") Date OrderRecvTime;
    @Json(name = "ArriveTime") Date ArriveTime;
    @Json(name = "SaveDate") Date SaveDate;
    @Json(name = "OrderStatus") String OrderStatus;
    @Json(name = "MaterialInfos")
    CmMaterialDownloadInfos MaterialInfos;
    public String getuserid() {
        return userid;
    }

    public void setuserid(String userid) {
        this.userid = userid;
    }
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

    public String getEquitCode() {
        return EquitCode;
    }

    public void setEquitCode(String EquitCode) {
        this.EquitCode = EquitCode;
    }

    public String getEquitNamee() {
        return EquitName;
    }

    public void setEquitName(String EquitName) {
        this.EquitName = EquitName;
    }

    public String getFaultNote() {
        return FaultNote;
    }

    public void setFaultNote(String FaultNote) {
        this.FaultNote = FaultNote;
    }

    public String getSymptomCode() {
        return SymptomCode;
    }

    public void setSymptomCode(String SymptomCode) {
        this.SymptomCode = SymptomCode;
    }
    public String getDiscovererTypeCode() {
        return DiscovererTypeCode;
    }

    public void setDiscovererTypeCode(String DiscovererTypeCode) {
        this.DiscovererTypeCode = DiscovererTypeCode;
    }
    public String getReportedBy() {
        return ReportedBy;
    }

    public void setReportedBy(String ReportedBy) {
        this.ReportedBy = ReportedBy;
    }
    public String getWorkArea() {
        return WorkArea;
    }

    public void setWorkArea(String WorkArea) {
        this.WorkArea = WorkArea;
    }
    public String getInstruct() {
        return Instruct;
    }

    public void setInstruct(String Instruct) {
        this.Instruct = Instruct;
    }
    public String getPrepareMan() {
        return PrepareMan;
    }

    public void setPrepareMan(String PrepareMan) {
        this.PrepareMan = PrepareMan;
    }
    public String getDisposePerson() {
        return DisposePerson;
    }

    public void setDisposePerson(String DisposePerson) {
        this.DisposePerson = DisposePerson;
    }
    public String getPriority() {
        return Priority;
    }

    public void setPriority(String Priority) {
        this.Priority = Priority;
    }


    public Date getFaultStartTime() {
        return FaultStartTime;
    }

    public void setFaultStartTime(Date FaultStartTime) {
        this.FaultStartTime = FaultStartTime;
    }


    public Date getApplyStartTime() {
        return ApplyStartTime;
    }

    public void setApplyStartTime(Date ApplyStartTime) {
        this.ApplyStartTime = ApplyStartTime;
    }
    public Date getApplyEndTime() {
        return ApplyEndTime;
    }

    public void setApplyEndTime(Date ApplyEndTime) {
        this.ApplyEndTime = ApplyEndTime;
    }

    public Date getOrderRecvTime() {
        return OrderRecvTime;
    }

    public void setOrderRecvTime(Date OrderRecvTime) {
        this.OrderRecvTime = OrderRecvTime;
    }

    public Date getArriveTime() {
        return ArriveTime;
    }

    public void setArriveTime(Date ArriveTime) {
        this.ArriveTime = ArriveTime;
    }

    public Date getSaveDate() {
        return SaveDate;
    }

    public void setSaveDate(Date SaveDate) {
        this.SaveDate = SaveDate;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String OrderStatus) {
        this.OrderStatus = OrderStatus;
    }
    public CmMaterialDownloadInfos getMaterialInfos() {
        return MaterialInfos;
    }

    public void setMaterialInfos(CmMaterialDownloadInfos MaterialInfos) {
        this.MaterialInfos = MaterialInfos;
    }
}
