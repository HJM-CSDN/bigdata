package org.unicom.bigdata.mapreduce.topnmmy.util;

/**
 * @description 初始化数组工具类
 * @author: Mmy mamingyume@foxmail.com
 * @create: 2018-09-29 15:37:56
 **/
public class InitializeArrayUtil {
    /**
     * 将一个给定的double类型的数组初始化，具体操作是将数组的每一个值设置为Double.MIN_VALUE
     * @param oldArr
     * @return
     */
    public static double[] initDoubleArr(double[] oldArr){
        for (int i = 0; i < oldArr.length; i++) {
            oldArr[i] = Double.NEGATIVE_INFINITY;
        }
        return  oldArr;
    }
}
