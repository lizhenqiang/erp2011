package cc.xingyan.android.afc.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import cc.xingyan.android.afc.api.model.CmMaterial;
import cc.xingyan.android.afc.api.model.CmMaterialsInfo;
import cc.xingyan.android.afc.api.model.CmReport;
import cc.xingyan.android.afc.api.model.CmReports;
import cc.xingyan.android.afc.api.model.CmWork;
import cc.xingyan.android.afc.api.model.CmWorkID;
import cc.xingyan.android.afc.api.model.CmWorks;
import cc.xingyan.android.afc.api.model.DayInspect;
import cc.xingyan.android.afc.api.model.DayInspectIfsWork;
import cc.xingyan.android.afc.api.model.DayInspectItem;
import cc.xingyan.android.afc.api.model.DayInspectReport;
import cc.xingyan.android.afc.api.model.DayInspectReports;
import cc.xingyan.android.afc.api.model.DayInspectWorkInfo;
import cc.xingyan.android.afc.api.model.DayInspectWorkPM;
import cc.xingyan.android.afc.api.model.DayInspectWorkPMItem;
import cc.xingyan.android.afc.api.model.DayInspectWorkReport;
import cc.xingyan.android.afc.api.model.DayInspectWorkReports;
import cc.xingyan.android.afc.api.model.Device;
import cc.xingyan.android.afc.api.model.MEquipPhysic;
import cc.xingyan.android.afc.api.model.PRWorkID;
import cc.xingyan.android.afc.api.model.ParamCmMaterial;
import cc.xingyan.android.afc.api.model.ParamValue;
import cc.xingyan.android.afc.api.model.PmReport;
import cc.xingyan.android.afc.api.model.PmReports;
import cc.xingyan.android.afc.api.model.PmWork;
import cc.xingyan.android.afc.api.model.PmWorkID;
import cc.xingyan.android.afc.api.model.PmWorkItemResult;
import cc.xingyan.android.afc.api.model.PmWorkMaterialHead;
import cc.xingyan.android.afc.api.model.PmWorkMaterialRows;
import cc.xingyan.android.afc.api.model.PmWorkMissionRecord;
import cc.xingyan.android.afc.api.model.PmWorkMissionRecordItemValue;
import cc.xingyan.android.afc.api.model.ReportRespData;
import cc.xingyan.android.afc.api.model.User;
import cc.xingyan.android.afc.api.model.WorkOrder;
import cc.xingyan.android.afc.api.model.WorkOrders;
import cc.xingyan.android.afc.provider.cmmaterial.CmMaterialColumns;
import cc.xingyan.android.afc.provider.cmmaterial.CmMaterialContentValues;
import cc.xingyan.android.afc.provider.cmmaterialrow.CmMaterialRowColumns;
import cc.xingyan.android.afc.provider.cmmaterialrow.CmMaterialRowContentValues;
import cc.xingyan.android.afc.provider.cmparammaterials.CmParamMaterialsColumns;
import cc.xingyan.android.afc.provider.cmparammaterials.CmParamMaterialsContentValues;
import cc.xingyan.android.afc.provider.cmwork.CmWorkColumns;
import cc.xingyan.android.afc.provider.cmwork.CmWorkContentValues;
import cc.xingyan.android.afc.provider.cmwork.CmWorkCursor;
import cc.xingyan.android.afc.provider.cmwork.CmWorkSelection;
import cc.xingyan.android.afc.provider.cmwork.CmWorkStatus;
import cc.xingyan.android.afc.provider.device.DeviceContentValues;
import cc.xingyan.android.afc.provider.device.DeviceSelection;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsColumns;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsContentValues;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsSelection;
import cc.xingyan.android.afc.provider.diifsinfo.DiIfsStatus;
import cc.xingyan.android.afc.provider.diifsinfo.DiifsInfoColumns;
import cc.xingyan.android.afc.provider.diifsinfo.DiifsInfoContentValues;
import cc.xingyan.android.afc.provider.diifsinfo.DiifsInfoCursor;
import cc.xingyan.android.afc.provider.diifsinfo.DiifsInfoSelection;
import cc.xingyan.android.afc.provider.diifspm.DiifsPmColumns;
import cc.xingyan.android.afc.provider.diifspm.DiifsPmContentValues;
import cc.xingyan.android.afc.provider.diifspm.DiifsPmCursor;
import cc.xingyan.android.afc.provider.diifspm.DiifsPmSelection;
import cc.xingyan.android.afc.provider.diifspmitem.DiifsPmItemColumns;
import cc.xingyan.android.afc.provider.diifspmitem.DiifsPmItemContentValues;
import cc.xingyan.android.afc.provider.diifspmitem.DiifsPmItemCursor;
import cc.xingyan.android.afc.provider.diifspmitem.DiifsPmItemSelection;
import cc.xingyan.android.afc.provider.diifswork.DiifsWorkColumns;
import cc.xingyan.android.afc.provider.diifswork.DiifsWorkContentValues;
import cc.xingyan.android.afc.provider.diifswork.DiifsWorkCursor;
import cc.xingyan.android.afc.provider.diifswork.DiifsWorkSelection;
import cc.xingyan.android.afc.provider.diwork.DiWorkColumns;
import cc.xingyan.android.afc.provider.diwork.DiWorkContentValues;
import cc.xingyan.android.afc.provider.diwork.DiWorkCursor;
import cc.xingyan.android.afc.provider.diwork.DiWorkSelection;
import cc.xingyan.android.afc.provider.diwork.DiWorkStatus;
import cc.xingyan.android.afc.provider.diworkitem.DiWorkItemColumns;
import cc.xingyan.android.afc.provider.diworkitem.DiWorkItemContentValues;
import cc.xingyan.android.afc.provider.diworkitem.DiWorkItemCursor;
import cc.xingyan.android.afc.provider.diworkitem.DiWorkItemSelection;
import cc.xingyan.android.afc.provider.pankureport.PankuReportSelection;
import cc.xingyan.android.afc.provider.paramvalue.ParamValueColumns;
import cc.xingyan.android.afc.provider.paramvalue.ParamValueContentValues;
import cc.xingyan.android.afc.provider.paramvalue.ParamValueCursor;
import cc.xingyan.android.afc.provider.paramvalue.ParamValueSelection;
import cc.xingyan.android.afc.provider.pmifsresultenum.PmifsResultEnumColumns;
import cc.xingyan.android.afc.provider.pmifsresultenum.PmifsResultEnumContentValues;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkColumns;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkContentValues;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkCursor;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkSelection;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkStatus;
import cc.xingyan.android.afc.provider.pmifsworkitem.PmifsWorkItemColumns;
import cc.xingyan.android.afc.provider.pmifsworkitem.PmifsWorkItemContentValues;
import cc.xingyan.android.afc.provider.pmifsworkitem.PmifsWorkItemCursor;
import cc.xingyan.android.afc.provider.pmifsworkitem.PmifsWorkItemSelection;
import cc.xingyan.android.afc.provider.pmmaterial.PmMaterialColumns;
import cc.xingyan.android.afc.provider.pmmaterial.PmMaterialContentValues;
import cc.xingyan.android.afc.provider.pmmaterialrow.PmMaterialRowColumns;
import cc.xingyan.android.afc.provider.pmmaterialrow.PmMaterialRowContentValues;
import cc.xingyan.android.afc.provider.report.ReportColumns;
import cc.xingyan.android.afc.provider.report.ReportContentValues;
import cc.xingyan.android.afc.provider.report.ReportSelection;
import cc.xingyan.android.afc.provider.user.UserColumns;
import cc.xingyan.android.afc.provider.user.UserContentValues;
import cc.xingyan.android.afc.provider.user.UserSelection;
import cc.xingyan.android.afc.provider.workorder.SyncStatus;
import cc.xingyan.android.afc.provider.workorder.WorkOrderCursor;
import cc.xingyan.android.afc.provider.workorder.WorkOrderSelection;

