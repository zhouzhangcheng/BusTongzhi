package com.zzc.bustongzhi.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Administrator on 2015/8/2.
 */
public abstract class BaseActivity extends Activity{
    protected LayoutInflater mInflater;
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

}
