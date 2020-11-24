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

import java.util.List;

/**
 * 工作项
 * Created by San on 10/9/15.
 */
public class PmWorkItem {

    public static final String RESULT_TYPE_BINARY = "二选一";
    public static final String RESULT_TYPE_LIST = "值列表";
    public static final String RESULT_TYPE_NUMERIC = "数字";
    public static final String RESULT_TYPE_TEXT = "文本";

    @Json(name = "missionRecordNo") String id;

    @Json(name = "workItemId") int ordinal;

    @Json(name = "workItemDes") String description;

    @Json(name = "workPackageID") String packageId;

    @Json(name = "workPackageDes") String packageDescription;

    @Json(name = "workItemType") String resultType;

    @Json(name = "workItemValueList") List<ResultEnum> resultEnums;

    @Json(name = "workItemMaxValue") int resultMaxValue;

    @Json(name = "workItemMinValue") int resultMinValue;

    @Json(name = "workItemDefaultValue") String resultDefaultValue;

    @Json(name = "workItemUnit") String resultValueUnit;

    @Json(name = "result") String resultValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageDescription() {
        return packageDescription;
    }

    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public List<ResultEnum> getResultEnums() {
        return resultEnums;
    }

    public void setResultEnums(List<ResultEnum> resultEnums) {
        this.resultEnums = resultEnums;
    }

    public int getResultMaxValue() {
        return resultMaxValue;
    }

    public void setResultMaxValue(int resultMaxValue) {
        this.resultMaxValue = resultMaxValue;
    }

    public int getResultMinValue() {
        return resultMinValue;
    }

    public void setResultMinValue(int resultMinValue) {
        this.resultMinValue = resultMinValue;
    }

    public String getResultDefaultValue() {
        return resultDefaultValue;
    }

    public void setResultDefaultValue(String resultDefaultValue) {
        this.resultDefaultValue = resultDefaultValue;
    }

    public String getResultValueUnit() {
        return resultValueUnit;
    }

    public void setResultValueUnit(String resultValueUnit) {
        this.resultValueUnit = resultValueUnit;
    }

    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }

    public static class ResultEnum implements Comparable<ResultEnum> {
        @Json(name = "ItemValueNo") int ordinal;
        @Json(name = "ItemValueDes") String value;

        public int getOrdinal() {
            return ordinal;
        }

        public void setOrdinal(int ordinal) {
            this.ordinal = ordinal;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override public int compareTo(ResultEnum another) {
            return this.ordinal - another.ordinal;
        }

        @Override public String toString() {
            return value;
        }
    }
}
