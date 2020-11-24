package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2018/3/28.
 */
public class TranUpdateHeadInfo {
    @Json(name = "taskId") String taskId;
    @Json(name = "packNumber")  String packNumber;
    @Json(name = "type")  String type;
    @Json(name = "from")  String from;
    @Json(name = "to")  String to;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getPackNumber() {
        return packNumber;
    }

    public void setPackNumber(String packNumber) {
        this.packNumber = packNumber;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

