/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.Date;
import java.util.List;

/**
 * Created by San on 10/9/15.
 */
public class PmReport {

    @Json(name = "OrderId") String orderId;
    @Json(name = "workItemResultList") List<PmWorkItemResult> pmWorkItemResultList;
    @Json(name = "IntCustemNo") String intCustemNo;
    @Json(name = "WorkDetailsCode") String WorkDetailsCode;
    @Json(name = "WorkDone") String workDone;
    @Json(name = "WorkDes") String workDes;
    @Json(name = "RealStartDate") Date realStartDate;
    @Json(name = "RealFinishDate") Date realFinishDate;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<PmWorkItemResult> getPmWorkItemResultList() {
        return pmWorkItemResultList;
    }

    public void setPmWorkItemResultList(List<PmWorkItemResult> pmWorkItemResultList) {
        this.pmWorkItemResultList = pmWorkItemResultList;
    }

    public String getIntCustemNo() {
        return intCustemNo;
    }

    public void setIntCustemNo(String intCustemNo) {
        this.intCustemNo = intCustemNo;
    }

    public String getWorkDetailsCode() {
        return WorkDetailsCode;
    }

    public void setWorkDetailsCode(String workDetailsCode) {
        WorkDetailsCode = workDetailsCode;
    }

    public String getWorkDone() {
        return workDone;
    }

    public void setWorkDone(String workDone) {
        this.workDone = workDone;
    }

    public String getWorkDes() {
        return workDes;
    }

    public void setWorkDes(String workDes) {
        this.workDes = workDes;
    }

    public Date getRealStartDate() {
        return realStartDate;
    }

    public void setRealStartDate(Date realStartDate) {
        this.realStartDate = realStartDate;
    }

    public Date getRealFinishDate() {
        return realFinishDate;
    }

    public void setRealFinishDate(Date realFinishDate) {
        this.realFinishDate = realFinishDate;
    }
}
