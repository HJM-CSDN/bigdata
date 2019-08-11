package org.koudai.bigdata.mapreduce.sortdesc.reducer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @description 数字倒序排序中的规约器
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/27 21:19
 */
public class DescSortReducer extends Reducer<LongWritable, NullWritable,LongWritable,NullWritable> {
    @Override
    protected void reduce(LongWritable key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        context.write(key,NullWritable.get());
    }
}

