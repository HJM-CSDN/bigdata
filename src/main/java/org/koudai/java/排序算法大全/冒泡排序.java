package org.koudai.java.排序算法大全;

import java.util.Arrays;

/**
 * @description 冒泡排序
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2016/10/8
 */

/**
 * 冒泡排序的基本思想是，对相邻的元素进行两两比较，
 * 顺序相反则进行交换，这样，每一趟会将最小或最大的元素“浮”到顶端，
 * 最终达到完全有序
 * 时间复杂度依然为O(n2）
 */
public class 冒泡排序 {
    public static void main(String[] args){
        int[] array = {1,5,2,3,6,2,34,5,345,543,2,2,1,35,64,2,2,0,-98};

        bubbleSort(array);
        String s = Arrays.toString(array);
        System.out.println(s);
    }

    public static void bubbleSort(int[] array){
        for (int i = 0; i < array.length-1; i++) {//冒泡排序取topn只需要改array.length-1的值即可
            for (int j = 0; j < array.length-1-i; j++) {
                if (array[j]>array[j+1]){
                    array[j]=array[j]^array[j+1];
                    array[j+1]=array[j]^array[j+1];
                    array[j]=array[j]^array[j+1];
                }
            }
        }
    }

}
