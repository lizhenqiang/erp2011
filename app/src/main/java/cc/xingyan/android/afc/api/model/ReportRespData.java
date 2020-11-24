package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.Date;

/**
 * Created by YuJiadeng on 2016/8/23.
 */
public class ReportRespData {
    @Json(name = "Code") String Code;
    @Json(name = "Name") String name;
    @Json(name = "DateStart") Date dateStart;
    @Json(name = "DateEnd") Date dateEnd;
    @Json(name = "LastReceNum") String lastReceNum;
    @Json(name = "CurrectReceNum") String currectReceNum;
    @Json(name = "ReceLRR") String receLRR;
    @Json(name = "LastFormNum") String lastFormNum;
    @Json(name = "CurrectFormNum") String currectFormNum;
    @Json(name = "FormLRR") String formLRR;
    @Json(name = "LastFormDelay") String lastFormDelay;
    @Json(name = "CurrectFormDelay") String currectFormDelay;
    @Json(name = "FormDelayLRR") String formDelayLRR;
    @Json(name = "YTDReceNum") String yTDReceNum;
    @Json(name = "RecePer") String recePer;
    @Json(name = "FormPer") String	formPer;
    @Json(name = "AGNum") String aGNum;
    @Json(name = "BomNum") String bomNum;
    @Json(name = "TvmNum") String tvmNum;
    @Json(name = "OtherNum") String otherNum;
    @Json(name = "AGPer") String aGPer;
    @Json(name = "BomPer") String bomPer;
    @Json(name = "TvmPer") String tvmPer;
    @Json(name = "OtherPer") String otherPer;
    @Json(name = "DeviceFaultNum") String deviceFaultNum;
    @Json(name = "DeviceFaultPer") String deviceFaultPer;
    @Json(name = "NotDeviceFaultNum") String notDeviceFaultNum;
    @Json(name = "NotDeviceFaultPer") String notDeviceFaultPer;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getLastReceNum() {
        return lastReceNum;
    }

    public void setLastReceNum(String lastReceNum) {
        this.lastReceNum = lastReceNum;
    }

    public String getCurrectReceNum() {
        return currectReceNum;
    }

    public void setCurrectReceNum(String currectReceNum) {
        this.currectReceNum = currectReceNum;
    }

    public String getReceLRR() {
        return receLRR;
    }

    public void setReceLRR(String receLRR) {
        this.receLRR = receLRR;
    }

    public String getLastFormNum() {
        return lastFormNum;
    }

    public void setLastFormNum(String lastFormNum) {
        this.lastFormNum = lastFormNum;
    }

    public String getCurrectFormNum() {
        return currectFormNum;
    }

    public void setCurrectFormNum(String currectFormNum) {
        this.currectFormNum = currectFormNum;
    }

    public String getFormLRR() {
        return formLRR;
    }

    public void setFormLRR(String formLRR) {
        this.formLRR = formLRR;
    }

    public String getLastFormDelay() {
        return lastFormDelay;
    }

    public void setLastFormDelay(String lastFormDelay) {
        this.lastFormDelay = lastFormDelay;
    }

    public String getCurrectFormDelay() {
        return currectFormDelay;
    }

    public void setCurrectFormDelay(String currectFormDelay) {
        this.currectFormDelay = currectFormDelay;
    }

    public String getFormDelayLRR() {
        return formDelayLRR;
    }

    public void setFormDelayLRR(String formDelayLRR) {
        this.formDelayLRR = formDelayLRR;
    }

    public String getyTDReceNum() {
        return yTDReceNum;
    }

    public void setyTDReceNum(String yTDReceNum) {
        this.yTDReceNum = yTDReceNum;
    }

    public String getRecePer() {
        return recePer;
    }

    public void setRecePer(String recePer) {
        this.recePer = recePer;
    }

    public String getFormPer() {
        return formPer;
    }

    public void setFormPer(String formPer) {
        this.formPer = formPer;
    }

    public String getaGNum() {
        return aGNum;
    }

    public void setaGNum(String aGNum) {
        this.aGNum = aGNum;
    }

    public String getBomNum() {
        return bomNum;
    }

    public void setBomNum(String bomNum) {
        this.bomNum = bomNum;
    }

    public String getTvmNum() {
        return tvmNum;
    }

    public void setTvmNum(String tvmNum) {
        this.tvmNum = tvmNum;
    }

    public String getOtherNum() {
        return otherNum;
    }

    public void setOtherNum(String otherNum) {
        this.otherNum = otherNum;
    }

    public String getaGPer() {
        return aGPer;
    }

    public void setaGPer(String aGPer) {
        this.aGPer = aGPer;
    }

    public String getBomPer() {
        return bomPer;
    }

    public void setBomPer(String bomPer) {
        this.bomPer = bomPer;
    }

    public String getTvmPer() {
        return tvmPer;
    }

    public void setTvmPer(String tvmPer) {
        this.tvmPer = tvmPer;
    }

    public String getOtherPer() {
        return otherPer;
    }

    public void setOtherPer(String otherPer) {
        this.otherPer = otherPer;
    }

    public String getDeviceFaultNum() {
        return deviceFaultNum;
    }

    public void setDeviceFaultNum(String deviceFaultNum) {
        this.deviceFaultNum = deviceFaultNum;
    }

    public String getDeviceFaultPer() {
        return deviceFaultPer;
    }

    public void setDeviceFaultPer(String deviceFaultPer) {
        this.deviceFaultPer = deviceFaultPer;
    }

    public String getNotDeviceFaultNum() {
        return notDeviceFaultNum;
    }

    public void setNotDeviceFaultNum(String notDeviceFaultNum) {
        this.notDeviceFaultNum = notDeviceFaultNum;
    }

    public String getNotDeviceFaultPer() {
        return notDeviceFaultPer;
    }

    public void setNotDeviceFaultPer(String notDeviceFaultPer) {
        this.notDeviceFaultPer = notDeviceFaultPer;
    }
}
