package com.kasusa.communityaccessmanagement.threads;

import android.util.Log;

import com.kasusa.communityaccessmanagement.datacls.DataUserinfo;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.kasusa.communityaccessmanagement.MySQLutil;

/**
 * 填写好了全部信息(除了小区) 进行注册
 */
public class Thread_NewUserinsert1 implements Runnable {
    private Thread t;

    public void run() {
        Log.println(Log.INFO,"meow","thread: 开始查找数据库中有没有这个 向数据库 插入这个用户");
        MySQLutil.UserRegisterOperation();
        Dataclass.threadDone();

        Log.println(Log.INFO,"meow","thread: 完成任务,插入这个用户 完成: \n" +
                DataUserinfo.to_static_String());
    }

    public void start () {
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }
}