/**
 * Created by San on 11/11/15.
 */
public class SyncHelper {

    public static WorkOrders collectLocalWorkOrders(ContentResolver contentResolver, String user) {
        return collectWorkOrders(new WorkOrderSelection().userId(user).and().syncStatus(SyncStatus.LOCAL), contentResolver);
    }


    public static WorkOrders collectUploadPendingWorkOrders(ContentResolver contentResolver) {
        return collectWorkOrders(new WorkOrderSelection().uploadPending(true), contentResolver);
    }


    public static WorkOrders collectWorkOrdersWithGuids(ContentResolver contentResolver, String... guids) {
        return collectWorkOrders(new WorkOrderSelection().guid(guids), contentResolver);
    }


    public static WorkOrders collectWorkOrdersWithIds(ContentResolver contentResolver, long... ids) {
        return collectWorkOrders(new WorkOrderSelection().id(ids), contentResolver);
    }

    private static WorkOrders collectWorkOrders(WorkOrderSelection sel, ContentResolver contentResolver) {
        final WorkOrderCursor cur = sel.query(contentResolver);
        try {
            final List<WorkOrder> list = new ArrayList<>(cur.getCount());
            while (cur.moveToNext()) {
                final WorkOrder workOrder = new WorkOrder();
                workOrder.setGuid(cur.getGuid());
                workOrder.setDeviceCode(cur.getDeviceCode());
                workOrder.setFaultDescriptionCode(cur.getFaultDescriptionCode());
                workOrder.setFaultTypeCode(cur.getFaultTypeCode());
                workOrder.setReporterTypeCode(cur.getReporterTypeCode());
                workOrder.setReporter(cur.getReporter());
                workOrder.setFaultNote(cur.getFaultNote());
                workOrder.setFaultCauseCode(cur.getFaultCauseCode());
                workOrder.setOperationCode(cur.getOperationCode());
                workOrder.setOperationResult(cur.getOperationResultCode());
                workOrder.setOperationNote(cur.getOperationNote());
                workOrder.setOperator(cur.getUserId());
                workOrder.setFaultStartTime(new Date(cur.getFaultStartTime()));
                workOrder.setStartDate(new Date(cur.getOperationStartTime()));
                workOrder.setFinishDate(new Date(cur.getOperationEndTime()));
                workOrder.setSaveDate(new Date(cur.getLastModified()));
                workOrder.setFormFlag("FALSE");
                list.add(workOrder);
            }

            WorkOrders workOrders = new WorkOrders();
            workOrders.setWorkOrders(list);
            return workOrders;
        } finally {
            cur.close();
        }
    }



    public static CmReports collectLocalCmWorkReports(ContentResolver contentResolver, String user) {
        return collectCmReports(new CmWorkSelection().userId(user).and().status(CmWorkStatus.NEW), contentResolver);
    }


    public static CmReports collectUploadPendingCmWorkReports(ContentResolver contentResolver) {
        return collectCmReports(new CmWorkSelection().uploadPending(true), contentResolver);
    }


    public static CmReports collectCmWorkReportsWithIds(ContentResolver contentResolver, long... ids) {
        return collectCmReports(new CmWorkSelection().id(ids), contentResolver);
    }

    private static CmReports collectCmReports(CmWorkSelection sel, ContentResolver contentResolver) {
        final CmWorkCursor cur = sel.query(contentResolver);
        try {
            final List<CmReport> list = new ArrayList<>(cur.getCount());
            while (cur.moveToNext()) {
                final CmReport cmReport = new CmReport();
                cmReport.setOrderId(cur.getOrderId());

                cmReport.setIntCustomerNo(cur.getIntCustomerNo());
                cmReport.setFormFlag("FALSE");
                cmReport.setEquipFault(cur.getEquipFault());
                cmReport.setFaultGradeCode(cur.getFaultGradeCode());



                cmReport.setSymptomCode(cur.getFaultDescriptionCode());
                cmReport.setFaultTypeCode(cur.getFaultTypeCode());
                cmReport.setCauseCode(cur.getFaultCauseCode());

                cmReport.setWorkDetailsCode(cur.getWorkDetailsCode());
                cmReport.setWorkDone(cur.getWorkDoneCode());





                if(cur.getFaultCauseNote() == null){
                    cmReport.setcommentsCause("noFaultCauseNoteInfo");
                }else{
                    cmReport.setcommentsCause(cur.getFaultCauseNote());
                }

                if(cur.getWorkNote() == null){
                    cmReport.setcommentsWork("noWorkNoteInfo");
                }else{
                    cmReport.setcommentsWork(cur.getWorkNote());
                }





                cmReport.setDisposePerson(cur.getDispose());
                cmReport.setRealStartDate(new Date(cur.getOperationStartTime()));
                cmReport.setRealFinishDate(new Date(cur.getOperationEndTime()));

                cmReport.setWorkDes("CM故障工单");
                cmReport.setPlanStartDate(new Date(cur.getPlanSTime()));
                cmReport.setPlanFinishDate(new Date(cur.getPlanFTime()));
                cmReport.setSaveDate(new Date(cur.getLastModified()));
                list.add(cmReport);
            }
            CmReports cmReports = new CmReports();
            cmReports.setCmReports(list);
            return cmReports;
        } finally {
            cur.close();
        }
    }



    public static CmWorks collectLocalCmWorksNew(ContentResolver contentResolver, String user) {
        return collectCmWorksNew(new CmWorkSelection().userId(user).and().status(CmWorkStatus.NEW), contentResolver);
    }


    public static CmWorks collectUploadPendingCmWorksNew(ContentResolver contentResolver) {
        return collectCmWorksNew(new CmWorkSelection().uploadPending(true).and().status(CmWorkStatus.NEW), contentResolver);
    }


