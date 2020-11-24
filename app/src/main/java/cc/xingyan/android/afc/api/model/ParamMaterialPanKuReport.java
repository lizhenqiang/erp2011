package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by YuJiadeng on 2017/4/24.
 *
 * 盘点报告要素(下载用到)
 */
public class ParamMaterialPanKuReport {
    @Json(name = "ReportNo") String reportNo;
    @Json(name = "PartNo") String partNo;
    @Json(name = "PartName") String partName;
    @Json(name = "ActualAmount") String actualAmount;
    @Json(name = "LotBatchNo") String lotBatchNo;
    @Json(name = "LineNo") String lineNo;
    @Json(name = "PartSeq") String partSeq;
    @Json(name = "WarehouseNo") String warehouseNo;
    @Json(name = "WarehouseName") String warehouseName;
    @Json(name = "PanTime")long pandianTime;
    @Json(name = "PanMark") String pandianMark;

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public String getLotBatchNo() {
        return lotBatchNo;
    }

    public void setLotBatchNo(String lotBatchNo) {
        this.lotBatchNo = lotBatchNo;
    }

    public String getPandianMark() {
        return pandianMark;
    }

    public void setPandianMark(String pandianMark) {
        this.pandianMark = pandianMark;
    }

    public long getPandianTime() {
        return pandianTime;
    }

    public void setPandianTime(long pandianTime) {
        this.pandianTime = pandianTime;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public String getPartSeq() {
        return partSeq;
    }

    public void setPartSeq(String partSeq) {
        this.partSeq = partSeq;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseNo() {
        return warehouseNo;
    }

    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }
}
