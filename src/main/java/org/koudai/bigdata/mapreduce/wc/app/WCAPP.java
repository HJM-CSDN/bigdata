package org.koudai.bigdata.mapreduce.wc.app;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.koudai.bigdata.mapreduce.wc.mapper.WCMapper;
import org.koudai.bigdata.mapreduce.wc.reducer.WCReducer;

import java.io.IOException;

/**
 * @description word count  应用程序驱动程序
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/22 20:13
 */
public class WCAPP {
    /**
     * 程序总入口
     * @param args
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //本程序要处理的数据在HDFS上的目录
        String inputPath = args[0];
        //本程序处理结果存在HDFS上的目录,注意:这个目录不能存在
        String outputPath = args[1];
        if(inputPath == null || inputPath == "" || outputPath == null || outputPath == ""){
            System.out.println("参数不对,请回去重新配置");
            System.exit(1);
        }else {
            //构件Job类的对象
            Job wcjob = Job.getInstance();
            //给当前Job类的对象设置job名称
            wcjob.setJobName("hjm's WC Procedure");

            //设置运行主类
            wcjob.setJarByClass(WCAPP.class);

            //设置job的Mapper及其输出K,V的类型
            wcjob.setMapperClass(WCMapper.class);
            wcjob.setMapOutputKeyClass(Text.class);
            wcjob.setMapOutputValueClass(IntWritable.class);

            //设置job的输出K,V的类型,也可以说是Reducer输出的K,V类型
            wcjob.setReducerClass(WCReducer.class);
            wcjob.setOutputKeyClass(Text.class);
            wcjob.setOutputValueClass(IntWritable.class);

            //设置要处理的HDFS上的文件路径
            FileInputFormat.addInputPath(wcjob,new Path(inputPath));
    //        FileInputFormat.addInputPath(wcjob,new Path("hdfs://min1:8020/mr/wc/input"));
            //设置最终输出结果的路径
            FileOutputFormat.setOutputPath(wcjob,new Path(outputPath));
     //       FileOutputFormat.setOutputPath(wcjob,new Path("hdfs://min1:8020/mr/wc/output54321"));

            //等待程序完成后自动结束程序
            System.exit(wcjob.waitForCompletion(true)?0:1);
        }
    }
}
