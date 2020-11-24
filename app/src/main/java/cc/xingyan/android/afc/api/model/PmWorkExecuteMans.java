package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2017/6/21.
 */
public class PmWorkExecuteMans {
    @Json(name = "m")
    List<PmWorkExecuteMan> pmWorkExecuteMans;

    public List<PmWorkExecuteMan> getPmWorkExecuteMans() {
        return pmWorkExecuteMans;
    }

    public void setPmWorkExecuteMans(List<PmWorkExecuteMan> pmWorkExecuteMans) {
        this.pmWorkExecuteMans = pmWorkExecuteMans;
    }
}
