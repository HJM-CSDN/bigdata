package org.koudai.bigdata.mapreduce.join.reducejoin.v2.app;

import org.koudai.bigdata.mapreduce.join.reducejoin.v2.mapper.JoinMapper;
import org.koudai.bigdata.mapreduce.join.reducejoin.v2.reducer.JoinReducer;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @description Jion程序的主入口
 * @author: Mmy 2018-6-8 08:41:57
 * @create: 2018-06-11 09:06:40
 **/
public class JoinApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        String inputPath = "/mr/join/input";
        String outputPath = "/mr/join/v2";

        Job job = Job.getInstance();
        //设置jobName（注意：我们无法设置jobID）
        job.setJobName("mmy_join_v2");

        //运行此jar包的主类
        job.setJarByClass(JoinApp.class);

        //设置当前job所使用的Mapper，详见下述设置输入路径的代码
        job.setMapperClass(JoinMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

//        //设置当前job所使用的Reducer
        job.setReducerClass(JoinReducer.class);
//
//        //设置job的输出的数据的类型
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);

        //设置job的输入路径
        Path path = new Path(inputPath);
        //1.单输入路径，且只能由一个mapper处理
        FileInputFormat.addInputPath(job,path);
        //设置job的输出路径
        Path output = new Path(outputPath);
        FileOutputFormat.setOutputPath(job,output);
        //当程序结束时，退出
        System.exit(job.waitForCompletion(true)?0:1);
    }
}
