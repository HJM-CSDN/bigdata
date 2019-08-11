package org.koudai.bigdata.mapreduce.passrate.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 统计成材率的Mapper
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/27 13:02
 */
public class PassrateMapper extends Mapper<LongWritable, Text,Text,Text> {
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
        //第一行数据不处理
        if (key.get() != 0){
            String[] items = value.toString().trim().split(" ");
            //三门成绩均大于60分的key设置为"up"输出,否则为"down"
            if (Double.parseDouble(items[1]) >= 60 &&Double.parseDouble(items[2]) >= 60 && Double.parseDouble(items[3])>= 60){
                context.write(new Text("up"), new Text("1"));
            }else {
                context.write(new Text("down"), new Text("1"));
            }

        }
    }
}
