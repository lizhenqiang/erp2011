package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2017/5/4.
 * 上传盘点报告后接收服务器返回信息实体
 */
public class ParamMaterialPanKuReportReturn {
    @Json(name = "ReportNo") String reportNo;
    @Json(name = "UploadSatae") String uploadSatae;

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public String getUploadSatae() {
        return uploadSatae;
    }

    public void setUploadSatae(String uploadSatae) {
        this.uploadSatae = uploadSatae;
    }
}
