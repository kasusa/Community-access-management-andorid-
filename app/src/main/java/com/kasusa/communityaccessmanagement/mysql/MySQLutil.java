package com.kasusa.communityaccessmanagement.mysql;

import java.sql.*;

public class MySQLutil{

    //    ip :123.56.18.36
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://123.56.18.36:3306/andorid-2020-spring?useSSL=false&serverTimezone=Asia/Shanghai";
    static final String USER = "root";
    static final String PASS = "Jinghaoyang1";

    public static void getCitizenInfo() {
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
            sql = " SELECT citizenID, citizen_name, citizen_gender FROM citizen";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                String citizenID = rs.getString("citizenID");
                String citizen_name = rs.getString("citizen_name");
                String citizen_gender = rs.getString("citizen_gender");

                // 输出数据
                System.out.print("citizenID: " + citizenID);
                System.out.print(", citizen_name: " + citizen_name);
                System.out.print(", citizen_gender: " + citizen_gender);
                System.out.print("\n");
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
}