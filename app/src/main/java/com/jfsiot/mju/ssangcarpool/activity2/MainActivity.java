package com.jfsiot.mju.ssangcarpool.activity2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jfsiot.mju.ssangcarpool.R;
import com.jfsiot.mju.ssangcarpool.activity.MessageActivity;
import com.jfsiot.mju.ssangcarpool.activity.SignUpActivity;

public class MainActivity extends AppCompatActivity {
    Button Button_1;
    Button Button_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button_1 = ((Button) findViewById(R.id.button_1));
        Button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button_2 = ((Button) findViewById(R.id.button_2));
        Button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent2);
                finish();
            }
        });
    }
}
