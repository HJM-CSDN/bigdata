package org.unicom.bigdata.mapreduce.maxtemperature.reducer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @description max temperature 程序中的规约器
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/24 00:51
 */
public class MTReducer extends Reducer<Text,Text,Text,Text> {
    /**
     * 自定义规约器中的规约方法
     * @param key 由多个Mapper映射出来的某个key
     * @param values    上述key对应的的全部value
     * @param context   MR编程框架中的组件,帮助我们将规约结果输送到HDFS上
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        double max = Double.MIN_VALUE;
        //获取最大值
        for (Text t: values) {
            if(max < Double.parseDouble(t.toString())){
                max = Double.parseDouble(t.toString());
            }
        }
        context.write(new Text(max+""),new Text(""));
    }
}
