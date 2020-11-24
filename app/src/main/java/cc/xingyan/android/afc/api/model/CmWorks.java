package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by 1 on 2015/12/8.
 */
public class CmWorks {
    @Json(name = "ids") List<CmWork> CmWorks;

    public List<CmWork> getCmWorks() {
        return CmWorks;
    }

    public void setCmWorks(List<CmWork> CmWorks) {
        this.CmWorks = CmWorks;
    }
}
