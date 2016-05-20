package com.jfsiot.mju.ssangcarpool.activity2;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jfsiot.mju.ssangcarpool.R;
import com.jfsiot.mju.ssangcarpool.dialog.MessageDialog;
import com.jfsiot.mju.ssangcarpool.dialog.ReplyMsgDialog;

import java.util.List;

public class MessageAdapter extends BaseAdapter{
    
    private List<Message> items;
    
    private LayoutInflater inflater;
    private Context context;
    private int layout_res_id;
    
    public MessageAdapter(Context context, int layout_res_id, List<Message> items){
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
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        RoomViewHolder room;

//--dialog 안에 dialog
        final ReplyMsgDialog replyMsgDialog = new ReplyMsgDialog(context);
        replyMsgDialog.setMessageView(items.get(position));
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

        convertView = inflater.inflate(layout_res_id, parent, false);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //               AlertDialog.Builder message_dialog = new AlertDialog.Builder(context);
                //               message_dialog.setMessage(items.get(position).contents).show();


                MessageDialog messageDialog = new MessageDialog(context);
                messageDialog.setMessageView(items.get(position));
                messageDialog.setPositiveButton("답장", new DialogInterface.OnClickListener() {
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


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
//                alertBuilder.setMessage(items.get(position).contents).show();

                MessageDialog messageDialog = new MessageDialog(context);
                messageDialog.setMessageView(items.get(position));
                messageDialog.setPositiveButton("답장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "답장이 전송되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                messageDialog.setNegativeButton("확인", new DialogInterface.OnClickListener() { @Override public void onClick(DialogInterface dialog, int which) { } });
                messageDialog.setNeutralButton("중간", new DialogInterface.OnClickListener() { @Override public void onClick(DialogInterface dialog, int which) { } });
                messageDialog.show();
            }
        });
        room = new RoomViewHolder();
        room.imageview = (ImageView)convertView.findViewById(R.id.message_imageView_1);
        room.title = (TextView)convertView.findViewById(R.id.message_textView_1);
        room.contents = (TextView)convertView.findViewById(R.id.message_textView_2);
        room.date = (TextView)convertView.findViewById(R.id.message_textView_3);

        room.imageview.setImageResource(R.drawable.android);
        room.title.setText(items.get(position).title);
        room.contents.setText(items.get(position).contents);
        room.date.setText(items.get(position).date);



        return convertView;
    }

    public class RoomViewHolder{
        public ImageView imageview;
        public TextView title;
        public TextView contents;
        public TextView date;
    }
}
