package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2017/6/22.
 */
public class PRManualDeleteLists {
    @Json(name = "mmd")
    List<PRWorkManualDelete> prWorkManualDeletes;

    public List<PRWorkManualDelete> getPrWorkManualDeletes() {
        return prWorkManualDeletes;
    }

    public void setPrWorkManualDeletes(List<PRWorkManualDelete> prWorkManualDeletes) {
        this.prWorkManualDeletes = prWorkManualDeletes;
    }
}
