package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2017/8/21.
 */
public class DayInspectWorkReports {
    @Json(name = "woNos") List<DayInspectWorkReport> dayInspectWorkReports;

    public List<DayInspectWorkReport> getDayInspectWorkReports() {
        return dayInspectWorkReports;
    }

    public void setDayInspectWorkReports(List<DayInspectWorkReport> dayInspectWorkReports) {
        this.dayInspectWorkReports = dayInspectWorkReports;
    }
}
