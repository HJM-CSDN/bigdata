package org.koudai.java.排序算法大全;

import java.util.Arrays;
import java.util.Random;

import static org.koudai.java.排序算法大全.冒泡排序.bubbleSort;

/**
 * @description 初始化一个一维数组：15个随机整数且在[50-350]之间，求出最大值并输出
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/12/1 16:50
 */
public class test {
    public static void main(String[] args) {
        int[] a = new int[15];
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(301)+50 ;
        }
       // bubbleSort(a);
        int result = a[a.length-1];
        System.out.println(Arrays.toString(a));

        System.out.println("result is:" + result);
    }

}