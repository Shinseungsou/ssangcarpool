package com.jfsiot.mju.ssangcarpool.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jfsiot.mju.ssangcarpool.R;
import com.jfsiot.mju.ssangcarpool.adapter.OnItemClickListener;
import com.jfsiot.mju.ssangcarpool.adapter.SearchAdapter;
import com.jfsiot.mju.ssangcarpool.model.map.MapPOIData;
import com.jfsiot.mju.ssangcarpool.model.map.MapRoute;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;


public class NavigationActivity extends Activity implements View.OnClickListener, OnItemClickListener {

    private TextView searchOrigination;
    private TextView searchDestination;
    private RelativeLayout viewSearch;
    private ImageView searchButton;
    private EditText searchView;
    private ListView searchResult;
    private TextView routeSearchButton;

    private List<MapPOIData> items;
    private MapPOIData originationPOIData, destinationPOIData;

    private SearchAdapter adapter;
    private TMapData tMapData;


    private enum STATE{
        NONE, ORIGINATION, DESTINATION
    }
    private STATE state = STATE.NONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);
        searchOrigination = ((TextView) findViewById(R.id.search_origination));
        searchDestination = ((TextView) findViewById(R.id.search_destination));
        viewSearch = ((RelativeLayout) findViewById(R.id.view_search));
        viewSearch.setVisibility(View.GONE);
        this.searchButton = ((ImageView) findViewById(R.id.search_button));
        this.searchResult = (ListView) findViewById(R.id.search_result);
        this.searchResult.setVisibility(View.GONE);
        this.searchView = (EditText) findViewById(R.id.search_view);
        this.routeSearchButton = ((TextView) findViewById(R.id.search_route));

        tMapData = new TMapData();

        items = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        searchDestination.setOnClickListener(this);
        searchOrigination.setOnClickListener(this);
        routeSearchButton.setOnClickListener(this);
        this.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TMapData tMapData = new TMapData();
                tMapData.findTitlePOI(searchView.getText().toString(), 20, new TMapData.FindTitlePOIListenerCallback() {
                    @Override
                    public void onFindTitlePOI(ArrayList<TMapPOIItem> arrayList) {
                        ((InputMethodManager) NavigationActivity.this.getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(NavigationActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        NavigationActivity.this.items.clear();
                        for (TMapPOIItem item : arrayList){
                            NavigationActivity.this.items.add(new MapPOIData(item));
                            Timber.d("%s", new MapPOIData(item).toString());
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                NavigationActivity.this.adapter.notifyDataSetChanged();
                                if (NavigationActivity.this.searchResult.getVisibility() == View.GONE) NavigationActivity.this.searchResult.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                });
            }
        });
        this.searchResult.setAdapter(this.adapter = new SearchAdapter(this, R.layout.viewholder_search_result, items, this));

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == searchOrigination.getId()){
            viewSearch.setVisibility(View.VISIBLE);
            state = STATE.ORIGINATION;
            searchView.requestFocus();
        }else if(v.getId() == searchDestination.getId()){
            viewSearch.setVisibility(View.VISIBLE);
            state = STATE.DESTINATION;
            searchView.requestFocus();
        }else if(v.getId() == routeSearchButton.getId()){
            MapRoute.getInstance().update(originationPOIData, destinationPOIData);
            tMapData.findPathDataWithType(TMapData.TMapPathType.CAR_PATH, MapRoute.getInstance().getPointOrigination(), MapRoute.getInstance().getPointDestination(), new TMapData.FindPathDataListenerCallback() {
                @Override
                public void onFindPathData(TMapPolyLine tMapPolyLine) {
                    MapRoute.getInstance().update(originationPOIData, destinationPOIData, tMapPolyLine);
                    Timber.d("route : %s", tMapPolyLine.getLinePoint());
                    finish();
                }
            });
        }
    }

    @Override
    public void onItemClickListener(View view, int positiion) {
        if(state == STATE.ORIGINATION){
            this.originationPOIData = items.get(positiion);
            this.searchOrigination.setText(originationPOIData.getName());
        }else if(state == STATE.DESTINATION){
            this.destinationPOIData = items.get(positiion);
            this.searchDestination.setText(destinationPOIData.getName());
        }
        viewSearch.setVisibility(View.GONE);
        state = STATE.NONE;
    }

    @Override
    public void onBackPressed() {
        if(viewSearch.getVisibility() == View.VISIBLE) {
            viewSearch.setVisibility(View.GONE);
            state = STATE.NONE;
        }else
            super.onBackPressed();
    }
}
