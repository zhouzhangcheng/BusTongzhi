package com.zzc.bustongzhi.utils;

public interface ILocationNetwork {
	/**
	 * 获取经纬度回调
	 * @param location 用“，”隔开
	 */
	public void getLocation(String location);
	/**
	 * 获取地址
	 * @param address
	 */
	public void getCityAddress(String address);
	/**
	 * 获取地址失败
	 */
	public void getLocationFail();
}
