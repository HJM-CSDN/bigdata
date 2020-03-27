package org.unicom.bigdata.mapreduce.countall.v3.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 计算所有字数的Mapper
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/25 20:58
 **/
public class CountAllMapper extends Mapper<LongWritable,Text,Text,LongWritable> {
    long countall = 0L;//用来记录当前处理块的总字数

    /**
     * map函数，由MR框架调用，每次处理一行
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        countall += value.toString().split(",").length;
    }

    /**
     * 当一个输入分片（默认等同于一个块的大小）处理完毕后，由MR框架调用一次
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        //版本3：使用最新套路，每一个块写出一个k,v
        context.write(new Text("ca"),new LongWritable(countall));
    }
}
