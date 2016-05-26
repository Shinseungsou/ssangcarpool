package com.jfsiot.mju.ssangcarpool.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.jfsiot.mju.ssangcarpool.R;
import com.jfsiot.mju.ssangcarpool.model.data.Message;
import com.jfsiot.mju.ssangcarpool.adapter.MessageAdapter;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        ArrayList<Message> message = new ArrayList<Message>();

        message.add(new Message(R.drawable.android, "권동섭", "카풀 신청합니다. 연락주세요.", "2016년 4월 8일"));
        message.add(new Message(R.drawable.android, "이강선", "안녕하세요. 목적지가 비슷한데 탑승 가능할까요?", "2016년 4월 13일"));
        message.add(new Message(R.drawable.android, "신민호", "경로보고 쪽지 드렸는데 분당까지 매주 가시나요?", "2016년 4월 17일"));
        message.add(new Message(R.drawable.android, "안희철", "두 명이서 같이 카풀 신청하려는데 괜찮나요", "2016년 4월 17일"));

        MessageAdapter madapter = new MessageAdapter(this , R.layout.viewholder_message, message);

        ListView mlist = (ListView) findViewById(R.id.message_listView_1);
        mlist.setAdapter(madapter);
    }
}
