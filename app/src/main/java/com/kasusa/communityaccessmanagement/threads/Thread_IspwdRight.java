package com.kasusa.communityaccessmanagement.threads;

import android.util.Log;

import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.kasusa.communityaccessmanagement.MySQLutil;

public class Thread_IspwdRight implements Runnable {
    private Thread t;
    private String id ;
    private String pwd;
    public boolean ans = false ;

    public Thread_IspwdRight(String citizenid , String passwd) {
        id = citizenid;
        pwd = passwd;
    }

    public void run() {
        Log.println(Log.INFO,"meow","thread: 判断 citizenid 的 pwd 是否正确");
        ans = MySQLutil.IsPwdRight(id,pwd);
        Dataclass.boolanswer = ans;
        Dataclass.threadDone();
        Log.println(Log.INFO,"meow","thread: 完成任务,pwd正确 : " + ans);
    }

    public void start () {
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }
}