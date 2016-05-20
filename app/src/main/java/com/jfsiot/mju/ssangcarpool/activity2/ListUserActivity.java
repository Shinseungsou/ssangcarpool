package com.jfsiot.mju.ssangcarpool.activity2;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.jfsiot.mju.ssangcarpool.R;
import com.jfsiot.mju.ssangcarpool.model.data.PassengerName;

import java.util.ArrayList;

/**
 * Created by User on 2016-05-20.
 */

public class ListUserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listuser);

        ArrayList<PassengerName> arDessert = new ArrayList<PassengerName>();
        //Room item1 = new Room(R.drawable.capture,"제목","캬르르르를르를르르르를르ㅡㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹ르르르르르를르르르르르르르르르르르르르르르르ㅡ를륵" );
        //item1.image = R.drawable.capture;
        //item1.title = "제목";
        //item1.contents = "캬르르르를르를르르르를르ㅡㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹ르르르르르를르르르르르르르르르르르르르르르르ㅡ를륵";
        arDessert.add(new PassengerName("기흥역",  "명지대 사거리" ,"2016년 4월 5일" ));
        arDessert.add(new PassengerName("기흥역",   "강남역","2016년 2월 1일" ));
        arDessert.add(new PassengerName("도봉역",   "화정역","2016년 1월 30일" ));
        arDessert.add(new PassengerName("쌍용역" ,  "분당역","2016년 1월 2일" ));
        arDessert.add(new PassengerName("사당역" , "명지대","2015년 12월 31일" ));
        //arDessert.add("List1");
        //arDessert.add("List2");
        //arDessert.add("List3");
        //arDessert.add("List4");


        //ArrayAdapter<String> adapter;
        // Aadapter aadapter1 = new Aadapter(this, R.layout.relativepra2,arDessert);


        ListUserAdapter listUserAdapter = new ListUserAdapter(this, R.layout.relativepra2,arDessert);

        ListView list1 = (ListView) findViewById(R.id.list1);
        list1.setAdapter(listUserAdapter);

        list1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list1.setDivider(new ColorDrawable(Color.LTGRAY ));
        list1.setDividerHeight(2);


    }
}
