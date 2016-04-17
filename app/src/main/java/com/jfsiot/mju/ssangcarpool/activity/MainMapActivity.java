package com.jfsiot.mju.ssangcarpool.activity;


import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jfsiot.mju.ssangcarpool.R;
import com.jfsiot.mju.ssangcarpool.adapter.SearchAdapter;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainMapActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private TextView tab1;
    private List<String> items;
    private TextView searchView;
    private ImageView searchButton;
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);
        final TMapView tmap = new TMapView(this);
        tmap.setSKPMapApiKey(getResources().getString(R.string.tmap_api_server_key));
        tmap.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmap.setIconVisibility(true);
        tmap.setZoomLevel(15);
//        tmap.setCompassMode(true);
        tmap.setTrackingMode(true);
        ((FrameLayout) findViewById(R.id.maps_map)).addView(tmap);

        items = new ArrayList<>();
        this.searchView = (EditText) findViewById(R.id.search_query);

        tab1 = ((TextView) findViewById(R.id.tab1));
        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TMapData tMapData = new TMapData();
//                tMapData.findTimeMachineCarPath()
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListView searchResult = ((ListView) findViewById(R.id.search_result));

        this.searchButton = ((ImageView) findViewById(R.id.search_button));
        this.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TMapData tMapData = new TMapData();
                tMapData.findTitlePOI(searchView.getText().toString(), 20, new TMapData.FindTitlePOIListenerCallback() {
                    @Override
                    public void onFindTitlePOI(ArrayList<TMapPOIItem> arrayList) {
                        for (TMapPOIItem item : arrayList){
                            MainMapActivity.this.items.add(item.address);
                        }
                        MainMapActivity.this.adapter.notifyDataSetChanged();
                    }
                });
            }
        });
        searchResult.setAdapter(adapter = new SearchAdapter(this, R.layout.viewholder_search_result, items));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
