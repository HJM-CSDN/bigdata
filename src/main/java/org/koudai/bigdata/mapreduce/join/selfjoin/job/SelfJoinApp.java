package org.koudai.bigdata.mapreduce.join.selfjoin.job;


import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.koudai.bigdata.mapreduce.join.selfjoin.mapper.SelfJoinMapper;
import org.koudai.bigdata.mapreduce.join.selfjoin.reducer.SelfJoinReducer;
import org.koudai.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @description Jion程序的主入口
 * @author: Mmy 2018-6-8 08:41:57
 * @create: 2018-06-11 09:06:40
 **/
public class SelfJoinApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        String inputPath = "/mr/selfjoin/input";
        String outputPath = "/mr/selfjoin/out";
        System.setProperty("HADOOP_USER_NAME","root");
        Job job = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
        job.setJobName("mmy_join_mapjoin");

        //运行此jar包的主类
        job.setJarByClass(SelfJoinApp.class);

        //设置当前job所使用的Mapper
        job.setMapperClass(SelfJoinMapper.class);
        //设置Mapper输出的数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        //设置当前job所使用的Reducer
        job.setReducerClass(SelfJoinReducer.class);

        //设置job的输出的数据的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        //设置job的输入路径
        FileInputFormat.addInputPath(job,new Path(inputPath));
        //设置job的输出路径
        FileOutputFormat.setOutputPath(job,new Path(outputPath));
        //当程序结束时，退出
        System.exit(job.waitForCompletion(true)?0:1);
    }
}
