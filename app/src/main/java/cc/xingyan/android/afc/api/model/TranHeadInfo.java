package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2018/2/26.
 *
 */
public class TranHeadInfo {
    @Json(name = "TaskId") String newTranTaskId;
    @Json(name = "State")  String newTranState;

    public String getNewTranState() {
        return newTranState;
    }

    public void setNewTranState(String newTranState) {
        this.newTranState = newTranState;
    }

    public String getNewTranTaskId() {
        return newTranTaskId;
    }

    public void setNewTranTaskId(String newTranTaskId) {
        this.newTranTaskId = newTranTaskId;
    }
}
