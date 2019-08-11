package org.koudai.bigdata.mapreduce.topone.reducer;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @description 求TOPOne的Reducer
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/29 21:28
 */
public class TOPOneReducer extends Reducer<Text, DoubleWritable,Text, DoubleWritable> {
    //定义一个全局变量,用于记录最大值
    //double max = Double.MIN_VALUE;//double中的最小正数
    double max = Double.NEGATIVE_INFINITY;//double类型中的最小值

    @Override
    protected void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        Iterator<DoubleWritable> iterator = values.iterator();
        while(iterator.hasNext()){
            double current = iterator.next().get();
            if (current>max){
                max = current;
            }
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        context.write(new Text("最大值: "),new DoubleWritable(max));
    }
}