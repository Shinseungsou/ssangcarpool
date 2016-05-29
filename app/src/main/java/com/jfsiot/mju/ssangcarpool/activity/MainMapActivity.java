package com.jfsiot.mju.ssangcarpool.activity;


import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.jfsiot.mju.ssangcarpool.R;
import com.jfsiot.mju.ssangcarpool.activity2.ProfileActivity;
import com.jfsiot.mju.ssangcarpool.adapter.OnItemClickListener;
import com.jfsiot.mju.ssangcarpool.adapter.SearchAdapter;
import com.jfsiot.mju.ssangcarpool.model.map.MapPOIData;
import com.jfsiot.mju.ssangcarpool.model.map.MapRoute;
import com.jfsiot.mju.ssangcarpool.support.api.Api;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapGpsManager;
import com.skp.Tmap.TMapGpsManager.onLocationChangedCallback;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import timber.log.Timber;

public class MainMapActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback, OnItemClickListener, View.OnClickListener, onLocationChangedCallback {

    private ImageView tabMsg;
    private ImageView tabProfile;
    private ImageView tabEvaluation;
    private ImageView tabOther;

    private ImageView gpsButton;
    private ImageView navigationButton;

    private ImageView searchButton;
    private EditText searchView;
    private ListView searchResult;


    private LinearLayout tabContainer;

    private RelativeLayout pathRegisterContainer;
    private TextView pathRegisterButton;
    private TextView pathDatePicker;
    private TextView pathTimePicker;
    private TextView carpoolerType;

    private TMapView tmap;
    private TMapGpsManager tMapGpsManager;

    private boolean gpsOpened;
    private List<MapPOIData> items;

    private SearchAdapter adapter;

    private int PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);
        tmap = new TMapView(this.getBaseContext());
        tmap.setSKPMapApiKey(getResources().getString(R.string.tmap_api_server_key));
        tmap.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmap.setIconVisibility(true);
        tmap.setZoomLevel(15);
//        tmap.setCompassMode(true);
        tmap.setTrackingMode(true);
        tMapGpsManager = new TMapGpsManager(this);
        tMapGpsManager.setMinTime(1000);
        tMapGpsManager.setMinDistance(5);
        tMapGpsManager.setProvider(TMapGpsManager.LOCATION_SERVICE);
//        tMapGpsManager.OpenGps();

        ((FrameLayout) findViewById(R.id.maps_map)).addView(tmap);
        this.searchResult = (ListView) findViewById(R.id.search_result);
        this.searchResult.setVisibility(View.GONE);

        this.tabContainer = ((LinearLayout) findViewById(R.id.tab_container));
        this.pathRegisterContainer = ((RelativeLayout) findViewById(R.id.path_register_container));
        this.pathRegisterButton = ((TextView) findViewById(R.id.path_register_button));
        this.pathRegisterButton.setOnClickListener(this);
        this.pathDatePicker = ((TextView) findViewById(R.id.path_datepicker));
        pathDatePicker.setOnClickListener(this);
        this.pathTimePicker = ((TextView) findViewById(R.id.path_timepicker));
        pathTimePicker.setOnClickListener(this);
        this.carpoolerType = ((TextView) findViewById(R.id.carpooler_type));
        this.carpoolerType.setOnClickListener(this);

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


        if( Build.VERSION.SDK_INT >= 23 ){
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION);
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_CODE);
            }else{
                doAfterPermission(true);
            }
        }

        tabMsg.setOnClickListener(this);
        tabEvaluation.setOnClickListener(this);
        tabProfile.setOnClickListener(this);
        tabOther.setOnClickListener(this);

        gpsButton.setOnClickListener(this);
        navigationButton.setOnClickListener(this);
        Api.call().get().subscribe();

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
            Timber.d("center point : <%s> <%s>", MapRoute.getInstance().getPointOrigination().getLatitude(), MapRoute.getInstance().getPointOrigination().getLongitude());
            tmap.setCenterPoint(MapRoute.getInstance().getPointOrigination().getLongitude(), MapRoute.getInstance().getPointOrigination().getLatitude(), true);
            setMapMode(MAPMODE.ROUTE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
         if(requestCode == PERMISSION_CODE)
             doAfterPermission(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED);
    }

    public void doAfterPermission(boolean isGranted){
        if(isGranted){
            this.gpsButton.setVisibility(View.VISIBLE);
        }else{
            this.gpsButton.setVisibility(View.GONE);
        }
    }

    public enum MAPMODE{
        MAP, ROUTE
    }
    public void setMapMode(MAPMODE mapMode){
        switch (mapMode){
            case MAP:
                this.pathRegisterContainer.setVisibility(View.GONE);
                this.tabContainer.setVisibility(View.VISIBLE);
                MapRoute.getInstance().clear();
                break;
            case ROUTE:
                this.pathRegisterContainer.setVisibility(View.VISIBLE);
                this.tabContainer.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //뒤로가기 버튼 눌렀을때
        if (this.searchResult.getVisibility() == View.VISIBLE) this.searchResult.setVisibility(View.GONE);
        else if(this.pathRegisterContainer.getVisibility() == View.VISIBLE){
            setMapMode(MAPMODE.MAP);
        }
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
//        tmap.setCenterPoint(Double.parseDouble(mapPOIData.getCenterLon()), Double.parseDouble(mapPOIData.getCenterLat()));
        tmap.setLocationPoint(Double.parseDouble(mapPOIData.getCenterLon()), Double.parseDouble(mapPOIData.getCenterLat()));
        this.searchView.setText(mapPOIData.getName());

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
            changeToActivity(UserListActivity.class);
        }else if(v.getId() == this.tabOther.getId()){
            changeToActivity(MessageActivity.class);
        }else if(v.getId() == this.gpsButton.getId()){
            gpsOpened = !gpsOpened;
            if (gpsOpened){
                tMapGpsManager.OpenGps();
                tmap.setTrackingMode(true);
                gpsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            }else{
                tmap.setTrackingMode(false);
                tMapGpsManager.CloseGps();
                gpsButton.setBackgroundColor(getResources().getColor(R.color.white));
            }
        }else if(v.getId() == this.navigationButton.getId()) {
            changeToActivity(NavigationActivity.class);
        }else if (v.getId() == this.pathDatePicker.getId()){
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    pathDatePicker.setText(year+"년 "+monthOfYear+"월 "+dayOfMonth+"일");
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)).show();
        }else if(v.getId() == this.pathTimePicker.getId()) {
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pathTimePicker.setText(hourOfDay+"시 "+minute+"분");
                }
            }, calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE), true).show();
        }else if(v.getId() == this.carpoolerType.getId()){
            AlertDialog.Builder alerDialog = new AlertDialog.Builder(this);
            alerDialog.setItems(new String[]{"차량보유자", "탑승희망자", "스쿠터보유자"}, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    carpoolerType.setText(((AlertDialog) dialog).getListView()
                            .getItemAtPosition(which).toString());
                }
            }).show();
        }else if(v.getId() == this.pathRegisterButton.getId()){
            setMapMode(MAPMODE.MAP);
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
            tmap.setLocationPoint(point.getLongitude(), point.getLatitude());
        }
        Timber.d("center changed : <%s> <%s>", location.getLongitude(), location.getLatitude());
    }
}
