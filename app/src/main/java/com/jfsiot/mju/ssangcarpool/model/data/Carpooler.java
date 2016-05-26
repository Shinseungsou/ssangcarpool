package com.jfsiot.mju.ssangcarpool.model.data;

/**
 * Created by SSS on 2016-05-24.
 */
public class Carpooler {
    public int uid;
    public User user;
    public String origination;
    public String destination;
    public String date;
    public int carpoolerType;

    public Carpooler(){
        this.user = new User();
    }
}
