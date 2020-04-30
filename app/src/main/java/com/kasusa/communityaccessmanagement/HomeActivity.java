package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.kasusa.communityaccessmanagement.datacls.DataScan;
import com.kasusa.communityaccessmanagement.datacls.DataUserinfo;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.kasusa.communityaccessmanagement.datacls.Worker;
import com.kasusa.communityaccessmanagement.threads.Thread_GetXiaoquFromXiaoquID;
import com.kasusa.communityaccessmanagement.threads.Thread_getHistoryList;
import com.kasusa.communityaccessmanagement.threads.Thread_getworker;
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
        setContentView(R.layout.activity_home);
        imageView_network = findViewById(R.id.imageView4);
        CheckNetWorkAndShow();
        // perference里有id的时候直接存入Datauserinfo
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String temp = sharedPreferences.getString("citizenID", "");
        DataUserinfo.user_citizenID = temp;
        // 这个BroadcastReceiver 用于退出登录时的接受退出信号
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

//      根据当前用户类型决定是否显示"管理"卡片
        getworkerinfo();
        if(!Worker.isworker)
        {
            CardView worker= findViewById(R.id.cardview_manage);
            ((ViewGroup) worker.getParent()).removeView(worker);
        }
    }

    /**
     * do sql and check if usercitizenid is in worker table
     */
    private void getworkerinfo() {
        Worker.clear();
        Log.println(Log.INFO,"meow","get worker info");
        Dataclass.reset();
        Thread_getworker t = new Thread_getworker();
        t.start();
        while (!Dataclass.threadDone) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.println(Log.INFO,"meow", Worker.tostatic_String());
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


    /**the second card view -- show history
     * @param view
     */
    public void history(View view) {
        //show current user's history on default
        String id = DataUserinfo.user_citizenID;
        dosql_getHistoryList(id);
        Toast.makeText(this,"正在查询,稍等", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, ShowHistoryActivity.class));
    }
    private void dosql_getHistoryList(String id) {
        Dataclass.reset();
        Dataclass.qurey_citizenID = id;
        Thread_getHistoryList t = new Thread_getHistoryList();
        t.start();
        while (!Dataclass.threadDone) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void management(View view) {
        startActivity(new Intent(this, Worker.class));

    }
    public void me(View view) {
        //会使用dataclass的id进行meactivity的初始化
        Dataclass.qurey_citizenID = DataUserinfo.user_citizenID;
        Toast.makeText(this,"正在查询,稍等", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MeActivity.class);
        startActivity(intent);
    }



}
