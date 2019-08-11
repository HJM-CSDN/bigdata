package org.koudai.bigdata.mapreduce.sort.job;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.koudai.bigdata.mapreduce.sort.mapper.SortMapper;
import org.koudai.bigdata.mapreduce.sort.partitioner.SortPartitioner;
import org.koudai.bigdata.mapreduce.sort.reducer.SortReducer;
import org.koudai.bigdata.mapreduce.util.HadoopUtil;

/**
 * @description 排序的应用程序驱动程序
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/28 19:01
 */
public class SortJob {
    public static void main(String[] args) throws Exception{
        System.setProperty("HADOOP_USER_HOME","root");
        //构件Job类的对象
        Job job = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
        //给当前类的Job类的对象设置job名称
        job.setJobName("sort app");

        //设置运行主类
        job.setJarByClass(SortJob.class);

        //设置job的Mapper及其输出K,V的类型
        job.setMapperClass(SortMapper.class);
        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(NullWritable.class);

        //设置job的Reducer及其输出K,V的类型
        job.setReducerClass(SortReducer.class);
        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(NullWritable.class);

        job.setPartitionerClass(SortPartitioner.class);
        //Partitioner里面返回流向了了两个ReduceTask
        job.setNumReduceTasks(2);

        //设置要处理的HDFS上的文件的路径
        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //等待程序完成后自动结束程序
        System.exit(job.waitForCompletion(true)?0:1);
    }
}
