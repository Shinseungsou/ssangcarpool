package com.jfsiot.mju.ssangcarpool.model.data;

import java.util.List;

/**
 * Created by mj on 2016-04-16.
 */
public class Message {
    public int uid;
    public int image;
    public String title;
    public String contents;
    public String date;

    public Message(int i, String t, String c, String d){
        image = i; title = t; contents =c; date = d;
    }
}
