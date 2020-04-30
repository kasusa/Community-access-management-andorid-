package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.kasusa.communityaccessmanagement.Adapter.GenQrAdapter;
import com.kasusa.communityaccessmanagement.Adapter.xiaoquAdapter;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.kasusa.communityaccessmanagement.datacls.xiaoqu;
import com.kasusa.communityaccessmanagement.threads.Thread_getXiaoquListPutThemInDatacls;

import java.util.LinkedList;

public class ListGenQrActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private GenQrAdapter mAdapter;
    LinkedList<xiaoqu> mWordList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_gen_qr);

        mRecyclerView = findViewById(R.id.listfor_gen_qr_xiaoqu);
        try {
            fillxiaoquList();
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
        mAdapter = new GenQrAdapter(this, Dataclass.xiaoqulist);//告诉adapter我用的局部链表是啥
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
