package org.koudai.bigdata.mapreduce.util;

/**
 * @description 数组类型转换工具类
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/30 08:38
 */
public class ArrayTypeTransformUtil {
    /**
     * 将给定的String类型的数组转换成Double类型的数组，如果有转换失败的项直接替换成最小值
     * @param oldArray
     * @return
     */
    public static double[] transStrArr2DoubleArr(String[] oldArray){
        double[] resultArray = new double[oldArray.length];
        for (int i = 0; i < oldArray.length; i++) {
            try {
                resultArray[i] = Double.parseDouble(oldArray[i]);
            }catch (Exception e){
                resultArray[i] = Double.NEGATIVE_INFINITY;
            }
        }
        return resultArray;
    }
}
