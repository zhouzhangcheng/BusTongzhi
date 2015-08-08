package com.zzc.bustongzhi.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.zzc.bustongzhi.utils.ToastProUtils;

/**
 * Created by Administrator on 2015/8/2.
 */
public abstract class BaseActivity extends Activity{
    protected LayoutInflater mInflater;
    private ToastProUtils progDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInflater = LayoutInflater.from(this);
        setContentView(customView(savedInstanceState));
    }

    /**
     * 自定义View
     */
    public abstract View customView(Bundle savedInstanceState);
    /**
     * 显示进度框
     */
    protected void showProgressDialog(String text) {
        if (progDialog == null)
            progDialog = new ToastProUtils(this);
        progDialog.showstr(text);
    }

    /**
     * 隐藏进度框
     */
    protected void dissmissProgressDialog() {
        if (progDialog != null) {
            progDialog.cancle();
        }
    }
}
