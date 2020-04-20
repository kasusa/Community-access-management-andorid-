package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kasusa.communityaccessmanagement.datacls.DataUserinfo;

public class MeActivity extends AppCompatActivity {
    TextView tva_name ;
    TextView tva_sex ;
    TextView tva_age ;
    TextView tva_citizenid ;
    TextView tv_xiaoqu ;
    TextView tv_phone ;
    TextView tv_email ;
    TextView tv_note ;
    String avtarurl = "";
    ImageView imageView_userAvtar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        tva_name = (TextView)findViewById(R.id.textView6);
        tva_sex = (TextView)findViewById(R.id.textView7);
        tva_age = (TextView)findViewById(R.id.textView8);
        tva_citizenid = (TextView)findViewById(R.id.textView9);

        tv_xiaoqu = (TextView)findViewById(R.id.Textview10);
        tv_phone = (TextView)findViewById(R.id.textView11);
        tv_email = (TextView)findViewById(R.id.textView20);
        tv_note = (TextView)findViewById(R.id.textView17);
        imageView_userAvtar = (ImageView) findViewById(R.id.imageView);

//get citizen id from shared perference and init for Datauserinfo class
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        String temp =sharedPreferences.getString("citizenID", "");
        try {
            DataUserinfo.Init(temp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//set all text views
        tva_name.setText("姓名: "+DataUserinfo.citizen_name );
        tva_sex.setText("性别: "+DataUserinfo.citizen_gender );
        tva_age.setText("年龄: "+DataUserinfo.age );
        tva_citizenid.setText("身份证号: "+DataUserinfo.user_citizenID );
        tv_xiaoqu.setText(DataUserinfo.user_xiaoqu );
        tv_phone.setText(DataUserinfo.user_phone );
        tv_email.setText(DataUserinfo.user_email );
        tv_note.setText(DataUserinfo.user_note );

        avtarurl = DataUserinfo.user_avtarlink;
        Glide.with(this).load(avtarurl).into(imageView_userAvtar);
    }


    public void LogOut(View view) {
//set SharedPreferences citizen id to ""
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("citizenID", "");
        editor.commit();
        DataUserinfo.Clean(); // remove all Userinfo
        Toast.makeText(this, "登出成功",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        Intent intent2 = new Intent("finish");
        sendBroadcast(intent2);

        this.finish();


    }
}
