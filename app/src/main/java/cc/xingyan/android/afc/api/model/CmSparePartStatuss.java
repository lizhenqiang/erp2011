package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by 1 on 2015/12/8.
 */
public class CmSparePartStatuss {
    @Json(name = "spss")
    List<CmSparePartStatus> CmSparePartStatuss;

    public List<CmSparePartStatus> getCmSparePartStatuss() {
        return CmSparePartStatuss;
    }

    public void setCmSparePartStatuss(List<CmSparePartStatus> CmSparePartStatuss) {
        this.CmSparePartStatuss = CmSparePartStatuss;
    }
}
