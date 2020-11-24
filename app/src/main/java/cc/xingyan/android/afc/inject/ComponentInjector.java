/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc.inject;

import javax.inject.Singleton;

//import cc.xingyan.android.afc.ChangePasswordActivity;
//import cc.xingyan.android.afc.ChangeWorkRemindActivity;
//import cc.xingyan.android.afc.CmFaultConfirmFragment;
//import cc.xingyan.android.afc.CmFaultFragment;
//import cc.xingyan.android.afc.CmFaultReleasedorChangeFragment;
//import cc.xingyan.android.afc.CmFragment;
//import cc.xingyan.android.afc.CmMaterialFragment;
//import cc.xingyan.android.afc.CmReportFragment;
//import cc.xingyan.android.afc.CmWorkDeleteFragment;
//import cc.xingyan.android.afc.CmWorkEditerConfirmFragment;
//import cc.xingyan.android.afc.CmWorkEditerFragment;
//import cc.xingyan.android.afc.CmWorkEditerNewFragment;
//import cc.xingyan.android.afc.CmWorkListFragment;
//import cc.xingyan.android.afc.CmWorkReleasedFragment;
//import cc.xingyan.android.afc.CmWorkReportFragment;
//import cc.xingyan.android.afc.DayInspectEditorFragment;
//import cc.xingyan.android.afc.DayInspectWorkActivity;
//import cc.xingyan.android.afc.DayInspectWorkFragment;
//import cc.xingyan.android.afc.DayInspectWorkInfoFragment;
//import cc.xingyan.android.afc.DayInspectWorkListFragment;
//import cc.xingyan.android.afc.DayInspectWorkPmFragment;
//import cc.xingyan.android.afc.DayInspectWorkPmItemContentFragment;
//import cc.xingyan.android.afc.DayInspectWorkPmItemImgFragment;
//import cc.xingyan.android.afc.HomeFragment;
//import cc.xingyan.android.afc.LoginActivity;
//import cc.xingyan.android.afc.Main4ChartForWebActivity;
//import cc.xingyan.android.afc.Main4PankuReportManagementActivity;
//import cc.xingyan.android.afc.Main4YunshuActivity;
//import cc.xingyan.android.afc.Main4YunshuUploadedDetailActivity;
//import cc.xingyan.android.afc.MainActivity;
//import cc.xingyan.android.afc.PartKunCunFragment;
//import cc.xingyan.android.afc.PartLingJianFragment;
//import cc.xingyan.android.afc.PartPanKuFragment;
//import cc.xingyan.android.afc.PartYunShuHeadFragment;
//import cc.xingyan.android.afc.PartYunShuLineFragment;
//import cc.xingyan.android.afc.PartYunShuUploadedHeadFragment;
//import cc.xingyan.android.afc.PartYunShuUploadedLineFragment;
//import cc.xingyan.android.afc.PartYunshuNoUploadFragment;
//import cc.xingyan.android.afc.PartYunshuUploadedFragment;
//import cc.xingyan.android.afc.PmFragment;
//import cc.xingyan.android.afc.PmWorkEditorContentFragment;
//import cc.xingyan.android.afc.PmWorkEditorFragment;
//import cc.xingyan.android.afc.PmWorkEditorImageAfterWorkFragment;
//import cc.xingyan.android.afc.PmWorkEditorImageBeforeWorkFragment;
//import cc.xingyan.android.afc.PmWorkEditorImageFragment;
//import cc.xingyan.android.afc.PmWorkListFragment;
//import cc.xingyan.android.afc.PmWorkNewContentFragment;
//import cc.xingyan.android.afc.PmWorkNewDeviceInfoFragment;
//import cc.xingyan.android.afc.PmWorkNewFragment;
//import cc.xingyan.android.afc.PmWorkNewMaterialFragment;
//import cc.xingyan.android.afc.PmWorkUploadImageFragment;
//import cc.xingyan.android.afc.PmWorkUploadedFragment;
//import cc.xingyan.android.afc.SettingsActivity;
//import cc.xingyan.android.afc.SyncActivity;
//import cc.xingyan.android.afc.WorkOrderEditorFragment;
//import cc.xingyan.android.afc.WorkOrderFragment;
//import cc.xingyan.android.afc.WorkOrderListFragment;
//import cc.xingyan.android.afc.adapter.WorkOrderAdapter;
//import cc.xingyan.android.afc.receiver.NetworkStatusReceiver;
//import cc.xingyan.android.afc.service.SyncService;
//import cc.xingyan.android.afc.service.WorkRemindService;
import cc.xingyan.android.afc.LoginActivity;
import cc.xingyan.android.afc.Main4ChartForWebActivity;
import cc.xingyan.android.afc.MainActivity;
import cc.xingyan.android.afc.SyncActivity;
import cc.xingyan.android.afc.service.SyncService;
import cc.xingyan.android.afc.service.WorkRemindService;
import cc.xingyan.android.afc.util.MyCrashHandler;
import dagger.Component;

