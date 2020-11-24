package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.Date;

/**
 * Created by YuJiadeng on 2018/4/24.
 */
public class UploadedTranHeadInfo {
    @Json(name = "taskId") String taskId;
    @Json(name = "state") String state;
    @Json(name = "createDate") Date createDate;
    @Json(name = "planBy") String planBy;
    @Json(name = "packNumber")  String packNumber;
    @Json(name = "type")  String type;
    @Json(name = "fromLocationNo")  String fromLocationNo;
    @Json(name = "fromLocationName")  String fromLocationName;
    @Json(name = "toLocationNo")  String toLocationNo;
    @Json(name = "toLocationName")  String toLocationName;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPlanBy() {
        return planBy;
    }

    public void setPlanBy(String planBy) {
        this.planBy = planBy;
    }

    public String getPackNumber() {
        return packNumber;
    }

    public void setPackNumber(String packNumber) {
        this.packNumber = packNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFromLocationNo() {
        return fromLocationNo;
    }

    public void setFromLocationNo(String fromLocationNo) {
        this.fromLocationNo = fromLocationNo;
    }

    public String getFromLocationName() {
        return fromLocationName;
    }

    public void setFromLocationName(String fromLocationName) {
        this.fromLocationName = fromLocationName;
    }

    public String getToLocationNo() {
        return toLocationNo;
    }

    public void setToLocationNo(String toLocationNo) {
        this.toLocationNo = toLocationNo;
    }

    public String getToLocationName() {
        return toLocationName;
    }

    public void setToLocationName(String toLocationName) {
        this.toLocationName = toLocationName;
    }
}
