package com.zzc.bustongzhi.adpater;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.zzc.bustongzhi.R;
import com.zzc.bustongzhi.activity.BusDetailActivity;
import com.zzc.bustongzhi.bean.BusSearchLinearInfoBean;

import java.util.List;

/**
 * Created by Administrator on 2015/8/6.
 */
public class BusInfoAdapter extends BaseAdapter{
    private List<BusSearchLinearInfoBean> listInfo;
    private LayoutInflater  mInflater;
    private Context context;
    public BusInfoAdapter(List<BusSearchLinearInfoBean> list,LayoutInflater inflater,Context context) {
        super();
        listInfo = list;
        this.context = context;
        this.mInflater = inflater;
    }

    @Override
    public int getCount() {
        return listInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_bus_info,null);
            holder = new ViewHolder();
            holder.infoBt = (Button) convertView.findViewById(R.id.info_bt);
            holder.infoDistance = (TextView) convertView.findViewById(R.id.info_distance);
            holder.infoPrice = (TextView) convertView.findViewById(R.id.info_price);
            holder.infoTime = (TextView) convertView.findViewById(R.id.info_time);
            holder.infoEnd = (TextView) convertView.findViewById(R.id.info_end);
            holder.infoStart = (TextView) convertView.findViewById(R.id.info_start);
            holder.infoStatue = (TextView) convertView.findViewById(R.id.info_statue);
            holder.infoStartPlace = (TextView) convertView.findViewById(R.id.info_start_place);
            holder.infoStartTime = (TextView) convertView.findViewById(R.id.info_start_time);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.infoStartTime.setText(listInfo.get(position).a3);
        holder.infoStartPlace.setText(listInfo.get(position).a2);
        holder.infoStart.setText(listInfo.get(position).a5);
        holder.infoEnd.setText(listInfo.get(position).a6);
        holder.infoPrice.setText(String.format("￥%s",listInfo.get(position).a4));
        holder.infoDistance.setText(String.format("全程%skm",listInfo.get(position).a7));
        holder.infoTime.setText(String.format("约%s分钟", listInfo.get(position).a8));
        holder.infoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BusDetailActivity.class);
                intent.putExtra("lineBaseId",listInfo.get(position).a1);
                intent.putExtra("slineId",listInfo.get(position).a9);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    private class ViewHolder {
        TextView infoStartTime;
        TextView infoStartPlace;
        TextView infoStatue;
        TextView infoStart;
        TextView infoEnd;
        TextView infoDistance;
        TextView infoTime;
        TextView infoPrice;
        Button infoBt;

    }
}
