package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by 1 on 2015/12/8.
 */
public class CmMaterialsInfos {
    @Json(name = "mis")
    List<CmMaterialsInfo> CmMaterialsInfos;

    public List<CmMaterialsInfo> getCmMaterialsInfos() {
        return CmMaterialsInfos;
    }

    public void setCmMaterialsInfos(List<CmMaterialsInfo> CmMaterialsInfos) {
        this.CmMaterialsInfos = CmMaterialsInfos;
    }
}
