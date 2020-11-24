package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2017/6/9.
 */
public class CMDeleteLists {
    @Json(name = "cms")
    List<CmWorkDelete> cmWorkDeletes;

    public List<CmWorkDelete> getCmWorkDeletes() {
        return cmWorkDeletes;
    }

    public void setCmWorkDeletes(List<CmWorkDelete> cmWorkDeletes) {
        this.cmWorkDeletes = cmWorkDeletes;
    }
}
