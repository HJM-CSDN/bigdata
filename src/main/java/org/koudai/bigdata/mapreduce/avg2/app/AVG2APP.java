package org.koudai.bigdata.mapreduce.avg2.app;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.koudai.bigdata.mapreduce.avg2.mapper.AVG2Mapper;
import org.koudai.bigdata.mapreduce.avg2.reducer.AVG2Reducer;
import org.koudai.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;

/**
 * @description average scores2 应用程序驱动程序
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/24 22:24
 */
public class AVG2APP {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //本程序要处理的数据在HDFS上的目录
        String inputPath = args[0];
        //本程序处理结果存在HDFS上的目录,注意:这个目录不能存在
        String outputPath = args[1];
        if(inputPath == null || inputPath == "" || outputPath == null || outputPath == ""){
            System.out.println("参数不对,请回去重新配置");
            System.exit(1);
        }else {
            System.setProperty("HADOOP_USER_NAME", "root");
            Configuration conf = HadoopUtil.getRemoteHadoopConf();
            //构件Job类的对象
            Job avg2job = Job.getInstance();
            //给当前Job类的对象设置job名称
            avg2job.setJobName("hjm's AVG2 Procedure");

            //设置运行主类
            avg2job.setJarByClass(AVG2APP.class);

            //设置job的Mapper及其输出K,V的类型
            avg2job.setMapperClass(AVG2Mapper.class);
            avg2job.setMapOutputKeyClass(Text.class);
            avg2job.setMapOutputValueClass(Text.class);

            //设置job的输出K,V的类型,也可以说是Reducer输出的K,V类型
            avg2job.setReducerClass(AVG2Reducer.class);
            avg2job.setOutputKeyClass(Text.class);
            avg2job.setOutputValueClass(Text.class);

            //设置要处理的HDFS上的文件路径
            FileInputFormat.addInputPath(avg2job,new Path(inputPath));
            //设置最终输出结果的路径
            FileOutputFormat.setOutputPath(avg2job,new Path(outputPath));

            //等待程序完成后自动结束程序
            System.exit(avg2job.waitForCompletion(true)?0:1);
        }
    }
}
