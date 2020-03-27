package org.unicom.java.进制转换;

import java.util.Scanner;

/**
 * @description
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2019/3/11 15:40
 */
public class 进制转换 {
    public static void main(String[] args) {
        System.out.println("请输入一个数：");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        Byte num1 = (byte)num;
        System.out.println("你输入的数字为：");
        System.out.println(num1);
        System.out.println("二进制为：");
        System.out.println(Integer.toBinaryString(num));//十进制转换为二进制
        System.out.println("八进制为：");
        System.out.println(Integer.toOctalString(num1));//十进制转八进制
        System.out.println("十六进制为：");
        System.out.println(Integer.toHexString(num));//十进制转十六进制



    }
}
