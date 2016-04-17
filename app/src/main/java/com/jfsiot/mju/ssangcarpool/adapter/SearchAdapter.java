package com.jfsiot.mju.ssangcarpool.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jfsiot.mju.ssangcarpool.R;
import com.jfsiot.mju.ssangcarpool.model.map.MapPOIData;

import java.util.List;

/**
 * Created by SSS on 2016-04-17.
 */
public class SearchAdapter extends BaseAdapter {
    private List<MapPOIData> items;
    private Context context;
    private int resId;
    private OnItemClickListener listener;

    public SearchAdapter(Context context, int resId, List<MapPOIData> items){
        this(context, resId, items, null);
    }

    public SearchAdapter(Context context, int resId, List<MapPOIData> items, OnItemClickListener listener){
        this.items = items;
        this.context = context;
        this.resId = resId;
        this.listener = listener;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = ((LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(resId, parent, false);
        ((TextView) convertView.findViewById(R.id.result_name)).setText(items.get(position).getName());

        final View view = convertView;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) SearchAdapter.this.listener.onItemClickListener(view, position);
            }
        });
        return convertView;
    }
}
