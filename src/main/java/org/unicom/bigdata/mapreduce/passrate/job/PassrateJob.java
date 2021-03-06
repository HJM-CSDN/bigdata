package org.unicom.bigdata.mapreduce.passrate.job;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.unicom.bigdata.mapreduce.passrate.mapper.PassrateMapper;
import org.unicom.bigdata.mapreduce.passrate.reducer.PassrateReducer;
import org.unicom.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;

/**
 * @description 统计成才率的驱动程序应用程序
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/27 13:03
 */
public class PassrateJob {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        String inputPath = args[0];
        String outputPath = args[1];
        if (inputPath == null || inputPath == "" || outputPath == null || outputPath == ""){
            System.out.print("参数不对,请回去重新配置");
            System.exit(1);
        }else {
            //告知hadoop我们当前程序的用户是root,防止权限问题出现
            System.setProperty("HADOOP_USER_NAME", "root");
            //构件Job类对象
            Job job = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
            //给当前的job类的对象设置job名称
            job.setJobName("passrate");

            //设置运行主类
            job.setJarByClass(PassrateJob.class);

            //设置job的Mapper及其输出K,V的类型
            job.setMapperClass(PassrateMapper.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);

            //设置job的输出的K,V的类型,使用默认Reducer
            job.setReducerClass(PassrateReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            //设置要处理的HDFS上的文件的路径
            FileInputFormat.addInputPath(job,new Path(inputPath));
            //设置最终输出结果的路径
            FileOutputFormat.setOutputPath(job,new Path(outputPath));

            //等待程序完成后自动结束程序
            System.exit(job.waitForCompletion(true)?0:1);
        }
    }
}
