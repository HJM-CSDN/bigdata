package org.koudai.bigdata.mapreduce.topnmmy.util;

/**
 * @description java从给定的数组中获取TOPN个元素的工具类
 * @author: Mmy mamingyume@foxmail.com
 * @create: 2018-09-25 16:32:30
 **/
public class TOPNUtil {
    /**
     * 本类内简单测试
     * @param args
     */
    public static void main(String[] args) {
        double[] source = {7,9,6,3,2};
        double[] topn = getTOPN(2, source);
        for (int i = 0; i < topn.length; i++) {
            System.out.println(topn[i]);
        }
    }

    /**
     * 从给定的数组中获取TOPN个元素的工具类
     * @param n 取前n位数据
     * @param arr
     * @return
     */
    public static double[] getTOPN(int n,double[] arr){
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < arr.length-i-1; j++) {
                if (arr[j]>arr[j+1]){
                    double temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            result[i] = arr[arr.length-1-i];
        }
        return result;
    }

    /**
     * 使用归并排序的思想求两个数组中的TOPN
     * @param n
     * @param arr1
     * @param arr2
     * @return
     */
    public static double[] getTOPNByMergeSort(int n,double[] arr1,double[] arr2){
        //该数组存储最大值,
        double[] result = new double[n];
        //传进来的数组，默认由大到小排序,游标i首先指向arr1第0下标的元素
        int i = 0;
        //传进来的数组，默认由大到小排序,游标j首先指向arr2第0下标的元素
        int j = 0;

        //保证i指向arr1中的为使用过的最大值,保证j指向arr2中未使用过的的最大值

        //给result数组赋值
        for (int k = 0; k < n; k++) {
            //如果arr1中的最大值比arr2中的最大值大,取出arr1中的最大值放入结果数组.arr1的游标i后移。
            if (arr1[i] > arr2[j]) {
                result[k] = arr1[i];
                i++;
                //如果arr2中的最大值比arr1中的最大值大,取出arr2中的最大值放入结果数组.arr2的游标j后移。
            } else {
                result[k] = arr2[j];
                j++;
            }
        }
        return result;
    }

    /**
     * 从给定的两个数组中取TOPN
     * 处理流程：先合并两个数组为一个数组，然后在从中取TOPN
     * @param n
     * @param arr1
     * @param arr2
     * @return
     */
    public static double[] getTOPN(int n,double[] arr1,double[] arr2){
        double[] mergeArray = new double[arr1.length+arr2.length];
        for (int i = 0; i < arr1.length ; i++) {
            mergeArray[i] = arr1[i];
        }
        for (int i = arr1.length; i < mergeArray.length; i++) {
            mergeArray[i] = arr2[i-arr1.length];
        }
        return getTOPN(n,mergeArray);
    }
}
