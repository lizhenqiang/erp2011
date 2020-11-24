package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by 1 on 2015/12/8.
 */
public class CmWorkIDs {
    @Json(name = "ids")
    List<CmWorkID> CmWorkIDs;

    public List<CmWorkID> getCmWorkIDs() {
        return CmWorkIDs;
    }

    public void setCmWorkIDs(List<CmWorkID> CmWorkIDs) {
        this.CmWorkIDs = CmWorkIDs;
    }

}
