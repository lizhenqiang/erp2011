package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by 1 on 2015/12/8.
 */
public class CmSpareParts {
    @Json(name = "sps")
    List<CmSparePart> CmSpareParts;

    public List<CmSparePart> getCmSpareParts() {
        return CmSpareParts;
    }

    public void setCmSpareParts(List<CmSparePart> CmSpareParts) {
        this.CmSpareParts = CmSpareParts;
    }
}
