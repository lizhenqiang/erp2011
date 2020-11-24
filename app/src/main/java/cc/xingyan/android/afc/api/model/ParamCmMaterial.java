package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.io.Serializable;

/**
 * Created by 1 on 2015/12/23.
 */
public class ParamCmMaterial implements Serializable {
    @Json(name = "Code") String Code;
    @Json(name = "Name") String Name;
    @Json(name = "ContainLine") String ContainLine;
    @Json(name = "ContainDevice") String ContainDevice;

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getContainLine() {
        return ContainLine;
    }

    public void setContainLine(String ContainLine) {
        this.ContainLine = ContainLine;
    }

    public String getContainDevice() {
        return ContainDevice;
    }

    public void setContainDevicen(String ContainDevice) {
        this.ContainDevice = ContainDevice;
    }
}
