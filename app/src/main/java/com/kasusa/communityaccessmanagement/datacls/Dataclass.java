package com.kasusa.communityaccessmanagement.datacls;

import java.util.LinkedList;

public class Dataclass {
    public static boolean IsCitizenAlreadyExist =false;
    public static  boolean threadDone = false;
    public static  boolean boolanswer = false;
    public static String id = "";

    public static LinkedList<xiaoqu> xiaoqulist = new LinkedList<>();
    public static LinkedList<history> historylist = new LinkedList<>();
    public static xiaoqu thexiaoqu = null;

    public static String qurey_citizenID = "";
    public static String qurey_name = "";
    public static String qurey_isworker = "";
    public static boolean qurey_Promote = false;


    public static void reset(){
        IsCitizenAlreadyExist = false;
        threadDone = false;
        boolanswer = false;
        if (xiaoqulist!= null)
            xiaoqulist.clear();
        if (historylist!= null)
            historylist.clear();
        thexiaoqu = null;
        qurey_citizenID = "";

        qurey_name = "";
        qurey_isworker = "";
        qurey_Promote = false;
    }
    public static void threadDone(){
        threadDone = true;
    }
}
