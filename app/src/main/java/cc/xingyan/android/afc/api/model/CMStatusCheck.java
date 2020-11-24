package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2017/6/12.
 */
public class CMStatusCheck {
    @Json(name = "cms")
    List<CmWorkStatusCheckList> cmWorkStatusChecks;

    public List<CmWorkStatusCheckList> getCmWorkStatusChecks() {
        return cmWorkStatusChecks;
    }

    public void setCmWorkStatusChecks(List<CmWorkStatusCheckList> cmWorkStatusChecks) {
        this.cmWorkStatusChecks = cmWorkStatusChecks;
    }
}
