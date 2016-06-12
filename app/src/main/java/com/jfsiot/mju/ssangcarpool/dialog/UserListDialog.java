package com.jfsiot.mju.ssangcarpool.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jfsiot.mju.ssangcarpool.R;
import com.jfsiot.mju.ssangcarpool.model.data.Carpooler;

/**
 * Created by SSS on 2016-05-30.
 */
public class UserListDialog extends AlertDialog.Builder {
    private Context context;
    private LayoutInflater inflater;

    public UserListDialog(Context context) {
        super(context);
        this.context = context;
        this.inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
    }

    public AlertDialog.Builder setMessageView(Carpooler carpooler){
        View view = this.inflater.inflate(R.layout.dialog_carpooler, null);
        UserListDialogView messageDialogView = new UserListDialogView(view, carpooler);

        this.setView(view);

        return this;
    }

    public class UserListDialogView  {
        private TextView username;
        private TextView origination;
        private TextView destination;
        private TextView date;
        private TextView carpoolerType;

        public UserListDialogView(View view, Carpooler carpooler){
            ((TextView) view.findViewById(R.id.carpooler_type)).setText(carpooler.getCarpoolerTypeString());
            ((TextView) view.findViewById(R.id.text_origination)).setText(carpooler.origination);
            ((TextView) view.findViewById(R.id.text_destination)).setText(carpooler.destination);
            ((TextView) view.findViewById(R.id.date)).setText(carpooler.date);
            ((TextView) view.findViewById(R.id.username)).setText(carpooler.userData.name);
        }
    }
}
