package com.kasusa.communityaccessmanagement.threads;

import com.kasusa.communityaccessmanagement.MySQLutil;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;


public class Thread_getHistoryList implements Runnable {
    private Thread t;

    public void run() {
        Dataclass.historylist =
            MySQLutil.getInoutHistorybyid();
        Dataclass.threadDone();
    }

    public void start () {
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }
}

