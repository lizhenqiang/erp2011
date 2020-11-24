package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.Date;
import java.util.List;

/**
 * Created by 1 on 2015/10/26.
 */
public class DayInspectReport {
    @Json(name = "guid") String guid;
    @Json(name = "DayInspectID") String DayInspectID;
    @Json(name = "Operator") String Operator;
    @Json(name = "MDayInspectDevices") List<DayInspectDevice> DayInspectDevices;
    @Json(name = "SaveTime") Date SaveTime;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getDayInspectID() {
        return DayInspectID;
    }

    public void setDayInspectID(String DayInspectID) {
        this.DayInspectID = DayInspectID;
    }

    public String getOperator() {
        return Operator;
    }

    public void setOperator(String Operator) {
        this.Operator = Operator;
    }

    public List<DayInspectDevice> getDayInspectDevices() {
        return DayInspectDevices;
    }

    public void setDayInspectDevices(List<DayInspectDevice> DayInspectDevices) {
        this.DayInspectDevices = DayInspectDevices;
    }

    public Date getSaveTime() {
        return SaveTime;
    }

    public void setSaveTime(Date SaveTime) {
        this.SaveTime = SaveTime;
    }

    public static class DayInspectDevice {
        @Json(name = "DeviceId") String DeviceId;
        @Json(name = "DeviceStatus") String DeviceStatus;
        @Json(name = "RealCompleteDate") Date CompleteTime;
        
        public String getDeviceId() {
            return DeviceId;
        }

        public void setDeviceId(String DeviceId) {
            this.DeviceId = DeviceId;
        }

        public String getDeviceStatus() {
            return DeviceStatus;
        }

        public void setDeviceStatus(String DeviceStatus) {
            this.DeviceStatus = DeviceStatus;
        }

        public Date getCompleteTime() {
            return CompleteTime;
        }

        public void setCompleteTime(Date CompleteTime) {
            this.CompleteTime = CompleteTime;
        }
    }
}
