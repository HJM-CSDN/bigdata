package org.koudai.bigdata.mapreduce.checkrandom.reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @description data maker程序中的规约器
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/25 14:14
 */
public class CheckRandomReducer extends Reducer<Text, IntWritable,Text,IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        Iterator<IntWritable> iterator = values.iterator();
        while(iterator.hasNext()){
            count += iterator.next().get();
        }
        context.write(key,new IntWritable(count));
    }
}
