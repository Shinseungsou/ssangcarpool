package com.jfsiot.mju.ssangcarpool.support.api;

public class Api {
    /* Singleton */
    private static Api instance = null;
    public static Api getInstance() {
        return instance;
    }

    /* Instance Creation */
    private Api(ApiGoogleMaps apiGoogleMaps) {
        this.googleMapsApi = apiGoogleMaps;
    }

    /**
     * Only triggered when first triggered.
     * @param apiGoogleMaps
     */
    public static synchronized void createInstance(ApiGoogleMaps apiGoogleMaps) {
        if(Api.instance != null) return;
        Api.instance = new Api(apiGoogleMaps);
    }

    /* Register Api*/
    private final ApiGoogleMaps googleMapsApi;
    public static ApiGoogleMaps googleMaps() {
        return Api.instance.googleMapsApi;
    }
}
