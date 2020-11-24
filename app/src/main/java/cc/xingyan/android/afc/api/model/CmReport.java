package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.Date;

/**
 * Created by 1 on 2015/12/8.
 */
public class CmReport {
    @Json(name = "OrderId") String OrderId;
    @Json(name = "InternalCustomerNo") String intCustomerNo;
    @Json(name = "FaultGradeCode") String FaultGradeCode;
    @Json(name = "FaultTypeCode") String FaultTypeCode;
    @Json(name = "CauseCode") String CauseCode;
    @Json(name = "WorkDetailsCode") String WorkDetailsCode;
    @Json(name = "WorkDone")    String WorkDone;
    @Json(name = "commentsCause")    String commentsCause;
    @Json(name = "commentsWork")    String commentsWork;
    @Json(name = "DisposePerson")    String DisposePerson;
    @Json(name = "WorkDes")    String WorkDes;
    @Json(name = "PlanStartDate")    Date PlanStartDate;
    @Json(name = "PlanFinishDate")    Date PlanFinishDate;
    @Json(name = "RealStartDate")    Date RealStartDate;
    @Json(name = "RealFinishDate")    Date RealFinishDate;
    @Json(name = "SaveDate")    Date SaveDate;
    @Json(name = "SymptomCode")    String symptomCode;
    @Json(name = "FormFlag")    String formFlag;
    @Json(name = "EquipFault")    String equipFault;

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String OrderId) {
        this.OrderId = OrderId;
    }

    public String getIntCustomerNo() {
        return intCustomerNo;
    }

    public void setIntCustomerNo(String intCustomerNo) {
        this.intCustomerNo = intCustomerNo;
    }

    public String getWorkDes() {
        return WorkDes;
    }

    public void setWorkDes(String WorkDes) {
        this.WorkDes = WorkDes;
    }
    public Date getPlanStartDate() {
        return PlanStartDate;
    }

    public void setPlanStartDate(Date PlanStartDate) {
        this.PlanStartDate = PlanStartDate;
    }
    public Date getPlanFinishDate() {
        return PlanFinishDate;
    }

    public void setPlanFinishDate(Date PlanFinishDate) {
        this.PlanFinishDate = PlanFinishDate;
    }

    public String getFaultGradeCode() {
        return FaultGradeCode;
    }

    public void setFaultGradeCode(String FaultGradeCode) {
        this.FaultGradeCode = FaultGradeCode;
    }

    public String getFaultTypeCode() {
        return FaultTypeCode;
    }

    public void setFaultTypeCode(String FaultTypeCode) {
        this.FaultTypeCode = FaultTypeCode;
    }

    public String getCauseCode() {
        return CauseCode;
    }

    public void setCauseCode(String CauseCode) {
        this.CauseCode = CauseCode;
    }

    public String getWorkDetailsCode() {
        return WorkDetailsCode;
    }

    public void setWorkDetailsCode(String WorkDetailsCode) {
        this.WorkDetailsCode = WorkDetailsCode;
    }

    public String getWorkDone() {
        return WorkDone;
    }

    public void setWorkDone(String WorkDone) {
        this.WorkDone = WorkDone;
    }

    public String getcommentsCause() {
        return commentsCause;
    }

    public void setcommentsCause(String commentsCause) {
        this.commentsCause = commentsCause;
    }

    public String getcommentsWork() {
        return commentsWork;
    }

    public void setcommentsWork(String commentsWork) {
        this.commentsWork = commentsWork;
    }

    public String getDisposePerson() {
        return DisposePerson;
    }

    public void setDisposePerson(String DisposePerson) {
        this.DisposePerson = DisposePerson;
    }

    public Date getRealStartDate() {
        return RealStartDate;
    }

    public void setRealStartDate(Date RealStartDate) {
        this.RealStartDate = RealStartDate;
    }

    public Date getRealFinishDate() {
        return RealFinishDate;
    }

    public void setRealFinishDate(Date RealFinishDate) {
        this.RealFinishDate = RealFinishDate;
    }

    public Date getSaveDate() {
        return SaveDate;
    }

    public void setSaveDate(Date SaveDate) {
        this.SaveDate = SaveDate;
    }

    public String getSymptomCode() {
        return symptomCode;
    }

    public void setSymptomCode(String symptomCode) {
        this.symptomCode = symptomCode;
    }

    public String getFormFlag() {
        return formFlag;
    }

    public void setFormFlag(String formFlag) {
        this.formFlag = formFlag;
    }

    public String getEquipFault() {
        return equipFault;
    }

    public void setEquipFault(String equipFault) {
        this.equipFault = equipFault;
    }
}
