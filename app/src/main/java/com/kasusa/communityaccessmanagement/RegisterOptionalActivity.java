package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kasusa.communityaccessmanagement.activity_xiaoqu.SelectXiaoquActivity;
import com.kasusa.communityaccessmanagement.datacls.DataUserinfo;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.kasusa.communityaccessmanagement.threads.Thread_IsCitizenAlreadyExist;
import com.kasusa.communityaccessmanagement.threads.Thread_NewUserinsert1;
import com.kasusa.communityaccessmanagement.util.EmailR;

public class RegisterOptionalActivity extends AppCompatActivity {

    EditText et_email;
    EditText et_note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_optional);
        et_email = findViewById(R.id.editText4);
        et_note = findViewById(R.id.editText6);

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
    }



    /**
     * 提交并登陆按钮
     * @param view
     */
    public void register_btn(View view) {
        String email = "";
        String note = "";
        String avtarurl = "";
        email = et_email.getText().toString();
        note = et_note.getText().toString();
        //TODO 头像
//        avtarurl = et_pwd1.getText().toString();
        if(email.equals("")) Toast.makeText(this, "请填写email", Toast.LENGTH_SHORT).show();
        else   if(!EmailR.isEmail(email)) Toast.makeText(this, "email 格式错误", Toast.LENGTH_SHORT).show();
//        else if(avtarurl.equals("")) Toast.makeText(this, "请上传一张您的照片", Toast.LENGTH_SHORT).show();else
        else {
            DataUserinfo.user_email = email;
            DataUserinfo.user_note = note;
            if (avtarurl.equals(""))avtarurl = "http://ww1.sinaimg.cn/large/0083vuQJly1ge2rt4chxwj303f0353y9.jpg";//无照片
            DataUserinfo.user_avtarlink = avtarurl;

//            enter next activity --- register detail
            startActivity(new Intent(this, SelectXiaoquActivity.class));

        }
    }
}
