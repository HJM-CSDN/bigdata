package org.koudai.bigdata.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

/**
 * @description Hbase工具类
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/10/16 11:00
 */
public class HbaseUtil {
    public static Connection getHbaseConnetion()throws Exception{
        //1、获取conf对象
        Configuration conf = HBaseConfiguration.create();
        //2、指定hbase的zk集群地址
        conf.set("hbase.zookeeper.quorum", "min1,min2,min3");
        //3、获取connection
        try {
            return ConnectionFactory.createConnection(conf);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
