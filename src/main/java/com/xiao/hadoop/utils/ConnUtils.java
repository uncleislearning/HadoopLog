package com.xiao.hadoop.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by xiaomengning on 2017/10/15.
 */
public class ConnUtils {

    private static Connection mysqlConn = null;
    private static Connection hiveConn = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("org.apache.hive.jdbc.HiveDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getMysqlConn() {

        if (null == mysqlConn) {
            try {
                mysqlConn = DriverManager.getConnection("jdbc:mysql://192.168.199.101:3306/hive", "root", "0528");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mysqlConn;
    }

    public static Connection getHiveConn() {

        if (null == hiveConn) {
            try {
                hiveConn = DriverManager.getConnection("jdbc:hive2://192.168.199.101:10000/default", "root", "");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return hiveConn;
    }


    public static void close(Connection conn) {
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(PreparedStatement pst) {
        if (null != pst) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(getMysqlConn());
        System.out.println(getHiveConn());
    }


    public static void closeAll(PreparedStatement pst, Connection conn) {
        close(pst);
        close(conn);
    }

}
