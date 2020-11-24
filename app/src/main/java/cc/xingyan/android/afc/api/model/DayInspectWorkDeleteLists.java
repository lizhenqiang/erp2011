package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2018/4/17.
 */
public class DayInspectWorkDeleteLists {
    @Json(name = "dis")
    List<DayInspectWorkDelete> dayInspectWorkDeletes;

    public List<DayInspectWorkDelete> getDayInspectWorkDeletes() {
        return dayInspectWorkDeletes;
    }

    public void setDayInspectWorkDeletes(List<DayInspectWorkDelete> dayInspectWorkDeletes) {
        this.dayInspectWorkDeletes = dayInspectWorkDeletes;
    }
}
