package org.koudai.bigdata.mapreduce.wc.tester;

/**
 * @description 临时测试器
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/22 14:08
 */
public class Tester {
    public static void main(String[] args) {
         String s = "a b c  ";
         String[] items = s.split(" ",-1);//最后的空格切割也有效
         System.out.println(items.length);
    for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
    }

}
