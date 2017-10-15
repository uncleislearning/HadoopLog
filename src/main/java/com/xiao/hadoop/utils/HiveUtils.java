package com.xiao.hadoop.utils;

import com.xiao.hadoop.entity.HadoopLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaomengning on 2017/10/15.
 */
public class HiveUtils {
    private static Connection conn = null;
    private static PreparedStatement pst = null;
    private static ResultSet rs = null;

    //创建表
    public static void createTable(String sql) {
        boolean res = false;
        conn = ConnUtils.getHiveConn();
        try {
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnUtils.closeAll(pst, conn);
        }

    }

    public static void loadData(String sql) {
        conn = ConnUtils.getHiveConn();
        try {
            conn.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnUtils.closeAll(pst, conn);
        }
    }

    public static void writeToMysql(List<HadoopLog> rs) {
        conn = ConnUtils.getMysqlConn();
        try {
            String sql = "INSERT INTO hadooplog(rdate,time,type,relateclass,information) VALUE (?,?,?,?,?)";
            pst = conn.prepareStatement(sql);

            //将hive处理结果 导入mysql
            for (HadoopLog hlog : rs) {
                String rdate = hlog.getRdate();
                String time = hlog.getTime();
                String type = hlog.getType();
                String relateclass = hlog.getRelateclass();
                String information = hlog.getInformation();
                pst.setString(1, rdate);
                pst.setString(2, time);
                pst.setString(3, type);
                pst.setString(4, relateclass);
                pst.setString(5, information);
                pst.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnUtils.closeAll(pst, conn);
        }

    }

    public static List<HadoopLog> queryDataFromHive(String sql) {
        List<HadoopLog> resultList = new ArrayList<HadoopLog>();
        conn = ConnUtils.getHiveConn();
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            //将hive处理结果 导入mysql
            while (rs.next()) {
                String rdate = rs.getString("rdate");
                String time = rs.getString(2);
                String type = rs.getString("type");
                String relateclass = rs.getString("relateclass");
                String information = rs.getString("info1") + rs.getString("info2") + rs.getString("info3");
                resultList.add(new HadoopLog(rdate, time, type, relateclass, information));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnUtils.closeAll(pst, conn);
        }
        return resultList;
    }


}
