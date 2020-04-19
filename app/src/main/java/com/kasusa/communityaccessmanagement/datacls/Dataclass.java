package com.kasusa.communityaccessmanagement.datacls;

public class Dataclass {
    public static boolean IsCitizenAlreadyExist =false;
    public static  boolean threadDone = false;

    public static void reset(){
        IsCitizenAlreadyExist = false;
        threadDone = false;

    }
    public static void threadDone(){
        threadDone = true;
    }
}
