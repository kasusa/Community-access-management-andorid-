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
    public static String user_avtarlink = "";
    public static String citizen_name = "";
    public static String citizen_gender = "";
    public static String age = "";
    public static String user_citizenID  = "";
    public static String user_xiaoqu = "";
    public static String user_phone = "";
    public static String user_email = "";
    public static String user_note = "";
    public static String user_pwd = "";

    public static Long   xiaoqu_id = new Long(0);
    public static String building = "";
    public static String unit = "";
    public static String room = "";



    /**
     * using citizenId  to get all info from mysql using  a thread
     *
     */
    public static void Init(String id) throws InterruptedException {
        boolean ans = false;
        user_citizenID = id;
        Map<String, String> BirAgeSex =
                citizenID.getBirAgeSex(id);
        age = BirAgeSex.get("age");

        // thread mysql get all information
        // (新建线程
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

    /**
     * 通过 Datauserinfo class 里面的 citizenid 生成性别和年龄 ,用于插入表
     * @throws InterruptedException wait
     */
    public static void InitZer0 () throws InterruptedException {
        Map<String, String> BirAgeSex =
                citizenID.getBirAgeSex(user_citizenID);
        age = BirAgeSex.get("age");
        citizen_gender =  BirAgeSex.get("sexCode");

        if (citizen_gender.equals("F")){
            citizen_gender = "女";
        }else {
            citizen_gender = "男";

        }
        Log.println(Log.INFO,"meow","通过citizenid生成 Datauserinfo : \n" +
                DataUserinfo.to_static_String());
    }

    /**
     * erase all info in DataUserinfo class
     */
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
        user_pwd = "";
        xiaoqu_id = new Long(0);
        building = "";
        unit = "";
        room = "";
        Log.println(Log.INFO,"meow","DataUserInfo 已经清空：");
    }

    /**
     * use this to see what is in datauserinfo class
     * @return out put str
     */
    public static String to_static_String() {
        return "user_avtarlink:" +user_avtarlink+"\n"+
                "user_citizenID:" +user_citizenID+"\n"+
                "citizen_name:" +citizen_name+"\n"+
                "citizen_gender:" +citizen_gender+"\n"+
                "age:" +age+"\n"+
                "user_pwd:"+user_pwd+"\n"+
                "user_xiaoqu:"+user_xiaoqu +"\n"+
       "xiaqu id:"+ xiaoqu_id +"\n"+
       "buil:"+ building +"\n"+
       "unit:"+ unit +"\n"+
       "user_note:"+ user_note +"\n"+
        "room:"+ room ;
    }

}
