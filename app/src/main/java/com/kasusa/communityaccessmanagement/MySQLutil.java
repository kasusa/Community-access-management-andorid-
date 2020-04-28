package com.kasusa.communityaccessmanagement;

import android.util.Log;

import com.kasusa.communityaccessmanagement.datacls.DataScan;
import com.kasusa.communityaccessmanagement.datacls.DataUserinfo;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.kasusa.communityaccessmanagement.datacls.Worker;
import com.kasusa.communityaccessmanagement.datacls.history;
import com.kasusa.communityaccessmanagement.datacls.xiaoqu;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class MySQLutil{

    //    ip :123.56.18.36
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://123.56.18.36:3306/andorid-2020-spring?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf-8";
    static final String USER = "root";
    static final String PASS = "Jinghaoyang1";


    /**
     * 判断citizenID是否存在的链接mysql函数
     * @param compare_citizenID 要判断的citizenID
     * @return 如果存在, 返回true
     */
    public static boolean IsCitizenidAlreadyExist(String compare_citizenID) {
        boolean ans = false;

        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = " SELECT citizenID FROM citizen where citizenID = '"+ compare_citizenID +"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next() != false){
                 ans = true;
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

        return ans;
    }

    /**
     * 判断  user citizenID 和 密码 是否正确对应
     * @param citizenid
     * @param pwd
     * @return 如果对, 返回 true
     */
    public static boolean IsPwdRight(String citizenid , String pwd) {
        boolean ans = false;

        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = " SELECT * FROM `user` WHERE user_citizenID = '"+ citizenid +"' and user_pwd = '"+ pwd +"'";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            if(rs.next() != false){
                ans = true;
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return ans;
    }

    /**
     * get all information using citizenID
     * @param id citizen id to get all information
     */
    public static void getUserInfo(String id) {
        Connection conn = null;
        Statement stmt = null;
        try{

            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            Log.println(Log.INFO,"meow","sql 获取user信息测试中 !");


            stmt = conn.createStatement();
            String sql = "SELECT citizen_name,citizen_gender, \n" +
                    "\tuser_phone,user_avtarlink,user_email,user_note,\n" +
                    "\t xiaoqu_name\n" +
                    "FROM `user` , citizen , link  ,xiaoqu\n" +
                    "WHERE link.citizenid = user_citizenID \n" +
                    "AND  user_citizenID = citizen.citizenID AND link.xiaoquid = xiaoqu.xiaoquID AND\n" +
                    "citizen.citizenID = '"+id +"'";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                System.out.println("");
                Log.println(Log.INFO,"meow","sql 获取user信息 有 !");


                // 通过字段检索
                DataUserinfo.citizen_gender = rs.getString("citizen_gender");
//                DataUserinfo.age = rs.getString("citizen_name");
                DataUserinfo.citizen_name = rs.getString("citizen_name");
                DataUserinfo.user_avtarlink = rs.getString("user_avtarlink");
                DataUserinfo.user_email = rs.getString("user_email");
                DataUserinfo.user_note = rs.getString("user_note");
                DataUserinfo.user_phone = rs.getString("user_phone");
                DataUserinfo.user_xiaoqu = rs.getString("xiaoqu_name");
                Log.println(Log.INFO,"meow","sql 获取user信息 书籍写入DATAcls完成 !");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

    /**
     * insert a user using data from DataUserInfo
     * insert in citizen as sql1
     * insert in user as in sql2
     * insert in link as in sql3
     */
    public static void UserRegisterOperation() {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql1 = "INSERT INTO citizen ( citizenID, citizen_name,citizen_gender )\n" +
                    "VALUES( '"+DataUserinfo.user_citizenID+"', '"+DataUserinfo.citizen_name+"','"+DataUserinfo.citizen_gender+"' );";
            int a = stmt.executeUpdate(sql1);
            System.out.println("成功插入citizen表条数: "+ a);
            Log.println(Log.INFO,"meow","成功插入citizen表条数"+a);

            String sql2 = "INSERT INTO `user` ( user_citizenID, user_email,user_phone,user_pwd,user_avtarlink,user_note )\n" +
                    "VALUES( '"+DataUserinfo.user_citizenID+"', '"+DataUserinfo.user_email+"','"+DataUserinfo.user_phone+"'," +
                    "'"+DataUserinfo.user_pwd+"','"+DataUserinfo.user_avtarlink+"','"+DataUserinfo.user_note+"' );";
            a = stmt.executeUpdate(sql2);
            System.out.println("成功插入user表条数: "+ a);
            Log.println(Log.INFO,"meow","成功插入user表条数"+a);


            java.util.Date dNow = new Date( );
            SimpleDateFormat timeYYMMDD = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
            String sql3 = "INSERT into `link` (citizenid,xiaoquid,buliding,unit,room,register_time)\n" +
                    "VALUES\n" +
                    "('"+DataUserinfo.user_citizenID+"',"+DataUserinfo.xiaoqu_id+",'"+
                    DataUserinfo.building+"','"+DataUserinfo.unit+"','"+DataUserinfo.room+"','"+timeYYMMDD.format(dNow)+"')";
            a = stmt.executeUpdate(sql3);
            Log.println(Log.INFO,"meow","成功插入link表条数"+a);

            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }/* 什么都不做*/try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    /** get xiaoqu info and return a linklist have all data
     * @return LinkedList<xiaoqu> , return null if no xiaoqu exist
     */
    public static LinkedList<xiaoqu> getXiaoquList() {
        LinkedList<xiaoqu> xiaoquList = new LinkedList<>();
        Connection conn = null;
        Statement stmt = null;
        boolean noxiaoquExist = true;

        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT xiaoquID, xiaoqu_name , area_name ,area_city ,area_province" +
                    " FROM xiaoqu , area WHERE xiaoqu_area_id = areaID;";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                Long id = rs.getLong("xiaoquID");
                String name = rs.getString("xiaoqu_name");
                String area = rs.getString("area_name");
                String city = rs.getString("area_city");
                String prov = rs.getString("area_province");
                xiaoqu xq = new xiaoqu(id,name,area,city,prov);
                xiaoquList.addLast(xq);
                System.out.println(xq.toString());
                noxiaoquExist = false;
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
        if (noxiaoquExist){
            return null;
        }else
            return xiaoquList;
    }

    /** Get Xiaoqu From Xiaoqu ID - store in DataScan.xiaoquname
     * @param xiaoquID use this xiaoqu id to search for name
     */
    public static void GetXiaoquFromXiaoquID(Long xiaoquID){
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM xiaoqu WHERE xiaoquID = '"+xiaoquID+"'";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                DataScan.xiaoqu_name = rs.getString("xiaoqu_name");

            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

    /**
     * insert DataScan data to in_and_out table
     */
    public static void Insert_in_out() {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = " INSERT into in_and_out  (citizen_id,xiaoqu_id,`in`,`out`,action_time)\n" +
                    "VALUES(\n" +
                    "'"+ DataUserinfo.user_citizenID +"',\n" +
                    "'"+DataScan.xiaoqu_id+"',\n" +
                    ""+DataScan.in+",\n" +
                    ""+DataScan.out+",\n" +
                    "'"+DataScan.action_time+"'\n" +
                    ")\n";
            stmt.execute(sql);
            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
            DataScan.sqlfailed = true;
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

    /** get history in and out info and return a linklist have all data
     * @return LinkedList<history> , return null if no history exist
     */
    public static LinkedList<history> getInoutHistorybyid() {
        LinkedList<history> historylist = new LinkedList<>();
        Connection conn = null;
        Statement stmt = null;
        boolean noxiaoquExist = true;

        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT\n" +
                    "\t* \n" +
                    "FROM\n" +
                    "\tin_and_out , xiaoqu\n" +
                    "WHERE\n" +
                    "\tcitizen_id = '"+ Dataclass.qurey_citizenID+"' and\n" +
                    "\tin_and_out.xiaoqu_id = xiaoqu.xiaoquID\n" +"ORDER BY"+
                    "\taction_time DESC";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                System.out.println("you data");
                // 通过字段检索
                Long xiaoqu_id = rs.getLong("xiaoqu_id");
                String citizen_id = rs.getString("citizen_id");
                String xiaoqu_name = rs.getString("xiaoqu_name");
                String action_time = rs.getString("action_time");
                boolean in = rs.getBoolean("in");
                boolean out = rs.getBoolean("out");
                history hs = new history(citizen_id,xiaoqu_name,action_time,xiaoqu_id,in,out);
                historylist.addLast(hs);
                noxiaoquExist = false;
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
        if (noxiaoquExist){
            System.out.println("no history data");
            return null;
        }else
            return historylist;
    }

    /**
     * get if the user is an worker(which can use management feature)
     * if the user is a worker , then put info into Worker class */
    public static void getWorkerInfo() {
        Connection conn = null;
        Statement stmt = null;
        boolean isworker = false;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM worker,mangement WHERE worker_citizenid = '"+DataUserinfo.user_citizenID+"' and worker_management = managementID";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                Worker.worker_citizenid = rs.getString("worker_citizenid");
                Worker.management_name = rs.getString("management_name");
                Worker.worker_position = rs.getString("worker_position");

                Worker.isworker = true;
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}