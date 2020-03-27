package org.unicom.bigdata.mapreduce.avg2.reducer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @description average scores 程序中的规约器
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/24 14:30
 */
public class AVG2Reducer extends Reducer<Text,Text,Text,Text> {
    String subject = "";
    //在reduce方法执行之前执行一次。(仅一次)
    @Override
    protected void setup(Context context)
            throws IOException, InterruptedException {
        subject = context.getConfiguration().get("subject");
    }

    @Override
    protected void reduce(Text key, Iterable<Text> value,Context context) throws IOException, InterruptedException {
        int counter = 0;
        Iterator<Text> iterator = value.iterator();
        while(iterator.hasNext()){

        }

//        counter ++;
//
//        if(subject.equals("chinese")){
//            context.write(new Text("语文:  "+c/counter), new Text(""));
//        } else if (subject.equals("math")){
//            context.write(new Text("数学:  "+m/counter), new Text(""));
//        }else if (subject.equals("english")){
//            context.write(new Text("英语:  "+e/counter), new Text(""));
//        }
    }
    //在reduce方法执行之后执行一次。(仅一次)
    @Override
    protected void cleanup(Context context)
            throws IOException, InterruptedException {
    }
}
