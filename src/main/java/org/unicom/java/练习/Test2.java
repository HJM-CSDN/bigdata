package org.unicom.java.练习;

/**
 * @description
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/12/31 10:40
 */
public class Test2 {
    public static void main(String[] args) {
        AA aa = new BB();
        aa.ShowAB();
    }
}

abstract class AA{
    int a=5,b=4;
    abstract void ShowA();

    void ShowAB(){
        System.out.println(a + b);
    }
}

class BB extends AA{
    void ShowA(){
        System.out.println(a);
    }
    void ShowAB(){
        System.out.println(a*b);
    }
}