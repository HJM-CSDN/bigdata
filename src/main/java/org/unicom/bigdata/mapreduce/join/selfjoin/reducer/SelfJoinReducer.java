package org.unicom.bigdata.mapreduce.join.selfjoin.reducer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author mamy
 * @Description
 * @Date 23:34 2018/9/28
 */
public class SelfJoinReducer extends Reducer<Text,Text,Text,Text>{
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        //定义一个集合存放孙子辈
        List<String> grandChild = new ArrayList<String>();
        //定义一个集合存放祖辈
        List<String> grandParent = new ArrayList<String>();

        for (Text s: values) {
            String strs [] = s.toString().split("_");
            //判断r是1还是2 如果是1，则将他的值放孙子辈，如果是2则放到祖辈
            if(strs[0].equals("1")){
                grandChild.add(strs[1]);
            } else if(strs[0].equals("2")){
                grandParent.add(strs[1]);
            }
        }

        //嵌套循环
        for (int i = 0; i < grandChild.size(); i++) {
            for (int j = 0; j < grandParent.size(); j++) {
                context.write(new Text(grandChild.get(i)), new Text(grandParent.get(j)));
            }
        }
    }
}
