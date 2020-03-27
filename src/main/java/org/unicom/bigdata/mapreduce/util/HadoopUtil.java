package org.unicom.bigdata.mapreduce.util;

import org.apache.hadoop.conf.Configuration;

/**
 * @description Hadoop的工具类
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/26 10:13
 */
public class HadoopUtil {
    /**
     * 获取远程Hadoop集群配置对象,默认直接使用bigdata-all.jar
     * @return  远程Hadoop集群配置对象
     */
    public static Configuration getRemoteHadoopConf(){
        Configuration conf = getBaseRemoteHadoopConf();
        conf.set("mapreduce.job.jar", "D:\\IDEAProjects\\bigdata\\target\\bigdata-all.jar");
        return conf;
    }
    /**
     * 获取远程hadoop集群配置对象，使用给定的jar包的绝对路径
     * @param jarAbsPath
     * @return
     */
    public static Configuration getRemoteHadoopConf(String jarAbsPath){
        Configuration conf = getBaseRemoteHadoopConf();
        conf.set("mapreduce.job.jar", jarAbsPath);
        return conf;
    }

    /**
     * 构建一个基础的远程Hadoop配置信息
     * @return conf对象
     */
    public static Configuration getBaseRemoteHadoopConf(){
        Configuration conf = new Configuration();
        //HDFS远程连接信息
        conf.set("fs.defaultFS", "hdfs://mycluster");
        conf.set("dfs.nameservices", "mycluster");
        conf.set("dfs.ha.namenodes.mycluster", "nn1,nn2");
        conf.set("dfs.namenode.rpc-address.mycluster.nn1", "min1:8020");
        conf.set("dfs.namenode.rpc-address.mycluster.nn2", "min2:8020");
        conf.set("dfs.client.failover.proxy.provider.mycluster", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        //MR&YARN相关配置
        conf.set("mapreduce.framework.name", "yarn");
        conf.set("yarn.resourcemanager.address", "http://min3:8032");
        conf.set("mapreduce.app-submission.cross-platform", "true");//允许跨平台提交jar包

        conf.set("yarn.resourcemanager.scheduler.address", "http://min3:8030");
        return  conf;
    }
}
