package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.Date;
import java.util.List;

/**
 * Created by 1 on 2015/10/26.
 */
public class DayInspect {
    @Json(name = "DayInspectID") String DayInspectID;
    @Json(name = "WorkAreaID") String WorkAreaID;
    @Json(name = "WorkAreaDes") String WorkAreaDes;
    @Json(name = "DayInspectDate") Date DayInspectDate;
    @Json(name = "PlanStartTime") Date PlanStartTime;
    @Json(name = "PlanEndTime") Date PlanEndTime;
    @Json(name = "saveDate") Date SaveTime;
    @Json(name = "MDayInspectWorks") List<DayInspectItem> DayInspectWorks;

    public String getDayInspectID() {
        return DayInspectID;
    }

    public void setDayInspectID(String DayInspectID) {
        this.DayInspectID = DayInspectID;
    }

    public String getWorkAreaID() {
        return WorkAreaID;
    }

    public void setWorkAreaID(String WorkAreaID) {
        this.WorkAreaID = WorkAreaID;
    }

    public String getWorkAreaDes() {
        return WorkAreaDes;
    }

    public void setWorkAreaDes(String WorkAreaDes) {
        this.WorkAreaDes = WorkAreaDes;
    }

    public Date getDayInspectDate() {
        return DayInspectDate;
    }

    public void setDayInspectDate(Date DayInspectDate) {
        this.DayInspectDate = DayInspectDate;
    }

    public Date getPlanStartTime() {
        return PlanStartTime;
    }

    public void setPlanStartTime(Date PlanStartTime) {
        this.PlanStartTime = PlanStartTime;
    }

    public Date getPlanEndTime() {
        return PlanEndTime;
    }

    public void setPlanEndTime(Date PlanEndTime) {
        this.PlanEndTime = PlanEndTime;
    }

    public Date getSaveTime() {
        return SaveTime;
    }

    public void setSaveTime(Date SaveTime) {
        this.SaveTime = SaveTime;
    }

    public List<DayInspectItem> getDayInspectWorks() {
        return DayInspectWorks;
    }

    public void setDayInspectWorks(List<DayInspectItem> DayInspectWorks) {
        this.DayInspectWorks = DayInspectWorks;
    }

}
