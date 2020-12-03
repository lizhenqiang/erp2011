package cc.xingyan.android.afc.photoutil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import cc.xingyan.android.afc.provider.picture.PictureColumns;
import cc.xingyan.android.afc.provider.picture.PictureContentValues;

/**
 * Created by YuJiadeng on 2016/9/9.
 *
 */
public class PicUploadUtil {
    private String keyID;
    private String type;
    private String remark;
    private String compressorPath;          
    private String originalPath;              
    private String uploadUrl;
    ContentResolver contentResolver;
    private Activity activity;
    private ProgressDialog pd;
    private MyTask mTask;


    public PicUploadUtil(String keyID, String type, String remark, String compressorPath,String originalPath, String uploadUrl, ContentResolver contentResolver, Activity activity) {
        this.keyID = keyID;
        this.type = type;
        this.remark = remark;
        this.compressorPath = compressorPath;
        this.originalPath = originalPath;
        this.uploadUrl = uploadUrl;
        this.contentResolver = contentResolver;
        this.activity = activity;
    }

    public void uploadFile(){
        File file = new File(compressorPath);
        if(file.exists() && file.length()>0){
            mTask = new MyTask();
            mTask.execute(compressorPath, uploadUrl);
        }else{
            Toast.makeText(activity, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }

    private class MyTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPostExecute(String result) {

            String resultStr = "";
            if(result.equals("0")){
                resultStr = "上传成功！";

                File delFile = new File(compressorPath);
                delFile.delete();

                ContentValues[] values = new ContentValues[1];
                values[0] = new PictureContentValues()
                        .putKeyId(keyID)
                        .putType(type)
                        .putRemark(remark)
                        .putPictureId(originalPath).values();
                contentResolver.bulkInsert(PictureColumns.CONTENT_URI, values);

                String tempAction = "";
                if(type.equals("P") && remark.equals("0")){
                    tempAction = "receiver_pm_work_item_before_work_update_ok";
                }else if(type.equals("P") && remark.equals("1")){
					tempAction = "receiver_pm_work_item_after_work_update_ok";
                }else{
                    tempAction = "update_ok";
                }

                Intent intent = new Intent();
                intent.setAction(tempAction);
                activity.sendBroadcast(intent);

            }else if(result.equals("1")){
                resultStr = "上传失败！";
            }

            pd.dismiss();
            new AlertDialog.Builder(activity)
                    .setTitle("温馨提示")
                    .setMessage(resultStr)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setCancelable(false)
                    .show();


        }

        @Override
        protected void onPreExecute() {

            pd = new ProgressDialog(activity);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setMessage("上传文件中...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            pd.setProgress((int) (values[0]));
        }

        @Override
        protected String doInBackground(String... params) {
            String filePath = params[0];
            String uploadUrl = params[1];
            String end = "\r\n";
            String twoHyphens = "--";
            String boundary = UUID.randomUUID().toString();
            try {
                URL url = new URL(uploadUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setConnectTimeout(6 * 1000);
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");

                DataOutputStream dos = new DataOutputStream(httpURLConnection.getOutputStream());


                FileInputStream fis = new FileInputStream(filePath);
                long total = fis.available();
                String totalstr = String.valueOf(total);
                byte[] buffer = new byte[1024];
                int count = 0;
                int length = 0;
                while ((count = fis.read(buffer)) != -1) {
                    dos.write(buffer, 0, count);
                    length += count;
                    publishProgress((int) ((length / (float) total) * 100));

                }
                fis.close();
                dos.writeBytes(end);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + end);
                dos.flush();

                InputStream is = httpURLConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "utf-8");
                BufferedReader br = new BufferedReader(isr);


                dos.close();
                is.close();
                return "0";
            }catch (Exception e) {
                e.printStackTrace();
                return "1";
            }
        }
    }
}
