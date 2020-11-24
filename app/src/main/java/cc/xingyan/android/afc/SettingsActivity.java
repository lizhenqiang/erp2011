package cc.xingyan.android.afc;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import cc.xingyan.android.afc.api.ChangePassword;
import cc.xingyan.android.afc.api.DayInspectService;
import cc.xingyan.android.afc.api.model.DayInspectWorkObjInfoReturn;
import cc.xingyan.android.afc.api.model.DayInspectWorkPMOnly;
import cc.xingyan.android.afc.api.model.DayInspectWorkPMOnlys;
import cc.xingyan.android.afc.api.model.NewVersion;
import cc.xingyan.android.afc.api.model.NewVersions;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.cmwork.CmWorkSelection;
import cc.xingyan.android.afc.provider.diifsinfo.DiifsInfoSelection;
import cc.xingyan.android.afc.provider.diifspm.DiifsPmCursor;
import cc.xingyan.android.afc.provider.diifspm.DiifsPmSelection;
import cc.xingyan.android.afc.provider.diifspmitem.DiifsPmItemContentValues;
import cc.xingyan.android.afc.provider.diifspmitem.DiifsPmItemSelection;
import cc.xingyan.android.afc.provider.diifswork.DiifsWorkContentValues;
import cc.xingyan.android.afc.provider.diifswork.DiifsWorkSelection;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkSelection;
import cc.xingyan.android.afc.provider.workorder.WorkOrderSelection;
import cc.xingyan.android.afc.util.IMEIUtil;
import cc.xingyan.android.afc.util.LogUtil;

/**
 * Created by 1 on 2015/10/22.
 */
