package org.koudai.java.练习;

/**
 * @description
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/12/31 10:46
 */
public class Test3 {
    static int a;
    public Test3(int a){
        this.a = a;
    }
    static void Method(){
        System.out.println(a);
    }

    public static void main(String[] args) {
        Test3 a = new Test3(15);
        Test3 b = new Test3(20);
        a.Method();
    }
}