@Singleton
@Component(modules = {ApiServiceModule.class, SystemServiceModule.class})
public interface ComponentInjector {
    void inject(LoginActivity t);

    void inject(Main4ChartForWebActivity t);

    void inject(MainActivity t);
//
    void inject(SyncActivity t);
//
//    void inject(SettingsActivity.SettingsFragment t);
//
//    void inject(ChangePasswordActivity.ChangePwdFragment t);
//    void inject(ChangeWorkRemindActivity.ChangeWorkRemindFragment t);
//
//    void inject(HomeFragment t);
//
//    void inject(WorkOrderEditorFragment t);
//
//    void inject(WorkOrderAdapter.WorkOrderViewHolder t);
//
//    void inject(WorkOrderListFragment t);
//
//    void inject(PmWorkListFragment t);
//    void inject(PmWorkNewFragment t);
//    void inject(PmWorkNewDeviceInfoFragment t);
//    void inject(PmWorkNewContentFragment t);
//    void inject(PmWorkNewMaterialFragment t);
//    void inject(PmWorkEditorContentFragment t);
//    void inject(PmWorkEditorImageFragment t);
//    void inject(PmWorkEditorImageBeforeWorkFragment t);
//    void inject(PmWorkEditorImageAfterWorkFragment t);
//    void inject(PmWorkUploadedFragment t);
//    void inject(PmWorkUploadImageFragment t);
//    void inject(PmWorkEditorFragment t);
//
//    void inject(WorkOrderFragment t);
//
//    void inject(PmFragment t);
//
//    void inject(DayInspectEditorFragment t);
//
//    void inject(DayInspectWorkFragment t);
//    void inject(DayInspectWorkListFragment t);
//
//    void inject(DayInspectWorkInfoFragment t);
//    void inject(DayInspectWorkPmFragment t);
//
//    void inject(DayInspectWorkPmItemContentFragment t);
//    void inject(DayInspectWorkPmItemImgFragment t);
//
//    void inject(DayInspectWorkActivity t);
//
//
    void inject(SyncService t);
//
    void inject(WorkRemindService t);
//
//    void inject(NetworkStatusReceiver t);
//
//    void inject(CmFragment t);
//    void inject(CmWorkListFragment t);
//    void inject(CmFaultFragment t);
//    void inject(CmWorkEditerNewFragment t);
//    void inject(CmFaultConfirmFragment t);
//    void inject(CmWorkEditerConfirmFragment t);
//    void inject(CmReportFragment t);
//    void inject(CmWorkEditerFragment t);
//    void inject(CmMaterialFragment t);
//    void inject(CmWorkReportFragment t);
//    void inject(CmWorkDeleteFragment t);
//    void inject(CmWorkReleasedFragment t);
//    void inject(CmFaultReleasedorChangeFragment t);
//
//    void inject(PartLingJianFragment t);
//    void inject(PartKunCunFragment t);
//    void inject(PartPanKuFragment t);
//    void inject(PartYunShuHeadFragment t);
//    void inject(PartYunShuLineFragment t);
//    void inject(PartYunShuUploadedHeadFragment t);
//    void inject(PartYunShuUploadedLineFragment t);
//    void inject(PartYunshuNoUploadFragment t);
//    void inject(PartYunshuUploadedFragment t);
//    void inject(Main4PankuReportManagementActivity t);
//    void inject(Main4YunshuActivity t);
//    void inject(Main4YunshuUploadedDetailActivity t);


    void inject(MyCrashHandler t);

}
