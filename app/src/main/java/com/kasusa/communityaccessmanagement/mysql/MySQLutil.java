package com.kasusa.communityaccessmanagement.mysql;

import android.util.Log;

import com.kasusa.communityaccessmanagement.datacls.DataUserinfo;

import java.sql.*;

public class MySQLutil{

    //    ip :123.56.18.36
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://123.56.18.36:3306/andorid-2020-spring?useSSL=false&serverTimezone=Asia/Shanghai";
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
            System.out.println(" 实例化Statement对象...");

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

}