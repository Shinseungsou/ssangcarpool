package com.jfsiot.mju.ssangcarpool.activity;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jfsiot.mju.ssangcarpool.R;
import com.jfsiot.mju.ssangcarpool.model.response.SimpleResponse;
import com.jfsiot.mju.ssangcarpool.support.api.Api;

import rx.functions.Action1;
import timber.log.Timber;


public class SignUpActivity extends AppCompatActivity {

    private EditText signupId;
    private EditText signupPw;
    private EditText signupName;
    private EditText signupNick;
    private EditText signupPhone;
    private EditText signupEmail;
    private EditText signupGentder;
    private TextView signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupId = ((EditText) findViewById(R.id.signup_id));
        signupPw = ((EditText) findViewById(R.id.signup_pw));
        signupName = ((EditText) findViewById(R.id.signup_name));
        signupNick = ((EditText) findViewById(R.id.signup_nickname));
        signupPhone = ((EditText) findViewById(R.id.signup_phonenumber));
        signupEmail = ((EditText) findViewById(R.id.signup_email));
        signupGentder = ((EditText) findViewById(R.id.signup_gender));

        signupButton = (TextView) findViewById(R.id.signup_signup_button);
        signupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Api.call().postUsersSignup(
                        signupId.getText().toString(),
                        signupPw.getText().toString(),
                        signupName.getText().toString(),
                        signupNick.getText().toString(),
                        signupPhone.getText().toString(),
                        signupEmail.getText().toString(),
                        signupGentder.getText().toString())
                        .subscribe(
                                new Action1<SimpleResponse>() {
                                    @Override
                                    public void call(SimpleResponse simpleResponse) {

                                    }
                                },
                                new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {
                                        throwable.printStackTrace();
                                        Timber.d("retrofit :throwable %s", throwable.getCause());
                                    }
                                }
                        );

                Toast.makeText(SignUpActivity.this,"회원가입 되었습니다!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
