package org.koudai.java.Array;

import static org.apache.calcite.linq4j.Ord.reverse;

/**
 * @description 数组反转
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/7/27 16:27
 */
public class ReverseArray {
    public static void main(String[] args) {
        //定义一个数组
        int a[] = {32,65,12,89,41};

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }

        System.out.println();
        System.out.println("------------------------------------------");
        reverse(a);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
    }

    //定义一个数组反转
    public static void reverse(int[] arr){
        int temp; //中间变量

        for (int i = 0,j = arr.length-1; i < j ; i++,j--) {
            temp=arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }


}
