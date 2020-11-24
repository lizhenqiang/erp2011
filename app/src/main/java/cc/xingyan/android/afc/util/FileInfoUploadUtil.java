package cc.xingyan.android.afc.util;


import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;

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
 * Created by YuJiadeng on 2017/7/5.
 *
 */
public class FileInfoUploadUtil{
    private MyTask mTask;
    Context mContext;

    private String filePath;
    private String uploadUrl;


        public FileInfoUploadUtil(String filePath, String uploadUrl, Context mContext) {

            this.filePath = filePath;
            this.uploadUrl = uploadUrl;
            this.mContext = mContext;
    }

    public void uploadFile(){
        File file = new File(filePath);
        if(file.exists() && file.length()>0){
            mTask = new MyTask();
            mTask.execute(filePath, uploadUrl);
        }else{

        }
    }

    private class MyTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPostExecute(String result) {

            String resultStr = "";
            if(result.equals("0")){
                resultStr = "上传成功！";

                File delFile = new File(filePath);
                delFile.delete();

            }else if(result.equals("1")){
                resultStr = "上传失败！";
            }

        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
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
                dos.flush();

                InputStream is = httpURLConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "utf-8");
                BufferedReader br = new BufferedReader(isr);


                dos.close();
                is.close();


                ContentValues[] values = new ContentValues[1];
                values[0] = new PictureContentValues()
                        .putKeyId("110")
                        .putType("crash")
                        .putRemark("crash_log")
                        .putPictureId(filePath).values();
                mContext.getContentResolver().bulkInsert(PictureColumns.CONTENT_URI, values);

                return "0";
            }catch (Exception e) {
                e.printStackTrace();
                return "1";
            }
        }
    }
}