    public static CmWorks collectCmWorksNewWithIds(ContentResolver contentResolver, long... ids) {
        return collectCmWorksNew(new CmWorkSelection().id(ids), contentResolver);
    }

    private static CmWorks collectCmWorksNew(CmWorkSelection sel, ContentResolver contentResolver) {
        final CmWorkCursor cur = sel.query(contentResolver);
        try {
            final List<CmWork> list = new ArrayList<>(cur.getCount());
            while (cur.moveToNext()) {
                final CmWork cmWork = new CmWork();
                cmWork.setOrderId(cur.getOrderId());
                cmWork.setGuid(cur.getGuid());
                cmWork.setPriority(cur.getPriority());
                cmWork.setEquitCode(cur.getDeviceCode());
                cmWork.setEquitName(cur.getDeviceName());
                cmWork.setSymptomCode("");
                cmWork.setFaultNote("");
                cmWork.setDiscovererTypeCode(cur.getReporterTypeCode());
                cmWork.setInstruct(cur.getInstruct() + "(Syn)");
                cmWork.setReportedBy(cur.getReporter());
                cmWork.setPrepareMan(cur.getPrepareMan());
                cmWork.setDisposePerson(cur.getDispose());
                cmWork.setWorkArea(cur.getWorkarea());
                cmWork.setFaultStartTime(new Date(cur.getFaultStartTime()));
                cmWork.setApplyStartTime(new Date(cur.getApplySTime()));
                cmWork.setApplyEndTime(new Date(cur.getApplyFTime()));
                cmWork.setOrderRecvTime(new Date(cur.getOrderReceiveTime()));
                cmWork.setArriveTime(new Date(cur.getArriveTime()));
                cmWork.setSaveDate(new Date(cur.getLastModified()));

                list.add(cmWork);
            }
            CmWorks cmWorks = new CmWorks();
            cmWorks.setCmWorks(list);
            return cmWorks;
        } finally {
            cur.close();
        }
    }


    public static PmReports collectDonePmReports(ContentResolver contentResolver, String user) {
        return collectPmReports(new PmifsWorkSelection().userId(user).and().status(PmifsWorkStatus.WORKDONE), contentResolver);
    }

    public static PmReports collectUploadPendingPmReports(ContentResolver contentResolver) {
        return collectPmReports(new PmifsWorkSelection().uploadPending(true), contentResolver);
    }

    public static PmReports collectPmReportsWithGuids(ContentResolver contentResolver, String... guids) {
        return collectPmReports(new PmifsWorkSelection().guid(guids), contentResolver);
    }

    public static PmReports collectPmReportsWithIds(ContentResolver contentResolver, long... ids) {
        return collectPmReports(new PmifsWorkSelection().id(ids), contentResolver);
    }

    private static PmReports collectPmReports(PmifsWorkSelection sel, ContentResolver contentResolver) {

        String intCustemNo = "";
        ParamValueSelection paramValueSelection = new ParamValueSelection().type("InternalCustomer");
        ParamValueCursor paramValueCursor = paramValueSelection.query(contentResolver);
        try {

            while (paramValueCursor.moveToNext()) {
                intCustemNo = paramValueCursor.getCode();
            }
        } finally {
            paramValueCursor.close();
        }


        final PmifsWorkCursor cur = sel.query(contentResolver);
        try {
            final List<PmReport> reports = new ArrayList<>(cur.getCount());
            while (cur.moveToNext()) {
                PmReport report = new PmReport();
                report.setOrderId(cur.getOrderId());
                report.setPmWorkItemResultList(collectPmWorkItemResults(cur.getOrderId(), contentResolver));
                report.setIntCustemNo(intCustemNo);
                report.setWorkDetailsCode("04");
                report.setWorkDone("01");
                report.setWorkDes("PM工单");
                report.setRealStartDate(new Date(cur.getOperationStartTime()));
                report.setRealFinishDate(new Date(cur.getOperationEndTime()));



                reports.add(report);
            }

            final PmReports pmReports = new PmReports();
            pmReports.setPmReports(reports);
            return pmReports;
        } finally {
            cur.close();
        }
    }

    private static List<PmWorkItemResult> collectPmWorkItemResults(String pMOrderId, ContentResolver contentResolver) {
        final PmifsWorkItemCursor cur = new PmifsWorkItemSelection().orderId(pMOrderId).query(contentResolver);
        try {
            final List<PmWorkItemResult> results = new ArrayList<>(cur.getCount());
            while (cur.moveToNext()) {
                PmWorkItemResult result = new PmWorkItemResult();
                result.setItemId(cur.getItemId());
                result.setResult(cur.getResultEnumOrdinal() + "");
                results.add(result);
            }
            return results;
        } finally {
            cur.close();
        }
    }



    public static DayInspectReports collectDoneDayInspectReports(ContentResolver contentResolver) {
        return collectDayInspectReports(new DiWorkSelection().status(DiWorkStatus.DONE), contentResolver);
    }



    public static DayInspectWorkReports collectDoneDayInspectWorkReports(ContentResolver contentResolver, String user) {

        List<String> woNos = new ArrayList<>();
        DiifsInfoCursor diifsInfoCursor = new DiifsInfoSelection().status(DiIfsStatus.DONE).query(contentResolver);
        while (diifsInfoCursor.moveToNext()){
            woNos.add(diifsInfoCursor.getWono());
        }

        if(woNos.size() < 1){
            return null;
        }

        return collectDayInspectWorkReports(new DiifsWorkSelection().wono(woNos.toArray(new String[woNos.size()])).and().isupdate(false), contentResolver, user);
    }


    public static DayInspectReports collectUploadPendingDayInspectReports(ContentResolver contentResolver) {
        return collectDayInspectReports(new DiWorkSelection().uploadPending(true), contentResolver);
    }

    public static DayInspectWorkReports collectUploadPendingDayInspectWorkReports(ContentResolver contentResolver, String user) {
        List<String> woNos = new ArrayList<>();
        DiifsInfoCursor diifsInfoCursor = new DiifsInfoSelection().uploadPending(true).query(contentResolver);
        while (diifsInfoCursor.moveToNext()){
            woNos.add(diifsInfoCursor.getWono());
        }

        if(woNos.size() < 1){
            return null;
        }
        return collectDayInspectWorkReports(new DiifsWorkSelection().wono(woNos.toArray(new String[woNos.size()])).and().isupdate(false), contentResolver, user);
    }



    public static DayInspectReports collectDayInspectReportsWithGuids(ContentResolver contentResolver, String... guids) {
        return collectDayInspectReports(new DiWorkSelection().guid(guids), contentResolver);
    }

