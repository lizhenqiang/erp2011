package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.Arrays;
import java.util.List;

/**
 * Created by YuJiadeng on 2017/8/11.
 */
public class DayInspectWorks {
    @Json(name = "woNos") List<DayInspectWork> diws;

    public List<DayInspectWork> getDiws() {
        return diws;
    }

    public void setDiws(List<DayInspectWork> diws) {
        this.diws = diws;
    }

    public static DayInspectWorks from(DayInspectWork... d) {
        DayInspectWorks dayInspectWorks = new DayInspectWorks();
        dayInspectWorks.setDiws(Arrays.asList(d));
        return dayInspectWorks;
    }
}
