package com.kasusa.communityaccessmanagement.threads;

import com.kasusa.communityaccessmanagement.MySQLutil;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;

public class Thread_Promote implements Runnable {
    private Thread t;

    public void run() {
        MySQLutil.Promote(Dataclass.qurey_citizenID,Dataclass.qurey_Promote);
        Dataclass.threadDone();

    }

    public void start () {
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }
}