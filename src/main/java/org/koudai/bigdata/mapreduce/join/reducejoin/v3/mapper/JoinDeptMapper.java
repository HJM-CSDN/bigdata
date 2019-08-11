package org.koudai.bigdata.mapreduce.join.reducejoin.v3.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description join操作中处理部门数据的Mapper
 * @author: Mmy 2018-6-8 08:41:57
 * @create: 2018-06-10 22:58:09
 **/
public class JoinDeptMapper extends Mapper<LongWritable,Text,Text,Text> {
    String flag = new String("dept");
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //使用严格的切割逻辑，保证切割出的元素的个数符合要求
        String[] split = value.toString().split(",",-1);

        //10,开发部,北京
        //部门ID，部门名称，部门所属地区
        //split[0],split[1],split[2]

        //原始数据错误检测，如果当前行数据的长度不为3则忽略此数据
        if (split.length == 3){
            Text outputKey = new Text(split[0]);
            String outputValue = flag + ","+split[1]+","+split[2];
            context.write(outputKey,new Text(outputValue));
        }
    }
}
