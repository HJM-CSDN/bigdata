package org.koudai.bigdata.mapreduce.subjectavgbyfilename.job;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.koudai.bigdata.mapreduce.subjectavgbyfilename.mapper.MyMapper;
import org.koudai.bigdata.mapreduce.subjectavgbyfilename.reducer.MyReducer;
import org.koudai.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;

/**
 * @description 计算平均分的应用程序驱动程序
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/29 19:38
 */
public class MyJob {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //本程序要处理的数据在HDFS上的目录
        String inputPath = args[0];
        //本程序处理结果存在HDFS上的目录,注意:这个目录不能存在
        String outputPath = args[1];
        if(inputPath == null || inputPath == "" || outputPath == null || outputPath == ""){
            System.out.println("参数不对,请回去重新配置");
            System.exit(1);
        }else {
            System.setProperty("HADOOP_USER_NAME","root");

            //构件Job类的对象
            Job job = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
            //给当前Job类的对象设置job名称
            job.setJobName("subject average score by filename app");

            //设置运行主类
            job.setJarByClass(MyJob.class);

            //设置job的Mapper及其输出K,V的类型
            job.setMapperClass(MyMapper.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);

            //设置job的输出K,V的类型,也可以说是Reducer输出的K,V类型
            job.setReducerClass(MyReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            //设置要处理的HDFS上的文件路径
            FileInputFormat.addInputPath(job,new Path(inputPath));
            //设置最终输出结果的路径
            FileOutputFormat.setOutputPath(job,new Path(outputPath));

            //等待程序完成后自动结束程序
            System.exit(job.waitForCompletion(true)?0:1);
        }
    }
}

