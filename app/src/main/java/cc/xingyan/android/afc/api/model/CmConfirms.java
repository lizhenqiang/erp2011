package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by 1 on 2015/12/8.
 */
public class CmConfirms {
    @Json(name = "icds")
    List<CmConfirm> CmConfirms;

    public List<CmConfirm> getCmConfirms() {
        return CmConfirms;
    }

    public void setCmConfirms(List<CmConfirm> CmConfirms) {
        this.CmConfirms = CmConfirms;
    }
}
