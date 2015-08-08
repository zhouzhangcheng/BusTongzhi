package com.zzc.bustongzhi.utils;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

/**
 * ============================================================
 * 
 * 版 权 ： 百变美金 版权所有 (c) 2015
 * 
 * 作 者 : 周章成
 * 
 * 版 本 ： 3.7
 * 
 * 创建日期 ： 2015-7-28 下午5:42:32
 * 
 * 描 述 ： 地图管理器
 * 
 * 修订历史 ： 1. 获取地址 2. 获取数据（判断本地或者去网络获取）
 * 
 * ============================================================
 **/
public class MapManage implements AMapLocationListener, Runnable
		 {
	private Activity mActivity;
	private LocationManagerProxy aMapLocManager = null;
	private Handler handler = new Handler();
	private AMapLocation aMapLocation;// 用于判断定位超时
	private ILocationNetwork locationCall;
	private boolean flag = true;
	private String limit = "100";
	private double geoLat = 0;
	private double geoLng = 0;

	public MapManage(Activity activity) {
		mActivity = activity;
	}

	/**
	 * 获取地址 ，在activity的onpause方法中要调用stopLocation()
	 *
	 */
	public void getLocationNetWork(ILocationNetwork locationCall) {
		this.locationCall = locationCall;
		aMapLocManager = LocationManagerProxy.getInstance(mActivity);
		/*
		 * mAMapLocManager.setGpsEnable(false);//
		 * 1.0.2版本新增方法，设置true表示混合定位中包含gps定位，false表示纯网络定位，默认是true Location
		 * API定位采用GPS和网络混合定位方式
		 * ，第一个参数是定位provider，第二个参数时间最短是2000毫秒，第三个参数距离间隔单位是米，第四个参数是定位监听者
		 */
		aMapLocManager.requestLocationData(LocationProviderProxy.AMapNetwork,
				2000, 10, this);
		Toast.makeText(mActivity, "正在定位中", Toast.LENGTH_LONG).show();
		handler.postDelayed(this, 12000);// 设置超过12秒还没有定位到就停止定位
	}

	@Override
	public void onLocationChanged(Location location) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onLocationChanged(AMapLocation location) {
		if (location != null) {
			stopLocation();
			this.aMapLocation = location;// 判断超时机制
			geoLat = location.getLatitude();
			geoLng = location.getLongitude();
			Bundle locBundle = location.getExtras();
			if (locBundle != null) {
				locationCall.getCityAddress(locBundle.getString("desc"));
			}
			if (geoLat != 0) {
				locationCall.getLocation(geoLat + "," + geoLng);
			}
		}
	}

	@Override
	public void run() {
		if (aMapLocation == null) {
			stopLocation();// 销毁掉定位
			locationCall.getLocationFail();
		}
	}

	/**
	 * 销毁定位
	 */
	public void stopLocation() {
		if (aMapLocManager != null) {
			aMapLocManager.removeUpdates(this);
			aMapLocManager.destroy();
		}
		aMapLocManager = null;
	}


}
