package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kasusa.communityaccessmanagement.datacls.DataUserinfo;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.kasusa.communityaccessmanagement.threads.Thread_IsCitizenWorker;
import com.kasusa.communityaccessmanagement.threads.Thread_getHistoryList;
import com.kasusa.communityaccessmanagement.util.citizenID;

public class FindHistoryByidActivity extends AppCompatActivity {
    EditText et_id;
    Button btn_his;
    Button btn_personinfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_history_byid);
        et_id = findViewById(R.id.editText13);
        btn_his = findViewById(R.id.button12);
        btn_personinfo = findViewById(R.id.button13);
    }

    /**
     * 查询某个人的出行历史按钮
     * @param view
     */
    public void find_history(View view) {
        Dataclass.reset();
        String ID = et_id.getText().toString();
        if(!citizenID.isIDNumber(ID)){
            Toast.makeText(this,"身份证不合法", Toast.LENGTH_SHORT).show();
        }else {
            // 这里开始是从homeactivity搬过来的,改了一点
            dosql_getHistoryList(ID);
            Toast.makeText(this,"正在查询,稍等", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ShowHistoryActivity.class));
        }
    }

    /**查询历史按钮用到的函数
     * @param id
     */
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

    public void find_personinfo(View view){
        String ID = et_id.getText().toString();

        Dataclass.qurey_citizenID = ID;
        // 这个boolans用于移除(退出登录按钮)
        Dataclass.boolanswer = true;

        Toast.makeText(this,"正在查询,稍等", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MeActivity.class);
        startActivity(intent);
    }
}
