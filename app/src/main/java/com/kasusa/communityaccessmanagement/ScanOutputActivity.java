package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.kasusa.communityaccessmanagement.datacls.DataScan;

public class ScanOutputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_output);
        // tv show what insterted
        String out = getDataScanStr();
        TextView textView = findViewById(R.id.textView42);
        textView.setText(out);
        ImageView imv = findViewById(R.id.imageView2);
        if(DataScan.sqlfailed)
            imv.setImageResource(R.drawable.notpass_sign);
    }

    private String getDataScanStr() {
        String action = "";
        if(DataScan.in == true)
            action = "进入区域";
        else
            action = "离开区域";
        return
                "地点:" + DataScan.xiaoqu_name + "\n" + "\n" +
                "动作:" + action + "\n" + "\n" +
                "时间:" + DataScan.action_time + "\n" + "\n";

    }


}
