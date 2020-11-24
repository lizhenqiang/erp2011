package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2017/6/16.
 */
public class PRDeleteLists {
    @Json(name = "prd")
    List<PRWorkDelete> prWorkDeletes;

    public List<PRWorkDelete> getPrWorkDeletes() {
        return prWorkDeletes;
    }

    public void setPrWorkDeletes(List<PRWorkDelete> prWorkDeletes) {
        this.prWorkDeletes = prWorkDeletes;
    }
}
