package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2016/10/30.
 */
public class PmWorkIDs {
    @Json(name = "guids")
    List<PmWorkID> PmWorkIDs;

    public List<PmWorkID> getPmWorkIDs() {
        return PmWorkIDs;
    }

    public void setPmWorkIDs(List<PmWorkID> pmWorkIDs) {
        PmWorkIDs = pmWorkIDs;
    }
}
