package com.zzc.bustongzhi;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zzc.bustongzhi.activity.BusInfoActivity;
import com.zzc.bustongzhi.activity.MapActivity;
import com.zzc.bustongzhi.base.BaseActivity;
import com.zzc.bustongzhi.bean.BusStation;
import com.zzc.bustongzhi.utils.CommentUtil;
import com.zzc.bustongzhi.utils.ILocationNetwork;
import com.zzc.bustongzhi.utils.MapManage;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private ImageView changePlace;
    private TextView startPlace;
    private TextView endStart;
    private ImageView deletePlace;
    private Button queryBus;
    private View view;
    private Intent intent;
    private BusStation startItem;
    private BusStation endItem;
    private String address;
    private String location;
    private MapManage mapManage;

    /**
     * 生成id
     */
    private void assignViews() {
        changePlace = (ImageView) view.findViewById(R.id.change_place);
        startPlace = (TextView) view.findViewById(R.id.start_place);
        endStart = (TextView) view.findViewById(R.id.end_start);
        deletePlace = (ImageView) view.findViewById(R.id.delete_place);
        queryBus = (Button) view.findViewById(R.id.query_bus);
        changePlace.setOnClickListener(this);
        startPlace.setOnClickListener(this);
        endStart.setOnClickListener(this);
        deletePlace.setOnClickListener(this);
        queryBus.setOnClickListener(this);
    }

    @Override
    public View customView(Bundle savedInstanceState) {
        view = mInflater.inflate(R.layout.activity_main, null);
        assignViews();
        mapManage = new MapManage(this);
        getLocation();
        return view;
    }

    private void getLocation() {
        mapManage.getLocationNetWork(new ILocationNetwork() {
            @Override
            public void getLocation(String location) {
                MainActivity.this.location = location;
            }

            @Override
            public void getCityAddress(String address) {
                MainActivity.this.address = address;
                startPlace.setText("当前位置");
            }

            @Override
            public void getLocationFail() {
                Toast.makeText(MainActivity.this, "定位失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_place:
                String temp = startPlace.getText().toString();
                startPlace.setText(endStart.getText().toString());
                endStart.setText(temp);
                break;
            case R.id.start_place:
                intent = new Intent(this, MapActivity.class);
                startActivityForResult(intent, CommentUtil.START);
                break;
            case R.id.end_start:
                intent = new Intent(this, MapActivity.class);
                startActivityForResult(intent, CommentUtil.END);
                break;
            case R.id.delete_place:
//                startPlace.setText("请输入起点");
//                endStart.setText("请输入终点");
                break;
            case R.id.query_bus:
//                intent = new Intent(this,BusInfoService.class);
//                startService(intent);
                intent = new Intent(this, BusInfoActivity.class);
                if (startItem == null && address != null) {
                    intent.putExtra("address", address);
                    intent.putExtra("location", location);
                    if (endItem != null) {
                        intent.putExtra(CommentUtil.endItem, endItem);
                    }
                    startActivity(intent);
                } else if (startItem != null) {
                    intent.putExtra(CommentUtil.startItem, startItem);
                    if (endItem != null) {
                        intent.putExtra(CommentUtil.endItem, endItem);
                    }
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "数据未填写完整", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CommentUtil.START && resultCode == CommentUtil.RESULT) {
            startItem = data.getExtras().getParcelable("poi");
            startPlace.setText(startItem.getA1());
        }
        if (requestCode == CommentUtil.END && resultCode == CommentUtil.RESULT) {
            endItem = data.getExtras().getParcelable("poi");
            endStart.setText(endItem.getA1());
        }
    }
}
