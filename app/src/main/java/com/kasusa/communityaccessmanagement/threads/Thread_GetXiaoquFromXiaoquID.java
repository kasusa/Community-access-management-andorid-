package com.kasusa.communityaccessmanagement.threads;


import com.kasusa.communityaccessmanagement.MySQLutil;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;

public class Thread_GetXiaoquFromXiaoquID implements Runnable {
    private Thread t;
    private Long xiaoquid ;

    public Thread_GetXiaoquFromXiaoquID(Long xiaoqu_id) {
        xiaoquid = xiaoqu_id;
    }

    public void run() {
        MySQLutil.GetXiaoquFromXiaoquID(xiaoquid);
        Dataclass.threadDone();
    }

    public void start () {
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }
}