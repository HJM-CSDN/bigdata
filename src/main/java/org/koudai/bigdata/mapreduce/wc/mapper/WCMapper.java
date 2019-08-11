package org.koudai.bigdata.mapreduce.wc.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description word count程序中的映射器
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2016/9/20
 */
public class WCMapper extends Mapper<LongWritable, Text,Text,IntWritable> {
    /**
     * 自定义映射器中的映射方法
     * 具体效果:line -->list(k,v)
     *
     * @param key   当前处理行的行号
     * @param value  当前处理行的内容
     * @param context   MR编程框架中的组件,帮助我们将映射出来的多个k,v输送到Reducer那里去
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //将Text类型的行内容转成String类型
        String lineContent = value.toString();
        //将给定的一行文本切割成多个字符
        String[] items = lineContent.split(" ");
        for (int i = 0; i < items.length; i++) {
            context.write(new Text(items[i]),new IntWritable(1));
        }

    }
}
