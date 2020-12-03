package cc.xingyan.android.afc.photoutil.album;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cc.xingyan.android.afc.R;
import cc.xingyan.android.afc.photoutil.ShowRecordFileActivity;
import cc.xingyan.android.afc.photoutil.albumutil.AlbumHelper;
import cc.xingyan.android.afc.photoutil.albumutil.BitmapCache;
import cc.xingyan.android.afc.photoutil.albumutil.ImageBucket;
import cc.xingyan.android.afc.photoutil.albumutil.ImageItem;
import cc.xingyan.android.afc.util.LogUtil;

/**
 *
 */
public class FolderAdapter extends BaseAdapter {

	String keyID;
	String type;
	String remark;
	private Context mContext;
	private Intent mIntent;
	private DisplayMetrics dm;
	
	private AlbumHelper helper;
	private  List<ImageBucket> contentList;
	
	BitmapCache cache;
	final String TAG = getClass().getSimpleName();
	public FolderAdapter(Context c, String keyID, String type, String remark) {
		this.keyID = keyID;
		this.type = type;
		this.remark = remark;
		cache = new BitmapCache();
		try {
			init(c);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}


	public void init(Context c) throws Exception {
		mContext = c;
		mIntent = ((Activity) mContext).getIntent();
		dm = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(dm);
		
		helper = AlbumHelper.getHelper();
		helper.init(c);
		
		contentList = helper.getImagesBucketList(false);
	}

	

	@Override
	public int getCount() {
		return contentList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	BitmapCache.ImageCallback callback = new BitmapCache.ImageCallback() {
		@Override
		public void imageLoad(ImageView imageView, Bitmap bitmap,
				Object... params) {
			if (imageView != null && bitmap != null) {
				String url = (String) params[0];
				if (url != null && url.equals((String) imageView.getTag())) {
					((ImageView) imageView).setImageBitmap(bitmap);
				} else {
					LogUtil.error(TAG, "callback, bmp not match");
				}
			} else {
				LogUtil.error(TAG, "callback, bmp null");
			}
		}
	};

	private class ViewHolder {

		public ImageView backImage;
		public ImageView imageView;
		public ImageView choose_back;
		public TextView folderName;
		public TextView fileNum;
	}
	ViewHolder holder = null;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.plugin_camera_select_folder, null);
			holder = new ViewHolder();
			holder.backImage = (ImageView) convertView
					.findViewById(R.id.file_back);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.file_image);
			holder.choose_back = (ImageView) convertView
					.findViewById(R.id.choose_back);
			holder.folderName = (TextView) convertView.findViewById(R.id.name);
			holder.fileNum = (TextView) convertView.findViewById(R.id.filenum);
			holder.imageView.setAdjustViewBounds(true);
			holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();
		String path;
		if (contentList.get(position).imageList != null) {
			
			path = contentList.get(position).imageList.get(0).imagePath;
			holder.folderName.setText(contentList.get(position).bucketName);
			
			holder.fileNum.setText("" + contentList.get(position).count);
			
		} else
			path = "android_hybrid_camera_default";
		if (path.contains("android_hybrid_camera_default"))
			holder.imageView.setImageResource(R.drawable.plugin_camera_no_pictures);
		else {
			final ImageItem item = contentList.get(position).imageList.get(0);
			holder.imageView.setTag(item.imagePath);
			cache.displayBmp(holder.imageView, item.thumbnailPath, item.imagePath,
					callback);
		}
		holder.imageView.setOnClickListener(new ImageViewClickListener(
				position, mIntent,holder.choose_back));
		
		return convertView;
	}

	private class ImageViewClickListener implements OnClickListener {
		private int position;
		private Intent intent;
		private ImageView choose_back;
		public ImageViewClickListener(int position, Intent intent,ImageView choose_back) {
			this.position = position;
			this.intent = intent;
			this.choose_back = choose_back;
		}
		
		public void onClick(View v) {
			String folderName = contentList.get(position).bucketPath;

			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putString("filesDir", folderName);
			bundle.putInt("fileType", 0);
			bundle.putString("keyID", keyID);
			bundle.putString("type", type);
			bundle.putString("remark", remark);
			intent.putExtra("showFilesInfo", bundle);
			
			intent.setClass(mContext, ShowRecordFileActivity.class);
			mContext.startActivity(intent);
			
		}
	}

	public int dipToPx(int dip) {
		return (int) (dip * dm.density + 0.5f);
	}

}
