package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2016/5/30.
 * 物理设备编码信息
 */
public class MEquipPhysic {

    @Json(name = "Code") String code;
    @Json(name = "CodePhysics") String codePhysics;
    @Json(name = "Name") String name;
    @Json(name = "EquipType") String equipType;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodePhysics() {
        return codePhysics;
    }

    public void setCodePhysics(String codePhysics) {
        this.codePhysics = codePhysics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }
}
