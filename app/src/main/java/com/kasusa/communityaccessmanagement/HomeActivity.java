package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide(); //<< this
        setContentView(R.layout.activity_home);


        BroadcastReceiver broadcast_reciever = new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("finish")) {
                    finish();
                }
            }
        };
        registerReceiver(broadcast_reciever, new IntentFilter("finish"));
    }

    public void scan(View view) {
    }

    public void history(View view) {

    }

    public void management(View view) {

    }
    public void me(View view) {
        Intent intent = new Intent(this, MeActivity.class);
        startActivity(intent);
    }


}
