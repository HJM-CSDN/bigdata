package org.koudai.bigdata.mapreduce.everyoneavgscore.mapper;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 求每个人的平均成绩的Mapper
 * @author: Mmy mamingyume@foxmail.com
 * @create: 2018-09-26 10:45:32
 **/
public class AvgScoreMapper extends Mapper<LongWritable,Text,Text,DoubleWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //数据文件中的第一行我们并不处理
//        if (key.get()!=0){
//            String[] iterms = value.toString().trim().split(" ");
//            String name = iterms[0];//取出学生姓名
//            double totalScore = 0.0;//定义变量计算总成绩
//            //循环取出成绩并累加，注意循环从i=1即数组的第二个元素开始
//            for (int i = 1; i < iterms.length; i++) {
//                totalScore += Double.parseDouble(iterms[i]);
//            }
//            context.write(new Text(name),new DoubleWritable(totalScore/(iterms.length-1)));
//        }
//        else {
//         //在第一行的时候我们什么也不处理，所以此if的else没有必要写
//        }
    }
}
