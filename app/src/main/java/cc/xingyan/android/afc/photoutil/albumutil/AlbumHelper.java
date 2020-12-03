package cc.xingyan.android.afc.photoutil.albumutil;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore.Audio.Albums;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Images.Thumbnails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import cc.xingyan.android.afc.util.LogUtil;


public class AlbumHelper {
	final String TAG = getClass().getSimpleName();
	Context context;
	ContentResolver cr;
	
	HashMap<String, String> thumbnailList = new HashMap<String, String>();

	List<HashMap<String, String>> albumList = new ArrayList<HashMap<String, String>>();
	HashMap<String, ImageBucket> bucketList;

	private static AlbumHelper instance;

	private AlbumHelper() {
	}

	public static AlbumHelper getHelper() {
		if (instance == null) {
			instance = new AlbumHelper();
		}
		return instance;
	}

	public void init(Context context) {
		bucketList = new HashMap<String, ImageBucket>();
		if (this.context == null) {
			this.context = context;
			cr = context.getContentResolver();
		}
	}

	private void getThumbnail() {
		String[] projection = { Thumbnails._ID, Thumbnails.IMAGE_ID,
				Thumbnails.DATA };
		Cursor cursor = cr.query(Thumbnails.EXTERNAL_CONTENT_URI, projection,
				null, null, null);
		getThumbnailColumnData(cursor);
	}


	private void getThumbnailColumnData(Cursor cur) {
		if (cur.moveToFirst()) {
			int _id;
			int image_id;
			String image_path;
			int _idColumn = cur.getColumnIndex(Thumbnails._ID);
			int image_idColumn = cur.getColumnIndex(Thumbnails.IMAGE_ID);
			int dataColumn = cur.getColumnIndex(Thumbnails.DATA);

			do {
				_id = cur.getInt(_idColumn);
				image_id = cur.getInt(image_idColumn);
				image_path = cur.getString(dataColumn);

				thumbnailList.put("" + image_id, image_path);
			} while (cur.moveToNext());
		}
	}

	void getAlbum() {
		String[] projection = { Albums._ID, Albums.ALBUM, Albums.ALBUM_ART,
				Albums.ALBUM_KEY, Albums.ARTIST, Albums.NUMBER_OF_SONGS };
		Cursor cursor = cr.query(Albums.EXTERNAL_CONTENT_URI, projection, null,
				null, null);
		getAlbumColumnData(cursor);

	}


	private void getAlbumColumnData(Cursor cur) {
		if (cur.moveToFirst()) {
			int _id;
			String album;
			String albumArt;
			String albumKey;
			String artist;
			int numOfSongs;

			int _idColumn = cur.getColumnIndex(Albums._ID);
			int albumColumn = cur.getColumnIndex(Albums.ALBUM);
			int albumArtColumn = cur.getColumnIndex(Albums.ALBUM_ART);
			int albumKeyColumn = cur.getColumnIndex(Albums.ALBUM_KEY);
			int artistColumn = cur.getColumnIndex(Albums.ARTIST);
			int numOfSongsColumn = cur.getColumnIndex(Albums.NUMBER_OF_SONGS);

			do {
				_id = cur.getInt(_idColumn);
				album = cur.getString(albumColumn);
				albumArt = cur.getString(albumArtColumn);
				albumKey = cur.getString(albumKeyColumn);
				artist = cur.getString(artistColumn);
				numOfSongs = cur.getInt(numOfSongsColumn);

				LogUtil.info(TAG, _id + " album:" + album + " albumArt:" + albumArt
						+ "albumKey: " + albumKey + " artist: " + artist
						+ " numOfSongs: " + numOfSongs + "---");
				HashMap<String, String> hash = new HashMap<String, String>();
				hash.put("_id", _id + "");
				hash.put("album", album);
				hash.put("albumArt", albumArt);
				hash.put("albumKey", albumKey);
				hash.put("artist", artist);
				hash.put("numOfSongs", numOfSongs + "");
				albumList.add(hash);

			} while (cur.moveToNext());

		}
	}

	boolean hasBuildImagesBucketList = false;