    public static DayInspectReports collectDayInspectReportsWithIds(ContentResolver contentResolver, long... ids) {
        return collectDayInspectReports(new DiWorkSelection().id(ids), contentResolver);
    }


    public static DayInspectWorkReports collectDayInspectWorkReportsWithWoNos(ContentResolver contentResolver, String user, String... WoNos) {
        return collectDayInspectWorkReports(new DiifsWorkSelection().wono(WoNos).and().isupdate(false), contentResolver, user);
    }


    private static DayInspectWorkReports collectDayInspectWorkReports(DiifsWorkSelection sel, ContentResolver contentResolver, String user) {
        final DiifsWorkCursor diifsWorkCursor = sel.query(contentResolver);
        try {
            final List<DayInspectWorkReport> reports = new ArrayList<>(diifsWorkCursor.getCount());
            while (diifsWorkCursor.moveToNext()) {
                final DayInspectWorkReport report = new DayInspectWorkReport();
                report.setWoNo(diifsWorkCursor.getWono());
                report.setPhysicCode(diifsWorkCursor.getPhysiccode());
                report.setObjId(diifsWorkCursor.getObjid());
                report.setObjVersion(diifsWorkCursor.getObjversion());
                report.setPmgenValue(diifsWorkCursor.getPmgenvalue());
                report.setSign(user);
                reports.add(report);
            }
            final DayInspectWorkReports dayInspectReports = new DayInspectWorkReports();
            dayInspectReports.setDayInspectWorkReports(reports);
            return dayInspectReports;
        } finally {
            diifsWorkCursor.close();
        }

    }



    private static DayInspectReports collectDayInspectReports(DiWorkSelection sel, ContentResolver contentResolver) {
        final DiWorkCursor diWorkCursor = sel.query(contentResolver);
        try {
            final List<DayInspectReport> reports = new ArrayList<>(diWorkCursor.getCount());
            while (diWorkCursor.moveToNext()) {
                final DayInspectReport report = new DayInspectReport();
                report.setGuid(diWorkCursor.getGuid());
                report.setDayInspectID(diWorkCursor.getWorkId());
                report.setOperator(diWorkCursor.getOperator());
                report.setDayInspectDevices(collectDayInspectItemResults(diWorkCursor.getGuid(), contentResolver));
                report.setSaveTime(new Date(diWorkCursor.getCompleteTime()));
                reports.add(report);
            }
            final DayInspectReports dayInspectReports = new DayInspectReports();
            dayInspectReports.setDayInspectReports(reports);
            return dayInspectReports;
        } finally {
            diWorkCursor.close();
        }
    }

    private static List<DayInspectReport.DayInspectDevice> collectDayInspectItemResults(String guid, ContentResolver contentResolver) {
        final DiWorkItemCursor diWorkItemCursor = new DiWorkItemSelection().workGuid(guid).query(contentResolver);
        try {
            final List<DayInspectReport.DayInspectDevice> reports = new ArrayList<>(diWorkItemCursor.getCount());
            while (diWorkItemCursor.moveToNext()) {
                DayInspectReport.DayInspectDevice report = new DayInspectReport.DayInspectDevice();
                report.setDeviceId(diWorkItemCursor.getDeviceId());
                report.setCompleteTime(new Date(diWorkItemCursor.getLastModified()));
                report.setDeviceStatus(diWorkItemCursor.getResultValue());
                reports.add(report);
            }
            return reports;
        } finally {
            diWorkItemCursor.close();
        }
    }

    public static void saveDevices(ContentResolver contentResolver, List<Device> devices) {

        int m = 2;
        for (int i = 0; i < devices.size(); i++) {
            final Device device = devices.get(i);
            DeviceContentValues v = new DeviceContentValues()
                    .putCode(device.getCode())
                    .putName(device.getName())
                    .putType(device.getType())
                    .putLocation(device.getLocation());
            m = v.update(contentResolver, new DeviceSelection().code(device.getCode()));
            if (m > 1) {
                new DeviceSelection().code(device.getCode()).delete(contentResolver);
                v.insert(contentResolver);
            } else if (m == 0) {
                v.insert(contentResolver);
            }

        }
    }


    public static void savePhysics(ContentResolver contentResolver, List<MEquipPhysic> devices) {
        final ContentValues[] values = new ContentValues[devices.size()];
        for (int i = 0; i < devices.size(); i++) {
            final MEquipPhysic physicValue = devices.get(i);
            values[i] = new DevicePhysicsContentValues()
                    .putCode(physicValue.getCode())
                    .putCode(physicValue.getCode())
                    .putCodePhysics(physicValue.getCodePhysics())
                    .putName(physicValue.getName())
                    .putType(physicValue.getEquipType())
                    .values();
        }
        if(values.length > 0){
            new DevicePhysicsSelection().delete(contentResolver);
        }
        contentResolver.bulkInsert(DevicePhysicsColumns.CONTENT_URI, values);
    }

    public static void saveParams(ContentResolver contentResolver, List<ParamValue> params) {
        final ContentValues[] values = new ContentValues[params.size()];
        for (int i = 0; i < params.size(); i++) {
            final ParamValue paramValue = params.get(i);
            values[i] = new ParamValueContentValues()
                    .putCode(paramValue.getCode())
                    .putType(paramValue.getParam())
                    .putValue(paramValue.getValue())
                    .putParentCode(paramValue.getParentParamTypeCode())
                    .putParentType(paramValue.getParentParamType())
                    .values();
        }
        if(values.length > 0) {
            new ParamValueSelection().delete(contentResolver);
        }
        contentResolver.bulkInsert(ParamValueColumns.CONTENT_URI, values);
    }

    public static void saveMaterials(ContentResolver contentResolver, List<ParamCmMaterial> materials) {
        final ContentValues[] values = new ContentValues[materials.size()];
        for (int i = 0; i < materials.size(); i++) {
            final ParamCmMaterial paramCmMaterial = materials.get(i);
            values[i] = new CmParamMaterialsContentValues()
                    .putCode(paramCmMaterial.getCode())
                    .putName(paramCmMaterial.getName())
                    .putLine(paramCmMaterial.getContainLine())
                    .putDevice(paramCmMaterial.getContainDevice())
                    .values();
        }
        contentResolver.bulkInsert(CmParamMaterialsColumns.CONTENT_URI, values);
    }

    public static void saveUsers(ContentResolver contentResolver, List<User> users) {
        final ContentValues[] values = new ContentValues[users.size()];
        for (int i = 0; i < users.size(); i++) {
            final User user = users.get(i);
            values[i] = new UserContentValues()
                    .putOrgCode(user.getOrgCode())
                    .putUserId(user.getId())
                    .putUserNo(user.getNo())
                    .putUserName(user.getName())
                    .putPasswordMd5(user.getPassword())
                    .values();
        }
        if(values.length > 0){
            new UserSelection().delete(contentResolver);
        }
        contentResolver.bulkInsert(UserColumns.CONTENT_URI, values);
    }

