package com.kasusa.communityaccessmanagement.threads;


import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.kasusa.communityaccessmanagement.MySQLutil;


public class Thread_getXiaoquListPutThemInDatacls implements Runnable {
    private Thread t;

    public void run() {
        Dataclass.xiaoqulist = MySQLutil.getXiaoquList();
        Dataclass.threadDone();
    }
    public void start () {
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }
}