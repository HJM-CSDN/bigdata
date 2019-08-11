package org.koudai.java.排序算法大全;

import java.util.Arrays;

/**
 * @description 归并排序
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2016/10/8
 */
public class 归并排序 {
    public static void main(String[] args) {
        //递归版
        int[] array = {1,5,2,3,6,2,1,35,0};
        int[] result = new int[18];
        merge_sort_recursive(array,result,0,17);
        String s = Arrays.toString(array);
        System.out.println(s);


        //方法二:迭代法.
        int[] array2 = {1,5,2,3,6,2,1,35,0};
        String s2 = Arrays.toString(array2);
        merge_sort(array2);
        System.out.println(s2);
    }
    //递归版
    static void merge_sort_recursive(int[] arr, int[] result, int start, int end) {
        if (start >= end)
            return;
        int len = end - start, mid = (len >> 1) + start;//区间长度的一半加上开始位置得到第一个区域的结束位置.
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end; //第二个区域的开始位置是第一个区域的结束位置加1
        merge_sort_recursive(arr, result, start1, end1); //对两个区域分别进行归并排序
        merge_sort_recursive(arr, result, start2, end2);
        int k = start;
        while (start1 <= end1 && start2 <= end2)
            result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
        while (start1 <= end1)
            result[k++] = arr[start1++];
        while (start2 <= end2)
            result[k++] = arr[start2++];
        for (k = start; k <= end; k++)
            arr[k] = result[k];
    }
    public static void merge_sort(int[] arr) {
        int len = arr.length;
        int[] result = new int[len];
        merge_sort_recursive(arr, result, 0, len - 1);
    }

    //迭代版：
    public static void merge_sort2(int[] arr) {
        int[] orderedArr = new int[arr.length];
        for (int i = 2; i < arr.length * 2; i *= 2) {
            for (int j = 0; j < (arr.length + i - 1) / i; j++) {
                int left = i * j;
                int mid = left + i / 2 >= arr.length ? (arr.length - 1) : (left + i / 2);
                int right = i * (j + 1) - 1 >= arr.length ? (arr.length - 1) : (i * (j + 1) - 1);
                int start = left, l = left, m = mid;
                while (l < mid && m <= right) {
                    if (arr[l] < arr[m]) {
                        orderedArr[start++] = arr[l++];
                    } else {
                        orderedArr[start++] = arr[m++];
                    }
                }
                while (l < mid)
                    orderedArr[start++] = arr[l++];
                while (m <= right)
                    orderedArr[start++] = arr[m++];
                System.arraycopy(orderedArr, left, arr, left, right - left + 1);
            }
        }
    }
}
/**
递归法（Top-down）
        申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
        设定两个指针，最初位置分别为两个已经排序序列的起始位置
        比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
        重复步骤3直到某一指针到达序列尾
        将另一序列剩下的所有元素直接复制到合并序列尾

迭代法（Bottom-up）
        原理如下（假设序列共有 {\display style n} n个元素）：
        将序列每相邻两个数字进行归并操作，形成 {\display style ceil(n/2)} {\display style ceil(n/2)}个序列，排序后每个序列包含两/一个元素
        若此时序列数不是1个则将上述序列再次归并，形成 {\display style ceil(n/4)} {\display style ceil(n/4)}个序列，每个序列包含四/三个元素
        重复步骤2，直到所有元素排序完毕，即序列数为1
        */
