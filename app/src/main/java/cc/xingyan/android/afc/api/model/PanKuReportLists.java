package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2017/5/4.
 *
 */
public class PanKuReportLists {
    @Json(name = "panKuReport") List<ParamMaterialPanKuReportUpload> panKuReportList;

    public List<ParamMaterialPanKuReportUpload> getPanKuReportList() {
        return panKuReportList;
    }

    public void setPanKuReportList(List<ParamMaterialPanKuReportUpload> panKuReportList) {
        this.panKuReportList = panKuReportList;
    }
}
