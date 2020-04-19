package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LoginPwdActivity extends AppCompatActivity {

    String id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pwd);

        TextView passed_id = (TextView) findViewById(R.id.textView_showcitizenid);//show citizen id passed by loginactivity.java
        Intent intent = getIntent();
         id = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
        passed_id.setText(id);


    }
}
