package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2017/3/22.
 */
public class ParamMaterialKunCun {
    @Json(name = "Description") String Description;
    @Json(name = "LocNo") String LocNo;
    @Json(name = "LocName") String LocName;
    @Json(name = "PartsRows") List<ParamMaterialWarehouseInfo> WarehouseInfos;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLocName() {
        return LocName;
    }

    public void setLocName(String locName) {
        LocName = locName;
    }

    public String getLocNo() {
        return LocNo;
    }

    public void setLocNo(String locNo) {
        LocNo = locNo;
    }

    public List<ParamMaterialWarehouseInfo> getWarehouseInfos() {
        return WarehouseInfos;
    }

    public void setWarehouseInfos(List<ParamMaterialWarehouseInfo> warehouseInfos) {
        WarehouseInfos = warehouseInfos;
    }
}
