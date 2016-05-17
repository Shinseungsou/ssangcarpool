package com.jfsiot.mju.ssangcarpool.activity2;

import java.util.List;

/**
 * Created by User on 2016-04-16.
 */
public class Room {
    public String departure_name;
    public String destination_name;
    public String date;

    Room(String departure_name, String destination_name, String date){
        this.departure_name = departure_name;
        this.destination_name = destination_name;
        this.date = date;
    }
}