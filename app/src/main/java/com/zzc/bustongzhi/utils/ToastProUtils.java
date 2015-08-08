/*******************************************************************************
 * Copyright (c) 2015 by jalen Corporation all right reserved.
 * 2015年5月14日 
 * 
 *******************************************************************************/
package com.zzc.bustongzhi.utils;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzc.bustongzhi.R;

/**
 * ============================================================
 * 
 * 版 权 ： 百变美金 版权所有 (c) 2015
 * 
 * 作 者 : 周章成
 * 
 * 版 本 ： 3.7
 * 
 * 创建日期 ： 2015-7-14 下午3:57:20
 * 
 * 描 述 ：加载进度条
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
public class ToastProUtils {
	private ToastProUtils instanse = null;
	private Dialog dialog;
	private View view;
	private TextView tv;

	public ToastProUtils(Context context) {

		if (instanse == null) {
			instanse = new ToastProUtils();
			dialog = new Dialog(context, R.style.MyDialogStyle);
			view = LayoutInflater.from(context).inflate(
					R.layout.progressbar_item, null);
			dialog.setContentView(view);
			dialog.setCanceledOnTouchOutside(false);
			ImageView progressImageView = (ImageView) view
					.findViewById(R.id.myloading_image_id);
			AnimationDrawable animationDrawable = (AnimationDrawable) progressImageView
					.getDrawable();
			animationDrawable.start();
		}
	}

	private ToastProUtils() {

	}

	/**
	 * 方法说明：
	 * 
	 */
	public void show() {
		if (instanse != null) {
			dialog.show();
		}
	}

	public void showstr(String str) {
		if (tv == null) {
			tv = (TextView) view.findViewById(R.id.mylaodint_text_id);
			tv.setText(str);
		} else {
			tv.setText(str);
		}
		dialog.show();
	}

	public void cancle() {
		if (instanse == null || dialog == null) {
			return;
		} else {
			dialog.cancel();
		}
	};

}
