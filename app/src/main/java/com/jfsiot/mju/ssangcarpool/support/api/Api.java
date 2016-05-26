package com.jfsiot.mju.ssangcarpool.support.api;

public class Api {
    /* Singleton */
    private static Api instance = null;
    public static Api getInstance() {
        return instance;
    }

    /* Instance Creation */
    private Api(Call Call) {
        this.call = Call;
    }

    /**
     * Only triggered when first triggered.
     * @param Call
     */
    public static synchronized void createInstance(Call Call) {
        if(Api.instance != null) return;
        Api.instance = new Api(Call);
    }

    /* Register Api*/
    private final Call call;
    public static Call call() {
        return Api.instance.call;
    }
}
