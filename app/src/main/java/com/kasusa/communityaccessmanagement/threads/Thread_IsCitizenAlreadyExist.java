package com.kasusa.communityaccessmanagement.threads;
import android.util.Log;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.kasusa.communityaccessmanagement.mysql.MySQLutil;

public class Thread_IsCitizenAlreadyExist implements Runnable {
    private Thread t;
    private String id ;
    public boolean ans = false ;

    public Thread_IsCitizenAlreadyExist(String citizenid) {
        id = citizenid;
    }

    public void run() {
        Log.println(Log.INFO,"meow","thread: 开始查找数据库中有没有这个 citizenid");
        ans = MySQLutil.IsCitizenidAlreadyExist(id);
        Dataclass.IsCitizenAlreadyExist = ans;
        Dataclass.threadDone();
        Log.println(Log.INFO,"meow","thread: 完成任务,citizenid 存在 : " + ans);
    }

    public void start () {
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }
}