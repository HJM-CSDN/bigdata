package org.koudai.bigdata.mapreduce.multifileoutput.job;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.koudai.bigdata.mapreduce.multifileoutput.mapper.MultipleMapper;
import org.koudai.bigdata.mapreduce.multifileoutput.reducer.MultipleReducer;
import org.koudai.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;

/**
 * @description 多文件输出的job
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/29 07:46
 */
public class MultipleJob {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        System.setProperty("HADOOP_USER_NAME","root");

        //构件job类对象
        Job job = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
        //给当前job类的对象设置job名称
        job.setJobName("multiplePath output app");

        //设置运行主类
        job.setJarByClass(MultipleJob.class);

        //设置job的Mapper及其输出K,V的类型
        job.setMapperClass(MultipleMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        //设置输入输出路径
        FileInputFormat.addInputPath(job,new Path(args[0]));

        //设置多路径输出
        MultipleOutputs.addNamedOutput(job,"az", TextOutputFormat.class,Text.class,Text.class);
        MultipleOutputs.addNamedOutput(job,"AZ",TextOutputFormat.class,Text.class,Text.class);
        MultipleOutputs.addNamedOutput(job,"09",TextOutputFormat.class,Text.class,Text.class);
        MultipleOutputs.addNamedOutput(job,"others",TextOutputFormat.class,Text.class,Text.class);


        //设置job的Reducer及其输出K,V的类型
        job.setReducerClass(MultipleReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

/*        //判断输出目录是否存在,若存在则删除
        FileSystem fs = FileSystem.get(HadoopUtil.getRemoteHadoopConf());
        if (fs.exists(new Path(args[1]))){
            fs.delete(new Path(args[1]),true);
        }
        FileOutputFormat.setOutputPath(job,new Path(args[1]));*/
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //等待程序完成后自动结束程序
        System.exit(job.waitForCompletion(true)?0:1);
    }
}
