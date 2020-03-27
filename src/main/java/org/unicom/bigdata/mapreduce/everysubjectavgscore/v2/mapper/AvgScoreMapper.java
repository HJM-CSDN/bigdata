package org.unicom.bigdata.mapreduce.everysubjectavgscore.v2.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 求每个学科的平均成绩的Mapper
 * @author: Mmy mamingyume@foxmail.com
 * @create: 2018-09-26 10:45:32
 **/
public class AvgScoreMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
    String subject = "";
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        subject = context.getConfiguration().get("subject");
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] iterms = value.toString().trim().split(" ");
        //取出一行中每个学科的成绩并输出，注意从数组的第二个元素开始，因为第一列是学生姓名
        if (subject.equals("chinese")){
            context.write(new Text("chinese"),new IntWritable(Integer.parseInt(iterms[1])));
        }else if (subject.equals("math")){
            context.write(new Text("math"),new IntWritable(Integer.parseInt(iterms[2])));
        }else if (subject.equals("english")){
            context.write(new Text("english"),new IntWritable(Integer.parseInt(iterms[3])));
        }else {
            context.write(new Text("chinese"),new IntWritable(Integer.parseInt(iterms[1])));
            context.write(new Text("math"),new IntWritable(Integer.parseInt(iterms[2])));
            context.write(new Text("english"),new IntWritable(Integer.parseInt(iterms[3])));
        }
    }
}
