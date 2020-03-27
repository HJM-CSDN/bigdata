package org.unicom.java.排序算法大全;

import java.util.Arrays;

/**
 * @description 选择排序
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2016/10/8
 */

/**
 * 简单选择排序是最简单直观的一种算法，
 * 基本思想为每一趟从待排序的数据元素中选择最小（或最大）的一个元素作为首元素，
 * 直到所有元素排完为止，简单选择排序是不稳定排序。
 * 时间复杂度为O(n2)
 */
public class 选择排序 {
    public static void main(String[] args) {
        int[] array = {1,5,2,3,6,2,34,5,345,543,2,2,1,35,64,2,2,12,223,12312,0,-98};

        selectSort(array);
        String s = Arrays.toString(array);
        System.out.println(s);
    }
    public static void selectSort(int[] array){
        //一共进行数组长度-1次挑选,即可排好序
        for (int i = 0; i < array.length-1; i++) {
            //此处j从i和i+1开始均可.
            for (int j = i+1; j < array.length; j++) {
                if (array[i]>array[j]){
                    array[i]=array[i]^array[j];
                    array[j]=array[i]^array[j];
                    array[i]=array[i]^array[j];
                }
            }
        }
    }
}
