package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kasusa.communityaccessmanagement.datacls.DataUserinfo;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.kasusa.communityaccessmanagement.threads.Thread_IsCitizenAlreadyExist;
import com.kasusa.communityaccessmanagement.threads.Thread_NewUserinsert1;

public class RegisterOptionalActivity extends AppCompatActivity {

    EditText et_email;
    EditText et_note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_optional);
        et_email = findViewById(R.id.editText4);
        et_note = findViewById(R.id.editText6);

    }

    /**
     * enter home activity
     * add shared perference citizenid
     * finish this activity and sister activity
     */
    private void finishRigister() {
        startActivity(new Intent(this, HomeActivity.class));

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("citizenID", DataUserinfo.user_citizenID);
        editor.commit();

        Intent intent = new Intent("finish");
        sendBroadcast(intent);
        this.finish();

    }

    /**
     *
     * @param view
     */
    public void register_btn(View view) {
        String email = "";
        String note = "";
        String avtarurl = "";
        email = et_email.getText().toString();
        note = et_note.getText().toString();
//        avtarurl = et_pwd1.getText().toString();
        if(email.equals("")) Toast.makeText(this, "请填写email", Toast.LENGTH_SHORT).show();
//        else if(avtarurl.equals("")) Toast.makeText(this, "请上传一张您的照片", Toast.LENGTH_SHORT).show();
        else {
            DataUserinfo.user_email = email;
            DataUserinfo.user_avtarlink = avtarurl;
//            sql update stuff
            // 这里开始处理 如果身份证符合规则,判断是否存在于mysql的citizen表中. (新建线程
            Dataclass.reset();
            Thread_NewUserinsert1 t = new Thread_NewUserinsert1();
            t.start();
            Toast toast = Toast.makeText(this, "注册成功!", Toast.LENGTH_SHORT);
            toast.show();
            // 线程未完成数据拿的时候等待.
            while (!Dataclass.threadDone) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // (线程结束
//            enter next activity --- register detail
            finishRigister();

        }
    }
}
