package org.koudai.java.排序算法大全;

/**
 * @description 直接插入排序
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2016/10/9
 */

import java.util.Arrays;

/**
 * 直接插入排序基本思想是每一步将一个待排序的记录，
 * 插入到前面已经排好序的有序序列中去，直到插完所有元素为止。
 * 简单插入排序在最好情况下，需要比较n-1次，无需交换元素，时间复杂度为O(n);
 * 在最坏情况下，时间复杂度依然为O(n2)。
 * 但是在数组元素随机排列的情况下，插入排序还是要优于选择和冒泡排序的。
 */
public class 插入排序 {
    public static void main(String[] args) {
        int[] array = {1,5,2,3,6,2,34,5,345,543,2,2,1,35,64,2,2,0,-98};
        insertionSort(array);
        String s = Arrays.toString(array);
        System.out.println(s);
    }
    public static void insertionSort(int[] array){
        //从下标1开始到下标array.length-1选择插入
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (j > 0 && array[j] < array[j - 1]){
                array[j]=array[j]^array[j-1];
                array[j-1]=array[j]^array[j-1];
                array[j]=array[j]^array[j-1];
                j--;
            }
            //TODO
/*            for (int j = 0;j <= i;j++){
                if (array[j])
            }*/
        }
    }
}
