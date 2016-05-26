package com.jfsiot.mju.ssangcarpool.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jfsiot.mju.ssangcarpool.R;
import com.jfsiot.mju.ssangcarpool.model.data.Message;


public class MessageDialog extends AlertDialog.Builder {
    private Context context;
    private LayoutInflater inflater;

    public MessageDialog(Context context) {
        super(context);
        this.context = context;
        this.inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
    }

    public AlertDialog.Builder setMessageView(Message message){
        View view = this.inflater.inflate(R.layout.dialog_message, null);
        MessageDialogView messageDialogView = new MessageDialogView(view, message);

        this.setView(view);

        return this;
    }

    public class MessageDialogView  {
      private ImageView profileImage;
      private TextView senderName;
      private TextView receivedDate;
      private TextView messageContents;

        public MessageDialogView(View view, Message message){
            profileImage =((ImageView) view.findViewById(R.id.profile_image));
            senderName =((TextView) view.findViewById(R.id.sender_name));
            receivedDate= ((TextView) view.findViewById(R.id.received_date));
            messageContents = ((TextView) view.findViewById(R.id.message_contents));

            profileImage.setImageDrawable(view.getResources().getDrawable(message.image));
            senderName.setText(message.title);
            receivedDate.setText(message.date);
            messageContents.setText(message.contents);
        }
    }
}
