package org.unicom.bigdata.mapreduce.topnmmy.mapper;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;
import org.unicom.bigdata.mapreduce.topnmmy.ben.DoubleArrayWritable;
import org.unicom.bigdata.mapreduce.topnmmy.util.InitializeArrayUtil;
import org.unicom.bigdata.mapreduce.topnmmy.util.TOPNUtil;

import java.io.IOException;

import static org.unicom.bigdata.mapreduce.topnmmy.util.TOPNUtil.getTOPN;
import static org.unicom.bigdata.mapreduce.util.ArrayTypeTransformUtil.transStrArr2DoubleArr;

/**
 * @description 求TOPN的mapper
 * @author: Mmy mamingyume@foxmail.com
 * @create: 2018-09-25 16:17:33
 **/
public class TOPNMapper extends Mapper<LongWritable,Text,Text,DoubleArrayWritable> {
    int n;//取前n位
    double[] cuttentInputSpiltTOPN;//存放当前输入分片中的TOPN数组
    /**
     * 初始化n
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //前n位
        n = Integer.parseInt(context.getConfiguration().get("n"));
        //根据n，初始化当前输入分片的TOPN数组
        cuttentInputSpiltTOPN = new double[n];
        //将数组中的每个值初始化为Double类型的最小值
        cuttentInputSpiltTOPN = InitializeArrayUtil.initDoubleArr(cuttentInputSpiltTOPN);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] strArr = value.toString().split(",");
        //将切割好的字符串数组，转换成double类型的数组,如果有非数字，则用double类型的最小值替换
        double[] doubleArr = transStrArr2DoubleArr(strArr);
        //如果当前行中的数字的个数比n小，用double类型的最小值将其数量补充至n
        if (doubleArr.length<n){
            double[] temp = new double[n];
            //把当前行中已有的值先添加到temp中
            for (int i = 0; i < doubleArr.length; i++) {
                temp[i] = doubleArr[i];
            }
            for (int i = doubleArr.length; i < n; i++) {
                temp[i] = Double.NEGATIVE_INFINITY;
            }
            //用补充好的数组求当前行的TOPN
            double[] currentLineTOPN = getTOPN(n, temp);
            //求当前输入分片截止到当前行的TOPN
            cuttentInputSpiltTOPN = TOPNUtil.getTOPN(n,currentLineTOPN,cuttentInputSpiltTOPN);
        }else {
            //正常求TOPN
            double[] currentLineTOPN = getTOPN(n, doubleArr);
            cuttentInputSpiltTOPN = TOPNUtil.getTOPN(n,currentLineTOPN,cuttentInputSpiltTOPN);
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        //构造DoubleArrayWritable类型的对象，用于Mapper输出TOPN数组
        DoubleArrayWritable inputSplitTOPNArr = new DoubleArrayWritable();
        //将当前分片的TOPN数组添加到上述创建的对象中
        Writable[] inputSplitTOPNArrTypeWritable = new DoubleWritable[n];
        for (int i = 0; i < cuttentInputSpiltTOPN.length; i++) {
            inputSplitTOPNArrTypeWritable[i] = new DoubleWritable(cuttentInputSpiltTOPN[i]);
        }
        inputSplitTOPNArr.set(inputSplitTOPNArrTypeWritable);

        //将上述填充好的对象输出到Reduce端
        context.write(new Text("topn"),inputSplitTOPNArr);
    }
}
