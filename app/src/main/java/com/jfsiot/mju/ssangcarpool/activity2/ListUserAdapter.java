package com.jfsiot.mju.ssangcarpool.activity2;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jfsiot.mju.ssangcarpool.R;
import com.jfsiot.mju.ssangcarpool.dialog.ReplyMsgDialog;
import com.jfsiot.mju.ssangcarpool.dialog.UserListDialog;
import com.jfsiot.mju.ssangcarpool.model.data.Carpooler;
import com.jfsiot.mju.ssangcarpool.model.data.Message;
import com.jfsiot.mju.ssangcarpool.model.data.PassengerName;
import com.jfsiot.mju.ssangcarpool.model.data.UserData;

import java.util.List;

import timber.log.Timber;

/**
 * Created by User on 2016-05-20.
 */

public class ListUserAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private int layout_res_id;
    private List<PassengerName> items;

    public ListUserAdapter(Context context, int layout_res_id, List<PassengerName> items){
        this.context = context;
        this.layout_res_id = layout_res_id;
        this.items = items;
        this.inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        RoomViewHolder passengername;
        //if(convertView == null){
        convertView = inflater.inflate(layout_res_id, parent, false);
        passengername = new RoomViewHolder();
        passengername.title1 = (TextView)convertView.findViewById(R.id.departure_name);
        passengername.title2 = (TextView)convertView.findViewById(R.id.destination_name);
        passengername.contents = (TextView)convertView.findViewById(R.id.date);
        //}
        passengername.title1.setText(items.get(position).origination);
        passengername.title2.setText(items.get(position).destination);
        passengername.contents.setText(items.get(position).date);
        Timber.d("items : %s %s", items.get(position).origination, items.get(position).destination);

        Message message = new Message(R.drawable.android, "권동섭", "카풀 신청합니다. 연락주세요.", "2016년 4월 8일");

        final ReplyMsgDialog replyMsgDialog = new ReplyMsgDialog(context);
        replyMsgDialog.setMessageView(message);
        replyMsgDialog.setPositiveButton("보내기", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which){
                Toast.makeText(context, "메시지가 보내졌습니다.", Toast.LENGTH_SHORT).show();
            }

        });
        replyMsgDialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "전송이 취소되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserListDialog messageDialog = new UserListDialog(context);
                Carpooler carpooler = new Carpooler();
                carpooler.userData = new UserData();
                carpooler.userData.name = "권동섭";
                carpooler.origination = "양재역";
                carpooler.destination = "명지대학교(자연)";
                carpooler.date = "2016년 5월 31일 12시 30분";
                carpooler.carpoolerType = 2;
                messageDialog.setMessageView(carpooler);
                messageDialog.setTitle("탑승 희망자");
                messageDialog.setPositiveButton("메시지", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        replyMsgDialog.show();
                    }
                });
                messageDialog.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


                messageDialog.show();
            }
        });
        return convertView;
    }
    public class RoomViewHolder{
        public TextView title1;
        public TextView title2;
        public TextView contents;
    }
}