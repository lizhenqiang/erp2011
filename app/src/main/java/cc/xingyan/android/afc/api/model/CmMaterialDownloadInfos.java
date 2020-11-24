package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by 1 on 2015/12/29.
 */
public class CmMaterialDownloadInfos {
    @Json(name = "MaterialHead") CmMaterial MaterialHead;
    @Json(name = "MaterialRows") List<CmMaterialsInfo> MaterialRows;

    public CmMaterial getMaterialHead() {
        return MaterialHead;
    }

    public void setMaterialHead(CmMaterial MaterialHead) {
        this.MaterialHead = MaterialHead;
    }

    public List<CmMaterialsInfo> getMaterialRows() {
        return MaterialRows;
    }

    public void setMaterialRows(List<CmMaterialsInfo> MaterialRows) {
        this.MaterialRows = MaterialRows;
    }
}
