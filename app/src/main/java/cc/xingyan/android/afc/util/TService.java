package cc.xingyan.android.afc.util;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.util.UUID;

public class TService extends Service {

	public static String imei;

	public static String locErrorCode;
	public static  String lat;
	public static  String lon;
	public static  String addr;


	private LocationClient locationClient;
	private MyLocationListener myLocListener = new MyLocationListener();

	public void onCreate() {
		super.onCreate();
		initLocation();
	}

	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}

	public IBinder onBind(Intent intent) {
		return null;
	}

	public class LocalBinder extends Binder {
		public TService getService() {
			return TService.this;
		}
	}

	public boolean onUnbind(Intent intent) {
		return false;
	}

	public void onRebind(Intent intent) {
	}

	public void onDestroy() {

		super.onDestroy();
		LogUtil.info("Scan_erp", "onDestroy_<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				locationClient.stop();
	}

	public void initLocation(){
//		TelephonyManager tm = (TelephonyManager)getApplicationContext().getSystemService(getApplicationContext().TELEPHONY_SERVICE);
//		imei = tm.getDeviceId();
		imei = IMEIUtil.getIMEI(getApplicationContext());
		Log.e("TAG","imei=="+imei);
		if(imei == null){
			imei = "no_imei";
		}

		locationClient = new LocationClient(getApplicationContext().getApplicationContext());
		locationClient.registerLocationListener(myLocListener);
		LocationClientOption option = new LocationClientOption();

		option.setLocationMode(LocationClientOption.
				LocationMode.Hight_Accuracy);
		option.setCoorType("bd09ll");
		option.setScanSpan(1 * 60 * 1000);
		option.setIsNeedAddress(true);
		option.setOpenGps(true);
		option.setLocationNotify(true);
		option.setIsNeedLocationDescribe(true);
		option.setIsNeedLocationPoiList(true);
		option.setIgnoreKillProcess(false);
		option.SetIgnoreCacheException(false);
		option.setEnableSimulateGps(false);
		option.setOpenAutoNotifyMode();

		locationClient.setLocOption(option);
		locationClient.start();
	}
	

	public class MyLocationListener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation bdLocation) {
			lat = Double.toString(bdLocation.getLatitude());
			if(lat == null){
				lat = "no_lat";
			}

			lon = Double.toString(bdLocation.getLongitude());
			if(lon == null){
				lon = "no_lon";
			}

			addr = bdLocation.getAddrStr();
			if(addr == null){
				addr = "no_addr";
			}
			locErrorCode = Integer.toString(bdLocation.getLocType());
			LogUtil.info("Scan_erp", "CommunicationUtil_<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<" +
					", New_imei:" + imei +
					", New_error code:" + locErrorCode +
					", New_lat:" + lat +
					", New_lon:" + lon +
					", New_add:" + addr);
		}
	}
}
