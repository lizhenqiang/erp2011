package cc.xingyan.android.afc;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


import androidx.fragment.app.Fragment;

import java.io.File;

import cc.xingyan.android.afc.photoutil.CompressUtil;
import cc.xingyan.android.afc.photoutil.PicUploadUtil;
import cc.xingyan.android.afc.provider.cmwork.CmWorkCursor;
import cc.xingyan.android.afc.provider.cmwork.CmWorkStatus;
import cc.xingyan.android.afc.util.Action;
import cc.xingyan.android.afc.util.NetUtil;
import cc.xingyan.android.afc.util.TService;

/**
 * Created by 1 on 2015/11/26.
 */
public class CmWorkActivity extends ThemeActivity {
    public static final String EXTRA_CM_WORK = "cm_work_uri";
    private int TYPE_CM_WORK_NEW = 0;
    private int TYPE_CM_WORK_QUERY = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            final Intent intent = getIntent();
            final Uri uri = intent.getParcelableExtra(EXTRA_CM_WORK);
            Bundle bundle = intent.getExtras();
            String info = bundle.getString("deviceID");
            int type = bundle.getInt("type");
            if (type == TYPE_CM_WORK_QUERY) {
                final CmWorkCursor cur = new CmWorkCursor(getContentResolver().query(uri, null, null, null, null));
                try {
                    Fragment fragment;
                    if (cur.moveToNext()) {
                        if (CmWorkStatus.WORKDONEUPLOAD == cur.getStatus()) {
                            fragment = CmWorkReportFragment.newInstance(uri);
                        } else if (CmWorkStatus.FINISH == cur.getStatus()) {
                            fragment = CmWorkDeleteFragment.newInstance(uri);
                        } else if (CmWorkStatus.FAULTREPORT == cur.getStatus()) {
                            fragment = CmWorkEditerConfirmFragment.newInstance(uri);
                        }else {
                            fragment = CmWorkEditerFragment.newInstance(uri, info);
                        }

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content, fragment)
                                .commit();
                    } else {
                        showNotFoundDialog();
                    }
                } finally {
                    cur.close();
                }
            } else {
                Fragment fragment = CmWorkEditerNewFragment.newInstance(uri, info);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
            }
        }
    }

    private void showNotFoundDialog() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_info_black_24dp)
                .setTitle("Oops!")
                .setMessage(R.string.message_cm_job_not_found)
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    finish();
                })
                .show();
    }


    @Override
    public void finish() {
        final Action finish = new Action() {
            @Override
            public String name() {
                return "finish";
            }

            @Override
            public void proceed() {
                CmWorkActivity.super.finish();
            }
        };

        for (Fragment f : getSupportFragmentManager().getFragments()) {
            if (f instanceof Action.Interceptor && ((Action.Interceptor) f).onInterceptAction(finish)) {
                return;
            }
        }

        finish.proceed();
    }


    static int count;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if (CmReportFragment.pictureUri != null) {
            final String uriPath = CmReportFragment.pictureUri.getPath();
            final File file = new File(uriPath);


            new Thread(new Runnable() {
                @Override
                public void run() {
                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, CmReportFragment.pictureUri));
                }
            }).start();


            if(file.exists() && file.length() > 0){
                String[] ps = { "立即上传", "暂不上传" };
                new AlertDialog.Builder(CmWorkActivity.this).setTitle("文件上传选项")
                        .setItems(ps, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {

                                    progress = new ProgressDialog(CmWorkActivity.this);
                                    progress.setCancelable(false);
                                    progress.setCanceledOnTouchOutside(false);
                                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    progress.setMessage("请稍后...");
                                    progress.setIndeterminate(false);
                                    progress.show();

                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {

                                            String compressorPath = "";
                                            long compressorStartTime = System.currentTimeMillis();

                                            CompressUtil compressUtil =
                                                    new CompressUtil(CmWorkActivity.this, Uri.parse("file://" + uriPath));


                                            do {
                                                compressorPath = compressUtil.getCompressorPath();
                                                long compressorUseTime = System.currentTimeMillis() - compressorStartTime;
                                                if (compressorUseTime >= 5 * 1000) {
                                                    break;
                                                }
                                            }while (compressorPath == null || compressorPath.length() <= 0);


                                            if (compressorPath != null && compressorPath.length() > 0) {
                                                Message msg = new Message();
                                                msg.obj = compressorPath;
                                                msg.what = 0x002;
                                                myHandler.sendMessage(msg);
                                            } else {
                                                Message msg = new Message();
                                                msg.what = 0x003;
                                                myHandler.sendMessage(msg);

                                            }
                                        }
                                    }).start();


                                }
                            }
                        }).setCancelable(false)
                        .show();
            }

        }

    }


    ProgressDialog progress;
    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0x002){
                String compressorPath = (String)msg.obj;

                String upFileAdd = null;
                long current = System.currentTimeMillis();

                String keyID = CmReportFragment.mCmOrderId;

                String lat = TService.lat;
                String lon = TService.lon;
                if(lat != null && lon != null){
                    upFileAdd = lat + "_" + lon;
                }else{
                    upFileAdd = "no_add_info_camera";
                }

                String type = "C";

                String dateT = current + "";
                String remark = "no_remark_info_camera";
                String serverPath = NetUtil.getUploadFileServerPath(keyID, type, upFileAdd, dateT, remark);

                String uriPath = CmReportFragment.pictureUri.getPath();

                progress.dismiss();
                try {
                    postFile(keyID, compressorPath, uriPath, serverPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else if(msg.what == 0x003){
                progress.dismiss();
                new AlertDialog.Builder(CmWorkActivity.this)
                        .setTitle("温馨提示")
                        .setMessage("上传失败，请重试！")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setCancelable(false)
                        .show();
            }

        }
    };



    public void postFile(String keyID, String compressorPath,String originalPath, String uploadUrl) throws Exception{
        PicUploadUtil picUploadUtil = new PicUploadUtil(keyID, "C", "no_remark_info",  compressorPath, originalPath, uploadUrl, getApplicationContext().getContentResolver(), CmWorkActivity.this);
        picUploadUtil.uploadFile();
    }






}