public class SettingsActivity extends ThemeActivity {
    private static final String TAG = "Setting";
    static String updataVersionUrl;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.content, new SettingsFragment())
                    .commit();
        }
    }

    public static class SettingsFragment extends PreferenceFragment {

        private static final String[] PERMISSIONS_WRITE_EXTERNAL_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        public static final int REQUEST_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 0;


        @Inject
        ChangePassword mChangePassword;
        @Inject
        DayInspectService dayInspectService;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Dagger.inject(this);
            addPreferencesFromResource(R.xml.settings);
        }

        @Override public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            final Preference pref = findPreference("updates");
            final Preference clcPref = findPreference("clearLocalCache");
            final Preference getObjIdPref = findPreference("getObjId");

            if ("REAL8833".equals(BuildConfig.FLAVOR)) {
                pref.setSummary(getString(R.string.format_current_version_real8833, BuildConfig.VERSION_NAME));
            } else if ("TEST8832".equals(BuildConfig.FLAVOR))  {
                pref.setSummary(getString(R.string.format_current_version_test8832, BuildConfig.VERSION_NAME));
            } else if ("DEV8831".equals(BuildConfig.FLAVOR))  {
                pref.setSummary(getString(R.string.format_current_version_develop8831, BuildConfig.VERSION_NAME));
            } else {
                pref.setSummary(getString(R.string.format_current_version_develop8830, BuildConfig.VERSION_NAME));
            }
            pref.setOnPreferenceClickListener(p -> {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int versionCode = BuildConfig.VERSION_CODE;

                        String versionType = "";
                        if ("REAL8833".equals(BuildConfig.FLAVOR)) {
                            versionType = "REAL";
                        } else if ("TEST8832".equals(BuildConfig.FLAVOR)) {
                            versionType = "TEST";
                        } else if ("DEV8831".equals(BuildConfig.FLAVOR)) {
                            versionType = "DEV";
                        } else {
                            versionType = "DEV";
                        }

//                        TelephonyManager tm = (TelephonyManager) getActivity().getSystemService(getActivity().TELEPHONY_SERVICE);
                        String imei = IMEIUtil.getIMEI(getActivity());


                        NewVersion newVersion = new NewVersion();
                        newVersion.setCurrectVersion(Integer.toString(versionCode));
                        newVersion.setCurrentVersionType(versionType);
                        newVersion.setImei(imei);

                        NewVersions newVersions = new NewVersions();
                        ArrayList<NewVersion> newVersionList = new ArrayList<NewVersion>();
                        newVersionList.add(newVersion);
                        newVersions.setNewVersionList(newVersionList);

                        mChangePassword.getNewVersion(newVersions).subscribe(resp -> {
                            LogUtil.info("NewVersions", ">>>>>>>>>>>>>>>>>>>>>>>OK!");
                            if (resp.size() > 0) {
                                NewVersion newVersionBack = resp.get(0);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("newVersionInfo", newVersionBack);

                                Message msg = new Message();
                                msg.setData(bundle);
                                myHandler.sendMessage(msg);
                            }
                        }, e -> {
                            LogUtil.info("NewVersions", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Fail!" + e);
                        });
                    }
                }).start();

                return true;
            });

            clcPref.setOnPreferenceClickListener(p -> {

                showClearDialog();
                return true;
            });

            getObjIdPref.setOnPreferenceClickListener(p -> {

                new AlertDialog.Builder(getActivity())
                        .setIcon(R.drawable.ic_info_black_24dp)
                        .setTitle("温馨提示")
                        .setMessage("该功能仅对日巡检有效！\n 日巡检监测信息没有巡检ID的请点击获取巡检ID！")
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getObjInfo();
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .setCancelable(false)
                        .show();

                return true;
            });
        }


        String[] mlistText = { "全选", "快速工单", "CM工单", "PM工单", "日巡检"};
        boolean[] bl = { false, false, false, false, false};
        ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
        SetSimpleAdapter adapter;

        private void showClearDialog() {

            if(mData.size() > 0){
                mData.clear();
            }
            for (int i = 0; i < mlistText.length; i++) {

                bl[i] = false;

                Map<String, Object> item = new HashMap<String, Object>();
                item.put("order_type", mlistText[i]);
                mData.add(item);
            }

            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View getlistview = inflater.inflate(R.layout.layout4settingclear, null);

            ListView listview = (ListView) getlistview.findViewById(R.id.type_listview);
            adapter = new SetSimpleAdapter(getActivity(), mData, R.layout.layout4settingclearitem, new String[] { "order_type" },
                    new int[] { R.id.type_item_text });
            listview.setAdapter(adapter);
            listview.setItemsCanFocus(false);
            listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            listview.setOnItemClickListener(new ItemOnClick());

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("请选择要清除数据的工单类型");
            builder.setIcon(R.drawable.ic_info_black_24dp);
            builder.setView(getlistview);
            builder.setPositiveButton("确定", new DialogOnClick());
            builder.setNegativeButton("取消", new DialogOnClick());
            builder.create().show();
        }

        class SetSimpleAdapter extends SimpleAdapter {

            public SetSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from,
                                    int[] to) {
                super(context, data, resource, from, to);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LinearLayout.inflate(getActivity(), R.layout.layout4settingclearitem, null);
                }
                CheckBox ckBox = (CheckBox) convertView.findViewById(R.id.type_checkbox);
                if (bl[position] == true) {
                    ckBox.setChecked(true);
                } else if (bl[position] == false) {
                    ckBox.setChecked(false);
                }
                return super.getView(position, convertView, parent);
            }
        }

        class ItemOnClick implements AdapterView.OnItemClickListener {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                CheckBox cBox = (CheckBox) view.findViewById(R.id.type_checkbox);
                if (cBox.isChecked()) {
                    cBox.setChecked(false);
                } else {
                    Log.i("TAG", "取消该选项");
                    cBox.setChecked(true);
                }


                if (position == 0 && (cBox.isChecked())) {
                    for (int i = 0; i < bl.length; i++) {
                        bl[i] = true;
                    }
                    adapter.notifyDataSetChanged();
                } else if (position == 0 && (!cBox.isChecked())) {
                    for (int i = 0; i < bl.length; i++) {
                        bl[i] = false;
                    }
                    adapter.notifyDataSetChanged();
                }
                if (position != 0 && (!cBox.isChecked())) {
                    bl[0] = false;
                    bl[position] = false;
                    adapter.notifyDataSetChanged();
                } else if (position != 0 && (cBox.isChecked())) {
                    bl[position] = true;
                    int a = 0;
                    for (int i = 1; i < bl.length; i++) {
                        if (bl[i] == false) {
                            break;
                        } else {
                            a++;
                            if (a == bl.length - 1) {
                                bl[0] = true;
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
            }
        }


        class DialogOnClick implements DialogInterface.OnClickListener {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case Dialog.BUTTON_POSITIVE:
                        if(bl[0] == true){

                            new WorkOrderSelection().delete(getActivity().getContentResolver());

                            new CmWorkSelection().delete(getActivity().getContentResolver());

                            new PmifsWorkSelection().delete(getActivity().getContentResolver());


                            new DiifsWorkSelection().delete(getActivity().getContentResolver());
                            new DiifsPmItemSelection().delete(getActivity().getContentResolver());
                            new DiifsPmSelection().delete(getActivity().getContentResolver());
                            new DiifsInfoSelection().delete(getActivity().getContentResolver());


                            Toast.makeText(getActivity(), "清除了全部工单数据!", Toast.LENGTH_SHORT).show();
                        }else {
                            if(bl[1] == true){

                                new WorkOrderSelection().delete(getActivity().getContentResolver());

                                Toast.makeText(getActivity(), "清除了快速工单数据!", Toast.LENGTH_SHORT).show();
                            }
                            if(bl[2] == true){

                                new CmWorkSelection().delete(getActivity().getContentResolver());

                                Toast.makeText(getActivity(), "清除了CM工单数据!", Toast.LENGTH_SHORT).show();
                            }
                            if(bl[3] == true){

                                new PmifsWorkSelection().delete(getActivity().getContentResolver());

                                Toast.makeText(getActivity(), "清除了PM工单数据!", Toast.LENGTH_SHORT).show();
                            }
                            if(bl[4] == true){

                                new DiifsWorkSelection().delete(getActivity().getContentResolver());
                                new DiifsPmItemSelection().delete(getActivity().getContentResolver());
                                new DiifsPmSelection().delete(getActivity().getContentResolver());
                                new DiifsInfoSelection().delete(getActivity().getContentResolver());

                                Toast.makeText(getActivity(), "清除了日巡检数据!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        if(bl[0] == false && bl[1] == false && bl[2] == false && bl[3] == false && bl[4] == false){
                            Toast.makeText(getActivity(), "没有选择任何一类工单！", Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case Dialog.BUTTON_NEGATIVE:
                        break;
                    default:
                        break;
                }

                getActivity().finish();
            }
        }


        private ProgressDialog progress;
        private void getObjInfo(){

            progress = new ProgressDialog(getActivity());
            progress.setCancelable(false);
            progress.setCanceledOnTouchOutside(false);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIcon(R.drawable.ic_settings_white_24dp);
            progress.setTitle("获取信息");
            progress.setMessage("正在下载...");
            progress.setIndeterminate(false);
            progress.setButton(DialogInterface.BUTTON_POSITIVE, "取消",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            progress.dismiss();
                        }

                    });
            progress.show();


            List<DayInspectWorkPMOnly> needDownloadPmInfos = new ArrayList<DayInspectWorkPMOnly>();

            DiifsPmSelection diifsPmSelection = new DiifsPmSelection();
            DiifsPmCursor diifsPmCursor = diifsPmSelection.query(getActivity().getContentResolver());
            try {
                while(diifsPmCursor.moveToNext()){
                    DayInspectWorkPMOnly pmOnly = new DayInspectWorkPMOnly();
                    pmOnly.setPmNo(diifsPmCursor.getPmno());
                    pmOnly.setWoNo(diifsPmCursor.getWono());
                    pmOnly.setPhysicCode(diifsPmCursor.getPhysiccode());
                    needDownloadPmInfos.add(pmOnly);
                }
            } finally {
                diifsPmCursor.close();
            }


            DayInspectWorkPMOnlys dayInspectWorkPMOnlys = new DayInspectWorkPMOnlys();
            dayInspectWorkPMOnlys.setDiwPMs(needDownloadPmInfos);

            new Thread(new Runnable() {
                @Override
                public void run() {

                    dayInspectService.getDayInspectWorkObjInfo(dayInspectWorkPMOnlys).subscribe(works -> {
                        for (DayInspectWorkObjInfoReturn obj : works) {

                            new DiifsPmItemContentValues()
                                    .putObjid(obj.getObjId())
                                    .putObjversion(obj.getObjVersion())
                                    .update(getActivity().getContentResolver(), new DiifsPmItemSelection().pmno(obj.getPmNo())
                                            .and()
                                            .wono(obj.getWoNo())
                                            .and().
                                                    testpointid(obj.getTestPointId())
                                            .and()
                                            .parametercode(obj.getParameterCode()));


                            new DiifsWorkContentValues()
                                    .putObjid(obj.getObjId())
                                    .putObjversion(obj.getObjVersion())
                                    .putPhysiccode(obj.getPhysicCode())
                                    .update(getActivity().getContentResolver(), new DiifsWorkSelection().pmno(obj.getPmNo())
                                            .and()
                                            .wono(obj.getWoNo())
                                            .and().
                                                    testpointid(obj.getTestPointId())
                                            .and()
                                            .parametercode(obj.getParameterCode()));
                        }
                        progress.dismiss();
                    }, e -> {
                        progress.dismiss();
                        LogUtil.debug(TAG, "Failed to download day inspect  Obj");
                    });

                }
            }).start();

        }

        private Runnable showUpdate = new Runnable() {
            public void run() {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(updataVersionUrl));
                startActivity(intent);

            }
        };

        Handler myHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                NewVersion newVersionBack = (NewVersion) bundle.get("newVersionInfo");

                String updateFlag = newVersionBack.getUpdateFlag();
                String url = newVersionBack.getUrl();
                String updateContent = newVersionBack.getUpdateContent();

                if(updateFlag.contains("1")){
                    new AlertDialog.Builder(getActivity()).setTitle("更新提示").setMessage(getString(R.string.no_updates))
                            .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {


                                }
                            }).setCancelable(false)
                            .show();
                }else if(updateFlag.contains("2")){
                    new AlertDialog.Builder(getActivity()).setTitle("更新提示").setMessage(updateContent)
                            .setPositiveButton(getString(R.string.update_now), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {

                                    Handler versionHandler = new Handler();
                                    updataVersionUrl = url;
                                    versionHandler.post(showUpdate);

                                }
                            }).setNegativeButton(getString(R.string.later), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    }).setCancelable(false)
                            .show();
                }else if(updateFlag.contains("3")){
                    new AlertDialog.Builder(getActivity()).setTitle("重要更新").setMessage(updateContent)
                            .setPositiveButton(getString(R.string.update_now), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {

                                    Handler versionHandler = new Handler();
                                    updataVersionUrl = url;
                                    versionHandler.post(showUpdate);
                                    MainActivity.isMustExit = true;
                                    getActivity().finish();
                                }
                            }).setCancelable(false)
                            .show();
                }
            }
        };



        @TargetApi(Build.VERSION_CODES.M) @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            switch (requestCode) {
                default:
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                    break;
                case REQUEST_PERMISSIONS_WRITE_EXTERNAL_STORAGE:
                    if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    }
                    break;
            }
        }

    }

}
