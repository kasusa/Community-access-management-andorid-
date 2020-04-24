package com.kasusa.communityaccessmanagement.threads;

import com.kasusa.communityaccessmanagement.MySQLutil;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;

public class Thread_Insert_in_out implements Runnable {
    private Thread t;

    public void run() {
        MySQLutil.Insert_in_out();
        Dataclass.threadDone();
    }

    public void start () {
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }
}