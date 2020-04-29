package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.kasusa.communityaccessmanagement.util.ShareContext;
import com.kasusa.communityaccessmanagement.util.citizenID;

public class PromoteActivity extends AppCompatActivity {
    TextView name;
    TextView isworker;
    EditText editText_citizenid;
    Button button_query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promote);
        name = findViewById(R.id.textView49);
        isworker = findViewById(R.id.textView50);
        editText_citizenid = findViewById(R.id.editText12);
        button_query = findViewById(R.id.button9);

        name.setText("暂时没有结果");
        isworker.setText("暂时没有结果");
    }

    /**
     * 查询按钮
     * 首先判断edittext里面是否为身份证
     * 如果是然后通过mysql查询这个人的姓名信息和是否为worker
     * 并且把值通过textview展示.
     * @param view
     */
    public void query_citizen(View view) {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        Dataclass.reset();
        Dataclass.qurey_citizenID = editText_citizenid.getText().toString();
        if(!citizenID.isIDNumber(Dataclass.qurey_citizenID)){
            Toast.makeText(this,"身份证不合法", Toast.LENGTH_SHORT).show();
        }else {
            Thread_IsCitizenWorker t = new Thread_IsCitizenWorker();
            t.start();
            while (!Dataclass.threadDone) {
                    SystemClock.sleep(500);
            }
            name.setText(Dataclass.qurey_name);
            isworker.setText(Dataclass.qurey_isworker);
        }
        progress.dismiss();

    }

}
