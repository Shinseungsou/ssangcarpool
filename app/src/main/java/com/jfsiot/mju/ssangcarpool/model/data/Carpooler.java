package com.jfsiot.mju.ssangcarpool.model.data;

/**
 * Created by SSS on 2016-05-24.
 */
public class Carpooler {
    public int uid;
    public UserData userData;
    public String origination;
    public String destination;
    public String date;
    public int carpoolerType;

    public Carpooler(){
        this.userData = new UserData();
    }
    public void update(UserData userData, int uid, String origination, String destination, String data, int carpoolerType){

    }

    public String getCarpoolerTypeString(){
        switch (carpoolerType){
            case 1:return "차량보유자";
            case 2: return "탑승희망자";
            case 3: return "스쿠터보유자";
            default: return "";
        }
    }
}
