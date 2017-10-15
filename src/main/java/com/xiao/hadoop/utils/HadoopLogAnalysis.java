package com.xiao.hadoop.utils;

import java.util.List;

/**
 * Created by xiaomengning on 2017/10/15.
 */
public class HadoopLogAnalysis {

    public static void main(String[] args) {
        //1.在hive建立待装载的表
        String sql1 = "create table loginfo(rdate string,time array<string>,type string,relateclass string,info1 string,info2 string ,info3 string) " +
                "row format delimited fields terminated by ' '" +
                "collection items terminated by ','" +
                "map keys terminated by ':'";
        HiveUtils.createTable(sql1);
//        2.装载数据到hive表

        //注意：装载数据 load字段代表的本地含义是:hive安装所在文件系统的路径
        String sql2 = "load data local inpath '/root/hadoop/hadoop-2.6.5/logs/hadoop-root-namenode-master.log' overwrite into table loginfo";
        HiveUtils.loadData(sql2);

//        3.hive进行统计分析、查询有用的信息(相当于对hadoop日志进行数据清洗后，导入mysql)
        String sql3 = "select rdate,time[0],type,relateclass,info1,info2,info3 from loginfo where type = 'INFO'";
        List res = HiveUtils.queryDataFromHive(sql3);
        HiveUtils.writeToMysql(res);


    }
}
