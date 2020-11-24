package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by 1 on 2016/1/18.
 */
public class CmWorkExecuteMans {
    @Json(name = "m")
    List<CmWorkExecuteMan> cmWorkExecuteMans;

    public List<CmWorkExecuteMan> getcmWorkExecuteMans() {
        return cmWorkExecuteMans;
    }

    public void setcmWorkExecuteMans(List<CmWorkExecuteMan> cmWorkExecuteMans) {
        this.cmWorkExecuteMans = cmWorkExecuteMans;
    }
}