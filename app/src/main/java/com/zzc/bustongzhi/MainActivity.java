package com.zzc.bustongzhi;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzc.bustongzhi.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private ImageView changePlace;
    private TextView startPlace;
    private TextView endStart;
    private ImageView deletePlace;
    private Button queryBus;
    private View view;
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
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_place:
                String temp = startPlace.getText().toString();
                startPlace.setText(endStart.getText().toString());
                endStart.setText(temp);
                break;
            case R.id.start_place:

                break;
            case R.id.end_start:

                break;
            case R.id.delete_place:
                startPlace.setText("请输入起点");
                endStart.setText("请输入终点");
                break;
            case R.id.query_bus:

                break;
        }
    }
}
