package org.unicom.java;

/**
 * @description
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2019/3/2 20:25
 */


public class 素数 {
    public static void main(String[] args) {
        int count = 0;
        boolean isPrime = true;
        for (int i = 100; i < 201; i++) {
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.println(i);
                count+=1;
            }
            isPrime = true;
        }
        System.out.println("素数的个数有："+ count);
    }
}








