package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kasusa.communityaccessmanagement.datacls.DataUserinfo;
import com.kasusa.communityaccessmanagement.util.CheckStrength;
import com.kasusa.communityaccessmanagement.util.mobileTest;

public class RegisterMustHaveActivity extends AppCompatActivity {
    TextView tv_age;
    TextView tv_sex;
    EditText et_name;
    EditText et_phone;
    EditText et_pwd1 ;
    EditText et_pwd2 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_must_have);
        tv_age = findViewById(R.id.textView19);
        tv_sex = findViewById(R.id.textView18);
        et_name = findViewById(R.id.editText);
        et_phone = findViewById(R.id.editText2);
        et_pwd1 = findViewById(R.id.editText5);
        et_pwd2 = findViewById(R.id.editText7);

        try {
            DataUserinfo.InitZer0();
        } catch (InterruptedException e) { }
        tv_age.setText(DataUserinfo.age);
        tv_sex.setText(DataUserinfo.citizen_gender);

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

    public void register_next_btn(View view) {
        String name = "";
        String phone = "";
        String Pwd1 = "";
        String Pwd2 = "";
        name = et_name.getText().toString();
        phone = et_phone.getText().toString();
        Pwd1 = et_pwd1.getText().toString();
        Pwd2 = et_pwd2.getText().toString();
        if(name.equals("")) Toast.makeText(this, "请填写姓名", Toast.LENGTH_SHORT).show();
        else if(phone.equals("")) Toast.makeText(this, "请填写手机号", Toast.LENGTH_SHORT).show();
        else if(!mobileTest.isMobile(phone)) Toast.makeText(this, "手机号不正确", Toast.LENGTH_SHORT).show();
        else if(Pwd1.equals("")) Toast.makeText(this, "请填写第一个密码框", Toast.LENGTH_SHORT).show();
        else if(Pwd2.equals("")) Toast.makeText(this, "请填写第二个密码框", Toast.LENGTH_SHORT).show();
        else if(Pwd1.length()<6) Toast.makeText(this, "密码不可以少于6位", Toast.LENGTH_SHORT).show();
        else if(! Pwd2.equals(Pwd2)) Toast.makeText(this, "您两次填写的密码不同!", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(this,"密码强度等级:"+CheckStrength.checkPasswordStrength(Pwd1), Toast.LENGTH_SHORT).show();
            DataUserinfo.user_phone = phone;
            DataUserinfo.citizen_name = name;
            DataUserinfo.user_pwd = Pwd1;
//            enter next activity --- register detail
            startActivity(new Intent(this, RegisterOptionalActivity.class));
        }


    }
}
