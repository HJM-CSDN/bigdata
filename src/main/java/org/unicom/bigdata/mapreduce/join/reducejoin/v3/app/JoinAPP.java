package org.unicom.bigdata.mapreduce.join.reducejoin.v3.app;

import org.unicom.bigdata.mapreduce.join.reducejoin.v3.mapper.JoinDeptMapper;
import org.unicom.bigdata.mapreduce.join.reducejoin.v3.mapper.JoinEmpMapper;
import org.unicom.bigdata.mapreduce.join.reducejoin.v3.reducer.JoinReducer;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @description MR实现Join程序
 * @author: Mmy 2018-6-8 08:41:57
 * @create: 2018-06-09 12:39:10
 **/
public class JoinAPP {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        String inputPath1 = "/mr/join/input/dept.txt";
        String inputPath2 = "/mr/join/input/emp.txt";
        String outputPath = "/mr/join/v3";

        Job job = Job.getInstance();
        //设置jobName（注意：我们无法设置jobID）
        job.setJobName("mmy_join_v3");

        //运行此jar包的主类
        job.setJarByClass(JoinAPP.class);

        //设置当前job所使用的Mapper，详见下述设置输入路径的代码
//        job.setMapperClass(JoinAtMapSideMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        //设置当前job所使用的Reducer
        job.setReducerClass(JoinReducer.class);

        //设置job的输出的数据的类型
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);

        //设置job的输入路径
        Path path1 = new Path(inputPath1);
        Path path2 = new Path(inputPath2);
        //1.单输入路径，且只能由一个mapper处理
        //FileInputFormat.addInputPath(job,input);
        //2.多输入路径，且针对每一个路径都有一个不同的Mapper处理，这样写，
        // job.setMapper即可省略。且不同的数据文件还可采用不同的分隔符，
        // 但是要注意，多个Mapper的输出类型要保持一致
        MultipleInputs.addInputPath(job,path1,TextInputFormat.class,JoinDeptMapper.class);
        MultipleInputs.addInputPath(job,path2,TextInputFormat.class,JoinEmpMapper.class);

        //设置job的输出路径
        Path output = new Path(outputPath);
        FileOutputFormat.setOutputPath(job,output);
        //当程序结束时，退出
        System.exit(job.waitForCompletion(true)?0:1);
    }
}
