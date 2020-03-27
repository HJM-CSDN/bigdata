package org.unicom.java.进制转换;

import java.util.Scanner;

/**
 * @description 十进制转换为十六进制
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2019/3/11 19:29
 */
public class intToHex {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int i = 0;
        char[] s = new char[100];
        if (number == 0){
            System.out.println(0);
        }else {
            while (number != 0){
             int t = number%16;
             if (t >= 0 && t < 10){
                 s[i] = (char)(t+'0');
                 i++;
             }else {
                s[i] = (char)(t+'A'-10);
                i++;
             }
             number = number/16;
            }

            for (int j = i-1;j>=0;j--){
                System.out.print(s[j]);
            }
        }
    }
}
