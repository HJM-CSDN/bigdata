package org.unicom.bigdata.mapreduce.join.reducejoin.v3.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description join操作处理雇员的Mapper
 * @author: Mmy 2018-6-8 08:41:57
 * @create: 2018-06-10 22:50:17
 **/
public class JoinEmpMapper extends Mapper<LongWritable,Text,Text,Text> {
    String flag = new String("emp");
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //使用严格的切割逻辑，保证切割出的元素的个数符合要求
        String[] split = value.toString().split(",",-1);

        //1,邱阳本,10,58
        //雇员ID，雇员姓名，所属部门ID，年龄
        //split[0],split[1],split[2],split[3]

        //原始数据错误检测，如果当前行数据的长度不为3则忽略此数据
        if (split.length == 4){
            Text outputKey = new Text(split[2]);
            //outputvalue值组成如下：
            //emp，雇员ID，雇员姓名，雇员年龄
            String outputValue = flag + ","+split[0]+","+split[1]+","+split[3];
            context.write(outputKey,new Text(outputValue));
        }
    }
}
