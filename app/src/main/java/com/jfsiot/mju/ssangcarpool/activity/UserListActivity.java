package com.jfsiot.mju.ssangcarpool.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.jfsiot.mju.ssangcarpool.R;
import com.jfsiot.mju.ssangcarpool.activity2.ListUserAdapter;
import com.jfsiot.mju.ssangcarpool.model.data.PassengerName;

import java.util.ArrayList;

/**
 * Created by User on 2016-05-20.
 */

public class UserListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listuser);

        ArrayList<PassengerName> userList = new ArrayList<>();
        userList.add(new PassengerName("강남역 12번출구",  "명지대 사거리" ,"2016년 5월 31일 14시 20분" ));
        userList.add(new PassengerName("양재역",   "명지대학교(자연)","2016년 5월 31일 12시 30분" ));
        userList.add(new PassengerName("강남구 역삼동 강남대로 5", "명지대진입로","2016년 5월 31일 16시 20분" ));
        userList.add(new PassengerName("신갈 오거리" , "용인대학교","2016년 5월 31일 15시 30분" ));
        userList.add(new PassengerName("오리역" , "명지대학교(자연)","2016년 5월 31일 14시 20분" ));

        ListUserAdapter listUserAdapter = new ListUserAdapter(this, R.layout.item_userlist, userList);

        ListView list1 = (ListView) findViewById(R.id.list1);
        list1.setAdapter(listUserAdapter);

        list1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list1.setDivider(new ColorDrawable(Color.LTGRAY ));
        list1.setDividerHeight(2);
    }
}
