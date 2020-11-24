package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2017/8/31.
 */
public class DayInspectWorkInfoList1Level {
    @Json(name = "getdi")
    DayInspectWorkInfoList2Level dayInspectWorkInfoList2Level;

    public DayInspectWorkInfoList2Level getDayInspectWorkInfoList2Level() {
        return dayInspectWorkInfoList2Level;
    }

    public void setDayInspectWorkInfoList2Level(DayInspectWorkInfoList2Level dayInspectWorkInfoList2Level) {
        this.dayInspectWorkInfoList2Level = dayInspectWorkInfoList2Level;
    }
}
