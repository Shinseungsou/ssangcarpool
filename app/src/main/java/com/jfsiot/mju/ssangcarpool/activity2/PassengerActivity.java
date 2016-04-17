package com.jfsiot.mju.ssangcarpool.activity2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.jfsiot.mju.ssangcarpool.R;

import java.util.ArrayList;

/**
 * Created by mj on 2016-04-17.
 */
public class PassengerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);

        ArrayList<Passenger> passenger = new ArrayList<Passenger>();

        passenger.add(new Passenger(R.drawable.android, "권동섭", "경유 시간 : 07:45AM", "2016년 4월 8일"));
        passenger.add(new Passenger(R.drawable.android, "이강선", "경유 시간 : 09:15AM", "2016년 4월 13일"));
        passenger.add(new Passenger(R.drawable.android, "신민호", "경유 시간 : 06:30PM", "2016년 4월 17일"));
        passenger.add(new Passenger(R.drawable.android, "안희철", "경유 시간 : 08:45PM", "2016년 4월 17일"));

        PassengerAdapter padapter = new PassengerAdapter(this , R.layout.viewholder_passenger, passenger);

        ListView plist = (ListView) findViewById(R.id.passenger_listView_1);
        plist.setAdapter(padapter);
    }
}
