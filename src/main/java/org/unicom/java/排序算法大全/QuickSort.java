package org.unicom.java.排序算法大全;

import java.util.Arrays;

/**
 * @description 快速排序
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2016/10/8
 * https://blog.csdn.net/yushiyi6453/article/details/76407640
 */

/*public class QuickSort {
    public static void main(String[] args) {
        int[] numbers = {6,1,2,7,9,3,4,10,8};
        int len = numbers.length;
        //如果数组大于2的时候才开始排序
        if(len>1){
            quickSort(numbers,0,len-1);
        }
        System.out.println(Arrays.toString(numbers));
    }
    public static void  quickSort(int[] list, int low, int high) {
        if(low < high){
            int middle = getMiddle(list, low, high);
            quickSort(list, low, middle - 1);
            quickSort(list, middle + 1, high);
        }
    }
    public static int  getMiddle(int[] list, int low, int high) {
        int temp = list[low];
        while(low < high){
            while(low < high && temp <= list[high]){
                high--;
            }
          //  temp>=list[high]
            list[low] = list[high];
            while(low < high && list[low] <= temp){
                low++;
            }
      //      list[low]>temp
            list[high] = list[low];
        }
        list[low] = temp;
        return low;
    }
}*/
public class QuickSort {
    public static void main(String []args){
        int[] a = {6,1,2,7,9,3,4,10,8};
        int i = 0;
        int j = a.length-1;
        sort(a,i,j);
        System.out.print(Arrays.toString(a));
    }

    public static void sort(int[] a,int low,int high){
        int i = low;
        int j = high;
        int key = a[low];
        while(j>i){
            //从后往前比较
            while(j>i && a[j]>=key)  //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                j--;
            if(a[j]<=key){
                int temp = a[j];
                a[j] = a[i];
                a[i] = temp;
            }
            //从前往后比较
            while(j>i && a[i]<=key)//如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                i++;
            if(a[i]>=key){
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
            //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        //递归
        if(i>low) sort(a,low,i-1);//左边序列。第一个索引位置到关键值索引-1
        if(j<high) sort(a,j+1,high);//右边序列。从关键值索引+1到最后一个
    }

}
/**
 * 快排原理：
 *     在要排的数（比如数组A）中选择一个中心值key（比如A[0]），通过一趟排序将数组A分成两部分，其中以key为中心，
 *     key右边都比key大，key左边的都key小，然后对这两部分分别重复这个过程，直到整个有序。
 *     整个快排的过程就简化为了一趟排序的过程，然后递归调用就行了。
 */