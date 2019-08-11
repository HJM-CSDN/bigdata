package org.koudai.bigdata.mapreduce.avg2.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 求每个学科的平均成绩的Mapper
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/24 22:25
 */
public class AVG2Mapper extends Mapper<LongWritable, Text,Text,IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] items = value.toString().split(" ");
        //取出一行中每个学科的成绩并输出
        context.write(new Text("chinese"),new IntWritable(Integer.parseInt(items[1])));
        context.write(new Text("math"),new IntWritable(Integer.parseInt(items[2])));
        context.write(new Text("english"),new IntWritable(Integer.parseInt(items[3])));
    }
}
