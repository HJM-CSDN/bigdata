package org.unicom.bigdata.mapreduce.topnmmy.job;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.unicom.bigdata.mapreduce.topnmmy.ben.DoubleArrayWritable;
import org.unicom.bigdata.mapreduce.topnmmy.mapper.TOPNMapper;
import org.unicom.bigdata.mapreduce.topnmmy.reducer.TOPNReducer;
import org.unicom.bigdata.mapreduce.util.HadoopUtil;

/**
 * @description 用来取TOPN的Job main方法的参数顺序：1.前n位中的n2.输入文件路径3.输出文件路径
 * @author: Mmy mamingyume@foxmail.com
 * @create: 2018-09-25 16:21:10
 **/
public class TOPNJob {
    public static void main(String[] args) throws Exception{
        if (args.length!=3){
            System.out.println("你传够/多参数了吗？");
        }else {
            //本程序要处理的数据在HDFS上的目录
            String inputPath = args[1];
            //本程序处理结果存在HDFS上的目录，注意：这个目录不能存在
            String outputPath = args[2];
            if (inputPath == null || inputPath == "" || outputPath == null || outputPath == ""){
                System.out.println("参数不对，滚回去重配");
                System.exit(1);
            }else {

                Configuration conf = HadoopUtil.getRemoteHadoopConf();
                //用来给MR程序内部传递参数，这里的参数的作用是取前n位中的n
                conf.set("n",args[0]);
                System.setProperty("HADOOP_USER_NAME","root");
                //构建Job类的对象
                Job TOPNJob = Job.getInstance(conf);
                //给当前job类的对象设置job名称
                TOPNJob.setJobName("topn app");

                //设置运行主类
                TOPNJob.setJarByClass(org.unicom.bigdata.mapreduce.topn.job.TOPNJob.class);

                //设置job的Mapper及其输出K,V的类型
                TOPNJob.setMapperClass(TOPNMapper.class);
                TOPNJob.setMapOutputKeyClass(Text.class);
                TOPNJob.setMapOutputValueClass(DoubleArrayWritable.class);

                //设置job的输出K,V的类型，也可以说是Reducer输出的K,V的类型
                TOPNJob.setReducerClass(TOPNReducer.class);
                TOPNJob.setOutputKeyClass(Text.class);
                TOPNJob.setOutputValueClass(DoubleWritable.class);

                //设置要处理的HDFS上的文件的路径
                FileInputFormat.addInputPath(TOPNJob,new Path(inputPath));
                //设置最终输出结果的路径
                FileOutputFormat.setOutputPath(TOPNJob,new Path(outputPath));

                //等待程序完成后自动结束程序
                TOPNJob.waitForCompletion(true);
/*                HDFSOperator.readHDFSFile(args[2]+"/part-r-00000");*/
            }
        }
    }
}
