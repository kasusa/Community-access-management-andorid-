package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


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
            Toast.makeText(this, "检测到登录历史,跳转中",
                    Toast.LENGTH_LONG).show();
        }


        return ans;
    }

    public void jump_login(View view) {
        Intent s = new Intent(this, LoginActivity.class);
        startActivity(s);
        this.finish();
    }
}
