package org.unicom.java.排序算法大全;


import java.util.ArrayList;
import java.util.Collections;

/**
 * @author HJM
 * @description 猴子排序
 * 然而，有个看似笑话的方法声称可以用O(n)实现Bogo排序，
 * 依照量子理论的平行宇宙解释，使用量子随机性随机地重新排列元素，
 * 不同的可能性将在不同的宇宙中展开，总有一种可能猴子得到了正确的顺序，
 * 量子计算机找到了这个宇宙后，就开始毁灭其他排序不成功的宇宙，剩下一个
 * 观察者可以看到的正确顺序的宇宙。
 * 如果想要迈出这个看似荒诞，但令人无比兴奋的"高效算法"的第一步，请先
 * 证明"平行宇宙解释"的正确性。
 * @create 2018-12-01 10:20
 **/
public class BogoSort {
    public static void main(String[] args) {
        int[] arr = {23,4,3,24,6,1,2,44,42};
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        // 计数器
        int count = 0;
        Long startTime = System.currentTimeMillis();
        while(true){
            //有序 跳出循环
            if (isSorted(list)) {
                System.out.println("恭喜，运气真好,用了："+count+"次");
                break;
            }
            // 无序，继续打乱
            else {
                System.out.println("继续排序");
                shuffle(list);
                count += 1;
            }
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("用时: "+(endTime-startTime)+"ms");

    }
    // 集合打乱
    public static void shuffle(ArrayList<Integer> list){
        Collections.shuffle(list);
        for (Integer integer : list) {
            System.out.print(integer+"\t");
        }
        System.out.println();
    }
    // 判断集合是否有序
    public static boolean isSorted(ArrayList<Integer> list){
        boolean flag = false;
        for (int i = 0; i < list.size()-1; i++) {
            if(list.get(i) <= list.get(i+1)){
                flag = true;
            }else {
                flag = false;
                break;
            }
        }
        if (flag) return true;
        else return false;
    }
}