/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.io.File;

import cc.xingyan.android.afc.photoutil.CompressUtil;
import cc.xingyan.android.afc.photoutil.PicUploadUtil;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkCursor;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkStatus;
import cc.xingyan.android.afc.util.Action;
import cc.xingyan.android.afc.util.NetUtil;

/**
 * Created by San on 10/13/15.
 */
public class PmWorkActivity extends ThemeActivity {
    public static final String EXTRA_PM_WORK = "pm_work_uri";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            final Intent intent = getIntent();
            final Uri uri = intent.getParcelableExtra(EXTRA_PM_WORK);
            final PmifsWorkCursor cur = new PmifsWorkCursor(getContentResolver().query(uri, null, null, null, null));
            try {
                Fragment fragment;
                if (cur.moveToNext()) {
                    if(PmifsWorkStatus.NEW == cur.getStatus()){
                        fragment = PmWorkNewFragment.newInstance(uri);
                    }else if(PmifsWorkStatus.RELEASED == cur.getStatus()){
                        fragment = PmWorkEditorFragment.newInstance(uri);
                    }else if (PmifsWorkStatus.WORKDONEUPLOAD == cur.getStatus()) {
                        fragment = PmWorkUploadedFragment.newInstance(uri);
                    } else {
                        fragment = PmWorkEditorFragment.newInstance(uri);
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
        }
    }

    private void showNotFoundDialog() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_info_black_24dp)
                .setTitle("Oops!")
                .setMessage(R.string.message_pm_job_not_found)
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    finish();
                })
                .show();
    }


    @Override public void finish() {
        final Action finish = new Action() {
            @Override public String name() {
                return "finish";
            }

            @Override public void proceed() {
                PmWorkActivity.super.finish();
            }
        };

        for (Fragment f : getSupportFragmentManager().getFragments()) {
            if (f instanceof Action.Interceptor && ((Action.Interceptor) f).onInterceptAction(finish)) {
                return;
            }
        }

        finish.proceed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            dealBackData(PmWorkEditorImageBeforeWorkFragment.pictureUri, requestCode);
        }else if(requestCode == 1){
            dealBackData(PmWorkEditorImageAfterWorkFragment.pictureUri, requestCode);
        }
    }


    private void dealBackData(Uri uri, int requestCode){
        if (uri != null) {
            final String uriPath = uri.getPath();
            final File file = new File(uriPath);



            new Thread(new Runnable() {
                @Override
                public void run() {
                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
                }
            }).start();


            if(file.exists() && file.length() > 0){
                String[] ps = { "立即上传", "暂不上传" };
                new AlertDialog.Builder(PmWorkActivity.this).setTitle("文件上传选项")
                        .setItems(ps, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {

                                    progress = new ProgressDialog(PmWorkActivity.this);
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
                                                    new CompressUtil(PmWorkActivity.this, Uri.parse("file://" + uriPath));


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
                                                msg.arg1 = requestCode;
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
                int beforeOrAfter = msg.arg1;

                String upFileAdd = null;
                long current = System.currentTimeMillis();

                String keyID = PmWorkEditorImageFragment.mPMOrderId;

                upFileAdd = "no_add_info_camera";

                String type = "P";

                String dateT = current + "";

                String remark = beforeOrAfter + "";

                String serverPath = NetUtil.getUploadFileServerPath(keyID, type, upFileAdd, dateT, remark);

                String uriPath = "";
                if(beforeOrAfter == 0){
                    uriPath = PmWorkEditorImageBeforeWorkFragment.pictureUri.getPath();
                }else if(beforeOrAfter == 1){
                    uriPath = PmWorkEditorImageAfterWorkFragment.pictureUri.getPath();
                }


                progress.dismiss();
                try {
                    postFile(keyID, remark, compressorPath, uriPath, serverPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else if(msg.what == 0x003){
                progress.dismiss();
                new AlertDialog.Builder(PmWorkActivity.this)
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

    public void postFile(String keyID, String remark, String compressorPath,String originalPath, String uploadUrl) throws Exception{
        PicUploadUtil picUploadUtil = new PicUploadUtil(keyID, "P", remark,  compressorPath, originalPath, uploadUrl, getApplicationContext().getContentResolver(), PmWorkActivity.this);
        picUploadUtil.uploadFile();
    }
}
