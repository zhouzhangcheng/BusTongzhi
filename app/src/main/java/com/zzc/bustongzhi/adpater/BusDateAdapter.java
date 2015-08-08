package com.zzc.bustongzhi.adpater;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zzc.bustongzhi.BusInfoService;
import com.zzc.bustongzhi.R;
import com.zzc.bustongzhi.bean.BusInfoBean;

import java.util.List;

/**
 * Created by Administrator on 2015/8/7.
 */
public class BusDateAdapter extends BaseAdapter {
    private List<BusInfoBean.TicketInfo> lists;
    private Context context;
    private int indexShow;
    private String sLinear;
    private String lineBaseId;

    public BusDateAdapter(List<BusInfoBean.TicketInfo> lists, Context context,int indexShow,String sLinear,String lineBaseId) {
        this.lists = lists;
        this.context = context;
        this.indexShow = indexShow;
        this.sLinear = sLinear;
        this.lineBaseId = lineBaseId;
    }

    @Override
    public int getCount() {
        return lists.size()+indexShow-1;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_time_detail, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        if(position<indexShow-1){
            holder.itemll.setVisibility(View.INVISIBLE);
        }else{
            int index = position - indexShow+1;
            holder.itemll.setVisibility(View.VISIBLE);
            final BusInfoBean.TicketInfo info = lists.get(index);
            if(info.freeSeat.trim().equals("-1")){
                holder.itemprice.setVisibility(View.GONE);
                holder.itemstatue.setVisibility(View.GONE);
                holder.itemtime.setText(info.orderDate.split("-")[2]);
            }else{
                holder.itemprice.setVisibility(View.VISIBLE);
                holder.itemstatue.setVisibility(View.VISIBLE);
                holder.itemtime.setText(info.orderDate.split("-")[2]);
                holder.itemprice.setText("￥" +info.price);
                holder.itemstatue.setText(info.freeSeat.trim()+"票");
            }
            if(Integer.valueOf(info.freeSeat.toString()) <= 0 && position%7 != 0 && position% 7 != 6 && position != indexShow-1){
                holder.itemll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, BusInfoService.class);
                        intent.putExtra("sLinear",sLinear);
                        intent.putExtra("lineBaseId",lineBaseId);
                        intent.putExtra("time",500);
                        intent.putExtra("selectTime",info.orderDate);
                        context.startService(intent);
                        Toast.makeText(context,"开启模式",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
        return convertView;
    }
    public class ViewHolder {
        public final TextView itemtime;
        public final TextView itemprice;
        public final TextView itemstatue;
        public final LinearLayout itemll;
        public final View root;

        public ViewHolder(View root) {
            itemtime = (TextView) root.findViewById(R.id.item_time);
            itemprice = (TextView) root.findViewById(R.id.item_price);
            itemstatue = (TextView) root.findViewById(R.id.item_statue);
            itemll = (LinearLayout) root.findViewById(R.id.item_ll);
            this.root = root;
        }
    }
}
