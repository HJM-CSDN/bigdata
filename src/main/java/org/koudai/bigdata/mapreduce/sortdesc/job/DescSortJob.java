package org.koudai.bigdata.mapreduce.sortdesc.job;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.koudai.bigdata.mapreduce.sortdesc.comparator.DescSortComparator;
import org.koudai.bigdata.mapreduce.sortdesc.mapper.DescSortMapper;
import org.koudai.bigdata.mapreduce.sortdesc.partitioner.DescSortPartitioner;
import org.koudai.bigdata.mapreduce.sortdesc.reducer.DescSortReducer;
import org.koudai.bigdata.mapreduce.util.HadoopUtil;

/**
 * @description 倒序排序的应用程序驱动程序
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/28 19:01
 */
public class DescSortJob {
    public static void main(String[] args) throws Exception{
        System.setProperty("HADOOP_USER_NAME","root");
        //构建Job类的对象
        Job job = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
        //给当前job类的对象设置job名称
        job.setJobName("sortdesc app");

        //设置运行主类
        job.setJarByClass(DescSortJob.class);

        //设置job的Mapper及其输出K,V的类型
        job.setMapperClass(DescSortMapper.class);
        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(Text.class);

        //设置job的输出K,V的类型，也可以说是Reducer输出的K,V的类型
        job.setReducerClass(DescSortReducer.class);
        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(Text.class);

        //设置Partitioner
        job.setPartitionerClass(DescSortPartitioner.class);
        job.setNumReduceTasks(2);

        //设置Comparator
        job.setSortComparatorClass(DescSortComparator.class);

        //设置要处理的HDFS上的文件的路径
        FileInputFormat.addInputPath(job,new Path(args[0]));
        //设置最终输出结果的路径
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //等待程序完成后自动结束程序
        System.exit(job.waitForCompletion(true)?0:1);
    }
}
