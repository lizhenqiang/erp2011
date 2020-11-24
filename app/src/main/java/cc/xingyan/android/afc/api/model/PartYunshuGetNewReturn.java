package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2018/2/26.
 *
 */
public class PartYunshuGetNewReturn {
    @Json(name = "TranHead") TranHeadInfo tranHead;
    @Json(name = "TranFromList")  List<TranFromInfo> tranFromList;
    @Json(name = "TranToList") List<TranToInfo> tranToList;

    public List<TranFromInfo> getTranFromList() {
        return tranFromList;
    }

    public void setTranFromList(List<TranFromInfo> tranFromList) {
        this.tranFromList = tranFromList;
    }

    public TranHeadInfo getTranHead() {
        return tranHead;
    }

    public void setTranHead(TranHeadInfo tranHead) {
        this.tranHead = tranHead;
    }

    public List<TranToInfo> getTranToList() {
        return tranToList;
    }

    public void setTranToList(List<TranToInfo> tranToList) {
        this.tranToList = tranToList;
    }
}
