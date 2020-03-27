package org.unicom.bigdata.mapreduce.countall.v3.job;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.unicom.bigdata.mapreduce.countall.v3.mapper.CountAllMapper;
import org.unicom.bigdata.mapreduce.countall.v3.reducer.CountAllReducer;

/**
 * @description 检测Random类随机性应用程序驱动程序
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/25 20:50
 **/
public class CountAllJob {
    /**
     * 程序总入口
     * @param args
     */
    public static void main(String[] args) throws Exception {
        if (args.length!=2){
            System.out.println("你传够/多参数了吗？");
        }else {
            //本程序要处理的数据在HDFS上的目录
            String inputPath = args[0];
            //本程序处理结果存在HDFS上的目录，注意：这个目录不能存在
            String outputPath = args[1];
            if (inputPath == null || inputPath == "" || outputPath == null || outputPath == ""){
                System.out.println("参数不对，滚回去重配");
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
                conf.set("yarn.resourcemanager.address", "http://min3:8032");
                conf.set("mapreduce.app-submission.cross-platform", "true");//允许跨平台提交jar包
                conf.set("mapreduce.job.jar", "D:\\IDEAProjects\\bigdata\\target\\bigdata-all.jar");

                conf.set("yarn.resourcemanager.scheduler.address", "http://min3:8030");

                //构建Job类的对象
                Job countAllJob = Job.getInstance(conf);
                //给当前job类的对象设置job名称
                countAllJob.setJobName("count all v3");

                //设置运行主类
                countAllJob.setJarByClass(CountAllJob.class);

                //设置job的Mapper及其输出K,V的类型
                countAllJob.setMapperClass(CountAllMapper.class);
                countAllJob.setMapOutputKeyClass(Text.class);
                countAllJob.setMapOutputValueClass(LongWritable.class);

                //设置job的输出K,V的类型，也可以说是Reducer输出的K,V的类型
                countAllJob.setReducerClass(CountAllReducer.class);
                countAllJob.setOutputKeyClass(Text.class);
                countAllJob.setOutputValueClass(LongWritable.class);

                //设置要处理的HDFS上的文件的路径
                FileInputFormat.addInputPath(countAllJob,new Path(inputPath));
                //设置最终输出结果的路径
                FileOutputFormat.setOutputPath(countAllJob,new Path(outputPath));

                //等待程序完成后自动结束程序
                System.exit(countAllJob.waitForCompletion(true)?0:1);
            }
        }
    }
}
