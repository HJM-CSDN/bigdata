package org.unicom.java.排序算法大全;

/**
 * @description 二分查找
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/11/23 19:10
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] a = {1,2,3,5,6,7,8,10};
        int val = 2;
        int index = BinarySearch(a,val);
        System.out.println("查找的数"+val+"的下标为:"+index);
    }

    /**
     * 二分查找
     * @param a 传入一个有序的数组
     * @param val 要查找的值
     * @return 返回查到的索引,没有返回-1
     */

    public static int BinarySearch(int[] a,int val){
        int low = 0;
        int high = a.length - 1;
        while (low <= high){
           // int middle = (low+high)/2;
            int middle = (low+high) >> 1;  //效率较高的方式
            if (val == a[middle]){
                return middle;
            }else if (val < a[middle]){
                high = middle - 1;
            }else {
                low = middle + 1;
            }
        }
        return -1;
    }
}
