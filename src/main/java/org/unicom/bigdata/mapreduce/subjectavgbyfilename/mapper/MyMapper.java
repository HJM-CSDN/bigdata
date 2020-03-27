package org.unicom.bigdata.mapreduce.subjectavgbyfilename.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @description 计算多文件输入的平均分的Mapper 根据文件名计算平均分
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/29 19:36
 */
public class MyMapper extends Mapper<LongWritable, Text,Text,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] items = value.toString().trim().split(" ");
        //得到分片信息
        InputSplit is = context.getInputSplit();
        //得到分片的文件名
        String filename = ((FileSplit)is).getPath().getName();
        context.write(new Text(filename),new Text(items[1]));
    }
}
