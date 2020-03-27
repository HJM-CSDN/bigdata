package org.unicom.bigdata.mapreduce.everyscorepartpercentage.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 求每个分数段的百分比的Mappper
 * @author: Mmy mamingyume@foxmail.com
 * @create: 2018-09-26 10:45:32
 **/
public class PercentageMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
    //多次使用相同对象，可以将其升级为类的成员变量多次使用
    IntWritable one = new IntWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] items = value.toString().trim().split(" ");//.trim() 去掉两端的空格
        //循环下标从1开始，即从第二列开始获取成绩数据，第一列是姓名信息
        for (int i = 1; i < items.length; i++) {
            int score = Integer.parseInt(items[i]);
//            if (score < 60 && score>=0){//所有小于60的成绩
//                context.write(new Text("<60"),one);
//            }else if (score < 70){//大于等于60且小于70的成绩
//                context.write(new Text("60-69"),one);
//            }else if (score < 80){//大于等于70且小于80的成绩
//                context.write(new Text("70-79"),one);
//            }else if (score < 90){//大于等于80且小于90的成绩
//                context.write(new Text("80-89"),one);
//            }else if (score <= 100){
//                context.write(new Text("90-100"),one);
//            }
            if (score>=0&&score<=100){
            String result = score>=60?(score>=70?(score>=80?(score>=90?"90-100":"80-89"):"70-79"):"60-69"):"<60";
             context.write(new Text(result),one);
         }
        }
    }
}
