package com.jfsiot.mju.ssangcarpool.activity2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jfsiot.mju.ssangcarpool.R;
import com.jfsiot.mju.ssangcarpool.activity2.Room;

import java.util.List;

/**
 * Created by User on 2016-04-16.
 */
public class Aadapter extends BaseAdapter{

    private LayoutInflater inflater;
    private Context context;
    private int layout_res_id;
    private List<Room> items;

    public Aadapter(Context context, int layout_res_id, List<Room> items){
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
        RoomViewHolder room;
        //if(convertView == null){
        convertView = inflater.inflate(layout_res_id, parent, false);
        room = new RoomViewHolder();

        room.departure_name = (TextView)convertView.findViewById(R.id.departure_name);
        room.destination_name = (TextView)convertView.findViewById(R.id.destination_name);
        room.date = (TextView)convertView.findViewById(R.id.date);

        //}
        room.departure_name.setText(items.get(position).departure_name);
        room.destination_name.setText(items.get(position).destination_name);
        room.date.setText(items.get(position).date);
        return convertView;
    }
    public class RoomViewHolder{
        public TextView departure_name;
        public TextView destination_name;
        public TextView date;
    }
}