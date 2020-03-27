package org.unicom.bigdata.mapreduce.join.selfjoin.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author mamy
 * @Description
 * @Date 23:33 2018/9/28
 */
public class SelfJoinMapper extends Mapper<LongWritable,Text,Text,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String lines [] = line.split(" ");
        //父辈 1_孩子
        context.write(new Text(lines[1]), new Text("1_"+lines[0]));
        // 孩子 2_父母
        context.write(new Text(lines[0]), new Text("2_"+lines[1]));
    }
}
