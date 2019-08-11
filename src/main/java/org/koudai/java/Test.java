package org.koudai.java;

import java.util.Date;

/**
 * @description
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2019/3/6 21:59
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(2<<3);//2*8的最快速的方法
        System.out.println("It's "+ new Date());
        System.out.println("abcde".substring(2));//cde
        System.out.println("abcde".substring(2,3));//c

        String r1 = String.format("channel %s", "r1");
        System.out.println(r1);

    }
}
