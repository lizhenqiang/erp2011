package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.Arrays;
import java.util.List;

/**
 * Created by YuJiadeng on 2017/8/11.
 */
public class DayInspectWorkPMOnlys {
    @Json(name = "pmNos") List<DayInspectWorkPMOnly> diwPMs;

    public List<DayInspectWorkPMOnly> getDiwPMs() {
        return diwPMs;
    }

    public void setDiwPMs(List<DayInspectWorkPMOnly> diwPMs) {
        this.diwPMs = diwPMs;
    }

    public static DayInspectWorkPMOnlys from(DayInspectWorkPMOnly... d) {
        DayInspectWorkPMOnlys dayInspectWorkPMOnlys = new DayInspectWorkPMOnlys();
        dayInspectWorkPMOnlys.setDiwPMs(Arrays.asList(d));
        return dayInspectWorkPMOnlys;
    }
}
