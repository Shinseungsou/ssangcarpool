package com.jfsiot.mju.ssangcarpool.activity2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jfsiot.mju.ssangcarpool.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016-04-16.
 */
public class Badapter extends BaseAdapter{

    private LayoutInflater inflater;
    private Context context;
    private int layout_res_id;
    private List<Room2> items;

    public Badapter(Context context, int layout_res_id, ArrayList<Room2> items){
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
        room.title = (TextView)convertView.findViewById(R.id.text1);
        room.contents = (TextView)convertView.findViewById(R.id.text2);
        room.rating = (RatingBar)convertView.findViewById(R.id.rating);

        //}
        room.title.setText(items.get(position).title);
        room.contents.setText(items.get(position).contents);
        room.rating.setProgress(items.get(position).rating);
        return convertView;
    }
    public class RoomViewHolder{
        public TextView title;
        public TextView contents;
        public RatingBar rating;
    }
}