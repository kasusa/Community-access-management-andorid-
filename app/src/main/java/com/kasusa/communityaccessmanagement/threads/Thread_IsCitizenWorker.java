package com.kasusa.communityaccessmanagement.threads;

import com.kasusa.communityaccessmanagement.MySQLutil;

import com.kasusa.communityaccessmanagement.datacls.Dataclass;


public class Thread_IsCitizenWorker implements Runnable {
    private Thread t;

    public void run() {
        MySQLutil.query_citizen_is_worker_or_not(Dataclass.qurey_citizenID);
        Dataclass.threadDone();

    }

    public void start () {
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }
}