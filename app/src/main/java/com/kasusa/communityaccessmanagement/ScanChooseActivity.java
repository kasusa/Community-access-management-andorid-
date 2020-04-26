package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kasusa.communityaccessmanagement.datacls.DataScan;
import com.kasusa.communityaccessmanagement.datacls.DataUserinfo;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.kasusa.communityaccessmanagement.threads.Thread_Insert_in_out;

public class ScanChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_choose);
        TextView textView = findViewById(R.id.textView35);
        //如果小区名为空则代表没有从sql中找到小区,小区应该不存在.
        if (!DataScan.xiaoqu_name.equals(""))
            textView.setText(DataScan.xiaoqu_name);
        else {
            Toast.makeText(this, "获取小区错误", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    public void goIn(View view) {
        DataScan.in = true;
        startActivity(new Intent(this, ScanOutputActivity.class));
        dosql();
        finish();
    }


    public void goOut(View view) {
        DataScan.out = true;
        startActivity(new Intent(this, ScanOutputActivity.class));
        dosql();
        finish();

    }

    private void dosql() {

        Dataclass.reset();
        Thread_Insert_in_out t = new Thread_Insert_in_out();
        t.start();
        while (!Dataclass.threadDone) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.println(Log.INFO, "meow", "插入sql完成,请检查:\n" + DataScan.to_Static_String() + "\n" + DataUserinfo.to_static_String());
    }
}
