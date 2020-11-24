package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by YuJiadeng on 2017/3/6.
 */
public class ServerTime implements Serializable {
    @Json(name = "serverTime") Date serverTime;

    public Date getServerTime() {
        return serverTime;
    }

    public void setServerTime(Date serverTime) {
        this.serverTime = serverTime;
    }
}
