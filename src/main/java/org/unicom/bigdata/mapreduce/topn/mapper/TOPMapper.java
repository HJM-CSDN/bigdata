package org.unicom.bigdata.mapreduce.topn.mapper;

/**
 * @description topn问题的Mapper
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/29 14:42
 */
public class TOPMapper {
    //TODO
 /*    public static double[] getTopN(int n, double[] arr1, double[] arr2) {
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
    }*/

}
