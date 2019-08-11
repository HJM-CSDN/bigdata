package org.koudai.bigdata.mapreduce.maxtemperature.app;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.koudai.bigdata.mapreduce.maxtemperature.mapper.MTMapper;
import org.koudai.bigdata.mapreduce.maxtemperature.reducer.MTReducer;


import java.io.IOException;

/**
 * @description max temperature 应用程序驱动程序
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/24 00:53
 */
public class MTAPP {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //本程序要处理的数据在HDFS上的目录
        String inputPath = args[0];
        //本程序处理结果存在HDFS上的目录,注意:这个目录不能存在
        String outputPath = args[1];
        if(inputPath == null || inputPath == ""||outputPath == null || outputPath == ""){
            System.out.println("参数不对,请回去重新配置");
            System.exit(1);
        }else {
            //构件Job类对象
            Job mtjob = Job.getInstance();
            //给当前Job类的对象设置job名称
            mtjob.setJobName("hjm's MT Procedure");

            //设置运行主类
            mtjob.setJarByClass(MTAPP.class);

            //设置job的Mapper及其输出K,V的类型
            mtjob.setMapperClass(MTMapper.class);
            mtjob.setOutputKeyClass(Text.class);
            mtjob.setMapOutputValueClass(Text.class);

            //设置job的输出K,V的类型,也可以说是Reducer输出的K,V的类型
            mtjob.setReducerClass(MTReducer.class);
            mtjob.setOutputKeyClass(Text.class);
            mtjob.setOutputValueClass(Text.class);

            //设置要处理的HDFS上的文件路径
            FileInputFormat.addInputPath(mtjob,new Path(inputPath));
            //设置最终输出结果的路径
            FileOutputFormat.setOutputPath(mtjob,new Path(outputPath));

            //等待程序完成后自动结束程序
            System.exit(mtjob.waitForCompletion(true)?0:1);
        }

    }
}
