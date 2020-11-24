package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by YuJiadeng on 2016/7/18.
 */
public class NewVersions {
    @Json(name = "softs")
    List<NewVersion> newVersionList;

    public List<NewVersion> getNewVersionList() {
        return newVersionList;
    }

    public void setNewVersionList(List<NewVersion> newVersionList) {
        this.newVersionList = newVersionList;
    }
}
