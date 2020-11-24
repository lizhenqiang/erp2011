package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by 1 on 2015/12/8.
 */
public class CmMaterials {
    @Json(name = "ms")
    List<CmMaterial> CmMaterials;

    public List<CmMaterial> getCmMaterials() {
        return CmMaterials;
    }

    public void setCmMaterials(List<CmMaterial> CmMaterials) {
        this.CmMaterials = CmMaterials;
    }
}
