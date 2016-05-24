package com.jfsiot.mju.ssangcarpool.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.jfsiot.mju.ssangcarpool.R;
import com.jfsiot.mju.ssangcarpool.model.unique.HistoryDetail;

public class HistoryDetailActivity extends Activity {
    private TextView carpoolerType;
    private TextView destination;
    private TextView origination;
    private TextView carpoolerName;
    private TextView carpoolerOrigination;
    private TextView carpoolerDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);

        carpoolerType = ((TextView) this.findViewById(R.id.path_type));
        destination = ((TextView) this.findViewById(R.id.text_destination));
        origination = ((TextView) this.findViewById(R.id.text_origination));
        carpoolerName = ((TextView) this.findViewById(R.id.carpooler_name));
        carpoolerOrigination = ((TextView) this.findViewById(R.id.text_carpooler_origination));
        carpoolerDestination = ((TextView) this.findViewById(R.id.text_carpooler_destination));

        HistoryDetail historyDetail = HistoryDetail.getInstance();

        carpoolerType.setText(historyDetail.getPath().carpooler_type);
        destination.setText(historyDetail.getPath().destination);
        origination.setText(historyDetail.getPath().origination);
        carpoolerName.setText(historyDetail.getCarpooler().user.name);
        carpoolerDestination.setText(historyDetail.getCarpooler().destination);
        carpoolerOrigination.setText(historyDetail.getCarpooler().origination);
    }

}
