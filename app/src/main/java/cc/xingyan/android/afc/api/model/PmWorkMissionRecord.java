package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2016/10/31.
 */
public class PmWorkMissionRecord {
    @Json(name = "result") String result;
    @Json(name = "workItemDefaultValue") String workItemDefaultValue;
    @Json(name = "workItemDes") String workItemDes;
    @Json(name = "workItemId") String workItemId;
    @Json(name = "workItemMaxValue") String workItemMaxValue;
    @Json(name = "workItemMinValue") String workItemMinValue;
    @Json(name = "workItemType") String workItemType;
    @Json(name = "workItemUnit") String workItemUnit;
    @Json(name = "workItemValueList") List<PmWorkMissionRecordItemValue> itemValues;
    @Json(name = "workPackageDes") String workPackageDes;
    @Json(name = "workPackageID") String workPackageID;
    @Json(name = "work_sn") String workSn;

    public List<PmWorkMissionRecordItemValue> getItemValues() {
        return itemValues;
    }

    public void setItemValues(List<PmWorkMissionRecordItemValue> itemValues) {
        this.itemValues = itemValues;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getWorkItemDefaultValue() {
        return workItemDefaultValue;
    }

    public void setWorkItemDefaultValue(String workItemDefaultValue) {
        this.workItemDefaultValue = workItemDefaultValue;
    }

    public String getWorkItemDes() {
        return workItemDes;
    }

    public void setWorkItemDes(String workItemDes) {
        this.workItemDes = workItemDes;
    }

    public String getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(String workItemId) {
        this.workItemId = workItemId;
    }

    public String getWorkItemMaxValue() {
        return workItemMaxValue;
    }

    public void setWorkItemMaxValue(String workItemMaxValue) {
        this.workItemMaxValue = workItemMaxValue;
    }

    public String getWorkItemMinValue() {
        return workItemMinValue;
    }

    public void setWorkItemMinValue(String workItemMinValue) {
        this.workItemMinValue = workItemMinValue;
    }

    public String getWorkItemType() {
        return workItemType;
    }

    public void setWorkItemType(String workItemType) {
        this.workItemType = workItemType;
    }

    public String getWorkItemUnit() {
        return workItemUnit;
    }

    public void setWorkItemUnit(String workItemUnit) {
        this.workItemUnit = workItemUnit;
    }

    public String getWorkPackageDes() {
        return workPackageDes;
    }

    public void setWorkPackageDes(String workPackageDes) {
        this.workPackageDes = workPackageDes;
    }

    public String getWorkPackageID() {
        return workPackageID;
    }

    public void setWorkPackageID(String workPackageID) {
        this.workPackageID = workPackageID;
    }

    public String getWorkSn() {
        return workSn;
    }

    public void setWorkSn(String workSn) {
        this.workSn = workSn;
    }
}
