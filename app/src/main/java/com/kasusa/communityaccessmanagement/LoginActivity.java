package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.kasusa.communityaccessmanagement.mysql.MySQLutil;
import com.kasusa.communityaccessmanagement.threads.Thread_IsCitizenAlreadyExist;
import com.kasusa.communityaccessmanagement.util.citizenID;

import java.net.IDN;

public class LoginActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "YOUR_PACKAGE_NAME.MESSAGE";
    EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        editText1 =  (EditText) findViewById(R.id.editText_login_citizenid);
    }


    /**
     * 验证身份证是否符合身份证规则要求,然后连接数据库查看是否有数据已经存在
     * 如果存在,直接登录(输入密码)
     * 如果不存在,进入填写详细信息界面
     * @param view
     */
    public void checkid_redirect(View view) throws InterruptedException {
        String id = editText1.getText().toString();
        Log.println(Log.INFO,"meow","login 身份证号码："+ id);
        boolean isID = citizenID.isIDNumber(id);

        if(id.equals("")){
            Toast toast = Toast.makeText(this, "请输入身份证", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if (!isID){
            Toast toast = Toast.makeText(this, "身份证错误,请核实身份证号", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            // 这里开始处理 如果身份证符合规则,判断是否存在于mysql的citizen表中. (新建线程
            Dataclass.reset();
            Thread_IsCitizenAlreadyExist t = new Thread_IsCitizenAlreadyExist(id);
            t.start();
            Toast toast = Toast.makeText(this, "查询远程数据库中", Toast.LENGTH_SHORT);
            toast.show();
            // 线程未完成数据拿的时候等待.
            while (!Dataclass.threadDone)
                Thread.sleep(500);
            // (线程结束


            boolean citizenidExist = Dataclass.IsCitizenAlreadyExist;
            if(citizenidExist){
                Intent s = new Intent(this, LoginPwdActivity.class);
                String message = editText1.getText().toString();
                s.putExtra(EXTRA_MESSAGE, message);
                startActivity(s);
                this.finish();

            }else {
//                register
            }
        }

    }
}
