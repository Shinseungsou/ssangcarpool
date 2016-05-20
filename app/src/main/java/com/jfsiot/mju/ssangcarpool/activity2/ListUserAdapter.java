package com.jfsiot.mju.ssangcarpool.activity2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jfsiot.mju.ssangcarpool.R;
import com.jfsiot.mju.ssangcarpool.model.data.PassengerName;

import java.util.List;

/**
 * Created by User on 2016-05-20.
 */

public class ListUserAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private int layout_res_id;
    private List<PassengerName> items;

    public ListUserAdapter(Context context, int layout_res_id, List<PassengerName> items){
        this.context = context;
        this.layout_res_id = layout_res_id;
        this.items = items;
        this.inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RoomViewHolder passengername;
        //if(convertView == null){
        convertView = inflater.inflate(layout_res_id, parent, false);
        passengername = new RoomViewHolder();
        passengername.title1 = (TextView)convertView.findViewById(R.id.departuretxt);
        passengername.title2 = (TextView)convertView.findViewById(R.id.destinationtxt);
        passengername.contents = (TextView)convertView.findViewById(R.id.date1);
        //}
        passengername.title1.setText(items.get(position).title1);
        passengername.title2.setText(items.get(position).title2);
        passengername.contents.setText(items.get(position).contents);
        return convertView;
    }
    public class RoomViewHolder{
        public TextView title1;
        public TextView title2;
        public TextView contents;
    }
}