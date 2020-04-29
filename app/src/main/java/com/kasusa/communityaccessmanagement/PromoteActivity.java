package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.kasusa.communityaccessmanagement.threads.Thread_IsCitizenWorker;
import com.kasusa.communityaccessmanagement.threads.Thread_Promote;
import com.kasusa.communityaccessmanagement.util.ShareContext;
import com.kasusa.communityaccessmanagement.util.citizenID;

public class PromoteActivity extends AppCompatActivity {
    TextView name;
    TextView isworker;
    EditText editText_citizenid;
    Button button_query;
    Button button_up;
    Button button_down;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promote);
        name = findViewById(R.id.textView49);
        isworker = findViewById(R.id.textView50);
        editText_citizenid = findViewById(R.id.editText12);
        button_query = findViewById(R.id.button9);
        button_up = findViewById(R.id.button10);
        button_down = findViewById(R.id.button11);

        name.setText("");
        isworker.setText("");
        button_up.setEnabled(false);
        button_down.setEnabled(false);
    }

    /**
     * 查询按钮
     * 首先判断edittext里面是否为身份证
     * 如果是然后通过mysql查询这个人的姓名信息和是否为worker
     * 并且把值通过textview展示.
     * @param view
     */
    public void query_citizen(View view) {
        button_up.setEnabled(false);
        button_down.setEnabled(false);

        Dataclass.reset();
        Dataclass.qurey_citizenID = editText_citizenid.getText().toString();
        if(!citizenID.isIDNumber(Dataclass.qurey_citizenID)){
            Toast.makeText(this,"身份证不合法", Toast.LENGTH_SHORT).show();
            name.setText("");
            isworker.setText("");
        }else {
            Thread_IsCitizenWorker t = new Thread_IsCitizenWorker();
            t.start();
            while (!Dataclass.threadDone) {
                    SystemClock.sleep(500);
            }
            // 换个颜色给人醒目的感觉...
            name.setTextColor(Color.parseColor("#0d86ff"));
            name.setTextColor(Color.parseColor("#0d86ff"));
            name.setText(Dataclass.qurey_name);
            isworker.setText(Dataclass.qurey_isworker);
        }
        // 判断应该启动的按钮.
        if(Dataclass.qurey_isworker.equals("是")){
            button_down.setEnabled(true);
        }else
            button_up.setEnabled(true);
    }

    public void button_up(View view) {
        Dataclass.reset();
        Dataclass.qurey_citizenID = editText_citizenid.getText().toString();
        Dataclass.qurey_Promote = true;
        Thread_Promote t = new Thread_Promote();
        t.start();
        while (!Dataclass.threadDone)
            SystemClock.sleep(500);
        Toast.makeText(this,"操作成功.", Toast.LENGTH_SHORT).show();
        button_query.callOnClick();
    }

    public void button_down(View view) {
        Dataclass.reset();
        Dataclass.qurey_citizenID = editText_citizenid.getText().toString();
        Dataclass.qurey_Promote = false;
        Thread_Promote t = new Thread_Promote();
        t.start();
        while (!Dataclass.threadDone)
            SystemClock.sleep(500);
        Toast.makeText(this,"操作成功.", Toast.LENGTH_SHORT).show();
        button_query.callOnClick();
    }
}
