package com.zzc.bustongzhi.adpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zzc.bustongzhi.R;
import com.zzc.bustongzhi.bean.BusStation;

import java.util.List;

/**
 * Created by Administrator on 2015/8/3.
 */
public class MapAdapter extends BaseAdapter {
    private List<BusStation> lists;
    private Context context;
    public MapAdapter( List<BusStation> lists,Context context) {
        super();
        this.lists = lists;
        this.context = context;
    }



    @Override
    public int getCount() {
        return lists.size();
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
        if(convertView == null){
            convertView = View.inflate(context,R.layout.item_map_address,null);
            holder = new ViewHolder();
            holder.itemMapName = (TextView) convertView.findViewById(R.id.item_map_name);
            holder.itemMapAddress = (TextView) convertView.findViewById(R.id.item_map_address);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.itemMapName.setText(lists.get(position).getA1());
        holder.itemMapAddress.setText(lists.get(position).getA4());
        return convertView;
    }

    private class ViewHolder{
        TextView itemMapName;
        TextView itemMapAddress;
    }

}
