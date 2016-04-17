package com.jfsiot.mju.ssangcarpool.activity2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.jfsiot.mju.ssangcarpool.R;

import java.util.ArrayList;

/**
 * Created by mj on 2016-04-17.
 */
public class RiderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider);

        ArrayList<Rider> rider = new ArrayList<Rider>();

        rider.add(new Rider(R.drawable.android, "권동섭", "탑승 희망 시간 : 07:45AM", "2016년 4월 8일"));
        rider.add(new Rider(R.drawable.android, "이강선", "탑승 희망 시간 : 09:15AM", "2016년 4월 13일"));
        rider.add(new Rider(R.drawable.android, "신민호", "탑승 희망 시간 : 06:30PM", "2016년 4월 17일"));
        rider.add(new Rider(R.drawable.android, "안희철", "탑승 희망 시간 : 08:45PM", "2016년 4월 17일"));

        RiderAdapter radapter = new RiderAdapter(this , R.layout.viewholder_rider, rider);

        ListView rlist = (ListView) findViewById(R.id.rider_listView_1);
        rlist.setAdapter(radapter);

    }
}