    public static void savePmWorks(ContentResolver contentResolver, String user, List<PmWork> works) {
        final List<ContentValues> workValues = new ArrayList<>(works.size());
        final List<ContentValues> workItemValues = new ArrayList<>();
        final List<ContentValues> resultEnums = new ArrayList<>();


        long timeNew = System.currentTimeMillis();
        long timeArriveTime = 0;
        long timeRecvTime = 0;

        for (PmWork work : works) {

            if (work.getArriveTime() != null){
                timeArriveTime = Dates.getMillis(work.getArriveTime());
            }else{
                timeArriveTime = timeNew;
            }

            if  ( work.getRecvTime() != null){
                timeRecvTime =  Dates.getMillis(work.getRecvTime());
            }else{
                timeRecvTime = timeNew;
            }

            workValues.add(new PmifsWorkContentValues()
                    .putUserId(user)
                    .putGuid(work.getPmGuid())
                    .putOrderId(work.getOrderId())
                    .putDeviceCode(work.getEquitCode())
                    .putDeviceName(work.getEquitName())
                    .putDeviceLogicCode(work.getLogicCode())
                    .putInstruct(work.getInstruct())
                    .putPrepareManCode(work.getPrepareMan())
                    .putWorkareaCode(work.getWorkArea())
                    .putApplySTime(Dates.getMillis(work.getApplyStartTime()))
                    .putApplyFTime(Dates.getMillis(work.getApplyEndTime()))
                    .putPlanSTime(Dates.getMillis(work.getPlanStartTime()))
                    .putPlanFTime(Dates.getMillis(work.getPlanEndTime()))
                    .putArriveTime(timeArriveTime)
                    .putOrderReceiveTime(timeRecvTime)
                    .putStatus(PmifsWorkStatus.NEW)
                    .putWorkTypeId(work.getWorkTypeId())
                    .values());

            if (work.getPmWorkMissionRecords() == null)
                continue;

            for(PmWorkMissionRecord pmWorkMissionRecord : work.getPmWorkMissionRecords()){
                String itemGuid = UUID.randomUUID().toString();
                workItemValues.add(new PmifsWorkItemContentValues()
                        .putOrderId(work.getOrderId())
                        .putGuid(work.getPmGuid())
                        .putWorkGuid(itemGuid)
                        .putPackageId(pmWorkMissionRecord.getWorkPackageID())
                        .putPackageDes(pmWorkMissionRecord.getWorkPackageDes())
                        .putItemId(pmWorkMissionRecord.getWorkItemId())
                        .putItemDes(pmWorkMissionRecord.getWorkItemDes())
                        .putWorkSn(Integer.parseInt(pmWorkMissionRecord.getWorkSn() == null ? "-1" : pmWorkMissionRecord.getWorkSn()))
                        .putResultType(pmWorkMissionRecord.getWorkItemType())
                        .putResultMinValue(Integer.parseInt(pmWorkMissionRecord.getWorkItemMinValue() == null ? "0" : pmWorkMissionRecord.getWorkItemMinValue()))
                        .putResultMaxValue(Integer.parseInt(pmWorkMissionRecord.getWorkItemMaxValue() == null ? "0" : pmWorkMissionRecord.getWorkItemMaxValue()))
                        .putResultDefaultValue(pmWorkMissionRecord.getWorkItemDefaultValue())
                        .putResultValueUnit(pmWorkMissionRecord.getWorkItemUnit())
                        .putResultValue(pmWorkMissionRecord.getResult())
                        .values());

                if (pmWorkMissionRecord.getItemValues() == null)
                    continue;

                for(PmWorkMissionRecordItemValue pmWorkMissionRecordItemValue : pmWorkMissionRecord.getItemValues()){
                    resultEnums.add(new PmifsResultEnumContentValues()
                            .putItemGuid(itemGuid)
                            .putSn(pmWorkMissionRecordItemValue.getItemValueNo())
                            .putValue(pmWorkMissionRecordItemValue.getItemValueDes())
                            .values());
                }

            }

        }


        contentResolver.bulkInsert(PmifsWorkColumns.CONTENT_URI, workValues.toArray(new ContentValues[workValues.size()]));
        contentResolver.bulkInsert(PmifsWorkItemColumns.CONTENT_URI, workItemValues.toArray(new ContentValues[workItemValues.size()]));
        contentResolver.bulkInsert(PmifsResultEnumColumns.CONTENT_URI, resultEnums.toArray(new ContentValues[resultEnums.size()]));
    }

    public static void savePmWorksMaterialInfos(ContentResolver contentResolver, String user, List<PmWork> works) {
        final List<ContentValues> workMaterialHeadValues = new ArrayList<>();
        final List<ContentValues> workMaterialRowsValues = new ArrayList<>();

        for (PmWork work : works) {
            if (work.getPmWorkMaterialInfo() == null)
                continue;

            PmWorkMaterialHead materialHead = work.getPmWorkMaterialInfo().getPmWorkMaterialHead();
            workMaterialHeadValues.add(new PmMaterialContentValues()
                    .putPmOrderId(materialHead.getpMOrderId())
                    .putMaterialOrderId(materialHead.getOrderNumber())
                    .putUser(materialHead.getUserid())
                    .putDepartment(materialHead.getDepartment())
                    .putIntCustomerNo(materialHead.getIntCustomerNo())
                    .putEnterDate(Dates.getMillis(materialHead.getEnterDate()))
                    .putDueDate(Dates.getMillis(materialHead.getDueDate()))
                    .values());


            if(work.getPmWorkMaterialInfo().getPmWorkMaterialRows() == null){
                continue;
            }
            for(PmWorkMaterialRows pmWorkMaterialRows : work.getPmWorkMaterialInfo().getPmWorkMaterialRows()){
                workMaterialRowsValues.add(new PmMaterialRowContentValues()
                        .putPmOrderId(pmWorkMaterialRows.getpMOrderId())
                        .putMaterialOrderId(pmWorkMaterialRows.getOrderNumber())
                        .putMaterialRow(pmWorkMaterialRows.getOrderLineNo())
                        .putMaterialId(pmWorkMaterialRows.getPartNo())
                        .putMaterialDescription(pmWorkMaterialRows.getPartDescription())
                        .putDueDate(Dates.getMillis(pmWorkMaterialRows.getDateRequired()))
                        .putMaterialCount(Integer.parseInt(pmWorkMaterialRows.getQuantityRequired() == null ? "0" : pmWorkMaterialRows.getQuantityRequired()))
                        .values());
            }
        }


        contentResolver.bulkInsert(PmMaterialColumns.CONTENT_URI, workMaterialHeadValues.toArray(new ContentValues[workMaterialHeadValues.size()]));
        contentResolver.bulkInsert(PmMaterialRowColumns.CONTENT_URI, workMaterialRowsValues.toArray(new ContentValues[workMaterialRowsValues.size()]));
    }


