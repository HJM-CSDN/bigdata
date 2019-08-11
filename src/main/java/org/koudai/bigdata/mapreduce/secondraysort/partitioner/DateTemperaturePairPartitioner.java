package org.koudai.bigdata.mapreduce.secondraysort.partitioner;

import org.koudai.bigdata.mapreduce.secondraysort.bean.DateTemperaturePair;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


/**
 * @description 针对我们自定义的类型的对象的分区器
 * @author: Mmy 2018-6-8 08:41:57
 * @create: 2018-06-13 16:54:50
 **/
public class DateTemperaturePairPartitioner extends Partitioner<DateTemperaturePair,Text> {
    /**
     * 根据mapper映射出的key（DateTemperaturePair），计算出此key所要去的分区
     *  在这里，key的类型是我们自定义的DateTemperaturePair，此类型的对象封装了两个有用的属性
     *  1.yearMonth 2.Wendu
     *  我们这里计算分区ID的算法是：只要属性1即yearMonth相同，就分配一个相同的分区ID
     * @param dateTemperaturePair
     * @param text
     * @param numPartitions
     * @return
     */
    @Override
    public int getPartition(DateTemperaturePair dateTemperaturePair, Text text, int numPartitions) {
        return Math.abs(dateTemperaturePair.getYearMonth().hashCode()%numPartitions);
    }
}
