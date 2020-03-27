package org.unicom.bigdata.mapreduce.everyscorepartpercentage.reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.unicom.bigdata.util.PercentageUtil;


import java.io.IOException;
import java.util.*;


/**
 * @description 求分数段百分比
 * @author: Mmy mamingyume@foxmail.com
 * @create: 2018-09-26 11:29:40
 **/
public class PercentageReducer extends Reducer<Text,IntWritable,Text,Text> {
    Map<String,Integer> scorePartPersonNumberMap = new HashMap<>();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int currentScorePartPersonNumber = 0;
        for (IntWritable value : values) {
            currentScorePartPersonNumber += value.get();
        }
        scorePartPersonNumberMap.put(key.toString(),currentScorePartPersonNumber);
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        Collection<Integer> allScorePartPersonNumberCollection = scorePartPersonNumberMap.values();
        int allPeople = 0;
        for (Integer value : allScorePartPersonNumberCollection) {
            allPeople += value.intValue();
        }
        Set<String> allScorePart = scorePartPersonNumberMap.keySet();
        for (String scorePart : allScorePart) {
            context.write(new Text(scorePart),new Text(PercentageUtil.getPercentage(scorePartPersonNumberMap.get(scorePart)+"",allPeople+"",2)));
        }
    }
}
