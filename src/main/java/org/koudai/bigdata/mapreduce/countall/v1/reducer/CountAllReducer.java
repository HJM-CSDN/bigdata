package org.koudai.bigdata.mapreduce.countall.v1.reducer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @description 计算所有字数的Reducer
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/25 20:39
 */
public class CountAllReducer extends Reducer<Text,LongWritable,Text,LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long count = 0;
        Iterator<LongWritable> iterator = values.iterator();
        while (iterator.hasNext()){
            count += iterator.next().get();
        }
        context.write(new Text("总字数:"),new LongWritable(count));
    }
}
