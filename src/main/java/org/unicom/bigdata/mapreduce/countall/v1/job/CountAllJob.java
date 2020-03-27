package org.unicom.bigdata.mapreduce.countall.v1.job;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.unicom.bigdata.mapreduce.countall.v1.mapper.CountAllMapper;
import org.unicom.bigdata.mapreduce.countall.v1.reducer.CountAllReducer;

import java.io.IOException;

/**
 * @description 计算所有字数应用程序
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/25 20:50
 */
public class CountAllJob {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length!=2){
            System.out.println("参数个数不对");
        }else {
            String inputpath = args[0];
            String outputpath = args[1];
            if (inputpath==null||inputpath==""||outputpath==null||outputpath==""){
                System.out.println("路径不对,回去重新配置");
                System.exit(1);
            }else {
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
                conf.set("yarn.resourcemanager.address", "http://min2:8032");
                conf.set("mapreduce.app-submission.cross-platform", "true");//允许跨平台提交jar包
                conf.set("mapreduce.job.jar", "C:\\Users\\mmy\\IdeaProjects\\min\\target/min-all.jar");

                Job countAllJob = Job.getInstance();
                countAllJob.setJobName("计总数 v1");

                countAllJob.setJarByClass(CountAllJob.class);
                countAllJob.setMapperClass(CountAllMapper.class);
                countAllJob.setMapOutputKeyClass(Text.class);
                countAllJob.setOutputValueClass(LongWritable.class);

                countAllJob.setReducerClass(CountAllReducer.class);
                countAllJob.setOutputKeyClass(Text.class);
                countAllJob.setOutputKeyClass(LongWritable.class);

                FileInputFormat.addInputPath(countAllJob,new Path(inputpath));
                FileOutputFormat.setOutputPath(countAllJob,new Path(outputpath));

                System.exit(countAllJob.waitForCompletion(true)?0:1);
            }
        }
    }
}
