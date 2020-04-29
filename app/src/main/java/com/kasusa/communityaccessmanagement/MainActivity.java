package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.kasusa.communityaccessmanagement.activity_Login.LoginActivity;
import com.kasusa.communityaccessmanagement.activity_xiaoqu.SelectXiaoquActivity;
import com.kasusa.communityaccessmanagement.datacls.DataUserinfo;
import com.kasusa.communityaccessmanagement.util.ping;

public class MainActivity extends AppCompatActivity {
    ImageView imageView_network;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(havePerferenceID())
        {
            Intent s = new Intent(this, HomeActivity.class);
            startActivity(s);
            this.finish();

        }
        else{
            imageView_network  = findViewById(R.id.imageView5);
            CheckNetWorkAndShow();
        }

        startActivity(new Intent(this, PromoteActivity.class));
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
     * 检查 shared perference 里面是否有存储 citizen id
     * @return
     */
    private boolean havePerferenceID() {
        boolean ans = false;

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        String temp =sharedPreferences.getString("citizenID", "");
        if(!temp.equals("")){
            ans = true;
            //检测到登录历史,跳转中
        }
        return ans;
    }

    /**
     * 跳转登录界面
     * @param view
     */
    public void jump_login(View view) {
        Intent s = new Intent(this, LoginActivity.class);
        startActivity(s);
        this.finish();
    }

    /** 网络状态条
     * @param view
     */
    public void network_retest_image_btn_main(View view) {
        CheckNetWorkAndShow();
        Toast.makeText(this,"已刷新网络状态", Toast.LENGTH_SHORT).show();
    }
}
