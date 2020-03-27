package org.unicom.bigdata.mapreduce.join.mapsidejoin.app;


import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.unicom.bigdata.mapreduce.join.mapsidejoin.mapper.JoinAtMapSideMapper;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @description Join程序的主入口
 * @author: Mmy 2018-6-8 08:41:57
 * @create: 2018-06-11 09:06:40
 **/
public class JoinApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        String inputPath = "/mr/join/input/emp.txt";
        String outputPath = "/mr/join/mapsidejoin";

        Job job = Job.getInstance();
        //设置jobName（注意：我们无法设置jobID）
        job.setJobName("mmy_join_mapjoin");

        //运行此jar包的主类
        job.setJarByClass(JoinApp.class);

        //设置当前job所使用的Mapper
        job.setMapperClass(JoinAtMapSideMapper.class);
        //设置Mapper输出的数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        //设置当前job所使用的Reducer
//        job.setReducerClass(JoinReducer.class);
        job.setNumReduceTasks(0);

        //设置job的输出的数据的类型
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);

        //设置分布式缓存
        //0.网上好多写法如下，但是这种方式已经过时了
//        DistributedCache.addCacheFile("xxx");
        //1.要缓存的文件可以在hdfs上
//        job.addCacheFile(new URI("hdfs://tcbdcluster/mr/join/cache/dept.txt"));
        //2.要缓存的文件也可以在本地文件系统上
        //job.addCacheFile(new URI("file:///root/xxxfile"));
        //3.要缓存的文件（可能是项目引用的第三方jar包）也可以添加到ClassPath中
        //job.addArchiveToClassPath(new URI("file:///root/xxxfile"));

        //设置job的输入路径
        Path path = new Path(inputPath);
        //1.单输入路径，且只能由一个mapper处理
        FileInputFormat.addInputPath(job,path);
        //设置job的输出路径
        Path output = new Path(outputPath);
        FileOutputFormat.setOutputPath(job,output);
        //当程序结束时，退出
        System.exit(job.waitForCompletion(true)?0:1);
    }
}
