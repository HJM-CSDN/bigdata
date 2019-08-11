package org.koudai.bigdata.mapreduce.passrate.reducer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.koudai.bigdata.util.PercentageUtil;

import java.io.IOException;
import java.util.Iterator;


/**
 * @description 统计成材率的Reducer
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/27 13:02
 */
public class PassrateReducer extends Reducer<Text,Text,Text,Text> {
    @Override
    protected void setup(Context context)
            throws IOException, InterruptedException {
        context.write(new Text("分数段"), new Text("人数"+"\t"+"百分比"));
    }
    int total = 0;//总人数
    int count = 0;//及格人数
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

       Iterator<Text> iterator = values.iterator();
        while (iterator.hasNext()){
            iterator.next();  //此处加上迭代器的.next()方法才能迭代!!
            if (key.toString().equals("up")){
                count++;
            }
            total++;
        }
//此处也可用增强for循环方法
//        for (Text t : values) {
//            if(key.toString().equals("up")){
//                count ++;
//            }
//            total ++;
//        }
   }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        //调用PercentageUtil工具类,计算百分比
        context.write(new Text("成材率"),new Text(PercentageUtil.getPercentage(count+"",total+"",2)));
        context.write(new Text("留级率"),new Text(PercentageUtil.getPercentage((total-count)+"",total+"",2)));
    }
}
