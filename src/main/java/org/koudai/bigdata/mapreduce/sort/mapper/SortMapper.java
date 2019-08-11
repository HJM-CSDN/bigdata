package org.koudai.bigdata.mapreduce.sort.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description Sort排序中的映射器
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/27 21:08
 */
public class SortMapper extends Mapper<LongWritable, Text,LongWritable, NullWritable> {
    /**
     * 自定义映射器中的映射方法
     * 具体效果:line-->list(k,v)
     * @param key   当前处理行的行号
     * @param value  当前处理行的内容
     * @param context   MR编程框架中的组件,帮助我们将映射出来的多个k,v输送到Reducer那里去
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //将Text类型的行内容转换成String类型并切割成多个字符
        String[] items = value.toString().split(",");
        for (int i = 0;i < items.length ; i++){
            //输出键值对为<数字,null>
            context.write(new LongWritable(Long.parseLong(items[i])),NullWritable.get());
        }
    }
}
