package cc.xingyan.android.afc.photoutil;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.SurfaceHolder;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FileRecorderUtil {
	MediaRecorder mediaRecorder;
	Camera mCamera;
	SurfaceHolder mHolder;

	public FileRecorderUtil(){

	}
	public String getFileName(int fileType, String mCmOrderId) {
		try {

			DateFormat formatter = new SimpleDateFormat("yyyyMMdd_HH-mm-ss");


			String MAIN_SAVE_FOLDER = Environment.
					getExternalStorageDirectory().getPath() +
					File.separator + "AFC_FC" + File.separator;

			String FILE_TYPE_FOLDER = MAIN_SAVE_FOLDER + fileType + File.separator;


			String FILE_SAVE_FOLDER = FILE_TYPE_FOLDER + mCmOrderId + File.separator;



			File mainPath = new File(MAIN_SAVE_FOLDER);
			if (!mainPath.exists()){
				mainPath.mkdir();
			}

			File fileTypePath = new File(FILE_TYPE_FOLDER);
			if(!fileTypePath.exists()){
				fileTypePath.mkdir();
			}

			File filePath = new File(FILE_SAVE_FOLDER);
			if(!filePath.exists()){
				filePath.mkdir();
			}

			return FILE_SAVE_FOLDER + formatter.format(new Date())+ "_" + mCmOrderId + ".jpg";

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public Uri cameraTakePic(Activity activity, String mCmOrderId,int remark) throws Exception{
		Uri uri = null;
		String str;
		if (activity != null) {
			Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

			File file = new File(getFileName(0, mCmOrderId));
			uri = Uri.fromFile(file);

			in.putExtra(MediaStore.EXTRA_OUTPUT, uri);
			activity.startActivityForResult(in, remark);
			return uri;
		} else {
			return null;
		}
	}

}
