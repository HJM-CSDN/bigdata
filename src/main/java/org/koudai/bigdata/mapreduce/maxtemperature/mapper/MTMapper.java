package org.koudai.bigdata.mapreduce.maxtemperature.mapper;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description max temperature程序中的映射器
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/24 00:48
 */
public class MTMapper extends Mapper<Object, Text,Text,Text> {
    /**
     * 自定义映射器中的映射方法
     * 具体效果:line -->list(k,v)
     *
     * @param key   当前处理行的行号
     * @param value 当前处理行的内容
     * @param context   MR编程框架中的组件,帮助我们将映射出来的多个k,v输送到Reducer那里去
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        //将Text类型的文本转换成String类型
        String line = value.toString();
        //截取温度,从第八位往后截取获得温度值
        String tmp = line.substring(8,line.length());
        context.write(new Text(""),new Text(tmp));
    }
}
