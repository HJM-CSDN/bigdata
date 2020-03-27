package org.unicom.bigdata.mapreduce.secondraysort.reducer;

import org.unicom.bigdata.mapreduce.secondraysort.bean.DateTemperaturePair;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @description 二次排序程序的Reducer
 * @author: Mmy 2018-6-8 08:41:57
 * @create: 2018-06-13 17:33:04
 **/
public class SecondraySortReducer extends Reducer<DateTemperaturePair,Text,Text,Text> {
    @Override
    protected void reduce(DateTemperaturePair key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        context.write(key.getYearMonth(),new Text(key.getWendu().toString()));
    }
}
