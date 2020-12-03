package cc.xingyan.android.afc.photoutil;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 */
public class LGImgCompressor {
    private static LGImgCompressor instance = null;
    private Context context;
    private CompressListener compressListener;
    private static final int DEFAULT_OUTWIDTH = 720;
    private static final int DEFAULT_OUTHEIGHT = 1080;
    private static final int DEFAULT_MAXFILESIZE = 1024;

    private LGImgCompressor(Context context) {
        this.context = context;
    }

    public static LGImgCompressor getInstance(Context context) {
        if (instance == null) {
            synchronized (LGImgCompressor.class) {
                if (instance == null)
                    instance = new LGImgCompressor(context.getApplicationContext());
            }
        }
        return instance;
    }

    public LGImgCompressor withListener(CompressListener compressListener) {
        this.compressListener = compressListener;
        return this;
    }

    private String getFilePathFromUri(String uri) {
        Uri pathUri = Uri.parse(uri);
        Cursor cursor = context.getContentResolver().query(pathUri, null, null, null, null);
        if (cursor == null) {
            return pathUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            String str = cursor.getString(index);
            cursor.close();
            return str;
        }
    }

    public String compressImage(String srcImageUri, int outWidth, int outHeight, int maxFileSize) {
        String srcImagePath = getFilePathFromUri(srcImageUri);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(srcImagePath, options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;
        float maxWidth = outWidth;
        float maxHeight = outHeight;
        float srcRatio = srcWidth / srcHeight;
        float outRatio = maxWidth / maxHeight;
        float actualOutWidth = srcWidth;
        float actualOutHeight = srcHeight;

        if (srcWidth > maxWidth || srcHeight > maxHeight) {

            if (srcRatio < outRatio) {
                actualOutHeight = maxHeight;
                actualOutWidth = actualOutHeight * srcRatio;
            } else if (srcRatio > outRatio) {
                actualOutWidth = maxWidth;
                actualOutHeight = actualOutWidth / srcRatio;
            } else {
                actualOutWidth = maxWidth;
                actualOutHeight = maxHeight;
            }
        }
        options.inSampleSize = computSampleSize(options, actualOutWidth, actualOutHeight);
        options.inJustDecodeBounds = false;
        Bitmap scaledBitmap = null;
        try {
            scaledBitmap = BitmapFactory.decodeFile(srcImagePath, options);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        if (scaledBitmap == null) {
            return null;
        }
        Bitmap actualOutBitmap = Bitmap.createScaledBitmap(scaledBitmap, (int) actualOutWidth, (int) actualOutHeight, true);
        if(actualOutBitmap != scaledBitmap)
            scaledBitmap.recycle();

        ExifInterface exif = null;
        try {
            exif = new ExifInterface(srcImagePath);
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Matrix matrix = new Matrix();
            if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                matrix.postRotate(90);
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
                matrix.postRotate(180);
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                matrix.postRotate(270);
            }
            actualOutBitmap = Bitmap.createBitmap(actualOutBitmap, 0, 0,
                    actualOutBitmap.getWidth(), actualOutBitmap.getHeight(), matrix, true);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options_ = 100;
        actualOutBitmap.compress(Bitmap.CompressFormat.JPEG, options_, baos);

        int baosLength = baos.toByteArray().length;

        while (baosLength / 1024 > maxFileSize) {
            baos.reset();
            options_ = Math.max(0, options_ - 10);
            actualOutBitmap.compress(Bitmap.CompressFormat.JPEG, options_, baos);
            baosLength = baos.toByteArray().length;
            if (options_ == 0)
                break;
        }
        actualOutBitmap.recycle();

        FileOutputStream fos = null;
        String filePath = getOutputFileName(srcImagePath);
        try {
            fos = new FileOutputStream(filePath);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fos);
            bufferedOutputStream.write(baos.toByteArray());
            bufferedOutputStream.flush();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return filePath;
    }

    private int computSampleSize(BitmapFactory.Options options, float reqWidth, float reqHeight) {
        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;
        int sampleSize = 1;
        if (srcWidth > reqWidth || srcHeight > reqHeight) {
            int withRatio = Math.round(srcWidth / reqWidth);
            int heightRatio = Math.round(srcHeight / reqHeight);
            sampleSize = Math.min(withRatio, heightRatio);
        }
        return sampleSize;
    }

    private String getOutputFileName(String srcFilePath) {
        File srcFile = new File(srcFilePath);
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "AFC_FC/TempImages");
        if (!file.exists()) {
            file.mkdirs();
        }
        String oldNameString = srcFile.getName();
        String uriSting = file.getAbsolutePath() + File.separator + oldNameString;
        return uriSting;
    }

    public void starCompress(String srcImageUri, int outWidth, int outHeight, int maxFileSize) {
        new CompressTask().execute(srcImageUri, "" + outWidth, "" + outHeight, "" + maxFileSize);
    }

    public void starCompressWithDefault(String srcImageUri) {
        new CompressTask().execute(srcImageUri, "" + DEFAULT_OUTWIDTH, "" + DEFAULT_OUTHEIGHT, "" + DEFAULT_MAXFILESIZE);
    }

    public static class CompressResult implements Parcelable{
        public static final int RESULT_OK = 0;
        public static final int RESULT_ERROR = 1;
        private int status = RESULT_OK;
        private String srcPath;
        private String outPath;

        public CompressResult(){

        }

        protected CompressResult(Parcel in) {
            status = in.readInt();
            srcPath = in.readString();
            outPath = in.readString();
        }

        public static final Creator<CompressResult> CREATOR = new Creator<CompressResult>() {
            @Override
            public CompressResult createFromParcel(Parcel in) {
                return new CompressResult(in);
            }

            @Override
            public CompressResult[] newArray(int size) {
                return new CompressResult[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(status);
            dest.writeString(srcPath);
            dest.writeString(outPath);
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getSrcPath() {
            return srcPath;
        }

        public void setSrcPath(String srcPath) {
            this.srcPath = srcPath;
        }

        public String getOutPath() {
            return outPath;
        }

        public void setOutPath(String outPath) {
            this.outPath = outPath;
        }
    }
    public interface CompressListener {
        void onCompressStart();

        void onCompressEnd(CompressResult imageOutPath);
    }

    private class CompressTask extends AsyncTask<String, Void, CompressResult> {

        @Override
        protected CompressResult doInBackground(String... params) {
            String path = params[0];
            int outWidth = Integer.parseInt(params[1]);
            int outHeight = Integer.parseInt(params[2]);
            int maxFileSize = Integer.parseInt(params[3]);
            CompressResult compressResult = new CompressResult();
            String outPutPath = null;
            try {
                outPutPath = compressImage(path, outWidth, outHeight, maxFileSize);
            }catch (Exception e){
            }
            compressResult.setSrcPath(path);
            compressResult.setOutPath(outPutPath);
            if(outPutPath == null){
                compressResult.setStatus(CompressResult.RESULT_ERROR);
            }
            return compressResult;
        }

        @Override
        protected void onPreExecute() {
            if (compressListener != null) {
                compressListener.onCompressStart();
            }
        }

        @Override
        protected void onPostExecute(CompressResult compressResult) {
            if (compressListener != null) {
                compressListener.onCompressEnd(compressResult);
            }
        }
    }
}
