package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.Date;
import java.util.List;

/**
 * Created by YuJiadeng on 2016/10/31.
 */
public class PmWork{
    @Json(name = "OrderId") String orderId;
    @Json(name = "Guid") String pmGuid;
    @Json(name = "Priority") String priority;
    @Json(name = "EquitCode") String equitCode;
    @Json(name = "EquitName") String equitName;
    @Json(name = "LogicCode") String logicCode;
    @Json(name = "Instruct") String instruct;
    @Json(name = "PrepareMan") String prepareMan;
    @Json(name = "DisposePerson") String disposePerson;
    @Json(name = "WorkArea") String workArea;
    @Json(name = "ApplyStartTime") Date applyStartTime;
    @Json(name = "ApplyEndTime") Date applyEndTime;
    @Json(name = "PlanStartTime") Date planStartTime;
    @Json(name = "PlanEndTime") Date planEndTime;
    @Json(name = "SaveDate") String saveDate;
    @Json(name = "OrderStatus") String orderStatus;
    @Json(name = "MaterialInfos")
    PmWorkMaterialInfo pmWorkMaterialInfo; ;
    @Json(name = "RecvTime") Date recvTime;
    @Json(name = "ArriveTime") Date arriveTime;
    @Json(name = "missionRecord") List<PmWorkMissionRecord> pmWorkMissionRecords;
    @Json(name = "WorkTypeId") String workTypeId;

    public Date getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(Date applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public Date getApplyStartTime() {
        return applyStartTime;
    }

    public void setApplyStartTime(Date applyStartTime) {
        this.applyStartTime = applyStartTime;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getDisposePerson() {
        return disposePerson;
    }

    public void setDisposePerson(String disposePerson) {
        this.disposePerson = disposePerson;
    }

    public String getEquitCode() {
        return equitCode;
    }

    public void setEquitCode(String equitCode) {
        this.equitCode = equitCode;
    }

    public String getEquitName() {
        return equitName;
    }

    public void setEquitName(String equitName) {
        this.equitName = equitName;
    }

    public String getInstruct() {
        return instruct;
    }

    public void setInstruct(String instruct) {
        this.instruct = instruct;
    }

    public String getLogicCode() {
        return logicCode;
    }

    public void setLogicCode(String logicCode) {
        this.logicCode = logicCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(Date planEndTime) {
        this.planEndTime = planEndTime;
    }

    public Date getPlanStartTime() {
        return planStartTime;
    }

    public void setPlanStartTime(Date planStartTime) {
        this.planStartTime = planStartTime;
    }

    public String getPmGuid() {
        return pmGuid;
    }

    public void setPmGuid(String pmGuid) {
        this.pmGuid = pmGuid;
    }

    public PmWorkMaterialInfo getPmWorkMaterialInfo() {
        return pmWorkMaterialInfo;
    }

    public void setPmWorkMaterialInfo(PmWorkMaterialInfo pmWorkMaterialInfo) {
        this.pmWorkMaterialInfo = pmWorkMaterialInfo;
    }

    public List<PmWorkMissionRecord> getPmWorkMissionRecords() {
        return pmWorkMissionRecords;
    }

    public void setPmWorkMissionRecords(List<PmWorkMissionRecord> pmWorkMissionRecords) {
        this.pmWorkMissionRecords = pmWorkMissionRecords;
    }

    public String getPrepareMan() {
        return prepareMan;
    }

    public void setPrepareMan(String prepareMan) {
        this.prepareMan = prepareMan;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getRecvTime() {
        return recvTime;
    }

    public void setRecvTime(Date recvTime) {
        this.recvTime = recvTime;
    }

    public String getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(String saveDate) {
        this.saveDate = saveDate;
    }

    public String getWorkArea() {
        return workArea;
    }

    public void setWorkArea(String workArea) {
        this.workArea = workArea;
    }

    public String getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(String workTypeId) {
        this.workTypeId = workTypeId;
    }
}