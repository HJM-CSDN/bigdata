package org.unicom.bigdata.mapreduce.sortstubyscore.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.unicom.bigdata.mapreduce.sortstubyscore.bean.StudentWritable;

import java.io.IOException;

/**
 * @description Sort程序中的映射器
 * @author: Mmy mamingyume@foxmail.com
 * @create: 2018-09-20 15:38:20
 **/
public class SortStuByScoreMapper extends Mapper<LongWritable,Text,StudentWritable,NullWritable> {

    /**
     * 自定义映射器中的映射方法
     * 具体效果：line --> list(k,v)
     * @param key 当前处理行的行号
     * @param value 当前处理行的内容
     * @param context MR编程框架中的组件，帮助我们将映射出来的多个k,v输送到Reducer那里去
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //将Text类型的行内容转换成String类型
        String lineContent = value.toString();
        //将给定的一行文本切割成多个字符
        String[] items = lineContent.split(" ");
        StudentWritable currentStu = new StudentWritable();
        currentStu.setName(items[0]);
        currentStu.setScore(Integer.parseInt(items[1]) + Integer.parseInt(items[2]) +Integer.parseInt(items[3]));
        context.write(currentStu,NullWritable.get());
    }
}
