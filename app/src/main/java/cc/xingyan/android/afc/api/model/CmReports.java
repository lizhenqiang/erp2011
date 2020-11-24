package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by 1 on 2015/12/8.
 */
public class CmReports {
    @Json(name = "cmrs")
    List<CmReport> CmReports;

    public List<CmReport> getCmReports() {
        return CmReports;
    }

    public void setCmReports(List<CmReport> CmReports) {
        this.CmReports = CmReports;
    }
}
