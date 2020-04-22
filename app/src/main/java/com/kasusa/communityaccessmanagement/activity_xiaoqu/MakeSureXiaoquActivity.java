package com.kasusa.communityaccessmanagement.activity_xiaoqu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kasusa.communityaccessmanagement.HomeActivity;
import com.kasusa.communityaccessmanagement.R;
import com.kasusa.communityaccessmanagement.datacls.DataUserinfo;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.kasusa.communityaccessmanagement.threads.Thread_NewUserinsert1;

public class MakeSureXiaoquActivity extends AppCompatActivity {
    String name = Dataclass.thexiaoqu.name;
    String area = Dataclass.thexiaoqu.area;
    String city = Dataclass.thexiaoqu.city;
    String province = Dataclass.thexiaoqu.province;
    EditText buil ;
    EditText unit ;
    EditText room ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_sure_xiaoqu);
        TextView tvname = findViewById(R.id.textView36);
        TextView tvarea = findViewById(R.id.textView39);
        TextView tvcity = findViewById(R.id.textView38);
        TextView tvprovince = findViewById(R.id.textView37);
        buil = findViewById(R.id.editText9);
        unit = findViewById(R.id.editText10);
        room = findViewById(R.id.editText11);
        tvarea.setText(area);
        tvcity.setText(city);
        tvname.setText(name);
        tvprovince.setText(province);

    }

    public void jump_selectxiaoqu(View view) {
        super.onBackPressed();


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
    public void btn_queding(View view) {

        String  sbuil  = buil.getText().toString();
        String  sunit  = unit.getText().toString();
        String  sroom  = room.getText().toString();

        if(sbuil.equals("")) Toast.makeText(this,"楼号为空", Toast.LENGTH_SHORT).show();
        else if(sunit.equals("")) Toast.makeText(this,"单元号为空", Toast.LENGTH_SHORT).show();
        else if(sroom.equals("")) Toast.makeText(this,"房间为空", Toast.LENGTH_SHORT).show();
        else {
            DataUserinfo.xiaoqu_id = Dataclass.thexiaoqu.id;
            DataUserinfo.user_xiaoqu = name;
            DataUserinfo.building = sbuil;
            DataUserinfo.unit = sunit;
            DataUserinfo.room = sunit;

            Log.println(Log.INFO,"meow",DataUserinfo.to_static_String());
            Toast.makeText(this,"即将插入", Toast.LENGTH_SHORT).show();

            //            sql update stuff
            Dataclass.reset();
            Thread_NewUserinsert1 t = new Thread_NewUserinsert1();
            t.start();
            Toast toast = Toast.makeText(this, "注册成功!", Toast.LENGTH_SHORT);
            toast.show();
            while (!Dataclass.threadDone) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // (线程结束

            finishRigister();
        }
    }
}
