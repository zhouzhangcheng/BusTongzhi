package com.zzc.bustongzhi;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zzc.bustongzhi.bean.BusInfoBean;
import com.zzc.bustongzhi.utils.VibratorUtil;


public class BusInfoService extends Service {
    private HttpUtils httpUtils;
    private Gson gson;
    private BusInfoBean busInfo;
    private String url = "http://bsapp.pig84.com/customline_app/app_book/getLineAndClassInfo.action";
    private RequestParams params;
    private MediaPlayer mp;
    private String sLinear;
    private String lineBaseId;
    private int time;
    private String selectTime;
    private Context context;
    private boolean b = true;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            httpUtils.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    String result = responseInfo.result;
                    busInfo = gson.fromJson(result, BusInfoBean.class);
                    for (int i = 0; i < busInfo.list1.size(); i++) {
                        if (busInfo.list1.get(i).orderDate.equals(selectTime)) {
                            if (Integer.valueOf(busInfo.list1.get(i).freeSeat) > 0) {
                                playMusic();
                                VibratorUtil.Vibrate(context, 5000);
                                handler.removeMessages(1);
                            } else {
                                if(b) {
                                    Toast.makeText(context,"meiyou",Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }

                }

                @Override
                public void onFailure(HttpException e, String s) {
                }
            });
            handler.sendEmptyMessageDelayed(1, time);
        }
    };

    public BusInfoService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        flags = START_REDELIVER_INTENT;
        Notification notification = new Notification(R.drawable.ic_launcher,
                getString(R.string.app_name), System.currentTimeMillis());
        PendingIntent pendingintent = PendingIntent.getActivity(this, 0,
                new Intent(this, BusInfoService.class), 0);
        notification.setLatestEventInfo(this, "uploadservice", "请保持程序在后台运行",
                pendingintent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Bundle bundle = intent.getExtras();
        sLinear = bundle.getString("sLinear");
        lineBaseId = bundle.getString("lineBaseId");
        time = bundle.getInt("time");
        selectTime = bundle.getString("selectTime");
        httpUtils = new HttpUtils();
        params = new RequestParams();
        gson = new Gson();
        context = getApplicationContext();
        params.addBodyParameter("lineBaseId", lineBaseId);
        params.addBodyParameter("slineId", sLinear);
        handler.sendEmptyMessage(1);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    private void playMusic() {
        mp = MediaPlayer.create(this, R.raw.music);
        try {
            if (mp != null) {
                mp.stop();
            }
            mp.prepare();
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
