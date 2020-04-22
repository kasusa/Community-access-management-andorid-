package com.kasusa.communityaccessmanagement.activity_xiaoqu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kasusa.communityaccessmanagement.R;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;

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

        }
    }
}
