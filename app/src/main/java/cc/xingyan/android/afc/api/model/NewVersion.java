package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.io.Serializable;

/**
 * Created by YuJiadeng on 2016/7/18.
 */
public class NewVersion implements Serializable{
    @Json(name = "UpdateFlag") String updateFlag;
    @Json(name = "CurrectVersion") String currectVersion;
    @Json(name = "CurrectVersionType") String currentVersionType;
    @Json(name = "IMEI") String imei;
    @Json(name = "URL") String url;
    @Json(name = "UpdateContent") String updateContent;

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

    public String getCurrectVersion() {
        return currectVersion;
    }

    public void setCurrectVersion(String currectVersion) {
        this.currectVersion = currectVersion;
    }

    public String getCurrentVersionType() {
        return currentVersionType;
    }

    public void setCurrentVersionType(String currentVersionType) {
        this.currentVersionType = currentVersionType;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }
}
