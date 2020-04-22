package com.kasusa.communityaccessmanagement.datacls;

import java.util.LinkedList;

public class Dataclass {
    public static boolean IsCitizenAlreadyExist =false;
    public static  boolean threadDone = false;
    public static  boolean boolanswer = false;
    public static String id = "";
    public static LinkedList<xiaoqu> xiaoqulist = new LinkedList<>();
    public static xiaoqu thexiaoqu = null;


    public static void reset(){
        IsCitizenAlreadyExist = false;
        threadDone = false;
        boolanswer = false;
        xiaoqulist.clear();
        thexiaoqu = null;
    }
    public static void threadDone(){
        threadDone = true;
    }
}
