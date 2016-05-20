package com.jfsiot.mju.ssangcarpool.activity2;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import com.jfsiot.mju.ssangcarpool.R;

import java.util.ArrayList;

/**
 * Created by User on 2016-04-16.
 */
public class ProfileActivity extends AppCompatActivity{

    Button button;
    EditText editText1, editText2, editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile);

        ArrayList<Room> arDessert = new ArrayList<Room>();
        //Room item1 = new Room(R.drawable.capture,"제목","캬르르르를르를르르르를르ㅡㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹ르르르르르를르르르르르르르르르르르르르르르르ㅡ를륵" );
        //item1.image = R.drawable.capture;
        //item1.title = "제목";
        //item1.contents = "캬르르르를르를르르르를르ㅡㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹ르르르르르를르르르르르르르르르르르르르르르르ㅡ를륵";
        arDessert.add(new Room("기흥역", "명지대 사거리" ,"2016년 4월 5일" ));
        arDessert.add(new Room("기흥역" ,"강남역", "2016년 2월 1일" ));
        arDessert.add(new Room("도봉역" , "화정역","2016년 1월 30일" ));
        arDessert.add(new Room("쌍용역" , "분당역","2016년 1월 2일" ));
        arDessert.add(new Room("사당역" ,"명지대","2015년 12월 31일" ));
        //arDessert.add("List1");
        //arDessert.add("List2");
        //arDessert.add("List3");
        //arDessert.add("List4");

        ((RatingBar) findViewById(R.id.avg_rating)).setProgress(4);

        //ArrayAdapter<String> adapter;
        Aadapter aadapter1 = new Aadapter(this, R.layout.relativepra2,arDessert);

        final ArrayList<Room2> room2 = new ArrayList<>();
        room2.add(new Room2("권동섭","운전 잘하십니다.",4 ));
        room2.add(new Room2("이강선","위험한 운전이였습니다." ,5));
        room2.add(new Room2("신민호","매너가 좋으십니다." ,5));
        room2.add(new Room2("안희철","좋은 운전이였습니다.",3 ));

        final Badapter badapter2 = new Badapter(this, R.layout.relative1,room2);

        ListView list1 = (ListView) findViewById(R.id.list1);
        list1.setAdapter(aadapter1);

        list1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list1.setDivider(new ColorDrawable(Color.LTGRAY ));
        list1.setDividerHeight(2);


        ListView list2 = (ListView) findViewById(R.id.list2);
        list2.setAdapter(badapter2);
        list2.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list2.setDivider(new ColorDrawable(Color.LTGRAY));
        list2.setDividerHeight(2);




        button = (Button) findViewById(R.id.myprofile_button1);
        editText1 = (EditText) findViewById(R.id.myprofile_edittext1);
        editText2 = (EditText) findViewById(R.id.myprofile_edittext2);
        editText3 = (EditText) findViewById(R.id.myprofile_edittext3);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                room2.add(new Room2(editText1.getText().toString(),editText2.getText().toString(),
                        Integer.parseInt(editText3.getText().toString())));
                badapter2.notifyDataSetChanged();
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                editText1.requestFocus();
            }
        });
    }
}

