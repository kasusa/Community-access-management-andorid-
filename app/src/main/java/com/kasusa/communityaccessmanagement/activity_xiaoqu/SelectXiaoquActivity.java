package com.kasusa.communityaccessmanagement.activity_xiaoqu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.kasusa.communityaccessmanagement.R;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.kasusa.communityaccessmanagement.datacls.xiaoqu;
import com.kasusa.communityaccessmanagement.threads.Thread_getXiaoquListPutThemInDatacls;

import java.util.LinkedList;

public class SelectXiaoquActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private xiaoquAdapter mAdapter;
    LinkedList<xiaoqu> mWordList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_xiaoqu);
        mRecyclerView = findViewById(R.id.recyclerView);
        //        跨activity关闭监听器
        BroadcastReceiver broadcast_reciever = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("finish")) {
//finishing the activity
                    finish();
                }
            }
        };
        registerReceiver(broadcast_reciever, new IntentFilter("finish"));


        try {
            fillxiaoquList();
            Log.println(Log.INFO,"meow","yousee");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * fill the xiaoqu list using sql thread
     * @throws InterruptedException
     */
    private void fillxiaoquList() throws InterruptedException {
        Log.println(Log.INFO,"meow","got ur xiaoqu list form mysql");
        Dataclass.reset();
        Thread_getXiaoquListPutThemInDatacls t = new Thread_getXiaoquListPutThemInDatacls();
        t.start();
        while (!Dataclass.threadDone) {
                Thread.sleep(500);}
        for (xiaoqu item : Dataclass.xiaoqulist){
            String s = item.toString();
            Log.println(Log.INFO,"meow",s);
        }
        mAdapter = new xiaoquAdapter(this, Dataclass.xiaoqulist);//告诉adapter我用的局部链表是啥
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
