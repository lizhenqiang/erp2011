package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2018/4/24.
 */
public class PartYunshuGetAllInfoReturn {
    @Json(name = "headInfo") UploadedTranHeadInfo headInfo;
    @Json(name = "lineInfoList") List<UploadedTranLineInfo> lineInfoList;

    public UploadedTranHeadInfo getHeadInfo() {
        return headInfo;
    }

    public void setHeadInfo(UploadedTranHeadInfo headInfo) {
        this.headInfo = headInfo;
    }

    public List<UploadedTranLineInfo> getLineInfoList() {
        return lineInfoList;
    }

    public void setLineInfoList(List<UploadedTranLineInfo> lineInfoList) {
        this.lineInfoList = lineInfoList;
    }
}
