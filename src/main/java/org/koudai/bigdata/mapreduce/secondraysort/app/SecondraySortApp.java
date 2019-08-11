package org.koudai.bigdata.mapreduce.secondraysort.app;

import org.koudai.bigdata.mapreduce.secondraysort.bean.DateTemperaturePair;
import org.koudai.bigdata.mapreduce.secondraysort.mapper.SecondraySortMapper;
import org.koudai.bigdata.mapreduce.secondraysort.partitioner.DateTemperaturePairPartitioner;
import org.koudai.bigdata.mapreduce.secondraysort.reducer.SecondraySortReducer;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.koudai.bigdata.mapreduce.util.HadoopUtil;

/**
 * @description 二次排序程序主类
 * @author: Mmy 2018-6-8 08:41:57
 * @create: 2018-06-13 17:40:47
 **/
public class SecondraySortApp {
    public static void main(String[] args) throws Exception {
        System.setProperty("HADOOP_USER_NAME","root");
        String inputPath = "/mr/secondraysort/input";
        String outputPath = "/mr/secondraysort/result";
        Job job = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
        //设置jobName（注意：我们无法设置jobID）
        job.setJobName("secondray sort app");

        //运行此jar包的主类
        job.setJarByClass(SecondraySortApp.class);

        //设置当前job所使用的Mapper
        job.setMapperClass(SecondraySortMapper.class);
        job.setMapOutputKeyClass(DateTemperaturePair.class);
        job.setMapOutputValueClass(Text.class);

        //设置当前job所使用的Partitioner
        job.setPartitionerClass(DateTemperaturePairPartitioner.class);


        //设置当前job所使用的Comparator
//        job.setSortComparatorClass(DateTemperaturePairComparator.class);

//        //设置当前job所使用的Reducer
        job.setReducerClass(SecondraySortReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        //严格设定Reduce的数量
//        job.setNumReduceTasks(5);

        //设置job的输入路径
        Path input = new Path(inputPath);
        FileInputFormat.addInputPath(job,input);
        //设置job的输出路径
        Path output = new Path(outputPath);
        FileOutputFormat.setOutputPath(job,output);
        //当程序结束时，退出
        job.waitForCompletion(true);
    }
}
