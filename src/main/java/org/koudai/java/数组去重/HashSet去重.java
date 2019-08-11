package org.koudai.java.数组去重;

import java.util.HashSet;
import java.util.Set;

/**
 * @description 数组去重
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2019/3/14 20:46
 */
public class HashSet去重 {
    public static void main(String[] args) {
        Object[] arr = {1,2,3,4,3,3,4,5,6};
        Object[] arr2 = {'a','b','b','c'};

        Object[] res = test(arr);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]+"\t");
        }

        System.out.println();

        Object[] res2 = test(arr2);
        for (int i = 0; i < res2.length; i++) {
            System.out.print(res2[i]+"\t");
        }
    }

    /**
     * 利用hashSet去重
     * @param arr
     * @return
     */
    public static Object[] test(Object[] arr){
        Set set = new HashSet();
        //遍历数组并存入集合，如果元素已存在则不会重复存入
        for (int i = 0;i < arr.length;i++){
            set.add(arr[i]);
        }
        return set.toArray();
    }

}
