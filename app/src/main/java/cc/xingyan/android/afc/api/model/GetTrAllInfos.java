package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2018/4/24.
 */
public class GetTrAllInfos {
    @Json(name = "getAllInfo") List<GetTrAllInfo> getTrAllInfos;

    public List<GetTrAllInfo> getGetTrAllInfos() {
        return getTrAllInfos;
    }

    public void setGetTrAllInfos(List<GetTrAllInfo> getTrAllInfos) {
        this.getTrAllInfos = getTrAllInfos;
    }
}
