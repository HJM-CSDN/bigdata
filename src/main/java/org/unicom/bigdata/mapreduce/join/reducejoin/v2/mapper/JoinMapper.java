package org.unicom.bigdata.mapreduce.join.reducejoin.v2.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

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
        //通过context对象获取输入分片对象
        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        //通过输入分片对象获取 当前行所在的文件的名字
        String currentLineAtThefilesName = inputSplit.getPath().getName();
        //当前行数据切分后的数组
        String[] lineContextSplit = lineContext.toString().split(",");
        //根据数据来源文件的名字判断数据是雇员的还是部门的
        if (currentLineAtThefilesName.equals("emp.txt")){//当前行的数据肯定是雇员数据
            Text outputKey = new Text(lineContextSplit[2]);
            Text outputValue = new Text("emp,"+lineContextSplit[0]+","+lineContextSplit[1]+","+lineContextSplit[3]);
            context.write(outputKey,outputValue);
        }else {
            Text outputKey = new Text(lineContextSplit[0]);
            Text outputValue = new Text("dept,"+lineContextSplit[1]+","+lineContextSplit[2]);
            context.write(outputKey,outputValue);
        }
    }
}
