package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kasusa.communityaccessmanagement.activity_xiaoqu.SelectXiaoquActivity;

public class WorkerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);
    }

    public void search_card(View view) {
    }

    public void card_gen_qr(View view) {
        startActivity(new Intent(this, ListGenQrActivity.class));
    }

    public void card_Promote(View view) {
        startActivity(new Intent(this, PromoteActivity.class));
    }
}
