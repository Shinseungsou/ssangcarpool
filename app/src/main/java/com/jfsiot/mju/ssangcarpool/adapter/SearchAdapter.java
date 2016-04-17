package com.jfsiot.mju.ssangcarpool.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jfsiot.mju.ssangcarpool.R;

import java.util.List;

/**
 * Created by SSS on 2016-04-17.
 */
public class SearchAdapter extends BaseAdapter {
    private List<String> items;
    private Context context;
    private int resId;

    public SearchAdapter(Context context, int resId, List<String> items){
        this.items = items;
        this.context = context;
        this.resId = resId;
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
        convertView = ((LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(resId, parent, false);
        ((TextView) convertView.findViewById(R.id.result_name)).setText(items.get(position));
        return convertView;
    }
}
