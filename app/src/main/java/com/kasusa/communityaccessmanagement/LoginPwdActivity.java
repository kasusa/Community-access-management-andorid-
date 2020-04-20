package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.kasusa.communityaccessmanagement.threads.Thread_IspwdRight;

public class LoginPwdActivity extends AppCompatActivity {

    EditText editText_pwd ;
    String id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pwd);

//        从intent中获得上一个界面传过来的身份证id , 然后赋值到textview
        TextView passed_id = (TextView) findViewById(R.id.textView_showcitizenid);//show citizen id passed by loginactivity.java
        Intent intent = getIntent();
         id = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
        passed_id.setText(id);

         editText_pwd = (EditText) findViewById(R.id.editText3);
    }

    public void Login(View view) {

        String pwd = editText_pwd.getText().toString();

        if(pwd.equals("")){
//            pwd is empty logic
            Toast toast = Toast.makeText(this, "密码不可为空", Toast.LENGTH_SHORT);
            toast.show();
        }
        else
        {
            // (新建线程
            Dataclass.reset();
            Thread_IspwdRight t = new Thread_IspwdRight(id,pwd);
            t.start();
            // 等待.
            while (!Dataclass.threadDone) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // (线程结束
            boolean pwdIsRight = Dataclass.boolanswer;
            if(pwdIsRight){
//            pwd is right logic
                //put citizen id in shared perference
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("citizenID", id);
                editor.commit();
                Toast.makeText(this, "登录成功 ！",Toast.LENGTH_LONG).show();


                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                this.finish();

            }else {
//            pwd is wrong  logic
                Toast toast = Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

    }
}
