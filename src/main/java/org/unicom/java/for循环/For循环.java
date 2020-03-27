package org.unicom.java.for循环;

/**
 * @description
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/12/15 23:09
 */
public class For循环 {
    public static void main(String[] args) {
        int[] a = {1,2,3};

        String[] b = {"Hi","Fang","good"};

        for (String each : b) {
            System.out.println(each.toUpperCase());
        }

    }
}
