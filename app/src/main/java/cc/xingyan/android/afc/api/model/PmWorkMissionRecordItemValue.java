package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2016/10/31.
 */
public class PmWorkMissionRecordItemValue {
    @Json(name = "ItemValueNo") int itemValueNo;
    @Json(name = "ItemValueDes") String itemValueDes;

    public String getItemValueDes() {
        return itemValueDes;
    }

    public void setItemValueDes(String itemValueDes) {
        this.itemValueDes = itemValueDes;
    }

    public int getItemValueNo() {
        return itemValueNo;
    }

    public void setItemValueNo(int itemValueNo) {
        this.itemValueNo = itemValueNo;
    }
}
