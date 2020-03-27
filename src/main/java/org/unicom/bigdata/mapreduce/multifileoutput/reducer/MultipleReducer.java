package org.unicom.bigdata.mapreduce.multifileoutput.reducer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;

/**
 * @description 多文件输出的Reducer
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/27 19:43
 */
public class MultipleReducer extends Reducer<Text,Text,Text,Text> {
    //在reduce方法执行之前执行一次。(仅一次)
    MultipleOutputs<Text,Text> mos = null;

    @Override
    protected void setup(Context context)
            throws IOException, InterruptedException {
        //获取mos多文件输出对象
        mos = new MultipleOutputs<Text, Text>(context);
    }
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        int counter = 0 ;
        for (Text t : values) {
            counter += Integer.parseInt(t.toString());
        }
        String word  = key.toString();
        //判断单词首字母
        String firstChar = word.substring(0,1);
        if (firstChar.matches("[a-z]")){
            mos.write("az",key,new Text(counter+""));
        }else if (firstChar.matches("[A-Z]")){
            mos.write("AZ",key,new Text(counter+""));
        }else if(firstChar.matches("[0-9]")){
            mos.write("09",key,new Text(counter+""));
        }else {
            mos.write("others",key,new Text(counter+""));
        }

    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        mos.close();
    }
}