	void buildImagesBucketList() {

		getThumbnail();

		String columns[] = new String[] { Media._ID, Media.BUCKET_ID,
				Media.PICASA_ID, Media.DATA, Media.DISPLAY_NAME, Media.TITLE,
				Media.SIZE, Media.BUCKET_DISPLAY_NAME, Media.DATE_MODIFIED };
		Cursor cur = cr.query(Media.EXTERNAL_CONTENT_URI, columns, null, null,
				null);
		if (cur.moveToFirst()) {
			int photoIDIndex = cur.getColumnIndexOrThrow(Media._ID);
			int photoPathIndex = cur.getColumnIndexOrThrow(Media.DATA);
			int photoNameIndex = cur.getColumnIndexOrThrow(Media.DISPLAY_NAME);
			int photoTitleIndex = cur.getColumnIndexOrThrow(Media.TITLE);
			int photoSizeIndex = cur.getColumnIndexOrThrow(Media.SIZE);
			int bucketDisplayNameIndex = cur
					.getColumnIndexOrThrow(Media.BUCKET_DISPLAY_NAME);
			int bucketIdIndex = cur.getColumnIndexOrThrow(Media.BUCKET_ID);
			int picasaIdIndex = cur.getColumnIndexOrThrow(Media.PICASA_ID);
			int bucketModifiedIndex = cur.getColumnIndexOrThrow(Media.DATE_MODIFIED);
			int totalNum = cur.getCount();

			do {
				String _id = cur.getString(photoIDIndex);
				String name = cur.getString(photoNameIndex);
				String path = cur.getString(photoPathIndex);
				String title = cur.getString(photoTitleIndex);
				String size = cur.getString(photoSizeIndex);
				String bucketName = cur.getString(bucketDisplayNameIndex);
				String bucketModified = cur.getString(bucketModifiedIndex);
				String bucketId = cur.getString(bucketIdIndex);
				String picasaId = cur.getString(picasaIdIndex);

				if((Integer.parseInt(size) > 0) && (path.endsWith("jpg") || path.endsWith("png") || path.endsWith("JPEG"))){

					LogUtil.info(TAG, _id + ", bucketId: " + bucketId + ", picasaId: "
							+ picasaId + " name:" + name + " path:" + path
							+ " title: " + title + " size: " + size + " bucket: "
							+ bucketName + "---");
					
					ImageBucket bucket = bucketList.get(bucketId);
					if (bucket == null) {
						bucket = new ImageBucket();
						bucketList.put(bucketId, bucket);
						bucket.imageList = new ArrayList<ImageItem>();
						bucket.bucketName = bucketName;
						bucket.bucketPath = path.replace(name, "");

					}
					ImageItem imageItem = new ImageItem();
					imageItem.imageId = _id;
					imageItem.lastModified = Long.parseLong(bucketModified);
					imageItem.imagePath = path;
					imageItem.thumbnailPath = thumbnailList.get(_id);
					if(!isContain(bucket.imageList, imageItem)){
						bucket.imageList.add(imageItem);
					}
					bucket.count = bucket.imageList.size();
					bucket.modified = getMaxtModified(bucket.imageList);
				}

			} while (cur.moveToNext());
		}

		Iterator<Entry<String, ImageBucket>> itr = bucketList.entrySet()
				.iterator();
		while (itr.hasNext()) {
			Entry<String, ImageBucket> entry = (Entry<String, ImageBucket>) itr
					.next();
			ImageBucket bucket = entry.getValue();
			LogUtil.debug(TAG, entry.getKey() + ", " + bucket.bucketName + ", "
					+ bucket.count + " ---------- ");
			for (int i = 0; i < bucket.imageList.size(); ++i) {
				ImageItem image = bucket.imageList.get(i);
				LogUtil.debug(TAG, "----- " + image.imageId + ", " + image.imagePath
						+ ", " + image.thumbnailPath);
			}
		}
		hasBuildImagesBucketList = true;
	}

	private long getMaxtModified(List<ImageItem> imageList){
		if(imageList.size() == 1){
			return imageList.get(0).lastModified;
		}

		Collections.sort(imageList, new CompratorFilesByLastModified());
		return imageList.get(0).lastModified;
	}

	public boolean isContain(List<ImageItem> imageList, ImageItem imageItem){
		for(ImageItem tempImageItem : imageList){
			if(tempImageItem.imageId.equals(imageItem.imageId)){
				return true;
			}
		}
		return false;
	}

	public List<ImageBucket> getImagesBucketList(boolean refresh) {
			buildImagesBucketList();
		List<ImageBucket> tmpList = new ArrayList<ImageBucket>();
		Iterator<Entry<String, ImageBucket>> itr = bucketList.entrySet()
				.iterator();
		while (itr.hasNext()) {
			Entry<String, ImageBucket> entry = (Entry<String, ImageBucket>) itr
					.next();
			tmpList.add(entry.getValue());
		}
		
		Collections.sort(tmpList, new CompratorFoldersByLastModified());
		return tmpList;
	}

	String getOriginalImagePath(String image_id) {
		String path = null;
		LogUtil.info(TAG, "---(^o^)----" + image_id);
		String[] projection = { Media._ID, Media.DATA };
		Cursor cursor = cr.query(Media.EXTERNAL_CONTENT_URI, projection,
				Media._ID + "=" + image_id, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			path = cursor.getString(cursor.getColumnIndex(Media.DATA));

		}
		return path;
	}



	class CompratorFilesByLastModified implements Comparator<ImageItem>{
		public int compare(ImageItem imageItem1, ImageItem imageItem2) {
			long diffTime = imageItem1.lastModified - imageItem2.lastModified;
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

	class CompratorFoldersByLastModified implements Comparator<ImageBucket>{
		public int compare(ImageBucket imageBucket1, ImageBucket imageBucket2) {  
			long diffTime = imageBucket1.modified - imageBucket2.modified;  
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

}
