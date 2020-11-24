package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2017/8/11.
 */
public class DayInspectWork {
    @Json(name = "WoNo") String woNo;

    public String getWoNo() {
        return woNo;
    }

    public void setWoNo(String woNo) {
        this.woNo = woNo;
    }

    @Override
    public boolean equals(Object o) {
        DayInspectWork c = (DayInspectWork) o;

        return woNo.equals(c.woNo);
    }
}
