package org.unicom.bigdata.mapreduce.sortstubyscore.reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.unicom.bigdata.mapreduce.sortstubyscore.bean.StudentWritable;

import java.io.IOException;

/**
 * @description 用于全局排序的Reducer
 * @author: Mmy mamingyume@foxmail.com
 * @create: 2018-09-27 15:16:56
 **/
public class SortStuByScoreReducer extends Reducer<StudentWritable,NullWritable,Text,IntWritable>{
    int i = 0;//学生的名次
    @Override
    protected void reduce(StudentWritable stu, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        context.write(new Text(stu.getName()),new IntWritable(++i));
    }
}
