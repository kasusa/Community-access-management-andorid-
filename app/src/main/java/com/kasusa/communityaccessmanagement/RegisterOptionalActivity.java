package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kasusa.communityaccessmanagement.datacls.DataUserinfo;

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

    public void register_to_mysql(View view) {
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
//            enter next activity --- register detail
            startActivity(new Intent(this, RegisterOptionalActivity.class));
        }
    }
}
