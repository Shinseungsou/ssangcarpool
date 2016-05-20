package com.jfsiot.mju.ssangcarpool.activity;


import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jfsiot.mju.ssangcarpool.R;
import com.jfsiot.mju.ssangcarpool.activity2.ListUserActivity;
import com.jfsiot.mju.ssangcarpool.activity2.MessageActivity;
import com.jfsiot.mju.ssangcarpool.activity2.ProfileActivity;
import com.jfsiot.mju.ssangcarpool.adapter.OnItemClickListener;
import com.jfsiot.mju.ssangcarpool.adapter.SearchAdapter;
import com.jfsiot.mju.ssangcarpool.model.map.MapPOIData;
import com.jfsiot.mju.ssangcarpool.model.map.MapRoute;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapGpsManager;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;
import com.skp.Tmap.TMapView;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class MainMapActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback, OnItemClickListener, View.OnClickListener, TMapGpsManager.onLocationChangedCallback {

    private ImageView tabMsg;
    private ImageView tabProfile;
    private ImageView tabEvaluation;
    private ImageView tabOther;

    private ImageView gpsButton;
    private ImageView navigationButton;

    private ImageView searchButton;
    private EditText searchView;
    private ListView searchResult;

    private TMapView tmap;
    private TMapGpsManager tMapGpsManager;

    private boolean gpsOpened;
    private List<MapPOIData> items;

    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);
        tmap = new TMapView(this);
        tmap.setSKPMapApiKey(getResources().getString(R.string.tmap_api_server_key));
        tmap.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmap.setIconVisibility(true);
        tmap.setZoomLevel(15);
//        tmap.setCompassMode(true);
        tmap.setTrackingMode(true);
        tMapGpsManager = new TMapGpsManager(this);
        tMapGpsManager.setMinTime(1000);
        tMapGpsManager.setMinDistance(5);
        tMapGpsManager.setProvider(TMapGpsManager.GPS_PROVIDER);
//        tMapGpsManager.OpenGps();

        ((FrameLayout) findViewById(R.id.maps_map)).addView(tmap);
        this.searchResult = (ListView) findViewById(R.id.search_result);
        this.searchResult.setVisibility(View.GONE);

        items = new ArrayList<>();
        this.searchView = (EditText) findViewById(R.id.search_query);

        this.gpsButton = ((ImageView) findViewById(R.id.gps_location_button));
        this.navigationButton = ((ImageView) findViewById(R.id.navigation_button));
        gpsOpened = false;

        tabMsg = ((ImageView) findViewById(R.id.tab_msg));
        tabEvaluation = ((ImageView) findViewById(R.id.tab_eval));
        tabProfile = ((ImageView) findViewById(R.id.tab_profile));
        tabOther = ((ImageView) findViewById(R.id.tab_other));
    }

    @Override
    protected void onResume() {
        //onCreate로 생성된 뷰를 가져오는 함수
        super.onResume();
        final ListView searchResult = ((ListView) findViewById(R.id.search_result));

        tabMsg.setOnClickListener(this);
        tabEvaluation.setOnClickListener(this);
        tabProfile.setOnClickListener(this);
        tabOther.setOnClickListener(this);

        gpsButton.setOnClickListener(this);
        navigationButton.setOnClickListener(this);

        this.searchButton = ((ImageView) findViewById(R.id.search_button));
        this.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TMapData tMapData = new TMapData();
                tMapData.findTitlePOI(searchView.getText().toString(), 20, new TMapData.FindTitlePOIListenerCallback() {    //callback : 이벤트에 대한 피드백
                    // searchView 검색창에서 검색
                    @Override
                    public void onFindTitlePOI(ArrayList<TMapPOIItem> arrayList) {
                        ((InputMethodManager) MainMapActivity.this.getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(MainMapActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        MainMapActivity.this.items.clear();
                        for (TMapPOIItem item : arrayList) {
                            MainMapActivity.this.items.add(new MapPOIData(item));
                            Timber.d("%s", new MapPOIData(item).toString());
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                MainMapActivity.this.adapter.notifyDataSetChanged();
                                if (MainMapActivity.this.searchResult.getVisibility() == View.GONE)
                                    MainMapActivity.this.searchResult.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                });
            }
        });
        this.searchResult.setAdapter(this.adapter = new SearchAdapter(this, R.layout.viewholder_search_result, items, this));

        if(MapRoute.getInstance().getRoute() != null){
            this.tmap.addTMapPolyLine("route", MapRoute.getInstance().getRoute());
            Timber.d("find route");
            this.tmap.setTMapPoint(MapRoute.getInstance().getPointOrigination().getLatitude(), MapRoute.getInstance().getPointOrigination().getLongitude());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {
        //뒤로가기 버튼 눌렀을때
        if (this.searchResult.getVisibility() == View.VISIBLE) this.searchResult.setVisibility(View.GONE);
        else super.onBackPressed();
    }

    @Override
    public void onItemClickListener(View view, int positiion) {
        Timber.d("click!");
        ((InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.searchView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        this.searchResult.setVisibility(View.GONE);

//        tMapGpsManager.CloseGps();

        TMapData tMapData = new TMapData();
        MapPOIData mapPOIData = ((MapPOIData) adapter.getItem(positiion));
        tmap.setCenterPoint(Double.parseDouble(mapPOIData.getCenterLon()), Double.parseDouble(mapPOIData.getCenterLat()));

//        tMapData.findPathData(new TMapPoint(currentLocation.getLatitude(), currentLocation.getLongitude()), new TMapPoint(Double.parseDouble(items.get(positiion).getCenterLat()), Double.parseDouble(items.get(positiion).getCenterLon())), new TMapData.FindPathDataListenerCallback() {
//            @Override
//            public void onFindPathData(TMapPolyLine tMapPolyLine) {
//                Timber.d("find path");
//                tmap.addTMapPolyLine("line", tMapPolyLine);
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.tabMsg.getId()){
            changeToActivity(MessageActivity.class);
        }else if(v.getId() == this.tabProfile.getId()){
            changeToActivity(ProfileActivity.class);
        }else if(v.getId() == this.tabEvaluation.getId()){
            changeToActivity(ListUserActivity.class);
        }else if(v.getId() == this.tabOther.getId()){
            changeToActivity(MessageActivity.class);
        }else if(v.getId() == this.gpsButton.getId()){
            gpsOpened = !gpsOpened;
            if(gpsOpened){
                tMapGpsManager.OpenGps();
                gpsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            }else{
                tMapGpsManager.CloseGps();
                gpsButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        }else if(v.getId() == this.navigationButton.getId()){
            changeToActivity(NavigationActivity.class);
        }
    }

    public void changeToActivity(Class activity){
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    @Override
    public void onLocationChange(Location location) {
        if(gpsOpened){
            TMapPoint point = tMapGpsManager.getLocation();
            tmap.setCenterPoint(point.getLongitude(), point.getLatitude());
        }
    }
}
