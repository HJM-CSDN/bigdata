package org.koudai.bigdata.mapreduce.sort.partitioner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @description 用于全局排序的Partitioner
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/27 20:53
 */
public class SortPartitioner extends Partitioner <LongWritable, NullWritable>{
    @Override
    public int getPartition(LongWritable longWritable, NullWritable nullWritable, int i) {
        long item = longWritable.get();
        //大于一百的数字所在的键值对放在reducetask1中
        if (item>100){
            return 1;
        }else {
            return 0;
        }
    }
}
