package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kasusa.communityaccessmanagement.util.citizenID;

public class LoginActivity extends AppCompatActivity {
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
    public void checkid_redirect(View view) {
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
            Toast toast = Toast.makeText(this, "进入正常流程", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
