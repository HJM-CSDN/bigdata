package org.koudai.bigdata.mapreduce.join.reducejoin.v1.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description Jion程序的Mapper
 * @author: Mmy 2018-6-8 08:41:57
 * @create: 2018-06-11 09:04:40
 **/
public class JoinMapper extends Mapper<LongWritable,Text,Text,Text> {
    /**
     * map方法，每执行一次，处理一行数据
     * @param lineNumber 当前处理的行的行号
     * @param lineContext 当前处理的行的内容
     * @param context mr程序的上下文对象
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable lineNumber, Text lineContext, Context context) throws IOException, InterruptedException {
        String[] lineContextSplit = lineContext.toString().split(",");
        if (lineContextSplit.length==4){//表示当前行是emp.txt文件中的数据，即雇员信息数据
            Text outputKey = new Text(lineContextSplit[2]);
            Text outputValue = new Text("emp,"+lineContextSplit[0]+","+lineContextSplit[1]+","+lineContextSplit[3]);
            context.write(outputKey,outputValue);
        }else {//表示当前行是dept.txt文件中的数据，即部门信息数据
            Text outputKey = new Text(lineContextSplit[0]);
            Text outputValue = new Text("dept,"+lineContextSplit[1]+","+lineContextSplit[2]);
            context.write(outputKey,outputValue);
        }
    }
}
