package com.zzc.bustongzhi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zzc.bustongzhi.BusInfoService;
import com.zzc.bustongzhi.R;
import com.zzc.bustongzhi.adpater.BusDateAdapter;
import com.zzc.bustongzhi.base.BaseActivity;
import com.zzc.bustongzhi.bean.BusInfoBean;
import com.zzc.bustongzhi.utils.Urls;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2015/8/7.
 */
public class BusDetailActivity extends BaseActivity {
    private TextView infostarttime;
    private TextView infostartplace;
    private TextView infostatue;
    private TextView infostart;
    private TextView infoend;
    private TextView infodistance;
    private TextView infotime;
    private TextView infoprice;
    private Button infobt;
    private TextView detailtime;
    private GridView detailgv;
    private HttpUtils httpUtils;
    private String lineBaseId;
    private String slineId;
    private  Date date = new Date();
    private  EditText editLunTime;
    private  EditText editTime;
    private  Button bt;

    @Override
    public View customView(Bundle savedInstanceState) {
        View detailView = mInflater.inflate(R.layout.activity_bus_detail, null);
        Bundle bundle = getIntent().getExtras();
        lineBaseId = bundle.getString("lineBaseId");
        slineId = bundle.getString("slineId");
        initView(detailView);
        initData();
        return detailView;
    }

    private void initData() {
        httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("lineBaseId", lineBaseId);
        params.addBodyParameter("slineId", slineId);
        httpUtils.send(HttpRequest.HttpMethod.POST, Urls.getBusTicket, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                BusInfoBean info = new Gson().fromJson(responseInfo.result, BusInfoBean.class);
                int indexShow = getDay(date);
                detailgv.setAdapter(new BusDateAdapter(info.list1, BusDetailActivity.this, indexShow,slineId,lineBaseId));
                setData(info);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                System.out.println(s);
            }
        });
    }
    private void setData(BusInfoBean info){
        infostarttime.setText(info.a1);
        infostartplace.setText(info.a2);
        infostart.setText(info.a3);
        infoend.setText(info.a4);
        infodistance.setText(String.format("全程%skm",info.a5));
        infotime.setText(String.format("共%s分钟",info.a6));
        infoprice.setText("￥"+info.a10);
    }
    private void initView(View view) {
        detailtime = (TextView) view.findViewById(R.id.detail_time);
        detailtime.setText(new SimpleDateFormat("yyyy-MM").format(date));
        detailgv = (GridView) view.findViewById(R.id.detail_gv);
        infostarttime = (TextView) view.findViewById(R.id.info_start_time);
        infostartplace = (TextView) view.findViewById(R.id.info_start_place);
        infostatue = (TextView) view.findViewById(R.id.info_statue);
        infostart = (TextView) view.findViewById(R.id.info_start);
        infoend = (TextView) view.findViewById(R.id.info_end);
        infodistance = (TextView) view.findViewById(R.id.info_distance);
        infotime = (TextView) view.findViewById(R.id.info_time);
        infoprice = (TextView) view.findViewById(R.id.info_price);
        infobt = (Button) view.findViewById(R.id.info_bt);
        infobt.setVisibility(View.INVISIBLE);
        editLunTime = (EditText) view.findViewById(R.id.edit_lun_time);
        editTime = (EditText) view.findViewById(R.id.edit_time);
        bt = (Button) view.findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editLunTime.getText()!= null && editTime.getText()!=null){
                    Intent intent = new Intent(BusDetailActivity.this, BusInfoService.class);
                    intent.putExtra("sLinear",slineId);
                    intent.putExtra("lineBaseId",lineBaseId);
                    intent.putExtra("time",editLunTime.getText().toString());
                    intent.putExtra("selectTime",editTime.getText().toString());
                    BusDetailActivity.this.startService(intent);
                    Toast.makeText(BusDetailActivity.this,"开启模式",Toast.LENGTH_LONG).show();;
                }else{
                    Toast.makeText(BusDetailActivity.this, "请输入数据", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }
}
