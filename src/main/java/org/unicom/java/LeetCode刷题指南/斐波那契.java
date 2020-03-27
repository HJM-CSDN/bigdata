package org.unicom.java.LeetCode刷题指南;

import java.util.Scanner;

/**
 * @description 斐波那契数列
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2019/2/27 14:22
 * 斐波那契数列指的是这样一个数列
 * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233，377，610，987，1597，2584，4181，
 * 6765，10946，17711，28657，46368。
 */
public class 斐波那契 {
    public static void main(String[] args) {
        System.out.println("请输入斐波那契数列的个数：");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println("斐波那契数列的前"+n+"项为：");
        for (int i = 1;i<= n;i++){
            System.out.print(testFibonacci(i)+"\t");
            if (i % 5 ==0){
                System.out.println();
            }
        }
    }

    /**
     * 递归方式
     * @param n 斐波那契的第n项
     * @return 斐波那契的第n项的结果
     */
    public static int testFibonacci(int n){
        if (n == 1){
            return 0;
        }else if (n == 2){
            return 1;
        }else {
            return testFibonacci(n-1) + testFibonacci(n-2);
        }
    }
}
