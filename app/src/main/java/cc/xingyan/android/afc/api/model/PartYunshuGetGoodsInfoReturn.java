package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2018/3/20.
 *
 */
public class PartYunshuGetGoodsInfoReturn {
    @Json(name = "materialName") String materialName;
    @Json(name = "materialType") String materialType;
    @Json(name = "materialUnit") String materialUnit;
    @Json(name = "availableSum") String availableSum;

    public String getAvailableSum() {
        return availableSum;
    }

    public void setAvailableSum(String availableSum) {
        this.availableSum = availableSum;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit;
    }
}
