package org.koudai.bigdata.mapreduce.sortdesc.partitioner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @description 用于全局倒序排序的Partitioner
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/27 20:53
 */
public class DescSortPartitioner extends Partitioner<LongWritable,Text> {
    @Override
    public int getPartition(LongWritable longWritable, Text text, int numPartitions) {
        long item = longWritable.get();
        if (item>100){
            return 0;
        }else {
            return 1;
        }
    }
}
