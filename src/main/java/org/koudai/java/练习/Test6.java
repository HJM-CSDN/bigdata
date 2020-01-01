package org.koudai.java.练习;

/**
 * @description
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/12/27 21:38
 */
public class Test6 extends Class0{
    public static void main(String[] args) {
        Class0 cc = new Class0(){

            @Override
            void show() { System.out.println("Hello world!"); }
        };
        cc.show();
    }
}
class Class0{
    Class0(){
        System.out.println("这是构造函数");
    }
    Class0(String string){
        System.out.println("构造函数的参数为"+string);
    }
    void show(){
        System.out.println("Hello!");
    }
}

