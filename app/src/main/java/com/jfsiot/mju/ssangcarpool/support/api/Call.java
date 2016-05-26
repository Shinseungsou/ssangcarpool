package com.jfsiot.mju.ssangcarpool.support.api;

import com.google.maps.model.DirectionsResult;
import com.jfsiot.mju.ssangcarpool.model.response.VoidResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by SSS on 2016-04-15.
 */
public interface Call {
    @GET("/json")
    Observable<DirectionsResult> getDirections(
        @Query("origin") String origin,
        @Query("destination") String destinaton,
        @Query("key") String key,
        @Query("sensor") Boolean sensor
    );
    @GET("/user")
    Observable<VoidResponse> get(
    );
}
