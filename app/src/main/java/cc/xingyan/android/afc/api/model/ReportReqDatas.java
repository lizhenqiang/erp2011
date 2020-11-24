package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2016/8/22.
 */
public class ReportReqDatas {
    @Json(name = "info")
    List<ReportReqData> reportReqDataList;

    public List<ReportReqData> getReportReqDataList() {
        return reportReqDataList;
    }

    public void setReportReqDataList(List<ReportReqData> reportReqDataList) {
        this.reportReqDataList = reportReqDataList;
    }
}
