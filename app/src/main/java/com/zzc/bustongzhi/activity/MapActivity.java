package com.zzc.bustongzhi.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.amap.api.maps.MapView;
import com.zzc.bustongzhi.R;
import com.zzc.bustongzhi.base.BaseActivity;

/**
 * Created by Administrator on 2015/8/2.
 */
public class MapActivity extends BaseActivity{
    private MapView map;
    private ListView listView;
    private View view;
    private void assignViews() {
        map = (MapView) view.findViewById(R.id.map);
        listView = (ListView) view.findViewById(R.id.list_view);
    }


    @Override
    public View customView(Bundle savedInstanceState) {
        view = mInflater.inflate(R.layout.activity_map,null);
        assignViews();
        return view;
    }
}
