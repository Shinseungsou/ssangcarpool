package com.jfsiot.mju.ssangcarpool.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jfsiot.mju.ssangcarpool.R;
import com.jfsiot.mju.ssangcarpool.model.data.Message;

/**
 * Created by 박현민 on 2016-05-20.
 */
public class ReplyMsgDialog extends AlertDialog.Builder {

    private Context context;
    private LayoutInflater inflater;

    public ReplyMsgDialog(Context context){
        super(context);
        this.context = context;
        this.inflater = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
    }

    public AlertDialog.Builder setMessageView(Message message){
        View view = this.inflater.inflate(R.layout.dialog_reply_message,null);
        ReplyMsgDialogView messageDialogView = new ReplyMsgDialogView(view, message);
        this.setView(view);
        return this;
    }

    public class ReplyMsgDialogView{
        private ImageView profileImage;
        private TextView receiverName;
        private TextView sendDate;
        private TextView messageContents;

        public ReplyMsgDialogView(View view, Message message){
            profileImage = ((ImageView)view.findViewById(R.id.profile_image));
            receiverName = ((TextView)view.findViewById(R.id.receiver_name_reply));
            sendDate = ((TextView)view.findViewById(R.id.send_date_reply));
            messageContents = ((TextView)view.findViewById(R.id.message_contents_reply));

            profileImage.setImageDrawable(view.getResources().getDrawable(message.image));
            receiverName.setText(message.title);
            sendDate.setText(message.date);
        }

    }
}
