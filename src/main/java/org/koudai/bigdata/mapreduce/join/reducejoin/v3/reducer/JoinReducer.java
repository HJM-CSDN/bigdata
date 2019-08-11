package org.koudai.bigdata.mapreduce.join.reducejoin.v3.reducer;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description join程序的Reducer
 * @author: Mmy 2018-6-8 08:41:57
 * @create: 2018-06-09 12:03:54
 **/
    public class JoinReducer extends Reducer<Text,Text,NullWritable,Text> {
    /**
     * 用来输出要求中的表头
     *  即先输出一行固定内容的值
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //注意：NullWritable类型的对象的获取方式为：NullWritable.get()，并不是 new NullWritable()
        context.write(NullWritable.get(),new Text("雇员ID\t雇员姓名\t所属部门\t所在地区"));
    }

    //用来保存部门信息
    String departStr;
    /**
     * 每执行一次reduce方法，输出一个部门下的所有雇员的信息
     * @param key  部门ID
     * @param values 部门下的所有员工信息和部门信息
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        //先values中的数据添加到List中，因为两次迭代values过程中数据会丢失
//        Iterator转List
//        https://blog.csdn.net/u010533843/article/details/62085345
//        对java集合进行遍历删除时，一定要记得使用迭代器
//        https://www.cnblogs.com/goody9807/p/6432904.html
        List<String> valueAll = new ArrayList<>();
        for (Text value : values) {
            valueAll.add(value.toString());
        }

//        //先找到表示部门信息的那一行
        for (int i = 0; i < valueAll.size(); i++) {
            if (valueAll.get(i).startsWith("dept,")){
                departStr = valueAll.get(i);
                break;
            }
        }
        //对表示部门信息的那一行切割，切割成如下的形式：
        //部门ID，部门名称，部门所属地区
        String[] departInfoSplit = departStr.split(",");
        //对每一个雇员都添加上所属的部门信息
        for (int i = 0; i < valueAll.size(); i++) {
            String valueStr = valueAll.get(i);
            if (valueStr.startsWith("emp,")){
                String[] empInfoStrSplit = valueStr.split(",");
                String outputValue = empInfoStrSplit[1]+"\t"+empInfoStrSplit[2]+"\t"+empInfoStrSplit[3]
                        +"\t"+departInfoSplit[1]+"\t"+departInfoSplit[2];
                context.write(NullWritable.get(),new Text(outputValue));
            }
        }
    }
}
