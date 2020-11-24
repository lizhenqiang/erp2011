package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2016/10/31.
 */
public class PmWorkMaterialInfo {
    @Json(name = "MaterialHead") PmWorkMaterialHead pmWorkMaterialHead;
    @Json(name = "MaterialRows") List<PmWorkMaterialRows> pmWorkMaterialRows;

    public PmWorkMaterialHead getPmWorkMaterialHead() {
        return pmWorkMaterialHead;
    }

    public void setPmWorkMaterialHead(PmWorkMaterialHead pmWorkMaterialHead) {
        this.pmWorkMaterialHead = pmWorkMaterialHead;
    }

    public List<PmWorkMaterialRows> getPmWorkMaterialRows() {
        return pmWorkMaterialRows;
    }

    public void setPmWorkMaterialRows(List<PmWorkMaterialRows> pmWorkMaterialRows) {
        this.pmWorkMaterialRows = pmWorkMaterialRows;
    }
}
