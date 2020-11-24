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

/**
 * Created by San on 9/24/15.
 */
public class ParamValue {
    public static final String PARAM_FAULT_TYPE = "Fault";
    public static final String PARAM_FAULT_DESCRIPTION = "Symptom";
    public static final String PARAM_REPORTER_TYPE = "Discoverer";
    public static final String PARAM_FAULT_CAUSE = "Cause";
    public static final String PARAM_OPERATION = "WorkDetails";
    public static final String PARAM_OPERATION_RESULT = "WorkDone";
    public static final String PARAM_OPERATOR = "DisposePerson";
    public static final String PARAM_PRIORITY = "Priority";
    public static final String PARAM_INTERNALCUSTOMER = "InternalCustomer";
    public static final String PARAM_CM_FORMFLAG = "CmFormflag";
    public static final String PARAM_CM_EQUIPFAULT = "CmEquipfault";
    public static final String PARAM_FAULT_GRADE = "FaultGrade";

    @Json(name = "Code") String code;
    @Json(name = "Name") String value;
    @Json(name = "ParamType") String param;
    @Json(name = "ParentParamType") String parentParamType;
    @Json(name = "ParentParamTypeCode") String parentParamTypeCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getParentParamType() {
        return parentParamType;
    }

    public void setParentParamType(String parentParamType) {
        this.parentParamType = parentParamType;
    }

    public String getParentParamTypeCode() {
        return parentParamTypeCode;
    }

    public void setParentParamTypeCode(String parentParamTypeCode) {
        this.parentParamTypeCode = parentParamTypeCode;
    }
}
