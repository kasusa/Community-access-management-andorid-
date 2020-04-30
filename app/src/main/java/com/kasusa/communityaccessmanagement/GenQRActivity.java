package com.kasusa.communityaccessmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.yzq.zxinglibrary.encode.CodeCreator;

public class GenQRActivity extends AppCompatActivity {

    ImageView imvQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_q_r);
        imvQR= findViewById(R.id.imageView6);
        TextView tv_xiqoquname = findViewById(R.id.textView51);
        tv_xiqoquname.setText(Dataclass.thexiaoqu.name);
        
        Toast.makeText(this,"生成小区二维码id: "+Dataclass.thexiaoqu.id, Toast.LENGTH_SHORT).show();
        /*
         * contentEtString：字符串内容
         * w：图片的宽
         * h：图片的高
         * logo：不需要logo的话直接传null
         * */

        Bitmap bitmap = CodeCreator.createQRCode(Dataclass.thexiaoqu.id.toString(), 500, 500,null);
        imvQR.setImageBitmap(bitmap);
        
    }
}
