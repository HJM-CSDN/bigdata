package org.koudai.bigdata.mapreduce.subjectavgbyfilename.reducer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @description 计算多文件输入的平均分的Reducer
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/29 19:37
 */
public class MyReducer extends Reducer<Text,Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        double counter = 0;
        int sum = 0;
        for (Text value : values) {
            counter += Double.parseDouble(value.toString());
            sum ++;
        }
        if (key.toString().equals("chinese.txt")) {
            context.write(new Text("语文平均分"), new Text(counter / sum + ""));
        } else if (key.toString().equals("math.txt")) {
            context.write(new Text("数学平均分"), new Text(counter / sum + ""));
        } else if (key.toString().equals("english.txt")) {
            context.write(new Text("英语平均分"), new Text(counter / sum + ""));
        }
    }
}
