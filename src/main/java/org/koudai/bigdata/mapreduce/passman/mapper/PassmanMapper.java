package org.koudai.bigdata.mapreduce.passman.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 过滤出不及格学生(即统计及格学生)的Mapper
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/27 11:27
 */
public class PassmanMapper extends Mapper<LongWritable, Text,Text,Text> {
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
        //第一行内容跳过,因为第一行没有学生的成绩数据
        if (key.get() != 0) {
            String[] items = value.toString().trim().split(" ");
            String chinese = items[1];
            String math = items[2];
            String english = items[3];
            //三门成绩均大于60的才算及格,此处比较int型先自动转double型在比较
            if (Double.parseDouble(chinese) >= 60 && Double.parseDouble(math) >= 60 && Double.parseDouble(english) >= 60) {
                context.write(value, new Text("及格"));
            }
            //无else内容,因为if之外的情况不需要处理
        }
    }
}
