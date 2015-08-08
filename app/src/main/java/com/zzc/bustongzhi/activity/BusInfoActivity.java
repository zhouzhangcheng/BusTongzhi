package com.zzc.bustongzhi.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.zzc.bustongzhi.R;
import com.zzc.bustongzhi.adpater.BusInfoAdapter;
import com.zzc.bustongzhi.base.BaseActivity;
import com.zzc.bustongzhi.bean.BusInfoBeans;
import com.zzc.bustongzhi.bean.BusSearchLinearInfoBean;
import com.zzc.bustongzhi.bean.BusStation;
import com.zzc.bustongzhi.utils.CommentUtil;
import com.zzc.bustongzhi.utils.Urls;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/3.
 */
public class BusInfoActivity extends BaseActivity {
    private ListView listView;
    private BusStation startItem;
    private BusStation endItem;
    private String address;
    private String location;
    private String sLat;
    private String sLon;
    private String sName;
    private String eLat;
    private String eLon;
    private String eName;
    private List<BusSearchLinearInfoBean> listBus = new ArrayList<BusSearchLinearInfoBean>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    listView.setAdapter(new BusInfoAdapter(listBus, mInflater,BusInfoActivity.this));
                    break;
            }
        }
    };

    @Override
    public View customView(Bundle savedInstanceState) {
        View busView = mInflater.inflate(R.layout.activity_bus_info, null);
        listView = (ListView) busView.findViewById(R.id.list_view);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            startItem = bundle.getParcelable(CommentUtil.startItem);
            endItem = bundle.getParcelable(CommentUtil.endItem);
            address = bundle.getString("address");
            location = bundle.getString("location");
            if(address!=null){
                String[] locations = location.split(",");
                sLat = locations[0];
                sLon = locations[1];
                sName = address;
            }else{
                sLat = startItem.getA3();
                sLon = startItem.getA2();
                sName = startItem.getA1();
            }
            loadData();
        } else {
            Toast.makeText(this, "没有相关数据", Toast.LENGTH_LONG).show();
        }
        return busView;
    }

    private void loadData() {
        AsyncHttpClient client = new AsyncHttpClient();
        StringBuffer sb = new StringBuffer(Urls.getSearchBusInfo);
        sb.append("sLat="+sLat);
        sb.append("&sLon="+sLon);
        sb.append("&sName=" + sName);
        if(endItem != null){
            sb.append("&eLat="+endItem.getA3());
            sb.append("&eLon="+endItem.getA2());
            sb.append("&eName="+endItem.getA1());
        }
        client.get(sb.toString(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String str = new String(bytes);
                BusInfoBeans info = new Gson().fromJson(String.format("{lists:%s}",str), BusInfoBeans.class);
                listBus.addAll(info.lists);
                handler.sendEmptyMessage(1);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }
}
