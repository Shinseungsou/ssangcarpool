package com.jfsiot.mju.ssangcarpool.support.api;

import android.content.Context;

import com.jfsiot.mju.ssangcarpool.BuildConfig;
import com.jfsiot.mju.ssangcarpool.config.AppConst;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class ApiManager {
    public static final int TIMEOUT = 30;

    private static RestAdapter getRestAdapter(Context context, RestAdapter.LogLevel loglevel, RestAdapter.Log logger) {
        /* Build RestAdapter */
        RestAdapter.Builder builder = new RestAdapter.Builder();
        if(BuildConfig.DEBUG) { // Debug Mode
            builder.setEndpoint(String.format("https://%s/api/%s/", AppConst.API.API_BASE_DEBUG, AppConst.API.API_VERSION_DEBUG));
            builder.setClient(new OkClient(new OkHttpClient()));
//            builder.setExecutors(executor, executor);
            builder.setLogLevel(loglevel);
            builder.setLog(logger);
        } else { // Release Mode
            builder.setEndpoint(String.format("https://%s/api/%s/", AppConst.API.API_BASE_RELEASE, AppConst.API.API_VERSION_RELEASE));
            builder.setClient(new OkClient(new OkHttpClient()));
//            builder.setExecutors(executor, executor);
        }
        return builder.build();
    }
    public static ApiGoogleMaps createGoogleMapsApi(Context context, RestAdapter.LogLevel loglevel, RestAdapter.Log logger) {
        return getRestAdapter(context, loglevel, logger).create(ApiGoogleMaps.class);
    }
}
