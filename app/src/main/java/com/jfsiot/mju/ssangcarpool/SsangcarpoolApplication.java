package com.jfsiot.mju.ssangcarpool;

import android.app.Application;

import com.jfsiot.mju.ssangcarpool.support.api.Api;
import com.jfsiot.mju.ssangcarpool.support.api.ApiManager;
import com.jfsiot.mju.ssangcarpool.support.api.RetrofitLogger;

import retrofit.RestAdapter;
import timber.log.Timber;

/**
 * Created by SSS on 2016-04-15.
 */
public class SsangcarpoolApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
        Api.createInstance(ApiManager.createGoogleMapsApi(this, RestAdapter.LogLevel.FULL, new RetrofitLogger("RetrofitApi", "^[AC\\-\\<\\{].*")));
    }
}
