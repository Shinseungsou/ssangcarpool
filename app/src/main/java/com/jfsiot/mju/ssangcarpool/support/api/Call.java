package com.jfsiot.mju.ssangcarpool.support.api;

import com.google.maps.model.DirectionsResult;
import com.jfsiot.mju.ssangcarpool.model.response.SimpleResponse;
import com.jfsiot.mju.ssangcarpool.model.response.UserResponse;
import com.jfsiot.mju.ssangcarpool.model.response.VoidResponse;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
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
    @GET("/users")
    Observable<VoidResponse> get(
    );
    @POST("/users/signup")
    Observable<SimpleResponse> postUsersSignup(
        @Query("id") String id,
        @Query("pw") String pw,
        @Query("username") String name,
        @Query("nick") String nick,
        @Query("email") String email,
        @Query("pn") String pn,
        @Query("gender") String gender
    );
    @POST("/users/signin")
    Observable<UserResponse> postUsersSignin(
            @Query("id") String id,
            @Query("pw") String pw
    );
    @POST("/path/register")
    Observable<SimpleResponse> postPathRegister(
            @Query("id") Integer id,
            @Body String route,
            @Query("from_lat") Double from_lat,
            @Query("from_lon") Double from_lon,
            @Query("to_lat") Double to_lat,
            @Query("to_lon") Double to_lon,
            @Query("carpooler_type") Integer carpooler,
            @Query("date_time") String datetime,
            @Query("duration") Double duraion
            );
}
