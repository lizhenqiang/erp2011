package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by 1 on 2016/1/26.
 */
public class PMDeleteLists {
    @Json(name = "pms") List<PmWorkDelete> pmWorkDeletes;

    public List<PmWorkDelete> getPmWorkDeletes() {
        return pmWorkDeletes;
    }

    public void setPmWorkDeletes(List<PmWorkDelete> pmWorkDeletes) {
        this.pmWorkDeletes = pmWorkDeletes;
    }
}
