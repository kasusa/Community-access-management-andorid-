package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.kasusa.communityaccessmanagement.datacls.DataScan;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.kasusa.communityaccessmanagement.threads.Thread_GetXiaoquFromXiaoquID;
import com.kasusa.communityaccessmanagement.util.ping;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

public class HomeActivity extends AppCompatActivity {
    ImageView imageView_network;


    // this code is for qr get back info
    private static final int REQUEST_CODE_SCAN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide(); //<< this
        setContentView(R.layout.activity_home);

        imageView_network = findViewById(R.id.imageView4);
        CheckNetWorkAndShow();


        BroadcastReceiver broadcast_reciever = new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("finish")) {
                    finish();
                }
            }
        };
        registerReceiver(broadcast_reciever, new IntentFilter("finish"));
    }

    /**
     * check net work connection my ali server and give a visual feed back
     */
    private void CheckNetWorkAndShow() {
        boolean netok = ping.connectTest();
        Log.println(Log.INFO,"meow","network test ok:"+netok);
        if (netok)
            imageView_network.setImageResource(R.drawable.network_good);
        else imageView_network.setImageResource(R.drawable.network_bad);
    }

    /**
     * top bar showing net work connection retest
     * @param view
     */
    public void network_retest_image_btn(View view) {
        CheckNetWorkAndShow();
        Toast.makeText(this,"已刷新网络状态", Toast.LENGTH_SHORT).show();
    }
    /**
     * the first card view -- scan QRCODE
     * @param view
     */
    public void scanQRcode(View view) {
        //清理扫码信息类,然后开始加信息
        DataScan.clean();
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);

    }

    /**
     * the first card view -- scan QRCODE result
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(Constant.CODED_CONTENT);

                //判断小区码是否符合规则
                try {
                    // 获取小区id(从二维码)
                    DataScan.xiaoqu_id = new Long(content);

                    // 获取小区名称(通过id链接sql查询得到)
                    Dataclass.reset();
                    Thread_GetXiaoquFromXiaoquID t = new Thread_GetXiaoquFromXiaoquID(DataScan.xiaoqu_id);
                    t.start();
                    while (!Dataclass.threadDone)
                        Thread.sleep(500);
                    startActivity(new Intent(this, ScanChooseActivity.class));
                    Log.println(Log.INFO,"meow","二维码获取:"+DataScan.to_Static_String());
                }catch (Exception e){
                    Toast.makeText(this,"您扫的码不是小区码", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    public void history(View view) {

    }

    public void management(View view) {

    }
    public void me(View view) {
        Intent intent = new Intent(this, MeActivity.class);
        startActivity(intent);
    }



}
