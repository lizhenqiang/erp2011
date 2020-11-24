package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2018/3/28.
 *
 */
public class PartYunshuGetLineNoReturn {
    @Json(name = "taskId") String taskId;
    @Json(name = "materialId") String materialId;
    @Json(name = "lineNo") String lineNo;
    @Json(name = "lineId") long lineId;

    public long getLineId() {
        return lineId;
    }

    public void setLineId(long lineId) {
        this.lineId = lineId;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
