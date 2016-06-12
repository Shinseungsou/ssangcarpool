package com.jfsiot.mju.ssangcarpool.activity2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jfsiot.mju.ssangcarpool.R;
import com.jfsiot.mju.ssangcarpool.activity.HistoryDetailActivity;
import com.jfsiot.mju.ssangcarpool.model.data.Carpooler;
import com.jfsiot.mju.ssangcarpool.model.data.Path;
import com.jfsiot.mju.ssangcarpool.model.unique.HistoryDetail;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
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


        final int p = position;

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HistoryDetailActivity.class);

                Path path = new Path();
                path.destination = items.get(position).destination_name;
                path.origination = items.get(position).departure_name;
                path.date = items.get(position).date;
                path.carpooler_type = "차량 보유자";

                Carpooler carpooler = new Carpooler();
                carpooler.userData.name = "shin";
                carpooler.origination="서울ic";
                carpooler.destination="수원ic";

                HistoryDetail.getInstance().setCarpooler(carpooler);
                HistoryDetail.getInstance().setPath(path);

                context.startActivity(intent);
            }
        });
        return convertView;
    }
    public class RoomViewHolder{
        public TextView departure_name;
        public TextView destination_name;
        public TextView date;
    }
}