package org.unicom.bigdata.mapreduce.checkrandom.app;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.unicom.bigdata.mapreduce.checkrandom.mapper.CheckRandomMapper;
import org.unicom.bigdata.mapreduce.checkrandom.reducer.CheckRandomReducer;

import java.io.IOException;

/**
 * @description data maker 驱动程序应用程序
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/25 14:15
 */
public class CheckRandomAPP{
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length!=2){
            System.out.println("你参数传的个数不对");
        }else {
            String inputPath = args[0];
            String outputPath = args[1];
            if (inputPath == null || inputPath == "" ||outputPath == null || outputPath == ""){
                System.out.println("路径不对,回去重新配置");
                System.exit(1);
            }else {
                Configuration conf = new Configuration();
//                conf.addResource("core-site.xml");
//                conf.addResource("hdfs-site.xml");
//                conf.addResource("mapred-site.xml");
//                conf.addResource("yarn-site.xml");
                //HDFS远程连接信息
/*                conf.set("fs.defaultFS", "hdfs://mycluster");
                conf.set("dfs.nameservices", "mycluster");
                conf.set("dfs.ha.namenodes.mycluster", "nn1,nn2");
                conf.set("dfs.namenode.rpc-address.mycluster.nn1", "min1:8020");
                conf.set("dfs.namenode.rpc-address.mycluster.nn2", "min2:8020");
                conf.set("dfs.client.failover.proxy.provider.mycluster", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
                //MR&YARN相关配置
                conf.set("mapreduce.framework.name", "yarn");
                conf.set("yarn.resourcemanager.address", "http://min2:8032");
                conf.set("mapreduce.app-submission.cross-platform", "true");//允许跨平台提交jar包
                conf.set("mapreduce.job.jar", "C:\\Users\\mmy\\IdeaProjects\\min\\target/min-all.jar");*/

                //构建Job类的对象
                Job checkRandomJob = Job.getInstance(conf);
                //给当前job类的对象设置job名称
                checkRandomJob.setJobName("checkRandom app");

                //设置运行主类
                checkRandomJob.setJarByClass(CheckRandomAPP.class);

                //设置job的Mapper及其输出K,V的类型
                checkRandomJob.setMapperClass(CheckRandomMapper.class);
                checkRandomJob.setMapOutputKeyClass(Text.class);
                checkRandomJob.setMapOutputValueClass(IntWritable.class);

                //设置job的输出K,V的类型，也可以说是Reducer输出的K,V的类型
                checkRandomJob.setReducerClass(CheckRandomReducer.class);
                checkRandomJob.setOutputKeyClass(Text.class);
                checkRandomJob.setOutputValueClass(IntWritable.class);

                //设置要处理的HDFS上的文件的路径
                FileInputFormat.addInputPath(checkRandomJob,new Path(inputPath));
                //设置最终输出结果的路径
                FileOutputFormat.setOutputPath(checkRandomJob,new Path(outputPath));

                //等待程序完成后自动结束程序
                System.exit(checkRandomJob.waitForCompletion(true)?0:1);
            }
        }
    }
}
