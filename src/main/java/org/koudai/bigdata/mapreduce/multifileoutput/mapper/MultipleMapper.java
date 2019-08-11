package org.koudai.bigdata.mapreduce.multifileoutput.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 多文件输出的Mapper
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/27 19:35
 */
public class MultipleMapper extends Mapper<LongWritable, Text,Text,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        //自动帮我们切分单词,以常见的分隔符
//        StringTokenizer st = new StringTokenizer(line);
//        while (st.hasMoreTokens()){
//            context.write(new Text(st.nextToken()),new Text("1"));
//        }
        String[] items = line.split("\t");
        for (int i = 0; i < items.length; i++) {
            context.write(new Text(items[i]),new Text("1"));
        }
    }
}
