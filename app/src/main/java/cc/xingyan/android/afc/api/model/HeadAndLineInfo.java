package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2018/3/28.
 */
public class HeadAndLineInfo {
    @Json(name = "headInfo")
    TranUpdateHeadInfo tranHead;
    @Json(name = "lineInfoList") List<TranUpdateLineInfo> lineInfoList;

    public List<TranUpdateLineInfo> getLineInfoList() {
        return lineInfoList;
    }

    public void setLineInfoList(List<TranUpdateLineInfo> lineInfoList) {
        this.lineInfoList = lineInfoList;
    }

    public TranUpdateHeadInfo getTranHead() {
        return tranHead;
    }

    public void setTranHead(TranUpdateHeadInfo tranHead) {
        this.tranHead = tranHead;
    }
}