    public static void saveDiWorks(ContentResolver contentResolver, List<DayInspect> works) {
        final List<ContentValues> workValues = new ArrayList<>(works.size());
        final List<ContentValues> workItemValues = new ArrayList<>();
        for (DayInspect work : works) {

            workValues.add(new DiWorkContentValues()
                    .putWorkId(work.getDayInspectID())
                    .putWorkAreaId(work.getWorkAreaID())
                    .putWorkAreaDescription(work.getWorkAreaDes())
                    .putDate(Dates.getMillis(work.getDayInspectDate()))
                    .putEndTime(Dates.getMillis(work.getPlanEndTime()))
                    .putStartTime(Dates.getMillis(work.getPlanStartTime()))
                    .putGuid(work.getDayInspectID())
                    .putStatus(DiWorkStatus.NEW)
                    .values());

            if (work.getDayInspectWorks() == null)
                continue;

            for (DayInspectItem jobItem : work.getDayInspectWorks()) {
                String itemGuid = UUID.randomUUID().toString();
                workItemValues.add(new DiWorkItemContentValues()
                        .putWorkGuid(work.getDayInspectID())
                        .putStationId(jobItem.getstation_id())
                        .putStationDescription(jobItem.getstation_description())
                        .putDeviceId(jobItem.getdevice_id())
                        .putDeviceDescription(jobItem.getdevice_description())
                        .putDeviceSystem(jobItem.getdevice_system())
                        .putGuid(itemGuid)
                        .values());
            }
        }
        contentResolver.bulkInsert(DiWorkColumns.CONTENT_URI, workValues.toArray(new ContentValues[workValues.size()]));
        contentResolver.bulkInsert(DiWorkItemColumns.CONTENT_URI, workItemValues.toArray(new ContentValues[workItemValues.size()]));
    }


    public static void saveDiWorkInfos(ContentResolver contentResolver, List<DayInspectWorkInfo> works, String user){

//        System.out.println("DayInspectWorkInfo>>>>  " + works.size());

        final List<ContentValues> workValues = new ArrayList<>(works.size());

        for (DayInspectWorkInfo work : works) {

            workValues.add(new DiifsInfoContentValues()
                    .putWono(work.getWoNo())
                    .putRounddefid(work.getRounddefId())
                    .putDescr(work.getDescr())
                    .putRequiredstartdate(Dates.getMillis(work.getRequiredStartDate()))
                    .putPlanstartdate(Dates.getMillis(work.getPlanStartDate()))
                    .putPlanfinishdate(Dates.getMillis(work.getPlanFinishDate()))
                    .putSignature(work.getSignature())
                    .putSign(work.getSign())
                    .putDevicecount(work.getDeviceCount())
                    .putUploadPending(false)
                    .putIsconfirm(false)
                    .putStatus(DiIfsStatus.NEW)
                    .values());
        }
        contentResolver.bulkInsert(DiifsInfoColumns.CONTENT_URI, workValues.toArray(new ContentValues[workValues.size()]));


    }

    public static void saveDiWorkPMs(ContentResolver contentResolver, List<DayInspectWorkPM> works){
//        System.out.println("DayInspectWorkPM>>>>  " + works.size());


        final List<ContentValues> workValues = new ArrayList<>(works.size());

        int pmCOunt = 0;
        for (DayInspectWorkPM work : works) {
            pmCOunt++;
            workValues.add(new DiifsPmContentValues()
                    .putWono(work.getWoNo())
                    .putPmno(work.getPmNo())
                    .putLogicname(work.getLogicName())
                    .putPhysicname(work.getPhysicName())
                    .putPhysiccode(work.getPhysicCode())
                    .putIsdone(false)
                    .values());

//            if(pmCOunt > 3){
//                break;
//            }
        }
        contentResolver.bulkInsert(DiifsPmColumns.CONTENT_URI, workValues.toArray(new ContentValues[workValues.size()]));


    }

    public static void saveDiWorkPMItems(ContentResolver contentResolver, List<DayInspectWorkPMItem> works){
//        System.out.println("DayInspectWorkPMItem>>>>  " + works.size());


        final List<ContentValues> workValues = new ArrayList<>(works.size());

        for (DayInspectWorkPMItem work : works) {

            workValues.add(new DiifsPmItemContentValues()
                    .putWono(work.getWoNo())
                    .putPmno(work.getPmNo())
                    .putTestpointid(work.getTestPointId())
                    .putParametercode(work.getParameterCode())
                    .putParameterdesc(work.getParameterDesc())
                    .putParametertype(work.getParameterType())
                    .putUnit(work.getUnit())
                    .putMinvalue(work.getMinValue())
                    .putMaxvalue(work.getMaxValue())
                    .putStartvalue(work.getStartValue())
                    .putInterval(work.getInterval())
                    .putLastvalue(work.getLastValue())
                    .putObjid(work.getObjId())
                    .putObjversion(work.getObjVersion())
                    .values());
        }
        contentResolver.bulkInsert(DiifsPmItemColumns.CONTENT_URI, workValues.toArray(new ContentValues[workValues.size()]));


    }


    public static void delNotCurrentDateDiifis(ContentResolver contentResolver) throws Exception{
        List<String> notCurrentDateDiifsWoNoList = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar c1 = new GregorianCalendar();
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);

        String startTimeString = sdf.format(c1.getTime());
        Date date2Long = sdf.parse(startTimeString);
        long startTimeLong = date2Long.getTime();


        Calendar c2 = new GregorianCalendar();
        c2.set(Calendar.HOUR_OF_DAY, 23);
        c2.set(Calendar.MINUTE, 59);
        c2.set(Calendar.SECOND, 59);

        String finishTimeString = sdf.format(c2.getTime());
        date2Long = sdf.parse(finishTimeString);

        long finishTimeLong = date2Long.getTime();
        DiifsInfoCursor diifsInfoCursor = new DiifsInfoSelection().query(contentResolver);
        while(diifsInfoCursor.moveToNext()){
            long requiredStartDate = diifsInfoCursor.getRequiredstartdate();
            if(requiredStartDate < startTimeLong || requiredStartDate > finishTimeLong){
                notCurrentDateDiifsWoNoList.add(diifsInfoCursor.getWono());
            }
        }

