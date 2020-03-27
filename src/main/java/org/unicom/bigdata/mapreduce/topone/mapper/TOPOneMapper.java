package org.unicom.bigdata.mapreduce.topone.mapper;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 求TOPOne的Mapper
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/29 21:18
 */
public class TOPOneMapper extends Mapper<LongWritable, Text,Text, DoubleWritable> {
    //定义一个全局变量,用于记录当前分片的最大值
    //double max = Double.MIN_VALUE;//double中的最小正数
    double max = Double.NEGATIVE_INFINITY;//double类型中的最小值


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] items = value.toString().trim().split(",");
        for (int i = 0; i < items.length; i++) {
            //先将文本类型的单个内容转成Double类型便于比较
            double current = Double.parseDouble(items[i]);
            if (current>max){
                max = current;//逐个与最大值比较,若大于最大值就将最大值替换
            }
        }

    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        context.write(new Text("最大值"),new DoubleWritable(max));
    }
}
