package org.koudai.bigdata.mapreduce.sortstubyscore.job;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.koudai.bigdata.mapreduce.sortstubyscore.bean.StudentWritable;
import org.koudai.bigdata.mapreduce.sortstubyscore.mapper.SortStuByScoreMapper;
import org.koudai.bigdata.mapreduce.sortstubyscore.reducer.SortStuByScoreReducer;
import org.koudai.bigdata.mapreduce.util.HadoopUtil;

/**
 * @description 根据学生成绩进行排序的job
 * @author: Mmy mamingyume@foxmail.com
 * @create: 2018-09-28 16:40:35
 **/
public class SortStuByScoreJob {
    public static void main(String[] args) throws Exception{
        System.setProperty("HADOOP_USER_NAME","root");
        //构建Job类的对象
        Job job = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
        //给当前job类的对象设置job名称
        job.setJobName("sort stu by score app");

        //设置运行主类
        job.setJarByClass(SortStuByScoreJob.class);

        //设置job的Mapper及其输出K,V的类型
        job.setMapperClass(SortStuByScoreMapper.class);
        job.setMapOutputKeyClass(StudentWritable.class);
        job.setMapOutputValueClass(NullWritable.class);

        //设置job的输出K,V的类型，也可以说是Reducer输出的K,V的类型
        job.setReducerClass(SortStuByScoreReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //设置要处理的HDFS上的文件的路径
        FileInputFormat.addInputPath(job,new Path(args[0]));
        //设置最终输出结果的路径
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //等待程序完成后自动结束程序
        System.exit(job.waitForCompletion(true)?0:1);
    }
}