        for(String notCurrentDateDiifsWoNo : notCurrentDateDiifsWoNoList){
            new DiifsInfoSelection().wono(notCurrentDateDiifsWoNo).delete(contentResolver);
            new DiifsPmSelection().wono(notCurrentDateDiifsWoNo).delete(contentResolver);
            new DiifsWorkSelection().wono(notCurrentDateDiifsWoNo).delete(contentResolver);
        }


    }


    public static void composeDiWorkPMItems(ContentResolver contentResolver){
        DiifsPmCursor diifsPmCursor = new DiifsPmSelection().query(contentResolver);
        List<DayInspectIfsWork> dayInspectIfsWorkList = new ArrayList<>();

        boolean isLocalHasSameIfsWork = false;

        while(diifsPmCursor.moveToNext()){
            String woNo = diifsPmCursor.getWono();
            String pmNo = diifsPmCursor.getPmno();
            String physicCode = diifsPmCursor.getPhysiccode();

            DiifsPmItemCursor diifsPmItemCursor = new DiifsPmItemSelection().pmno(pmNo).query(contentResolver);
            while(diifsPmItemCursor.moveToNext()){
                DayInspectIfsWork dayInspectIfsWork = new DayInspectIfsWork();
                dayInspectIfsWork.setWoNo(woNo);
                dayInspectIfsWork.setPmNo(pmNo);
                dayInspectIfsWork.setTestPointId(diifsPmItemCursor.getTestpointid());
                dayInspectIfsWork.setParameterCode(diifsPmItemCursor.getParametercode());
                dayInspectIfsWork.setParameterDesc(diifsPmItemCursor.getParameterdesc());
                dayInspectIfsWork.setParameterType(diifsPmItemCursor.getParametertype());
                dayInspectIfsWork.setUnit(diifsPmItemCursor.getUnit());
                dayInspectIfsWork.setMinValue(diifsPmItemCursor.getMinvalue());
                dayInspectIfsWork.setMaxValue(diifsPmItemCursor.getMaxvalue());
                dayInspectIfsWork.setStartValue(diifsPmItemCursor.getStartvalue());
                dayInspectIfsWork.setInterval(diifsPmItemCursor.getInterval());
                dayInspectIfsWork.setLastValue(diifsPmItemCursor.getLastvalue());
                dayInspectIfsWork.setPmgenValue(diifsPmItemCursor.getPmgenvalue());

                dayInspectIfsWork.setObjId(diifsPmItemCursor.getObjid());
                dayInspectIfsWork.setObjVersion(diifsPmItemCursor.getObjversion());
                dayInspectIfsWork.setPhysicCode(physicCode);


                dayInspectIfsWorkList.add(dayInspectIfsWork);
            }
        }


        final List<ContentValues> workValues = new ArrayList<>(dayInspectIfsWorkList.size());

        for (DayInspectIfsWork work : dayInspectIfsWorkList) {

            DiifsWorkCursor diifsWorkCursor = new DiifsWorkSelection().query(contentResolver);
            while (diifsWorkCursor.moveToNext()){
                String woNoInDiifisWork = diifsWorkCursor.getWono();
                String pmNoInDiifisWork = diifsWorkCursor.getPmno();
                String testPointIdInDiifisWork = diifsWorkCursor.getTestpointid();
                String parameterCodeInDiifisWork = diifsWorkCursor.getParametercode();

                if(woNoInDiifisWork.equals(work.getWoNo()) && pmNoInDiifisWork.equals(work.getPmNo())
                        && testPointIdInDiifisWork.equals(work.getTestPointId()) && parameterCodeInDiifisWork.equals(work.getParameterCode())){

                    isLocalHasSameIfsWork = true;
                    break;
                }else {
                    isLocalHasSameIfsWork = false;
                }
            }

            if(!isLocalHasSameIfsWork){
                workValues.add(new DiifsWorkContentValues()
                        .putWono(work.getWoNo())
                        .putPmno(work.getPmNo())
                        .putTestpointid(work.getTestPointId())
                        .putParametercode(work.getParameterCode())
                        .putParameterdesc(work.getParameterDesc())
                        .putParametertype(work.getParameterType())
                        .putUnit(work.getUnit())
                        .putMinvalue(work.getMinValue())
                        .putMaxvalue(work.getMaxValue())
                        .putStartvalue(work.getStartValue())
                        .putInterval(work.getInterval())
                        .putLastvalue(work.getLastValue())
                        .putPmgenvalue(work.getPmgenValue())

                        //**20180510新加
                        .putObjid(work.getObjId())
                        .putObjversion(work.getObjVersion())
                        .putIsupdate(false)
                        .putPhysiccode(work.getPhysicCode())
                        .values());
            }

        }
        contentResolver.bulkInsert(DiifsWorkColumns.CONTENT_URI, workValues.toArray(new ContentValues[workValues.size()]));

    }

    public static void saveCmWorks(ContentResolver contentResolver, String user, List<CmWork> works) {
        final List<ContentValues> workValues = new ArrayList<>(works.size());
        CmWorkStatus OrderStatus;
        long timeNew = System.currentTimeMillis();
        long timeRecvTime = 0;
        long timeArriveTime = 0;


        for (CmWork work : works) {
            if (work.getOrderStatus().equals("RELEASED")) {
                OrderStatus = CmWorkStatus.RELEASED;
                if (work.getArriveTime() != null)
                {
                    timeArriveTime = Dates.getMillis(work.getArriveTime());
                }else{
                    timeArriveTime = 0;
                }
                if  ( work.getOrderRecvTime() != null)
                {
                    timeRecvTime =  Dates.getMillis(work.getOrderRecvTime());
                }else{
                    timeRecvTime = 0;
                }
            } else {
                OrderStatus = CmWorkStatus.FAULTREPORT;
                timeRecvTime = timeNew;
                timeArriveTime = timeNew;
            }

            CmWorkSelection cmWorkSelection = new CmWorkSelection().userId(user);
            CmWorkCursor cmWorkCursor = cmWorkSelection.query(contentResolver);
            boolean isLocalHasSameGuid = false;
            String localGuid = "";
            String instructString = "";
            while(cmWorkCursor.moveToNext()){
                localGuid = cmWorkCursor.getGuid();

                instructString = work.getInstruct();
                if(instructString.contains("CM故障工单")){
                    if(localGuid.trim().equals(work.getGuid().trim())){
                        isLocalHasSameGuid = true;
                        break;
                    }else {
                        isLocalHasSameGuid = false;
                    }
                }else{
                    isLocalHasSameGuid = false;
                }

            }

            if(!isLocalHasSameGuid){
                workValues.add(new CmWorkContentValues()
                        .putUserId(user)
                        .putGuid(work.getGuid())
                        .putOrderId(work.getOrderId())
                        .putPriority(work.getPriority())
                        .putDeviceCode(work.getEquitCode())
                        .putDeviceName(work.getEquitNamee())
                        .putFaultDescriptionCode(work.getSymptomCode())
                        .putReporter(work.getReportedBy())
                        .putPrepareMan(work.getPrepareMan())
                        .putDispose(user)
                        .putFaultNote(work.getFaultNote())
                        .putReporterTypeCode(work.getDiscovererTypeCode())
                        .putWorkarea(work.getWorkArea())
                        .putApplySTime(Dates.getMillis(work.getApplyStartTime()))
                        .putApplyFTime(Dates.getMillis(work.getApplyEndTime()))
                        .putFaultStartTime(Dates.getMillis(work.getFaultStartTime()))
                        .putOrderReceiveTime(timeRecvTime)
                        .putArriveTime(timeArriveTime)
                        .putReporterTypeCode(work.getDiscovererTypeCode())
                        .putLastModified(System.currentTimeMillis())
                        .putStatus(OrderStatus)
                        .putUploadPending(false)
                        .values());
            }

        }
        contentResolver.bulkInsert(CmWorkColumns.CONTENT_URI, workValues.toArray(new ContentValues[workValues.size()]));
    }

    public static void saveCmWorksMaterialInfos(ContentResolver contentResolver, String user, List<CmWork> works) {
        final List<ContentValues> workValues = new ArrayList<>(works.size());
        int RowsNum = 0;
        boolean t = false;
        for (CmWork work : works) {
            if (work.getMaterialInfos() != null) {
                if ((work.getMaterialInfos().getMaterialHead().getCMOrderId() != null) &&
                        (work.getMaterialInfos().getMaterialHead().getOrderNumber() != null) &&
                        (work.getMaterialInfos().getMaterialHead().getuserid() != null)) {
                    CmMaterial cmMaterial = work.getMaterialInfos().getMaterialHead();
                    workValues.add(new CmMaterialContentValues()
                                    .putCmOrderId(cmMaterial.getCMOrderId())
                                    .putMaterialOrderId(cmMaterial.getOrderNumber())
                                    .putUser(cmMaterial.getuserid())
                                    .putIntCustomerNo(cmMaterial.getIntCustomerNo())
                                    .putEnterDate(Dates.getMillis(cmMaterial.getEnterDate()))
                                    .putDueDate(Dates.getMillis(cmMaterial.getDueDate()))
                                    .putDepartment(cmMaterial.getDepartment())
                                    .putGuid(cmMaterial.getGuid())
                                    .values()
                    );
                    t = true;
                }
                RowsNum = work.getMaterialInfos().getMaterialRows().size();
                if (RowsNum > 0) {
                    final List<ContentValues> RowValues = new ArrayList<>(RowsNum);
                    for (CmMaterialsInfo row : work.getMaterialInfos().getMaterialRows()) {
                        RowValues.add(new CmMaterialRowContentValues()
                                        .putCmOrderId(row.getCMOrderId())
                                        .putMaterialOrderId(row.getOrderNumber())
                                        .putMaterialRow(row.getOrderLineNo())
                                        .putMaterialId(row.getPartNo())
                                        .putMaterialDescription(row.getPartDescription())
                                        .putMaterialCount(Integer.parseInt(row.getQuantityRequired()))
                                        .putDueDate(Dates.getMillis(row.getDateRequired()))
                                        .putGuid(row.getGuid())
                                        .values()
                        );
                    }
                    contentResolver.bulkInsert(CmMaterialRowColumns.CONTENT_URI, RowValues.toArray(new ContentValues[workValues.size()]));
                }
            }
        }
        if (t == true) {
            contentResolver.bulkInsert(CmMaterialColumns.CONTENT_URI, workValues.toArray(new ContentValues[workValues.size()]));
        }
    }

    public static int saveChartData(ContentResolver contentResolver, List<ReportRespData> reportDatas) {
        final ContentValues[] values = new ContentValues[reportDatas.size()];
        for (int i = 0; i < reportDatas.size(); i++) {
            final ReportRespData reportData = reportDatas.get(i);
            values[i] = new ReportContentValues()
                    .putCode(reportData.getCode())
                    .putName(reportData.getName())
                    .putDataStart(Dates.getMillis(reportData.getDateStart()))
                    .putDataEnd(Dates.getMillis(reportData.getDateEnd()))
                    .putLastReceNum(reportData.getLastReceNum())
                    .putCurrectReceNum(reportData.getCurrectReceNum())
                    .putReceLrr(reportData.getReceLRR())
                    .putLastFormNum(reportData.getLastFormNum())
                    .putCurrectFormNum(reportData.getCurrectFormNum())
                    .putFormLrr(reportData.getFormLRR())
                    .putLastFormDelay(reportData.getLastFormDelay())
                    .putCurrectFormDelay(reportData.getCurrectFormDelay())
                    .putFormDelayLrr(reportData.getFormDelayLRR())
                    .putYtdReceNum(reportData.getyTDReceNum())
                    .putRecePer(reportData.getRecePer())
                    .putFormPer(reportData.getFormPer())
                    .putAgNum(reportData.getaGNum())
                    .putBomNum(reportData.getBomNum())
                    .putTvmNum(reportData.getTvmNum())
                    .putOtherNum(reportData.getOtherNum())
                    .putAgPer(reportData.getaGPer())
                    .putBomPer(reportData.getBomPer())
                    .putTvmPer(reportData.getTvmPer())
                    .putOtherPer(reportData.getOtherPer())
                    .putDeviceFaultNum(reportData.getDeviceFaultNum())
                    .putDeviceFaultPer(reportData.getDeviceFaultPer())
                    .putNotDeviceFaultNum(reportData.getNotDeviceFaultNum())
                    .putNotDeviceFaultPer(reportData.getNotDeviceFaultPer())
                    .values();
        }
        if(values.length > 0){
            new ReportSelection().delete(contentResolver);
        }
        return contentResolver.bulkInsert(ReportColumns.CONTENT_URI, values);
    }

    public static void deletePM(ContentResolver contentResolver, List<PmWorkID> pmWorkIDs) {
        for (PmWorkID pmWorkID : pmWorkIDs) {

            new PmifsWorkSelection().orderId(pmWorkID.getOrderId()).delete(contentResolver);
        }
    }

    public static void deleteCM(ContentResolver contentResolver, List<CmWorkID> cmWorkIDs) {
        for (CmWorkID cmWorkID : cmWorkIDs) {

            int delCMIndex = new CmWorkSelection().orderId(cmWorkID.getOrderId()).delete(contentResolver);
            LogUtil.info("delete", "delCMIndex>>>> " + delCMIndex);
        }
    }


    public static void deletePR(ContentResolver contentResolver, Context context, List<PRWorkID> prWorkIDs) {
        for (PRWorkID prWorkID : prWorkIDs) {

           int delPRIndex =  new PankuReportSelection().reportNo(prWorkID.getOrderId()).delete(contentResolver);
            LogUtil.info("delete", "delPRIndex>>>> " + delPRIndex);

            if(delPRIndex> 0){
                Intent intent = new Intent();
                intent.setAction("delPR");
                context.sendBroadcast(intent);
            }
        }
    }

}
