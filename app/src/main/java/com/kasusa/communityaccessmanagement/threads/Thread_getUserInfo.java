package com.kasusa.communityaccessmanagement.threads;

import android.util.Log;

import com.kasusa.communityaccessmanagement.datacls.DataUserinfo;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.kasusa.communityaccessmanagement.MySQLutil;

public class Thread_getUserInfo implements Runnable {
    private Thread t;
    private String id ;
    public boolean ans = false ;

    /** using MySQLutil.IsCitizenidAlreadyExist(id); to get info from mysql
     * @param citizenid
     */
    public Thread_getUserInfo(String citizenid) {
        id = citizenid;
    }

    public void run() {
        Log.println(Log.INFO,"meow","thread: 开始查找数据库中 citizenid :"+id +"的人的信息");
        MySQLutil.getUserInfo(id);
        Dataclass.threadDone();
        Log.println(Log.INFO,"meow", "thread:" + DataUserinfo.to_static_String());
    }

    public void start () {
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }
}
