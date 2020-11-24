package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by 1 on 2015/10/26.
 */
public class DayInspectReports {
    @Json(name = "dayInspects")
    List<DayInspectReport> DayInspectReports;

    public List<DayInspectReport> getDayInspectReports() {
        return DayInspectReports;
    }

    public void setDayInspectReports(List<DayInspectReport> DayInspectReports) {
        this.DayInspectReports = DayInspectReports;
    }
}
