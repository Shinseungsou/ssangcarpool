package com.jfsiot.mju.ssangcarpool.activity2;

import java.util.List;

/**
 * Created by mj on 2016-04-16.
 */
public class Message {
    public int image;
    public String title;
    public String contents;
    public String date;

    Message(int i, String t, String c, String d){
        image = i; title = t; contents =c; date = d;
    }
}
