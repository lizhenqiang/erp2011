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

/**
 * Created by San on 10/9/15.
 */
public class WorkOrder extends WorkOrderNo {
    @Json(name = "EquitCode") String deviceCode;
    @Json(name = "SymptomCode") String faultDescriptionCode;
    @Json(name = "FaultTypeCode") String faultTypeCode;
    @Json(name = "DiscovererTypeCode") String reporterTypeCode;
    @Json(name = "ReportedBy") String reporter;
    @Json(name = "comments1") String faultNote;
    @Json(name = "FaultStartDate") Date faultStartTime;
    @Json(name = "CauseCode") String faultCauseCode;
    @Json(name = "WorkDetailsCode") String operationCode;
    @Json(name = "WorkDone") String operationResult;
    @Json(name = "comments2") String operationNote;
    @Json(name = "DisposePerson") String operator;
    @Json(name = "StartDate") Date startDate;
    @Json(name = "FinishDate") Date finishDate;
    @Json(name = "SaveDate") Date saveDate;
    @Json(name = "FormFlag") String formFlag;

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getFaultDescriptionCode() {
        return faultDescriptionCode;
    }

    public void setFaultDescriptionCode(String faultDescriptionCode) {
        this.faultDescriptionCode = faultDescriptionCode;
    }

    public String getFaultTypeCode() {
        return faultTypeCode;
    }

    public void setFaultTypeCode(String faultTypeCode) {
        this.faultTypeCode = faultTypeCode;
    }

    public String getReporterTypeCode() {
        return reporterTypeCode;
    }

    public void setReporterTypeCode(String reporterTypeCode) {
        this.reporterTypeCode = reporterTypeCode;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getFaultNote() {
        return faultNote;
    }

    public void setFaultNote(String faultNote) {
        this.faultNote = faultNote;
    }

    public Date getFaultStartTime() {
        return faultStartTime;
    }

    public void setFaultStartTime(Date faultStartTime) {
        this.faultStartTime = faultStartTime;
    }

    public String getFaultCauseCode() {
        return faultCauseCode;
    }

    public void setFaultCauseCode(String faultCauseCode) {
        this.faultCauseCode = faultCauseCode;
    }

    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public String getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }

    public String getOperationNote() {
        return operationNote;
    }

    public void setOperationNote(String operationNote) {
        this.operationNote = operationNote;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    public String getFormFlag() {
        return formFlag;
    }

    public void setFormFlag(String formFlag) {
        this.formFlag = formFlag;
    }
}
