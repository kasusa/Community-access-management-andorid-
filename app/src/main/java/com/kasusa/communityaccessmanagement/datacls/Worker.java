package com.kasusa.communityaccessmanagement.datacls;

public class Worker {
    public static boolean  isworker = false;
    public static String   worker_citizenid = "";
    public static String   management_name = "";
    public static String   worker_position = "";

    public static String tostatic_String() {
        return "Worker{" +
                "isworker=" + isworker +
                ", worker_citizenid='" + worker_citizenid + '\'' +
                ", worker_management='" + management_name + '\'' +
                ", worker_position='" + worker_position + '\'' +
                '}';
    }
    public static void clear() {
        isworker = false;
        worker_citizenid = "";
        management_name = "";
        worker_position = "";
    }
}
