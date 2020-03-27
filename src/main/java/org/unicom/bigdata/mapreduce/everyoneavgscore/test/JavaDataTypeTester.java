package org.unicom.bigdata.mapreduce.everyoneavgscore.test;

import java.math.BigDecimal;

/**
 * @description java数据类型测试
 * @author: Mmy mamingyume@foxmail.com
 * @create: 2018-09-26 10:54:14
 **/
public class JavaDataTypeTester {
    public static void main(String[] args) {
        //使用BigDecimal类型时，数值一开始必须是String类型的
        String double1 = "2.34";
        String double2 = "5.67";
        //0.4126984126984127
        BigDecimal bigdou1 = new BigDecimal(double1);
        BigDecimal bigdou2 = new BigDecimal(double2);
        //四舍五入，保留3位有效数字的除法
        BigDecimal divideResult = bigdou1.divide(bigdou2,3,5);
        System.out.println(divideResult);

        //注意：这种形式的代码在此类外见到，直接打死
        double 第一个数 = 2.34;
        double 第2个数 = 5.67;

        double 结果 = 第一个数/第2个数;
        System.out.println(结果);

    }
}
