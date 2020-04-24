package com.kasusa.communityaccessmanagement.datacls;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DataScan {
    public static Long      xiaoqu_id = 0L;
    public static boolean   in = false;
    public static boolean   out = false;
    public static boolean   sqlfailed = false;
    public static String    action_time = "";
    public static String    xiaoqu_name = "";

    public  static void clean(){
        Date dNow = new Date( );
        SimpleDateFormat timeYYMMDD = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        action_time = timeYYMMDD.format(dNow);
        xiaoqu_id = 0L;
        in = false;
        out = false;
        sqlfailed = false;
        xiaoqu_name = "";
    }

    public static String to_Static_String() {
        return "DataScan{" +
                "xiaoqu_id=" + xiaoqu_id +
                ", in=" + in +
                ", out=" + out +
                ", action_time='" + action_time + '\'' +
                ", xiaoqu_name='" + xiaoqu_name + '\'' +
                ", sqlfiled='" + sqlfailed + '\'' +
                '}';
    }
}
