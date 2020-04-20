package com.kasusa.communityaccessmanagement.datacls;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.kasusa.communityaccessmanagement.threads.Thread_IsCitizenAlreadyExist;
import com.kasusa.communityaccessmanagement.threads.Thread_getUserInfo;
import com.kasusa.communityaccessmanagement.util.citizenID;

import java.util.Map;

public class DataUserinfo {
    public static String user_avtarlink;

    public static String citizen_name;
    public static String citizen_gender;
    public static String age;
    public static String user_citizenID ;
    public static String user_xiaoqu;

    public static String user_phone;
    public static String user_email;
    public static String user_note;

    /**
     * using citizenId  to get all info from mysql using  a thread
     * @return true if can get result
     */
    public static void Init(String id) throws InterruptedException {
        boolean ans = false;
        user_citizenID = id;
        Map<String, String> BirAgeSex =
                citizenID.getBirAgeSex(id);
        age = BirAgeSex.get("age");

        // thread mysql get all information
        // 这里开始处理 如果身份证符合规则,判断是否存在于mysql的citizen表中. (新建线程
        Dataclass.reset();
        Thread_getUserInfo t = new Thread_getUserInfo(id);
        t.start();
        // 线程未完成数据拿的时候等待.
        while (!Dataclass.threadDone)
        {
            Thread.sleep(500);

        }
        // (线程结束
    }

    public static void Clean() {
        user_avtarlink = "" ;
        citizen_name = "" ;
        citizen_gender = "" ;
        age = "" ;
        user_citizenID  = "" ;
        user_xiaoqu = "" ;
        user_phone = "" ;
        user_email = "" ;
        user_note = "" ;
        Log.println(Log.INFO,"meow","DataUserInfo 已经清空：");
    }

    public static String to_static_String() {
        return "user_avtarlink:" +user_avtarlink+"\n"+
                "citizen_name:" +citizen_name+"\n"+
                "citizen_gender:" +citizen_gender+"\n"+
                "age:" +age+"\n"+
                "user_citizenID:" +user_citizenID+"\n"+
                "user_xiaoqu:"+user_xiaoqu ;
    }

}
