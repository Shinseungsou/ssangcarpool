package com.jfsiot.mju.ssangcarpool.model.data;

public class PassengerName {
    public String origination;
    public String destination;
    public String date;

    public PassengerName(String origination, String destination, String date){
        this.origination = origination;
        this.destination = destination;
        this.date = date;
    }
}

