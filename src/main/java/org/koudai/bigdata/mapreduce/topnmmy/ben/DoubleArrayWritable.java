package org.koudai.bigdata.mapreduce.topnmmy.ben;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.DoubleWritable;

/**
 * @description 指定Array中放置的数据类型
 * @author: Mmy mamingyume@foxmail.com
 * @create: 2018-09-25 16:21:10
 **/
public class DoubleArrayWritable extends ArrayWritable {
    /**
     * 当前类的构造器，指定当前Array中放置的数据类型
     */
    public DoubleArrayWritable() {
        super(DoubleWritable.class);
    }
}
