package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.kasusa.communityaccessmanagement.Adapter.historyAdapter;
import com.kasusa.communityaccessmanagement.datacls.DataUserinfo;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.kasusa.communityaccessmanagement.datacls.history;
import com.kasusa.communityaccessmanagement.threads.Thread_getHistoryList;

import java.util.LinkedList;

public class ShowHistoryActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private historyAdapter mAdapter;
    private ImageView nodataimage;
    LinkedList<history> mWordList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);
        mRecyclerView = findViewById(R.id.recycleviewhistory);
        String id = DataUserinfo.user_citizenID;
        try {
            dosql_getHistoryList(id);
        }catch (Exception e){

        }
        //无数据的显示结果:
        if (Dataclass.historylist==null)
        {
            Toast.makeText(this,"没有任何历史数据", Toast.LENGTH_SHORT).show();
            this.finish();
        }else {
            mWordList = Dataclass.historylist;

            mAdapter = new historyAdapter(this, mWordList);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    private void dosql_getHistoryList(String id) {
        Dataclass.reset();
        Dataclass.qurey_citizenID = id;
        Thread_getHistoryList t = new Thread_getHistoryList();
        t.start();
        while (!Dataclass.threadDone) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
