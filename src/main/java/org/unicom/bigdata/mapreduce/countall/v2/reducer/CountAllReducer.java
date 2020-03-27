package org.unicom.bigdata.mapreduce.countall.v2.reducer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @description 计算总字数的Reducer
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/25 20:54
 **/
public class CountAllReducer extends Reducer<Text,LongWritable,Text,LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        Iterator<LongWritable> iterator = values.iterator();
        long countall = 0;
        while (iterator.hasNext()){
            countall += iterator.next().get();
        }
        context.write(new Text("总字数："),new LongWritable(countall));
    }
}
