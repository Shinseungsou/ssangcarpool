package com.jfsiot.mju.ssangcarpool.activity;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jfsiot.mju.ssangcarpool.R;
import com.jfsiot.mju.ssangcarpool.config.AppConst;
import com.jfsiot.mju.ssangcarpool.model.response.SimpleResponse;
import com.jfsiot.mju.ssangcarpool.support.api.Api;

import rx.functions.Action1;
import timber.log.Timber;

public class AuthActivity extends AppCompatActivity {
    private EditText authPw;
    private EditText authId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, AppConst.APP_PERMISSION.GPS);
        authId = ((EditText) findViewById(R.id.auth_id));
        authPw = ((EditText) findViewById(R.id.auth_pw));
        findViewById(R.id.auth_sign_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Api.call().postUsersSignin(authId.getText().toString(), authPw.getText().toString())
                        .subscribe(new Action1<SimpleResponse>() {
                            @Override
                            public void call(SimpleResponse simpleResponse) {
                                if (simpleResponse.result) {
                                    Intent intent = new Intent(AuthActivity.this, MainMapActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        });
            }
        });
        findViewById(R.id.auth_sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
