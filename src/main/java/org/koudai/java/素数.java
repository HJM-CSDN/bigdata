package org.koudai.java;

/**
 * @description
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2019/3/2 20:25
 */
public class 素数 {
    public static void main(String[] args) {
        int i = 0;
        int j = 0;
        for (i = 200;i<=500;i++){
            for (j = 2;j < i;j++){
                if ( i%j==0){
                   break;
                }
            }
            if (j >= i)
                System.out.print(i+" ");
        }
    }
}
