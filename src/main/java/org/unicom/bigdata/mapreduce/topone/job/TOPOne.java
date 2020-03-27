package org.unicom.bigdata.mapreduce.topone.job;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.unicom.bigdata.mapreduce.topone.mapper.TOPOneMapper;
import org.unicom.bigdata.mapreduce.topone.reducer.TOPOneReducer;
import org.unicom.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;

/**
 * @description 求TOPOne的Job
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/29 21:39
 */
public class TOPOne {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        System.setProperty("HADOOP_USER_NAME","root");
        //构件job类的对象
        Job job = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
        job.setJobName("top one app");
        job.setJarByClass(TOPOne.class);
        job.setMapperClass(TOPOneMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(DoubleWritable.class);
        job.setReducerClass(TOPOneReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        System.exit(job.waitForCompletion(true)?0:1);
    }
}
