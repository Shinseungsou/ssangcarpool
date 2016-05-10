package com.jfsiot.mju.ssangcarpool.activity2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jfsiot.mju.ssangcarpool.R;

import java.util.List;

/**
 * Created by mj on 2016-04-16.
 */
public class MessageAdapter extends BaseAdapter{
    
    private List<Message> items;
    
    private LayoutInflater inflater;
    private Context context;
    private int layout_res_id;
    
    public MessageAdapter(Context context, int layout_res_id, List<Message> items){
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RoomViewHolder room;

        convertView = inflater.inflate(layout_res_id, parent, false);

        room = new RoomViewHolder();
        room.imageview = (ImageView)convertView.findViewById(R.id.message_imageView_1);
        room.title = (TextView)convertView.findViewById(R.id.message_textView_1);
        room.contents = (TextView)convertView.findViewById(R.id.message_textView_2);
        room.date = (TextView)convertView.findViewById(R.id.message_textView_3);

        room.imageview.setImageResource(R.drawable.android);
        room.title.setText(items.get(position).title);
        room.contents.setText(items.get(position).contents);
        room.date.setText(items.get(position).date);

        return convertView;
    }

    public class RoomViewHolder{
        public ImageView imageview;
        public TextView title;
        public TextView contents;
        public TextView date;
    }
}
