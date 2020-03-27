package org.unicom.bigdata.mapreduce.secondraysort.mapper;

import org.unicom.bigdata.mapreduce.secondraysort.bean.DateTemperaturePair;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 二次排序程序的mapper
 * @author: Mmy 2018-6-8 08:41:57
 * @create: 2018-06-13 17:23:07
 **/
public class SecondraySortMapper extends Mapper<LongWritable,Text,DateTemperaturePair,Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //一个line数据长相
        //2012,01,01,5
        String[] lineSplit = value.toString().split(",",-1);
        //取出输出key所需要的属性
        String yearMonth = lineSplit[0] + lineSplit[1];
        String day = lineSplit[2];
        int wenDu = Integer.parseInt(lineSplit[3]);

        //将上述取出的值封装到outputKey对象上
        DateTemperaturePair outputKey = new DateTemperaturePair();
        outputKey.setYearMonth(new Text(yearMonth));
        outputKey.setDay(new Text(day));
        outputKey.setWendu(new IntWritable(wenDu));

        //将自定义类型的对象作为outputKey，将温度作为value进行写出
        context.write(outputKey,new Text(wenDu+""));
    }
}
