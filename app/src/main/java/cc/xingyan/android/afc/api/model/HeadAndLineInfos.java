package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2018/3/28.
 */
public class HeadAndLineInfos {
    @Json(name = "hlInfo") HeadAndLineInfo headAndLineInfo;

    public HeadAndLineInfo getHeadAndLineInfo() {
        return headAndLineInfo;
    }

    public void setHeadAndLineInfo(HeadAndLineInfo headAndLineInfo) {
        this.headAndLineInfo = headAndLineInfo;
    }
}
