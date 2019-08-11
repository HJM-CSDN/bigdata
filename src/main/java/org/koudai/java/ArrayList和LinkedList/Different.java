package org.koudai.java.ArrayList和LinkedList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description ArrayList和LinkedList的区别
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2019/2/20 23:59
 */
public class Different {
    static final int N=500000;
    static long timeList(List list){
        long start=System.currentTimeMillis();
        Object o = new Object();
        for(int i=0;i<N;i++) {
            list.add(0, o);
        }
        return System.currentTimeMillis()-start;
    }
    static long readList(List list){
        long start=System.currentTimeMillis();
        for(int i=0,j=list.size();i<j;i++){

        }
        return System.currentTimeMillis()-start;
    }

    static List addList(List list){
        Object o = new Object();
        for(int i=0;i<N;i++) {
            list.add(0, o);
        }
        return list;
    }
    public static void main(String[] args) {
        System.out.println("ArrayList添加"+N+"条耗时："+timeList(new ArrayList()));
        System.out.println("LinkedList添加"+N+"条耗时："+timeList(new LinkedList()));

        List list1=addList(new ArrayList<>());
        List list2=addList(new LinkedList<>());
        System.out.println("ArrayList查找"+N+"条耗时："+readList(list1));
        System.out.println("LinkedList查找"+N+"条耗时："+timeList(list2));
    }
}
/**
 * ArrayList添加500000条耗时：26555
 * LinkedList添加500000条耗时：23
 * ArrayList查找500000条耗时：2
 * LinkedList查找500000条耗时：13
 *
 * 显然我们可以看出ArrayList更适合读取数据，linkedList更多的时候添加或删除数据。
 *
 * ArrayList内部是使用可増长数组实现的，所以是用get和set方法是花费常数时间的，但是如果插入元素和删除元素，
 * 除非插入和删除的位置都在表末尾，否则代码开销会很大，因为里面需要数组的移动。
 * LinkedList是使用双链表实现的，所以get会非常消耗资源，除非位置离头部很近。但是插入和删除元素花费常数时间。
 */