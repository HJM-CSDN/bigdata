package org.koudai.bigdata.mapreduce.sortdesc.comparator;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @description 倒序排序的比较器
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/28 20:15
 */
public class DescSortComparator extends WritableComparator {
    /**
     * 定义一个空参构造方法
     *  用来生成我们自定义的比较器对象
     *  实际上是调用WritableComparator的带参构造方法，调用
     *  WritableComparator的带参构造方法，实际上是告知比较器，
     *  我们需要比较的对象的类型
     */
    public DescSortComparator() {
        super(LongWritable.class,true);
    }
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        return -super.compare(a, b);
    }
}
