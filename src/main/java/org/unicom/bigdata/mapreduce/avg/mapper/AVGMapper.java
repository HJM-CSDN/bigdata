package org.unicom.bigdata.mapreduce.avg.mapper;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 求每个人的平均成绩的Mapper
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/24 14:28
 */
public class AVGMapper extends Mapper<LongWritable, Text,Text,DoubleWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (key.get() != 0) {
            String[] items = value.toString().trim().split(" ");
            String name = items[0];
            double totalScore = 0.0;
            for (int i = 1; i < items.length; i++) {
                totalScore += Double.parseDouble(items[i]);
            }
            context.write(new Text(name), new DoubleWritable(totalScore / (items.length - 1)));
        }
//        else {
//         //在第一行的时候我们什么也不处理，所以此if的else没有必要写
//        }
    }
}