package org.unicom.bigdata.mapreduce.wc.reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @description word count 程序的规约器
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/22 19:56
 */
public class WCReducer extends Reducer<Text, IntWritable,Text,IntWritable> {
    /**
     * 自定义规约器中的规约方法
     * @param key 由多个Mapper中映射出的某个key
     * @param values 上述key对应的全部value
     * @param context MR编程框架中的组件,帮助我们将规约结果输送到HDFS上
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        Iterator<IntWritable> iterator = values.iterator();
        while(iterator.hasNext()){
            count = count + iterator.next().get();
        }
        context.write(key,new IntWritable(count));
    }
}
