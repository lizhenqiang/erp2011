package cc.xingyan.android.afc.photoutil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import cc.xingyan.android.afc.CmReportFragment;
import cc.xingyan.android.afc.DayInspectWorkPmItemImgFragment;
import cc.xingyan.android.afc.PmWorkEditorImageAfterWorkFragment;
import cc.xingyan.android.afc.PmWorkEditorImageBeforeWorkFragment;
import cc.xingyan.android.afc.R;
import cc.xingyan.android.afc.util.NetUtil;
import cc.xingyan.android.afc.util.TService;

/**
 * @ClassName: ShowRecordFileActivity
 * @author YuJiadeng
 * @date 2014-7-8 上午10:23:01
 *
 */
public class ShowRecordFileActivity extends Activity {
	int fileType;
	String keyID;
	String type;
	String remark;

	String filesPath;
	String fileAbsolutePath;
	TextView fileTotalCount;
	TextView uploadedCountTextView;
	ListView showRecordListView;

	ShowRecordAdapter showRecordAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.show_record);

		fileTotalCount = (TextView) findViewById(R.id.file_total_count);
		uploadedCountTextView = (TextView) findViewById(R.id.uploaded_count_show_record);
		showRecordListView = (ListView) findViewById(R.id.show_record);

		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("showFilesInfo");
		fileType = bundle.getInt("fileType");
		keyID = bundle.getString("keyID");
		type = bundle.getString("type");
		remark = bundle.getString("remark");
		filesPath = bundle.getString("filesDir");

		if(type.equals("P") && remark.equals("0")){
			IntentFilter iFilter = new IntentFilter();
			iFilter.addAction("receiver_pm_work_item_before_work_update_ok");
			registerReceiver(receiver, iFilter);
		}else if(type.equals("P") && remark.equals("1")){
			IntentFilter iFilter = new IntentFilter();
			iFilter.addAction("receiver_pm_work_item_after_work_update_ok");
			registerReceiver(receiver, iFilter);
		}else{
			IntentFilter iFilter = new IntentFilter();
			iFilter.addAction("update_ok");
			registerReceiver(receiver, iFilter);
		}


	}

	private BroadcastReceiver receiver = new BroadcastReceiver() {
		public void onReceive(Context context,
                              Intent intent) {

			try {
				Message msg = new Message();
				msg.what = 0x123;
				myHandler.sendMessage(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	Handler myHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what == 0x123){

				if(type.equals("P") && remark.equals("0")){
					uploadedCountTextView.setText(PmWorkEditorImageBeforeWorkFragment.uploadedCount + "");
				}else if(type.equals("P") && remark.equals("1")){
					uploadedCountTextView.setText(PmWorkEditorImageAfterWorkFragment.uploadedCount + "");
				}else if(type.equals("C")){
					uploadedCountTextView.setText(CmReportFragment.uploadedCount + "");
				}else if(type.equals("P") && remark.equals("D")){
					uploadedCountTextView.setText(DayInspectWorkPmItemImgFragment.uploadedCount + "");
				}
			}
		}
	};

	@Override
	protected void onResume() {

		super.onResume();

		if(type.equals("P") && remark.equals("0")){
			uploadedCountTextView.setText(PmWorkEditorImageBeforeWorkFragment.uploadedCount + "");
		}else if(type.equals("P") && remark.equals("1")){
			uploadedCountTextView.setText(PmWorkEditorImageAfterWorkFragment.uploadedCount + "");
		}else if(type.equals("C")){
			uploadedCountTextView.setText(CmReportFragment.uploadedCount + "");
		}else if(type.equals("P") && remark.equals("D")){
			uploadedCountTextView.setText(DayInspectWorkPmItemImgFragment.uploadedCount + "");
		}

		File file = new File(filesPath);
		final File[] files = file.listFiles();

		try {
			Arrays.sort(files, new CompratorFilesByLastModified());
		} catch (Exception e) {
			e.printStackTrace();
		}
		fileTotalCount.setText(files.length + "");
		showRecordAdapter = new ShowRecordAdapter(fileType, files, ShowRecordFileActivity.this);
		showRecordListView.setAdapter(showRecordAdapter);


		showRecordListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {

				String[] ps = {"预览", "上传", "删除"};
				fileAbsolutePath = files[position].getAbsolutePath();


				new AlertDialog.Builder(ShowRecordFileActivity.this).setTitle("文件处理")
						.setItems(ps, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								if (which == 0) {
									if (fileAbsolutePath.endsWith("jpg")) {
										Intent intent = new Intent(Intent.ACTION_VIEW);
										intent.setDataAndType(Uri.parse("file://" + fileAbsolutePath),
												"image/*");
										startActivity(intent);
									} else {
										Intent intent = new Intent(Intent.ACTION_VIEW);
										intent.setDataAndType(Uri.parse("file://" + fileAbsolutePath),
												"video/*");
										startActivity(intent);
									}


								} else if (which == 1) {
									int uploadedCount = 0;
									if(type.equals("P") && remark.equals("0")){
										uploadedCount = PmWorkEditorImageBeforeWorkFragment.uploadedCount;
									}else if(type.equals("P") && remark.equals("1")){
										uploadedCount = PmWorkEditorImageAfterWorkFragment.uploadedCount;
									}else if(type.equals("C")){
										uploadedCount = CmReportFragment.uploadedCount;
									}else if(type.equals("P") && remark.equals("D")){
										uploadedCount = DayInspectWorkPmItemImgFragment.uploadedCount;
									}

									if (uploadedCount >= 9) {
										Toast.makeText(ShowRecordFileActivity.this, "上传图片已达最大数！", Toast.LENGTH_SHORT).show();
										return;
									} else {
										progress = new ProgressDialog(ShowRecordFileActivity.this);
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
														new CompressUtil(ShowRecordFileActivity.this, Uri.parse("file://" + fileAbsolutePath));


												do {
													compressorPath = compressUtil.getCompressorPath();
													long compressorUseTime = System.currentTimeMillis() - compressorStartTime;
//
													if (compressorUseTime >= 5 * 1000) {
														break;
													}
												}while (compressorPath == null || compressorPath.length() <= 0);


												if (compressorPath != null && compressorPath.length() > 0) {
													Message msg = new Message();
													msg.obj = compressorPath;
													msg.what = 0x002;
													myHandler2.sendMessage(msg);
												} else {
													Message msg = new Message();
													msg.what = 0x003;
													myHandler2.sendMessage(msg);
												}
											}
										}).start();

									}


								} else if (which == 2) {

									new AlertDialog.Builder(ShowRecordFileActivity.this)
											.setTitle("删除提示")
											.setMessage("删除的图片若是已上传的，本地将不可再查看！")
											.setPositiveButton("确定删除", new DialogInterface.OnClickListener() {
												@Override
												public void onClick(DialogInterface dialog, int which) {
													File delFile = new File(fileAbsolutePath);
													delFile.delete();



													new Thread(new Runnable() {

														@Override
														public void run() {

															Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
															Uri uri = Uri.fromFile(new File(fileAbsolutePath));
															intent.setData(uri);
															sendBroadcast(intent);
														}
													}).start();

													File file = new File(filesPath);
													File[] files = file.listFiles();
													if (files.length == 0) {
														progress = new ProgressDialog(ShowRecordFileActivity.this);
														progress.setCancelable(false);
														progress.setCanceledOnTouchOutside(false);
														progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
														progress.setMessage("请稍后...");
														progress.setIndeterminate(false);
														progress.show();

														new Thread(new Runnable() {

															@Override
															public void run() {

																try {
																	Thread.sleep(1000);
																	Message msg = new Message();
																	msg.what = 0x001;
																	myHandler2.sendMessage(msg);
																} catch (InterruptedException e) {

																	e.printStackTrace();
																}
															}
														}).start();
													}
													Arrays.sort(files, new CompratorFilesByLastModified());
													fileTotalCount.setText(files.length + "");
													showRecordAdapter = new ShowRecordAdapter(fileType, files, ShowRecordFileActivity.this);
													showRecordListView.setAdapter(showRecordAdapter);
												}
											}).setNegativeButton("取消删除", new DialogInterface.OnClickListener() {
												@Override
												public void onClick(DialogInterface dialog, int which) {

												}
									}).setCancelable(false)
											.show();


								}
							}
						}).show();


			}
		});

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}

	ProgressDialog progress;
	Handler myHandler2 = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what == 0x001){
				progress.dismiss();
				finish();
			}else if(msg.what == 0x002){
				String compressorPath = (String)msg.obj;

				long current = System.currentTimeMillis();

				String lat = TService.lat;
				String lon = TService.lon;
				String upFileAdd = null;
				if (lat != null && lon != null) {
					upFileAdd = lat + "_" + lon;
				} else {
					upFileAdd = "no_add_info_local";
				}

				String dateT = current + "";
				String serverPath = NetUtil.getUploadFileServerPath(keyID, type, upFileAdd, dateT, remark);

				progress.dismiss();
				PicUploadUtil picUploadUtil = new PicUploadUtil(keyID, type, remark, compressorPath, fileAbsolutePath, serverPath, getApplicationContext().getContentResolver(), ShowRecordFileActivity.this);
				picUploadUtil.uploadFile();

			}else if(msg.what == 0x003){ //
				progress.dismiss();
				new AlertDialog.Builder(ShowRecordFileActivity.this)
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


	class CompratorFilesByLastModified implements Comparator<File>{
		public int compare(File f1, File f2) {
			long diffTime = f1.lastModified() - f2.lastModified();
			if(diffTime > 0)
				return -1;
			else if(diffTime == 0)
				return 0;
			else
				return 1;
		}
		public boolean equals(Object obj){
			return true;
		}
	}


	class ShowRecordAdapter extends BaseAdapter {
		int fileType;
		File[] files;
		LayoutInflater inflater;


		public ShowRecordAdapter(int fileType, File[] files, Context context) {
			super();
			this.fileType = fileType;
			this.files = files;
			inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return files.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return files[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			Holder myHolder;
			if (convertView == null) {
				myHolder = new Holder();
				convertView = inflater.inflate(R.layout.show_record_item, null);
				myHolder.fileLength = (TextView) convertView.findViewById(R.id.file_length);
				myHolder.fileName = (TextView) convertView.findViewById(R.id.fileName);
				myHolder.fileIcon = (SimpleDraweeView) convertView.findViewById(R.id.fileIcon);
				convertView.setTag(myHolder);
			} else {
				myHolder = (Holder) convertView.getTag();
			}


			myHolder.fileName.setText(files[position].getName());
			if (fileType == 0) {

				Uri uri = Uri.parse("file://" + files[position].getPath());
				myHolder.fileIcon.setImageURI(uri);

			} else if (fileType == 1) {
			}
			return convertView;
		}


		class Holder {
			TextView fileLength;
			TextView fileName;
			SimpleDraweeView fileIcon;
		}
	}
}

