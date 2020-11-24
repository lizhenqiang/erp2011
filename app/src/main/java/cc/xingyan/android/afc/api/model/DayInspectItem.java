package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by 1 on 2015/10/26.
 */
public class DayInspectItem {
    public static final String RESULT_TYPE_BINARY = "二选一";

    @Json(name = "work_guid") String work_guid;
    @Json(name = "StationID") String station_id;
    @Json(name = "StationDes") String station_description;
    @Json(name = "DeviceID") String device_id;
    @Json(name = "DeviceDes") String device_description;
    @Json(name = "DeviceSystem") String device_system;
    @Json(name = "guid") String guid;
    @Json(name = "result_ordinal") String result_ordinal;
    @Json(name = "result_value") String result_value;
    @Json(name = "last_modified") String last_modified;
    @Json(name = "resultEnums") List<ResultEnum> resultEnums;


    public String getwork_guid() {
        return work_guid;
    }

    public void setwork_guid(String work_guid) {
        this.work_guid = work_guid;
    }

    public String getstation_id() {
        return station_id;
    }

    public void setstation_id(String station_id) {
        this.station_id = station_id;
    }

    public String getstation_description() {
        return station_description;
    }

    public void setstation_description(String station_description) {
        this.station_description = station_description;
    }
    public String getdevice_id() {
        return device_id;
    }

    public void setdevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getdevice_description() {
        return device_description;
    }

    public void setdevice_description(String device_description) {
        this.device_description = device_description;
    }

    public String getdevice_system() {
        return device_system;
    }

    public void setdevice_system(String device_system) {
        this.device_system = device_system;
    }

    public String getguid() {
        return guid;
    }

    public void setguid(String guid) {
        this.guid = guid;
    }
    public String getresult_ordinal() {
        return result_ordinal;
    }

    public void setresult_ordinal(String result_ordinal) {
        this.result_ordinal = result_ordinal;
    }
    public String getresult_value() {
        return result_value;
    }

    public void setresult_value(String result_value) {
        this.result_value = result_value;
    }
    public String getlast_modified() {
        return last_modified;
    }

    public void setlast_modified(String last_modified) {
        this.last_modified = last_modified;
    }

    public List<ResultEnum> getresultEnums() {
        return resultEnums;
    }

    public void setresultEnums(List<ResultEnum> resultEnums) {
        this.resultEnums = resultEnums;
    }

    public static class ResultEnum implements Comparable<ResultEnum> {
        @Json(name = "ValueID") int ValueID;
        @Json(name = "ValueDes") String ValueDes;

        public int getValueID() {
            return ValueID;
        }

        public void setValueID(int ValueID) {
            this.ValueID = ValueID;
        }

        public String getValueDes() {
            return ValueDes;
        }

        public void setValueDes(String ValueDes) {
            this.ValueDes = ValueDes;
        }

        @Override public int compareTo(ResultEnum another) {
            return this.ValueID - another.ValueID;
        }
    }
}
