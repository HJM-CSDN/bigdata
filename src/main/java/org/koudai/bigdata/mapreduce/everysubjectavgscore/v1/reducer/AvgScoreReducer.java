package org.koudai.bigdata.mapreduce.everysubjectavgscore.v1.reducer;


/**
 * @description 求每个学科的平均成绩
 * @author: Mmy mamingyume@foxmail.com
 * @create: 2018-09-26 11:29:40
 **/
/*
public class AvgScoreReducer extends Reducer<Text,IntWritable,Text,DoubleWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        Iterator<IntWritable> iterator = values.iterator();
        int count = 0;//记录迭代器迭代获取内部元素的次数，即成绩的总个数
        int score = 0;//记录当前科目的总成绩
        while (iterator.hasNext()){
            score += iterator.next().get();
            count++;//在这里前加加后加加均可
        }
        context.write(key,new DoubleWritable(score/count));
    }
}
*/
