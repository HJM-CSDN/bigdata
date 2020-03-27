package org.unicom.bigdata.util;

import java.math.BigDecimal;

/**
 * @description 百分比Util
 * @author: Mmy mamingyume@foxmail.com
 * @create: 2018-09-26 16:14:54
 **/
public class PercentageUtil {
    public static void main(String[] args) {
        String number1 = 31+"";
        String number2 = 60+"";
        System.out.println(getPercentage(number1,number2,2));
    }
    /**
     * 计算两个数相除后得到的百分比,默认使用四舍五入
     * @param number1 第一个数的String形式
     * @param number2 第二个数的String形式
     * @param scale 在百分比中保留几位小数
     * @return 百分比形式的字符串
     */
    public static String getPercentage(String number1,String number2,int scale){
//        String a = "abcdefghi";
//        System.out.println(a.substring(0,9));//前闭后开0-8九位
        BigDecimal bNumber1 = new BigDecimal(number1);
        BigDecimal bNumber2 = new BigDecimal(number2);
        String temp = (bNumber1.divide(bNumber2,scale+2,5)).multiply(new BigDecimal("100")).toString();
        return temp.substring(0,temp.length()-2)+"%";
    }
}
