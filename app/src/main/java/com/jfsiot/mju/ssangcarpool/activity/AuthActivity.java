package com.jfsiot.mju.ssangcarpool.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.EditText;

import com.jfsiot.mju.ssangcarpool.R;
import com.jfsiot.mju.ssangcarpool.config.AppConst;
import com.jfsiot.mju.ssangcarpool.model.response.UserResponse;
import com.jfsiot.mju.ssangcarpool.model.unique.User;
import com.jfsiot.mju.ssangcarpool.support.api.Api;

import rx.functions.Action1;

public class AuthActivity extends Activity {
    private EditText authPw;
    private EditText authId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, AppConst.APP_PERMISSION.GPS);
        authId = ((EditText) findViewById(R.id.auth_id));
        authPw = ((EditText) findViewById(R.id.auth_pw));
    }

    @Override
    protected void onResume() {
        super.onResume();
        findViewById(R.id.auth_sign_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Api.call().postUsersSignin(authId.getText().toString(), authPw.getText().toString())
                    .subscribe(new Action1<UserResponse>() {
                        @Override
                        public void call(UserResponse response) {
                            if (response.result) {
                                User.getInstance().setUserData(response.user);
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
