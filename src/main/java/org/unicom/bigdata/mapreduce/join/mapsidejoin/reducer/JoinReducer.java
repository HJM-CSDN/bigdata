package org.unicom.bigdata.mapreduce.join.mapsidejoin.reducer;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description join程序的Reducer
 * @author: Mmy 2018-6-8 08:41:57
 * @create: 2018-06-11 09:31:43
 **/
public class JoinReducer extends Reducer<Text,Text,NullWritable,Text> {
    String deptStr;
    /**
     * 每执行一次，处理一个业务Key
     *  实际上即每执行一次，处理一个部门
     * @param deptID 部门ID
     * @param values 混合了一个部门信息和部门下的所有雇员信息
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text deptID, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        List<String> valueAll = new ArrayList<>();
        //将Iterator中的多个value的String形式存储到List中
        for (Text value : values) {
            valueAll.add(value.toString());
        }
        //获取部门数据
        for (int i = 0; i < valueAll.size(); i++) {
            if (valueAll.get(i).startsWith("dept")){
                deptStr = valueAll.get(i);

                //因为一次reduce方法调用只处理一个业务key，即只处理一个部门，即只有一行部门数据
                //所以，一旦找到部门信息，就没有再找下去的必要了
                break;
            }
        }
        //将部门数据使用逗号分隔成数组
        String[] deptStrSplit = deptStr.split(",");
        //循环给每一个雇员添加部门信息
        for (int i = 0; i < valueAll.size(); i++) {
            if (valueAll.get(i).startsWith("emp,")){
                String outputValueStr = valueAll.get(i) + "," +  deptStrSplit[1] + "," + deptStrSplit[2];
                Text outputValue = new Text(outputValueStr);
                context.write(NullWritable.get(),outputValue);
            }
        }
    }
}
