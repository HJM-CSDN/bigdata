package org.koudai.bigdata.mapreduce.avg.app;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.koudai.bigdata.mapreduce.avg.mapper.AVGMapper;
import org.koudai.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;
/**
 * @description 求每个人的平均成绩的驱动程序
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/24 14:28
 */


public class AVGAPP {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
            System.setProperty("HADOOP_USER_NAME", "root");
            //构建Job类的对象
            Job job = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
            //给当前job类的对象设置job名称
            job.setJobName("erery body avg score");

            //设置运行主类
            job.setJarByClass(AVGAPP.class);

            //设置job的Mapper及其输出K,V的类型
            job.setMapperClass(AVGMapper.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(DoubleWritable.class);

            //本程序不要自定义实现Reducer
//        //设置job的输出K,V的类型，也可以说是Reducer输出的K,V的类型
//        job.setReducerClass(CheckRandomReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(DoubleWritable.class);

            //设置要处理的HDFS上的文件的路径
            FileInputFormat.addInputPath(job,new Path(args[0]));
            //设置最终输出结果的路径
            FileOutputFormat.setOutputPath(job,new Path(args[1]));

            //等待程序完成后自动结束程序
            System.exit(job.waitForCompletion(true)?0:1);

        }
    }
