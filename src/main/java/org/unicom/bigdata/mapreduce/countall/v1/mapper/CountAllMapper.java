package org.unicom.bigdata.mapreduce.countall.v1.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 计算所有字数的Mapper
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/25 20:28
 */
public class CountAllMapper extends Mapper<LongWritable, Text,Text,LongWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //版本1:使用WC的套路,每一个单词写出一个k,v
        String[] items = value.toString().split(",");
        for (int i = 0; i < items.length; i++) {
            context.write(new Text("ca"),new LongWritable(1));
        }
    }
}
